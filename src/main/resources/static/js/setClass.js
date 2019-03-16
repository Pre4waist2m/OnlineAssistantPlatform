/**
 * Created by hello on 2018/7/4.
 */
$(function () {
    var header = $("meta[name='_csrf_header']").attr("content");
    var token = $("meta[name='_csrf']").attr("content");
    $("#ClassSave").click(function () {
        var classInfo = $("#inputEmail3").val();
        $("#inputEmail3").val("");
        var classId = $("#inputPassword3").val();
        $("#inputPassword3").val("");


        if (classId == "" || classInfo == "") {
            alert("请将信息填写完整");
        } else {
            $.ajax({
                type: "POST",
                url: "/OpenClass",
                cache: "false",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token);
                },
                contentType: 'application/x-www-form-urlencoded;',
                dataType: "text",
                data: {
                    "className": classInfo,
                    "classId": classId,
                    "teacherName":$("#title").text()
                }
            });
        }
        location.reload(true);
    });




    /*  $.ajax({
     url:"/courseMangement",
     type:"POST",
     cache:"false",
     beforeSend: function (xhr) {
     xhr.setRequestHeader(header, token);
     },
     contentType: 'application/x-www-form-urlencoded;',
     dataType: "text",
     data:{

     }
     })*/
  /*  $(".delete").click(function () {
       var temp=$(this).attr("id");
       console.log(temp);
       $.ajax({
            url: "/deleteCourse",
            type: "POST",
            cache: "false",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            contentType: 'application/x-www-form-urlencoded;',
            dataType: "text",
            data: {
                "courseId": temp
            }
        })
    })*/
});
