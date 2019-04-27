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
				<br />
				<form action="IntegralAction_updateIntegral.action" method="post"
					onsubmit="return check()">
					<table>
						<s:if test="statue=='zengjia'">
							<tr>
								<td colspan="2" align="center">
									<font size="5"><strong>增加积分</strong> </font>
								</td>
							</tr>
							<tr>
								<th align="right">
									积分
								</th>
								<td>
									<input type="text" name="integral.isList[0].addintegral"
										id="zjjf" />
								</td>
							</tr>
							<tr>
								<th align="right">
									积分来源
								</th>
								<td>
									<input type="text" name="integral.isList[0].laiyuan"
										id="laiyuan" />
								</td>
							</tr>
							<tr>
						</s:if>
						<s:elseif test="statue=='xiaofei'">
							<tr>
								<td colspan="2" align="center">
									<font size="5"><strong>消费</strong> </font>
								</td>
							</tr>
							<tr>
							</tr>
							<tr>
								<th>
									消费积分
								</th>
								<td>
									<input type="text" name="integral.xfList[0].xiaofeijifen"
										id="xfjf" />
								</td>
							</tr>
							<tr>
								<th>
									消费内容
								</th>
								<td>
									<input type="text" name="integral.xfList[0].neirong"
										id="neirong" />
								</td>
							</tr>
						</s:elseif>
						<tr>
							<td colspan="2" align="center">
								<input type="hidden" name="integral.id" value="${integral.id}" />
								<input type="hidden" id="rebeack" value='${successMessage}' />
								<input type="submit" value="提交" id="sub" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
$(document).ready(function(){
		var rebeack=$("#rebeack").val();
		if(rebeack =="成功"){
			alert(rebeack);
			parent.chageDiv('none');
			parent.window.location.reload();
		}
	})
function check(){
	var zjjf=document.getElementById("zjjf");
	var xfjf=document.getElementById("xfjf");
	var laiyuan=document.getElementById("laiyuan");
	var neirong=document.getElementById("neirong");
	var reg='^[1-9]\d*|0$';
	if(zjjf!=null&&laiyuan!=null){
		var value=zjjf.value;
		var r = value.match(reg);  
		if(r==null){
			alert("请输入非负整数");
			zjjf.focus();
			return false;
		}else if(laiyuan.value==""){
			alert("积分来源不能为空");
			laiyuan.focus();
			return false;
		}
	}
	else if(xfjf!=null&&neirong!=null){
		var value=xfjf.value;
		var r = value.match(reg);
		if(r==null){
			alert("请输入非负整数");
			xfjf.focus();
			return false;
		}else if(neirong.value == ""){
			alert("消费内容不能为空");
			neirong.focus();
			return false;
		}
	}
	document.getElementById("sub").disabled="disabled";
	return true;
}
</SCRIPT>
	</body>
</html>
