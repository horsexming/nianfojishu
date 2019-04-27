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
				</div>
			</div>
			
			<div align="center">
				<form action="rewardPunishAction!updateRewardPunish.action" method="post"
					style="">
					<br>
					<table border="0" width="100%" class="table">
						<tr>
							<input id="id" type="hidden" name="rewardPunish.id" value="${rewardPunish.id}" />
							<td align="right">
								工号:
							</td>
							<td>
								<input id="code" type="text" name="rewardPunish.code" value="${rewardPunish.code}" disabled="disabled"/>
							</td>
							<td align="right">
								姓名:
							</td>
							<td>
								<input id="name" type="text" name="rewardPunish.name" value="${rewardPunish.name}" disabled="disabled"/>
							</td>
							<td align="right">
								部门:
							</td>
							<td>
								<input id="dept" type="text" name="rewardPunish.dept" value="${rewardPunish.dept}" disabled="disabled"/>
							
							</td>
						</tr>
						<tr>
							<td align="right">
								时间:
							</td>
							<td>
								<input id="date" class="Wdate" type="text" name="rewardPunish.date"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" value="${rewardPunish.date}"/>
							</td>
							<td align="right">
								项目:
							</td>
							<td>
								<input type="project" name="rewardPunish.project" value="${rewardPunish.project}" />
							</td>
							
							
						</tr>
						<tr>
							<td align="right">
								类型:
							</td>
							<td>
								<select name="rewardPunish.type" id="type">
									<option value="">请选择</option>
									<option value="加班">加班</option>
									<option value="请假">请假</option>
									<option value="违纪">违纪</option>
								</select>
							</td>
							<td align="right">
								金额:
							</td>
							<td>
								<input type="money" name="rewardPunish.money" value="${rewardPunish.money}" />
							</td>
							
						</tr>
						<tr>
							<td align="right">
								备注:
							</td>
							<td colspan="3">
								<textarea type="explain" name="rewardPunish.explain" >${rewardPunish.explain}</textarea>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" value="修改"
									style="width: 100px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<<script type="text/javascript">
$(function(){
	$("#type").find("option[value='${rewardPunish.type}']").attr("selected",true);
});
</script>
