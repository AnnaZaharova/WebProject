<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="m" uri="http://azimkhan.net/taglib" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>
<c:set var="user" scope="page" value="${m:user(pageContext.request)}"/>
<html>
<head>
    <title><fmt:message key="admin.manager.title"/></title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>

<%@include file="../../WEB-INF/jspf/header.jspf"%>
<jsp:useBean id="dao" class="kz.enu.epam.azimkhan.tour.dao.TourDAO"/>


<div class="content">
    <div class="actions">
        <a class="btn" href="app?c=add_tour&lang=${locale}"><fmt:message key="tour_table.add"/></a>
    </div>

    <c:forEach items="${dao.findAll()}" var="tour">
        <div class="tour">
            <h3>${tour.tourname}
                <c:if test="${tour.hot}">
                    <span class="hot"><fmt:message key="tour_table.hot"/></span>
                </c:if>
            </h3>
            <div class="inner">
                <p><b><fmt:message key="tour_table.price"/>:</b> ${tour.price} USD</p>
                <p><b><fmt:message key="tour_table.type"/>:</b> ${tour.type}</p>
                <p><b><fmt:message key="tour_table.details"/>:</b> ${tour.details}</p>
                <p><b><fmt:message key="tour_table.discount"/>:</b> ${tour.regularDiscount}%</p>
            </div>
            <a class="btn btn-blue" href="app?c=update_tour&id=${tour.id}&lang=${locale}"><fmt:message key="tour_table.edit"/></a>
            <a class="btn btn-red" href="app?c=delete_tour&id=${tour.id}&lang=${locale}"><fmt:message key="tour_table.delete"/></a>
        </div>
    </c:forEach>

    <div class="actions">
        <a class="btn" href="app?c=add_tour&lang=${locale}"><fmt:message key="tour_table.add"/></a>
    </div>



</div>
<%@include file="../../WEB-INF/jspf/footer.jspf"%>
</body>
</html>