<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
		<div>

			<div align="center">
				<h3>
					项目需求单明细
					<br />
					(project order detail)
				</h3>
				<div align="center" id="printDiv">
					<table width="100%" border="1" style="border-collapse: collapse;">
						<tr>
							<td align="center" rowspan="2">
								<img src="<%=path%>/upload/file/sysImages/logoOK.jpg"
									style="width: 100px; height: 120px" />
							</td>
							<td align="center" rowspan="2" colspan="7">
								<h1>
									产 品 试 制 需 求 单
								</h1>
							</td>
							<td align="center" colspan="2">
								试制单编号
							</td>
							<td align="center" colspan="2">
								客户名称
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								${projectOrderVo.orderNO}
							</td>
							<td align="center" colspan="2">
								${projectOrderVo.cusName}
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								试制项目名称
							</td>
							<td align="center" colspan="2">
								${projectOrderVo.proName}
							</td>
							<td align="center" colspan="2">
								产品工程师
							</td>
							<td align="center" colspan="2">
								${projectOrderVo.productEngineer}
							</td>
							<td align="center" colspan="2">
								技术工程师
							</td>
							<td align="center" colspan="2">
								${projectOrderVo.technicalEngineer}
							</td>
						</tr>
						<tr>
							<td align="center">
								序 号
							</td>
							<td align="center">
								件 号
							</td>
							<td align="center">
								名 称
							</td>
							<td align="center">
								数 量
							</td>
							<td align="center">
								说 明
							</td>
							<td align="center" colspan="4">
								交付时间、地点及接受人
							</td>
							<td align="center" colspan="3">
								备注
							</td>
						</tr>
						<s:if
							test="projectOrderVo==null||projectOrderVo.projectOrderPart.size()==0">
							<tr align="center">
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td colspan="4">
									${projectOrderVo.deliveryInfo}
								</td>
								<td colspan="4" rowspan="4">
									${projectOrderVo.remark}
								</td>
							</tr>
							<tr align="center">
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td colspan="4">
									产品处理决定
								</td>
							</tr>
							<tr align="center">
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td colspan="3">
									1.冲抵生产计划，入库销售
								</td>
								<s:if test="projectOrderVo.deal=='1'.toString()">
									<td>
										是
									</td>
								</s:if>
								<s:else>
									<td></td>
								</s:else>
							</tr>
							<tr align="center">
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td colspan="3">
									2.试验用，不予入库
								</td>
								<s:if test="projectOrderVo.deal=='2'.toString()">
									<td>
										是
									</td>
								</s:if>
								<s:else>
									<td></td>
								</s:else>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="projectOrderVo.projectOrderPart" id="part"
								status="pageStatus">
								<tr align="center">
									<s:if test="#pageStatus.index==0">
										<td>
											<s:property value="#pageStatus.index+1" />
										</td>
										<td>
											${part.markId}
										</td>
										<td>
											${part.partName}
										</td>
										<td>
											${part.partNum}
										</td>
										<td>
											${part.remark}
										</td>
										<td colspan="4"
											rowspan="<s:property value="projectOrderVo.projectOrderPart.size()"/>">
											${projectOrderVo.deliveryInfo}
										</td>
										<td colspan="4"
											rowspan="<s:property value="projectOrderVo.projectOrderPart.size()+3"/>">
											${projectOrderVo.remark}
										</td>
									</s:if>
									<s:else>
										<td>
											<s:property value="#pageStatus.index+0" />
										</td>
										<td>
											${part.markId}
										</td>
										<td>
											${part.partName}
										</td>
										<td>
											${part.partNum}
										</td>
										<td>
											${part.remark}
										</td>
									</s:else>
								</tr>
							</s:iterator>
							<tr align="center">
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td colspan="4">
									产品处理决定
								</td>
							</tr>
							<tr align="center">
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td colspan="3">
									1.冲抵生产计划，入库销售
								</td>
								<s:if test="projectOrderVo.deal=='1'.toString()">
									<td>
										是
									</td>
								</s:if>
								<s:else>
									<td></td>
								</s:else>
							</tr>
							<tr align="center">
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td>
									/
								</td>
								<td colspan="3">
									2.试验用，不予入库
								</td>
								<s:if test="projectOrderVo.deal=='2'.toString()">
									<td>
										是
									</td>
								</s:if>
								<s:else>
									<td></td>
								</s:else>
							</tr>
						</s:else>
						<tr>
							<td colspan="12">
								注：1、图纸由资料室统一发放
								2、本表单一式三联：第一联技术开发部存，第二、三联计划供应部存，第三联随产品发出，产品接收人签收后传真返回计划供应部存档
							</td>
						</tr>
						<tr>
							<td rowspan="2" align="center">
								编制
							</td>
							<td rowspan="2" colspan="1" align="center">
								${projectOrderVo.userName}
							</td>
							<td rowspan="2" align="center">
								审核
							</td>
							<td rowspan="2" colspan="3" align="center">
								<s:if test="projectOrderVo.status=='同意'">
									<s:if
										test="projectOrderVo!=null&&projectOrderVo.qianmingUrl!=null&&projectOrderVo.qianmingUrl.size()>1">
										<s:iterator value="projectOrderVo.qianmingUrl" id="qmUrl"
											begin="0" end="projectOrderVo.qianmingUrl.size()-2">
											<img src="<%=path%><s:property value="#qmUrl"/>"
												style="width: 50px; height: 50px" />
										</s:iterator>
									</s:if>
									<s:elseif
										test="projectOrderVo!=null&&projectOrderVo.qianmingUrl!=null&&projectOrderVo.qianmingUrl.size()==1">
										<img
											src="<%=path%><s:property value="projectOrderVo.qianmingUrl.get(0)"/>"
											style="width: 50px; height: 50px" />
									</s:elseif>
									<!-- 
                    <s:if test="projectOrderVo!=null&&projectOrderVo.qianmingUrl.size()>0">
				 <img src="<%=path%><s:property value="projectOrderVo.qianmingUrl.get(0)"/>" style="width: 50px;height: 50px"/>
				 </s:if>
				  -->
								</s:if>
							</td>
							<td rowspan="2" align="center">
								批准
								<s:if test="projectOrderVo.status=='同意'">
									<s:if
										test="projectOrderVo!=null&&projectOrderVo.qianmingUrl!=null&&projectOrderVo.qianmingUrl.size()>0">
										<img
											src="<%=path%><s:property value="projectOrderVo.qianmingUrl.get(projectOrderVo.qianmingUrl.size()-1)"/>"
											style="width: 50px; height: 50px" />
									</s:if>
								</s:if>
							</td>
							<td rowspan="2" colspan="2" align="center">
								${projectOrderVo.status}
							</td>
							<td colspan="2" align="center">
								签收人
							</td>
							<td>

							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								试制班周期确认
							</td>
							<td align="center">
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div align="center">
			<s:if test="projectOrderVo.status=='同意'">
				<input type="button" class="input" value="打印"
					onclick="pagePrint('printDiv')">
			</s:if>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
</script>
	</body>
</html>
