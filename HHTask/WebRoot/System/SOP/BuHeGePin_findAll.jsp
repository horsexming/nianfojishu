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
.dhlabel {
	border-top: 1px solid #000;
	border-bottom: 1px solid #000;
	border-left: 1px solid #000;
	border-right: 1px solid #000;
	margin-left: 5px;
	margin-right: 5px;
	padding: 3px 5px;
	white-space: nowrap;
}
</style>

	</head>

	<body bgcolor="#ffffff">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
					align="left">
				</div>
				<div align="center">
					<div>
						<h3>
							${defType.defName}缺陷小类统计管理
						</h3>

						<form action="BuHeGePinAction_findBhgByDefId.action" method="post"
							id="formid">
							<table width="90%" id="table!">
								<tr>
									<td align="right">
										<strong>缺陷代码 </strong>
									</td>
									<td>
										<input type="text" name="buhegepin.code" />
									</td>
									<td align="right">
										<strong>缺陷类型 </strong>
									</td>
									<td>
										<input type="text" name="buhegepin.type" />
									</td>
								</tr>
								<tr align="center" id="tr!">
									<td colspan="4">
										<input type="hidden" value="${statue}" name="statue">
										<input class="button" id="select" type="submit" value="查询"
											width="20%" style="width: 100; height: 40" />
										&nbsp; &nbsp;
<%--										<input class="button" id="add" type="button" value="添加"--%>
<%--											onclick="tanchu('add')" width="20%"--%>
<%--											style="width: 100; height: 40" />--%>
									</td>
								</tr>
							</table>
						</form>
						<br>
					</div>
					<s:if test="statue=='ybd'">
						<label style="background-color: gray;" class="dhlabel">
							已绑缺陷小类
						</label>
					</s:if>
					<s:else>
						<label style="background-color: #5cb85c; cursor: pointer;"
							onclick="toShowWW(${defType.id},'ybd');" class="dhlabel">
							<font color="white">已绑缺陷小类</font>
						</label>
					</s:else>
					<s:if test="statue=='wbd'">
						<label style="background-color: gray;" class="dhlabel">
							未绑缺陷小类
						</label>
					</s:if>
					<s:else>
						<label style="background-color: #5cb85c; cursor: pointer;"
							onclick="toShowWW(${defType.id},'wbd');" class="dhlabel">
							<font color="white">未绑缺陷小类</font>
						</label>
					</s:else>
					<div>
					<form action="BuHeGePinAction_bangdingbhg.action" method="Post">
						<table border="1" width="90%">
							<tr align="center" bgcolor="#c0dcf2" height="50px">
								<td>
									<input type="checkbox" onclick="chageAllCheck(this,'','')" >
								</td>
								<td>
									序号
								</td>
								<td>
									缺陷代码
								</td>
								<td>
									缺陷类型
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
							<s:iterator id="pahebhg" value="buhegepinlist"
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
									<input type="checkbox" name="selected"  value="${pahebhg.id}">
								</td>
								<td>
									<s:property value="#statussdf.index+1" />
								</td>
								<td>
									${pahebhg.code}
								</td>
								<td>
									${pahebhg.type}
								</td>
								<td>
									${pahebhg.writePerson}
								</td>
								<td>
									${pahebhg.writeDate}
								</td>
								<td>
									<a id="xiugai${buhegepintest.id}" href="javscript:;"
										onclick="tanchu(${buhegepintest.id})">修改</a>/
									<a onclick="return confirm('确定要删除吗?') "
										href='BuHeGePinAction_delBuHeGePin.action?id=${buhegepintest.id}'>删除</a>
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
						<input type="hidden" value="${defType.id}" name = "id"/>
						<input type="submit" value="绑定" class="input"/>
					</form>
					</div>
				</div>
			</div>
			<%@include file="/util/foot.jsp"%>
<script type="text/javascript">
function toShowWW(id,status){
	window.location.href = "BuHeGePinAction_findBhgByDefId.action?id="+id+"&statue="+status;
}
</script>
		</center>
	</body>
</html>
