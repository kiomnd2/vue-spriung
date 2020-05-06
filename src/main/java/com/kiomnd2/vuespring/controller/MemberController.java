package com.kiomnd2.vuespring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController
{

    @RequestMapping(method = RequestMethod.POST, value = "/api/hello")
    public String hello() {
        System.out.println("hello");
        return "hello";
    }


}
