<#import "parts/common.ftlh" as c>

<@c.page>


<h4>ПОЛЬЗОВАТЕЛЬ ${user.name}</h4>
<hr>
<b>EMAIL:</b>${user.email}<br>
<b>НОМЕР ТЕЛЕФОНА:</b>${user.phoneNumber}<br>


<h3>ВАШИ ЗАПИСИ</h3>
<hr>
<h4>Запланированные</h4>
<table border="1">
    <tr>
        <th>НАЗВАНИЕ</th>
        <th>ЦЕНА</th>
        <th>ДАТА</th>
    </tr>

    <#list nextRecords as record>
        <tr>
            <th>${record.product.title}</th>
            <th>${record.product.price}</th>
            <th>${record.getStrDate()}</th>
            <th><form action="/admin/user/record/cancel/${record.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Отменить">
                </form></th>
        </tr>
    <#else >
        <h3>ВЫ НИКУДА НЕ ЗАПИСАНЫ</h3>
    </#list>

</table><br>
<h4>Оконченные(история)</h4>
<table border="1">
    <tr>
        <th>НАЗВАНИЕ</th>
        <th>ЦЕНА</th>
        <th>ДАТА</th>
    </tr>

    <#list previousRecords as record>
        <tr>
            <th>${record.product.title}</th>
            <th>${record.product.price}</th>
            <th>${record.getStrDate()}</th>
            <th><form action="/user/product/rate/${record.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="ОЦЕНИТЬ">
                </form></th>
        </tr>
    <#else >
        <h3>ИСТОРИЯ ПУСТА</h3>
    </#list>
</table>

</@c.page>