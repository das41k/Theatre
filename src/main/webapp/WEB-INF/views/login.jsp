<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Armen
  Date: 16.05.2025
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Theatre</title>
    <link rel="stylesheet" type="text/css" href="/theatre/login.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600;700&display=swap" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2>Пожалуйста, войдите в систему</h2>

    ${not empty error ? '<div class="error-message">'.concat(error).concat('</div>') : ''}

    <form:form action="${pageContext.request.contextPath}/login-client" modelAttribute="client" method="POST">
        <input type="hidden" name="loginByPhone" value="false" id="loginByPhone">

        <div class="toggle-group">
            <input type="checkbox" id="loginTypeToggle" onchange="toggleLoginField()" />
            <label for="loginTypeToggle">Войти по номеру телефона</label>
        </div>

        <div class="form-group" id="emailField">
            <label>Введите почту</label>
            <form:input path="email" type="email" id="emailInput" required="true"/>
            <form:errors path="email" cssClass="error"/>
        </div>

        <div class="form-group" id="phoneField" style="display:none;">
            <label>Введите номер телефона</label>
            <form:input path="phone" type="tel" id="phoneInput" required="false"/>
            <form:errors path="phone" cssClass="error"/>
        </div>

        <div class="form-group">
            <label>Введите пароль</label>
            <form:input path="password" type="password" required="true"/>
            <form:errors path="password" cssClass="error"/>
        </div>

        <input type="submit" value="Вход" class="login-button">
    </form:form>

    <script>
        window.onload = function() {
            toggleLoginField(); // Инициализируем форму
        };
        function toggleLoginField() {
            const toggle = document.getElementById('loginTypeToggle');
            const loginByPhone = document.getElementById('loginByPhone');
            const emailField = document.getElementById('emailField');
            const phoneField = document.getElementById('phoneField');
            const emailInput = document.getElementById('emailInput');
            const phoneInput = document.getElementById('phoneInput');

            if (toggle.checked) {
                emailField.style.display = 'none';
                phoneField.style.display = 'block';
                emailInput.required = false;
                phoneInput.required = true;
                loginByPhone.value = "true";
            } else {
                emailField.style.display = 'block';
                phoneField.style.display = 'none';
                emailInput.required = true;
                phoneInput.required = false;
                loginByPhone.value = "false";
            }
        }
    </script>

    <div class="register-link">
        <h3>Еще не зарегистрированы?</h3>
        <button type="button" onclick="window.location.href='<c:url value="/register"/>'">
            Перейти к регистрации
        </button>
    </div>
</div>
</body>
</html>
