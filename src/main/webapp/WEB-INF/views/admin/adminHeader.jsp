<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="container container--70">
    <ul class="nav--actions">
        <li class="logged-user">
            Witaj Admin: <sec:authentication property="principal.username"/>
            <ul class="dropdown">
                <li><a href="#">Profil</a></li>
                <li><a href="#">Moje zbiórki</a></li>
                <li>
                    <form action="<c:url value="/logout"/> " method="post">
                        <input type="submit" value="Wyloguj" class="btn-link">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    </form>
                </li>
            </ul>
        </li>
    </ul>


    <ul>
        <li><a href="/" class="btn btn--without-border active">Strona Główna</a></li>
        <li><a href="/admin#institutions" class="btn btn--without-border">Fundacje</a></li>
    </ul>
</nav>