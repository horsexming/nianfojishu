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
		<center>
			<table class="table">
				<tr>
					<th>序号</th>
					<th>
						物品名称
					</th>
					<th>
						物品规格
					</th>
					<th>
						物品单位
					</th>
					
					<th>
						历史单价(含税)
					</th>
					<th>
						历史单价(不含税)
					</th>
					<th>
						入库时间
					</th>
				</tr>
				<s:if test="list.size()>0">
					<s:iterator id="procut" value="list" status="asd">
						<tr>		
							<td align="center">
								<s:property value="#asd.index+1" />
							</td>		
							<td align="center">
								${procut.goodsStoreGoodsName }
							</td>
							<td align="center">
								${procut.goodsStoreFormat }
							</td>
							<td align="center">
								${procut.goodsStoreUnit }
							</td>
							<td align="center">
								${procut.hsPrice}
							</td>
							<td align="center">
								${procut.goodsStorePrice}
							</td>
							<td align="center">
								${procut.goodsStoreDate}
							</td>
						</tr>
					</s:iterator>
				</s:if>
				<s:else>
					<td colspan="6" style="color: red;font-size:13px;font-weight:bold">
					没有该物品的历史参照单价！
					</td>
				</s:else>
		</center>
	</body>
</html>
