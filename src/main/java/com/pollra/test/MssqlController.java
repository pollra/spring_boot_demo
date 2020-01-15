package com.pollra.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/ms")
public class MssqlController {

    private MssqlRepository mssqlRepository;

    public MssqlController(MssqlRepository mssqlRepository) {
        this.mssqlRepository = mssqlRepository;
    }

    @GetMapping("test")
    public ModelAndView test(){
        List<Test> list = mssqlRepository.getTest();
        ModelAndView model = new ModelAndView("ms/test");
        model.addObject("msg", list.get(0).getMessage());
        return model;
    }
}
