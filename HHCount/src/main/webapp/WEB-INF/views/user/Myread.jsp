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
<title>我的阅读</title>
<link type="image/png" href="http://tm-image.tianyancha.com/tm/b928c552e7d861b16141d5d40e950749.jpg" rel="shortcut icon">
<script  src="<%=request.getContextPath()%>/static/js/jquery-3.3.1.min.js"></script>
<link href="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"rel="stylesheet" />
<script src="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/indexstyle/pintuer.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/indexstyle/style.css">
</head>
<script type="text/javascript">
	function StopCourse(){
	    //判断至少写了一项
	    var checkedNum = $("input[name='subcheck']:checked").length;
	    if(checkedNum==0){
	        alert("请选择一项!");
	        return false;
	    }
	    if(checkedNum>1){
	        alert("请选择一项!");
	        return false;
	    }	   	
		var ids = $("input[name='subcheck']:checked").val();	        
		findReadingID(ids);
		$("#readingNumber").modal({
			backdrop:"static"
		});	        	    		    
	}
				
	function findReadingID(id){
		$.ajax({
			type : "POST",
			url : "<%=request.getContextPath()%>/User/findReadingID",
			data : {id:id},
			dataType:"json",
			success : function(data) {				
				document.getElementById("Type1").value = data.reading;
				document.getElementById("Type2").value = data.id;
			}
		});	
		
	}
	
	function setreadingNumber(){
		var reading = document.getElementById("Type1").value
		var id = document.getElementById("Type2").value
        $.ajax({
            type:"POST",
            url:"<%=request.getContextPath()%>/User/setReadingnum",
            data:{"reading":reading,"id":id}, 
            success:function(data){  
            	$("#readingNumber").modal('hide');
                location.reload();//页面刷新
            }    
        });	    		    		
	}
</script>
<body>
<!-- 阅读量模态框 -->
	<div class="modal fade" id="readingNumber" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  		<div class="modal-dialog" role="document">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
        			<br>
        			<h4 align="center">阅读量设置</h4>
      			</div>
				<div class="modal-body">
        			<form class="form-horizontal">        	
						<div class="form-group">
						<br>
							<label class="col-sm-2 control-label">阅读量设置&nbsp;&nbsp;</label>
							<div class="col-sm-6">
								<label class="radio-inline">
									<input type="text" name="reading" id="Type1" value="" onkeyup="value=value.replace(/[^\d]/g,'')">
									<input type="hidden"  id="Type2" value="">																
								</label>				
							</div>
						</div>					
					</form>
      			</div>
      			
      			<div class="modal-footer">
      			<br>
        			<button type="button" class="btn btn-default btn-sm"  data-dismiss="modal">关闭</button>&nbsp;&nbsp;
        			<button type="button" class="btn btn-default btn-sm"  id="emp_save_btn" onclick="setreadingNumber()">提交</button>
        			&nbsp;&nbsp;&nbsp;&nbsp;
      			</div>
      			<br>
    		</div>
  		</div>
	</div>
	<p>&nbsp;</p>
	<p align="right"><font size="3">欢迎<font size="3" color="green">${username}</font>来到系统
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</p>
	<p>&nbsp;</p>
	<div class="container-layout">
	    <div class="line head clearfix ">
	    	<div class="head-mu xb10 xm10 xs12 xl12">
	        	<div class="head-logo xb2 xm2 xs4 xl4">
	            	<a href="#">读书育人</a>
	        	</div>
	        	<ul class="head-list list-inline xb6 xm6 xs6 xl12">
	            	<li><a href="#">首页</a></li>
	            	<li><a href="<%=request.getContextPath()%>/User/updateUser">个人信息</a></li>
	            	<li><a href="<%=request.getContextPath()%>/User/ReadCount">阅读统计</a></li>	
	            	<li><a href="<%=request.getContextPath()%>/Common/logOut">退出系统</a></li>       
	        	</ul>         
	    	</div>
		</div>
	    <div class="index-body xb10 xm12 xs12 xl12">
	        <div class="line index-body-nr-left clearfix">
	            <div class="main-tit xl12 xs12 xm12 xb12">
	                <span class='mt_tit'>阅读记录</span>
	                <span class='mt_tit'>&nbsp;</span>	
	                <span class="btn btn-default" onclick="StopCourse()">
	                	<font color="#6CA6CD"> &nbsp;添加阅读次数&nbsp;</font>
	               	</span>	                
	                <table align="center" class="table table-hover">
	                	<tr>              
	                		<td></td>
	                		
	                		<td>课程名称</td>
	                		<td>发布者</td>
	                		<td align="center">日阅读次数参考</td>
	                		<td align="center">实应阅读次数</td>
	                		<td align="center" >累计阅读次数</td>
	                		<td>课程状态</td>
	                		<td>最新发布时间</td>
	                		<td>最早可阅读时间</td>
	                		<td>阅读</td>
	                		<td align="center">添加阅读次数</td>	              
	                		<td>历史记录</td>
	                		
	                	</tr>
	                	<c:forEach items="${myread}" var="myread" varStatus="status">
	                		<tr>
	                			<td>
		                			<c:if test="${myread.state=='1'}">
		                				<input type="checkbox" id="subcheck" name="subcheck" value="${myread.id}">
		                			</c:if>
									<c:if test="${myread.state=='0'}">&nbsp;</c:if>
								</td>	                					            		         
		                		<c:if test="${myread.coursename.length()<=10}">
		                			<td>${myread.coursename}</td>
		                		</c:if>	
		                		<c:if test="${myread.coursename.length()>10}">
		                			<td>${myread.coursename.substring(0,10)}...</td>
		                		</c:if>	          		
		                		<td>${myread.writer}</td>
		                		<td align="center">${myread.courseRead}</td>
		                		<c:if test="${myread.myrDa==0}">
		                			<td align="center">${myread.courseRead}</td>
		                		</c:if>
		                		<c:if test="${myread.myrDa!=0}">
		                			<td align="center">${myread.myrDa*myread.courseRead}</td>
		                		</c:if>		             
		                		<td align="center">${myread.reading}</td>
		                		<td>
		                			<c:if test="${myread.state=='1'}"><font color="green">进行中</font></c:if>
									<c:if test="${myread.state=='0'}">已停课</c:if>
								</td>
								<c:if test="${myread.publishTime==null}">
									<td>
			                			课程已停课
			                		</td>
								</c:if>	
								<c:if test="${myread.publishTime!=null}">
									<td>
			                			<fmt:formatDate value="${myread.publishTime}" pattern="yyyy-MM-dd"/>
			                		</td>
								</c:if>	
								<td><fmt:formatDate value="${myread.myrDate}" pattern="yyyy-MM-dd"/></td>                			                		
		                		<td>
		                			<a href="<%=request.getContextPath()%>/User/LookCourse/${myread.id}?pn=${pageInfo.pageNum}">阅读</a>		                		                			
		                		</td>		             
		                		<td align="center">
		                			<c:if test="${myread.state=='1'}">
										<a class="btn btn-primary btn-sm" href="<%=request.getContextPath()%>/User/SetReadingN/${myread.rid}?pn=${pageInfo.pageNum}">&nbsp;&nbsp;+&nbsp;&nbsp;</a>		                			</c:if>
									<c:if test="${myread.state=='0'}">&nbsp;</c:if>	                												
								</td>
								<td><a href="<%=request.getContextPath()%>/User/oudReader/${myread.rid}">阅读记录</a></td>			                		                		           	          			
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
									<li><a href="<%=request.getContextPath()%>/User/Myread?pn=1" aria-label="Previous">首页</a></li>
									<c:if test="${pageInfo.hasPreviousPage}">
										<li>
											<a href="<%=request.getContextPath()%>/User/Myread?pn=${pageInfo.pageNum-1}" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
											</a>
										</li>
									</c:if>
									<!-- <li aria-label="Previous">&nbsp;</li> -->					    
									<c:forEach items="${pageInfo.navigatepageNums}" var="page_Number">
										<c:if test="${page_Number==pageInfo.pageNum}">
											<li class="active"><a href="#">${page_Number}</a></li>
										</c:if>
															    		
										<c:if test="${page_Number!=pageInfo.pageNum}">
											<li><a href="<%=request.getContextPath()%>/User/Myread?pn=${page_Number}">${page_Number}></a></li>
										</c:if>					  					    	
									</c:forEach>
									
									<c:if test="${pageInfo.hasNextPage}">
										<li>
											<a href="<%=request.getContextPath()%>/User/Myread?pn=${pageInfo.pageNum+1}" aria-label="Next">
												<span aria-hidden="true">&raquo;</span>
											</a>
										</li>
									</c:if>
														    
									<li><a href="<%=request.getContextPath()%>/User/Myread?pn=${pageInfo.pages}" aria-label="Previous">尾页</a></li>							
								</ul>
							</nav>
						</div>
						<!-- 加载分页信息完毕 -->		                
	            </div>
	        </div>
		</div> 
	</div>
</body>
</html>