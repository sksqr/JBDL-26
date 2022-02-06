package com.gfg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class WelcomeController {

    private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @GetMapping("/")
    public ModelAndView welcome() {
        logger.debug("Welcome to JDBL-26...");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.getModelMap().put("msg","Hello!!");
        modelAndView.getModelMap().put("today",new Date());
        return modelAndView;

    }

    public String getMessage() {
        return "Hello World";
    }

}
