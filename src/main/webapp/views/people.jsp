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
                    <input class="layui-input" placeholder="开始日期" id="LAY_demorange_s">
                </div>
                <div class="layui-input-inline">
                    <input class="layui-input" placeholder="截止日期" id="LAY_demorange_e">
                </div>

                <div class="layui-input-inline">
                    <select id="list">
                        <c:forEach items="${list}" var="item">
                            <option id="${item.code}" value="${item.code}"
                                    data-id="${item.code}">${item.scenicname}</option>
                        </c:forEach>
                    </select>
                </div>

            </div>

            <div class="layui-form-item">
                <span>&nbsp;</span>
                <a href="javascript:;" class="layui-btn layui-btn-small" id="search">
                    <i class="layui-icon">&#xe615;</i> 搜索景区
                </a>
                <a href="javascript:;" class="layui-btn layui-btn-small" id="searchAll">
                    <i class="layui-icon">&#xe615;</i> 搜索全部景区
                </a>
                <input type="file" name="file1" lay-type="file" class="layui-upload-file" lay-title="导入文件" id="test">
                <a href="javascript:;" class="layui-btn layui-btn-small" id="exportAll">
                    <i class="layui-icon">&#xe61d;</i> 导出详情
                </a>

                <a href="javascript:;" class="layui-btn layui-btn-small" id="export">
                    <i class="layui-icon">&#xe62a;</i> 导出汇总
                </a>
            </div>

        </div>

    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>人流列表</legend>
        <div class="layui-field-box layui-form">
            <table class="layui-table admin-table">
                <thead>
                <tr>
                    <th>景区编号</th>
                    <th>景区名称</th>
                    <th>景区地址</th>
                    <th>日期</th>
                    <th>承载量</th>
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
        <td>
            <a href="javascript:;" data-name="{{item.enter_day}}" data-id="{{ item.code }}" data-opt="edit"
               class="layui-btn layui-btn-mini" onclick="getTouristInfor(this)" style="z-index:-1">详情</a>
            <a href="javascript:;" data-id="{{item.code}}" data-name="{{item.enter_day}}" data-opt="del"
               class="layui-btn layui-btn-danger layui-btn-mini" onclick="deleteScenicarea(this)"
               style="z-index:-1">删除</a>
        </td>
    </tr>
    {{# }); }}
</script>
<script type="text/javascript" src="../assets/js/plugins/layui/layui.js"></script>
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

        $('#exportAll').on('click', function () {
            location.href = "${pageContext.request.contextPath}/File/exportTouristExcelAll.do";
        })

        $('#export').on('click', function () {
            location.href = "${pageContext.request.contextPath}/File/exportTouristExcel.do";
        })

    });
</script>
<script>
    $(function () {
        $('select').searchableSelect();
    });
</script>

<script>
    function deleteScenicarea(e) {

        layui.use('layer', function () {
            var $ = layui.jquery,
                layer = layui.layer;

            var code = e.getAttribute("data-id");
            var day = e.getAttribute("data-name");
            alert(code);
            alert(day);
            $.ajax({
                type: "GET",
                url: "${pageContext.request.contextPath}/peolple/deleteTouristByCode.do",
                data: {code: code, enter_day: day},
                dataType: "text",
                success: function (data) {
                    layer.msg(data)
                    $('#searchAll').click();
                    //var checkValue=$("#list").val();
                    //alert(checkValue);
                    window.location.href = "${pageContext.request.contextPath}/people/getpeopleMenu.do";

                }
            });

        })
    }

    function getTouristInfor(e) {
        layui.use('layer', function () {
            var $ = layui.jquery,
                layer = layui.layer;
            var code = e.getAttribute("data-id");
            var day = e.getAttribute("data-name");
            //alert(day);
            //alert(code);
            layer.open({
                title: '人流详细信息',
                maxmin: true,
                type: 2,
                content: '${pageContext.request.contextPath}/people/getTouristInfor.do?code=' + code + '&&day=' + day,
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
    layui.use('upload', function () {
        layer = layui.layer;
        layui.upload({
            url: '${pageContext.request.contextPath}/File/importfilePeople.do'
            , elem: '#test' //指定原始元素，默认直接查找class="layui-upload-file"
            , method: 'post' //上传接口的http类型
            , success: function (res) {
                layer.msg(res);
            }
        });
    });
</script>