<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new driver</title>
    <c:import url="../header.jsp"/>

</head>
<body>

<c:import url="../admin_menu.jsp"/>

<div id="main" role="main">
    <div id="content">
      <c:import url="../../message.jsp"/>
        <section id="widget-grid" class="">

            <div class="row">

                <article class="col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">

                    <div class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" data-widget-custombutton="false" role="widget">

                        <header>
                            <span class="jarviswidget-loader"><i class="fa fa-refresh fa-spin"></i></span></header>
                        <div>
                            <div class="jarviswidget-editbox">

                            </div>

                            <div class="widget-body no-padding">

                                <form id="order-form" class="smart-form" novalidate="novalidate" action="${pageContext.request.contextPath}/admin/addTruck" method="POST">
                                    <header>
                                        New truck
                                    </header>

                                    <fieldset>
                                        <section>
                                            <label class="input">
                                                <input type="text" name="licencePlate" value="${licencePlate}" placeholder="Truck`s licence plate">
                                            </label>
                                        </section>
                                        <section>
                                            <label class="input">
                                                <input type="text" name="cargoCapacity" value="${cargoCapacity}" placeholder="Cargo capacity(kg)">
                                            </label>
                                        </section>

                                        <section>
                                            <label class="select">
                                                <select name="status">
                                                    <option value="">Select status</option>
                                                    <c:forEach items="${statuses}" var="status">
                                                        <option>${status}</option>
                                                    </c:forEach>                        </select>
                                            </label>
                                        </section>
                                        <section>
                                            <label class="select">
                                                <select name="currentCity">
                                                    <option value="">Select city</option>
                                                    <c:forEach items="${cityList}" var="city">
                                                        <option  value=${city.name} >${city.name}</option>
                                                    </c:forEach>                        </select></label>
                                        </section>


                                    </fieldset>
                                    <footer>
                                        <button type="submit" class="btn btn-primary">
                                            Add new truck
                                        </button>
                                    </footer>
                                </form>

                            </div>

                        </div>

                    </div>

                </article>

            </div>

        </section>

    </div></div>

<script src="${pageContext.request.contextPath}/resources/js/libs/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/validation.js"></script>
</body>
</html>

