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
<style type="text/css">
.dhlabel{
border-top:1px solid #000;
border-bottom: 1px solid #000;
border-left: 1px solid #000;
border-right: 1px solid #000;
margin-left: 5px;
margin-right: 5px;
padding: 3px 5px;
white-space: nowrap;
}
#fullbg1 {
	background-color: gray;
	left: 0;
	opacity: 0.5;
	position: absolute;
	top: 0;
	z-index: 3;
	filter: alpha(opacity =     50);
	-moz-opacity: 0.5;
	-khtml-opacity: 0.5;
}

#dialog1 {
	background-color: #fff;
	border: 5px solid rgba(0, 0, 0, 0.4);
	left: 50%;
	margin: -200px 0 0 -200px;
	padding: 1px;
	position: fixed !important; /* 浮动对话框 */
	position: absolute;
	top: 45%;
	width: 400px;
	height: 100px;
	z-index: 5;
	border-radius: 5px;
	display: none;
}

#xiugaiIframe1 {
	background-color: #fff;
	height: 75px;
	line-height: 24px;
	width: 400px;
}
</STYLE>
		<script type="text/javascript">
$(function() {
	var pageStatus = "${pageStatus}";
	if (pageStatus == "zjl" || pageStatus == "sc") {

		var size = parseFloat("${size}");
		var manage = $("#manage");
		var laborcostLabel = $("#laborcostLabel");
		var fenpeiRateLabel = $("#fenpeiRateLabel");
		if (pageStatus != "" && size > 0) {
			for ( var i = 0; i < size; i++) {
				$("#manage" + i).show();
				//市场,隐藏可调系数
				if (pageStatus == "sc") {
					$("#fenpeiRateLabel" + i).hide();
				}
			}
		}
	}
});

function update(obj, index, id, name, oldValue) {
	var laborcost = $("#laborcost" + index).val();
	var fenpeiRate = $("#fenpeiRate" + index).val();
	if (laborcost == "") {
		laborcost = 0;
	}
	if (fenpeiRate == "") {
		fenpeiRate = 0;
	}
	//判断值是否相同
	if (parseFloat(obj.value) == parseFloat(oldValue)) {
		$("#showMessage").html("");
		return false;
	}

	if (fenpeiRate > 1) {
		$("#showMessage").html("可调系数不能大于1,请重新填写!");
		$("#fenpeiRate" + index).select();
		return false;
	}

	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!updatelf.action",
		data : {
			id : id,
			laborcost : laborcost,
			fenpeiRate : fenpeiRate
		},
		dataType : "json",
		success : function(msg) {
			if (msg.success == true) {
				$("#showMessage").html(name + "更改成功");
			} else {
				$("#showMessage").html(name + "更改失败,请重新更改!");

			}
		}
	});
}
</script>

	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 90%; display: none;"
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
					<iframe id="showProcardDetail" src="" marginwidth="0"
						marginheight="0" hspace="0" vspace="0" frameborder="0"
						scrolling="yes"
						style="width: 98%; height: 900px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">   
			<div id="msg_div" style="color: red"></div>
			<div align="center" style="border: 1px solid #00000;">
				<form id="form" action="ProcardTemplateAction!findAllProcardTem.action"
					method="post">
					<!-- 只查询第一层
					<input type="hidden" name="procardTemplate.belongLayer" value="1" /> -->
					<input type="hidden" name="pageStatus" value="${pageStatus}" />
					<table class="table">
						<tr>
							<th colspan="5">
								流水单模板管理(Single template water management)
							</th>
						</tr>
						<tr>
							<th>
								件号(Part No.):
							</th>
							<td>
								<input name="procardTemplate.markId"
									value="${procardTemplate.markId}" />
							</td>
							<th>
								名称(Name):
							</th>
							<td>
								<input name="procardTemplate.proName"
									value="${procardTemplate.proName}" />
							</td>
						</tr>
						<tr>
							<th>
								卡片类型(Card Type):
							</th>
							<td>
								<select name="procardTemplate.procardStyle"
									style="width: 155px;">
									<option>
										${procardTemplate.procardStyle}
									</option>
									<option></option>
									<option>
										总成
									</option>
									<option>
										组合
									</option>
									<option>
										外购
									</option>
									<option>
										自制
									</option>
									<option>
										待定
									</option>
								</select>
							</td>
							<th>
								产品类型(Product Type):
							</th>
							<td>
								<select name="procardTemplate.productStyle"
									style="width: 155px;">
									<s:if test="pageStatus=='view'">
										<option></option>
										<option value="批产">
											批产
										</option>
										<option value="试制">
											试制
										</option>
									</s:if>
									<s:elseif test="pageStatus=='sop'">
										<option value="试制">
											试制
										</option>
									</s:elseif>
									<s:else>
										<option value="批产">
											批产
										</option>
									</s:else>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								图号(other Name):
							</th>
							<td>
								<input name="procardTemplate.tuhao"
									value="${procardTemplate.tuhao}" />
							</td>
							<th>
								原材料图号(other name):
							</th>
							<td>
								<input name="procardTemplate.ytuhao"
									value="${procardTemplate.ytuhao}" />
							</td>
						</tr>
						<tr>
							<th>
								原材料牌号:
							</th>
							<td>
								<input name="procardTemplate.trademark"
									value="${procardTemplate.trademark}" />
							</td>
							<th>
								原材料规格:
							</th>
							<td>
								<input name="procardTemplate.specification"
									value="${procardTemplate.specification}" />
							</td>
						</tr>
						<tr>
							<th>
								业务件号:
							</th>
							<td>
								<input name="procardTemplate.ywMarkId"
									value="${procardTemplate.ywMarkId}" />
							</td>
							<th>
							</th>
							<td>
							</td>
						</tr>
						<tr>
							<th colspan="6">
								<input type="hidden" value="${tag}" name="tag">
								<input type="submit" value="查询" class="input" />
								<input type="reset" value="清空" class="input" />
								<s:if test="tag!='jjbom'">
									<input type="button" value="导入BOM" class="input"
									onclick="showDaoRuDiv()">
									<input type="button" value="导出BOM" class="input"
										onclick="exportBom();todisabledone(this)" data="downData">
								</s:if>
								
							</th>
						</tr>
					</table>
				</form>
				<s:if test='tag =="jjbom"'>
					<form action="ProcardTemplateAction!daoruProcessJJMoney.action" method="post"
					enctype="multipart/form-data" id="uploadForm" >
					<table>
						<tr>
							<th>
								工序计件单价批量导入:
							</th>
							<th>
								<input type="file" name="gygf" id="file">
							</th>
							<td>
								<input type="button" onclick="showjindu()"  value="批量导入">
								<a href="<%=basePath %>/upload/file/download/ProcessPriceUpdateLog.xls">导入模版下载</a>
								<a href="FileViewAction.action?FilePath=/upload/file/download/ProcessPriceUpdateLog.xls&Refresh=true">/预览</a>
							</td>
						</tr>
					</table>

				</form>
				</s:if>
				<div id="rootTemplateDiv">
					<div id="showMessage"
						style="color: red; font-size: 14px; font-weight: bolder;">
					</div>
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
								<br />
								No.
							</th>
							<th align="center">
								件号/图号
								<br />
								Part No.
							</th>
							<th align="center">
								名称
								<br />
								Name
							</th>
							<th align="center">
								卡片类型
								<br />
								Card Type
							</th>
							<th align="center">
								产品类型
								<br />
								Product Type
							</th>
							<th align="center">
								总成件号/业务件号
								<br />
								Assembly Member
							</th>
							<th align="center">
								编制状态
								<br />
								edit status
							</th>
							<s:if test="pageStatus!=null">
								<th align="center">
									单件价格
									<br />
									Single price
								</th>
							</s:if>
							<th align="center">
									导入时间
									<br />
									Time
							</th>
							<th align="center">
									单产品占地面积 (m2)
									<br />
									Area
							</th>
							<th align="center">
									可叠放层数
									<br />
									CengNum
							</th>
							<th align="center" width="200">
									实际占用面积(m2)<br/>
									(单产品占地面积/可叠放层数)
									<br />
									CengNum
							</th>
							<th align="center">
								操作
								<br />
								Operation
							</th>
						</tr>
						<s:iterator value="procardTemplateList" id="pageProcardTem"
							status="pageindex">
							<s:if test="#pageindex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									>
							</s:if>
							<s:else>
								<tr align="center" >
							</s:else>
							<td>
								<s:property value="#pageindex.index+1" />
							</td>
							<td>
								
									${pageProcardTem.markId} 
								<s:if
									test="#pageProcardTem.tuhao!=null&&#pageProcardTem.tuhao!=''">
									<br />(${pageProcardTem.tuhao} )
								</s:if>
							</td>
							<td style="width: 180px;" align="left">
								${pageProcardTem.proName}
								
							</td>
							<td>
								${pageProcardTem.procardStyle}
							</td>
							<td>
								${pageProcardTem.productStyle}
							</td>
							<td  align="left">
								
									${pageProcardTem.rootMarkId}
									<s:if
										test="#pageProcardTem.ywMarkId!=null&&#pageProcardTem.ywMarkId!=''">
										<br />
										<font color="green" style="font-weight: bolder;">(${pageProcardTem.ywMarkId})</font>
									</s:if>
							</td>
							<td>
								${pageProcardTem.bzStatus}
							</td>
							<s:if test="pageStatus!=null">
								<td>
									${pageProcardTem.onePrice}
								</td>
							</s:if>
							<td>
									${pageProcardTem.daoruDate}
							</td>
							
							<td>  
								<input type="text" name="area${pageProcardTem.id}" id="area${pageProcardTem.id}" value="${pageProcardTem.procardArea}" disabled="disabled">
								
							</td>
							<td> 
								<input type="text" name="ceng${pageProcardTem.procardCengNum}" id="cengNum${pageProcardTem.id}" value="${pageProcardTem.procardCengNum}" disabled="disabled">
								
							</td>
							<td>  
								<input type="text" name="actualUseArea${pageProcardTem.actualUsedProcardArea}" id="actualUseArea${pageProcardTem.id}" value="<fmt:formatNumber value="${pageProcardTem.actualUsedProcardArea}" pattern="#0.00" />" disabled="disabled"><br/>
								
								
							</td>
							
							
							
							
							
							
							<td align="right">
								<div align="right">
									<div id="manage${pageindex.index}"
										style="float: left; font-weight: bolder; display: none; margin: 0xp; padding: 0px;">
										<label id="laborcostLabel${pageindex.index}">
											报价:
											<input id="laborcost${pageindex.index}" style="width: 60px;"
												value="${pageProcardTem.laborcost}"
												onblur="update(this,'${pageindex.index}',${pageProcardTem.id},'${pageProcardTem.markId}的报价','${pageProcardTem.laborcost}')" />
										</label>
										<label id="fenpeiRateLabel${pageindex.index}">
											可调系数:
											<input id="fenpeiRate${pageindex.index}" style="width: 60px;"
												value="${pageProcardTem.fenpeiRate}"
												onblur="update(this,'${pageindex.index}',${pageProcardTem.id},'${pageProcardTem.markId}的可调系数','${pageProcardTem.fenpeiRate}')" />
										</label>
										
										
										<a target="_showSz"
											href="<%=basePath%>System/SOP/produce/Bonus_Spreadsheet.jsp?id=${pageProcardTem.id}">试算</a>
									</div>
									
									<%--<s:if test="#pageProcardTem.procardStyle=='总成'">
										--%><div style="float: left; margin: 0xp; padding: 0px;">
											<input type="button" value="修改单产品占地面积" id="updateAreaBtn${pageProcardTem.id}" onclick="updateArea(${pageProcardTem.id})"/>
							
										</div>
										<div style="float: left; margin: 0xp; padding: 0px;display:none;" id="submitBtn${pageProcardTem.id}" >
											<input type="button" value="提交" id="updateAreaBtn${pageProcardTem.id}" onclick="submitUpdate(${pageProcardTem.id})"/>
										
										</div><%--
									</s:if>
									
									
									
									
									
								--%></div>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="14" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="11" align="center" style="color: red">
							</s:else>
							</td>
						</tr>
					</table>
					
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function toshowPro(id) {
	$("#showProcardDetail").attr("src",
			"ProcardTemplateAction!findCardTemForShow.action?id=" + id);
	chageDiv('block');
}
function showDaoRuDiv() {
	$("#showProcardDetail").attr("src",
			"procardTemplateGyAction_toDaoRuBom.action");
	chageDiv('block');
}
function exportBom(){
	document.getElementById('form').action = "ProcardTemplateAction!exportBom.action";
	document.getElementById('form').submit();
	document.getElementById('form').action = "ProcardTemplateAction!findAllProcardTem.action";
}
function updateBOM(parth, id,tag) {
	if(tag!=''){
		window.open(parth + "System/SOP/produce/Template_findProcardjjbom.jsp?id=" + id);
	}else{
		window.open(parth + "System/SOP/produce/Template_findProcard.jsp?id=" + id);	
	}
	
}
function exportExcel(id,obj,pageStatus) {
	$(obj).attr("disabled","disabled");
	window.location.href = "procardTemplateGyAction_daoChuBom.action?id=" + id+"&pageStatus="+pageStatus;
}
function showgongxu(parth, id) {
	window.open(parth + "System/SOP/produce/procard_showgongxu.jsp?id=" + id);
}
function showwbwgj(parth, id) {
	window.open(parth + "ProcardTemplateAction!findwbdProcessWgProcard.action?id=" + id);
}
function showjindu(){
	//弹出遮罩层开始
		$("body").append("<div id='fullbg1'></div>");
		$("body")
				.append(
						"<div id='dialog1' class='loginbox'>"
								+ "<iframe id='xiugaiIframe1' src='<%=basePath%>/System/SOP/produce/jindu.jsp' "
								+ "marginwidth='0' marginheight='0' hspace='0' vspace='0' "
								+ "frameborder='0' scrolling='yes'"
								+ " style='width: 100%;margin: 0px; padding: 0px;'>"
								+ "</iframe></div>")

		var sWidth, sHeight;
		//sWidth=document.body.offsetWidth;//得出当前屏幕的宽
		sWidth = document.body.clientWidth;//BODY对象宽度

		//sHeight=screen.height; //得到当前屏幕的高
		//sHeight=document.body.clientHeight;//BODY对象高度
		if (window.innerHeight && window.scrollMaxY) {
			sHeight = window.innerHeight + window.scrollMaxY;
		} else if (document.body.scrollHeight > document.body.offsetHeight) {
			sHeight = document.body.scrollHeight;
		} else {
			sHeight = document.body.offsetHeight;
		} //以上得到整个屏幕的高
		//		var bw = $("body").width();
		//		var bh = window.screen.availHeight;
		$("#fullbg1").css( {
			height : sHeight,
			width : sWidth,
			display : "block"
		});
		$("#dialog1").show();
		//弹出遮罩层结束
		  var formData = new FormData($( "#uploadForm" )[0])
			$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!daoruProcessJJMoney.action",
		 data: formData, 
		dataType : "json",
          cache: false,  
          contentType: false,  
          processData: false, 
		success : function(data) {
			if(data!=null){
				$("#msg_div").html(data);
				$("#dialog1").hide();
				$("#fullbg1").hide();
			}
		}
	})
}



//修改流水单占地面积
function updateArea(procardId){
	
	$("#area"+procardId).removeAttr("disabled");
	$("#cengNum"+procardId).removeAttr("disabled");
	
	
	$("#submitBtn"+procardId).css("display","block");
	$("#updateAreaBtn"+procardId).css("display","none");
	$("#actualUseArea"+procardId).val("");
}



//提交修改
function  submitUpdate(procardId){
	
	var cengObj=document.getElementById("cengNum"+procardId);
	var areaObj=document.getElementById("area"+procardId);
	
	var area=$("#area"+procardId).val();
	var ceng=$("#cengNum"+procardId).val();
	
	if(area==""){
		alert("单产品占地面积不能为空");
		$("#area"+procardId).html("");
		$("#area"+procardId).focus();
		return ;
	}else{
		var areaFlag=numyanzheng(areaObj,'');
		if(!areaFlag){
			$("#area"+procardId).html("");
			$("#area"+procardId).focus();
			return ;
		}
	}

	 if(ceng==""){
		alert("可叠放层数不能为空");
		$("#cengNum"+procardId).html("0");
		$("#cengNum"+procardId).focus();
		return ;
	}else{
		var cengFlag=numyanzheng(cengObj,'zhengshu');
		if(!cengFlag){
			$("#cengNum"+procardId).html("");
			$("#cengNum"+procardId).focus();
			return ;
		}
	}
	 //修改	
	 $.ajax( {
			type : "POST",
			url : "ProcardTemplateAction!ajaxUpdateProcardArea.action",
			data : {
				id:procardId,
				procardArea:area,
				procardCengNum:ceng
			},
			dataType : "json",
			success : function(data) {				
				$("#area"+procardId).attr("disabled","disabled");
				$("#cengNum"+procardId).attr("disabled","disabled");
				$("#submitBtn"+procardId).css("display","none");
				$("#updateAreaBtn"+procardId).css("display","block");
				var actualUseArea=area/ceng;
				var  str=data.split("!");
				$("#actualUseArea"+procardId).val(str[1]);
	 			alert(str[0]);				
			}
	});
}
</script>
	</body>
</html>
