package com.bigid.scanner.model;

import com.bigid.connectors.api.model.DataSourceObject;
import com.bigid.scanner.properties.AhaProperties;
import lombok.Data;
import org.apache.sis.internal.jaxb.gml.DateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

@Data
public class Release implements AhaResponseModel {

    private String id;

    @XmlElement(name = "product_id")
    private String productId;

    @XmlElement(name = "reference_num")
    private String referenceNum;

    private String name;

    @XmlElement(name = "start_date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date startDate;

    @XmlElement(name = "release_date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date releaseDate;

    private Boolean released;

    @XmlElement(name = "parking_lot")
    private Boolean parkingLot;

    @XmlElement(name = "workflow_status")
    private WorkflowStatus workflowStatus;


    @Override
    public String getModelName() {
        return "Release";
    }

    @Override
    public List<DataSourceObject> convertToDataSource(AhaProperties properties) {
        return null;
    }


}
