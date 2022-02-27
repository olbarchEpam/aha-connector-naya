package com.bigid.scanner;

import com.bigid.connectors.api.BigIDException;
import com.bigid.connectors.api.model.DataSourceObject;
import com.bigid.scanner.iterator.AhaIterator;
import com.bigid.scanner.properties.AhaProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Iterator;

@Log4j2
@RequiredArgsConstructor
public class AhaClient {
    @Getter
    private final AhaProperties ahaProperties;
    private final Client client;

    public void setProperty(String key, String value) {
        ahaProperties.setProperty(key, value);
    }

    public AhaClient(AhaProperties ahaProperties) {
        this.ahaProperties = ahaProperties;
        ClientConfig config = new ClientConfig();
        client = ClientBuilder.newClient(config).register(MoxyJsonFeature.class);
    }

    public Response request(AhaProperties properties, int page) throws BigIDException {
        String elementsPerPage = properties.getProperty("partitionSize");
        String path = properties.getProperty("path_to_scan");
        String container = properties.getProperty("container_name");
        String apiVersion = properties.getProperty("api");
        String perPageParameter = properties.getProperty("perPageParameter");
        String baseUrl = properties.getBaseUrl();
        MediaType mediaType = properties.getMediaType();
        String token = "Bearer " + properties.getAuthToken();
        String pagination = "?page=" + page;
        String fullPath = "/" + apiVersion + container + (path.startsWith("/") ? path : "/" + path) + pagination;
        Response response = client.target(baseUrl + fullPath)
                .queryParam(perPageParameter, elementsPerPage)
                .request(mediaType)
                .header("Authorization", token)
                .get();
        int status = response.getStatus();
        if(status != 200) {
            throw new BigIDException("Error. Status code " + status);
        }
        return response;
    }

    public Response requestById(AhaProperties properties, String id) {
        String path = properties.getProperty("path_to_scan");
        String container = properties.getProperty("container_name");
        String apiVersion = properties.getProperty("api");
        String baseUrl = properties.getBaseUrl();
        MediaType mediaType = properties.getMediaType();
        String token = "Bearer " + properties.getAuthToken();
        String fullPath = "/" + apiVersion + container + (path.startsWith("/") ? path : "/" + path);
        return client.target(baseUrl + fullPath)
                .request(mediaType)
                .header("Authorization", token)
                .get();
    }


    public Iterator<DataSourceObject> getObjectsTestScan(String dataSourceConnectionName, String containerName, String pathToScan) {
        ahaProperties.setProperty("partitionSize", "10");
        ahaProperties.setTestScan(true);
        return getObjectsFromDataSource(dataSourceConnectionName, containerName, pathToScan, true);
    }


    public Iterator<DataSourceObject> getObjectsFromDataSource(String dataSourceConnectionName, String containerName, String pathToScan, boolean topOnly) {
        ahaProperties.setProperty("name", dataSourceConnectionName);
        ahaProperties.setProperty("container_name", containerName);
        ahaProperties.setProperty("path_to_scan", pathToScan);
        ahaProperties.setProperty("top_only", Boolean.toString(topOnly));
        return new AhaIterator(ahaProperties, this);
    }
}
