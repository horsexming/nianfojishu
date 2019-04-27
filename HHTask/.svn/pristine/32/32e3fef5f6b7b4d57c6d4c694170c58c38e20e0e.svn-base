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
		<div align="center" id="gongneng">
			<div align="center"
				style="width: 70%; height: 100%; border: 0px solid #000000;">
				<form action="dimission_HandoverAction_add.action" method="post"
					onsubmit="return dimiss_add()">
					<table class="table" align="center">
						<tr>
							<th align="center" colspan="4">
								<img alt="loge" src="<%=path%>/images/zhaobiao.jpg"
									style="width: 30px; height: 30px;">
								${companyInfo.name}员工离职交接单
								<input type="hidden" name="dimissionHandover.ta_dimissionLog_id"
									id="ta_dimissionLog_id" value="${dimissionLog.id}" />
									<input type="hidden" name="tag"
										value="code" />
								<br/>
								<font color="red">${errorMessage}</font> 
							</th>
						</tr>
						<tr>
							<th align="center">
								姓名
							</th>
							<td align="center">
								<input type="hidden" name="dimissionHandover.codeId" id="codeId"
									value="${Users.id}" readonly="readonly" />
								<input type="text" name="dimissionHandover.name" id="name"
									value="${Users.name}" readonly="readonly" />
							</td>
							<th align="center">
								部门
							</th>
							<td align="center">
								<input type="text" name="dimissionHandover.dept" id="dept"
									value="${Users.dept}" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="center">
								离职原因
							</th>
							<td align="left" colspan="3">
								<textarea rows="3" cols="100%"
									name="dimissionHandover.dimission_Reason" id="dimission_Reason"></textarea>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input type="submit" value="申请"
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
		<SCRIPT type="text/javascript">
		function dimiss_add(){
		if(!validateText("dimission_Reason","离职原因")){
				return false;
			}
		}
			
function validateText(id, textname) {
	var textValue = $("#" + id).val();
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空！");
		return false;
	}
	return true;
}
		</SCRIPT>
	</body>
</html>
