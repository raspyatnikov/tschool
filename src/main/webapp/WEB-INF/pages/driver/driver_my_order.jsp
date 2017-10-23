<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My order</title>
    <c:import url="../admin/header.jsp"/>
</head>
<body>
<c:import url="driver_menu.jsp"/>
<div id="main" role="main">
    <div id="content">
        <c:import url="../message.jsp"/>

        <c:if test="${hasActiveOrder != 1}">
            <div class="alert alert-warning fade in">
                <strong>${message}</strong>
            </div></c:if>

        <c:if test="${hasActiveOrder == 1}">
        <div class="well well-lg">
<dl class="dl-horizontal">
    <dt>Order No.</dt>
    <dd>${orderNumber}<br><br></dd>
    <dt>Your commander</dt>
    <dd>${commander}<br><br></dd>
    <dt>Truck`s licence plate</dt>
    <dd>${truck}<br><br></dd>
    <dt>Route waypoints</dt>
    <dd><c:forEach items="${waypoints}" var="waypoint">
       <a href="/driver/waypoint/${waypoint.key}/${orderId}">${waypoint.value}</a> <br>
    </c:forEach>

        </dd>
</dl>
        </div></c:if>
            </div></div>

</body>
</html>
