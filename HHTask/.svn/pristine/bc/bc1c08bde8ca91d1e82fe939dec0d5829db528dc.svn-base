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
		<form action="ZhuserOfferAction_firstCheck.action" method="post">
			<table class="table">
				<tr align="center">
					<td colspan="4" style="font-size: 20px;">
						样品详情
						(${zhuserOffer.cmp}-${zhuserOffer.cperson}-${zhuserOffer.ctel})
					</td>
				</tr>
				<tr>
					<td align="right">
						件号：
					</td>
					<td>
						${zhuserOffer.markId}
					</td>
					<td align="right">
						工序号：
					</td>
					<td>
						${zhuserOffer.processNO}
					</td>
				</tr>
				<tr>
					<td align="right">
						工序名称：
					</td>
					<td>
						${zhuserOffer.processName}
					</td>
					<td align="right">
						报价：
					</td>
					<td>
						${zhuserOffer.taxprice}(税率)
						<hr>
						${zhuserOffer.hsPrice}(含税价)
						<hr>
						${zhuserOffer.bhsPrice}(不含税价)
					</td>
				</tr>
				<tr>
					<td align="right">
						样品基本信息:
						<br />
						
					</td>
					<td colspan="10">
						<div id="fileDiv" style="display: block;">
							<div id="file1">
								图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;纸:
								<s:if test="sample.pic!=null">
<%--								<a href="<%=path%>/upload/file/sample/${sample.pic}">图纸下载</a>--%>
								<a href="FileViewAction.action?FilePath=/upload/file/sample/${sample.pic}">图纸下载</a>
								</s:if>
								<s:else>
									无
								</s:else>
							</div>
							<div id="file2">
								检&nbsp;&nbsp;验&nbsp;&nbsp;报&nbsp;&nbsp;告:
								<s:if test="sample.checkNote!=null">
<%--								<a href="<%=path%>/upload/file/sample/${sample.checkNote}">检验公告下载</a>--%>
								<a href="FileViewAction.action?FilePath=/upload/file/sample/${sample.checkNote}">检验公告下载</a>
								</s:if>
								<s:else>
									无
								</s:else>
							</div>
							<div id="file3">
								材&nbsp;&nbsp;质&nbsp;&nbsp;证&nbsp;&nbsp;明:
								<s:if test="sample.caiZhi!=null">
<%--								<a href="<%=path%>/upload/file/sample/${sample.caiZhi}">材质证明下载</a>--%>
								<a href="FileViewAction.action?FilePath=/upload/file/sample/${sample.caiZhi}">材质证明下载</a>
								</s:if>
								<s:else>
									无
								</s:else>
							</div>
							<div id="file4">
								环&nbsp;&nbsp;保&nbsp;&nbsp;报&nbsp;&nbsp;告:
								<s:if test="sample.huanBao!=null">
<%--								<a href="<%=path%>/upload/file/sample/${sample.huanBao}">环保报告下载</a>--%>
								<a href="FileViewAction.action?FilePath=/upload/file/sample/${sample.huanBao}">环保报告下载</a>
								</s:if>
								<s:else>
									无
								</s:else>
							</div>
							<div id="file5">
								材&nbsp;&nbsp;料&nbsp;&nbsp;性&nbsp;&nbsp;能:
								<s:if test="sample.caiLiao!=null">
								<a href="FileViewAction.action?FilePath=/upload/file/sample/${sample.caiLiao}">材料性能下载</a>
								</s:if>
								<s:else>
									无
								</s:else>
							</div>
							<div id="file6">
								盐&nbsp;&nbsp;雾&nbsp;&nbsp;检&nbsp;&nbsp;验:
								<s:if test="sample.yanWu!=null">
								<a href="FileViewAction.action?FilePath=/upload/file/sample/${sample.yanWu}">盐雾检验下载</a>
								</s:if>
								<s:else>
									无
								</s:else>
							</div>
							<div id="file7">
								模具认证资料:
								<s:if test="sample.moJuRenZhen!=null">
<%--								<a href="<%=path%>/upload/file/sample/${sample.moJuRenZhen}">模具认证资料下载</a>--%>
								<a href="FileViewAction.action?FilePath=/upload/file/sample/${sample.moJuRenZhen}">模具认证资料下载</a>
								</s:if>
								<s:else>
									无
								</s:else>
							</div>
							<div id="file8">
								工&nbsp;&nbsp;艺&nbsp;认&nbsp;证:&nbsp;&nbsp;
								<s:if test="sample.gongYi!=null">
<%--								<a href="<%=path%>/upload/file/sample/${sample.gongYi}">工艺认证下载</a>--%>
								<a href="FileViewAction.action?FilePath=/upload/file/sample/${sample.gongYi}">工艺认证下载</a>
								</s:if>
								<s:else>
									无
								</s:else>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					</td>
				</tr>

			</table>
		</form>
	</body>
</html>
