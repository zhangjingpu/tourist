<%--
  Created by IntelliJ IDEA.
  User: snake
  Date: 2017/7/25
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/plugins/layui/css/layui.css" media="all"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css" media="all">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/js/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/table.css"/>
<link href="${pageContext.request.contextPath}/assets/css/jquery.searchableSelect.css" rel="stylesheet" type="text/css">

<div class="admin-main">

    <blockquote class="layui-elem-quote">
        <div class="layui-form-pane">
            <div class="layui-form-item">

                <div class="layui-input-inline">
                    <select id="list" style="z-index: 100;">
                        <c:forEach items="${list}" var="item">
                            <option id="${item.code}" value="${item.code}"
                                    data-id="${item.code}">${item.scenicname}</option>
                        </c:forEach>
                    </select>
                </div>
                <span>&nbsp;&nbsp;</span>
                <label class="layui-form-label">范围选择</label>
                <div class="layui-input-inline">
                    <input class="layui-input" placeholder="开始日" id="LAY_demorange_s">
                </div>
                <div class="layui-input-inline">
                    <input class="layui-input" placeholder="截止日" id="LAY_demorange_e">
                </div>


                <div class="layui-form-item" style="position:relative;left:200px;">

                    <a href="javascript:;" class="layui-btn layui-btn-small" id="search">
                    <i class="layui-icon">&#xe615;</i> 搜索景区
                </a>

                    <a href="javascript:;" class="layui-btn layui-btn-small" id="searchAll">
                        <i class="layui-icon">&#xe615;</i> 搜索全部景区
                    </a>
                </div>
            </div>

        </div>

    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>景区列表</legend>
        <div class="layui-field-box layui-form">
            <table class="layui-table admin-table">
                <thead>
                <tr>
                    <th>景区编号</th>
                    <th>景区名称</th>
                    <th>景区地址</th>
                    <th>日期</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="content">

                </tbody>
            </table>
        </div>
    </fieldset>
    <div class="admin-table-page">
        <div id="paged" class="page">
        </div>
    </div>
</div>
<!--模板-->
<script type="text/html" id="tpl">
    {{# layui.each(d.list, function(index, item){ }}
    <tr>
        <td>{{ item.id }}</td>
        <td>{{ item.scenicname }}</td>
        <td>{{ item.address }}</td>
        <td>{{item.enter_day}}</td>
        <td>
            <a href="javascript:;" id="test1" data-name="{{ item.id }}" data-title="{{item.scenicname}}"
               data-id="{{item.enter_day}}" data-opt="edit"
               class="layui-btn "
               onclick="TestDI(this)" style="z-index: 10;">天气舒适指数</a>

            <a href="javascript:;" id="test2" data-name="{{ item.id }}" data-title="{{ item.scenicname }}"
               data-id="{{item.enter_day}}" data-opt="edit"
               class="layui-btn layui-btn-normal " onclick="TestHLC(this)" style="z-index: 10;">人流承载量</a>

            <a href="javascript:;" data-name="{{ item.id }}" data-id="{{item.enter_day}}"
               data-opt="edit" class="layui-btn layui-btn-warm "
               onclick="TestVLC(this)" style="z-index: 10;">车流承载量</a>

            <a href="javascript:;" data-name="{{ item.id }}" data-id="{{item.enter_day}}" data-opt="edit"
               class="layui-btn layui-btn-danger " onclick="TestCD(this)" style="z-index: 10;">总体舒适度</a>
        </td>
        </td>
    </tr>
    {{# }); }}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/plugins/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.searchableSelect.js"></script>

<script>
    layui.config({
        base: "../assets/js/modules/"
    });
    layui.use(['paging', 'form'], function () {
        var $ = layui.jquery,
            paging = layui.paging(),
            layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
            layer = layui.layer, //获取当前窗口的layer对象
            form = layui.form();

        paging.init({
            openWait: true,
            url: '${pageContext.request.contextPath}/people/getScenicpeopleAll.do', //地址
            elem: '#content', //内容容器
            params: { //发送到服务端的参数
            },
            type: 'GET',
            tempElem: '#tpl', //模块容器
            pageConfig: { //分页参数配置
                elem: '#paged', //分页容器
                pageSize: 5//分页大小
            },
            success: function () { //渲染成功的回调
                //alert('渲染成功');
            },
            fail: function (msg) { //获取数据失败的回调
                alert('获取数据失败')
            },
            complate: function () { //完成的回调
                //alert('处理完成');
                //重新渲染复选框
                form.render('checkbox');
                form.on('checkbox(allselector)', function (data) {
                    var elem = data.elem;

                    $('#content').children('tr').each(function () {
                        var $that = $(this);
                        //全选或反选
                        $that.children('td').eq(0).children('input[type=checkbox]')[0].checked = elem.checked;
                        form.render('checkbox');
                    });
                });

            }
        });


        $('#test1').on('click', function () {
            var a = 1;
            alert(a);
        });


    });
</script>
<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        var a1, a2;
        var start = {
            min: '2010-01-01',
            format: 'YYYY-MM-DD hh:mm:ss' //日期格式
            , max: laydate.now()
            , istoday: true
            , istime: true
            , choose: function (datas) {
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas //将结束日的初始值设定为开始日
                //alert(datas);
                a1 = datas;
            }
        };

        var end = {
            min: '2010-01-01',
            format: 'YYYY-MM-DD hh:mm:ss'
            , max: laydate.now()
            , istoday: true
            , istime: true
            , choose: function (datas) {
                start.max = datas; //结束日选好后，重置开始日的最大日期
                a2 = datas;
                // alert(datas);
            }
        };

        document.getElementById('LAY_demorange_s').onclick = function () {
            start.elem = this;
            laydate(start);

        }
        document.getElementById('LAY_demorange_e').onclick = function () {
            end.elem = this
            laydate(end);

        }

        $('#search').on('click', function () {

            layui.use(['paging', 'form'], function () {
                var $ = layui.jquery,
                    paging = layui.paging(),
                    layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
                    layer = layui.layer, //获取当前窗口的layer对象
                    form = layui.form();
                var str = $('#list').val();
                //alert(str);
                //alert(a1);
                //alert(a2);
                parent.layer.msg(str)
                paging.init({
                    openWait: true,
                    url: '${pageContext.request.contextPath}/people/getpeopleInfor.do', //地址
                    elem: '#content', //内容容器
                    params: { //发送到服务端的参数
                        code: str,
                        startday: a1,
                        endday: a2
                    },
                    type: 'GET',
                    tempElem: '#tpl', //模块容器
                    pageConfig: { //分页参数配置
                        elem: '#paged', //分页容器
                        pageSize: 5 //分页大小
                    },
                    success: function () { //渲染成功的回调
                        // alert('渲染成功');
                    },
                    fail: function (msg) { //获取数据失败的回调
                        //alert('获取数据失败')
                    },
                    complate: function () { //完成的回调
                        //alert('处理完成');
                        //重新渲染复选框
                        form.render('checkbox');
                        form.on('checkbox(allselector)', function (data) {
                            var elem = data.elem;

                            $('#content').children('tr').each(function () {
                                var $that = $(this);
                                //全选或反选
                                $that.children('td').eq(0).children('input[type=checkbox]')[0].checked = elem.checked;
                                form.render('checkbox');
                            });
                        });

                    }
                });
            });
        });

        $('#searchAll').on('click', function () {

            layui.use(['paging', 'form'], function () {
                var $ = layui.jquery,
                    paging = layui.paging(),
                    layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
                    layer = layui.layer, //获取当前窗口的layer对象
                    form = layui.form();


                paging.init({
                    openWait: true,
                    url: '${pageContext.request.contextPath}/people/getScenicpeopleAll.do', //地址
                    elem: '#content', //内容容器
                    params: { //发送到服务端的参数
                    },
                    type: 'GET',
                    tempElem: '#tpl', //模块容器
                    pageConfig: { //分页参数配置
                        elem: '#paged', //分页容器
                        pageSize: 5 //分页大小
                    },
                    success: function () { //渲染成功的回调
                        // alert('渲染成功');
                    },
                    fail: function (msg) { //获取数据失败的回调
                        //alert('获取数据失败')
                    },
                    complate: function () { //完成的回调
                        //alert('处理完成');
                        //重新渲染复选框
                        form.render('checkbox');
                        form.on('checkbox(allselector)', function (data) {
                            var elem = data.elem;

                            $('#content').children('tr').each(function () {
                                var $that = $(this);
                                //全选或反选
                                $that.children('td').eq(0).children('input[type=checkbox]')[0].checked = elem.checked;
                                form.render('checkbox');
                            });
                        });

                    }
                });
            });
        });



    });
</script>


<script>
    function TestDI(e) {
        layui.use('layer', function () {
            var $ = layui.jquery,
                layer = layui.layer;

            var enter_time = e.getAttribute('data-id');
            var scenic_id = e.getAttribute('data-name');
            var scenic_name = e.getAttribute('data-title');

            layer.open({
                title: '天气舒适指数',
                maxmin: true,
                type: 2,
                content: '${pageContext.request.contextPath}/Data/getDITable.do?enter_time=' + enter_time + "&&scenic_id=" + scenic_id + "&&scenic_name=" + scenic_name,
                area: ['100%', '100%'],
                resize: 'true',
                moveOut: 'true',
                success: function (layero, index) {
                    console.log(layero, index);
                    $('.admin-table-page').hide();
                },
                cancel: function (index, layero) {
                    $('.admin-table-page').show();
                }
            });

        })
    }
</script>

<script>
    function TestHLC(e) {
        layui.use('layer', function () {
            var $ = layui.jquery,
                layer = layui.layer;

            var enter_time = e.getAttribute('data-id');
            var scenic_id = e.getAttribute('data-name');
            var scenic_name = e.getAttribute('data-title');
            layer.open({
                title: '人流承载量',
                maxmin: true,
                type: 2,
                content: '${pageContext.request.contextPath}/Data/getHLCTable.do?enter_time=' + enter_time + "&&scenic_id=" + scenic_id + "&&scenic_name=" + scenic_name,
                area: ['100%', '100%'],
                resize: 'true',
                moveOut: 'true',
                success: function (layero, index) {
                    console.log(layero, index);
                    $('.admin-table-page').hide();
                },
                cancel: function (index, layero) {
                    $('.admin-table-page').show();
                }
            });
        })
    }
</script>

<script>
    function TestVLC(e) {
        layui.use('layer', function () {
            var $ = layui.jquery,
                layer = layui.layer;

            var enter_time = e.getAttribute('data-id');
            var scenic_id = e.getAttribute('data-name');

            layer.open({
                title: '人流承载量',
                maxmin: true,
                type: 2,
                content: '${pageContext.request.contextPath}/DataTwo/getVLCTable.do?enter_time=' + enter_time + "&&scenic_id=" + scenic_id,
                area: ['100%', '100%'],
                resize: 'true',
                moveOut: 'true',
                success: function (layero, index) {
                    console.log(layero, index);
                    $('.admin-table-page').hide();
                },
                cancel: function (index, layero) {
                    $('.admin-table-page').show();
                }
            });
        })
    }
</script>
<script>
    function TestCD(e) {
        layui.use('layer', function () {
            var $ = layui.jquery,
                layer = layui.layer;

            var enter_time = e.getAttribute('data-id');
            var scenic_id = e.getAttribute('data-name');

            layer.open({
                title: '总体舒适度',
                maxmin: true,
                type: 2,
                content: '${pageContext.request.contextPath}/DataTwo/getCDTable.do?enter_time=' + enter_time + "&&scenic_id=" + scenic_id,
                area: ['100%', '100%'],
                resize: 'true',
                moveOut: 'true',
                success: function (layero, index) {
                    console.log(layero, index);
                    $('.admin-table-page').hide();
                },
                cancel: function (index, layero) {
                    $('.admin-table-page').show();
                }
            });

        })

    }
</script>

<script>
    $(function () {
        $('select').searchableSelect();
    });
</script>


