<%@ page import="java.util.List" %>
<%@ page import="com.korit.servlet_study.entity.Category" %>
<%@ page import="com.korit.servlet_study.datas.DataList" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2025-01-06
  Time: 오전 9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>${serverName}</title>
</head>
<body>
  <h1>상품등록</h1>
  <form action="http://localhost:8080/servlet_study_war/product/register" method="post">
    <table>
      <tr>
        <td>카테고리</td>
        <td>
          <select name="category">
            <c:forEach var="category" items="${categories}"> <%--request와 연결?--%>
              <option value="${category.id}">${category.name}</option>
            </c:forEach>

          </select>
        </td>
      </tr>
      <tr>
        <td>상품명</td>
        <td><input type="text" name="productName"></td>
      </tr>
      <tr>
        <td>가격</td>
        <td><input type="text" name="price"></td>
      </tr>
      <tr>
        <td>등록일자</td>
        <td><input type="date" name="registerDate"></td>
      </tr>
    </table>
    <button type="submit">저장</button>
  </form>

</body>
</html>