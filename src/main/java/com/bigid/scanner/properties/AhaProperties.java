package com.bigid.scanner.properties;

import com.bigid.connectors.api.DataSourceConnection;
import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.core.MediaType;
import java.util.Map;
import java.util.Properties;

@Getter
@Setter
public class AhaProperties {
    Properties properties;
    String authToken = "f297b2fe5c7d8646feea78ee0a24498969e652056429fa337334c571854caea9"; // todo
    private final MediaType mediaType = new MediaType("application", "json", "utf-8");
    private final String baseUrl;
    private boolean isTestScan;

    public AhaProperties(DataSourceConnection dataSourceConnection) {
        properties = new Properties();
        Map<String, Object> map = dataSourceConnection.getMap();
        properties.putAll(map);
        if(!properties.contains("partitionSize")) {
            properties.put("partitionSize", 5);
        }
        if(!properties.contains("perPageParameter")) {
            properties.put("perPageParameter", "per_page");
        }
        properties.setProperty("protocol", "https");
        properties.setProperty("baseDomain", "aha.io");
        properties.setProperty("api", "api/v1/");
        baseUrl = getBaseUrl();
    }

    public String getProperty(String key) {
        return properties.get(key).toString();
    }

    public void setProperty(String key, String value) {
        properties.put(key, value);
    }

    public String getBaseUrl() {
        String protocol = properties.getProperty("protocol");
        String subdomain = properties.getProperty("subdomain");
        String baseDomain = properties.getProperty("baseDomain");
        return protocol + "://" + subdomain + "." + baseDomain;
    }


}
