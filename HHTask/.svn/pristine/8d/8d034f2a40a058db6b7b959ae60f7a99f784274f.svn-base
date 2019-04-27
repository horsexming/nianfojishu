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
			<div id="xitong" >
			</div>
			
			<div  id="printDiv" style="margin-left: 10%;margin-right: 10%">
				<div align="center">
				<h3>
					委托付款协议
				</h3>
				</div>
<%--				<div>--%>
<%--				<p><b>甲方：上海红湖排气系统有限公司</b></p>--%>
<%--				<p><b>乙方：中国航发贵州黎阳航空发动机有限公司</b></p>--%>
<%--				<div>--%>
<%--				&nbsp;&nbsp;&nbsp;&nbsp;经过双方友好协商，甲方委托乙方所属财务部门仅针对于甲方在乙方监管下<br/>--%>
<%--				所开具的委托贷款账户内的资金进行支付和收取的业务。当进行资金支付时，<br/>--%>
<%--				乙方所属财务部门应收到甲方的《付款通知单》（见本协议附表）后方可进行付款。<br/>--%>
<%--				在未征得甲方书面同意时，乙方不得擅自挪用甲方账户内资金，如果违反协议约定内容，<br/>--%>
<%--				甲方有权追究乙方的法律责任。<br/>--%>
<%--				&nbsp;&nbsp;&nbsp;&nbsp;本协议一式贰份，自2017年5月1日起经双方签字盖章后生效，终止日期由双方商定另行约定。--%>
<%--				附表：--%>
<%--				</div>--%>
				<table width="100%" border="1" style="border-collapse: collapse;">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							收款方
						</td>
						<td align="center">
							收款账号
						</td>
						<td align="center">
							付款用途
						</td>
						<td align="center">
							付款金额
						</td>
						<td align="center">
							备注
						</td>
						<td align="center">
							应付登记单号
						</td>
					</tr>
					<s:iterator value="list" id="pageescrow" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td align="center">
							${pageescrow.shoukuanFang}
						</td>
						<td align="center">
							${pageescrow.shoukuanZhanghao}
						</td>
						<td align="center">
							${pageescrow.fukuanYongtu}
						</td>
						<td align="right" >
							${pageescrow.yingfuJine}
						</td>
						<td>
						</td>
						<td align="center">
							${pageescrow.zijinshiyongNum}
						</td>
						
					</tr>
					</s:iterator>
				</table>
				<div style="width: 25%;float: left;"><p>批准（法人签字）：<p/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
						<s:if
										test="qianmingList!=null&&qianmingList.size()>0">
										<img
											src="<%=path%><s:property value="qianmingList.get(qianmingList.size()-1)"/>"
											style="width: 50px; height: 50px" />
									</s:if>
				</div>
				<div style="width: 25%;float: left;">
				<p>审核（财务签字）：<p/>
<%--				<s:if test="qianmingList!=null&&qianmingList.size()>1">--%>
<%--										<s:iterator value="qianmingList" id="qmUrl"--%>
<%--											begin="0" end="qianmingList.size()-2">--%>
<%--											<img src="<%=path%><s:property value="#qmUrl"/>"--%>
<%--												style="width: 50px; height: 50px" />--%>
<%--										</s:iterator>--%>
<%--									</s:if>--%>
<%--									<s:elseif--%>
<%--										test="qianmingList!=null&&qianmingList.size()==1">--%>
<%--										<img--%>
<%--											src="<%=path%><s:property value="qianmingList.get(0)"/>"--%>
<%--											style="width: 50px; height: 50px" />--%>
<%--									</s:elseif>--%>
				</div>
				<div style="width: 25%;float: left;"><p>制表（财务人员签字）：<p/></div>
				<div style="width: 25%;float: left;"><p>审核日期：${spTime}<p/></div>
				<div style="clear: both;"></div>
				<br/>
				<br/>
				<br/>
				<div style="width: 49%;float: left;"><p>甲方：${companyInfo.name}</></div>
				<div style="width: 49%;float: left;"> <p>乙方：${bwtCompany}</></div>
				<div style="clear: both;"></div>
				<div style="width: 49%;float: left;"><p>日期：</></div>
				<div style="width: 49%;float: left;"> <p>日期：</></div>
				<div style="clear: both;"></div>
			</div>
			<div align="center">
				<form action="EscrowAction_print.action">
					<s:iterator value="ids" id="ttt">
					<input name="ids" value="${ttt}" type="hidden">
					</s:iterator>
					<input id="dyBtn" value="打印" type="submit" style="width: 60px;height: 40px;"  onclick="pagePrint('printDiv','sy');">
				</form>
			</div>
				</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function chageNum(obj) {
	var inputs = document.getElementsByTagName("input");
	var num=0;
	for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "checkbox") {
				var checkBox = inputs[i];
				if (checkBox.checked == true) {
						num++;
				}
			}
	}
	if(num==0){
		$("#dyBtn").attr("disabled","disabled");
	}else{
		$("#dyBtn").removeAttr("disabled");
	}
}
</script>
	</body>
</html>
