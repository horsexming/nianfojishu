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
					领用记录管理
				</h3>
				<br />
				<form action="consuming_queryConsumingByCondition.action"
					method="post" onsubmit="return testTime()" name="myForm">
					<table class="table">
						<tr>
							<th align="right">
								部门：
							</th>
							<td>
								<input type="text" name="voc.dept" value="${voc.dept}" />
							</td>
							<th align="right">
								卡号：
							</th>
							<td>
								<input type="text" name="voc.cardId" value="${voc.cardId}" />
							</td>
							<th align="right">
								领用人：
							</th>
							<td colspan="2">
								<input type="text" name="voc.person" value="${voc.person}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								物品名称：
							</th>
							<td>
								<input type="text" name="voc.name" value="${voc.name}" />
							</td>
							<th align="right">
								规格：
							</th>
							<td>
								<input type="text" name="voc.standard" value="${voc.standard}" />
							</td>
							<th align="right">
								编号：
							</th>
							<td colspan="2">
								<input type="text" name="voc.number" value="${voc.number}" />
							</td>

						</tr>
						<tr>
							<th align="right">
								仓库：
							</th>
							<td>
								<input type="text" name="voc.storehouse"
									value="${voc.storehouse}" />
							</td>
							<th align="right">
								开始日期：
							</th>
							<td>
								<input style="width: 155px" class="Wdate" type="text"
									name="voc.startTime" value="${voc.startTime}" id="startTime"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
								结束日期：
							</th>
							<td>
								<input style="width: 155px" class="Wdate" id="endTime"
									type="text" name="voc.endTime" value="${voc.endTime}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="hidden" name="errorMessage" value="all" />
								<input type="submit" value="查询"
									style="width: 80px; height: 50px;" />
								<input type="button" value="导出Excel"
									style="width: 80px; height: 50px;" onclick="exportExcel();todisabledone(this)" data="downData" />
							</td>
						</tr>
					</table>
				</form>
				<form action="consuming_printStorage.action" method="post" onsubmit="return vali()">
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px" align="center">
					<td></td>
						<td>
							序号
						</td>
						<td>
							卡号
						</td>
						<td style="width: 50px;">
							领用人
						</td>
						<td>
							部门
						</td>
						<td>
							编号
						</td>
						<td>
							物品名称
						</td>
						<td>
							规格
						</td>
						<td>
							车型
						</td>
						<td style="width: 30px;">
							数量
						</td>
						<td>
							单位
						</td>
						<td style="width: 60px;">
							仓库名
						</td>
						<td style="width: 80px;">
							领用时间
						</td>
						<td style="width: 40px;"></td>
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
						<td><input type="checkbox" name="selected"  value="${pageList.id}"/></td>
						<td>
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td>
								${pageList.cardNum}
						</td>
						<td>
							${pageList.peopleName}
						</td>
						<td>
							${pageList.dept}
						</td>
						<td>
							${pageList.number}
						</td>
						<td>
							${pageList.matetag}
						</td>
						<td>
							${pageList.format}
						</td>
						<td>
							${pageList.carType}
						</td>

						<td>
							${pageList.num}
						</td>
						<td>
							${pageList.unit}
						</td>
						<td>
							${pageList.storehouse}
						</td>

						<td>
							${pageList.date}
						</td>
						<td>
							<a href="consuming_del.action?voc.id=${pageList.id}"
								onclick="return del()">删除</a>
						</td>
					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="15" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="15" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
					<tr>
						<td colspan="15">
							<input type="submit" style="width: 80px; height: 50px;" value="打印"/>
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
		function vali(){
 				var selectList = document.getElementsByName("selected");
 				for(var i = 0;i<selectList.length;i++){
    				if(selectList[i].checked){
     					return true;
    					}
  					}
  				alert("请选择需要打印的记录！谢谢");
  				return false;
			}
		 
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
//导出action跳转
function exportExcel() {
	document.forms.myForm.action = "consuming_export.action";
	document.forms.myForm.submit();
	document.forms.myForm.action = "consuming_queryConsumingByCondition";
}
function del() {
	if (confirm("确定删除吗?")) {
		return;
	} else {
		return false;
	}
}
</script>
	</body>
</html>
