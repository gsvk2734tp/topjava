<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Моя еда</h2>
<br>
<table border=1>
    <thead>
    <tr>
        <th>Дата/Время:</th>
        <th>Описание:</th>
        <th>Калории:</th>
        <th colspan="2">Действия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.meals}" var="meal">
        <tr style="color: ${meal.isExceed() ? 'red': 'green'}">
            <td>${meal.getDateTime()}</td>
            <td>${meal.getDescription()}</td>
            <td>${meal.getCalories()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
