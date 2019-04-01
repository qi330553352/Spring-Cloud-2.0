package com.example.qixin.design.builder;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.exceptions.ExceptionFactory;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactoryBuilder() {
    }

    public SqlSessionFactoryBuilder builder(Reader reader){

        return null;
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }

    public SqlSessionFactory build(Reader reader, String environment, Properties properties) {
        SqlSessionFactory sessionFactory;

        try {
            XMLConfigBuilder parser = new XMLConfigBuilder(reader, environment, properties);
            sessionFactory = this.build(parser.parse());
        }catch (Exception e){
            throw ExceptionFactory.wrapException("Error building SqlSession.", e);
        }finally {
            ErrorContext.instance().reset();
            try {
                reader.close();
            } catch (IOException ignored) {
            }
        }
        return sessionFactory;
    }

    public SqlSessionFactory build(InputStream inputStream, String environment, Properties properties) {
        SqlSessionFactory sessionFactory;

        try {
            XMLConfigBuilder parser = new XMLConfigBuilder(inputStream, environment, properties);
            sessionFactory = this.build(parser.parse());
        }catch (Exception e){
            throw ExceptionFactory.wrapException("Error building SqlSession.", e);
        }finally {
            ErrorContext.instance().reset();
            try {
                inputStream.close();
            } catch (IOException ignored) {
            }
        }
        return sessionFactory;
    }
}
