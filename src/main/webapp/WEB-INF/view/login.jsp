<%--
  Created by IntelliJ IDEA.
  User: DacaP
  Date: 18/09/2022
  Time: 3:15 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>

<style>
    .outline {
        border: 2px solid #c5c6c6;
        padding: 10px;
        border-radius: 20px;
        width: 200px;
        margin: 0 auto;
        text-align: center;
    }
</style>

<body>
<div style="text-align: center">
    <form action="${pageContext.request.contextPath}/registration" method="get">
        <button type="submit" name="update" value="update" class="btn-link">Create account</button>
    </form>
</div>
<form:form method="post">
<div class="col-lg-6 col-xs-12 text-center">
    <div class="outline">
        <p></p>
        <label>
            <input type="text" required placeholder="username" name="username">
        </label>
        <p>
            <label>
                <input type="password" required placeholder="password" name="password">
            </label>
        <p>
        <div style="padding-left: 115px;">
            <input class="button" type="submit" value="Sign in">
        </div>
    </div>
    </form:form>
    <div style="text-align: center; color: red">
        <c:if test="${not empty error}">
            <c:out value="${error}"/>
        </c:if><br>
    </div>
</div>
</body>
</html>