package com.luigimoro.spring.security.http.resource;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminService {


    @GetMapping(value = "/checkuser", params = {"name","surname"})
    @ResponseBody
    public String checkUser(String name, String surname) {


        System.out.println("Name is" + name + " while surname is " + surname);

        return "{\"result\":\"ok\"}";

    }

}

