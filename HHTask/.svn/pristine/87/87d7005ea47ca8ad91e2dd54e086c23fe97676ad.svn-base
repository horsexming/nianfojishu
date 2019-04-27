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
		<style type="text/css">
			.divHeaderTable{
	            width: 100%;
	            padding-bottom:5px;
	            display:block;
	            float:left;
			}
			.ul{
				margin: 0 20 0 20;
			}
			.li{
				margin-top: 60;
			}
			.input{
				width: 200px;
				height: 48px;
			}
			
		</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div><input class="input" onclick="window.history.back();" type="button" value="返回"/></div>
			<div align="center">
<%--			<s:if test="#session.Users.name!=null">--%>
				<ul>
<%--			</s:if>--%>
<%--			<s:else>--%>
<%--				<ul style="margin-top: 150px;">--%>
<%--			</s:else>--%>
					<li class="li"><input class="input"  onclick="window.history.back();" type="button" value="领工序"/></li>
					<li class="li"><input class="input" onclick="dianji(2)" type="button" value="请假"/></li>
					<li class="li"><input class="input" onclick="dianji(3)" type="button" value="收入"/></li>
					<li class="li"><input class="input" onclick="dianji(4)" type="button" value="其他"/></li>
					<li class="li"><input class="input" onclick="dianji(5)" type="button" value="现场卫生"/></li>
					<li class="li"><input class="input" onclick="dianji(6)" type="button" value="计件工资"/></li>
				</ul>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function toadd(){
	window.location.href = "ToolCabineAction_toadd.action?tag=${tag}";
}
function dianji(id){
	if(id==1){
<%--		window.location.href = "SuspsomAction_selectGongxu.action?ipAddress=${ipAddress}&tag=one";--%>
		parent.location.reload(true);
	}else if(id==2){
		window.location.href = "SuspsomAction_qingjia.action?pageStatus=self&ipAddress=${ipAddress}&tag=one";
	}else if(id==3){
		window.location.href = "SuspsomAction_jiangjingongzi.action?wage.code=${session.Users.code}&pageStatus=print";
	}else if(id==4){
		window.location.href = "IntegralAction_findIntegral.action?statue=person&ipAddress=${ipAddress}&tag=one";
	}else if(id==5){
		window.location.href = "<%=basePath%>/System/SOP/block/boclk_Receive.jsp";
	}else if(id==6){
		if('${Users.id}' != ''){
			window.location.href = "ProcardAction!findUMMoneyByCondition.action?umm.code=${Users.code}";
		}else{
			window.location.href = "ProcardAction!findUMMoneyByCondition.action";
		}
		
	}
}
</script>
	</body>
</html>
