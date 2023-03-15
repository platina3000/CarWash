<!DOCTYPE html>
<html>
    <head>
        <title>CAR WASH</title>
    </head>
    <body>
    <h1>АВТОМОЙКА "ПОЦЕЛУЙ-КА"</h1><hr>

    <h4>Авторизация</h4>
        <form action="/login" method="post">
            EMAIL:<input type="email" name="username"><br><br>
            ПАРОЛЬ:<input type="password" name="password"><br><br>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Вход"/>
        </form>

    <a href="/registration">ЗАРЕГИСТРИРОВАТЬСЯ</a>
</body>
</html>