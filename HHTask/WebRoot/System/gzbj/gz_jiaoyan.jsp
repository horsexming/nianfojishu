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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center">
				<form action="GzstoreAction_gzjiaoyan.action" method="post"
					onsubmit="return validate()" enctype="multipart/form-data">
					<table class="table">
						<tr>
							<th colspan="2" align="center">
								<h3>
									修改工装报检信息
								</h3>
							</th>
						</tr>
						<tr>
							<th align="right">
								工装编号:
							</th>
							<td>
								<input type="text" id="number" name="gzstore.number"
									value=" ${gzstore.number}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								名称:
							</th>
							<td>
								<input type="text" id="matetag" name="gzstore.matetag"
									value=" ${gzstore.matetag}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								仓库:
							</th>
							<td>
								<input type="text" id="storehouse" name="gzstore.storehouse"
									value="${gzstore.storehouse}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								分类:
							</th>
							<td>
								<input type="text" id="parClass" name="gzstore.parClass"
									value="${gzstore.parClass}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								位置:
							</th>
							<td>
								${gzstore.place}
							</td>
						</tr>
						<tr>
							<th align="right">
								报检次数:
							</th>
							<td>
								<input type="text" name="gzstore.bjcs" value="${gzstore.bjcs}" />
							</td>
								<tr>
									<th align="right">
										已报检次数:
									</th>
									<td>
										<s:if test="gzstore.sybjcs!=null">
							${gzstore.bjcs - gzstore.sybjcs}
						</s:if>
										<s:else>
							0
						</s:else>
									</td>
						<tr>
							<th align="right">
								价格:
							</th>
							<td>
								${gzstore.price}
							</td>
						</tr>
						<tr>
							<th align="right">
								型别:
							</th>
							<td>
								${gzstore.xingbie}
							</td>
						</tr>
						<tr>
							<th align="right">
								校验报告
							</th>
							<td>
								<input type="file" value="" name="attachment"/>
							</td>
						</tr>
						<tr>
							<th align="right"></th>
							<td>
								<input type="hidden" name="gzstore.id" value="${gzstore.id}" />
								<input type="button" value="正常 " class="input"  onclick="submitform(this.form,'OK')"/>
								<input type="button" value="报废 " class="input" onclick="submitform(this.form,'break')"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	var number = document.getElementById("number").value;
	var matetag = document.getElementById("matetag").value;
	var storehouse = document.getElementById("storehouse").value;
	var parClass = document.getElementById("parClass").value;
	if (number == "") {
		alert("请输入工装号!");
		return false;
	}
	if (matetag == "") {
		alert("请输入名称!");
		return false;
	}
	//				if(storehouse ==""){
	//					alert("请输入仓库名称！");
	//					return false;
	//				}
	//				if(parClass ==""){
	//					alert("请输入分类名称！");
	//					return false;
	//				}
}

function submitform(obj,status){
	$(obj).attr('action',"GzstoreAction_gzjiaoyan.action?status="+status);
	$(obj).submit();
}
</script>
		</ body>
</html>
