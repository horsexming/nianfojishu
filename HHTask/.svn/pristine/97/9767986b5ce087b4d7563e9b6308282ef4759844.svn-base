<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<center>
			<table class="table">
				<tr>
					<th>序号</th>
					<th>预算名称</th>
					<th>年度</th>
					<th>部门</th>
					<th>总金额(元)</th>
					<th>剩余金额(元)</th>
					
					
					<th>操作</th>
				</tr>
				<s:iterator id="pageyusuantianbaobiao" value="list" status="statussdf">
							<s:if test="#statussdf.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#statussdf.index+1" />
							</td>
							<td>
								${pageyusuantianbaobiao.xiangmumingda}
							</td>
							<td>
								${pageyusuantianbaobiao.niandu}
							</td>
							<td>
								${pageyusuantianbaobiao.bumen}
							</td>
								<td>
								${pageyusuantianbaobiao.zongshu}
							</td>
								<td>
								${pageyusuantianbaobiao.shengyu}
							</td>
							
							<td>
								<input type="hidden" name="allow" value="${allow}"/>
								<a href="YusuantianbaobiaoAction!getAllListSimple.action?id=${pageyusuantianbaobiao.id}">查看预算明细</a>		
								 	<s:if test="allow!='同意'">																				
								<a href="YusuantianbaobiaoAction!addmingxi.action?id=${pageyusuantianbaobiao.id}">添加预算明细</a>
								</s:if>
								
								 
<!--								<a href="YusuantianbaobiaoAction!findZUpdate.action?id=${pageyusuantianbaobiao.id}">修改</a>-->
								 
<!--									<a href="YusuantianbaobiaoAction!deleteYusuantianbaototal.action?id=${pageyusuantianbaobiao.id}"-->
<!--								onclick="return window.confirm('是否确认删除?')">删除</a> -->
							
<!--								<a href="YusuantianbaobiaoAction!tijiaoYusuantianbaototal.action?id=${pageyusuantianbaobiao.id}"-->
<!--								onclick="return window.confirm('是否确认提交审批?')">提交</a>-->
								 
							
								
							</td>
							</tr>				
							
				</s:iterator>
						<tr>
							<td colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</tr>
							
			</table>
		</center>
		<script type="text/javascript">
</script>
	</body>
</html>