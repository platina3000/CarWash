<#import "parts/common.ftlh" as c>

<@c.page>


<h4>Информация об услуге</h4>
<b>Название:</b> ${product.title}<br>
<b>Описание:</b> ${product.description}<br>
<b>Цена:</b> ${product.price}$<br>
<hr>

    <div class="container-fluid d-flex h-100 justify-content-center align-items-center p-0 pb-4 ">
        <form class="form-inline" action="/product/${product.id}" method="get">
            <div class="form-group mx-sm-3 mb-2 shadow">
            <input type="date" class="form-control" id="date" value="${oldDate}" name="date">
            </div>
            <button type="submit" class="btn btn-primary mb-2 shadow">Поиск</button>
        </form>

    </div>

<#if oldDate!="">
    <#if records?size!=0>
        <#if product.available==true>

            <table class="table table-dark">
                <thead>
                <tr>
                    <th scope="col">Запись</th>
                    <th scope="col">Время</th>

                </tr>
                </thead>
                <tbody>
                <#list records as record>
                    <tr>
                        <th> <form action="/user/enroll/${record.id}/${product.id}" method="post">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <input type="submit" value="ЗАПИСАТЬСЯ"/>
                            </form></th>
                        <th>${record.getStrDate()}</th>
                    </tr>

                </#list>
                </tbody>
            </table>
        <#else>
            <div class="container-fluid d-flex  justify-content-center align-items-center ">
            <h3>ЗАПИСИ НЕТ</h3>
            </div>
        </#if>
        <#else>
            <div class="container-fluid d-flex  justify-content-center align-items-center ">
            <h3>ЗАПИСИ НА ЭТУ ДАТУ НЕТ</h3>
            </div>
    </#if>
    <#else >
        <div class="container-fluid d-flex  justify-content-center align-items-center ">
        <h3>ВЫБЕРИТЕ ДАТУ</h3>
        </div>
</#if>













</@c.page>