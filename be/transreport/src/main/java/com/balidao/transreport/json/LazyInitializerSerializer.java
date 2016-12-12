package com.balidao.transreport.json;

import java.io.IOException;

import org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LazyInitializerSerializer extends JsonSerializer<JavassistLazyInitializer>{

    public void serialize(JavassistLazyInitializer lazy, JsonGenerator generator, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        
    }

}
