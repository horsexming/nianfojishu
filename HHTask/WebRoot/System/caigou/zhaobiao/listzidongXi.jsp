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
						<tr>
							<td>ID</td>
							<td>使用模版</td>
							<td>数量/单位</td>
							<td>规格要求</td>
							<td>物料介绍</td>
							<td>操作</td>
						</tr>
				<s:iterator value="list" id="zhaobiaoXi"  status="pageIndex">
						<tr onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')" >
							<td>${pageIndex.index+1}</td>
							<td>${zhaobiaoXi.zhmoban.name}</td>
							<td>${zhaobiaoXi.t2}/${zhaobiaoXi.t3}</td>
							<td>${zhaobiaoXi.t5}</td>
							<td>${zhaobiaoXi.t6}</td>
							<td>
							<s:if test='#zhaobiaoXi.t7=="N"'>
									<a href="zhaobiaoAction!listzidongpingxuanToubiao.action?zhaobiaoXi.id=${zhaobiaoXi.id}">自动评标</a>
							</s:if>
							
							<s:if test='#zhaobiaoXi.t7=="Y"'>
							  			 <a href="System/caigou/zhaobiao/chongxin.jsp?namee=${zhaobiaoXi.id}">重新评标</a>
							</s:if>
							</td>
						</tr>
				</s:iterator>	
				<div id="t1"></div>
				
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
	    
	</SCRIPT>
	</body>
</html>
