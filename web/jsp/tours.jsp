<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="m" uri="http://azimkhan.net/taglib" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>
<c:set var="user" scope="page" value="${m:user(pageContext.request)}"/>
<html>
<head>
    <title><fmt:message key="main.page.title"/></title>
    <link rel="stylesheet" href="css/style.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>

<%@include file="../WEB-INF/jspf/header.jspf"%>

<div class="content" id="tours">
    <c:forEach var="tour" items="${tours}">
        <div class="tour-view">
            <h3>${tour.tourname}</h3>
            <c:set var="type" value="${tour.type.displayName}"/>
            <p class="type type-${type}"><fmt:message key="tour_type.${type}"/><c:if test="${tour.hot}"><span class="hot"><fmt:message key="tour_table.hot"/></span></c:if></p>
            <p class="details">${tour.details}</p>
            <a class="btn" href="app?c=order&id=${tour.id}&lang=${locale}"><fmt:message key="tour.order"/></a>
            <c:set var="discounted" value="${tour.price - (tour.price * tour.regularDiscount * 0.01)}"/>
            <span class="price">${tour.price} (${discounted}) USD</span>
        </div>
    </c:forEach>
</div>

<%@include file="../WEB-INF/jspf/footer.jspf"%>

</body>
</html>