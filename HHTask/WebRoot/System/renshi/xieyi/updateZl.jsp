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
		<script type="text/javascript">


</script>
	</head>
	<body bgcolor="#ffffff" onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 30px; "
				align="left">
					<form action="UsersAction!updateZL.action" method="post"
						onsubmit="return chackForm()" enctype="multipart/form-data">
						<input type="hidden" name="user.id" value="${user.id}">
						<table width="100%" border="1" class="table" bgcolor="#F0F0F0">
							<tr>
								<th colspan="6" align="center">
									<font color="red">${user.name}</font>的信息资料 
									<a href="UsersAction!findUserById.action?id=${user.id}"
										target="_blank"> </a>
								</th>
							</tr>
							<tr>
								<th colspan="6" align="center">
									<br />
									个人资料
									<br />
									<br />
								</th>
							</tr>
							<tr>
								<th align="right">
									姓名:
								</th>
								<td>
									<input id="userName" name="user.name" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.name}">
									<font color="red"> *</font>
								</td>
								<th align="right">
									性别:
								</th>
								<td>
									<s:if test='user.sex=="男"'>
										<input type="radio" name="user.sex" value="男"
											checked="checked">
									男
									<input type="radio" name="user.sex" value="女">
									女
									</s:if>
									<s:else>
										<input type="radio" name="user.sex" value="男">
									男
									<input type="radio" name="user.sex" value="女" checked="checked">
									女
								</s:else>
									<font color="red"> *</font>
								</td>
								<th rowspan="3" align="center">
									照片
								</th>
								<td rowspan="3">
									<s:if test='user.sex =="男"'>
										<img alt="${user.name}"
											src="upload/user/${user.password.picture}" width="120px;"
											style="border: solid 1px #000000; height: 130px;"
											onerror="this.src='images/man.jpg'">
									</s:if>
									<s:else>
										<img alt="${user.name}"
											src="upload/user/${user.password.picture}" width="120px;"
											style="border: solid 1px #000000; height: 130px;"
											onerror="this.src='images/woman.jpg'">
									</s:else>
									
								</td>
							</tr>
							<tr>
								<th align="right">
									邮箱:
								</th>
								<td>
									<input id="mailBox" name="user.password.mailBox"
										class="horizontalLine" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.password.mailBox}">
								</td>
								<th align="right">
									手机:
								</th>
								<td>
									<input id="phoneNumber"
										onkeyup="this.value=this.value.replace(/\D/g,'')"
										name="user.password.phoneNumber" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.password.phoneNumber}">
									<font color="red"> *</font>
								</td>
							</tr>
								<tr>
								<th align="right">
									重传照片:
								</th>
								<td>
									<input type="file" name="picture">
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
										<input type="submit" value="修改"
											style="width: 80px; height: 50px;" />
										<input type="reset" value="重置"
											style="width: 80px; height: 50px;" />
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<br>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
