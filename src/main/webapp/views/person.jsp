<%--
  Created by IntelliJ IDEA.
  User: snake
  Date: 2017/7/25
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>个人资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/user.css" media="all"/>
</head>
<body class="childrenBody">
<form class="layui-form" action="personInfoSubmit.do" method="post">
    <div class="user_left">
        <div class="layui-form-item">
            <label class="layui-form-label">账号</label>
            <div class="layui-input-block">
                <input type="text" value="${currentUser.account}" style="width:100px;" disabled
                       class="layui-input layui-disabled">
                <input type="hidden" name="account" value="${currentUser.account}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户组</label>
            <div class="layui-input-block">
                <input type="text" value="超级管理员" style="width:100px;" disabled class="layui-input layui-disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="username" value="${currentUser.username}" placeholder="请输入真实姓名"
                       lay-verify="username" style="width:210px;" lay-verify="required"
                       class="layui-input realName">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <c:if test="${currentUser.sex==1}">
                    <input type="radio" name="sex" value="1" title="男" checked>
                    <input type="radio" name="sex" value="0" title="女">
                </c:if>
                <c:if test="${currentUser.sex==0}">
                    <input type="radio" name="sex" value="1" title="男">
                    <input type="radio" name="sex" value="0" title="女" checked>
                </c:if>

            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">联系电话</label>
            <div class="layui-input-block">
                <input type="tel" name="phone" value="${currentUser.phone}" placeholder="请输入联系电话" lay-verify="phone"
                       style="width:210px;"
                       lay-verify="required|phone"
                       class="layui-input userPhone">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="text" style="width:210p" name="email" value="${currentUser.email}" lay-verify="email"
                       placeholder="请输入邮箱"
                       lay-verify="required|email"
                       class="layui-input userEmail">
            </div>
        </div>


        <div class="layui-form-item" style="margin-left: 20px;">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="changeUser">修改</button>
                <button type="reset" class="layui-btn layui-btn-primary" style="margin-left: 20px">重置</button>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/plugins/layui/layui.js"></script>
<script>
    layui.use('form', function () {
        var form = layui.form();
    });
</script>
<
<script>
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form()
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate;

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            username: function (value) {
                if (value.length < 1) {
                    return '请填写姓名';
                }
            }
            ,
            phone: [/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/ || /^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/ || /^(\d{7,8})(-(\d{3,}))?$/, '请正确输入电话号码']

            ,
            content: function (value) {
                layedit.sync(editIndex);
            }
            ,
            email: [/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/, '请正确填写邮箱']
            ,
            content: function (value) {
                layedit.sync(editIndex);
            }


        });
        //监听提交
        form.on('submit(demo1)', function (data) {
            layer.msg('修改成功');
            window.parent.location.reload();
            return true;

        });
    });

</script>
</body>
</html>