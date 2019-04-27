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
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
				</div>
			</div>

			<div align="center">
				<form action="rewardPunishAction!findAllRewardPunish.action"
					method="post" style="">
					<br>
					<table class="table">
						<s:if test="status != 'person'">
							<tr>
								<td align="right">
									工号:
								</td>
								<td>
									<input id="code" type="text" name="rewardPunish.code" value="" />
								</td>
								<td align="right">
									姓名:
								</td>
								<td>
									<input id="name" type="text" name="rewardPunish.name" value="" />
								</td>
								<td align="right">
									部门:
								</td>
								<td>
									<select id="dept" name="rewardPunish.dept">
										<option value="">
											请选择
										</option>
									</select>
								</td>
							</tr>
						</s:if>
						<s:else>
							<input type="hidden" value="${rewardPunish.userId}"
								name="rewardPunish.userId" />
							<input type="hidden" value="${status}" name="status" />
						</s:else>
						<tr>
							<td align="right">
								时间:
							</td>
							<td>
								<input id="date" class="Wdate" type="text"
									name="rewardPunish.date"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td align="right">
								项目:
							</td>
							<td>
								<input type="project" name="rewardPunish.project" value="" />
							</td>
							<td align="right">
								类型:
							</td>
							<td>
								<select name="rewardPunish.type" id="type">
									<option value="">
										请选择
									</option>
									<option value="加班">
										加班
									</option>
									<option value="请假">
										请假
									</option>
									<option value="违纪">
										违纪
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="5">
								<input type="submit" value="查询"
									style="width: 100px; height: 50px;" />

								<input type="reset" value="重置"
									style="width: 100px; height: 50px;" />
							</td>
							<td align="center">
								<a style="text-decoration: none"
									href="rewardPunishAction!collectRewardPunishByMonth.action"><input
										type="button" value="汇总" style="width: 100px; height: 50px;" />
								</a>
							</td>
						</tr>
					</table>
				</form>

				<table class="table">
					<tr align="center" bgcolor="#c0dcf2"
						style="height: 40px; font-weight: bold;">
						<td width="40px">
							序号
						</td>
						<td width="100px">
							工号
						</td>
						<td width="100px">
							姓名
						</td>
						<td width="100px">
							部门
						</td>
						<td width="120px">
							时间
						</td>
						<td width="120px">
							项目
						</td>
						<td width="120px">
							类型
						</td>
						<td width="60px">
							金额
						</td>
						<td>
							备注
						</td>
						<td>
							操作
						</td>
					</tr>

					<s:iterator id="rewardPunish" value="rewardPunishList"
						status="ststusOvertime">
						<s:if test="#ststusOvertime.first">
							<tr bgcolor="green">
								<th colspan="11" align="center">
									<font color="#ffffff"> 奖扣记录</font>
								</th>
							</tr>
						</s:if>
						<s:if test="#ststusOvertime.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#ststusOvertime.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="red">
							</s:else>
							<s:property value="#ststusOvertime.index+1" />
							</font>
						</td>
						<td>
							${code}
						</td>
						<td>
							${name}
						</td>
						<td>
							${dept}
						</td>
						<td>
							<s:date name="date" format="yyyy-MM-dd" />
						</td>
						<td>
							${project}
						</td>
						<td>
							${type}
						</td>
						<td>
							${money}
						</td>
						<td>
							${explain}
						</td>
						<td>
							<a target="_blank"
								href="rewardPunishAction!getUpdatePage.action?rewardPunish.id=${id}">
								修改</a>/
							<a onclick="return window.confirm('确认要删除该奖扣记录?')"
								href="rewardPunishAction!deleteRewardPunish.action?rewardPunish.id=${id}">删除</a>
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
					</tr>
					<tr>
						<td colspan="7" align="center">
							<font color="red">${errorMessage}${successMessage}</font>
						</td>
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
<script type="text/javascript">
$(function() {
	$.ajax( {
		type : "get",
		dataType : "text",
		url : "DeptNumberAction!finAllDeptNumberForSetlect.action",
		async : false,
		success : function(data) {
			var dept = data.split("|");
			for ( var i = 0; i < dept.length - 1; i++) {
				var deptItem = new Option(dept[i], dept[i]);
				$("#dept").append(deptItem);
			}
		}
	});
});
</script>