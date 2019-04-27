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
		<div id="bodyDiv" align="center" class="transDiv" style="z-index: 2"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none; top: 20px;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%" id="carzuo">
					<tr>
						<td>
							<span id="title">您正在进行绑定管理员卡号操作:</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none');reload();">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 900px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
					印章查询
				</h3>
				<form action="SealLogAction_showSealLogTypeList.action"
					method="post">
					<table class="table">
						<tr style="width: 100%">
							<th align="center" style="width: 25%">
								请输入印章名称：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="sealLogType.slname" />
							</td>
							<td align="center" colspan="2" style="width: 50%">
								<input type="submit" value="查询"
									style="width: 100px; height: 25px;" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							印章名称
						</td>
						<td align="center">
							存放位置
						</td>
						<td align="center">
							存放位置编号
						</td>
						<td align="center">
							存档室门禁ID
						</td>
						<td align="center">
							柜子编号
						</td>
						<td align="center">
							柜子编号(开门指令)
						</td>
						<td align="center">
							柜子IP
						</td>
						<td align="center">
							柜子ID
						</td>
						<td align="center">
							印章保管人部门
						</td>
						<td align="center">
							印章保管人姓名
						</td>
						<td align="center">
							印章保管人卡号
						</td>
						<td align="center">
							印章保管人电话
						</td>
						<td align="center">
							印章保管人工号
						</td>
						<td align="center">
							印章在柜状态
						</td>
						<td align="center" colspan="2">
							操作类型
						</td>
					</tr>
					<s:iterator value="sealLogTypeList" id="samples"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${samples.slname}
						</td>
						<td align="center">
							${samples.cunFangWeizhi}
						</td>
						<td align="center">
							${samples.cunFangNum}
						</td>
						<td align="center">
							${samples.cunShiId}
						</td>
						<td align="center">
							${samples.guiHao}
						</td>
						<td align="center">
							${samples.guiHaoNum}
						</td>
						<td align="center">
							${samples.guiIp}
						</td>
						<td align="center">
							${samples.guiId}
						</td>
						<td align="center">
							${samples.bgDept}
						</td>
						<td align="center">
							${samples.bgName}
						</td>
						<td align="center">
							${samples.bgCardId}
						</td>
						<td align="center">
							${samples.bgTel}
						</td>
						<td align="center">
							${samples.bgCode}
						</td>
						<td align="center">
							${samples.yzZGStatus}
						</td>
						<td align="center" colspan="2">
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="17" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="17" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function updateadmin(id) {
	var url = "AccessEquipmentAction_toupdate.action?tag=administrator&accessEquipment.id="
			+ id;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
function shengyan(id, page, tag) {
	window.location.href = "AccessEquipmentAction_addAceYanZ.action?accessEquipment.id="
			+ id + "&cpage=" + page + "&tag=" + tag;
}
</script>
	</body>
</html>
