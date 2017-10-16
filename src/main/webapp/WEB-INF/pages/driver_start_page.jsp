<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.10.2017
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Driver dashboard</title>
    <style type="text/css">
        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 14px;
            border-collapse: collapse;
            text-align: center;
        }
        th, td:first-child {
            background: #AFCDE7;
            color: white;
            padding: 10px 20px;
        }
        th, td {
            border-style: solid;
            border-width: 0 1px 1px 0;
            border-color: white;
        }
        td {
            background: #D8E6F3;
        }
        th:first-child, td:first-child {
            text-align: left;
        }
    </style>

</head>
<body>
<table>
    <tr>
        <td>Driver Employee ID</td>
        <td>${employeeId}</td>
    </tr>
    <tr>
        <td>Your current сommander</td>
        <td>${commander.name} ${commander.surname}</td>
    </tr>
    <tr>
        <td>Your current truck`s licence plate</td>
        <td>${truckLicencePlate}</td>
    </tr>
    <tr>
        <td>Current order waypoints</td>
<c:forEach items="${currentOrder}" var="waypoint">
        <td>${waypoint.city.name}</td>
</c:forEach>
    </tr>
</table>
</body>
</html>
