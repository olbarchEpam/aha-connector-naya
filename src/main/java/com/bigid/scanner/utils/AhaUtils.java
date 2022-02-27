package com.bigid.scanner.utils;

import com.bigid.connectors.api.BigIDException;
import com.bigid.scanner.model.AhaResponseModel;
import com.bigid.scanner.properties.AhaProperties;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class AhaUtils {

    private static final Map<String, Class<? extends AhaResponseModel>> models = ModelScanner.getDataTypes();

    private void checkFields(String containerName, String dataSourceConnectionName) throws BigIDException {
        if (containerName == null || containerName.isEmpty()) {
            throw new BigIDException("Container name is null or empty");
        }
        if (StringUtils.isEmpty(dataSourceConnectionName)) {
            throw new BigIDException("The dataSourceConnectionName must be specified");
        }
    }


    public static Class<? extends AhaResponseModel> getEntity(AhaProperties properties) {
        String containerName = properties.getProperty("container_name");
        return models.get(containerName);
    }



}
