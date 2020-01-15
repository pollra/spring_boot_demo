package com.pollra.test;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MssqlRepository {

    @Select("SELECT * FROM t_test")
    public List<Test> getTest();

}
