<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/topjava.common.js" defer></script>
<script type="text/javascript" src="resources/js/topjava.meals.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<%--<hr>--%>
<%--<a href="meals/create"><spring:message code="meal.add"/></a>--%>
<%--<hr>--%>
<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="meal.title"/></h3>

        <div class="card border-dark">
            <div class="card-body pb-0">
                <form method="post" action="meals/filter">
                    <div class="row">
                        <div class="offset-1 col-2">
                            <label for="startDate"><spring:message code="meal.startDate"/>:</label>
                            <input class="form-control" name="startDate" value="${param.startDate}" id="startDate">
                        </div>
                        <div class="col-2">
                            <label for="endDate"><spring:message code="meal.endDate"/>:</label>
                            <input class="form-control" name="endDate" value="${param.endDate}" id="endDate">
                        </div>
                        <div class="offset-2 col-2">
                            <label for="startTime"><spring:message code="meal.startTime"/>:</label>
                            <input class="form-control" name="startTime" value="${param.startTime}" id="startTime">
                        </div>
                        <div class="col-2">
                            <label for="endTime"><spring:message code="meal.endTime"/>:</label>
                            <input class="form-control" name="endTime" value="${param.endTime}" id="endTime">
                        </div>
                    </div>
                    <br>
                    <div class="card-footer text-right">
                        <button class="btn btn-primary" type="submit">
                            <span class="fa fa-filter"></span> <spring:message code="meal.filter"/>
                        </button>
                    </div>
                </form>
            </div>
        </div>


        <br>
        <button class="btn btn-primary" onclick="add">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>

        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="meal.dateTime"/></th>
                <th><spring:message code="meal.description"/></th>
                <th><spring:message code="meal.calories"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${meals}" var="meal">
                <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealTo"/>
                <tr data-mealExcess="${meal.excess}">
                    <td>
                            <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                            <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                            <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                            ${fn:formatDateTime(meal.dateTime)}
                    </td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td><a href="meals/update?id=${meal.id}"><span class="fa fa-pencil"></span></a></td>
                    <td><a href="meals/delete?id=${meal.id}"><span class="fa fa-remove"></span></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>