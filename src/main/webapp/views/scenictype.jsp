<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: snake
  Date: 2017/7/25
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>景区类型设置</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css" media="all">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/js/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/table.css"/>
    <link href="${pageContext.request.contextPath}/assets/css/jquery.searchableSelect.css" rel="stylesheet"
          type="text/css">


</head>

<body>
<div class="admin-main">

    <blockquote class="layui-elem-quote">
        <a href="javascript:;" class="layui-btn layui-btn-small" id="add" onclick="addScenicarea()">
            <i class="layui-icon">&#xe608;</i> 添加景区类型
        </a>
        <div class="layui-input-inline">
            <select id="list">
                <c:forEach items="${list}" var="item">
                    <option id="${item.id}" value="${item.id}" data-id="">${item.scenictype_name}</option>
                </c:forEach>
            </select>
        </div>
        <a href="javascript:;" class="layui-btn layui-btn-small" id="search">
            <i class="layui-icon">&#xe615;</i> 搜索景区类型
        </a>
        <a href="javascript:;" class="layui-btn layui-btn-small" id="searchAll">
            <i class="layui-icon">&#xe615;</i> 搜索全部景区类型
        </a>
    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>景区列表</legend>
        <div class="layui-field-box layui-form">
            <table class="layui-table admin-table">
                <thead>
                <tr>
                    <th>景区类型编号</th>
                    <th>景区类型名称</th>
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
        <td>{{ item.scenictype_name }}</td>
        <td>

            <a href="javascript:;" data-id="{{item.id}}" class="layui-btn layui-btn-mini edit"
               onclick=" getScenictypeInfor(this)">详情</a>
            <a href="javascript:;" data-id="{{item.id}}" data-name="{{item.scenictype_name}}"
               class="layui-btn layui-btn-mini edit"
               onclick="editScenictype(this)">修改</a>
            <a href="javascript:;" data-id="{{item.id}}" data-opt="del"
               class="layui-btn layui-btn-danger layui-btn-mini delete" onclick="deleteScenictype(this)">删除</a>
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

        if ('${Msg}' != '') {
            layer.msg('${Msg}');
            //alert('${Msg}');
        }

        paging.init({
            openWait: true,
            url: '${pageContext.request.contextPath}/scenictype/getScenictypeAll.do', //地址
            //url:'../assets/tsconfig.json',
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

        $('#search').on('click', function () {

            layui.use(['paging', 'form'], function () {
                var $ = layui.jquery,
                    paging = layui.paging(),
                    layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
                    layer = layui.layer, //获取当前窗口的layer对象
                    form = layui.form();
                var str = $('#list').val();
                parent.layer.msg(str)
                paging.init({
                    openWait: true,
                    url: '${pageContext.request.contextPath}/scenictype/getScenicById.do', //地址
                    elem: '#content', //内容容器
                    params: { //发送到服务端的参数
                        id: str
                    },
                    type: 'GET',
                    tempElem: '#tpl', //模块容器
                    pageConfig: { //分页参数配置
                        elem: '#paged', //分页容器
                        pageSize: 1 //分页大小
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
                    url: '${pageContext.request.contextPath}/scenictype/getScenictypeAll.do', //地址
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

        $('#export').on('click', function () {
            location.href = "${pageContext.request.contextPath}/File/exportScenicExcel.do";
        })

    });
</script>

<script>
    $(function () {
        $('select').searchableSelect();
    });
</script>

<script>
    function addScenicarea() {
        layui.use('layer', function () {
            var $ = layui.jquery,
                layer = layui.layer;

            layer.open({
                title: '添加景区类型',
                maxmin: true,
                type: 2,
                content: '${pageContext.request.contextPath}/scenictype/getScenictypeAdd.do',
                area: ['35%', '55%'],
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


<script>
    function editScenictype(e) {
        layui.use('layer', function () {
            var $ = layui.jquery,
                layer = layui.layer;
            var code = e.getAttribute("data-id");
            var name = e.getAttribute("data-name");

            layer.open({
                title: '修改景区类型',
                maxmin: true,
                type: 2,
                content: '${pageContext.request.contextPath}/scenictype/geteditScenictypeMenu.do?id=' + code + "&&name=" + name,
                area: ['35%', '55%'],
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

<script>
    function getScenictypeInfor(e) {
        layui.use('layer', function () {
            var $ = layui.jquery,
                layer = layui.layer;
            var code = e.getAttribute("data-id");
            layer.open({
                title: '景区类型因子',
                maxmin: true,
                type: 2,
                content: '${pageContext.request.contextPath}/scenictype/getScenictypeInfor.do?id=' + code,
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


<script>
    function deleteScenictype(e) {

        layui.use('layer', function () {
            var $ = layui.jquery,
                layer = layui.layer;

            var code = e.getAttribute("data-id");
            $.ajax({
                type: "GET",
                url: "${pageContext.request.contextPath}/scenictype/deleteScenictypeById.do",
                data: {id: code},
                dataType: "text",
                success: function (data) {
                    layer.msg("删除成功！" + data)
                    $('#searchAll').click();
                    //var checkValue=$("#list").val();
                    //alert(checkValue);
                    window.location.href = "${pageContext.request.contextPath}/scenictype/getScenictypeMenu.do";

                }
            });

        })
    }
</script>

</body>

</html>