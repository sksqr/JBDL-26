package com.example.controllerdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class WebController {


    private static Logger logger = LoggerFactory.getLogger(WebController.class);

    @Value("${value.from.file}")
    private String propertyValue;

    List<Product> productList;


    @PostConstruct
    public void initProduct(){
        productList=new ArrayList<>();
        productList.add(new Product(1,"mobile"));
        productList.add(new Product(2,"laptop"));
        productList.add(new Product(3,"smart band"));
    }

    @GetMapping("/menu")
    public ModelAndView getMenu(){
        logger.warn("propertyValue: {}",propertyValue );
        ModelAndView modelAndView = new ModelAndView("dynamic-menu.html");
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY hh:mm:ss");
        modelAndView.getModelMap().put("serverTime",dateFormat.format(new Date()));
        modelAndView.getModelMap().put("products",productList);
        return modelAndView;
    }


    @GetMapping("/productList")
    @ResponseBody
    public List<Product> getMenuString(){
        return productList;
    }

    @GetMapping("dynamic")
    public ModelAndView getDynamicPage(){
        ModelAndView modelAndView = new ModelAndView("dynamic-file.html");
        return modelAndView;
    }



}
