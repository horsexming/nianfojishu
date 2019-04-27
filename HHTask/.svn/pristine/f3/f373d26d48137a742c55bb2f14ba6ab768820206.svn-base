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
		<SCRIPT type="text/javascript">
			$(function (){
				var procardStyle="${procardTemplate.procardStyle}"
				if(procardStyle=="总成"||procardStyle=="组合"){
					$("#showZong").show();
				}else if(procardStyle=="自制"){
					$("#showZi").show();
				}else if(procardStyle=="外购"){
					$("#showWai").show();
				}
			});
		</SCRIPT>
	</head>
	<body>
		<center>
			<div id="showZong" style="border: solid #000 1px; display: none;">
				<div align="center" style="border-bottom: solid #000 1px;">
					工 艺 流 水 卡 片
				</div>
				<div style="font-weight: bolder;">
					名称:${procardTemplate.proName}
					&nbsp;&nbsp;件号:${procardTemplate.markId} &nbsp;&nbsp;
					卡片类型:${procardTemplate.procardStyle} &nbsp;&nbsp;
					产品类型:${procardTemplate.productStyle} &nbsp;&nbsp;
					车型:${procardTemplate.carStyle}&nbsp;&nbsp;
					<hr />
					<div style="font-size: 14px; color: red;">
						单班时长：${procardTemplate.singleDuration}(h)&nbsp;&nbsp;
						客户需求产能:${procardTemplate.needCapacity}(件)&nbsp;&nbsp;
						产能:${procardTemplate.capacity}(件)&nbsp;&nbsp;
						单件节拍:${procardTemplate.allJiepai}(s)&nbsp;&nbsp;
						延误时长:${procardTemplate.deliveryDuration}(h)
					</div>
				</div>
				<table class="table" style="width: 100%;">
					<tr>
						<th colspan="2" style="color: red;">
							单批数量
						</th>
						<th colspan="3" style="color: red;">
							${procardTemplate.maxCount} ${procardTemplate.unit}
						</th>
						<th colspan="3">
							数量(权值)
						</th>
						<th colspan="4">
							1 :
							${procardTemplate.corrCount==null?0:procardTemplate.corrCount}
						</th>
					</tr>
					<tr align="center">
						<td colspan="4">
							零组件
						</td>
						<td colspan="3">
							名称
						</td>
						<td>
							数量
						</td>
						<td colspan="4">
							卡片类型
						</td>

					</tr>
					<s:iterator value="procardTemplateList" id="pageProcardTem">
						<tr align="center">
							<th colspan="4">
								${pageProcardTem.markId}
							</th>
							<th colspan="3">
								${pageProcardTem.proName}
							</th>
							<th>
								${pageProcardTem.corrCount}
							</th>
							<th colspan="4">
								${pageProcardTem.procardStyle}
							</th>
						</tr>
					</s:iterator>
					<tr>
						<th colspan="12">
							&nbsp;
						</th>
					</tr>
					<tr>
						<th colspan="12">
							&nbsp;
						</th>
					</tr>
					<tr align="center">
						<th>
							工序号
						</th>
						<th>
							名称
						</th>
						<th>
							总节拍(s)
						</th>
						<th>
							生产类型
						</th>
						<th>
							是否并行
						</th>
						<th>
							产能
						</th>
						<th>
							产能盈余
						</th>
						<th>
							产能比
						</th>
						<th>
							延误时长(h)
						</th>
						<th>
							配送周期
						</th>
						<th>
							送货量
						</th>
						<th>
							总成单班生产时长
						</th>
					</tr>
					<s:iterator value="list" id="pageProcessTem">
						<tr align="center">
							<th>
								${pageProcessTem.processNO}
							</th>
							<th>
								${pageProcessTem.processName}
							</th>
							<th>
								${pageProcessTem.allJiepai}
							</th>
							<th>
								${pageProcessTem.productStyle}
							</th>
							<th>
								${pageProcessTem.processStatus}
							</th>
							<th>
								${pageProcessTem.capacity}
							</th>
							<s:if test="#pageProcessTem.capacitySurplus<0">
								<th bgcolor="red">
							</s:if>
							<s:else>
								<th>
							</s:else>
							${pageProcessTem.capacitySurplus}
							</th>
							<th>
								${pageProcessTem.capacityRatio}
							</th>
							<th>
								${pageProcessTem.deliveryDuration}
							</th>
							<th>
								<s:if test="#pageProcessTem.deliveryPeriod!=null">
									${pageProcessTem.deliveryPeriod}天/次
								</s:if>
							</th>
							<th>
								${pageProcessTem.deliveryAmount}
							</th>
							<th>
								${pageProcessTem.proSingleDuration}
							</th>
						</tr>
					</s:iterator>
				</table>
				<input type="button" value="转入精益BOM" onclick="gotoJYBOM(${procardTemplate.rootId})"/>
			</div>
			<div id="showZi" style="border: solid #000 1px; display: none;">
				<div align="center" style="border-bottom: solid #000 1px;">
					工 艺 流 水 卡 片
				</div>
				<div style="font-weight: bolder;">
					名称:${procardTemplate.proName}
					&nbsp;&nbsp;件号:${procardTemplate.markId} &nbsp;&nbsp;
					卡片类型:${procardTemplate.procardStyle} &nbsp;&nbsp;
					产品类型:${procardTemplate.productStyle} &nbsp;&nbsp;
					车型:${procardTemplate.carStyle}&nbsp;&nbsp;
					<hr />
					<div style="font-size: 14px; color: red; font-weight: bolder;">
						单班时长：${procardTemplate.singleDuration}(h)&nbsp;&nbsp;
						客户需求产能:${procardTemplate.needCapacity}(件)&nbsp;&nbsp;
						产能:${procardTemplate.capacity}(件)&nbsp;&nbsp;
						单件节拍:${procardTemplate.allJiepai}(s)&nbsp;&nbsp;
						延误时长:${procardTemplate.deliveryDuration}(h)
					</div>
				</div>
				<table class="table" style="width: 100%;">
					<tr>
						<th colspan="3" width="40%" style="color: red;">
							单批数量: ${procardTemplate.maxCount}${procardTemplate.unit}
						</th>
						<th colspan="9" width="50%">
							数量(权值) 1 :
							${procardTemplate.corrCount==null?0:procardTemplate.corrCount}
						</th>
					</tr>
					<tr>
						<td rowspan="5" style="width: 5px;" align="center">
							原
							<br />
							<br />
							材
							<br />
							<br />
							料
						</td>
						<td width="15%">
							牌号
						</td>
						<td width="15%">
							${procardTemplate.trademark}
						</td>
						<td rowspan="5" align="center" width="10%">
							备
							<br />
							<br />
							<br />
							注
						</td>
						<td rowspan="5" colspan="8">
							${procardTemplate.remark}
						</td>
					</tr>
					<tr>
						<td>
							规格
						</td>
						<td>
							${procardTemplate.specification}
						</td>
					</tr>
					<tr>
						<td>
							数量(权值)
						</td>
						<td>
							${procardTemplate.quanzi1} : ${procardTemplate.quanzi2}
						</td>
					</tr>
					<tr>
						<td>
							炉号
						</td>
						<td>
							${procardTemplate.luhao}
						</td>
					</tr>
					<tr>
						<td>
							编号
						</td>
						<td>
							${procardTemplate.number}
						</td>
					</tr>
					<tr>
						<td colspan="12">
							&nbsp;
						</td>
					</tr>
					<tr align="center">
						<th>
							工序号
						</th>
						<th>
							名称
						</th>
						<th>
							总节拍(s)
						</th>
						<th>
							生产类型
						</th>
						<th>
							是否并行
						</th>
						<th>
							产能
						</th>
						<th>
							产能盈余
						</th>
						<th>
							产能比
						</th>
						<th>
							延误时长(h)
						</th>
						<th>
							配送周期
						</th>
						<th>
							送货量
						</th>
						<th>
							总成单班生产时长
						</th>
					</tr>
					<s:iterator value="list" id="pageProcessTem">
						<tr align="center">
							<th>
								${pageProcessTem.processNO}
							</th>
							<th>
								${pageProcessTem.processName}
							</th>
							<th>
								${pageProcessTem.allJiepai}
							</th>
							<th>
								${pageProcessTem.productStyle}
							</th>
							<th>
								${pageProcessTem.processStatus}
							</th>
							<th>
								${pageProcessTem.capacity}
							</th>
							<s:if test="#pageProcessTem.capacitySurplus<0">
								<th bgcolor="red">
							</s:if>
							<s:else>
								<th>
							</s:else>
							${pageProcessTem.capacitySurplus}
							</th>
							<th>
								${pageProcessTem.capacityRatio}
							</th>
							<th>
								${pageProcessTem.deliveryDuration}
							</th>
							<th>
								<s:if test="#pageProcessTem.deliveryPeriod!=null">
									${pageProcessTem.deliveryPeriod}天/次
								</s:if>
							</th>
							<th>
								${pageProcessTem.deliveryAmount}
							</th>
							<th>
								${pageProcessTem.proSingleDuration}
							</th>
						</tr>
					</s:iterator>
				</table>
			</div>
			<div id="showWai" style="border: solid #000 1px; display: none;">
				<div align="center" style="border-bottom: solid #000 1px;">
					工 艺 流 水 卡 片
				</div>
				<div style="font-size: 14px; color: red; font-weight: bolder;">
					单班时长：${procardTemplate.singleDuration}(h)&nbsp;&nbsp;
					产能:${procardTemplate.capacity}(件)&nbsp;&nbsp;
					产能盈余:${procardTemplate.capacitySurplus}(件)&nbsp;&nbsp;
					单件节拍:${procardTemplate.allJiepai}(s)
					<hr />
					产能比:${procardTemplate.capacityRatio}&nbsp;&nbsp;
					配送时长(h):${procardTemplate.deliveryDuration}&nbsp;&nbsp;
					延误时长(h):${procardTemplate.proSingleDuration}&nbsp;&nbsp;
					配送周期:${procardTemplate.deliveryPeriod}天/次&nbsp;&nbsp;
					送货量:${procardTemplate.deliveryAmount} 供应商:${procardTemplate.gys}
				</div>
				<table class="table" style="width: 100%;">
					<tr>
						<th align="right" style="width: 25%;">
							件号:
						</th>
						<td>
							${procardTemplate.markId}
						</td>
					</tr>
					<tr>
						<th align="right">
							名称:
						</th>
						<td>
							${procardTemplate.proName}
						</td>
					</tr>
					<tr>
						<th align="right">
							车型:
						</th>
						<td>
							${procardTemplate.carStyle}
						</td>
					</tr>
					<tr>
						<th align="right">
							卡片类型:
						</th>
						<td>
							${procardTemplate.procardStyle}
						</td>
					</tr>
					<tr>
						<th align="right">
							单位:
						</th>
						<td>
							${procardTemplate.unit}
						</td>
					</tr>
					<tr>
						<th align="right">
							数量:
						</th>
						<td>
							${procardTemplate.maxCount}
						</td>
					</tr>
					<tr>
						<th align="right">
							权值:
						</th>
						<td>
							${procardTemplate.quanzi1} : ${procardTemplate.quanzi2}
						</td>
					</tr>
					<tr>
						<th align="right">
							产品类型:
						</th>
						<td>
							${procardTemplate.productStyle}
						</td>
					</tr>
				</table>
			</div>
		</center>
		<script type="text/javascript">
$(function() {
	getUnit("danwei");
	getUnit("danwei2");
})
function gotoJYBOM(rootId){
	$.ajax({
		type : "POST",
		url : "ProcardTemplateAction!gotoJYBOM.action",
		dataType : "json",
		data : {
		 id : rootId
		},
		success : function(data){
			if(data.success){
				alert("进入精益BOM成功！");
			}else{
				alert(data.message);
			}
		}
	});
}
</script>
	</body>
</html>
