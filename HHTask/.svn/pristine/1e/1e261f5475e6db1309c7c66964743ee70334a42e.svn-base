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
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center">
				<h3>
					入库查询
				</h3>
				<br />
				<form action="storage_queryStorageByCondition.action" method="post"
					onsubmit="return testTime()" name="myForm">
					<table class="table">
						<tr>
							<th>
								品名：
							</th>
							<td>
								<input type="text" name="vsto.matetag" value="${vsto.matetag}" />
							</td>
							<th>
								规格：
							</th>
							<td>
								<input type="text" name="vsto.format" value="${vsto.format}" />
							</td>
							<th>
								仓库：
							</th>
							<td>
								<select name="vsto.storage">
									<option></option>
									<s:iterator id="house" value='{"工具库 ","工装库","备件库","综合库","油料库"}'>
										<s:if test='#house == vsto.storage'>
											<option value="${house }" selected="selected">
												${house }
											</option>
										</s:if>
										<s:else>
											<option value="${house }">
												${house }
											</option>
										</s:else>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								类别：
							</th>
							<td>
								<input type="text" name="vsto.category" value="${vsto.category}" />
								<input type="hidden" name="errorMessage" value="all" />
							</td>
							<th>
								库位：
							</th>
							<td>
								<input type="text" name="vsto.place" value="${vsto.place}" />
							</td>
							<th>
								编号：
							</th>
							<td>
								<input type="text" name="vsto.number" value="${vsto.number}" />
							</td>
						</tr>
						<tr>
							<th>
								开始日期：
							</th>
							<td>
								<input style="width: 155px" class="Wdate" id="startTime"
									type="text" name="vsto.startTime" value="${vsto.startTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th>
								结束日期：
							</th>
							<td>
								<input style="width: 155px" class="Wdate" id="endTime"
									type="text" name="vsto.endTime" value="${vsto.endTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>

						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="查询"
									style="width: 80px; height: 50px;" />
								<input type="button" value="导出Excel"
									style="width: 80px; height: 50px;" onclick="exportExcel();todisabledone(this)" data="downData" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center" style="width: 40px;">
							编号
						</td>
						<td align="center" style="width: 70px;">
							品名
						</td>
						<td align="center">
							规格
						</td>
						<td align="center" width="50px;">
							单位
						</td>
						<td align="center" width="50px;">
							仓库
						</td>
						<td align="center" width="70px;">
							类别
						</td>
						<td align="center" width="60px;">
							库位
						</td>
						<td align="center" width="50px;">
							入库量
						</td>
						<td align="center">
							单价(含税)
						</td>
						<td align="center">
							金额(含税)
						</td>
						<td align="center">
							日期
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							供应商
						</td>
						<td align="center" width="160px;">
							备注
						</td>
						<td width="40px;"></td>
					</tr>
					<s:iterator value="list" id="pageList" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td style="width: 50px;">
							${pageList.number}
						</td>
						<td>
							${pageList.matetag}
						</td>
						<td>
							${pageList.format}
						</td>
						<td>
							${pageList.category}
						</td>
						<td>
							${pageList.storehouse}
						</td>
						<td>
							${pageList.parClass}
						</td>
						<td>
							${pageList.position}
						</td>
						<td>
							${pageList.categoryNum}
						</td>
						<td>
							${pageList.exchangPrice}
						</td>
						<td>
							${pageList.storageTaxMoney}
						</td>
						<td>
							${pageList.date}
						</td>
						<td>
							<s:if test="%{'已开票'.equals(#pageList.bzStatus)}">
								<span style="color: green">${pageList.bzStatus}</span>
							</s:if>
							<s:elseif test="%{'可开票'equals(#pageList.bzStatus)}">
								<span style="color: red">${pageList.bzStatus}</span>
							</s:elseif>
							<s:else>${pageList.bzStatus}</s:else>

						</td>
						<td>
							${pageList.storageCompany}
						</td>
						<td>
							${pageList.more}
						</td>
						<td>
							<a href="storage_initUpdate.action?vsto.id=${pageList.id}">修改</a>
							<a href="storage_del.action?vsto.id=${pageList.id}">删除</a>
						</td>
					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="16" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="16" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function testTime() {
	var startStr = document.getElementById("startTime").value;
	var endStr = document.getElementById("endTime").value;
	if (startStr != "" && endStr != "") {
		var start = startStr.split("-");
		var end = endStr.split("-");
		var startTime = new Date(start[0], start[1], start[2]);
		var endTime = new Date(end[0], end[1], end[2]);
		var myDate = new Date(); //获得当前时间
		myDate.setMonth(myDate.getMonth() + 1);//为当前date的月份+1后重新赋值
		if (startTime <= endTime == false) {
			alert("开始时间不能大于结束时间!请重新选择!谢谢!");
			return false;
		} else if (endTime <= myDate == false) {
			alert("结束时间不能大于当前时间!请重新选择!谢谢!");
			return false;
		}
	}
	return true;
}
function exportExcel() {
	document.forms.myForm.action = "storage_export.action";
	document.forms.myForm.submit();
	document.forms.myForm.action = "storage_queryStorageByCondition.action";
}
</script>
	</body>
</html>
