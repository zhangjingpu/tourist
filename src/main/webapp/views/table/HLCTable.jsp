<%--
  Created by IntelliJ IDEA.
  User: snake
  Date: 2017/7/25
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath}/dist/js/echarts.min.js"></script>
<script src="${pageContext.request.contextPath}/dist/js/vintage.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/plugins/layui/layui.js"></script>
<h1>${enter_day} ${scenic_name}人流承载量统计图</h1>
<div id="main" style="width:1000px;height:800px;"></div>
<script type="text/javascript">

    layui.use(['jquery', 'layer'], function (args) {
        var $ = layui.jquery;
        var layer = layui.layer;

        var app = new Object();
        var myChart = echarts.init(document.getElementById('main'));
        var option = {
            title: {
                text: '人流承载量',
                subtext: '纯属虚构'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#c5ccc7'
                    }
                }
            },
            legend: {
                data: ['人流承载量', '景区最大人流承载量', '实时人流量']
            },
            toolbox: {
                right: '0',
                bottom: '0',
                show: true,
                feature: {
                    dataView: {readOnly: false},
                    restore: {},
                    saveAsImage: {}
                }
            }, visualMap: {
                top: 90,
                right: 0,
                pieces: [{
                    gt: 0,
                    lte: 80,
                    color: '#42992b'
                }, {
                    gt: 90,
                    lte: 100,
                    color: '#995b00'
                }, {
                    gt: 100,
                    color: '#e10000'
                }],
                outOfRange: {
                    color: '#e10000'
                }
            },
            dataZoom: {
                show: false,
                start: 0,
                end: 100
            },
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: true,
                    data: []
                },
                {
                    type: 'category',
                    boundaryGap: true,
                    data: []
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    scale: true,
                    name: '人流承载量(百分制)',
                    max: 1,
                    min: 0,
                    boundaryGap: [0.2, 0.2]
                },
                {
                    type: 'value',
                    scale: true,
                    name: '实时人流量',
                    max: 200,
                    min: 0,
                    boundaryGap: [0.2, 0.2]
                }
            ],
            series: [
                {
                    name: '实时人流量',
                    type: 'bar',
                    xAxisIndex: 1,
                    yAxisIndex: 1,
                    data: []
                },
                {
                    name: '人流承载量',
                    type: 'line',
                    data: []
                }
            ]
        };

        var HLC = [];
        var HF = [];
        var time = [];
        var count = [];

        myChart.showLoading();	//数据加载完之前先显示一段简单的loading动画

        $.ajax({	//使用JQuery内置的Ajax方法
            type: "post",		//post请求方式
            async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url: "${pageContext.request.contextPath}/Data/getHLCInfor.do",	//请求发送到ShowInfoIndexServlet处
            data: {scenic_id: "${scenic_id}", enter_time: "${enter_day}"},		//请求内包含一个key为name，value为A0001的参数；服务器接收到客户端请求时通过request.getParameter方法获取该参数值
            dataType: "json",		//返回数据形式为json
            success: function (result) {
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                alert("123" + JSON.stringify(result));
                if (result != null && result.length > 0) {
                    for (var i = 0; i < result.length; i++) {
                        //time.push(result[i].time_hour);
                        // 挨个取出温度等值并填入前面声明的温度、湿度、压强等数组
                        if (result[i].hlc > 1) {
                            layer.msg("警告！超过景区最大人流承载量！")
                        }
                        HLC.push(result[i].hlc);
                        HF.push(result[i].hf);
                        count.push(result[i].time_hour);
                    }
                    app.count = count.length;
                    //alert(HF);

                    myChart.hideLoading();	//隐藏加载动画
                    option.xAxis[0].data = count;
                    option.xAxis[1].data = count;
                    option.series[0].data = HF;
                    option.series[1].data = HLC;
                    myChart.setOption(option);
                }
                else {
                    //返回的数据为空时显示提示信息
                    alert("图表请求数据为空，可能服务器暂未录入观测数据，您可以稍后再试！");
                    myChart.hideLoading();
                }

            },
            error: function (errorMsg) {
                //请求失败时执行该函数
                alert("图表请求数据失败，可能是服务器开小差了");
                myChart.hideLoading();
            }
        })

        myChart.setOption(option);	//载入图表


        setInterval(function () {
            $.ajax({	//使用JQuery内置的Ajax方法
                type: "post",		//post请求方式
                async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                url: "${pageContext.request.contextPath}/Data/getHLCInfor.do",	//请求发送到ShowInfoIndexServlet处
                data: {scenic_id: "${scenic_id}", enter_time: "${enter_day}"},		//请求内包含一个key为name，value为A0001的参数；服务器接收到客户端请求时通过request.getParameter方法获取该参数值
                dataType: "json",		//返回数据形式为json
                success: function (result) {
                    //请求成功时执行该函数内容，result即为服务器返回的json对象
                    var a = result;
                    //alert(a[1].time_hour);
                    //alert("123"+option.xAxis[0].data.length);
                    //alert(a[a.length-1].time_hour);
                    //alert("app.count:"+app.count+"option.xAxis[0].data.length:"+option.xAxis[0].data.length+"result:"+result.length+"time_hour"+a[a.length-1].time_hour);
                    //alert(result.length);
                    //alert("123" + JSON.stringify( result[result.length].time_hour));
                    if (result != null && result.length > 0) {
                        if (a[a.length - 1].time_hour == (app.count) && app.count < 24) {
                            //alert("修改");
                            app.count++;
                            //time.push(result[i].time_hour);		//挨个取出温度等值并填入前面声明的温度、湿度、压强等数组
                            var HLC = (a[a.length - 1].hlc);
                            var HF = (a[a.length - 1].hf);
                            var count_1 = (a[a.length - 1].time_hour);
                            //alert(result.length);
                            if (HLC > 1) {
                                layer.msg("警告！超过景区最大人流承载量！")
                            }

                            myChart.hideLoading();	//隐藏加载动画
                            option.xAxis[0].data.push(count_1);
                            //option.xAxis[1].data.push(count_1);
                            option.series[0].data.push(HF);
                            option.series[1].data.push(HLC);
                            myChart.setOption(option);
                        }
                    }
                    else {
                        //返回的数据为空时显示提示信息
                        alert("图表请求数据为空，可能服务器暂未录入观测数据，您可以稍后再试！");
                        myChart.hideLoading();
                    }

                },
                error: function (errorMsg) {
                    //请求失败时执行该函数
                    alert("图表请求数据失败，可能是服务器开小差了");
                    myChart.hideLoading();
                }
            })

            //myChart.setOption(option);
        }, (6000));


    });
</script>