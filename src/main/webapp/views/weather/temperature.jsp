<%--
  Created by IntelliJ IDEA.
  User: snake
  Date: 2017/7/25
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/plugins/layui/css/layui.css" media="all"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css" media="all">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/js/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/table.css"/>
<link href="${pageContext.request.contextPath}/assets/css/jquery.searchableSelect.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/assets/js/plugins/layui/lay/modules/laydate.js"></script>
<div class="admin-main">

    <blockquote class="layui-elem-quote">


    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>${scincename}${time}气温列表</legend>
        <input type="hidden" name="time" value="${time}"/>
        <input type="hidden" name="scenie_id" value="${scenic_id}"/>
        <div class="layui-field-box layui-form">
            <table class="layui-table admin-table">
                <thead>
                <tr>
                    <th>时间(h)</th>
                    <th>温度(℃)</th>
                    <th>相对湿度</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="content">
                <!--<c:forEach items="${diTable}" var="item">
                    <tr>
                        <td>${item.time_hour}</td>
                        <td>${item.celsius}</td>
                        <td>${item.relative_humidity}</td>
                        <td>td>
                            <a href="javascript:;" class="layui-btn layui-btn-mini info" onclick="info(this)">详情</a>
                            <a href="javascript:;" class="layui-btn layui-btn-danger layui-btn-mini del" onclick="del(this)">删除</a>
                        </td></td>
                    </tr>
                </c:forEach>-->
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
        <td>{{ item.time_hour }}</td>
        <td>{{ item.celsius }}</td>
        <td>{{ item.relative_humidity }}</td>
        <td>
            <a href="javascript:;" data-id="{{item.scenic_id}}" data-day="{{item.time_date}}"
               data-hour="{{item.time_hour}}" class="layui-btn layui-btn-mini info" onclick="edit(this)">编辑</a>
            <a href="javascript:;" data-id="{{item.id}}" class="layui-btn layui-btn-danger layui-btn-mini del"
               onclick="del(this)">删除</a>
            <a href="javascript:;" data-id="{{item.scenic_id}}" data-day="{{item.time_date}}"
               data-hour="{{item.time_hour}}" class="layui-btn layui-btn-mini add" onclick="add(this)">添加</a>
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
        var scenie_id = $(" input[ name='scenie_id' ] ").val();
        var time = $(" input[ name='time' ] ").val();
        paging.init({
            openWait: true,
            url: '${pageContext.request.contextPath}/temperature/getTemCuPage.do', //地址
            //url:'../assets/tsconfig.json',
            elem: '#content', //内容容器
            params: { //发送到服务端的参数
                scenic_id: scenie_id,
                time: time,
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
</script>
<script>
    function edit(e) {
        layui.use('layer', function () {
            var $ = layui.jquery,
                layer = layui.layer;
            var code = e.getAttribute("data-id");
            var day = e.getAttribute("data-day");
            var hour = e.getAttribute("data-hour");
            layer.open({
                title: '修改景区',
                maxmin: true,
                type: 2,
                content: "${pageContext.request.contextPath}/temperature/getTemByTimeAndId.do?code=" + code + "&&day=" + day + "&&hour=" + hour,
                offset: ['50px', '50px'],
                area: ['40%', '40%'],
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

    function add(e) {
        layui.use('layer', function () {
            var $ = layui.jquery,
                layer = layui.layer;
            var code = e.getAttribute("data-id");
            var day = e.getAttribute("data-day");
            layer.open({
                title: '修改景区',
                maxmin: true,
                type: 2,
                content: "${pageContext.request.contextPath}/temperature/addForm.do?code=" + code + "&&day=" + day,
                offset: ['50px', '50px'],
                area: ['40%', '40%'],
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

    function del(e) {
        layer.alert('是否删除？？', {
            skin: 'layui-layer-molv' //样式类名  自定义样式
            , closeBtn: 1    // 是否显示关闭按钮
            , anim: 1 //动画类型
            , btn: ['是', '否'] //按钮
            , icon: 6    // icon
            , yes: function () {
                layui.use('layer', function () {
                    var $ = layui.jquery,
                        layer = layui.layer;
                    var id = e.getAttribute("data-id");
                    $.ajax({
                        type: "GET",
                        url: "${pageContext.request.contextPath}/temperature/delTem.do",
                        data: {id: id},
                        dataType: "text",
                        success: function (data) {
                            layer.msg("删除成功！")
                            location.reload();
                        }
                    })

                })
            }
            , btn2: function () {

            }
        });
    }
</script>
<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;

        var start = {
            min: '2017-07-01',
            format: 'YYYY-MM-DD' //日期格式
            , max: laydate.now()
            , istoday: false
            , choose: function (datas) {
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas //将结束日的初始值设定为开始日
            }
        };

        var end = {
            min: '2017-07-01',
            format: 'YYYY-MM-DD'
            , max: laydate.now()
            , istoday: false
            , choose: function (datas) {
                start.max = datas; //结束日选好后，重置开始日的最大日期
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

    });
</script>
<script>
    $(function () {
        $('select').searchableSelect();
    });
</script>

