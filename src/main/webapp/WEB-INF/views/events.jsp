<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Theatre</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/theatre/events.css">
    <style>
        /* Вставить все CSS стили выше */
    </style>
</head>
<body>
<header>
    <nav>
        <ul>
            <li>Список мероприятий</li>
            <li>Личный кабинет</li>
        </ul>
    </nav>
</header>

<main>
    <div class="filter-section">
        <h3>Фильтр</h3>
        <form id="filterForm" action="${pageContext.request.contextPath}/events/filters">

            <!-- Тип мероприятия -->
            <div class="filter-group">
                <h4>Тип мероприятия</h4>
                <div class="filter-options">
                    <c:forEach var="eventType" items="${types}">
                        <label><input type="checkbox" name="eventTypeId" value="${eventType.id}"> ${eventType.name}</label>
                    </c:forEach>
                </div>
            </div>

            <!-- Дата -->
            <div class="filter-group">
                <h4>Дата</h4>
                <div class="filter-options">
                    <label><input type="checkbox" name="dateFilter" value="today"> Сегодня</label>
                    <label><input type="checkbox" name="dateFilter" value="tomorrow"> Завтра</label>
                    <label><input type="checkbox" name="dateFilter" value="week"> На этой неделе</label>
                    <label><input type="checkbox" name="dateFilter" value="month"> В этом месяце</label>
                </div>
            </div>

            <div class="filter-group">
                <h4>Зал</h4>
                <div class="filter-options">
                    <c:forEach var="var" begin="1" end="${countHalls}">
                        <label><input type="checkbox" name="hallId" value="${var}"> Зал ${var} </label>
                    </c:forEach>
                </div>
            </div>

            <button class="apply-filters-button">Применить фильтры</button>
        </form>
    </div>

    <div class="events-section">
        <h2>Список ближайших мероприятий</h2>

        <div class="events-grid">
            <c:forEach var="event" items="${events}">
                <a href="${pageContext.request.contextPath}/events/${event.id}/page" class="event-link"
                   style="text-decoration: none; color: inherit; display: block;">
                    <div class="event-card">
                        <img class="event-image"
                             src="${pageContext.request.contextPath}/events/${event.id}/image"
                             alt="${event.name}">
                        <div class="event-info">
                            <h3 class="event-name">${event.name}</h3>
                            <p class="event-details">${event.dateEvent}</p>
                            <p class="event-details">Зал ${event.hall.number}</p>
                            <span class="event-type">${event.eventType.name}</span>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
</main>
</body>
</html>