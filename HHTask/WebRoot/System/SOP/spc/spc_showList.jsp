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
	<title></title>
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
								<span id="title">修改群组数大小</span>
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
				<form action="SpcControlAction_findAllSpcControlList.action" method="post" >
					<table class="table">
						<tr>
							<th align="right">项目名称</th>
							<td>
								<input type="text" name="spcControl.proName" value="${spcControl.proName}"/>
							</td>
							<th align="right">物料名称</th>
							<td>
								<input type="text" name="spcControl.wlName" value="${spcControl.wlName}"/>
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input"/>
					<input type="button" value="添加" class="input" onclick="window.open('SpcControlAction_toadd.action?spcControl.shebeiNo=${spcControl.shebeiNo}')"/>
					<s:if test="spcgroupsList != null || spcgroupsList.size() != 0">
							<input type="button" value="修改群组数 " class="input" onclick="tanchu()"/>
					</s:if>
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							工位号
						</th>
						<th>
							设备编码
						</th>
						<th>
							项目名称
						</th>
						<th>
							物料名称
						</th>
						<th>
							测量项目
						</th>
						<th>
							物料编码
						</th>
						<th>
							名义值
						</th>
						<th>
							公差±
						</th>
						<th>
							上限
						</th>
						<th>
							下限
						</th>
						<th>
							测量仪器
						</th>
						<th>
							模穴号
						</th>
						<th>
							测量单位
						</th>
						<th>
							群组数大小
						</th>
						<th>
							状态
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="spcControlList" id="pagespc" status="statussdf">
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
							<td>${pagespc.gongwei}</td>
							<td>${pagespc.shebeiNo}</td>
							<td>${pagespc.proName}</td>
							<td>${pagespc.wlName}</td>
							<td>${pagespc.clcontent}</td>
							<td>${pagespc.markId}</td>
							<td>${pagespc.nominalValue}</td>
							<td>${pagespc.tolerance}</td>
							<td>${pagespc.ceilValue}</td>
							<td>${pagespc.floorValue}</td>
							<td>${pagespc.clinstrument}</td>
							<td>${pagespc.mxNo}</td>
							<td>${pagespc.clunit}</td>
							<td>${pagespc.groupsize}</td>
							<td>
								<s:if test="pagespc.status != '历史'">
									默认
								</s:if>
								<s:else>
									${pagespc.status}
								</s:else>
							</td>
							<td>
								<s:if test="#pagespc.status != '历史'">
									<a href="javascript:;" onclick="window.open('SpcControlAction_findSpcControlById.action?id=${pagespc.id}')">修改</a>/
								</s:if>
								<a href="javascript:;" onclick="window.open('SpcControlAction_findSpcControlById.action?id=${pagespc.id}&pageStatus=show')">图表</a>/
								<a href="SpcControlAction_delSPC.action?id=${pagespc.id}">删除</a>
							</td>
						</tr>
					</s:iterator>
					<tr>
								<td colspan="30" align="right">
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
function tanchu(){
	document.getElementById("xiugaiIframe").src = "SpcControlAction_findAllspcGroups.action";
	chageDiv('block')
}

</SCRIPT>
	</body>
</html>
