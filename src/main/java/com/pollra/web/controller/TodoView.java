package com.pollra.web.controller;

import lombok.Getter;

import java.sql.Date;

@Getter
public class TodoView {
    private int idx;
    private String userName;
    private String message;
    private Date datetime;
}
