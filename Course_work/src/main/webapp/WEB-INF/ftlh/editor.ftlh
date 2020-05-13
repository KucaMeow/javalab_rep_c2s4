<html>
<head>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
</head>
<body>
<div class="container"><div class="row mx-auto"><div id="editor" style="height: 300px; width: 100%">public class Main {
    public static void main(String[] args) {
        //Пиши свой код тут
    }
}</div></div>
    <div class="row">
        <div class="col-11">
            <pre id="result" style="color: darkgreen; font-size: 20px"></pre>
            <pre id="errors" style="color: darkred; font-size: 20px"></pre>
        </div>
        <div class="col">
            <button type="button" class="btn btn-success ml-auto" onclick="x()">Run</button>
        </div>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.4.2/ace.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.4.2/mode-java.js" type="text/javascript"></script>
<script>
    var editor = ace.edit("editor");
    editor.getSession().setMode("ace/mode/java");
    editor.setFontSize(20);
    editor.resize();

    function x() {
        var codeText = editor.getValue();
        $.post("execute",
            {
                code:codeText
            },
            function (resultObj) {
                var text = "";
                var error = "";
                resultObj.results.forEach(function (elem) {
                    text += elem;
                    text += "\n";
                });
                resultObj.errors.forEach(function (elem) {
                    error += elem;
                    error += "\n";
                });
                $('#result').text(text);
                $('#errors').text(error);
            })
    }
</script>
</body>
</html>
