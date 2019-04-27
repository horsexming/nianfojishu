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
		<div id="gongneng" >
			<div align="center">
				<h3>
					E-CAR待处理展示
				</h3>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
						提出日期
						</td>
						<td align="center">
						E-CER编号
						</td>
						<td align="center">
						产品编码
						</td>						
						<td align="center">
						问题点
						</td>						
						<td align="center">
						提交人
						</td>						
						<td align="center">
						所属
						</td>						
						<td align="center">
						目前状态
						</td>						
						<td align="center">
						关闭时间
						</td>						
						<td align="center">
						时效性
						</td>						
						<td align="center" colspan="2">操作</td>
					</tr>
					<tr bgcolor="red">
					<td colspan="12" align="center">待创建人确认</td>
					</tr>
					<s:iterator value="list" id="pagedqrecar" status="dqrecarStatus">
						<s:if test="#dqrecarStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#dqrecarStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#dqrecarStatus.index+1" />
							</font>
						</td>
						<td>
							${pagedqrecar.applyDate}
						</td>
						<td>
							${pagedqrecar.ecarNumber}
						</td>
						<td>
							${pagedqrecar.ywMarkId}
						</td>
						<td>
							${pagedqrecar.problempoint}
						</td>
						<td>
							${pagedqrecar.tjUserName}
						</td>
						<td>
							${pagedqrecar.belongto}
						</td>
						<td>
							${pagedqrecar.zxstatus}
						</td>
						<td>
							${pagedqrecar.endTime}
						</td>
						<td>
							${pagedqrecar.sxx}
						</td>
						<td  colspan="2">
							<s:if test="#pagedqrecar.files.size()>0">
								<a href="procardTemplateGyAction_ecarfileshow.action?id=${pagedqrecar.id}">文件查看</a>
							</s:if>
							<s:else>
								<a href="procardTemplateGyAction_ecarfileshow.action?id=${pagedqrecar.id}">无文件</a>
							</s:else>
						</td>
					</tr>
					</s:iterator>
					
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function update(id) {
	window.location.href = "procardTemplateGyAction_toecarupdate.action?cpage=${cpage}&id=" + id;
}
function todelete(id) {
	if(confirm("确定要删除此记录？")){
		window.location.href = "procardTemplateGyAction_toecardelete.action?cpage=${cpage}&id=" + id;
	}
}
</script>
	</body>
</html>
