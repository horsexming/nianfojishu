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
					E-CAR展示
				</font>
				<s:if test="pageStatus!='pingmu'">
					<form action="procardTemplateGyAction_ecarshowList.action"
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
									<input type="text" name="ecar.ywMarkId"
										value="<s:property value="ecar.ywMarkId"/>" />
								</td>
								<td align="center">
									目前状态
									<SELECT name="ecar.zxstatus" id="zxstatus" >
									<option>${ecar.zxstatus}</option>
									<option></option>
									<option>待创建人确认</option>
									<option>待处理人处理</option>
									<option>待处理试制报告</option>
									<option>待提交试制报告</option>
									<option>待回归试制报告</option>
									<option>待确认试制报告</option>
									<option>待审核试制报告</option>
									<option>已驳回</option>
									<option>已取消</option>
									<option>已关闭</option>
								</SELECT>
								</td>
							</tr>
							<tr>
								<td align="center">
									所属
									<input type="text" name="ecar.belongto"
										value="<s:property value="ecar.belongto"/>" />
								</td>
								<td align="center">
									提交人
									<input type="text" name="ecar.tjUserName"
										value="<s:property value="ecar.tjUserName"/>" />
								</td>
							</tr>

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
						<td align="center" colspan="2">
							操作
						</td>
					</tr>
					<tr bgcolor="red">
					<td colspan="12" align="center">待处理</td>
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
						<td colspan="2">
							<s:if test="pageStatus!='pingmu'">
								<input type="button" value="修改(update)"
									style="width: 60px; height: 30px;"
									onclick="update(${pagedqrecar.id})" />

								<input type="button" value="删除(delete)"
									style="width: 60px; height: 30px;"
									onclick="todelete(${pagedqrecar.id})" />
								<s:if test="#pagedqrecar.files.size()>0">
									<a
										href="procardTemplateGyAction_ecarfileshow.action?id=${pagedqrecar.id}">文件查看</a>
								</s:if>
								<s:else>
									<a
										href="procardTemplateGyAction_ecarfileshow.action?id=${pagedqrecar.id}">无文件</a>
								</s:else>
							</s:if>
						</td>
						</tr>
					</s:iterator>

					<tr bgcolor="green">
						<td colspan="12" align="center">
							已关闭
						</td>
					</tr>
					<s:iterator value="ecarList" id="pageecar" status="ecarStatus">
						<s:if test="#ecarStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#ecarStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#ecarStatus.index+1" />
							</font>
						</td>
						<td>
							${pageecar.applyDate}
						</td>
						<td>
							${pageecar.ecarNumber}
						</td>
						<td>
							${pageecar.ywMarkId}
						</td>
						<td>
							${pageecar.problempoint}
						</td>
						<td>
							${pageecar.tjUserName}
						</td>
						<td>
							${pageecar.belongto}
						</td>
						<td>
							${pageecar.zxstatus}
						</td>
						<td>
							${pageecar.endTime}
						</td>
						<td>
							${pageecar.sxx}
						</td>
						<td colspan="2">
							<s:if test="pageStatus!='pingmu'">
								<input type="button" value="修改(update)"
									style="width: 60px; height: 30px;"
									onclick="update(${pageecar.id})" />

								<input type="button" value="删除(delete)"
									style="width: 60px; height: 30px;"
									onclick="todelete(${pageecar.id})" />
								<s:if test="#pageecar.files.size()>0">
									<a
										href="procardTemplateGyAction_ecarfileshow.action?id=${pageecar.id}">文件查看</a>
								</s:if>
								<s:else>
									<a
										href="procardTemplateGyAction_ecarfileshow.action?id=${pageecar.id}">无文件</a>
								</s:else>
							</s:if>
						</td>
						</tr>
					</s:iterator>
					<tr bgcolor="yellow">
					<td colspan="12" align="center">已驳回</td>
					</tr>
					<s:iterator value="list2" id="pageqxecar" status="qxecarStatus">
						<s:if test="#qxecarStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#qxecarStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#qxecarStatus.index+1" />
							</font>
						</td>
						<td>
							${pageqxecar.applyDate}
						</td>
						<td>
							${pageqxecar.ecarNumber}
						</td>
						<td>
							${pageqxecar.ywMarkId}
						</td>
						<td>
							${pageqxecar.problempoint}
						</td>
						<td>
							${pageqxecar.tjUserName}
						</td>
						<td>
							${pageqxecar.belongto}
						</td>
						<td>
							${pageqxecar.zxstatus}
						</td>
						<td>
							${pageqxecar.endTime}
						</td>
						<td>
							${pageqxecar.sxx}
						</td>
						<td colspan="2">
							<s:if test="pageStatus!='pingmu'">
								<input type="button" value="修改(update)"
									style="width: 60px; height: 30px;"
									onclick="update(${pageqxecar.id})" />

								<input type="button" value="删除(delete)"
									style="width: 60px; height: 30px;"
									onclick="todelete(${pageqxecar.id})" />
								<s:if test="#pageqxecar.files.size()>0">
									<a
										href="procardTemplateGyAction_ecarfileshow.action?id=${pageqxecar.id}">文件查看</a>
								</s:if>
								<s:else>
									<a
										href="procardTemplateGyAction_ecarfileshow.action?id=${pageqxecar.id}">无文件</a>
								</s:else>
							</s:if>
						</td>
						</tr>
					</s:iterator>
					<tr bgcolor="yellow">
					<td colspan="12" align="center">已取消</td>
					</tr>
					<s:iterator value="list3" id="pageqxecar" status="qxecarStatus">
						<s:if test="#qxecarStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#qxecarStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#qxecarStatus.index+1" />
							</font>
						</td>
						<td>
							${pageqxecar.applyDate}
						</td>
						<td>
							${pageqxecar.ecarNumber}
						</td>
						<td>
							${pageqxecar.ywMarkId}
						</td>
						<td>
							${pageqxecar.problempoint}
						</td>
						<td>
							${pageqxecar.tjUserName}
						</td>
						<td>
							${pageqxecar.belongto}
						</td>
						<td>
							${pageqxecar.zxstatus}
						</td>
						<td>
							${pageqxecar.endTime}
						</td>
						<td>
							${pageqxecar.sxx}
						</td>
						<td  colspan="2">
							<s:if test="pageStatus!='pingmu'">
							<input type="button" value="修改(update)"
								style="width: 60px; height: 30px;"
								onclick="update(${pageqxecar.id})" />

							<input type="button" value="删除(delete)"
								style="width: 60px; height: 30px;"
								onclick="todelete(${pageqxecar.id})" />
							<s:if test="#pageqxecar.files.size()>0">
								<a href="procardTemplateGyAction_ecarfileshow.action?id=${pageqxecar.id}">文件查看</a>
							</s:if>
							<s:else>
								<a href="procardTemplateGyAction_ecarfileshow.action?id=${pageqxecar.id}">无文件</a>
							</s:else></s:if>
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
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function update(id) {
	window.location.href = "procardTemplateGyAction_toecarupdate.action?cpage=${cpage}&id="
			+ id;
}
function todelete(id) {
	if (confirm("确定要删除此记录？")) {
		window.location.href = "procardTemplateGyAction_toecardelete.action?cpage=${cpage}&id="
				+ id;
	}
}
</script>
	</body>
</html>
