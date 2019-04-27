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
					<font color="#ffffff">功能使用</font>
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="zhaobiaoAction!listAll.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			<hr color="#BFEFFF">
			<div align="center" id="d1">
					  <form action="DingdanAction!listAll.action" method="post"  theme="simple">
			  <table class="table">
			   		<tr><th align="center">所属公司：</th><td><input type="text" id="zhUser.name" name="zhUser.name" /></td>
			   			<th align="center">联系人：</th><td><input type="text" id="zhUser.cperson" name="zhUser.cperson" /></td>
			   			<td rowspan="2"><input type="submit" value="查询"   class="input"/></td>
			   		</tr>
			   		<tr>
			   			<th align="center">注册时间：</th><td>
			   			
			   				<input class="Wdate" type="text" id="zhUser.time"
									name="zhUser.time"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
			   			</td>
			   			<th align="center">公司地址：</th><td>
			   			<input type="text" id="zhUser.companydz" name="zhUser.companydz" />
			   			</td>
			   		</tr>
			  
			  </table>
			  </form>
				 <!-- 
			<form action="DingdanAction!printStorage.action" method="post"
					onsubmit="return vali()">
		
		  -->
			<form action="DingdanAction!addJihuadan.action" method="post"
					onsubmit="return vali()">
			
				<table class="table">
					<tr bgcolor="#c0dcf2">
					<td align="center">
							选择
						</td>
						<td align="center">
							序号
						</td>
						<td align="center">
							订单编号
						</td>
						<td align="center">
							总金额
						</td>
						<td align="center">
							客户
						</td>
						<td align="center">
							交付日期
						</td>
						<td align="center">
							跟单人
						</td>
						<td align="center">
							开单人
						</td>
						<td align="center">
							交付状态
						</td>
						<td align="center">
							回款状态
						</td>
						<td></td>
					</tr>
			<s:iterator value="list" id="pageList" status="pageStatus">
						<tr onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb');">
						<td>
						  <input type="checkbox" name="selected"
											value="${pageList.id}" />
						</td>
						<td>
						   ${pageStatus.index+1}
						</td>
						<td>
							${pageList.orderNum }
						</td>
						<s:if test="#pageList.totalAmount == 0.0">
							<td>
								0.00
							</td>
						</s:if>
						<s:else>
							<td>
								${pageList.totalAmount }
							</td>
						</s:else>
						<td>
							${pageList.custome.clientcompanyname}
						</td>
						<td>
							${pageList.paymentDate}
						</td>
						<td>
							${pageList.documentaryPeople }
						</td>
						<td>
							${pageList.billingPeople}
						</td>
						<td>
							${pageList.deliveryStatus }
						</td>
						<td>
							${pageList.backSection }
						</td>
						<td>
							<input type="button" value="产品"
								style="width: 70px; height: 30px;"
								onclick="detail(${pageList.id})" />
						</td></tr>
					
					</s:iterator>
						<s:if test="errorMessage==null">
							<th colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<th colspan="11" align="center" style="color: red">
						</s:else>
						</th>
					</tr>
					<tr>
							<td colspan="19" align="center">
								<input type="submit" style="width: 100px; height: 50px;"
									value="生成采购计划" />
								
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
	<SCRIPT type="text/javascript">
function vali() {
	var nums = document.getElementsByName("selected");
	for ( var i = 0; i < nums.length; i++) {
		if (nums[i].checked) {
			return true;
		}
	}
	alert("请选择需要转换的订单！谢谢");
	return false;
}
function exportExcel(objForm) {
	objForm.action = "sellAction!exportEXCEL.action?tag=sellDetail";
	objForm.submit();
}
    function detail(id){
				window.location="orderManager_queryDetail.action?id="+id;
			}
	</SCRIPT>

</html>
