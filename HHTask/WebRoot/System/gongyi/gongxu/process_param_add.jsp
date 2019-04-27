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
		<style type="text/css">
			.table{
				border:0px solid #999;
				border-width: 1px;
				border-collapse:collapse;
			}
			.table th,.table td {
				border-width: 1px;
				padding: 0px;
			}
			
			.subTable{
				text-align: center;
				border-collapse:collapse;
				width: 100%;
				border-width:1px; 
				border-style:hidden;
			}
		</style>
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
					
				</div>
			</div>
			
			<div align="center">
				<form action="processDataAction!updateProcessParam.action" method="post"
					style="">
					<input type="hidden" id="id" name="processParam.id" value="${processParam.id}"/>
					<table border="0" width="100%" class="table" style="text-align: center;">
						<tr><td>材料牌号及厚度</td><td><input type="text" id="cailiaoPaihaoHoudu" name="processParam.cailiaoPaihaoHoudu" value="${processParam.cailiaoPaihaoHoudu}"/></td></tr>
						<tr><td>焊料名称直径</td><td><input type="text" id="hanliaoMingchengZhijing" name="processParam.hanliaoMingchengZhijing" value="${processParam.hanliaoMingchengZhijing}"/></td></tr>
						<tr><td>气体流量</td><td><input type="text" id="qitiLiuliang" name="processParam.qitiLiuliang" value="${processParam.qitiLiuliang}"/></td></tr>
						<tr><td>电流强度</td><td><input type="text" id="dianliuQiangdu" name="processParam.dianliuQiangdu" value="${processParam.dianliuQiangdu}"/></td></tr>
						<tr><td>保护气体</td><td><input type="text" id="baohuQiti" name="processParam.baohuQiti" value="${processParam.baohuQiti}"/></td></tr>
						<tr><td>钨丝直径</td><td><input type="text" id="wusiZhijing" name="processParam.wusiZhijing" value="${processParam.wusiZhijing}"/></td></tr>
						<tr><td>送丝速度</td><td><input type="text" id="songsiSudu" name="processParam.songsiSudu" value="${processParam.songsiSudu}"/></td></tr>
						<tr><td>电弧电压</td><td><input type="text" id="dianhuDianya" name="processParam.dianhuDianya" value="${processParam.dianhuDianya}"/></td></tr>
						<tr><td>附注</td><td><input type="text" id="remark" name="processParam.remark" value="${processParam.remark}"/></td></tr>
						<tr>
							<td align="center" colspan="2" >
								<input type="submit" value="修改"
									style="width: 100px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<script type="text/javascript">
$(function(){
	
});
</script>
