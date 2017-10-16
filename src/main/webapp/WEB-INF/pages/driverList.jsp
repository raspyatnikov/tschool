<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of drivers</title>
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/bootstrap.min.css"/> "/>
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/font-awesome.min.css"/> "/>
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/smartadmin-production-plugins.min.css"/> "/>
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/smartadmin-production.min.css"/> "/>
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/smartadmin-skins.min.css"/> "/>
</head>
<body>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

    <!-- Widget ID (each widget will need unique ID)-->
    <div class="jarviswidget jarviswidget-color-blueDark" id="wid-id-0" data-widget-editbutton="false">
<div class="table-responsive">
<table class="table table-bordered">
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
            <td>${driver.employeeId}</td>
            <td>${driver.userAccount.mail}</td>
            <td>${driver.name}</td>
            <td>${driver.surname}</td>
            <td>${driver.currentCity.name}</td>
        </tr>  </c:forEach>
    </tbody>
</table>
</div>

    </div></article></body>
</body>
</html>
