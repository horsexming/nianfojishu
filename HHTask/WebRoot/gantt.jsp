<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<SCRIPT type="text/javascript">
	var gantt_pageNum = "${param.urlPageNum}";


	var gantt_addFlag ="${param.errorMessage}";
	var index=gantt_addFlag.indexOf("add");
	
	//var index1=gantt_addFlag.indexOf("deltrue");
<%--	var index1=gantt_addFlag.indexOf("deltrue");--%>
<%--	--%>

   var gantt_add;
	if(index>=0){
		gantt_add=true;
	}
	
	</SCRIPT>
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
		<link rel="stylesheet" href="css/style1.css" />
        <link rel="stylesheet" href="css/bootstrap.css" />
        <link rel="stylesheet" href="css/prettify.css" />
		<style type="text/css">
			body {
				font-family: Helvetica, Arial, sans-serif;
				font-size: 13px;
				padding: 0 0 50px 0;
			}
			.contain {
				width: 98%;
				margin: 0 auto;
			}
			h1 {
				margin: 40px 0 20px 0;
			}
			h2 {
				font-size: 1.5em;
				padding-bottom: 3px;
				border-bottom: 1px solid #DDD;
				margin-top: 50px;
				margin-bottom: 25px;
			}
			table th:first-child {
				width: 150px;
			}
			
			
			
			#dialog-overlay {

				 width:100%; 
				 height:100%;
				 filter:alpha(opacity=50); 
				 -moz-opacity:0.5; 
				 -khtml-opacity: 0.5; 
				 opacity: 0.5; 
				 position:absolute; 
				 background:#000; 
				 top:0; left:0; 
				 z-index:3000; 
				 display:none;
				
			}

			#dialog-box {
				 -webkit-box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
				 -moz-box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
				 -moz-border-radius: 5px;
				 -webkit-border-radius: 5px;
				 
				 background:#eee;
				 width:400px; 
				 position:absolute; 
				 z-index:5000; 
				 display:none;
				 left:600px;
				 top:200px;
			}

			#dialog-box .dialog-content {
				 text-align:left; 
				 padding:10px; 
				 margin:13px;
				 color:#666; 
				 font-family:arial;
				 font-size:11px; 
			}

			a.button{
				 margin:10px auto 0 auto;
				 text-align:center;
				 background-color: #e33100;
				 display:inline-block;
				 width:50px;
				 padding: 5px 10px 6px;
				 color: #fff;
				 text-decoration: none;
				 font-weight: bold;
				 line-height: 1;
				 -moz-border-radius: 5px;
				 -webkit-border-radius: 5px;
				 -moz-box-shadow: 0 1px 3px rgba(0,0,0,0.5);
				 -webkit-box-shadow: 0 1px 3px rgba(0,0,0,0.5);
				 text-shadow: 0 -1px 1px rgba(0,0,0,0.25);
				 border-bottom: 1px solid rgba(0,0,0,0.25);
				 position: relative;
				 cursor: pointer;
			 }
			 #submitBtn,#updateBtn,#deleteBtn,#submitUpdBtn,#addInputText,#submitaddSon{
				 margin:10px auto 0 auto;
			   	  left: 100px;
				 text-align:center;
				 background-color: #e33100;
				 display:inline-block;
				 padding: 5px 10px 6px;
				 color: #fff;
				 text-decoration: none;
				 font-weight: bold;
				 line-height: 1;
				 -moz-border-radius: 5px;
				 -webkit-border-radius: 5px;
				 -moz-box-shadow: 0 1px 3px rgba(0,0,0,0.5);
				 -webkit-box-shadow: 0 1px 3px rgba(0,0,0,0.5);
				 text-shadow: 0 -1px 1px rgba(0,0,0,0.25);
<%--				 border-bottom: 1px solid rgba(0,0,0,0.25);--%>
				 position: relative;
				 cursor: pointer;
			 }
			 
			#dialog-box .dialog-content p {
			 	font-weight:700; margin:0;
			}

			#dialog-box .dialog-content ul {
				 margin:10px 0 10px 20px; 
				 padding:0; 
				 height:50px;
			}
			
			
			
			
<%--			多填样式--%>
td:hover .qs_ul {
	display: block;
}

.qs_ul {
	display: none;
	border: 1px solid #999;
	list-style: none;
	margin: 0;
	padding: 0;
	position: absolute;
	width: auto;
	background: #CCC;
	color: green;
}
.ztree li a {
	color: #fff;
}

/* 带复选框的下拉框 */
ul li {
	list-style: none;
	padding: 0px;
	margin: 0px;
}

.select_checkBox {
	border: 0px solid red;
	position: relative;
	display: inline-block;
}

.chartQuota {
	height: 23px;
	float: left;
	display: inline-block;
	border: 0px solid black;
	position: relative;
}

.chartOptionsFlowTrend {
	z-index: 300;
	background-color: white;
	border: 1px solid gray;
	display: none;
	position: absolute;
	left: 0px;
	top: 23px;
	width:300px;
}

.chartOptionsFlowTrend ul {
	float: left;
	padding: 0px;
	margin: 5px;
}

.chartOptionsFlowTrend li {
	display: block;
	position: relative;
	left: 0px;
	margin: 0px;
	clear: both;
}

.chartOptionsFlowTrend li * {
	float: left;
}

<%--a:-webkit-any-link {--%>
<%--	color: -webkit-link;--%>
<%--	text-decoration: underline;--%>
<%--	cursor: auto;--%>
<%--}--%>

.chartQuota p a {
	float: left;
	height: 21px;
	outline: 0 none;
	border: 1px solid #ccc;
	line-height: 22px;
	padding: 0 5px;
	overflow: hidden;
	background: #eaeaea;
	color: #313131;
	text-decoration: none;
}

.chartQuota p {
	margin: 0px;
	folat: left;
	overflow: hidden;
	height: 23px;
	line-height: 24px;
	display: inline-block;
}

.chartOptionsFlowTrend p {
	height: 23px;
	line-height: 23px;
	overflow: hidden;
	position: relative;
	z-index: 2;
	background: #fefbf7;
	padding-top: 0px;
	display: inline-block;
}
.chartOptionsFlowTrend p a {
	border: 1px solid #fff;
	margin-left: 15px;
	color: #2e91da;
}
.duoxuaselect_div{
	width:200px;
	inline-block;top:30px;
	
}
		</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;" align:center;>
			<div align="center">
					
					<div class="contain">

			<h1>
				项目进度甘特图
			</h1>
			<span style="color:red;" id="error">
				<s:if test="#session.errorMessage=='addtrue'">
					添加成功
				</s:if>
				<s:elseif test="#session.errorMessage=='addfalse'">
					添加失败
				</s:elseif>
				<s:elseif test="#session.errorMessage=='deltrue'">
					删除成功
				</s:elseif>
				<s:elseif test="#session.errorMessage=='delfalse'">
					删除失败
				</s:elseif>
				<s:elseif test="#session.errorMessage=='updtrue'">
					修改成功
				</s:elseif>
				<s:elseif test="#session.errorMessage=='updfalse'">
					修改失败
				</s:elseif>
			
			</span>
			<!--显示图-->
			<div class="gantt" id="ganttChart" ></div>
			<div>
				<input type="button" onclick="add()" value="添加" class="input searchBtn" style="width: 80px;height:50px;font-weight:bold;">
<%--				<input type="button" onclick="del()" value="删除"  class="input" style="width: 80px;">--%>
			</div>
			
			
		
<div id="dialog-overlay"></div>
<div id="dialog-box">
 <div class="dialog-content">
 
   
 <div id="dialog-message"  >
							  <a href="javascript:;" class="button"  onclick="closeDia()"  style=left:300px;top:-32px;" >关闭</a>
</div>
 <form  method="post"  id="form" name="form">
 <table class="table table-striped"  id="addTable">
 					
					<tr>
						<th>
							<input type="hidden" id="ganttId" name="gantt.id"/>
							<code id="ganttData-item-id" >
							项目名称：
							</code><br/>
							<input type="text" name="gantt.projectName" id="projectName" />
							<input type="hidden" name="urlPageNum" id="pageNum">
						</th>		 
				  </tr>	
				  	
				  <tr>	
						<th id="peopleTh">
							<code>
							项目负责人 ：
							</code><br/>
							<select id="deptname" style="width: 120px;" onmousemove="createDept0('deptname')"
								onchange="userlist(0)">
									<option value="0">
										请选择部门
									</option>
							</select>
								<select id="username" name="gantt.peopleStr" style="width: 150px;">
									<option value="0">
										请先选择部门
									</option>
								</select>
							<input type="hidden" name="gantt.peopleIds" id="peopleIds">
						</th>
					
				</tr>
				<tr>
						<th>
						<!--     -->
							<code>
							开始时间：
							</code><br/>
							<input class="Wdate" type="text" name="gantt.startDate"
									value="${startDate}" size="15"
									 id="startTime"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
		
							
						</th>
				</tr>
				<tr>
						<th>
							<code>
							结束时间：
							</code><br/>
							
							<input class="Wdate" type="text" name="gantt.endDate"
									value="${endDate}" size="15"
									 id="endTime"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
	
							
						</th>
				</tr>
				<tr>
					<th>
						<code>状态:
						</code><br/>
						<select id="status" name="gantt.status" >
							<option value="未启动">
								未启动
							</option>
							<option value="进行中">
								进行中
							</option>
							<option value="完成">
								完成
							</option>
						</select>
					</th>
					<input type="reset" style="display:none;" /> 
				</tr>
				<tr>
					<th>
						<code>内容详情:
						</code><br/>
						<input type="text" name="gantt.detail" id="detail" />
						
					</th>
					<input type="reset" style="display:none;" /> 
				</tr>
			</table>
     <input type="button "   id="submitBtn" style="display:none;left:0px; width:100px;" value="添加"  onclick="submitAdd()">
     
<%--     <input type="button"   id="updateBtn"  value="修改"  onclick="update()"> --%>
     <input type="button"   id="submitUpdBtn"  value="修改"  onclick="submitUpd()"> 
      <input type="button"   id="submitaddSon"  value="添加子项目"  onclick="addSon()"> 
     <input type="button"   id="deleteBtn"  value="删除"   onclick="del()"> 

     
     

</div>
</form>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/jquery.fn.gantt1.js" charset ="UTF-8"></script>
	<script src="js/bootstrap-tooltip.js"></script>
	<script src="js/bootstrap-popover.js"></script>
	<script src="js/prettify.js"></script>
	<script src="javascript/GanttJS.js"></script>
    <script> 
	$(function(){
		show();
	});
			


	
	
function add(){
		$("input[type=reset]").trigger("click"); 
		$("#submitBtn").css("display","block");
		$(".chartQuota").show();
		$("#deptname").show();
		$("#updateBtn").css("display","none");
		$("#submitUpdBtn").css("display","none");
		$("#deleteBtn").css("display","none");
		$('#dialog-overlay').show();
		$('#dialog-box').show();
		$("#textselectusername").attr("readonly","readonly");
}	



function submitUpd(){
	var nameVal=$.trim($("#projectName").val());
	var descVal=$.trim($("#textselectusername").val());
	if(nameVal=="" || nameVal.length<=0){
		alert("请输入项目名称");
		$("#projectName").focus();
		return false;
	}
	if(descVal=="" ||descVal.length<=0){
		alert("请选择负责人编号");
		$("#textselectusername").focus();
		return false;
		
	}
	var flag=testTime();
	if(flag){
		document.form.action="GanttAction!updateOneGantt.action";
    	document.form.submit();
	}
	
}
function submitAdd(){
	var nameVal=$.trim($("#projectName").val());
	var descVal=$.trim($("#textselectusername").val());
	if(nameVal=="" || nameVal.length<=0){
		alert("请输入项目名称");
		$("#projectName").focus();
		return false;
		
	}
	if(descVal=="" ||descVal.length<=0){
		alert("请选择负责人编号");
		$("#textselectusername").focus();
		return false;
	}
	var flag=testTime();
	if(flag){
		 document.form.action="GanttAction!addOneGantt.action";
   		 document.form.submit();
	}

}
	
	
	
function del(){
	var id=$("#ganttId").val();
	var urlPageNum=$("#pageNum").val();
	var flag=confirm("确定删除吗?");
	if(flag){//+"&urlPageNum="+urlPageNum
		window.location.href="GanttAction!deleteOneGantt.action?ganttId="+id;
	}
}


//查询所有部门

function userlist(flag) {//flag0表示是点击部门的时候flag1表示页面刷新的时候
	var deptname = $("#deptname").val();
	if (deptname == "0") {
		$("#username").empty();
		$("<option value='0'>请先选择部门</option>").appendTo("#username");
	} else {
		$("#username").empty();//不合适
		$.ajax( {
					type : "post",
					url : "DeptNumberAction!findUsersBydept.action",
					data : {
						moveType : deptname
					},
					dataType : "json",
					async : false,
					success : function(data) {
						if (flag == 0){
							$("#username").empty();
							$("<option value='0'>请选择人员</option>").appendTo("#username");
						}
						$(data).each(function() {
									$("<option value='" + this.id + "_"+this.name+"'>"+ this.code + "_"+ this.name + "</option>").appendTo("#username");
						});
						

					}
		});
	}
	duoxuaSelect('username');
	var duoxuaselect_div = $(".duoxuaselect_div");
	if (duoxuaselect_div[1] != null) {
		document.getElementById("peopleTh").removeChild(duoxuaselect_div[0]);
   }	
	$("#textselectusername").attr("readonly","readonly");
}
function addSon(){
	var nameVal=$.trim($("#projectName").val());
	var descVal=$.trim($("#textselectusername").val());
	if(nameVal=="" || nameVal.length<=0){
		alert("请输入项目名称");
		$("#projectName").focus();
		return false;
		
	}
	if(descVal=="" ||descVal.length<=0){
		alert("请选择负责人编号");
		$("#textselectusername").focus();
		return false;
	}
	var flag=testTime();
	if(flag){
		 document.form.action="GanttAction!addSon.action";
   		 document.form.submit();
	}
	
}

$(document).ready(function() {
	userlist(1);
})
    </script>
</html>
