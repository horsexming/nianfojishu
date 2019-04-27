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
		<div align="center">
			<form action="markIdAction!listtianxiejiepai.action" method="post"
				theme="simple">
				<table class="table" style="width: 100%; border: 1ex;">
					<tr bgcolor="#c0dcf2">
						<th align="center">
							ID
						</th>
						<th align="center">
							名称
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							供货厂家
						</th>
						<th align="center">
							类型
						</th>
						<th align="center">
							是否完成
						</th>
						<th align="center">
							产能
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="list" id="zhUser1" status="pageIndex">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
							<td>
								${pageIndex.index+1}
							</td>
							<td>
								${zhUser1.proName}
							</td>
							<td>
								${zhUser1.markId}
							</td>
							<td>
								${zhUser1.gys}
							</td>
							<td>
								${zhUser1.procardStyle}
							</td>
							<td>
							
								<s:if test='#zhUser1.waiweistatus=="外委"'>
								     请看工序详细
								</s:if>
								<s:else>
									<s:if test='#zhUser1.capacity==null'>
									<font color="red">未完成</font>
								</s:if>
								<s:else>
								   已完成
								</s:else>
								</s:else>
							</td>
							<td>
								${zhUser1.capacity}
							</td>
							<td>
								<s:if test='#zhUser1.waiweistatus=="外委"'>
									<a
										href="markIdAction!listWaiweiGongxu.action?gysMarkIdjiepai.id=${zhUser1.id}">填写工序</a>
								</s:if>
								<s:else>
									<a
										href="<%=basePath%>System/caigou/zhaobiao/bangding/Template_findProcard.jsp?id=${zhUser1.rootId}">明细(Details)</a>
								</s:else>
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

			</form>


		</div>

		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
	function toUpdatezhaobiao(id){
			var url=encodeURI(encodeURI("zhaobiaoAction!tojiepai.action?gysjiepai.id="+id));
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
		function chakan(id){
			var url=encodeURI(encodeURI("zhaobiaoAction!chakan.action?gysjiepai.id="+id));
		$("#showProcess").attr("src", url);	
		chageDiv('block');
		
	}
	</SCRIPT>
	</body>
</html>
