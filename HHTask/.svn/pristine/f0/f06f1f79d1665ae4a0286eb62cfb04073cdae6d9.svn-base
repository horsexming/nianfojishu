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

				<form action="BaoXiaoDanAction!findBaoXiaoDan.action?tag=${tag}"
					method="post">
					<table class="table">
						<tr>
							<th>
								收款单位(个人)
							</th>
							<th>
								<input type="text" name="baoxiao.shoukuanRen"
									value="${baoxiao.shoukuanRen}" />
							</th>
							<th>
								付款方式
							</th>
							<th>
								<select name="baoxiao.payStyle" style="width: 150px;">
									<option value="${baoxiao.payStyle}">
										${baoxiao.payStyle}
									</option>
									<option value="">
										无
									</option>
									<option value="现金">
										现金
									</option>
									<option value="银行对公转账">
										银行对公转账
									</option>
									<option value="归还借款">
										归还借款
									</option>
									<option value="其他">
										其他
									</option>
								</select>
							</th>
							<th>
								报销人
							</th>
							<th>
								<input type="text" name="baoxiao.baoxiaoren"
									value="${baoxiao.baoxiaoren}" />
							</th>

						</tr>
						<tr>
							<th>
								申报编号：
							</th>
							<th>
								<input type="text" name="baoxiao.baoxiaoBarcode"
									value="${baoxiao.baoxiaoBarcode}" />
							</th>
							<th>
								日期从：
							</th>
							<th>
								<input class="Wdate" type="text" name="startDate"
									value="${startDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />

							</th>
							<th>
								<span style="width: 21px;"></span>到
								<span style="width: 21px;"></span>：
							</th>
							<th>
								<input class="Wdate" type="text" name="endDate"
									value="${endDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</th>

						</tr>
						<tr>
							<th>
								部门
							</th>
							<th>
								<select style="width: 150px;" name="baoxiao.dept" id="dept"
									onclick="createDept('dept')">
									<option value="${baoxiao.dept}">
										${baoxiao.dept}
									</option>
									<option value="">
										选择部门
									</option>
								</select>
							</th>
							<th colspan="2">
								<input type="submit" style="width: 90px; height: 30px;"
									value="查询" />
							</th>
							<th colspan="2">
								<s:if test="%{tag=='manger'}">
									<input type="button" style="width: 90px; height: 30px;"
										value="数据导出" onclick="exportExcel(this.form);todisabledone(this)" data="downData"/>
								</s:if>
							</th>
						</tr>
						</form>
						<table class="table">
							<tr bgcolor="#c0dcf2" height="30px"
								style="border-collapse: separate;">
								<th align="center">
									序号
								</th>
								<th align="center">
									收款单位(个人)
								</th>
								<th align="center">
									报销人
								</th>
								<th align="center">
									部门
								</th>
								<th align="center">
									合计金额
								</th>
								<th align="center">
									币种
								</th>
								<th align="center">
									付款方式
								</th>
								<th align="center">
									报销日期
								</th>
								<th align="center">
									报销编号
								</th>

								<th align="center">
									操作
								</th>
							</tr>

							<s:if test="{list.size()>0}">
								<s:iterator value="list" status="se" id="bxd">
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
										${bxd.shoukuanRen}
									</td>
									<td>
										${bxd.baoxiaoren}
									</td>
									<td>
										${bxd.dept}
									</td>
									<td>
										${bxd.totalMoney}
									</td>
									<td>
										${bxd.currency}
									</td>
									<td>
										${bxd.payStyle}
									</td>
									<td>
										${bxd.baoxiaoDate}
									</td>
									<td>
										${bxd.baoxiaoBarcode}
									</td>

									<td>
										<a
											href="BaoXiaoDanAction!getBaoXiaoDan.action?id=${id}&tag=detail">查看</a>&nbsp;&nbsp;&nbsp;
										<a
											href="BaoXiaoDanAction!getBaoXiaoDan.action?id=${id}&tag=update">修改</a>&nbsp;&nbsp;&nbsp;
										<!-- 
								<a onClick="return confirm('确定要删除该条记录吗？')" href="BaoXiaoDanAction!deleteBaoXiaoDanById.action?id=${id}" >删除</a>
								 -->
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
									<td colspan="9" style="font-size: 15px; color: red;">
										对不起，没有查到相关的标识贴信息
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
