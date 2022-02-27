package com.bigid.scanner.utils;

import com.bigid.scanner.model.AhaResponseModel;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ModelScanner {

    public static Map<String, Class<? extends AhaResponseModel>> getDataTypes() {
        Reflections reflections = new Reflections("com.bigid.scanner.model", Scanners.SubTypes);
        Set<Class<? extends AhaResponseModel>> subTypesOf = reflections.getSubTypesOf(AhaResponseModel.class);
        return subTypesOf.stream().collect(Collectors.toMap(t -> t.getSimpleName().toLowerCase(Locale.ROOT), t -> t));
    }

}
