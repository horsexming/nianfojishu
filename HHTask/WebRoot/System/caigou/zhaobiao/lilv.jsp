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
			<div align="center">
			<form action="zhaobiaoAction!updatezhaobiaoXililv.action" method="post"
					theme="simple">
				<table style="border: 1px;width: 70%">
					<tr>
						<th colspan="2">
							招商信息
						</th>
					</tr>
					<tr>
						<td width="25%">
							招标题目
						</td>
						<td width="70%">
							${zhaobiao.title}
						</td>
					</tr>

					<tr>
						<td>
							招标负责人:
						</td>
						<td>
							${zhaobiao.fuze}
						</td>
					</tr>
					<tr>
						<td>
							负责人电话:
						</td>
						<td>
							${zhaobiao.phone}
						</td>
					</tr>
					<tr>
						<td>
							开始时间:
						</td>
						<td>
							${zhaobiao.moban}
						</td>
					</tr>
					<tr>
						<td>
							结束时间:
						</td>
						<td>
							${zhaobiao.kongxian}
						</td>
					</tr>

					<tr>
						<td>
							招商简介:
						</td>
						<td>
							<tr>
								<td colspan="2" width="100%" height="100px">
									${zhaobiao.loc}
									</textarea>
				</table>
				<!--               -->
				<br />
				<br />
				
				<table class="table" style="width: 100%;border: 1ex;" >
					<tr>
						<td>
							ID
						</td>
						<td>
							使用模版
						</td>
						<td>
							数量/单位
						</td>
						<td>
							规格要求
						</td>
						<td>
							物料介绍
						</td>
						<td>
							年利率
						</td>
					</tr>
						<tr >
							<td>
								${pageIndex.index+1}
							</td>
							<td>
								${zhaobiaoXi.zhmoban.name}
							</td>
							<td>
								${zhaobiaoXi.t2}/${zhaobiaoXi.t3}
							</td>
							<td>
								${zhaobiaoXi.t5}
							</td>
							<td>
								${zhaobiaoXi.t6}
								<input type="hidden" value="${zhaobiaoXi.id}" name="zhaobiaoXi.id"/>
							</td>
							<td>
		                       <input type="text" name="zhaobiaoXi.lilv"  value="${zhaobiaoXi.lilv}"/>
							</td>
						</tr>
						<tr><th colspan="6" align="center"><s:submit value="保存" cssClass="input" />
								<input type="button" name="Submit2" value="取消"  class="right-buttons" onclick="window.history.go(-1);"/>
						
							</th></tr>
						</table>
						
						</form>
						
				
		</div>
		
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
			 $(function(){
	   var errorMessage = '${errorMessage}';
		if (errorMessage != "") {
			alert(errorMessage);
			parent.location.reload(true);//刷新父页面
		}
	})
	</SCRIPT>
	</body>
</html>
