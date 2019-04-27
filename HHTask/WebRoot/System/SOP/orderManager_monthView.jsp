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
		<div align="center">
			<table class="table" style="width: 100%;">
				<tr bgcolor="#c0dcf2" height="50px">
					<td align="center">
						序号
						<br />
						No.
					</td>
					<td align="center">
						订单编号
						<br />
						Order Number
					</td>
					<td align="center">
						客户
						<br />
						Customers
					</td>
					<td align="center">
						交付日期
						<br />
						Delivery Date
					</td>
					<td align="center">
						跟单人
						<br />
						With a single person
					</td>
					<td align="center">
						开单人
						<br />
						Billing person
					</td>
					<td align="center">
						回款状态
						<br />
						Reimbursement status
					</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
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
					<td>
						${pageList[1] }
					</td>
					<td>
						${pageList[3] }
					</td>
					<td>
						${pageList[4] }
					</td>
					<td>
						${pageList[5] }
					</td>
					<td>
						${pageList[6] }
					</td>
					<td>
						${pageList[7] }
					</td>
					<td>
						<input type="button" value="产品" style="width: 70px; height: 30px;"
							onclick="detail(${pageList[0] })" />
					</td>
					<td>
						<input type="button" value="修改" style="width: 60px; height: 30px;"
							onclick="update(${pageList[0] })" />
					</td>
					<td>
						<input type="button" value="删除" style="width: 60px; height: 30px;"
							onclick="del(${pageList[0] })" />
					</td>
					<td>
						<input type="button" value="进度" style="width: 60px; height: 30px;"
							onclick="jindu('${pageList[0] }')" />
					</td>
					</tr>
					<tr>
						<th align="left" colspan="20"
							style="background-color: #c33e41; border: 0px; margin: 0px; padding: 0px; font-size: 2px;">
							<div align="left"
								style="background-color: #00ff01; width:${pageList[9] /pageList[8]*100}%; text-overflow: ellipsis; white-space: nowrap; height: 100%;">
								&nbsp;&nbsp;&nbsp;&nbsp;订单完成进度(Order completion
								schedule):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="#ffffff">${pageList[9]} / ${pageList[8]}</font>
							</div>
						</th>
					</tr>
				</s:iterator>
			</table>
		</div>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
			function add(){
				window.location="orderManager_initAdd.action";
			}
			function del(id){
				if(window.confirm('确定删除该订单吗?')){
					window.location="orderManager_del.action?id="+id;
				}
			}
			function update(id){
				window.location="orderManager_initUpdate.action?id="+id;
			}
			function detail(id){
				window.location="orderManager_queryDetail.action?id="+id;
			}
			function jindu(id){
				window.open("orderManager_showOrderDetils.action?id="+id,'_showJindu') 
			}
			setTimeout(function() {
	window.location.href="orderManager_findOrderIdsFormMonth.action";
}, 1000 * 2);
		</SCRIPT>
	</body>
</html>
