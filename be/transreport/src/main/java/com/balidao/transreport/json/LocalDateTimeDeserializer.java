package com.balidao.transreport.json;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime>{

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        Date date = new Date();
        try {
            date = CustomDateTimeModule.DATE_FORMAT.parse(parser.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return LocalDateTime.fromDateFields(date);
    }

}
