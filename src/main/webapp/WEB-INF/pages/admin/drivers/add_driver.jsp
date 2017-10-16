<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="../header.jsp"/>

</head>
<body>

<c:import url="../admin_menu.jsp"/>

<div id="main" role="main">
    <div id="content">
         <c:if test="${error != null}">
        <div class="alert alert-danger fade in">
            <strong>Error!</strong> ${error}
        </div></c:if>
        <c:if test="${success != null}">
            <div class="alert alert-success fade in">
                <strong>${success}</strong>
            </div></c:if>
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

                                <form id="order-form" class="smart-form" novalidate="novalidate" action="${pageContext.request.contextPath}/admin/addDriver" method="POST">
                                    <header>
                                        New driver
                                    </header>

                                    <fieldset>
                                        <section>
                                            <label class="input">
                                                <input type="text" name="name" placeholder="Name">
                                            </label>
                                        </section>
                                        <section>
                                            <label class="input">
                                                <input type="text" name="surname" placeholder="Surname">
                                            </label>
                                        </section>

                                        <section>
                                            <label class="input">
                                                <input type="text" name="employeeId" placeholder="Employee ID">
                                            </label>
                                        </section>
                                        <section>
                                            <label class="select">
                                                <select name="currentCity">
                                                    <option value="">Select city</option>
                                                    <c:forEach items="${cityList}" var="city">
                                                        <option  value=${city.id} >${city.name}</option>
                                                    </c:forEach>                        </select></label>
                                        </section>

                                    </fieldset>
                                    <header> Create driver account</header>

                                    <fieldset>
                                        <section>
                                        <label class="input">
                                            <input type="text" name="email" placeholder="Email">
                                        </label>
                                         </section>
                                        <section>
                                         <label class="input">
                                             <input type="text" name="password" placeholder="Password">
                                            </label>
                                           </section>
                                    </fieldset>
                                    <footer>
                                        <button type="submit" class="btn btn-primary">
                                            Add new driver
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

<script src="${pageContext.request.contextPath}/resources/js/libs/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/validation.js"></script>
</body>
</html>
