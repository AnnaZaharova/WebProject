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
    <form method="post" action="app?c=add_tour&lang=${locale}">
        <label for="tourname"><fmt:message key="tour_table.tourname"/></label>
        <input id="tourname" name="tourname" type="text">

        <label for="type"><fmt:message key="tour_table.type"/></label>
        <select id="type" name="type">
            <option value="1"><fmt:message key="tour_type.vaction"/></option>
            <option value="2"><fmt:message key="tour_type.shopping"/></option>
            <option value="3"><fmt:message key="tour_type.excursion"/></option>
        </select>

        <label for="details"><fmt:message key="tour_table.details"/></label>
        <textarea id="details" name="details"></textarea>

        <label for="hot"><fmt:message key="tour_table.hot"/></label>
        <input id="hot" type="checkbox" name="hot" value="1"/>

        <label for="price"><fmt:message key="tour_table.price"/></label>
        <input id="price" type="number" name="price"/>

        <label for="discount"><fmt:message key="tour_table.discount"/></label>
        <input id="discount" type="number" name="regular_discount"/>

        <input type="submit" value="<fmt:message key="form_submit"/>"/>

    </form>

</div>
</body>
</html>