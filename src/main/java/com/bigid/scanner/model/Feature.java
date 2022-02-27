package com.bigid.scanner.model;

import com.bigid.connectors.api.model.DataSourceObject;
import com.bigid.scanner.properties.AhaProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feature implements AhaResponseModel {

    @Override
    public String getModelName() {
        return "Feature";
    }

    @Override
    public List<DataSourceObject> convertToDataSource(AhaProperties properties) {
        return null;
    }

    private String id;

    @XmlElement(name = "product_id")
    private String productId;

    private String name;

    private String url;

    @XmlElement(name = "created_at")
    private Date created_at;

    @XmlElement(name = "reference_num")
    private String referenceNum;

    private Description description;

    @XmlElement(name = "workflow_status")
    private WorkflowStatus workflowStatus;

    @XmlElement(name = "ideas")
    private Idea[] ideas;

    private Release release;


}
