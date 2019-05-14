<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>读书系统</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/read/vendor/simple-line-icons/css/simple-line-icons.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/read/vendor/font-awesome/css/fontawesome-all.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/read/css/styles.css">
    <script src="<%=request.getContextPath()%>/read/vendor/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ui/echarts/echarts-3.2.3/dist/echarts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ui/echarts/echarts-3.2.3/options/cfgopts2.js"></script>
	<script src="<%=request.getContextPath()%>/read/vendor/popper.js/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/read/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/read/vendor/chart.js/chart.min.js"></script>
	<script src="<%=request.getContextPath()%>/read/js/carbon.js"></script>
	<script src="<%=request.getContextPath()%>/read/js/demo.js"></script>
</head>
<script type="text/javascript"> 	
	function showTime() {
		var datetime = new Date();
		var h = datetime.getHours();
		var m = datetime.getMinutes();
		var s = datetime.getSeconds();
		if (h < 10) {
			h = "0" + h;
		}
		if (m < 10) {
			m = "0" + m;
		}
		if (s < 10) {
			s = "0" + s;
		}
		var hour = document.getElementById("hour");
		var minute = document.getElementById("minute");
		var seconds = document.getElementById("second");

		hour.innerHTML = h;
		minute.innerHTML = m;
		seconds.innerHTML = s;
		setTimeout(showTime, 1000);
	}
</script>
 <script type="text/javascript">
        $().ready(
                function() {
            var myChart = echarts.init(document.getElementById('main'));
            var datetime = new Date();
            var year = datetime.getFullYear()-1;
            //图表显示提示信息
           myChart.showLoading();
            //定义图表options
            var options = {
                title: {
                    text: year+"年阅读图表",
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: []
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: false
                    }
                },
                calculable: true,
                xAxis: [
                    {
                        type: 'category',
                        data: []
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        splitArea: { show: true }
                    }
                ],
                series: []
                };

                    //通过Ajax获取数据
                    $.ajax({
                        type : "post",
                        async : false, //同步执行
                        url : "showEchartLine1",
                        dataType : "json", //返回数据形式为json
                        success : function(result) {
                            if (result) {
                                //将返回的category和series对象赋值给options对象内的category和series
                                //因为xAxis是一个数组 这里需要是xAxis[i]的形式
                                    options.xAxis[0].data = result.category;
                                    options.series = result.series;
                                    options.legend.data = result.legend;
                                    myChart.hideLoading();
                                    myChart.setOption(options);
                            }
                        },
                        error : function(errorMsg) {
                            alert("图表请求数据失败啦!");
                        }
                    });

                });
    </script>
<body class="sidebar-fixed header-fixed" onload="showTime()">
<div class="page-wrapper">
    <nav class="navbar page-header">
        <a href="#" class="btn btn-link sidebar-mobile-toggle d-md-none mr-auto">
            <i class="fa fa-bars"></i>
        </a>

        <a class="navbar-brand" href="#">
            <font size="5">阅读图表</font>
        </a>

        <a href="#" class="btn btn-link sidebar-toggle d-md-down-none">
            <i class="fa fa-bars"></i>
        </a>

        <ul class="navbar-nav ml-auto">
            <li class="nav-item d-md-down-none">
                             北京时间：
				<span id="hour"></span>时
				<span id="minute"></span>分
				<span id="second"></span>秒	
            </li>
			<li class="nav-item dropdown">
                <a href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                </a>
            </li>
            <li class="nav-item d-md-down-none">
                <a>
                   <span class="small ml-1 d-md-down-none">欢迎用户：${username}</span>
                </a>
            </li>
            <li class="nav-item dropdown">
                <a href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                </a>
            </li>
        </ul>
    </nav>

    <div class="main-container">
        <div class="sidebar">
            <nav class="sidebar-nav">
                <ul class="nav">
                    <li class="nav-title">读书系统</li>

                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/User/Myread" class="nav-link active">
                            <i></i>首页
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/User/updateUser" class="nav-link active">
                            <i></i>个人信息
                        </a>
                    </li>
                     <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/User/ReadCount" class="nav-link active">
                            <i></i>阅读统计
                        </a>
                    </li>
                     <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/User/tubiao" class="nav-link active">
                            <i></i>图表统计
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/Common/logOut" class="nav-link active">
                            <i></i>退出系统
                        </a>
                    </li>                     
                </ul>
            </nav>
        </div>

        <div class="content">
            <div class="container-fluid">
                <div class="row ">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <font size="4">本年阅读图表</font>
                                <a href="<%=request.getContextPath()%>/User/tubiao">
								   <font color="#6CA6CD">&nbsp;&nbsp;本年图表&nbsp;</font>
								</a>
								<a href="<%=request.getContextPath()%>/User/tubiao1">
								   <font color="#6CA6CD">&nbsp;&nbsp;去年图表&nbsp;</font>
								</a>
                                <a href="<%=request.getContextPath()%>/User/Myread">
								   <font color="#6CA6CD">&nbsp;&nbsp;返回&nbsp;</font>
								</a>
                            </div>
                            <div class="card-body p-0">      
                                <div class="justify-content-around mt-4 p-4 bg-light d-flex border-top d-md-down-none">
                                	<div id="main"
        							style="height: 400px;width :800px; border: 1px solid #ccc; padding: 10px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>                   
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
