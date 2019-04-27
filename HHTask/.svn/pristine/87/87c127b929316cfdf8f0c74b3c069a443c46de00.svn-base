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
		<script type="text/javascript">
		</script>
	</head>
	<body>
			<table class="table">
				<tr align="center">
					<td colspan="4" style="font-size: 20px;">
						报价明细表 (${zhuserOffer.cmp})
					</td>
				</tr>
				<tr>
					<td align="right">
						件号：
					</td>
					<td>
						${zhuserOffer.markId}
						
					</td>
					<td align="right">
						供料属性：
					</td>
					<td>
						${zhuserOffer.kgliao}
						
					</td>
				</tr>
				<tr>
					<td align="right">
						名称：
					</td>
					<td>
						${zhuserOffer.name}
						
					</td>
					<td align="right">
						规格：
					</td>
					<td>
						${zhuserOffer.specification}
						
					</td>
				</tr>
				<tr>
					<td align="right">
						物料类别：
					</td>
					<td>
						${zhuserOffer.wgType}
						
					</td>
					<td align="right">
						报价：
					</td>
					<td>
						<s:else>
						${zhuserOffer.taxprice}
							
						</s:else>
						(税率)
						<br />
						${zhuserOffer.hsPrice}
						含税价)
						<br/>
						${zhuserOffer.bhsPrice}
						(不含税价)
					</td>
				</tr>
			</table>
	</body>
</html>
