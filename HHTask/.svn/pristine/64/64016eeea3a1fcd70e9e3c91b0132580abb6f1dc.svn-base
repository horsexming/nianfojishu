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
<center>
				<form action="zhaobiaoAction!addzhaobiao.action" method="post"
					theme="simple">
					
					<table class="table" style="width: 70%;">
					<tr><th colspan="2" align="center">添加招标</th></tr>
						<tr>
							<th align="right">
								招标名称：
							</th>
							<td>
								<input type="text" id="zhaobiao.title" name="zhaobiao.title" />
							</td>
						</tr>
						<tr>
							<th align="right">
								开始时间：
							</th>
							<td  >
								<input class="Wdate" type="text" id="zhaobiao.moban"
									name="zhaobiao.moban"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th align="right">
								结束时间：
							</th>
							<td>
								<input class="Wdate" type="text" id="zhaobiao.kongxian"
									name="zhaobiao.kongxian"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th align="right">
								招标负责人：
							</th>
							<td>
								<input type="text" id="zhaobiao.fuze" name="zhaobiao.fuze"  value="${sessionScope.Users.name}"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								负责人电话：
							</th>
							<td>
								<input type="text" id="zhaobiao.phone" name="zhaobiao.phone"  value="${sessionScope.Users.password.phoneNumber}"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								招商简介：
							</th>
							<td>
								<textarea rows="2" cols="50" id="zhaobiao.loc"
									name="zhaobiao.loc" /></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="保存" class="input" />
								<input type="button" name="Submit2" value="取消" class="input"
									class="right-buttons" onclick="window.history.go(-1);" />
							</td>
						</tr>
					</table>


				</form>
			</div>
			<%@include file="/util/foot.jsp"%>
			
			
			</center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript">
	$(function(){
	var errorMessage = '${errorMessage}';
		if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
	
	})
	
	</script>


</html>
