<!DOCTYPE html>
<html>

    <head>
        <title>CAR WASH</title>
    </head>
<body>
<h1>АВТОМОЙКА "ПОЦЕЛУЙ-КА"</h1><hr>

<h4>РЕДАКТИРОВАНИЕ ПОЛЬЗОВАТЕЛЯ ${user.name}</h4>
<hr>
<b>EMAIL:</b>${user.email}<br>
<b>НОМЕР ТЕЛЕФОНА:</b>${user.phoneNumber}<br>
<hr>
<form action="/admin/user/edit" method="post">
    <#list roles as role >
        <div>
            <label><input type="radio" name="status" value="${role}" ${user.roles?seq_contains(role)?string("checked","")}>${role}</label>
        </div>
    </#list>
    <input type="hidden" name="userId" value="${user.id}">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Сохранить">
</form>
</body>
</html>