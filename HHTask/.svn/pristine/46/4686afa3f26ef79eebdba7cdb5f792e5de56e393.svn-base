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
							<span id="title">您正在查看工序信息</span>
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
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; border: solid 1px #0170b8;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			<div align="left">
				<table>
					<tr>
						<td align="right" colspan="2">
							<s:submit value="提交" cssClass="input" onclick="tijiao();" />
						</td>
					</tr>
				</table>



				<!-- 显示树形流水卡片模板 -->
				<div style="width: 40%; height: 100%; float: left;" align="left">
					<div style="height: 100%;">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
				<!-- 添加流水卡片模板操作 -->
				<div style="border-left: 1px solid #000000; float: left; width: 58%">
					<div id="selectDiv" style="display: none;" align="center">
						<input id="module1" type="button" value="添加组合件"
							onclick="chageModule(this,'1')"
							style="width: 80px; height: 50px;" />
						<input id="module2" type="button" value="添加外购件"
							onclick="chageModule(this,'2')"
							style="width: 80px; height: 50px;" />
						<input id="module3" type="button" value="添加自制件"
							onclick="chageModule(this,'3')"
							style="width: 80px; height: 50px;" />
						<input id="module4" type="button" value="添加工序"
							onclick="chageModule(this,'4');showProcess();"
							style="width: 80px; height: 50px;" />
						<input type="button" value="删除本卡片" onclick="delProCard()"
							style="width: 80px; height: 50px;" />
						<br />
					</div>
					<div id="module1_1" style="display: none;">
						<form id="lingForm" style="margin: 0px; padding: 0px;">
							<input type="hidden" id="wrootId" name="gysMarkIdjiepai.rootId" />
							<input type="hidden" id="wfatherId"
								name="gysMarkIdjiepai.fatherId" />
							<input type="hidden" id="wbelongLayer"
								name="gysMarkIdjiepai.belongLayer" />
							<table class="table" style="width: 100%;">
								<tr>
									<th align="center" colspan="2">
										添加组合件工艺卡片模版
									</th>
								</tr>
								<tr>
									<th align="right" style="width: 25%;">
										件号:
									</th>
									<td>
										<input name="gysMarkIdjiepai.markId">
									</td>
								</tr>
								<tr>
									<th align="right">
										名称:
									</th>
									<td>
										<input name="gysMarkIdjiepai.proName">
									</td>
								</tr>
								<tr>
									<th align="right">
										车型:
									</th>
									<td>
										<input name="gysMarkIdjiepai.carStyle">
									</td>
								</tr>
								<tr>
									<th align="right">
										数量:
									</th>
									<td>
										<input name="gysMarkIdjiepai.maxCount">
										(每张流水卡片可放最大数量)
									</td>
								</tr>
								<tr>
									<th align="right">
										数量:
									</th>
									<td>
										<input name="gysMarkIdjiepai.corrCount">
										(权值,对应上层所需数量)
									</td>
								</tr>
								<tr>
									<th align="right">
										卡片类型:
									</th>
									<td>
										<input name="gysMarkIdjiepai.procardStyle" value="组合"
											readonly="readonly" />
									</td>
								</tr>
								<tr>
									<th align="right">
										单位:
									</th>
									<td>
										<select id="danwei1" name="gysMarkIdjiepai.unit"
											style="width: 155px;">
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
										备注:
									</th>
									<td>
										<input name="gysMarkIdjiepai.remark">
									</td>
								</tr>
								<tr>
									<th align="right">
										是否外购:
									</th>
									<td>
										<select name="gysMarkIdjiepai.status" style="width: 155px;">
											<option value="否">
												否
											</option>
											<option value="是">
												是
											</option>
										</select>
									</td>
								</tr>
								</tr>

								<tr>
									<td align="center" colspan="2">
										<input type="button" value="添加"
											onclick="submitForm('lingForm')"
											style="width: 80px; height: 50px;" />
										<input type="reset" value="重置"
											style="width: 80px; height: 50px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="module1_2" style="display: none;">
						<form id="waiForm" style="margin: 0px; padding: 0px;">
							<input type="hidden" id="rootId" name="gysMarkIdjiepai.rootId" />
							<input type="hidden" id="fatherId"
								name="gysMarkIdjiepai.fatherId" />

							<input type="hidden" id="belongLayer"
								name="gysMarkIdjiepai.belongLayer" />

							<table class="table" style="width: 100%;">
								<tr>
									<th align="center" colspan="2">
										添加外购件工艺卡片模版
									</th>
								</tr>
								<tr>
									<th align="right" style="width: 25%;">
										件号:
									</th>
									<td>
										<input name="gysMarkIdjiepai.markId">
									</td>
								</tr>
								<tr>
									<th align="right">
										名称:
									</th>
									<td>
										<input name="gysMarkIdjiepai.proName">
									</td>
								</tr>
								<tr>
									<th align="right">
										车型:
									</th>
									<td>
										<input name="gysMarkIdjiepai.carStyle">
									</td>
								</tr>
								<tr>
									<th align="right">
										卡片类型:
									</th>
									<td>
										<input name="gysMarkIdjiepai.procardStyle" value="外购"
											readonly="readonly" />
									</td>
								</tr>
								<tr>
									<th align="right">
										单位:
									</th>
									<td>
										<select id="danwei2" name="gysMarkIdjiepai.unit"
											style="width: 155px;">
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
										<input name="gysMarkIdjiepai.quanzi1" style="width: 71px;" />
										:
										<input name="gysMarkIdjiepai.quanzi2" style="width: 71px;" />
										(组合:外购件,格式如1:1)
									</td>
								</tr>
								<tr>
									<th align="right">
										产品类型:
									</th>
									<td>
										<select name="gysMarkIdjiepai.productStyle"
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
										备注:
									</th>
									<td>
										<input name="gysMarkIdjiepai.remark">
									</td>
								</tr>


								<tr>
									<td align="center" colspan="2">
										<input type="button" value="添加"
											onclick="submitForm('waiForm')"
											style="width: 80px; height: 50px;" />
										<input type="reset" value="重置"
											style="width: 80px; height: 50px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="module1_3" style="display: none;">
						<form id="yuanForm" style="margin: 0px; padding: 0px;">
							<input type="hidden" id="yrootId" name="gysMarkIdjiepai.rootId" />
							<input type="hidden" id="yfatherId"
								name="gysMarkIdjiepai.fatherId" />
							<input type="hidden" id="ybelongLayer"
								name="gysMarkIdjiepai.belongLayer" />
							<table class="table" style="width: 100%;">
								<tr>
									<th align="center" colspan="2">
										添加原材料流水卡片模版
									</th>
								</tr>
								<tr>
									<th align="right" style="width: 25%;">
										件号:
									</th>
									<td>
										<input name="gysMarkIdjiepai.markId">
									</td>
								</tr>
								<tr>
									<th align="right">
										名称:
									</th>
									<td>
										<input name="gysMarkIdjiepai.proName">
									</td>
								</tr>
								<tr>
									<th align="right">
										车型:
									</th>
									<td>
										<input name="gysMarkIdjiepai.carStyle">
									</td>
								</tr>
								<tr>
									<th align="right">
										数量:
									</th>
									<td>
										<input name="gysMarkIdjiepai.maxCount">
										(每张流水卡片可放最大数量)
									</td>
								</tr>
								<tr>
									<th align="right">
										数量:
									</th>
									<td>
										<input name="gysMarkIdjiepai.corrCount">
										(权值,对应上层所需数量)
									</td>
								</tr>
								<tr>
									<th align="right">
										备注:
									</th>
									<td>
										<input name="gysMarkIdjiepai.remark">
									</td>
								</tr>
								<tr>
									<th align="right">
										单位:
									</th>
									<td>
										<select id="danwei3" name="gysMarkIdjiepai.unit"
											style="width: 155px;">
											<option value="件">
												件
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<th align="right">
										卡片类型:
									</th>
									<td>
										<input name="gysMarkIdjiepai.procardStyle" value="自制"
											readonly="readonly" />
									</td>
								</tr>
								<tr>
									<th align="right">
										产品类型:
									</th>
									<td>
										<select name="gysMarkIdjiepai.productStyle"
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
									<th colspan="2">
										原材料信息
									</th>
								</tr>
								<tr>
									<th align="right">
										牌号:
									</th>
									<td>
										<input name="gysMarkIdjiepai.trademark">
									</td>
								</tr>
								<tr>
									<th align="right">
										规格:
									</th>
									<td>
										<input name="gysMarkIdjiepai.specification">
									</td>
								</tr>
								<tr>
									<th align="right">
										单位:
									</th>
									<td>
										<select id="danwei4" name="gysMarkIdjiepai.yuanUnit"
											style="width: 155px;">
										</select>
									</td>
								</tr>
								<tr>
									<th align="right">
										权值:
									</th>
									<td>
										<input name="gysMarkIdjiepai.quanzi1" style="width: 71px;" />
										:
										<input name="gysMarkIdjiepai.quanzi2" style="width: 71px;" />
										(自制件:原材料,格式如1:1)
									</td>
								</tr>
								<tr>
									<th align="right">
										炉号:
									</th>
									<td>
										<input name="gysMarkIdjiepai.luhao">
									</td>
								</tr>
								<tr>
									<th align="right">
										编号:
									</th>
									<td>
										<input name="gysMarkIdjiepai.number">
									</td>
								</tr>
								<tr>
									<th align="right">
										实际定额:
									</th>
									<td>
										<input name="gysMarkIdjiepai.actualFixed">
									</td>
								</tr>

								<tr>
									<th align="right">
										是否外购:
									</th>
									<td>
										<select name="gysMarkIdjiepai.status" style="width: 155px;">
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
									<td align="center" colspan="2">
										<input type="button" value="添加"
											onclick="submitForm('yuanForm')"
											style="width: 80px; height: 50px;" />
										<input type="reset" value="重置"
											style="width: 80px; height: 50px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="module1_4" style="display: none;" align="center">
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
							<input id="parallelId" type="hidden" name="pIdZijian.parallelId" />
							<table class="table" id="tb">
								<tr>
									<th align="right">
										工序号:
									</th>
									<td>
										<input type="text" id="processNO" name="pIdZijian.processNO"
											value="5" />
										<font style="color: red;">*必填项</font>
									</td>
									<th align="right">
										工序名称:
									</th>
									<td>
										<input type="text" name="pIdZijian.processName"
											value="${pIdZijian.processName}" />
										<font style="color: red;">*必填项</font>
									</td>
									<th align="right">
										&nbsp;
									</th>
									<td>
										<input id="addTrBtn" type="button" value="添加工位">
									</td>
								</tr>
								<tr>
									<th align="right">
										并行:
									</th>
									<td>
										<select id="processStatus" name="pIdZijian.processStatus">
											<option value="yes">
												并行
											</option>
											<option value="no">
												不并行
											</option>

										</select>
									</td>
									<th align="right">
										生产类型:
									</th>
									<td>
										<select name="pIdZijian.productStyle">
											<option>
												自制
											</option>
											<option>
												外委
											</option>
										</select>
									</td>
									<th align="right">
										是否参与:
									</th>
									<td>
										<select name="pIdZijian.isPrice">
											<option value="${pIdZijian.isPrice}">
												<s:if test="pIdZijian.isPrice=='yes'">
										参与
									</s:if>
												<s:else>不参与</s:else>
											</option>
											<option value="yes">
												参与
											</option>
											<option value="no">
												不参与
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<th align="right">
										是否首检:
									</th>
									<td>
										<select id="processStatus" name="pIdZijian.zjStatus">
											<option value="yes">
												yes
											</option>
											<option value="no">
												no
											</option>

										</select>
									</td>
								</tr>
								<tr>
									<th align="right">
										部门
									</th>
									<td align="left">
										<select name="pIdZijian.operatorDept" id="dept"
											style="width: 100px; float: left;">
											<option value="${pIdZijian.operatorDept}">
												${pIdZijian.operatorDept}
											</option>
										</select>
									</td>
									<th align="right">
										操作者:
									</th>
									<td align="left">
										<input id="userId" name="pIdZijian.operatorUserId"
											type="hidden" />
										<input id="userName" name="pIdZijian.operatorName"
											value="${pIdZijian.operatorName}" type="hidden" />
										<select id="users" style="width: 100px; float: left;">
											<option value="${pIdZijian.operatorName}">
												${pIdZijian.operatorName}
											</option>
										</select>
									</td>
									<th align="right">
										操作人工号:
									</th>
									<td>
										<input type="text" id="code" name="pIdZijian.operatorCode"
											value="${pIdZijian.operatorCode}" />
										<input id="cardId" name="pIdZijian.operatorCardId"
											value="${pIdZijian.operatorCardId}" type="hidden" />
									</td>
								</tr>
								<tr>
									<th align="right">
										工位号:
									</th>
									<td>
										<select name="pIdZijian.gongwei" id="gongwei"
											style="width: 155px;" onchange="getshebeiCode(this)">
											<option value="${pIdZijian.gongwei}">
												${pIdZijian.gongwei}
											</option>
											<option></option>
										</select>
									</td>
									<th align="right">
										设备编号:
									</th>
									<td>
										<select id="shebeiCode" name="pIdZijian.shebeiNo"
											style="width: 155px;" onchange="getGongweiAndOth()">
											<option value="${pIdZijian.shebeiNo}">
												${pIdZijian.shebeiNo}
											</option>
											<option></option>
										</select>
									</td>
									<th align="right">
										设备名称:
									</th>
									<td>
										<input type="text" name="pIdZijian.shebeiName" id="shebeiName"
											readOnly="readonly" style="background-color: #cccccc;"
											value="${pIdZijian.shebeiName}" />
									</td>
								</tr>
								<tr>
									<td colspan="6" align="center">
										<b>操作过程</b>
									</td>
								</tr>
								<tr>
									<th align="right">
										人工节拍:
									</th>
									<td>
										<input type="text" name="pIdZijian.opcaozuojiepai"
											value="${pIdZijian.opcaozuojiepai}" />
										(s)
										<font style="color: red;">*必填项</font>
									</td>
									<th align="right">
										设备节拍:
									</th>
									<td>
										<input id="opshebeijiepai" type="text"
											name="pIdZijian.opshebeijiepai"
											value="${pIdZijian.opshebeijiepai}" />
										(s)
										<font style="color: red;">*必填项</font>
									</td>
									<th align="right">
										&nbsp;
									</th>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr>
									<th align="right">
										负荷指数:
									</th>
									<td>
										<input type="text" name="pIdZijian.opfuheRate"
											value="${pIdZijian.opfuheRate}" />
									</td>
									<th align="right">
										技能指数:
									</th>
									<td>
										<input type="text" name="pIdZijian.optechnologyRate"
											id="optechnologyRate" readOnly="readonly"
											style="background-color: #cccccc;"
											value="${pIdZijian.optechnologyRate}" />
									</td>
									<th align="right">
										可替换人数:
									</th>
									<td>
										<input type="text" name="pIdZijian.opCouldReplaceRate"
											id="opCouldReplaceRate" readOnly="readonly"
											style="background-color: #cccccc;"
											value="${pIdZijian.opCouldReplaceRate}" />
									</td>

								</tr>
								<tr>
									<td colspan="6" align="center">
										<b>准备过程</b>
									</td>
								</tr>
								<tr>
									<th align="right">
										人工节拍:
									</th>
									<td>
										<input type="text" name="pIdZijian.gzzhunbeijiepai"
											value="${pIdZijian.gzzhunbeijiepai}" />
										(s)
										<font style="color: red;">*必填项</font>
									</td>
									<th align="right">
										准备次数:
									</th>
									<td>
										<input type="text" name="pIdZijian.gzzhunbeicishu"
											value="${pIdZijian.gzzhunbeicishu}" />
										<font style="color: red;">*必填项</font>
									</td>
									<th align="right">
										&nbsp;
									</th>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr>
									<th align="right">
										负荷指数:
									</th>
									<td>
										<input type="text" name="pIdZijian.gzfuheRate" id="gzfuheRate"
											readOnly="readonly" style="background-color: #cccccc;"
											value="${pIdZijian.gzfuheRate}" />
									</td>
									<th align="right">
										技能指数:
									</th>
									<td>
										<input type="text" name="pIdZijian.gztechnologyRate"
											id="gztechnologyRate" readOnly="readonly"
											style="background-color: #cccccc;"
											value="${pIdZijian.gztechnologyRate}" />
									</td>
									<th align="right">
										可替换人数:
									</th>
									<td>
										<input type="text" name="pIdZijian.gzCouldReplaceRate"
											id="gzCouldReplaceRate" readOnly="readonly"
											style="background-color: #cccccc;"
											value="${pIdZijian.gzCouldReplaceRate}" />
									</td>

								</tr>
								<tr>
									<td colspan="6" align="center">
										<input type="submit" class="input" value="添加" />
										&nbsp;
										<input type="reset" class="input" value="取消">

									</td>
								</tr>
							</table>
							<!-- 
							<input type="hidden" name="pIdZijian.optechnologyRate"
								value="0" />
							<input type="hidden" name="pIdZijian.opCouldReplaceRate"
								value="0" />
							<input type="hidden" name="pIdZijian.opfuheRate" value="0" />
							<input type="hidden" name="pIdZijian.opcaozuojiepai"
								value="0" />
							<input type="hidden" name="pIdZijian.opshebeijiepai"
								value="0" />
							<input type="hidden" name="pIdZijian.opnoReplaceRate"
								value="0" />
							<input type="hidden" name="pIdZijian.opzonghezhishu"
								value="0" />
							<input type="hidden" name="pIdZijian.opzongheqiangdu"
								value="0" />
							<input type="hidden" name="pIdZijian.gztechnologyRate"
								value="0" />
							<input type="hidden" name="pIdZijian.gzCouldReplaceRate"
								value="0" />
							<input type="hidden" name="pIdZijian.gzfuheRate" value="0" />
							<input type="hidden" name="pIdZijian.gzzhunbeijiepai"
								value="0" />
							<input type="hidden" name="pIdZijian.gzzhunbeicishu"
								value="0" />
							<input type="hidden" name="pIdZijian.gznoReplaceRate"
								value="0" />
							<input type="hidden" name="pIdZijian.gzzonghezhishu"
								value="0" />
							<input type="hidden" name="pIdZijian.gzzongheqiangdu"
								value="0" />
							<input type="hidden" name="pIdZijian.processMomey"
								value="0" />
							<input type="hidden" name="pIdZijian.opjiaofu" value="0" />
							<table class="table" style="width: 100%">
								<tr>
									<th colspan="8" align="center">
										添加工序
									</th>
								</tr>
								<tr>
									<td align="right">
										工序号:
									</td>
									<td>
										<input id="processNO" name="pIdZijian.processNO"
											value="5" />
									</td>
									<td align="right">
										名 称:
									</td>
									<td>
										<input id="processName" name="pIdZijian.processName" />
									</td>
								</tr>
								<tr>
									<td align="right">
										设备节拍:
									</td>
									<td>
										<input id="processName" name="pIdZijian.deviceBeats"
											value="1" />
										(s)
									</td>
									<td align="right">
										并行:
									</td>
									<td>
										<select id="processStatus"
											name="pIdZijian.processStatus">
											<option value="yes">
												并行
											</option>
											<option value="no">
												不并行
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right">
										生产类型:
									</td>
									<td>
										<select name="pIdZijian.productStyle">
											<option>
												自制
											</option>
											<option>
												外委
											</option>
										</select>
									</td>
									<td align="right">
										是否参与:
									</td>
									<td>
										<select name="pIdZijian.isPrice">
											<option value="yes">
												参与
											</option>
											<option value="no">
												不参与
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td align="right">
										是否首检:
									</td>
									<td>
										<select name="pIdZijian.zjStatus">
											<option>
												yes
											</option>
											<option>
												no
											</option>
										</select>
									</td>
									<td align="right">
									</td>
									<td>
									</td>
								</tr>
								<tr>
									<td colspan="8" align="center">
										<input type="submit" value="添加"
											style="width: 80px; height: 40px;" />
									</td>
								</tr>
							</table>
							 -->
						</form>
					</div>
					<div id="showCardTemplate" style="display: none; height: 100%;">
						<iframe id="showCardIframe" src="" marginwidth="0"
							marginheight="0" hspace="0" vspace="0" frameborder="0"
							scrolling="no"
							style="width: 98%; height: 600px; margin: 0px; padding: 0px;"></iframe>
					</div>
				</div>
				<div style="clear: both;"></div>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//========================================zTree显示
//自动组装树形结构
var setting = {
	data : {
		simpleData : {
			enable : true
		}
	},

	callback : {
		onClick : onClick
	}
};
//加载树形数据
$(document).ready(loadTree());
//生成
function loadTree() {
	$.ajax( {
		url : 'markIdAction!findProcardTemByRootId.action',
		type : 'post',
		dataType : 'json',
		data : {
			id : '${param.id}'
		},
		cache : true,
		success : function(doc) {
			var zNodes = [];
			$(doc).each(
					function() {
						zNodes.push( {
							id : $(this).attr('id'),
							pId : $(this).attr('fatherId'),
							proStruts : $(this).attr('procardStyle'),
							rootId : $(this).attr('rootId'),
							belongLayer : $(this).attr('belongLayer'),
							name : $(this).attr('proName') + ' '
									+ $(this).attr('markId') + ' '
									+ $(this).attr('procardStyle'),
							click : false,
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

var oldObj;
var oldObj2;
//切换添加类型
function chageModule(obj, obj2) {
	if (oldObj != null) {
		$("#module1_" + oldObj2).hide();
	}
	$("#module1_" + obj2).show('slow');
	oldObj = obj;
	oldObj2 = obj2;
}

//点击回调函数
function onClick(event, treeId, treeNode, clickFlag) {
	var proStruts = treeNode.proStruts;// 卡片状态(总成，零组件，原材料)
	//工序赋值
	$("#cardId").val(treeNode.id);
	showDiv();//页面内容清空
	//显示添加选项的页面
	$("#selectDiv").show();
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
		//全部可用
		$("#module1").attr("disabled", false);
		$("#module2").attr("disabled", false);
		$("#module3").attr("disabled", false);
		$("#module4").attr("disabled", false);
	} else if (proStruts == "外购") {
		//全部不可用
		$("#module1").attr("disabled", true);
		$("#module2").attr("disabled", true);
		$("#module3").attr("disabled", true);
		$("#module4").attr("disabled", true);
	} else if (proStruts == "自制") {
		//全部不可用
		$("#module1").attr("disabled", true);
		$("#module2").attr("disabled", true);
		$("#module3").attr("disabled", true);
		$("#module4").attr("disabled", false);
	}

	//关闭已打开的功能Div
	if (oldObj != null) {
		$("#module1_" + oldObj2).hide();
	}

	//显示流水卡片明细
	$("#showCardTemplate").show();
	$("#showCardIframe").attr("src",
			"markIdAction!findCardTemForShow.action?id=" + treeNode.id);
}
//添加组件/原材料流水卡片
function submitForm(formId) {
	$.ajax( {
		type : "POST",
		url : "markIdAction!addProcardTemplate.action",
		dataType : "json",
		data : $("#" + formId).serialize(),
		success : function(msg) {
			if (msg) {
				alert("添加成功!");
				loadTree();
			} else {
				alert("添加失败!");
			}
		}
	});
}

//显示卡片已有工序
function showProcess() {
	$
			.ajax( {
				type : "POST",
				url : "markIdAction!findProcessByFkId.action",
				dataType : "json",
				data : {
					id : $("#cardId").val()
				},
				success : function(msg) {
					$("#ProcessTab").empty();
					$("#ProcessTab")
							.append(
									"<tr><th colspan='7'>已有工序</th></tr>"
											+ "<tr><th>工序号</th><th>名称</th><th>总节拍(s)</th><th>生产类型</th>"
											+ "<th>是否并行</th><th>是否首检</th><th>操作</th></tr>");
					var maxProcessNO = 5;//最大工序号
					$(msg)
							.each(
									function() {
										$("#ProcessTab")
												.append(
														'<tr align="center"><td>'
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
																+ $(this)
																		.attr(
																				'processStatus')
																+ '</td><td>'
																+ $(this)
																		.attr(
																				'zjStatus')
																+ '</td><td><a href="javascript:;" onclick="showProcessForSb('
																+ $(this).attr(
																		'id')
																+ ');">修改</a>/<a href="javascript:;" onclick="deleteProcess('
																+ $(this).attr(
																		'id')
																+ ');">删除</a>'
																+
																 
																'</td></tr>') 
																
															  ;
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
			url : "markIdAction!addProcessTemplate.action",
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
function delProCard() {
	if (window.confirm('确定要删除本卡片吗?此操作将会删除该流水卡片下属的所有信息!')) {
		$.ajax( {
			type : "POST",
			url : "markIdAction!delProcard.action",
			dataType : "json",
			data : {
				id : $("#cardId").val()
			},
			success : function(msg) {
				if (msg=="删除成功!") {
					alert("删除成功!");
					showDiv();//页面内容清空
			$("#selectDiv").hide();
			loadTree();//重新加载树形

		} else {
			alert(msg);
		}
	}
		});
	}
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
function showProcessForSb(id) {
	$("#showProcess").attr("src", "markIdAction!showProcess.action?id=" + id);
	chageDiv('block');

}

function deleteProcess(id) {
	if (window.confirm('确定要删除本工序吗?')) {
		$.ajax( {
			type : "POST",
			url : "markIdAction!deleteProcess.action",
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
	getUnit("danwei1");
	getUnit("danwei2");
	getUnit("danwei3");
	getUnit("danwei4");
})
function tijiao() {
	var tid = '${param.id}';
	 window.location.href="markIdAction!tijiao.action?gysMarkIdjiepai.id="+tid;
}
</script>
	</body>
</html>
