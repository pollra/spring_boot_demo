package com.pollra.web.entity;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Data
@Getter
@ToString
@Table(value = "tbl_todos")
public class Todo {

    private int idx;

    private String userName;

    private String password;

    private String message;

    private Date datetime;
}
