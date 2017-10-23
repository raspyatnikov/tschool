<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trucks</title>
    <c:import url="../header.jsp"/>
</head>
<body>
<c:import url="../admin_menu.jsp"/>

<div id="main" role="main">
    <div id="content">
        <c:import url="../../message.jsp"/>
        <div class="row">
            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                <a class="btn btn-success btn pull-left margin-bottom-10" href="${pageContext.request.contextPath}/admin/addTruck">
                    Add new truck
                </a></div></div>
        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="row">
                <div class="table-responsive">
                    <table class="table table-bordered bg-color-white" >
                        <thead>
                        <tr>
                            <th>Licence plate</th>
                            <th>Capacity(kg)</th>
                            <th>CurrentCity</th>
                            <th>Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${trucklist}" var="truck">
                            <tr>
                                <td><a href ="/admin/editTruck/${truck.id}">${truck.licencePlate}</a></td>
                                <td>${truck.cargoCapacity}</td>
                                <td>${truck.currentCity.name}</td>
                                <td>${truck.status}</td>
                            </tr>  </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div></article></div></div>
</body>
</html>