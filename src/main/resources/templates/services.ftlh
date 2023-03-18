<!DOCTYPE html>
<html>
<head>
    <title>CAR WASH</title>
</head>
<body>
<h1>АВТОМОЙКА "ПОЦЕЛУЙ-КА"</h1><hr>

<#if user.email??>
    <h3>Пользователь: <a href="/user">${user.name}</a></h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выход"/>
    </form>
    <#if user.isAdmin()>
        <a href="/admin"> Панель администратора</a>
    </#if>
<#else>
    <a href="/login">Войти</a>
</#if>



<form action="/" method="get">
    Поиск по названию услуги: <input type="text" name="title" value="${search}"><br>
    <input type="submit" value="Поиск"/>
</form>

<table border="1">
    <tr>
        <th>ТИП</th>
        <th>НАЗВАНИЕ</th>
        <th>ОПИСАНИЕ</th>
        <th>ЦЕНА</th>


    </tr>
    <#list products as product>
        <tr>
            <th>${product.type}</th>
            <th><a href="/product/${product.id}">${product.title}</a></th>
            <th>${product.description}</th>
            <th>${product.price}$</th>
        </tr>
    <#else>
        <h3>УСЛУГ НЕТ</h3>
    </#list>
</table>




</body>
</html>
