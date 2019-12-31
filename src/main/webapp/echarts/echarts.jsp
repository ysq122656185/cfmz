<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html lang="en">
<head>
    <title>Document</title>
    <!-- 引入 ECharts 文件 -->
    <script src="echarts.min.js"></script>
    <script src="china.js"></script>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script src="${pageContext.request.contextPath}/statics/boot/js/jquery-2.2.1.min.js"></script>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));
    $(function () {
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: 'ECharts 入门示例'
            },
            tooltip: {},//展示对应光标移入的详细信息
            legend: {
                data: ['男性', '女性']

            },
            xAxis: {
                data: ["第一周", "第二周", "第三周"]
            },
            yAxis: {},
            series: [
                {
                    name: '男性',
                    type: 'line'
                },
                {
                    name: '女性',
                    type: 'line'
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        $.ajax({
            url: "${pageContext.request.contextPath}/user/showByDate",
            dataType: "json",
            type: "post",
            success: function (data) {
            }
        });
    })
    var goEasy = new GoEasy({
        appkey: "BS-65b2ad39928f4728989acd95af033c73"
    });
    goEasy.subscribe({
        channel: "man",
        onMessage: function (message) {

            var data = message.content;
            var d = JSON.parse(data);
            var manv = '';
            var womanv = '';
            for (var key in d) {
                if (key == 'man') {
                    var manv = d[key]
                }
                if (key == 'woman') {
                    var womanv = d[key]
                }
            }
            console.log(d)
            myChart.setOption({
                series: [
                    {
                        data: manv
                    }, {
                        data: womanv
                    },
                ]
            });
        }
    });

</script>
</body>
</html>