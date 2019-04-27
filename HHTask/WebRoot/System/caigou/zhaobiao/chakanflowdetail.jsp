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
					<a href="zhaobiaoAction!listZhmoban.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="d1">
				<table class="table">
					<tr>
						<th>id</th>
						<th>标题</th>
						<th>申请类型</th>
						<th>审批状态</th>
						
						<th>审批人</th>
						<th>审批意见</th>
						<th>审批时间</th>
					</tr>
					<s:iterator value="list" id="flowdetail"  status="pageIndex">
						<tr>
							<th>${pageIndex.index+1}</th>
							<th>${flowdetail.title}</th>
							<th>${flowdetail.sqtype}</th>
							<th><s:if test='#flowdetail.sphj=="N"'>未通过</s:if>
							    <s:if test='#flowdetail.sphj=="Y"'>通过</s:if>
							
							</th>
							
							<th>${flowdetail.spr}</th>
							<th>${flowdetail.spyj}</th>
							<th>${flowdetail.sprq}</th>
						</tr>
					</s:iterator>
					<tr><td colspan="7" align="center"><input type="button" name="Submit2" value="返回"  class="right-buttons" onclick="window.history.go(-1);"/></td></tr>
					<tr>
						<s:if test="errorMessage==null">
							<th colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<th colspan="11" align="center" style="color: red" >
						</s:else>
						</th>
					</tr>

				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
</script>
	</body>
</html>
