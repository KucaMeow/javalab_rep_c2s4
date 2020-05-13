<!doctype html>
<html lang="en">
<head>
    <title>Edit checkbox test</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<#include "header.ftlh">
<div class="col-8">
    <div class="container">
        <form method="post" action="/edit/checkbox-test/${checkboxTest.id}" id="form">
            <label>
                Вопрос для теста:
                <textarea rows="5" cols="150" name="question">${checkboxTest.question}</textarea>
            </label>
            <input type="hidden" name="answers" id="answers_array">
            <#list checkboxTest.checkboxTestAnswers as answer>
                <div class="form-check" id="ans_${answer_index}">
                    <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="correctAnswer" id="${answer_index}"
                               value="${answer.answer}" <#if answer.answer == checkboxTest.correctAnswer>checked</#if>>
                        ${answer.answer}
                    </label>
                    <button type="button" class="btn btn-dark btn-sm" onclick="deleteAns('ans_${answer_index}')">удалить</button>
                </div>
            </#list>
            <input type="text" id="new_answer">
            <button type="button" class="btn btn-light" onclick="addAnswer()">добавить ответ</button>
            <button type="button" onclick="send()" class="btn btn-success">Сохранить изменения</button>
        </form>
    </div>
</div>
<script>
    var index = ${checkboxTest.checkboxTestAnswers?size};
    function send() {
        let array = [];
        let elements = document.getElementsByClassName("form-check-input");
        for(element of elements){
            array.push(element['value']);
        }
        $('#answers_array').val(array);
        $('#form').submit();
    }
    function deleteAns(id) {
        document.getElementById(id).remove();
    }
    function addAnswer() {
        $('#new_answer').before(
            "<div class=\"form-check\" id=\"ans_" + index + "\">\n" +
            "                    <label class=\"form-check-label\">\n" +
            "                        <input type=\"radio\" class=\"form-check-input\" name=\"correctAnswer\" id=\"" + index + "\"\n" +
            "                               value=\"" + $('#new_answer').val() + "\">\n" +
            "                        " + $('#new_answer').val() + "\n" +
            "                    </label>\n" +
            "                    <button type=\"button\" class=\"btn btn-dark btn-sm\" onclick=\"deleteAns('ans_" + index + "')\">удалить</button>\n" +
            "                </div>"
        );
        index++;
    }
</script>
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