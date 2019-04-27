<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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

		<base href="<%=basePath%>">

		<title>不合格品类型管理</title>
		<%@include file="/util/sonHead.jsp"%>
		<style type="text/css">
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
		<script type="text/javascript">
function tanchu(num) {
	if (num == 'add') {

		document.getElementById("xiugaiIframe").src = "./System/SOP/buhegepin_add.jsp";
	} else {
		document.getElementById("xiugaiIframe").src = "BuHeGePinAction_findBhgByDefId.action?id="
				+ num+"&statue=ybd&tag=update";
	}
	chageDiv('block')
}
</script>

	</head>

	<body bgcolor="#ffffff">
		<center>
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
								<span id="title">您正在对缺陷类型进行操作</span>
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
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
					align="left">
				</div>
				<div align="center">
					<div>
						<h3>
							缺陷类型统计管理
						</h3>

						<form action="BuHeGePinAction_findAllDefTypeList.action" method="post"
							id="formid">
							<table width="90%" id="table!">
								<tr>
									<td align="right">
										<strong>缺陷类型 </strong>
									</td>
									<td align="right">
										<strong>缺陷代码 </strong>
									</td>
									<td>
										<input type="text" name="defType.defCode" />
									</td>
									<td>
										<input type="text" name="defType.defName" />
									</td>
								</tr>
								<tr align="center" id="tr!">
									<td colspan="4">
										<input class="button" id="select" type="submit" value="查询"
											width="20%" style="width: 100; height: 40" />
										&nbsp; &nbsp;
										<input class="button" id="add" type="button" value="添加"
											onclick="tanchu('add')" width="20%"
											style="width: 100; height: 40" />
									</td>
								</tr>
							</table>
						</form>

						<br>
					</div>
					<div>
						<table border="1" width="90%">
							<tr align="center" bgcolor="#c0dcf2" height="50px">
								<td>
									序号
								</td>
								<td>
									缺陷类型
								</td>
								<td>
									缺陷代码
								</td>
								<td>
									添加人
								</td>
								<td>
									添加时间
								</td>
								<td>
									操作
								</td>
							</tr>
							<s:iterator id="pagedef" value="defTypeList"
								status="statussdf">
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
									${pagedef.defName}
								</td>
								<td>
									${pagedef.defCode}
								</td>
								<td>
									${pagedef.addUsersName}
								</td>
								<td>
									${pagedef.addtime}
								</td>
								<td>
									<a id="xiugai${pagedef.id}" href="javscript:;"
										onclick="tanchu(${pagedef.id})">修改</a>/
									<a href="javscript:;" onclick="window.open('BuHeGePinAction_findBhgByDefId.action?id=${pagedef.id}&statue=ybd')">明细</a>
<%--									<a onclick="return confirm('确定要删除吗?') "--%>
<%--										href='BuHeGePinAction_delDefType.action?id=${buhegepintest.id}'>删除</a>--%>
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
			</div>
			<%@include file="/util/foot.jsp"%>
<script type="text/javascript">

</script>
		</center>
	</body>
</html>
