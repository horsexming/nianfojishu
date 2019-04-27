<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="" style="color: #ffffff">添加功能</a>
				</div>
			</div>

			<div align="center">
				<form action="TijingpriceAction!conditionFind.action" method="post">
					<table>
						<tr>
							<th colspan="6">
								<font size="5">查询单件计价表</font>
							</th>
						</tr>
						<tr>
							<th>
								件号
								<td>
									<input type="text" name="tijiangprice.pricemarkId" />
								</td>
								<th>
									型别
								</th>
								<td>
									<input type="text" name="tijiangprice.pricestyle" />
								</td>
								<th>
									规格
								</th>
								<td>
									<input type="text" name="tijiangprice.priceformt" />
								</td>
						</tr>

						<tr>
							<th>
								状态
							</th>
							<td colspan="5">
								<select name="tijiangprice.pricedefault">
									<option value=""></option>
									<option value="正常使用">
										正常使用
									</option>
									<option value="备用">
										备用
									</option>
								</select>
								&nbsp;&nbsp;&nbsp;
								<input type="submit" value="确  定" />
								&nbsp;&nbsp;&nbsp;
								<a href="TijingpriceAction!findtijingprice.action">查看全部</a>
							</td>
						</tr>
					</table>
				</form>
				<table align="center">
					<tr>
						<th>
							件号
						</th>
						<th>
							型别
						</th>
						<th>
							规格
						</th>
						<th>
							计件单价
						</th>
						<th>
							状态
						</th>
						<th>
							开始数量
						</th>
						<th>
							结束数量
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator id="t" value="tijingpricelist" status="stauts">
						<s:if test="#stauts.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td align="center">
							${t.pricemarkId}
						</td>
						<td align="center">
							${t.pricestyle}
						</td>
						<td align="center">
							${t.priceformt}
						</td>
						<td align="center">
							${t.pricefactPrice}
						</td>
						<td align="center">
							${t.pricedefault}
						</td>
						<td align="center">
							${t.pricesenacount}
						</td>
						<td align="center">
							${t.priceendcount}
						</td>
						<td align="center">
							<a
								href="TijingpriceAction!deletetijingprice.action?id=${t.priceid}"
								onClick="return window.confirm('确认要删除选中的信息吗？')">删除</a> &nbsp;
							<a href="TijingpriceAction!updatefind.action?id=${t.priceid}">修改</a>
							&nbsp;
							<s:if test="#t.pricefilepath!=''">
<%--								<a href="<%=path%>/upload/tijiang/${t.pricefilepath}">查看文件</a>--%>
								<a href="FileViewAction.action?FilePath=/upload/tijiang/${t.pricefilepath}">查看文件</a>
							</s:if>

						</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="16" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
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
	</body>
















</html>
