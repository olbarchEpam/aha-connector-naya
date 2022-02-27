package com.bigid.scanner.model;

import com.bigid.connectors.api.model.DataSourceObject;
import com.bigid.scanner.datasource.AhaDataSourceObject;
import com.bigid.scanner.properties.AhaProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Features implements AhaResponseModel, AhaResponseModelPageable {
    private List<Feature> features;

    private Pagination pagination;

    @Override
    public String getModelName() {
        return "Features";
    }

    @Override
    public List<DataSourceObject> convertToDataSource(AhaProperties properties) {
        return features
                .stream()
                .map(f -> convert(f, properties))
                .collect(Collectors.toList());
    }

    public DataSourceObject convert(Feature feature, AhaProperties properties) {
        return AhaDataSourceObject.builder()
                .containerName(properties.getProperty("container_name"))
                .objectName(properties.getProperty("path_to_scan") + feature.getName())
                .dataSourceName(properties.getProperty("name"))
                .lastModifiedDate(feature.getCreated_at())
                .fileId(feature.getId())
                .creationDate(feature.getCreated_at())
                .objectUrl(feature.getUrl())
                .build();
    }


}
