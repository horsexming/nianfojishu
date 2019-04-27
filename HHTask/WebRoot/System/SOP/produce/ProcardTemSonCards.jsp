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
			<input type="button" value="修改" style="width: 50px; height: 40px;"
				onclick="show('updateProcardT','slow')" />
			<input type="button" value="关闭"
				onclick="hide('updateProcardT','hide')"
				style="width: 50px; height: 40px;" />
			<div id="updateProcardT" style="display: none">
				<form action="ProcardTemplateAction!updateProcardTem.action"
					method="post" style="margin: 0px; padding: 0px;">
					<input type="hidden" name="id" value="${procardTemplate.id}" />
					<table class="table" style="width: 100%;">
						<tr>
							<th align="center" colspan="2">
								修改${procardTemplate.proName}(${procardTemplate.markId})的模版信息
							</th>
						</tr>
						<s:if test='procardTemplate.procardStyle=="外购"'>
							<tr>
								<th align="right" style="width: 25%;">
									外购件信息
								</th>
								<td>
									<div id="showAllwgj"
										style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">
									</div>
									<input id="shortnamewgj"
										value="${procardTemplate.markId}:${procardTemplate.proName}:${procardTemplate.unit}"
										onkeyup="getAllNameswgj()" style="height: 20px"
										onFocus="init('shortnamewgj','showAllwgj')"
										onBlur="hidediv('showAllwgj')">
									(输入件号或者名称)
								</td>
							</tr>

							<tr>
								<th align="right" style="width: 25%;">
									件号:
								</th>
								<td>
									<input id="markId" name="procardTemplate.markId"
										value="${procardTemplate.markId}" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<th align="right">
									名称:
								</th>
								<td>
									<input id="proName" name="procardTemplate.proName"
										value="${procardTemplate.proName}" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<th align="right">
									单位:
								</th>
								<td>
									<input id="unit" name="procardTemplate.unit"
										value="${procardTemplate.unit}" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<th align="right">
									是否半成品:
								</th>
								<td>
									<select name="procardTemplate.needProcess" id="needProcess"
										style="width: 155px;" onchange="changeneedProcess()">
										<s:if test="procardTemplate.needProcess==null">
											<option value="no">
												否
											</option>
											<option value="yes">
												是
											</option>
										</s:if>
										<s:elseif test="procardTemplate.needProcess=='yes'">
											<option value="${procardTemplate.needProcess}">
												是
											</option>
											<option value="no">
												否
											</option>
										</s:elseif>
										<s:else>
											<option value="${procardTemplate.needProcess}">
												否
											</option>
											<option value="yes">
												是
											</option>
										</s:else>
									</select>
								</td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<th align="right" style="width: 25%;">
									件号:
								</th>
								<td>
									<input name="procardTemplate.markId"
										value="${procardTemplate.markId}" />
								</td>
							</tr>
							<tr>
								<th align="right">
									名称:
								</th>
								<td>
									<input name="procardTemplate.proName"
										value="${procardTemplate.proName}" />
								</td>
							</tr>
							<tr>
								<th align="right">
									单位:
								</th>
								<td>
									<select name="procardTemplate.unit" id="danwei"
										style="width: 155px;">
										<option value="${procardTemplate.unit}">
											${procardTemplate.unit}
										</option>
										<option value="件">
											件
										</option>
									</select>
								</td>
							</tr>
						</s:else>

						<tr>
							<th align="right">
								车型:
							</th>
							<td>
								<input name="procardTemplate.carStyle"
									value="${procardTemplate.carStyle}">
							</td>
						</tr>
						<s:if test='procardTemplate.procardStyle=="外购"'>
							<tr>
								<th align="right">
									权值:
								</th>
								<td>
									<input name="procardTemplate.quanzi1" style="width: 71px;"
										value="${procardTemplate.quanzi1}" />
									:
									<input name="procardTemplate.quanzi2" style="width: 71px;"
										value="${procardTemplate.quanzi2}" />
									(组合:外购件,格式如1:1)
								</td>
							</tr>
							<tr id="safetr">
								<th align="right">
									安全库存
								</th>
								<td>
									<input name="procardTemplate.safeCount"
										value="${procardTemplate.safeCount}">
								</td>
							</tr>
							<tr id="lasttr">
								<th align="right">
									最低存量
								</th>
								<td>
									<input name="procardTemplate.lastCount"
										value="${procardTemplate.lastCount}">
								</td>
							</tr>
						</s:if>
						<s:else>

							<s:if test='procardTemplate.procardStyle=="总成"'>
								<tr>
									<th align="right">
										数量:
									</th>
									<td>
										<input name="procardTemplate.maxCount"
											value="${procardTemplate.maxCount}">
										(生产批次的最大数量)
									</td>
								</tr>
								<tr>
									<th align="right">
										安全库存
									</th>
									<td>
										<input name="procardTemplate.safeCount"
											value="${procardTemplate.safeCount}">
									</td>
								</tr>
								<tr>
									<th align="right">
										最低存量
									</th>
									<td>
										<input name="procardTemplate.lastCount"
											value="${procardTemplate.lastCount}">
									</td>
								</tr>
								<tr>
									<th align="right">
										激活类型:
									</th>
									<td>
										<s:if test="procardTemplate.jihuoType!='zzj'">
											<select name="procardTemplate.jihuoType"
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
											<select name="procardTemplate.jihuoType"
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
								<tr>
									<th align="right">
										单班时长:
									</th>
									<td>
										<input name="procardTemplate.singleDuration"
											value="${procardTemplate.singleDuration}">
										(h)
									</td>
								</tr>
							</s:if>
							<s:else>
								<tr>
									<th align="right">
										数量:
									</th>
									<td>
										<input name="procardTemplate.corrCount"
											value="${procardTemplate.corrCount}">
										(权值,对应上层所需数量)
									</td>
								</tr>
								<tr>
									<th align="right">
										安全库存
									</th>
									<td>
										<input name="procardTemplate.safeCount"
											value="${procardTemplate.safeCount}">
									</td>
								</tr>
								<tr>
									<th align="right">
										最低存量
									</th>
									<td>
										<input name="procardTemplate.lastCount"
											value="${procardTemplate.lastCount}">
									</td>
								</tr>
							</s:else>
						</s:else>

						<tr>
							<th align="right">
								卡片类型:
							</th>
							<td>
								<input name="procardTemplate.procardStyle"
									value="${procardTemplate.procardStyle}" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								产品类型:
							</th>
							<td>
								<select name="procardTemplate.productStyle"
									style="width: 155px;">
									<option value="${procardTemplate.productStyle}">
										${procardTemplate.productStyle}
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
						<tr>
							<th align="right">
								是否领料:
							</th>
							<td>
								<select name="procardTemplate.lingliaostatus"
									style="width: 155px;">
									<s:if test="procardTemplate.lingliaostatus==null">
										<option value="是">
											是
										</option>
									</s:if>
									<s:else>
										<option value="${procardTemplate.lingliaostatus}">
											${procardTemplate.lingliaostatus}
										</option>
									</s:else>
									<option value="是">
										是
									</option>
									<option value="否">
										否
									</option>
								</select>
							</td>
						</tr>
						<s:if
							test="procardTemplate.procardStyle=='总成'||procardTemplate.procardStyle=='组合'">
							<tr>
								<th align="right">
									领料方式:
								</th>
								<td>
									<select name="procardTemplate.lingliaoType"
										style="width: 155px;">
										<s:if test="procardTemplate.lingliaoType=='part'">
											<option value="part">
												部分到齐
											</option>
											<option value="all">
												全部到齐
											</option>
										</s:if>
										<s:else>
											<option value="all">
												全部到齐
											</option>
											<option value="part">
												部分到齐
											</option>
										</s:else>
									</select>
								</td>
							</tr>
						</s:if>
						<s:if test="procardTemplate.procardStyle=='自制'">
							<tr>
								<th colspan="2">
									原材料信息
								</th>
							</tr>
							<tr>
								<th align="right">
									查询原材料:
								</th>
								<td>
									<div id="showAllycl1"
										style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">
									</div>
									<input id="shortnameycl1"
										value="${procardTemplate.trademark}:${procardTemplate.specification}:${procardTemplate.yuanUnit}"
										onkeyup="getAllNames('shortnameycl1','showAllycl1','trademark1','specification1','danwei1')"
										style="height: 20px"
										onFocus="init('shortnameycl1','showAllycl1')"
										onBlur="hidediv('showAllycl1')">
								</td>
							</tr>
							<tr>
								<th align="right">
									牌号:
								</th>
								<td>
									<input id="trademark1" name="procardTemplate.trademark"
										value="${procardTemplate.trademark}" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th align="right">
									规格:
								</th>
								<td>
									<input id="specification1" name="procardTemplate.specification"
										value="${procardTemplate.specification}" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th align="right">
									单位:
								</th>
								<td>
									<input id="danwei1" name="procardTemplate.yuanUnit"
										value="${procardTemplate.yuanUnit}" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th align="right">
									权值:
								</th>
								<td>
									<input name="procardTemplate.quanzi1" style="width: 71px;"
										value="${procardTemplate.quanzi1}" />
									:
									<input name="procardTemplate.quanzi2" style="width: 71px;"
										value="${procardTemplate.quanzi2}" />
									(自制件:原材料,格式如1:1)
								</td>
							</tr>
							<tr>
								<th align="right">
									炉号:
								</th>
								<td>
									<input name="procardTemplate.luhao"
										value="${procardTemplate.luhao}">
								</td>
							</tr>
							<tr>
								<th align="right">
									编号:
								</th>
								<td>
									<input name="procardTemplate.number"
										value="${procardTemplate.number}">
								</td>
							</tr>
							<tr>
								<th align="right">
									实际定额:
								</th>
								<td>
									<input name="procardTemplate.actualFixed"
										value="${procardTemplate.actualFixed}">
								</td>
							</tr>
						</s:if>
						<tr>
							<th align="right">
								是否外购:
							</th>
							<td>
								<select name="procardTemplate.status" style="width: 155px;">
									<option value="${procardTemplate.status}">
										${procardTemplate.status}
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
						<s:if
							test="procardTemplate.procardStyle!=null&&procardTemplate.procardStyle=='总成'">
							<tr>
								<th align="right">
									是否为单交件:
								</th>
								<td>
									<s:if
										test='procardTemplate.danjiaojian!=null&&procardTemplate.danjiaojian=="单交件"'>
							是
									<input type="radio" name="procardTemplate.danjiaojian"
											checked="checked" value="单交件" onchange="danjiao('是');">
										否
									<input type="radio" name="procardTemplate.danjiaojian" value=""
											onchange="danjiao('否');">
									</s:if>
									<s:else>
							是
									<input type="radio" name="procardTemplate.danjiaojian"
											value="单交件" onchange="danjiao('是');">
							否
									<input type="radio" name="procardTemplate.danjiaojian" value=""
											checked="checked" onchange="danjiao('否');">

									</s:else>

								</td>
							</tr>
							<!-- ***************************************************************************************** -->
							<s:if
								test='procardTemplate.danjiaojian!=null&&procardTemplate.danjiaojian=="单交件"'>
								<tr>
							</s:if>
							<s:else>
								<tr id="tdjj" style="display: none;">
							</s:else>
							<td id="tdjj" colspan="2" align="center">
								<table class="table" style="width: 60%;">
									<tr>
										<th colspan="2">
											原材料信息
										</th>
									</tr>
									<tr>
										<th align="right">
											查询原材料:
										</th>
										<td>
											<div id="showAllycl2"
												style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">
											</div>
											<s:if test="procardTemplate.trademark!=null">
												<input id="shortnameycl2"
													value="${procardTemplate.trademark}:${procardTemplate.specification}:${procardTemplate.yuanUnit}"
													onkeyup="getAllNames('shortnameycl2','showAllycl2','trademark2','specification2','danwei2')"
													style="height: 20px"
													onFocus="init('shortnameycl2','showAllycl2')"
													onBlur="hidediv('showAllycl2')">
											</s:if>
											<s:else>
												<input id="shortnameycl2"
													onkeyup="getAllNames('shortnameycl2','showAllycl2','trademark2','specification2','danwei2')"
													style="height: 20px"
													onFocus="init('shortnameycl2','showAllycl2')"
													onBlur="hidediv('showAllycl2')">
											</s:else>

										</td>
									</tr>
									<tr>
										<th align="right">
											牌号:
										</th>
										<td>
											<input id="trademark2" name="procardTemplate.trademark"
												value="${procardTemplate.trademark}" readonly="readonly">
										</td>
									</tr>
									<tr>
										<th align="right">
											规格:
										</th>
										<td>
											<input id="specification2"
												name="procardTemplate.specification"
												value="${procardTemplate.specification}" readonly="readonly">
										</td>
									</tr>
									<tr>
										<th align="right">
											单位:
										</th>
										<td>
											<input id="danwei2" name="procardTemplate.yuanUnit"
												value="${procardTemplate.yuanUnit}" readonly="readonly">
										</td>
									</tr>
									<tr>
										<th align="right">
											权值:
										</th>
										<td>
											<input name="procardTemplate.quanzi1" style="width: 71px;"
												value="${procardTemplate.quanzi1}" />
											:
											<input name="procardTemplate.quanzi2" style="width: 71px;"
												value="${procardTemplate.quanzi2}" />
											(自制件:原材料,格式如1:1)
										</td>
									</tr>
									<tr>
										<th align="right">
											炉号:
										</th>
										<td>
											<input name="procardTemplate.luhao"
												value="${procardTemplate.luhao}">
										</td>
									</tr>
									<tr>
										<th align="right">
											编号:
										</th>
										<td>
											<input name="procardTemplate.number"
												value="${procardTemplate.number}">
										</td>
									</tr>
									<tr>
										<th align="right">
											实际定额:
										</th>
										<td>
											<input name="procardTemplate.actualFixed"
												value="${procardTemplate.actualFixed}">
										</td>
									</tr>
								</table>
							</td>
							</tr>
						</s:if>
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
					名称:${procardTemplate.proName}
					&nbsp;&nbsp;件号:${procardTemplate.markId} &nbsp;&nbsp;
					卡片类型:${procardTemplate.procardStyle} &nbsp;&nbsp;
					产品类型:${procardTemplate.productStyle} &nbsp;&nbsp;
					车型:${procardTemplate.carStyle}&nbsp;&nbsp;
				</div>
				<table class="table" style="width: 100%;">
					<tr>
						<th colspan="2">
							数量
						</th>
						<th colspan="3">
							${procardTemplate.maxCount} ${procardTemplate.unit}
						</th>
						<th colspan="3">
							数量(权值)
						</th>
						<th colspan="3">
							1 :
							${procardTemplate.corrCount==null?0:procardTemplate.corrCount}
						</th>
					</tr>
					<tr>
						<th colspan="2">
							安全库存
						</th>
						<th colspan="3">
							${procardTemplate.safeCount}
						</th>
						<th colspan="3">
							最低存量
						</th>
						<th colspan="3">
							${procardTemplate.lastCount}
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
							权值
						</td>
						<td colspan="3">
							卡片类型
						</td>

					</tr>
					<s:iterator value="procardTemplateList" id="pageProcardTem">
						<tr align="center">
							<th colspan="4">
								<a
									href="ProcardTemplateAction!findCardTemForShow.action?id=${pageProcardTem.id}">
									${pageProcardTem.markId}</a>
							</th>
							<th colspan="3">
								${pageProcardTem.proName}
							</th>
							<th>
								<s:if test='#pageProcardTem.procardStyle=="外购"'>
									${pageProcardTem.quanzi1} ：${pageProcardTem.quanzi2}
								</s:if>
								<s:else>
									1 : ${pageProcardTem.corrCount}
								</s:else>
							</th>
							<th colspan="3">
								${pageProcardTem.procardStyle}
							</th>
						</tr>
					</s:iterator>
					<tr>
						<th colspan="11">
							&nbsp;
						</th>
					</tr>
					<tr>
						<th colspan="11">
							&nbsp;
						</th>
					</tr>
				</table>
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
				</div>
				<table class="table" style="width: 100%;">
					<tr>
						<th colspan="3" width="40%">
							数量: ${procardTemplate.maxCount}${procardTemplate.unit}
						</th>
						<th colspan="8" width="50%">
							数量(权值) 1 :
							${procardTemplate.corrCount==null?0:procardTemplate.corrCount}
						</th>
					</tr>
					<tr>
						<th colspan="2">
							安全库存
						</th>
						<th colspan="1">
							${procardTemplate.safeCount}
						</th>
						<th colspan="1">
							最低存量
						</th>
						<th colspan="7">
							${procardTemplate.lastCount}
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
						<td rowspan="5" colspan="7">
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
						<td colspan="11">
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
							数量: :
						</th>
						<td>
							${procardTemplate.maxCount}${procardTemplate.unit}
						</td>
					</tr>
					<s:if test="procardTemplate.needProcess=='yes'">
						<tr>
							<th align="right">
								安全库存
							</th>
							<td>
								${procardTemplate.safeCount}
							</td>
						</tr>
						<tr>
							<th align="right">
								最低存量
							</th>
							<td>
								${procardTemplate.lastCount}
							</td>
						</tr>
					</s:if>
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
function danjiao(val) {
	if ("是" == val) {
		$("#tdjj").show();
	} else {
		$("#tdjj").hide();
	}
}
function init(shortname, showAll) {
	count_seach++;
	var shortnameycl = document.getElementById(shortname);
	var showAllycl = document.getElementById(showAll);
	showAllycl.style.top = getTop(shortnameycl) + 20;
	showAllycl.style.left = getLeft(shortnameycl);
	showAllycl.style.visibility = "visible";
}
//ajax获取所有的类似的全称
function getAllNames(short, showall, trademark, specification, danwei) {
	if ($("#" + short).val() == null || $("#" + short).val() == "") {
		$("#" + showall).empty();
		return;
	}
	$
			.ajax( {
				type : "POST",
				url : "yuanclAndWaigjAction!getAllNames.action",
				dataType : "json",
				data : {
					'yuanclAndWaigj.trademark' : $("#" + short).val(),
					'yuanclAndWaigj.clClass' : '原材料'
				},
				success : function(data) {
					$("#" + showall).empty();
					$(data)
							.each(
									function() {
										var trademark2 = $(this).attr(
												'trademark').replace(
												$("#" + short).val(),
												"<font color='red'>"
														+ $("#" + short).val()
														+ "</font>");
										$("#" + showall)
												.append(
														"<div onmouseover=\"ondiv(this)\" onmouseout=\"outdiv(this)\" onclick=\"selectdiv(this,\'"
																+ short
																+ "\',\'"
																+ showall
																+ "\',\'"
																+ trademark
																+ "\',\'"
																+ specification
																+ "\',\'"
																+ danwei
																+ "\')\" align='left'>"
																+ trademark2
																+ ":"
																+ $(this)
																		.attr(
																				'specification')
																+ ":"
																+ $(this).attr(
																		'unit')
																+ "<span style='visibility: hidden;'>"
																+ $(this)
																		.attr(
																				'trademark')
																+ ":"
																+ $(this)
																		.attr(
																				'specification')
																+ ":"
																+ $(this).attr(
																		'unit')
																+ "</span>"
																+ "</div>");
									});
				}
			});
}
//选中展示出来的div
function selectdiv(obj, short, showall, trademark, specification, danwei) {
	var html = $(obj).find("span").html();
	;
	var showAllycl = document.getElementById(showall);
	showAllycl.style.visibility = "hidden";
	var htmls = html.split(":");
	$("#" + short).val(html);
	$("#" + trademark).val(htmls[0]);
	$("#" + specification).val(htmls[1]);
	$("#" + danwei).val(htmls[2]);
}
//ajax获取所有的类似的全称
function getAllNameswgj() {
	if ($("#shortnamewgj").val() == null || $("#shortnamewgj").val() == "") {
		$("#showAllwgj").empty();
		return;
	}
	$
			.ajax( {
				type : "POST",
				url : "yuanclAndWaigjAction!getAllNames.action",
				dataType : "json",
				data : {
					'yuanclAndWaigj.markId' : $("#shortnamewgj").val(),
					'yuanclAndWaigj.clClass' : '外购件'
				},
				success : function(data) {
					$("#showAllwgj").empty();
					$(data)
							.each(
									function() {
										var markId = $(this)
												.attr('markId')
												.replace(
														$("#shortnamewgj")
																.val(),
														"<font color='red'>"
																+ $(
																		"#shortnamewgj")
																		.val()
																+ "</font>");
										$("#showAllwgj")
												.append(
														"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdivwgj(this)' align='left'>"
																+ markId
																+ ":"
																+ $(this).attr(
																		'name')
																+ ":"
																+ $(this).attr(
																		'unit')
																+ "<span style='visibility: hidden;'>"
																+ $(this)
																		.attr(
																				'markId')
																+ ":"
																+ $(this).attr(
																		'name')
																+ ":"
																+ $(this).attr(
																		'unit')
																+ "</span>"
																+ "</div>");
									});
				}
			});
}
//选中展示外购件的div
function selectdivwgj(obj) {
	var html = $(obj).find("span").html();
	var showAllwgj = document.getElementById("showAllwgj");
	showAllwgj.style.visibility = "hidden";
	var htmls = html.split(":");
	$("#shortnamewgj").val(html);
	$("#markId").val(htmls[0]);
	$("#proName").val(htmls[1]);
	$("#unit").val(htmls[2]);
}
function isneedprocess() {
	var needProcess = $("#needProcess").val();
	if (needProcess == 'yes') {
		$("#safetr").show();
		$("#lasttr").show();
		parent.module4.attr("disabled", false);
	} else if (needProcess == 'no') {
		$("#safetr").hide();
		$("#lasttr").hide();
		parent.module4.attr("disabled", true);
	}
}
$(document).ready(isneedprocess());
</script>

	</body>
</html>
