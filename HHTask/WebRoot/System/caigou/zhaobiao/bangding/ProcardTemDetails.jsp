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
				var procardStyle="${gysMarkIdjiepai.procardStyle}"
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
			<input type="button" value="修改" class="input"
				onclick="show('updateProcardT','slow')" />
			<input type="button" value="关闭"
				onclick="hide('updateProcardT','hide')" class="input" />
			<div id="updateProcardT" style="display: none">
				<form action="markIdAction!updateProcardTem.action" method="post"
					style="margin: 0px; padding: 0px;" onsubmit="return checkType();">
					<input type="hidden" name="id" value="${gysMarkIdjiepai.id}" />
					<table class="table" style="width: 100%;">
						<tr>
							<th align="center" colspan="2">
								修改${gysMarkIdjiepai.proName}(${gysMarkIdjiepai.markId})的模版信息
							</th>
						</tr>
						<tr>
							<th align="right" style="width: 25%;">
								件号:
							</th>
							<td>
								<input name="gysMarkIdjiepai.markId"
									value="${gysMarkIdjiepai.markId}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								名称:
							</th>
							<td>
								<input name="gysMarkIdjiepai.proName"
									value="${gysMarkIdjiepai.proName}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								供货属性:
							</th>
							<td>
								<select name="gysMarkIdjiepai.kgliao">
									<option>
										${gysMarkIdjiepai.kgliao}
									</option>
									<option value="TK">
										自购(TK)
									</option>
									<option value="TK AVL">
										指定供应商(TK AVL)
									</option>
									<option value="TK Price">
										完全指定(TK Price)
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								版本:
							</th>
							<td>
								<input name="gysMarkIdjiepai.banBenNumber"
									value="${gysMarkIdjiepai.banBenNumber}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								车型:
							</th>
							<td>
								<input name="gysMarkIdjiepai.carStyle"
									value="${gysMarkIdjiepai.carStyle}">
							</td>
						</tr>
						<s:if test='gysMarkIdjiepai.procardStyle=="外购"'>
							<tr>
								<th align="right">
									权值:
								</th>
								<td>
									<input name="gysMarkIdjiepai.quanzi1" style="width: 71px;"
										value="${gysMarkIdjiepai.quanzi1}" />
									:
									<input name="gysMarkIdjiepai.quanzi2" style="width: 71px;"
										value="${gysMarkIdjiepai.quanzi2}" />
									(组合:外购件,格式如1:1)
								</td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<th align="right">
									数量:
								</th>
								<td>
									<input name="gysMarkIdjiepai.maxCount"
										value="${gysMarkIdjiepai.maxCount}">
									(生产批次的最大数量)
								</td>
							</tr>
							<s:if test='gysMarkIdjiepai.procardStyle=="总成"'>
								<tr>
									<th align="right">
										激活类型:
									</th>
									<td>
										<s:if test="gysMarkIdjiepai.jihuoType!='cc'">
											<select name="gysMarkIdjiepai.jihuoType"
												style="width: 155px;">
												<option value="cc">
													层次激活
												</option>
												<option value="zzj">
													自制件激活
												</option>
											</select>
										</s:if>
										<s:else>
											<select name="gysMarkIdjiepai.jihuoType"
												style="width: 155px;">
												<option value="zzj">
													自制件激活
												</option>
												<option value="cc">
													层次激活
												</option>
											</select>
										</s:else>
									</td>
								</tr>
							</s:if>
							<s:else>
								<tr>
									<th align="right">
										数量:
									</th>
									<td>
										<input name="gysMarkIdjiepai.corrCount"
											value="${gysMarkIdjiepai.corrCount}">
										(权值,对应上层所需数量)
									</td>
								</tr>
							</s:else>
						</s:else>
						<tr>
							<th align="right">
								卡片类型:
							</th>
							<td>
								<input name="gysMarkIdjiepai.procardStyle"
									value="${gysMarkIdjiepai.procardStyle}" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								单位:
							</th>
							<td>
								<select name="gysMarkIdjiepai.unit" id="danwei"
									style="width: 155px;">
									<option value="${gysMarkIdjiepai.unit}">
										${gysMarkIdjiepai.unit}
									</option>
									<option value="件">
										件
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								产品类型:
							</th>
							<td>
								<select name="gysMarkIdjiepai.productStyle"
									style="width: 155px;">
									<option value="${gysMarkIdjiepai.productStyle}">
										${gysMarkIdjiepai.productStyle}
									</option>
									<option value="批产">
										批产
									</option>
									<option value="试制">
										试制
									</option>
								</select>
							</td>
						</tr>
						<s:if test="gysMarkIdjiepai.procardStyle=='自制'">
							<tr>
								<th colspan="2">
									原材料信息
								</th>
							</tr>
							<tr>
								<th align="right">
									牌号:
								</th>
								<td>
									<input name="gysMarkIdjiepai.trademark"
										value="${gysMarkIdjiepai.trademark}">
								</td>
							</tr>
							<tr>
								<th align="right">
									规格:
								</th>
								<td>
									<input name="gysMarkIdjiepai.specification"
										value="${gysMarkIdjiepai.specification}">
								</td>
							</tr>
							<tr>
								<th align="right">
									单位:
								</th>
								<td>
									<select id="danwei2" name="gysMarkIdjiepai.yuanUnit"
										style="width: 155px;">
										<option value="${gysMarkIdjiepai.yuanUnit}">
											${gysMarkIdjiepai.yuanUnit}
										</option>
										<option value="件">
											件
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<th align="right">
									权值:
								</th>
								<td>
									<input name="gysMarkIdjiepai.quanzi1" style="width: 71px;"
										value="${gysMarkIdjiepai.quanzi1}" />
									:
									<input name="gysMarkIdjiepai.quanzi2" style="width: 71px;"
										value="${gysMarkIdjiepai.quanzi2}" />
									(自制件:原材料,格式如1:1)
								</td>
							</tr>
							<tr>
								<th align="right">
									炉号:
								</th>
								<td>
									<input name="gysMarkIdjiepai.luhao"
										value="${gysMarkIdjiepai.luhao}">
								</td>
							</tr>
							<tr>
								<th align="right">
									编号:
								</th>
								<td>
									<input name="gysMarkIdjiepai.number"
										value="${gysMarkIdjiepai.number}">
								</td>
							</tr>
							<tr>
								<th align="right">
									实际定额:
								</th>
								<td>
									<input name="gysMarkIdjiepai.actualFixed"
										value="${gysMarkIdjiepai.actualFixed}">
								</td>
							</tr>
						</s:if>

						<tr>
							<th align="right">
								是否外购:
							</th>
							<td>
								<select name="gysMarkIdjiepai.status" style="width: 155px;">
									<option value="${gysMarkIdjiepai.status}">
										${gysMarkIdjiepai.status}
									</option>
									<option value="否">
										否
									</option>
									<option value="是">
										是
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								配送时长:
							</th>
							<td>
								<input name="gysMarkIdjiepai.deliveryDuration"
									id="gysMarkIdjiepai.deliveryDuration"
									value="${gysMarkIdjiepai.deliveryDuration}">
								/h
								<font style="color: red;">*必填项(配送时长是指您 总共占用我们多少生产时间)</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								单班时长:
							</th>
							<td>
								<input name="gysMarkIdjiepai.singleDuration"
									id="gysMarkIdjiepai.singleDuration"
									value="${gysMarkIdjiepai.singleDuration}">
								/h
								<font style="color: red;">*必填项单班时长是指您的单班工作时长即上班时长(8h)</font>
							</td>
						</tr>

						<tr>
							<td align="center" colspan="2">
								<input type="submit" value="修改"
									style="width: 80px; height: 50px;" />
								<input type="reset" value="重置"
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div id="showZong" style="border: solid #000 1px; display: none;">
				<div align="center" style="border-bottom: solid #000 1px;">
					工 艺 流 水 卡 片
				</div>
				<div style="font-weight: bolder;">
					名称:${gysMarkIdjiepai.proName}
					&nbsp;&nbsp;件号:${gysMarkIdjiepai.markId} &nbsp;&nbsp;
					卡片类型:${gysMarkIdjiepai.procardStyle} &nbsp;&nbsp;
					产品类型:${gysMarkIdjiepai.productStyle} &nbsp;&nbsp;
					车型:${gysMarkIdjiepai.carStyle}&nbsp;&nbsp;
					产能:${gysMarkIdjiepai.capacity}(件)&nbsp;&nbsp;单件节拍:${gysMarkIdjiepai.allJiepai}(s)
				</div>
				<table class="table" style="width: 100%;">
					<tr>
						<th>
							数量
						</th>
						<th>
							${gysMarkIdjiepai.maxCount} ${gysMarkIdjiepai.unit}
						</th>
						<th>
							数量(权值)
						</th>
						<th>
							1 :
							${gysMarkIdjiepai.corrCount==null?0:gysMarkIdjiepai.corrCount}
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
					<s:iterator value="gysMarkIdjiepaiList" id="pageProcardTem">
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
								${pageProcessTem.opshebeijiepai+pageProcessTem.opcaozuojiepai+pageProcessTem.gzzhunbeicishu*pageProcessTem.gzzhunbeijiepai}
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
					名称:${gysMarkIdjiepai.proName}
					&nbsp;&nbsp;件号:${gysMarkIdjiepai.markId} &nbsp;&nbsp;
					卡片类型:${gysMarkIdjiepai.procardStyle} &nbsp;&nbsp;
					产品类型:${gysMarkIdjiepai.productStyle} &nbsp;&nbsp;
					车型:${gysMarkIdjiepai.carStyle}&nbsp;&nbsp;
					产能:${gysMarkIdjiepai.capacity}(件)&nbsp;&nbsp;单件节拍:${gysMarkIdjiepai.allJiepai}(s)
				</div>
				<table class="table" style="width: 100%;">
					<tr>
						<th colspan="3" width="40%">
							数量: ${gysMarkIdjiepai.maxCount}${gysMarkIdjiepai.unit}
						</th>
						<th colspan="2" width="50%">
							数量(权值) 1 :
							${gysMarkIdjiepai.corrCount==null?0:gysMarkIdjiepai.corrCount}
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
							${gysMarkIdjiepai.trademark}
						</td>
						<td rowspan="5" align="center" width="10%">
							备
							<br />
							<br />
							<br />
							注
						</td>
						<td rowspan="5">
							${gysMarkIdjiepai.remark}
						</td>
					</tr>
					<tr>
						<td>
							规格
						</td>
						<td>
							${gysMarkIdjiepai.specification}
						</td>
					</tr>
					<tr>
						<td>
							数量(权值)
						</td>
						<td>
							${gysMarkIdjiepai.quanzi1} : ${gysMarkIdjiepai.quanzi2}
						</td>
					</tr>
					<tr>
						<td>
							炉号
						</td>
						<td>
							${gysMarkIdjiepai.luhao}
						</td>
					</tr>
					<tr>
						<td>
							编号
						</td>
						<td>
							${gysMarkIdjiepai.number}
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
								${pageProcessTem.opshebeijiepai+pageProcessTem.opcaozuojiepai+pageProcessTem.gzzhunbeicishu*pageProcessTem.gzzhunbeijiepai}
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
							${gysMarkIdjiepai.markId}
						</td>
					</tr>
					<tr>
						<th align="right">
							名称:
						</th>
						<td>
							${gysMarkIdjiepai.proName}
						</td>
					</tr>
					<tr>
						<th align="right">
							车型:
						</th>
						<td>
							${gysMarkIdjiepai.carStyle}
						</td>
					</tr>
					<tr>
						<th align="right">
							卡片类型:
						</th>
						<td>
							${gysMarkIdjiepai.procardStyle}
						</td>
					</tr>
					<tr>
						<th align="right">
							单位:
						</th>
						<td>
							${gysMarkIdjiepai.unit}
						</td>
					</tr>
					<tr>
						<th align="right">
							权值:
						</th>
						<td>
							${gysMarkIdjiepai.quanzi1} : ${gysMarkIdjiepai.quanzi2}
						</td>
					</tr>
					<tr>
						<th align="right">
							产品类型:
						</th>
						<td>
							${gysMarkIdjiepai.productStyle}
						</td>
					</tr>
				</table>
			</div>
		</center>
		<script type="text/javascript">
function checkType() {
	//gysMarkIdjiepai.deliveryDuration  配送时长
	var deliveryDuration = document
			.getElementById('gysMarkIdjiepai.deliveryDuration').value;
	//gysMarkIdjiepai.singleDuration  单班时长
	var singleDuration = document
			.getElementById('gysMarkIdjiepai.singleDuration').value;

	if (deliveryDuration == "") {
		alert("配送时长不能为空！！！");
		return false;
	}
	if (singleDuration == "") {
		alert("单班时长不能为空！！！");
		return false;
	}
	return true;
}
$(function() {
	getUnit("danwei");
	getUnit("danwei2");
})
</script>
	</body>
</html>
