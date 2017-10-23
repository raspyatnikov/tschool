<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="../header.jsp"/>
    <style>
        #map {
            width: 100%; height: 20%;
        }

    </style>

</head>
<body class = "desktop-detected pace-done">



<c:import url="../admin_menu.jsp"/>

<div id="main" role="main">
    <div id="content">

        <section id="widget-grid" class="">


            <!-- START ROW -->

            <div class="row">

                <!-- NEW COL START -->
                <article class="col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">


                    <div class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" data-widget-custombutton="false" role="widget">

                        <header>
                            <%--<h2>Review form </h2>--%>

                            <span class="jarviswidget-loader"><i class="fa fa-refresh fa-spin"></i></span></header>

                        <!-- widget div-->
                        <div>

                            <!-- widget edit box -->
                            <div class="jarviswidget-editbox">
                                <!-- This area used as dropdown edit box -->

                            </div>
                            <!-- end widget edit box -->

                            <!-- widget content -->
                            <div class="widget-body no-padding">

                                <form id="order-form" class="smart-form" novalidate="novalidate" name = "form_main">
                                    <header>
                                        <b>New order</b>
                                    </header>

                                    <fieldset>
                                        <section>
                                            <label class="input">
                                                <input type="text" name="order_number" placeholder="Order number" >
                                            </label>
                                        </section>
                                        <div class="row">

                                            <div class="cities">
                                            <section class="col col-4"> <label class="select">
                                                <select name="waypoint_city" id = "start_waypoint">
                                                    <option value="">Origin city</option>
                                                    <c:forEach items="${cityList}" var="city">
                                                        <option  value=${city.name}>${city.name}</option>
                                                    </c:forEach>                        </select></label></section></div>
                                            <div class="cities"><section class="col col-4"> <label class="select">
                                                <select name="waypoint_city">
                                                    <option value="">Destination city</option>
                                                    <c:forEach items="${cityList}" var="city">
                                                        <option  value=${city.name} >${city.name}</option>
                                                    </c:forEach>                        </select></label></section></div>

                                        </div>
                                    <section>
                                        <label class="label">Route length (km)</label>
                                        <label class="input">
                                            <input type="text" id="route_length" readonly>
                                        </label>
                                    </section>
                                        <section>
                                            <div id="map"></div>
                                        </section>
                                    </fieldset>
                                    <header> Cargoes</header>
                                    <fieldset class = "cargoes_list">
                                        <div class="row">
                                            <section class="col col-2">
                                                <button type="button" name = "addCargo" class="btn btn-default btn-block btn-sm bg-color-blueLight">Add cargoe</button>
                                            </section>
                                        </div>
                                        <div class="row cargo-row"><div class = "cargoes">
                                        <section class="col col-3">
                                            <label class="input">
                                                <input type="text" name="title" placeholder="Cargo title">
                                            </label>
                                        </section>

                                            <section class="col col-2">
                                                <label class="input">
                                                    <input type="text" name="weight" placeholder="Weight (kg)">
                                                </label>
                                            </section>


                                        <section class="col col-3"> <label class="select">
                                            <select name="order_origin" required>
                                                <option value="">Origin city</option>
                                                <c:forEach items="${cityList}" var="city">
                                                    <option  value=${city.name}>${city.name}</option>
                                                </c:forEach>                        </select></label></section>

                                        <section class="col col-3"> <label class="select">
                                            <select name="order_destination" required>
                                                <option value="">Destination city</option>
                                                <c:forEach items="${cityList}" var="city">
                                                    <option  value=${city.name}>${city.name}</option>
                                                </c:forEach>                        </select></label></section></div>
                                        </div>
                                    </fieldset>

                                    <header> Truck and drivers</header>

                                    <fieldset>
                                        <div class = "row">

                                            <section class="col col-2">
                                                <button type="button" name="getTruckList" class="btn btn-default btn-block btn-sm bg-color-blueLight">Get truck list</button>
                                            </section>

                                        <section class="col col-10"> <label class="select">
                                            <select name="truck">
                                                <option value="">Select truck</option>
                                                                      </select></label></section>

                                        </div>
                                        <div class="row">

                                            <section class="col col-2">
                                                <button type="button" class="btn btn-default btn-block btn-sm bg-color-blueLight" name="getDriversList">Get drivers list</button>
                                            </section>

                                        <section  class="col col-10"><label class="select">
                                            <select name="driver">
                                                <option value="">Driver</option>

                                            </select></label></section></div></fieldset>
                                    <footer>
                                        <button type="button" class="btn btn-danger" id = "saveOrder" name = "saveOrder">
                                            Create new order
                                        </button>
                                    </footer>
                                </form>

                            </div>
                            <!-- end widget content -->

                        </div>
                        <!-- end widget div -->

                    </div>
                    <!-- end widget -->





                </article>
                <!-- END COL -->


            </div>

            <!-- END ROW -->

        </section>

    </div></div>

<%--<script src="//yandex.st/jquery/2.2.3/jquery.min.js"></script>--%>
<script src="${pageContext.request.contextPath}/resources/js/libs/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/libs/jquery-ui-1.10.3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/validation.js"></script>
<script src="//api-maps.yandex.ru/2.1/?lang=ru_RU"></script>
<script src="${pageContext.request.contextPath}/resources/custom_view.js"></script>
<script src="${pageContext.request.contextPath}/resources/multiroute_data_access.js"></script>
<script src="${pageContext.request.contextPath}/resources/saveorder.js"></script>
<script src="${pageContext.request.contextPath}/resources/getdriversandtrucks.js"></script>
<script src="${pageContext.request.contextPath}/resources/add_cargo_script.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/app.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/app.config.js"></script>

</body>
</html>
