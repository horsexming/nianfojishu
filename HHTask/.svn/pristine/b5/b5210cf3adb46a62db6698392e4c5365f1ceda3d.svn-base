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
				<form action="GzstoreAction_addGzbj.action?id1=1" method="post"
					onsubmit="return validate()" enctype="multipart/form-data">
					<table class="table">
						<tr>
							<th colspan="2" align="center">
								<s:if test="status == 'muju'">
									<h3>添加模具信息</h3>
								</s:if>
								<s:else>
									<h3>添加工装信息</h3>
								</s:else>	
							</th>
						</tr>
						<tr>
							<th align="right">
								工装编号:
							</th>
							<td>
								<input type="text" id="number" name="gzstore.number" value="" />
							</td>
						</tr>
						<tr>
							<th align="right">
								名称:
							</th>
							<td>
								<input type="text" id="matetag" name="gzstore.matetag" value=" " />
							</td>
						</tr>
						<tr>
							<th align="right">
								仓库:
							</th>
							<td>
								<input type="text" id="storehouse" name="gzstore.storehouse"
									value="" />
							</td>
						</tr>
						<tr>
							<th align="right">
								分类:
							</th>
							<td>
									<input type="text" id="parClass" name="gzstore.parClass"
									value="" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								位置:
							</th>
							<td>
								<input type="text" name="gzstore.place" value="" />
							</td>
						</tr>
						<tr>
							<th align="right">
								维修周期:
							</th>
							<td>
								<input type="text" name="gzstore.period" value="" />
								<tr>
									<th align="right">
										价格:
									</th>
									<td>
										<input type="text" name="gzstore.price" value="" />
									</td>
								</tr>
								<tr>
									<th align="right">
										型别:
									</th>
									<td>
										<input type="text" name="gzstore.xingbie" value="" />
									</td>
								</tr>
								<tr>
									<th align="right">
										数量:
									</th>
									<td>
										<input id="sl" type="text" name="gzstore.total" value="" onkeyup="mustBeNumber('sl')" />
									</td>
								</tr>
								<tr>
									<th align="right">
										单位:
									</th>
									<td>
										<input type="text" name="gzstore.unit" value="" />
									</td>
								</tr>
								<tr>
									<th align="right">
										负责人:
									</th>
									<td>
										<input type="text" name="gzstore.fzr" value="" />
									</td>
								</tr>
								<tr>
									<th align="right">
										报检周期(次):
									</th>
									<td>
										<input type="text" name="gzstore.bjcs" value="" />
									</td>
								</tr>
								<th align="right">
									上传图片:
								</th>
								<td>
									<input type="file" value="" name="attachment" />
								</td>
						</tr>
						<tr>
							<th align="right"></th>
							<td>
								<input type="hidden" name="gzstore.id" value="" />
								<input type="submit" value="添加" class="input" />
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
$(function(){
	if('${param.status}'=='muju'){
		$("#parClass").val('模具');
	}else if('${param.status}'=='gz'){
		$("#parClass").val('工装');
	}
})
function mustBeNumber(id){
	var number=$("#"+id).val();
	if(isNaN(number)){
		alert("请输入数字");
		$("#"+id).val(0);
		return false;
	}
	return true;
	
}
function validate() {
	var number = document.getElementById("number").value;
	var matetag = document.getElementById("matetag").value;
	var storehouse = document.getElementById("storehouse").value;
	var parClass = document.getElementById("parClass").value;
	if (number == "") {
		alert("请输入工装编号!");
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
</script>
	</body>
</html>
