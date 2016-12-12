package com.balidao.transreport.json;

import java.io.IOException;
import java.util.Date;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime>{

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator generator, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        Date date = localDateTime.toDate();
        generator.writeString(CustomDateTimeModule.DATE_FORMAT.format(date));
    }

}
