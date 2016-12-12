package com.balidao.transreport.json;

import org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer;

import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CustomHibernateModule extends SimpleModule {

    private static final long serialVersionUID = 316315754586334819L;

    public CustomHibernateModule() {
        super(PackageVersion.VERSION);
        addSerializer(JavassistLazyInitializer.class, new LazyInitializerSerializer());
        addDeserializer(JavassistLazyInitializer.class, new LazyInitializerDeserializer());
    }
}
