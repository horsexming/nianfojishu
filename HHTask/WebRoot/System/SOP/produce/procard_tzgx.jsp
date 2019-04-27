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
		<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>调整工序</h2>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							供应商
						</th>
						<th>
							件号
						</th>
						<th>
							零件名称
						</th>
						<th>
							批次
						</th>
						<th>
							版本
						</th>
						<th>
							版次
						</th>
						<th>
							工序号
						</th>
						<th>
							工序名称
						</th>
						<th>
							数量
						</th>
						<th>
							外委类型
						</th>
						<th>
							添加人
						</th>
						<th>
							添加时间
						</th>
					</tr>
					<tr>
						<td align="center">
							<s:if test="#pwwApplyDetail.gysId==null">
								<font color="red">尚未匹配到合同</font>
							</s:if>
							<s:else>
								${pwwApplyDetail.gysName}
								</s:else>
						</td>
						<td align="center">
							${pwwApplyDetail.markId}
						</td>
						<td align="left">
							${pwwApplyDetail.proName}
						</td>
						<td align="center">
							${pwwApplyDetail.selfCard}
						</td>
						<td align="center">
							${pwwApplyDetail.banbenNumber}
						</td>
						<td align="center">
							${pwwApplyDetail.banci}
						</td>
						<td align="left">
							${pwwApplyDetail.processNOs}
						</td>
						<td align="left">
							${pwwApplyDetail.processNames}
						</td>
						<td align="center">
							${pwwApplyDetail.applyCount}
						</td>
						<td align="center">
							${pwwApplyDetail.wwType}
						</td>
						<td align="center">
							${pwwApplyDetail.userName}
						</td>
						<td align="center">
							${pwwApplyDetail.addTime}
						</td>
					</tr>
				</table>
				<br/>
				<form action="ProcardAction!tzgongxu.action" method="POST">
					<table  width="98%">
						<tr>
							<td>
								<ul style="text-align: left; margin: 0px 0px 0px 50px;">
									<s:iterator value="processList" id="pageprocess">
										<li style="float: left; width: 33%;">
											<s:if test="#pageprocess.msg == 'true'">
												<input type="checkbox" value="${pageprocess.id}" name="processIds"
													checked="checked" />${pageprocess.processNO }_${pageprocess.processName}
											</s:if>
											<s:else>
												<input type="checkbox" value="${pageprocess.id}"  name="processIds" />${pageprocess.processNO }_${pageprocess.processName}
											</s:else>
										</li>
									</s:iterator>
								</ul>
							</td>
						</tr>
						<tr align="center">
							<td>
								<input type="hidden" value="${pwwApplyDetail.id}" name="id">							
								<input type="submit" value="提交" class="input" />
							</td>
						</tr>
					</table>

					
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">


</SCRIPT>
	</body>
</html>
