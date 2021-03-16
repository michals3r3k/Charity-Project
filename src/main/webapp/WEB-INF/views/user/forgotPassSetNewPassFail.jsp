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
            <input id="newPassword" type="text" name="newPassword" placeholder="Nowe hasło" />
        </div>
        <div class="form-group">
            <input id="confirmPassword" type="text" name="confirmPassword" placeholder="Potwierdź hasło" />
        </div>
        <div id="error" class="form-group" style="color: red;">
            <h3>Hasła nie pasują do siebie!</h3>
        </div>
        <div class="form-group form-group--buttons">
            <input id="submit-btn" type="submit" class="btn" value="Zmień hasło"/>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="confirmationToken" value="${token}"/>
    </form:form>
</section>

<%@include file="../footer.jsp"%>
<script src="<c:url value="/resources/js/password.js"/> " defer></script>
</body>
</html>
