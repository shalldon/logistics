package com.balidao.transreport.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CustomJsonObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = 5507693655085537464L;

    public CustomJsonObjectMapper() {
        this.registerModule(new CustomDateTimeModule());
        this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
}
