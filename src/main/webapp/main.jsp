<%--
  Created by IntelliJ IDEA.
  User: snake
  Date: 2017/7/25
  Time: 0:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
    <title>舒适度指数大数据管理平台 </title>
    <link rel="icon" type="image/png"
          href="${pageContext.request.contextPath}/assets/images/favicon.png?v=26838d09a1?v=26838d09a1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="https://at.alicdn.com/t/font_mpw4gvnu4xk9y66r.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css?v=9d061fa274?v=9d061fa274">

</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header">
        <div class="layui-main">
            <div class="logo"><img
                    src="${pageContext.request.contextPath}/assets/images/logo.png?v=80c71e8de2?v=80c71e8de2"
                    alt="舒适度指数大数据管理平台">舒适度指数大数据管理平台
            </div>
            <ul class="layui-nav">
                <li class="layui-nav-item">
                    <a href="javascript:;" class="admin-header-user">
                        <img src="${pageContext.request.contextPath}/assets/pic/456.jpg"/>
                        <span>${sessionScope.currentUser.username}</span>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:;"><i class="iconfont icon-password"></i> 修改密码</a></dd>
                            <dd><a href="javascript:;"><i class="iconfont icon-xiugai"></i> 更换头像</a></dd>
                            <dd><a href="javascript:;"><i class="iconfont icon-tuichu"></i> 切换账号</a></dd>
                        </dl>
                    </a>

                <li class="layui-nav-item"><a href="javascript:;" onclick="toggleFullScreen()"><i
                        class="iconfont icon-quanping"></i> 全屏</a></li>
                <li class="layui-nav-item"><a href="javascript:;" onclick="myFunction()"><i
                        class="iconfont icon-quanping"></i> 收缩导航栏</a></li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/user/logout.do"><i
                        class="iconfont icon-tuichu"></i> 退出</a></li>
            </ul>
        </div>
    </div>


    <div class="layui-side layui-bg-black" id="admin-side">
        <div class="layui-side-scroll" id="admin-navbar-side" lay-filter="side"></div>
    </div>

    <!--
        <div class="layui-side layui-bg-black" id="admin-side">
            <div class="layui-side-scroll">
                <ul class="layui-nav layui-nav-tree" id="admin-navbar-side" lay-filter="navBar">
                    <li class="layui-nav-item"><a href="javascript:;"><i
                            class="iconfont icon-yonghu"><span>管理员管理</span>></i></a>
                        <dl class="layui-nav-child">
                            <dd data-url="./views/base.html" data-id="1" data-hash="base"><a href="javascript:;"><i
                                    class="layui-icon">&#xe602;</i> <span>个人信息</span></a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item"><a href="javascript:;"><i class="iconfont icon-shijian"></i> &nbsp;景区设置</a>
                        <dl class="layui-nav-child">
                            <dd data-url="./views/list-pro-start.html" data-id="2" data-hash="order-pro-start"><a
                                    href="javascript:;"><i class="layui-icon">&#xe602;</i> <span>景区管理</span></a></dd>
                            <dd data-url="./views/list-pro-in.html" data-id="3" data-hash="order-pro-in"><a
                                    href="javascript:;"><i class="layui-icon">&#xe602;</i> <span>车辆管理</span></a></dd>
                            <dd data-url="./views/list-pro-end.html" data-id="4" data-hash="order-pro-end"><a
                                    href="javascript:;"><i class="layui-icon">&#xe602;</i> <span>停车场管理</span></a></dd>
                            <dd data-url="./views/list-pro-end.html" data-id="5" data-hash="order-pro-end"><a
                                    href="javascript:;"><i class="layui-icon">&#xe602;</i> <span>天气管理</span></a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item"><a href="javascript:;"><i class="iconfont icon-yonghu"></i> 数据统计</a>
                        <dl class="layui-nav-child">
                            <dd data-url="./views/base.html" data-id="6" data-hash="base"><a href="javascript:;"><i
                                    class="layui-icon">&#xe602;</i> <span>天气舒适指数</span></a></dd>
                            <dd data-url="./views/role.html" data-id="7" data-hash="role"><a href="javascript:;"><i
                                    class="layui-icon">&#xe602;</i> <span>人流承载量</span></a></dd>
                            <dd data-url="./views/account.html" data-id="8" data-hash="account"><a href="javascript:;"><i
                                    class="layui-icon">&#xe602;</i> <span>车流承载量</span></a></dd>
                            <dd data-url="./views/account.html" data-id="9" data-hash="account"><a href="javascript:;"><i
                                    class="layui-icon">&#xe602;</i> <span>总体舒适度</span></a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
        </div>
          -->
    <!--右侧主界面 -->
    <div class="layui-body" style="bottom: 0;border-left: solid 2px #1AA094;" id="admin-body">
        <div class="layui-tab layui-tab-brief admin-nav-card" lay-allowclose="true" lay-filter="admin-tab"
             id="tabtitle">
            <ul class="layui-tab-title" id="tabBody">
                <li class="layui-this" id="title" style="width: auto">欢迎界面</li>
            </ul>
            <div class="layui-tab-content" id="main">
                <div class="layui-tab-item layui-show"></div>

            </div>
        </div>
    </div>
    <div class="layui-footer footer footer-demo" id="admin-footer">
        <div class="layui-main"><p>@2017 舒适度指数大数据管理平台</p></div>
    </div>
</div>
<div id="tpl" class="layui-hide"></div>


<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/plugins/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/plugins/layui/layui.js?v=ceda237a84?v=ceda237a84"></script>
<script src="${pageContext.request.contextPath}/assets/js/config.js?v=594b4d83e6?v=594b4d83e6"></script>


<script>
    layui.use(['layer', 'form', 'navbar', 'tab'], function () {
        tab = layui.tab({
            elem: '.admin-nav-card' //设置选项卡容器
            ,
            //maxSetting: {
            //	max: 5,
            //	tipMsg: '只能开5个哇，不能再开了。真的。'
            //},
            contextMenu: true,
            onSwitch: function (data) {
                console.log(data.id); //当前Tab的Id
                console.log(data.index); //得到当前Tab的所在下标
                console.log(data.elem); //得到当前的Tab大容器

                console.log(tab.getCurrentTabId())
            },
            closeBefore: function (obj) { //tab 关闭之前触发的事件
                console.log(obj);
                //obj.title  -- 标题
                //obj.url    -- 链接地址
                //obj.id     -- id
                //obj.tabId  -- lay-id
                if (obj.title === 'BTable') {
                    layer.confirm('确定要关闭' + obj.title + '吗?', {icon: 3, title: '系统提示'}, function (index) {
                        //因为confirm是非阻塞的，所以这里关闭当前tab需要调用一下deleteTab方法
                        tab.deleteTab(obj.tabId);
                        layer.close(index);
                    });
                    //返回true会直接关闭当前tab
                    return false;
                } else if (obj.title === '表单') {
                    layer.confirm('未保存的数据可能会丢失哦，确定要关闭吗?', {icon: 3, title: '系统提示'}, function (index) {
                        tab.deleteTab(obj.tabId);
                        layer.close(index);
                    });
                    return false;
                }
                return true;
            }
        });

        navbar = layui.navbar();
        //设置navbar
        navbar.set({
            spreadOne: true,
            elem: '#admin-navbar-side',
            cached: false,
            url: '${pageContext.request.contextPath}/getMenu.do'
            //data: navs
            /*cached:true,
             url: 'datas/nav.json'*/
        });
        //渲染navbar
        navbar.render();
        navbar.on('click(side)', function (data) {
            tab.tabAdd(data.field);
        });
    });
</script>


<script type="text/javascript">
    //左边侧边条隐藏

    function myFunction() {
        var sideWidth = $('#admin-side').width();
        if (sideWidth === 200) {
            $('#admin-body').animate({
                left: '0'
            }); //admin-footer
            $('#admin-footer').animate({
                left: '0'
            });
            $('#admin-side').animate({
                width: '0'
            });
            $('#title').animate({
                left: '0px'
            });
            $('#tabBody').animate({
                left: '0px'
            });
            $('#admin-footer').hide();
        } else {
            $('#admin-body').animate({
                left: '200px'
            });
            $('#admin-footer').animate({
                left: '200px'
            });
            $('#admin-side').animate({
                width: '200px'
            });
            $('#tabBody').animate({
                left: '200px'
            });
            $('#admin-footer').show();
        }
    }

</script>
</body>
</html>