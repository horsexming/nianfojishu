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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<font color="red" size="5">${errorMessage}</font>
			<s:if test="status != 'person'">
				<form action="IntegralGiftAction_showigList.action" method="post">
					<strong>姓名:</strong>
					<input type="text" value="" name="integralgift.userName"/>
					<strong>部门:</strong>
					<input type="text" value="" name="integralgift.dept"/>
					<strong>礼品名称:</strong>
					<input type="text" value="" name="integralgift.giftNmae"/>
					<input type="submit" value="查询" class="input"/>
				</form>
			</s:if>
			<s:else>
				<div style="height: 8px;">&nbsp;</div>
				<h2 style="font-size: 25;">个人积分兑换情况</h2><div style="height: 8px;">&nbsp;</div>
			</s:else>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>序号</th>
						<th>姓名</th>
						<th>部门</th>
						<th>礼品图片</th>
						<th>礼品名称</th>
						<th>领取数量</th>
						<th>类型</th>
						<th>单件积分</th>
						<th>消耗积分</th>
						<th>兑换码</th>
						<th>操作</th>
					</tr>
				<s:iterator id="pagelist" value="igList" status="pagestatus">
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
						<s:property value="#pagestatus.index+1" />
					</td>
					<td>${pagelist.userName}</td>
					<td>${pagelist.dept}</td>
					<td>
						<img alt="${pagelist.giftNmae}" src="<%=basePath%>upload/file/Gift/${pagelist.picture}" 
						 	onmousemove="showimg(${pagelist.id})"  style="width: 25px;height: 25px">
						 <div id="div_${pagelist.id}" style="display: none;">
						 	<img alt="${pagelist.giftNmae}" 
						 	src="<%=basePath%>upload/file/Gift/${pagelist.picture}" onmouseout="hidimg(${pagelist.id})">
						 </div>
					</td>
					<td>
						${pagelist.giftNmae}
					</td>
					<td>${pagelist.lqnum}</td>
					<td>${pagelist.type}</td>
					<td>${pagelist.djIntegral}</td>
					<td>${pagelist.xaIntegral}</td>
					<td>${pagelist.dhnum}</td>
					<td>
						<s:if test="status != 'person'">
							<a href="IntegralGiftAction_delintegralgift.action?integralgift.id=${pagelist.id}&cpage=${cpage}" onclick="confirm('确定要删除吗?')">删除</a>
						</s:if>
					</td>
					</tr>
   				 </s:iterator>
   				 <tr>
				<td colspan="11" align="right">
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
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function showimg(num){
	$("#div_"+num).show();
}
function hidimg(num){
	$("#div_"+num).hide();
}
</script>
	</body>
</html>
