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
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">产品明细与维护</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div>
			<div align="left">
				<!-- 区别1 -->
				<s:if test="pageStatus==null||pageStatus!='shenyue'">
					<!-- 显示树形流水卡片模板 -->
					<div style="" align="left">
						<div style="height: 100%;">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
					</div>
				</s:if>
				<s:else>
					<div style="" align="left">
						<div style="height: 100%;">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
					</div>
				</s:else>
				<!-- 区别1 -->
				<!-- 添加流水卡片模板操作 -->
				<div
					style="border: 1px solid #000000; position: absolute; background-color: #ffffff"
					id="showProDetail">
					<div id="selectDiv" style="display: none;" align="center">
						<input id="module0" type="button" value="配比明细"
							onclick="showSonCard()" style="width: 80px; height: 40px;" />
						<input id="module1" type="button" value="添加组合件"
							onclick="chageModule(this,'1')"
							style="width: 80px; height: 40px;" />
						<input id="module2" type="button" value="添加外购件"
							onclick="chageModule(this,'2')"
							style="width: 80px; height: 40px;" />
						<input id="module3" type="button" value="添加自制件"
							onclick="chageModule(this,'3')"
							style="width: 80px; height: 40px;" />
						<input id="module2_1" type="button" value="添加半成品"
							onclick="chageModule(this,'2',true)"
							style="width: 80px; height: 40px;" />
						<input id="module4" type="button" value="工序管理"
							onclick="chageModule(this,'4');showProcess();"
							style="width: 80px; height: 40px;" />
						<input id="module5" type="button" value="复制模板"
							onclick="chageModule(this,'5');"
							style="width: 80px; height: 40px;" />
						<%--						<input id="module6" type="button" value="修改件号"--%>
						<%--							onclick="chageModule(this,'6');"--%>
						<%--							style="width: 80px; height: 40px;" />--%>
						<input id="uId" type="hidden">
						<input id="module7" type="button" value="更新流水卡"
							onclick="updateProcard(this)" style="width: 80px; height: 40px;" />
						<input id="module8" type="button" value="删除本卡片"
							onclick="delProCard(this)" style="width: 80px; height: 40px;" />
						<input id="module9" type="button" value="转批产"
							onclick="chageModule(this,'9');"
							style="width: 80px; height: 40px;" />
						<input id="module10" type="button" value="产品图纸"
							onclick="showCardTz()" style="width: 80px; height: 40px;" />
						<input id="module11" type="button" value="BOM预览"
							onclick="reviewBom();" style="width: 80px; height: 40px;" />
						<input id="module11_1" type="button" value="自检"
							onclick="checkSelf();" style="width: 80px; height: 40px;" />
						<input id="module12" type="button" value="图纸导入"
							onclick="checkAndUpdateTz();" style="width: 80px; height: 40px;" />
						<input id="module13" type="button" value="下阶层"
							onclick="moveStatus()" style="width: 80px; height: 40px;" />
						<input id="module14" type="button" value="修改记录"
													onclick="changeLogshow();" style="width: 80px; height: 40px;" />
						<input id="module15" type="button" value="Bom结构申请审批"
							onclick="applyBomTree()" style="width: 100px; height: 40px;" />
						<input id="module16" type="button" value="Bom结构审批动态"
							onclick="BomTreeSpdt()"
							style="width: 120px; height: 40px; display: none;" />
						<input type="hidden" id="epId2">
						<br />
					</div>
					<div id="module1_1"
						style="display: none; background-color: #ffffff">
						<form id="lingForm" style="margin: 0px; padding: 0px;">
							<input type="hidden" id="wrootId" name="procardTemplate.rootId" />
							<input type="hidden" id="wfatherId"
								name="procardTemplate.fatherId" />
							<input type="hidden" id="wbelongLayer"
								name="procardTemplate.belongLayer" />
							<table class="table" style="width: 100%;">
								<tr>
									<th align="center" colspan="2">
										添加组合件工艺卡片模版
										<input type="submit" value="保存" />
										<input type="reset" value="重置" />
									</th>
								</tr>
								<tr>
									<th align="right" style="width: 25%;">
										件号:
									</th>
									<td>

										<div id="showAll1"
											style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">
										</div>
										<input type="hidden" value="0" id="shortnameId1">
										<input name="procardTemplate.markId"
											onBlur="hidediv('showAll1')"
											onkeyup="noZhongwen(this,'zuhemarkId','showAll1','shortnameId1','组合')"
											onFocus="initfz('zuhemarkId','showAll1',130,230)"
											id="zuhemarkId">
										<font color="red">*</font>
										<div id="zuhemarkIddiv"></div>
									</td>
								</tr>
								<tr>
									<th align="right">
										名称:
									</th>
									<td>
										<input name="procardTemplate.proName" id="zuheproName">
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<th align="right">
										版本号:
									</th>
									<td>
										<input name="procardTemplate.banBenNumber"
											id="zuhebanBenNumber">
									</td>
								</tr>
								<tr>
									<th align="right">
										初始总成:
									</th>
									<td>
										<input name="procardTemplate.loadMarkId" id="zuheloadMarkId">
									</td>
								</tr>
								<tr>
									<th align="right">
										车型:
									</th>
									<td>
										<input name="procardTemplate.carStyle" id="zuhecarStyle">
									</td>
								</tr>

								<tr>
									<th align="right">
										数量:
									</th>
									<td>
										<input name="procardTemplate.corrCount" id="zuhecorrCount">
										<font color="red">*</font> (权值,对应上层所需数量)
									</td>
								</tr>
								<tr>
									<th align="right">
										安全库存:
									</th>
									<td>
										<input name="procardTemplate.safeCount" id="zuhesafeCount">
									</td>
								</tr>
								<tr>
									<th align="right">
										最低存量:
									</th>
									<td>
										<input name="procardTemplate.lastCount" id="zuhelastCount">
									</td>
								</tr>
								<tr>
									<th align="right">
										卡片类型:
									</th>
									<td>
										<input name="procardTemplate.procardStyle" value="组合"
											readonly="readonly" id="zuheprocardStyle" />

									</td>
								</tr>
								<tr>
									<th align="right">
										单位:
									</th>
									<td>
										<select name="procardTemplate.unit" style="width: 155px;"
											id="zuheunit">
										</select>
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<th align="right">
										产品类型:
									</th>
									<td>
										<select name="procardTemplate.productStyle"
											id="zuheproductStyle" style="width: 155px;">
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
										是否外购:
									</th>
									<td>
										<select name="procardTemplate.status" style="width: 155px;"
											id="zuhestatus">
											<option value="是">
												是
											</option>
											<option value="否">
												否
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
											id="zuhelingliaostatus" style="width: 155px;">
											<option value="是">
												是
											</option>
											<option value="否">
												否
											</option>
										</select>
										<font color="red">*</font>
									</td>
								</tr>
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
										<%--										<div id="showAllycl"--%>
										<%--											style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">--%>
										<%--										</div>--%>
										<%--										<input type="text" id="shortnameycl"--%>
										<%--											onkeyup="getAllNamesycl()" style="height: 20px"--%>
										<%--											onFocus="init('shortnameycl','showAllycl')"--%>
										<%--											onBlur="hidediv('showAllycl')" name="markId" />--%>
										<input type="text" id="zuhetrademark" readonly="readonly"
											name="procardTemplate.trademark">
										<input type="button" value="选择原材料"
											onclick="selectYclAndWgj('zuhe')">
									</td>
								</tr>
								<tr>
									<th align="right">
										规格:
									</th>
									<td>
										<input type="text" id="zuhespecification"
											name="procardTemplate.specification" readonly="readonly">
									</td>
								</tr>
								<tr>
									<th align="right">
										原材料名称:
									</th>
									<td>
										<input type="text" id="zuheyuanName"
											name="procardTemplate.yuanName" readonly="readonly">
									</td>
								</tr>
								<tr>
									<th align="right">
										原材料图号:
									</th>
									<td>
										<input type="text" id="zuheytuhao"
											name="procardTemplate.ytuhao" readonly="readonly">
									</td>
								</tr>
								<tr>
									<th align="right">
										单张重量:
									</th>
									<td>
										<input type="text" id="zuhebili" name="procardTemplate.bili"
											readonly="readonly">
									</td>
								</tr>
								<tr>
									<th align="right">
										单位:
									</th>
									<td>
										<input type="text" id="zuheyuanUnit"
											name="procardTemplate.yuanUnit" readonly="readonly">
									</td>
								</tr>
								<tr>
									<th align="right">
										材质类型:
									</th>
									<td>
										<input type="text" id="zuhewgType"
											name="procardTemplate.wgType" readonly="readonly">
									</td>
								</tr>
								<tr>
									<th align="right">
										权值:
									</th>
									<td>
										<input name="procardTemplate.quanzi1" style="width: 71px;"
											id="zuhequanzi1" />
										:
										<input name="procardTemplate.quanzi2" style="width: 71px;"
											id="zuhequanzi2" />
										(自制件:原材料,格式如1:1)
									</td>
								</tr>
								<tr>
								<th align="right">
									长
								</th>
								<td>
									<input type="text" id="zuhethisLength"
										name="procardTemplate.thisLength">
								</td>
							</tr>
							<tr>
								<th align="right">
									宽
								</th>
								<td>
									<input type="text" id="zuhethisWidth"
										name="procardTemplate.thisWidth">
								</td>
							</tr>
							<tr>
								<th align="right">
									高
								</th>
								<td>
									<input type="text" id="zuhethisHight"
										name="procardTemplate.thisHight">

								</td>
							</tr>
								<tr>
									<th align="right">
										余料加工:
									</th>
									<td>
										<select name="procardTemplate.jgyl" id="zuhejgyl">
											<option value="yes">
												是
											</option>
											<option value="no">
												否
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<th align="right">
										炉号:
									</th>
									<td>
										<input name="procardTemplate.luhao" id="zuheluhao">
									</td>
								</tr>
								<tr>
									<th align="right">
										编号:
									</th>
									<td>
										<input name="procardTemplate.number" id="zuhenumber">
									</td>
								</tr>
								<tr>
									<th align="right">
										实际定额:
									</th>
									<td>
										<input name="procardTemplate.actualFixed" id="zuheactualFixed">
									</td>
								</tr>

								<tr>
									<th align="right">
										是否外购:
									</th>
									<td>
										<select name="procardTemplate.status" style="width: 155px;"
											id="zuhestatus">
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
										工艺编号:
									</th>
									<td>
										<input name="procardTemplate.numb" id="zuhenumb">
									</td>
								</tr>
								<tr>
									<th align="right">
										发出日:
									</th>
									<td>
										<input class="Wdate" type="text"
											name="procardTemplate.fachuDate" id="zuhefachuDate"
											readonly="readonly"
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
									</td>
								</tr>
								<tr>
									<th align="right">
										页数:
									</th>
									<td>
										<input name="procardTemplate.pageTotal" id="zuhepageTotal"
											onkeyup="mustBeNumber('pageTotal')">
									</td>
								</tr>
								<tr>
									<th align="right">
										编辑人 ：
									</th>
									<td>
										<select name="procardTemplate.bianzhiId" class="bianzhi"
											id="zuhebianzhiId"></select>
										<%--
										时间 ：
										<input class="Wdate" type="text"
											name="procardTemplate.bianzhiDate" id="bianzhiDate"
											readonly="readonly"
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
									--%>
									</td>
								</tr>
								<tr>
									<th align="right">
										校对人 ：
									</th>
									<td>
										<select name="procardTemplate.jiaoduiId" class="jiaodui"
											id="zuhejiaoduiId"></select>
										<%--
										时间 ：
										<input class="Wdate" type="text"
											name="procardTemplate.jiaoduiDate" id="jiaoduiDate"
											readonly="readonly"
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
									--%>
									</td>
								</tr>
								<tr>
									<th align="right">
										审核人 ：
									</th>
									<td>
										<select name="procardTemplate.shenheId" class="shenhe"
											id="zuheshenheId"></select>
										<%--
										时间 ：
										<input class="Wdate" type="text"
											name="procardTemplate.shenheDate" id="shenheDate"
											readonly="readonly"
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
									--%>
									</td>
								</tr>
								<tr>
									<th align="right">
										批准人 ：
									</th>
									<td>
										<select name="procardTemplate.pizhunId" class="pizhun"
											id="zuhepizhunId"></select>
										<%--
										时间 ：
										<input class="Wdate" type="text"
											name="procardTemplate.pizhunDate" id="pizhunDate"
											readonly="readonly"
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
									--%>
									</td>
								</tr>
								<tr>
									<th align="right">
										备注:
									</th>
									<td>
										<input name="procardTemplate.remark" id="zuheremark">
									</td>
								</tr>
								<tr>
									<td align="center" colspan="2">
										<input type="button" value="添加"
											onclick="submitForm('lingForm')"
											style="width: 80px; height: 40px;" />
										<input type="reset" value="重置"
											style="width: 80px; height: 40px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="module1_2"
						style="display: none; background-color: #ffffff">
						<form id="waiForm" style="margin: 0px; padding: 0px;">
							<input type="hidden" id="rootId" name="procardTemplate.rootId" />
							<input type="hidden" id="fatherId"
								name="procardTemplate.fatherId" />
							<input type="hidden" id="belongLayer"
								name="procardTemplate.belongLayer" />
							<table class="table" style="width: 100%;">
								<tr>
									<th align="center" colspan="2">
										添加外购件工艺卡片模版
										<input type="submit" value="保存" />
										<input type="reset" value="重置" />
									</th>
								</tr>
								<tr>
									<th align="right" style="width: 25%;">
										件号：
									</th>
									<td>
										<%--										<div id="showAllwgj"--%>
										<%--											style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">--%>
										<%--										</div>--%>
										<%--										<input id="shortnamewgj" onkeyup="getAllNameswgj()"--%>
										<%--											style="height: 20px"--%>
										<%--											onFocus="init('shortnamewgj','showAllwgj')"--%>
										<%--											onBlur="hidediv('showAllwgj')">--%>
										<input type="text" id="wajmarkId"
											name="procardTemplate.markId" readonly="readonly" />
										<input type="button" value="选择外购件"
											onclick="selectYclAndWgj('wgj')">
										<font color="red">*</font>
										<a href="<%=path%>/System/yclandwgj/yuanclAndWaigj_add.jsp" target="_showWai">找不到需要的件号?前往添加</a>
									</td>
								</tr>
								<tr>
									<th align="right">
										名称:
									</th>
									<td>
										<input type="text" id="wajproName"
											name="procardTemplate.proName" readonly="readonly" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<th align="right">
										规格:
									</th>
									<td>
										<input type="text" id="wajspecification"
											name="procardTemplate.specification" readonly="readonly">
									</td>
								</tr>
								<tr>
									<th align="right">
										单位:
									</th>
									<td>
										<input type="text" id="danwei2" name="procardTemplate.unit"
											readonly="readonly" />
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<th align="right">
										图号:
									</th>
									<td>
										<input type="text" id="wajtuhao" name="procardTemplate.tuhao"
											readonly="readonly" />
									</td>
								</tr>
								<tr>
									<th align="right">
										版本号:
									</th>
									<td>
										<input type="text" id="wajbanBenNumber"
											name="procardTemplate.banBenNumber" readonly="readonly">
									</td>
								</tr>
								<tr>
									<th align="right">
										初始总成:
									</th>
									<td>
										<input type="text" id="wajloadMarkId"
											name="procardTemplate.loadMarkId">
									</td>
								</tr>
								<tr>
									<th align="right">
										材质类型:
									</th>
									<td>
										<input type="text" id="wajwgType"
											name="procardTemplate.wgType" readonly="readonly">
									</td>
								</tr>
								<tr>
								<th align="right">
									长
								</th>
								<td>
									<input type="text" id="wajthisLength"
										name="procardTemplate.thisLength">
								</td>
							</tr>
							<tr>
								<th align="right">
									宽
								</th>
								<td>
									<input type="text" id="wajthisWidth"
										name="procardTemplate.thisWidth">
								</td>
							</tr>
							<tr>
								<th align="right">
									高
								</th>
								<td>
									<input type="text" id="wajthisHight"
										name="procardTemplate.thisHight">

								</td>
							</tr>
								<tr>
									<th align="right">
										车型:
									</th>
									<td>
										<input name="procardTemplate.carStyle">
									</td>
								</tr>
								<tr>
									<th align="right">
										卡片类型:
									</th>
									<td>
										<input name="procardTemplate.procardStyle" value="外购"
											readonly="readonly" />
									</td>
								</tr>
								<tr>
									<th align="right">
										供料属性:
									</th>
									<td>
										<select name="procardTemplate.kgliao">
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
								<tr>
									<th align="right">
										权值:
									</th>
									<td>
										<input name="procardTemplate.quanzi1" style="width: 71px;" />
										:
										<input name="procardTemplate.quanzi2" style="width: 71px;" />
										(组合:外购件,格式如1:1)
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<th align="right">
										产品类型:
									</th>
									<td>
										<select name="procardTemplate.productStyle"
											style="width: 155px;">
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
										是否半成品:
									</th>
									<td>
										<select name="procardTemplate.needProcess" id="neddProcess"
											style="width: 155px;"
											onchange="changeneedProcess(this.value)">
											<option value="no">
												否
											</option>
											<option value="yes">
												是
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
											id="zizlingliaostatus" style="width: 155px;">
											<option value="是">
												是
											</option>
											<option value="否">
												否
											</option>
										</select>
										<font color="red">*</font>
									</td>
								</tr>
								<tr id="safetr" style="display: none;">
									<th align="right">
										安全库存:
									</th>
									<td>
										<input name="procardTemplate.safeCount">
									</td>
								</tr>
								<tr id="lasttr" style="display: none;">
									<th align="right">
										最低存量:
									</th>
									<td>
										<input name="procardTemplate.lastCount">
									</td>
								</tr>
								<tr>
									<th align="right">
										工艺编号:
									</th>
									<td>
										<input name="procardTemplate.numb" id="wajnumb">
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
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
									</td>
								</tr>
								<tr>
									<th align="right">
										页数:
									</th>
									<td>
										<input name="procardTemplate.pageTotal" id="pageTotal"
											onkeyup="mustBeNumber('pageTotal')">
									</td>
								</tr>
								<tr>
									<th align="right">
										编辑人 ：
									</th>
									<td>
										<select name="procardTemplate.bianzhiId" class="bianzhi"></select>
										<%--
										时间 ：
										<input class="Wdate" type="text"
											name="procardTemplate.bianzhiDate" id="bianzhiDate"
											readonly="readonly"
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
									--%>
									</td>
								</tr>
								<tr>
									<th align="right">
										校对人 ：
									</th>
									<td>
										<select name="procardTemplate.jiaoduiId" class="jiaodui"></select>
										<%--
										时间 ：
										<input class="Wdate" type="text"
											name="procardTemplate.jiaoduiDate" id="jiaoduiDate"
											readonly="readonly"
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
									--%>
									</td>
								</tr>
								<tr>
									<th align="right">
										审核人 ：
									</th>
									<td>
										<select name="procardTemplate.shenheId" class="shenhe"></select>
										<%--
										时间 ：
										<input class="Wdate" type="text"
											name="procardTemplate.shenheDate" id="shenheDate"
											readonly="readonly"
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
									--%>
									</td>
								</tr>
								<tr>
									<th align="right">
										批准人 ：
									</th>
									<td>
										<select name="procardTemplate.pizhunId" class="pizhun"></select>
										<%--
										时间 ：
										<input class="Wdate" type="text"
											name="procardTemplate.pizhunDate" id="pizhunDate"
											readonly="readonly"
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
									--%>
									</td>
								</tr>
								<tr>
									<th align="right">
										备注:
									</th>
									<td>
										<input name="procardTemplate.remark">
									</td>
								</tr>
								<tr>
									<td align="center" colspan="2">
										<input type="button" value="添加"
											onclick="submitForm('waiForm')"
											style="width: 80px; height: 40px;" />
										<input type="reset" value="重置"
											style="width: 80px; height: 40px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="module1_3"
						style="display: none; background-color: #ffffff">
						<form id="yuanForm" style="margin: 0px; padding: 0px;">
							<input type="hidden" id="yrootId" name="procardTemplate.rootId" />
							<input type="hidden" id="yfatherId"
								name="procardTemplate.fatherId" />
							<input type="hidden" id="ybelongLayer"
								name="procardTemplate.belongLayer" />
							<table class="table" style="width: 100%;">
								<tr>
									<th align="center" colspan="2">
										添加自制件流水卡片模版
										<input type="submit" value="保存" />
										<input type="reset" value="重置" />
									</th>
								</tr>
								<tr>
									<th align="right" style="width: 25%;">
										件号:
									</th>
									<td>
										<div id="showAll2"
											style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">
										</div>
										<input type="hidden" value="0" id="shortnameId2">
										<input name="procardTemplate.markId"
											onBlur="hidediv('showAll2')"
											onkeyup="noZhongwen(this,'zizmarkId','showAll2','shortnameId2','自制')"
											onFocus="initfz('zizmarkId','showAll2',130,230)"
											id="zizmarkId">
										<font color="red">*</font>
										<div id="zizmarkIddiv"></div>
									</td>
								</tr>
								<tr>
									<th align="right">
										名称:
									</th>
									<td>
										<input name="procardTemplate.proName" id="zizproName">
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<th align="right">
										图号:
									</th>
									<td>
										<input name="procardTemplate.tuhao" id="ziztuhao">
									</td>
								</tr>
								<tr>
									<th align="right">
										版本号:
									</th>
									<td>
										<input name="procardTemplate.banBenNumber"
											id="zizbanBenNumber">
									</td>
								</tr>
								<tr>
									<th align="right">
										车型:
									</th>
									<td>
										<input name="procardTemplate.carStyle" id="zizcarStyle">
									</td>
								</tr>

								<tr>
									<th align="right">
										数量:
									</th>
									<td>
										<input name="procardTemplate.corrCount" id="corrCount">
										(权值,对应上层所需数量)
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<th align="right">
										安全库存:
									</th>
									<td>
										<input name="procardTemplate.safeCount" id="zizsafeCount">
									</td>
								</tr>
								<tr>
									<th align="right">
										最低存量:
									</th>
									<td>
										<input name="procardTemplate.lastCount" id="zizlastCount">
									</td>
								</tr>
								<tr>
									<th align="right">
										备注:
									</th>
									<td>
										<input name="procardTemplate.remark" id="zizremark">
									</td>
								</tr>
								<tr>
									<th align="right">
										单位:
									</th>
									<td>
										<select id="danwei3" name="procardTemplate.unit" id="zizunit"
											style="width: 155px;">
										</select>
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<th align="right">
										卡片类型:
									</th>
									<td>
										<input name="procardTemplate.procardStyle" value="自制"
											readonly="readonly" id="zizprocardStyle" />
									</td>
								</tr>
								<tr>
									<th align="right">
										产品类型:
									</th>
									<td>
										<select name="procardTemplate.productStyle"
											id="zizproductStyle" style="width: 155px;">
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
											id="zizlingliaostatus" style="width: 155px;">
											<option value="是">
												是
											</option>
											<option value="否">
												否
											</option>
										</select>
										<font color="red">*</font>
									</td>
								</tr>
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
										<%--										<div id="showAllycl"--%>
										<%--											style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">--%>
										<%--										</div>--%>
										<%--										<input type="text" id="shortnameycl"--%>
										<%--											onkeyup="getAllNamesycl()" style="height: 20px"--%>
										<%--											onFocus="init('shortnameycl','showAllycl')"--%>
										<%--											onBlur="hidediv('showAllycl')" name="markId" />--%>
										<input type="text" id="ziztrademark" readonly="readonly"
											id="ziztrademark" name="procardTemplate.trademark">
										<input type="button" value="选择原材料"
											onclick="selectYclAndWgj('ycl')">
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<th align="right">
										规格:
									</th>
									<td>
										<input type="text" id="zizspecification"
											name="procardTemplate.specification" readonly="readonly">
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<th align="right">
										原材料名称:
									</th>
									<td>
										<input type="text" id="zizyuanName"
											name="procardTemplate.yuanName" readonly="readonly">
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<th align="right">
										图号:
									</th>
									<td>
										<input type="text" id="zizytuhao"
											name="procardTemplate.ytuhao" readonly="readonly">
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
											name="procardTemplate.yuanUnit" readonly="readonly">
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<th align="right">
										材质类型:
									</th>
									<td>
										<input type="text" id="zizwgType"
											name="procardTemplate.wgType" readonly="readonly">
									</td>
								</tr>
								<tr>
								<th align="right">
									长
								</th>
								<td>
									<input type="text" id="zizthisLength"
										name="procardTemplate.thisLength">
								</td>
							</tr>
							<tr>
								<th align="right">
									宽
								</th>
								<td>
									<input type="text" id="zizthisWidth"
										name="procardTemplate.thisWidth">
								</td>
							</tr>
							<tr>
								<th align="right">
									高
								</th>
								<td>
									<input type="text" id="zizthisHight"
										name="procardTemplate.thisHight">

								</td>
							</tr>
								<tr>
									<th align="right">
										权值:
									</th>
									<td>
										<input name="procardTemplate.quanzi1" style="width: 71px;"
											id="zizquanzi1" />
										:
										<input name="procardTemplate.quanzi2" style="width: 71px;"
											id="zizquanzi2" />
										(自制件:原材料,格式如1:1)
										<font color="red">*</font>
									</td>
								</tr>
								<tr>
									<th align="right">
										余料加工:
									</th>
									<td>
										<select name="procardTemplate.jgyl" id="zizjgyl">
											<option value="yes">
												是
											</option>
											<option value="no">
												否
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<th align="right">
										炉号:
									</th>
									<td>
										<input name="procardTemplate.luhao" id="zizluhao">
									</td>
								</tr>
								<tr>
									<th align="right">
										编号:
									</th>
									<td>
										<input name="procardTemplate.number" id="ziznumber">
									</td>
								</tr>
								<tr>
									<th align="right">
										实际定额:
									</th>
									<td>
										<input name="procardTemplate.actualFixed" id="zizactualFixed">
									</td>
								</tr>

								<tr>
									<th align="right">
										是否外购:
									</th>
									<td>
										<select name="procardTemplate.status" style="width: 155px;"
											id="zizstatus">
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
										工艺编号:
									</th>
									<td>
										<input name="procardTemplate.numb" id="ziznumb">
									</td>
								</tr>
								<tr>
									<th align="right">
										发出日:
									</th>
									<td>
										<input class="Wdate" type="text"
											name="procardTemplate.fachuDate" id="zizfachuDate"
											readonly="readonly"
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
									</td>
								</tr>
								<tr>
									<th align="right">
										页数:
									</th>
									<td>
										<input name="procardTemplate.pageTotal" id="zizpageTotal"
											onkeyup="mustBeNumber('pageTotal')">
									</td>
								</tr>
								<tr>
									<th align="right">
										编辑人 ：
									</th>
									<td>
										<select name="procardTemplate.bianzhiId" class="zizbianzhiId"></select>
										<%--
										时间 ：
										<input class="Wdate" type="text"
											name="procardTemplate.bianzhiDate" id="bianzhiDate"
											readonly="readonly"
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
									--%>
									</td>
								</tr>
								<tr>
									<th align="right">
										校对人 ：
									</th>
									<td>
										<select name="procardTemplate.jiaoduiId" class="jiaodui"
											id="zizjiaoduiId"></select>
										<%--
										时间 ：
										<input class="Wdate" type="text"
											name="procardTemplate.jiaoduiDate" id="jiaoduiDate"
											readonly="readonly"
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
									--%>
									</td>
								</tr>
								<tr>
									<th align="right">
										审核人 ：
									</th>
									<td>
										<select name="procardTemplate.shenheId" class="shenhe"
											id="shenheId"></select>
										<%--
										时间 ：
										<input class="Wdate" type="text"
											name="procardTemplate.shenheDate" id="shenheDate"
											readonly="readonly"
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
									--%>
									</td>
								</tr>
								<tr>
									<th align="right">
										批准人 ：
									</th>
									<td>
										<select name="procardTemplate.pizhunId" class="pizhun"
											id="zizpizhunId"></select>
										<%--
										时间 ：
										<input class="Wdate" type="text"
											name="procardTemplate.pizhunDate" id="pizhunDate"
											readonly="readonly"
											onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />
									--%>
									</td>
								</tr>
								<tr>
									<td align="center" colspan="2">
										<input type="button" value="添加"
											onclick="submitForm('yuanForm')"
											style="width: 80px; height: 40px;" />
										<input type="reset" value="重置"
											style="width: 80px; height: 40px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="module1_4"
						style="display: none; background-color: #ffffff" align="center">
						<div>
							<table id="ProcessTab" class="table" style="width: 100%;">
							</table>
						</div>
						<br />
						<br />
						<form id="processForm"
							action="javascript:submitForm2('processForm');"
							style="margin: 0px; padding: 0px;" method="post">
							<input id="cardId" type="hidden" name="id" />
							<input id="parallelId" type="hidden"
								name="processTemplate.parallelId" />
							<input type="hidden" name="processTemplate.optechnologyRate"
								value="0" />
							<input type="hidden" name="processTemplate.opCouldReplaceRate"
								value="0" />
							<input type="hidden" name="processTemplate.opfuheRate" value="0" />
							<input type="hidden" name="processTemplate.opcaozuojiepai"
								value="0" />
							<input type="hidden" name="processTemplate.opshebeijiepai"
								value="0" />
							<input type="hidden" name="processTemplate.opnoReplaceRate"
								value="0" />
							<input type="hidden" name="processTemplate.opzonghezhishu"
								value="0" />
							<input type="hidden" name="processTemplate.opzongheqiangdu"
								value="0" />
							<input type="hidden" name="processTemplate.gztechnologyRate"
								value="0" />
							<input type="hidden" name="processTemplate.gzCouldReplaceRate"
								value="0" />
							<input type="hidden" name="processTemplate.gzfuheRate" value="0" />
							<input type="hidden" name="processTemplate.gzzhunbeijiepai"
								value="0" />
							<input type="hidden" name="processTemplate.gzzhunbeicishu"
								value="0" />
							<input type="hidden" name="processTemplate.gznoReplaceRate"
								value="0" />
							<input type="hidden" name="processTemplate.gzzonghezhishu"
								value="0" />
							<input type="hidden" name="processTemplate.gzzongheqiangdu"
								value="0" />
							<input type="hidden" name="processTemplate.processMomey"
								value="0" />
							<input type="hidden" name="processTemplate.opjiaofu" value="0" />
						</form>
					</div>
					<div id="module1_5"
						style="display: none; background-color: #ffffff" align="center">
						<br />
						<br />
						<br />
						<input id="mfatherId" type="hidden" />
						<div id="showAll"
							style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">
						</div>
						<input type="text" id="shortname" onkeyup="getAllNames()"
							style="height: 20px;" onFocus="initfz()"
							onBlur="hidediv('showAll')" name="markId" />
						<input type="hidden" value="0" id="shortnameId">
						<input type="button" value="复制" onclick="copyProcard()">
						<br />
						<br />
						<br />
					</div>
					<div id="module1_6"
						style="display: none; background-color: #ffffff" align="center">
						<form action="" method="get">
							<table>
								<tr>
									<th>
										原件号
									</th>
									<td>
										<label id="oldMarkId"></label>
									</td>
								</tr>
								<tr>
									<th>
										新件号
									</th>
									<td>
										<input id="newMarkId" name="newMarkId">
									</td>
								</tr>
								<tr align="center">
									<td colspan="2">
										<input type="button" value="修改自己"
											onclick="updateMarkId(false)">
										<input type="button" value="修改所有" onclick="updateMarkId(true)">
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="module1_9"
						style="display: none; background-color: #ffffff" align="center">
						<br />
						<br />
						<br />
						<form action="" method="get">
							<table>
								<tr>
									<th>
										批产件号
									</th>
									<td>
										<input id="lpMarkId" name="lpMarkId">
									</td>
								</tr>
								<tr id='dd9' style="display: none;">
									<td align="center">
										<h3>
											<font color="red">正在转换请耐心等待.............</font>
										</h3>
									</td>
								</tr>
								<tr align="center">
									<td colspan="2">
										<input type="button" value="转换" onclick="changeTolp()">
									</td>
								</tr>
							</table>
						</form>
						<br />
						<br />
						<br />
					</div>
					<div id="module1_12"
						style="display: none; background-color: #ffffff" align="center">
						<br />
						<br />
						<br />
						<h3>
							<font color="red">正在转换请耐心等待.............</font>
						</h3>
						<br />
						<br />
						<br />
					</div>
					<div id="showCardTemplate"
						style="display: none; height: 1000px; background-color: #ffffff">
						<iframe id="showCardIframe" src="" marginwidth="0"
							marginheight="0" hspace="0" vspace="0" frameborder="0"
							scrolling="no"
							style="width: 100%; height: 1500px; margin: 0px; padding: 0px;"></iframe>
					</div>
				</div>
				<div style="clear: both;"></div>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.excheck-3.5.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/zTree/js/jquery.ztree.exedit-3.5.js">
</script>
		<script type="text/javascript">
//========================================zTree显示
var module4 = $("#module4");
//自动组装树形结构
var setting = {
	edit : {
		enable : true,
		showRemoveBtn : false,
		showRenameBtn : false,
		showTitle : true
	},
	data : {
		simpleData : {
			enable : true
		},
		key : {
			title : "title"
		}
	},
	callback : {
		onClick : onClick,
		beforeDrag : beforeDrag,
		beforeDrop : beforeDrop,
		onDrag : onDrag
	},
	view : {
		fontCss : getFont,
		nameIsHTML : true,
		showTitle : true
	}
};

function setCheck() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), isCopy = true, isMove = true, prev = true, inner = true, next = true;
	zTree.setting.edit.drag.isCopy = isCopy;
	zTree.setting.edit.drag.isMove = isMove;

	zTree.setting.edit.drag.prev = prev;
	zTree.setting.edit.drag.inner = inner;
	zTree.setting.edit.drag.next = next;
}

//加载树形数据
$(document).ready(loadTree());
var totalMaxCount = 0;
//生成
function loadTree() {
	$
			.ajax( {
				url : 'ProcardTemplateAction!findProcardTemByRootId.action',
				type : 'post',
				dataType : 'json',
				data : {
					id : '${param.id}',
					pageStatus : '${pageStatus}'
				},
				cache : true,
				success : function(doc) {
					var zNodes = [];
					$(doc)
							.each(function() {
								//var b = true;
									if ($(this).attr('procardStyle') == "自制"
											|| $(this).attr('procardStyle') == "外购") {
										//b = false;
									} else if ($(this).attr('procardStyle') == "总成") {
										totalMaxCount = $(this)
												.attr('maxCount');
									}
									var procardStyle = $(this).attr(
											'procardStyle');
									if (procardStyle == "待定") {
										procardStyle = "<span style='color:red;margin-right:0px;'>待定</span>";
									}
									//单交件状态
									var danjiaojian = $(this).attr(
											'danjiaojian');
									if (danjiaojian == null) {
										danjiaojian = "";
									}
									//半成品状态
									var needProcess = $(this).attr(
											'needProcess');
									if (needProcess == "yes") {
										needProcess = "(半成品)";
									} else {
										needProcess = "";
									}
									var bzStatus = $(this).attr('bzStatus');
									if (bzStatus == null || bzStatus == "") {
										bzStatus = "初始";
									}
									if (bzStatus != "已批准") {
										bzStatus = "<span style='color:red;margin-right:0px;'>"
												+ bzStatus + "</span>";
									} else {
										bzStatus = "";
									}

									var hgstyle = "<span style='font-weight: bolder;font-size: 18px;'>--</span>";
									zNodes
											.push( {
												id : $(this).attr('id'),
												sbStatus : $(this).attr(
														'sbStatus'),
												bzStatus : $(this).attr(
														'bzStatus'),
												bomApplyStatus : $(this).attr(
														'bomApplyStatus'),
												epId2 : $(this).attr('epId2'),
												pId : $(this).attr('fatherId'),
												proStruts : $(this).attr(
														'procardStyle'),
												rootId : $(this).attr('rootId'),
												markId : $(this).attr('markId'),
												danjiaojian : danjiaojian,
												productStyle : $(this).attr(
														'productStyle'),
												belongLayer : $(this).attr(
														'belongLayer'),
												name : "<span style='font-weight: bolder;font-size: 12px;'>"
														+ procardStyle
														+ needProcess
														+ "</span>"
														+ hgstyle
														+ $(this)
																.attr('markId')
														+ hgstyle
														+ $(this).attr(
																'proName')
														+ danjiaojian
														+ " "
														+ bzStatus,
												title : $(this).attr(
														'procardStyle')
														+ '--'
														+ $(this)
																.attr('markId')
														+ '--'
														+ $(this).attr(
																'proName')
														+ danjiaojian
														+ needProcess,
												click : false,
												drop : true,
												open : true
											});

								});
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				},
				error : function() {
					alert("服务器异常!");
				}
			});

}
function getFont(treeId, node) {
	return node.font ? node.font : {};
}
var moveId = 0;
function onDrag(event, treeId, treeNodes) {
	moveId = treeNodes[0].id;
}
function beforeDrag(treeId, treeNodes) {
	for ( var i = 0, l = treeNodes.length; i < l; i++) {
		if (treeNodes[i].drag === false) {
			return false;
		}
	}
	return true;
}
function beforeDrop(treeId, treeNodes, targetNode, moveType) {
	if (!window.confirm("是否移动bom结构")) {
		return false;
	}
	if (targetNode.id == null) {
		return false;
	}
	var moveok = targetNode ? targetNode.drop !== false : true;
	if (!moveok) {
		return moveok;
	}
	if (moveok) {
		//alert(moveId);
		$.ajax( {
			type : "POST",
			url : "ProcardTemplateAction!moveProcardTemplate.action",
			dataType : "json",
			data : {
				moveId : moveId,
				targetId : targetNode.id
			},
			success : function(msg) {
				alert(msg.message);
				loadTree();
				return true;
			}
		});
	}
}
var oldObj;
var oldObj2;
//切换添加类型
function chageModule(obj, obj2, bcpStatus) {
	if (oldObj != null) {
		$("#module1_" + oldObj2).hide();
	}

	if (bcpStatus) {
		$("#neddProcess").val("yes");
		changeneedProcess("yes");
		obj2 = "2";
	} else {
		$("#neddProcess").val("no");
		changeneedProcess("no");
	}
	$("#module1_" + obj2).show('slow');
	oldObj = obj;
	oldObj2 = obj2;
}

//点击回调函数
function onClick(event, treeId, treeNode, clickFlag) {
	var proStruts = treeNode.proStruts;// 卡片状态(总成，零组件，原材料)
	var danjiaojian = treeNode.danjiaojian;// 卡片状态(总成，零组件，原材料)
	var productStyle = treeNode.productStyle;//生产类型
	var sbStatus = treeNode.sbStatus;//设变申请状态
	var bzStatus = treeNode.bzStatus;//编制状态
	var bomApplyStatus = treeNode.bomApplyStatus;//编制状态
	if (proStruts == "总成"
			&& (bomApplyStatus == null || (!bomApplyStatus == "未审批"
					&& !bomApplyStatus == "审批中" && !bomApplyStatus == "同意"))) {
		$("#module15").attr("disabled", false);
	} else {
		$("#module15").attr("disabled", true);
	}
	if (bomApplyStatus == "未审批" || bomApplyStatus == "审批中"
			|| bomApplyStatus == "同意") {
		$("#module16").show();
	} else {
		$("#module16").hide();
	}

<%--	if(bzStatus=="已批准"){--%>
<%--		if(sbStatus=="未审批"||sbStatus=="审批中"){--%>
<%--			$("#module14").attr("disabled", true);--%>
<%--		}else{--%>
<%--			$("#module14").attr("disabled", false);--%>
<%--		}--%>
<%--	}else{--%>
<%--		$("#module14").attr("disabled", true);--%>
<%--	}--%>
	//工序赋值
	$("#cardId").val(treeNode.id);
	$("#mfatherId").val(treeNode.id);
	$("#uId").val(treeNode.id);
	$("#newMarkId").val(treeNode.markId);
	$("#oldMarkId").empty();
	$("#oldMarkId").append(treeNode.markId);
	showDiv();//页面内容清空
	//显示添加选项的页面
	$("#selectDiv").show();
	var e = event || window.event;
	var scrollTop = getScrollTop();//获取滚动条离顶部距离
	var mouseLeft = e.clientX + 150;
	var mouseTop = e.clientY - 80 + scrollTop;
	if (mouseTop < 0) {
		mouseTop = 0;
	}
	//显示悬浮窗样式的项目
	$("#showProDetail").css( {
		"top" : mouseTop,
		"left" : mouseLeft
	});

	if (proStruts == "总成" && productStyle == "试制") {
		$("#module9").show();
	} else {
		$("#module9").hide();
	}
	if (proStruts == "总成") {
		$("#module12").show();
	} else {
		$("#module12").hide();
	}
if (proStruts == "总成" || proStruts == "组合") {
		//零组件赋值
		$("#rootId").val(treeNode.rootId);
		$("#fatherId").val(treeNode.id);
		$("#belongLayer").val(treeNode.belongLayer + 1);
		//外购件赋值
		$("#wrootId").val(treeNode.rootId);
		$("#wfatherId").val(treeNode.id);
		$("#wbelongLayer").val(treeNode.belongLayer + 1);
		//原材料赋值
		$("#yrootId").val(treeNode.rootId);
		$("#yfatherId").val(treeNode.id);
		$("#ybelongLayer").val(treeNode.belongLayer + 1);
		if (danjiaojian == "单交件") {
			//全部不可用
			$("#module1").attr("disabled", true);
			$("#module2").attr("disabled", true);
			$("#module2_1").attr("disabled", true);
			$("#module3").attr("disabled", true);
			$("#module4").attr("disabled", false);
			$("#module5").attr("disabled", false);
<%--			$("#module6").attr("disabled", false);--%>
		} else {
			//全部可用
			$("#module1").attr("disabled", false);
			$("#module2").attr("disabled", false);
			$("#module2_1").attr("disabled", false);
			$("#module3").attr("disabled", false);
			$("#module4").attr("disabled", false);
			$("#module5").attr("disabled", false);
<%--			$("#module6").attr("disabled", false);--%>
			if (proStruts == "总成") {
<%--				$("#module6").attr("disabled", false);--%>
			}
		}
	} else if (proStruts == "外购") {
		//全部不可用
		$("#module1").attr("disabled", true);
		$("#module2").attr("disabled", true);
		$("#module2_1").attr("disabled", true);
		$("#module3").attr("disabled", true);
		$("#module4").attr("disabled", true);
		$("#module5").attr("disabled", true);
		$("#module5").attr("disabled", true);
<%--		$("#module6").attr("disabled", false);--%>
	} else if (proStruts == "自制") {
		//全部不可用
		$("#module1").attr("disabled", true);
		$("#module2").attr("disabled", true);
		$("#module2_1").attr("disabled", true);
		$("#module3").attr("disabled", true);
		$("#module4").attr("disabled", false);
		$("#module5").attr("disabled", false);
<%--		$("#module6").attr("disabled", false);--%>
	}

	//关闭已打开的功能Div
	if (oldObj != null) {
		$("#module1_" + oldObj2).hide();
	}

	//显示流水卡片明细
	$("#bodyDiv").show();
	$("#showProDetail").show();
	$("#showCardTemplate").show();
	$("#showCardIframe")
			.attr(
					"src",
					"ProcardTemplateAction!findCardTemForShow.action?id="
							+ treeNode.id);
	$("#bodyDiv").bind("click", function() {
		$("#bodyDiv").hide();
		$("#showProDetail").hide();
		$("#showCardTemplate").hide();
	});
}
//添加组件/原材料流水卡片
function submitForm(formId) {
	if(formId=="lingForm"){//组合
		var makrId=$("#zuhemarkId").val();
		var proName=$("#zuheproName").val();
		var corrCount=$("#zuhecorrCount").val();
		var lingliaostatus=$("#zuhelingliaostatus").val();
		var trademark=$("#zuhetrademark").val();
		if(makrId==null||makrId==""){
			alert("请填写件号!");
			return false;
		}
		if(proName==null||proName==""){
			alert("请填写名称!");
			return false;
		}
		if(corrCount==null||corrCount==""){
			alert("请填写组合与上层的比例!");
			return false;
		}
		if((lingliaostatus==null||lingliaostatus==""||lingliaostatus=="是")&&trademark.length>0){
			var specification=$("#zuhespecification").val();
			if(specification==null||specification==""){
				alert("该组合需领料且有原材料，请填写原材料规格!");
				return false;
			}
			var quanzi1=$("#zuhequanzi1").val();
			var quanzi2=$("#zuhequanzi2").val();
			if(quanzi1==null||quanzi1==""||quanzi2==null||quanzi2==""){
				alert("该组合需领料且有原材料，请填写原材料的权值比例!");
				return false;
			}
		}
	}
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!addProcardTemplate.action",
		dataType : "json",
		data : $("#" + formId).serialize(),
		success : function(obj) {
			if (obj.success) {
				alert("添加成功!");
				loadTree();
			} else {
				alert(obj.message);
			}
		}
	});
}

//显示卡片已有工序
function showProcess() {
	$
			.ajax( {
				type : "POST",
				url : "ProcardTemplateAction!findProcessByFkId.action",
				dataType : "json",
				data : {
					id : $("#cardId").val()
				},
				success : function(msg) {
					$("#ProcessTab").empty();
					$("#ProcessTab")
							.append(
									"<tr><th colspan='8'>已有工序(<a onclick='toAddProcess()'>添加工序</a>)</th></tr>"
											+ "<tr><th>工序号</th><th>名称</th><th>总节拍(s)</th><th>生产类型</th><th>特殊工序</th>"
											+ "<th>是否并行</th><th>是否首检</th><th>操作</th></tr>");
					var maxProcessNO = 5;//最大工序号
					var i=0;
					$(msg)
							.each(
									function() {
										var isSpecial = $(this).attr('isSpecial');
										var applySpecial="";
										if(isSpecial!=null&&isSpecial=="审批中"){
											applySpecial='<a href="CircuitRunAction_findAduitPage.action?id='+ $(this).attr('epId')+'">审批动态</a>/';
										}else if(isSpecial==null||isSpecial.length==0||isSpecial=="普通"){
											isSpecial="普通";
											applySpecial='<a href="javascript:;" onclick=\'applySpecial('
															+ $(this).attr('id')
															+ ','+i+');\'>申请特殊工序</a>/';
										}
										isSpecial = "<label id='isSpecial"+i+"'>"+isSpecial+"</label>"
										$("#ProcessTab")
												.append(
														'<tr align="center"  onmouseover=chageBgcolor(this)  onmouseout=outBgcolor(this,"#FFFFFF") ><td>'
																+ $(this)
																		.attr(
																				'processNO')
																+ '</td><td>'
																+ $(this)
																		.attr(
																				'processName')
																+ '</td><td>'
																+ parseFloat($(
																		this)
																		.attr(
																				'opshebeijiepai')
																		+ $(
																				this)
																				.attr(
																						'opcaozuojiepai')
																		+ $(
																				this)
																				.attr(
																						'gzzhunbeijiepai')
																		* $(
																				this)
																				.attr(
																						'gzzhunbeicishu'))
																+ '</td><td>'
																+ $(this)
																		.attr(
																				'productStyle')
																+ '</td><td>'
																+ isSpecial
																+ '</td><td>'
																+ $(this)
																		.attr(
																				'processStatus')
																+ '</td><td>'
																+ $(this)
																		.attr(
																				'zjStatus')
																+ '</td><td>' 
																+applySpecial
																+'<a href="javascript:;" onclick="showProcessTz('
																+ $(this).attr(
																		'id')
																+ ');">图纸</a>/<a href="javascript:;" onclick="showProcessForSb('
																+ $(this).attr(
																		'id')//  onmouseout="outBgcolor(this,'#e6f3fb')" onmouseover="alert(000000000)"
																+ ');">修改</a>/<a href="javascript:;" onclick="showProcessZJ('
																	+$(this).attr('id')+','+$(this).attr('processNO')+');">质验</a>/<a href="javascript:;" onclick="deleteProcess('
																+ $(this).attr(
																		'id')
																+ ');">删除</a></td></tr>');
																																
																
										
										var processStatus = $(this).attr(
												'processStatus');
										if (processStatus == "no") {
											$("#parallelId").val("");
										} else {
											$("#parallelId").val(
													$(this).attr('id'));
										}
										maxProcessNO = parseFloat($(this).attr(
												'processNO')) + 5;
										i++;
									});
					$("#processNO").val(maxProcessNO);//计算下一个工序号是多少，方便填写
					$("#processName").select();
				}
			});
}
//添加工序
function submitForm2(formId) {
	if ($("#processName").val() == "") {
		alert("请填写工序名称!");
		$("#processName").select();
	} else {
		if ($("#processStatus").val() == "no") {
			$("#parallelId").val("");
		}
		$.ajax( {
			type : "POST",
			url : "ProcardTemplateAction!addProcessTemplate.action",
			dataType : "json",
			data : $("#" + formId).serialize(),
			success : function(msg) {
				if (msg) {
					alert("添加成功!");
					showProcess();
					$("#processNO").val(parseFloat($("#processNO").val()) + 5);
					$("#processName").val("");
					$("#processName").select();

				} else {
					alert("添加失败!");
				}
			}
		});
	}
}

//删除流水卡片
function delProCard(obj) {
	if (window.confirm('确定要删除本卡片吗?此操作将会删除该流水卡片下属的所有信息!')) {
		$(obj).attr("disabled","disabled");
		$(obj).val("正在删除中,请耐心等待....");
		
		$.ajax( {
			type : "POST",
			url : "ProcardTemplateAction!delProcard.action",
			dataType : "json",
			data : {
				id : $("#cardId").val()
			},
			success : function(msg) {
				if (msg) {
					alert("删除成功!");
					showDiv();//页面内容清空
			$("#selectDiv").hide();
			loadTree();//重新加载树形

		} else {
			alert("删除失败!");
		}
	}
		});
	}
}

//精益计算
function jingyiJisuan() {
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!jingyiJisuan.action",
		dataType : "json",
		data : {
			id : $("#cardId").val()
		},
		success : function(msg) {
			alert(msg);
		}
	});
}

//页面内容清空
function showDiv() {
	//清空工序table
	$("#ProcessTab").empty();
	//隐藏各个添加的详细页面
	$("#showCardTemplate").hide();
	$("#processDiv").hide();
	$("#lingDiv").hide();
	$("#yuanDiv").hide();
}

//显示工序详细
function showProcessForSb(id,ProcessNO ) {
	$("#showProcess").attr("src",
			"ProcardTemplateAction!showProcess.action?id=" + id);
	chageDiv('block');

}
//显示工序图纸
function showProcessTz(id) {
	$("#showProcess").attr("src",
			"ProcardTemplateAction!showProcessTz.action?id=" + id);
	chageDiv('block');

}
//申请特殊工序
function applySpecial(id,index) {
if (window.confirm('确定要将此工序申请为特殊工序吗?')) {
		$.ajax( {
			type : "POST",
			url : "ProcardTemplateAction!applySpecial.action",
			dataType : "json",
			data : {
				id : id
			},
			success : function(msg) {
				if (msg=="true") {
					$("#isSpecial"+index).empty();
					$("#isSpecial"+index).html("申请中");
				} else {
					alert(msg);
				}
			}
		});
	}
}
//显示工序质检；
function showProcessZJ(id,ProcessNO ){
	$("#showProcess").attr("src",
		"OsTemplate_addInput.action?id="+id+"&gongxuNum="+ProcessNO);


	chageDiv('block');
}
function deleteProcess(id) {
	if (window.confirm('确定要删除本工序吗?')) {
		$.ajax( {
			type : "POST",
			url : "ProcardTemplateAction!deleteProcess.action",
			dataType : "json",
			data : {
				id : id
			},
			success : function(msg) {
				if (msg.success) {
					alert(msg.message);
					showProcess();//显示该流水卡片已有工序
			$("#processNO").val(parseFloat($("#processNO").val()) + 5);
			$("#processName").val("");
			$("#processName").select();
		} else {
			alert(msg.message);
		}
	}
		});
	}
}

$(function() {
	getUnit("danwei2");
	getUnit("danwei3");
	getUnit("danwei4");
})
</script>

		<script language="javascript">
function initfz() {
	count_seach++;
	var shortname = document.getElementById("shortname");
	var showAll = document.getElementById("showAll");
	showAll.style.top = shortname.offsetTop + 20;
	showAll.style.left = shortname.offsetLeft;
	showAll.style.visibility = "visible";
}
//ajax获取所有的类似的全称
function getAllNames() {
	$
			.ajax( {
				type : "POST",
				url : "ProcardTemplateAction!getAllNames.action",
				dataType : "json",
				data : {
					markId : $("#shortname").val()
				},
				success : function(data) {
					$("#showAll").empty();
					$("#shortnameId").val(0);
					$(data)
							.each(
									function() {
										var markid = $(this)
												.attr('markId')
												.replace(
														$("#shortname").val(),
														"<font color='red'>"
																+ $(
																		"#shortname")
																		.val()
																+ "</font>");
										$("#showAll")
												.append(
														"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdiv(this)' align='left' data='"
																+ $(this).attr(
																		'id')
																+ "'>"
																+ markid
																+ ","
																+ $(this)
																		.attr(
																				'procardStyle')
																+ "<span style='display: none;'>"
																+ $(this)
																		.attr(
																				'markId')
																+ "</span></div>");
									});
				}
			});
}

function copyProcard() {
	if ($("#shortname").val() == "") {
		alert("您选中的模板不存在，添加失败！");
		return;
	}
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!copyProcard.action",
		dataType : "json",
		data : {
			id : $("#mfatherId").val(),
			id2 : $("#shortnameId").val()
		},
		success : function(data) {
			if (data.success) {
				loadTree();
			}
			if (data.message != null) {
				alert(data.message);

			}

		}
	});
}
function updateProcard(obj) {
	if (!window.confirm("您将更新所有同件号的模板和状态为初始或者已发卡的流水卡,是否继续更新！")) {
		return false;
	}
	var id = $("#uId").val();
	if (id == "") {
		alert("您选中的模板不存在，添加失败！");
		return;
	}
	$(obj).attr("disabled", "disabled");
	$(obj).val("更新中....");
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!updateProcard.action",
		dataType : "json",
		data : {
			id : id
		},
		success : function(data) {
			if (data.message != null) {
				alert(data.message);
				$(obj).removeAttr("disabled");
				$(obj).val("更新流水卡");
			}
		}
	});
}
/**
 * 显示添加工序弹框
 */
function toAddProcess() {
	var id = $("#cardId").val();
	$("#showProcess").attr("src",
			"ProcardTemplateAction!toAddProcess.action?id=" + id);
	chageDiv('block');
}
//查询所有工序
function getProcess() {
	$.ajax( {
		type : "POST",
		url : "processGzstoreAction_getProcessGzstoreListAllForSelect.action",
		dataType : "json",
		success : function(msg) {
			if (msg.success) {
				//$("#processName").empty();
				$("#processName").append("<option value=''></option>");
				$.each(msg.data, function(i, n) {
					$("#processName").append(
							"<option value='" + n.id + "'>" + n.processName
									+ "</option>");
				});
			} else {
				alert(msg.message);
			}
		}
	});
}
getProcess();
function updateMarkId(isAll) {
	var oldMarkId = $("#oldMarkId").html();
	var newMarkId = $("#newMarkId").val();
	if (newMarkId == null || newMarkId == "") {
		alert("请输入新件号");
		return false;
	}
	if (oldMarkId == newMarkId) {
		alert("原新件号请不要一致！");
		return false;
	}
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!updateMarkId.action",
		dataType : "json",
		data : {
			id : $("#cardId").val(),
			markId : newMarkId,
			isAll : isAll
		},
		success : function(data) {
			alert(data.message);
			if (data.success) {
				loadTree();
				$("#oldMarkId").empty();
				$("#oldMarkId").append(newMarkId);
				$("#showCardTemplate").show();
				$("#showCardIframe").attr(
						"src",
						"ProcardTemplateAction!findCardTemForShow.action?id="
								+ $("#cardId").val());
			}
		}
	})
}
function changeTolp() {
	var lpMarkId = $("#lpMarkId").val();
	$("#dd9").show();
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!changeTolp.action",
		dataType : "json",
		data : {
			id : $("#cardId").val(),
			markId : lpMarkId
		},
		success : function(data) {
			alert(data.message);
			$("#dd9").hide();
			loadTree();
			$("#oldMarkId").empty();
			$("#oldMarkId").append(newMarkId);
			$("#showCardTemplate").show();
			$("#showCardIframe").attr(
					"src",
					"ProcardTemplateAction!findCardTemForShow.action?id="
							+ $("#cardId").val());
		},error:function(){
			alert("系统异常");
		}
	})
}
function showSonCard() {
	var id = $("#cardId").val();
	$("#showProcess").attr("src",
			"ProcardTemplateAction!showSonCard.action?id=" + id);
	chageDiv('block');

}
</script>
		<!-- 外购件原材料模糊搜索js -->
		<SCRIPT type="text/javascript">
		function hidediv(allname) {
	count_seach--;
	if (count_seach == 0) {
		var showAll = document.getElementById(allname);
		showAll.style.visibility = "hidden";
	}

}

function init(shortName,allname) {
	count_seach++;
	var shortnamewgj = document.getElementById(shortName);
	var showAllwgj = document.getElementById(allname);
	showAllwgj.style.top = getTop(shortnamewgj) + 20;
	showAllwgj.style.left = getLeft(shortnamewgj);
	showAllwgj.style.visibility = "visible";
}
function selectdiv(obj){
	var html=$(obj).find("span").html();
	$("#shortname").val(html);
	var data = $(obj).attr('data');
	$("#shortnameId").val(data);
	var showAll=document.getElementById("showAll"); 
	   showAll.style.visibility = "hidden";
}
//ajax获取所有的类似的全称
function getAllNameswgj() {
	if($("#shortnamewgj").val()==null||$("#shortnamewgj").val()==""){
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
					$(data).each(
									function() {
										var markId = $(this)
												.attr('markId')
												.replace(
														$("#shortnamewgj").val(),
														"<font color='red'>"
																+ $("#shortnamewgj").val()
																+ "</font>");
										$("#showAllwgj")
												.append(
														"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdivwgj(this)' align='left'>"
																+ markId
																+ ":"
																+ $(this).attr('name')
																+":"
																+$(this).attr('unit')
																+"<span style='visibility: hidden;'>"
																+ $(this).attr('markId')
																+ ":"
																+ $(this).attr('name')
																+":"
																+$(this).attr('unit')
																+"</span>"
																+ "</div>");
									});
				}
			});
}
function selectdivwgj(obj){
	var html=$(obj).find("span").html();
	var showAllwgj=document.getElementById("showAllwgj"); 
	showAllwgj.style.visibility = "hidden";
	var htmls=html.split(":");
	$("#shortnamewgj").val(html);
	$("#wajmarkId").val(htmls[0]);
	$("#wajproName").val(htmls[1]);
	$("#danwei2").val(htmls[2]);
}
		
//ajax获取所有的类似的全称
function getAllNamesycl() {
	if($("#shortnameycl").val()==null||$("#shortnameycl").val()==""){
		$("#showAllycl").empty();
		return;
	}
	$
			.ajax( {
				type : "POST",
				url : "yuanclAndWaigjAction!getAllNames.action",
				dataType : "json",
				data : {
					'yuanclAndWaigj.trademark' : $("#shortnameycl").val(),
					'yuanclAndWaigj.clClass' : '原材料'
				},
				success : function(data) {
					$("#showAllycl").empty();
					$(data).each(
									function() {
										var trademark = $(this)
												.attr('trademark')
												.replace(
														$("#shortnameycl").val(),
														"<font color='red'>"
																+ $("#shortnameycl").val()
																+ "</font>");
										$("#showAllycl")
												.append(
														"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdivycl(this)' align='left'>"
																+ trademark
																+ ":"
																+ $(this).attr('specification')
																+":"
																+$(this).attr('unit')
																+"<span style='visibility: hidden;'>"
																+ $(this).attr('trademark')
																+ ":"
																+ $(this).attr('specification')
																+":"
																+$(this).attr('unit')
																+"</span>"
																+ "</div>");
									});
				}
			});
}

function selectdivycl(obj){
	var html=$(obj).find("span").html();;
	var showAll=document.getElementById("showAllycl"); 
	showAll.style.visibility = "hidden";
	var htmls=html.split(":");
	$("#shortnameycl").val(html);
	$("#trademark").val(htmls[0]);
	$("#specification").val(htmls[1]);
	$("#unit4").val(htmls[2]);
}
//修改外购件是否需要工序
function changeneedProcess(obj){
	if(obj=="yes"){
		$("#safetr").show();
		$("#lasttr").show();
	}else{
		$("#safetr").hide();
		$("#lasttr").hide();
	}
	
}
function getGyPeople(type){
		$
			.ajax( {
				type : "POST",
				url : "procardTemplateGyAction_getGyPeople.action?tag="+type,
				dataType : "json",
				success : function(data) {
					$(data).each(
									function() {
										if(type=="bz"){
										    $(".bianzhi")
												.append("<option value='" + $(this).attr('userId')+ "'>"+ $(this).attr('userName') +"</option>");
										}else if(type=="jd"){
											$(".jiaodui")
												.append("<option value='" + $(this).attr('userId')+ "'>"+ $(this).attr('userName') +"</option>");
										}else if(type=="sh"){
											$(".shenhe")
												.append("<option value='" + $(this).attr('userId')+ "'>"+ $(this).attr('userName') +"</option>");
										}else if(type=="pz"){
											$(".pizhun")
												.append("<option value='" + $(this).attr('userId')+ "'>"+ $(this).attr('userName') +"</option>");
											
										}
									});
				}
			});
}
$(document).ready(function(){
	getUnit("zuheunit");
	getGyPeople("bz");
	getGyPeople("jd");
	getGyPeople("sh");
	getGyPeople("pz");
	//区别2
	click2();
	//区别2
})
function reviewBom(){
	window.open("procardTemplateGyAction_reviewBom.action?id=${param.id}");  
}
function showCardTz(){
	var id=$("#cardId").val();
	$("#showProcess").attr("src",
			"procardTemplateGyAction_showCardTz.action?id=" + id);
	chageDiv('block');
}
function selectYclAndWgj(type){
	$("#showProcess").attr("src",
			"procardTemplateGyAction_showYclAndWgj.action?type="+type);
	chageDiv('block');
}
function noZhongwen(obj){
	if (escape(obj.value).indexOf( "%u" )>=0){
	  obj.value="";
	  alert( "不能包含中文!" );
	}
}
function checkSelf(){
	$("#showProcess").attr("src",
			"procardTemplateGyAction_checkSelf.action?id=${param.id}");
	chageDiv('block');
}
function checkAndUpdateTz(){
	if(window.confirm("是否导入工艺图纸内容!")){
		$("#module1_12").show();
		$
			.ajax( {
				type : "POST",
				url : "procardTemplateGyAction_checkAndUpdateTz.action",
				data : {
					id:${param.id}
				},
				dataType : "json",
				success : function(data) {
					alert(data);
					$("#module1_12").hide();
				}
			});
	}
}
function click2(){
	
	var id =${procardTemplate.id};
	var rootId =${procardTemplate.rootId};
	var markId ="${procardTemplate.markId}";
	var proStruts = "${procardTemplate.procardStyle}";// 卡片状态(总成，零组件，原材料)
	var danjiaojian = "${procardTemplate.danjiaojian}";// 卡片状态(总成，零组件，原材料)
	var productStyle = "${procardTemplate.productStyle}";//生产类型
	var belongLayer = "${procardTemplate.belongLayer}";//层数
	//工序赋值
	$("#cardId").val(id);
	$("#mfatherId").val(id);
	$("#uId").val(id);
	$("#newMarkId").val(markId);
	$("#oldMarkId").empty();
	$("#oldMarkId").append(markId);
	showDiv();//页面内容清空
	//显示添加选项的页面
	$("#selectDiv").show();
	var e = event || window.event;
	var scrollTop = getScrollTop();//获取滚动条离顶部距离
	var mouseLeft = e.clientX + 150;
	var mouseTop = 0;
	//显示悬浮窗样式的项目
	$("#showProDetail").css( {
		"top" : mouseTop,
		"left" : mouseLeft 
	});

	if (proStruts == "总成" && productStyle == "试制") {
		$("#module9").show();
	} else {
		$("#module9").hide();
	}
	if (proStruts == "总成") {
		$("#module12").show();
	} else {
		$("#module12").hide();
	}
if (proStruts == "总成" || proStruts == "组合") {
		//零组件赋值
		$("#rootId").val(id);
		$("#fatherId").val(id);
		$("#belongLayer").val(belongLayer + 1);
		//外购件赋值
		$("#wrootId").val(rootId);
		$("#wfatherId").val(id);
		$("#wbelongLayer").val(belongLayer + 1);
		//原材料赋值
		$("#yrootId").val(rootId);
		$("#yfatherId").val(id);
		$("#ybelongLayer").val(belongLayer + 1);
		if (danjiaojian == "单交件") {
			//全部不可用
			$("#module1").attr("disabled", true);
			$("#module2").attr("disabled", true);
			$("#module2_1").attr("disabled", true);
			$("#module3").attr("disabled", true);
			$("#module4").attr("disabled", false);
			$("#module5").attr("disabled", false);
<%--			$("#module6").attr("disabled", false);--%>
		} else {
			//全部可用
			$("#module1").attr("disabled", false);
			$("#module2").attr("disabled", false);
			$("#module2_1").attr("disabled", false);
			$("#module3").attr("disabled", false);
			$("#module4").attr("disabled", false);
			$("#module5").attr("disabled", false);
<%--			$("#module6").attr("disabled", false);--%>
			if (proStruts == "总成") {
<%--				$("#module6").attr("disabled", false);--%>
			}
		}
	} else if (proStruts == "外购") {
		//全部不可用
		$("#module1").attr("disabled", true);
		$("#module2").attr("disabled", true);
		$("#module3").attr("disabled", true);
		$("#module4").attr("disabled", true);
		$("#module5").attr("disabled", true);
		$("#module5").attr("disabled", true);
<%--		$("#module6").attr("disabled", false);--%>
	} else if (proStruts == "自制") {
		//全部不可用
		$("#module1").attr("disabled", true);
		$("#module2").attr("disabled", true);
		$("#module2_1").attr("disabled", true);
		$("#module3").attr("disabled", true);
		$("#module4").attr("disabled", false);
		$("#module5").attr("disabled", false);
<%--		$("#module6").attr("disabled", false);--%>
	}

	//关闭已打开的功能Div
	if (oldObj != null) {
		$("#module1_" + oldObj2).hide();
	}

	//显示流水卡片明细
	$("#bodyDiv").show();
	$("#showProDetail").show();
	$("#showCardTemplate").show();
	$("#showCardIframe")
			.attr(
					"src",
					"ProcardTemplateAction!findCardTemForShow.action?id="
							+ id);
	$("#bodyDiv").bind("click", function() {
		$("#bodyDiv").hide();
		$("#showProDetail").hide();
		$("#showCardTemplate").hide();
	});
}
function BomTreeSpdt(){
	var id=$("#epId2").val();
	$("#showProcess").attr("src",
			"CircuitRunAction_findAduitPage.action?id="+id);
	chageDiv('block');
}
function applyBomTree(){
	if(confirm("您将申请BOM结构审批是否继续?")){
	var id=$("#cardId").val();
	$("#module15").attr("disabled","disabled");
	$
			.ajax( {
				type : "POST",
				url : "procardTemplateGyAction_applyBomTree.action",
				data : {
					id:id
				},
				dataType : "json",
				success : function(data) {
					alert(data.message);
				}
			});
	}
}
function applySb(){
	if(confirm("您将申请设变是否继续?")){
	var id=$("#cardId").val();
<%--	$("#module14").attr("disabled","disabled");--%>
	$
			.ajax( {
				type : "POST",
				url : "procardTemplateGyAction_applySb.action",
				data : {
					id:id
				},
				dataType : "json",
				success : function(data) {
					alert(data.message);
				}
			});
	}
}
function moveStatus(){
	var id=$("#cardId").val();
	$("#showProcess").attr("src",
			"procardTemplateGyAction_moveStatus.action?id="+id);
	chageDiv('block');
}
function changeLogshow(){
	var id=$("#cardId").val();
	$("#showProcess").attr("src",
			"procardTemplateGyAction_changeLogshow.action?id="+id);
	chageDiv('block');
}
		</SCRIPT>
	</body>
</html>
