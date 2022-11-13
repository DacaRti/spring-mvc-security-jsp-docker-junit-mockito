<%--
  Created by IntelliJ IDEA.
  User: DacaP
  Date: 19/09/2022
  Time: 5:20 pm
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
<jsp:include page="/WEB-INF/view/logout.jsp"/>
<form method="post" action="${saveOrUpdate}">
  <h1>${action}</h1>
  <div style="color: red">
    <c:if test="${not empty error}">
      <c:out value="${error}"/>
    </c:if><br>
  </div>
  <div class="main">
    <div class="field">
      <label>Username</label>
      <input type="text" name="username" required="required" value="${user.username}" ${readability}/>
    </div>
    <div class="field">
      <label>Password</label><input type="text" name="password"/>
    </div>
    <div class="field">
      <label>Password again </label><input type="text" name="passwordAgain"><br>
    </div>
    <div class="field">
      <label>Email</label>
      <input type="email" name="email" required="required" value="${user.email}"/><br>
    </div>
    <div class="field">
      <label>FirstName</label>
      <input type="text" name="firstName" required="required" value="${user.firstName}"/><br>
    </div>
    <div class="field">
      <label>LastName</label>
      <input type="text" name="lastName" required="required" value="${user.lastName}"/><br>
    </div>
    <div class="field">
      <label>BirthDay</label>
      <input type="date" name="birthday" required="required" value="${user.birthday}"/><br>
    </div>
    <div class="field">
      <label> Role </label>
      <select name="role">
        <option style="display:none" value="${user.role.name}">
          ${user.role.name}</option>
        <c:forEach var="role" items="${roles}">
          <option value="${role.name}">${role.name}</option>
        </c:forEach>
        <br/>
      </select>
    </div>
    <div class="field">
      <label>
        <input type="submit" value="Ok" name="Ok" size="5px">
      </label>
      <label>
        <input type="button" onclick="history.back();" value="Cancel"/>
      </label>
    </div>
  </div>
  <label>
    <input type="number" hidden name="id" value="${user.id}"/>
  </label>
  <label>
    <input type="email" hidden name="currentEmail" value="${user.email}"/>
  </label>
  <label>
    <input type="text" hidden name="currentPassword" value="${user.password}"/>
  </label>
  <label>
    <input type="text" hidden name="currentRole" value="${user.role.name}"/>
  </label>
</form>
</body>
</html>