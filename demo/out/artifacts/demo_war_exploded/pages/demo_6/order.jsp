<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"  errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <%
        int[] array = {3,21};
    %>
    数组指定元素为：<%=(array[99]) %><!--此处会产生异常  -->
</body>
</html>