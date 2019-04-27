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
							<span id="title">车补申请记录</span>
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
							充值金额
						</th>
						<th align="center">
							是否报销
						</th>
						<th align="center">
							充值时间
						</th>						
						
						<th align="center">
							操作
						</th>
					</tr>

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
								${bxd.chongzhiJine}
							</td>
							<td>
								${bxd.baoxiaoStatus}
							</td>
							<td>
								${bxd.operateTime}
							</td>	
							<td>								
								<input type="button" id="delFile"
									onclick="dele1('${bxd.id}')" value="del">
									
							</td>
						</s:iterator>
						<tr>
							<td colspan="7" align="right">
								共
								<s:property value="total" />
								页 第
								<s:property value="cpage" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td colspan="7" style="font-size: 15px; color: red;">
								对不起，没有查到相关的车补充值信息
							</td>
						</tr>
					</s:else>
				</table>
          	 
			<br/>
			
			</div>
			<br>
		<%@include file="/util/foot.jsp"%>
	</body>
	<script type="text/javascript">
	//刪除
	function dele1(id1){
	if (window.confirm('确认要删除该条车补油卡充值记录吗?')) {
		window.location.href = "carAllowAction!deleteOneCZHistory.action?id="+ id1;
	}
}
	
	</script>		

</html>
