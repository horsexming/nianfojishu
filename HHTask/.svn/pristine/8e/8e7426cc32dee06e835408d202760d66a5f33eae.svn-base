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
					修改库存记录
				</h3>
				<form action="store_updateStore.action" method="post">
					<table border="1" style="border-collapse: collapse;">
						<tr>
							<td>
								编号：
								<input type="text" name="store.number" value="${store.number}" />
							</td>
							<td align="right">
								名称：
								<input type="text" name="store.matetag"
									value="${store.matetag}" />
							</td>
						</tr>
						<tr>
							<td>
								规格：
								<input type="text" name="store.format" value="${store.format}" />
							</td>
							<td align="right">
								单位：
<%--								<input type="text" name="store.unit" value="${store.unit}" />--%>
								<select name="store.unit"  id="category">
									<option selected="selected" value="${store.unit}">${store.unit}</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								仓库：
								<select name="store.storehouse">
									<s:iterator id="house" value='{"工具库 ","工装库","备件库","综合库","油料库"}'>
										<s:if test='#house == store.storehouse'>
											<option value="${house}" selected="selected">
												${house}
											</option>
										</s:if>
										<s:else>
											<option value="${house}">
												${house}
											</option>
										</s:else>
									</s:iterator>
								</select>
								<%-- <input type="text" name="store.storehouse" value="${store.storehouse }"/>
						--%>
							</td>
							<td>
								类别：
								<select name="store.parClass">
									<s:iterator id="parClass"
										value='{"模具 ","夹具 ","工具 ","消耗品","工装备件","弯管备件","办公用品","量具","检具"}'>
										<s:if test='#parClass == store.parClass'>
											<option value="${parClass}" selected="selected">
												${parClass}
											</option>
										</s:if>
										<s:else>
											<option value="${parClass}">
												${parClass}
											</option>
										</s:else>
									</s:iterator>
								</select>
								<%-- <input type="text" name="store.parClass" value="${store.parClass }"/>--%>
							</td>
						</tr>
						<tr>
							<td>
								数量：
								<input type="text" readonly="readonly" name="store.total"
									value="${store.total}" />
							</td>
							<td align="right">
								库位：
								<input type="text" name="store.place" value="${store.place}" />
							</td>
						</tr>
						<tr>
							<td>
								零件号：
								<input type="text" name="store.serverCardId"
									value="${store.serverCardId}" />
							</td>
							<td align="right">
								车型：
								<input type="text" name="store.carModel"
									value="${store.carModel}" />
							</td>
						</tr>
						<tr>
							<td>
								入库状态：
								<select name="store.classify">
									<s:iterator id="b" value="{'可借用','领用'}" status="status">
										<s:if test="#b == store.classify">
											<option value="${b}" selected="selected">
												${b}
											</option>+
									    </s:if>
										<s:else>
											<option value="${b}">
												${b}
											</option>
										</s:else>
									</s:iterator>
								</select>
								
							</td>
							<td align="center" colspan="2">
								备注：
								<textArea rows="4" cols="20" name="store.more">${store.more }</textArea>
							</td>
						</tr>
						<tr>
							<td><select name="store.lingyongZhouqi"></select></td>
							<td>&nbsp;</td>
						</tr>
						<TR>
							<td align="center" colspan="2">
								<input type="hidden" name="store.id" value="${store.id }" />
								<input type="submit" value="更改"
									style="width: 100px; height: 50px;" />
							</td>
						</TR>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript">
	$(function(){
		getUnit("category");
	})
	</script>
</html>
