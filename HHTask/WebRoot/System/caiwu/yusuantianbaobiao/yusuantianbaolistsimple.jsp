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
			<s:if test="id!=null&&id>0">
				<table>
					<tr>
						<th align="center">
							<span style="color: red">${yusuantianbaototal.xiangmumingda}</span>
							预算明细
							<br />
							本年度已通过审批总计
							<span style="color: red">${yusuantianbaototal.zongshu}</span>元，
							剩余
							<span style="color: red">${yusuantianbaototal.shengyu}</span>元。
						</th>
					</tr>
				</table>
			</s:if>
			<table class="table">
				<tr>
					<td align="center" colspan="11">
						<span style="color: red"> </span> 预算明细
					</td>
				</tr>
				<tr>
					<th>
						序号
					</th>
					<th>
						项目名称或者代码
					</th>
					<th>
						预算总金额(元)
					</th>
					<th>
						预算剩余金额(元)
					</th>
					<th>
						收益金额(元)
					</th>
					<th>
						收益年限
					</th>
					<th>
						年度
					</th>
					<th>
						部门
					</th>
					<th>
						报告人
					</th>
					<th>
						状态
					</th>
					<th>
						操作
					</th>
				</tr>
				<s:iterator id="pageyusuantianbaobiao" value="list"
					status="statussdf">
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
						${pageyusuantianbaobiao.zongjine}
					</td>
					<td>
						${pageyusuantianbaobiao.shengyu}
					</td>
					<td>
						${pageyusuantianbaobiao.shouyijine}
					</td>
					<td>
						${pageyusuantianbaobiao.shouyinianxian}
					</td>
					<td>
						${pageyusuantianbaobiao.niandu}
					</td>
					<td>
						${pageyusuantianbaobiao.bumen}
					</td>
					<td>
						${pageyusuantianbaobiao.name}
					</td>
					<td>
						${pageyusuantianbaobiao.shenpi}
					</td>
					<td>
						<s:if test="#pageyusuantianbaobiao.shenpi!='同意'">
							<a
								href="YusuantianbaobiaoAction!findUpdate.action?id=${pageyusuantianbaobiao.id}&statu=hz">修改</a>
							<a
								href="YusuantianbaobiaoAction!deleteYusuantianbaobiao.action?id=${pageyusuantianbaobiao.id}&&yusuantianbaototal.id=${yusuantianbaototal.id}"
								onclick="return window.confirm('是否确认删除?')">删除</a>
						</s:if>
						<a
							href="YusuantianbaobiaoAction!findYusuantianbaobiao.action?id=${pageyusuantianbaobiao.id}">详细</a>
						<a
							href="CircuitRunAction_findAduitPage.action?id=${pageyusuantianbaobiao.epid}">审批动态</a>
					</td>


				</s:iterator>

				<!--			-->
				<!--					<tr>-->
				<!--							<td></td><th>总计:</th><td></td>   -->
				<!---->
				<!--						<td colspan="11" align="right">-->
				<!--							<input type="button"-->
				<!--								onclick="window.location.href=-->
				<!--								'YusuantianbaobiaoAction!addmingxi.action?id=${yusuantianbaototal.id}'"-->
				<!--								value="添加">-->
				<!--						</td>-->
				<!--					</tr>-->


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