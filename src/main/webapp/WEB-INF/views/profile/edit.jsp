<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <%@include file="../header.jsp" %>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Cześć ${user.firstName}<br />
                <span class="uppercase">Możesz edytować swoje dane</span>
            </h1>
        </div>
    </div>
</header>
<footer>
    <div class="contact" id="contact">
        <h2>${user.firstName} ${user.lastName}</h2>
        <h3>Formularz edycji</h3>
        <form:form modelAttribute="user" cssClass="form--contact" method="post" action="/profile/${user.id}/edit">
            <form:hidden path="id"/>
            <div class="form-group form-group--50">
                <form:input path="firstName"/>
            </div>
            <div class="form-group form-group--50">
                <form:input path="lastName"/>
            </div>
            <div class="form-group">
                <form:input path="email"/>
            </div>
            <div class="form-group form-group--50">
                <button class="btn" type="submit">Edytuj</button>
            </div>
            <div class="form-group form-group--50">
                <a href="/profile/${user.id}/change-pass" class="btn btn--without-border">Zmień hasło</a>
            </div>


        </form:form>
    </div>
    <div class="bottom-line">
        <span class="bottom-line--copy">Copyright &copy; 2018</span>
        <div class="bottom-line--icons">
            <a href="#" class="btn btn--small"><img src="<c:url value="/resources/images/icon-facebook.svg"/>"/></a> <a
                href="#"
                class="btn btn--small"><img
                src="<c:url value="/resources/images/icon-instagram.svg"/>"></a>
        </div>
    </div>
</footer>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
