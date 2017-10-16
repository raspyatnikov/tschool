<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shift Journal</title>
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
        <td>Shift began</td>
        <td>Shift finished</td>
    </tr>
       <tr>
        <c:forEach items="${shift_records_begin}" var="begin">
            <td>${begin}</td>
        </c:forEach>
            <c:forEach items="${shift_record_end}" var="end">
            <td>${end}</td>
       </tr>
    </c:forEach>

</table>


<form method="POST" action="/addShift" >

                <label for = "begin">Shift beginned</label>
                    <input type="text" name="shiftBeggined" id = "begin"/>
               <label for = "end"> Shift ended  </label>
                <input type="text" name="shiftEnded" id = "end"/>
         <input type="submit" value="Add shift"/>

</form>

<%--<form:form method="POST" action="/addShift" modelAttribute="shift_record">--%>
    <%--<table>--%>
        <%--<tr>--%>
            <%--<td><form:label path="">Start</form:label></td>--%>
            <%--<td><form:input path="shiftBeggined"/></td>--%>
        <%--</tr>--%>

        <%--<tr>--%>
            <%--<td><form:label path="">End</form:label></td>--%>
            <%--<td><form:input path="shiftEnded"/></td>--%>
        <%--</tr>--%>

        <%--<tr>--%>
            <%--<td><input type="submit" value="Add driver"/></td>--%>
        <%--</tr>--%>
    <%--</table>--%>
<%--</form:form>--%>
</body>
</html>
