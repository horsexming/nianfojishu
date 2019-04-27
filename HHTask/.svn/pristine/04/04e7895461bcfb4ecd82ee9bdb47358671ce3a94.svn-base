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
		<div align="center" id="gongneng">
			<div align="center" id="tongzhi"
				style="width: 756px; height: 1086px; border: 0px solid #000000;">
				<%-- 
				style="width: 70%; height: 100%; border: 0px solid #000000;">
				--%>
					<table class="table" align="center">
						<tr>
							<th colspan="6" align="left" style="height: 25px;">
								HR-SD-0000072
							</th>
						</tr>
						<tr>
							<th align="center" colspan="6" style="font-size: 20px; height: 70px;">
								<img width="45px" height="45px;"
								src="<%=basePath%>${companyInfo.logoOKjpg}" align="center"></img>
							&nbsp;&nbsp;&nbsp;&nbsp;${companyInfo.name}离职人事通知单
							</th>
						</tr>
						<tr>
							<th colspan="6" align="right" style="height: 25px;">
								编号：${dimissionNotice.noticeNumber}
							</th>
						</tr>
						<tr>
							<th style="height: 30px;">
								主送
							</th>
							<th>
								财务部
							</th>
							<th>
								抄送：
							</th>
							<td align="center">
								${dimissionNotice.chaosong}
							</td>
							<th>
								存档：
							</th>
							<th>
								人力资源
							</th>
						</tr>
						<tr>
							<th style="height: 30px;">
								姓名
							</th>
							<th>
								${dimissionNotice.name}
							</th>
							<th>
								部门/班组
							</th>
							<th>
								${dimissionNotice.dept}
							</th>
							<th>
								工号
							</th>
							<th>
								${dimissionNotice.code}
							</th>
						</tr>
						<tr>
							<th style="height: 30px;">
								离职原因
							</th>
							<th colspan="5">
								${dimissionNotice.lizhi}
							</th>
						</tr>
						<tr>
							<th style="height: 30px;">
								办理手续日期
							</th>
							<th>
								${dimissionNotice.banli_time}
							</th>
							<th>
								止薪日期
							</th>
							<th>
								${dimissionNotice.zhixin_time}
							</th>
							<th>
								停缴社保、公积金日期
							</th>
							<th>
							${dimissionNotice.shebao_time}
							</th>
						</tr>
						<tr>
							<th style="height: 30px;">
								离职当月工资
							</th>
							<td colspan="5">
								&nbsp;&nbsp;${dimissionNotice.gongzijiesuan}
							</td>
						</tr>
						<tr>
							<th colspan="6" align="left" style="height: 70px;border-bottom-width: 0px;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;此通知
							</th>
						</tr>
						<tr>
							<th colspan="6" align="right" style="height: 30px;border-top-width: 0px;">（公章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								经办人：${dimissionNotice.banliren}<input type="hidden" name="dimissionNotice.banliren" value="${dimissionNotice.banliren}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</th>
						</tr>
						<tr>
							<th colspan="6" align="left"style="height: 30px;">
								&nbsp;&nbsp;&nbsp;&nbsp;签收：
							</th>
						</tr>
					</table>
			</div>
			<div>
				<input type="submit" value="打印" onclick="pagePrint('tongzhi','sy')"
					style="width: 80px; height: 40px;">
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
</script>
	</body>
</html>
