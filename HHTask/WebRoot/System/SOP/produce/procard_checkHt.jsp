<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div align="center">
				<h3>件号：${pwwApplyDetail.markId}&nbsp;&nbsp;&nbsp;工序号：${pwwApplyDetail.processNOs}&nbsp;&nbsp;
					工序名称：${pwwApplyDetail.processNames}&nbsp;&nbsp;外委类型：${pwwApplyDetail.wwType}</h3>
				</div>
				<br/>
				<div align="center">
					<table class="table">
						<tr>
							<th>序号</th>
							<th>供应商</th>
							<th>件号</th>
							<th>工序号</th>
							<th>工序名称</th>
							<th>含税价</th>
							<th>不含税价</th>
							<th>税率</th>
							<th>外委类型</th>
							<th>操作</th>
						</tr>
						<s:iterator value="priceList" id="pagePrice" status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 50px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td align="center">
								<s:property value="#pageStatus.index+1" />
							</td>
							<td align="right">${pagePrice.gys}</td>
							<td align="center">${pagePrice.partNumber}</td>
							<td align="center">${pagePrice.gongxunum}</td>
							<td align="center">${pagePrice.processNames}</td>
							<td align="right"><fmt:formatNumber value="${pagePrice.hsPrice}" maxFractionDigits="4"/>
							</td>
							<td align="right"><fmt:formatNumber value="${pagePrice.bhsPrice}" maxFractionDigits="4"/></td>
							<td align="right"><fmt:formatNumber value="${pagePrice.taxprice}" maxFractionDigits="4"/></td>
							<td align="center">${pagePrice.wwType}</td>
							<td align="center">
							<s:if test="#pagePrice.flag=='had'">
									<font color="green">已选中</font>
								</s:if>
								<s:else>
							<input type="button" value="选择" onclick="updatePrice(${pagePrice.id},${pwwApplyDetail.id})">
							</s:else>
							</td>
						</s:iterator>
						<s:if test="priceList==null||priceList.size()==0">
						<tr>
							<td align="center" colspan="9"> <font color="red">没有找到待填充的合同，请前往添加合同!</font>
							</td>
						</tr>
						</s:if>
					</table>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function  updatePrice(id,id2){
	if(window.confirm("您是否选定这份合同作为此外委的依据?")){
		window.location.href="ProcardAction!updatePrice.action?id="+id+"&id2="+id2;
	}
}
</SCRIPT>
	</body>
</html>
