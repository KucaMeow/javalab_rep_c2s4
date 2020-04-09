<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <title>Test</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
</head>
<body>
<jsp:include page="header.jsp"/>
<main style="padding-top: 6rem">
    <div id="my_alerts" class="container">
        <%--    Error alert--%>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>Не правильно(</strong>
        </div>
        <%--    --%>
        <%--    Success alert--%>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>Правильно(</strong>
        </div>
        <%--    --%>
    </div>
    <div id="carouselId" class="carousel slide" data-ride="false" data-wrap="false">
        <ol class="carousel-indicators">
            <li data-target="#carouselId" data-slide-to="0" class="active"></li>
            <li data-target="#carouselId" data-slide-to="1"></li>
            <li data-target="#carouselId" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
            <div class="carousel-item active">
                <div class="container">
                <pre id="task" style="font-size: 20px">
                    Тут будет текст задания
                </pre>
                </div>
                <jsp:include page="editor.jsp"/>
            </div>
            <div class="carousel-item">
                <img data-src="holder.js/900x500/auto/#666:#444/text:Second slide" alt="Second slide">
            </div>
            <div class="carousel-item">
                <img data-src="holder.js/900x500/auto/#666:#444/text:Third slide" alt="Third slide">
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
</main>
<button type="button" class="btn btn-primary" onclick="alertF()">BUTTON</button>
<script>
    document.getElementById("errors").onclick = function(){
        alertF();
    };
    function alertF() {
        document.getElementById("my_alerts").innerHTML += "<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button><strong>Не правильно(</strong></div>";
    }
</script>

</body>
</html>