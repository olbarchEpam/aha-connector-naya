package com.bigid.scanner.model;

import com.bigid.connectors.api.model.DataSourceObject;
import com.bigid.scanner.properties.AhaProperties;
import lombok.Data;

import java.util.List;

@Data
public class Releases implements AhaResponseModel, AhaResponseModelPageable {

    private List<Release> releases;

    private Pagination pagination;

    @Override
    public String getModelName() {
        return "Releases";
    }

    @Override
    public List<DataSourceObject> convertToDataSource(AhaProperties properties) {
        return null;
    }

}
