<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<div align="center">
			<br />
			<form action="BaoXiaoDanAction!importFile.action" method="post"
				enctype="multipart/form-data">
				选择导入文件:
				<input type="file" name="uploadFile" />
				<a href="<%=basePath%>/upload/file/download/payee.xls">导入模版下载</a>
				<input type="submit" value="批量导入" id="sub" />
			</form>
			<br />
			<form action="BaoXiaoDanAction!addPayee.action"
				method="post">
				<table align="center" class="table">
					<tr>
						<th colspan="4">
							<font size="5">添加收款账户</font>
						</th>

					</tr>
					<tr>
						<th align="right">
							开户行：
						</th>
						<td>
							<input id="bank" name="payee.bank"
								value="${payee.bank}" />
						</td>

						<th align="right">
								联系方式:
							</th>
							<td>
								<input id="cmobile" name="payee.cmobile"
									value="${payee.cmobile}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								账号:
							</th>
							<td>
								<input id="aNumber" name="payee.aNumber"
									value="${payee.aNumber}" />
							</td>
							<th align="right">
								名称:
							</th>
							<td>
								<input id="name" name="payee.name"
									value="${payee.name}" onchange="findSame()" />
							</td>
						</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="submit"
								value="添加" class="input" />
							<input type="reset" value=" 重置" class="input" />
							<input class="input" onclick="window.history.back();" type="button" value="返回"/>
						</td>
					</tr>
				</table>
			</form>
		</div>

		<SCRIPT type="text/javascript">
		function findSame(){
		var name = document.getElementById("name").value;
		$.ajax( {
		type : "POST",
		url:"BaoXiaoDanAction!checkName.action",
		data : {
			name:name
		},
		dataType : "json",
		success : function(data) {
			 if(data=="err"){     
                 alert("该收款名称已经存在");    
               } 
		}
	});
   } 
      
		</SCRIPT>
	</body>
</html>