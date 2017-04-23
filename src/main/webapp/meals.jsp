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
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>
<head>
    <title>Meals list</title>
</head>
<body>
<h1>Meals list</h1>

<table>
    <tr bgcolor="#556b2f">
        <th style="font-style: italic;color: aquamarine">Date</th>
        <th style="font-style: italic;color: aquamarine">Description</th>
        <th style="font-style: italic;color: aquamarine">Calories</th>
        <th colspan="2">Action</th>
    </tr>

<c:forEach var="mealWithExceed" items="${mealWithExceeds}">
    <c:choose>
    <c:when test="${mealWithExceed.exceed == true}">
            <tr bgcolor="#cd5c5c">
        </c:when>
    <c:when test="${mealWithExceed.exceed == false}">
            <tr bgcolor="#90ee90">
    </c:when>
    </c:choose>


    <td width="150"><javatime:format value="${mealWithExceed.dateTime}" pattern="dd.MM.yyyy
		HH:mm"/></td>
            <td width="150">${mealWithExceed.description}</td>
            <td width="150" style="font-family: Bender">${mealWithExceed.calories}</td>
            <td>
                <a href="${pageContext.request.contextPath}/meals?action=edit&id=${mealWithExceed.id}">Edit</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/meals?action=delete&id=${mealWithExceed.id}">Delete</a>
            </td>
            </tr>
</c:forEach>
    <form action="${pageContext.request.contextPath}/meals" method="post">
        <table>
            <tr>
                <td>
                    <c:if test="${editmeal.id != 0}">
                        <input readonly="readonly" type="text" name="id" value="${editmeal.id}" title="Id">
                    </c:if>
                </td>
                <td>
                    <input type="datetime-local" pattern="dd.MM.yyyy HH:mm" name="dateTime"
                           value="<c:out value="${editmeal.id != 0 ? editmeal.dateTime : ''}"/>" title="Data and Time"/>
                </td>
                <td>
                    <input type="text" name="description" title="Description" value="<c:out
				        value="${editmeal.id != 0 ? editmeal.description : ''}" />"/>
                </td>
                <td>
                    <input type="text" name="calories" title="Calories" value="<c:out
				value="${editmeal.id != 0 ? editmeal.calories : ''}"/>"/>
                </td>
                <td>
                    <input type="submit" name="Ok"/>
                </td>
            </tr>
        </table>

    </form>
</table>




</body>



</html>
