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
@media  screen{

}
 @media print {
            INPUT {
                display: none;
            }
        }
        
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

<%--
#shenhe { --%> <%--
	position: absolute; --%> <%--
	top: 50px; --%> <%--
	right: 10px; --%> <%--
	z-index: 10; --%> <%--
	transform: rotate(12deg); --%> <%--
	opacity: 1.0;
	--%>
	<%--
}

--%>
.table th,.table td {
	border: solid #000;
	border-width: 1 1px 1px 1;
	padding: 0px;
}
</style>
	</head>
	<body style="background: url('');">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">

			<div align="center">
				<div id="printDiv">
					<div align="center" style="font-weight: bolder;">
						<table width="100%" border="1" id="mytable"; 
							style="border-collapse: collapse; font-size: 8px; font-family: 黑体;">
						<thead style="display: table-header-group;  ">
							<tr>
								<td>
									<img src="${companyInfo.logoOKjpg}" height="40px" width="80px">
								</td>

								<td colspan="15"
									style="width: 100%; text-align: center; font-size: 25; font-weight: bold;">
									${companyInfo.name}———入库记录详单
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th align="center">
									件号
								</th>
								<th align="center">
									批次
								</th>
								<th align="center">
									业务件号
								</th>
								<th align="center">
									规格
								</th>
								<th align="center">
									品名
								</th>
								<th align="center">
									数量
								</th>
								<th align="center">
									单位
								</th>
								<th align="center">
									支数
								</th>
								<th align="center">
									仓库
								</th>
								<th align="center">
									库位
								</th>
								<th align="center">
									客户
								</th>
								<th align="center">
									供应商
								</th>
								<th align="center">
									入库类型
								</th>
								<th align="center">
									入库日期
								</th>
								<th align="center">
									备注
								</th>
							</tr>
							<s:iterator value="list" id="pageList" status="pageSat">
							<s:if test="#pageSat.index%15==0 && #pageSat.index>0">
							</s:if>
								<tr align="center" style="page-break-after:always;">
									<td>
										<input type="hidden" id="sellId_${pageSat.index}"
											value="${pageList.goodsStoreId}">
										${pageList.goodsStoreMarkId}
									</td>
									<td>
										${pageList.goodsStoreLot}
									</td>
									<td>
										${pageList.ywmarkId}
									</td>
									<td align="left" style="width: 60px">
										${pageList.goodsStoreFormat}
									</td>
									<td align="left" style="width: 60px">
										${pageList.goodsStoreGoodsName}
									</td>
									<td>
										${pageList.goodsStoreCount}
									</td>
									<td>
										${pageList.goodsStoreUnit}
									</td>
									<td>
										${pageList.goodsStoreZhishu}
									</td>
									<td>
										${pageList.goodsStoreWarehouse}
									</td>
									<td>
										${pageList.goodsStorePosition}
									</td>
									<td>
										${pageList.goodsStoreCompanyName}
									</td>
									<td>
										${pageList.goodsStoreSupplier}
									</td>
									<td>
										${pageList.style}
									</td>
									<td>
										${pageList.goodsStoreDate}
									</td>
									<td>
									</td>
								</tr>
							</s:iterator>
						</tbody>
						<tfoot style="display: table-footer-group;">
							<tr>
								<td>
									<img src="${companyInfo.logoOKjpg}" height="40px" width="80px">
								</td>

								<td colspan="15"
									style="width: 100%; text-align: center; font-size: 25; font-weight: bold;">
									${companyInfo.name}———入库记录详单
								</td>
							</tr>
						</tfoot>
						</table>
						<br>
						<div style="font-family: 黑体; font-size: 14px;">
							物料员:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							品管:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							仓库:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							部门主管:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							制单:${Users.name}
						</div>
					</div>
					<input type="button" value=" 打 印 " onclick="javascript: window.print();">
				</div>
<%--				<input type="button" value="打印" onclick="aaa()" />--%>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
function aaa() {
	pagePrint('printDiv');
	var listLength = "<s:property value='list.size()' />";
	for ( var i = 0; i < listLength; i++) {
		var sellId = $("#sellId_" + i).val();
		$.ajax( {
			type : "POST",
			url : "GoodsStoreAction!print.action",
			data : {
				id : sellId
			},
			dataType : "json",
			success : function(msg) {
			}
		});

	}

}

$(function(){
	$("th").dblclick(function(){
        var tdclass =	$(this).attr("class")
	var cellIndex =	this.cellIndex;
	if(tdclass!='notprintTd'){
		$(this).addClass("notprintTd");
		$(this).parent().parent().find("tr th").each(function(){
			if(this.cellIndex === cellIndex ){
				$(this).addClass("notprintTd");
			}
		})
	}else{
		$(this).removeClass();
		$(this).parent().parent().find("tr th").each(function(){
			if(this.cellIndex === cellIndex ){
				$(this).removeClass();
			}
		})
	}
    });
});
</script>
	</body>
</html>
