$(function(){
    var $showArea = $('#showArea');
    var intervalIds = [];

    $('#newWebsocket').click(function () {
        var ws = ct();

        var intervalId = setInterval(function () {
            var time = new Date().getTime();
            console.log(time);
            ws.send(time);
        }, 6000);
        intervalIds.push(intervalId);
    });

    function ct() {
        var websocket = null;

        if ('WebSocket' in window) {
            console.log('support websocket');
            websocket = new WebSocket("ws://localhost:8080/websck");
        } else if ('MozWebSocket' in window) {
            console.log('support MozWebSocket');
            websocket = new MozWebSocket("ws://localhost:8080/websck");
        } else {
            websocket = new SockJS('http://localhost:8080/websocket/js')
            console.log('support SockJS');
        }

        websocket.onopen = function (evnt) {
            console.log('open:' + evnt.data);
        };
        websocket.onmessage = function (evnt) {
            console.log('receive callback from server:' + evnt.data);
            $showArea.append(evnt.data + "\n\r");
        };
        websocket.onerror = function (evnt) {
            clearIntervalFunction()
            console.error('onerror:' + evnt.data);
        };
        websocket.onclose = function (evnt) {
            clearIntervalFunction()
            console.log('close:' + evnt.data);
        }
        return websocket;
    }

    function clearIntervalFunction() {
        for(var i = 0; i < intervalIds.length; i++){
            clearInterval(intervalIds[i]);
            console.log('clear intervalId=' + intervalIds[i]);
        }
        intervalId = [];
    }
})