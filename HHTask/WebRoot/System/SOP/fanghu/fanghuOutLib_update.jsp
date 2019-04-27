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
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
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
				<form action="outlib_updateFanghuOutLib.action" method="post"
					style="">
					<input type="hidden" name="fanghuOutLib.id"
						value="${fanghuOutLib.id}" />
					<input type="hidden" name="fanghuOutLib.userId"
						value="${fanghuOutLib.userId}" />
					<table class="table">
						<tr>
							<td>
								物品名称
							</td>
							<td>
								<%--<input type="text" id="matetag" name="fanghuOutLib.matetag"/>--%>
								<select id="matetag" name="fanghuOutLib.matetag"></select>
							</td>
							<td>
								规格尺寸
							</td>
							<td>
								<%--<input type="text" id="format" name="fanghuOutLib.format"/>--%>
								<select id="format" name="fanghuOutLib.format"></select>
							</td>
							<td>
								单位
							</td>
							<td>
								<%--<input type="text" name="fanghuOutLib.unit"/>--%>
								<select name="fanghuOutLib.unit" id="danwei">
									<option value="${fanghuOutLib.unit}">
										${fanghuOutLib.unit}
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								领用限量
							</td>
							<td>
								<input type="text" name="fanghuOutLib.limitCount"
									value="${fanghuOutLib.limitCount}" />
							</td>
							<td>
								领用周期
							</td>
							<td>
								<input type="text" name="fanghuOutLib.lingyongCircle"
									value="${fanghuOutLib.lingyongCircle}" />
							</td>
							<td>
								上次领用时间
							</td>
							<td>
								<input class="Wdate" type="text"
									name="fanghuOutLib.lastLingyongTime"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"
									value="${fanghuOutLib.lastLingyongTime}" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" value="提交"
									style="width: 100px; height: 50px;" />
								<input type="reset" value="重置"
									style="width: 100px; height: 50px;" />
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
	</body>
</html>
<script type="text/javascript">
$(function() {
	getUnit("danwei");
	$.ajax( {
		type : "get",
		dataType : "json",
		url : "fanghuYongpinAction!getFanghuYongpinListForSelect.action",
		async : false,
		success : function(data) {
			var success = data.success;
			if (success) {
				data = data.data;
				$("<option value='' >请选择物品名称</option>").appendTo("#matetag");
				$("<option value='' >请选择尺寸</option>").appendTo("#format");
				$(data)
						.each(
								function(i, n) {
									$(
											"<option value='"
													+ this.fanghuYongpinName
													+ "' data-id='" + this.id
													+ "'>"
													+ this.fanghuYongpinName
													+ "</option>").appendTo(
											"#matetag");
								});
			}
		}
	});

	//级联查询出部门所对应的所有人员

	$("#matetag")
			.bind(
					"change",
					function() {
						if ($("#matetag").val() != "") {
							$
									.ajax( {
										url : "fanghuYongpinAction!getFanghuYongpinGuigeListForSelect.action",
										type : 'post',
										dataType : 'json',
										async : false,
										cache : false,//防止数据缓存
										data : {
											'fanghuYongpin.parentId' : $(
													"#matetag").find(
													"option:selected").attr(
													"data-id")
										},
										success : function(data) {
											var success = data.success;
											if (success) {
												data = data.data;
												$("#format").empty();//清空
										$("<option value=''>请选择尺寸</option>")
												.appendTo("#format");
										$(data)
												.each(
														function() {
															$(
																	"<option value='"
																			+ this.fanghuYongpinGuige
																			+ "' >"
																			+ this.fanghuYongpinGuige
																			+ "</option>")
																	.appendTo(
																			"#format");
														});
									}
								}
									});
						}
					});
	var fanghuYongpinName = '${fanghuOutLib.matetag}'
	var fanghuYongpinGuige = '${fanghuOutLib.format}';
	$("#matetag").find("option[value='" + fanghuYongpinName + "']").attr(
			"selected", true);
	$("#format").append(
			"<option value='" + fanghuYongpinGuige + "' selected='selected'>"
					+ fanghuYongpinGuige + "</option>");
});
</script>
