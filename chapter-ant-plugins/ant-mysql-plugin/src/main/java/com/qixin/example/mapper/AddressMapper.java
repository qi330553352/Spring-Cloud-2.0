package com.qixin.example.mapper;

import com.qixin.example.entity.Address;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 创  建   时  间： 2019/3/3 23:04
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Repository
public interface AddressMapper {

    Address findById(@Param("id")Long id);
}
