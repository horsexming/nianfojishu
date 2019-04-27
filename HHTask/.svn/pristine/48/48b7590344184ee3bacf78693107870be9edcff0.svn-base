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
				</div>
			</div>
			
			<div align="center">
				<form action="rewardPunishAction!findAllRewardPunish.action" method="post"
					style="">
					<br>
					<table border="0" width="100%" class="table">
						
					</table>
				</form>

				<table style="width: 980px; border-collapse: collapse" border="0"
					align="center" >
					
					
					<s:iterator id="rewardPunishCollect" value="rewardPunishCollectList" status="ststusRewardPunishCollect">
						<s:if test="#ststusRewardPunishCollect.index==0">
							<tr align="center" bgcolor="#c0dcf2"
								style="height: 40px; font-weight: bold; font-size: 15px;" >
								<td width="35px">
									序号
								</td>
								<td width="80px">
									部门
								</td>
								<s:iterator id="itemHead" value="list" status="ststusItem">
									<td>
										<s:property value="itemHead"/>
									</td>
								</s:iterator>
								<td>
									(单位/月)
								</td>
							</tr>
						</s:if>
						<s:else>
							<s:if test="#ststusRewardPunishCollect.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')" style="font-size: 12px;">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')" style="font-size: 12px;">
								</s:else>
								<td >
									<s:if test="#ststusRewardPunishCollect.index%2==1">
										<font>
									</s:if>
									<s:else>
										<font color="red">
									</s:else>
									<s:property value="#ststusRewardPunishCollect.index" />
									</font>
								</td>
								<td>${dept}</td>
								<s:iterator id="item" value="list" status="ststusItem">
									<td>
										<s:iterator value="item" id="itemData">
											<s:property value="itemData"/>
										</s:iterator>
									</td>
								</s:iterator>
								<td>${num}&nbsp;&nbsp;${money}</td>
								</tr>
						</s:else>
					</s:iterator>
					<tr>
						<td colspan="7" align="center">
							<font color="red">${errorMessage}${successMessage}</font>
						</td>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<script type="text/javascript">
$(function(){
	$.ajax({
		type: "get",
		dataType: "text",
        url: "DeptNumberAction!finAllDeptNumberForSetlect.action",
		async: false,
		success: function(data){
			var dept=data.split("|");
			for(var i=0;i<dept.length-1;i++){
				var deptItem=new Option(dept[i],dept[i]);
    			$("#dept").append(deptItem); 
			}
		}
	});
});
</script>