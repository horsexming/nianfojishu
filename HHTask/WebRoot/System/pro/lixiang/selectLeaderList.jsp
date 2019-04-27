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
</script>
	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<form action="QuotedPrice_findQPByCondition.action" method="post">
					<input name="pageStatus" value="${pageStatus}" type="hidden">
					<table class="table">
						<tr>
							<th colspan="6">
								<h2>
									项目组长投选
								</h2>
							</th>
						</tr>
						<tr>
							<th align="right">
								项目:
							</th>
							<th align="left">
								<select name="quotedPrice.proId" id="proId"
									style="width: 155px;">

								</select>
							</th>
							<th align="right">
								询价单号:
							</th>
							<th align="left">
								<input name="quotedPrice.quotedNumber"
									value="${quotedPrice.quotedNumber}" />
							</th>
						</tr>
						<tr>
							<th align="right">
								件号:
							</th>
							<th align="left">
								<input name="quotedPrice.markId" value="${quotedPrice.markId}" />
							</th>
							<th align="right">
								名称:
							</th>
							<th align="left">
								<input name="quotedPrice.proName" value="${quotedPrice.proName}" />
							</th>
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" value="查询" class="input" />
								<input type="reset" value="重置" class="input" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div align="center">
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							询价单号
						</th>
						<th align="center">
							产品生命周期
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							名称
						</th>
						<th align="center">
							类型
						</th>
						<th align="center">
							数量
						</th>
						<th align="center">
							流程状态
						</th>
						<th align="center">
							当前组长
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:if test="pageStatus!='all'">
						<tr bgcolor="red" height="40px">
							<th colspan="11" style="color: #ffffff;">
								待录入报价信息
							</th>
						</tr>
						<s:iterator value="list" id="pageNeedQpri" status="pageStatus">
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
								<s:property value="#pageStatus.index+1" />
							</td>
							<td align="center">
								${pageNeedQpri.quotedNumber}
							</td>
							<td align="center">
								${pageNeedQpri.procardLifeCycle}年
							</td>
							<td align="center">
								${pageNeedQpri.markId}
							</td>
							<td align="center">
								${pageNeedQpri.proName}
							</td>
							<td align="center">
								${pageNeedQpri.procardStyle}
							</td>
							<td align="center">
								${pageNeedQpri.filnalCount}
							</td>
							<td align="center">
								${pageNeedQpri.status}
							</td>
							<td align="center">
								${pageNeedQpri.zuzhang}
							</td>
							<td align="center">
							<s:if test="#pageNeedQpri.zuzhang==null||#pageNeedQpri.zuzhang==''">
							 <a href="QuotedPrice_findQpPartner.action?id=${pageNeedQpri.id}">组长推选</a>/
							</s:if>
							</td>
							</tr>
						</s:iterator>
						<tr bgcolor="green" height="40px">
							<th colspan="11" style="color: #ffffff;">
								报价历史
							</th>
						</tr>
					</s:if>
					<s:iterator value="quotedPriceList" id="pageQpri"
						status="pageStatus">
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
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${pageQpri.quotedNumber}
						</td>
						<td align="center">
							${pageQpri.procardLifeCycle}年
						</td>
						<td align="center">
							${pageQpri.markId}
						</td>
						<td align="center">
							${pageQpri.proName}
						</td>
						<td align="center">
							${pageQpri.procardStyle}
						</td>
						<td align="center">
							${pageQpri.filnalCount}
						</td>
						<td align="center">
							${pageQpri.status}
						</td>
						<td align="center">
							<s:if test="pageStatus=='all'">
								<s:if test='#pageQpri.status=="项目启动中"'>
									<a
										href="QuotedPrice_findQuotedPrice.action?id=${pageQpri.rootId}&pageStatus=start">Bom</a>/
									</s:if>
								<s:elseif test='#pageQpri.status=="项目跟踪"'>
									<a href="QuotedPrice_findQuotedPrice.action?id=${pageQpri.rootId}">Bom</a>/
									</s:elseif>
								<s:else>
									<a
										href="QuotedPrice_findQuotedPrice.action?id=${pageQpri.rootId}&pageStatus=bom">Bom</a>/
									</s:else>
								<s:if test='#pageQpri.status!="项目启动中"&&#pageQpri.status!="项目跟踪"'>
									<a
										href="ProjectManage_findDeptProTime.action?id=${pageQpri.rootId}">时间指派</a>/
								</s:if>
								<s:if test='#pageQpri.status=="外购外委评审"'>
									<a href="QuotedPrice_findeOsa.action?id=${pageQpri.rootId}">查看评审进度</a>/
								</s:if>
								<s:if test='#pageQpri.status!="初始"'>
									<a
										href="ProjectManage_findDeptProTime.action?id=${pageQpri.rootId}&pageStatus=show">完成进度</a>/
								</s:if>
								<a href="QuotedPrice_findAllPrice.action?id=${pageQpri.rootId}">报价汇总</a>
								<s:if test='#pageQpri.status!="项目启动中"'>
								/<a onclick="return window.confirm('确定要删除该信息吗?')"
										href="QuotedPrice_delQuotedPrice.action?id=${pageQpri.rootId}&cpage=${cpage}&total=${total}&pageStatus=${pageStatus}">删除</a>
								</s:if>
								<s:if test='#pageQpri.status=="集合报价"'>
									/<a
										href="QuotedPrice_startproject.action?id=${pageQpri.rootId}&cpage=${cpage}&total=${total}&pageStatus=all">启动项目</a>
								</s:if>
								<s:if test='#pageQpri.status=="项目跟踪"'>
									/<a
										href="QuotedPrice_trackQuotedPrice.action?id=${pageQpri.rootId}">项目跟踪</a>
										/<a onclick="intoSop(${pageQpri.rootId})">进入SOP</a>
								</s:if>
							</s:if>
							<s:else>
								<s:if test='#pageQpri.status=="项目启动中"'>
									<a
										href="QuotedPrice_findQuotedPrice.action?id=${pageQpri.id}&pageStatus=start">变更工装</a>/
							</s:if>
								<s:if
									test='#pageQpri.status=="项目启动中"||#pageQpri.status=="项目跟踪"||#pageQpri.status=="项目编制"||#pageQpri.status=="完成"'>
									<font color="gray">修改</font>/
								</s:if>
								<s:else>
									<a
										href="QuotedPrice_findQuotedPrice.action?id=${pageQpri.rootId}&pageStatus=${pageStatus}">修改</a>/
									
									</s:else>
								<s:if test="#pageQpri.fileName!=null">
									<a
<%--										href="DownAction.action?fileName=${pageQpri.fileName}&directory=/upload/file/project/">查看附件</a>--%>
										href="FileViewAction.action?FilePath=/upload/file/project/${pageQpri.fileName}">查看附件</a>
								</s:if>
								<s:else>
									<font color="gray">查看附件</font>
								</s:else>
							</s:else>
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
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function() {
	$.ajax( {
		type : "POST",
		url : "ProjectManage_findAllProMan.action",
		dataType : "json",
		success : function(msg) {
			$("#proId").append("<option value=''></option>");
			$.each(msg, function(i, n) {
				$("#proId").append(
						"<option value='" + n.id + "'>" + n.projectNum + "("
								+ n.projectName + ")</option>");
			});
		}
	});
})
function intoSop(rootId){
	if(window.confirm("是否确定进入sop?")){
		window.location.href="QuotedPrice_buildBomtosop.action?quotedPrice.rootId="+rootId;
	}
}
</script>
	</body>
</html>
