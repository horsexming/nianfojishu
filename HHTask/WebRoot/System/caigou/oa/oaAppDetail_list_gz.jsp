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
	<body onload="createDept('detailAppDept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<form
					action="oaAppDetailAction!findOADetail_1.action?powerTag=${powerTag}&tag=${tag}"
					method="post">
					<table class="table">
						<tr>
							<th>
								部门
								<br>
								Department
							</th>
							<th>
								<select name="oadetail.detailAppDept" style="width: 130px;"
									id="detailAppDept" >
									<option value="">
										选择部门
									</option>
									<option value="${oadetail.detailAppDept}">
										${oadetail.detailAppDept}
									</option>
								</select>
							</th>
							<th>
								物品名称
								<br>
								Item Name
							</th>
							<th>
								<input type="text" style="width: 130px;"
									name="oadetail.detailAppName"
									value="${oadetail.detailAppName }" size="80px" />
							</th>
						</tr>
						<tr>
							<th>
								规格
								<br>
								Specifications
							</th>
							<th>
								<input type="text" style="width: 130px;"
									name="oadetail.detailFormat" value="${oadetail.detailFormat}"
									size="80px" />
							</th>
							<th>
								编号
								<br />
								Serial number
							</th>
							<th>
								<input type="text" style="width: 130px;"
									name="oadetail.detailSeqNum" value="${oadetail.detailSeqNum}"
									size="80px" />
							</th>
						</tr>
						<tr>
							<th colspan="4">
								<input type="button" style="width: 90px; height: 50px;"
									value="扫描库位码" onclick="getcheckList2()"/>
								<input type="submit" style="width: 90px; height: 50px;"
									value="查询(Query)" />
							</th>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="30px"
						style="border-collapse: separate;">
						<th align="center">
							选择
						</th>
						<th align="center">
							序号
							<br>
							No.
						</th>
						<th align="center">
							编号
							<br>
							Numbers.
						</th>
						<th align="center">
							名称
							<br>
							Numbers
						</th>
						<th align="center">
							规格
							<br>
							Specifications
						</th>
						<th align="center">
							部门
							<br />
							departments
						</th>
						<th align="center">
							单位
							<br>
							Units
						</th>
						<th align="center">
							申报数量
							<br>
							Declare quantity
						</th>
						<th align="center">
							预算单价
							<br>
							Declare quantity
						</th>
					</tr>
					<s:if test="{list.size()>0}">
						<s:iterator value="list" status="se" id="detail">
							<s:if test="#se.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<input type="checkbox"
									name="wgjId" value="${detail.id}" >
							</td>
							<td>
								<s:property value="#se.index+1" />
							</td>
							<td>
								${detail.detailSeqNum}
							</td>
							<td>
								${detail.detailAppName}
							</td>
							<td>
								${detail.detailFormat}
							</td>
							<td>
								${detail.detailAppDept}
							</td>
							<td>
								${detail.detailUnit}
							</td>
							<td>
								${detail.detailCount}
							</td>
							<td>
								${detail.detailBudgetMoney}
							</td>
						</s:iterator>
						<tr>
							<td colspan="9" align="right">
								共
								<s:property value="total" />
								页 第
								<s:property value="cpage" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />

							</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td colspan="9" style="font-size: 15px; color: red;">
								没有查到工装信息
							</td>
						</tr>
					</s:else>
				</table>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript">
function wagId(){
	var invalue = document.getElementsByName("wgjId");
	var num= invalue.length;
	var mxId= "";
	for (i=0;i<num;i++){
		if(invalue[i].checked == true) {
		   	mxId+=invalue[i].value+",";
		}
	}
	return mxId;
}
function getcheckList2() {
	var mxId= wagId();
	if(mxId == ""){
		alert("请选择待存物品");return false;
	}
	if (typeof (myObj) != "undefined") {
		//打开扫描服务
		myObj.scanGongWei("");
	} else {
		alert("无法打开扫描服务,请检查后重试!");
	}
}
function funFromjs(tm) {
	var mxId= wagId();
	window.location.href = "oaAppDetailAction!findDeliveryRuGuiBacode.action?bacode=" + tm +"&mxId="+mxId+"&tag=gz";
}
</script>
</html>
