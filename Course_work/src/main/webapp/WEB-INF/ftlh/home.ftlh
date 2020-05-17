<#import "spring.ftl" as spring />
<!doctype html>
<html lang="ru">
<head>
    <meta content="text/html" charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title><@spring.message 'page.home.name'/></title>

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        .carousel-item {
            height: 32rem;
        }

        .carousel-item > img {
            position: absolute;
            top: 0;
            left: 0;
            min-width: 100%;
            height: 32rem;
        }
    </style>
</head>
<body>
<#include "header.ftlh">
<main>
    <br class="col-md">
    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleControls" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleControls" data-slide-to="1"></li>
            <li data-target="#carouselExampleControls" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active" data-interval="10000">
                <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg"
                     preserveAspectRatio="xMidYMid slice" focusable="false" role="img">
                    <rect width="100%" height="100%" fill="#6f6f6f"/>
                </svg>
                <div class="container">
                    <div class="carousel-caption text-left">
                        <h1>Площадка для курсов по языку джава</h1>
                        <p>Простой проект площадка для курсов по джаве с внутренним компилятором и возможностью
                            создавать свои курсы в конструкторе</p>
                        <p><a class="btn btn-lg btn-dark" href="/register" role="button">Присоединиться</a></p>
                    </div>
                </div>
            </div>
            <div class="carousel-item" data-interval="10000">
                <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg"
                     preserveAspectRatio="xMidYMid slice" focusable="false" role="img">
                    <rect width="100%" height="100%" fill="#6f6f6f"/>
                </svg>
                <div class="container">
                    <div class="carousel-caption text-left">
                        <h1>Самый популярный курс</h1>
                        <p>Тут будет описание курса</p>
                        <p><a class="btn btn-lg btn-dark" href="#" role="button">Перейти к курсу</a></p>
                    </div>
                </div>
            </div>
            <div class="carousel-item" data-interval="10000">
                <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg"
                     preserveAspectRatio="xMidYMid slice" focusable="false" role="img">
                    <rect width="100%" height="100%" fill="#6f6f6f"/>
                </svg>
                <div class="container">
                    <div class="carousel-caption text-left">
                        <h1>Песочница</h1>
                        <p>Попробуйте среду разработки прямо в браузере</p>
                        <p><a class="btn btn-lg btn-dark" href="/sandbox" role="button">Перейти</a></p>
                    </div>
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
            <span class="carousel-control-next-icon"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>

    </br>
    </br>
    </br>
    </br>
    </br>
    </br>
    <div class="container">
        <div class="col-md-10 mx-auto text-dark">
            Большое описание
        </div>
    </div>
</main>
</body>
</html>