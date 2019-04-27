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
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h2>
					${companyInfo.shortName}出门凭证
				</h2>
				<input style="width: 80px; font-size: 18px;" onclick="printStick()"
					type="button" value="打印">
				<br />
				<br />

				<div id="printDiv" style="width: 365px;; height: 272">
				
				<style type="text/css">
		table {
				border-collapse:collapse; /* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
				border:solid #030303; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
				border-width:2px 2px 2px 2px; /* 设置边框状粗细：上 右 下 左 = 对应：1px 0 0 1px */
				//font-size:13px;
		}
		table th,table td {border:solid #030303;border-width:1px 1px 1px 1px;padding:2px;}
		</style>
					<table border="1"
						style="font-size: 13px; font-weight: bold; width: 360px; height: 270">
						
						<tr>
							<td style="padding-top: 0px; margin-left: 2px; height: 60px;">
								<img alt="honghu" widtn="64px;" height="55px;"
									src="<%=basePath%>${companyInfo.logoOKjpg}" />
							</td>
							<td colspan="3"
								style="font-size: 16px; font-weight: bold; center; vertical-align: middle;">

								${companyInfo.shortName} 出门凭证
								<span style="width: 6px;"></span>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="height: 46px;">
								<img
									src="<%=request.getContextPath()%>/barcode.action?msg=<s:property value="iaoApply.barcode" />&type=code128"
									height="50px" width="360px" />
								</div>

							</td>
						</tr>
						<tr>
							<td style="width: 65px;">
								进出人
							</td>
							<td style="width: 125px;">
								${iaoApply.username }
							</td>
							<td style="width: 60px;">
								出入对象
							</td>
							<td style="width: 110px;">
								${iaoApply.iaoPersonTyle }
							</td>
						</tr>
						<tr>
							<td>
								出入类别
							</td>
							<td>
								${iaoApply.iaoStyle }
							</td>
							<td>
								出门时间
							</td>
							<td>
								${iaoApply.applyOutTime }
							</td>
						</tr>
						<tr>
							<td>
								车牌号
							</td>
							<td>
								${iaoApply.plateNum }
							</td>
							<td>
								携带物品
							</td>
							<td>
								${iaoApply.carryGoods }
							</td>
						</tr>
							<tr>
								<td>
									说明
								</td>
								<td colspan="3">
									${iaoApply.result}
								</td>

							</tr>
						

					</table>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function printStick(){
			pagePrint('printDiv');
			var id=${sticker.id }
			$.ajax({
					type : "POST",
					url : "iaoAction!print.action",
					data : {
						id : id
					},
					dataType : "json",
					success : function(msg) {
					}
				});		
		}
		</SCRIPT>
	</body>
</html>
