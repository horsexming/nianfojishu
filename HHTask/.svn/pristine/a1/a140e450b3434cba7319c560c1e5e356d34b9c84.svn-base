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
		<link type="text/css" rel="stylesheet"
			href="${pageContext.request.contextPath}/css/cosmetic.css">

		<%@include file="/util/sonHead.jsp"%>
		<script type="text/javascript" src="<%=basePath%>/js/JsBarcode.all.js">
</script>
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/css/pritureS/assets/css/bootstrap.min.css">
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/css/pritureS/dist/viewer.css">
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/css/pritureS/demo/css/main.css">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/jquery.enlarge.js">
</script>
		<script type="text/javascript">
function daoJiShi(date, id) {
	var now = new Date();
	var endDate = new Date(date);//年月日时分秒，月要减去1
	var oft = Math.round((endDate - now) / 1000);
	var ofd = parseInt(oft / 3600 / 24);
	var ofh = parseInt((oft % (3600 * 24)) / 3600);
	var ofm = parseInt((oft % 3600) / 60);
	var ofs = oft % 60;
	document.getElementById(id).innerHTML = +ofd + ' 天 ' + ofh + ' 时 ' + ofm
			+ ' 分 ' + ofs + ' 秒';
	setTimeout("daoJiShi('" + date + "','" + id + "')", 1000);
};

</script>
		<style type="text/css">
.contentDiv100 {
	height: 800px;
}
.contentDiv {
	height: 5000px;
}

.contentDiv_now {
	position: absolute;
	z-index: 255;
	width: 100%;
	display: none;
	left:0px;
}
</style>
	</head>
	<body onkeydown="enter()">
		<div id="bodyDiv" align="center" class="transDiv" style="z-index: 2;"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 100%; display: none; top: 20px;"
			align="center">
			<div id="closeDiv">
				<table style="width: 100%" id="carzuo">
					<tr>
						<td>
							<span id="title">请操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none');reload();">
						</td>
					</tr>
				</table>
				<div id="submitProcessDiv" style="display: none;">
					<%--					<table style="width: 100%; margin-top: ">--%>
					<%--						<tr>--%>
					<%--							<td>--%>
					<%--								您正在提交工序:--%>
					<%--							</td>--%>
					<%--							<td align="right">--%>
					<%--								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"--%>
					<%--									height="32" onclick="chageDiv('none');reload();">--%>
					<%--							</td>--%>
					<%--						</tr>--%>
					<%--					</table>--%>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
						<form id="submitProcess">
							<input id="submitProId" type="hidden" name="process.id" />
							<input id="submitProcardId" type="hidden" name="id" />
							<table class="table" style="width: 40%">
								<tr>
									<th colspan="2">
										提交工序
									</th>
								</tr>
								<tr>
									<th align="right">
										提交数量:
									</th>
									<th align="left">
										<input id="subNumber" name="process.submmitCount"
											maxsize="100" onblur="numyanzheng(this,'zhengshu')"
											onkeyup="numyanzheng(this,'zhengshu')"
											onchange="changNumber()" />
										最大可提交数量
									</th>
								</tr>
								<tr>
									<th align="right">
										不合格类型
									</th>
									<th align="left">
										<input type="radio" value="零件损坏" name="breaksubmit.type"
											id="zzbreakType_1" onclick="hidProcessAndWg('1')"
											checked="checked" />
										零件损坏
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" value="外购件不合格" name="breaksubmit.type"
											id="wgbreakType_1" onclick="showProcessAndWg('1')" />
										<span id="wgbreakType_span">外购件不合格</span>
									</th>
								</tr>
								<tr id="wgtr_1" style="display: none;">

								</tr>
								<tr id="ljbreak_tr_1">
									<th align="right">
										不合格数量:
									</th>
									<th align="left">
										<input name="process.breakCount" value="0"
											onchange="changNumber();numyanzheng(this,'zhengshu')"
											id="breakCount_1" />
									</th>
								</tr>
								<tr>
									<td colspan="2" align="center">
										辅料
										<%--							<s:if test="process.isNeedFuliao=='yes'">--%>
										<%--								<input type="radio" name='process.isNeedFuliao'--%>
										<%--									value="yes" checked="checked" onchange="changeFuliao('是')">是--%>
										<%--					  <input type="radio" name='process.isNeedFuliao'--%>
										<%--									value="no" onchange="changeFuliao('否')"> 否--%>
										<%--					 </s:if>--%>
										<%--							<s:else>--%>
										<%--								<input type="radio" name='process.isNeedFuliao'--%>
										<%--									value="yes" onchange="changeFuliao('是')">是--%>
										<%--					  <input type="radio" name='process.isNeedFuliao'--%>
										<%--									value="no" checked="checked" onchange="changeFuliao('否')"> 否--%>
										<%--					 </s:else>--%>
									</td>
								</tr>
								<tr id="fuliaoTr">
									<td colspan="2" align="center">
										<table id="fuliaoTb">
											<tr>
												<td style="border-top: 0px; border-left: 0px" align="center">
													类别
												</td>
												<td style="border-top: 0px; border-left: 0px" align="center">
													名称
												</td>
												<td style="border-top: 0px; border-left: 0px" align="center">
													规格
												</td>
												<td style="border-top: 0px; border-left: 0px" align="center">
													用量
												</td>
												<td style="border-top: 0px; border-left: 0px" align="center">
													单位
												</td>
												<td
													style="border-top: 0px; border-left: 0px; border-right: 0px"
													align="center">
													<input type="button" value="增加" onclick="addFuliaoLine()">
												</td>
											</tr>
											<tr id='fuliaoTr0'>
												<td style="border-top: 0px; border-left: 0px" align="center">
													<SELECT id="type0" name="process.fuliaoList[0].type">
														<option></option>
														<option>
															备件
														</option>
														<option>
															金属五交材料
														</option>
														<option>
															工具
														</option>
														<option>
															办公用品
														</option>
														<option>
															杂品
														</option>
														<option>
															金属五交
														</option>
														<option>
															工装
														</option>
														<option>
															五金
														</option>
														<option>
															包装物
														</option>
													</SELECT>
												</td>
												<td style="border-top: 0px; border-left: 0px" align="center">
													<SELECT id="type0" name="process.fuliaoList[0].name">
														<option></option>
														<option>
															焊丝
														</option>
														<option>
															气体
														</option>
														<option>
															手套
														</option>
														<option>
															砂轮打磨片
														</option>
														<option>
															导电嘴
														</option>
														<option>
															润滑油
														</option>
														<option>
															油漆性记号笔
														</option>
														<option>
															防护玻璃片
														</option>
													</SELECT>
												</td>
												<td style="border-top: 0px; border-left: 0px" align="center">
													<input id="specification0"
														name="process.fuliaoList[0].specification">
												</td>

												<td style="border-top: 0px; border-left: 0px" align="center">
													<input id="outCount0" name="process.fuliaoList[0].outCount">
												</td>
												<td style="border-top: 0px; border-left: 0px" align="center">
													<select id="flUnit0" name="process.fuliaoList[0].unit">
														<option></option>
													</select>
												</td>

												<td
													style="border-top: 0px; border-left: 0px; border-right: 0px"
													align="center">
													<input type="button" value="删除"
														onclick="deleteFuliaoLine(0)">
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th align="center" colspan="2">
										本工作日未完成半成品入库
									</th>
								</tr>
								<tr>
									<td colspan="2">
										<table class="table">
											<tr>
												<th style="display: none;">
													库别
												</th>
												<th>
													仓区
												</th>
												<th>
													库位
												</th>
											</tr>
											<tr>
												<td style="display: none;">
													<select name="processSaveLog.warehouse" id="warehouse"
														onchange="getcangqu()">
														<option></option>
														<option value="半成品库">
															半成品库
														</option>
													</select>
												</td>
												<td align="center" id="goodHouseName_td">
													<SELECT name="processSaveLog.goodHouseName"
														id="goodHouseName" onclick="selectcq()"
														onchange="getkuwei(this)">
														<option value=""></option>
													</SELECT>
												</td>
												<td align="center" id="goodsStorePosition_td">
													<select name="processSaveLog.goodsStorePosition"
														style="width: 200px;" id="goodsStorePosition">
														<option value=""></option>
													</select>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th colspan="2">
										<input type="hidden" name="barcode" id="proLgbarcode" />
										<input id="submitProce" type="button" value="提交"
											onclick='submitForm()' style="width: 80px; height: 50px;" />
										<div id="showWait"></div>
									</th>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 100%; height: 5000px; margin: 0px; padding: 0px;"></iframe>
				</div>

			</div>
		</div>
		<div id="printDiv" style="display: none; width: 100%">
			<div id="printgxDiv" style="width: 100%; padding: 0px; margin: 0px;">
				<table class="table"
					style="width: 260px; padding: 0px; margin: 0px; font-weight: bolder;">
					<tr>
						<th colspan="2">
							提交工序
						</th>
					</tr>
					<tr>
						<th align="right">
							件号:
						</th>
						<td>
							<div id="printMarkId"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							名称:
						</th>
						<td>
							<div id="printProName"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							版本号:
						</th>
						<td>
							<div id="printbanben"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							内部订单:
						</th>
						<td>
							<div id="orderNum"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							批次:
						</th>
						<td>
							<div id="printSelfCard"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							工序号:
						</th>
						<td>
							<div id="printProcessNO"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							工序名称:
						</th>
						<td>
							<div id="printProcessName"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							提交数量:
						</th>
						<td>
							<div id="printSubmmitCount"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							提交人:
						</th>
						<td>
							<div id="printUsernames"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							提交时间:
						</th>
						<td>
							<div id="printSubmitDate"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							下一工序:
						</th>
						<td>
							<div id="nextProcess"></div>
						</td>
					</tr>
					<tr>
						<th align="right">
							成型图:
						</th>
						<td>
							<div id="cxtShow"></div>
						</td>
					</tr>
<%--					<tr>--%>
<%--						<th align="right">--%>
<%--							条形码:--%>
<%--						</th>--%>
<%--						<td>--%>
<%--							<img id="bar" width="200px" height="50px" />--%>
<%--						</td>--%>
<%--					</tr>--%>
					<tr>
						<th align="right">
							二维码:
						</th>
						<td>
							<div id="erweima"></div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div style="margin-bottom: 5px; margin-top: 5px;" align="right">
					<div
						style="float: left; margin-top: 9px; left: 100px; margin-left: 40px; font-size: 16px;">
						<s:if test="name=='yes'">
							<b>工位:</b>
							<span style="font-weight: bolder;" title="${gow}">
								${gongwei} </span>
						</s:if>
						<s:else>
							<b>工位：${gongwei}</b>
						</s:else>
						操作者:
						<s:if test="#session.Users.name!=null">
						${Users.name}&nbsp;&nbsp;&nbsp;&nbsp;
						</s:if>
						<s:else>
							<a href="javascript:;" onclick="denglu('')">点击登录</a>&nbsp;&nbsp;&nbsp;&nbsp;
						</s:else>
					</div>
					<div style="float: left; margin-top: 9px; right: 100px;">
						<s:if test="#session.Users.name!=null">
							<a href="javascript:;" onclick="lingqu()"
								style="font-size: 22px;">[领取工序]</a>&nbsp;&nbsp;
							<a href="javascript:;" onclick="tijiaoList()"
								style="font-size: 22px;">[提交工序]</a>&nbsp;&nbsp;
							<s:if test="tag=='one'">
								<a
									href="SuspsomAction_selectGongxu.action?ipAddress=${ipAddress}&tag=one"
									style="font-size: 22px;">[所有工序]</a>&nbsp;&nbsp;
							</s:if>
							<s:else>
								<a
									href="SuspsomAction_selectGongxu.action?ipAddress=${ipAddress}&tag="
									style="font-size: 22px;">[我的工序]</a>&nbsp;&nbsp;
							</s:else>
							<a href="javascript:;" onclick="qita()" style="font-size: 22px;">[查看其它]</a>&nbsp;&nbsp;&nbsp;&nbsp;
						</s:if>
					</div>
					<div id="lightId" style="float: left; margin-left: 10px;">
						<s:if test="#session.Users.name!=null">
							<s:iterator value="listLight" id="light" status="pageIndex">
								<s:if test='#light.lightStatus=="关闭"'>
									<img height="40" width="30" src='images/Loff.png'
										style="margin-right: 10px;"
										onclick="lightOnOff('${light.id}','${light.lightZhiLing}','${ipAddress}',this)"
										title="${light.lightNum}" />
								</s:if>
								<s:else>
									<img style="margin-right: 10px;"
										onclick="lightOnOff('${light.id}','${light.lightZhiLing}','${ipAddress}',this)"
										height="40" width="30" src='images/Lon.png'
										title="${light.lightNum}" />
								</s:else>
							</s:iterator>
						</s:if>
					</div>
					<div
						style="float: right; margin-top: 9px; margin-right: 30px; font-size: 18px;">
						<a href="javascript:reload();">[刷新]</a>
						<s:if test="#session.Users.name!=null">
							<a href="SuspsomAction_toOut.action?ipAddress=${ipAddress}">[退出登录]</a>
						</s:if>
					</div>
					<div style="clear: both;"></div>
				</div>

				<div id="time-limit" class="time-limit w floor floor-current"
					style="width: 95%">
					<div class="m">
						<div class="mc">
							<s:if test="errorMessage!=null">
								<div style="font-size: 25px; color: red; margin-top: 20px;">
									${errorMessage}
								</div>
							</s:if>
							<s:iterator value="listAll" id="listNum" status="pageIndex">
								<ul class="limit-list">
									<li clstag="firsttype|keycount|mztm|g1" class="fore1"
										id="time_limit_1950166215" sku="1950166215" style="height: a">
										<div class="p-img" style="background-color: #ccc">
										</div>
										<div class="p-info" style="left: 20px; width: 260px;">
											<div id="time_1950166215" class="time"
												style="left: 15px; width: 230px; font-size: 14px; color: red;">
												<i></i><b id="timer${pageIndex.index+1}"></b>
												<script type="text/javascript">
												daoJiShi('${processInfor.submitDate}', "timer${pageIndex.index+1}");
												</script>
											</div>
											<dl>
												<dt style="margin-bottom: 2px;">
													${processInfor.procard.markId}
												</dt>
												<dd style="height: 160px">
													<table style="line-height: 23px;">
														<tr>
															<th align="right" style="width: 51px;">
																名称:
															</th>
															<td>
																${processInfor.procard.proName}
															</td>
														</tr>
														<tr>
															<th align="right">
																批次:
															</th>
															<td>
																${processInfor.procard.selfCard}
															</td>
														</tr>
														<tr>
															<th align="right">
																状态:
															</th>
															<td>
																${processInfor.procard.status}
															</td>
														</tr>
														<tr>
															<th align="right">
																数量:
															</th>
															<td>
																${processInfor.procard.filnalCount}件
															</td>
														</tr>
														<tr>
															<th align="right">
																类型:
															</th>
															<td>
																${processInfor.procard.productStyle}
															</td>
														</tr>
														<tr>
															<th>
																图纸：
															</th>
															<td>
																<s:iterator value="#listNum.tdtuzhi" id="file3D1">
																	<s:if test="#file3D1.type=='3D文件'">
																		<a target="_show3D"
																			href="show3Dfile.jsp?filePath=upload/file/processTz/${file3D1.month}/${file3D1.fileName}">查看3D图纸</a>&nbsp;&nbsp;
																</s:if>
																</s:iterator>
															</td>
														</tr>
													</table>
												</dd>
											</dl>
											<div class="clr"></div>
										</div>
										<div class="p-info" style="left: 300px; width: 74%;">
											<div align="left" style="border-bottom: dotted 1px #ccc;">
												<table style="width: 100%;">
													<tr align="center">
														<th>
															工序号
														</th>
														<th>
															名称
														</th>
														<th>
															总数量
														</th>
														<th>
															可领数量
														</th>
														<th>
															已领数量
														</th>
														<th>
															提交数量
														</th>
														<th>
															不合格量
														</th>
													</tr>
													<tr align="center">
														<th height="30px" style="border-bottom: dotted 1px #ccc;">
															${processInfor.processNO}
														</th>
														<th style="border-bottom: dotted 1px #ccc;">
															${processInfor.processName}
														</th>
														<th style="border-bottom: dotted 1px #ccc;">
															${processInfor.totalCount}
														</th>
														<th style="border-bottom: dotted 1px #ccc;">
															${processInfor.totalCount}
														</th>
														<th style="border-bottom: dotted 1px #ccc;">
															${processInfor.applyCount}
														</th>
														<th style="border-bottom: dotted 1px #ccc;">
															${processInfor.submmitCount}
														</th>
														<th style="border-bottom: dotted 1px #ccc;">
															${processInfor.breakCount}
														</th>
													</tr>
													<tr>
														<th>
															领取时间
														</th>
														<th>
															工位
														</th>
														<th>
															设备编号
														</th>
														<th>
															状态
														</th>
														<th>
															控制类型
														</th>
														<th rowspan="2">
															<s:iterator value="#listNum.users" id="usersNum"
																status="ststusfunction">
																<div style="float: left;">
																	<img
																		src="<%=basePath%>upload/user/${usersNum.password.picture}"
																		height="60" width="60" title="${usersNum.name}"
																		style="border: solid 1px #000000; border-radius: 50%;"
																		onerror="this.src='images/man.jpg'" />
																	<br />
																	${usersNum.name}
																</div>
															</s:iterator>
														</th>
														<th rowspan="2">
															<s:iterator value="#listNum.users" id="usersNumst">
																<s:if
																	test="#session.Users.name!=null&&processInfor.status=='已领'.toString()&&#usersNumst.name==#session.Users.name">
																	<!-- 如果提交量为0 则开始首检提交 -->
																	<s:if
																		test="processInfor.hadsj!='yes'&&processInfor.zjStatus=='yes'">
																		<input type="button" value="提交"
																			onclick="submitProcess('${processInfor.procard.id}','${processInfor.id}','1','sj',this)"
																			style="height: 50px; width: 50px; border: solid 1px red; border-radius: 100%;" />
																	</s:if>
																	<s:else>
																		<s:if test='processInfor.pmiType=="强控"'>
																			<input type="button" value="断电&提交"
																				style="height: 30px;"
																				onclick="pmiAutoSubPro('${processInfor.id}')" />
																		</s:if>
																		<s:else>
																			<input type="button" value="提交"
																				style="height: 50px; width: 50px; border: solid 1px red; border-radius: 100%;"
																				onclick="submitProcess('${processInfor.procard.id}','${processInfor.id}','${processInfor.applyCount-processInfor.submmitCount-processInfor.breakCount}','',this,'${processInfor.pg.barcode}')" />
																		</s:else>
																		<%--																		submitProcess('${processInfor.procard.id}','${processInfor.id}','${processInfor.applyCount-processInfor.submmitCount-processInfor.breakCount}','',this)" />--%>
																	</s:else>
																</s:if>
															</s:iterator>
														</th>
													</tr>
													<tr>
														<th width="100px">
															${processInfor.firstApplyDate}
														</th>
														<th>
															${processInfor.gongwei}
														</th>
														<th>
															${processInfor.shebeiNo}
														</th>
														<th>
															${processInfor.status}
														</th>
														<th>
															<s:if test='processInfor.pmiType==null||processInfor.pmiType==""'>
																无
															</s:if>
															<s:else>
																${processInfor.id}${processInfor.pmiType}
															</s:else>
														</th>
													</tr>
												</table>
											</div>
											<div align="left"
												style="margin-top: 10px; float: left; width: 100%;">
												<div style="float: left; width: 50%">
													<ul style="float: left;">
														<li>
															图纸：
														</li>
													</ul>
													<div class="container">
														<div class="row">
															<div class="col-sm-8 col-md-6">
																<div class="docs-galley">
																	<ul class="docs-pictures clearfix">
																		<s:iterator value="#listNum.tuzhi" id="tzFile">
																			<s:if test="#tzFile.type=='工艺规范'">
																				<li>
																					<a target="_showPri" href="/FileViewAction.action?FilePath=<%=path%>/upload/file/processTz/${tzFile.month}/${tzFile.fileName}">
																							<img src="<%=path%>/upload/file/processTz/${tzFile.month}/${tzFile.fileName}" style="width: 80px; height: 80px;">
																					</a>
																				</li>
																			</s:if>
																		</s:iterator>
																	</ul>
																</div>
															</div>

														</div>
													</div>
												</div>
<%--												<div style="float: left; width: 50%">--%>
<%--													<ul style="float: left;">--%>
<%--														<li>--%>
<%--															SOP：--%>
<%--														</li>--%>
<%--													</ul>--%>
<%--													<div class="container">--%>
<%--														<div class="row">--%>
<%--															<div class="col-sm-8 col-md-6">--%>
<%--																<div class="docs-galley">--%>
<%--																	<ul class="docs-pictures clearfix">--%>
<%--																		<s:iterator value="#listNum.tuzhi" id="tzFile">--%>
<%--																			<s:if test="#tzFile.type=='SOP文件'">--%>
<%--																				<li>--%>
<%--																					<img id="demo1"--%>
<%--																						src="<%=path%>/upload/file/processTz/${tzFile.month}/${tzFile.fileName}"--%>
<%--																						style="width: 80px; height: 79px; border: solid 1px #000000;" />--%>
<%--																				</li>--%>
<%--																			</s:if>--%>
<%--																		</s:iterator>--%>
<%--																	</ul>--%>
<%--																</div>--%>
<%--															</div>--%>
<%----%>
<%--														</div>--%>
<%--													</div>--%>
<%--												</div>--%>
											</div>
										</div>
									</li>
								</ui>
						</s:iterator>
						</div>
						<s:if test="tag=='one'&&#session.Users.name!=null&&list3!=null">
							<br />
							<div align="center" style="font-size: 30px; color: green;">
								我的待检码

							</div>
							<br />
							<s:iterator value="list3" id="jynum" status="codeIndex">
								<div style="float: left; margin: 20 20 0 0 px;" align="left">
									<div id="code${codeIndex.index}"
										style="height: 210px; width: 250px;">
									</div>
									<script type="text/javascript">
$('#code${codeIndex.index}').qrcode( {
	render : "table", //table方式 
	width : 175, //宽度 
	height : 120, //高度 
	text : "${jynum.number}" //任意内容 
}); //任意字符串 
</script>
									件号:
									<b>${jynum.markId}</b>
									<br />
									批次:
									<b>${jynum.lotId}</b>
									<br />
									工序号:
									<b>${jynum.processNO}</b>
									<br />
									工位:
									<b>${jynum.gongwei}</b>
								</div>
							</s:iterator>
						</s:if>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

		<script type="text/javascript">
function lingqu() {
	var url = "<%=basePath%>/System/SOP/produce/Procard_noCardList.jsp?pageStatus=lingqu";
	$("#showProcess").attr("src", url);
	$("#carzuo").show();
	$("#showProcess").show();
	$("#submitProcessDiv").hide();
	//$("#contentDiv").removeAttr("style");
	$("#contentDiv").removeAttr("class");
	$("#contentDiv").addClass("contentDiv1");//关闭统一样式
	chageDiv('block');
	//调整左边距
	$("#contentDiv").css("left",  "0px");
	$("#contentDiv").removeAttr("css");
	$("#contentDiv").addClass("contentDiv_now");//关闭统一样式
	
}
function tijiaoList() {
	var url = "ProcardAction!findProcardByCardNum.action?pageStatus=noCardHadYlGx&cardNumber=${session.Users.cardId}";
	$("#showProcess").attr("src", url);
	$("#carzuo").show();
	$("#showProcess").show();
	$("#submitProcessDiv").hide();
	$("#contentDiv").removeAttr("class");
	$("#contentDiv").removeAttr("style");
	$("#contentDiv").addClass("contentDiv1");//关闭统一样式
	chageDiv('block');
	//调整左边距
	$("#contentDiv").css("left",  "0px");
	$("#contentDiv").removeAttr("css");
	$("#contentDiv").addClass("contentDiv_now");//关闭统一样式
	$("#contentDiv").css("height",  "5000px");
}
function tijiao() {
	var url = "<%=basePath%>System/SOP/produce/Process_shuaka.jsp?pageStatus=gongweitijiao";
	$("#showProcess").attr("src", url);
	$("#carzuo").show();
	$("#showProcess").show();
	$("#submitProcessDiv").hide();
	chageDiv('block');
}
function selecttuzhi(obj) {
	var url = obj;
	$("#showProcess").attr("src", url);
	$("#carzuo").show();
	$("#showProcess").show();
	$("#submitProcessDiv").hide();
	chageDiv('block');
}
function denglu(cardid) {
	var url = "SuspsomAction_denglu.action";
	if (cardid != '') {
		url += "?gow=" + cardid;
	}
	$("#showProcess").attr("src", url);
	$("#carzuo").show();
	$("#showProcess").show();
	$("#submitProcessDiv").hide();
	chageDiv('block');
}
function qita() {
	var url = "SuspsomAction_xuanzhe.action";
	$("#showProcess").attr("src", url);
	$("#carzuo").show();
	$("#showProcess").show();
	$("#submitProcessDiv").hide();
	chageDiv('block');
}
function showTuZhi() {
	//var url = "show3Dfile.jsp?filePath=upload/file/processTz/+month+/+fileName+";
	var url = "<%=basePath%>show3Dfile.jsp?filePath=upload/file/processTz/2016-03/20160309111938_0.3dxml";
	$("#showProcess").attr("src", url);
	$("#carzuo").show();
	$("#showProcess").show();
	$("#submitProcessDiv").hide();
	chageDiv('block');
}

function lightOnOff(id, zl_id, ipAddress, obj) {//id一体机id zl_id开关指令 ip ipAddress一体机iP
	$.ajax( {
		url : "OneLightAction_openGongWeiLight.action",
		type : 'post',
		dataType : 'json',
		cache : false,//防止数据缓存
		data : {
			"id" : id,
			"oc_id" : zl_id,
			"ipAddress" : ipAddress
		},
		success : function(data) {
			if (data == null) {
				alert("操作失败！");
			} else {
				if (data.lightStatus == "关闭") {
					$(obj).attr("src", "images/Loff.png");
				} else {
					$(obj).attr("src", "images/Lon.png");
				}
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}

function isdj(id, obj, tag) {
	var bool = true;
	$.ajax( {
		type : "POST",
		url : "MachineDayYZSJAction_getMachinebyproessId.action",
		dataType : "json",
		async : false,
		data : {
			id : id
		},
		success : function(data) {
			if (data.success && data.message == '是') {
				var mdy = data.data;
				if (mdy == null || mdy.dj_status != '已点检') {
					$("#showZjProcess").attr(
							"src",
							"DJNRAction_getdjnrbyId.action?id="
									+ mdy.machine_id);
					$("#zjProcessDiv").show();
					$("#submitProcessDiv").hide();
					$("#collorProcessDiv").hide();
					chageDiv("block");
					//单独设置弹出层的高度
					var thisTopHeight = $(obj).offset().top - 100;
					if (thisTopHeight < 0) {
						thisTopHeight = 0;
					}
					$('#contentDiv').css( {
						'top' : thisTopHeight + 'px'
					});
					bool = false;
				}
			}
		}
	});
	return bool;
}

var sumNumber = 0;
//提交工序赋值 
function submitProcess(procardId, processId, number, sjstatus, obj,
		proLgbarcode) {
	sumNumber = number;
	if (isdj(processId, obj, "提交")) {
		$("#submitProcardId").val(procardId);
		$("#submitProId").val(processId);
		$("#subNumber").val(number);
		if (sjstatus == 'sj') {
			$("#subNumber").attr("readonly", "readonly");
		} else {
			$("#subNumber").removeAttr("readonly");
		}
		$("#proLgbarcode").val(proLgbarcode);
		$("#zjProcessDiv").hide();
		$("#submitProcessDiv").show();
		$("#collorProcessDiv").hide();
		getUnit("flUnit0");
		chageDiv("block");
		//单独设置弹出层的高度
		var thisTopHeight = $(obj).offset().top - 160;
		$('#contentDiv').css( {
			'top' : thisTopHeight + 'px'
		});

		$("#contentDiv").removeAttr("css");
		$("#contentDiv").css("left", "0");
		$("#contentDiv").addClass("contentDiv100");//关闭统一样式
		var contentDiv = document.getElementById("contentDiv");
		contentDiv.style.height = parseFloat("500");//更改透明层的高度
	}
}

//提交工序赋值 
function submitProcess23(procardId, processId, number, sjstatus, obj) {
	$("#submitProcardId").val(procardId);
	$("#submitProId").val(processId);
	$("#subNumber").val(number);
	if (sjstatus == 'sj') {
		$("#subNumber").attr("readonly", "readonly");
	} else {
		$("#subNumber").removeAttr("readonly");
	}
	$("#carzuo").hide();
	$("#showProcess").hide();
	$("#submitProcessDiv").show();
	chageDiv("block");
	//单独设置弹出层的高度
	var thisTopHeight = $(obj).offset().top;
	$('#contentDiv').css( {
		'top' : thisTopHeight + 'px'
	});
}
//提交工序
function submitForm() {
	$("#submitProce").hide();
	$("#showWait").html("<font color='green'>提交工序中....请等待!</font>");
	$
			.ajax( {
				type : "POST",
				url : "ProcardAction!submitProcess.action",
				dataType : "json",
				data : $("#submitProcess").serialize(),
				success : function(datas) {
					if (datas.message == "提交工序成功") {
						/*提交成功后推送led信息*/
						$.ajax( {
							type : "POST",
							url : "ProcardAction!sendLedMs.action",
							dataType : "json",
							data : {
								"processIds" : $("#submitProId").val()
							},
							success : function(msg) {
							}
						});
						var proLgbarcode = $("#proLgbarcode").val();
						var data = datas.data;
						var process = data.process;
						var procard = data.procard;
						$("#printMarkId").html(procard.markId);
						$("#printProName").html(procard.proName);
						$("#printYwMarkId").html(procard.ywMarkId);
						$("#printSelfCard").html(procard.selfCard);
						$("#printProcessNO").html(process.processNO);
						$("#printProcessName").html(process.processName);
						$("#printSubmmitCount").html(process.submmitCount);
						$("#printUsernames").html(process.usernames);
						$("#printSubmitDate").html(process.submitDate);
						$("#nextProcess").html(process.guding);
						$("#orderNum").html(procard.orderNumber);
						$("#printbanben").html(procard.banBenNumber);
						getQRCode(
								85,
								85,
								'<%=basePath%>ProcardAction!code.action?barcode=' + proLgbarcode,
								'erweima');
<%--						var barcode = document.getElementById('bar'), str = "<%=basePath%>ProcardAction!code.action?barcode="--%>
<%--								+ barcode, options = {--%>
<%--							format : "CODE128",--%>
<%--							displayValue : true,--%>
<%--							fontSize : 18,--%>
<%--							height : 100--%>
<%--						};--%>
<%--						JsBarcode(barcode, str, options);//原生--%>
						if (process.checkIdea != null)
							$("#cxtShow").html(
									"<img  width='150px' height='80px' src='/upload/file/processTz/"
											+ process.checkIdea + "' />");
						pagePrint('printgxDiv', 'view');
						window.location = "SuspsomAction_selectGongxu.action?ipAddress=${ipAddress}&tag=${tag}";
					} else {
						/*提交成功后推送led信息*/
						$.ajax( {
							type : "POST",
							url : "ProcardAction!sendLedMs.action",
							dataType : "json",
							data : {
								"processIds" : $("#submitProId").val()
							},
							success : function(msg) {
							}
						});
						$('#submitProce').show();
						$('#showWait').html("");
						alert(datas.message);
					}
				}
			});
}

//PMI自动提交工序
function pmiAutoSubPro(processId) {
	$("#submitProce").hide();
	$("#showWait").html("<font color='green'>自动提交工序中....请等待!</font>");
	$
			.ajax( {
				type : "POST",
				url : "ProcardAction!pmiAutosubPro.action",
				dataType : "json",
				data : {
					"process.id" : processId,
					"id" : "${procard.id}"
				},
				success : function(datas) {
					if (datas.message == "提交工序成功") {
						var data = datas.data;
						var process = data.process;
						var procard = data.procard;
						$("#printMarkId").html(procard.markId);
						$("#printSelfCard").html(procard.selfCard);
						$("#printProcessNO").html(process.processNO);
						$("#printProcessName").html(process.processName);
						$("#printSubmmitCount").html(process.submmitCount);
						$("#printUsernames").html(process.usernames);
						$("#printSubmitDate").html(process.submitDate);
						pagePrint('printgxDiv', 'view');
						window.location = "ProcardAction!findProcardByRunCard2.action?id=${id}&pageStatus=${pageStatus}";
					} else {
						$('#submitProce').show();
						$('#showWait').html("");
						alert(datas.message);
					}
				}
			});
}
var cardNumber = "";
function enter() {
	var keyCode = window.event.keyCode;
	if (keyCode < 48 || keyCode > 57) {
		if (keyCode == 13 && cardNumber.length == 10) {
			denglu(cardNumber);
			cardNumber = "";
			return;
		} else {
			cardNumber = "";
			event.returnvalue = false;
			return;
		}
	}
	if (keyCode == 48) {
		keyCode = 0;
	} else if (keyCode == 49) {
		keyCode = 1;
	} else if (keyCode == 50) {
		keyCode = 2;
	} else if (keyCode == 51) {
		keyCode = 3;
	} else if (keyCode == 52) {
		keyCode = 4;
	} else if (keyCode == 53) {
		keyCode = 5;
	} else if (keyCode == 54) {
		keyCode = 6;
	} else if (keyCode == 55) {
		keyCode = 7;
	} else if (keyCode == 56) {
		keyCode = 8;
	} else if (keyCode == 57) {
		keyCode = 9;
	}
	cardNumber = cardNumber + keyCode;
}

var fuliaoSize = 1;
var maxFuLiaoIndex = fuliaoSize - 1;//辅料最大下标
function addFuliaoLine() {
	maxFuLiaoIndex++;
	var html = "<tr id='fuliaoTr"
			+ maxFuLiaoIndex
			+ "'><td style='border-top: 0px;border-left: 0px' align='center'>"
			+ "<SELECT id='type"
			+ maxFuLiaoIndex
			+ "' name='process.fuliaoList["
			+ maxFuLiaoIndex
			+ "].type'><option>请选择</option><option >备件</option><option >金属五交材料</option><option>工具</option><option>办公用品</option><option>杂品</option><option>金属五交</option><option>工装</option><option>五金</option><option>包装物</option></SELECT> </td>"
			+ "<td style='border-top: 0px;border-left: 0px' align='center'> "
			+ "<SELECT id='type"
			+ maxFuLiaoIndex
			+ "' name='process.fuliaoList["
			+ maxFuLiaoIndex
			+ "].name'><option>焊丝</option><option >气体</option><option >手套</option><option>砂轮打磨片</option><option>导电嘴</option><option>润滑油</option>" 
			+"<option>油漆性记号笔</option><option>防护玻璃片</option></SELECT> </td>"
			+ "<td style='border-top: 0px;border-left: 0px' align='center'><input id='specification"
			+ maxFuLiaoIndex
			+ "' name='process.fuliaoList["
			+ maxFuLiaoIndex
			+ "].specification'> </td>"
			+ "<td style='border-top: 0px;border-left: 0px' align='center'><input id='unit"
			+ maxFuLiaoIndex
			+ "' name='process.fuliaoList["
			+ maxFuLiaoIndex
			+ "].outCount'> </td>"
			+"<td style='border-top: 0px;border-left: 0px' align='center'><select id='flUnit"+maxFuLiaoIndex+"' name='process.fuliaoList["
			+ maxFuLiaoIndex
			+ "].unit'></select></td>"
			+ "<td style='border-top: 0px;border-left: 0px;border-right: 0px' align='center'><input type='button' value='删除' onclick='deleteFuliaoLine("
			+ maxFuLiaoIndex + ")'> </td>" + "</tr>";
	$("#fuliaoTb>tbody>tr").eq(fuliaoSize).after(html);
	fuliaoSize++;
	getUnit("flUnit"+maxFuLiaoIndex);
}

function deleteFuliaoLine(index) {
	if (fuliaoSize == 1) {
		alert("至少一条辅料");
		return;
	}
	fuliaoSize--;
	$("#fuliaoTr" + index).remove();
}

//得到库别
function selectcq(){
	$("#warehouse").find("option[value='半成品库']").attr("selected",true);
	getcangqu();
}

//得到仓区;
function getcangqu(){
	var warehouse = $("#warehouse").val();
	if(warehouse!=""){
			$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_findwaListByNO.action",
		data : {
			wareHouseName:warehouse
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$("#goodHouseName").empty();
				$("#goodHouseName").append('<option value="">--请选择--</option>')
				$(data).each(function(){
						$("#goodHouseName").append('<option value='+this.goodHouseName+'>'+this.goodHouseName+'</option>');
					});
			}
				var tinyselect = $("#goodHouseName_td").children(".tinyselect");
						if (tinyselect[0] != null) {
							document.getElementById("goodHouseName_td").removeChild(
									tinyselect[0]);
						}
						$("#goodHouseName").tinyselect();
		}
	});
	}
}
<%----%>
//得到库位
function getkuwei(obj){
	var warehouse = $("#warehouse").val();
	if(warehouse != "" && obj!=null && obj.value != ""){
			$.ajax( {
		type : "POST",
		url : "WarehouseAreaAction_findwnListByNO.action",
		data : {
			wareHouseName:warehouse,
			cangqu:obj.value
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$("#goodsStorePosition").empty();
				$("#goodsStorePosition").append('<option value="">--请选择--</option>')
				$(data).each(function(){
						$("#goodsStorePosition").append('<option value='+this.number+'>'+this.number+'</option>');
					});
			}
			var tinyselect =  $("#goodsStorePosition_td").children(".tinyselect");
						if (tinyselect[0] != null) {
							document.getElementById("goodsStorePosition_td").removeChild(
									tinyselect[0]);
						}
			$("#goodsStorePosition").tinyselect();
		}
	});
	}
}

</script>
		<script src="<%=basePath%>css/pritureS/assets/js/bootstrap.min.js">
</script>
		<script src="<%=basePath%>css/pritureS/dist/viewer.js">
</script>
		<script src="<%=basePath%>css/pritureS/demo/js/main.js">
</script>


	</body>
</html>
