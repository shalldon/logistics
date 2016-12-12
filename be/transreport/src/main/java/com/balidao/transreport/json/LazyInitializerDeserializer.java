package com.balidao.transreport.json;

import java.io.IOException;

import org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LazyInitializerDeserializer extends JsonDeserializer<JavassistLazyInitializer>{

    public JavassistLazyInitializer deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        return null;
    }

}
