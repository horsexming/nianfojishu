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
				var procardStyle="${procard.procardStyle}"
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
					名称:${procard.proName} &nbsp;&nbsp;件号:${procard.markId} &nbsp;&nbsp;
					卡片类型:${procard.procardStyle} &nbsp;&nbsp;
					产品类型:${procard.productStyle} &nbsp;&nbsp;
					车型:${procard.carStyle}&nbsp;&nbsp;

				</div>
				<table class="table" style="width: 100%;">
					<tr>
						<th>
							最大数量
						</th>
						<th>
							${procard.maxCount} ${procard.unit}
						</th>
						<th>
							实际数量
						</th>
						<th>
							${procard.filnalCount}
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr align="center">
						<td>
							零组件
						</td>
						<td>
							名称
						</td>
						<td>
							数量
						</td>
						<td>
							卡片类型
						</td>
						<th>
							&nbsp;
						</th>
					</tr>
					<s:iterator value="procardList" id="pageProcardTem">
						<tr align="center">
							<th>
								${pageProcardTem.markId}
							</th>
							<th>
								${pageProcardTem.proName}
							</th>
							<th>
								${pageProcardTem.corrCount}
							</th>
							<th>
								${pageProcardTem.procardStyle}
							</th>
							<th>
								&nbsp;
							</th>
						</tr>
					</s:iterator>
					<tr>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
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
								${pageProcessTem.opshebeijiepai+pageProcessTem.opcaozuojiepai}
							</th>
							<th>
								${pageProcessTem.productStyle}
							</th>
							<th>
								${pageProcessTem.processStatus}
							</th>
						</tr>
					</s:iterator>
				</table>
			</div>
			<div id="showZi" style="border: solid #000 1px; display: none;">
				<div align="center" style="border-bottom: solid #000 1px;">
					工 艺 流 水 卡 片
				</div>
				<div style="font-weight: bolder;">
					名称:${procard.proName} &nbsp;&nbsp;件号:${procard.markId} &nbsp;&nbsp;
					卡片类型:${procard.procardStyle} &nbsp;&nbsp;
					产品类型:${procard.productStyle} &nbsp;&nbsp;
					车型:${procard.carStyle}&nbsp;&nbsp;
				</div>
				<table class="table" style="width: 100%;">
					<tr>
						<th colspan="3" width="40%">
							数量: ${procard.maxCount}${procard.unit}
						</th>
						<th colspan="2" width="50%">
							实际数量: ${procard.filnalCount}
						</th>
					</tr>
					<tr>
						<td rowspan="7" style="width: 5px;" align="center">
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
							${procard.trademark}
						</td>
						<td rowspan="6" align="center" width="10%">
							备
							<br />
							<br />
							<br />
							注
						</td>
						<td rowspan="7">
							${procard.remark}
						</td>
					</tr>
					<tr>
						<td>
							规格
						</td>
						<td>
							${procard.specification}
						</td>
					</tr>
					<tr>
						<td>
							原材料名称
						</td>
						<td>
							${procard.yuanName}
						</td>
					</tr>
					<tr>
						<td>
							数量
						</td>
						<td>
							${procard.needCount}
						</td>
					</tr>
					<tr>
						<td>
							单位
						</td>
						<td>
							${procard.yuanUnit}
						</td>
					</tr>
					<tr>
						<td>
							炉号
						</td>
						<td>
							${procard.luhao}
						</td>
					</tr>
					<tr>
						<td>
							编号
						</td>
						<td>
							${procard.number}
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td>
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
								${pageProcessTem.opshebeijiepai+pageProcessTem.opcaozuojiepai}
							</th>
							<th>
								${pageProcessTem.productStyle}
							</th>
							<th>
								${pageProcessTem.processStatus}
							</th>
						</tr>
					</s:iterator>
				</table>
			</div>
			<div id="showWai" style="border: solid #000 1px; display: none;">
				<div align="center" style="border-bottom: solid #000 1px;">
					工 艺 流 水 卡 片
				</div>
				<table class="table" style="width: 100%;">
					<tr>
						<th align="right" style="width: 25%;">
							件号:
						</th>
						<td>
							${procard.markId}
						</td>
					</tr>
					<tr>
						<th align="right">
							名称:
						</th>
						<td>
							${procard.proName}
						</td>
					</tr>
					<tr>
						<th align="right">
							车型:
						</th>
						<td>
							${procard.carStyle}
						</td>
					</tr>
					<tr>
						<th align="right">
							卡片类型:
						</th>
						<td>
							${procard.procardStyle}
						</td>
					</tr>
					<tr>
						<th align="right">
							单位:
						</th>
						<td>
							${procard.unit}
						</td>
					</tr>
					<tr>
						<th align="right">
							数量:
						</th>
						<td>
							${procard.filnalCount}
						</td>
					</tr>
					<tr>
						<th align="right">
							产品类型:
						</th>
						<td>
							${procard.productStyle}
						</td>
					</tr>
				</table>
			</div>
		</center>
	</body>
</html>
