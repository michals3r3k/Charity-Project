<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" />
</head>
<body>
<header>
    <%@include file="../header.jsp"%>
</header>

<section class="login-page">
    <h2>Zaponiałem hasła</h2>
    <form:form method="post">
        <div class="form-group">
            <input type="email" name="email" placeholder="Email" />
        </div>
        <div class="form-group form-group--buttons">
            <input type="submit" class="btn" value="Przypomnij"/>
            <a href="/login" class="btn btn--without-border">Zaloguj się</a>
            <a href="/register" class="btn btn--without-border">Załóż konto</a>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>
</section>

<%@include file="../footer.jsp"%>
</body>
</html>
