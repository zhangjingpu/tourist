<%--
  Created by IntelliJ IDEA.
  User: snake
  Date: 2017/7/24
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title>舒适度指数大数据管理平台</title>
    <link rel="icon" type="image/png"
          href="${pageContext.request.contextPath}/assets/images/favicon.png?v=26838d09a1?v=26838d09a1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/plugins/layui/css/layui.css"
          media="all">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/login.css?v=45d62fc962?v=45d62fc962">
</head>
<body class="layui-bg-cyan">
<div class="admin-login-block">
    <div class="admin-login-text">舒适度指数大数据<span>管理平台</span></div>
    <div class="login-font"><span>系统登录</span></div>
    <div class="admin-login-form">
        <form class="layui-form" action="${pageContext.request.contextPath}/user/login.do" lay-filter="login">
            <div class="layui-form-item">
                <div class="layui-input-block admin-login-input"><input type="text" name="account" required
                                                                        lay-verify="required" placeholder="登录账号"
                                                                        autocomplete="off" class="layui-input"></div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block admin-login-input"><input type="password" name="password" required
                                                                        lay-verify="required" placeholder="登录密码"
                                                                        autocomplete="off" class="layui-input"></div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block admin-login-input">
                    <button class="layui-btn admin-login-btn" lay-submit>后台登录</button>
                </div>
            </div>
        </form>
        <div class="admin-login-copy"><p>舒适度指数大数据管理平台${errorMsg}</p></div>
    </div>
</div>
e
<script src="${pageContext.request.contextPath}/assets/js/plugins/layui/layui.js?v=ceda237a84?v=ceda237a84"></script>
<script>layui.use(["layer", "form"], function () {
    var i = (layui.jquery, layui.layer), o = layui.form();
    var layer = layui.layer;
    o.on("submit(login)", function () {
        layer.msg("登录！");
        //return false;
    })
    if ('${errorMsg}' != '') {
        layer.msg('${errorMsg}');
        //alert('${errorMsg}');
    }
})</script>
</body>
</html>
