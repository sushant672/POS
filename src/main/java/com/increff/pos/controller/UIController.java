package com.increff.pos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class UIController {

    @Value("${app.baseUrl}")
    private String baseUrl;

    @RequestMapping(value = "")
    public ModelAndView index() {
        return mav("index.html");
    }

    @RequestMapping(value = "/ui/home")
    public ModelAndView home() {
        return mav("home.html");
    }

    @RequestMapping(value = "/ui/brand")
    public ModelAndView brands() {
        return mav("brand.html");
    }

    @RequestMapping(value = "/ui/learn")
    public ModelAndView learn() {
        return mav("learn.html");
    }




    private ModelAndView mav(String page) {
        ModelAndView mav = new ModelAndView(page);
        //mav.addObject("info", new InfoData());
        mav.addObject("baseUrl", baseUrl);
        return mav;
    }

}