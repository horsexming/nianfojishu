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
			<div id="jiaojie" align="center"
				style="width: 756px; height: 1086px; border: 0px solid #000000;">
				<table class="table" align="center">
					<tr>
						<th align="center" colspan="10"
							style="font-size: 20px; height: 70px;">
							<%--<img alt="loge" src="<%=path%>/images/zhaobiao.jpg"
								style="width: 30px; height: 30px;">
							--%>
							<img width="45px" height="45px;"
								src="<%=basePath%>${companyInfo.logoOKjpg}" align="center"></img>
							&nbsp;&nbsp;&nbsp;&nbsp;${companyInfo.name}员工离职交接单
							<input type="hidden" name="dimissionHandover.id"
								value="${dimissionHandover.id}}">
						</th>
					</tr>
					<tr>
						<th align="right" colspan="10" style="height: 25px;">
							离职交接单编号：${dimissionHandover.dimission_number}&nbsp;&nbsp;&nbsp;&nbsp;
						</th>
					</tr>
					<tr>
						<th style="height: 40px; width: 75px;">
							姓名
						</th>
						<td colspan="2" align="center">
							${dimissionHandover.name}
						</td>
						<th align="center">
							部门
						</th>
						<td colspan="2" align="center">
							${dimissionHandover.dept}
						</td>
						<th colspan="2" align="center">
							止薪日期
						</th>
						<td colspan="2" align="center">
							${dimissionLog.app_time}
						</td>
					</tr>
					<tr>
						<th align="center" style="height: 40px; width: 75px;">
							离职原因
						</th>
						<td align="left" colspan="9">
							&nbsp;${dimissionHandover.dimission_Reason}
						</td>
					</tr>

					<tr>
						<th align="center" style="height: 40px; width: 75px;">
							交接内容
						</th>
					</tr>
					<tr>
						<td align="left" colspan="10" style="font-size: 9px;">
							1、技术资料室：借阅技术资料是否已归还&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、总经办档案室：借阅文档资料是否已归还
							<br />
							3、物流工具库：借用工装模具等是否已归还&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							4、物流综合库：领用的工作服和其它工具等是否已归还
							<br />
							5、本部门：项目和本职工作是否移交完成&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							6、信息部：领用的通讯工具、计算机、员工卡等是否已归还
							<br />
							7、物业：是否已结清租住集体宿舍的相关费用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							8、财务部：是否有欠款或赔付项目
							<br />
							9、人力资源：(1)是否完成离职体检
							&nbsp;&nbsp;(2)是否有根据培训协议需要赔偿的项目&nbsp;&nbsp;(3)《员工手册》是否归还
						</td>
					</tr>
				</table>
				<s:if test="dimissionHandover.lzjj_status!=null">
					<table class="table" align="center">
						<tr>
							<th colspan="10" align="center" style="height: 30px;">
								交接完成
							</th>
						</tr>
						<tr>
							<th style="height: 35px;">
								序号
							</th>
							<th colspan="2">
								部门
							</th>
							<th colspan="2">
								姓名
							</th>
							<th colspan="1">
								状态
							</th>
							<th colspan="3">
								是否有需要赔偿项目
								<br />
								(交接内容)
							</th>
							<th colspan="1">
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
								<td align="center" colspan="2">
									${execut.auditUserDept}
								</td>
								<td align="center" colspan="2">
									${execut.auditUserName}
								</td>
								<td align="center" colspan="1">
									<s:if
										test="#execut.auditStatus=='同意'&&#execut.auditStatus!=null">
									${execut.auditStatus}
								</s:if>
								</td>
								<td align="center" colspan="3">
									${execut.auditOpinion}
								</td>
								<td align="center" colspan="1">
									${execut.auditDateTime}
								</td>
							</s:iterator>
						</tr>

						<%--
						<tr>
							<th colspan="4" align="center">
								生产部
							</th>
							<th colspan="4" align="center">
								其它相关部门
							</th>
							<th colspan="2" align="center">
								财务部
							</th>
						</tr>
						<tr>
							<th align="center" style="height: 50px; width: 75px;">
								总成库
							</th>
							<td align="center" style="width: 75px;">
								李惠明
							</td>
							<th align="center" style="height: 50px; width: 75px;">
								组合夹具库
							</th>
							<td align="center" style="height: 50px; width: 75px;">
								孙百庆
							</td>
							<th rowspan="2" align="center" style="height: 50px; width: 75px;">
								资料室
							</th>
							<td rowspan="2" align="center" style="height: 50px; width: 75px;">
								王建
							</td>
							<th rowspan="2" align="center" style="height: 50px; width: 75px;">
								计算机室
							</th>
							<td rowspan="2" align="center" style="height: 50px; width: 75px;">
								刘培
							</td>
							<th rowspan="2" align="center" style="height: 50px; width: 75px;">
								财务室
							</th>
							<td rowspan="2" align="center" style="height: 50px; width: 75px;">
								张欣
							</td>
						</tr>
						<tr>
							<th align="center" style="height: 50px;">
								工具库
							</th>
							<td align="center">
								杨红
							</td>
							<th align="center">
								综合库
							</th>
							<td align="center">
								杨红
							</td>
						</tr>
						<tr>
							<th align="center" style="height: 50px; width: 75px;">
								物流主管
							</th>
							<td colspan="3" align="center">
								武丽
							</td>
							<th rowspan="2" align="center">
								档案室
							</th>
							<td rowspan="2" align="center">
								徐海燕
							</td>
							<th rowspan="2" align="center">
								信息主管
							</th>
							<td rowspan="2" align="center">
								贾辉辉
							</td>
							<th rowspan="2" align="center">
								财务主管
							</th>
							<td rowspan="2" align="center">
								范海英
							</td>
						</tr>
						<tr>
							<th colspan="4" align="center" style="height: 50px;">
								本部门工作移交
							</th>
						</tr>
						<tr>
							<th align="center" style="height: 50px; width: 75px;">
								移交接收
							</th>
							<td align="center"></td>
							<th align="center">
								本部门主管
							</th>
							<td align="center"></td>
							<th align="center">
								物业宿舍
							</th>
							<td colspan="3" align="center">
								张春毅
							</td>
							<th align="center">
								总经办主任
							</th>
							<td align="center">
								张春毅
							</td>
						</tr>
						<tr>
							<th align="center" style="height: 50px; width: 75px;">
								是否需要 离职体检
							</th>
							<td align="center"></td>
							<th align="center">
								有无赔付事项
							</th>
							<td colspan="3" align="center"></td>
							<th align="center">
								员工卡交回签收
							</th>
							<th align="center">
								员工手册 交回签收
							</th>
							<th colspan="2" align="center">
								人力资源主管
							</th>
						</tr>
						<tr>
							<th align="center" style="height: 50px; width: 75px;">
								备注
							</th>
							<td colspan="5" align="center"></td>
							<td align="center"></td>
							<td align="center"></td>
							<td colspan="2" align="center">
								张海平
							</td>
						</tr>--%>

					</table>
					<br />
					<br />
					<div align="right" style="height: 60px;">
						人力资源签字：__________________&nbsp;
					</div>
				</s:if>
			</div>
			<s:if test="dimissionHandover.lzjj_status!=null">
				<div align="center">
					<input type="submit" value="打印" onclick="pagePrint('jiaojie','sy')"
						style="width: 80px; height: 50px;" />
				</div>
			</s:if>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
