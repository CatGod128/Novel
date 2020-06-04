<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h2 id = "counter">当前连接数目为</h2>
<input id="message" />
</body>
<script>
    var socket;
    var id='${cid}';    // 通过'${cid}'来取得前端传来的cid值
    if(typeof(WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    }else{
        console.log("您的浏览器支持WebSocket");
        //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
        socket = new WebSocket("ws://127.0.0.1:8090/websocket/"+id);    // 建立连接
        //打开事件
        socket.onopen = function() {
            console.log("Socket 已打开");
        };
        //获得消息事件
        socket.onmessage = function(msg) {
            console.log(msg.data);      // 将数据显示到console
            //发现消息进入    开始处理前端触发逻辑
            var x = document.getElementById("message");
            x.value = msg.data;         // 将数据显示到前端
        };
        //关闭事件
        socket.onclose = function() {
            console.log("Socket已关闭");
        };
        //发生了错误事件
        socket.onerror = function() {
            alert("Socket发生了错误");
            //此时可以尝试刷新页面
        }
    }
</script>
</html>