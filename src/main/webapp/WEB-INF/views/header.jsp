<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="pl.coderslab.charity.user.UserService" %>
<%@ page import="pl.coderslab.charity.user.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="container container--70">
    <%
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
        UserService userService = context.getBean(UserService.class);
        User user = userService.findByEmail(email);
        pageContext.setAttribute("currentUser", user);
    %>
    <sec:authorize access="isAuthenticated()">
    <ul class="nav--actions">
        <li class="logged-user">
            Witaj ${currentUser.firstName} ${currentUser.lastName}
<%--            Witaj <sec:authentication property="principal.username"/>--%>
            <ul class="dropdown">
                <li><a href="/profile/${currentUser.id}/edit">Profil</a></li>
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
    </sec:authorize>

    <sec:authorize access="!isAuthenticated()">
    <ul class="nav--actions">
        <li><a href="/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
        <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
    </ul>
    </sec:authorize>

    <ul>
        <li><a href="/" class="btn btn--without-border active">Start</a></li>
        <sec:authorize access="hasRole('ADMIN')">
        <li><a href="/admin" class="btn btn--without-border active">Admin Panel</a></li>
        </sec:authorize>
        <li><a href="/#steps" class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href="/#about" class="btn btn--without-border">O nas</a></li>
        <li><a href="/#institutions" class="btn btn--without-border">Fundacje i organizacje</a></li>
        <sec:authorize access="isAuthenticated()">
        <li><a href="/donation" class="btn btn--without-border">Przekaż dary</a></li>
        </sec:authorize>
        <li><a href="/#contact" class="btn btn--without-border">Kontakt</a></li>
    </ul>
</nav>