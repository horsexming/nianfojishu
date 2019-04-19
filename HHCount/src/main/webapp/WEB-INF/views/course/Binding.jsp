<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="UTF-8">
        <title>权限绑定</title>
        <link href="<%=request.getContextPath()%>/power/css/style.css" rel="stylesheet" />
        <link type="image/png" href="http://tm-image.tianyancha.com/tm/b928c552e7d861b16141d5d40e950749.jpg" rel="shortcut icon">	
        <script  src="<%=request.getContextPath()%>/power/js/jquery.js"></script>
        <script  src="<%=request.getContextPath()%>/power/js/jquery.SuperSlide.2.1.1.js"></script>
    </head>
<style type="text/css">
	a { transition:0.5s; }
	a:hover { color:blue; }
</style>
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
    <body>
        <div class="suCai17-content">
            <div class="suCai17-background clearfix">
                <div class="suCai17-nav hd">                   
                    <ul class="suCai17-nav-list">
	                    <li class="suCai17-item on">                   	
		                  <font size="4">课程绑定</font>  		
	                    </li>
	                    <li class="suCai17-item on">
	                   		<a href="#" >————————————————————————————————————————</a>
	                   </li>	                                    	                    
                    	<c:forEach items="${CourseBuding}" var="CourseB" varStatus="state">             
                    		<c:if test="${CourseB.coursename.length()<=18}">
	                    		<li class="suCai17-item on">                   	
		                    		<a href="<%=request.getContextPath()%>/Course/UpadtePower/${CourseB.id}?pn=${pageInfo.pageNum}">     		
		                    			${CourseB.coursename}	
		                    		</a>
	                    		</li>
                    		</c:if> 
                    		<c:if test="${CourseB.coursename.length()>18}">
	                    		<li class="suCai17-item on">                   	
		                    		<a href="<%=request.getContextPath()%>/Course/UpadtePower/${CourseB.id}">     		
		                    			${CourseB.coursename.substring(0,16)}...	
		                    		</a>
	                    		</li>
                    		</c:if> 
                    	</c:forEach>                   	
                    	<li class="suCai17-item on">
                    		当前第<font color="blue">${pageInfo.pageNum}</font>页&nbsp;&nbsp;
				    		共有<font color="blue">${pageInfo.pages}</font>页&nbsp;&nbsp;
				    		共有<font color="blue">${pageInfo.total}</font>条记录
                    	</li> 
                    	  
                    	<li class="suCai17-item on">
                    		<a href="<%=request.getContextPath()%>/Course/PrivilegeBinding?pn=1" aria-label="Previous">首页</a>
                    		<c:forEach items="${pageInfo.navigatepageNums}" var="page_Number">
								<c:if test="${page_Number==pageInfo.pageNum}">
									<a href="#">${page_Number}</a>
								</c:if>															    		
								<c:if test="${page_Number!=pageInfo.pageNum}">
									<a href="<%=request.getContextPath()%>/Course/PrivilegeBinding?pn=${page_Number}">${page_Number}></a>
								</c:if>					  					    	
							</c:forEach>
                    		<a href="<%=request.getContextPath()%>/Course/PrivilegeBinding?pn=${pageInfo.pages}" aria-label="Previous">尾页</a>
                    	</li>                             
                    </ul>
                    <ul class="suCai17-nav-list">
                     <li class="suCai17-item on">
	                   		<a href="#" >————————————————————————————————————————</a>
	                   </li>
	                   <li class="suCai17-item on">
	                   		<a href="<%=request.getContextPath()%>/Admin/toAdminIndex" >
	                   		<font color="#0A0A0A" style="text-decoration: underline;">返回首页</font>
	                   		</a>
	                   </li>
                    </ul>
                </div>             
                <div class="suCai17-info bd">                                                                                          
                   <ul>
                        <li> 
                        	<h5 align="right"><button onclick=" StopCourse()">提交绑定</button></h5>
                        	<h2>请选择用户:</h2>                     
                            <div class="table-pub">
                            <form action="" method="post">
                            <input type="hidden" value="${CoursePowerID}" id="CoursePowerID">                                   
                                <table width="100%">                                  
                           				<tr>
	                           				<td colspan="3">
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
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        
    </body>
</html>
