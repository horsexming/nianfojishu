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
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">

			<div align="center">
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							检验次数
						</th>
						<th>
							需检项
						</th>
						<th>
							质量特征
						</th>
						<th>
							检查方法
						</th>
						<th>
							检查结果
						</th>

					</tr>
					<s:iterator id="s" value="ss" status="st">
						<tr>
							<td>
								${st.index+1 }
							</td>
							<th>
								${s.jyCount }
							</th>
							<td>
								${s.scope.content}
							</td>
							<td>
								${s.scope.zltz}
							</td>
							<td>
								${s.scope.jcff}
							</td>
							<td>
								${s.content}
							</td>

						</tr>

					</s:iterator>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
