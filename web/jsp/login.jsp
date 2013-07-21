<%--
  Created by IntelliJ IDEA.
  User: azimkhan
  Date: 12.07.13
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <title><fmt:message key="auth.page.title"/></title>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>

    <%@include file="../WEB-INF/jspf/header.jspf"%>



    <div class="content">

        <h1><fmt:message key="auth.page.title"/> </h1>
        <p><fmt:message key="auth.page.message"/> </p>
        <form method="post" id="loginForm">
            <input type="hidden" name="command" value="login"/>
            <label for="login"><fmt:message key="auth.page.login_form.login"/> :</label>
            <input id="login" type="text" name="login"/>
            <label for="password"><fmt:message key="auth.page.login_form.password"/>:</label>
            <input id="password" type="password" name="password"/>
            <input type="submit" value="<fmt:message key="auth.page.login_form.submit"/>"/>
        </form>
    </div>
</body>
</html>