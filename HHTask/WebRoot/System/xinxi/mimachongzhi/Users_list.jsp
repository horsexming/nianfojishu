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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				<form action="caoZuoAction!listUsers.action" method="post"
					theme="simple">
					<table class="table">
						<tr>
							<td>
								工号:
							</td>
							<td>
								<input type="text" id="userss.code" name="userss.code" />
							</td>
							<td>
								卡号:
							</td>
							<td>
								<input type="text" id="userss.cardId" name="userss.cardId" />
							</td>
							<Td rowspan="2">
								<input type="submit" value="查询" class="input" />
							</Td>
						</tr>
						<tr>
							<td>
								姓名:
							</td>
							<td>
								<input type="text" id="userss.name" name="userss.name" />
							</td>
							<td>
								部门:
							</td>
							<td>
								<select id='dept' name="userss.dept"
									onclick="createDept('dept')" style="width: 90px;">
								</select>
							</td>
						</tr>
					</table>
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								序号

							</td>
							<td align="center">
								工号
							</td>
							<td align="center">
								卡号
							</td>
							<td align="center">
								姓名
							</td>
							<td align="center">
								部门
							</td>
							<td>
								职位
							</td>
							<td>
								状态
							</td>
							<td>
								操作
							</td>
						</tr>
						<s:iterator value="list" id="zhaobiao1" status="pageIndex">
							<tr onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
								<td>
									${pageIndex.index+1}
								</td>
								<td>
									${zhaobiao1.code}
								</td>
								<td>
									${zhaobiao1.cardId}
								</td>
								<td>
									${zhaobiao1.name}
								</td>
								<td>
									${zhaobiao1.dept}
								</td>

								<td>
									${zhaobiao1.post}
								</td>
								<td>
									${zhaobiao1.onWork}
								</td>
								<td>
									<a
										href="caoZuoAction!chongzhi.action?userss.id=${zhaobiao1.id}"
										onclick="return confirm('确定要重置吗？')">重置</a>
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
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
			function update(id){
				window.location="internalOrder_initProduct.action?id="+id+"&customeId="+${id};
			}
		</SCRIPT>
	</body>
</html>
