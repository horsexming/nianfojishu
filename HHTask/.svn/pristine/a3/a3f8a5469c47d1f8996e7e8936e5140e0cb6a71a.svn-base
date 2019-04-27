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
		<script type="text/javascript">
</script>
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
							<span id="title">项目核算</span>
						</td>
						<td align="right">
							<img id="closeimg" alt=""
								src="<%=basePath%>/images/closeImage.png" width="30" height="32"
								onclick="chageDiv('none');">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="addProIf" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 100%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<form action="QuotedPrice_findQpForCost.action" method="post">
					<input name="pageStatus" value="${pageStatus}" type="hidden">
					<table class="table">
						<tr>
							<th colspan="6">
								<h2>
									项目费用填报管理
								</h2>
							</th>
						</tr>
						<tr>
							<th align="right">
								项目:
							</th>
							<th align="left">
								<select name="quotedPrice.proId" id="proId"
									style="width: 155px;">

								</select>
							</th>
							<th align="right">
								询价单号:
							</th>
							<th align="left">
								<input name="quotedPrice.quotedNumber"
									value="${quotedPrice.quotedNumber}" />
							</th>
						</tr>
						<tr>
							<th align="right">
								件号:
							</th>
							<th align="left">
								<input name="quotedPrice.markId" value="${quotedPrice.markId}" />
							</th>
							<th align="right">
								名称:
							</th>
							<th align="left">
								<input name="quotedPrice.proName" value="${quotedPrice.proName}" />
							</th>
						</tr>
						<tr>
							<th align="right">
								放标状态:
							</th>
							<th align="left">
								<SELECT name="quotedPrice.fbSatuts">
									<option>${quotedPrice.fbSatuts}</option>
									<option></option>
									<option>未放标</option>
									<option>放标</option>
									<option>关闭</option>
								</SELECT>
							</th>
							<th align="right">
							</th>
							<th align="left">
							</th>
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" value="查询" class="input" />
								<input type="reset" value="重置" class="input" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div align="center">
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							询价单号
						</th>
						<th align="center">
							产品生命周期
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							名称
						</th>
						<th align="center">
							类型
						</th>
						<th align="center">
							数量
						</th>
						<th align="center">
							流程状态
						</th>
						<th align="center">
							放标状态
						</th>
						<th align="center">
							参与投资
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="quotedPriceList" id="pageQpri"
						status="pageStatus">
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
							${pageQpri.quotedNumber}
						</td>
						<td align="center">
							${pageQpri.procardLifeCycle}年
						</td>
						<td align="center">
							${pageQpri.markId}
						</td>
						<td align="center">
							${pageQpri.proName}
						</td>
						<td align="center">
							${pageQpri.procardStyle}
						</td>
						<td align="center">
							${pageQpri.filnalCount}
						</td>
						<td align="center">
							${pageQpri.status}
						</td>
						<td align="center">
							${pageQpri.fbSatuts}
						</td>
						<td align="center">
							<s:if test="#pageQpri.hasTz=='yes'">
								<font color="red">是</font>
							</s:if>
							<s:else>
								否
							</s:else>
						</td>
						<td align="center">
							<s:if test="pageStatus==null||pageStatus==''">
							<a	target="forcost"
									href="QuotedPrice_findQpDetailForCost.action?id=${pageQpri.rootId}">费用申报</a>
									<s:if test="#pageQpri.status=='核算完成'&&#pageQpri.procardStyle=='总成'">
							/<a href="QuotedPrice_ApplyQP.action?id=${pageQpri.rootId}&pageStatus=${pageStatus}&cpage=${cpage}">进入首件</a>
							</s:if>
							<s:elseif test="#pageQpri.status=='项目首件&&'#pageQpri.procardStyle=='总成'">
							/<a href="QuotedPrice_ApplyQP.action?id=${pageQpri.rootId}&pageStatus=${pageStatus}&cpage=${cpage}">进入试制</a>
							</s:elseif>
							<s:elseif test="#pageQpri.status=='项目试制&&'#pageQpri.procardStyle=='总成'">
							/<a href="QuotedPrice_ApplyQP.action?id=${pageQpri.rootId}&pageStatus=${pageStatus}&cpage=${cpage}">进入批产</a>
							</s:elseif>
							<s:elseif test="#pageQpri.status=='首件申请中'||#pageQpri.status=='试制申请中'||#pageQpri.status=='批产申请中'">
							/<a href="CircuitRunAction_findAduitPage.action?id=${pageQpri.epId}">审批动态</a>
							</s:elseif>
							</s:if>
							<s:elseif test="pageStatus=='setprotz'">
							<a	target="forcost"
									href="QuotedPrice_tosetProTz.action?pageStatus=${pageStatus}&id=${pageQpri.rootId}">项目投资设置</a>
							<a	target="forcost"
									href="QuotedPrice_showRealCost.action?pageStatus=${pageStatus}&id=${pageQpri.rootId}">实际成本</a>
							</s:elseif>
							<s:else>
							<a	target="forcost"
									href="QuotedPrice_findQpDetailForCost.action?pageStatus=${pageStatus}&id=${pageQpri.rootId}">项目投资</a>
							</s:else>
							<s:if test="#pageQpri.hasTz=='yes'">
							<a	target="forcost"
									href="QuotedPrice_findQpPartner.action?pageStatus=${pageStatus}&id=${pageQpri.rootId}">合伙人</a>
							<a	target="forcost"
									href="QuotedPrice_tocbzheixan.action?pageStatus=${pageStatus}&id=${pageQpri.rootId}">成本动态</a>
							</s:if>
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
								${errorMessage}
						</s:else>
						</td>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function() {
	$.ajax( {
		type : "POST",
		url : "ProjectManage_findAllProMan.action",
		dataType : "json",
		success : function(msg) {
			$("#proId").append("<option value=''></option>");
			$.each(msg, function(i, n) {
				$("#proId").append(
						"<option value='" + n.id + "'>" + n.projectNum + "("
								+ n.projectName + ")</option>");
			});
		}
	});
})
</script>
	</body>
</html>
