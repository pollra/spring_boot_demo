package com.pollra.web.usecase;

import com.pollra.persistence.mssql.procedures.MsProcRepository;
import com.pollra.tools.InspectionTool;
import com.pollra.web.entity.Todo;
import com.pollra.persistence.mssql.basic.MsBasicRepository;
import com.pollra.web.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Service
public class TodoService implements TodoResource{
    private MsProcRepository repository;

    public TodoService(MsProcRepository repository) {
        this.repository = repository;
    }
    @Override
    public Collection<Todo> readAll() {
        Collection<Todo> result;
        try{
            result = repository.readAll();
        }catch (TodoException e){
            log.error(e.getMessage());
            result = new ArrayList<Todo>();
        }
        return result;
    }

    @Override
    public Todo readOne(int idx){
        Todo result;
        try{
            if(idx <= 0 ) throw new TodoCannotExistException("입력된 idx 가 0 이하 입니다(idx 는 0이 존재할 수 없습니다)");
            result = repository.readOne(idx);
        }catch (TodoException e){
            log.error(e.getMessage());
            result = new Todo();
        }
        return result;
    }

    @Override
    public int create(Todo todo){
        int result = 0;
        if(todo.getMessage().trim().equals("") || todo.getPassword().trim().equals("") || todo.getUserName().trim().equals("")) {
            throw new TodoInputDataNotFoundException("입력된 데이터 중 비어있는 값이 존재합니다: " + todo.toString());
        }
        try{
            repository.create(todo);
        }catch (TodoException e){
            log.error("[!] 예상하지 못한 에러: "+e.getMessage());
            result = 10001;
        }
        return result;
    }

    @Override
    public int update(Todo todo){
        int result;
        if(todo.getMessage().trim().equals("")){
            throw new TodoInputDataNotFoundException("수정하려는 값이 비어있습니다: "+ todo.toString());
        } else if(todo.getIdx() <= 0) throw new TodoCannotExistException("존재할 수 없는 인덱스 접근 입니다");
        log.info("입력된 값: "+todo.toString());
        try{
            result = repository.update(todo);
        }catch (TodoException e){
            log.error(e.getMessage());
            result = 10001;
        }
        if(result != 1) throw new TodoOutputQueryException("컨텐츠 수정에 실패했습니다");
        return result;
    }

    @Override
    public int delete(Todo todo){
        int result = 0;
        if(todo.getIdx() <= 0) {
            throw new TodoCannotExistException("존재할 수 없는 범위의 값입니다");
        }else if(todo.getPassword().trim().equals("")){
            throw new TodoInputDataNotFoundException("입력되지 않은 값이 존재합니다: "+todo.toString());
        }
        try {
            result = repository.delete(todo);
        }catch (TodoException e){
            log.error(e.getMessage());
            result = 10001;
        }
        if(result != 1) throw new TodoOutputQueryException("컨텐츠 삭제에 실패했습니다");
        return result;
    }
}
