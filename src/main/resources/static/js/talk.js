/**
 * Created by hello on 2018/4/11.
 */
$(function () {

    var name = document.getElementById("title");
    var usename = name.text;

    var target = "ws://localhost:8080/talk/" + usename;
    var ws = new WebSocket(target);
    //开启通道
    ws.onmessage = function (event) {
        eval("var msg=" + event.data + ";");

        if (undefined != msg.welcome) {
            $("#newsList").append("<font class='login_information text-center' size='1'>" + msg.welcome + "</font><br/>");
        }
        if (undefined != msg.usernames) {
            $("#userList").html("");
            $(msg.usernames).each(function () {
                $("#userList").append("<input type='checkbox' value='" + this + "'/>" + this + "<br/>");
            });
        }
        if (undefined != msg.content) {
            $("#newsList").append("<font class='login_information' size='1'>" + gettime() + "</font><br/><li class='news_my'>" + msg.content + "</li><br/><br/>");
        }
    }
    //send后的的事件
    $("#send").click(function () {
        var val = $("#dope").val();
        var size = $("#userList :checked").length;
        //
        var object = null;
        if (size == 0) {
            object = {
                msg: val,
                type: 1//1 广播
            }
        } else {
            var to = $("#userList :checked").val();
            object = {
                to: to,
                msg: val,
                type: 2//私聊
            }
        }
        var str = JSON.stringify(object);
        ws.send(str);
        $("#dope").val("");
    })

    function gettime() {
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth() + 1;//js中是从0开始所以要加1
        var day = date.getDate();
        var hour = date.getHours();
        var minute = date.getMinutes();
        var second = date.getSeconds();
        var presentTime = year + '-' + month + '-' + day + '  ' + hour + ':' + minute;
        return presentTime;
    }
})
