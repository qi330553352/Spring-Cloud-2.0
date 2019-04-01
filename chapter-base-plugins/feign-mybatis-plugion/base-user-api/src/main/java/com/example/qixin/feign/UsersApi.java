package com.example.qixin.feign;

import com.example.qixin.entity.Users;
import com.example.qixin.hystrix.UsersApiHystrix;
import io.swagger.annotations.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 创  建   时  间： 2019/1/28 22:09
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@RestController
@Api(value = "用户组件", description = "消息操作 API", position = 100, protocols = "http")
@Resource(name = "对外接口")
@RequestMapping("/UsersApi")
@FeignClient(name="feign-mybatis-plugion",fallbackFactory=UsersApiHystrix.class)
public interface UsersApi {

    @GetMapping("/findById/{id}")
    @ApiImplicitParam(name = "id", value = "用户文件对象ID", required = true, dataType = "Long")
    @ApiOperation(value="获得用户文件对象", notes="根据id获得用户文件对象",httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Users findById(@PathVariable("id") Long id);

    @GetMapping(value = "/findAll",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "消息列表",notes = "完整的消息内容列表",produces="application/json, application/xml",consumes="application/json, application/xml",response = List.class)
    List<Users> findAll();

    @PostMapping("/save")
    @ApiOperation(value = "添加消息", notes = "根据参数创建消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "消息 ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "text", value = "正文", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "summary", value = "摘要", required = false, dataType = "String", paramType = "query"),
    })
    int save(@RequestBody Users users);

    @PutMapping("/updateById/{id}")
    @ApiOperation(value = "修改消息", notes = "根据参数修改消息")
    @ApiResponses({
            @ApiResponse(code = 100, message = "请求参数有误"),
            @ApiResponse(code = 101, message = "未授权"),
            @ApiResponse(code = 103, message = "禁止访问"),
            @ApiResponse(code = 104, message = "请求路径不存在"),
            @ApiResponse(code = 200, message = "服务器内部错误")
    })
    int updateById(@PathVariable("id") Long id,@RequestBody Users users);

    @DeleteMapping("/deleteById/{id}")
    int deleteById(@PathVariable("id") Long id);
}
