package com.example.qixin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/** MyBatis配置类
 * 创  建   时  间： 2019/1/22 1:00
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Configuration
@MapperScan(basePackages = "com.example.qixin.mapper")
public class MybatisConfiguration {

}
