<#import "parts/common.ftlh" as c>

<@c.page>

    <div class="container-fluid d-flex h-100 justify-content-center align-items-center p-0 pb-4 ">
        <form class="form-inline" action="/" method="get">
            <div class="form-group mx-sm-3 mb-2 shadow">
                <input type="text" name="title" class="form-control" id="inputPassword2" placeholder="Название"
                       value="${search}">
            </div>

            <button type="submit" class="btn btn-primary mb-2 shadow">Поиск</button>
        </form>

    </div>


    <h3>МОЙКА АВТОМОБИЛЯ</h3>
    <div class="row">
        <div class="owl-carousel slide-one owl-theme">
            <#list products1 as product>
                <div class="item ">

                    <div class="card shadow p-3 mb-5 bg-white rounded ">
                        <div class="card-body ">
                            <h5 class="card-title">${product.title}</h5>
                            <p class="card-text">${product.description}</p>
                        </div>
                        <div class="card-footer bg-transparent ml-2 ">${product.price}$ <a
                                    href="/product/${product.id}"
                                    class="btn btn-primary ml-5">Записаться</a>
                        </div>
                    </div>

                </div>

            <#else>
                <h4>УСЛУГ ПОКА НЕТ...</h4>
            </#list>
        </div>
    </div>


    <h3>ДРУГИЕ УСЛУГИ</h3>
    <#if products2?size!=0>
    <div class="row">
        <div class="owl-carousel slide-two owl-theme">
            <#list products2 as product>
                <div class="item ">

                    <div class="card shadow p-3 mb-3 bg-white rounded ">
                        <div class="card-body ">
                            <h5 class="card-title">${product.title}</h5>
                            <p class="card-text">${product.description}</p>
                        </div>
                        <div class="card-footer bg-transparent ml-2 ">${product.price}$ <a
                                    href="/product/${product.id}"
                                    class="btn btn-primary ml-5">Записаться</a>
                        </div>
                    </div>

                </div>


            </#list>


        </div>
    </div>
        <#else >
        <h4>Услуг пока нет...</h4>
    </#if>
    


</@c.page>
