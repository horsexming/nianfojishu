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
					卷帘门申请查询
				</h3>
				<form
					action="JLMApplicationAction_showApplication.action?tag=${tag}"
					method="post">
					<table class="table">
						<tr style="width: 100%">
							<th align="center" style="width: 25%">
								请输入门申请人姓名：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="jlmApplication.proposer_name" />
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
							申请人
						</td>
						<td align="center">
							部门
						</td>
						<td align="center">
							申请原因
						</td>
						<td align="center">
							申请时间
						</td>
						<td align="center">
							门名称
						</td>
						<td align="center">
							申请状态
						</td>
						<td align="center" colspan="2">
							操作类型
						</td>
					</tr>
					<s:iterator value="jlmApplicationList" id="samples"
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
							${samples.proposer_name}
						</td>
						<td align="center">
							${samples.proposer_dept}
						</td>
						<td align="center">
							${samples.reason}
						</td>
						<td align="center">
							${samples.addTime}
						</td>
						<td align="center">
							${samples.doorName}
						</td>
						<td align="center">
							${samples.status}
						</td>
						<td align="center" colspan="2">
							<s:if test="#samples.epId!=null">
								<a
									href="CircuitRunAction_findAduitPage.action?id=${samples.epId}">审批动态</a>/
							</s:if>
							<s:if test="tag=='admin'">
								<a
									href="JLMApplicationAction_showList_o.action?ta_jlm_operation=${samples.id}">操作</a>
							</s:if>
							<s:elseif test="tag=='aD'">
								<s:if test="#samples.status=='同意'">
									<a
										href="JLMApplicationAction_showList_o.action?ta_jlm_operation=${samples.id}">操作</a>
								</s:if>
							</s:elseif>
						</td>
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
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function update(id) {
	var url = "JLMApplicationAction_showApplication?tag=administrator&jlmApplication.id="
			+ id;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
function shengyan(id, page, tag) {
	window.location.href = "AccessEquipmentAction_addAceYanZ.action?accessEquipment.id="
			+ id + "&cpage=" + page + "&tag=" + tag;
}
function closeDoor(id,doorIp,doorPort) {
	$.ajax({
		url : "JLMApplicationAction_ColseDoorById.action",
		type : "POST",
		data : {
			id : id,
			doorIp :doorIp,
			doorPort : doorPort
		},
		dataType : "json",
		async : false,
		success : function(data) {
		if(data!=null){
				if(data.success){
					//getcheckList2();
					alert(data.message)
				}else{
					alert(data.message)
				}
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}
</script>
	</body>
</html>
