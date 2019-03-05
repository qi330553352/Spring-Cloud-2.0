package com.qixin.example.controller;

import com.qixin.example.entity.Address;
import com.qixin.example.entity.Grade;
import com.qixin.example.entity.User;
import com.qixin.example.service.AddressService;
import com.qixin.example.service.GradeService;
import com.qixin.example.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 创  建   时  间： 2019/3/2 19:40
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Log4j2
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private AddressService addressService;

    @ResponseBody
    @RequestMapping(value = "/findUsers")
    public List<User> findUsers() {
        Grade grade = gradeService.findById(1L);
        log.info(grade);
        List<Address> addresses = new ArrayList<>();
        for(int i=0;i<3;i++){
            Address address = new Address();
            address.setProvince("a"+i);
            address.setCity("b"+i);
            address.setArea("c"+i);
            address.setZipCode("abc"+i);
            addresses.add(address);
        }
        addressService.saves(addresses);
        List<User> list = userService.findUsers();
        return list;
    }

    @RequestMapping("xxxTest")
    public ModelAndView getTestHtml() {
        log.info("----------------------------------");
        return new ModelAndView("test.html");
    }
}
