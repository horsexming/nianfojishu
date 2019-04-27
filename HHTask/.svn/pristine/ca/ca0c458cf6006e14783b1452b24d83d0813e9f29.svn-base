<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
						href="tryMakeAction_showList.action"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					项目试制评审零件管理<br/>(Project Try make part management)
				</h3>
				<form action="tryMakeAction_showList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								项目名称（project name）：
								<input type="text" name="proTryMakeScore.proName" value="<s:property value="proTryMakeScore.proName"/>" />
							</td>
							<td align="center">
								月份（project name）：
								<input type="text"  class="Wdate" name="proTryMakeScore.month" value="<s:property value="proTryMakeScore.month"/>"
								id="month" onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<td align="center">
								零件号（part number）：
								<input type="text" name="tryMake.markId" value="<s:property value="tryMake.markId"/>" />
							</td>
							<td align="center">
								零件名字（part name）：
								<input type="text" name="tryMake.partName" value="<s:property value="tryMake.partName"/>" />
							</td>
						</tr>
						
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加试制零件(add)" onclick="add()"/>
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
							<br />
							(num)
						</td>
						<td align="center">
							项目名称
							<br />
							(project name)
						</td>
						<td align="center">
							月份<br />
							(Group)
						</td>
						<td align="center">
							入库数量
							<br />
							（input number）
						</td>
						<td align="center">
							零件号
							<br />
							（part number）
						</td>
						<td align="center">
							零件名称
							<br />
							（part name）
						</td>
						<td align="center">
							项目阶段
							<br />
							（project status）
						</td>
						<td align="center">
							工艺负荷分值
							<br />
							（Craft load score ）
						</td>
						<td align="center">
						             占比
							<br />
							（rate）
						</td>
						<td align="center">
							产能负荷系数
							<br />
							（Productivity load score）
						</td>
						<td align="center">
							占比
							<br />
							（rate）
						</td>
						<td align="center">
							奖金金负荷系数
							<br />
							（Bonus load score）
						</td>
						<td align="center">
							占比
							<br />
							（rate）
						</td>
						<td align="center">
							客户重要系数
							<br />
							（Customer importance score）
						</td>
						<td align="center">
							占比
							<br />
							（rate）
						</td>
						<td align="center">
						   总比得分
							<br />
							（total score）
						</td>
						<td align="center" colspan="2">操作<br/>(Operation)</td>
					</tr>
					<s:iterator value="tmVoList" id="tmVo" status="pageStatus">
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
								${tmVo.proName}
							</td>
							<td>
								${tmVo.month}
							</td>
							<td>
							${tmVo.inputNum}
							</td>
							<td>
							${tmVo.markId}
							</td>
							<td>
							${tmVo.partName}
							</td>
							<td>
							${tmVo.projectStatu}
							</td>
							<td>
							${tmVo.craftLoad}
							</td>
							<td>
							${tmVo.cLoadRate}
							</td>
							<td>
							${tmVo.productivityLoad}
							</td>
							<td>
							${tmVo.pLoadRate}
							</td>
							<td>
							${tmVo.bonusLoad}
							</td>
							<td>
							${tmVo.bLoadRate}
							</td>
							<td>
							${tmVo.cusImportance}
							</td>
							<td>
							${tmVo.cImRate}
							</td>
							<td>
							${tmVo.totalRate}
							</td>
						<td  colspan="2">
							<input type="button" value="修改(update)"
								style="width: 60px; height: 30px;"
								onclick="update(${tmVo.id})" />

							<input type="button" value="删除(delete)"
								style="width: 60px; height: 30px;"
								onclick="todelete(${tmVo.id},${cpage})" />
						</td>

					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="17" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="17" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
						
						<s:if test="successMessage!=null">
						<tr>
							<td colspan="17" align="center" style="color: red">
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
	window.location.href = "tryMakeAction_toupdate.action?tryMake.id=" + id;
}
function todelete(id,cpage) {
	window.location.href = "tryMakeAction_delete.action?tryMake.id=" + id+"&cpage="+cpage;
}
function add() {
	window.location.href = "tryMakeAction_toadd.action";
}
</script>
	</body>
</html>
