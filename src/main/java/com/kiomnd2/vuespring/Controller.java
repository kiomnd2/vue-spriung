package com.kiomnd2.vuespring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller
{

    @RequestMapping(method = RequestMethod.GET, value = "/api/hello")
    public String hello() {
        System.out.println("hello");
        return "hello";
    }


}
