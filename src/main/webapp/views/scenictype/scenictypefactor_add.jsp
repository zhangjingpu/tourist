<%--
  Created by IntelliJ IDEA.
  User: snake
  Date: 2017/7/27
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>表单</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/js/plugins/font-awesome/css/font-awesome.min.css">

</head>

<body>
<div style="margin: 15px;">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>添加景区类型因子信息</legend>
    </fieldset>

    <form class="layui-form" id="form1" action="${pageContext.request.contextPath}/scenictype/insertScenictypeFactor.do"
          accept-charset="UTF-8">
        <input type="hidden" name="scenictype_id" value="${id}" id="scenictype_id"> 　
        <div class="layui-form-item">
            <label class="layui-form-label">景区类型因子名称</label>
            <div class="layui-input-inline">
                <input type="text" name="scenictype_name" value="" lay-verify="title"
                       autocomplete="off"
                       placeholder="请输入景区名称"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">景区类型因子值</label>
            <div class="layui-input-inline">
                <input type="text" name="scenictype_value" value="" lay-verify="title"
                       autocomplete="off"
                       placeholder="请输入景区名称"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/plugins/layui/layui.js"></script>
<script>
    layui.use(['form', 'layedit', 'laydate', 'layer'], function () {
        var form = layui.form(),
            layer = layui.layer,
            layedit = layui.layedit,
            laydate = layui.laydate;
        var $ = layui.jquery;
        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');
        //自定义验证规则
        form.verify({
            title: function (value) {
                if (value.length < 1) {
                    return '景区类型因子不得为空!';
                }
            }, address: function (value) {
                if (value.length < 1) {
                    return '景区类型因子值不得为空!';
                }
            }

        });

        //监听提交
        form.on('submit(demo1)', function (data) {


        });

        if ('${errorMsg}' != '') {
            layer.msg('${errorMsg}');
            //alert('${errorMsg}');
        }

    });
</script>
</body>

</html>