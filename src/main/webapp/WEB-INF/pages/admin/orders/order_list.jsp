<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="../header.jsp"/>
</head>
<body class = "desktop-detected">
<c:import url="../admin_menu.jsp"/>
<div id="main" role="main">
    <div id="content">
        <div class="row">
            <div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
                <h1 class="page-title">
                <a class="btn btn-success btn pull-left margin-bottom-10" href="${pageContext.request.contextPath}/admin/newOrder">
                    Create new
                </a></h1></div></div>
        <div class="row">
        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

                <div class="table-responsive">
                    <table class="table table-bordered bg-color-white">
                        <thead>
                        <tr>
                            <th>Order No.</th>
                            <th>Route</th>
                            <th>Truck</th>
                            <th>Drivers</th>
                            <th>Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${orderList}" var="order">
                            <tr>
                                <td>${order.orderNumber}</td>
                                <td>${order.route}</td>
                                <td>${order.truckLicencePlate}</td>
                                <td>${order.drivers}</td>
                                <td>${order.status}</td>
                            </tr>  </c:forEach>
                        </tbody>
                    </table>
                </div>
        </article></div></div></div>
</body>
</html>
