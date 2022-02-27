package com.bigid.scanner.model;

import com.bigid.connectors.api.model.DataSourceObject;
import com.bigid.scanner.properties.AhaProperties;
import lombok.Data;

import java.util.List;

@Data
public class WorkflowStatus implements AhaResponseModel {

    private String id;

    private String name;

    @Override
    public String getModelName() {
        return "Workflow";
    }

    @Override
    public List<DataSourceObject> convertToDataSource(AhaProperties properties) {
        return null;
    }


}
