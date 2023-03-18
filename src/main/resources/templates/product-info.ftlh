<!DOCTYPE html>
<html>
<head>
    <title>CAR WASH</title>
</head>
<body>
<h1>АВТОМОЙКА "ПОЦЕЛУЙ-КА"</h1><hr>



<h4>Информация об услуге</h4>
<b>Название:</b> ${product.title}<br>
<b>Описание:</b> ${product.description}<br>
<b>Цена:</b> ${product.price}$<br>
<hr>
<table border="1">
    <tr>
        <th>Email</th>
        <th>Номер телефона</th>
    </tr>
<#list records as record>
    <tr>
                <th> <form action="/user/enroll/${record.id}/${product.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" value="ЗАПИСАТЬСЯ"/>
                    </form></th>
        <th>${record.getStrDate()}</th>
    </tr>
<#else>
    <h3>ЗАПИСИ НЕТ</h3>
</#list>
</table>


</body>
</html>