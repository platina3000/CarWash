<#import "parts/common.ftlh" as c>

<@c.page>



<h4>Информация об услуге</h4>
<b>Название:</b> ${product.title}<br>
<b>Описание:</b> ${product.description}<br>
<b>Цена:</b> ${product.price}$<br>
<hr>
<table border="1">
    <tr>
        <th>Email</th>
        <th>Номер телефона</th>
        <th>Дата</th>

    </tr>
    <#list records as record>
        <tr>
            <th><#if record.user??>${record.user.email}<#else>none</#if></th>
            <th><#if record.user??>${record.user.phoneNumber}<#else>none</#if></th>
            <th>${record.getStrDate()}</th>
        </tr>
    <#else>
        <h3>ЗАПИСИ НЕТ</h3>
    </#list>
</table>


</@c.page>