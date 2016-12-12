package com.balidao.transreport.common;

import com.balidao.transreport.json.CustomJsonObjectMapper;

public class JsonUtil {

    private static CustomJsonObjectMapper objectMapper;
    
    public static CustomJsonObjectMapper getObjectMapper() {
        if(objectMapper == null) {
            objectMapper = new CustomJsonObjectMapper();
        }
        return objectMapper;
    }
    
}
