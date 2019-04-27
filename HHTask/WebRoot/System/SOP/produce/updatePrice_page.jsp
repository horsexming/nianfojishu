<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<title>${waigouOrder.planNumber}</title>
		
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<table class="table" border=1
						style="border-collapse: collapse;  border: 1px solid #000000;"
						align=center cellpadding=3 >
						<tr>
							<th>
								供应商
							</th>
							<th >
								送货单号
							</th>
							<th >
								件号
							</th>
							<th >
								业务件号
							</th>	
							<th>
								零件名称
							</th>
							<th>
								版本
							</th>
							<th>
								供货属性
							</th>
							<th>
								单位
							</th>
							<th>
								含税单价(元)	、不含税单价(元)、税率
							</th>
						</tr>
						<tr>
							<td> ${waigouPlan.gysName}</td>
							<td>${waigouOrder.planNumber}</td>
							<td>${waigouPlan.markId}</td>
							<td>${waigouPlan.ywmarkId}</td>
							<td>${waigouPlan.proName}</td>
							<td>${waigouPlan.banben}</td>
							<td>${waigouPlan.kgliao}</td>
							<td>${waigouPlan.unit}</td>
							<td>${waigouPlan.hsPrice}/${waigouPlan.price}/${waigouPlan.taxprice}</td>
						</tr>		
					</table>
					<hr/>
					<hr/>
					<table class="table" border=1
						style="border-collapse: collapse;  border: 1px solid #000000;"
						align=center cellpadding=3 >
						<tr style="background-color:green">
							<th colspan="7" align="center" >原价格</th>
						</tr>
						<tr>
							<th>
								供应商
							</th>
							<th >
								件号
							</th>
								
							<th>
								零件名称
							</th>
							<th>
								版本
							</th>
							<th>
								供货属性
							</th>
							<th>
								单位
							</th>
							<th>
								含税单价(元)	、不含税单价(元)、税率
							</th>
						</tr>
						<tr>
							<td>${waigouPlan.gysName}</td>
							<td>${waigouPlan.markId}</td>
							<td>${waigouPlan.proName}</td>
							<td>${waigouPlan.banben}</td>
							<td>${waigouPlan.kgliao}</td>
							<td>${waigouPlan.unit}</td>
							<td>${waigouPlan.hsPrice}/${waigouPlan.price}/${waigouPlan.taxprice}</td>
						</tr>		
					</table>
					<table class="table" border=1
						style="border-collapse: collapse;  border: 1px solid #000000;"
						align=center cellpadding=3 >
						<tr style="background-color:red">
							<td colspan="4" align="center">刷新之后的价格</td>
						</tr>
						<tr>
							<th>
								件号
							</th>
							<th >
								版本
							</th>
							<th>
								名称
							</th>
							<th>
								含税单价(元)	、不含税单价(元)、税率
							</th>
						</tr>
						<tr>
							<td>${price.partNumber}</td>
							<td>${price.banbenhao}</td>
							<td>${price.name}</td>
							<td>${price.hsPrice}/${price.bhsPrice}/${price.taxprice}</td>
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

