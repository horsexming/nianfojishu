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
								您正在确认已付金额:
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none');reload();">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
						<form action="NoncorePayableAction!querenPay.action?tag=${tag}" method="post" onsubmit="return validate()">
							<input type="hidden" id="wuId" name="corePayable.id" />
							<table class="table" style="width: 40%" align="center">
								<tr>
									<th align="right">
										确认已付金额:
									</th>
									<th align="left">
										<input name="corePayable.realfukuanJIne" id="realfukuanJIne"/>
									</th>
								</tr>
								<tr>
									<th align="right">
										付款日期:
									</th>
									<th align="left">
										<input class="Wdate" name="corePayable.fukuaiDate" id="fukuaiDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
									</th>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<input type="submit" value="提交" style="width: 65px; height: 40px;" />
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					非主营业务应付管理
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							类型
						</td>
						<td align="center">
							收款单位
						</td>
						<td align="center">
							账期开始日期
						</td>
						<td align="center">
							账期结束日期
						</td>
						<td align="center">
							应付款金额
						</td>
						<td align="center">
							实付款金额
						</td>
						<td align="center">
							负责人
						</td>
						<td align="center">
							合同编号
						</td>
						<td align="center">
							付款日期
						</td>
						<th align="center">
							截止付款日
						</th>
						<td align="center">
							添加时间
						</td>
						<td align="center">
							审批状态
						</td>
						<td align="center" colspan="2">
							操作类型
						</td>
					</tr>
					<s:iterator value="corePayableList" id="samples" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${samples.subjectItem}
						</td>
						<td align="center">
							${samples.shoukuandanwei}
						</td>
						<td align="center">
							${samples.zhangqiStartDate}
						</td>
						<td align="center">
							${samples.zhangqiEndDate}
						</td>
						<td align="center">
							${samples.yingfukuanJIne}
						</td>
						<td align="center">
							${samples.realfukuanJIne}
						</td>
						<td align="center">
							${samples.fuzeren}
						</td>
						<td align="center">
							${samples.hetongbianhao}
						</td>
						<td align="center">
							${samples.fukuaiDate}
						</td>
						<td align="center">
							<font color="red">${samples.lateDate}</font>
						</td>
						<td align="center">
							${samples.saveTime}
						</td>
						<td align="center">
							${samples.status}
						</td>
						<td align="center" colspan="2">
							<s:if test="tag=='administratorse'">
								<a
									href="NoncorePayableAction!toupdate.action?corePayable.id=${samples.id}&tag=${tag}">修改/</a>
								<a onclick="return window.confirm('您将删除数据，是否继续?')"
									href="NoncorePayableAction!delete.action?id=${samples.id}&tag=${tag}">删除/</a>
							</s:if>
							<s:if test='#samples.status=="未审批"&&tag=="code"'>
								<a
									href="NoncorePayableAction!toupdate.action?corePayable.id=${samples.id}&tag=${tag}">修改/</a>
								<a onclick="return window.confirm('您将删除数据，是否继续?')"
									href="NoncorePayableAction!delete.action?id=${samples.id}&tag=${tag}">删除/</a>
							</s:if>
							<input type="button" value="下载附件" onclick="xiazai('${samples.fujian}')"/>
							<s:if test="#samples.epId!=null">
									<a href="CircuitRunAction_findAduitPage.action?id=${samples.epId}">/审批动态</a>
							</s:if>
<%--							<s:if test='#samples.status=="同意"'>--%>
<%--									<a href="FundApplyAction_toAddfundApply.action">/资金使用单</a>--%>
<%--									<a onclick="submitqueren('${samples.id}')">财务确认</a>--%>
<%--							</s:if>--%>
						</td>
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
							<td colspan="14" align="center" style="color: red">
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
		//下载合同
	function xiazai(url){
			//对中文进行加密
			//var fileName1 = encodeURI(encodeURI("${list.priceUrl}"));
			var fileName1 = url;
<%--			location.href="<%=request.getContextPath()%>/DownAction.action?fileName="+fileName1+"&directory=/upload/file/feiZY/";--%>
			location.href="<%=request.getContextPath()%>/FileViewAction.action?FilePath=/upload/file/feiZY/"+fileName1;
	}
				//财务确认付款 
function submitqueren(wuId) {
	$("#wuId").val(wuId);
	$("#submitProcessDiv").show();
	chageDiv("block");
	//单独设置弹出层的高度
	var thisTopHeight = $(obj).offset().top;
	$('#contentDiv').css( {
		'top' : thisTopHeight + 'px'
	});
}
function fukuaiDate(){
	if (!validateText("realfukuanJIne", "金额")) {
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
