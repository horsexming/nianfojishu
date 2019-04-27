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
				门禁设备查询
				</h3>
				<form action="AccessEquipmentAction_showList.action?tag=${tag}" method="post">
					<table class="table">
						<tr style="width: 100%">
							<th align="center" style="width: 25%">
								请输入门禁名称：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="accessEquipment.equipmentName"
									/>
							</td>
							<th align="center" style="width: 25%">
								请输入门禁IP：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="accessEquipment.equipmentIP"
									/>
							</td>
						</tr>
						<tr style="width: 100%">
							<th align="center" style="width: 25%">
								请输入门禁类型：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="accessEquipment.equipmentDaoType"
									/>
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
							设备名称
						</td>
						<td align="center">
							设备编号
						</td>
						<td align="center">
							设备供应商
						</td>
						<td align="center">
							设备用途（进/出）
						</td>
						<td align="center">
							设备IP
						</td>
						<td align="center">
							设备端口
						</td>
						<td align="center">
							设备摆放位置
						</td>
						<td align="center">
							设备类型
						</td>
						<s:if test="tag=='shui'">
							<td align="center">
								合计用量
							</td>
						</s:if>
						<td align="center" colspan="2">
							操作类型
						</td>
					</tr>
					<s:iterator value="accessEquipmentList" id="samples"
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
							${samples.equipmentName}
						</td>
						<td align="center">
							${samples.equipmentNum}
						</td>
						<td align="center">
							${samples.equipmentSupplier}
						</td>
						<td align="center">
							${samples.equipmentOutIn} 
							${samples.state}
						</td>
						<td align="center">
							${samples.equipmentIP}
						</td>
						<td align="center">
							${samples.equipmentPort}
						</td>
						<td align="center">
							${samples.equipmentLocation}
						</td>
						<td align="center">
							${samples.equipmentDaoType}
						</td>
						<s:if test="tag=='shui'">
							<td align="center">
								${samples.operationNote}
							</td>
						</s:if>
						<td align="center" colspan="2">
							<s:if test='tag=="admin"'>
								<a href="AccessEquipmentAction_showList_Web.action?accessEquipment.id=${samples.id}&tag=${tag}">查看对应摄像头</a>
								<a
									href="AccessEquipmentAction_findMfById.action?accessEquipment.id=${samples.id}&tag=${tag}">/查看绑定人员</a>
								<a
									href="AccessEquipmentAction_findOneLightById.action?accessEquipment.id=${samples.id}&tag=${tag}">/查看绑定灯号</a>
								<a style="text-decoration: none;"
									onclick="updateadmin('${samples.id}')">/管理卡</a>
								<a
									href="AccessEquipmentAction_toupdate.action?accessEquipment.id=${samples.id}&cpage=${cpage}&tag=${tag}">/修改</a>
								<a onclick="return window.confirm('您将删除数据，是否继续?')"
									href="AccessEquipmentAction_delete.action?accessEquipment.id=${samples.id}&cpage=${cpage}&tag=${tag}">/删除</a>
								<a
									href="AccessEquipmentAction_findallAceTime.action?accessEquipment.id=${samples.id}&tag=${tag}">/添加时间</a>
								<br>
								<s:if test="#samples.equipmentDaoType=='办公室'.toString()">
									<input type="button"
										onclick="shengyan('${samples.id}','${cpage}','${tag}')"
										value="生成验证码" />
									<s:if test="#samples.linYanZ!=null">
										 临时验证码为：<font color="red">${samples.linYanZ}</font>
									</s:if>
								</s:if>
							</s:if>
							<s:elseif test="tag=='aD'">
								<a
									href="AccessEquipmentAction_findMfById.action?accessEquipment.id=${samples.id}&tag=${tag}">查看绑定人员</a>
								<%--<a href="AccessEquipmentAction_addAceYanZ.action?accessEquipment.id=${samples.id}&cpage=${cpage}&tag=${tag}">生成验证码</a> --%>
								<s:if test="#samples.equipmentDaoType=='办公室'.toString()">
									<input type="button"
										onclick="shengyan('${samples.id}','${cpage}','${tag}')"
										value="生成验证码" />
									<s:if test="#samples.linYanZ!=null">
										 临时验证码为：<font color="red">${samples.linYanZ}</font>
									</s:if>
								</s:if>
							</s:elseif>
							<s:elseif test="tag=='all'">
								<a
									href="AccessEquipmentAction_toupdate.action?accessEquipment.id=${samples.id}&cpage=${cpage}">修改</a>
								<a
									href="AccessEquipmentAction_findMfById.action?accessEquipment.id=${samples.id}&tag=${tag}"> /查看绑定人员</a>
								<s:if test="#samples.equipmentDaoType=='办公室'.toString()">
									<input type="button"
										onclick="shengyan('${samples.id}','${cpage}','${tag}')"
										value="生成验证码" />
									<s:if test="#samples.linYanZ!=null">
										 临时验证码为：<font color="red">${samples.linYanZ}</font>
									</s:if>
								</s:if>
							</s:elseif>
							<s:elseif test="tag=='deng'">
								<a
									href="AccessEquipmentAction_closeDeng.action?accessEquipment.id=${samples.id}&cpage=${cpage}"
									style="border-radius: 20%; background-color: red; color: #ffffff; font-size: 25px;">关闭</a>
<%--								<a--%>
<%--									href="AccessEquipmentAction_findMfById.action?accessEquipment.id=${samples.id}&tag=${tag}"> /查看绑定人员</a>--%>
							</s:elseif>
							<s:elseif test="tag=='shui'">
								<a
									href="AccessRecordsAction_showList_log.action?accessLogInfor.aceId=${samples.id}&tag=${tag}"
									>查看明细</a>
								<a
								href="AccessEquipmentAction_closeShuiFa.action?id1=${samples.id}&cpage=${cpage}&tag=${tag}&tage=open"
								>打开/</a> 
								<a
								href="AccessEquipmentAction_closeShuiFa.action?id1=${samples.id}&cpage=${cpage}&tag=${tag}"
								>关闭 </a> 
							</s:elseif>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="12" align="center" style="color: red">
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
function shengyan(id,page,tag){
	window.location.href = "AccessEquipmentAction_addAceYanZ.action?accessEquipment.id="+id+"&cpage="+page+"&tag="+tag;
	}
</script>
	</body>
</html>
