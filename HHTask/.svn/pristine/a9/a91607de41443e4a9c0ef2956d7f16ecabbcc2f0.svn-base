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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h1>
					${processShiZhi.processName}
				</h1>
			</div>
			<s:if test="successMessage!=null">
			 <div align="center">
					<h5><font color="red">${successMessage}</font></h5>
			</div>
			</s:if>
			<s:if test="errorMessage!=null">
			 <div align="center">
					<h5><font color="red">${errorMessage}</font></h5>
			</div>
			</s:if>
			<div align="center">
					<input type="hidden" name="skillType.id" value="${processShiZhi.id}">
					<table style="width: 980px; border-collapse: collapse;" border="0"
						align="center">
						<tr align="center" bgcolor="#c0dcf2"
							style="height: 40px; font-weight: bold;">
							<td align="center">
								序号
								<br />
								(num)
							</td>
							<td align="center">
								名称
								<br />
								(name)
							</td>
							<td align="center">
								绑定
								<br />
								(link)
							</td>
						</tr>
								<tr bgcolor="green">
									<td colspan="7" style="font-family: 微软雅黑; font-weight: bold;"
										align="center">
										<font color="#ffffff"> 已绑定加工难点系数</font>
									</td>
								</tr>
							<s:if test="ppd!=null">
								<tr>
							<td align="center">0
							</td>
							<td align="center"><s:property value="ppd.name"/>
							</td>
							<td align="center"><input type="button" value="取消绑定" onclick="link('${processName}',${ppd.id},'no')">
							</td>
							</tr>
								</s:if>
							<s:else>
							<tr>
							 <td colspan="3" align="center"><font color="red"> 还未绑定工艺复杂系数</font>
							 </td>
							</tr>
							</s:else>
								<tr bgcolor="green">
									<td colspan="7" style="font-family: 微软雅黑; font-weight: bold;"
										align="center">
										<font color="#ffffff"> 未绑定的工艺复杂系数</font>
									</td>
								</tr>
						<s:iterator id="ppdPage" value="list" status="ststusfunction">
							<s:if test="#ststusfunction.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#ststusfunction.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#ststusfunction.index+1" />
								</font>
							</td>
							<td align="center"><s:property value="#ppdPage.name"/>
							</td>
							<td align="center">
							<input type="button" value="绑定" onclick="link('${processName}',${ppdPage.id},'yes')">
							</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="7" align="center">
								<font color="red">${errorMessage}</font>
							</td>
						</tr>
					</table>


			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		function link(processName,ccId,linkStatus){
			var isLink="";
			if(linkStatus=='yes'){
				isLink="您将绑定该加工难点系数是否继续？";
			}else{
				isLink="您将取消绑定该加工难点系数是否继续？";
			}
			if(processName!=null&&processName!=""){
		//对中文进行加密
			processName = encodeURI(encodeURI(processName));
		
	}
			if(window.confirm(isLink)){
				window.location.href="processShiZhiAction_linkppd.action?processShiZhi.processName="+processName+"&cc.id="+ccId;
			}
		}
		</script>
	</body>
</html>
