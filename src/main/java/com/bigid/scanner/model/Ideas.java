package com.bigid.scanner.model;

import com.bigid.connectors.api.model.DataSourceObject;
import com.bigid.scanner.properties.AhaProperties;
import lombok.Data;

import java.util.List;

@Data
public class Ideas implements AhaResponseModel, AhaResponseModelPageable {

    private List<Idea> ideas;

    private Pagination pagination;

    @Override
    public String getModelName() {
        return "Ideas";
    }

    @Override
    public List<DataSourceObject> convertToDataSource(AhaProperties properties) {
        return null;
    }

}
