package com.pollra.web.controller;

import com.pollra.tools.InspectionTool;
import com.pollra.web.entity.Todo;
import com.pollra.web.exception.TodoException;
import com.pollra.web.exception.TodoOutputQueryException;
import com.pollra.web.factory.TodoFactory;
import com.pollra.web.factory.TodoInstanceFactoryImpl;
import com.pollra.web.usecase.TodoResource;
import com.pollra.web.usecase.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Controller
@RequestMapping("/todo")
public class TodoController {

    private HttpServletRequest request;
    private TodoResource todoResource;
    private InspectionTool inspectionTool;

    public TodoController(HttpServletRequest request, TodoResource todoService) {
        this.request = request;
        this.todoResource = todoService;
        this.inspectionTool = new InspectionTool();
    }

    @GetMapping
    public ModelAndView mainPage() {
        ModelAndView view = new ModelAndView("/todo/index");
        view.addObject("list",todoResource.readAll());
        return view;
    }

    @Transactional
    @PostMapping("create")
    public ModelAndView createPage() throws RuntimeException{
        log.info("create Page");
        ModelAndView view = new ModelAndView("redirect:/todo");

        Todo todo = new Todo();
        todo.setUserName(inspectionTool.stringDataTagCheck(request.getParameter("id")));
        todo.setMessage(inspectionTool.stringDataTagCheck(request.getParameter("msg")));
        todo.setPassword(inspectionTool.stringDataTagCheck(request.getParameter("pw")));
        try {
            todoResource.create(todo);
        }catch (TodoException e){
            log.error(e.getMessage());
        }

        view.addObject("list",todoResource.readAll());

        return view;
    }

    @Transactional
    @GetMapping("update/idx/{idx}")
    public ModelAndView update(@PathVariable("idx") String idx){
        log.info("update/idx/"+idx+" Page");
        int index = 0;
        ModelAndView view = new ModelAndView("/todo/update");
        try {
            index = Integer.parseInt(idx);
        }catch (NumberFormatException e){
            log.error("형변환 실패: 넘어온 idx 데이터가 숫자형태로 변환되지 않습니다");
            view.setStatus(HttpStatus.BAD_REQUEST);
            view.setViewName("redirect:/todo");
            return view;
        }
        view.addObject("list", todoResource.readAll());
        Todo item = todoResource.readOne(index);
        log.info("찾은 아이디"+item.toString());
        view.addObject("todoObject", item);
        return view;
    }

    @PostMapping("update")
    public ModelAndView updatePage(){
        log.info("update Page");
        ModelAndView view = new ModelAndView("redirect:/todo");

        Todo todo = new Todo();

        todo.setIdx(inspectionTool.integerDataCheck(request.getParameter("idx")));
        todo.setPassword(inspectionTool.stringDataTagCheck(request.getParameter("pw")));
        todo.setMessage(inspectionTool.stringDataTagCheck(request.getParameter("msg")));
        try {
            todoResource.update(todo);
        } catch (TodoException e){
            log.error("업데이트 실패");
            log.error("--"+e.getMessage());
        }
        view.addObject("list",todoResource.readAll());

        return view;
    }

    @GetMapping("delete/idx/{idx}")
    public ModelAndView delete(@PathVariable("idx") String idx){
        log.info("delete/idx/"+idx+" Page");
        int index = 0;
        ModelAndView view = new ModelAndView("/todo/delete");
        try {
            index = Integer.parseInt(idx);
        }catch (NumberFormatException e){
            log.error("형변환 실패: 넘어온 idx 데이터가 숫자형태로 변환되지 않습니다");
            view.setStatus(HttpStatus.BAD_REQUEST);
            view.setViewName("redirect:/todo");
            return view;
        }
        view.addObject("list", todoResource.readAll());
        view.addObject("todoObject", todoResource.readOne(index));
        return view;
    }

    @PostMapping("delete")
    public ModelAndView deletePage(){
        log.info("delete Page");
        ModelAndView view = new ModelAndView("redirect:/todo");

        Todo todo = new Todo();
        todo.setIdx(inspectionTool.integerDataCheck(request.getParameter("idx")));
        todo.setPassword(inspectionTool.stringDataTagCheck(request.getParameter("pw")));
        try {
            todoResource.delete(todo);
        }catch (TodoOutputQueryException e){
            log.error("쿼리 실패");
            log.error("--"+e.getMessage());
        }catch (TodoException e){
            log.error("삭제 실패");
            log.error("--"+e.getMessage());
        }
        view.addObject("list",todoResource.readAll());
        return view;
    }
}
