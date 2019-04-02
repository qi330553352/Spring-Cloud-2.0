package com.example.qixin.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 创  建   时  间： 2019/1/28 22:07
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Data
@Entity
@NoArgsConstructor
public class Users implements Serializable{

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;
    private Date createTime;

    public Users(String name, Integer age, Date createTime) {
        this.name = name;
        this.age = age;
        this.createTime = createTime;
    }
}
