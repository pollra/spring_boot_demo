package com.pollra.web.usecase;

import com.pollra.web.entity.Todo;

import java.util.Collection;

public interface TodoResource{
    public Collection<Todo> readAll();

    public Todo readOne(int idx);

    int create(Todo t);

    int update(Todo t);

    int delete(Todo t);
}
