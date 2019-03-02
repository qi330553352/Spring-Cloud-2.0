package com.qixin.example.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 创  建   时  间： 2019/3/2 19:36
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Data
public class User implements Serializable{

    private Long id;
    private String name;
    private Date birthday;
    private Date createTime;

}
