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
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="" style="color: rgb(79, 77, 77)"><br /> </a>
				</div>
			</div>

			<div align="center">
				<h3>
					添加应付信息
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="NoncorePayableAction!addPayable.action?tag=${tag}" method="post"
					enctype="multipart/form-data" onsubmit="return validate()">
					<table class="table">
						<tr style="width: 100%">
							<th>
								应付类型：
							</th>
							<th align="left">
								<select name="corePayable.subjectItem" id="subjectItem"
										style="width: 156px;"
										onMouseOver="createDept('subjectItem','NoncorePayableAction!findSelectName.action')">
										<option value="">
											请选择类型
										</option>
									</select>
							</th>
							<th>
								收款单位：
							</th>
							<td align="left">
								<input id="shoukuandanwei" type="text" name="corePayable.shoukuandanwei"/>
							</td>
						</tr>
						<tr>
							<th>
								账期开始日期：
							</th>
							<td align="left">
								<input class="Wdate" id="zhangqiStartDate" type="text" name="corePayable.zhangqiStartDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
							</td>
							<th>
								账期结束日期：
							</th>
							<td align="left">
								<input class="Wdate" id="zhangqiEndDate" type="text" name="corePayable.zhangqiEndDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
							</td>
						</tr>
						<tr>
							<th>
								附件编号：
							</th>
							<td align="left">
								<input id="hetongbianhao" type="text" name="corePayable.hetongbianhao"/>
							</td>
							<th>
								附件：
							</th>
							<td align="left">
								<input id="fujian" style="width: 158px;" type="file" name="fujian"/>
							</td>
						</tr>
<%--						<tr>--%>
<%--							<th>--%>
<%--								付款日期：--%>
<%--							</th>--%>
<%--							<td align="left">--%>
<%--								<input class="Wdate" id="fukuaiDate" type="text" name="corePayable.fukuaiDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>--%>
<%--							</td>--%>
<%--							<th>--%>
<%--								实付款金额：--%>
<%--							</th>--%>
<%--							<td align="left">--%>
<%--								<input id="realfukuanJIne" type="text" name="corePayable.realfukuanJIne"/>--%>
<%--							</td>--%>
<%--						</tr>--%>
						<tr>
							<th>
								负责人：
							</th>
							<td align="left">
								<input id="fuzeren" type="text" name="corePayable.fuzeren"/>
							</td>
							<th>
								应付付款金额：
							</th>
							<td align="left">
								<input id="yingfukuanJIne" type="text" name="corePayable.yingfukuanJIne" onblur="mustBeNumber('yingfukuanJIne')"/>
							</td>
						</tr>
						<tr>
							<th>
								截止付款日期：
							</th>
							<td align="left">
								<input class="Wdate" id="lateDate" type="text" name="corePayable.lateDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
							</td>
							<th colspan="2">
							</th>
						</tr>
						<tr>
							<th>
								摘要：
							</th>
							<td align="left" colspan="3">
								<textarea rows="2" cols="100%" name="corePayable.zhaiyao"
										id="zhaiyao"></textarea>(限三百字以内)
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="添加(Add)"
									style="width: 80px; height: 30px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	if (!validateText("subjectItem", "应付类型")) {
		return false;
	}
	if (!validateText("shoukuandanwei", "收款单位")) {
		return false;
	}
	if (!validateText("zhangqiStartDate", "账期开始日期")) {
		return false;
	}
	if (!validateText("zhangqiEndDate", "账期结束日期")) {
		return false;
	}
	if (!validateText("fujian", "合同附件")) {
		return false;
	}
	if (!validateText("yingfukuanJIne", "应付付款金额")) {
		return false;
	}
	if (!validateText("fuzeren", "负责人")) {
		return false;
	}
}

function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
</script>
	</body>
</html>
