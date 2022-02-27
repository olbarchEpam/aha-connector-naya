package com.bigid.scanner.model;

import com.bigid.connectors.api.model.DataSourceObject;
import com.bigid.scanner.properties.AhaProperties;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Data
public class Idea implements AhaResponseModel {
    @Override
    public String getModelName() {
        return "Idea";
    }

    @Override
    public List<DataSourceObject> convertToDataSource(AhaProperties properties) {
        return null;
    }

    private String id;

    private String name;

    @XmlElement(name = "reference_num")
    private String referenceNum;

    @XmlElement(name = "product_id")
    private String productId;

    @XmlElement(name = "workflow_status")
    private WorkflowStatus workflowStatus;

    @XmlElement(name = "visibility")
    private String visibility;

    private Feature feature;

    private Description description;


}
