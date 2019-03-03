package com.qixin.example.controller;

import com.qixin.example.entity.User;
import com.qixin.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 创  建   时  间： 2019/3/2 19:40
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/findUsers")
    public List<User> findUsers() {
        List<User> list = userService.findUsers();
        return list;
    }

    @RequestMapping("xxxTest")
    public ModelAndView getTestHtml() {
        System.out.println("=======================================");
        return new ModelAndView("test.html");
    }
}
