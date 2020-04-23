var webSocket;
function connect(roomId, username) {
    webSocket = new SockJS("http://localhost:8080/sockjs");



    webSocket.onmessage = function receiveMessage(response) {
        let data = response['data'];
        let json = JSON.parse(data);
        $('#messagesList').first().after("<li>" + json['from'] + ' ' + json['text'] + "</li>")
    }
    webSocket.onclose = function close() {
        webSocket.close();
        window.location.href = "http://localhost:8080/login";
    }

    $('#content').html(
        '<label for="message">Текст сообщения</label>\n' +
        '    <input name="message" id="message" placeholder="Сообщение">\n' +
        '    <button onclick="sendMessage(' + roomId + ', $(\'#message\').val(), \'' + username + '\')">Отправить</button>\n' +
        '    <h3>Сообщения</h3>\n' +
        '    <ul id="messagesList">\n' +
        '\n' +
        '    </ul>'
    );
}

function sendMessage(roomId, text, pageId) {
    let message = {
        "roomId": roomId,
        "text": text,
        "from": pageId
    };

    webSocket.send(JSON.stringify(message));
}

