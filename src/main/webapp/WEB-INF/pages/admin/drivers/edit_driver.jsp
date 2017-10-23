<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="../header.jsp"/>
</head>
<body class="desktop-detected pace-done mobile-view-activated">

<c:import url="../admin_menu.jsp"/>

<div id="main" role="main">
    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-sortable">
                        <form id="order-form" class="smart-form bg-color-white bordered" novalidate="novalidate" action="${pageContext.request.contextPath}/admin/updateDriver/${id}" method="POST" onsubmit="">
                            <header>
                                Update driver info
                            </header>

                            <fieldset>
                                    <section>
                                        <label class="label">Name</label>
                                        <label class="input">
                                            <input type="text" name="name" placeholder="Name" value="${driver.name}">
                                        </label>
                                    </section>
                                    <section><label class="label">Surname</label>
                                        <label class="input">
                                            <input type="text" name="surname" placeholder="Surname" value="${driver.surname}">
                                        </label>
                                    </section>
                                    <section><label class="label">Employee ID</label>
                                        <label class="input">
                                            <input type="text" name="employeeId" placeholder="Employee ID" value="${driver.employeeId}" readonly>
                                        </label>
                                    </section>
                                    <section><label class="label">Current city</label>
                                        <label class="select">
                                            <select name="currentCity">
                                                <c:forEach items="${cityList}" var="city">
                                                    <option  value=${city.id} >${city.name}</option>
                                                </c:forEach>                        </select> <i></i> </label>
                                    </section>

                            </fieldset>
                            <footer>
                                <button type="submit" class="btn btn-primary">
                                   Update driver info
                                </button>
                                <a href="/admin/removeDriver/${driver.employeeId}"><button type="button" class="btn btn-primary">Delete driver</button></a>
                            </footer>
                        </form>
                    </div></article></div></section></div></div>
</body>
</html>
