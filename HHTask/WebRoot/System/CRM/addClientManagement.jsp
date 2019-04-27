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
	   var kehuname=document.getElementById("kehuname");
	   var zhiwei=document.getElementById("zhiwei");
	   var gongshiname=document.getElementById("gongshiname");
	   var shoujinumber=document.getElementById("shoujinumber");
	   if(kehuname.value==""){
		   alert("客户姓名不能为空");
		   kehuname.focus();
		   return false;
	   }
	   else if(zhiwei.value==""){
		   alert("职位不能为空");
		   zhiwei.focus();
		   return false;
	   }
	   else if(shoujinumber.value==""){
		   alert("手机号码不能为空");
		   shoujinumber.focus();
		   return false;
	   }
	   else if(gongshiname.value==""){
		   alert("公司名称不能为空");
		   gongshiname.focus();
		   return false;
	   }
	   else{
		   return true;
	   }
   }
 
 </SCRIPT>
	</head>
	<body bgcolor="#ffffff">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
					align="left">
					<div style="float: left; width: 50%" align="left">
						
					</div>
				</div>
				
				<div align="center">
					<form action="ClientManagementAction.action" method="post"
						onsubmit="return check()">
						<table align="center">
							<tr>
								<th colspan="6">
									<font size="5">添加客户信息</font>
								</th>
							</tr>
							<s:if test="str==123">
								<tr>
									<td align="center" colspan="6">
										<font color="red">${kehuname}添加成功</font>
									</td>
								</tr>
							</s:if>
							<tr>
								<th>
									客户姓名
								</th>
								<td>
									<input type="text" name="clientManagement.clientname"
										id="kehuname" />
								</td>
								<th>
									所在部门
								</th>
								<td>
									<input type="text" name="clientManagement.clientdept" id="dept" />
								</td>
								<th>
									职位
								</th>
								<td>
									<input type="text" name="clientManagement.clientposition"
										id="zhiwei" />
								</td>
							</tr>
							<tr>
								<th>
									手机号码
								</th>
								<td>
									<input type="text" name="clientManagement.clientmobilenumber"
										id="shoujinumber" />
								</td>
								<th>
									电话号码
								</th>
								<td>
									<input type="text" name="clientManagement.clientphonenumber" />
								</td>
								<th>
									公司名称
								</th>
								<td>
									<input type="text" name="clientManagement.clientcompanyname"
										id="gongshiname" />
								</td>
							</tr>
							<tr>
								<th>
									性别
								</th>
								<td>
									<input type="radio" name="clientManagement.clientsex" id="sex"
										value="男">
									男
									<input type="radio" name="clientManagement.clientsex" id="sex"
										value="女" />
									女
								</td>
								<th>
									身份证号码
								</th>
								<td colspan="3">
									<input type="text" name="clientManagement.clientcardnumber" />
								</td>
							</tr>
							<tr>
								<th>
									备注
								</th>
								<td colspan="5">
									<textarea rows="" name="clientManagement.clientremarks" cols=""
										style="width: 250px; height: 80px;"></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<input type="submit" value="确 定" />
									&nbsp;&nbsp;&nbsp;
									<input type="reset" value="取 消" />
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
