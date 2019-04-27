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
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<s:iterator value="pwwApplyList" id="pageApply">
				<hr style="height:3px;border:none;border-top:3px double red;"/>
					<table class="table">
						<tr>
						<th>件号</th>
						<th>名称</th>
						<th>批次</th>
						<th>申请人</th>
						<th>申请时间</th>
						<th>状态</th>
						<th>备注</th>
						<th>操作</th>
						</tr>
						<tr>
							<form action="ProcardAction!wwsq.action" method="post" id="form_${pageApply.id}">
								<input type="hidden" value="${pageApply.id}" name="pwwApply.id">
								<td align="center">${pageApply.markId}</td>
								<td align="center">${pageApply.proName}</td>
								<td align="center">${pageApply.selfCard}</td>
								<td align="center">${pageApply.userName}</td>
								<td align="center">${pageApply.addTime}</td>
								<td align="center">${pageApply.status}</td>
								<td align="center">
									<textarea rows="1" cols="10" name="pwwApply.remarks">${pageApply.remarks}</textarea>
								</td>
								<td align="center">
								<s:if test="#pageApply.epId==null">
									<input onclick="sqsp(${pageApply.id})" id="tosq" type="button" value="申请审批" style="width: 80px;height: 25px;"/>
								</s:if>
								<s:else>
									<input onclick="sqdt(${pageApply.epId})" type="button" value="审批动态" style="width: 80px;height: 25px;"/>
								</s:else>
								</td>
							</form>
						</tr>
						<tr>
							<td align="center" colspan="8">
								<table class="table">
								<tr>
									<th>序号</th>
									<th>供应商</th>
									<th>件号</th>
									<th>零件名称</th>
									<th>批次</th>
									<th>版本</th>
									<th>版次</th>
									<th>工序号</th>
									<th>工序名称</th>
									<th>数量</th>
									<th>外委类型</th>
									<th>数据状态</th>
									<th>添加人</th>
									<th>添加时间</th>
									<s:if test="#pageApply.status!='未审批'&&#pageApply.status!='审批中'&&#pageApply.status!='同意'">
									<th>操作</th>
									</s:if>
								</tr>
									<s:iterator value="#pageApply.detailList" id="pagedetail" status="step1">
							<s:if test="#step1.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 50px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td align="center">
								<s:property value="#step1.index+1" />
							</td>
							<td align="center">
								<s:if test="#pagedetail.gysId==null">
									<font color="red">尚未匹配到合同</font>
								</s:if>
								<s:else>
								${pagedetail.gysName}
								</s:else>
							</td>
							<td align="center">
								${pagedetail.markId}
							</td>
							<td align="center">
								${pagedetail.proName}
							</td>
							<td align="center">
								${pagedetail.selfCard}
							</td>
							<td align="center">
								${pagedetail.banbenNumber}
							</td>
							<td align="center">
								${pagedetail.banci}
							</td>
							<td align="center">
								${pagedetail.processNOs}
							</td>
							<td align="center">
								${pagedetail.processNames}
							</td>
							<td align="center">
								${pagedetail.applyCount}
							</td>
							<td align="center">
								${pagedetail.wwType}
							</td>
							<td align="center">
								${pagedetail.dataStatus}
							</td>
							<td align="center">
								${pagedetail.userName}
							</td>
							<td align="center">
								${pagedetail.addTime}
							</td>
							<s:if test="#pageApply.status!='未审批'&&#pageApply.status!='审批中'&&#pageApply.status!='同意'">
							<td align="center">
								<input value="删除" type="button" style="width: 40px;height: 20px;" onclick="deleteDetail(${pagedetail.id})"/>
							</td>
							</s:if>
							</tr>
							</s:iterator>
								</table>
							</td>
						</tr>
					</table>
					<hr style="height:3px;border:none;border-top:3px double red;"/>
					<br/>
					<br/>
					<br/>
				</s:iterator>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function sqdt(id){
	window.location.href="CircuitRunAction_findAduitPage.action?id="+id;
}
function sqsp(id){
	$("#tosq").attr("disabled","disabled");
	$("#form_"+id).submit();
	//window.location.href="ProcardAction!wwsq.action?id="+id;
}
function deleteDetail(id){
	if(confirm("您是否要删除此条记录!")){
		window.location.href="ProcardAction!deleteWwApplyDetail.action?id="+id;
	}
}
</SCRIPT>
	</body>
</html>
