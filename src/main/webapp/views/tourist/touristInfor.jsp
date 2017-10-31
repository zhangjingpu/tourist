<%--
  Created by IntelliJ IDEA.
  User: snake
  Date: 2017/7/25
  Time: 10:30
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
                <label class="layui-form-label">时间范围</label>
                <div class="layui-input-inline">
                    <input class="layui-input" placeholder="开始时间"
                           onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"
                           id="LAY_demorange_s">
                </div>
                <div class="layui-input-inline">
                    <input class="layui-input" placeholder="截止时间"
                           onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"
                           id="LAY_demorange_e">
                </div>

                <div class="layui-input-inline">
                    <select id="list">
                        <c:forEach items="${list}" var="item">
                            <option id="${item.id}" value="${item.tourist_code}"
                                    data-id="${item.science_id}">${item.tourist_code}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="layui-form-item">
                    <a href="javascript:;" class="layui-btn layui-btn-small" id="searchTime">
                        <i class="layui-icon">&#xe615;</i> 时间搜索
                    </a>
                    <span>&nbsp;</span>
                    <a href="javascript:;" class="layui-btn layui-btn-small" id="search">
                        <i class="layui-icon">&#xe615;</i> 搜索游客
                    </a>
                    <a href="javascript:;" class="layui-btn layui-btn-small" id="searchAll">
                        <i class="layui-icon">&#xe615;</i> 搜索全部游客
                    </a>
                </div>
            </div>

        </div>

    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>人流详细信息</legend>
        <div class="layui-field-box layui-form">
            <table class="layui-table admin-table">
                <thead>
                <tr>
                    <th>景区编号</th>
                    <th>景区名称</th>
                    <th>景区地址</th>
                    <th>日期</th>
                    <th>承载量</th>
                    <th>游客编号</th>
                    <th>游客类型</th>
                    <th>进入时间</th>
                    <th>离开时间</th>
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
        <td>{{ item.code }}</td>
        <td>{{ item.scenicname }}</td>
        <td>{{ item.address }}</td>
        <td>{{ item.enter_day}}</td>
        <td>{{ item.max_people }}</td>
        <td>{{ item.tourist_code}}</td>
        <td>
            {{# if(item.tourist_type == 1){ }}
            游客
            {{# } }}
            {{# if(item.tourist_type == 2){ }}
            会员
            {{# } }}
        </td>
        <td>{{ item.enter_time}}</td>
        <td>{{ item.leave_time}}</td>
        <td>
            <a href="javascript:;" data-name="{{item.enter_day}}" data-id="{{ item.tourist_code }}" data-opt="edit"
               class="layui-btn layui-btn-mini" onclick="getTouristInfor(this)">编辑</a>
            <a href="javascript:;" data-id="{{item.tourist_code}}" data-name="{{item.enter_day}}" data-opt="del"
               class="layui-btn layui-btn-danger layui-btn-mini" onclick="deleteTourist(this)">删除</a>
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
            url: '${pageContext.request.contextPath}/people/getAllTouristInfor.do', //地址
            elem: '#content', //内容容器
            params: { //发送到服务端的参数
                code: "${code}",
                day: "${day}"
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

    });
</script>
<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        var a1, a2;
        var start = {
            min: '${day} 00:00:00',
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
            min: '${day} 24:00:00',
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
                var str1 = $("#list").find(" option:selected").attr("data-id");
                alert(str);
                alert(str1);
                //parent.layer.msg(str)
                paging.init({
                    openWait: true,
                    url: '${pageContext.request.contextPath}/people/searchTouristInfor.do', //地址
                    elem: '#content', //内容容器
                    params: { //发送到服务端的参数
                        tourist_code: str,
                        science_id: str1
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

        $('#searchTime').on('click', function () {
            layui.use(['paging', 'form'], function () {
                var $ = layui.jquery,
                    paging = layui.paging(),
                    layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
                    layer = layui.layer, //获取当前窗口的layer对象
                    form = layui.form();
                var str1 = $("#list").find(" option:selected").attr("data-id");
                alert(str1);
                alert(a1);
                alert(a2);
                //parent.layer.msg(str1)
                paging.init({
                    openWait: true,
                    url: '${pageContext.request.contextPath}/people/searchTouristInforTime.do', //地址
                    elem: '#content', //内容容器
                    params: { //发送到服务端的参数
                        science_id: str1,
                        enter_time: a1,
                        leave_time: a2,
                        enter_day: '${day}'
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

                var str1 = $("#list").find(" option:selected").attr("data-id");
                paging.init({
                    openWait: true,
                    url: '${pageContext.request.contextPath}/people/searchTouristInforAll.do', //地址
                    elem: '#content', //内容容器
                    params: { //发送到服务端的参数
                        science_id: str1,
                        enter_day: '${day}'
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
    $(function () {
        $('select').searchableSelect();
    });
</script>

<script>
    function deleteTourist(e) {

        layui.use('layer', function () {
            var $ = layui.jquery,
                layer = layui.layer;
            var code = e.getAttribute("data-id");
            alert(code);
            $.ajax({
                type: "GET",
                url: "${pageContext.request.contextPath}/people/deleteByTouristCode.do",
                data: {tourist_code: code},
                dataType: "text",
                success: function (data) {
                    layer.msg(data)
                    $('#searchAll').click();
                    //var checkValue=$("#list").val();
                    //alert(checkValue);
                }
            });

        })
    }

    function getTouristInfor(e) {
        layui.use('layer', function () {
            var $ = layui.jquery,
                layer = layui.layer;
            var code = e.getAttribute("data-id");

            layer.open({
                title: '修改人流信息',
                maxmin: true,
                type: 2,
                content: '${pageContext.request.contextPath}/people/getTouristedit.do?tourist_code=' + code,
                area: ['100%', '100%'],
                resize: 'true',
                moveOut: 'true',
                success: function (layero, index) {
                    console.log(layero, index);
                    $('.admin-table-page').hide();
                },
                cancel: function (index, layero) {
                    $('.admin-table-page').show();
                    $('#searchAll').click();
                }
            });


        })
    }
</script>

