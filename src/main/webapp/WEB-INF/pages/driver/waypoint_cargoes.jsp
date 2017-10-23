<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Route point</title>
    <c:import url="../admin/header.jsp"/>
</head>
<body>
<c:import url="driver_menu.jsp"/>
<div id="main" role="main">
    <div id="content">
        <div class="well well-lg">
            ${city}<br>
            <p>Cargoes to load</p>

                <div class="table-responsive">
                    <table class="table table-bordered bg-color-white" >
                        <thead>
                        <tr>
                            <th>Title</th>
                            <th>Weight</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${loadCargoes}" var="loadCargo">
                            <tr>
                                <td>${loadCargo.title}</td>
                                <td>${loadCargo.weight}</td>
                                <td>${loadCargo.status}</td>
                                <td><a href="/driver/waypoint/setCargoLoaded/${loadCargo.cargoId}/${order_id}" class="hyperlink">Complete</a></td>
                            </tr>  </c:forEach>
                        </tbody>
                    </table>
                </div>

                <p>Cargoes to unload</p>

                <div class="table-responsive">
                    <table class="table table-bordered bg-color-white" >
                        <thead>
                        <tr>
                            <th>Title</th>
                            <th>Weight</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${unloadCargoes}" var="unloadCargo">
                            <tr>
                                <td>${unloadCargo.title}</td>
                                <td>${unloadCargo.weight}</td>
                                <td>${unloadCargo.status}</td>
                                <td><a href="/driver/waypoint/setCargoDelivered/${unloadCargo.cargoId}/${order_id}" class="hyperlink">Complete</a></td>
                            </tr>  </c:forEach>
                        </tbody>
                    </table>
                </div>
        </div></div></div>
</body>
</html>
