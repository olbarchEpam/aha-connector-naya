package com.bigid.scanner;

import com.bigid.connectors.api.BigIDException;
import com.bigid.connectors.api.DataSourceConnection;
import com.bigid.connectors.api.ScanContext;
import com.bigid.connectors.api.model.DataSourceObject;
import com.bigid.scanner.properties.AhaProperties;
import com.bigid.sdk.unstructured.BasicUnstructuredConnector;
import com.bigid.sdk.unstructured.CustomFilter;
import com.bigid.sdk.unstructured.FileService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Iterator;

@Log4j2
@Component
@Scope("prototype")
@Getter
@Setter
@NoArgsConstructor
public class AhaConnector extends BasicUnstructuredConnector {

    AhaFileService ahaFileService;
    AhaClient ahaClient;

    public AhaConnector(AhaFileService ahaFileService) {
        this.ahaFileService = ahaFileService;
    }


    @Override
    public FileService initFileService(DataSourceConnection dataSourceConnection) throws BigIDException {
        return null;
    }

    @Override
    public CustomFilter initCustomFilter() {
        return null;
    }

    @Override
    public FileService getFileService() {
        return null;
    }

    @Override
    public Iterator<DataSourceObject> getObjectsFromDataSource(String dataSourceConnectionName, String containerName, @Nullable String pathToScan, boolean topOnly) throws BigIDException {
        return ahaClient.getObjectsFromDataSource(dataSourceConnectionName, containerName, pathToScan, topOnly);
    }

    @Override
    public void validateConfigs(DataSourceConnection dataSourceConnection) throws BigIDException {

    }

    @Override
    public Iterator<DataSourceObject> getObjectsTestScan(String dataSourceConnectionName, String containerName, @Nullable String pathToScan) throws BigIDException {
        return ahaClient.getObjectsTestScan(dataSourceConnectionName, containerName, pathToScan);
    }

    @Override
    public Iterator<String> getContainers() throws BigIDException {
        return null;
    }

    @Override
    public void init(ScanContext scanContext) throws BigIDException, IOException {
        DataSourceConnection connection = scanContext.getConnection();
        AhaProperties ahaProperties = new AhaProperties(connection);
        ahaClient = new AhaClient(ahaProperties);
    }

    @Override
    public void close() {

    }
}
