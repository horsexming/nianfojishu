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
		<div id="gongneng" style="width: 100%;">
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
								<span id="title">不合格品确认操作</span>
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
			
			<div align="center">
				<form action="SCTuiliaoSqDanAction_findAllSctuiliaoSqDan.action"
					method="post">
					<table class="table">
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								<input type="text" name="sqd.markId" value="${sqd.markId}" />
							</td>
							<th align="right">
								版本
							</th>
							<td>
								<input type="text" name="sqd.banBenNumber" value="${sqd.banBenNumber}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								生产批次
							</th>
							<td>
								<input type="text" name="sqd.selfCard" value="${sqd.selfCard}" />
							</td>
							<th align="right">
								追踪批次
							</th>
							<td>
								<input type="text" name="sqd.examineLot" value="${sqd.examineLot}" />
							</td>
						</tr>
						
					</table>
					<input type="hidden" value="${pageStatus}" name="pageStatus" />
					<input type="submit" value="查询" class="input" />
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							件号
						</th>
						<th>
							零件名称
						</th>
						<th>
							总成件号
						</th>
						<th>
							总成批次
						</th>
						<th>
							生产批次
						</th>
						<th>
							追踪批次
						</th>
						<th>
							物料类别
						</th>
						<th>
							供料属性
						</th>
						<th>
							规格
						</th>
						<th>
							图号
						</th>
						<th>
							供应商
						</th>
						<th>
							领料数量 
						</th>
						<th>
							退料数量
						</th>
						<th>
							申请时间
						</th>
						<th>
							申请人
						</th>
						<th>
							审批动态
						</th>
						<td>
							操作
						</td>
					</tr>
					<s:iterator value="sqdList" id="pageList" status="statussdf">
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
							${pageList.markId}
						</td>
						<td>
							${pageList.proName}
						</td>
						<td>
							${pageList.rootmarkId}
						</td>
						<td>
							${pageList.rootLotId}
						</td>
						<td>
							${pageList.selfCard}
						</td>
						<td>
							${pageList.examineLot}
						</td>
						<td>
							${pageList.wgType}
						</td>
						<td>
							${pageList.kgliao}
						</td>
						<td>
							${pageList.specification}
						</td>
						<td>
							${pageList.tuhao}
						</td>
						<td>
							${pageList.gys}
						</td>
						<td>
							${pageList.llNumber}
						</td>
						<td>
							${pageList.tlNumber}
						</td>
						<td>
							${pageList.addTime}
						</td>
						<td>
							${pageList.adduser}
						</td>
						<td>
							<a href="CircuitRunAction_findAduitPage.action?id=${pageList.epId}">审批动态</a>
						</td>
						<td>
							<a href="SCTuiliaoSqDanAction_findSctuiliaoSqDanById.action?id=${pageList.id}">明细</a>
							<s:if test='#pageList.epStatus == "未审批" || #pageList.epStatus == "打回"'>
								/<a href="SCTuiliaoSqDanAction_findSctuiliaoSqDanById.action?id=${pageList.id}&pageStatus=xg">修改</a>
								/<a href="SCTuiliaoSqDanAction_delSctuiliaoSqDan.action?id=${pageList.id}">删除</a>
							</s:if>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="18" align="right">
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
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
	function tanchu(num){
		document.getElementById("xiugaiIframe").src = "WaigouwaiweiPlanAction!tobhgth.action?id="+ num;
		chageDiv('block')
	}
</SCRIPT>
	</body>
</html>
