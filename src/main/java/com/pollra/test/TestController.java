package com.pollra.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {

    TestRepository testRepository;

    public TestController(TestRepository testRepository){
        this.testRepository = testRepository;
    }

    @RequestMapping("inner")
    public ModelAndView test(){
        ModelAndView mav = new ModelAndView("test/innerTest");
        mav.addObject("msg",testRepository.getTest().get(0).getMessage());
        return mav;
    }
}
