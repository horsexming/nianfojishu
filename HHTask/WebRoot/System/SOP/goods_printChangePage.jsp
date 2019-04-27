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
 h3{
 	padding: 0 !important;
 	margin: 0 !important;
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
 #gongneng{
 	overflow: hidden !important;
 }
.table th,.table td {
	border: solid #000;
	border-width: 1 1px 1px 1;
	padding: 0px;
}
		</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="PrintedOutAction_addprint.action" method="post" >
					<h3>${companyInfo.name}</h3>
					<h3>${companyInfo.englishName}</h3>
					<h3>库存调拨单(调出)</h3>
					<br/>
					<div class="row">
						<div class="col-xs-9 text-left">
							REV:01
						</div>
						<div class="col-xs-3">
							QP750500-U
						</div>
					</div>
					<div class="row">
						<div class="col-xs-5 text-left">
							编&nbsp;&nbsp;&nbsp;号: ${printedOutOrder.planNum }
						</div>
						<div class="col-xs-4 text-left">
							日&nbsp;&nbsp;&nbsp;期: ${printedOutOrder.riqi}
						</div>
						<div class="col-xs-3">
							调拨类别:${printedOutOrder.dbStyle }
						</div>
					</div>
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
								调出仓库
							</td>
							<td>
								调出仓区
							</td>
							<td>
								调出库位
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
								日期
							</td>
							<td>
								出库类型
							</td>
						</tr>
						<s:iterator value="listSell" id="pageList" status="statusSdf">
							<tr>
								<td>
									${statusSdf.index+1}
								</td>
								<td>
									${pageList.sellMarkId}
									<s:if test='#pageList.ywmarkId!=null'>
										(<font color="green">${pageList.ywmarkId}</font> )
									</s:if>	
								</td>
								<td>
									${pageList.sellGoods}
								</td>
								<td>
									${pageList.sellFormat}
								</td>
								<td>
									${pageList.banBenNumber}
								</td>
								<td>
									${pageList.sellUnit}
								</td>
								<td>
									${pageList.sellCount}
								</td>
								<td>
									${pageList.sellWarehouse}
								</td>
								<td>
									${pageList.goodHouseName}
								</td>
								<td>
									${pageList.kuwei}
								</td>
								<td>
									${pageList.goodsStoreWarehouse}
								</td>
								<td>
									${pageList.goodsStorehouseName}
								</td>
								<td>
									${pageList.goodsStorePosition}
								</td>
								<td>
									${pageList.sellTime }
								</td>
								<td>
									${pageList.style }
								</td>
							</tr>
						</s:iterator>
					</table>
					<div class="row">
						<div class="col-xs-2 text-left">仓库主管:<input type="text" size="10" value="${printedOutOrder.ckzg }" id="ckzg"></div>
						<div class="col-xs-2 text-left">验收:<input type="text" size="10" value="${printedOutOrder.yanShou }" id="yanShou"></div>
						<div class="col-xs-2 text-left">仓管:<input type="text" size="10" value="${printedOutOrder.cgName }" id="cgName"></div>
						<div class="col-xs-2 text-left">部门主管:<input type="text" size="10" value="${printedOutOrder.bmzg }" id="bmzg"></div>
						<div class="col-xs-2 text-left">物料员:<input type="text" size="10" value="${printedOutOrder.wlUsers }" id="wlUsers"></div>
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
				$("#ckzg").attr('size',$("#ckzg").val().length);
				
				
				$("#yanShou").css('border','none');
				$("#yanShou").attr('size',$("#yanShou").val().length);
				
				$("#cgName").css('border','none');
				$("#cgName").attr('size',$("#cgName").val().length);
				
				$("#bmzg").css('border','none');
				$("#bmzg").attr('size',$("#bmzg").val().length);
				
				$("#wlUsers").css('border','none');
				$("#wlUsers").attr('size',$("#wlUsers").val().length);
				
				if(selected!=null && selected!=""){
					var ckzg = $("#ckzg").val();
					var yanShou = $("#yanShou").val();
					var cgName = $("#cgName").val();
					var bmzg = $("#bmzg").val();
					var wlUsers = $("#wlUsers").val();
					$.ajax({
						type:"post",
						url:"sellAction!updateChangeSellPrintStatus.action",
						data:{
							"flag":selected,
							"printedOutOrder.planNum":"${printedOutOrder.planNum}",
							"printedOutOrder.riqi":"${printedOutOrder.riqi}",
							"printedOutOrder.dbStyle":"${printedOutOrder.dbStyle}",
							"printedOutOrder.ckzg":ckzg,
							"printedOutOrder.yanShou":yanShou,
							"printedOutOrder.cgName":cgName,
							"printedOutOrder.bmzg":bmzg,
							"printedOutOrder.wlUsers":wlUsers,
							"printedOutOrder.addUsers":"${Users.name }"
						},
						dataType:"json",
						success:function(data){
							if(data=="true"){
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
