<%--
  Created by IntelliJ IDEA.
  User: LIAOWX
  Date: 2020/4/26
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
pageEncoding="utf-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContent.request.contextPath}/welcome.jsp">
        <c:if test="${empty param.num1}">
            name: <input type="text" name="num1" />
            <input type="submit" value="submit" />
            <br />
        </c:if>
            ${param.num1}welcome!!<br />
        </c:if>
    </form>
</body>
</html>
