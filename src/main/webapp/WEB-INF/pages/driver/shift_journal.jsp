<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shift Journal</title>
    <c:import url="../admin/header.jsp"/>
</head>
<body>
<c:import url="driver_menu.jsp"/>
<div id="main" role="main">
    <div id="content">
<c:import url="../message.jsp"/>
        <!-- NEW COL START -->
        <article class="col-sm-12 col-md-12 col-lg-6">

            <div class="jarviswidget" id="wid-id-4" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-custombutton="false">

                <header>
                    <b>New shift record</b>
                </header>

                <div>
                    <div class="jarviswidget-editbox">
                    </div>
                    <div class="widget-body">
        <form name = "form_main" action="${pageContext.request.contextPath}/driver/addShift" method="post">


            <div class="row">
                <div class="col col-sm-6">
                    <div class="form-group">
                        <label>Shift begginned</label>
                        <div class="input-group">
                            <input name="shiftBeggined" required type="text" class="form-control" data-mask="99/99/9999" data-mask-placeholder="-">
                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        </div>
                        <p class="note">
                            Data format ****-**-** **:**
                        </p>
                    </div></div>

                <div class="col col-sm-6">
                    <div class="form-group">
                        <label>Shift finished</label>
                        <div class="input-group">
                            <input name="shiftEnded" required type="text" class="form-control" data-mask="99/99/9999" data-mask-placeholder="-">
                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        </div>
                        <p class="note">
                            Data format ****-**-** **:**
                        </p>
                    </div></div> </div>
                <footer>
                    <button type="submit" class="btn btn-primary">
                        Add new shift
                    </button>
                </footer>
           </form>

                    </div></div></div></article>

        <table class="table table-bordered bg-color-white" >
            <thead>
            <tr>
                <th>Shift start time</th>
                <th>Shift end time</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${shift_records}" var="record">
            <tr>
                <td>${record.shiftBeggined}</td>
                <td>${record.shiftEnded}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
<script src="${pageContext.request.contextPath}/resources/js/libs/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/libs/jquery-ui-1.10.3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/validation.js"></script>
<script src="${pageContext.request.contextPath}/resources/custom_view.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/app.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/app.config.js"></script>
    </div></div>
</body>
</html>
