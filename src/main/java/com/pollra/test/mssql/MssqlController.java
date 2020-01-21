package com.pollra.test.mssql;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ms")
public class MssqlController {

    MssqlTestRepository mssqlTestRepository;

    public MssqlController(MssqlTestRepository mssqlTestRepository) {
        this.mssqlTestRepository = mssqlTestRepository;
    }

    @GetMapping("test")
    public ModelAndView test(){
        ModelAndView model = new ModelAndView("test/innerTest");
        model.addObject("msg",mssqlTestRepository.getTTest().get(0).getMessage());
        return model;
    }
}
