<#import "parts/common.ftlh" as c>

<@c.page>

<div class="container-fluid d-flex h-100 justify-content-center align-items-center p-0 pb-4">
    <form class="form-inline" action="/" method="get">
        <div class="form-group mx-sm-3 mb-2">
            <input type="text" name="title" class="form-control" id="inputPassword2" placeholder="Название" value="${search}">
        </div>

        <button type="submit" class="btn btn-primary mb-2">Поиск</button>
    </form>

</div>

<div class="card-group">
<#list products as product>
    <div style="padding-right: 10px">
    <div class="card  mb-3 shadow  bg-white rounded " style="max-width: 18rem;">

        <div class="card-body ">
            <h5 class="card-title">${product.title}</h5>
            <p class="card-text">${product.description}</p>
        </div>
        <div class="card-footer bg-transparent ml-2 ">${product.price}$ <a href="/product/${product.id}" class="btn btn-primary ml-5">Записаться</a></div>
    </div>
    </div>
<#--    <#else>-->
<#--        <h3>УСЛУГ НЕТ</h3>-->
</#list>
</div>


<#--<table border="1">-->
<#--    <tr>-->
<#--        <th>ТИП</th>-->
<#--        <th>НАЗВАНИЕ</th>-->
<#--        <th>ОПИСАНИЕ</th>-->
<#--        <th>ЦЕНА</th>-->


<#--    </tr>-->
<#--    <#list products as product>-->
<#--        <tr>-->
<#--            <th>${product.type}</th>-->
<#--            <th><a href="/product/${product.id}">${product.title}</a></th>-->
<#--            <th>${product.description}</th>-->
<#--            <th>${product.price}$</th>-->
<#--        </tr>-->
<#--    <#else>-->
<#--        <h3>УСЛУГ НЕТ</h3>-->
<#--    </#list>-->
<#--</table>-->

</@c.page>
