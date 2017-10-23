<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header id="header">
    <%--<div id="logo-group">--%>

        <%--<span id="logo"><img src="<c:url value="/resources/img/logo.png"/>"/> </span>--%>

    <%--</div>--%>

    <div class="pull-right">

        <!-- logout button -->
        <div id="logout" class="btn-header transparent pull-right">
            <span> <a href="${pageContext.request.contextPath}/signout" title="Sign Out" data-action="userLogout"><i class="fa fa-sign-out"></i></a> </span>
        </div>


    </div>
    <!-- end pulled right: nav area -->

</header>
<aside id="left-panel">

    <!-- User info -->
    <div class="login-info">
				<span> <!-- User image size is adjusted inside CSS, it should stay as it -->

					<a id="show-shortcut" data-action="toggleShortcut">
						<span>
							${usermail}
						</span>
					</a>

				</span>
    </div>

    <nav>
        <ul>

            <li>
                <a href="${pageContext.request.contextPath}/driver/myorder"><i class="fa fa-lg fa-fw fa-table"></i> <span class="menu-item-parent">My order</span></a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/driver/shifts"><i class="fa fa-lg fa-fw fa-table"></i> <span class="menu-item-parent">Shift journal</span></a>
            </li>

        </ul>
    </nav>

</aside>
