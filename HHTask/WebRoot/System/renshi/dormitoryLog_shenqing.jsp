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
	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div align="center" id="gongneng">
			<div align="center"
				style="width: 70%; height: 100%; border: 0px solid #000000;">
				<form action="dormitoryLogAction_add.action" method="post"
					onsubmit="return shenqing()">
					<table class="table" align="center">
						<tr>
							<th align="center" colspan="6" style="size: 20pt;">
								<img alt="loge" src="<%=path%>/images/zhaobiao.jpg"
									style="width: 30px; height: 30px;">
								${companyInfo.name}单身宿舍居住申请表
								<input type="hidden" name="tag" value="code"/>
								<br/>
								<font color="#ff0000">${errorMessage}</font>
							</th>
						</tr>
						<%--<tr>
							<th colspan="6" align="right">
								申请单编号：${dormitoryLog.shenqing_number}
								&nbsp;&nbsp;&nbsp;&nbsp;
							</th>
						</tr>
						--%>
						<tr>
							<th>
								申请人姓名
							</th>
							<td>
								<input type="text" name="dormitoryLog.name" id="name" value="${Users.name}" readonly="readonly"/>
								<input type="hidden" name="dormitoryLog.codeId" id="name" value="${Users.id}"/>
							</td>
							<th>
								年龄
							</th>
							<td>
								<input type="text" name="dormitoryLog.age" id="age"
									onblur="mustBeNumber('age')"/>
							</td>
							<th>
								性别
							</th>
							<td>
								<select name="dormitoryLog.sex" id="sex">
									<option value="${Users.sex}">
										${Users.sex}
									</option>
									<option value="">
										请选择性别
									</option>
									<option value="男">
										男
									</option>
									<option value="女">
										女
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								申请人身份证号码
							</th>
							<td>
								<input type="text" name="dormitoryLog.identity_id"
									id="identity_id" value="${Users.uid}" readonly="readonly"/>
							</td>
							<th>
								申请居住起始时间
							</th>
							<td>
								<input class="Wdate" type="text" name="dormitoryLog.startTime"
									id="startTime"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
							<th>
								申请居住截止时间
							</th>
							<td>
								<input class="Wdate" type="text" name="dormitoryLog.endTime"
									id="endTime"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
						</tr>
						<tr>

							<th>
								申请人所在部门
							</th>
							<td>
								<SELECT name="dormitoryLog.dept" id="dept"
									style="width: 160px; height: 100%">
									<option value="${Users.dept}">${Users.dept}</option>
									<option>请选择部门</option>
								</SELECT>
							</td>
							<th>
								员工工号
							</th>
							<td>
								<input type="text" name="dormitoryLog.code" id="code"
									value="${Users.code}" readonly="readonly">
							</td>
							<th>
								员工合同编号
							</th>
							<td>
								<input type="text" name="dormitoryLog.contract_number" value="${contract.contractNumber}"
									id="contract_number" readonly="readonly">
							</td>
						</tr>
						<tr>
							<th align="center" colspan="6">
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
								<td align="left" colspan="6">
									&nbsp;&nbsp;&nbsp;&nbsp;${provil.content}
								</td>
							</s:iterator>
						</tr>
						<tr>
							<td align="center" colspan="6">
								我已经阅读并接受此协议
								<input type="checkbox" name="dormitoryLog.isAgree" id="isAgree"
									value="同意"
									onclick="if(this.checked) {document.getElementById('btnOk').disabled=false;}else{document.getElementById('btnOk').disabled=true;}">
							</td>
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" value="申请" disabled="disabled" id="btnOk"
									style="width: 80px; height: 40px;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="重置"
									style="width: 80px; height: 40px;">
							</td>
						</tr>

					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function shenqing() {

	if (!valldateText("name", "姓名")) {
		return false;
	}
	if (!valldateText("age", "年龄")) {
		return false;
	}
	if (!valldateText("sex", "性别")) {
		return false;
	}
	if (!valldateText("identity_id", "身份证号")) {
		return false;
	}
	if (!valldateText("startTime", "起始时间")) {
		return false;
	}
	if (!valldateText("dept", "所在部门")) {
		return false;
	}
	if (!valldateText("code", "员工卡号")) {
		return false;
	}
}

function valldateText(id ,textName){
				var textValue = document.getElementById(id).value;
				if(textValue == null || textValue == ""){
					alert(textName + "不能为空");
					return false;
				}
				return true;
			}

		</script>
	</body>
</html>
