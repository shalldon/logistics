package com.balidao.transreport.json;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CustomDateTimeModule extends SimpleModule {

    private static final long serialVersionUID = 5877266557261221487L;

    public static final String DATE_TIME_PATTERN = "yyyy/MM/dd HH:mm:ss";
    
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_TIME_PATTERN);
    
    public CustomDateTimeModule() {
        super(PackageVersion.VERSION);
        addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        addSerializer(Date.class, new DateSerializer());
        addDeserializer(Date.class, new DateDeserializer());
    }

    @Override
    public String getModuleName() {
        return getClass().getSimpleName();
    }
}
