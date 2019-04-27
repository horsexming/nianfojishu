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
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">

				<form action="Detail_getMultipleDetail.action" method="post">
				
					<table class="table" align="center">
						<tr>
							<th>
								序号
							</th>
							<th>
								产品名称
							</th>
							<th>
								数量
							</th>
							<th>
								月份
							</th>
							<th>
								件号
							</th>
							<th>
								规格
							</th>
							<th>
								操作
							</th>
						</tr>
						
						<s:iterator value="#session.detailItems" id="d" status="st">
							<tr>
								<td><s:property value="#st.index+1"/> </td>
								<td>
									<input type="hidden" name="details[<s:property value="#st.index"/>].id" value="<s:property value="#d.value.id" />" />
									<s:property value="#d.value.templet.name" />
								</td>
								<td>
									<s:property value="#d.value.quantity" />
								</td>
								<td>
									<s:property value="#d.value.month" />
								</td>
								<td>
									<s:property value="#d.value.partsNumber" />
								</td>
								<td>
									<s:property value="#d.value.specification" />
								</td>
								<td>
									<a href="Detail_deleteItem.action?detail.id=${d.key}" onclick="if(confirm('确实要删除该记录吗?')) return true; return false;">删除</a>
								</td>
							</tr>
						</s:iterator>
						<tfoot>
							<tr>
								<td colspan="8" align="center">
									<input style="width: 80px; height: 50px;" type="submit" value="查看配件">
								</td>
							</tr>
						</tfoot>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>

</html>
