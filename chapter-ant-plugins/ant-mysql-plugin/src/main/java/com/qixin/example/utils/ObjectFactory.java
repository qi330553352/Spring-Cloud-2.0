package com.qixin.example.utils;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

/**
 * 创  建   时  间： 2019/3/3 21:37
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class ObjectFactory extends DefaultObjectFactory {

    private Logger log = LoggerFactory.getLogger(ObjectFactory.class);

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        T result = super.create(type, constructorArgTypes, constructorArgs);
        log.info("创建对象方法："+result.getClass().getSimpleName());
        return result;
    }

    @Override
    public <T> boolean isCollection(Class<T> type) {
        return super.isCollection(type);
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        log.info("初始化参数：［"+properties.toString()+"]");
    }
}
