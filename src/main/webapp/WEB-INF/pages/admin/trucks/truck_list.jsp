<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of trucks</title>

</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Capacity - kg</th>
        <th>CurrentCity</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${trucklist}" var="truck">
        <tr>
            <td>${truck.id}</td>
            <td>${truck.cargoCapacity}</td>
            <td>${truck.currentCity.name}</td>
            <td>
                <a href="<c:url value='/deleteUser/${truck.id}'/>">Delete</a>
            </td>
        </tr>  </c:forEach>
    </tbody>
</table>

<h2>Add new truck</h2>

<form:form method="POST" action="/addTruck" modelAttribute="truck">
    <table>
        <tr>
            <td><form:label path="licencePlate">Licence no.</form:label></td>
            <td><form:input path="licencePlate"/></td>
        </tr>
        <tr>
            <td><form:label path="cargoCapacity">Cargo capacity (kg)</form:label></td>
            <td><form:input path="cargoCapacity"/></td>
        </tr>

        <tr>
            <td><form:label path="currentCity">Location city</form:label></td>
            <td>
            <form:select path="currentCity">
                    <%--<form:option value="NONE" label="Select" />--%>
            <form:options items="${cityList}" itemValue="id" itemLabel="name"/>
                </form:select>
        </td>
        </tr>
        <tr>
            <td><form:label path="status">Location city</form:label></td>
            <td>
                <form:select path="status">
                    <%--<form:option value="NONE" label="Select" />--%>
                    <form:options items="${statuses}" itemValue="name" itemLabel="name"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Add truck"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
