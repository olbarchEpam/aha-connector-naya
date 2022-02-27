package com.bigid.scanner;

import com.bigid.sdk.unstructured.FileService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
@Scope("prototype")
public class AhaFileService implements FileService<List<String>> { // TODO
    @Override
    public InputStream getFileInputStream(Map<String, Object> map) throws IOException {
        return null;
    }

    @Override
    public FileContainer listFiles(List<String> sfoDataObjects) throws IOException {
        return null;
    }




}
