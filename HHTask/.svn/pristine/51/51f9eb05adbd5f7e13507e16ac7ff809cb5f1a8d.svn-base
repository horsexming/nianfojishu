<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="customerOpinionAction_showList.action"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					供应商季度合格率
				</h3>
				<table width="100%" border="0" style="border-collapse: collapse;" class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							供应商名称
						</td>
						<td align="center">
							季度
						</td>
						<td align="center">
							合格数量
						</td>
						<td align="center">
							总送货数量
						</td>
						<td align="center">
							合格率
						</td>
						<td align="center">
							总送货批次数
						</td>	
						<td align="center">
							送货批次总合格数
						</td>					
						<td align="center">
							批次合格率
						</td>						
						<td align="center">
							实际送货数量
						</td>						
						<td align="center">
							申请数量
						</td>	
						<td align="center">
							缺件率
						</td>						
					</tr>
					<s:iterator value="quarterHegeList" id="dh" status="pageStatus">
						
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
						<td align="center">
							${dh.gysname}
						</td>
						<td align="center">
							${dh.quarter}
						</td>
						<td align="left">
							${dh.hgCount}
						</td>
						<td align="left">
							${dh.shCount}
						</td>
						<td align="center">
						<fmt:formatNumber value="${dh.qualifiedrate*100}" pattern="#.00"/>%
						</td>
						<td align="center">
							${dh.piciCount}
						</td>						
						<td align="center">
							${dh.picihgCount}
						</td>	
						<td align="center">
						<fmt:formatNumber value="${dh.piciQualifiedrate*100}" pattern="#.00"/>%
						</td>					
						<td align="center">
							${dh.shijiyunsongCount}
						</td>						
						<td align="center">
							${dh.shenqinCount}
						</td>		
						<td align="center">
							<fmt:formatNumber value="${dh.quejianrate*100}" pattern="#.00"/>%
						</td>		
					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="12" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
						<s:if test="successMessage!=null">
						<tr>
							<td colspan="12" align="center" style="color: red">
								${successMessage}
						</td>
					</tr>
                          </s:if>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
</script>
	</body>
</html>
