package com.qixin.example.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

/** 年级
 * 创  建   时  间： 2019/3/5 22:04
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Data
@Alias("Grade")
public class Grade implements Serializable {

    private Long id;
    /** 年级名称 */
    private String name;
    private List<User> users;

}
