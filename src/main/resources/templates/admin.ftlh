<#import "parts/common.ftlh" as c>

<@c.page>


    <h4>Панель администратора</h4>
    <hr>

    <div class="card text-center ">
        <div class="card-header ">
            <ul class="nav nav-tabs card-header-tabs">
                <li class="nav-item">
                    <a class="nav-link active" role="tab" data-toggle="tab" href="#s">Услуги</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" role="tab" data-toggle="tab" href="#u">Пользователи</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " role="tab" data-toggle="tab" href="#a">Администрация</a>
                </li>
            </ul>
        </div>
        <div class="tab-content ">
            <div role="tabpanel1 "  class="tab-pane mt-4 mb-4 " id="u">
                <div class="container ">
                    <div class="row justify-content-center">
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
                                <#if !user.isAdmin()>
                                    <tr>
                                        <th>${user.email}</th>
                                        <th>${user.phoneNumber}</th>
                                        <th><#if user.active>true<#else>false</#if></th>
                                        <th><#list user.roles as role>${role}</#list></th>
                                        <th>
                                            <form action="/admin/user/ban/${user.id}" method="post">
                                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                                <#if user.active>
                                                    <input type="submit" value="Бан">
                                                <#else >
                                                    <input type="submit" value="Разблокировать">
                                                </#if>
                                            </form>
                                        </th>
                                        <th>
                                            <a href="/admin/user/edit/${user.id}">Редактировние</a>
                                        </th>
                                        <th><a href="/admin/user/${user.id}">Подробная информация</a></th>
                                    </tr>
                                </#if>
                            <#else>
                                <h3>Пользователей нет</h3>
                            </#list>
                        </table>
                    </div>
                </div>

            </div>
            <div role="tabpanel1" class="tab-pane active mt-4 mb-4" id="s">
                <div class="container ">
                    <div class="row justify-content-center">

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
                                    <th>

                                        <form action="/admin/product/available/${product.id}" method="post">
                                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                                            <#if product.available>
                                                <input type="submit" value="Доступна">
                                            <#else >
                                                <input type="submit" value="Недоступна">
                                            </#if>

                                        </form>
                                    </th>


                                </tr>
                            <#else>
                                <h3>УСЛУГ НЕТ</h3>
                            </#list>
                        </table>
                    </div>
                    <hr>
                    <h4>Запись открыта до: ${lastDate}</h4>
                    <form action="/admin/product/record" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" value="Добавить запись на 7 дней">
                    </form>
                    <hr>
                    <h3> Добавить услугу</h3>

                    <form action="/admin/create" method="post">
                        <div class="form-row ">
                            <div class="col  mb-3">
                                <label for="validationDefault01">Название услуги</label>
                                <input type="text" class="form-control" id="validationDefault01"
                                       placeholder="Название"
                                       name="title" required>
                            </div>
                            <div class="col  mb-3">
                                <label for="validationDefault02">Описание услуги:</label>
                                <input type="text" class="form-control" id="validationDefault02"
                                       placeholder="Описание"
                                       name="description" required>
                            </div>

                        </div>
                        <div class="form-row justify-content-center">

                            <div class="col col-lg-2 mb-3">
                                <label for="validationDefaultUsername">Стоимость</label>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroupPrepend2">$</span>
                                    </div>
                                    <input type="number" class="form-control" id="validationDefault03"
                                           placeholder="Цена" name="price" aria-describedby="inputGroupPrepend2"
                                           required>
                                </div>
                            </div>

                            <div class="col col-lg-2 mb-3">
                                <label for="inputState">Тип</label>
                                <select name="type" id="inputState" class="form-control" required>

                                    <option value="1">Мойка</option>
                                    <option value="2">Чистка</option>
                                    <option value="3">Другое</option>
                                </select>
                            </div>

                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button class="btn btn-primary btn-block" type="submit">Добавить услугу</button>
                    </form>


                </div>
            </div>

            <div role="tabpanel1" class="tab-pane mt-4 mb-4" id="a">
                <div class="container ">
                    <div class="row justify-content-center">
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
                                <#if user.isAdmin()>
                                    <tr>
                                        <th>${user.email}</th>
                                        <th>${user.phoneNumber}</th>
                                        <th><#if user.active>true<#else>false</#if></th>
                                        <th><#list user.roles as role>${role}</#list></th>
                                        <th>
                                            <#if  admin.id!=user.id && !user.isSuperAdmin()>
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
                                        <th>

                                            <#if  admin.isSuperAdmin()==true>
                                                <a href="/admin/user/edit/${user.id}">Редактировние</a>
                                            <#else> <#if  user.isSuperAdmin()==false>
                                                <a href="/admin/user/edit/${user.id}">Редактировние</a>
                                            </#if>
                                            </#if>


                                        </th>
                                        <th><a href="/admin/user/${user.id}">Подробная информация</a></th>


                                    </tr>
                                </#if>
                            </#list>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div>








</@c.page>