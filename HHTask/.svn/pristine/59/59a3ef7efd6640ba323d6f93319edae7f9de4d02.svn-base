<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

function print2() {
	window.location.href = "InformAction!findli.action";
}
</script>
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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<div align="center" id="image">
					<div align="center">
						<div>
							<table align="center" frame="void" style="width: 80%;">
								<tr>
									<td width="80px"></td>
									<td align="left" width="10px">
										<img widtn="64px;" height="55px;" alt="${companyInfo.shortName}" src="<%=basePath%>${companyInfo.logoOKjpg}">
									</td>
									<td align="center" width="900px">
										<font size='6'><B>员工离职交接单</B>
										</font>
									</td>
								</tr>
							</table>
						</div>
						<div align="center" style="width: 80%; text-align: center">
							<table class="table" style="width: 90%; margin: auto">
								<tr>
									<td width="60px" height="30px" align="right">
										姓名
									</td>
									<td width="60px">
										${leaveInform.username}
									</td>
									<td width="65px" align="center">
										部门班组
									</td>
									<td width="60px">
										${leaveInform.dept}
									</td>
									<td width="60px">
										离职原因
									</td>
									<td colspan="2">
										${leaveInform.reason}
									</td>
									<td colspan="2" align="center">
										离职申请编号
									</td>
									<td width="100px">
										${leaveInform.time}
									</td>
								</tr>
								<tr>
									<td colspan="10" height="30px" align="center">
										员工离职交接完成情况
									</td>
								</tr>
								<tr>
									<td colspan="4" height="30px" align="center">
										生产部
									</td>
									<td colspan="4" align="center">
										其他相关部门
									</td>
									<td colspan="2" align="center">
										财务部
									</td>
								</tr>
								<tr>
									<td height="30px" align="center">
										总成库
									</td>
									<td></td>
									<td align="center">
										工具工段
									</td>
									<td></td>
									<td rowspan="2" width="60px" align="center">
										资料室
									</td>
									<td rowspan="2" width="80px"></td>
									<td rowspan="2" align="center" width="60px">
										计算机室
									</td>
									<td rowspan="2" width="80px"></td>
									<td rowspan="2" align="center">
										财务室
									</td>
									<td rowspan="2" width="80px"></td>
								</tr>
								<tr>
									<td height="30px" align="center">
										材料库
									</td>
									<td></td>
									<td align="center">
										综合夹具
									</td>
									<td></td>
								</tr>
								<tr>
									<td height="30px" align="center">
										物流主管
									</td>
									<td></td>
									<td align="center">
										工具库
									</td>
									<td></td>
									<td rowspan="2" align="center">
										档案室
									</td>
									<td rowspan="2"></td>
									<td rowspan="2" align="center">
										信息主管
									</td>
									<td rowspan="2"></td>
									<td rowspan="2" align="center">
										财务主管
									</td>
									<td rowspan="2"></td>
								</tr>
								<tr>
									<td height="30px" align="center">
										综合库
									</td>
									<td></td>
									<td align="center">
										设备主管
									</td>
									<td></td>
								</tr>
								<tr>
									<td rowspan="2" height="30px" align="center">
										生产办公室
									</td>
									<td rowspan="2"></td>
									<td rowspan="2" align="center">
										生产主管
									</td>
									<td rowspan="2"></td>
									<td rowspan="2" align="center">
										医务室
									</td>
									<td rowspan="2"></td>
									<td rowspan="2" align="center">
										是否离职体检
									</td>
									<td rowspan="2"></td>
									<td rowspan="2" align="center">
										总经办主任
									</td>
									<td rowspan="2"></td>
								</tr>
								<tr></tr>
								<tr>
									<td rowspan="3" height="80px" align="center">
										物业宿舍
									</td>
									<td rowspan="3"></td>
									<td rowspan="3" align="center">
										有无赔付事项
									</td>
									<td rowspan="3" colspan="3"></td>
									<td colspan="2" align="center">
										工资/补偿结算方式
									</td>
									<td colspan="2" align="center">
										人力资源主管
									</td>
								</tr>
								<tr>
									<td colspan="2" rowspan="2" height="50px"></td>
									<td colspan="2" rowspan="2"></td>
								</tr>
							</table>
						</div>
					</div>

					<div align="center" style="width: 80%">
						<div>
							<table align="center" frame="void">
								<tr>
									<td width="80px"></td>
									<td align="left" width="10px">
										<img widtn="64px;" height="55px;" alt="${companyInfo.shortName}" src="<%=basePath%>${companyInfo.logoOKjpg}">
									</td>
									<td align="center" width="900px">
										<font size='5'> <B>${companyInfo.shortName}消声器厂员工离职人事通知单</B>
										</font>
									</td>
								</tr>
							</table>
						</div>
						<div align="center" style="width: 100%; text-align: center">
							<table class="table" style="width: 90%; margin: auto">
								<tr>
									<td width="80px" align="center">
										主送
									</td>
									<td align="left" colspan="5">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										财务部

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										抄送 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										人力资源室
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										存档
									</td>
								</tr>
								<tr>
									<td width="80px" rowspan="2" align="center">
										姓名
									</td>
									<td width="80px" rowspan="2" align="center">
										${leaveInform.username}
									</td>
									<td width="80px" rowspan="2" align="center">
										部门/班组
									</td>
									<td width="80px" rowspan="2" align="center">
										${leaveInform.dept}
									</td>
									<td width="180px" align="center">
										离职原因
									</td>
									<td align="center">
										${leaveInform.reason}
									</td>
								</tr>
								<tr>
									<td align="center" width="180px">
										离职申请单编号
									</td>
									<td align="center">
										${leaveInform.time}
									</td>
								</tr>
								<tr>
									<td colspan="6" align="left">
										请与
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										年 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 月
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 日前办理手续，
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 月
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 日起止薪。
									</td>
								</tr>
								<tr>
									<td align="center" colspan="6" rowspan="6">
										</br>
										&nbsp;
										</br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										此通知
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</br>
										&nbsp;
										</br>
										&nbsp;
										</br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										(公章)
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										经办人: &nbsp;&nbsp;${sessionScope.Users.name}
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div align="center">
					<table width="165px" height="50px">
						<tr>
							<td align="center">
								<input type="button" onclick="pagePrint('image')" class="input"
									id="print" value="打      印" style="width: 80px; height: 50px;" />
							</td>
							<td align="center">
								<input type="button" onclick="print2()" value="更多打印"
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>

</html>