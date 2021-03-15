<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
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
    <ul class="nav--actions">
        <li class="logged-user">
            Witaj Admin: ${currentUser.firstName} ${currentUser.lastName}
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


    <ul>
        <li><a href="/" class="btn btn--without-border active">Strona Główna</a></li>
        <li><a href="/admin#institutions" class="btn btn--without-border">Fundacje</a></li>
        <li><a href="/admin#admins" class="btn btn--without-border">Administratorzy</a></li>
        <li><a href="/admin#users" class="btn btn--without-border">Użytkownicy</a></li>
    </ul>
</nav>