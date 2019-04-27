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
				<font style="font-size: 22px;font-weight: bolder;">
					pcn展示</font>
				<s:if test="pageStatus!='pingmu'">
					<form action="procardTemplateGyAction_pcnshowList.action"
						method="post">
						<table class="table" align="center">
							<tr>
								<td align="center">
									提出日期（起）
									<input type="text" name="start"
										value="<s:property value="start"/>" class="Wdate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</td>
								<td align="center">
									提出日期(止)
									<input type="text" name="end" value="<s:property value="end"/>"
										class="Wdate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</td>
							</tr>
							<tr>
								<td align="center">
									产品编码
									<input type="text" name="pcn.ywMarkId"
										value="<s:property value="pcn.ywMarkId"/>" />
								</td>
								<td align="center">
									客户反馈意见
									<SELECT name="pcn.feedbackoption">
										<option>
											${pcn.feedbackoption}
										</option>
										<option></option>
										<option>
											接受
										</option>
										<option>
											拒绝
										</option>
									</SELECT>
								</td>
							</tr>
							<tr>
								<td align="center">
									发起人
									<input type="text" name="pcn.applyUserName"
										value="<s:property value="pcn.applyUserName"/>" />
								</td>
								<td align="center">
									所属
									<input type="text" name="pcn.belongto"
										value="<s:property value="pcn.belongto"/>" />
								</td>
							</tr>
							<%--						<tr>--%>
							<%--							<td align="center">--%>
							<%--								是否结案--%>
							<%--								<SELECT name="pcn.isend">--%>
							<%--									<option>${pcn.isend}</option>--%>
							<%--									<option></option>--%>
							<%--									<option>已结案</option>--%>
							<%--									<option>未结案</option>--%>
							<%--								</SELECT>--%>
							<%--							</td>--%>
							<%--							<td align="center">--%>
							<%--							</td>--%>
							<%--						</tr>--%>

							<tr>
								<td align="center" colspan="2">
									<input type="submit" style="width: 100px; height: 40px;"
										value="查询(select)" />
								</td>
							</tr>
						</table>
					</form>
				</s:if>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							提出日期
						</td>
						<td align="center">
							发起人
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
						<td align="center" colspan="2">
							操作
						</td>
					</tr>
					<tr bgcolor="red">
						<td colspan="14" align="center">
							未结案
						</td>
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
						<td colspan="2">
							<s:if test="pageStatus!='pingmu'">
								<input type="button" value="修改(update)"
									style="width: 60px; height: 30px;"
									onclick="update(${pagewjapcn.id})" />

								<input type="button" value="删除(delete)"
									style="width: 60px; height: 30px;"
									onclick="todelete(${pagewjapcn.id})" />
								<s:if test="#pagewjapcn.files.size()>0">
									<a
										href="procardTemplateGyAction_pcnfileshow.action?id=${pagewjapcn.id}">文件查看</a>
								</s:if>
								<s:else>
									<a
										href="procardTemplateGyAction_pcnfileshow.action?id=${pagewjapcn.id}">无文件</a>
								</s:else>
							</s:if>
						</td>
						</tr>
					</s:iterator>
					<tr bgcolor="green">
						<td colspan="14" align="center">
							已结案
						</td>
					</tr>
					<s:iterator value="pcnList" id="pagepcn" status="pcnStatus">
						<s:if test="#pcnStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#pcnStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pcnStatus.index+1" />
							</font>
						</td>
						<td>
							${pagepcn.applyDate}
						</td>
						<td>
							${pagepcn.applyUserName}
						</td>
						<td>
							${pagepcn.pcnNumber}
						</td>
						<td>
							${pagepcn.ywMarkId}
						</td>
						<td>
							${pagepcn.belongto}
						</td>
						<td>
							${pagepcn.pcntheme}
						</td>
						<td>
							${pagepcn.submitDate}
						</td>
						<td>
							${pagepcn.feedbackDate}
						</td>
						<td>
							${pagepcn.feedbackoption}
						</td>
						<td>
							${pagepcn.zxStatus}
						</td>
						<td>
							${pagepcn.isend}
						</td>
						<td colspan="2">
							<s:if test="pageStatus!='pingmu'">
								<input type="button" value="修改(update)"
									style="width: 60px; height: 30px;"
									onclick="update(${pagepcn.id})" />

								<input type="button" value="删除(delete)"
									style="width: 60px; height: 30px;"
									onclick="todelete(${pagepcn.id})" />
								<s:if test="#pagepcn.files.size()>0">
									<a
										href="procardTemplateGyAction_pcnfileshow.action?id=${pagepcn.id}">文件查看</a>
								</s:if>
								<s:else>
									<a
										href="procardTemplateGyAction_pcnfileshow.action?id=${pagepcn.id}">无文件</a>
								</s:else>
							</s:if>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="14" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="14" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>

					<s:if test="successMessage!=null">
						<tr>
							<td colspan="14" align="center" style="color: red">
								${successMessage}

							</td>
						</tr>
					</s:if>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function update(id) {
	window.location.href = "procardTemplateGyAction_topcnupdate.action?cpage=${cpage}&id="
			+ id;
}
function todelete(id) {
	if (confirm("确定要删除此记录？")) {
		window.location.href = "procardTemplateGyAction_topcndelete.action?cpage=${cpage}&id="
				+ id;
	}
}
</script>
	</body>
</html>
