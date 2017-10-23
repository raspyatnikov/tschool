<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Logiweb Cargo service</title>
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/bootstrap.min.css"/> "/>
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/font-awesome.min.css"/> "/>
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/smartadmin-production-plugins.min.css"/> "/>
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/smartadmin-production.min.css"/> "/>
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/smartadmin-skins.min.css"/> "/>

</head>
<body class="animated fadeInDown  desktop-detected pace-done"><div class="pace  pace-inactive"><div class="pace-progress" data-progress-text="100%" data-progress="99" style="width: 100%;">
    <div class="pace-progress-inner"></div>
</div>
    <div class="pace-activity"></div></div>


<div id="main" role="main">

    <!-- MAIN CONTENT -->
    <div id="content" class="container">

        <div class="row">

            <div class="col-xs-12 col-sm-12 col-md-5 col-lg-4" style ="left:25%">
                <div class="well no-padding center-block">
                    <form action="${pageContext.request.contextPath}/j_spring_security_check" id="login-form" class="smart-form client-form" novalidate="novalidate" method="post">
                        <header>
                            Sign In
                        </header>

                        <fieldset>

                            <section>
                                <label class="label">E-mail</label>
                                <label class="input"> <i class="icon-append fa fa-user"></i>
                                    <input type="email" name="email">
                                    <b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i> Please enter email address/username</b></label>
                            </section>

                            <section>
                                <label class="label">Password</label>
                                <label class="input"> <i class="icon-append fa fa-lock"></i>
                                    <input type="password" name="password">
                                    <b class="tooltip tooltip-top-right"><i class="fa fa-lock txt-color-teal"></i> Enter your password</b> </label>
                                <c:if test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">
                                    <div class="error">
                                        Email or password is incorrect!
                                    </div>
                                </c:if>

                            </section>


                        </fieldset>
                        <footer>
                            <button type="submit" class="btn btn-primary">
                                Sign in
                            </button>
                        </footer>
                    </form>

                </div>





            </div>
        </div>
    </div>

</div>

</body>
</html>
