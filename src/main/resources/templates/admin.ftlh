<!DOCTYPE html>
<html>
<head>
    <title>CAR WASH</title>
</head>
<body>
<h1>АВТОМОЙКА "ПОЦЕЛУЙ-КА"</h1><hr>


<h4>Панель администратора</h4>
<hr>
<table border="1">
    <tr>
        <th>Email</th>
        <th>Номер телефона</th>
        <th>Активность</th>
        <th>Роли</th>
        <th>Бан</th>
        <th>Редактирование</th>
        <th>Подробная информация</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.email}</th>
            <th>${user.phoneNumber}</th>
            <th><#if user.active>true<#else>false</#if></th>
            <th><#list user.roles as role>${role}</#list></th>
            <th>
                <#if  admin.id!=user.id>
                    <form action="/admin/user/ban/${user.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <#if user.active>
                            <input type="submit" value="Бан">
                            <#else >
                                <input type="submit" value="Разблокировать">
                        </#if>

                    </form>

                </#if>

            </th>
            <th><a href="/admin/user/edit/${user.id}">Редактировние</a></th>
            <th><a href="/user/${user.id}">Подробная информация</a></th>


        </tr>
    <#else>
        <h3>Пользователей нет</h3>
    </#list>
</table>

<hr>


<form action="/admin" method="get">
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
            <th><a href="/admin/product/${product.id}">${product.title}</a></th>
            <th>${product.description}</th>
            <th>${product.price}$</th>
            <th><form action="/admin/product/delete/${product.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Удалить">
                </form>
            </th>
            <th>${product.lastDate}</th>

        </tr>
    <#else>
        <h3>УСЛУГ НЕТ</h3>
    </#list>
</table>

<hr>
<h4>Запись открыта до: ${lastDate}</h4>
<form action="/admin/product/record" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Добавить запись на 7 дней">
    </form>
<hr>
<h3> Добавить услугу</h3>
<form action="/admin/create" method="post">
    Название услуги: <input type="text" , name="title"/><br><br>
    Описание услуги: <input type="text" , name="description"/><br><br>
    Тип услуги: <input type="number" , name="type"/><br><br>
    Цена: <input type="number" , name="price"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить услугу"/>
</form>

</body>
</html>