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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					IP管理
				</h3>
				<form action="ipManager_queryIPEntityByCondition.action"
					method="post">
					<table class="table">
						<tr>
							<td>
								IP地址：
								<input type="text" name="ipStr" value="${ipStr}" />
							</td>
							<td>
								用户名：
								<input type="text" name="nameStr" value="${nameStr}" />
							</td>
						</tr>
						<tr>
							<td>
								部门&nbsp;&nbsp;&nbsp;：
								<input type="text" name="deptStr" value="${deptStr}" />
							</td>
							<td>
								状态：
								<s:if test='%{"闲置"==stateStr}'>
									<input type="radio" name="stateStr" value="使用">使用</input>
									<input type="radio" name="stateStr" checked="checked"
										value="闲置">闲置</input>
									<input type="hidden" name="errorMessage" value="all" />
								</s:if>
								<s:else>
									<input type="radio" name="stateStr" checked="checked"
										value="使用">使用</input>
									<input type="radio" name="stateStr" value="闲置">闲置</input>
									<input type="hidden" name="errorMessage" value="all" />
								</s:else>

							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加IP信息" onclick="add()" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							IP地址
						</td>
						<td align="center">
							用户名
						</td>
						<td align="center">
							计算机名
						</td>
						<td align="center">
							默认网关
						</td>
						<td align="center">
							使用部门
						</td>
						<td align="center">
							IP状态
						</td>
						<td align="center">
							备注
						</td>
						<td></td>
						<td></td>
					</tr>
					<s:iterator value="list" id="pageList" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td>
							${pageList.ip }
						</td>
						<td>
							${pageList.name}
						</td>
						<td>
							${pageList.computerName}
						</td>
						<td>
							${pageList.gateway }
						</td>
						<td>
							${pageList.dept }
						</td>
						<td>
							${pageList.status }
						</td>
						<td>
							${pageList.remark }
						</td>
						<td>
							<input type="button" value="修改"
								style="width: 60px; height: 30px;"
								onclick="update(${pageList.id})" />
						</td>
						<td>
							<input type="button" value="删除"
								style="width: 60px; height: 30px;"
								onclick="del(${pageList.id })" />
						</td>

					</s:iterator>
					</tr>
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
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function update(id) {
	window.location = "ipManager_initUpdate.action?id=" + id;
}
function del(id) {
	window.location = "ipManager_del.action?id=" + id;
}
function add() {
	window.location = "ipManager_initAdd.action";
}
</script>
	</body>
</html>
