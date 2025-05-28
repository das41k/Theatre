<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Административная панель | Theatre</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/theatre/preferences.css">
</head>
<body>
<div class="admin-container">
    <h2>Сбор информации о предпочтениях зрителей относительно мест</h2>
    <div class="preferences-table">
        <table>
            <thead>
            <tr>
                <th>№</th>
                <th>Название</th>
                <th>Тип мероприятия</th>
                <th>Зал</th>
                <th>Дата проведения</th>
                <th>Количество проданных билетов</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="popular" items="${populars}" varStatus="status">
                <tr class="${status.index < 3 ? 'top-3' : (status.index < 5 ? 'top-5' : '')}">
                    <td>${status.index + 1}</td>
                    <td>${popular[0].name}</td>
                    <td>${popular[0].eventType.name}</td>
                    <td>${popular[0].hall.number}</td>
                    <td>${popular[0].dateEvent}</td>
                    <td>${popular[1]}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
