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
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/pritureS/css/normalize.css" />
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/pritureS/css/default.css">
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/css/pritureS/assets/css/bootstrap.min.css">
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/css/pritureS/dist/viewer.css">
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/css/pritureS/css/main.css">
		<link type="text/css" rel="stylesheet"
			href="${pageContext.request.contextPath}/css/saved_resource">
		<link type="text/css" rel="stylesheet"
			href="${pageContext.request.contextPath}/css/cosmetic.css">

		<%@include file="/util/sonHead.jsp"%>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/viewer.js">
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
	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv" style="z-index: 2"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none; top: 20px;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
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
					<table style="width: 100%; margin-top: ">
						<tr>
							<td>
								您正在提交工序:
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none');reload();">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
						<form id="submitProcess">
							<input id="submitProId" type="hidden" name="process.id" />
							<input type="hidden" id="submitProcardId" name="id" />
							<table class="table" style="width: 40%" align="center">
								<tr>
									<td colspan="2" align="center">
										<b>提交工序</b>
									</td>
								</tr>
								<tr>
									<th>
										提交数量:
									</th>
									<th align="left">
										<input id="subNumber" name="process.submmitCount"
											maxsize="100" />
										最大可提交数量
									</th>
								</tr>
								<tr>
									<th>
										不合格数量:
									</th>
									<th align="left">
										<input name="process.breakCount" value="0" />
									</th>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<input id="submitProce" type="button" value="提交"
											onclick='submitForm()' style="width: 80px; height: 50px;" />
										<div id="showWait"></div>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 900px; margin: 0px; padding: 0px;"></iframe>
				</div>

			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div style="margin-bottom: 5px; margin-top: 5px;" align="right">
					<div
						style="float: left; margin-top: 9px; left: 100px; margin-left: 40px; font-size: 18px;">
						<b>工位：${gongwei}</b> 当前操作人：
						<s:if test="#session.Users.name!=null">
						${Users.name}&nbsp;&nbsp;&nbsp;&nbsp;
						</s:if>
						<s:else>
							<a href="javascript:;" onclick="denglu()">未登录</a>&nbsp;&nbsp;&nbsp;&nbsp;
						</s:else>
					</div>
					<div style="float: left; margin-top: 9px; right: 100px;">
						<s:if test="#session.Users.name!=null">
							<a href="javascript:;" onclick="lingqu()"
								style="font-size: 22px;">领取工序</a>&nbsp;&nbsp;
						</s:if>
					</div>
					<div id="lightId" style="float: left;">
						<s:if test="#session.Users.name!=null">
							<s:iterator value="listLight" id="light" status="pageIndex">
								<s:if test='#light.lightStatus=="关闭"'>
									<img height="40" width="30" src='images/Loff.png'
										style="margin-right: 10px;"
										onclick="lightOnOff('${light.id}','${light.lightOpen}','${ipAddress}',this)"
										title="${light.lightNum}" />
								</s:if>
								<s:else>
									<img style="margin-right: 10px;"
										onclick="lightOnOff('${light.id}','${light.lightOpen}','${ipAddress}',this)"
										height="40" width="30" src='images/Lon.png'
										title="${light.lightNum}" />
								</s:else>
							</s:iterator>
						</s:if>
					</div>
					<div
						style="float: right; margin-top: 9px; margin-right: 30px; font-size: 18px;">
						<s:if test="#session.Users.name!=null">
							<a href="logOff.jsp">[退出]</a>
						</s:if>
					</div>
					<div style="clear: both;"></div>
				</div>

				<div id="time-limit" class="time-limit w floor floor-current"
					style="width: 95%">
					<div class="m">
						<div class="mc">
							<s:if test="errorMessage!=null">
								<div style="font-size: 25px; color: red">
									${errorMessage}
								</div>
							</s:if>
							<ul class="limit-list">
								<s:iterator value="listAll" id="listNum" status="pageIndex">
									<li clstag="firsttype|keycount|mztm|g1" class="fore1"
										id="time_limit_1950166215" sku="1950166215">
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
																<img
																	src="<%=basePath%>upload/user/${usersNum.password.picture}"
																	height="60" width="60" title="${usersNum.name}"
																	style="border: solid 1px #000000; border-radius: 50%;"
																	onerror="this.src='images/man.jpg'" />
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
																			onclick="submitProcess('${processInfor.id}','1','sj',this)"
																			style="height: 30px;" />
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
																				onclick="submitProcess('${processInfor.procard.id}','${processInfor.id}','${processInfor.applyCount-processInfor.submmitCount-processInfor.breakCount}','',this)" />
																		</s:else>
																	</s:else>
																</s:if>
															</s:iterator>
														</th>
													</tr>
													<tr>
														<th>
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
															${processInfor.pmiType}
														</th>
													</tr>
												</table>
											</div>
											<div align="left" style="margin-top: 20px;">
												<div style="float: left;">
													<ul style="float: left;">
														<li>
															2D图纸：
														</li>
													</ul>
													<ul class="docs-pictures clearfix">
														<s:iterator value="#listNum.tuzhi" id="tzFile">
															<s:if test="#tzFile.type=='工艺规范'">
																<li>
																	<img
																		src="<%=path%>/upload/file/processTz/${tzFile.month}/${tzFile.fileName}"
																		style="width: 80px; height: 80px;" />
																</li>
															</s:if>
														</s:iterator>
													</ul>

												</div>
												<div style="float: right; margin-right: 100px;">
													<s:iterator value="#listNum.tuzhi" id="file3D">
														<s:if test="#file3D.type=='3D文件'">
															<td>
																<a target="_show3D"
																	href="show3Dfile.jsp?filePath=upload/file/processTz/${file3D.month}/${file3D.fileName}">3D图纸</a>&nbsp;&nbsp;
															</td>
														</s:if>
													</s:iterator>
													<%--<a target="_show3D"
														href="show3Dfile.jsp?filePath=/liu.3dxml">3D图纸</a>&nbsp;&nbsp;
												--%>
												</div>
												<div style="float: right; margin-right: 100px;">
													<s:iterator value="#listNum.tuzhi" id="modelfile">
														<s:if test="#modelfile.type=='3D模型'">
															<td>
																<a href="DownAction.action?fileName=${modelfile.fileName}&directory=/upload/file/processTz/${modelfile.month}/">3D模型</a>&nbsp;&nbsp;
															</td>
														</s:if>
													</s:iterator>
													<%--<a target="_show3D"
														href="show3Dfile.jsp?filePath=/liu.3dxml">3D图纸</a>&nbsp;&nbsp;
												--%>
												</div>
											</div>
										</div>
									</li>
								</s:iterator>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

		<script type="text/javascript">
function lingqu() {
	var url = "OneMachineAction_findProcardByCardGxNum.action";
	$("#showProcess").attr("src", url);
	$("#carzuo").show();
	$("#showProcess").show();
	$("#submitProcessDiv").hide();
	chageDiv('block');
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
function denglu() {
	var url = "SuspsomAction_denglu.action";
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
			if (data.lightStatus == "关闭") {
				$(obj).attr("src", "images/Loff.png");
				$(obj).click(function() {
					lightOnOff(id, data.lightOpen, ipAddress, obj);
				});
			} else {
				$(obj).attr("src", "images/Lon.png");
				$(obj).click(function() {
					lightOnOff(id, data.lightClose, ipAddress, obj);
				});
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}

//提交工序赋值 
function submitProcess(procardId, processId, number, sjstatus, obj) {
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
	$.ajax( {
		type : "POST",
		url : "ProcardAction!submitProcess.action",
		dataType : "json",
		data : $("#submitProcess").serialize(),
		success : function(datas) {
			if (datas.message == "提交工序成功") {
				location.reload(true);
			} else {
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
</script>
		<script src="<%=basePath%>css/pritureS/assets/js/jquery.min.js">
</script>
		<script src="<%=basePath%>css/pritureS/assets/js/bootstrap.min.js">
</script>
		<script src="<%=basePath%>css/pritureS/dist/viewer.js">
</script>
		<script src="<%=basePath%>css/pritureS/assets/js/main.js">
</script>
	</body>
</html>
