<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<c:if test="${message != null}">
    <div class="alert alert-dismissable response alert-success">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <c:out value="${message}"/>
    </div>
</c:if>
<c:if test="${exception != null}">
    <div class="alert alert-dismissable response alert-danger">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <c:out value="${exception}"/>
    </div>
</c:if>