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
    <%@include file="../header.jsp" %>
    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Panel twoich darów<br/>
                Zarządzaj swoimi darami
            </h1>
        </div>
    </div>
</header>


<section id="donations" class="steps">
    <h2>Lista darów</h2>

    <table class="custom-table">
        <thead>
        <tr class="custom-tr-dark">
            <th class="custom-th">Ulica</th>
            <th>Miasto</th>
            <th>Kategorie</th>
            <th>Data</th>
            <th>Godzina</th>
            <th>Status</th>
            <th>Akcja</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${donations}" var="donation">
            <tr class="custom-tr">
                <td class="custom-td">${donation.street}</td>
                <td class="custom-td">${donation.city}</td>
                <td class="custom-td">
                    <c:forEach items="${donation.categories}" var="category">
                        ${category.name}
                    </c:forEach>
                </td>
                <td class="custom-td">${donation.pickUpDate}</td>
                <td class="custom-td">${donation.pickUpTime}</td>
                <td class="custom-td">
                    <c:if test="${donation.taken}">
                        Odebrane
                    </c:if>
                    <c:if test="${!donation.taken}">
                        Nieodebrane
                    </c:if>
                </td>
                <td class="custom-td">
                    <c:if test="${!donation.taken}">
                        <a href="/profile/${user.id}/donations/mark-as-taken/${donation.id}" class="btn btn--without-border">Oznacz jako odebrane</a>
                    </c:if>
                    <a href="/profile/${user.id}/donation/${donation.id}" class="btn btn--without-border">Szczegóły</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="/donation" class="btn btn--large">Przekaż dary</a>
</section>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
