<%@ page import="com.pollra.web.entity.Todo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Todo JSP</title>
</head>
<body>
    <div id="form">
        <label class="userList">
            <li class="idx">index</li>
            <li class="msg">todo</li>
            <li class="name">user</li>
            <li class="date">date</li>
        </label>
        <div id="listContainer">
            <c:forEach var="item" items="${list}">
                <label class="userList" for="popItem${item.idx}" >
<%--                    <input type="checkbox" id="popItem${item.idx}" class="hidden" checked required>--%>
                    <li class="idx">${item.idx}</li>
                    <li class="msg">${item.message}</li>
                    <button class="update btn" onclick="location.replace('/todo/update/idx/${item.idx}')">수정</button>
                    <button class="delete btn" onclick="location.replace('/todo/delete/idx/${item.idx}')">삭제</button>
                    <li class="name">${item.userName}</li>
                    <li class="date">${item.datetime}</li>
                </label>
            </c:forEach>
        </div>
        <form id="titleContainer" action="/todo/delete" method="post">
            <input name="idx" value="${todoObject.idx}" type="hidden">
            <div>
                <label class="msg">${todoObject.message}</label>
            </div>
            <div>
                <label class="name">${todoObject.userName}</label>
            </div>
            <div>
                <input type="password" placeholder="비밀번호를 입력" name="pw" class="pw" autocomplete="off"/>
            </div>
            <div>
                <button type="submit" id="submitBtn">삭제</button>
            </div>
        </form>
    </div>

</body>
</html>
<style>
    body{
        margin:0px;
        padding: 0px;
        background-image: linear-gradient(to right, #4facfe 0%, #00f2fe 100%);
        width: 100%;
        height: 100%;
        display: inline-block;
    }
    #form{
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 900px;
        height: 600px;
        border: 1px solid azure;
        box-shadow: 0 0 8px -3px black;
        border-radius: 5px;
        padding: 30px 50px 70px;
        background: white;
    }
    label{
        list-style: none;
        width: 100%;
        float: left;
        margin: 10px 11px;
        padding: 0px;
        flex-direction: row;
        display: flex;
        flex-grow: 1;
    }
    li{
        float: left;
        width: 25%;
    }
    #listContainer{
        overflow: scroll;
        height: 550px;
    }
    #titleContainer{
        width: 100%;
        padding: 15px 0px;
        background: #4a7789;
        display: flex;
        border-radius: 3px;
        position: relative;
    }
    #titleContainer > div {
        display: flex;
        margin: 0px 10px;
        justify-content: space-around;
        color: #eaf8ff;
    }
    #titleContainer > div > input::placeholder{
        color: cadetblue;
    }
    #titleContainer > div > input{
        border: 0px;
        border-bottom: 1px solid cadetblue;
        background: linear-gradient(to bottom, #4a7789 90%, white 100%);
        color: white;
        outline: none;
        font-size: 14px;
        padding: 5px 3px;
    }
    #titleContainer > div > .msg{
        width: 500px;
        min-height: 20px;
        word-break: break-word;
        max-height: 100px;
    }
    #titleContainer > div > .name{
        width: 70px;
    }
    #titleContainer > div > .pw{
        width: 100px;
    }
    #titleContainer > div >
    #submitBtn{
        padding: 5px 25px;
        border: none;
        cursor: pointer;
        background: white;
        right: 30px;
        border-radius: 3px;
        position: absolute;
    }
    .userList {
        float: inherit;
    }
    .userList > .idx{
        width: 50px;
    }
    .userList > .msg{
        width: 650px;
        min-height: 20px;
        word-break: break-word;
        max-height: 100px;
    }
    .userList > .name{
        width: 100px;
    }
    .userList > .date{
        width: 100px;
        position: relative;
    }
    .btn{
        width: 80px;
        min-height: 20px;
        max-height: 60px;
        border-radius: 3px;
        font-weight: 700;
        border: 0px;
        position: absolute;
        display: none;
    }
    .update.btn{
        background: #3cd8e8;
        color: white;
        right: 325px;
    }
    .delete.btn{
        background: #b4251c;
        color: white;
        right: 240px;
    }
    .userList:hover > .btn{
        display: block;
    }

</style>