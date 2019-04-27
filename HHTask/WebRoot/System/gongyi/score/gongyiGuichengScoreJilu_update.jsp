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
		<style type="text/css">
			body{
				font-family:宋体;
			}
			.table{
				border:0px solid #999;
				width: 756px;	
				border-collapse:collapse;
			}
			.table th,.table td {
				height: 24px;
			}
		</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					
				</div>
			</div>
			
			<form action="gongyiGuichengScoreAction!updateGongyiGuichengScoreJilu.action" method="post" style="">
			<input type="hidden" name="gongyiGuichengScoreJilu.id" value="${gongyiGuichengScoreJilu.id}"/>
			<input type="hidden" name="gongyiGuichengScoreJilu.parentId" value="${gongyiGuichengScoreJilu.parentId}"/>
			<div align="center">
					<table class="table" >
						<tr><td style="">打分记录名称</td><td><input name="gongyiGuichengScoreJilu.name" value="${ gongyiGuichengScoreJilu.name}"/></td><td style="">打分记录标识</td><td><input name="gongyiGuichengScoreJilu.biaoshi" value="${ gongyiGuichengScoreJilu.biaoshi}"/></td><td style="">打分类别排序号</td><td><input name="gongyiGuichengScoreJilu.orderNumb" value="${ gongyiGuichengScoreJilu.orderNumb}"/></td></tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" value="提交" style="width: 100px; height: 50px;" />
								<input type="reset" value="重置" style="width: 100px; height: 50px;" />
							</td>
						</tr>
					</table>
			</div>
			</form>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
