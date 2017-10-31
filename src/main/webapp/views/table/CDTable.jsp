<%--
  Created by IntelliJ IDEA.
  User: snake
  Date: 2017/7/25
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath}/dist/js/echarts.min.js"></script>
<script src="${pageContext.request.contextPath}/dist/js/vintage.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/plugins/layui/layui.js"></script>
<h1>${enter_day} ${scenic_id}舒适度指数统计图</h1>
<div id="main" style="width:1000px;height:800px;"></div>
<script type="text/javascript">
    layui.use(['jquery',], function (args) {
        var $ = layui.jquery;

        var myChart = echarts.init(document.getElementById('main'));

        var option = {
            title: {
                text: '总舒适指数'
            },
            tooltip: {
                trigger: 'axis'
            },
            xAxis: {
                data: []
            },
            yAxis: {
                splitLine: {
                    show: false
                }
            },
            toolbox: {
                left: 'center',
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    restore: {},
                    saveAsImage: {}
                }
            },
            dataZoom: [{
                type: 'inside'
            }],
            visualMap: {
                top: 10,
                right: 0.1,
                pieces: [{
                    gt: 0,
                    lte: 0.1,
                    color: '#130d99'
                }, {
                    gt: 0.1,
                    lte: 0.2,
                    color: '#2c6bff'
                }, {
                    gt: 0.2,
                    lte: 0.3,
                    color: '#3fb4ff'
                }, {
                    gt: 0.3,
                    lte: 0.4,
                    color: '#36cc95'
                }, {
                    gt: 0.4,
                    lte: 0.5,
                    color: '#479948'
                }, {
                    gt: 0.5,
                    lte: 0.6,
                    color: '#779910'
                }, {
                    gt: 0.6,
                    lte: 0.7,
                    color: '#ffff00'
                }, {
                    gt: 0.7,
                    lte: 0.8,
                    color: '#996803'
                }, {
                    gt: 0.8,
                    color: '#7e0102'
                }],
                outOfRange: {
                    color: '#991006'
                }
            },
            series: {
                name: '天气舒适指数',
                type: 'line',
                data: []
            },
            markLine: {
                silent: true,
                data: [{
                    yAxis: 0.1
                }, {
                    yAxis: 0.2
                }, {
                    yAxis: 0.3
                }, {
                    yAxis: 0.4
                }, {
                    yAxis: 0.5
                }, {
                    yAxis: 0.6
                }, {
                    yAxis: 0.7
                }, {
                    yAxis: 0.8
                }, {
                    yAxis: 0.9
                }, {
                    yAxis: 1.0
                }, {
                    yAxis: 1.1
                }, {
                    yAxis: 1.2
                }, {
                    yAxis: 1.3
                }, {
                    yAxis: 1.4
                }, {
                    yAxis: 1.5
                }, {
                    yAxis: 1.6
                }, {
                    yAxis: 1.7
                }, {
                    yAxis: 1.8
                }]
            }
        }

        myChart.showLoading();	//数据加载完之前先显示一段简单的loading动画
        var time = [];		//时间数组
        var di = [];

        $.ajax({	//使用JQuery内置的Ajax方法
            type: "post",		//post请求方式
            async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url: "${pageContext.request.contextPath}/DataTwo/getCDInfor.do",	//请求发送到ShowInfoIndexServlet处
            data: {scenic_id: "${scenic_id}", enter_time: "${enter_day}"},		//请求内包含一个key为name，value为A0001的参数；服务器接收到客户端请求时通过request.getParameter方法获取该参数值
            dataType: "json",		//返回数据形式为json
            success: function (result) {
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                if (result != null && result.length > 0) {
                    for (var i = 0; i < result.length; i++) {
                        time.push(result[i].hour);		//挨个取出温度等值并填入前面声明的温度、湿度、压强等数组
                        di.push(result[i].cd);
                    }
                    myChart.hideLoading();	//隐藏加载动画

                    myChart.setOption({		//载入数据
                        xAxis: {
                            data: time	//填入X轴数据
                        },
                        series: [	//填入系列（内容）数据
                            {
                                // 根据名字对应到相应的系列
                                name: '总舒适指数'
                                , data: di
                            }
                        ]
                    });

                }
                else {
                    //返回的数据为空时显示提示信息
                    alert("图表请求数据为空，可能服务器暂未录入近五天的观测数据，您可以稍后再试！");
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


    });

</script>
