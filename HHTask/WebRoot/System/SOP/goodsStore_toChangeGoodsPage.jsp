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
<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"></script>
 <script type="text/javascript"
 		src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<%--  <script src="${pageContext.request.contextPath}/js/myJquery.js"></script> --%>
<script type="text/javascript">
// 现在window.$和window.jQuery是3.2.1版本:
console.log($().jquery); // => '3.2.1'
var $jq = jQuery.noConflict(true);
// 现在window.$和window.jQuery被恢复成1.5版本:
console.log($().jquery); // => '1.5.0'

</script>
<style type="text/css" media="print">
.noprintsss { display:none;}
</style>
<style type="text/css">
@media screen {
	.notprint {
		display: inline;
		cursor: pointer;
	}
	.notprintTd {
		background-color: #d0d0d0;
	}
	.notprintTr {
		background-color: #d0d0d0;
	}
}

.text1 {
	width: 120px;
	overflow: hidden;
	text-overflow: ellipsis;
}

.text2 {
	width: 80px;
	overflow: hidden;
	text-overflow: ellipsis;
}

 .table{
 	padding: 0 !important;
 	margin: 0 !important;
 }
 h3{
 	padding: 0 !important;
 	margin: 0 !important;
 }
 th{
 	padding: 0 !important;
 	margin: 0 !important;
 }
 #gongneng{
 	overflow: hidden !important;
 }
 .col-xs-2{
 	padding-right: 0;
 }
	</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<form action="PrintedOutAction_addprint.action" method="post" >
					<h3>${companyInfo.name}</h3>
					<h3>${companyInfo.englishName}</h3>
					<h3>库存调拨单(调入)</h3>
					<br/>
					<table class="table" style="border: none;" align="left">
						<tr>
							<th style="border: none;">REV:01</th>
							<th style="border: none;"></th>
							<th style="border: none;">QP750500-U</th>
						</tr>
						<tr>
							<th style="border: none;width: 40%">
								编&nbsp;&nbsp;&nbsp;号:${poor.planNum }
							</th>
							<th style="border: none;width: 40%;">
								日&nbsp;&nbsp;&nbsp;期:${poor.riqi }
							</th>
							<th style="border: none;width: 20%">
								调拨类别:${poor.dbStyle}
							</th>
						</tr>
					</table>
					<table class="table">
						<tr>
							<td>
								序号
							</td>
							<td>
								件号
							</td>
							<td>
								产品名称
							</td>
							<td>
								规格
							</td>
							<td>
								版本
							</td>
							<td>
								单位
							</td>
							<td>
								数量
							</td>
							<td>
								调入仓库
							</td>
							<td>
								调入仓区
							</td>
							<td>
								调入库位
							</td>
							<td>
								调出仓库
							</td>
							<td>
								调出仓区
							</td>
							<td>
								调出库位
							</td>
							<td>
								日期
							</td>
							<td>
								出库类型
							</td>
						</tr>
						<s:iterator value="goodsStoreList" id="unGS" status="pageStatus">
							<tr>
								<td>${pageStatus.index+1 }</td>
								<td>
									${unGS.goodsStoreMarkId}<br>
									<s:if test='#unGS.ywmarkId!=null'>
										(<font color="green">${unGS.ywmarkId}</font> )
									</s:if>	
								</td>
								<td>${unGS.goodsStoreGoodsName}</td>
								<td>${unGS.goodsStoreFormat}</td>
								<td>${unGS.banBenNumber }</td>
								<td>${unGS.goodsStoreUnit}</td>
								<td align="right">${unGS.goodsStoreCount}</td>
								<td>${unGS.goodsStoreWarehouse}</td>
								<td>${unGS.goodHouseName}</td>
								<td>${unGS.goodsStorePosition}</td>
								<td style="background-color: lime">${unGS.sellWarehouse}</td>
								<td style="background-color: lime">${unGS.sellHouseName}</td>
								<td style="background-color: lime">${unGS.sellKuwei}</td>
								<td>${unGS.goodsStoreDate}</td>
								<td>${unGS.style}</td>
							</tr>
						</s:iterator>
					</table>
					<div class="row">
						<div class="col-xs-2 text-left">仓库主管:<input type="text" size="10" value="${poor.ckzg }" id="ckzg"></div>
						<div class="col-xs-2 text-left">验收:<input type="text" size="10" value="${poor.yanShou }" id="yanShou"></div>
						<div class="col-xs-2 text-left">仓管:<input type="text" size="10" value="${poor.cgName }" id="cgName"></div>
						<div class="col-xs-2 text-left">部门主管:<input type="text" size="10" value="${poor.bmzg }" id="bmzg"></div>
						<div class="col-xs-2 text-left">物料员:<input type="text" size="10" value="${poor.wlUsers }" id="wlUsers"></div>
						<div class="col-xs-2 text-left">制单:${Users.name }</div>
					</div>
					<div class="row">
						<div class="col-xs-2 text-left">
							白联: 仓管
						</div>
						<div class="col-xs-2 text-left">
							红联: 财务
						</div>
						<div class="col-xs-2 text-left">
							黄联: 物料员
						</div>
					</div>
					<input type="button" value="打印" class="input noprintsss" onclick="updatePrintStatus('${flag}');"/>			
				</div>
			</form>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			function updatePrintStatus(selected){
				$("#ckzg").css('border','none');
				$("#yanShou").css('border','none');
				$("#cgName").css('border','none');
				$("#bmzg").css('border','none');
				$("#wlUsers").css('border','none');
				if(selected!=null && selected!=""){
					var ckzg = $("#ckzg").val();
					var yanShou = $("#yanShou").val();
					var cgName = $("#cgName").val();
					var bmzg = $("#bmzg").val();
					var wlUsers = $("#wlUsers").val();
					$.ajax({
						type:"post",
						url:"GoodsStoreAction!updateChangeGoods.action",
						data:{
							"flag":selected,
							"poor.planNum":"${poor.planNum}",
							"poor.riqi":"${poor.riqi}",
							"poor.dbStyle":"${poor.dbStyle}",
							"poor.ckzg":ckzg,
							"poor.yanShou":yanShou,
							"poor.cgName":cgName,
							"poor.bmzg":bmzg,
							"poor.wlUsers":wlUsers,
							"poor.addUsers":"${Users.name }"
							
						},
						dataType:"json",
						success:function(data){
							if(data=="true"){
								//pagePrint("print",null);
								window.print();
							}else{
								alert(data);
							}
						},
						error:function(){
							alert("更新状态异常");
						}
						
					});
				}
			}
		</script>
	</body>
</html>
