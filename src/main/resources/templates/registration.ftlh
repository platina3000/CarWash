<#import "parts/common.ftlh" as c>

<@c.page>

    <div class="container-fluid d-flex h-100 justify-content-center align-items-center p-0">

    <div class="row shadow p-3 mb-5 bg-white rounded">

    <div class="col  p-4">
        <h3 class="text-center mb-4">Регистрация</h3>
    <form action="/registration" method="post">
        <div class="form-row">
            <div class="col-md-4 mb-3">
                <label for="validationDefault01">First name</label>
                <input type="text" class="form-control" id="validationDefault01" placeholder="First name" name="name" required>
            </div>
            <div class="col-md-4 mb-3">
                <label for="validationDefault02">Last name</label>
                <input type="text" class="form-control" id="validationDefault02" placeholder="Last name"   required>
            </div>
            <div class="col-md-4 mb-3">
                <label for="validationDefaultUsername">Username</label>
                <input type="email" class="form-control" id="validationDefaultUsername" placeholder="Email" name="email" required>
            </div>
        </div>
        <div class="form-row">
            <div class="col-md-6 mb-3">
                <label for="validationDefault03">Phone</label>
                <input type="text" class="form-control" id="validationDefault03" name="phoneNumber" placeholder="phoneNumber" required>
            </div>
            <div class="col-md-3 mb-3">
                <label for="validationDefault04">Password</label>
                <input type="password" class="form-control"  name="password" id="validationDefault04" placeholder="Password" required>
            </div>
            <div class="col-md-3 mb-3">
                <label for="validationDefault05">Zip</label>
                <input type="text" class="form-control" id="validationDefault05" placeholder="Zip" required>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button class="btn btn-primary btn-block" type="submit">Зарегистрироваться</button>
    </form>

    </div></div></div>


<#--<form action="/registration" method="post">-->
<#--    ФИО:<input type="text" name="name"><br><br>-->
<#--    EMAIL:<input type="email" name="email"><br><br>-->
<#--    НОМЕР ТЕЛЕФОНА:<input type="text" name="phoneNumber"><br><br>-->
<#--    ПАРОЛЬ:<input type="password" name="password"><br><br>-->
<#--    <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--    <input type="submit" value="Зарегистрироваться"/>-->

<#--</form>-->
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</@c.page>