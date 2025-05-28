<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Theatre | Личный кабинет</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/theatre/profile.css">
</head>
<body>
<div class="container">
    <section class="personal-info">
        <h2>Личная информация</h2>
        <p><strong>ФИО:</strong> ${client.name}</p>
        <p><strong>Телефон:</strong> ${client.phone}</p>
        <p><strong>Почта:</strong> ${client.email}</p>
    </section>

    <section class="personal-info">
        <h2>Мои билеты</h2>
        <c:choose>
            <c:when test="${empty client.tickets}">
                <p>У вас пока нет купленных билетов.</p>
            </c:when>
            <c:otherwise>
                <div class="tickets-grid">
                    <c:forEach var="ticket" items="${client.tickets}">
                        <div class="ticket-card">
                            <div class="ticket-header">
                                <h3 class="ticket-event">${ticket.event.name}</h3>
                                <span class="ticket-type">${ticket.event.eventType.name}</span>
                            </div>
                            <div class="ticket-details">
                                <div class="ticket-detail">
                                    <strong>Дата мероприятия</strong>
                                        ${ticket.event.dateEvent}
                                </div>
                                <div class="ticket-detail">
                                    <strong>Дата покупки</strong>
                                        ${ticket.dateSale}
                                </div>
                                <div class="ticket-detail">
                                    <strong>Зал</strong>
                                    №${ticket.event.hall.number}
                                </div>
                                <div class="ticket-detail">
                                    <strong>Место</strong>
                                    Ряд ${ticket.place.row}, Место ${ticket.place.number}
                                </div>
                                <div class="ticket-detail">
                                    <strong>Стоимость</strong>
                                        ${ticket.cost} руб.
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </section>
</div>
</body>
</html>