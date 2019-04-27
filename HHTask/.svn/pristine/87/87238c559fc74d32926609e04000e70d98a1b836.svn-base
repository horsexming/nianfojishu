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
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="proProcessDifficultyAction_showList.action"
						style="color: #ffffff">刷新<br />(reflesh)</a>
				</div>
			</div>

			<div align="center">
				<h3>
					项目加工难点系数管理

					<br />
					(Project process difficult score management)
				</h3>
				<form action="proProcessDifficultyAction_showList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								分类（type）：
								<input type="text" name="proProcessDifficulty.name"
									value="<s:property value="proProcessDifficulty.name"/>" />
							</td>
						</tr>

						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加加工难点系数(add)" onclick="add()" />
							</td>
						</tr>
					</table>
				</form>
				<table width="100%" border="1" style="border-collapse: collapse;">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
							<br />
							(num)
						</td>
						<td align="center">
							分类
							<br />
							(skill lv)
						</td>
						<td align="center">
							技能分类
							<br />
							(skill lv)
						</td>
						<td align="center">
							技能最高系数
							<br />
							（skill max score）
						</td>
						<td align="center" colspan="2">
							技能系数
							<br />
							（skill score）
						</td>
						<td align="center">
							总体技能系数
							<br />
							（total skill score）
						</td>
						<td align="center">
							强度系数
							<br />
							（difficult score）
						</td>
						<td align="center">
							综合系数
							<br />
							（total score）
						</td>
						<td align="center" colspan="3">
							操作
							<br />
							(Operation)
						</td>
					</tr>



					<s:iterator value="ppdVoList" id="ppdVo" status="pageStatus1">
					       <!-- 当分类列表长度为0时 -->
					           <s:if test="#ppdVo.skillTypeVo.size()==0">
					           <s:if test="#pageStatus1.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
					           <td>
					           <s:property value="#pageStatus1.index+1" />
					           </td>
					           <td>
					            ${ppdVo.name}
					           </td>
					           <td colspan="6">
					           </td>
					           <td><td rowspan="<s:property value="#ppdVo.scoreCount" />" colspan="3">
										<input type="button" value="技能分类绑定(skill type link)"
											style="width: 60px; height: 30px;"
											onclick="skilltype(${ppdVo.id})" />
										<input type="button" value="修改(update)"
											style="width: 60px; height: 30px;"
											onclick="update(${ppdVo.id})" />

										<input type="button" value="删除(delete)"
											style="width: 60px; height: 30px;"
											onclick="todelete(${ppdVo.id })" />
									</td></td>
					           </tr>
					           </s:if>
					
					
						<s:iterator value="#ppdVo.skillTypeVo" id="stVo"
							status="pageStatus2">
							<s:iterator value="#stVo.skillScore" id="sScore"
								status="pageStatus3">
								<!-- 设置行颜色 -->
								<s:if
									test="#pageStatus1.index%2==1&pageStatus2.index==0&#pageStatus3.index==0;">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<s:if test="#pageStatus2.index==0&#pageStatus3.index==0">
									<td rowspan="<s:property value="#ppdVo.scoreCount" />">
										<s:if test="#pageStatus1.index%2==1">
											<font>
										</s:if>
										<s:else>
											<font color="#c0dcf2">
										</s:else>
										<s:property value="#pageStatus1.index+1" />
										</font>
									</td>
									<td rowspan="<s:property value="#ppdVo.scoreCount" />">
										${ppdVo.name}
									</td>
								</s:if>

								<s:if test="#pageStatus3.index==0">
									<td rowspan="<s:property value="#stVo.scoreCount" />">
										${stVo.name}
									</td>
									<td rowspan="<s:property value="#stVo.scoreCount" />">
										${stVo.maxscore}
									</td>
								</s:if>
								<td align="center">
									${sScore.name }
								</td>
								<td align="center">
									${sScore.score}
								</td>
								<td align="center">
									${sScore.totalscore}
								</td>
								<td align="center">
									${sScore.difficultScore}
								</td>
								<td align="center">
									${sScore.total}
								</td>

								<s:if test="#pageStatus3.index==0&#pageStatus2.index==0">
									<td rowspan="<s:property value="#ppdVo.scoreCount" />"
										colspan="3">
										<input type="button" value="技能分类绑定(skill type link)"
											style="width: 60px; height: 30px;"
											onclick="skilltype(${ppdVo.id})" />
										<input type="button" value="修改(update)"
											style="width: 60px; height: 30px;"
											onclick="update(${ppdVo.id})" />

										<input type="button" value="删除(delete)"
											style="width: 60px; height: 30px;"
											onclick="todelete(${ppdVo.id })" />
									</td>
								</s:if>
								</tr>

							</s:iterator>
						</s:iterator>
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
function skilltype(id) {
	window.location.href = "proProcessDifficultyAction_skillTypeView.action?proProcessDifficulty.id="
			+ id;
}
function linkprocess(id) {
	window.location.href = "proProcessDifficultyAction_processView.action?proProcessDifficulty.id="
			+ id;
}
function update(id) {
	window.location.href = "proProcessDifficultyAction_toupdate.action?proProcessDifficulty.id="
			+ id;
}
function todelete(id) {
	window.location.href = "proProcessDifficultyAction_delete.action?proProcessDifficulty.id="
			+ id;
}
function add() {
	window.location.href = "<%=path%>/System/shizhi/ppd_add.jsp";
}
</script>
	</body>
</html>
