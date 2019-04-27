<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
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
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="left">

			</div>

			<div align="center">
				<form action="AssetAction!updateAsset.action" method="post" enctype="multipart/form-data">
					<input type="hidden" name="aesset.id" value="${aesset.id}">
					<table>
						<tR>
							<td colspan="6" align="center">
								<font size="5">修改固定资产</font>
							</td>
						</tR>
						<tr>
							<th>
								资产编号
							</th>
							<td>
								<s:textfield name="aesset.taassetsnumber" />
							</td>
							<th>
								资产名称
							</th>
							<td>
								<s:textfield name="aesset.taassetsname" />
							</td>
							<th>
								资产类别
							</th>
							<td>
								<s:textfield name="aesset.taassetsclass" />
							</td>
						</tr>

						<tr>
							<th>
								资产用途
							</th>
							<td>
								<s:textfield name="aesset.taassetsuse" />
							</td>
							<th>
								折旧方法
							</th>
							<td>
								<s:textfield name="aesset.tademolitionmethods" />
							</td>
							<th>
								资产原值
							</th>
							<td>
								<s:textfield name="aesset.taassetscost" />
							</td>
						</tr>

						<tr>
							<th>
								累计折旧
							</th>
							<td>
								<s:textfield name="aesset.taaccumulateddepreciation" />
							</td>
							<th>
								资产净值
							</th>
							<td>
								<s:textfield name="aesset.taassetsNetworth" />
							</td>
							<th>
								资产数量
							</th>
							<td>
								<s:textfield name="aesset.taassetsQuantity" />
							</td>
						</tr>

						<tr>
							<th>
								年限
							</th>
							<td>
								<s:textfield name="aesset.tayearsof" />
							</td>
							<th>
								月折旧率(%)
							</th>
							<td>
								<s:textfield name="aesset.tamonthlydepreciation" />
							</td>
							<th>
								月折旧额(%)
							</th>
							<td>
								<s:textfield name="aesset.tayuezhejiudepreciation" />
							</td>
						</tr>

						<tr>
							<th>
								年折旧率(%)
							</th>
							<td>
								<s:textfield name="aesset.taannualrate" />
							</td>
							<th>
								年折旧额(%)
							</th>
							<td>
								<s:textfield name="aesset.tanianzhejiudepreciation" />
							</td>
							<th>
								规格型号
							</th>
							<td>
								<s:textfield name="aesset.taspecificationsmodel" />
							</td>
						</tr>

						<tr>
							<th>
								开始使用日期
							</th>
							<td>
								<s:textfield name="aesset.tastartdatetime" />
								<th>
									编号
								</th>
								<td>
									<s:textfield name="aesset.tanumber" />
								</td>
								<th>
									使用对象
								</th>
								<tD>
									<s:textfield name="aesset.tausingobject" />
								</tD>
						</tr>

						<tr>
							<th>
								状态
							</th>
							<td colspan="5">
								<select name="aesset.tastatus">
									<option value="${aesset.tastatus}">
										${aesset.tastatus}
									</option>
									<option value="正常使用">
										正常使用
									</option>
									<option value="报废">
										报废
									</option>
									<option value="停用">
										停用
									</option>
									<option value="租用">
										租用
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								合同附件
							</th>
							<td colspan="5">
								<input type="file" name="assetAttachment"/>
							</td>
						</tr>

						<tr>
							<th>
								备注
							</th>
							<td colspan="5">
								<input type="text" value="${aesset.taremarks}"
									name="aesset.taremarks" style="width: 300px; height: 50px;" />
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="确  定" />
								&nbsp;&nbsp;&nbsp;
								<input type="reset" value="取  消" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
