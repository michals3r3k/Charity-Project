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
                <span class="uppercase">Tu możesz zmienić swoje hasło</span>
            </h1>
        </div>
    </div>
</header>
<footer>
    <div class="contact" id="contact">
        <h2>${user.firstName} ${user.lastName}</h2>
        <h3>Formularz zmiany hasła</h3>
        <form:form class="form--contact" method="post" action="/profile/${user.id}/change-pass">

            <div class="form-group">
                <input id="oldPassword" type="text" name="oldPassword" placeholder="Stare hasło">
            </div>

            <div class="form-group">
                <input id="newPassword" type="text" name="newPassword" placeholder="Nowe hasło">
            </div>

            <div class="form-group">
                <input id="confirmPassword" type="text" name="confirmPassword" placeholder="Powtórz hasło">
            </div>

            <div id="error" class="form-group hidden" style="color: red;">
                <h3>Hasła nie pasują do siebie!</h3>
            </div>

            <div class="form-group form-group--50">
                <button id="submit-btn" class="btn" type="submit">Zmień hasło</button>
            </div>

            <div class="form-group form-group--50">
                <a href="/profile/${user.id}/edit" class="btn btn--without-border">Powrót</a>
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
<script src="<c:url value="/resources/js/password.js"/>"></script>
</body>
</html>
