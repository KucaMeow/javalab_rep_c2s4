<#import "spring.ftl" as spring />
<!doctype html>
<html lang="en">
<head>
    <title><@spring.message 'page.register.name'/></title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<style>
    .error {
        color: brown;
    }
</style>
<#include "header.ftlh">
<main style="padding-top: 6rem">
    <div class="col-3 mx-auto">
        <@spring.bind "registerDto"/>
        <form method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div class="form-group">
                <label for="">Email</label>
<#--                <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelpId" placeholder="">-->
                <@spring.formInput "registerDto.email", "class=form-control"/>
                <@spring.showErrors "<br>","error" />
            </div>
            <div class="form-group">
                <label for=""><@spring.message 'page.password'/></label>
<#--                <input type="password" class="form-control" name="password" id="password" placeholder="">-->
                <@spring.formPasswordInput "registerDto.password", "class=form-control"/>
                <@spring.showErrors "<br>","error" />
            </div>
            <button type="submit" class="btn btn-dark mr-0"><@spring.message 'page.register'/></button>
            <br>
            <br>
            <small class="form-text text-muted"><a href="/login"><@spring.message 'page.login'/></a></small>
        </form>
    </div>
</main>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>