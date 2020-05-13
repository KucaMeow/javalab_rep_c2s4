<!doctype html>
<html lang="en">
<head>
    <title>Edit code task</title>
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
        <form method="post" action="/edit/code-task/${codeTask.id}" id="form">
            <label>
                Описание задания:
                <textarea rows="5" cols="150" name="text">${codeTask.text}</textarea>
            </label>
            <input type="hidden" name="inputs" id="in_array">
            <input type="hidden" name="outputs" id="out_array">
            <table class="table">
                <thead>
                <tr>
                    <th>Входные данный</th>
                    <th>Выходные данные</th>
                    <th>Кнопка удаления</th>
                </tr>
                </thead>
                <tbody>
                <#list codeTask.tests as test>
                    <tr id="test_${test_index}">
                        <td scope="row" class="input">${test.input}</td>
                        <td class="output">${test.output}</td>
                        <td>
                            <button type="button" class="btn btn-dark btn-sm" onclick="detete_test('test_${test_index}')">удалить</button>
                        </td>
                    </tr>
                </#list>
                <tr id="end">
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
            <textarea id="input"></textarea>
            <textarea id="output"></textarea>
            <button type="button" class="btn btn-light" onclick="addTest()">добавить тест</button>
            <button type="button" onclick="send()" class="btn btn-success">Сохранить изменения</button>
        </form>
    </div>
</div>
<script>
    var index = ${codeTask.tests?size};
    function detete_test(id) {
        document.getElementById(id).remove();
    }

    function addTest() {
        $('#end').before(
            "<tr id=\"test_" + index + "\">\n" +
            "                        <td scope=\"row\" class=\"input\">" + $('#input').val() + "</td>\n" +
            "                        <td class=\"output\">" + $('#output').val() + "</td>\n" +
            "                        <td>\n" +
            "                            <button type=\"button\" class=\"btn btn-dark btn-sm\" onclick=\"detete_test('test_" + index + "')\">удалить</button>\n" +
            "                        </td>\n" +
            "                    </tr>"
        );
    }
    function send() {
        let inp = [];
        let out = [];
        let inputs = document.getElementsByClassName("input");
        let outputs = document.getElementsByClassName("output");
        for(e of inputs) {
            inp.push(e.innerText)
        }
        for(e of outputs) {
            out.push(e.innerText)
        }
        $('#in_array').val(inp);
        $('#out_array').val(out);
        $('#form').submit();
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