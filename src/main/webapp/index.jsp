<!DOCTYPE HTML>
<html>
<head>
    <link href="estilo.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        function WebSocketTest() {
            if ("WebSocket" in window) {

                // Let us open a web socket
                var ws = new WebSocket("ws:localhost:8080/ArduinoSikWebAsync/temperature2");
                ws.onopen = function () {
                    // Web Socket is connected, send data using send()
                    //ws.send("Message to send");
                    //alert("Message is sent en open...");
                };
                ws.onmessage = function (evt) {
                    var received_msg = evt.data;
                    //alert("Message is received " + received_msg);
                    sleep(1000);
                    document.getElementById("temperatureText").innerHTML = received_msg;
                    ws.send(received_msg);
                };
                ws.onclose = function () {
                    // websocket is closed.
                    alert("Connection is closed...");
                };
            }
            else {
                // The browser doesn't support WebSocket
                alert("WebSocket NOT supported by your Browser!");
            }
        }
        function sleep(milliseconds) {
            var start = new Date().getTime();
            for (var i = 0; i < 1e7; i++) {
                if ((new Date().getTime() - start) > milliseconds){
                    break;
                }
            }
        }
    </script>
</head>
<body>
<div id="clientweb">
    <a id="servicelink" href="javascript:WebSocketTest()"> Quiero saber la temperatura! </a>
    <div id="temperatureText">20.0</div>
    <div id="scaleText">&#176;C</div>
</div>
</body>
</html>