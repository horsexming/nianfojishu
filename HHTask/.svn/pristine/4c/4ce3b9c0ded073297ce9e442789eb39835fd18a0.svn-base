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
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
				<div>
					<form action="Detail_query.action" method="post">
						输入月份：<input id="month" style="width: 155px" class="Wdate" type="text" name="month" 	onclick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" /> <input type="submit" value="查询">
					</form>
				</div>
				<div  align="center" id="hello" >
				
				<form action="" method="post" id="from1">
					<input type="hidden" name="month" value="${month}" >
					<table id="showTable" width="100%" border="0" style="border-collapse: collapse;">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								选择
							</th>
							<th align="center">
								序号
							</th>
							<th align="center">
								采购产品
							</th>
							<th align="center">
								月份
							</th>
							<th align="center">
								数量
							</th>
							<th align="center">
								件号
							</th>
							<th align="center">
								规格
							</th>
							<th align="center">
								说明
							</th>
							<th align="center">
								计划单编号
							</th>
							<th align="center">
								状态
							</th>
						</tr>
						<s:iterator value="details" id="d" status="detailsStatus">
							<s:if test="#detailsStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
							<s:if test="#d.purchase.equals('未审核')">
								<input type="checkbox" name="details.id" value="${d.id}">
							</s:if><s:else>
								<input type="checkbox" disabled="disabled" name="details.id" value="${d.id}">
							</s:else>
							</td>
							<td>
								<s:if test="#detailsStatus.index%2==0">
									<font color="red"> <s:property value="#detailsStatus.index+1" /> </font>
								</s:if>
								<s:else>
									<s:property value="#detailsStatus.index+1" />
								</s:else>
							</td>
	
							<td>
								${d.templet.name}
							</td>
							<td>
								${d.month}
							</td>
							<td>
								${d.quantity}
							</td>
							<td>
								${d.templet.partsNumber}
							</td>
							<td>
								${d.templet.specification}
							</td>
							<td>
								${d.explanation}
							</td>
							<td>
								${d.detailNumber}
							</td>
							<td>
								${d.purchase}
							</td>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="8" align="center">
									<input type="button" value="查看采购明细" onclick="changeTemplet('look',this.form)" style="width: 90px; height: 40px;" />
									<input type="button" value="生成Excel" onclick="changeTemplet('excel',this.form)" style="width: 90px; height: 40px;" />
								</td>
							</s:if>
							<s:else>
								<td colspan="8" align="center" style="color: red">
									${errorMessage}
								</td>
							</s:else>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		
		<s:if test="details == null || details.size < 1">
			<script type="text/javascript">
				document.getElementById("showTable").style.display = "none";
			</script>
		</s:if>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
<script type="text/javascript">
function changeTemplet(status, form){
	if(!checkCheckBox()){
		alert("请至少选中一项");
		return false;
	}
	if(status == "excel"){
		form.action = "Detail_exportExcel.action";
		form.submit();
	} else if(status == "look"){
		form.action = "Detail_getMultipleDetail.action";
		form.submit();
	}
}

function checkCheckBox(){
	var k = document.getElementsByName("details.id");
	var b = false;
	for(var a = 0; a<k.length; a++){
		if(k[a].checked){
			return true;
		}
	}
	return b;
}
</script>
</html>
