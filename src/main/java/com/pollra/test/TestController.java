package com.pollra.test;

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
//        System.out.println(testRepository.getTest().get(0).getMessage());
        ModelAndView mav = new ModelAndView("test/innerTest");
        mav.addObject("msg",testRepository.getTest().get(0).getMessage());
        return mav;
    }
}
