package com.pollra.web.factory;

import com.pollra.web.entity.Todo;

import javax.servlet.http.HttpServletRequest;

public class TodoInstanceFactoryImpl {

    private HttpServletRequest request;

    public TodoInstanceFactoryImpl(HttpServletRequest request) {
        this.request = request;
    }

    public static Todo getTodo(){
        return null;
    }

}
