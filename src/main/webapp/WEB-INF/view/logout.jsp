<%--
  Created by IntelliJ IDEA.
  User: DacaP
  Date: 28/09/2022
  Time: 6:31 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Logout</title>
</head>
<body>
<h5>Admin <c:out value="${sessionScope.userFirstName}"/><a href="${pageContext.request.contextPath}/logout"> (Logout)</a></h5>
</body>
</html>