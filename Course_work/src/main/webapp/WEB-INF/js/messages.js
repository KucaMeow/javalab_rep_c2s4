function sendMessage(text) {
    $.post("messages",
        {
            message: text
        },
        function () {
        });
}

function receiveMessage() {
    $.ajax({
        url: "/messages",
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            let html = document.getElementById("messages").innerHTML;
            html += '<li class="list-group-item">' + response[0]['message'] + '</li>';
            $('#messages').html(html);
            receiveMessage();
        }
    })
}

function getAllMessages() {
    $.ajax({
        url: "/allmessages",
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            let html = "";
            response.forEach(function (messageDto) {
                html += '<li class="list-group-item">' + messageDto['message'] + '</li>';
            });
            $('#messages').html(html);
            receiveMessage();
        }
    })
}