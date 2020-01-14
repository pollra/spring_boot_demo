package com.pollra;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JspController {

    @RequestMapping("/")
    public String jspTest(){
        return "test";
    }
}
