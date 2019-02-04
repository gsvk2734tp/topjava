<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Home
  Date: 04.02.2019
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Еда</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>${meal.getId() == null ? 'Добавление еды': 'Редактирование еды'}</h2>

<form method="post" action="meals" name="addOrUpdate">
    Id еды: <input type="number" readonly="readonly" name="mealId"
                   value="<c:out value="${meal.getId()}"/>">
    <br>
    Дата: <input required type="date" name="date"
                 value="<c:out value="${meal.getDate()}"/>">
    <br>
    Время: <input required type="time" name="time"
                  value="<c:out value="${meal.getTime()}"/>">
    <br>
    Описание: <input required type="text" name="description" value="<c:out value="${meal.getDescription()}"/>">
    <br>
    Калории: <input required type="number" name="calories" value="<c:out value="${meal.getCalories()}"/>">
    <br>
    <br>
    <input type="submit" value="${meal.getId() == null ? 'Добавить': 'Сохранить'}">

</form>

</body>
</html>
