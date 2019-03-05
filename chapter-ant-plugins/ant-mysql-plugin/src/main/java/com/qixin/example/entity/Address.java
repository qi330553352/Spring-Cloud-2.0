package com.qixin.example.entity;

import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 创  建   时  间： 2019/3/3 22:59
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Data
@Alias("Address")
public class Address implements Serializable {

    private Long id;
    /** 省 */
    private String province;
    /** 市 */
    private String city;
    /** 区 */
    private String area;
    /** 邮编 */
    private String zipCode;
}
