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
					<font color="#ffffff">流水卡片管理</font>
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">制作卡片</a>
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="ProcardAction!findAllProcard.action" method="post">
					<table class="table">
						<tr>
							<td>
								条
								<span style="width: 28px;"></span>码：
								<input type="text" name="procard.barcode" />
							</td>
							<td>
								产品名称：
								<input type="text" name="procard.proName" />
							</td>
							<td>
								件
								<span style="width: 28px;"></span>号：
								<input type="text" name="procard.markId" />
							</td>
							<td rowspan="3" align="center">
								<input type="submit" style="width: 90px; height: 70px;"
									value="查询" />
							</td>
						</tr>
						<tr>
							<td>
								本卡片号：
								<input type="text" name="procard.selfCard" />
							</td>
							<td>
								计
								<span style="width: 7px;"></span>划
								<span style="width: 7px;"></span>员：
								<input type="text" name="procard.zhikaren" />
							</td>
							<td>
								车
								<span style="width: 28px;"></span>型：
								<input type="text" name="procard.carStyle" />
							</td>
						</tr>
						<tr>
							<td>
								卡片格式：
								<select name="procard.procardStyle">
									<option value="">
										卡片类型
									</option>
									<option value="root">
										总成卡片
									</option>
									<option value="cs">
										组合卡片
									</option>
									<option value="ys">
										自制卡片
									</option>
								</select>
							</td>
							<td>
								日
								<span style="width: 7px;"></span>期
								<span style="width: 7px;"></span>从：
								<input class="Wdate" type="text" name="startDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />

							</td>
							<td>
								<span style="width: 21px;"></span>到
								<span style="width: 21px;"></span>：
								<input class="Wdate" type="text" name="endDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>


						</tr>
						</form>
						<table class="table">
							<tr bgcolor="#c0dcf2" height="30px"
								style="border-collapse: separate;">
								<th align="center">
									序号
								</th>
								<th align="center">
									条码
								</th>
								<th align="center">
									件号
								</th>
								<th align="center">
									品名
								</th>
								<th align="center">
									本卡片号
								</th>
								<th align="center">
									数量
								</th>
								<th align="center">
									日期
								</th>
								<th align="center">
									计划员
								</th>
								<th align="center">
									车型
								</th>
								<th align="center">
									操作
								</th>
							</tr>

							<s:if test="{list.size()>0}">
								<s:iterator value="list" status="se" id="pageProcard">
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
										${pageProcard.barcode}
									</td>
									<td>
										${pageProcard.markId}
									</td>
									<td>
										${pageProcard.proName}
									</td>
									<td>
										${pageProcard.selfCard}
									</td>
									<td>
										${pageProcard.count}
									</td>
									<td>
										${pageProcard.procardTime}
									</td>
									<td>
										${pageProcard.zhikaren}
									</td>
									<td>
										${pageProcard.carStyle}
									</td>

									<td>
										<a href="">修改</a>
									</td>
									</tr>
								</s:iterator>
								<tr>
									<td colspan="10" align="right">
										共
										<s:property value="total" />
										页 第
										<s:property value="cpage" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />

									</td>
								</tr>
							</s:if>
							<s:else>
								<tr>
									<td style="font-size: 15px; color: red;">
										对不起，没有查到相关的卡片信息
									</td>
								</tr>
							</s:else>
						</table>

						</div>
						<br>
						</div>
						<%@include file="/util/foot.jsp"%>
						</center>
						<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
