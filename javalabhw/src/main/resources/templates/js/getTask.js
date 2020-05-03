var socket;

function subscribe() {
    socket = new SockJS("http://localhost:600/sub");

    socket.onmessage = function receiveMessage(resp) {
        let data = resp['data'];
        let json = JSON.parse(data);
        $('#ready_tasks').first().after("<li>Task with id " + json.id + " is completed</li>");
    }

    $('#sub_button').text("");
}