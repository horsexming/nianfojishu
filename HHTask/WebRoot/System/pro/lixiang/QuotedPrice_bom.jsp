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
	<body id="body">
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
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<input type="hidden" id="nrootid">
			<input type="hidden" id="totalStatus">
			<div align="center" style="margin-top: 8px;" id="nextDiv">
				当您的数据全部录入完成后请点击————>
				<%--				<a--%>
				<%--					href="ProjectManage_updateProTimeForFinal.action?id=${id}&pageStatus=${pageStatus}"--%>
				<%--					onclick="return window.confirm('本操作将完成本次报价!确定数据都已经录入完毕?')"><font--%>
				<%--					color="red" size="8px">提交11</font> </a>--%>
			</div>
			<br/>
			<div align="center">
			<form action="QuotedPrice_pladdquotedPrice.action" method="post"
							enctype="multipart/form-data" >
							选择导入文件:
							<input type="file" name="accessory">
							<SELECT name="productStyle">
								<option value="批产">批产</option>
								<option value="试制">试制</option>
							</SELECT>
							<input type="hidden" name="quotedPrice.id" value="${quotedPrice.id}"/>
							<input type="submit" value="导入BOM" id="sub">
						</form>
			</div>
			<hr />
			(
			<b>${projectTime.className}录入填报说明</b>: 填报部门:
			<b>${projectTime.dept}</b> 填报人员:
			<b>${projectTime.userName}</b> 截止时间:
			<b>${projectTime.provisionTime}</b>
			<s:if test="quotedPrice.fileName!=null&&quotedPrice.fileName!=''">
				<a
	<%--				href="DownAction?fileName=${quotedPrice.fileName}&directory=/upload/file/project/">查看附件</a>)--%>
					href="FileViewAction.action?FilePath=/upload/file/project/${quotedPrice.fileName}">查看附件</a>)
			</s:if>
			<s:else>
				<font color="gray">查看附件</font>
			</s:else>
			<div align="left">
				<!-- 显示工艺BOM -->
				<div style="width: 40%; height: 100%; float: left;" align="left">
					<div style="height: 100%;">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
				<!-- 添加工艺BOM -->
				<div style="border-left: 1px solid #000000; float: left; width: 58%;position: absolute;"
					id="showProDetail">
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
						<input id="module4" type="button" value="工序管理"
							onclick="chageModule(this,'4');showProcess();"
							style="width: 80px; height: 50px;" />
						<input id="module5" type="button" value="复制模板"
							onclick="chageModule(this,'5');"
							style="width: 80px; height: 50px;" />
						<input id="module6" type="button" value="修改件号"
							onclick="chageModule(this,'6');"
							style="width: 80px; height: 50px;" />
						<input id="uId" type="hidden">
						<input type="button" value="产品图纸" onclick="showCardTz()"
							style="width: 80px; height: 50px;" />
						<input type="button" value="删除" onclick="delProCard()"
							style="width: 80px; height: 50px;" />

						<br />
					</div>
					<div id="module1_1" style="display: none;">
						<form id="lingForm" style="margin: 0px; padding: 0px;">
							<input type="hidden" id="wrootId" name="quotedPrice.rootId" />
							<input type="hidden" id="wfatherId" name="quotedPrice.fatherId" />
							<input type="hidden" id="wbelongLayer"
								name="quotedPrice.belongLayer" />
							<table class="table" style="width: 100%;">
								<tr>
									<th align="center" colspan="2">
										添加组合件
									</th>
								</tr>
								<tr>
									<th align="right" style="width: 25%;">
										件号:
									</th>
									<td>
										<input name="quotedPrice.markId">
									</td>
								</tr>
								<tr>
									<th align="right">
										名称:
									</th>
									<td>
										<input name="quotedPrice.proName">
									</td>
								</tr>
								<tr>
									<th align="right">
										型别:
									</th>
									<td>
										<input id="zxingbie" name="quotedPrice.xingbie">
									</td>
								</tr>
								<tr>
									<th align="right">
										权值:
									</th>
									<td>
										<input name="quotedPrice.corrCount" value="1"
											onkeyup="value=value.replace(/[^\d\.]/g,'')" type="text">
										( 对应上层所需数量,默认为1:1)
									</td>
								</tr>
								<tr>
									<th align="right">
										卡片类型:
									</th>
									<td>
										<input name="quotedPrice.procardStyle" value="组合"
											readonly="readonly" />
									</td>
								</tr>
								<tr>
									<th align="right">
										单位:
									</th>
									<td>
										<select name="quotedPrice.unit" id="danwei"
											style="width: 155px;">
										</select>
									</td>
								</tr>
								<tr>
									<th align="right">
										产品类型:
									</th>
									<td>
										<select name="quotedPrice.productStyle" style="width: 155px;">
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
										<input name="quotedPrice.remark">
									</td>
								</tr>
								<tr>
									<th align="right">
										是否外购:
									</th>
									<td>
										<select name="quotedPrice.yucailiaostatus"
											style="width: 155px;">
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
							<input type="hidden" id="rootId" name="quotedPrice.rootId" />
							<input type="hidden" id="fatherId" name="quotedPrice.fatherId" />
							<input type="hidden" id="belongLayer"
								name="quotedPrice.belongLayer" />
							<table class="table" style="width: 100%;">
								<tr>
									<th align="center" colspan="2">
										添加外购件
									</th>
								</tr>
								<tr>
									<th align="right" style="width: 25%;">
										件号:
									</th>
									<td>
										<input name="quotedPrice.markId" readonly="readonly"  id="wajmarkId">
										<font color="red">*</font>
										<input type="button" value="选择外购件" onclick="selectYclAndWgj('wgj')">
										<a href="<%=path%>/System/yclandwgj/yuanclAndWaigj_add.jsp"
											target="_showWai">找不到需要的件号?前往添加</a>
									</td>
								</tr>
								<tr>
									<th align="right">
										名称:
									</th>
									<td>
										<input name="quotedPrice.proName" id="wajproName">
									</td>
								</tr>
								<tr>
									<th align="right">
										型别:
									</th>
									<td>
										<input id="wxingbie" name="quotedPrice.xingbie">
									</td>
								</tr>
								<tr>
									<th align="right">
										卡片类型:
									</th>
									<td>
										<input name="quotedPrice.procardStyle" value="外购"
											readonly="readonly" />
									</td>
								</tr>
								<tr>
									<th align="right">
										是否半成品:
									</th>
									<td>
										<select name="quotedPrice.needProcess" id="neddProcess"
											style="width: 155px;">
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
										单位:
									</th>
									<td>
										<select name="quotedPrice.unit" id="danwei2"
											style="width: 155px;">
										</select>
									</td>
								</tr>
								<tr>
									<th align="right">
										权值:
									</th>
									<td>
										<input name="quotedPrice.quanzi1" style="width: 71px;"
											onkeyup="value=value.replace(/[^\d\.]/g,'')" value="1" />
										:
										<input name="quotedPrice.quanzi2" style="width: 71px;"
											onkeyup="value=value.replace(/[^\d\.]/g,'')" value="1" />
										(组合件:外购件,默认为1:1)
									</td>
								</tr>
								<tr>
									<th align="right">
										产品类型:
									</th>
									<td>
										<select name="quotedPrice.productStyle" style="width: 155px;">
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
										<input name="quotedPrice.remark">
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
							<input type="hidden" id="yrootId" name="quotedPrice.rootId" />
							<input type="hidden" id="yfatherId" name="quotedPrice.fatherId" />
							<input type="hidden" id="ybelongLayer"
								name="quotedPrice.belongLayer" />
							<table class="table" style="width: 100%;">
								<tr>
									<th align="center" colspan="2">
										添加自制件
									</th>
								</tr>
								<tr>
									<th align="right" style="width: 25%;">
										件号:
									</th>
									<td>
										<input name="quotedPrice.markId">
									</td>
								</tr>
								<tr>
									<th align="right">
										名称:
									</th>
									<td>
										<input name="quotedPrice.proName">
									</td>
								</tr>
								<tr>
									<th align="right">
										型别:
									</th>
									<td>
										<input id="yxingbie" name="quotedPrice.xingbie">
									</td>
								</tr>
								<tr>
									<th align="right">
										数量:
									</th>
									<td>
										<input name="quotedPrice.corrCount" value="1"
											onkeyup="value=value.replace(/[^\d\.]/g,'')">
										(权值,对应上层所需数量,默认为1:1)
									</td>
								</tr>
								<tr>
									<th align="right">
										备注:
									</th>
									<td>
										<input name="quotedPrice.remark">
									</td>
								</tr>
								<tr>
									<th align="right">
										单位:
									</th>
									<td>
										<select name="quotedPrice.unit" id="danwei3"
											style="width: 155px;">
										</select>
									</td>
								</tr>
								<tr>
									<th align="right">
										卡片类型:
									</th>
									<td>
										<input name="quotedPrice.procardStyle" value="自制"
											readonly="readonly" />
									</td>
								</tr>
								<tr>
									<th align="right">
										产品类型:
									</th>
									<td>
										<select name="quotedPrice.productStyle" style="width: 155px;">
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
										<input name="quotedPrice.trademark">
									</td>
								</tr>
								<tr>
									<th align="right">
										规格:
									</th>
									<td>
										<input name="quotedPrice.specification">
									</td>
								</tr>
								<tr>
									<th align="right">
										材料消耗:
									</th>
									<td>
										<input name="quotedPrice.materialXh"
											onkeyup="value=value.replace(/[^\d\.]/g,'')">
										(指每件零件的单个消耗量)
									</td>
								</tr>
								<tr>
									<th align="right">
										单位:
									</th>
									<td>
										<select name="quotedPrice.yuanUnit" id="danwei1"
											style="width: 155px;">
										</select>
									</td>
								</tr>
								<tr>
									<th align="right">
										权值:
									</th>
									<td>
										<input name="quotedPrice.quanzi1" style="width: 71px;"
											onkeyup="value=value.replace(/[^\d\.]/g,'')" />
										:
										<input name="quotedPrice.quanzi2" style="width: 71px;"
											onkeyup="value=value.replace(/[^\d\.]/g,'')" />
										(自制件:原材料,格式如1:1)
									</td>
								</tr>
								<tr>
									<th align="right">
										是否外购:
									</th>
									<td>
										<select name="quotedPrice.yucailiaostatus"
											style="width: 155px;">
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
							<input id="gxingbie" type="hidden" />
							<table class="table" style="width: 100%">
								<tr>
									<th colspan="8" align="center">
										工序处理
									</th>
								</tr>
								<tr>
									<td align="right">
										工序号:
									</td>
									<td>
										<input id="processNO" name="qpInfor.processNO" value="5" />
									</td>
									<td align="right">
										名 称:
									</td>
									<td id="gongxutd">
										<select id="processName" name="qpInfor.processName"
											style="width: 150px;" onchange="getMachine(this.value)">
											<option></option>
										</select>
										<%--<input id="processName" name="qpInfor.processName" onclick=""
											onblur="getMachine(this.value)" />
									--%>
									</td>
								</tr>
								<tr>
									<td align="right">
										设备:
									</td>
									<td>
										<select id="shebeiNo" name="qpInfor.shebeiId"
											style="width: 150px;">
											<option></option>
										</select>
									</td>
									<td align="right">
										工装:
									</td>
									<td height="83px">
										<input type="button" id="luruNew" value="录入新工装"
											onclick="newGongZhuang()">
										<br />
										<div id = "gongzhuangdiv">
											<select id="gongzhuang" name="qpInfor.gongzhuangId"
											style="width: 197px;">
											<option></option>
										</select>
										</div>
										
										<div id="newGong" style="display: none;">
											编号:
											<input id="gzNumber" name="qpInfor.gongzhuangNumber">
											<br />
											名称:
											<input id="gzName" name="qpInfor.gongzhuang">
										</div>
									</td>
									<%--<td align="right">
										设备时长:
									</td>
									<td>
										<input id="shebeiDateTime" name="qpInfor.shebeiDateTime" />
										(秒)
									</td>
								--%>
								</tr>
								<tr>
									<td align="right">
										生产类型:
									</td>
									<td>
										<select name="qpInfor.productStyle" style="width: 150px;">
											<option value="自制">
												自制
											</option>
											<option value="外委">
												外委
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td colspan="8" align="center">
										<input type="submit" value="添加"
											style="width: 80px; height: 40px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="module1_5" style="display: none;" align="center">
						<br />
						<br />
						<br />
						<input id="mfatherId" type="hidden" />
						<div id="showAll"
							style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">
						</div>
						<input type="text" id="shortname" onkeyup="getAllNames()"
							style="height: 20px" onFocus="init('showAll')"
							onBlur="hidediv('showAll')" name="markId" />
						<input type="button" value="复制" onclick="copyProcard()">
						<br />
						<br />
						<br />
					</div>
					<div id="module1_6" style="display: none;" align="center">
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
					<div id="showCardTemplate" style="display: none; height: 100%;">
						<iframe id="showCardIframe" src="" marginwidth="0"
							marginheight="0" hspace="0" vspace="0" frameborder="0"
							scrolling="auto"
							style="width: 98%; height: 800px; margin: 0px; padding: 0px;"></iframe>
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
			src="<%=basePath%>/javascript/zTree/js/jquery.ztree.excheck-3.5.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>/javascript/zTree/js/jquery.ztree.exedit-3.5.js">
</script>
		<script type="text/javascript">
		var module4=$("#module4");
$(function() {
	getUnit("danwei");
	getUnit("danwei1");
	getUnit("danwei2");
	getUnit("danwei3");
})
//========================================zTree显示
//自动组装树形结构
var setting = {
	edit : {
		enable : true,
		showRemoveBtn : false,
		showRenameBtn : false
	},
	data : {
		simpleData : {
			enable : true
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
//加载树形数据
$(document).ready(loadTree());
//生成
function loadTree() {
	$
			.ajax( {
				url : 'QuotedPrice_findAllForTree.action',
				type : 'post',
				dataType : 'json',
				data : {
					id : '${param.id}'
				},
				cache : true,
				success : function(doc) {
					var zNodes = [];
					$(doc)
							.each(
									function() {
										var b = true;
										if ($(this).attr('procardStyle') == "自制"
												|| $(this).attr('procardStyle') == "外购") {
											b = false;
										}
										//半成品状态
									var needProcess = $(this).attr(
											'needProcess');
									if (needProcess == "yes") {
										needProcess = "(半成品)";
									} else {
										needProcess = "";
									}
										if ($(this).attr('id') == $(this).attr(
												'rootId')) {
											//点击下一步时用到的rootid
											$("#nrootid").val(
													$(this).attr('rootId'));
											$("#totalStatus").val(
													$(this).attr('status'));
											$("#nextDiv").empty();
											if ($(this).attr('status') == "BOM录入") {
<%--												$("#nextDiv")--%>
<%--														.append(--%>
<%--																" <a onclick='nextstpe()'><font color='red' size='8px'>下一步</font> </a>&nbsp;&nbsp;&nbsp;&nbsp;<a onclick='cratesopBom()'><font color='red' size='8px'>创建试制BOM</font></a>");--%>
												$("#nextDiv")
														.append(
																" <a onclick='nextstpe()'><font color='red' size='8px'>下一步</font> </a>");
											} else if ($(this).attr('status') == "外购外委评审") {
												$("#nextDiv")
														.append(
																"<font color='red' size='8px'>外购外委评审中</font>");
											} else if ($(this).attr('status') == "评审完成") {
												$("#nextDiv")
														.append(
																"当您的数据全部录入完成后请点击————> <a href='ProjectManage_updateProTimeForFinal.action?id=${id}&pageStatus=${pageStatus}'"
																		+ " onclick='return window.confirm(\"本操作将完成本次报价!确定数据都已经录入完毕?\")'><font color='red' size='8px'>提交</font> </a>");
											} else if ($(this).attr('status') == "集合报价") {
												$("#nextDiv")
														.append(
																"<font color='red' size='8px'>集合报价中</font>");
												//}else if($(this).attr('status')=="项目启动中"){
												//$("#nextDiv").append("<font color='red' size='8px'>项目启动中</font>");
											}else if ($(this).attr('status') == "完成") {
												$("#nextDiv")
														.append(
																"<font color='red' size='8px'>完成</font>&nbsp;&nbsp;&nbsp;&nbsp;(<a onclick='cratesopBom()'><font color='red' size='8px'>创建试制BOM</font></a>)");
												//}else if($(this).attr('status')=="项目启动中"){
												//$("#nextDiv").append("<font color='red' size='8px'>项目启动中</font>");
											}else {
												$("#nextDiv")
														.append(
																"<font color='red' size='8px'>"+$(this).attr('status')+"</font>");
												//alert("此bom目前状态不可查看或不存在！！！");
												//window.location.href = "QuotedPrice_findQPByCondition.action?quotedPrice.belongLayer=0&pageStatus=bom";
												//return false;
											}
										}
										zNodes.push( {
											id : $(this).attr('id'),
											pId : $(this).attr('fatherId'),
											proStruts : $(this).attr(
													'procardStyle'),
											rootId : $(this).attr('rootId'),
											belongLayer : $(this).attr(
													'belongLayer'),
											xingbie : $(this).attr('xingbie'),
											markId : $(this).attr('markId'),
											name : $(this).attr('proName')
													+ ' '
													+ $(this).attr('markId')
													+ ' '
													+"<span style='font-weight: bolder;font-size: 12px;'>"
														+ $(this).attr(
																'procardStyle')
														+ "<font color='red'>"+needProcess
														+ "</font></span>",
											click : false,
											drop : b,
											open : true
										});

									});
					$("#body").show();
					;
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
			url : "QuotedPrice_moveQuotedPrice.action",
			dataType : "json",
			data : {
				moveId : moveId,
				targetId : targetNode.id
			},
			success : function(msg) {
				alert(msg.message);
				loadTree();
				return msg.success;
			}
		});
	}
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
	//复制卡片
	$("#mfatherId").val(treeNode.id);
	//更新卡片
	$("#uId").val(treeNode.id);
	//修改件号
	$("#newMarkId").val(treeNode.markId);
	$("#oldMarkId").empty();
	$("#oldMarkId").append(treeNode.markId);
	//工序赋值
	$("#cardId").val(treeNode.id);
	//点击下一步时用到的rootid
	$("#nrootid").val(treeNode.rootId);
	showDiv();//页面内容清空
	//显示添加选项的页面
	$("#selectDiv").show();
	$("#showProDetail").show();
	var e = event || window.event;
	var scrollTop = getScrollTop();//获取滚动条离顶部距离
	if(scrollTop<=150){
		scrollTop=0
	}else{
		scrollTop =scrollTop-150;
	}
	var mouseLeft = e.clientX + 300;
	var mouseTop = e.clientY  +scrollTop;
	if (mouseTop < 0) {
		mouseTop = 0;
	}
	//显示悬浮窗样式的项目
	$("#showProDetail").css( {
		"top" : mouseTop,
		"left" : mouseLeft
	});
	//工序赋值
	$("#gxingbie").val(treeNode.xingbie);
	if (proStruts == "总成" || proStruts == "组合") {
		//零组件赋值
		$("#rootId").val(treeNode.rootId);
		$("#fatherId").val(treeNode.id);
		$("#belongLayer").val(treeNode.belongLayer + 1);
		$("#zxingbie").val(treeNode.xingbie);
		//外购件赋值
		$("#wrootId").val(treeNode.rootId);
		$("#wfatherId").val(treeNode.id);
		$("#wbelongLayer").val(treeNode.belongLayer + 1);
		$("#wxingbie").val(treeNode.xingbie);
		//原材料赋值
		$("#yrootId").val(treeNode.rootId);
		$("#yfatherId").val(treeNode.id);
		$("#ybelongLayer").val(treeNode.belongLayer + 1);
		$("#yxingbie").val(treeNode.xingbie);
		//全部可用
		$("#module1").attr("disabled", false);
		$("#module2").attr("disabled", false);
		$("#module3").attr("disabled", false);
		$("#module4").attr("disabled", false);
		$("#module5").attr("disabled", false);
		$("#module6").attr("disabled", false);
	} else if (proStruts == "外购") {
		//全部不可用
		$("#module1").attr("disabled", true);
		$("#module2").attr("disabled", true);
		$("#module3").attr("disabled", true);
		$("#module4").attr("disabled", true);
		$("#module5").attr("disabled", true);
		$("#module6").attr("disabled", false);
	} else if (proStruts == "自制") {
		//全部不可用
		$("#module1").attr("disabled", true);
		$("#module2").attr("disabled", true);
		$("#module3").attr("disabled", true);
		$("#module4").attr("disabled", false);
		$("#module5").attr("disabled", false);
		$("#module6").attr("disabled", false);
	}
	if ($("#totalStatus").val() == "项目启动中") {
		$("#module5").attr("disabled", true);
		$("#module6").attr("disabled", true);
	}
	//关闭已打开的功能Div
	if (oldObj != null) {
		$("#module1_" + oldObj2).hide();
	}

	//显示流水卡片明细
	$("#showCardTemplate").show();
	$("#showCardIframe").attr(
			"src",
			"QuotedPrice_findQpDetailForShow.action?pageStatus=${pageStatus}&id="
					+ treeNode.id);
}
//添加组件/原材料流水卡片
function submitForm(formId) {
	$.ajax( {
		type : "POST",
		url : "QuotedPrice_addQuotedPriceTree.action",
		dataType : "json",
		data : $("#" + formId).serialize(),
		success : function(msg) {
			if (msg) {
				alert("添加成功!");
				loadTree();//重新加载树形
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
				url : "QuotedPrice_findQpInforByFkid.action",
				dataType : "json",
				data : {
					id : $("#cardId").val()
				},
				success : function(msg) {
					$("#ProcessTab").empty();
					$("#ProcessTab")
							.append(
									"<tr><th colspan='7'>已有工序</th></tr>"
											+ "<tr><th>工序号</th><th>名称</th><th>设备</th><th>工装</th><th>操作</th></tr>");
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
																+ $(this)
																		.attr(
																				'shebeiName')
																+ '</td><td>'
																+ $(this)
																		.attr(
																				'gongzhuang')
																+ '</td><td><a href="javascript:;" onclick="showProcessTz('
																+ $(this).attr(
																		'id')
																+ ');">图纸</a>/<a href="javascript:;" onclick="deleteProcess('
																+ $(this).attr(
																		'id')
																+ ');">删除</a>/<a href="javascript:;" onclick="updateProcess('
																+ $(this).attr(
																		'id')
																+ ');">修改</a></td></tr>');
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
					$("#gongzhuang").empty();//清空设备列表

					getProcess();//加载工装

					getGz();//加载工装
				}
			});
}
//添加工序
function submitForm2(formId) {
	if ($("#processName").val() == "") {
		alert("请填写工序名称!");
		$("#processName").select();
	}
	//else if ($("#shebeiNo").val() != "" && $("#shebeiDateTime").val() == "") {
	//alert("请填写设备时长!");
	//$("#shebeiDateTime").select();
	//} 
	else {
		if ($("#gongzhuang").val() == null || $("#gongzhuang").val() == ""
				|| $("#gongzhuang").val() == 0) {
			if ($("#gzNumber").val() != null && $("#gzNumber").val() != ""
					&& ($("#gzName").val() == null || $("#gzName").val() == "")) {
				alert("不能只填写工装编号而不填写工装名称");
				return false;
			}
			if ($("#gzName").val() != null
					&& $("#gzName").val() != ""
					&& ($("#gzNumber").val() == null || $("#gzNumber").val() == "")) {
				alert("不能只填写工装名称而不填写工装编号");
				return false;
			}
		}

		if ($("#processStatus").val() == "no") {
			$("#parallelId").val("");
		}
		$.ajax( {
			type : "POST",
			url : "QuotedPrice_addQpInfor.action",
			dataType : "json",
			data : $("#" + formId).serialize(),
			success : function(msg) {
				if (msg.success) {
					alert(msg.message);
					showProcess();
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

//删除工艺规范
function delProCard() {
	if (window.confirm('确定要删除件号吗?此操作将会删除该件号下的所有信息!')) {
		$.ajax( {
			type : "POST",
			url : "QuotedPrice_delQuotedPriceTree.action",
			dataType : "json",
			data : {
				id : $("#cardId").val()
			},
			success : function(msg) {
				if (msg.success) {
					alert(msg.message);
					showDiv();
					$("#selectDiv").hide();
					loadTree();
				} else {
					alert(msg.message);
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
function updateProcess(id) {
	$("#showProcess").attr("src", "QuotedPrice_showProcess.action?id=" + id);
	chageDiv('block');

}
//显示工序图纸
function showProcessTz(id) {
	$("#showProcess").attr("src", "QuotedPrice_showProcessTz.action?id=" + id);
	chageDiv('block');

}

function deleteProcess(id) {
	if (window.confirm('确定要删除本工序吗?')) {
		$.ajax( {
			type : "POST",
			url : "QuotedPrice_delQpInfor.action",
			dataType : "json",
			data : {
				id : id
			},
			success : function(msg) {
				if (msg.success) {
					alert(msg.message);
					showProcess();
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
//通过工序名称查询设备
function getMachine(processName) {
	if (processName != "") {
		$.ajax( {
			type : "POST",
			url : "GzstoreAction!getMachineListByGongxuName.action",
			dataType : "json",
			data : {
				processName : processName
			},
			success : function(msg) {
				if (msg.success) {
					$("#shebeiNo").empty();
					$("#shebeiNo").append("<option value=''></option>");
					$.each(msg.data, function(i, n) {
						$("#shebeiNo").append(
								"<option value='" + n.id + "'>" + n.name + "("
										+ n.no + ")</option>");
					});
				} else {
					alert(msg.message);
				}
			}
		});
	} else {
		$("#shebeiNo").empty();
	}
}
//通过型别查询工装信息
function getGz() {
	var gxingbie = $("#gxingbie").val();
	$.ajax( {
		type : "POST",
		url : "GzstoreAction!getGzstoreListByXingbie.action",
		data : {
			'gzstore.xingbie' : gxingbie
		},
		dataType : "json",
		success : function(msg) {
			if (msg.success) {
				$("#gongzhuang").empty();
				$("#gongzhuang").append("<option value=''></option>");
				$.each(msg.data, function(i, n) {
					$("#gongzhuang").append(
							"<option value='" + n.id + "'>" + n.no + "("
									+ n.name + ")</option>");
				});
				var tinyselect = $(".tinyselect");
						if (tinyselect[1] != null) {
							document.getElementById("gongzhuangdiv").removeChild(
									tinyselect[1]);
						}
				$("#gongzhuang").tinyselect();
			} else {
				alert(msg.message);
			}
		}
	});
}

//查询所有工序
function getProcess() {
	$.ajax( {
		type : "POST",
		url : "processGzstoreAction_getProcessGzstoreListAllForSelect.action",
		dataType : "json",
		success : function(msg) {
			if (msg.success) {
				$("#processName").empty();
				$("#processName").append("<option value=''></option>");
				$.each(msg.data, function(i, n) {
					$("#processName").append(
							"<option value='" + n.processName + "'>"
									+ n.processName + "</option>");
				});
				var tinyselect = $(".tinyselect");
						if (tinyselect[1] != null) {
							document.getElementById("gongxutd").removeChild(
									tinyselect[1]);
						}
				$("#processName").tinyselect();
			} else {
				alert(msg.message);
			}
		}
	});
}

//录入新工装
var newStatus = "new";
function newGongZhuang() {
	if (newStatus == "new") {
		$("#newGong").show();
		$("#gongzhuang").hide();
		newStatus = "old";
		$("#luruNew").val("选择工装库");
	} else {
		$("#newGong").hide();
		$("#gongzhuang").show();
		newStatus = "new";
		$("#luruNew").val("录入新工装");
	}
}
//点击下一步跳往生成工艺的外购外委评审单
function nextstpe() {
	var b = window.confirm("您将把bom提交到外购外委评审，是否继续？");
	if (b) {
		var rootid = $("#nrootid").val();
		$.ajax( {
			type : "POST",
			url : "osaAction!saveBomOSApp.action",
			dataType : "json",
			data : {
				rootId : rootid
			},
			success : function(msg) {
				alert(msg.message);
			}
		});
	}
}
//创建试制bom
function cratesopBom() {
	var b = window.confirm("您将把bom打入试制bom是否继续？");
	if (b) {
		var rootid = $("#nrootid").val();
		$.ajax( {
			type : "POST",
			url : "QuotedPrice_buildBomtosop.action",
			dataType : "json",
			data : {
				'quotedPrice.rootId' : rootid
			},
			success : function(msg) {
				alert(msg.message);
			}
		});
	}
}
</script>
		<script language="javascript">
function init() {
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
				url : "QuotedPrice_getAllNames.action",
				dataType : "json",
				data : {
					markId : $("#shortname").val()
				},
				success : function(data) {
					$("#showAll").empty();
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
														"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdiv(this)' align='left'>"
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
		url : "QuotedPrice_copyProcard.action",
		dataType : "json",
		data : {
			id : $("#mfatherId").val(),
			markId : $("#shortname").val()
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
function updateProcard() {
	if (!window.confirm("您将更新所有同件号的报价零件,是否继续更新！")) {
		return false;
	}
	var id = $("#uId").val();
	if (id == "") {
		alert("您选中的模板不存在，添加失败！");
		return;
	}
	$.ajax( {
		type : "POST",
		url : "QuotedPrice_updateQuotedPriceByMarkId.action",
		dataType : "json",
		data : {
			id : id
		},
		success : function(data) {
			if (data.message != null) {
				alert(data.message);
			}
		}
	});
}

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
		url : "QuotedPrice_updateMarkId.action",
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
						"QuotedPrice_findQpDetailForShow.action?pageStatus=${pageStatus}&id="
								+ treeNode.id);
			}
		}
	})
}

function showCardTz(){
	var id=$("#cardId").val();
	$("#showProcess").attr("src",
			"QuotedPrice_showCardTz.action?id=" + id);
	chageDiv('block');
}

//选择外购件框
function selectYclAndWgj(type) {
	$("#showProcess").attr("src",
			"procardTemplateGyAction_showYclAndWgj.action?type=" + type);
	chageDiv('block');
}
</script>
	</body>
</html>
