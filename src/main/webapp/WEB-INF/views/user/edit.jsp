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
    <%@include file="../admin/adminHeader.jsp" %>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Edytuj poszczególnych użytkowników<br />
                <span class="uppercase">Użytkownik "${user.email}"</span>
            </h1>
        </div>
    </div>
</header>
<footer>
<div class="contact" id="contact">
    <h2>Edytuj ${user.firstName} ${user.lastName}</h2>
    <h3>Formularz edycji</h3>
    <form:form modelAttribute="user" cssClass="form--contact" method="post" action="/admin/user/edit">
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
<%--            <form:checkboxes path="roles" items="${roles}"/>--%>
            <c:forEach items="${roles}" var="role">
                <input id="roles${role.id}" type="checkbox" name="roles" value="${role.id}">
                <label for="roles${role.id}">${role.roleType.toString()}</label>
            </c:forEach>
        </div>
        <div class="form-group form-group--50"></div>
<%--        <form:hidden path="enabled"/>--%>

        <button class="btn" type="submit">Edytuj</button>
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
