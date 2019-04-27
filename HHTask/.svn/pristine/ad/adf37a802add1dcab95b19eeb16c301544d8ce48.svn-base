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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<div id="printDiv">
					<p style="font-size: 20px;">
						${companyInfo.name}
					</p>
					<p style="font-size: 20px;">
						${companyInfo.englishName}
					</p>
					<p style="font-size: 20px;">
						销售出库单
					</p>
					<br/>
					<table class="table">
						<tr>
							<th colspan="8" align="left" style="border:hidden;">
								&nbsp;&nbsp;&nbsp;&nbsp;送货地点:${poor.shaddress}
							</th>
							<th colspan="2" align="left"style="border:hidden;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:
							</th>
						</tr>
						<tr>
							<th colspan="3" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;购货单位:
								<input type="text" value="${printedOutOrder.kehuNmae}"
									readonly="readonly" name="poor.kehuNmae" />
							</th>
							<th colspan="5" align="left" style="border-left:hidden; border-right: hidden;">
								&nbsp;&nbsp;&nbsp;&nbsp;送货单号:
								<input type="text" value="${printedOutOrder.shPlanNum}"
									name="poor.shPlanNum" />
							</th>
							<th colspan="2" align="left" style="border-right: hidden;">
								&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;期:
								<input type="text" value="${printedOutOrder.riqi}" class="Wdate"
									name="poor.riqi"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</th>
						</tr>
						<tr>
							<th>
								序号
							</th>
							<th>
								业务件号
							</th>
							<th>
								产品名称
							</th>
							<th>
								规格型号
							</th>
							<th>
								单位
							</th>
							<th>
								数量
							</th>
							<th>
								外部订单号
							</th>
							<th>
								仓区
							</th>
							<th>
								内部订单号
							</th>
							<th>
								备注
							</th>
						</tr>
						<s:iterator value="list1" id="pageList" status="statusSdf">
							<tr>
								<th>
									${statusSdf.index+1}
								</th>
								<th>
									${pageList.ywmarkId}
								</th>
								<th>
									${pageList.proNmae}
								</th>
								<th>
									${pageList.format}
								</th>
								<th>
									${pageList.unit}
								</th>
								<th>
									${pageList.num}
								</th>
								<th>
									${pageList.waiOrderNum}
								</th>
								<th>
									${pageList.cangqu}
								</th>
								<th>
									${pageList.neiOrderNum}
								</th>
								<th>
									<input type="text" value=""
										name="poor.printedOutList[${statusSdf.index}].reamk" />
								</th>
							</tr>
						</s:iterator>
						<tr style="border-left: hidden; border-right: hidden;">
							<th align="left" colspan="10">
								审核:
								<input type="text" value="" name="poor.reviewers" />
								&nbsp;&nbsp;&nbsp;&nbsp; 业务员:
								<input type="text" value="" name="poor.ywName" />
								&nbsp;&nbsp;&nbsp;&nbsp; 仓管:
								<input type="text" value="" name="poor.cgName" />
								&nbsp;&nbsp;&nbsp;&nbsp; 品管:
								<input type="text" value="" name="poor.pgName" />
								&nbsp;&nbsp;&nbsp;&nbsp; 制单:
								<input type="text" value="${Users.name}" name="poor.addUsers" />
							</th>
						</tr>
						<tr style="border: hidden;">
							<th colspan="10" align="left">
								第一白联：仓库&nbsp;&nbsp;文员&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								第二红联：财务&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								第二黄联：仓管
							</th>
						</tr>
					</table>
					</div>
					<input type="submit" value="打印" class="input" onclick="todisabled(this)"/>
				</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
