<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctx }/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx }/css/amazeui.min.css"/>
    <script type="text/javascript" charset="utf-8" src="${ctx }/js/umedit/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx }/js/umedit/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx }/js/umedit/lang/zh-cn/zh-cn.js"></script>
    <script src="${ctx }/js/jquery.min.js"></script>
    <style>
        .item1_img {
            width: 300px;
            height: 150px;
        }
    </style>
</head>
<body>


<div class="main_top">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">修改文章
        </strong>
            <small></small>
        </div>
    </div>
    <hr>
    <form id="blog_form" action="${ctx}/article_updata.action" method="post" enctype="multipart/form-data">
        <div class="edit_content">
            <div class="item1">
                <div>
                    <span>文章标题：</span>
                    <input type="text" class="am-form-field" name="article_title" style="width: 300px"
                           value="<s:property value="article_title"/>">&nbsp;&nbsp;
                </div>
            </div>

            <input type="text" name="article_desc" id="article_desc" style="display: none;"
                   value="<s:property value="article_desc"/>">

            <div class="item1">
                <span>所属分类：</span>
                <select id="category_select" name="category.parentid" style="width: 150px">&nbsp;&nbsp;　

                </select>

                <select id="skill_select" name="category.cid" style="width: 150px">&nbsp;&nbsp;

                </select>

            </div>
            <div class="item1 update_pic" style="margin-bottom: 170px">
                <span>摘要图片：</span>
                <img src="${ctx}/upload/<s:property value="article_pic"/>" id="imageview" class="item1_img">
                <label for="fileupload" id="label_file">上传文件</label>
                <input type="file" name="upload" id="fileupload"/>
            </div>

            <div id="editor" name="article_content" style="width:900px;height:400px;">
            </div>
            <input type="hidden" id="rescontent" value="<s:property value="article_content"/> ">
            <input type="hidden" name="article_id" value="<s:property value="article_id"/>">
            <input type="hidden" name="article_pic" value="<s:property value="article_pic"/>">
            <button class="am-btn am-btn-default" type="button" id="send" style="margin-top: 10px;">
                修改
            </button>
        </div>

    </form>

</div>
<script>
    $(function () {
        /*发送请求获取parentid=0的分类数据*/
        $.post("${ctx}/article_getCategory.action", {"parentid": 0}, function (data) {
            console.log(data);
            /*将json的数组集合进行遍历data （i:下标；obj：对象）*/
            $(data).each(function (i, obj) {
                console.log(obj.cname);
                /*将id为category_select的选择器进行拼接*/
                $("#category_select").append("<option value=" + obj.cid + ">" + obj.cname + "</option>")
            });
            /*触发change事件*/
            // $("#category_select").trigger("change");
            $("#category_select option[value=<s:property value="category.parentid"/>]").prop("selected", true);
        }, "json");
        /*点击编辑后获取category.parentid*/
        var parentid = <s:property value="category.parentid"/>
            /*发送请求获取parentid=parentid的分类数据*/
            $.post("${ctx}/article_getCategory.action", {"parentid": parentid}, function (data) {
                console.log(data);
                /*将json的数组集合进行遍历data （i:下标；obj：对象）*/
                /*清空当前的子级的数据内容*/
                $("#skill_select").empty();
                $(data).each(function (i, obj) {
                    $("#skill_select").append("<option value=" + obj.cid + ">" + obj.cname + "</option>")
                });

                $("#skill_select option[value=<s:property value="category.cid"/>]").prop("selected", true);
            }, "json");


        /*监听分类数据值的改变*/
        $("#category_select").on("change", function () {
            /*获取当前父类选中的cid*/
            var cid = $("#category_select").val();
            /*清空当前的子级的数据内容*/
            $("#skill_select").empty();
            $.post("${ctx}/article_getCategory.action", {"parentid": cid}, function (data) {
                console.log(data);
                $(data).each(function (i, obj) {
                    $("#skill_select").append("<option value=" + obj.cid + ">" + obj.cname + "</option>")
                });
            }, "json");
        });
        /*原理是把本地图片路径："D(盘符):/image/..."转为"http://..."格式路径来进行显示图片*/
        $("#fileupload").change(function () {
            var $file = $(this);
            var objUrl = $file[0].files[0];
            //获得一个http格式的url路径:mozilla(firefox)||webkit or chrome
            var windowURL = window.URL || window.webkitURL;
            //createObjectURL创建一个指向该参数对象(图片)的URL
            var dataURL;
            dataURL = windowURL.createObjectURL(objUrl);
            $("#imageview").attr("src", dataURL);
            console.log($('#imageview').attr('style'));
            if ($('#imageview').attr('style') === 'display: none;') {
                $('#imageview').attr('style', 'inline');
                $('#imageview').width("300px");
                $('#imageview').height("200px");
                $('.update_pic').attr('style', 'margin-bottom: 80px;');
            }
        });
        /*初始化富文本*/
        var ue = UE.getEditor('editor');
        ue.ready(function () {
            ue.execCommand("inserthtml", $("#rescontent").val())
        });

        $("#send").click(function () {
            /*获取文本内容*/
            var text = ue.getContentTxt();
            /*设置文本描述*/
            text = text.substring(0, 20) + "...";
            $("#article_desc").val(text);
            /*提交*/
            $("#blog_form").submit();
        });
    });
</script>

</body>
</html>