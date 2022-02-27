package com.bigid.scanner.iterator;

import com.bigid.connectors.api.BigIDException;
import com.bigid.connectors.api.model.DataSourceObject;
import com.bigid.scanner.AhaClient;
import com.bigid.scanner.model.AhaResponseModel;
import com.bigid.scanner.model.AhaResponseModelPageable;
import com.bigid.scanner.model.Pagination;
import com.bigid.scanner.properties.AhaProperties;
import com.bigid.scanner.utils.AhaUtils;
import lombok.SneakyThrows;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.*;

public class AhaIterator implements Iterator<DataSourceObject> {

    private final AhaProperties properties;
    private final AhaClient client;
    private final List<DataSourceObject> queue = new ArrayList<>();
    private int currentPage = 1;
    private int totalPages = 1;
    private int totalRecords = 0;

    @SneakyThrows
    public AhaIterator(AhaProperties properties, AhaClient client) {
        this.properties = properties;
        this.client = client;
        List<DataSourceObject> callResult = apiCall(properties, currentPage++);
        queue.addAll(callResult);
    }

    @Override
    @SneakyThrows
    public boolean hasNext() {
        if (!queue.isEmpty()) {
            return true;
        }
        if (currentPage < totalPages && !properties.isTestScan()) {
            List<DataSourceObject> callResult = apiCall(properties, currentPage);
            queue.addAll(callResult);
        }
        return !queue.isEmpty();
    }

    @Override
    public DataSourceObject next() {
        if (hasNext()) {
            return queue.remove(0);
        }
        throw new NoSuchElementException();
    }

    private List<DataSourceObject> apiCall(AhaProperties properties, int page) throws BigIDException {
        Response response = client.request(properties, page);
        Class<? extends AhaResponseModel> entity = AhaUtils.getEntity(properties);
        AhaResponseModel ahaEntity = response.readEntity(new GenericType<>(entity));
        if (ahaEntity instanceof AhaResponseModelPageable) {
            Pagination pagination = ((AhaResponseModelPageable) ahaEntity).getPagination();
            totalPages = pagination.getTotal_pages();
            currentPage = pagination.getCurrent_page();
            totalRecords = pagination.getTotal_records();
        }
        return ahaEntity.convertToDataSource(properties);
    }

}
