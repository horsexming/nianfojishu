<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<meta name="applicable-device" content="pc,mobile">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>首页</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/indexstyle/pintuer.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/indexstyle/style.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/indexstyle/pintuer.js"></script>
<script  src="<%=request.getContextPath()%>/static/js/jquery-3.3.1.min.js"></script>
<link href="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"rel="stylesheet" />
<script src="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/indexstyle/common.js"></script>
<script type="text/javascript">
	function del() {
		var msg = "您确定要删除吗？\n\n请确认";
		if (confirm(msg)==true){
			return true;
		}else{
			return false;
		}
	}
	
	function StopCourse(){
	    //判断至少写了一项
	    var checkedNum = $("input[name='subcheck']:checked").length;
	    if(checkedNum==0){
	        alert("请至少选择一项!");
	        return false;
	    }
	    if(confirm("确定停止所选课程?")){
	    	var checkedList = new Array();
	        $("input[name='subcheck']:checked").each(function(){
	            checkedList.push($(this).val());
	        });
	        
	        $.ajax({
	            type:"POST",
	            url:"<%=request.getContextPath()%>/Course/StopCourse",
	            data:{"fabuPiliang":checkedList.toString()},
	            success:function(data){
	                $("[name='checkbox2']:checkbox").attr("checked",false);
	                location.reload();//页面刷新
	            },	        
	        });
	    	
	    }
	}
	
	function StopCourse(){
	    if(confirm("确定停止所选课程?")){
	    	var checkedList = new Array();
	        $("input[name='subcheck']:checked").each(function(){
	            checkedList.push($(this).val());
	        });
	        
	        $.ajax({
	            type:"POST",
	            url:"<%=request.getContextPath()%>/Course/StopCourse",
	            data:{"fabuPiliang":checkedList.toString()},
	            success:function(data){
	                $("[name='checkbox2']:checkbox").attr("checked",false);
	                location.reload();//页面刷新
	            },
	            error:function(data){
	                art.dialog.tips('上报失败!');
	            }
	        });
	    	
	    }
	}
</script>

</head>
<body>
	<p>&nbsp;</p>
	<p align="right"><font size="3">欢迎<font size="3" color="green">${username}</font>来到系统&nbsp;&nbsp;&nbsp;
		<a href="<%=request.getContextPath()%>/Common/logOut" class="btn btn-danger">退出系统</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</p>
	<p>&nbsp;</p>
	<div class="container-layout">
	    <div class="line head clearfix ">
	    	<div class="head-mu xb7 xm10 xs12 xl12">
	        	<div class="head-logo xb2 xm2 xs4 xl4">
	            	<a href="#">读书育人</a>
	        	</div>
	        	<ul class="head-list list-inline xb6 xm6 xs6 xl12">
	            	<li><a href="<%=request.getContextPath()%>/Admin/toAdminIndex">首页</a></li>
	            	<li><a href="<%=request.getContextPath()%>/Admin/updateAdmin">个人信息修改</a></li>
	            	<li><a href="<%=request.getContextPath()%>/Course/toRelease/${username}">课程发布</a></li>
	            	<li><a href="<%=request.getContextPath()%>/Course/AlreadyRelease">已发布课程</a></li>
	            	<li><a href="<%=request.getContextPath()%>/Course/AdminCount">课程统计</a></li>
	        	</ul>      	            
	    	</div>
		</div>
	    <div class="index-body xb7 xm12 xs12 xl12">
	        <div class="line index-body-nr-left clearfix">
	            <div class="main-tit xl12 xs12 xm12 xb12">	           	        
	                <span class='mt_tit'>课程记录</span>	 
	                <span class='mt_tit'>&nbsp;</span>	
	                <span class="btn btn-default" onclick="BatchRelease()">
	                	<font color="#6CA6CD"> &nbsp;批量停课&nbsp;</font>
	               </span>	          
	                <table align="center" class="table table-hover">
	                	<tr>
	                		<td></td>
	                		<td>序号</td>
	                		<td>课程名称</td>
	                		<td>发布者</td>
	                		<td>每日应读量</td>
	                		<td>状态</td>
	                		<td>提交时间</td>
	                		<td align="center" colspan="2">操作</td>
	                	</tr>
	                	<c:forEach items="${AdminCourseList}" var="AdminCourse" varStatus="status">
	                		<tr>	                		
	                			<td><input type="checkbox" id="subcheck" name="subcheck" value="${AdminCourse.id}"></td>	            	                              			                		                			
		                		<td>${status.index+1}</td>
		                		<td>${AdminCourse.coursename}</td>
		                		<td>${AdminCourse.writer}</td>
		                		<td>${AdminCourse.courseRead}</td>
		                		<td>
		                			<c:if test="${AdminCourse.state==1}"><font color="green">已发布</font></c:if>
									<c:if test="${AdminCourse.state==0}">待发布</c:if>
								</td>
		                		<td><fmt:formatDate value="${AdminCourse.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		                	
		                		<td><a href="<%=request.getContextPath()%>/Course/LookCourse/${AdminCourse.id}">查看</a></td>	          
								
		                		<c:if test="${AdminCourse.state==1}">
		                			<td><a href="<%=request.getContextPath()%>/Course/CourseState/${AdminCourse.id}">课程停止</a></td>	            
		                		</c:if>
	                		</tr>
	                	</c:forEach>
	                </table>
	                	<!-- 加载分页信息 -->
					<table align="center">	                
						<tr>
				    		<td>当前第<font color="blue">${pageInfo.pageNum}</font>页</td>
				    		<td>共有<font color="blue">${pageInfo.pages}</font>页</td>
				    		<td>共有<font color="blue">${pageInfo.total}</font>条记录</td>
				    	</tr> 
					</table>
					<div class="container">					
						<div class="col-md-12 col-md-offset-6">
							<nav aria-label="Page navigation">
								<ul class="pagination">
									<li><a href="<%=request.getContextPath()%>/Course/AlreadyRelease?pn=1" aria-label="Previous">首页</a></li>
									<c:if test="${pageInfo.hasPreviousPage}">
										<li>
											<a href="<%=request.getContextPath()%>/Course/AlreadyRelease?pn=${pageInfo.pageNum-1}" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
											</a>
										</li>
									</c:if>
									<li aria-label="Previous">&nbsp;</li>					    
									<c:forEach items="${pageInfo.navigatepageNums}" var="page_Number">
										<c:if test="${page_Number==pageInfo.pageNum}">
											<li class="active"><a href="#">${page_Number}</a></li>
										</c:if>															    		
										<c:if test="${page_Number!=pageInfo.pageNum}">
											<li><a href="<%=request.getContextPath()%>/Course/AlreadyRelease?pn=${page_Number}">${page_Number}></a></li>
										</c:if>					  					    	
									</c:forEach>
									
									<c:if test="${pageInfo.hasNextPage}">
										<li>
											<a href="<%=request.getContextPath()%>/Course/AlreadyRelease?pn=${pageInfo.pageNum+1}" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
											</a>
										</li>
									</c:if>
														    
									<li><a href="<%=request.getContextPath()%>/Course/AlreadyRelease?pn=${pageInfo.pages}" aria-label="Previous">尾页</a></li>							
								</ul>
							</nav>
						</div>
						<!-- 加载分页信息完毕 -->		
					</div>	
	            </div>
	        </div>
		</div> 
	</div>
</body>
</html>