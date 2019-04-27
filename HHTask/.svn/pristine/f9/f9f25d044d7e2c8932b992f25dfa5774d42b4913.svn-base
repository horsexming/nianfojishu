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
				<form action="markIdAction!updatewaigoujianpinci.action" method="post"
					theme="simple">
					
					<table class="table" style="width: 70%;">
					<tr><th colspan="2" align="center" id="th_1">外购外协件验收频次规定</th></tr>
						<tr>
							<th align="right" id="th_2">
								类型：
							</th>
							<td>
								<input type="text" id="waigoujianpinci.leixing" name="waigoujianpinci.leixing"  
								 value="${waigoujianpinci.leixing}"/>
							</td>
						</tr>
						<tr id="tr_1">
							<th align="right">
								节拍：
							</th>
							<td>
								<input type="text" id="waigoujianpinci.jiepai" name="waigoujianpinci.jiepai"  value="${waigoujianpinci.jiepai}" />
							</td>
						</tr>
						<tr id="tr_2">
							<th align="right">
								说明：
							</th>
							<td>
							<textarea rows="2" cols="50" id="waigoujianpinci.shuoming"
									name="waigoujianpinci.shuoming"  />${waigoujianpinci.shuoming}</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center" id="submit_td">
							<input type="hidden" id="waigoujianpinci.id" name="waigoujianpinci.id"   value="${waigoujianpinci.id}"/>
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
		if('${status}' == 'xj'){
			$("#th_1").html("巡检允手质量标准修改");
			$("#th_2").html("标准");
			$("#tr_1").remove();
			$("#submit_td").append('<input type="hidden" value="巡检" name="waigoujianpinci.type">');
		}
	var errorMessage = '${errorMessage}';
		if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
	
	})
	
	</script>


</html>
