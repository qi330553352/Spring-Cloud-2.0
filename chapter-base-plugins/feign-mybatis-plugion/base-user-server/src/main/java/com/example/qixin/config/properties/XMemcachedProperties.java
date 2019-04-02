package com.example.qixin.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 创 建 时 间: 2019/4/2
 * 版       本: V1.0
 * 作       者: qixin
 * 版 权 所 有: 版权所有(C)2019-2029
 */
@Data
@Component
@ConfigurationProperties(prefix = "memcached")
public class XMemcachedProperties {

    private String servers;
    private int poolSize;
    private long opTimeout;
}
