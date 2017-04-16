<%@ page import="ru.javawebinar.topjava.util.MealsUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.model.MealWithExceed" %>
<%@ page import="java.time.LocalTime" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 16.04.2017
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals list</title>
</head>
<body>
<h1>Meals list</h1>
<%--<c:set var="meals" value="${mealWithExceeds}"/>--%>
<c:forEach var="mealWithExceed" items="${mealWithExceeds}">
    <c:out value="${mealWithExceed}"></c:out><br>
</c:forEach>
</body>
</html>
