<#import "spring.ftl" as spring />
<html>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark   ">
        <span class="navbar-brand mb-0 h1">Kokie-to kursi</span>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/"><@spring.message 'header.main'/><span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/courses"><@spring.message 'header.courses'/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/create/list"><@spring.message 'header.edit.page'/></a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto">
            </ul>
        </div>
    </nav>
</header>
<br>
<br>
<br>
</body>
</html>
