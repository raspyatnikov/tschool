<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="../header.jsp"/>
</head>
<body>
<c:import url="../admin_menu.jsp"/>

<div id="main" role="main">
    <div id="content">
        <c:if test="${message != null}">
            <div class="alert alert-success fade in">
                <strong>${message}</strong>
            </div></c:if>
        <div class="row">
        <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
        <a class="btn btn-success btn pull-left margin-bottom-10" href="${pageContext.request.contextPath}/admin/addDriver">
 Add new driver
        </a></div></div>
        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="row">
<div class="table-responsive">
    <table class="table table-bordered bg-color-white" >
        <thead>
        <tr>
            <th>Employee ID</th>
            <th>E-MAIL</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Location</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${driverList}" var="driver">
            <tr>
                <td><a href ="/admin/editDriver/${driver.id}">${driver.employeeId}</a></td>
                <td>${driver.userAccount.mail}</td>
                <td>${driver.name}</td>
                <td>${driver.surname}</td>
                <td>${driver.currentCity.name}</td>
            </tr>  </c:forEach>
        </tbody>
    </table>
</div>
        </div></article></div></div>
</body>
</html>
