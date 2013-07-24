<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="auth" uri="http://azimkhan.net/taglib/auth" %>
<%@taglib prefix="n" uri="http://azimkhan.net/taglib/notification" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>
<c:set var="user" scope="page" value="${auth:user(pageContext.request)}"/>
<html>
<head>
    <title><fmt:message key="add_tour.title"/></title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>

<%@include file="../../WEB-INF/jspf/header.jspf"%>


<div class="content">
    <h1><fmt:message key="add_tour.title"/></h1>

    <%@include file="_form.jspf"%>

</div>

<%@include file="../../WEB-INF/jspf/footer.jspf"%>
</body>
</html>