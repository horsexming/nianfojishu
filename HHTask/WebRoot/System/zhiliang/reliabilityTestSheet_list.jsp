<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	</head>
	<body>
		<form action="ReliabilityTestAction!findRTSByCondition.action" method="post" >
			<table class="table">
				<tr align="center">
					<td colspan="8" style="font-size: 20px;">
						可靠性测试管理
					</td>
				</tr>
				
				<tr>
					<th align="right">申请单流水号:</th>
					<td><input type="text" name="sheet.number" value="${sheet.number }"></td>
					<th align="right">申请单位:</th>
					<td><input type="text" name="sheet.company" value="${sheet.company}"></td>
					<th align="right">申请日期:</th>
					<td><input type="text" name="sheet.addTime" value="${sheet.addTime}"></td>
					<th align="right">申请人:</th>
					<td><input type="text" name="sheet.addUserName" value="${sheet.addUserName}"></td>
				</tr>
				<tr>
					<th align="right">物料类别:</th>
					<td><input type="text" name="sheet.materialCategory" value="${sheet.materialCategory}"></td>
					<th align="right">物料来源:</th>
					<td><input type="text" name="sheet.materialResource" value="${sheet.materialResource}"></td>
					<th align="right">样品编号:</th>
					<td colspan="3"><input type="text" name="sheet.specimenNum" value="${sheet.specimenNum}"></td>
				</tr>
				<tr>
					<th align="right">机柜名称:</th>
					<td><input type="text" name="sheet.jGname" value="${sheet.jGname}"></td>
					<th align="right">图号/规格:</th>
					<td><input type="text" name="sheet.tuhao" value="${sheet.tuhao}"></td>
					<th align="right">零件料号:</th>
					<td colspan="3"><input type="text" name="sheet.markId" value="${sheet.markId}"></td>
				</tr>
				<tr>
					<td colspan="8" align="center">
						<input type="submit" class="input" value="查询" /> 
					</td>
				</tr>

			</table>
			<table class="table">
				<tr>
					<th>序号</th>
					<th>流水号</th>
					<th>物料类别</th>
					<th>物料来源</th>
					<th>样品编号</th>
					<th>机柜名称</th>
					<th>件号</th>
					<th>批次</th>
					<th>申请单位</th>
					<th>申请日期</th>
					<th>申请人</th>
					<th>审批动态</th>
					<th>操作</th>
				</tr>
				<s:if test="sheetList!=null && sheetList.size()>0">
					<s:iterator value="sheetList" id="rts" status="pageStatus">
					<s:if test="#pageStatus.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'')">
					</s:else>
							<td>${pageStatus.index+1 }</td>
							<td>${rts.number }</td>
							<td>${rts.materialCategory }</td>
							<td>${rts.materialResource }</td>
							<td>${rts.specimenNum }</td>
							<td>${rts.jGname }</td>
							<td>${rts.markId }</td>
							<td>${rts.lotId }</td>
							<td>${rts.company }</td>
							<td>${rts.addTime }</td>
							<td>${rts.addUserName }</td>
							<td><a href="CircuitRunAction_findAduitPage.action?id=${rts.epId}">${rts.epStatus }</a></td>
							<td>
								<s:if test="tag!=null && tag=='submit'">
									<input type="button" value="前往提交" onclick="toSubmit(${rts.id})">
									<input type="button" value="详细" onclick="toDetail(${rts.id})">
								</s:if>
								<s:else>
									<input type="button" value="详细" onclick="toDetail(${rts.id})">
									<input type="button" value="修改" onclick="toUpdate(${rts.id})">
									<input type="button" value="删除" onclick="toDelete(${rts.id})">
									
								</s:else>
							</td>
						</tr>	
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="15" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="15" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
				</s:if>				
				<s:else>
					<tr>
						<td colspan="20" style="color: red;">没有记录</td>
					</tr>
				</s:else>
			</table>
		</form>
	</body>
<script type="text/javascript">
	function toDetail(id){
		location.href="ReliabilityTestAction!getRTSById.action?id="+id;
	}
	function toUpdate(id){
		location.href="ReliabilityTestAction!getRTSById.action?id="+id+"&tag=update";
	}
	function toDelete(id){
		if(confirm("确定要删除吗？ 删除后测试项目也将删除")){
			location.href="ReliabilityTestAction!deleteRTS.action?id="+id+"&tag=update";
		}
	}
	function toSubmit(id){
		location.href="ReliabilityTestAction!toSubmitTestPro.action?id="+id;
	}
</script>
</html>
