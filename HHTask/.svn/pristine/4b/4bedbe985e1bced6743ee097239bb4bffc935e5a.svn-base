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
			<div align="center" id="lzshenq"
				style="width: 765px; height: 1086px; border: 0px solid #000000;">
				<table class="table" align="center">
					<tr>
						<th align="center" colspan="6"
							style="font-size: 20px; height: 70px;">
							<img width="45px" height="45px;"
								src="<%=basePath%>${companyInfo.logoOKjpg}" align="center"></img>
							&nbsp;&nbsp;&nbsp;&nbsp; ${companyInfo.name}员工离职申请单
							<input type="hidden" name="dimissionLog.id"
								value="${dimissionLog.id}" />
							<input type="hidden" name="tag" value="${tag}" />
						</th>
					</tr>
					<tr>
						<td colspan="2" align="left"
							style="border-right-width: 0px; height: 30px;">
							<h3>
								&nbsp;&nbsp;&nbsp;&nbsp;员工合同编号：${dimissionLog.contract_number}
							</h3>
						</td>
						<td colspan="4" align="right"
							style="border-left-width: 0px; height: 30px;">
							<h3>
								申请单编号：${dimissionLog.shenqing_number} &nbsp;&nbsp;&nbsp;&nbsp;
							</h3>
						</td>
					</tr>
					<tr>
						<th style="height: 30px; width: 110px;">
							申请人姓名
						</th>
						<th style="width: 110px;">
							部门
						</th>
						<th>
							岗位
						</th>
						<th colspan="2">
							本厂工作年限
						</th>
						<th>
							工号
						</th>
					</tr>
					<tr style="height: 30px;">
						<td align="center">
							${dimissionLog.name}
						</td>
						<td align="center">
							${dimissionLog.dept}
						</td>
						<td align="center">
							${dimissionLog.job}
						</td>
						<td align="center" colspan="2">
							${dimissionLog.year_term}
						</td>
						<td align="center">
							${dimissionLog.code}
						</td>
					</tr>
					<tr>
						<th style="height: 50px;">
							离职原因：
						</th>
						<td colspan="5">
							&nbsp;${dimissionLog.dimission_Reason}
						</td>
					</tr>

					<tr>
						<th style="height: 30px;">
							离职后去向：
						</th>
						<td align="left" colspan="3" style="border-right-width: 0px;">
							&nbsp;${dimissionLog.dimission_laterGo}
						</td>
						<s:if test="dimissionLog.app_time!=null">
							<th>
								主管确认离职时间
							</th>
							<th>
								${dimissionLog.app_time}
							</th>
						</s:if>
						<s:else>
							<td colspan="2" style="border-left-width: 0px;"></td>
						</s:else>
					</tr>
					<%--<tr>
							<th>
								是否有遗留问题或劳动争议:
							</th>
							<td colspan="5">
										${dimissionLog.naowuzhengyi}&nbsp;&nbsp;&nbsp;&nbsp;
								<s:if test="dimissionLog.naowuzhengyi=='是'&&dimissionLog.naowuzhengyi!=null">
									${dimissionLog.naowuzhengyi}
								</s:if>
							</td>
						</tr>--%>

					<tr>
						<th colspan="6">
							请确认以下条款
						</th>
					</tr>
					<tr>
						<s:iterator value="provisionlist" id="provil" status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="left">
							</s:if>
							<s:else>
								<tr align="left">
							</s:else>
							<td align="left" colspan="6">
								&nbsp;&nbsp;&nbsp;&nbsp;${provil.content}
							</td>
						</s:iterator>
					</tr>
					<tr>
						<th style="height: 30px;">
							本人签字
						</th>
						<td colspan="5" style="height: 30px;"></td>
					</tr>
					<%--<s:if test="tag!=null&&tag=='all'">
					</s:if>
					<s:else>
						<tr>
							<td align="center" colspan="6">
								本人确定:
								<s:if
									test="dimissionLog.confirm=='同意'&&dimissionLog.confirm!=null">
									同意
								</s:if>
								<s:else>
									<input type="checkbox" name="dimissionLog.confirm" id="confirm"
										value="同意">
								</s:else>
							</td>
						</tr>
					</s:else>--%>
					<tr>
					<th colspan="2" style="height: 40px;">
						是否需要离职体检
						<br />
						(如不需要做体检请本人确认签字)
					</th>
					<td colspan="4" style="height: 40px;">
						${dimissionLog.tijian}
					</td>
					</tr>
					<tr>
						<th colspan="2" style="height: 30px;">
							是否接受过工厂外派培训
							<br />
							并签有培训协议
						</th>
						<td colspan="4" style="height: 30px;">
							${dimissionLog.peixunxieyi}
						</td>
					</tr>
					<tr>
						<th colspan="2" style="height: 30px;">
							是否与工厂签有保密
							<br />
							及竟业禁止协议
						</th>
						<td colspan="4" style="height: 30px;">
							${dimissionLog.baomi}
						</td>
					</tr>
					<tr>
						<th colspan="2" style="height: 30px;">
							其它需要补充或说明的事项
						</th>
						<td colspan="4" style="height: 30px;">
							${dimissionLog.buchong}
						</td>
					</tr>
					<%--
					<tr>
						<th style="height: 40px;">
							组长
						</th>
						<td colspan="5" style="height: 30px;"></td>
					</tr>
					<tr>
						<th style="height: 40px;">
							部门主管
						</th>
						<td colspan="5" style="height: 30px;"></td>
					</tr>
					<tr>
						<th style="height: 50px;">
							总经理
						</th>
						<td colspan="5" style="height: 30px;"></td>
					</tr>
						--%>
					<tr>
						<th style="height: 60px;">
							备注
						</th>
						<td colspan="5" style="height: 30px;">
							${dimissionLog.zhengyi_content}
						</td>
					</tr>
					<tr>
						<th style="height: 40px;">
							人力资源签字
						</th>
						<td colspan="5" style="height: 30px;"></td>
					</tr>
					<tr>
						<th colspan="6" align="center" style="height: 30px;">
							审批状态
						</th>
					</tr>
					<tr>
						<th style="height: 35px;">
							序号
						</th>
						<th>
							部门
						</th>
						<th>
							姓名
						</th>
						<th>
							状态
						</th>
						<th colspan="2">
							审批时间
						</th>
					</tr>
					<tr>
						<s:iterator value="list" id="execut" status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="left">
							</s:if>
							<s:else>
								<tr align="left">
							</s:else>
							<td align="center" style="height: 30px;">
								<s:if test="#pageStatus.index%2==1">
									<font color="#000000"></font>
								</s:if>
								<s:else>
									<font color="#ff0000"></font>
								</s:else>
								<s:property value="#pageStatus.index+1" />
							</td>
							<td align="center">
								${execut.auditUserDept}
							</td>
							<td align="center">
								${execut.auditUserName}
							</td>
							<td align="center">
							<s:if test="#execut.auditStatus=='同意'&&#execut.auditStatus!=null">
								${execut.auditStatus}
							</s:if>
							</td>
							<td align="center" colspan="2">
								${execut.auditDateTime}
							</td>
						</s:iterator>
					</tr>
				</table>
			</div>
			<s:if
				test="dimissionLog.add_dimissTime_status=='已填写'&&dimissionLog.add_dimissTime_status!=null">
				<div align="center">
					<input type="submit" value="打印" onclick="pagePrint('lzshenq','sy')"
						style="width: 80px; height: 50px;" />
				</div>
			</s:if>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
