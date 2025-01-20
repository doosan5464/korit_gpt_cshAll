<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2025-01-10
  Time: 오후 5:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="http://localhost:8080/servlet_study_war/book1" method="post">
        <input type="text" name="bookName" placeholder="도서명">
        <input type="text" name="isbn" placeholder="isbn">
        <input type="text" name="author" placeholder="저자명">
        <input type="text" name="publisher" placeholder="출판사명">
        <input type="text" name="category" placeholder="카테고리">
        <input type="text" name="imgUrl" placeholder="표지URL">
        <button type="submit">추가</button>
    </form>
</body>
</html>
