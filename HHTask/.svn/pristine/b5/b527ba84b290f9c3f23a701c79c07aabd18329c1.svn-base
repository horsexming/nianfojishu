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
							<span id="title">新增报表项</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<form action="BankAccountsAction!addCwbb.action" method="post">
						<table class="table">
							<tr>
								<th colspan="4">
									报表名称:
									<input name="cwZhangWu.bankNC" />
								</th>
							</tr>
							<tr>
								<th>
									科目
								</th>
								<th>
									运算符号
								</th>
								<th>
									时间规则
								</th>
								<th>
									操作
								</th>
							</tr>
							<tr align="left" id="sbrTemplate">
								<th align="center">
									<select id="dept" style="width: 200px;"
										onMouseOver="sbrSelect(this)"
										name="cwZWAndSbrList[0].fk_sbrId">
										<option>
											选择科目
										</option>
									</select>
								</th>
								<th>
									<select name="cwZWAndSbrList[0].jiaOrJian">
										<option value="+">
											+
										</option>
										<option value="-">
											-
										</option>
									</select>
								</th>
								<th>
									<select name="cwZWAndSbrList[0].dateType">
										<option value="qichuMoney">
											期初
										</option>
										<option value="jieyuMoney">
											期末
										</option>
									</select>
								</th>
								<th>
									<input type="button" onclick="delThis(this)" value="删除" />
								</th>
							</tr>
							<tr align="left">
								<th align="center">
									<select id="dept" style="width: 200px;"
										onMouseOver="sbrSelect(this)"
										name="cwZWAndSbrList[1].fk_sbrId">
										<option>
											选择科目
										</option>
									</select>
								</th>
								<th>
									<select name="cwZWAndSbrList[1].jiaOrJian">
										<option value="+">
											+
										</option>
										<option value="-">
											-
										</option>
									</select>
								</th>
								<th>
									<select name="cwZWAndSbrList[1].dateType">
										<option value="qichuMoney">
											期初
										</option>
										<option value="jieyuMoney">
											期末
										</option>
									</select>
								</th>
								<th>
									<input type="button" onclick="delThis(this)" value="删除" />
								</th>
							</tr>
							<tr id="uploadtr">
								<th align="right" colspan="4">
									<input type="button" id="inforButton_1"
										onclick="saveHKInfor(this,1)" value="添加报表科目" />
									<input type="submit" value="保存" />
								</th>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">

				<table class="table">
					<tr>
						<th align="right" colspan="5">
							<a href="javascript:;" onclick="toaddPzIf()">(+新增报表项)</a>
						</th>
					</tr>
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							报表项
						</th>
						<th align="center">
							金额
						</th>
						<th align="center">
							添加人
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="list" id="pageCwZhangWu" status="pageStatus">
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
						<td>
							${pageCwZhangWu.bankNC}
						</td>
						<td>
							${pageCwZhangWu.money}
						</td>
						<td>
							${pageCwZhangWu.addUsers}
						</td>
						<td>
							<a href="${pagecwPingZheng.detailUrl}" target="_showPzGl">关联明细</a>/
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
						</s:else>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function toaddPzIf(id) {
	$("#toaddPzIf").attr("src",
			"BankAccountsAction!findPzToAdd.action?id=" + id);
	chageDiv('block');

}

var lineCount = 1;
var begAddLineNum = 2;

//删除明细
function delThis(obj) {
	$(obj).parent().parent().remove();
}

//添加明细
function saveHKInfor(obj, few) {

	$("#uploadtr").before($("#sbrTemplate").clone());

}
function sbrSelect(obj) {
	var selected = obj.id;//获取到当前列的id
	$.ajax( {
		type : "POST",
		url : "SubjectBudgetAction!findAllSBRate.action",
		data : {},
		dataType : "json",
		success : function(data) {
			$.each(data, function(i, n) {
				$(obj)
						.append(
								"<option value='" + n.id + "' >" + n.name
										+ "</option>");
			})
		}
	});

}
</script>
	</body>
</html>
