<%--
  Created by IntelliJ IDEA.
  User: DacaP
  Date: 18/09/2022
  Time: 3:16 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<div style="text-align: center; font: -apple-system-body;">
    <h1>Hello, <c:out value="${sessionScope.userFirstName}"/>!</h1>
    <h3>Click <a href="${pageContext.request.contextPath}/logout">here</a> to logout</h3>
</div>
</body>
</html>