<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<head>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<BODY>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
					研发项目人员汇总
				</h3>
				汇总方式：
				<select id="summarizing" onchange="changeSummarizing()">
					<option value="0">按人员汇总</option>
					<option value="1">按项目池汇总</option>
					<option value="2">按主项目汇总</option>
<%--					<option value="3">按年份汇总</option>--%>
<%--					<option value="4">按月份汇总</option>--%>
				</select>
			</div>
			<table class="table" id="showTable">
			
				
			</table>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	
<script type="text/javascript">

	$(function(){
		$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/projectPoolAction_personnelSummary.action",
				dataType:"json",
				success:function(data){
					$("#showTable").append("<tr><th>编号</th><th>工号</th><th>姓名</th><th>金额(元)</th><th>操作</th></tr>");
					if(data!=null){
						for(var i=0;i<data.length;i++){
							var userCode=data[i].userCode;
							if(null==userCode){
								userCode='';
							}
							$("#showTable").append("<tr><td align='right'>"+(i+1)+"</td><td>"+userCode+"</td><td>"
								+data[i].userName+"</td><td align='right'>"+data[i].money+"(元)</td>" +
								"<td align='center'><input type='button' value='查看项目信息' " +
								"onclick='location.href=\"${pageContext.request.contextPath}/projectPoolAction_showSelfProject.action?userId="+data[i].userId+"\"' /></td></tr>");
						}
					}
				}
			});
	});
	function changeSummarizing(){
		var index = $("#summarizing").val();
		$("#showTable").html("");
		if(index==0){
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/projectPoolAction_personnelSummary.action",
				dataType:"json",
				success:function(data){
					$("#showTable").append("<tr><th>编号</th><th>工号</th><th>姓名</th><th>金额(元)</th><th>操作</th></tr>");
					if(data!=null){
						for(var i=0;i<data.length;i++){
							var userCode=data[i].userCode;
							if(null==userCode){
								userCode='';
							}
							$("#showTable").append("<tr><td align='right'>"+(i+1)+"</td><td>"+userCode+"</td><td>"+data[i].userName+"</td>" +
							"<td align='right'>"+data[i].money+"(元)</td>" +
							"<td align='center'><input type='button' value='查看项目信息' " +
							"onclick='location.href=\"${pageContext.request.contextPath}/projectPoolAction_showSelfProject.action?userId="+data[i].userId+"\"' /></td></tr>");
						}
					}
				}
			});
		}else if(index==1){
			//按项目池
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/projectPoolAction_summarizingPool.action",
				dataType:"json",
				success:function(data){
					$("#showTable").append("<tr><th >编号</th><th>项目池编号</th><th>项目池名称</th><th>工号</th><th>姓名</th><th>金额(元)</th><th>操作</th></tr>");
					if(data!=null){
						for(var i=0;i<data.length;i++){
							var userCode=data[i].userCode;
							if(null==userCode){
								userCode='';
							}
							$("#showTable").append("<tr><td align='right'>"+(i+1)+"</td><td>"+data[i].poolNum+"</td><td>"+data[i].poolName+"</td>" +
							"<td>"+userCode+"</td><td>"+data[i].userName+"</td><td align='right'>"+data[i].money+"(元)</td>" +
							"<td align='center'><input type='button' value='查看项目信息' " +
							"onclick='location.href=\"${pageContext.request.contextPath}/projectPoolAction_showSelfProjectAndPool.action?userId="+data[i].userId+"&id="+data[i].poolId+"\"' /></td></tr>");
						}
					}
				}
			});
		}else if(index==2){
			//按主项目
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/projectPoolAction_summarizingRootPro.action",
				dataType:"json",
				success:function(data){
					$("#showTable").append("<tr><th>编号</th><th>主项目编号</th><th>主项目名称</th><th>工号</th><th>姓名</th><th>金额(元)</th><th>操作</th></tr>");
					if(data!=null){
						for(var i=0;i<data.length;i++){
							var userCode=data[i].userCode;
							if(null==userCode){
								userCode='';
							}
							$("#showTable").append("<tr><td align='right'>"+(i+1)+"</td><td>"+data[i].proNum+"</td><td>"+data[i].proName+"</td>" +
							"<td>"+userCode+"</td><td>"+data[i].userName+"</td><td align='right'>"+data[i].money+"(元)</td>" +
							"<td align='center'><input type='button' value='查看项目信息' " +
							"onclick='location.href=\"${pageContext.request.contextPath}/projectPoolAction_showSelfProjectByRoot.action?userId="+data[i].userId+"&id="+data[i].proId+"\"' /></td></tr>");
						}
					}
				}
			});
		}else if(index==3){
			
		}
	}

	function showAllProject(userId){
		
	}


</script>
	</BODY>
</HTML>