
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
				<center>
					<table style="width: 100%">
						<tr>
							<td>

							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none')">
							</td>
						</tr>
					</table>
				</center>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>

		<%@include file="/util/sonTop.jsp"%>



		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="zhaobiaoAction!listZhmoban.action" style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center" id="d1">
				<form action="FailureStAction!listFailureStatistics.action"
					method="post" theme="simple" id="myform">
					<table class="table">
						<tr>
							<th align="center">
								日期：
							</th>
							<td>
								<input class="Wdate" type="text" id="failureStatistics.dateTime"
									name="failureStatistics.dateTime"
									onClick="WdatePicker({dateFmt:'yyyy年MM月dd日',skin:'whyGreen'})" />
							</td>
							<th align="center">
								零件号：
							</th>
							<td>
								<input type="text" id="failureStatistics.markId"
									name="failureStatistics.markId" />
							</td>
							<td rowspan="3">
								<input type="submit" value="查询" class="input" />
								<input type="button" value="导出" onclick="exprot()" class="input" />
						</tr>
						<tr>
							<th align="center">
								添加时间：
							</th>
							<td>

								<input class="Wdate" type="text" id="failureStatistics.addTime"
									name="failureStatistics.addTime"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="center">

							</th>
							<td>

							</td>

						</tr>
					</table>
				</form>

				<table class="table">
					<tr bgcolor="#c0dcf2">
						<th>
							序号
						</th>
						<th>
							日期
						</th>
						<th>
							零件号
						</th>
						<th>
							提交数量
						</th>
						<th>
							不合格数量
						</th>
						<%--<th>
							焊接缺陷	
						</th>
						<th>
							走向/尺寸	
						</th>
						<th>
							法兰平面度	
						</th>
						<th>
							筒体内异物	
						</th>
						<th>
							气密	
						</th>
						<th>
							外观
						</th>
						<th>
							其它
						</th>
						--%>
						<th>
							目标值PPM
						</th>
						<th>
							周
						</th>
						<th>
							添加时间
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="list" id="zhaobiao1" status="pageIndex">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<th>
							${pageIndex.index+1}
						</th>
						<th>
							${zhaobiao1.dateTime}
						</th>

						<th>
							${zhaobiao1.markId}
						</th>
						<th>
							${zhaobiao1.submitCount}
						</th>

						<th>
							${zhaobiao1.failureCount}
						</th>
						<%--

						<th>
							${zhaobiao1.weldingDefects}
						</th>
						<th>
							${zhaobiao1.strikeSize}
						</th>

						<th>
							${zhaobiao1.flangeFlatness}
						</th>
						<th>
							${zhaobiao1.tfb}
						</th>
						<th>
							${zhaobiao1.airtight}
						</th>
						<th>
							${zhaobiao1.exterior}
						</th>
						<th>
							${zhaobiao1.other}
						</th>
						--%>
						<th>
							${zhaobiao1.targetPPM}
						</th>
						<th>
							${zhaobiao1.weekds}
						</th>
						<th>
							${zhaobiao1.addTime}
						</th>
						<th>
							<a onclick="toUpdatefailureStatistics(${zhaobiao1.id})">修改</a>
							<a onclick="return window.confirm('确定删除?')"
								href="FailureStAction!deletefailureStatistics.action?failureStatistics.id=${zhaobiao1.id}">删除</a>
							<s:iterator value="#zhaobiao1.fsdSet" id="pagefsd"
								status="pageIndex2">
								<table class="table">
									<tr>
										<td>
											${pageIndex2.index+1}
										</td>
										<td>
											${pagefsd.code}
										</td>
										<td>
											${pagefsd.type}
										</td>
										<td>
											${pagefsd.badNumber}
										</td>
									</tr>
								</table>
							</s:iterator>
						</th>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<th colspan="17" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<th colspan="15" align="center" style="color: red">
						</s:else>
						</th>
					</tr>

				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

function add() {
	var url = encodeURI(encodeURI("${pageContext.request.contextPath}/System/caigou/zhaobiao/addzhaobiao.jsp"));
	$("#showProcess").attr("src", url);
	chageDiv('block');
}

function toUpdatefailureStatistics(id) {
	var url = encodeURI(encodeURI("FailureStAction!toUpdatefailureStatistics.action?failureStatistics.id="
			+ id));
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
function exprot(){
	//FailureStAction!listFailureStatistics.action
	$("#myform").attr('action','FailureStAction!exprot.action');
	$("#myform").submit();
	$("#myform").attr('action','FailureStAction!listFailureStatistics.action');
}
</script>
	</body>
</html>
