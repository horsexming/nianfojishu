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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
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
					${title}
				</h3>
				<div align="left">
					计划编号: ${orderNum}
				</div>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							件号
						</td>
						<td align="center">
							产品名称
						</td>
						<td align="center">
							分配数量
						</td>
						<td align="center">
							入库数量
						</td>
						<td align="center">
							备注
						</td>
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
							${pageList.pieceNumber }
						</td>
						<td>
							${pageList.name}
						</td>
						<td>
							${pageList.num}
						</td>
						<td>
							${pageList.quantityCompletion}
						</td>
						<td>
							${pageList.remark }
						</td>
						<s:if test="bol==true">
							<td>
								<input type="button" value="修改"
									style="width: 60px; height: 30px;"
									onclick="update(${pageList.id})" />
							</td>
						</s:if>
					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage != null">
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:if>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
			function update(id){
				window.location="internalOrder_initProduct.action?id="+id+"&customeId="+${id};
			}
			    function detail(id){
				window.location="orderManager_queryDetail.action?id="+id;
			}
		</SCRIPT>
	</body>
</html>
