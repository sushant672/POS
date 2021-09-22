package com.increff.pos.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class ApiController {

    @ApiOperation(value = "Say Hello")
    @RequestMapping(path = "/api/say-hello", method = RequestMethod.GET)
    public String sayHello()
    {
        return "Hello Sushant";
    }

    @ApiOperation(value = "Say Hello 2")
    @RequestMapping(path = "/api/say-hello2", method = RequestMethod.GET)
    public String sayHello2()
    {
        return "Hello World 2";
    }

}

