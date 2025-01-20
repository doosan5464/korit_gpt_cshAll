<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2025-01-03
  Time: 오후 5:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--위에 코드 덕에 suvlet으로 한번 변환을 해준다?-->
<html>
<head>
    <title>Title</title>
</head>
<body>
  <%
    String name = request.getParameter("name"); // 요청후 입력한 값을 받음. 동적 웹페이지
    int age = Integer.parseInt(request.getParameter("age")); // getParameter는 String으로 가져옴
    // http://localhost:8080/servlet_study_war/hello?name=%EA%B9%8034343%EC%A4%80%EC%9D%BC&age=44
  %> <!--(주석) 스클립틀릿-->
  <h1>Hello Servlet </h1>
  <h2>이름: <%=name %></h2>
  <h2>나이: <%=age %></h2>
</body>
</html>
<!--http://localhost:8080/servlet_study_war/hello.jsp-->
