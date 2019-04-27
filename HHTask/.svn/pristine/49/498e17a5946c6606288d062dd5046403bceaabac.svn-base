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
		<script type="text/javascript">
			function deleteUser(id){
				if(!confirm('确定将此人的权限删除?')){
					return;
				}
				$.ajax({
					type: "POST",
					url: "WareHouseAuth!delete.action",
					data: "auth.id=" + id,
					dataType:"json",
					success: function(msg){
						alert(msg.message);
						if(msg.success){
							location.reload();
						}
					}
				});
			}
		</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			
			<div align="center">
			<form action="WareHouseAuth_list.action" method="post">
			<table class="table">
				<tr>
					<th align="right">名称:</th>
					<td>
						<input type="text" value="" name="auth.username">	
					</td>
					<th align="right">工号:</th>
					<td>
						<input type="text" value="" name="auth.usercode">	
					</td>
				</tr>
			</table>
			<input type="hidden" value="${status}" name="status"/>
			<input type="submit" value="查询" class="input"/>
		</form>
				<table width="100%" border="0" style="border-collapse: collapse;" class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							名称
						</th>
						<th align="center">
							工号
						</th>
						<th align="center">
							级别
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="auths" id="d" status="st">
						<tr>
							<s:if test="#st.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#st.index%2==0">
									<font color="red"> <s:property value="#st.index+1" /> </font>
								</s:if>
								<s:else>
									<s:property value="#st.index+1" />
								</s:else>
							</td>
							<td>
								${d.username}
							</td>
							<td>
								${d.usercode}
							</td>
							<td>
								${d.group}
							</td>
							<td>
								<a href="WareHouseAuth_editInput.action?auth.id=${d.id}&status=${status}">修改权限</a>
								<a href="javascript:void(0)" onclick="deleteUser(${d.id})">删除</a>
							</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="8" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</s:if>
						<s:else>
							<td colspan="8" align="center" style="color: red">
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
