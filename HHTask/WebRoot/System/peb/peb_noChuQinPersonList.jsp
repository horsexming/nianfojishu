<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<%@include file="/util/sonHead.jsp"%>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.css">
		<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"> </script> 
 		<script type="text/javascript"
 			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"> </script> 
 		<script type="text/javascript"
			// 现在window.$和window.jQuery是3.2.1版本:
			console.log($().jquery); // => '3.2.1'
			var $jq = jQuery.noConflict(true);
			// 现在window.$和window.jQuery被恢复成1.5版本:
			console.log($().jquery); // => '1.5.0'
		
		</script>
		<style type="text/css">
			caption{
				text-align: center;
			}
			body{
				margin:5px;
/* 				padding: 5px; */
			}
			.row{
				margin:0;
			}
		</style>
	</head>
	<body>
		<div class="row">
			<div class="cell col-md-12">
				<form action="${url }" method="post" autocomplete=off>
					<table class="table table-responsive">
						<caption>人数列表</caption>
						<tr>
							<th>年份</th>
							<td>
								<input name="pebUsers.year" value="${pebUsers.year }"  />
							</td>
							<th>月份</th>
							<td>
								<input name="pebUsers.month" value="${pebUsers.month }" />
							</td>
							<th>日期</th>
							<td>
								<input name="pebUsers.day" value="${pebUsers.day }"  />
							</td>
						</tr>
						<tr>
							<th>班组</th>
							<td>
								<input type="text" name="pebUsers.banZu" value="${pebUsers.banZu}">
							</td>
							<th>未出勤人数</th>
							<td>
								<input type="text" name="pebUsers.applyNum" value="${pebUsers.applyNum}">
							</td>
							<th>审批状态</th>
							<td>
								<select name="pebUsers.epStatus" style="width:173px" >
									<option></option>
									<s:iterator id="status" value="{'同意','未审批','打回'}" >
										<s:if test="#status==pebUsers.epStatus">
											<option value="${status}" selected="selected">${status}</option>
										</s:if>
										<s:else>
											<option value="${status}">${status}</option>
										</s:else>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td class="text-center" colspan="6">
								<input type="submit" value="查询" class="input">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
		<div class="row">
			<div class="col-xs-12">
				<table class="table table-responsive">
					<tr>
						<th>序号</th>
						<th>班组</th>
						<th>时间</th>
						<th>未出勤人数</th>
						<th>审批状态</th>
						<th>操作</th>
					</tr>
					<s:iterator value="pebUsersList" id="users" status="ps">
						<tr>
							<td>${ps.index+1}</td>
							<td>${users.banZu}</td>
							<td>${users.year}-${users.month}-${users.day}</td>
							<td>${users.applyNum}</td>
							<td><a href="CircuitRunAction_findAduitPage.action?id=${users.epId}">${users.epStatus}</a></td>
							<td>
								<input type="button" value="删除" onclick="updateUsers(${users.id})">
							</td>
						</tr>
					</s:iterator>
					<tr>
						<td class="text-right" colspan="20"> 
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
 								styleClass="page" theme="number" /> 
						</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		function updateUsers(id){
			location.href="${pageContext.request.contextPath}/productEBAction!deleteNoChuQinShenQin.action?id="+id+"&cpage=${cpage}";
		}
		
		function toImport(){
			location.href="${pageContext.request.contextPath}/productEBAction!toImportData.action?pageStatus=importpebUsers";
		}
	</script>
</html>
