package com.pollra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    TestRepository testRepository;

    public TestController(TestRepository testRepository){
        this.testRepository = testRepository;
    }

    @RequestMapping("/test")
    public ModelAndView test(){
        System.out.println(testRepository.getTest().get(0).getMessage());
        return new ModelAndView("test/innerTest");
    }
}
