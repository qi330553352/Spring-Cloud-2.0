package com.example.qixin.feign;

import com.example.qixin.entity.Users;
import com.example.qixin.hystrix.UsersApiHystrix;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 创  建   时  间： 2019/1/28 22:09
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@RestController
@Api(value = "用户组件")
@Resource(name = "对外接口")
@RequestMapping("/UsersApi")
@FeignClient(name="feign-mybatis-plugion",fallbackFactory=UsersApiHystrix.class)
public interface UsersApi {

    @GetMapping("/findById/{id}")
    @ApiImplicitParam(name = "id", value = "用户文件对象ID", required = true, dataType = "Long")
    @ApiOperation(value="获得用户文件对象", notes="根据id获得用户文件对象",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Users findById(@PathVariable("id") Long id);
}
