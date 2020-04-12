function sendMessage(text) {
    let body = {
        message: text
    };

    $.ajax({
        url: "/messages",
        method: "POST",
        data: JSON.stringify(body),
        contentType: "application/json",
        dataType: "json",
        complete: function () {
            if (text === 'asdawdwafg32tr31twdfyg172ry2g1uoygr67df') {
                receiveMessage()
            }
        }
    });
}

function receiveMessage() {
    $.ajax({
        url: "/messages",
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            if(response[0]['text'] !== 'asdawdwafg32tr31twdfyg172ry2g1uoygr67df') {
                $('#messages').first().after('<li>' + response[0]['text'] + '</li>');
            }
            receiveMessage();
        }
    })
}