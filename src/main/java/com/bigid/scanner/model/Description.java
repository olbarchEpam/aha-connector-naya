package com.bigid.scanner.model;

import com.bigid.connectors.api.model.DataSourceObject;
import com.bigid.scanner.properties.AhaProperties;
import lombok.Data;

import java.util.List;

@Data
public class Description implements AhaResponseModel {

    private String id;

    private String body;

    @Override
    public String getModelName() {
        return "Description";
    }

    @Override
    public List<DataSourceObject> convertToDataSource(AhaProperties properties) {

        return null;
    }



}
