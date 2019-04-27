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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="showSb">
				<form action="EquipmentAction!add1.action" method="post">
					<input type="hidden" name="machine.id" value="${machine.id}" />
					<table class="table">
						<tr>
							<td style="font-size: 18px; color: black;">
								&nbsp; &nbsp;工区
							</td>
							<td>
								<input type="text" name="maintenance.workArea"
									value="${machine.workArea}" readonly="readonly" />
							</td>
							<td style="font-size: 18px; color: black;">
								&nbsp;&nbsp; 工位
							</td>
							<td>
								<input type="text" name="maintenance.workPosition"
									value="${machine.workPosition}" readonly="readonly" />
							</td>
							<td align="center" style="font-size: 18px; color: black;">
								设备编号
							</td>
							<td>
								<input type="text" name="maintenance.no" value="${machine.no}"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td style="font-size: 18px; color: black;">
								设备名称
							</td>
							<td>
								<input type="text" name="maintenance.name"
									value="${machine.name}" readonly="readonly" />
							</td>
							<td style="font-size: 18px; color: black;">
								设备类型
							</td>
							<td>
								<input type="text" name="maintenance.type"
									value="${machine.type}" readonly="readonly" />
							</td>
							<td align="center" style="font-size: 18px; color: black;">
								故障状况
							</td>
							<td>
								<select name="maintenance.faultDetail" style="width: 155px;">
									<option value="运转不正常">
										运转不正常
									</option>
									<option value="无法启动">
										无法启动
									</option>
									<option value="漏油">
										漏油
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="left" style="font-size: 18px; color: black;">
								指定维修人
							</td>
							<td colspan="5">
								<input type="radio" name="partsradio"
									onclick="showOrClose('parts','block');showOrClose('addrepairMan','block');"
									value="yes">
								是
								<input type="radio" name="partsradio" checked="checked"
									onclick="showOrClose('parts','none')" value="no">
								否
							</td>
						</tr>
						<tr>
							<td colspan="6" align="left">
								<div id="parts" style="display: none;">
									维修人:
									<select id="repairMan" name="maintenance.repairMan"
										style="width: 100px;">
										<option></option>
										<s:iterator value="list" id="pagePeople">
											<option value="${pagePeople.repairname}">
												${pagePeople.repairname}
											</option>
										</s:iterator>
									</select>
									<input type="button" id="addrepairMan" value="增加维修人员"
										onclick="addPeople()" style="display: none;">
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center"
								style="font-size: 18px; color: black;">
								<input type="submit" value="报修"
									style="width: 90px; height: 60px;">
								<input type="reset" value="取消"
									style="width: 90px; height: 60px;">
								<input class="input" style="width: 90px; height: 60px;" onclick="window.history.back();" type="button" value="返回"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//添加维修人员
function addPeople() {
	$("#repairMan").clone(true).insertAfter("#repairMan");
}
</script>
	</body>
</html>
