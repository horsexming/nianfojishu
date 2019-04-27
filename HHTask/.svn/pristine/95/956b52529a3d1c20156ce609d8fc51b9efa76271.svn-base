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
				<form action="zhaobiaoAction!buchongjiepai.action" method="post"
					theme="simple">
					
					<table class="table" style="width: 70%;">
					<tr><th colspan="2" align="center">添加件号节拍</th></tr>
						<tr>
							<th align="right">
								件号名称：
							</th>
							<td>
								<input type="text" id="zhaobiao.title" name="gysjiepai.markId"  value="${gysjiepai.markId}" readonly="readonly"/>
							</td>
						</tr>
						<tr style="display: none">
							<th align="right">
								供应商：
							</th>
							<td>
								<input type="text" name="gysjiepai.gys"  value="${gysjiepai.gys}" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								操作人工节拍：
							</th>
							<td  >
							<input type="text" name="gysjiepai.jiepairen1"  value="${gysjiepai.jiepairen1}"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								操作设备节拍： 
							</th>
							<td>
								<input type="text"  name="gysjiepai.shebei1"  value="${gysjiepai.shebei1}"/>
							
							</td>
						</tr>
						<tr>
							<th align="right">
								准备人工节拍：
							</th>
							<td>
								<input type="text" id="gysjiepai.jiepairen2" name="gysjiepai.jiepairen2"  value="${gysjiepai.jiepairen2}"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								准备次数：
							</th>
							<td>
								<input type="text" id="gysjiepai.zhunbeicishu" name="gysjiepai.zhunbeicishu"  value="${gysjiepai.zhunbeicishu}"/>
							</td>
						</tr>
						<tr>
						<tr>
						
							<td colspan="2" align="center">
								<input type="hidden" name="gysjiepai.id"  value="${gysjiepai.id}"/>
								<input type="hidden" name="gysjiepai.zhuserId"  value="${gysjiepai.zhuserId}"/>
								<input type="hidden" name="gysjiepai.procardTemplateId"  value="${gysjiepai.procardTemplateId}"/>
								<input type="hidden" name="gysjiepai.userId"  value="${gysjiepai.userId}"/>
								
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
