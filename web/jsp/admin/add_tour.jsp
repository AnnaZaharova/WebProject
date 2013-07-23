<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="m" uri="http://azimkhan.net/taglib" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>
<c:set var="user" scope="page" value="${m:user(pageContext.request)}"/>
<html>
<head>
    <title><fmt:message key="add_tour.title"/></title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>

<%@include file="../../WEB-INF/jspf/header.jspf"%>
<jsp:useBean id="dao" class="kz.enu.epam.azimkhan.tour.dao.TourDAO"/>


<div class="content">
    <h1><fmt:message key="add_tour.title"/></h1>

    <form method="post" action="app?c=add_tour&lang=${locale}">
        <div class="form-row">
            <label for="tourname"><fmt:message key="tour_form.tourname"/></label>
            <input id="tourname" name="tourname" type="text" value="${tour.tourname}">
        </div>

        <div class="form-row">
            <label for="type"><fmt:message key="tour_form.type"/></label>
            <select id="type" name="type">
                <c:forEach var="type" items="${tourTypes}">
                    <option value="${type.id}" <c:if test="${type.id eq tour.type.id}"> selected</c:if>>${type}</option>
                </c:forEach>

            </select>
        </div>

        <div class="form-row">
            <label for="details"><fmt:message key="tour_form.details"/></label>
            <textarea id="details" name="details">${tour.details}</textarea>
        </div>

        <div class="form-row">
            <label for="hot"><fmt:message key="tour_form.hot"/></label>
            <input id="hot" type="checkbox" name="hot" value="1" <c:if test="${tour.hot eq true}">checked</c:if>/>
        </div>

        <div class="form-row">
            <c:choose>
                <c:when test="${tour.price > 0}">
                    <c:set var="price" value="${tour.price}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="price" value="500"/>
                </c:otherwise>
            </c:choose>
            <label for="price"><fmt:message key="tour_form.price"/></label>
            <input id="price" type="number" name="price" value="${price}"/>
        </div>

        <div class="form-row">
            <label for="discount"><fmt:message key="tour_form.discount"/></label>
            <input id="discount" type="number" name="regular_discount" value="${tour.regularDiscount}"/>
        </div>

        <input class="ml135px btn" name="submit" type="submit" value="<fmt:message key="form_submit"/>"/>
        <a href="app?c=manager&lang=${locale}"><fmt:message key="tour_form.back"/></a>

    </form>

</div>

<%@include file="../../WEB-INF/jspf/footer.jspf"%>
</body>
</html>