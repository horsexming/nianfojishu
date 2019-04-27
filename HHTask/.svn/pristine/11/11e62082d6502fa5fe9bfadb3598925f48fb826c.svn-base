<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<%@include file="/util/sonHead.jsp"%>
		<style type="text/css">
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
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
				<div id="submitProcessDiv" style="display: none;">
					<table style="width: 100%; margin-top: ">
						<tr>
							<td>
								请填写来访人手机号:
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none');">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
						<form action="VisitAction_agreen.action?&taga=${taga}" onsubmit="return feikong()">
							<table class="table" style="width: 40%" align="center">
								<tr>
									<td align="center" colspan="2">
										<b>填写手机号</b>
									</td>
								</tr>
								<tr>
									<th align="center">
										<input id="visitId" type="hidden" name="visit.id"/>
										<input type="text" name="visit.visitsTel" id="visitsTel3"/>
									</th>
									<td align="center">
										<input type="submit" value="提交" style="width: 80px; height: 50px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="left">
			</div>
			<div align="center">
				<form action="VisitAction_tofind.action?taga=${taga}" method="post">
					<table width="99%">
						<tr>
							<td align="right">
								被访人:
								<br />
							</td>
							<td>
								<input type="text" style="width: 150px;"
									name="visit.shouFangName">
							</td>
							<td align="right">
								来访人:
								<br>
							</td>
							<td>
								<input type="text" style="width: 150px;" name="visit.visitsName">
							</td>
							<td align="right">
								车牌号:
								<br />
							</td>
							<td>
								<input type="text" style="width: 150px;"
									name="visit.visitsLic">
							</td>
						</tr>
						<tr>
							<td colspan="6" rowspan="2" align="center">
								<input type="submit" value="查询"
									style="width: 100px; height: 40px">
								<input type="reset" value="重置 "
									style="width: 100px; height: 40px">

							</td>
						</tr>
					</table>
				</form>
				<br>
				<center>
					<table width="99%">
						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<td>
								序号
							</td>
							<td>
								申请人
							</td>
							<td>
								来访人
							</td>
							<td>
								被访人
							</td>
							<td>
								来访人数
							</td>
							<td>
								来访人车牌号
							</td>
							<td>
								来访人手机号
							</td>
							<td>
								来访人公司
							</td>
							<td>
								来访当前有效凭证
							</td>
							<td>
								进出通道
							</td>
							<td>
								来访时间
							</td>
							<td>
								审批状态
							</td>
							<td>
								来访状态
							</td>
							<td>
								验证码
							</td>
							<td>
								操作
							</td>
							<s:iterator id="unpricetest" value="visitList" status="statussdf">
								<s:if test="#statussdf.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#statussdf.index+1" />
								</td>
								<td>
									${unpricetest.applyName}
								</td>
								<td>
									${unpricetest.visitsName}
								</td>
								<td>
									${unpricetest.shouFangName}
								</td>
								<td>
									${unpricetest.visitsPop}
								</td>
								<td>
									${unpricetest.visitsLic}
								</td>
								<td>
									${unpricetest.visitsTel}
								</td>
								<td>
									${unpricetest.visitsCom}
								</td>
								<td>
									${unpricetest.visitsCode}
								</td>
								<td>
									<s:if test="#unpricetest.verifycar=='是'.toString()">车行道</s:if>
									<s:elseif test="#unpricetest.verifycar=='否'.toString()">
										<font color="#666">人行道</font>
									</s:elseif>
								</td>
								<td>
									${unpricetest.visitstime}
								</td>
								<td>
									${unpricetest.visitStatus}
								</td>
								<td>
									<s:if test="#unpricetest.visit_laiStatus=='未进门'">
										<font color="#A9A9A9">未进门</font>
									</s:if>
									<s:elseif test="#unpricetest.visit_laiStatus=='已进门'">
										<font color="#000" style="font-weight: 600">已进门</font>
									</s:elseif>
									<s:elseif test="#unpricetest.visit_laiStatus=='已出门'">
										<font color="#808080">已出门</font>
									</s:elseif>
									<s:else>${unpricetest.visit_laiStatus}</s:else>
								</td>
								<td>
									${unpricetest.visitsAllCode}
								</td>
								<td>
									<s:if test="#unpricetest.epId!=null">
										<a
											href="CircuitRunAction_findAduitPage.action?id=${unpricetest.epId}">审批动态</a>
									</s:if>
									<s:if
										test="#unpricetest.visitStatus=='同意'.toString()&&#unpricetest.visit_laiStatus=='已进门'.toString()">
										<a
											href="VisitAction_backOut.action?visit.id=${unpricetest.id}&taga=${taga}">/<font
											style="color: red;">确认出门</font>
										</a>
									</s:if>
									<s:if
										test="#unpricetest.visit_laiStatus=='已出门'.toString()&&#unpricetest.verifyManner=='车牌'.toString()">
										<a
											onclick="telisnull('${unpricetest.id}','${taga}','${unpricetest.visitsTel}')">/<font
											style="color: #76d092">再次申请</font>
										</a>
									</s:if>
									<s:if test="#unpricetest.visit_laiStatus=='出门中'.toString()">
										<a href="VisitAction_backOut.action?visit.id=${unpricetest.id}&taga=${taga}" >/<font
											style="color: #7f007f">再次出门</font>
										</a>
									</s:if>
								</td>
							</s:iterator>
						</tr>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="15" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="15" align="center" style="color: red">
									${errorMessage}
								</td>
							</s:else>
						</tr>
					</table>
				</center>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
		function agren(infor){
	alert(infor);
	//$("#infornei").val(infor);
	//$("#submitProcessDiv").show();
	//chageDiv("block");
	//单独设置弹出层的高度
	//var thisTopHeight = $(obj).offset().top;
	//$('#contentDiv').css( {
	//	'top' : thisTopHeight + 'px'
	//});
}
		
function telisnull(id,taga,tel){
	var re = /^1\d{10}$/
	if(tel == null || tel == "" || !re.test(tel)){
		$("#visitId").val(id);
		$("#submitProcessDiv").show();
		chageDiv("block");
		//单独设置弹出层的高度 
		var thisTopHeight = $(obj).offset().top;
		$('#contentDiv').css( {
			'top' : thisTopHeight + 'px'
		});
	}else{
		window.location.href = "VisitAction_agreen.action?visit.id="+id+"&taga="+taga+"&visit.visitsTel="+tel;
	}
}
function feikong(){
	if (!validateText("visitsTel3", "来访人手机号")) {
			return false;
		}
	var re = /^1\d{10}$/
    if (!re.test($("#visitsTel3").val())) {
        alert("请输入正确的手机号");
        return false;
    }
}
function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
		</script>
	</body>
</html>
