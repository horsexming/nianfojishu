<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
				if(procardStyle=="总成"||procardStyle=="自制"){
					$("#showZong").show();
				}else if(procardStyle=="外购"){
					$("#showWai").show();
				}
			});
		</SCRIPT>
		<style type="text/css">
.ztree li a {
	color: #fff;
}
</style>
	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">信息展示</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="tcIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<center>
		<s:if test="tag==jjbom">
			<s:if test="pageStatus==null||pageStatus==''||pageStatus!='view'">
				<input type="button" value="修改" style="height: 30px;"
					onclick="show('updateProcardT','slow')" />
				<input type="button" value="关闭修改"
					onclick="hide('updateProcardT','hide')"
					style="width: 60px; height: 30px;" />
				<input style="width: 50px; height: 30px;" type="button" value="提交"
					onclick="submitProcard(${procardTemplate.id})">
			</s:if>
			<s:if
				test="procardTemplate.bzStatus=='已编制'||procardTemplate.bzStatus=='已校对'||procardTemplate.bzStatus=='已审核'">
				<input style="width: 50px; height: 30px;" type="button" value="打回"
					onclick="backProcard(${procardTemplate.id})">
			</s:if>
		</s:if>
			<div id="updateProcardT" style="display: none">
				<form action="ProcardTemplateAction!updateProcardTem.action"
					method="post" style="margin: 0px; padding: 0px;" id="submintfrom">
					<input type="hidden" name="id" value="${procardTemplate.id}" />
					<table class="table" style="width: 100%;">
						<tr>
							<th align="center" colspan="2">
								修改${procardTemplate.proName}(${procardTemplate.markId})的模版信息
							</th>
						</tr>
						<s:if test='procardTemplate.procardStyle=="外购"'>
							<!-- 外购件不可编辑 -->
							<tr>
								<th align="right" style="width: 25%;">
									件号
								</th>
								<td>
									<input type="text" id="wajmarkId"
										value="${procardTemplate.markId}"
										name="procardTemplate.markId" readonly="readonly" />
									<input type="hidden" value="${procardTemplate.isycl}"
										name="procardTemplate.isycl" id="wajisycl" />
<%--									<input type="button" value="选择外购件"--%>
<%--										onclick="selectYclAndWgj('wgj')">--%>
<%--									<a href="<%=path%>/System/yclandwgj/yuanclAndWaigj_add.jsp"--%>
<%--										target="_showWai">找不到需要的件号?前往添加</a>--%>
								</td>
							</tr>
							<tr>
								<th align="right">
									图号:
								</th>
								<td>
									<input type="text" id="wajtuhao" name="procardTemplate.tuhao"
										value="${procardTemplate.tuhao}" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<th align="right">
									名称:
								</th>
								<td>
									<input id="wajproName" name="procardTemplate.proName"
									value="${procardTemplate.proName}" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<th align="right">
									规格:
								</th>
								<td>
									<input type="text" id="wajspecification"
										value="${procardTemplate.specification}"
										name="procardTemplate.specification" readonly="readonly" >
								</td>
							</tr>
							<tr>
								<th align="right">
									版本号:
								</th>
								<td>
									<input type="text" id="wajbanBenNumber"
										value="${procardTemplate.banBenNumber}"
										name="procardTemplate.banBenNumber" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th align="right">
									重要性:
								</th>
								<td>
									<input type="text" id="wajimportance"
										value="${procardTemplate.importance}"
										name="procardTemplate.importance" readonly="readonly" >
								</td>
							</tr>
							<tr>
								<th align="right">
									初始总成:
								</th>
								<td>
									<input type="text" id="wajloadMarkId"
										value="${procardTemplate.loadMarkId}"
										name="procardTemplate.loadMarkId" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th align="right">
									表处:
								</th>
								<td>
									<input type="text" id="wajbiaochu"
										value="${procardTemplate.biaochu}"
										name="procardTemplate.biaochu" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th align="right">
									物料类别:
								</th>
								<td>
									<input type="text" id="wajwgType" name="procardTemplate.wgType"
										readonly="readonly" value="${procardTemplate.wgType}">
								</td>
							</tr>
							<tr>
								<th align="right">
									单张重量:
								</th>
								<td>
									<input type="text" id="waibili" value="${procardTemplate.bili}"
										name="procardTemplate.bili" readonly="readonly" >
								</td>
							</tr>
							<tr>
								<th align="right">
									单位:
								</th>
								<td><!-- id="danwei1" -->
									<select  name="procardTemplate.unit"
										style="width: 155px;" >
										<option value="${procardTemplate.unit}">
											${procardTemplate.unit}
										</option>
									</select>
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
							<tr>
								<th align="right">
									余料加工:
								</th>
								<td>
									<select name="procardTemplate.jgyl" id="zuhejgyl">
										<s:if test="procardTemplate.jgyl==null">
											<option value="no">
												否
											</option>
											<option value="yes">
												是
											</option>
										</s:if>
										<s:elseif test="procardTemplate.jgyl=='yes'">
											<option value="${procardTemplate.jgyl}">
												是
											</option>
											<option value="no">
												否
											</option>
										</s:elseif>
										<s:else>
											<option value="${procardTemplate.jgyl}">
												否
											</option>
											<option value="yes">
												是
											</option>
										</s:else>
									</select>
								</td>
							</tr>
							<tr>
								<th align="right">
									炉号:
								</th>
								<td>
									<input name="procardTemplate.luhao" id="wailuhao"
										value="${procardTemplate.luhao}">
								</td>
							</tr>
							<tr>
								<th align="right">
									编号:
								</th>
								<td>
									<input name="procardTemplate.number" id="wainumber"
										value="${procardTemplate.number}">
								</td>
							</tr>
							<tr>
								<th align="right">
									实际定额:
								</th>
								<td>
									<input name="procardTemplate.actualFixed" id="waiactualFixed"
										value="${procardTemplate.actualFixed}">
								</td>
							</tr>

							<%--							<tr>--%>
							<%--								<th align="right">--%>
							<%--									是否外购:--%>
							<%--								</th>--%>
							<%--								<td>--%>
							<%--									<select name="procardTemplate.status" style="width: 155px;"--%>
							<%--										id="waistatus">--%>
							<%--										<s:if test="procardTemplate.status==null">--%>
							<%--											<option value="no">--%>
							<%--												否--%>
							<%--											</option>--%>
							<%--											<option value="yes">--%>
							<%--												是--%>
							<%--											</option>--%>
							<%--										</s:if>--%>
							<%--										<s:elseif test="procardTemplate.status=='yes'">--%>
							<%--											<option value="${procardTemplate.status}">--%>
							<%--												是--%>
							<%--											</option>--%>
							<%--											<option value="no">--%>
							<%--												否--%>
							<%--											</option>--%>
							<%--										</s:elseif>--%>
							<%--										<s:else>--%>
							<%--											<option value="${procardTemplate.status}">--%>
							<%--												否--%>
							<%--											</option>--%>
							<%--											<option value="yes">--%>
							<%--												是--%>
							<%--											</option>--%>
							<%--										</s:else>--%>
							<%--									</select>--%>
							<%--								</td>--%>
							<%--							</tr>--%>
						</s:if>
						<s:else>
							<tr>
								<th align="right" style="width: 25%;">
									件号:
								</th>
								<td>
									<input name="procardTemplate.markId"
										value="${procardTemplate.markId}" readonly="readonly"/>
								</td>
							</tr>
							<s:if test='procardTemplate.procardStyle=="总成"'>
								<tr>
									<th align="right" style="width: 25%;">
										业务件号:
									</th>
									<td>
										<input name="procardTemplate.ywMarkId"
											value="${procardTemplate.ywMarkId}" readonly="readonly" />
										（对外使用）
									</td>
								</tr>
							</s:if>
							<tr>
								<th align="right" style="width: 25%;">
									图号:
								</th>
								<td>
									<input name="procardTemplate.tuhao"
										value="${procardTemplate.tuhao}" />
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
									</select>
								</td>
							</tr>
							<tr>
								<th align="right">
									版本号:
								</th>
								<td>
									<input name="procardTemplate.banBenNumber"
										value="${procardTemplate.banBenNumber}" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th align="right">
									初始总成:
								</th>
								<td>
									<input type="text" id="wajloadMarkId"
										value="${procardTemplate.loadMarkId}"
										name="procardTemplate.loadMarkId">
								</td>
							</tr>
							<tr>
								<th align="right">
									表处:
								</th>
								<td>
									<input type="text" id="biaochu"
										value="${procardTemplate.biaochu}"
										name="procardTemplate.biaochu">
								</td>
							</tr>
						</s:else>
						<tr>
							<th align="right">
								型别:
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
							<tr>
								<th align="right">
									损耗值:
								</th>
								<td>
									<input type="text" value="${procardTemplate.sunhao}"
										name="procardTemplate.sunhao" onchange="numyanzheng(this)">
									%
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
							<tr>
								<th align="right">
									供料属性:
								</th>
								<td>
									<s:if test="procardTemplate.procardStyle=='外购'">
										<select name="procardTemplate.kgliao" id="kgliao">
											<option value="<s:property value="procardTemplate.kgliao" />">
												<s:if test="procardTemplate.kgliao=='TK'">
											自购(TK)
											</s:if>
												<s:elseif test="procardTemplate.kgliao=='TK AVL'">
											指定供应商(TK AVL)
											</s:elseif>
												<s:elseif test="procardTemplate.kgliao=='CS'">
											客供(CS)
											</s:elseif>
												<s:elseif test="procardTemplate.kgliao=='TK Price'">
											完全指定(TK Price)
											</s:elseif>
											</option>
										</select>
									</s:if>
									<s:else>
										<select name="procardTemplate.kgliao" id="kgliao">
											<option value="<s:property value="procardTemplate.kgliao" />">
												<s:if test="procardTemplate.kgliao=='TK'">
											自购(TK)
											</s:if>
												<s:elseif test="procardTemplate.kgliao=='TK AVL'">
											指定供应商(TK AVL)
											</s:elseif>
												<s:elseif test="procardTemplate.kgliao=='CS'">
											客供(CS)
											</s:elseif>
												<s:elseif test="procardTemplate.kgliao=='TK Price'">
											完全指定(TK Price)
											</s:elseif>
											</option>
											<option value="TK">
												自购(TK)
											</option>
											<option value="TK AVL">
												指定供应商(TK AVL)
											</option>
											<option value="CS">
												客供(CS)
											</option>
											<option value="TK Price">
												完全指定(TK Price)
											</option>
										</select>
									</s:else>
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
								长
							</th>
							<td>
								<input type="text" id="zizthisLength"
									value="${procardTemplate.thisLength}"
									name="procardTemplate.thisLength">
							</td>
						</tr>
						<tr>
							<th align="right">
								宽
							</th>
							<td>
								<input type="text" id="zizthisWidth"
									value="${procardTemplate.thisWidth}"
									name="procardTemplate.thisWidth">
							</td>
						</tr>
						<tr>
							<th align="right">
								高
							</th>
							<td>
								<input type="text" id="zizthisHight"
									value="${procardTemplate.thisHight}"
									name="procardTemplate.thisHight">

							</td>
						</tr>
						<tr>
							<th align="right">
								零件类型:
							</th>
							<td>
								<s:if test='procardTemplate.procardStyle=="待定"'>
									<select name="procardTemplate.procardStyle"
										onchange="changeddcardStyle(this.value)">
										<option>
											待定
										</option>
										<option>
											自制
										</option>
										<option>
											外购
										</option>
									</select>
								</s:if>
								<s:elseif test='procardTemplate.procardStyle=="外购"'>
									<input name="procardTemplate.procardStyle"
										value="${procardTemplate.procardStyle}"/>
								</s:elseif>
								<s:else>
									<input name="procardTemplate.procardStyle"
										value="${procardTemplate.procardStyle}" readonly="readonly" />
								</s:else>
							</td>
						</tr>
						<s:if test="procardTemplate.procardStyle=='待定'">
							<tr id="ddkgliaotr" style="display: none;">
								<th align="right">
									供料属性:
								</th>
								<td>
									<select id="ddkgliao" name="procardTemplate.kgliao"
										style="width: 155px;" disabled="disabled">
										<option value="TK">
											自购(TK)
										</option>
										<option value="TK AVL">
											指定供应商(TK AVL)
										</option>
										<option value="CS">
											客供(CS)
										</option>
										<option value="TK Price">
											完全指定(TK Price)
										</option>
									</select>
								</td>
							</tr>
							<tr id="ddwgtypetr" style="display: none;">
								<th align="right">
									物料类别:
								</th>
								<td>
									<div class="zTreeDemoBackground left">
										<ul class="list">
											<li class="title">
												<input id="ddwgtype" type="text" style="width: 120px;"
													name="procardTemplate.wgType" disabled="disabled" />
												<a id="menuBtn" href="#" onclick="showMenu('ddwgtype'); return false;">选择</a>
											</li>
										</ul>
									</div>
									<div id="menuContent" class="menuContent"
										style="display: none; position: absolute;">
										<ul id="treeDemo" class="ztree"
											style="margin-top: 0; width: 160px;"></ul>
									</div>
								</td>
							</tr>
						</s:if>
						<tr>
							<th align="right">
								产品类型:
							</th>
							<td>
								${procardTemplate.productStyle}
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
							test="procardTemplate.procardStyle=='总成'||procardTemplate.procardStyle=='自制'">
							<tr>
								<th align="right">
									提前激活:
								</th>
								<td>
									<select name="procardTemplate.zzjihuo" style="width: 155px;">
										<option>
											${procardTemplate.zzjihuo}
										</option>
										<option value="正常">
											正常
										</option>
										<option value="提前">
											提前
										</option>
									</select>
								</td>
							</tr>
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
						<%-- 
						<s:if
							test="procardTemplate.procardStyle=='自制'||procardTemplate.procardStyle=='组合'">
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
									<input type="text" id="ziztrademark"
										value="${procardTemplate.trademark}"
										name="procardTemplate.trademark" readonly="readonly">
									<input type="button" value="选择原材料"
										onclick="selectYclAndWgj('ycl')">
									<s:if test="procardTemplate.procardStyle=='组合'">
										<input type="button" value="去除原材料" onclick="deleteycl('ycl')">
									</s:if>
								</td>
							</tr>
							<tr>
								<th align="right">
									规格:
								</th>
								<td>
									<input type="text" id="zizspecification"
										value="${procardTemplate.specification}"
										name="procardTemplate.specification" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th align="right">
									原材料名称:
								</th>
								<td>
									<input type="text" id="zizyuanName"
										value="${procardTemplate.yuanName}"
										name="procardTemplate.yuanName" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th align="right">
									单张重量:
								</th>
								<td>
									<input type="text" id="zizbili" value="${procardTemplate.bili}"
										name="procardTemplate.bili" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th align="right">
									单位:
								</th>
								<td>
									<input type="text" id="zizyuanUnit"
										value="${procardTemplate.yuanUnit}"
										name="procardTemplate.yuanUnit" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th align="right">
									长
								</th>
								<td>
									<input type="text" id="zizthisLength"
										value="${procardTemplate.thisLength}"
										name="procardTemplate.thisLength">
								</td>
							</tr>
							<tr>
								<th align="right">
									宽
								</th>
								<td>
									<input type="text" id="zizthisWidth"
										value="${procardTemplate.thisWidth}"
										name="procardTemplate.thisWidth">
								</td>
							</tr>
							<tr>
								<th align="right">
									高
								</th>
								<td>
									<input type="text" id="zizthisHight"
										value="${procardTemplate.thisHight}"
										name="procardTemplate.thisHight">

								</td>
							</tr>

							<tr>
								<th align="right">
									材质类型:
								</th>
								<td>
									<input type="text" id="zizwgType"
										value="${procardTemplate.wgType}"
										name="procardTemplate.wgType" readonly="readonly">
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
						--%>
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
											牌号:
										</th>
										<td>
											<input type="text" id="ziztrademark"
												value="${procardTemplate.trademark}"
												name="procardTemplate.trademark" readonly="readonly">
											<input type="button" value="选择原材料"
												onclick="selectYclAndWgj('ycl')">
										</td>
									</tr>
									<tr>
										<th align="right">
											规格:
										</th>
										<td>
											<input type="text" id="zizspecification"
												value="${procardTemplate.specification}"
												name="procardTemplate.specification" readonly="readonly">
										</td>
									</tr>
									<tr>
										<th align="right">
											原材料名称:
										</th>
										<td>
											<input type="text" id="zizyuanName"
												value="${procardTemplate.yuanName}"
												name="procardTemplate.yuanName" readonly="readonly">
										</td>
									</tr>
									<tr>
										<th align="right">
											单张重量:
										</th>
										<td>
											<input type="text" id="zizbili" name="procardTemplate.bili"
												readonly="readonly">
										</td>
									</tr>
									<tr>
										<th align="right">
											单位:
										</th>
										<td>
											<input type="text" id="zizyuanUnit"
												value="${procardTemplate.yuanUnit}"
												name="procardTemplate.yuanUnit" readonly="readonly">
										</td>
									</tr>
									<tr>
										<th align="right">
											材质类型:
										</th>
										<td>
											<input type="text" id="zizwgType"
												value="${procardTemplate.wgType}"
												name="procardTemplate.wgType" readonly="readonly">
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
							<th align="right">
								工艺编号:
							</th>
							<td>
								<input name="procardTemplate.numb"
									value="${procardTemplate.numb}">
							</td>
						</tr>
						<tr>
							<th align="right">
								发出日:
							</th>
							<td>
								<input class="Wdate" type="text"
									name="procardTemplate.fachuDate" id="fachuDate"
									readonly="readonly"
									onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});"
									value="${procardTemplate.fachuDate}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								页数:
							</th>
							<td>
								<input name="procardTemplate.pageTotal" id="pageTotal"
									onkeyup="mustBeNumber('pageTotal')"
									value="${procardTemplate.pageTotal}">
							</td>
						</tr>
						<s:if
							test="procardTemplate.bzStatus==null||procardTemplate.bzStatus=='初始'">
							<tr>
								<th align="right">
									编制人 ：
								</th>
								<td>
									<select name="procardTemplate.bianzhiId" id='bianzhi'>
										<option value="${procardTemplate.bianzhiId}">
											${procardTemplate.bianzhiName}
										</option>
									</select>
									<%--
									时间 ：
									<input class="Wdate" type="text"
										name="procardTemplate.bianzhiDate" id="bianzhiDate"
										readonly="readonly" value="${procardTemplate.bianzhiDate}"
										onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
								--%>
								</td>
							</tr>
						</s:if>
						<s:if test="procardTemplate.bzStatus=='待编制'">
							<s:if test="jobNum=='2'.toString()">
								<tr>
								<th align="right">
									批准人 ：
								</th>
								<td>
									<select name="procardTemplate.pizhunId" id='pizhun'>
										<option value="${procardTemplate.pizhunId}">
											${procardTemplate.pizhunName}
										</option>
									</select>
									<%--
									时间 ：
									<input class="Wdate" type="text"
										name="procardTemplate.pizhunDate" id="pizhunDate"
										readonly="readonly" value="${procardTemplate.pizhunDate}"
										onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
								--%>
								</td>
							</tr>
							</s:if>
							<s:else>
							<tr>
								<th align="right">
									校对人 ：
								</th>
								<td>
									<select name="procardTemplate.jiaoduiId" id='jiaodui'>
										<option value="${procardTemplate.jiaoduiId}">
											${procardTemplate.jiaoduiName}
										</option>
									</select>
									<%--
									时间 ：
									<input class="Wdate" type="text"
										name="procardTemplate.jiaoduiDate" id="jiaoduiDate"
										readonly="readonly" value="${procardTemplate.jiaoduiDate}"
										onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
								--%>
								</td>
							</tr>
							</s:else>
						</s:if>
						<s:if test="procardTemplate.bzStatus=='已编制'">
							<s:if test="jobNum=='2'.toString()">
							<tr>
								<th align="right">
									批准人 ：
								</th>
								<td>
									<select name="procardTemplate.pizhunId" id='pizhun'>
										<option value="${procardTemplate.pizhunId}">
											${procardTemplate.pizhunName}
										</option>
									</select>
									<%--
									时间 ：
									<input class="Wdate" type="text"
										name="procardTemplate.pizhunDate" id="pizhunDate"
										readonly="readonly" value="${procardTemplate.pizhunDate}"
										onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
								--%>
								</td>
							</tr>
							</s:if>
							<s:else>
							<tr>
								<th align="right">
									审核人 ：
								</th>
								<td>
									<select name="procardTemplate.shenheId" id='shenhe'>
										<option value="${procardTemplate.shenheId}">
											${procardTemplate.shenheName}
										</option>
									</select>
									<%--
									时间 ：
									<input class="Wdate" type="text"
										name="procardTemplate.shenheDate" id="shenheDate"
										readonly="readonly" value="${procardTemplate.shenheDate}"
										onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
								--%>
								</td>
							</s:else>
							
							</tr>
						</s:if>
						<s:if test="procardTemplate.bzStatus=='已校对'">
							<tr>
								<th align="right">
									批准人 ：
								</th>
								<td>
									<select name="procardTemplate.pizhunId" id='pizhun'>
										<option value="${procardTemplate.pizhunId}">
											${procardTemplate.pizhunName}
										</option>
									</select>
									<%--
									时间 ：
									<input class="Wdate" type="text"
										name="procardTemplate.pizhunDate" id="pizhunDate"
										readonly="readonly" value="${procardTemplate.pizhunDate}"
										onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
								--%>
								</td>
							</tr>
						</s:if>
						<s:if test='procardTemplate.procardStyle=="自制"'>
							<th align="right">
								材质:
							</th>
							<td>
								<input name="procardTemplate.clType" id="clType"
									value="${procardTemplate.clType}">
							</td>
						</s:if>
						<s:elseif test='procardTemplate.procardStyle=="外购"'>
							<th align="right">
								材料类型:
							</th>
							<td>
								<input name="procardTemplate.clType" id="clType1"
									value="${procardTemplate.clType}" readonly="readonly">
							</td>
						</s:elseif>
						<tr>	
						<s:if test="procardTemplate.bzStatus=='已批准'">
							<font color="red">已批准不能修改</font>
						</s:if>
						<s:else>
							<td align="center" colspan="2">
								<input type="button" value="保存" id="toupdate_sub"
									style="width: 80px; height: 50px;"
									onclick="submitFrom('submintfrom')" />
								<input type="reset" value="重置"
									style="width: 80px; height: 50px;" />
							</td>
						</s:else>
						</tr>
					</table>
				</form>
			</div>
			<div>
				<a
					href="procardTemplateGyAction_showProcardDetailForTz.action?id=${procardTemplate.id}"
					target="showP">图纸查看</a>
			</div>
			<s:if test="procardTemplate.bzStatus!='已批准'">
						<div align="left" style="border-bottom: solid #000 1px;">
						设变提示:${messagePower}
						</div>
						</s:if>
			<div id="showZong" style="border: solid #000 1px; display: none;">
				<div align="center" style="border-bottom: solid #000 1px;">
					工 艺 流 水 卡 片
				</div>
				<div style="font-weight: bolder;font-size: 16px;">
					名称:${procardTemplate.proName}
					&nbsp;&nbsp;件号:${procardTemplate.markId} &nbsp;&nbsp;
					卡片类型:${procardTemplate.procardStyle} &nbsp;&nbsp;
					产品类型:${procardTemplate.productStyle} &nbsp;&nbsp;
					型别:${procardTemplate.carStyle}&nbsp;&nbsp;
					图号:${procardTemplate.tuhao}&nbsp;&nbsp;
					表处:${procardTemplate.biaochu}&nbsp;&nbsp;
					<s:if test="procardTemplate.procardStyle=='外购'">
					重要性:<font color="red">${procardTemplate.importance}</font>&nbsp;&nbsp;
					</s:if>
				</div>
				<table class="table" style="width: 100%;">
					<tr>
						<th colspan="2">
							最大数量/单位
						</th>
						<th colspan="1">
							${procardTemplate.maxCount} ${procardTemplate.unit}
						</th>
						<th colspan="1">
							数量(权值)
						</th>
						<th colspan="7">
							1 :
							${procardTemplate.corrCount==null?0:procardTemplate.corrCount}
						</th>
					</tr>
					<tr>
						<th colspan="2">
							总成件号
						</th>
						<th colspan="1" style="width: 15%">
							${procardTemplate.rootMarkId}
						</th>
						<th colspan="1">
							初始总成:
						</th>
						<td colspan="7">
							${procardTemplate.loadMarkId}
						</td>
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
						<th colspan="2">
							工艺状态
						</th>
						<th colspan="1">
							<font color="red">${procardTemplate.bzStatus}</font>
						</th>
						<th colspan="1">
							版本号
						</th>
						<th colspan="7">
							${procardTemplate.banBenNumber}
						</th>
					</tr>
					<tr>
						<th colspan="2">
							页数
						</th>
						<th colspan="1">
							${procardTemplate.pageTotal}
						</th>
						<th colspan="1">
							发出日
						</th>
						<th colspan="7">
							${procardTemplate.fachuDate}
						</th>
					</tr>
					<tr>
						<th colspan="2">
							添加人
						</th>
						<th colspan="1">
							${procardTemplate.adduser}
						</th>
						<th colspan="1">
							添加时间
						</th>
						<th colspan="7">
							${procardTemplate.addtime}
						</th>
					</tr>
					<tr align="center">
						<td colspan="2">
							<h3>
								编制人
							</h3>
						</td>
						<td colspan="1">
							${procardTemplate.bianzhiName}
						</td>
						<td colspan="1">
							<h3>
								编制时间
							</h3>
						</td>
						<td colspan="7">
							${procardTemplate.bianzhiDate}
						</td>
					</tr>
					<s:if test="jobNum=='3'.toString()||jobNum=='4'.toString()">
					<tr align="center">
						<td colspan="2">
							<h3>
								校对人
							</h3>
						</td>
						<td colspan="1">
							${procardTemplate.jiaoduiName}
						</td>
						<td colspan="1">
							<h3>
								校对时间
							</h3>
						</td>
						<td colspan="7">
							${procardTemplate.jiaoduiDate}
						</td>
					</tr>
					</s:if>
					<s:if test="jobNum=='4'.toString()">
					<tr align="center">
						<td colspan="2">
							<h3>
								审核人
							</h3>
						</td>
						<td colspan="1">
							${procardTemplate.shenheName}
						</td>
						<td colspan="1">
							<h3>
								审核时间
							</h3>
						</td>
						<td colspan="7">
							${procardTemplate.shenheDate}
						</td>
					</tr>
					</s:if>
					<tr align="center">
						<td colspan="2">
							<h3>
								批准人
							</h3>
						</td>
						<td colspan="1">
							${procardTemplate.pizhunName}
						</td>
						<td colspan="1">
							<h3>
								批准时间
							</h3>
						</td>
						<td colspan="7">
							${procardTemplate.pizhunDate}
						</td>
					</tr>
					<s:if test="procardTemplate.procardStyle=='总成'">
						<tr align="center">
							<td colspan="2">
								<h3>
									BOM结构校对人
								</h3>
							</td>
							<td colspan="1">
								${procardTemplate.jiaoduiName2}
							</td>
							<td colspan="1">
								<h3>
									BOM结构校对时间
								</h3>
							</td>
							<td colspan="7">
								${procardTemplate.jiaoduiDate2}
							</td>
						</tr>
						<tr align="center">
							<td colspan="2">
								<h3>
									BOM结构审核人
								</h3>
							</td>
							<td colspan="1">
								${procardTemplate.shenheName2}
							</td>
							<td colspan="1">
								<h3>
									BOM结构审核时间
								</h3>
							</td>
							<td colspan="7">
								${procardTemplate.shenheDate2}
							</td>
						</tr>
						<tr align="center">
							<td colspan="2">
								<h3>
									BOM结构批准人
								</h3>
							</td>
							<td colspan="1">
								${procardTemplate.pizhunName2}
							</td>
							<td colspan="1">
								<h3>
									BOM结构批准时间
								</h3>
							</td>
							<td colspan="7">
								${procardTemplate.pizhunDate2}
							</td>
						</tr>
					</s:if>
					<tr>
						<s:if test="procardTemplate.procardStyle=='自制'">
							<th colspan="2">
								长/宽/高
							</th>
							<td colspan="1">
								<s:if
									test="procardTemplate.thisLength!=null||procardTemplate.thisWidth!=null||procardTemplate.thisHight!=null">
							${procardTemplate.thisLength}/${procardTemplate.thisWidth}/${procardTemplate.thisHight}
						</s:if>
							</td>
							<th>
								提前激活
							</th>
							<td align="center">
								${procardTemplate.zzjihuo}
							</td>
						</s:if>
						<s:else>
							<td colspan="11">
							</td>
						</s:else>
					</tr>
					<tr>
						<s:if test="procardTemplate.procardStyle=='自制'">
							<th colspan="2">
								材质
							</th>
							<th>
								${procardTemplate.clType}
							</th>
							<th colspan="2"></th>
						</s:if>
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
						<th colspan="11">
							是否并行
						</th>
					</tr>
					<tr align="center">
						<s:iterator value="list" id="pageProcessTem">
							<tr align="center">
								<th>
									${pageProcessTem.processNO}
								</th>
								<th>
									${pageProcessTem.processName}
								</th>
								<th>
								<fmt:formatNumber value="${pageProcessTem.opshebeijiepai+pageProcessTem.opcaozuojiepai}" pattern="#.0000"/>
								</th>
								<th>
									${pageProcessTem.productStyle}
								</th>
								<th colspan="11">
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
						<th colspan="2">
							件号:
						</th>
						<td colspan="1" style="font-size: 16px;font-weight: bolder;">
							${procardTemplate.markId}
						</td>
						<th colspan="1">
							名称:
						</th>
						<td colspan="7">
							${procardTemplate.proName}
						</td>
					</tr>
					<tr>
						<th colspan="2">
							图号:
						</th>
						<td colspan="1">
							${procardTemplate.tuhao}
						</td>
						<th colspan="1">
							规格:
						</th>
						<td colspan="7">
							${procardTemplate.specification}
						</td>
					</tr>
					<tr>
						<th colspan="2">
							材质:
						</th>
						<td colspan="1">
							${procardTemplate.caizhi}
						</td>
						<th colspan="1">
							物料类别:
						</th>
						<td colspan="7">
							${procardTemplate.wgType}
						</td>
					</tr>
					<tr>
						<th colspan="2">
							版本号
						</th>
						<th colspan="1">
							${procardTemplate.banBenNumber}
						</th>
						<th colspan="1">
							供料属性:
						</th>
						<td colspan="7">
							<s:if test="procardTemplate.kgliao=='TK'">
										自购(TK)
										</s:if>
							<s:elseif test="procardTemplate.kgliao=='TK AVL'">
										指定供应商(TK AVL)
										</s:elseif>
							<s:elseif test="procardTemplate.kgliao=='CS'">
										客供(CS)
										</s:elseif>
							<s:elseif test="procardTemplate.kgliao=='TK Price'">
										完全指定(TK Price)
										</s:elseif>

						</td>
					</tr>
					<s:if test="procardTemplate.needProcess=='yes'">
						<tr>
							<th colspan="2">
								安全库存
							</th>
							<td colspan="1">
								${procardTemplate.safeCount}
							</td>
							<th colspan="1">
								最低存量
							</th>
							<td colspan="7">
								${procardTemplate.lastCount}
							</td>
						</tr>
					</s:if>

					<tr>
						<th colspan="2">
							卡片类型:
						</th>
						<td colspan="1">
							${procardTemplate.procardStyle}
						</td>
						<th colspan="1">
							单位:
						</th>
						<td colspan="7">
							${procardTemplate.unit}
						</td>
					</tr>
					<tr>
						<th colspan="2">
							权值:
						</th>
						<td colspan="1">
							${procardTemplate.quanzi1} :
							<fmt:formatNumber type="number"
								value="${procardTemplate.quanzi2}" maxFractionDigits="4" />
							<%--							<font color="red">(消耗量:${procardTemplate.xiaohaoCount})</font>--%>
						</td>
						<th colspan="1">
							产品类型:
						</th>
						<td colspan="7">
							${procardTemplate.productStyle}
						</td>
					</tr>
					<tr>
						<th colspan="2">
							长/宽/高
						</th>
						<td colspan="1">
							<s:if
								test="procardTemplate.thisLength!=null||procardTemplate.thisWidth!=null||procardTemplate.thisHight!=null">
							${procardTemplate.thisLength}/${procardTemplate.thisWidth}/${procardTemplate.thisHight}
						</s:if>
						</td>
						<s:if test="procardTemplate.needProcess=='yes'">
							<th colspan="1">
								是否领料:
							</th>
							<td colspan="7">
								${procardTemplate.lingliaostatus}
							</td>
						</s:if>

					</tr>
					<tr>
						<th colspan="2">
							总成件号
						</th>
						<th colspan="1">
							${procardTemplate.rootMarkId}
						</th>
						<th colspan="1">
							初始总成:
						</th>
						<td colspan="7">
							${procardTemplate.loadMarkId}
						</td>
					</tr>
					<tr>
						<th colspan="1">
							表处
						</th>
						<td colspan="7">
							${procardTemplate.biaochu}
						</td>
					</tr>
					<tr>
						<th colspan="2">
							工艺状态
						</th>
						<th colspan="1">
							${procardTemplate.bzStatus}
						</th>
						<th colspan="1">
							工艺编号
						</th>
						<th colspan="7">
							${procardTemplate.numb}
						</th>
					</tr>
						<tr>
						<th colspan="2">
							添加人
						</th>
						<th colspan="1">
							${procardTemplate.adduser}
						</th>
						<th colspan="1">
							添加时间
						</th>
						<th colspan="7">
							${procardTemplate.addtime}
						</th>
					</tr>
					<tr align="center">
						<td colspan="2">
							<h3>
								编制人
							</h3>
						</td>
						<td colspan="1">
							${procardTemplate.bianzhiName}
						</td>
						<td colspan="1">
							<h3>
								编制时间
							</h3>
						</td>
						<td colspan="7">
							${procardTemplate.bianzhiDate}
						</td>
					</tr>
					<s:if test="jobNum=='3'.toString()||jobNum=='4'.toString()">
					<tr align="center">
						<td colspan="2">
							<h3>
								校对人
							</h3>
						</td>
						<td colspan="1">
							${procardTemplate.jiaoduiName}
						</td>
						<td colspan="1">
							<h3>
								校对时间
							</h3>
						</td>
						<td colspan="7">
							${procardTemplate.jiaoduiDate}
						</td>
					</tr>
					</s:if>
					<s:if test="jobNum=='4'.toString()">
					<tr align="center">
						<td colspan="2">
							<h3>
								审核人
							</h3>
						</td>
						<td colspan="1">
							${procardTemplate.shenheName}
						</td>
						<td colspan="1">
							<h3>
								审核时间
							</h3>
						</td>
						<td colspan="7">
							${procardTemplate.shenheDate}
						</td>
					</tr>
					</s:if>
					<tr align="center">
						<td colspan="2">
							<h3>
								批准人
							</h3>
						</td>
						<td colspan="1">
							${procardTemplate.pizhunName}
						</td>
						<td colspan="1">
							<h3>
								批准时间
							</h3>
						</td>
						<td colspan="7">
							${procardTemplate.pizhunDate}
						</td>
					</tr>
					<tr>
						<th colspan="11">
							&nbsp;
						</th>
					</tr>
					<s:if test="procardTemplate.needProcess=='yes'">
						<s:iterator value="list" id="pageProcessTem">
							<tr align="center">
								<th>
									${pageProcessTem.processNO}
								</th>
								<th>
									${pageProcessTem.processName}
								</th>
								<th>
								<fmt:formatNumber value="${pageProcessTem.opshebeijiepai+pageProcessTem.opcaozuojiepai}" pattern="#.0000"/>
								</th>
								<th>
									${pageProcessTem.productStyle}
								</th>
								<th>
									${pageProcessTem.processStatus}
								</th>
							</tr>
						</s:iterator>
					</s:if>
				</table>
			</div>
			<br />
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
function getGyPeople() {
	var type = "";
	var gyStatus = "${procardTemplate.bzStatus}";
	if (gyStatus == null || gyStatus == "" || gyStatus == "初始") {
		type = "bz";
	} else if (gyStatus == "待编制") {
		type = "jd";
	} else if (gyStatus == "已编制") {
		type = "sh";
	} else if (gyStatus == "已校对") {
		type = "pz";
	}
	$.ajax( {
		type : "POST",
		url : "procardTemplateGyAction_getGyPeople.action?tag=" + type,
		dataType : "json",
		success : function(data) {
			$(data).each(
					function() {
						if (type == "bz") {
							$("#bianzhi").append(
									"<option value='" + $(this).attr('userId')
											+ "'>" + $(this).attr('userName')
											+ "</option>");
						} else if (type == "jd") {
							$("#jiaodui").append(
									"<option value='" + $(this).attr('userId')
											+ "'>" + $(this).attr('userName')
											+ "</option>");
						} else if (type == "sh") {
							$("#shenhe").append(
									"<option value='" + $(this).attr('userId')
											+ "'>" + $(this).attr('userName')
											+ "</option>");
						} else if (type == "pz") {
							$("#pizhun").append(
									"<option value='" + $(this).attr('userId')
											+ "'>" + $(this).attr('userName')
											+ "</option>");

						}
					});
		}
	});
}
$(document).ready(function() {
	isneedprocess();
	getGyPeople();
})
function submitProcard(id) {
	if (confirm("当前模板编制状态为:${procardTemplate.bzStatus},是否提交?")) {
		$.ajax( {
			type : "POST",
			url : "procardTemplateGyAction_submitProcard.action",
			dataType : "json",
			data : {
				id : id
			},
			success : function(data) {
				alert(data.message);
				if (data.success) {
					reload();
				}
				reload();
			}
		});
	}
}
function backProcard(id) {
	if (confirm("当前模板编制状态为:${procardTemplate.bzStatus},是否打回?")) {
		$
				.ajax( {
					type : "POST",
					url : "procardTemplateGyAction_backProcard.action",
					dataType : "json",
					data : {
						id : id
					},
					success : function(data) {
						alert(data.message);
						if (data.success) {
							top.location.href = "/System/SOP/produce/Template_findProcard2.jsp?id="
									+ data.data;
						}
					}
				});
	}
}
function selectYclAndWgj(type) {
	$("#tcIframe").attr("src",
			"procardTemplateGyAction_showYclAndWgj.action?type=" + type);
	chageDiv('block');
}
function deleteycl() {
	$("#ziztrademark").val("");
	$("#zizspecification").val("");
	$("#zizytuhao").val("");
	$("#zizbili").val("");
	$("#zizwgType").val("");
	$("#zizyuanUnit").val("");
	$("#zizyuanName").val("");
}
$(function() {
	getUnit("danwei");
	getUnit("danwei1");
	getUnit("danwei2");
	getUnit("danwei3");
	getUnit("danwei4");
})
function changeddcardStyle(obj) {
	if (obj == "外购") {
		$("#ddwgtypetr").show();
		$("#ddwgtype").removeAttr("disabled");
		$("#ddkgliaotr").show();
		$("#ddkgliao").removeAttr("disabled");
	} else {
		$("#ddwgtypetr").hide();
		$("#ddwgtype").attr("disabled", "disabled");
		$("#ddkgliaotr").hide();
		$("#ddkgliao").attr("disabled", "disabled");
	}
}

function submitFrom(obj) {
	$("#toupdate_sub").attr("disabled", "disabled");
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!updateProcardTem.action",
		dataType : "json",
		data : $("#" + obj).serialize(),
		success : function(data) {
			if (data == "修改成功!" || data == "true") {
				parent.loadTree();
				alert("修改成功!");
			} else {
				alert(data);
			}
			$("#toupdate_sub").removeAttr("disabled");
		}
	});
}

var mfzTree;
var addzTree;
var delzTree;
var updatezTree;

var id;
var pId;
var name;
var setting = {
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		beforeClick : beforeClick,
		onClick : onClick
	}
};
//读取树形数据
$(document).ready(function() {
	parent.mfzTree();
});
var zNodes = [];
parent.mfzTree = function() {
	$.ajax( {
		url : 'CategoryAction_findcyListByrootId.action',
		type : 'post',
		data : {
			status : '物料类别'
		},
		dataType : 'json',
		cache : true,
		success : function(doc) {
			$(doc).each(function() {
				zNodes.push( {
					id : $(this).attr('id'),
					pId : $(this).attr('fatherId'),
					name : $(this).attr('name'),
					target : "main",
					click : false
				});

			});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			if(zTree!=null){
			zTree.expandAll(true);
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
};
function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);

	return true;
}

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
			.getSelectedNodes(), v = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v = nodes[i].name;
	}
	//if (v.length > 0 ) v = v.substring(0, v.length-1); 
	cityObj = $("#ddwgtype").val(v);
	cityObj = $("#ddwgType").val(v);

}

function showMenu(id) {
	var cityObj = $("#"+id);
	var cityOffset = $("#"+id).offset();
	$("#menuContent").css( {
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
			event.target).parents("#menuContent").length > 0)) {
		hideMenu();
	}
}
</script>

	</body>
</html>
