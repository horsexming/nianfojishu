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
						href="craftLoadAction_showList.action"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					工艺负荷管理<br/>(Craft Load Management)
				</h3>
				<form action="craftLoadAction_showList.action"
					method="post">
					<table class="table" align="center">
						<tr>
								<td align="center">
								工序号（process No）：
								<input type="text" name="craftLoad.processNO" value="<s:property value="craftLoad.processNO"/>" />
							</td>
							<td align="center">
								工序名称（process name）：
								<input type="text" name="craftLoad.processName" value="<s:property value="craftLoad.processName"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								零件号（part name）：
								<input type="text" name="craftLoad.markId" value="<s:property value="craftLoad.markId"/>" />
							</td>
							</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" />
									<input type="reset" style="width: 100px; height: 40px;"
									value="重置"/>
									<!-- <input type="button" style="width: 100px; height: 40px;"
									value="更新数据(update all)" onclick="updateall()"/> -->
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号<br/>(num)
						</td>
						<td align="center">
							工艺负荷分值<br/>(craft load score)
						</td>
						<td align="center">
							零件号<br/>（mark id）
						</td>
						<td align="center">
							零件名称<br/>（mark name）
						</td>
						<td align="center">
							工序号<br/>（processNO）
						</td>
						<td align="center">
							工序名称<br/>（process name）
						</td>
						<td align="center">
							工艺复杂分值<br/>（craft complexity score）
						</td>
						<td align="center">
							加工难点分值<br/>（process difficulty score）
						</td>	
						<td align="center">
							是否关键工序<br/>（process PRN score）
						</td>						
						<td align="center" colspan="2">操作<br/>(Operation)</td>
					</tr>
					<s:iterator value="cLoadList" id="cload" status="pageStatus">
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
							${cload.craftLoadScore}
						</td>
						<td>
							${cload.markId}
						</td>
						<td>
							${cload.procardName}
						</td>
						<td>
							${cload.processNO}
						</td>
						<td>
							${cload.processName}
						</td>
						<td>
							${cload.craftComplexity}
						</td>
						<td>
							${cload.processDifficulty}
						</td>
						<td>
							${cload.processPRNScore}
						</td>
						
						<td  colspan="2">
							<input type="button" value="修改(update)"
								style="width: 60px; height: 30px;"
								onclick="update(${cload.id},${cpage})" />
								<input type="button" value="删除(delete)"
								style="width: 60px; height: 30px;"
								onclick="todelete(${cload.id})" />
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
						
						<s:if test="successMessage!=null">
						<tr>
							<td colspan="11" align="center" style="color: red">
								${successMessage}
						</td>
					</tr>
                          </s:if>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function update(id,cpage) {
	window.location.href = "craftLoadAction_toupdate.action?craftLoad.id=" + id+"&cpage="+cpage;
}
function todelete(id) {
	window.location.href = "craftLoadAction_delete.action?craftLoad.id=" + id;
}
function updateall() {
	window.location.href = "craftLoadAction_updateAll.action"
}
</script>
	</body>
</html>
