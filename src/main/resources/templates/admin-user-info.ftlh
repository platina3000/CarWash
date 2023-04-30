<#import "parts/common.ftlh" as c>

<@c.page>


    <h3>Аккаунт</h3>
    <hr>

    <b>ФАМИЛИЯ:</b>${user.lastname}<br>
    <b>ИМЯ:</b>${user.name}<br>
    <b>ОТЧЕСТВО:</b>${user.secondname}<br>
    <b>EMAIL:</b>${user.email}<br>
    <b>НОМЕР ТЕЛЕФОНА:</b>${user.phoneNumber}<br>
    <hr>
    <div class="card text-center ">
        <div class="card-header ">
            <ul class="nav nav-tabs card-header-tabs">
                <li class="nav-item">
                    <a class="nav-link active" role="tab" data-toggle="tab" href="#n">Запланированные</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" role="tab" data-toggle="tab" href="#p">История</a>
                </li>
            </ul>
        </div>
        <div class="tab-content ">
            <div role="tabpanel1 " class="tab-pane active mt-4 mb-4 " id="n">
                <div class="container ">
                    <div class="row justify-content-center">
                    <#if nextRecords?size!=0>
                        <table border="1">
                            <tr>
                                <th>НАЗВАНИЕ</th>
                                <th>ЦЕНА</th>
                                <th>ДАТА</th>
                            </tr>

                            <#list nextRecords as record>
                                <tr>
                                    <th><a href="/product/${record.product.id}">${record.product.title}</a>
                                    </th>
                                    <th>${record.product.price}</th>
                                    <th>${record.getStrDate()}</th>
                                    <th>
                                        <form action="/user/record/cancel/${record.id}" method="post">
                                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                                            <input type="submit" value="Отменить">
                                        </form>
                                    </th>
                                </tr>

                            </#list>

                        </table>
                                             <#else >
                            <h5>Записи нет</h5>
                        </#if>
                    </div>
                </div>
            </div>
            <div role="tabpanel1" class="tab-pane  mt-4 mb-4" id="p">
                <div class="container ">
                    <div class="row justify-content-center">
                        <#if previousRecords?size!=0>
                        <table border="1">
                            <tr>
                                <th>НАЗВАНИЕ</th>
                                <th>ЦЕНА</th>
                                <th>ДАТА</th>
                            </tr>
                            <#list previousRecords as record>
                                <tr>
                                    <th><a href="/product/${record.product.id}">${record.product.title}</a></th>
                                    <th>${record.product.price}</th>
                                    <th>${record.getStrDate()}</th>
                                </tr>
                            </#list>
                        </table>
                            <#else >
                            <h5>История пуста</h5>
                        </#if>

                    </div>
                </div>
            </div>
        </div>
    </div>

</@c.page>