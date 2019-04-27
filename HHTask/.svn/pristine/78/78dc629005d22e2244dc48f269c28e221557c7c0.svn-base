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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div align="center">
				<h3>设变生产外委数据</h3>
			</div>
			<div>
			<form action="procardTemplateGyAction_showWwSbList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<th>设变单号:</th>
							<td align="center">
								<input type="text" name="procardSbWw.sbNumber" value="<s:property value="procardSbWw.sbNumber"/>" />
							</td>
							<th>状态:</th>
							<td align="center">
								<SELECT name="procardSbWw.status" style="width: 172px;" >
									<s:elseif test="procardSbWw.status!=null">
									<option><s:property value="procardSbWw.status"/></option>
									</s:elseif>
									<s:else></s:else>
									<option>待处理</option>
									<option>完成</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th>件号:</th>
							<td align="center">
								<input type="text" name="procardSbWw.markId" value="<s:property value="procardSbWw.markId"/>" />
							</td>
							<th>业务件号:</th>
							<td align="center">
								<input type="text" name="procardSbWw.ywmarkId" value="<s:property value="procardSbWw.ywmarkId"/>" />
							</td>
							
						</tr>
						<tr>
						<th>版本号:</th>
							<td align="center">
								<input type="text" name="procardSbWw.banben" value="<s:property value="procardSbWw.banben"/>" />
							</td>
							<th>名称:</th>
							<td align="center">
								<input type="text" name="procardSbWw.proName" value="<s:property value="procardSbWw.proName"/>" />
							</td>
							
						</tr>
						<tr>
						<th>外委来源:</th>
							<td align="center">
								<SELECT name="procardSbWw.wwSource" style="width: 172px;" >
									<s:elseif test="procardSbWw.wwSource!=null">
									<option><s:property value="procardSbWw.wwSource"/></option>
									</s:elseif>
									<s:else></s:else>
									<option>手动外委</option>
									<option>BOM外委</option>
								</SELECT>
							</td>
						<th>外委类型:</th>
							<td align="center">
								<SELECT name="procardSbWw.wwType" style="width: 172px;" >
									<s:elseif test="procardSbWw.wwType!=null">
									<option><s:property value="procardSbWw.wwType"/></option>
									</s:elseif>
									<s:else></s:else>
									<option>包工包料</option>
									<option>工序外委</option>
								</SELECT>
							</td>
						
						</tr>
						
						<tr>
							<td align="center" colspan="4">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" />
							</td>
						</tr>
					</table>
				</form>
				
				<table class="table">
					<tr>
						<th>序号</th>
						<th>设变单号</th>
						<th>业务件号</th>
						<th>件号</th>
						<th>名称</th>
						<th>版本号</th>
						<th>工序号</th>
						<th>工序名称</th>
						<th>数量</th>
<%--						<th>处理数量</th>--%>
						<th>处理方案</th>
						<th>添加时间</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<s:iterator value="procardSbWwList" id="pageSbWw" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td>
							${pageSbWw.sbNumber}
						</td>
						<td>
							${pageSbWw.markId}
						</td>
						
						<td>
							${pageSbWw.proName}
						</td>
						<td>
							${pageSbWw.banben}
						</td>
						<td>
							${pageSbWw.processNOs}
						</td>
						<td>
							${pageSbWw.processNames}
						</td>
						<td align="right">
							${pageSbWw.clCount}
						</td>
<%--						<td align="right">--%>
<%--							${pageSbWw.clCount}--%>
<%--						</td>--%>
						<td>
							${pageSbWw.cltype}
						</td>
						<td>
							${pageSbWw.addTime}
						</td>
						<td>
							${pageSbWw.status}
						</td>
						<td>
						<s:if test="#pageSbWw.status!='关闭'">
						<input type="button" onclick="showforjudge(${pageSbWw.id})" value="判定"/>
						<input type="button" onclick="closesbcg(${pageSbWw.id})" value="关闭"/>
						</s:if>
						<input type="button" onclick="show(${pageSbWw.id})" value="明细"/>
						</td>
					</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
						
						<s:if test="successMessage!=null">
						<tr>
							<td colspan="11" align="center" style="color: red">
								${successMessage}
								
						</td>
					</tr>
                          </s:if>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function showforjudge(id){
	window.location.href="ProcardTemplateAction!wwSbforJudeg.action";
}
</SCRIPT>
	</body>
</html>
