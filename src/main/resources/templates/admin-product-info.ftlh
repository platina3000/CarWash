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

                <th><#if record.user??>${record.user.phoneNumber}<#else>none</#if></th>



            <th>${record.getStrDate()}</th>
        </tr>
    <#else>
        <h3>ЗАПИСИ НЕТ</h3>
    </#list>
</table>


</body>
</html>