<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<center>
			<div id="showZong" style="border: solid #000 1px;">
				<div align="center"
					style="border-bottom: solid #000 1px; font-weight: bolder;">
					工 艺 规范
				</div>
				<div style="font-weight: bolder;">
					名称:${quotedPrice.proName} &nbsp;&nbsp;件号:${quotedPrice.markId}
					&nbsp;&nbsp; 卡片类型:${quotedPrice.procardStyle} &nbsp;&nbsp;
					产品类型:${quotedPrice.productStyle} &nbsp;&nbsp;
					车型:${quotedPrice.carStyle}&nbsp;&nbsp;
				</div>
				<table class="table" style="width: 100%;">
					<tr>
						<th>
							数量(权值)
						</th>
						<th>
							1 : ${quotedPrice.corrCount==null?1:quotedPrice.corrCount}
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr align="center">
						<td style="width: 150px">
							零组件
						</td>
						<td>
							名称
						</td>
						<td>
							数量
						</td>
						<td>
							卡片类型
						</td>
						<th>
							&nbsp;
						</th>

					</tr>
					<s:iterator value="quotedPriceList" id="pageQuotedPrice">
						<tr align="center">
							<th>
								${pageQuotedPrice.markId}
							</th>
							<th>
								${pageQuotedPrice.proName}
							</th>
							<th>
								${pageQuotedPrice.filnalCount}
							</th>
							<th>
								${pageQuotedPrice.procardStyle}
							</th>
							<th>
								&nbsp;
							</th>
						</tr>
					</s:iterator>
					<tr>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr>
						<td rowspan="3" style="width: 5px;" align="center">
							原
							<br />
							<br />
							材
							<br />
							<br />
							料
						</td>
						<td width="15%">
							牌号
						</td>
						<td width="15%">
							${quotedPrice.trademark}
						</td>
						<td rowspan="3" align="center" width="10%">
							备
							<br />
							<br />
							<br />
							注
						</td>
						<td rowspan="3">
							${quotedPrice.remark}
						</td>
					</tr>
					<tr>
						<td>
							规格
						</td>
						<td>
							${quotedPrice.specification}
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
				<table class="table" style="width: 100%;">
					<tr>
						<th colspan="10">
							设备信息录入、核对
						</th>
					</tr>
					<tr align="center">
						<th>
							工序号
						</th>
						<th>
							名称
						</th>
						<th>
							设备编号
						</th>
						<th>
							设备名称
						</th>
						<th>
							设备时长(秒)
						</th>
						<th>
							设备净值
						</th>
						<th>
							剩余折旧年限
						</th>
						<th>
							设备折旧(含税)
						</th>
					</tr>
					<s:iterator value="list" id="pageQpInfor">
						<form action="QuotedPrice_updateDeptLuru.action" method="post"
							style="margin: 0px; padding: 0px;">
							<tr align="center">
								<td>
									<input name="pageStatus" value="${pageStatus}" type="hidden" />
									<input name="quotedPrice.id" value="${quotedPrice.id}"
										type="hidden" />
									<input name="qpInfor.id" value="${pageQpInfor.id}"
										type="hidden" />
									${pageQpInfor.processNO}
								</td>
								<td>
									${pageQpInfor.processName}
								</td>
								<s:if test="#pageQpInfor.shebeiNo!=null">
									<th>
										<%--<input name="qpInfor.shebeiNo" value="${pageQpInfor.shebeiNo}"
											style="width: 60px;" />
									--%>
										${pageQpInfor.shebeiNo}
									</th>
									<th>
										<%--<input name="qpInfor.shebeiName"
											value="${pageQpInfor.shebeiName}" style="width: 60px;" />
									--%>
										${pageQpInfor.shebeiName}
									</th>
									<th>
										<input name="qpInfor.shebeiDateTime"
											value="${pageQpInfor.shebeiDateTime}" style="width: 60px;" />
									</th>
									<th>
										<input name="qpInfor.shebeiJingzhi"
											value="${pageQpInfor.shebeiJingzhi}" style="width: 60px;" />
									</th>
									<th>
										<input name="qpInfor.shebeiZjYear"
											value="${pageQpInfor.shebeiZjYear}" style="width: 60px;" />
									</th>
									<th>
										${pageQpInfor.shebeiZhejiu}
									</th>
									<th>
										<input type="submit" value="确定" />
									</th>
								</s:if>
							</tr>
						</form>
					</s:iterator>
				</table>
			</div>
		</center>
	</body>
</html>
