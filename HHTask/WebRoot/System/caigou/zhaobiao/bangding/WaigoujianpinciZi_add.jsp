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
				<form action="markIdAction!addwaigoujianpinciZi.action" method="post"
					theme="simple">
					
					<table class="table" style="width: 70%;">
					<tr><th colspan="2" align="center">外购外协件验收频次规定子项</th></tr>
						<tr>
							<th align="right">
								采购数量范围：
							</th>
							<td>
								<input  type="text"  id="waigoujianpinciZi.caigoushuliang1"   name="waigoujianpinciZi.caigoushuliang1" 
								value="${num}"  />   ~
								<input  type="text"  id="waigoujianpinciZi.caigoushuliang2"   name="waigoujianpinciZi.caigoushuliang2"  />
							</td>
						</tr>
						<tr>
							<th align="right">
							一般抽检数量/件:
							
							</th>
							<td>
								<input  type="text"  id="waigoujianpinciZi.choujian"   name="waigoujianpinciZi.choujian"  />
							</td>
						</tr>
						<tr>
							<th align="right">
							再次抽检数量/件：
							</th>
							<td>
								<input  type="text"  id="waigoujianpinciZi.erchoujian"   name="waigoujianpinciZi.erchoujian"  />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<input  type="hidden"  id="waigoujianpinciZi.waigoujianpinciId"   name="waigoujianpinciZi.waigoujianpinciId"  value="${waigoujianpinci.id}" />
							
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
