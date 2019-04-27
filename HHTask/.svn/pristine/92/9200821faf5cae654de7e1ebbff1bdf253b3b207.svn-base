<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
		<SCRIPT type="text/javascript">
   function check(){
	   var gongnumber=document.getElementById("gongnumber");
	   var chengyuanname=document.getElementById("chengyuanname");
	   var kanumber=document.getElementById("kanumber");
	   if(gongnumber.value==""){
		   alert("工号不能为空");
		   gongnumber.focus();
		   return false;
	   }
	   else if(chengyuanname.value==""){
		   alert("成员名称不能为空");
		   chengyuanname.focus();
		   return false;
	   }
	  else if(kanumber.value==""){
		   alert("卡好不能为空");
			kanumber.focus();
		   return false;
	   }
	   else{
		   return true;
	   }
   }
  	function send(obj) {
	var value = encodeURI(obj.value);//对strValue进行编码 
	sendRequest(
			"TeammembersAction!findgongnumber.action?gongnumber=" + value,
			messageResponse);
}
// 联系人查询
function messageResponse() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var message = XMLHttpReq.responseText;
			var gongnumber = document.getElementById("gongnumber");//成员工号
			var chengyuanname = document.getElementById("chengyuanname");//成员姓名
			var kanumber = document.getElementById("kanumber");//成员卡号
			if (message == "") {
				chengyuanname.value = "";
				kanumber.value = "";
				gongnumber.focus();
				gongnumber.select();
				gongnumber.title = "该成员工号不存在!";
				gongnumber.style.border = " solid 1px red";
				return;
			} else {
				var value = message.split("|");
				gongnumber.title = "该成员工号存在!";
				chengyuanname.value = value[0];
				kanumber.value = value[1];
				gongnumber.style.border = " solid 1px #0f0f1f";
			}

		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}
  </SCRIPT>
	</head>
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="FunctionAction!findFunctionById.action?id=${function.id}"
						style="color: #ffffff">添加功能</a>
				</div>
			</div>
			
			<div align="center">
				<form action="TeammembersAction.action" method="post"
					onsubmit="return check()">
					<table align="center">
						<tr>
							<th colspan="6">
								<font size="5">添加班组成员</font>
							</th>
						</tr>
						<s:if test="str==123">
							<tr>
								<td align="center" colspan="6">
									<font color="red">工号：${name} 添加成功</font>
								</td>
							</tr>
						</s:if>
						<tr>
							<th>
								成员工号
							</th>
							<td>
								<input type="text" name="teammembers.teammembersmembernumber"
									onblur="send(this)" id="gongnumber" />
							</td>
							<th>
								成员姓名
							</th>
							<th>
								<input type="text" name="teammembers.teammembersteamname"
									readonly="readonly" id="chengyuanname" />
							</th>
							<th>
								成员卡号
							</th>
							<td>
								<input type="text" name="teammembers.teammemberscardnumber"
									readonly="readonly" id="kanumber" />
							</td>
						</tr>

						<tr>
							<th>
								备注
							</th>
							<td colspan="5">
								<textarea rows="" style="width: 350px; height: 80px;" cols=""
									name="teammembers.teammembersremarks"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="确  定" />
								&nbsp;&nbsp;
								<input type="reset" value="取  消" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
