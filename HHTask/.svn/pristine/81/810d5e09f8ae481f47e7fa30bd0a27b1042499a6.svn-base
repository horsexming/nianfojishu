<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<body onload="createDept('selectDept')">
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title"></span>
						</td>
						<td align="right">
							<img alt="" src="images/closeImage.png" width="30" height="32"
								onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
				</div>
				<div id="addProductPrice"
					style="background-color: #ffffff; width: 100%;">
					<form action="EquipmentAction!add.action" method="post">
						<input type="hidden" name="machine.id" value="${machine.id}" />
						<table class="table">
							<tr>
								<td style="font-size: 18px; color: black;">
									&nbsp; &nbsp;工区
								</td>
								<td>
									<input type="text" name="maintenance.workArea" />
								</td>
								<td style="font-size: 18px; color: black;">
									&nbsp;&nbsp; 工位
								</td>
								<td>
									<input type="text" name="maintenance.workPosition" />
								</td>
								<td align="center" style="font-size: 18px; color: black;">
									设备编号
								</td>
								<td>
									<input type="text" name="maintenance.no" />
								</td>
							</tr>
							<tr>
								<td style="font-size: 18px; color: black;">
									设备名称
								</td>
								<td>
									<input type="text" name="maintenance.name" />
								</td>
								<td style="font-size: 18px; color: black;">
									设备类型
								</td>
								<td>
									<input type="text" name="maintenance.type" />
								</td>
								<td align="center" style="font-size: 18px; color: black;">
									故障状况
								</td>
								<td>
									<input type="text" name="maintenance.faultDetail" />
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
									<input type="submit" value="报修/打印"
										style="width: 90px; height: 60px;">
									<input type="reset" value="取消"
										style="width: 90px; height: 60px;">
								</td>
							</tr>
						</table>
					</form>
				</div>

			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
					<a href="EquipmentAction!findAlll.action?maintenance.name="
						style="color: #ffffff">查询报修信息</a>
				</div>
			</div>


			<div align="center">
				<s:if test="pageStatus==null">
					<form action="EquipmentAction!findAlll.action" method="post"
						style="margin: 0px;">
						<table width="100%">
							<tr>
								<th colspan="6">
									其它设备报修管理
								</th>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<s:if test="pageStatus==null">
										<input type="button" onclick="chageDiv('block');"
											value="其它设备报修" style="width: 100px; height: 50px" />
									</s:if>
								</td>
							</tr>
						</table>
					</form>

					<div id="number" style="display: none;">
						<!-- <div id="printDiv" align="center"
							style="width: 230px; height: 100px;"> -->
						<table
							style="font-size: 11px; border-collapse: collapse; width: 230px; height: 99x; margin_left: 5px;">
							<tr>
								<th colspan="2" align="center" style="font-size: 14px;">
									报&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 修
								</th>
								<td style="font-size: 10px;">
									${maintenance.more}
								</td>
							</tr>
							<tr>
								<td align="left">
									工区:
								</td>
								<td align="left">
									工位:
								</td>

								<td align="left">
									设备编号:
								</td>
							</tr>
							<tr>
								<th align="left">
									${maintenance.workArea}
								</th>
								<th align="left">
									${maintenance.workPosition}
								</th>
								<th align="left">
									${maintenance.no}
								</th>
							</tr>
							<tr>
								<td align="left">
									设备类型:
								</td>
								<td align="left">
									设备名称:
								</td>
								<td align="left">
									所在班组:
								</td>
							</tr>
							<tr>
								<th align="left">
									${maintenance.type}
								</th>
								<th align="left">
									${maintenance.name}
								</th>
								<th align="left">
									${maintenance.classGroup}
								</th>
							</tr>
							<tr>
								<td align="left" colspan="3">
									故障状况:
									<font style="font-weight: bolder;">${maintenance.faultDetail}</font>
								</td>

							</tr>
							<tr>
								<td colspan="3" align="center">
									<s:if test="maintenance!=null">
										<img
											src="barcode.action?msg=${maintenance.barcode}&type=code39"
											style="height: 50px; width: 230px;">
									</s:if>
								</td>
							</tr>
						</table>
					</div>
			</div>
			</s:if>

		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
onload = function() {
	var machine = "${machine}";//设备
	var maintenance = "${maintenance.id}";//报修信息
	if (maintenance != "") {
		document.getElementById("number").style.display = "block";
		pagePrint('printDiv');
	} else {
		if (machine != "") {
			document.getElementById("showSb").style.display = "block";
		}
	}
}
//添加维修人员
function addPeople() {
	$("#repairMan").clone(true).insertAfter("#repairMan");
}
</script>
		</div>
	</body>
</html>
