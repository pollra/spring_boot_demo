package com.pollra.test;


import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository {

    @Select("SELECT * FROM tbl_test")
    public List<Test> getTest();
}
