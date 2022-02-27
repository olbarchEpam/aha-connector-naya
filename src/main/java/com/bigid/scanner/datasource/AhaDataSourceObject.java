package com.bigid.scanner.datasource;

import com.bigid.connectors.api.model.FileDataSourceObject;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class AhaDataSourceObject extends FileDataSourceObject {

    private final String fileId;
    private final Date creationDate;
    private final String objectUrl;

    @Builder
    public AhaDataSourceObject(String dataSourceName,
                               String containerName,
                               String objectName,
                               Date lastModifiedDate,
                               String fileId,
                               String objectUrl,
                               Date creationDate) {
        super(dataSourceName, containerName, objectName, lastModifiedDate);
        this.fileId = fileId;
        this.creationDate = creationDate;
        this.objectUrl = objectUrl;
    }
}
