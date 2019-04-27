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
				<form action="markIdAction!updatewaigoujianpinciZi.action" method="post"
					theme="simple">
					
					<table class="table" style="width: 70%;">
					<tr><th colspan="2" align="center">巡检批量</th></tr>
						<tr>
							<th align="right">
								巡检批量范围:
							</th>
							<td>
								<input  type="text"  id="waigoujianpinciZi.caigoushuliang1"   name="waigoujianpinciZi.caigoushuliang1"  value="${waigoujianpinciZi.caigoushuliang1}" />-
								<input  type="text"  id="waigoujianpinciZi.caigoushuliang2"   name="waigoujianpinciZi.caigoushuliang2"  value="${waigoujianpinciZi.caigoushuliang2}" />
							</td>
						</tr>
						<tr>
							<th align="right">
							样本代码:
							</th>
							<td>
								<input  type="text"  id="waigoujianpinciZi.ybCode"  value="${waigoujianpinciZi.ybCode}"  name="waigoujianpinciZi.ybCode"  />
							</td>
						</tr>
						<tr>
							<th align="right">
								样本大小:
							</th>
							<td>
								<input  type="text"  id="waigoujianpinciZi.choujian" value="${waigoujianpinciZi.choujian}"  name="waigoujianpinciZi.choujian"  />
							</td>
						</tr>
						<tr>
							<th align="right">
								AC:
							</th>
							<td>
								<input  type="text"  id="waigoujianpinciZi.ac"   name="waigoujianpinciZi.ac" value="${waigoujianpinciZi.ac}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								RE:
							</th>
							<td>
								<input  type="text"  id="waigoujianpinciZi.re"   name="waigoujianpinciZi.re" value="${waigoujianpinciZi.re}"  />
							</td>
						</tr>
						<tr>
							<th align="right">
								再次抽检样本大小:
							</th>
							<td>
								<input  type="text"  id="waigoujianpinciZi.erchoujian" value="${waigoujianpinciZi.erchoujian}"  name="waigoujianpinciZi.erchoujian"  />
							</td>
						</tr>
						<tr>
							<th align="right">
								再次抽检AC:
							</th>
							<td>
								<input  type="text"  id="waigoujianpinciZi.ac1"   name="waigoujianpinciZi.ac1" value="${waigoujianpinciZi.ac1}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								再次抽检RE:
							</th>
							<td>
								<input  type="text"  id="waigoujianpinciZi.re1"   name="waigoujianpinciZi.re1" value="${waigoujianpinciZi.re1}"  />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<input  type="hidden"  id="waigoujianpinciZi.waigoujianpinciId"   name="waigoujianpinciZi.waigoujianpinciId"  value="${waigoujianpinciZi.waigoujianpinciId}"/>
								<input  type="hidden"  id="waigoujianpinciZi.id"   name="waigoujianpinciZi.id"  value="${waigoujianpinciZi.id}"/>
								<input type="hidden" value="巡检" name="waigoujianpinciZi.type"/>
								<input type="hidden" value="${status}" name="status"/>
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
