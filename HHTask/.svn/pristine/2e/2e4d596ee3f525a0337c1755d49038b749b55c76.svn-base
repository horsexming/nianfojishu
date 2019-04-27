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
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center">
				<h3>
					${projectOrderVo.month}(${projectOrderVo.cusName})
					<br/>
					<font color="red">${successMessage}</font>
				</h3>
				<div align="left">
					试制单编号: ${projectOrderVo.orderNO}
				</div>
				<form action="projectOrderAction_addProCard.action" method="post"
					onsubmit="return window.confirm('根据bom结构的不同,可能生成时间会较长,请耐心等待!谢谢~')">
					<input name="projectOrder.id" value="${projectOrderVo.id}" type="hidden" />
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
								总数量
							</td>
							<td align="center">
								可分配数量
							</td>
							<td align="center">
								转换数量
							</td>
						</tr>
						<s:iterator value="projectOrderVo.projectOrderPart" id="pageList" status="pageindex">
							<s:if test="#pageindex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#pageindex.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#pageindex.index+1" />
								</font>
							</td>
							<td>
								${pageList.markId}
							</td>
							<td>
								${pageList.partName}
							</td>
							<td>
								${pageList.partNum}
							</td>
							<td>
								${pageList.partNum-pageList.hasturn}
							</td>
							<td><input type="hidden" name="pOrderPartList[<s:property value="#pageindex.index" />].id"
							 value="${pageList.id}"/>
							 <s:if test="#pageList.partNum==#pageList.hasturn">
							  0
							 </s:if>
							 <s:else>
									<input name="pOrderPartList[<s:property value="#pageindex.index" />].wantturn" id="wantturn"
										value="${pageList.partNum-pageList.hasturn}" onblur="mustBeNumber('wantturn')"/>
							 </s:else>
							</td>
						</s:iterator>
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" value="转换" class="input" />
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
		<SCRIPT type="text/javascript">
			function update(id){
				window.location="internalOrder_initProduct.action?id="+id+"&customeId="+${id};
			}
		</SCRIPT>
	</body>
</html>
