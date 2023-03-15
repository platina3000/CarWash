<!DOCTYPE html>
<html>
<head>
    <title>CAR WASH</title>
</head>
<body>
<h1>АВТОМОЙКА "ПОЦЕЛУЙ-КА"</h1>
<hr>

<h4>РЕГИСТРАЦИЯ</h4>


<form action="/registration" method="post">
    ФИО:<input type="text" name="name"><br><br>
    EMAIL:<input type="email" name="email"><br><br>
    НОМЕР ТЕЛЕФОНА:<input type="text" name="phoneNumber"><br><br>
    ПАРОЛЬ:<input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Зарегистрироваться"/>

</form>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>