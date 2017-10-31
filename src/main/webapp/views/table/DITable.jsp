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
<h1>${enter_day} ${scenic_name}舒适度指数统计图</h1>
<div id="main" style="width:1000px;height:800px;"></div>
<script type="text/javascript">
    layui.use(['jquery', 'layer'], function (args) {
        var $ = layui.jquery;
        var layer = layui.layer;

        var myChart = echarts.init(document.getElementById('main'));

        var option = {
                title: {
                    text: '天气舒适指数'
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
                    right: 5,
                    pieces: [{
                        gt: 0,
                        lte: 40,
                        color: '#130d99'
                    }, {
                        gt: 40,
                        lte: 45,
                        color: '#2c6bff'
                    }, {
                        gt: 45,
                        lte: 55,
                        color: '#3fb4ff'
                    }, {
                        gt: 55,
                        lte: 60,
                        color: '#36cc95'
                    }, {
                        gt: 60,
                        lte: 65,
                        color: '#479948'
                    }, {
                        gt: 65,
                        lte: 70,
                        color: '#779910'
                    }, {
                        gt: 70,
                        lte: 75,
                        color: '#ffff00'
                    }, {
                        gt: 75,
                        lte: 80,
                        color: '#996803'
                    }, {
                        gt: 80,
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
                            yAxis: 5
                        }, {
                            yAxis: 10
                        }, {
                            yAxis: 15
                        }, {
                            yAxis: 20
                        }, {
                            yAxis: 25
                        }, {
                            yAxis: 30
                        }, {
                            yAxis: 35
                        }, {
                            yAxis: 40
                        }, {
                            yAxis: 45
                        }, {
                            yAxis: 50
                        }, {
                            yAxis: 55
                        }, {
                            yAxis: 60
                        }, {
                            yAxis: 65
                        }, {
                            yAxis: 70
                        }, {
                            yAxis: 75
                        }, {
                            yAxis: 80
                        }, {
                            yAxis: 85
                        }, {
                            yAxis: 90
                        }]
                    }
                }

        myChart.showLoading();	//数据加载完之前先显示一段简单的loading动画
        var time = [];		//时间数组
        var di = [];

        $.ajax({	//使用JQuery内置的Ajax方法
            type: "post",		//post请求方式
            async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url: "${pageContext.request.contextPath}/Data/getDiInfor.do",	//请求发送到ShowInfoIndexServlet处
            data: {scenic_id: "${scenic_id}", enter_time: "${enter_day}"},		//请求内包含一个key为name，value为A0001的参数；服务器接收到客户端请求时通过request.getParameter方法获取该参数值
            dataType: "json",		//返回数据形式为json
            success: function (result) {
                //请求成功时执行该函数内容，result即为服务器返回的json对象
                alert("123" + JSON.stringify(result));
                if (result != null && result.length > 0) {
                    for (var i = 0; i < result.length; i++) {
                        if (result[i].di > 80) {
                            layer.msg("报警！舒适度指数超过最大阈值！")
                        }
                        time.push(result[i].time_hour);		//挨个取出温度等值并填入前面声明的温度、湿度、压强等数组
                        di.push(result[i].di);
                    }
                    alert(di);
                    myChart.hideLoading();	//隐藏加载动画

                    myChart.setOption({		//载入数据
                        xAxis: {
                            data: time	//填入X轴数据
                        },
                        series: [	//填入系列（内容）数据
                            {
                                // 根据名字对应到相应的系列
                                name: '天气舒适指数'
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
