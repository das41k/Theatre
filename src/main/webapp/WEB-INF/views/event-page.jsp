<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${event.name} | Theatre</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/theatre/events.css">
    <link rel="stylesheet" type="text/css" href="/theatre/event-page.css">
    <style>
        /* Стили для модального окна подтверждения */
        .confirmation-modal {
            display: none;
            position: fixed;
            z-index: 1001;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            justify-content: center;
            align-items: center;
        }

        .confirmation-content {
            background-color: white;
            padding: 30px;
            border-radius: 12px;
            width: 500px;
            max-width: 90%;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }

        .confirmation-title {
            font-size: 1.5rem;
            margin-bottom: 20px;
            color: #333;
            text-align: center;
        }

        .ticket-info {
            margin-bottom: 25px;
        }

        .ticket-info table {
            width: 100%;
            border-collapse: collapse;
        }

        .ticket-info td {
            padding: 10px;
            border-bottom: 1px solid #eee;
        }

        .ticket-info td:first-child {
            font-weight: 600;
            color: #555;
            width: 40%;
        }

        .confirmation-buttons {
            display: flex;
            justify-content: center;
            gap: 15px;
        }

        .confirm-btn, .cancel-btn {
            padding: 10px 25px;
            border-radius: 8px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s;
            border: none;
        }

        .confirm-btn {
            background: linear-gradient(135deg, #34a853, #4285f4);
            color: white;
        }

        .cancel-btn {
            background: #f1f1f1;
            color: #555;
        }

        .confirm-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .cancel-btn:hover {
            background: #e0e0e0;
        }
    </style>
</head>
<body>
<header>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/events"
                   style="text-decoration: none; color: inherit; display: block;">Список мероприятий</a></li>
            <li><a href="${pageContext.request.contextPath}/profile"
                   style="text-decoration: none; color: inherit; display: block;">Личный кабинет</a></li>
        </ul>
    </nav>
</header>

<!-- Герой-секция с изображением и карточкой -->
<div class="event-hero">
    <img class="event-image"
         src="${pageContext.request.contextPath}/events/${event.id}/image"
         alt="${event.name}">

    <div class="event-card-overlay">
        <h1 class="event-title">${event.name}</h1>

        <div class="event-meta">
            <div class="event-meta-item">
                <span class="event-meta-icon">📅</span>
                <span>${event.dateEvent}</span>
            </div>

            <div class="event-meta-item">
                <span class="event-meta-icon">🎭</span>
                <span>${event.eventType.name}</span>
            </div>

            <div class="event-meta-item">
                <span class="event-meta-icon">🏛️</span>
                <span>Зал ${hall.number}</span>
            </div>

            <div class="event-meta-item">
                <span class="event-meta-icon">👥</span>
                <span>${hall.capacity} мест</span>
            </div>
        </div>

        <button class="buy-ticket-btn" onclick="openModal()">Купить билеты</button>
    </div>
</div>

<!-- Модальное окно с залом -->
<div id="hallModal" class="modal">
    <div class="modal-content">
        <span class="close-modal" onclick="closeModal()">&times;</span>
        <h2 class="hall-title">Выбор мест - Зал ${hall.number}</h2>

        <div class="stage-indicator">СЦЕНА</div>

        <div class="hall-projection">
            <c:forEach var="row" begin="1" end="${rows}">
                <div class="hall-row">
                    <div class="row-number">Ряд ${row}</div>
                    <div class="row-places">
                        <c:forEach var="place" items="${places}">
                            <c:if test="${place[2] == row}">
                                <div class="place-container ${place[4] ? 'occupied' : 'available'}"
                                     data-row="${place[2]}"
                                     data-number="${place[1]}">
                                    <div class="place-circle"></div>
                                    <div class="place-info">
                                        <span class="place-number">${place[1]}</span>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div id="placeActions" class="place-actions" style="display: none; justify-content: center; gap: 10px; margin-top: 20px;">
            <button class="action-btn" style="background: linear-gradient(135deg, #4285f4, #34a853); color: white; border: none; padding: 10px 20px; font-size: 1rem; border-radius: 8px; cursor: pointer; font-weight: 600; transition: all 0.3s;" onmouseover="this.style.transform='translateY(-2px)'; this.style.boxShadow='0 4px 10px rgba(0, 0, 0, 0.1)';" onmouseout="this.style.transform=''; this.style.boxShadow='';" onclick="showConfirmation()">Купить билет</button>
            <button class="action-btn" style="background: linear-gradient(135deg, #4285f4, #34a853); color: white; border: none; padding: 10px 20px; font-size: 1rem; border-radius: 8px; cursor: pointer; font-weight: 600; transition: all 0.3s;" onmouseover="this.style.transform='translateY(-2px)'; this.style.boxShadow='0 4px 10px rgba(0, 0, 0, 0.1)';" onmouseout="this.style.transform=''; this.style.boxShadow='';">Посмотреть вид с места</button>
        </div>

        <div class="legend">
            <div class="legend-item">
                <div class="legend-circle available"></div>
                <span>Свободно</span>
            </div>
            <div class="legend-item">
                <div class="legend-circle occupied"></div>
                <span>Занято</span>
            </div>
            <div class="legend-item">
                <div class="legend-circle selected"></div>
                <span>Выбрано</span>
            </div>
        </div>
    </div>
</div>
<!-- Модальное окно подтверждения покупки -->
<div id="confirmationModal" class="confirmation-modal">
    <div class="confirmation-content">
        <h3 class="confirmation-title">Вы действительно хотите купить данный билет?</h3>

        <div class="ticket-info">
            <table>
                <tr>
                    <td>Название мероприятия:</td>
                    <td id="confirmEventName">${event.name}</td>
                </tr>
                <tr>
                    <td>Тип мероприятия:</td>
                    <td id="confirmEventType">${event.eventType.name}</td>
                </tr>
                <tr>
                    <td>Дата проведения:</td>
                    <td id="confirmEventDate">${event.dateEvent}</td>
                </tr>
                <tr>
                    <td>Зал:</td>
                    <td id="confirmHallNumber">${hall.number}</td>
                </tr>
                <tr>
                    <td>Ряд:</td>
                    <td id="confirmRowNumber"></td>
                </tr>
                <tr>
                    <td>Место:</td>
                    <td id="confirmPlaceNumber"></td>
                </tr>
            </table>
        </div>

        <div class="confirmation-buttons">
            <button class="confirm-btn">Подтвердить</button>
            <button class="cancel-btn" onclick="closeConfirmation()">Отмена</button>
        </div>
    </div>
</div>
<script>
    function openModal() {
        document.getElementById('hallModal').style.display = 'flex';
        document.body.style.overflow = 'hidden';
    }

    function closeModal() {
        document.getElementById('hallModal').style.display = 'none';
        document.body.style.overflow = 'auto';
    }

    // Закрытие при клике вне модального окна
    window.onclick = function(event) {
        const modal = document.getElementById('hallModal');
        if (event.target === modal) {
            closeModal();
        }
    }
    let selectedPlace = null;

    function selectPlace(element) {
        // Если место уже выбрано, сбросить выбор
        if (selectedPlace) {
            selectedPlace.classList.remove('selected');
        }

        // Выбрать новое место
        if (element !== selectedPlace) {
            element.classList.add('selected');
            selectedPlace = element;
            document.getElementById('placeActions').style.display = 'flex';
        } else {
            selectedPlace = null;
            document.getElementById('placeActions').style.display = 'none';
        }
    }
    // Добавляем обработчик событий для мест
    document.querySelectorAll('.place-container.available').forEach(place => {
        place.addEventListener('click', function() {
            selectPlace(this);
        });
    });
    function showConfirmation() {
        if (!selectedPlace) return;

        // Заполняем данные о месте
        document.getElementById('confirmRowNumber').textContent = selectedPlace.dataset.row;
        document.getElementById('confirmPlaceNumber').textContent = selectedPlace.dataset.number;

        // Показываем модальное окно подтверждения
        document.getElementById('confirmationModal').style.display = 'flex';
    }

    function closeConfirmation() {
        document.getElementById('confirmationModal').style.display = 'none';
    }
</script>
</body>
</html>
