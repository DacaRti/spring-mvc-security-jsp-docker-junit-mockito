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
    <title>Admin</title>
    <style>
        table {
            width: 300px;
            border: 3px solid #000121;
            margin: auto;
        }

        thead {
            background-color: #5b5458;
            color: white;
            text-shadow: 1px 1px 1px black;
        }

        tbody {
            font: -apple-system-body;
            background-color: #fdfcfc;
            color: black;
        }

        caption {
            font: -apple-system-body;
            padding-bottom: 10px;
        }

        td, th {
            text-align: left;
            padding: 10px;
        }

        h5 {
            text-align: right;
        }

        .btn-link {
            border: none;
            outline: none;
            background: none;
            cursor: pointer;
            color: #0000EE;
            padding: 0;
            text-decoration: underline;
            font-family: inherit;
            font-size: inherit;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/view/logout.jsp"/>
<table>
    <div style="color: red; text-align: center">
        <c:if test="${not empty error}">
            <c:out value="${error}"/>
        </c:if><br>
    </div>
    <caption>
        <form action="${pageContext.request.contextPath}/admin/add" method="get">
            <button type="submit" name="update" value="update" class="btn-link"> Add new user</button>
        </form>
    </caption>
    <br><br>
    <thead>
    <tr>
        <th>Username</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Age</th>
        <th>Role</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td><c:out value="${user.username}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.getAge()}"/></td>
            <td><c:out value="${user.role.name}"/></td>
            <td class="form">
                <form action="${pageContext.request.contextPath}/admin/delete" method="post" class="form2"
                      onclick="return confirm('Are you sure?')">
                    <label>
                        <input type="text" hidden name="id" value="<c:out value="${user.id}"/>"/>
                    </label>
                    <button type="submit" name="delete" value="delete" class="btn-link">Delete</button>
                </form>
                <script>
                    const el_up = document.getElementById("GFG_UP");
                    el_up.innerHTML = "Click on the LINK for further confirmation.";
                </script>
                <form action="${pageContext.request.contextPath}/admin/addForUpdate" method="get">
                    <input type="text" hidden name="id" value="<c:out value="${user.id}"/>"/>
                    <button type="submit" name="update" value="update" class="btn-link">Update</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>