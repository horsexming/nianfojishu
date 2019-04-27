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
					pcn待处理展示
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
						提出人
						</td>
						<td align="center">
						PCN编号
						</td>
						<td align="center">
						产品编码
						</td>						
						<td align="center">
						所属
						</td>						
						<td align="center">
						PCN主题
						</td>						
						<td align="center">
						提交客户日期
						</td>						
						<td align="center">
						客户反馈日期
						</td>						
						<td align="center">
						客户反馈结果
						</td>						
						<td align="center">
						内部执行情况
						</td>						
						<td align="center">
						是否结案
						</td>						
						<td align="center" colspan="2">操作</td>
					</tr>
					<tr bgcolor="red">
						<td colspan="13" align="center">未结案</td>
					</tr>
					<s:iterator value="list" id="pagewjapcn" status="wjapcnStatus">
						<s:if test="#wjapcnStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#wjapcnStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#wjapcnStatus.index+1" />
							</font>
						</td>
						<td>
							${pagewjapcn.applyDate}
						</td>
						<td>
							${pagewjapcn.applyUserName}
						</td>
						<td>
							${pagewjapcn.pcnNumber}
						</td>
						<td>
							${pagewjapcn.ywMarkId}
						</td>
						<td>
							${pagewjapcn.belongto}
						</td>
						<td>
							${pagewjapcn.pcntheme}
						</td>
						<td>
							${pagewjapcn.submitDate}
						</td>
						<td>
							${pagewjapcn.feedbackDate}
						</td>
						<td>
							${pagewjapcn.feedbackoption}
						</td>
						<td>
							${pagewjapcn.zxStatus}
						</td>
						<td>
							${pagewjapcn.isend}
						</td>
						<td  colspan="2">
							<s:if test="#pagewjapcn.files.size()>0">
								<a href="procardTemplateGyAction_pcnfileshow.action?id=${pagewjapcn.id}">文件查看</a>
							</s:if>
							<s:else>
								<a href="procardTemplateGyAction_pcnfileshow.action?id=${pagewjapcn.id}">无文件</a>
							</s:else>
						</td>
					</tr>
					</s:iterator>
					<tr bgcolor="yellow">
						<td colspan="13" align="center">客户拒绝</td>
					</tr>
					<s:iterator value="list2" id="pagekhjjpcn" status="khjjpcnStatus">
						<s:if test="#khjjpcnStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#khjjpcnStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#khjjpcnStatus.index+1" />
							</font>
						</td>
						<td>
							${pagekhjjpcn.applyDate}
						</td>
						<td>
							${pagekhjjpcn.applyUserName}
						</td>
						<td>
							${pagekhjjpcn.pcnNumber}
						</td>
						<td>
							${pagekhjjpcn.ywMarkId}
						</td>
						<td>
							${pagekhjjpcn.belongto}
						</td>
						<td>
							${pagekhjjpcn.pcntheme}
						</td>
						<td>
							${pagekhjjpcn.submitDate}
						</td>
						<td>
							${pagekhjjpcn.feedbackDate}
						</td>
						<td>
							${pagekhjjpcn.feedbackoption}
						</td>
						<td>
							${pagekhjjpcn.zxStatus}
						</td>
						<td>
							${pagekhjjpcn.isend}
						</td>
						<td  colspan="2">
							<s:if test="#pagekhjjpcn.files.size()>0">
								<a href="procardTemplateGyAction_pcnfileshow.action?id=${pagekhjjpcn.id}">文件查看</a>
							</s:if>
							<s:else>
								<a href="procardTemplateGyAction_pcnfileshow.action?id=${pagekhjjpcn.id}">无文件</a>
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
	window.location.href = "procardTemplateGyAction_topcnupdate.action?cpage=${cpage}&id=" + id;
}
function todelete(id) {
	if(confirm("确定要删除此记录？")){
		window.location.href = "procardTemplateGyAction_topcndelete.action?cpage=${cpage}&id=" + id;
	}
}
</script>
	</body>
</html>
