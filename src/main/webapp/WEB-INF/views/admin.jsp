<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Административная панель | Theatre</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/theatre/admin.css">
</head>
<body>
<div class="admin-container">
    <header class="admin-header">
        <div class="welcome-message">
            <h1>Приветствуем Вас, ${client.name}!</h1>
            <p>Административная панель театра</p>
        </div>
    </header>

    <section class="reports-section">
        <h2>Генерация отчетов</h2>

        <div class="report-cards">
            <!-- Карточка 1 -->
            <div class="report-card">
                <h3>Предпочтения зрителей</h3>
                <p>Анализ популярности различных мест в зале. Узнайте, какие ряды и места выбирают зрители чаще всего.</p>
                <form action="/theatre/reports/preferences" method="get">
                    <button type="submit" class="generate-btn">Сформировать отчет</button>
                </form>
            </div>

            <!-- Карточка 2 -->
            <div class="report-card">
                <h3>Популярные мероприятия</h3>
                <p>Рейтинг самых востребованных спектаклей и событий. Анализ продаж по различным мероприятиям.</p>
                <form action="/theatre/reports/popular" method="get">
                    <button type="submit" class="generate-btn">Сформировать отчет</button>
                </form>
            </div>
        </div>
    </section>
</div>
</body>
</html>