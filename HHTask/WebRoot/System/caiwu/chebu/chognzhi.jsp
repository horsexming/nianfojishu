<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
body {
	text-align: center;
}
</style>

		<%@include file="/util/sonHead.jsp"%>
	</head>
	<%
		Users user = (Users) session.getAttribute("Users");
	%>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">财务油卡充值</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv" style="background-color: #fff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 600px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="gongneng"
			style="width: 100%;text-align:center; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
				</div>
			</div>
           <!-- 列表展示 -->
				<table class="table">
					<tr bgcolor="#c0dcf2" height="30px"
						style="border-collapse: separate;">
						<th align="center">
							序号
						</th>
						<th align="center">
							车牌号
						</th>
						<th align="center">
							车主
						</th>
						<th align="center">
							总申请费用
						</th>
						<th align="center">
							已充值费用
						</th>
						<th align="center">
							可充值费用
						</th>
						<th align="center">
							本次充值费用
						</th>
						
					</tr>
					<form action="carAllowAction!saveCaiwuRecharge.action" method="post">
					<s:if test="{list.size()>0}">
						<s:iterator value="list" status="se" id="bxd">
							<s:if test="#se.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#se.index+1" />
							</td>
							<td>
								${bxd.platenumber}
							</td>
							<td>
								${bxd.name}
							</td>
							<td>
								${bxd.sumcost}
							</td>
							<td>
								${bxd.sumchognzhi}
							</td>
							<td>
								${bxd.chognzhiedu}
							</td>
							<td>
							<input type="hidden" name="listId"
											value="${bxd.id }" />
								<input type="text" name="chongzhi" value="${bxd.chognzhiedu}" 
								onkeyup="hejiJine()" id="h${se.index}"/>
							</td>							
							
						</s:iterator>
						
						
					</s:if>
						<tr>
								<td colspan="7" align="center">
								合计金额
								
									<span id="hejiMoney"><font color="red"><label
												id="allMoney"></label> </font> </span>
												</td>
							</tr>
							<tr>
								<td colspan="7" align="center">
									<input type="submit" value="充值确认"
										style="width: 150px; height: 40px;" align="top">
									&nbsp;&nbsp;
									
								</td>
							</tr>
							</form>
				</table>
          	 
			<br/>
			
			</div>
			<br>
		<%@include file="/util/foot.jsp"%>
	</body>
	<script type="text/javascript">
	
	function hejiJine() {
	var lineCount = "<s:property value='list.size()'/>";
	var total = 0;
	for ( var t = 0; t < lineCount; t++) {
		var id = "h" + t;
		var cur = document.getElementById(id).value;
		total = total + parseFloat(cur);
	}
	document.getElementById("allMoney").innerHTML = total;
	}
	</script>	

</html>
