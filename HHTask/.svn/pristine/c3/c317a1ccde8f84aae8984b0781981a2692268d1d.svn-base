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
			<div align="center">
				<form action="PanelSizeAction_findAllpanelSizeList.action"
					method="post">
					<table class="table">
						<tr>
							<th>
								材质
							</th>
							<td>
								<input type="text" value="${panelSize.caizhi}"
									name="panelSize.caizhi" />
							</td>
							<th>
								厚度
							</th>
							<td>
								<input type="text" value="${houdu}" name="houdu" />
							</td>
						</tr>
					</table>
					<input type="hidden" value="${status}" name="status"/>
					<input type="submit" value="查询" class="input" />
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							材质
						</th>
						<s:if test='status == "fenmo"'>
							<th>
								粉末1材质
							</th>
							<th>
								粉末2材质
							</th>
							<th>
								粉末1需喷面数
							</th>
							<th>
								粉末2需喷面数
							</th>
							<th>
								粉末1每公斤喷粉面积(㎡/kg)
							</th>
							<th>
								粉末2每公斤喷粉面积(㎡/kg)
							</th>
						</s:if>
						<s:else>
							<th>
								厚度从(mm)
							</th>
							<th>
								到(mm)
							</th>
							<th>
								长度(mm)
							</th>
							<th>
								宽度(mm)
							</th>
							<th>
								尺寸(mm)
							</th>
							<th>
								密度(kg/m³)
							</th>
						</s:else>
						<th>
							备注
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="panelSizeList" id="pageList" status="statussdf">
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
							${pageList.caizhi}
						</td>
						<s:if test='status == "fenmo"'>
							<td>
								${pageList.fenmo1}
							</td>
							<td>
								${pageList.fenmo2}
							</td>
							<td>
								${pageList.miancount1}
							</td>
							<td>
								${pageList.miancount2}
							</td>
							<td>
								${pageList.areakg1}
							</td>
							<td>
								${pageList.areakg2}
							</td>
						</s:if>
						<s:else>
							<td>
								${pageList.fristThickness}
							</td>
							<td>
								${pageList.endThickness}
							</td>
							<td>
								${pageList.thislength}
							</td>
							<td>
								${pageList.thiswideth}
							</td>
							<td>
								${pageList.size}
							</td>
							<td>
								${pageList.density}
							</td>
						</s:else>
						<td>
							${pageList.remarks}
						</td>
						<td>
							<a
								href="PanelSizeAction_findPanelSizeById.action?id=${pageList.id}">修改</a>/
							<a
								href="PanelSizeAction_delPanelSize.action?panelSize.id=${pageList.id}"
								onclick="return confirm('确定要删除吗?')">删除</a>
						</td>
						</tr>
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
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
