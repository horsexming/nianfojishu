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
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
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
				
						<table class="table">
							<tr bgcolor="#c0dcf2" height="30px"
								style="border-collapse: separate;">
								<th align="center">
									序号
								</th>
								<th align="center">
									外币编号
								</th>
								<th align="center">
									币名
								</th>
								<th align="center">
									代表符
								</th>
								<th align="center">
									标价方法
								</th>
								<th align="center">
									汇率方式
								</th>
								<th align="center">
									精度
								</th>
								<th align="center">
									折算误差
								</th>
								<th align="center">
									是否本位币
								</th>
								
							</tr>

							<s:if test="{list.size()>0}">
								<s:iterator value="list" status="se" id="wbzd">
									<s:if test="#se.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
									<td>
										<s:property value="#se.index+1" />
									</td>
									<td>
										${wbzd.WBBH}
									</td>
									<td>
										${wbzd.BM}
									</td>
									<td>
										${wbzd.DBF}
									</td>
									<td>
									<s:if test="%{#wbzd.DBF==1}">
									直接标价法
									</s:if>
									<s:else>
									间接标价法
									</s:else>	
									</td>
									<td>
										<s:if test="%{#wbzd.HLFS==1}">
									固定汇率
									</s:if>
									<s:else>
									现行汇率
									</s:else>	
									</td>
									<td>
										${wbzd.JD}
									</td>
									<td>
										${wbzd.ZSWC}
									</td>
									<td>
										<s:if test="%{#wbzd.BWB==1}">
									是
									</s:if>
									<s:else>
									否
									</s:else>	
									</td>
									
									</tr>
								</s:iterator>
								<tr>
									<td colspan="9" align="right">
										共
										<s:property value="total" />
										页 第
										<s:property value="cpage" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />

									</td>
								</tr>
							</s:if>
							<s:else>
								<tr>
									<td colspan="10" style="font-size: 15px; color: red;">
										对不起，没有查到相关的标识贴信息
									</td>
								</tr>
							</s:else>
						</table>
						</div>
						<br>
						</div>
						<%@include file="/util/foot.jsp"%>
						</center>
						<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
						<script type="text/javascript">
function exportExcel(objForm) {
	objForm.action = "BaoXiaoDanAction!exportEXCEL.action?tag=bxd";
	objForm.submit();
}
</script>
	</body>
</html>
