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
		<div id="gongneng">
			<div align="center">
				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							零件名称
						</th>
						<th align="center">
							版本
						</th>
						<th align="center">
							供料属性
						</th>
						<th align="center">
							数量
						</th>
						<th align="center">
							检验批次
						</th>
						<th align="center">
							检验人
						</th>
						<th align="center">
							检验时间
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="list" status="st" id="tt">
						<s:if test="#tt.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<th>
							${st.index +1 }
						</th>
						<td>
							${tt.markId}
						</td>
						<td>
							${tt.proName}
						</td>
						<td>
							${tt.banbenNumber}
						</td>
						<td>
							${tt.kgliao}
						</td>
						<td>
							${tt.hgNumber}(${tt.unit})
						</td>
						<td>
							${tt.jcpc}
						</td>
						<td>
							${tt.username}
						</td>
						<td>
							${tt.nowDate}
						</td>
						<td>
							<a href="OsRecord_rukuInput.action?record.id=${tt.id}">入库</a>
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
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
