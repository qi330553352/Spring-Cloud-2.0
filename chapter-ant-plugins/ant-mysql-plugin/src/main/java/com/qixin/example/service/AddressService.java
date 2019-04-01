package com.qixin.example.service;

import com.qixin.example.entity.Address;
import com.qixin.example.mapper.AddressMapper;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创  建   时  间： 2019/3/5 23:25
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Service
public class AddressService {

    @Autowired
    private AddressMapper addressMapper;

    //@Transactional(isolation= Isolation.DEFAULT,rollbackFor=FundException.class,propagation= Propagation.REQUIRED)
    public void saves(List<Address> list) {
        Map<String,Object> map = new HashMap<>();
        MapUtils.getString(map,"key");//
        System.out.println(addressMapper.saves(list));
    }
}
