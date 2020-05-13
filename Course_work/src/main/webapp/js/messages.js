var webSocket;
function connect() {
    // webSocket = new WebSocket('ws://localhost:8080/chat');
    webSocket = new SockJS("http://localhost:8080/chat");
    // document.cookie = 'X-Authorization=' + '12345' + ';path=/';

    webSocket.onmessage = function receiveMessage(response) {
        let data = response['data'];
        let json = JSON.parse(data);
        $('#messagesList').first().after("<li>" + json['sender'] + ' ' + json['message'] + "</li>")
    }
}

function sendMessage(text, pageId) {
    let message = {
        "message": text,
        "sender": pageId
    };

    webSocket.send(JSON.stringify(message));
}