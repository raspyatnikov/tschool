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
        <c:import url="../../message.jsp"/>
        <section id="widget-grid" class="">
            <div class="row">
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-sortable">
                        <form id="order-form" class="smart-form bg-color-white bordered" novalidate="novalidate" action="${pageContext.request.contextPath}/admin/updateTruck/${truck.id}" method="POST">
                            <header>
                                Update truck info
                            </header>

                            <fieldset>
                                <section>
                                    <label class="label">Licence plate</label>
                                    <label class="input">
                                        <input type="text" name="licencePlate" placeholder="Licence plate" value="${truck.licencePlate}">
                                    </label>
                                </section>
                                <section><label class="label">Cargo capacity(kg)</label>
                                    <label class="input">
                                        <input type="text" name="cargoCapacity" placeholder="Cargo capacity (kg)" value="${truck.cargoCapacity}">
                                    </label>
                                </section>
                                <section><label class="label">Current city</label>
                                    <label class="select">
                                        <select name="currentCity">
                                            <option value="${city.name}">${city.name}</option>
                                            <c:forEach items="${cityList}" var="city">
                                                <option  value=${city.name} >${city.name}</option>
                                            </c:forEach>                        </select> <i></i> </label>
                                </section>
                                <section><label class="label">Current status</label>
                                    <label class="select">
                                        <select name="status">
                                            <option value="${status}">${status}</option>
                                            <c:forEach items="${statuses}" var="status">
                                                <option >${status}</option>
                                            </c:forEach>                        </select> <i></i> </label>
                                </section>

                            </fieldset>
                            <footer>
                                <button type="submit" class="btn btn-primary">
                                    Update truck info
                                </button>
                                <a href="/admin/removeTruck/${truck.id}"><button type="button" class="btn btn-primary">Delete truck</button></a>
                            </footer>
                        </form>
                    </div></article></div></section></div></div>
</body>
</html>
