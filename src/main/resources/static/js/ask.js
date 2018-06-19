/**
 * Created by hello on 2018/6/17.
 */
$(function () {
    $("#QueButton").click(function (e) {
        e.preventDefault();
        var key_word=$("#que_Key").val();
        var question=$("#que_Area").val();
        $("#Que_list").append("<li class='nav-item'><a class='nav-link' th:href='@{/track}' th:classappend='${title} == '"+key_word+"' ? 'active' : '''>"+key_word+"</a></li>");
        $("#Que_list").scrollTop($("#Que_list")[0].scrollHeight);
    });
})