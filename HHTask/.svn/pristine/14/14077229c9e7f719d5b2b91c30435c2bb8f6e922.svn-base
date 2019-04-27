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
						href="processPRNScoreAction_showList.action"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					工序PRN评分管理<br/>(Process PRN Score Management)
				</h3>
				<form action="processPRNScoreAction_showList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								工序（process）：
								
								<input type="text" name="processPRNScore.processName" value="<s:property value="processPRNScore.processName"/>" />
							</td>
						</tr>
						
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加工序PRN评分(add)" onclick="add()"/>
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
							工序<br/>(process)
						</td>
						<td align="center">
							风险评分<br/>（risk core）
						</td>
						<td align="center">
							评分内容1<br/>（content1）
						</td>
						<td align="center">
							评分内容2<br/>（content2）
						</td>
						<td align="center">
							评分内容3<br/>（content3）
						</td>						
						<td align="center" colspan="2">操作<br/>(Operation)</td>
					</tr>
					<s:iterator value="pPRNSList" id="pPRNS" status="pageStatus">
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
							${pPRNS.processName}
						</td>
						<td>
							${pPRNS.riskScore}
						</td>
						<td>
							${pPRNS.content1}
						</td>
						<td>
							${pPRNS.content2}
						</td>
						<td>
							${pPRNS.content3}
						</td>
						
						<td  colspan="2">
							<input type="button" value="修改(update)"
								style="width: 60px; height: 30px;"
								onclick="update(${pPRNS.id})" />

							<input type="button" value="删除(delete)"
								style="width: 60px; height: 30px;"
								onclick="todelete(${pPRNS.id })" />
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
function update(id) {
	window.location.href = "processPRNScoreAction_toupdate.action?processPRNScore.id=" + id;
}
function todelete(id) {
	window.location.href = "processPRNScoreAction_delete.action?processPRNScore.id=" + id;
}
function add() {
	window.location.href = "processPRNScoreAction_toadd.action";
}

</script>
	</body>
</html>
