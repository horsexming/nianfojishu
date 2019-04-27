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
	<body onload="getDept('dept');">
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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
			
					<table class="table" style="width: 100%">
						<tr  bgcolor="#c0dcf2">
							<th>ID</th>
							<th>投标公司</th>
							
							<th>到货时间</th>
							<th>联系方式</th>
							<th>负责人</th>
							
							<th>备注</th>
							<th>操作</th>
						</tr  >
				<s:iterator value="list" id="zhtoubiao" status="pageIndex">
						<tr  align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
							<td >${pageIndex.index+1}</td>
							<td>${zhtoubiao.tname}</td>
							
							
							<td>${zhtoubiao.ttime}</td>
							<td>${zhtoubiao.tkong4}</td>
							<td>${zhtoubiao.tkong5}</td>
							<td>${zhtoubiao.tshuliang} <input type="hidden" id="tkong10" value="${zhtoubiao.tkong10}"/></td>
							<td>
								<a href="zhaobiaoAction!chakanjine.action?zhtoubiao.tid=${zhtoubiao.tid}">查看投标金额</a>
								<a href="zhaobiaoAction!pingxuanzhongbiao.action?zhtoubiao.tid=${zhtoubiao.tid}">选为供应商</a>
								</td>
						</tr>
				</s:iterator>	
				
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
							<th colspan="11" align="center" style="color: red">
						</s:else>
						</th>
					</tr>	
					</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function paixujiage(){
			alert("aaa");
		   var tkong10=document.getElementById("tkong10").value;
		   window.location.href="zhaobiaoAction!paixujiage.action?zhtoubiao.tkong10="+tkong10;
		}
	
	</SCRIPT>
	</body>
</html>