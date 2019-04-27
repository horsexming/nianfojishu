<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<br/>
		<s:if test="spcgroupsList == null || spcgroupsList.size() == 0">
			<h2 style="font-size: x-large;">SPC 群数组大小添加</h2>
			<form action="SpcControlAction_addspcGroups.action" method="post">
				<table class="table" style="width: 50%;">
					<tr>
						<th>
							群数组大小（n）
						</th>
						<th>
							A2
						</th>
						<th>
							D4
						</th>
						<th>
							D3
						</th>
					</tr>
					<tr>
						<th>
							<input type="text" style="width: 50px;" name="spcgroupsList[0].gropsSize" value="2"/>
						</th>
						<th>
							<input type="text" style="width: 50px;" name="spcgroupsList[0].a2" value=""/>
						</th>
						<th>
							<input type="text" style="width: 50px;" name="spcgroupsList[0].d4" value=""/>
						</th>
						<th>
							<input type="text" style="width: 50px;" name="" value=""/>
						</th>
					</tr>
					<tr>
						<th>
							<input type="text" style="width: 50px;" name="spcgroupsList[1].gropsSize" value="3"/>
						</th>
						<th>
							<input type="text" style="width: 50px;" name="spcgroupsList[1].a2" value=""/>
						</th>
						<th>
							<input type="text" style="width: 50px;" name="spcgroupsList[1].d4" value=""/>
						</th>
						<th>
							<input type="text" style="width: 50px;" name="" value=""/>
						</th>
					</tr>
					<tr>
						<th>
							<input type="text" style="width: 50px;" name="spcgroupsList[2].gropsSize" value="4"/>
						</th>
						<th>
							<input type="text" style="width: 50px;" name="spcgroupsList[2].a2" value=""/>
						</th>
						<th>
							<input type="text" style="width: 50px;" name="spcgroupsList[2].d4" value=""/>
						</th>
						<th>
							<input type="text" style="width: 50px;" name="" value=""/>
						</th>
					</tr>
					<tr>
						<th>
							<input type="text" style="width: 50px;" name="spcgroupsList[3].gropsSize" value="5"/>
						</th>
						<th>
							<input type="text" style="width: 50px;" name="spcgroupsList[3].a2" value=""/>
						</th>
						<th>
							<input type="text" style="width: 50px;" name="spcgroupsList[3].d4" value=""/>
						</th>
						<th>
							<input type="text" style="width: 50px;" name="" value=""/>
						</th>
					</tr>
				</table>
				<input type="submit" value="添加" class="input"/>
			</form>
		</s:if>
		<s:else>
				<h2 style="font-size: x-large;">SPC 管制图-均值极差控制图数据填写</h2>
				<br/>
			<form action="SpcControlAction_addspcControl.action" method="post">
			<b>工位号:</b>	<input type="text" value="${machine.workPosition}" name="spcControl.gongwei" readonly="readonly"/>
			<b>设备号:</b>	<input type="text" value="${machine.no}" name="spcControl.shebeiNo" readonly="readonly"/>
				<table class="table" id="mytable">
					<tr>
						<th colspan="3">
							项目名称
						</th>
						<td  colspan="3">
							<input type="text" name="spcControl.proName"  style="width: 135px;"/>
						</td>
						<th colspan="2">
							测量项目
						</th>
						<td colspan="2">
							<input type="text" name="spcControl.clcontent" style="width: 90px;"/>
						</td>
						<th colspan="2">
							名义值
						</th>
						<td colspan="2">
							<input type="text" name="spcControl.nominalValue" style="width: 90px;"/>
						</td>
						<th colspan="1">
							上限
						</th>
						<td colspan="2">
							<input type="text" name="spcControl.ceilValue" style="width: 90px;"/>
						</td>
						<th colspan="2">
							测量仪器
						</th>
						<td colspan="3">
							<input type="text" name="spcControl.clinstrument" style="width: 135px;"/>
						</td>
						<th colspan="3">
							测量单位
						</th>
						<td colspan="1">
							<input type="text" name="spcControl.clunit" style="width: 45px;"/>
						</td>
						<th colspan="1">
							版本
						</th>
					</tr>
					<tr>
						<th colspan="3">
							物料名称
						</th>
						<td  colspan="3">
							<input type="text" name="spcControl.wlName" style="width: 135px;"/>
						</td>
						<th colspan="2">
							物料编码
						</th>
						<td colspan="2">
							<input type="text" name="spcControl.markId"  style="width: 90px;"/>
						</td>
						<th colspan="2">
							公差(±)
						</th>
						<td colspan="2">
							<input type="text" name="spcControl.tolerance"  style="width: 90px;"/>
						</td>
						<th colspan="1">
							下限
						</th>
						<td colspan="2">
							<input type="text" name="spcControl.floorValue"  style="width: 90px;"/>
						</td>
						<th colspan="2">
							模穴号
						</th>
						<td colspan="3">
							<input type="text" name="spcControl.mxNo"  style="width: 135px;"/>
						</td>
						<th colspan="3">
							群组数大小
						</th>
						<td colspan="1">
							<select name="spcControl.groupsize" style="width: 45px;" onchange="changtr(this)">
								<s:iterator value="spcgroupsList" var="pagespcgroups">
									<s:if test="#pagespcgroups.gropsSize == 3">
										<option value="${pagespcgroups.gropsSize }" selected="selected">${pagespcgroups.gropsSize  }</option>
									</s:if>
									<s:else>
										<option value="${pagespcgroups.gropsSize }" >${pagespcgroups.gropsSize  }</option>
									</s:else>
								</s:iterator>
							</select>
						</td>
						<th colspan="1">
							v1.0
						</th>
					</tr>
					<tr>
						<th>Date</th>
						<c:forEach var="index" begin="0" end="24" step="1" >
							<td>
								<input type="text" name="spcControl.spcControlGroupList[${index}].cldate" 
									onClick="WdatePicker({dateFmt:'MM-dd',skin:'whyGreen'})"	style="width: 45px;"/>
							</td>
						</c:forEach>
						<th>Date</th>
					</tr>
					<tr>
						<th>n</th>
						<c:forEach var="index" begin="0" end="24" step="1" >
							<td>
								<input type="text" name="spcControl.spcControlGroupList[${index}].groupsNO" value="${index+1}" readonly="readonly"  style="width: 45px;"/>
							</td>
						</c:forEach>
						<th>n</th>
					</tr>
					<tr id="tr_1">
						<th>
							x1
						<input type="hidden" value="" name="size"/>
						</th>
						<c:forEach var="index" begin="0" end="24" step="1" >
							<td>
								<input type="text" name="spcControl.spcControlGroupList[${index}].clvalues"  
								onchange="numyanzheng(this)"	style="width: 45px;"/>
							</td>
						</c:forEach>
						<th>x1</th>
					</tr>
					<tr id="tr_2">
						<th>
							x2
							<input type="hidden" value="" name="size"/>
						</th>
						<c:forEach var="index" begin="0" end="24" step="1" >
							<td>
								<input type="text" name="spcControl.spcControlGroupList[${index}].clvalues"  
								onchange="numyanzheng(this)"	style="width: 45px;"/>
							</td>
						</c:forEach>
						<th>x2</th>
					</tr>
					<tr id="tr_3">
						<th>
							x3
							<input type="hidden" value="" name="size"/>
						</th>
						<c:forEach var="index" begin="0" end="24" step="1" >
							<td>
								<input type="text" name="spcControl.spcControlGroupList[${index}].clvalues"  
									onchange="numyanzheng(this)" style="width: 45px;"/>
							</td>
						</c:forEach>
						<th>x3</th>
					</tr>
				</table>
				<input type="submit" value="添加" class="input"/>
			</form>
		</s:else>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function changtr(obj){
	var num = parseInt(obj.value);
	var size = document.getElementsByName("size").length;
	var num0 = num-size;
	if(num0>0){
		for(var i=0;i<num0;i++){
			$("#mytable").append('<tr id="tr_'+(size+i+1)+'"><th>x'+(size+i+1)+'<input type="hidden" value="" name="size"/></th>' +
			'<c:forEach var="index" begin="0" end="24" step="1" ><td><input type="text" onchange="numyanzheng(this)" name="spcControl.spcControlGroupList[${index}].clvalues"  style="width: 45px;"/></td></c:forEach>' +
			'<th>x'+(size+i+1)+'</th>  </tr>');
		}
	}else{
		for(var i=0;i<Math.abs(num0);i++){
			$('#tr_'+(size-i)).remove();
		}
	}
}

</SCRIPT>
	</body>
</html>
