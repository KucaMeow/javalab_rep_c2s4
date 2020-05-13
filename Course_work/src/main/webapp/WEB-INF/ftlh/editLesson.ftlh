<!doctype html>
<html lang="en">
<head>
    <title>Edit Lesson</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<#include "header.ftlh">
<main>
    <div id="carouselId" class="carousel slide" data-ride="false" data-wrap="false" data-interval="false">
        <ol class="carousel-indicators">
            <#list lesson.courseModules as module>
                <#if module.orderNumber == 0>
                    <li data-target="#carouselId" data-slide-to="0" class="active"></li>
                <#else>
                    <li data-target="#carouselId" data-slide-to="${module.orderNumber}"></li>
                </#if>
            </#list>
            <#if lesson.courseModules?size == 0>
                <li data-target="#carouselId" data-slide-to="${lesson.courseModules?size}" class="active"></li>
            <#else>
                <li data-target="#carouselId" data-slide-to="${lesson.courseModules?size}"></li>
            </#if>
        </ol>
        <div class="carousel-inner" role="listbox">
            <#list lesson.courseModules as module>
            <#if module_index == 0>
            <div class="carousel-item overflow-auto active">
                <#else>
                <div class="carousel-item overflow-auto">
                    </#if>
                    <#if module.moduleType.name() == "CodeTask">
                        <div class="container-fluid">
                            <div class="col-8 mx-auto">
                                <div class="container">
                <pre id="codetask_task_${module.codeTask.id}">
                    ${module.codeTask.text}
                </pre>
                                </div>
                                <br>
                                <h1>Здесь будет редактор кода</h1><br>
                                <form action="/edit/module/${module.moduleType.name()}/${module.id}">
                                    <button type="submit" class="btn btn-success">Редактировать</button>
                                </form>
                            </div>
                        </div>
                    </#if>
                    <#if module.moduleType.name() == "LearningText">
                        <div class="col-8 mx-auto">
                            <div class="container">
                <pre id="learning_text_${module.learningText.id}">
                    ${module.learningText.text}
                </pre>

                                <form action="/edit/module/${module.moduleType.name()}/${module.id}">
                                    <button type="submit" class="btn btn-success">Редактировать</button>
                                </form>
                            </div>
                        </div>
                    </#if>
                    <#if module.moduleType.name() == "CheckboxTest">
                        <div class="container-fluid">
                            <div class="col-8 mx-auto">
                                <div class="container">
                <pre id="checkbox_test_question_${module.checkboxTest.id}">
                    ${module.checkboxTest.question}
                </pre>
                                    <form id="checkbox_test_form_${module.checkboxTest.id}">
                                        <#list module.checkboxTest.checkboxTestAnswers as answer>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="checkboxAnswer"
                                                       id="answer_${module.checkboxTest.id}_${answer.id}"
                                                       value="${answer.answer}">
                                                <label class="form-check-label"
                                                       for="answer_${module.checkboxTest.id}_${answer.id}">
                                                    ${answer.answer}
                                                </label>
                                            </div>
                                        </#list>
                                    </form>
                                    <form action="/edit/module/${module.moduleType.name()}/${module.id}">
                                        <button type="submit" class="btn btn-success">Редактировать</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </#if>
                </div>
                </#list>
                <#--            ADD-->
                <#if lesson.courseModules?size == 0>
                <div class="carousel-item overflow-auto active">
                    <#else>
                    <div class="carousel-item overflow-auto">
                        </#if>
                        <div class="container-fluid">
                            <div class="col-8 mx-auto">
                                <div class="container">
                                    <form method="post" action="/create/text-lesson">
                                        <input type="hidden" name="lessonId" value="${lesson.id}">
                                        <button type="submit" class="btn btn-primary">Добавить конспект</button>
                                    </form>
                                    <br>
                                    <form method="post" action="/create/checkbox-test">
                                        <input type="hidden" name="lessonId" value="${lesson.id}">
                                        <button type="submit" class="btn btn-primary">Добавить checkbox тест</button>
                                    </form>
                                    <br>
                                    <form method="post" action="/create/code-task">
                                        <input type="hidden" name="lessonId" value="${lesson.id}">
                                        <button type="submit" class="btn btn-primary">Добавить тест с программированием
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselId" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselId" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
            <footer class="container">
                <small>
                    Описание курса <br>
                    ${lesson.course.description}
                </small>
            </footer>
</main>
<link rel="stylesheet" href="/static/css/lesson_page.css" type="text/css">
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