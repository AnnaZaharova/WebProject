<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="m" uri="http://azimkhan.net/taglib" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>
<c:set var="user" scope="page" value="${m:user(pageContext.request)}"/>
<html>
<head>
    <title><fmt:message key="auth.page.title"/></title>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>

<%@include file="../../WEB-INF/jspf/header.jspf"%>
<jsp:useBean id="dao" class="kz.enu.epam.azimkhan.auth.dao.TourDAO"/>


<div class="content">
    <div class="actions">
        <a class="btn" href="#"><fmt:message key="tour_table.add"/></a>
    </div>
    <table>
        <thead>
            <th><fmt:message key="tour_table.id"/></th>
            <th><fmt:message key="tour_table.tourname"/></th>
            <th><fmt:message key="tour_table.details"/></th>
        </thead>
        <tbody>
        <c:forEach items="${dao.findAll()}" var="tour">
            <tr>
                <td>${tour.id}</td>
                <td>${tour.tourname}</td>
                <td><p>${tour.details}</p></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>