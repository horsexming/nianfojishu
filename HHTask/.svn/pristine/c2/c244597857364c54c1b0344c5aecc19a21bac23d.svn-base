<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE>
<html>
	<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script
 	src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"></script>
 <script type="text/javascript" 
 	src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"></script> 
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/button.css" />
<%@include file="/util/sonHead.jsp"%>
		
<style type="text/css">

.file {
    position: relative;
    display: inline-block;
    background: #D0EEFF;
    border: 1px solid #99D3F5;
    border-radius: 4px;
<!--    overflow: hidden;-->
    padding: 4px 12px;
    color: #1E88C7;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
}
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background: #AADFFD;
    border-color: #78C3F3;
    color: #004974;
    text-decoration: none;
}

</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在进行手动下单修改操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<h2>件号：${wgdSheet.markId } &nbsp;&nbsp; 批次:${wgdSheet.examineLot }&nbsp;&nbsp; ${wgdSheet.liaodate}日报检信息<s:if test='pageStatus == "xg"'>修改 </s:if><s:else>明细</s:else></h2>
				<form action="WaigouwaiweiPlanAction!updatewgdSheet.action" method="post" enctype="multipart/form-data" onsubmit="return check()" id="myform">
				<div id="printDiv">
					<table class="table" id="mytable">
						<tr>
							<td colspan="20" align="right">
								<b>样品编号:</b><input type="text" value="${wgdSheet.sampleNumber}" name="wgdSheet.sampleNumber" id="sampleNumber"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" value="推送提醒"  onclick="sendmsg(this.form)" class="button black"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								${wgdSheet.markId}
							</td>
							<th align="right">
								名称
							</th>
							<td>
								${wgdSheet.proName}
							</td>
							<th align="right">
								物料类别
							</th>
							<td>
								${wgdSheet.wgType}
							</td>
							<th align="right">
								供货属性
							</th>
							<td>
								${wgdSheet.kgliao}
							</td>
						</tr>
						<tr>
							<th align="right">
								采购订单编号
							</th>
							<td>
								${wgdSheet.cgOrderNum}
							</td>
							<th align="right">
								送货单号
							</th>
							<td>
								${wgdSheet.shOrderNum}
							</td>
							<th align="right">
								版本
							</th>
							<td>
								${wgdSheet.banbenNumber}
							</td>
							<th align="right">
								图号
							</th>
							<td>
								${wgdSheet.tuhao}
							</td>
						</tr>
						<tr>
							<th align="right">
								供应商名称
							</th>
							<td>
								${wgdSheet.gysName}
							</td>
							<th align="right">
								材质
							</th>
							<td>
								${wgdSheet.caizi}
							</td>
							<th align="right">
								添加人
							</th>
							<td>
								${wgdSheet.addUser}
							</td>
							<th align="right">
								添加时间
							</th>
							<td>
								${wgdSheet.addTime}
							</td>
						</tr>
						<tr>
							<th align="right">
								来料数量
							</th>
							<td>
								${wgdSheet.llNumber}
							</td>
							<th align="right">
								初检人姓名
							</th>
							<td>
								${wgdSheet.cjUser}
							</td>
							<th align="right">
								检验批次
							</th>
							<td>
								${wgdSheet.examineLot}
							</td>
							<th align="right">
								来料报检日期
							</th>
							<td>
								${wgdSheet.liaodate}
							</td>
						</tr>
						<tr>
							<th align="right">
								抽检数量
							</th>
							<td>
								${wgdSheet.cjNumber}
							</td>
							<th align="right">
								抽检合格数量
							</th>
							<td>
								${wgdSheet.cjhgNumber}
							</td>
							<th align="right">
								抽检不合格数量
							</th>
							<td>
								${wgdSheet.cjbhgNumber}
							</td>
							<th align="right">
								抽检时间
							</th>
							<td>
								${wgdSheet.cjTime}
							</td>
						</tr>
						<tr>
							<th align="right">
								终检合格数量
							</th>
							<td>
								${wgdSheet.zjhgNumber}
							</td>
							<th align="right">
								终检不合格数量
							</th>
							<td>
								${wgdSheet.zjbhgNumber}
							</td>
							<th align="right">
								最终判定
							</th>
							<td>
								${wgdSheet.zzpd}
							</td>
							<th align="right">
								终检人
							</th>
							<td>
								${wgdSheet.zjUser}
							</td>
						</tr>
						<tr>
							<th colspan="2">
								终检时间
							</th>
							<td colspan="2">
								${wgdSheet.zjTime}
							</td>
							<th colspan="2">
								环保号
							</th>
							<td colspan="2">
								<s:if test="wgdSheet.hbNumber == null || wgdSheet.hbNumber == '' ">
									<input type="text" name="wgdSheet.hbNumber" />
								</s:if>
								<s:else>
									${wgdSheet.hbNumber}
								</s:else>
							</td>
						</tr>
						<tr>
							<th>可靠性测试</th>
							<th><input type="button" value="添加可靠性测试" onclick="showReliabilityTest(${wgdSheet.id})"></th>
							<td></td>
						</tr>
						<tr>
							<th align="right">
								不锈钢材质分析
							</th>
							<td colspan="4">
								<textarea rows="2" style="width: 100%"  name="wgdSheet.bxgczfx">
									${wgdSheet.bxgczfx}
								</textarea>
							</td>
							<th align="right">上传不锈钢材质分析</th>
							<td colspan="2">
<%--								<a class="file" href="javascript:;">选择文件--%>
									<input type="file" name="attachment"  id="bxgczfxfile">
<%--								</a>--%>
								<s:if test='wgdSheet.bxgczfxfile!=null && wgdSheet.bxgczfxfile!=""'>
<%--									<input  class="button blue" type="button" value="预览" onclick="window.location.href='<%=basePath%>/upload/file/WaigouDailySheet/${wgdSheet.bxgczfxfile}'" />--%>
									<input  class="button blue" type="button" value="预览" onclick="window.location.href='FileViewAction.action?FilePath=/upload/file/WaigouDailySheet/${wgdSheet.bxgczfxfile}'" />
								</s:if>
							</td>
						</tr>
						<tr>
							<th align="right">
								ROHS测试分析
							</th>
							<td colspan="4">
								<textarea rows="2" style="width: 100%" name="wgdSheet.csROHS">
									${wgdSheet.csROHS}
								</textarea>
							</td>
							<th align="right">上传ROHS测试分析</th>
							<td colspan="2">
<%--								<a class="file" href="javascript:;">选择文件--%>
									<input type="file" name="attachment" id="csROHSfile">
<%--								</a>--%>
								<s:if test='wgdSheet.csROHSfile!=null && wgdSheet.csROHSfile!=""'>
<%--									<input class="button black"  type="button" value="预览" onclick="window.location.href='<%=basePath%>/upload/file/WaigouDailySheet/${wgdSheet.csROHSfile}'" />--%>
									<input class="button black"  type="button" value="预览" onclick="window.location.href='FileViewAction.action?FilePath=/upload/file/WaigouDailySheet/${wgdSheet.csROHSfile}'" />
								</s:if>
							</td>
						</tr>
						<tr>
							<th align="right">
								盐雾实验结论
							</th>
							<td colspan="4">
								<textarea rows="2" style="width: 100%"  name="wgdSheet.saltspraytest">
									${wgdSheet.saltspraytest}
								</textarea>
							</td>
							<th align="right">上传盐雾实验结论</th>
							<td colspan="2">
<%--								<a class="file" href="javascript:;">选择文件--%>
								<input type="file" name="attachment" id="saltspraytestfile">
<%--								</a>--%>
								<s:if test='wgdSheet.saltspraytestfile!=null && wgdSheet.saltspraytestfile!=""'>
<%--										<input class="button black"  type="button" value="预览" onclick="window.location.href='<%=basePath%>/upload/file/WaigouDailySheet/${wgdSheet.saltspraytestfile}'" />--%>
										<input class="button black"  type="button" value="预览" onclick="window.location.href='FileViewAction.action?FilePath=/upload/file/WaigouDailySheet/${wgdSheet.saltspraytestfile}'" />
								</s:if>
							</td>
						</tr>
						<tr>
							<th align="right">
								阻燃测试结论
							</th>
							<td colspan="4">
								<textarea rows="2" style="width: 100%"  name="wgdSheet.flammabilitytest">
									${wgdSheet.flammabilitytest}
								</textarea>
							</td>
							<th align="right">上传阻燃测试结论</th>
							<td colspan="2">
<%--							<a class="file" href="javascript:;">选择文件--%>
								<input type="file" name="attachment" id="flammabilitytestfile">
<%--							</a>--%>
								<s:if test='wgdSheet.flammabilitytestfile!=null && wgdSheet.flammabilitytestfile!=""'>
<%--									<input  class="button black" type="button" value="预览" onclick="window.location.href='<%=basePath%>/upload/file/WaigouDailySheet/${wgdSheet.flammabilitytestfile}'" />--%>
									<input  class="button black" type="button" value="预览" onclick="window.location.href='FileViewAction.action?FilePath=/upload/file/WaigouDailySheet/${wgdSheet.flammabilitytestfile}'" />
								</s:if>
							</td>
						</tr>
						<tr>
							<th align="right">
								检验不良描述
							</th>
							<td colspan="7">
								<textarea rows="2" style="width: 100%" name="wgdSheet.bldescribe">
									${ wgdSheet.bldescribe}
								</textarea>
							</td>
						</tr>
						<tr>
							<th align="right">
								备注
							</th>
							<td colspan="7">
								<textarea rows="2" style="width: 100%" name="wgdSheet.reamk">
									${wgdSheet.reamk}
								</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="9">
								<table class="table" >
									<tr bgcolor="#c0dcf2" height="50px">
										<th>
											需检项
										</th>
										<th>
											质量特征
										</th>
										<th>
											检查方法
										</th>
										<th>
											检查结果
										</th>
									</tr>
									<s:iterator value="list" id="pageList" status="pagestatus">
									<tr>
										<td>${pageList.scope.content}</td>
										<td>${pageList.scope.zltz}</td>
										<td>${pageList.scope.jcff}</td>
										<td>${pageList.content}</td>
									</tr>
									</s:iterator>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<div style="display: none;">
				
				</div>
					<s:if test='pageStatus == "xg"'>
						<input type="hidden"  value="${wgdSheet.id}" name="wgdSheet.id"/>
						<input type="submit" value="修改" onclick="todisabled(this)" class="button black">
					</s:if>
					<s:else>
						<input type="button" value="打印" onclick="printRs()" class="button black" />
					</s:else>
				</form>
				
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<SCRIPT type="text/javascript">
	function printRs(){
		pagePrint('printDiv');
	}
	function check(){
		var attachments =	document.getElementsByName("attachment");
		var zdnames = ""; 
		for(var i=0;i<attachments.length;i++){
			if(attachments[i].value!="" && attachments[i].id!="" ){
				zdnames+=","+attachments[i].id;
			}
		}
		var zdnamesArray = null;
		if(zdnames!="" && zdnames.length>1){
			zdnames = zdnames.substring(1);
			zdnamesArray = zdnames.split(",");
		}
		
		if(zdnamesArray!=null && zdnamesArray.length>0){
			var str = '<div style="display: none;">'
			for(var i=0;i<zdnamesArray.length;i++){
				str+='<input type="hidden" value='+zdnamesArray[i]+'  name="fileType" />'
			}
			$("#myform").append(str);
		}
		
	}
function sendmsg(obj){
	var sampleNumber = $("#sampleNumber").val();
	if(sampleNumber !=''){
		$(obj).attr('action','WaigouwaiweiPlanAction!sendmsg0.action');
		$(obj).submit();
	}else{
		alert('请填写样品编号!~')
	}
	
}

function showReliabilityTest(id){
// 	document.getElementById("xiugaiIframe").src = 
	location.href="ReliabilityTestAction!toAddRTS.action?dsId="+ id;
		
// 	chageDiv('block');
// 	$("#xiugaiIframe").show();
}
	</SCRIPT>
	</body>
</html>
