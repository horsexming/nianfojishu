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
					<a href=""
						style="color: rgb(79, 77, 77)"><br />
					</a>
				</div>
			</div>

			<div align="center">
				<h3>
					寄快递
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="AccessEquipmentAction_addResAccess.action"
					method="post" onsubmit="return validate()">
					<table class="table">
						<tr style="width: 100%">
							<th align="right">
								选择柜号型号：
							</th>
							<td >
								<select onchange="gui()" id="xinghao">
									<option value="">--选择快递柜型号--</option>
									<option value="A2" title="20*30*10">小</option>
									<option value="A1" title="20*30*20">中</option>
									<option value="A0" title="20*30*40">大</option>
								</select>
								<input type="hidden" name="resAccess.type" value="寄取" 
									 />
								<input type="hidden" name="tag" value="aD"/>
							</td>
							<th align="right">
								选择柜号：
							</th>
							<td >
								<select id="daGuiId" name="resAccess.daGuiId" style="width:152px;" >
									<option value="">--请先选择型号--</option>
								</select>
							</td>
						</tr>
						<tr style="width: 100%">
							<th align="right">
								快递员手机号：
							</th>
							<td >
								<input type="text" name="resAccess.shouTel" value="" 
									 />
							</td>
							<th align="right" colspan="2">
							</th>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="添加(Add)"
									style="width: 100px; height: 50px;" />
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
	if (!validateText("daGuiId", "快递柜号")) {
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
function gui(){
	var xinghao = $("#xinghao").val();
	if (xinghao != "") {
		$.ajax( {
			url : "ToolCabineAction!findkdgTool.action",
			type : 'post',
			dataType : 'json',
			cache : false,//防止数据缓存
			data : {
				an : xinghao
			},
			success : function(useradsfa) {
				$("#daGuiId").empty();//清空
				if(useradsfa==null||useradsfa==''){
					$("<option value=''>无</option>").appendTo("#daGuiId");
				}else{
					$("<option value=''>--选择一个快递柜--</option>").appendTo("#daGuiId");
					$(useradsfa).each(
							function() {
								$(
									"<option value='"
												+ this.id + "'>"
												+ this.cabNumber 
												+ "</option>")
									.appendTo("#daGuiId")
							});
				}
			},
			error : function() {
				alert("服务器异常!");
			}
		});
	}
}
<%--$("#cabOpenOrder").keyup(function() {--%>
<%--	var tmptxt = $(this).val();--%>
<%--	$(this).val(tmptxt.replace(/\D|^0/g, ''));--%>
<%--})--%>
</script>
	</body>
</html>
