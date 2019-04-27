<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<style type="text/css">
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
	</head>
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				
			</div>
			
			<div align="center">
				<form action="AssetAction!mohufindShoList.action" method="post">
					<table>
						<tr>
							<th colspan="6">
								<font size="5">显示所有固定资产信息</font>
							</th>
						</tr>
						<tr>
							<th>
								资产编号
							</th>
							<td>
								<input type="text" name="assets.taassetsnumber" />
							</td>
							<th>
								资产名称
							</th>
							<td>
								<input type="text" name="assets.taassetsname" />
							</td>
							<th>
								资产类别
							</th>
							<td>
								<input type="text" name="assets.taassetsclass" />
							</td>
						</tr>
						<tr>
							<th>
								使用对象	
							</th>
							<td>
								<input type="text" name="assets.tausingobject" />
							</td>
							<th>
								状态
							</th>
							<td colspan="3">
								<select name="assets.tastatus">
									<option value=""></option>
									<option value="正常使用">
										正常使用
									</option>
									<option value="停用">
										停用
									</option>
									<option value="报废">
										报废
									</option>
								</select>
								&nbsp;&nbsp;
								<input type="submit" value="确  定" />
								&nbsp;&nbsp;
								<a href="AssetAction!findShouList.action">查询所有信息</a>
							</td>
						</tr>
					</table>
				</form>

				<table>
					<tr>
						<th>
							序号
						</th>
						<th>
							资产编号
						</th>
						<th>
							资产名称
						</th>
						<th>
							资产类别
						</th>
						<th>
							资产用途
						</th>
						<th>
							资产原值
						</th>
						<th>
							资产净值
						</th>
						<th>
							资产数量
						</th>
						<th>
							规格型号
						</th>
						<th>
							使用对象
						</th>
						<th>
							开始时间
						</th>
						<th>
							状态
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator id="ass" value="aessetlist">
						<tr>
							<td align="center">
								${ass.id}
							</td>
							<td>
								${ass.taassetsnumber}
							</td>
							<!-- 资产编号 -->
							<td>
								${ass.taassetsname}
							</td>
							<!-- 资产名称 -->
							<td>
								${ass.taassetsclass}
							</td>
							<!--资产类别  -->
							<td>
								${ass.taassetsuse}
							</td>
							<!--资产用途  -->
							<td>
								${ass.taassetscost}
							</td>
							<!--资产原值 -->
							<td>
								${ass.taassetsNetworth}
							</td>
							<!-- 资产净值-->
							<td>
								${ass.taassetsQuantity}
							</td>
							<!--资产数量  -->
							<td>
								${ass.taspecificationsmodel}
							</td>
							<!-- 规格型号  -->
							<td>
								${ass.tausingobject}
							</td>
							<!-- 使用对象 -->
							<td>
								${ass.tastartdatetime}
							</td>
							<!-- 开始时间 -->
							<s:if test="tastatus=='报废'">
								<td align="center">
									<font color="red">${ass.tastatus}</font>
								</td>
							</s:if>
							<s:else>
								<td>
									${ass.tastatus}
								</td>
							</s:else>
							<td>
								<s:if test="tastatus=='报废'">
									<font color="gray">报废</font>
								</s:if>
								<s:else>
									<a href="AssetAction!updateAssetstatus.action?id=${ass.id}"
										onClick="return window.confirm('确定要报废选中的信息吗？')">报废</a>
								</s:else>
								&nbsp;
								<a href="AssetAction!updateAssetFindID.action?id=${ass.id}">修改</a>&nbsp;
								<a href="AssetAction!findByID.action?id=${ass.id}">查看详细</a>
							</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="16" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />
						</td>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
