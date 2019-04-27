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
					
				</div>
			</div>
			
			<div align="center">
				<table class="table" style="text-align: center;">
					<tr  bgcolor="#c0dcf2" height="50px">
						<td align="center">序号1</td>
						<td align="center">工艺规程编号</td>
						<td align="center">件号</td>
						<td align="center">件名</td>
						<td align="center">编制</td>
						<td align="center">较对</td>
						<td align="center">审核</td>
						<td align="center">批准</td>
						<td align="center">发出日</td>
						<td align="center">状态</td>
						<td align="center">操作</td>
					</tr>
					<s:iterator id="g" value="gongyiGuichengListForDsh" status="st">
						<s:if test="#st.first">
						<tr bgcolor="green">
							<th colspan="11" align="center">
								<font color="#ffffff"> 待审核记录</font>
							</th>
						</tr>
						</s:if>
						<s:if test="#st.index%2==1" >
						<tr  align="center" bgcolor="#e6f3fb" onmouseover="chageBgcolor(this);" onmouseout="outBgcolor(this,'');">	
						</s:if>
						<s:else>
						<tr align="center" onmouseover="chageBgcolor(this);" onmouseout="outBgcolor(this,'');">
						</s:else>
						
							<td>${st.index + 1}</td>
							<td>${numb}</td>
							<td>${jianNumb}</td>
							<td>${jianName}</td>
							<td>${bianzhiName}</td>
							<td>${jiaoduiName}</td>
							<td>${shenheName}</td>
							<td>${pizhunName}</td>
							<td><s:date name="fachuDate" format="yyyy-MM-dd"/></td>
							<td>${status}</td>
							<td>
								<a target="_detail" href="gongyiGuichengAction!getGongyiGuichengDetail.action?gongyiGuicheng.id=${g.id}">查看详细</a>
							</td>
						</tr>
					</s:iterator>
					<s:iterator id="g" value="gongyiGuichengListForYsh" status="st">
						<s:if test="#st.first">
						<tr bgcolor="green">
							<th colspan="11" align="center">
								<font color="#ffffff"> 已审核记录</font>
							</th>
						</tr>
						</s:if>
						<s:if test="#st.index%2==1" >
						<tr  align="center" bgcolor="#e6f3fb" onmouseover="chageBgcolor(this);" onmouseout="outBgcolor(this,'');">	
						</s:if>
						<s:else>
						<tr align="center" onmouseover="chageBgcolor(this);" onmouseout="outBgcolor(this,'');">
						</s:else>
						
							<td>${st.index + 1}</td>
							<td>${numb}</td>
							<td>${jianNumb}</td>
							<td>${jianName}</td>
							<td>${bianzhiName}</td>
							<td>${jiaoduiName}</td>
							<td>${shenheName}</td>
							<td>${pizhunName}</td>
							<td><s:date name="fachuDate" format="yyyy-MM-dd"/></td>
							<td>${status}</td>
							<td>
								<a target="_detail" href="gongyiGuichengAction!getGongyiGuichengDetail.action?gongyiGuicheng.id=${g.id}">查看详细</a>
							</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
			<br/>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
