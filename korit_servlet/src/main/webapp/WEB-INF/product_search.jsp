<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2025-01-06
  Time: 오후 4:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="http://localhost:8080/servlet_study_war/product/search" method="get">
        <input type="text" name="searchValue">
        <button>검색</button>
    </form>
<table>
    <tr>
        <th>상품 번호</th>
        <th>상품명</th>
    </tr>
    <c:forEach var="product" varStatus="status" items="${products}">
        <tr>
            <td>${status.index}</td>
            <td>${product.productName}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
