var webSocket;

function connect() {
    webSocket = new SockJS("http://localhost:600/javalabqueue");

    webSocket.onmessage = function (response) {
        let data = response['data'];
        let message = JSON.parse(data);
        if(message.command === 'accepted') {
            $('#tasks').first().after('<div class="alert alert-warning alert-dismissible fade show" role="alert">\n' +
                '            <button type="button" class="close" data-dismiss="alert" aria-label="Close">\n' +
                '                <span aria-hidden="true">&times;</span>\n' +
                '            </button>\n' +
                '            <strong>Added task ' + message.messageId + ' in queue ' + message.queueName + '</strong>\n' +
                '        </div>');
        } else if (message.command === 'completed') {
            $('#ready_tasks').first().after("<li>Task in " + message.queueName + " queue with id " + message.messageId + " is completed</li>");
        }
    }
}

function sendTask() {
    let message = {
        "messageId":"",
        "command":"receive",
        "queueName":document.getElementById('queue_send').value
    };
    webSocket.send(JSON.stringify(message));
}

function subscribe() {
    let message = {
        "messageId":"",
        "command":"subscribe",
        "queueName":document.getElementById('queue_sub').value
    };
    webSocket.send(JSON.stringify(message));
}