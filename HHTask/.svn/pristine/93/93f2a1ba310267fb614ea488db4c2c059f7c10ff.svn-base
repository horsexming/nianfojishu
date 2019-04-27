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
			<s:if test='waigoudd.againcheck == "待复检"'>
				<h2>
					件号:${waigoudd.markId}<br/>
					名称:${waigoudd.proName}<br/>
					来料数量:${waigoudd.qrNumber}<br/>
					检验数量:${waigoudd.jyNumber}<br/>
					检验出不合格为${waigoudd.jybhgNumber}<br/>
					超出不合格判定数${xujianpingci.re}<br/>
					是否增大抽检数量到${xujianpingci.erchoujian}继续检验?
				</h2>
				<input type="button" value="是" onclick="isAgainCheck(this)" class="input" id="but_yes"/>
<%--				<input type="button" value="否"  onclick="isAgainCheck(this)" class="input" id="but_no"/>--%>
			</s:if>
			<s:elseif test='waigoudd.againcheck == "待分检"'>
				<h2>
					件号:${waigoudd.markId}名称:${waigoudd.proName}来料数量:${waigoudd.qrNumber}增大复检量检验不合格
					请分拣后继续检验；
				</h2>
				<b>分拣数量:</b>	<input type="text" value="" name="" id="fenjianNum"/><br/>
				<input type="button" value="分拣" onclick="isAgainCheck(this)" class="input" id="but_yes"/>
				<input type="button" value="否"  onclick="isAgainCheck(this)" class="input" id="but_no"/>
			</s:elseif>	
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">

function isAgainCheck(obj){
	var isAgainCheck ="";
	var fenjianNum = 0;
	if(obj.value == "是"){
		isAgainCheck = "复检";
	}else if(obj.value == "分检"){
		isAgainCheck = "分检";
		fenjianNum = $("#fenjianNum").val();
		if(fenjianNum == ''){
			alert("请输入分检后数量!")
			return;
		}
	}
	$.ajax( {
		type : "POST",
		url : "WaigouwaiweiPlanAction!isAgainCheck.action",
		data : {
			'waigoudd.id':${waigoudd.id},
			'waigoudd.againcheck':isAgainCheck,
			'waigoudd.shNumber':fenjianNum
		},
		dataType : "json",
		success : function(data) {
			parent.window.location.reload();
		}
	})
}

</SCRIPT>
	</body>
</html>
