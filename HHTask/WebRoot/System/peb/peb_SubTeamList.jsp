<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"> </script> 
 		<script type="text/javascript"
 			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"> </script> 
		<script type="text/javascript">
			// 现在window.$和window.jQuery是3.2.1版本:
			console.log($().jquery); // => '3.2.1'
			var $jq = jQuery.noConflict(true);
			// 现在window.$和window.jQuery被恢复成1.5版本:
			console.log($().jquery); // => '1.5.0'
		
		</script>
		<style type="text/css">
			.row{
				width: 98%;
			}
		</style>
	</head>
	<body>
		<div class="row">
			<div class="col-xs-12" align="center">
				<s:if test="pageStatus==null || (pageStatus!='chejian' && pageStatus!='process')">
				<form action="productEBAction!findSubTeamByCon.action">
					<table class="table">
						<tr>
							<th>
								名称
							</th>
							<td>
								<input type="text" name="subTeam.subName" value="${subTeam.subName}">
							</td>
							<th>
								负责人
							</th>
							<td>
								<input type="text" name="subTeam.principals" value="${subTeam.principals}">
							</td>
							<th>
								车间或分厂
							</th>
							<td>
								<select name="subTeam.isBanzu">
									<option></option>
									<option value="车间">车间</option>
									<option vlaue="分厂">分厂</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="20" class="text-center">
								<input type="submit" value="查询" class="input">
								<input type="hidden" value="${pageStatus}" name="pageStatus">
								<input type="button" value="前往添加" class="input" onclick="toAddSubTeam()">
							</td>
						</tr>
					</table>
				</form>
				</s:if>
				<s:else>
					<input type="button" value="添加" class="input" onclick="toAddSubTeam('${pageStatus}',${id})">
				</s:else>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<table class="table">
					<tr>
						<th>序号</th>
						<th>名称</th>
						<th>负责人</th>
						<th>类别</th>
<%-- 						<s:if test="pageStatus!=null || pageStatus=='chejian'"> --%>
							<th>2015年平均</th>
							<th>2016年平均</th>
							<th>2017年平均</th>
<%-- 						</s:if> --%>
						<th>操作</th>
					</tr>
					<s:iterator value="subTeamList" id="st" status="ps">
						<tr >
							<td>${ps.index+1}</td>
							<td>${st.subName}</td>
							<td>${st.principals}</td>
							<td>${st.isBanzu}</td>
<%-- 							<s:if test="pageStatus!=null || pageStatus=='chejian'"> --%>
								<td>${st.avg2015}</td>
								<td>${st.avg2016}</td>
								<td>${st.avg2017}</td>
<%-- 							</s:if> --%>
							<td>
								<s:if test="pageStatus!=null || pageStatus=='chejian'">
									<input type="button" value="工序管理" onclick="toGongXuManager(${st.id})">
								</s:if>
<%-- 								<s:elseif test="pageStatus!=null || pageStatus=='gongxu'"> --%>
<%-- 								</s:elseif> --%>
								<s:else>
									<input type="button" value="车间管理" onclick="toChejianManager(${st.id})">
									
								</s:else>
								<input type="button" value="人均日目标" onclick="toSettingTarget('${st.subName}')">
								<input type="button" value="修改" onclick="updateSt(${st.id})">
								<input type="button" value="删除" onclick="deleteSt(${st.id})">
							</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="20" class="text-right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
 								styleClass="page" theme="number" /> 
						</td>
					</tr>
				</table>
				
				<form action="productEBAction!toAddProduction.action?pageStatus=settingTarget" method="post" id="settIngTargetForm">
					<input type="hidden" name="banzu" id="banzuName">
				</form>
			</div>
		</div>
		<script type="text/javascript">
			function deleteSt(id){
				if(confirm("确定要删除吗？")){
					location.href="${pageContext.request.contextPath}/productEBAction!deleteSteam.action?subTeam.id="+id;
				}
			}
			function toAddSubTeam(){
				location.href="${pageContext.request.contextPath}/productEBAction!toAddSubTeam.action?pageStatus="+pageStatus;
			}
			function updateSt(id){
				location.href="${pageContext.request.contextPath}/productEBAction!toAddProduction.action?pageStatus=updateSubTeam&id="+id;
			}
			function toChejianManager(id){
				location.href="${pageContext.request.contextPath}/productEBAction!findSubTeamByCheJianCon.action?pageStatus=chejian&id="+id;
			}
			function toGongXuManager(id){
				location.href="${pageContext.request.contextPath}/productEBAction!findSubTeamByCheJianCon.action?pageStatus=process&id="+id;
			}
			function toAddSubTeam(pageStatus,id){
				location.href="${pageContext.request.contextPath}/productEBAction!toAddSubTeam.action?pageStatus="+pageStatus+"&id="+id;
			}

			function toSettingTarget(banzu){
				$("#banzuName").val(banzu);
				$("#settIngTargetForm").submit();
			}
		</script>
	</body>	
</html>
