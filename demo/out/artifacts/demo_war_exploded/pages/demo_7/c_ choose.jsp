<%--
  Created by IntelliJ IDEA.
  User: LIAOWX
  Date: 2020/4/26
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
pageEncoding="utf-8" import="java.util.*"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:choose>
        <c:when test="${empty param.username}">
            unKnown user
        </c:when>
        <c:when test="${param.username == 'itcast'}">
            ${param.username} is manager
        </c:when>
        <c:otherwise>
            you are a manager
        </c:otherwise>
    </c:choose>
</body>
</html>
