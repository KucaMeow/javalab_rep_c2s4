var webSocket;

function connect() {
    webSocket = new SockJS("http://localhost:600/send");

    webSocket.onmessage = function (response) {
        $('#tasks').first().after('<div class="alert alert-warning alert-dismissible fade show" role="alert">\n' +
            '            <button type="button" class="close" data-dismiss="alert" aria-label="Close">\n' +
            '                <span aria-hidden="true">&times;</span>\n' +
            '            </button>\n' +
            '            <strong>Added task ' + JSON.parse(response['data']).id + '</strong>\n' +
            '        </div>');
    }
}

function sendTask() {
    webSocket.send("newTask");
}