<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
				<table>
					<tr>
						<th colspan="6">
							<font size="5">详细信息</font>
							<a href="AssetAction!findShouList.action">返回</a>
						</th>
					</tr>
					<tr>
						<th>
							资产编号
						</th>
						<td>
							<s:property value="aesset.taassetsnumber" />
						</td>
						<th>
							资产名称
						</th>
						<td>
							<s:property value="aesset.taassetsname" />
						</td>
						<th>
							资产类别
						</th>
						<td>
							<s:property value="aesset.taassetsclass" />
						</td>
					</tr>

					<tr>
						<th>
							资产用途
						</th>
						<td>
							<s:property value="aesset.taassetsuse" />
						</td>
						<th>
							折旧方法
						</th>
						<td>
							<s:property value="aesset.tademolitionmethods" />
						</td>
						<th>
							资产原值
						</th>
						<td>
							<s:property value="aesset.taassetscost" />
						</td>
					</tr>

					<tr>
						<th>
							累计折旧
						</th>
						<td>
							<s:property value="aesset.taaccumulateddepreciation" />
						</td>
						<th>
							资产净值
						</th>
						<td>
							<s:property value="aesset.taassetsNetworth" />
						</td>
						<th>
							资产数量
						</th>
						<td>
							<s:property value="aesset.taassetsQuantity" />
						</td>
					</tr>

					<tr>
						<th>
							年限
						</th>
						<td>
							<s:property value="aesset.tayearsof" />
						</td>
						<th>
							月折旧率(%)
						</th>
						<td>
							<s:property value="aesset.tamonthlydepreciation" />
						</td>
						<th>
							月折旧额(%)
						</th>
						<td>
							<s:property value="aesset.tayuezhejiudepreciation" />
						</td>
					</tr>

					<tr>
						<th>
							年折旧率(%)
						</th>
						<td>
							<s:property value="aesset.taannualrate" />
						</td>
						<th>
							年折旧额(%)
						</th>
						<td>
							<s:property value="aesset.tanianzhejiudepreciation" />
						</td>
						<th>
							规格型号
						</th>
						<td>
							<s:property value="aesset.taspecificationsmodel" />
						</td>
					</tr>

					<tr>
						<th>
							开始使用日期
						</th>
						<td>
							<s:property value="aesset.tastartdatetime" />
						</td>
						<th>
							编号
						</th>
						<td>
							<s:property value="aesset.tanumber" />
						</td>
						<th>
							使用对象
						</th>
						<tD>
							<s:property value="aesset.tausingobject" />
						</tD>
					</tr>
					<tr>
						<th>
							状态
						</th>
						<td colspan="5">
							<s:property value="aesset.tastatus" />
						</td>
					</tr>
					<s:if test="%{aesset.tastatus=='报废'}">
						<tr>
							<th>
								设备型号
							</th>
							<td>
								<s:property value="aesset.taequipmentModel" />
							</td>
							<th>
								制造厂家
							</th>
							<td>
								<s:property value="aesset.tamanufacturer" />
							</td>
							<th>
								出厂编号
							</th>
							<td>
								<s:property value="aesset.tafactorynumber" />
							</td>
						</tr>
						<tr>
							<th>
								出厂日期
							</th>
							<td>
								<s:property value="aesset.tafactorydate" />
							</td>
							<th>
								安装地点
							</th>
							<td>
								<s:property value="aesset.tainstallationsite" />
							</td>
							<th>
								安装时间
							</th>
							<td>
								<s:property value="aesset.tainstallationdate" />
							</td>
						</tr>
						<tr>
							<th>
								报废原因及根据
							</th>
							<td colspan="5" style="height: 80px;">
								${aesset.tascrappedwilling}
							</td>
						</tr>
					</s:if>
					<tr>
						<th>
							合同附件
						</th>
						<td colspan="5">
							<a href=FileViewAction.action?FilePath=${aessetFilePath}>查看合同</a>
						</td>
					</tr>

					<tr>
						<th>
							备注
						</th>
						<td colspan="5" style="height: 80px;">
							<s:property value="aesset.taremarks" />
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
