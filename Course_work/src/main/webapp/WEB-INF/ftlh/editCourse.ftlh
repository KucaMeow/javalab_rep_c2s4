<!doctype html>
<html lang="en">
<head>
    <title>Edit Course</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<#include "header.ftlh">
<form action="/edit/course/${course.id}" method="post">
    <div class="form-group">
        <label for="name">Название</label>
        <input type="text" class="form-control" name="name" id="name" aria-describedby="nameHelp"
               placeholder="name" value="${course.name}">
        <small id="nameHelp" class="form-text text-muted">Название курса</small>
    </div>
    <div class="form-group">
        <label for="desc">Описание</label>
        <input type="text" class="form-control" name="desc" id="desc" aria-describedby="descHelp"
               placeholder="Description" value="${course.description}">
        <small id="descHelp" class="form-text text-muted">Описание курса</small>
    </div>
    Author: ${course.author}<br>
    <button type="submit" class="btn btn-success">Сохранить</button>
</form>
<br>
<ul>
    <b>Уроки курса:</b>
    <br>
    <#list course.getLessons() as lesson>
        <li>
            ${lesson.id}   <a href="/edit/lesson/${lesson.id}">Редактировать</a>
        </li>
    </#list>
    <li>
        <form action="/create/lesson" method="post">
            <input type="hidden" name="courseId" value="${course.id}">
            <button type="submit" class="btn btn-success">Добавить</button>
        </form>
    </li>
</ul>

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