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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title"></span>
						</td>
						<td align="right">
							<img alt="" src="images/closeImage.png" width="30" height="32"
								onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
				</div>
				<div id="addSpareParts"
					style="background-color: #ffffff; width: 100%;">
					<form
						action="productPriceAction!saveSpareParts.action?id=<%=request.getParameter("id")%>"
						method="post">

						<table>
							<tr>
								<td>
									件号：
									<input type="text" name="spareParts.spmarkId" />
								</td>
								<td>
									产品名称：
									<input type="text" name="spareParts.spgoodsName" />
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<input type="submit" value="添加" />
									&nbsp;
									<input type="reset" value="取消">

								</td>
							</tr>
						</table>
					</form>

				</div>
			</div>
		</div>

		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
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

				<table width="95%" class="table">
					<tr>
						<td colspan="8" align="center">
							<input type="button" onclick="chageDiv('block');" value="添加零组件" />
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="javascript:history.back()">返回</a>
						</td>
					</tr>
					<tr align="center" bgcolor="#c0dcf2"
						style="height: 40px; font-weight: bold;">
						<td>
							序号
						</td>
						<td>
							件号
						</td>
						<td>
							产品名称
						</td>
						<td>
							零件总节拍
						</td>
						<td>
							实际工费用
						</td>
						<td>
							录入时间
						</td>
						<td>
							备注
						</td>
						<td>
							操作
						</td>

					</tr>
					<s:iterator value="list" status="se" id="sell">
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
							<s:property value="#se.index+1" />
						</td>
						<td>
							<s:property value="spmarkId" />
						</td>
						<td>
							<s:property value="spgoodsName" />
						</td>
						<td>
							<s:property value="allWorkHours" />
						</td>
						<td>
							<s:property value="allLaborcost" />
						</td>
						<td>
							<s:property value="entryDate" />
						</td>
						<td>
							<s:property value="more" />
						</td>
						<td>

							<a
								href="productPriceAction!queryProductProcessById.action?id=<s:property value='id' />&tag=queryProductProcee">查看工序</a>
							<!-- 			    	
			    	 -->
							<a
								href="productPriceAction!queryProductProcessById.action?id=<s:property value='id' />&tag=update">修改</a>
							<a
								href="productPriceAction!deleteSpareParts.action?id=<s:property value='id' />">删除</a>


						</td>


						</tr>
					</s:iterator>
					<tr>
						<td colspan="8" align="right">
							共
							<s:property value="total" />
							页 第
							<s:property value="cpage" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />

						</td>
					</tr>

				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
