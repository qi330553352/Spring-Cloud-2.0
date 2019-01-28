package com.example.qixin.hystrix;

import com.example.qixin.entity.Users;
import com.example.qixin.feign.UsersApi;
import feign.hystrix.FallbackFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 创  建   时  间： 2019/1/28 22:11
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@Component
public class UsersApiHystrix implements FallbackFactory<UsersApi> {

    @Override
    public UsersApi create(Throwable throwable) {
        log.info("用户组件出错啦:"+throwable);
        return new UsersApi(){
            @Override
            public Users findById(@PathVariable("id") Long id) {

                return null;
            }
        };
    }
}
