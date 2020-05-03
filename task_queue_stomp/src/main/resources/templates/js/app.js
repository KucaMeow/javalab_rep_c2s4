var stompClient;

function connect() {
    var socket = new SockJS("http://localhost:600/stomp");

    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/receive/got', function (message) {
            $('#tasks').first().after('<div class="alert alert-warning alert-dismissible fade show" role="alert">\n' +
                '            <button type="button" class="close" data-dismiss="alert" aria-label="Close">\n' +
                '                <span aria-hidden="true">&times;</span>\n' +
                '            </button>\n' +
                '            <strong>Added task ' + message + '</strong>\n' +
                '        </div>');
        });
    })
}

function sendTask() {
    let message = {
        "messageId":"",
        "command":"receive",
        "queueName":document.getElementById('queue_send').value
    };
    stompClient.send("/send/newtask", {}, JSON.stringify(message));
}

function subscribe() {
    stompClient.subscribe('/receive/' + document.getElementById('queue_sub').value, function (response) {
        let message = JSON.parse(response);
        $('#ready_tasks').first().after("<li>Task in " + message.queueName + " queue with id " + message.messageId + " is completed</li>");
    });
}