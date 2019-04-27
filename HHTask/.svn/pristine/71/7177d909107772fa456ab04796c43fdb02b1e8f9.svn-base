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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在对机密类型进行操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
						
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<STRONG><font size="4">机密类型管理 </font> </STRONG>
   				<form action="jimileixing_addJiMi.action" method="POST" onsubmit="return check()">
   					<input type="text" name="jimileixing.type" id="type"/>
   					<input type="submit"  value="添加" id="submit" />
   				</form>
   				<br/>
   				<br/>
   				<table class="table">
   					<tr width="80%" bgcolor="#e6f3fb">
   						<td align="center">
   							<strong>序号</strong>
   						</td>
   						<td align="center">
   							<strong>机密等级</strong>
   						</td>
   						<td align="center">
   							<strong>操作</strong>
   						</td>
   					</tr>
   					<s:iterator id="jimiList" value="JiMiList" status="statussdf">
   						<s:if test="#statussdf.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')" width="80%">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')" width="80%">
					</s:else>
					<td>
						<s:property value="#statussdf.index+1" />
					</td>
					<td>
						${jimiList.type}
					</td>
					<td>
						<a id="xiugai${jimiList.id}" onclick="tanchu(${jimiList.id})">修改</a>/
						<a onclick="return confirm('确定要删除吗?') " href='jimileixing_delJiMi.action?jimileixing.id=${jimiList.id}'>删除</a>/
						<a   href="jimileixing_findMfById.action?id=${jimiList.id}">绑定人员</a>
					</td>
					</tr>
   				 </s:iterator>
   				  <tr>
				<td colspan="3" align="right">
								第
					<font color="red"><s:property value="cpage" /> </font> /
						<s:property value="total" />
							页
						<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
									
				</td>
			</tr>
   				</table>
   			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function tanchu(num,obj){
	if(obj=='绑定'){
		document.getElementById("xiugaiIframe").src="jimileixing_findMfById.action?id="+num;
	}else{
		document.getElementById("xiugaiIframe").src="jimileixing_showJiMiListByid.action?jimileixing.id="+num;
	}
	
		chageDiv('block')
}	
function check(){
	var type=document.getElementById("type");
	if(type!=null&&type.value==""){
		alert("请填写机密等级");
		return false;
	}
	document.getElementById("submit").disabled="disabled";
	return true;
}
		</script>
	</body>
</html>
