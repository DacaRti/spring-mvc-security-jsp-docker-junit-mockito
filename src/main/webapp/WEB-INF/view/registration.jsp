<%--
  Created by IntelliJ IDEA.
  User: DacaP
  Date: 03/10/2022
  Time: 3:23 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Update</title>
    <style>
        .field, select {
            clear: both;
            text-align: right;
            line-height: 25px;
        }

        label {
            float: left;
            padding-right: 10px;
        }

        .main {
            float: left
        }

    </style>
</head>
<body>
<form method="post" action="/registration/add">
    <h1>Registration</h1>
    <div style="color: red">
        <c:if test="${not empty error}">
            <c:out value="${error}"/>
        </c:if><br>
    </div>
    <div class="main">
        <div class="field">
            <label>Username</label>
            <input type="text" name="username" required="required"/>
        </div>
        <div class="field">
            <label>Password</label><input type="text" name="password"/>
        </div>
        <div class="field">
            <label>Password again </label><input type="text" name="passwordAgain"><br>
        </div>
        <div class="field">
            <label>Email</label>
            <input type="email" name="email" required="required"/><br>
        </div>
        <div class="field">
            <label>FirstName</label>
            <input type="text" name="firstName" required="required"/><br>
        </div>
        <div class="field">
            <label>LastName</label>
            <input type="text" name="lastName" required="required"/><br>
        </div>
        <div class="field">
            <label>BirthDay</label>
            <input type="date" name="birthday" required="required"/><br>
        </div>
        <br>
        <label><img src="${pageContext.request.contextPath}/captcha" alt=""></label>
        <input type="text" name="captcha" required="required" style="margin-top: 5px;">
        <br><br><br>
        <div class="field">
            <label>
                <input type="submit" value="Ok" name="Ok" size="5px">
            </label>
            <label>
                <input type="button" onclick="history.back();" value="Cancel"/>
            </label>
        </div>
    </div>
</form>
</body>
</html>