<#import "spring.ftl" as spring />
<!doctype html>
<html lang="en">
<head>
    <title><@spring.message 'page.lesson.name'/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.4.2/ace.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.4.2/mode-java.js" type="text/javascript"></script>
</head>
<body>
<#include "header.ftlh">
<div id="my_alerts" class="container">
</div>
<#if lesson.courseModules?size == 0>
    <div class="container col-8 mx-auto">
        <h2><@spring.message 'page.lesson.empty'/></h2>
    </div>
<#else >
    <div id="carouselId" class="carousel slide" data-ride="false" data-wrap="false" data-interval="false">
        <ol class="carousel-indicators">
            <#list lesson.courseModules as module>
                <#if module.orderNumber == 0>
                    <li data-target="#carouselId" data-slide-to="0" class="active"></li>
                <#else>
                    <li data-target="#carouselId" data-slide-to="${module.orderNumber}"></li>
                </#if>
            </#list>
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
                <pre>
                    ${module.codeTask.text}
                </pre>
                                    <div class="container">
                                        <div class="row mx-auto">
                                            <div id="editor_${module.codeTask.id}" style="height: 300px; width: 100%">public class Main {
    public static void main(String[] args) {
        //Пиши свой код тут
        //Используй массив args, как входные данные
    }
}</div>
                                        </div>
                                        <div class="row">
                                            <div class="col-11">
                                            <pre id="result_${module.codeTask.id}"
                                                 style="color: darkgreen; font-size: 20px"></pre>
                                                <pre id="errors_${module.codeTask.id}"
                                                     style="color: darkred; font-size: 20px"></pre>
                                            </div>
                                        </div>
                                    </div>
                                    <script>
                                        var editor_${module.codeTask.id} = ace.edit("editor_${module.codeTask.id}");
                                        editor_${module.codeTask.id}.getSession().setMode("ace/mode/java");
                                        editor_${module.codeTask.id}.setFontSize(20);
                                        editor_${module.codeTask.id}.resize();
                                    </script>
                                    <button type="button" class="btn btn-success" onclick="codeTest(${module.codeTask.id})">
                                        <@spring.message 'page.lesson.tests.from.server'/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </#if>
                    <#if module.moduleType.name() == "LearningText">
                        <div class="col-8 mx-auto">
                            <div class="container">
                <pre>
                    ${module.learningText.text}
                </pre>
                            </div>
                        </div>
                    </#if>
                    <#if module.moduleType.name() == "CheckboxTest">
                        <div class="container-fluid">
                            <div class="col-8 mx-auto">
                                <div class="container">
                <pre>
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
                                    <button type="button" class="btn btn-success"
                                            onclick="checkboxTest(${module.checkboxTest.id})"><@spring.message 'page.lesson.check'/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </#if>
                </div>
                </#list>
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
    </div>
</#if>
<footer class="container">
    <small>
        <@spring.message 'page.lesson.course.descr'/> <br>
        ${lesson.course.description}
    </small>
</footer>
<script>
    function incorrect() {
        $('#my_alerts').append("<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">\n" +
            "            <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
            "                <span aria-hidden=\"true\">&times;</span>\n" +
            "            </button>\n" +
            "            <strong><@spring.message 'page.lesson.incor.msg'/></strong>\n" +
            "        </div>");
    }

    function correct() {
        $('#my_alerts').append("<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\n" +
            "            <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\n" +
            "                <span aria-hidden=\"true\">&times;</span>\n" +
            "            </button>\n" +
            "            <strong><@spring.message 'page.lesson.cor.msg'/></strong>\n" +
            "        </div>");
    }

    function codeTest(id) {
        let codeText = window["editor_" + id].getValue();
        $.post("/rest/code-test/" + id,
            {
                ${_csrf.parameterName}:${_csrf.token},
                code: codeText
            },
            function (resultObj) {
                if (resultObj['success'] === true) {
                    correct();
                } else {
                    incorrect();
                }
            })

    }

    function checkboxTest(id) {
        let answer = $("#checkbox_test_form_" + id).serializeArray()[0].value;
        $.post("/rest/checkbox-test/" + id,
            {
                ${_csrf.parameterName}:${_csrf.token},
                answer: answer
            },
            function (resultObj) {
                if (resultObj['success'] === true) {
                    correct();
                } else {
                    incorrect();
                }
            })
    }
</script>
<link rel="stylesheet" href="/static/css/lesson_page.css">
</body>
</html>