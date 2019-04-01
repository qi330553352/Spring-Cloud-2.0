package com.example.qixin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 创  建   时  间： 2019/1/28 22:07
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Data
@NoArgsConstructor
public class Users implements Serializable{

    private Long id;
    private String name;
    private Integer age;
    private Date createTime;

}
