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
		<div align="center" id="gongneng" style="width: 100%;">
			<div align="center" id="dansheng"
				style="width: 70%; height: 100%; border: 0px solid #000000;">
					<table class="table" align="center">
						<tr>
							<th align="center" colspan="6" style="size: 20pt;">
								<img alt="loge" src="<%=path%>/images/zhaobiao.jpg"
									style="width: 30px; height: 30px;">
								查看单身宿舍居住申请表
								<input type="hidden" name="dormitoryLog.id" value="${dormitoryLog.id}"/>
							</th>
						</tr>
						<tr>
							<th colspan="6" align="right">
								申请单编号：${dormitoryLog.shenqing_number}
								&nbsp;&nbsp;&nbsp;&nbsp;
							</th>
						</tr>
						<tr>
							<th>
								申请人姓名
							</th>
							<td>
								${dormitoryLog.name}
							</td>
							<th>
								年龄
							</th>
							<td>
								${dormitoryLog.age}
							</td>
							<th>
								性别
							</th>
							<td>
								${dormitoryLog.sex}
							</td>
						</tr>
						<tr>
							<th>
								申请人身份证号码
							</th>
							<td>
								${dormitoryLog.identity_id}
							</td>
							<th>
								申请居住起始时间
							</th>
							<td>
								${dormitoryLog.startTime}
							</td>
							<th>
								申请居住截止时间
							</th>
							<td>
							${dormitoryLog.endTime}
							</td>
						</tr>
						<tr>

							<th>
								申请人所在部门
							</th>
							<td>
							${dormitoryLog.dept}
							</td>
							<th>
								员工工号
							</th>
							<td>
								${dormitoryLog.code}
							</td>
							<th>
								员工合同编号
							</th>
							<td>${dormitoryLog.contract_number}
							</td>
						</tr>
						<tr>
							<th colspan="6">
								${companyInfo.name}单身宿舍居住协议
							</th>
						</tr>
						<tr>
							<s:iterator value="provisionlist" id="provil" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr>
								</s:if>
								<s:else>
									<tr>
								</s:else>
								<td align="left"" colspan="6">
									&nbsp;&nbsp;&nbsp;&nbsp;${provil.content}
								</td>
							</s:iterator>
						</tr>
						<tr>
							<td align="center" colspan="6">
								我已经阅读并接受此协议
								<s:if test="dormitoryLog.isAgree=='同意'&&dormitoryLog.isAgree!=null">
									：已同意
								</s:if>
									<s:else>：未同意</s:else>
							</td>
						</tr>
						<tr>
						<th colspan="6" align="center" style="height: 30px;">
							审批状态
						</th>
					</tr>
					<tr>
						<th style="height: 35px;">
							序号
						</th>
						<th>
							部门
						</th>
						<th>
							姓名
						</th>
						<th>
							状态
						</th>
						<th colspan="2">
							审批时间
						</th>
					</tr>
					<tr>
						<s:iterator value="list" id="execut" status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="left">
							</s:if>
							<s:else>
								<tr align="left">
							</s:else>
							<td align="center" style="height: 30px;">
								<s:if test="#pageStatus.index%2==1">
									<font color="#000000"></font>
								</s:if>
								<s:else>
									<font color="#ff0000"></font>
								</s:else>
								<s:property value="#pageStatus.index+1" />
							</td>
							<td align="center">
								${execut.auditUserDept}
							</td>
							<td align="center">
								${execut.auditUserName}
							</td>
							<td align="center">
							<s:if test="#execut.auditStatus=='同意'&&#execut.auditStatus!=null">
								${execut.auditStatus}
							</s:if>
							</td>
							<td align="center" colspan="2">
								${execut.auditDateTime}
							</td>
						</s:iterator>
					</tr>
				</table>
			</div>
			<div align="center">
					<input type="submit" value="打印" onclick="pagePrint('dansheng','sy')"
						style="width: 80px; height: 50px;" />
				</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		</script>
	</body>
</html>
