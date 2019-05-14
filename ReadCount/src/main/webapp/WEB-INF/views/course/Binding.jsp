<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>读书系统</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/read/vendor/simple-line-icons/css/simple-line-icons.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/read/vendor/font-awesome/css/fontawesome-all.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/read/css/styles.css">
    <script  src="<%=request.getContextPath()%>/static/js/jquery-3.3.1.min.js"></script>
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
function StopCourse(){
	var id = document.getElementById("CoursePowerID").value;
	if(id.trim()==""){
		return false;
	}   
    if(confirm("确定绑定所选用户?")){
    	var checkedList = new Array();
        $("input[name='ids']:checked").each(function(){
            checkedList.push($(this).val());
        });                    
       $.ajax({
            type:"POST",
            url:"<%=request.getContextPath()%>/Course/updatePoorState",
            data:{"CoursePower":checkedList.toString(),"id":id},
            success:function(data){
                location.reload();//页面刷新
            },	        
        });
    	
    }
}

$(function() {
    $("#selectAll").click(function() {
        $(":checkbox[name='ids']").prop("checked", this.checked); // this指代的你当前选择的这个元素的JS对象
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
            <font size="5">权限绑定</font>
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
                    <li class="nav-title">权限绑定</li>
                    <c:forEach items="${CourseBuding}" var="CourseB" varStatus="state">             
                    	<c:if test="${CourseB.coursename.length()<=18}">
	                    	<li class="nav-item">
	                    	                   	
		                    	<a href="<%=request.getContextPath()%>/Course/UpadtePower/${CourseB.id}?pn=${pageInfo.pageNum}" class="nav-link active">     		
		                    		<i></i>${CourseB.coursename}	
		                    	</a>
	                    	</li>
                    	</c:if> 
                    	<c:if test="${CourseB.coursename.length()>18}">
	                    	<li class="nav-item">
	                    	<i></i>                   	
		                    	<a href="<%=request.getContextPath()%>/Course/UpadtePower/${CourseB.id}" class="nav-link active">     		
		                    		<i></i>${CourseB.coursename.substring(0,16)}...	
		                    	</a>
	                    	</li>
                    	</c:if> 
                    </c:forEach>
                    <li class="nav-item">
                    	当前第<font color="blue">${pageInfo.pageNum}</font>页&nbsp;&nbsp;
				    	共有<font color="blue">${pageInfo.pages}</font>页&nbsp;&nbsp;
				    	共有<font color="blue">${pageInfo.total}</font>条记录
                    </li> 
                    <li class="nav-item">
                    	<a href="<%=request.getContextPath()%>/Course/PrivilegeBinding?pn=1" aria-label="Previous">首页</a>
                    	<c:forEach items="${pageInfo.navigatepageNums}" var="page_Number">
							<c:if test="${page_Number==pageInfo.pageNum}">
								<a href="#">${page_Number}</a>
							</c:if>															    		
							<c:if test="${page_Number!=pageInfo.pageNum}">
								<a href="<%=request.getContextPath()%>/Course/PrivilegeBinding?pn=${page_Number}">${page_Number}</a>
							</c:if>					  					    	
						</c:forEach>
                    	<a href="<%=request.getContextPath()%>/Course/PrivilegeBinding?pn=${pageInfo.pages}" aria-label="Previous">尾页</a>
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
                                <font size="4">请选择用户:</font>
				               	<span class="btn btn-default">
				                	<a href="<%=request.getContextPath()%>/Admin/toAdminIndex" >
			                   			<font color="#6CA6CD">返回首页</font>
			                   		</a>
				               	</span>
				               	<span class="btn btn-default" onclick="StopCourse()">
				                	<font color="#6CA6CD"> &nbsp;提交绑定&nbsp;</font>
				               	</span>
                            </div>
                            <div class="card-body p-0">      
                                <div class="justify-content-around mt-4 p-4 bg-light d-flex border-top d-md-down-none">
                                	<form action="" method="post">
			                            <input type="hidden" value="${CoursePowerID}" id="CoursePowerID">                                   
			                                <table width="100%" class="table table-hover">                                  
			                           				<tr>
				                           				<td colspan="3" align="center">
				                           				<span>${powCoursename }</span>	                           	
				                           				</td>
			                           				</tr>
			                                       <tr>
														<td>&nbsp;&nbsp;&nbsp;&nbsp;全选&nbsp;&nbsp;&nbsp;&nbsp;
															<input type="checkbox" id="selectAll">
														</td>
														<td>用户名</td>
														<td>真实姓名</td>
													</tr> 
													<c:forEach items="${CoursePower}" var="CoursePower" varStatus="status">
														<tr>
															<td align="center">
																<input type="checkbox" id="subcheck" name="ids" value="${CoursePower.username}"
																<c:if test="${CoursePower.state=='1'}">checked="checked"</c:if>							
																>
															</td>
															<td>
																<span>${CoursePower.username}</span>
															</td>
															<td>
																<span>${CoursePower.realname}</span>
															</td>					  						  
													  	</tr> 	
													</c:forEach>                             						
			                                </table>
			                            </form>
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
