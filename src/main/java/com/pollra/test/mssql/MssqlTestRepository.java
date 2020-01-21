package com.pollra.test.mssql;

import com.pollra.test.Test;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Repository
public interface MssqlTestRepository {

    @Select("SELECT * FROM t_test")
    public List<Test> getTTest();

}
