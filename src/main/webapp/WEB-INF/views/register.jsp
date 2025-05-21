<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Armen
  Date: 16.05.2025
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Theatre</title>
    <link rel="stylesheet" type="text/css" href="/theatre/register.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600;700&display=swap" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2>Пожалуйста, зарегистрируйтесь в системе</h2>
    <form:form action="${pageContext.request.contextPath}/register-client" modelAttribute="client">
        <div class="form-group">
            <label>Фамилия</label>
            <form:input path="lastName" required="true"/>
            <form:errors path="lastName" cssClass="error"/>
        </div>
        <div class="form-group">
            <label>Имя</label>
            <form:input path="firstName" required="true"/>
            <form:errors path="firstName" cssClass="error"/>
        </div>
        <div class="form-group">
            <label>Отчество</label>
            <form:input path="middleName"/>
            <form:errors path="middleName" cssClass="error"/>
        </div>
        <div class="form-group">
            <label>Введите почту</label>
            <form:input path="email" type="email" required="true"/>
            <form:errors path="email" cssClass="error"/>
        </div>
        <div class="form-group">
            <label>Введите номер телефона</label>
            <form:input path="phone" type="tel" required="true"/>
            <form:errors path="phone" cssClass="error"/>
        </div>
        <div class="form-group">
            <label>Введите пароль</label>
            <form:input path="password" type="password" required="true"/>
            <form:errors path="password" cssClass="error"/>
        </div>
        <input type="submit" value="Регистрация" class="register-button">
    </form:form>
    <div class="login-link">
        <h3>Уже зарегистрированы?</h3>
        <button type="button" onclick="window.location.href='<c:url value="/login"/>'">
            Перейти к входу в личный кабинет
        </button>
    </div>
</div>
</body>
</html>
