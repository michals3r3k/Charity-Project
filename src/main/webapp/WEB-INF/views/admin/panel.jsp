<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<header class="header--main-page">
    <%@include file="adminHeader.jsp" %>
    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Panel Administracyjny<br/>
                Edytuj, zmieniaj, zarządzaj!
            </h1>
        </div>
    </div>
</header>

<section id="stats" class="stats">
    <div class="container container--85">
        <div class="stats--item">
            <em>${countBags}</em>
            <h3>Oddanych worków</h3>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius est beatae, quod accusamus illum
                tempora!</p>
        </div>

        <div class="stats--item">
            <em>${countDonations}</em>
            <h3>Przekazanych darów</h3>
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Laboriosam magnam, sint nihil cupiditate quas
                quam.</p>
        </div>

    </div>
</section>

<section id="institutions" class="steps">
    <h2>Lista fundacji</h2>

    <table class="custom-table">
        <thead>
        <tr class="custom-tr-dark">
            <th class="custom-th">Id</th>
            <th>Nazwa</th>
            <th>Cel</th>
            <th>Akcja</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${institutions}" var="institution">
            <tr class="custom-tr">
                <td class="custom-td">${institution.id}</td>
                <td class="custom-td">${institution.name}</td>
                <td class="custom-td">${institution.description}</td>
                <td class="custom-td">
                    <a href="/admin/institution/edit/${institution.id}" class="btn btn--without-border">Edytuj</a>
                    <a href="/admin/institution/delete/${institution.id}" class="btn btn--without-border">Usuń</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="/admin/institution/add" class="btn btn--large">Dodaj fundację</a>
</section>

<hr class="custom-hr">

<section id="admins" class="steps">
    <h2>Lista administratorów</h2>
    <table class="custom-table">
        <thead>
        <tr class="custom-tr-dark">
            <th class="custom-th">Id</th>
            <th>Imię i Nazwisko</th>
            <th>Email</th>
            <th>Role</th>
            <th>Akcja</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${admins}" var="admin">
            <tr class="custom-tr">
                <td class="custom-td">${admin.id}</td>
                <td class="custom-td">${admin.firstName} ${admin.lastName}</td>
                <td class="custom-td">${admin.email}</td>
                <td class="custom-td">
                    <c:forEach items="${admin.roles}" var="role">
                        <c:if test='${role.roleType.toString().equals("ROLE_ADMIN")}'>
                            ADMIN,
                        </c:if>
                        <c:if test='${role.roleType.toString().equals("ROLE_USER")}'>
                            USER,
                        </c:if>
                    </c:forEach>
                </td>

                <td class="custom-td">
                    <c:if test="${admin.id!=loggedAdmin.id}">
                        <a href="/admin/admin/take-off-permissions/${admin.id}" class="btn btn--without-border">Zdejmij uprawnienia admina</a>
                    </c:if>
                    <c:if test="${admin.id==loggedAdmin.id}">
                        <a href="/profile/${admin.id}/edit" class="btn btn--without-border">Edytuj swój profil</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>

<hr class="custom-hr">
<section id="users" class="steps">
    <h2>Lista użytkowników</h2>
    <table class="custom-table">
        <thead>
        <tr class="custom-tr-dark">
            <th class="custom-th">Id</th>
            <th>Imię i Nazwisko</th>
            <th>Email</th>
            <th>Role</th>
            <th>Odblokowany</th>
            <th>Akcja</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr class="custom-tr">
                <td class="custom-td">${user.id}</td>
                <td class="custom-td">${user.firstName} ${user.lastName}</td>
                <td class="custom-td">${user.email}</td>
                <td class="custom-td">
                    <c:forEach items="${user.roles}" var="role">
                        <c:if test='${role.roleType.toString().equals("ROLE_ADMIN")}'>
                            ADMIN,
                        </c:if>
                        <c:if test='${role.roleType.toString().equals("ROLE_USER")}'>
                            USER,
                        </c:if>
                    </c:forEach>
                </td>
                <td class="custom-td">${user.enabled}</td>
                <td class="custom-td">
                    <a href="/admin/user/edit/${user.id}" class="btn btn--without-border">Edytuj</a>
                    <c:if test="${user.id!=loggedAdmin.id}">
                        <a href="/admin/user/delete/${user.id}" class="btn btn--without-border">Usuń</a>
                    </c:if>
                    <c:if test="${user.enabled}">
                        <a href="/admin/user/switch-enable/${user.id}" class="btn btn--without-border">Zablokuj</a>
                    </c:if>
                    <c:if test="${!user.enabled}">
                        <a href="/admin/user/switch-enable/${user.id}" class="btn btn--without-border">Odblokuj</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>

<section id="donations" class="steps">
    <h2>Lista darów</h2>

    <table class="custom-table">
        <thead>
        <tr class="custom-tr-dark">
            <th class="custom-th">Id</th>
            <th>Instytucja</th>
            <th>Kategorie</th>
            <th>Godzina</th>
            <th>Data</th>
            <th>Status</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${donations}" var="donation">
            <tr class="custom-tr">
                <td class="custom-td">${donation.id}</td>
                <td class="custom-td">${donation.institution.name}</td>
                <td class="custom-td">
                    <c:forEach items="${donation.categories}" var="category">
                        ${category.name}
                    </c:forEach>
                </td>
                <td class="custom-td">${donation.pickUpTime}</td>
                <td class="custom-td">${donation.pickUpDate}</td>
                <td class="custom-td">
                        <c:if test="${donation.taken}">
                            Odebrane
                        </c:if>
                        <c:if test="${!donation.taken}">
                            Nieodebrane
                        </c:if>
                </td>
                <td class="custom-td">${donation.user.email}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="/donation" class="btn btn--large">Przekaż dary</a>
</section>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
