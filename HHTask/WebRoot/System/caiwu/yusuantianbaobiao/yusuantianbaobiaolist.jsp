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
	<body>
		<center>
			<form
				action="YusuantianbaobiaoAction!addBiaoDan.action?id=${yusuantianbaototal.id}"
				method="post" onsubmit="return check()">
				<table class="table">
					<tr>
						<th colspan="4" align="center">
							添加
							<span style="color: red">${yusuantianbaototal.xiangmumingda}</span>
							预算明细
							<br />
							本年度已通过审批总计
							<span style="color: red">${yusuantianbaototal.zongshu}</span>元，
							剩余
							<span style="color: red">${yusuantianbaototal.shengyu}</span>元。
						</th>
					</tr>
					<tr>
						<th align="right">
							项目名称或者代码：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.xiangmumingda"
								id="xiangmumingda" />
						</td>
						<th align="right">
							固定资产：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.gudingzichan"
								id="gudingzichan" />
						</td>
					</tr>
					<tr>
						<th align="right">
							人力成本：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.renlichengbenzengjia"
								id="renlichengbenzengjia" />
						</td>
						<th align="right">
							试验费用：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.shiyanfeiyong"
								id="shiyanfeiyong" />
						</td>
					</tr>
					<tr>
						<th align="right">
							其他费用：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.qitafeiyong"
								id="qitafeiyong" />
						</td>
						<th align="right">
							收益金额：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.shouyijine"
								id="shouyijine" />
						</td>
					</tr>
					<tr>
						<th align="right">
							收益年限：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.shouyinianxian"
								id="shouyinianxian" />
						</td>
						<th align="right">
							预算类型：
						</th>
						<td>
							<select name="yusuantianbaobiao.yusuanleixing" id="yusuanleixing">
								<option value="开发项目">
									开发项目
								</option>
								<option value="kvp项目">
									kvp项目
								</option>
							</select>
							<input type="hidden" name="yusuantianbaobiao.niandu"
								value="${yusuantianbaototal.niandu}" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<th align="right">
								请选择币种：
						</th>
						<td align="left" style="border-right-width: 0px;">
							<select name="yusuantianbaobiao.currency">
								<option value="RMB">
									人民币
								</option>
								<option value="USD">
									美元
								</option>
								<option value="EUR">
									欧元
								</option>
								<option value="HKD">
									港币
								</option>
								<option value="GBR">
									英镑
								</option>
								<option value="JPY">
									日元
								</option>
								<option value="CHF">
									瑞士法郎
								</option>
								<option value="AUD">
									澳元
								</option>
								<option value="CAD">
									加元
								</option>
								<option value="SGD">
									新加坡元
								</option>
								<option value="SEK">
									瑞典克朗
								</option>
								<option value="DKK">
									丹麦克朗
								</option>
								<option value="NOK">
									挪威克朗
								</option>
								<option value="THB">
									泰国铢
								</option>
								<option value="NZD">
									新西兰元
								</option>
								<option value="KRW">
									韩国元
								</option>
							</select>
						</td>
						<td colspan="2" style="border-left-width: 0px;">
							<input type="submit" value="确定" />
							<input type="reset" value="重置 " />
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							注：预算总金额为 固定资产+人力资源+试验费用+其他费用 没有请填写0

						</td>
					</tr>
				</table>
			</form>

			<form action="YusuantianbaobiaoAction!getAllList.action"
				method="post">
				<table class="table">
					<tr>
						<td align="right">
							年度：
						</td>
						<td align="left">
							<input type="text" name="allow" class="Wdate"
								onClick="WdatePicker({dateFmt:'yyyy',skin:'whyGreen'})" />
						</td>
						<td>
							<input type="submit" value="查询" />
						</td>
					</tr>

				</table>
			</form>
			<table class="table">
				<tr>
					<td align="center" colspan="12">
						<span style="color: red">${yusuantianbaototal.bumen} </span> 预算明细
					</td>
				</tr>
				<tr>
					<th>
						序号
					</th>
					<th>
						项目名称或者代码
					</th>
					<th>
						预算总金额
					</th>
					<th>
						剩余金额
					</th>
					<th>
						收益金额
					</th>
					<th>
						币种
					</th>
					<th>
						收益年限
					</th>
					<th>
						年度
					</th>
					<th>
						部门
					</th>
					<th>
						报告人
					</th>
					<th>
						状态
					</th>
					<th>
						操作
					</th>
				</tr>
				<s:iterator id="pageyusuantianbaobiao" value="list"
					status="statussdf">
					<s:if test="#statussdf.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'')">
					</s:else>
					<td>
						<s:property value="#statussdf.index+1" />
					</td>
					<td>
						${pageyusuantianbaobiao.xiangmumingda}
					</td>
					<td>
						${pageyusuantianbaobiao.zongjine}
					</td>
					<td>
						${pageyusuantianbaobiao.shengyu}
					</td>
					<td>
						${pageyusuantianbaobiao.shouyijine}
					</td>
					<td>
						${pageyusuantianbaobiao.currency}
					</td>
					<td>
						${pageyusuantianbaobiao.shouyinianxian}
					</td>
					<td>
						${pageyusuantianbaobiao.niandu}
					</td>
					<td>
						${pageyusuantianbaobiao.bumen}
					</td>
					<td>
						${pageyusuantianbaobiao.name}
					</td>
					<td>
						${pageyusuantianbaobiao.shenpi}
					</td>

					<td>
						<s:if test="allow!='同意'">
							<a
								href="YusuantianbaobiaoAction!findYusuantianbaobiao.action?id=${pageyusuantianbaobiao.id}">详细</a>
							<a
								href="CircuitRunAction_findAduitPage.action?id=${pageyusuantianbaobiao.epid}">审批动态</a>
							<s:if test="#pageyusuantianbaobiao.shenpi!='同意'">
								<a
									href="YusuantianbaobiaoAction!findUpdate.action?id=${pageyusuantianbaobiao.id}">修改</a>
								<a
									href="YusuantianbaobiaoAction!deleteYusuantianbaobiao.action?id=${pageyusuantianbaobiao.id}
								&&yusuantianbaototal.id=${yusuantianbaototal.id}"
									onclick="return window.confirm('是否确认删除?')">删除</a>
							</s:if>
						</s:if>
						<s:else>
							<a
								href="YusuantianbaobiaoAction!shenPi.action?id=${pageyusuantianbaobiao.id}&key=0">审批</a>
							<a
								href="YusuantianbaobiaoAction!shenPi.action?id=${pageyusuantianbaobiao.id}&key=1">打回</a>
						</s:else>
					</td>


				</s:iterator>


				<tr>
					<td colspan="12" align="right">
						第
						<font color="red"><s:property value="cpage" /> </font> /
						<s:property value="total" />
						页
						<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
							styleClass="page" theme="number" />
					</td>
				</tr>

			</table>
		</center>
		<script type="text/javascript">
function check() {
	var testValue1 = document.getElementById("xiangmumingda").value;
	if (testValue1 == "") {
		alert("请填写项目名称或者代码!");
		return false;
	}
	var testValue2 = document.getElementById("gudingzichan").value;
	if (testValue2 == "") {
		alert("请填写固定资产!");
		return false;
	}
	var testValue3 = document.getElementById("renlichengbenzengjia").value;
	if (testValue3 == "") {
		alert("请填写人力成本增加!");
		return false;
	}
	var testValue4 = document.getElementById("shiyanfeiyong").value;
	if (testValue4 == "") {
		alert("请填写试验费用!");
		return false;
	}
	var testValue5 = document.getElementById("qitafeiyong").value;
	if (testValue5 == "") {
		alert("请填写其他费用!");
		return false;
	}
	var testValue6 = document.getElementById("shouyijine").value;
	if (testValue6 == "") {
		alert("请填写收益金额!");
		return false;
	}
	var testValue7 = document.getElementById("shouyinianxian").value;
	if (testValue7 == "") {
		alert("请填写收益年限!");
		return false;
	}
	var testValue8 = document.getElementById("yusuanleixing").value;
	if (testValue8 == "") {
		alert("预算类型!");
		return false;
	}
	return true;
}
</script>
	</body>
</html>