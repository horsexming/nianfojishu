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
		<style type="text/css">
		
			.operatingDiv {
		height: 900px;
	}
		</style>
		<style type="text/css">
.emLrZG {
	height: 18px;
	color: #fff;
	text-align: center;
	font-size: 12px;
	padding: 1px 1px;
	line-height: 17px;
}

.hlcmcS {
	background: #f7f7f7;
	position: fixed;
	bottom: 0px;
	right: 0px;
	height: 60px;
	z-index: 100;
	padding: 20px 20px 0 20px;
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	overflow: hidden;
	-webkit-transition: all .2 ease;
	transition: all .2s ease;
	-webkit-transition: all .2s ease;
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
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">发票明细查看</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" class="operatingDiv" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
					请选择票据
				</h3><h4><a href="FundApplyAction_toAddInvoce.action">没有票据？点击前往添加票据</a></h4>
				<form action="FundApplyAction_findInvoceCheckXuan.action?pagestatus=${pagestatus}" method="post">
					<table class="table">
						<tr style="width: 100%">
							<th align="center" style="width: 25%">
								销售方名称：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="invoiceCheck.xfmc" />
							</td>
							<th align="center" style="width: 25%">
								发票号码：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="invoiceCheck.fphm" />
							</td>
						</tr>
						<tr style="width: 100%">
							<th align="center" style="width: 25%">
								开票日期：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="invoiceCheck.kprq" />
							</td>
							<th align="center" style="width: 25%">
								添加人：
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="invoiceCheck.addName" />
							</td>
						</tr>
						<tr align="center">
							<td align="center" colspan="4">
								<input type="submit" value="查询"
									style="width: 100px; height: 25px;" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table" style="border-collapse: collapse;">
					<tr >
						<td colspan="10">
							<input type="button" value="确定" class="input" onclick="selectfapiao1()"/>
						</td>
					</tr>
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号<br/>
							全选<input type="checkbox" onclick="chageAllCheck(this)">
						</td>
						<td align="center">
							发票号码
						</td>
						<td align="center">
							发票代码
						</td>
						<td align="center">
							开票日期
						</td>
						<td align="center">
							销售方名称
						</td>
						<td align="center">
							价税合计
						</td>
						<td align="center">
							录入人
						</td>
						<td align="center">
							录入时间
						</td>
						<td align="center">
							发票类型
						</td>
						<td align="center">
							附件
						</td>
					</tr>
					<s:iterator value="invoiceCheckList1" id="toolCabines"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td align="right">
							<s:property value="#pageStatus.index+1" />
							<s:if
								test="#toolCabines.file_path!=null && #toolCabines.file_path!=''">
							<input type="checkbox" name="ids" value="${toolCabines.fphm}"/></s:if>
						</td>
						<td align="center">
							${toolCabines.fphm}
						</td>
						<td align="center">
							${toolCabines.fpdm}
						</td>
						<td align="center" style="width: 80px;">
							${toolCabines.kprq}
						</td>
						<td align="left" style="width: 300px;">
							${toolCabines.xfmc}
						</td>
						<td align="center">
						<s:if
								test="#toolCabines.file_path!=null && #toolCabines.file_path!=''">
							<input type="text" name="jshj" value="${toolCabines.jshj}" readonly="readonly"/></s:if>
							<s:else>${toolCabines.jshj}</s:else>
						</td>
						<td align="center">
							${toolCabines.addName}
						</td>
						<td align="center">
							${toolCabines.addTime}
						</td>
						<td align="center" colspan="1">
<%--							<a href="FundApplyAction_toSelInvoce.action?id=${toolCabines.id}&pagestatus=${pagestatus}">明细查看</a>--%>
							<s:if test='#toolCabines.fpzlShow=="专票"'>
								<input type="button" style="background-color: rgb(247, 163, 92);color: #fff;width: 54px;height: 25px;" value="${toolCabines.fpzlShow}" onclick="select1('${toolCabines.id}')" />
							</s:if>
							<s:elseif test='#toolCabines.fpzlShow=="普票"'>
								<input type="button" style="background-color: rgb(52, 76, 124);color: #fff;width: 54px;height: 25px;" value="${toolCabines.fpzlShow}" onclick="select1('${toolCabines.id}')" />
							</s:elseif>
							<s:elseif test='#toolCabines.fpzlShow=="电票"'>
								<input type="button" style="background-color: rgb(124, 181, 236);color: #fff;width: 54px;height: 25px;" value="${toolCabines.fpzlShow}" onclick="select1('${toolCabines.id}')" />
							</s:elseif>
							<s:elseif test='#toolCabines.fpzlShow=="其他"'>
								<input type="button" style="background-color: #108EE9; color: #fff;width: 54px;height: 25px;" value="${toolCabines.fpzlShow}" onclick="select1('${toolCabines.id}')" />
							</s:elseif>
							<s:elseif test='#toolCabines.fpzlShow=="定额发票"'>
								<input type="button"
									style="background-color: #108EE9; color: #fff; width: 75px; height: 25px;"
									value="${toolCabines.fpzlShow}"
									onclick="select1('${toolCabines.id}')" />
							</s:elseif>
							<s:else>
								<input type="button" style="background-color: rgb(52, 76, 124);color: #fff;width: 54px;height: 25px;" value="普票" onclick="select1('${toolCabines.id}')" />
							</s:else>
						</td>
						<td align="left">
							<s:if
								test="#toolCabines.file_path!=null && #toolCabines.file_path!=''">
								<input type="button" value="附件查看"
									onclick="showFile('${toolCabines.file_path}')"
									class="ant-btn ant-btn-primary ant-btn-lg" />
							</s:if>
							<s:else><input type="button" value="上传附件"
									onclick="uploadFile(${toolCabines.id})"
									class="ant-btn ant-btn-primary ant-btn-lg" />
									</s:else>
						</td>
					</s:iterator>
					<tr >
						<td colspan="10">
							<input type="button" value="确定" class="input" onclick="selectfapiao1()"/>
						</td>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
<%--function toadd(){--%>
<%--	window.location.href = "FundApplyAction_toAddInvoce.action?pagestatus=${pagestatus}";--%>
function select1(id) {
	var url = "<%=request.getContextPath()%>/FundApplyAction_toSelInvoce.action?pagestatus=${pagestatus}&id="+id;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
//数组去重
Array.prototype.unique3 = function(){
 var res = [];
 var json = {};
 for(var i = 0; i < this.length; i++){
  if(!json[this[i]]){
   res.push(this[i]);
   json[this[i]] = 1;
  }
 }
 return res;
}
function selectfapiao1() {
	var ids= "";
	var jshj= 0;
	var invalue = document.getElementsByName("ids");
	var jshjvalue = document.getElementsByName("jshj");
	for (i=0;i<invalue.length;i++){
		if(invalue[i].checked == true) {
		   	ids+=invalue[i].value+",";
		   	jshj+=parseFloat(jshjvalue[i].value);
		}
	}
	if(ids == ""){
		alert("请选择发票");
		return false;
	}
	
	var fapiaohao = '${fapiaohao}';
	var fapiao = ids;
	if(fapiaohao!=null&&fapiaohao!=''){
		fapiao = fapiaohao+ids;
	}
	var arr = fapiao.split(",");
	parent.$("#invoiceNum").val(arr.unique3());
	
	var fapiaohao2 = '${bwtCompany}';
	var fapiao2 = ids;
	if(fapiaohao2!=null&&fapiaohao2!=''){
		fapiao2 = fapiaohao2+ids;
	}
	var arr2 = fapiao2.split(",");
	parent.$("#invoiceNumd${wwNumber}").val(arr2.unique3());
	
	var oldjshj=parseFloat( parent.$("#h${wwNumber}").val());
	if(oldjshj!=null&&oldjshj>0){
		jshj+=oldjshj;
	}
	parent.$("#h${wwNumber}").val(jshj.toFixed(2));
	parent.comparecount(0);
	parent.hejiJine(0);
	
	parent.$("#attachmentsCount").val(arr.length-1);
	parent.$("#nofj").val("nofj");
	parent.$("#fj").hide();
	
	parent.chageDiv('none');
}

function uploadFile(id) {
	var url = "${pageContext.request.contextPath}/System/payment1/invoiceCheck_uploadFile.jsp?pagestatus=${pagestatus}&id="
			+ id;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}

function showFile(file) {
	var url = "${pageContext.request.contextPath}/FileViewAction.action?FilePath=/upload/file/invoiceChe/"
			+ file;
	$("#showProcess").attr("src", url);
	$("#showProcess").css("height", "500px");
	chageDiv('block');
}

</script>
	</body>
</html>
