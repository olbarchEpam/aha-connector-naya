package com.bigid.scanner.model;

import com.bigid.connectors.api.model.DataSourceObject;
import com.bigid.scanner.properties.AhaProperties;

import java.io.Serializable;
import java.util.List;


public interface AhaResponseModel extends Serializable {

    String getModelName();

    List<DataSourceObject> convertToDataSource(AhaProperties properties);


}
