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
					<a href="zhaobiaoAction!listAll.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="d1">
					  <form action="caoZuoAction!listCaoZuoHuiZong.action" method="post"  theme="simple">
			     <table class="table">
			   		<tr><th align="center">工位编号：</th><td><input type="text" id="zhCaozuo.gongweiId" name="zhCaozuo.gongweiId" /></td>
			   			<th align="center">设备编号：</th><td>
			   			    <input type="text" id="zhCaozuo.shebeiId" name="zhCaozuo.shebeiId" />
			   			
			   			<td rowspan="3"><input type="submit" value="查询"   class="input"/>
			   						<input type="button" value="添加" class="input" onclick="f1();" />
			   			</td>
			   		</tr>
			   		<tr>
			   			<th align="center">工号</th><td>
			   			    <input type="text"  name="zhCaozuo.nameId" id="zhCaozuo.nameId" />
			   			</td>
			   			<th align="center">姓名</th><td>
			   				 <input type="text" id="zhCaozuo.name" name="zhCaozuo.name" />
			   			</td>
			   		</tr>
			  <tr>
			   			<th align="center">设备名称</th><td>
			   			    <input type="text"  name="zhCaozuo.shebeiname" id="zhCaozuo.shebeiname" />
			   			</td>
			   			<th align="center">工序名称</th><td>
			   				 <input type="text" id="zhCaozuo.shebeigongxuName" name="zhCaozuo.shebeigongxuName" />
			   			</td>
			   			<td></td>
			   		</tr>
			  </table>
			  </form>
				<table class="table">
					<tr  bgcolor="#c0dcf2">
						<th>序号</th>
						<th>工号</th>
						<th>名称</th>
						
						<th>工位编号</th>
						<th>设备编号</th>
						<th>设备名称</th>
						<th>设备工序名称</th>
						
						
						
						<th>设备操作</th>
					</tr>
					<s:iterator value="list" id="zhUser1" status="pageIndex">
						<tr onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
							<th>${pageIndex.index+1}</th>
							<th>${zhUser1.nameId}</th>
							<th>${zhUser1.name}</th>
							
							<th>${zhUser1.gongweiId}</th>
							<th>${zhUser1.shebeiId}</th>
							<th>${zhUser1.shebeiname}</th>
							<th>${zhUser1.shebeigongxuName}</th>
							
							
							<th>${zhUser1.caozuodengji}</th>
							
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

	</body>
	<SCRIPT type="text/javascript">
 function f1(){
	    window.location.href="<%=basePath%>/System/renshi/caozuonengli/shebeiAndEmp_add.jsp";
	}
	</SCRIPT>

</html>
