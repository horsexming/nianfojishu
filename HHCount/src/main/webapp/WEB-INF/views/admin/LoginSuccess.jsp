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
<link type="image/png" href="http://tm-image.tianyancha.com/tm/b928c552e7d861b16141d5d40e950749.jpg" rel="shortcut icon">
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/indexstyle/pintuer.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/indexstyle/style.css">
<script  src="<%=request.getContextPath()%>/static/js/jquery-3.3.1.min.js"></script>
<link href="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"rel="stylesheet" />
<script src="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function del() {
		var msg = "您确定要删除吗？\n\n请确认";
		if (confirm(msg)==true){
			return true;
		}else{
			return false;
		}
	}
	
	function BatchRelease(){
	    //判断至少写了一项
	    var checkedNum = $("input[name='subcheck']:checked").length;
	    if(checkedNum==0){
	        alert("请至少选择一项!");
	        return false;
	    }
	    if(confirm("确定发布所选课程?")){
	    	var checkedList = new Array();
	        $("input[name='subcheck']:checked").each(function(){
	            checkedList.push($(this).val());
	        });
	        
	        $.ajax({
	            type:"POST",
	            url:"<%=request.getContextPath()%>/Course/BatchRelease",
	            data:{"fabuPiliang":checkedList.toString()},
	            success:function(data){
	                $("[name='checkbox2']:checkbox").attr("checked",false);
	                location.reload();//页面刷新
	            }           
	        });
	    	
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
	
</script>

</head>
<body>
<p>&nbsp;</p>
	<p align="right">
		<font size="3">欢迎<font size="3" color="green">${username}</font>
		来到系统&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		
	</p>
	<p>&nbsp;</p>
	<div class="container-layout">
	    <div class="line head clearfix ">
	    	<div class="head-mu xb10 xm10 xs12 xl12">
	        	<div class="head-logo xb2 xm2 xs4 xl4">
	            	<a href="#">读书育人</a>
	        	</div>
	        	<ul class="head-list list-inline xb7 xm7 xs7 xl12">
	            	<li><a href="<%=request.getContextPath()%>/Admin/toAdminIndex">首页</a></li>
	            	<li><a href="<%=request.getContextPath()%>/Admin/updateAdmin">个人信息</a></li>
	            	<li><a href="<%=request.getContextPath()%>/Course/toRelease">课程发布</a></li>	            	
	            	<li><a href="<%=request.getContextPath()%>/Course/AdminCount">课程统计</a></li>
	            	<li><a href="<%=request.getContextPath()%>/Course/PrivilegeBinding">权限绑定</a></li>
	            	<li><a href="<%=request.getContextPath()%>/Common/logOut">退出系统</a></li>
	        	</ul>      	       
	    	</div>
		</div>
	    <div class="index-body xb10 xm12 xs12 xl12">
	        <div class="line index-body-nr-left clearfix">
	            <div class="main-tit xl12 xs12 xm12 xb12">	           	        
	                <span class='mt_tit'>课程记录</span>	 
	                <span class='mt_tit'>&nbsp;</span>	
	                <span class="btn btn-default" onclick="BatchRelease()">
	                	<font color="#6CA6CD"> &nbsp;课程发布&nbsp;</font>
	               	</span>
	               	<span>
	                	&nbsp;
	               	</span>
	               	<span class="btn btn-default" onclick="StopCourse()">
	                	<font color="#6CA6CD"> &nbsp;课程停课&nbsp;</font>
	               	</span>		          
	                <table align="center" class="table table-hover">
	                	<tr>
	                		<td></td>
	                		<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                		<td>课程名称</td>
	                		<td>发布者</td>
	                		<td align="center">日阅读次数参考</td>	               		
	                		<td>状态</td>
	                		<td>提交时间</td>
	                		<td>首次发布时间</td>
	                		<td align="center" colspan="3">操作</td>
	                	</tr>
	                	<c:forEach items="${AdminCourseList}" var="AdminCourse" varStatus="status">
	                		<tr>	                			                		
		                		<td><input type="checkbox" id="subcheck" name="subcheck" value="${AdminCourse.id}"></td>	            	                			                               			                		                			
		                		<td>${status.index+1}</td>
		                		<c:if test="${AdminCourse.coursename.length()<=8}">
		                			<td>${AdminCourse.coursename}</td>
		                		</c:if>	
		                		<c:if test="${AdminCourse.coursename.length()>8}">
		                			<td>${AdminCourse.coursename.substring(0,8)}...</td>
		                		</c:if>	
		                		<td>${AdminCourse.writer}</td>
		                		<td align="center">${AdminCourse.courseRead}</td>
		                		<c:if test="${AdminCourse.courseState==0}">
		                			<td>
										<c:if test="${AdminCourse.state==0}">待发布</c:if>
									</td>
		                		</c:if>
		                		<c:if test="${AdminCourse.courseState==1}">
		                			<td>
		                				<c:if test="${AdminCourse.state==1}"><font color="green">已发布</font></c:if>
		                				<c:if test="${AdminCourse.state==0}"><font color="#FF0000">已停课</font></c:if>
									</td>
		                		</c:if>
		                		<td><fmt:formatDate value="${AdminCourse.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>	          
									<td><fmt:formatDate value="${AdminCourse.firstTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>	          
								<c:if test="${AdminCourse.state==0}">
									<td><a href="<%=request.getContextPath()%>/Course/LookCourse/${AdminCourse.id}">查看</a></td>
									<td><a href="<%=request.getContextPath()%>/Course/UpdateCourse/${AdminCourse.id}">修改</a></td>
			                		<td><a href="<%=request.getContextPath()%>/Course/DeleteCourse/${AdminCourse.id}" onclick="javascript:return del();">删除</a></td>
			                				                			            
		                		</c:if>
		                		<c:if test="${AdminCourse.state==1}">								
			                		<td colspan="3"><a href="<%=request.getContextPath()%>/Course/LookCourse/${AdminCourse.id}">查看</a></td>
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
									<li><a href="<%=request.getContextPath()%>/Admin/toAdminIndex?pn=1" aria-label="Previous">首页</a></li>
									<c:if test="${pageInfo.hasPreviousPage}">
										<li>
											<a href="<%=request.getContextPath()%>/Admin/toAdminIndex?pn=${pageInfo.pageNum-1}" aria-label="Previous">
												<span aria-hidden="true">上一页</span> 
											</a>
										</li>
									</c:if>
									<!-- <li aria-label="Previous">&nbsp;</li>	 -->				    
									<c:forEach items="${pageInfo.navigatepageNums}" var="page_Number">
										<c:if test="${page_Number==pageInfo.pageNum}">
											<li class="active"><a href="#">${page_Number}</a></li>
										</c:if>															    		
										<c:if test="${page_Number!=pageInfo.pageNum}">
											<li><a href="<%=request.getContextPath()%>/Admin/toAdminIndex?pn=${page_Number}">${page_Number}></a></li>
										</c:if>					  					    	
									</c:forEach>	
									<c:if test="${pageInfo.hasNextPage}">
										<li>
											<a href="<%=request.getContextPath()%>/Admin/toAdminIndex?pn=${pageInfo.pageNum+1}" aria-label="Next">
												<span aria-hidden="true">下一页</span> 
											</a>
										</li>
									</c:if>					    
									<li><a href="<%=request.getContextPath()%>/Admin/toAdminIndex?pn=${pageInfo.pages}" aria-label="Previous">尾页</a></li>							
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