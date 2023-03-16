<!DOCTYPE html>
<html>
<head>
    <title>CAR WASH</title>
</head>
<body>
<h1>АВТОМОЙКА "ПОЦЕЛУЙ-КА"</h1><hr>

<h4>ПОЛЬЗОВАТЕЛЬ ${user.name}</h4>
<hr>
<b>EMAIL:</b>${user.email}<br>
<b>НОМЕР ТЕЛЕФОНА:</b>${user.phoneNumber}<br>

<hr>
<h4>ВАШИ ЗАПИСИ</h4>
<#list products as product>
    <div>
        <p>${product.product.title}| ${product.product.price} |${product.date}
        </p>
    </div>
<#else >
    <h3>УСЛУГ НЕТ</h3>
</#list>

</body>
</html>