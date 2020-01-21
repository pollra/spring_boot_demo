package com.pollra.persistence.mssql.procedures;

import com.pollra.web.entity.Todo;
import com.pollra.web.usecase.TodoResource;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MsProcRepository extends TodoResource {

    @Select("EXEC proc_todo_select_all")
    public Collection<Todo> readAll();

    @Select("EXEC proc_todo_select_one_i @proc_idx=#{idx}")
    public Todo readOne(@Param("idx") int idx);

    @Insert("EXEC proc_todo_insert_one_npm @proc_user_name=#{userName}, @proc_password=#{password}, @proc_message=#{message}")
    int create(Todo t);
    //  UPDATE tbl_todos SET message='업데이트 테스트', datetime=GETDATE() WHERE idx=2 AND password='123';
    @Update("EXEC proc_todo_update_one_ipm @proc_idx=#{idx}, @proc_password=#{password}, @proc_message=#{message}")
    int update(Todo t);

    @Delete("EXEC proc_todo_delete_one_ip @proc_idx=#{idx}, @proc_password=#{password}")
    int delete(Todo t);
}
