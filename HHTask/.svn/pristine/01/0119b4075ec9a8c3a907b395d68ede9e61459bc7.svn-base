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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">二维码查看操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<%--<input type="button" value="添加摄像头" onclick="add()"/>
				<a href="AccessEquipmentAction_toadd_Web.action?">添加摄像头"</a>
			--%>
			</div>
			<div align="center">
				<h3>
					档案柜查询
				</h3>
				<form action="AccessEquipmentAction_showList_Dag.action?tag=${tag}" method="post">
					<table class="table">
						<tr style="width: 100%">
							<th align="center" style="width: 25%">
								档案柜类型：
							</th>
							<td align="center" style="width: 25%">
								<SELECT name="accessWebcam.type" id="type" style="width: 153px;">
									<option value=""></option>
									<option value="dag">档案柜</option>
									<option value="yz">印章柜</option>
									<option value="pz">凭证柜</option>
									<option value="fp">发票柜</option>
								</SELECT>
							</td>
							<th align="center" style="width: 25%">
								档案柜编号：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="accessWebcam.cabinetNum"
									/>
							</td>
						</tr>
						<tr style="width: 100%">
							<td align="center" colspan="4" style="width: 50%">
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
							档案柜编号
						</td>
						<td align="center">
							档案柜类型
						</td>
						<td align="center">
							档案柜用途
						</td>
						<td align="center">
							档案柜开门指令
						</td>
						<td align="center">
							档案柜IP
						</td>
						<td align="center">
							档案柜最大数
						</td>
						<td align="center">
							档案柜已经存
						</td>
						<td align="center">
							已发送验证码
						</td>
						<td align="center" colspan="2">
							操作类型
						</td>
					</tr>
					<s:iterator value="webcamFGuiAllList" id="samples"
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
							${samples.cabinetNum}
						</td>
						<td align="center">
							${samples.type}
						</td>
						<td align="center">
							${samples.cabinetType}
						</td>
						<td align="center">
							${samples.cabinetOpenInstruction}
						</td>
						<td align="center">
							${samples.cabinetAccessSim}
						</td>
						<td align="center">
							${samples.maxNum}
						</td>
						<td align="center">
							${samples.actualNum}
						</td>
						<td align="center">
							${samples.linshi}
						</td>
						<td align="center" colspan="2">
							<a
								href="AccessEquipmentAction_toupdate_Web.action?accessWebcam.id=${samples.id}&tag=dag&cpage=${cpage}">修改</a>/
							<a href="javascript:;"
								onclick="tanchu_1('<%=basePath%>/SuspsomAction_dangan.action?id=${samples.id}','${samples.cabinetNum}')">查看二维码</a>/
							<a
								href="AccessEquipmentAction_findPriceById.action?accessWebcam.id=${samples.id}&accessWebcam.maxNum=${samples.maxNum}">绑定档案</a>/

							<a onclick="return window.confirm('您将删除数据，是否继续?')"
								href="AccessEquipmentAction_delete_web.action?accessWebcam.id=${samples.id}&tag=${samples.type}&cpage=${cpage}"">删除</a>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="10" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="10" align="center" style="color: red">
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
function tanchu_1(number,obj){
	document.getElementById("xiugaiIframe").src="<%=basePath%>System/menjin/dangan_infor.jsp?number="+number+"&ku="+obj;
	chageDiv('block')
}
</script>
	</body>
</html>
