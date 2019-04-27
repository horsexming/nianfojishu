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
	<body onload="description1()">
		<%@include file="/util/sonTop.jsp"%>
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
							<span id="title">个人确认</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<table class="table">
						<tr>
							<td align="center">
								输入密码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="hidden" id="fid">
								<input type="password" id="password">
							</td>
						</tr>
						<tr>
							<td align="center">
								<input type="button" value="确认" onclick="sureSelf()"
									style="width: 60; height: 30;">
								<input type="button" value="取消" onclick="chageDiv('none');"
									style="width: 60; height: 30;">
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="FundApplyAction_findfundList.action" method="post">
					<table class="table">
						<tr>
							<th align="right">
								编号
							</th>
							<td>
								<input type="text" name="fundApply.number" />
							</td>
							<th align="right">
								申请人
							</th>
							<td>
								<input type="text" name="fundApply.approvalApplier" />
							</td>
						</tr>
						<tr>
							<th align="right">
								状态
							</th>
							<td>
								<input type="text" name="fundApply.zhifuoryufu" />
							</td>
							<th align="right">
								发票号
							</th>
							<td>
								<input type="text" name="fundApply.invoiceNum" />
							</td>
						</tr>
						<tr>
							<th align="right">
								创建时间从
							</th>
							<td>
								<input type="text" name="firstTime" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
								到
							</th>
							<td>
								<input type="text" name="endTime" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th align="right">
								收款单位
							</th>
							<td colspan="3">
								<input type="text" name="fundApply.relationclient" value="${fundApply.relationclient}"/>
							</td>
						</tr>
					</table>
					<input type="hidden" value="${pagestatus}" name="pagestatus" />
					<input type="submit" value="查询" class="input" />
				</form>
				<form method="post" action="FundApplyAction_weituo.action">
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							编号
						</th>
						<th>
							责任人
						</th>
						<th>
							收款单位
						</th>
						<th>
							创建人
						</th>
						<th>
							合计金额
						</th>
						<th>
							归还金额
							<br />
							(预付)
						</th>
						<th>
							类别
						</th>
						<th>
							支付方式
						</th>
						<th>
							是否增税发票
						</th>
						<th>
							发票号
						</th>
						<th>
							类型
						</th>
						<th>
							创建时间
						</th>
						<th>
							审批动态
						</th>
						<th>
							状态
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator id="pagelist" value="fundList" status="statussdf">
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
						<s:if test="#pagelist.haswt==null||#pagelist.haswt!='是'.toString()">
							<input type="checkbox" value="${pagelist.id}" name="ids"
								onchange="chageNum(this)">
							<s:property value="#statussdf.index+1" />
						</s:if>
						</td>
						<td>
							${pagelist.number}
							${pagelist.haswt}
						</td>
						<td>
							${pagelist.unitname}
						</td>
						<td>
							${pagelist.relationclient}
						</td>
						<td>
							${pagelist.approvalApplier}
						</td>
						<td>
							${pagelist.totalMoney}
						</td>
						<td>
							<s:if test="#pagelist.zhifuoryufu=='预付'">
						${pagelist.backMoney}
						</s:if>
							<s:else>
						/
						</s:else>
						</td>
						<td>
							${pagelist.category}
						</td>
						<td>
							${pagelist.voucherway}
						</td>
						<td>
							${pagelist.isTax}
						</td>
						<td>
							<s:iterator id="paged"
									value="#pagelist.invoiceNum.split(',')"
									status="pdindex">
									${paged}<b>；</b>
								</s:iterator>
						</td>
						<td>
							${pagelist.zhifuoryufu}
						</td>
						<td>
							${pagelist.addTime}
						</td>
						<td>
							<a
								href="CircuitRunAction_findAduitPage.action?id=${pagelist.epId}">${pagelist.epStattus}</a>
						</td>
						<td>
							${pagelist.status}
						</td>
						<td>
							<a
								href="FundApplyAction_findfundDetailedList.action?id=${pagelist.id}">明细(打印)</a>/
							<%--							<s:if test='#pagelist.zhifuoryufu == "预付"'>--%>
							<%--								<a href="FundApplyAction_toupdatefundApply.action?id=${pagelist.id}&cpage=${cpage}">申请支付</a>/--%>
							<%--							</s:if>--%>
								<s:if
									test="#pagelist.epStattus!='同意'&&#pagelist.epStattus!='审批中'&&#pagelist.status!='财务确认'">
									<a
										href="FundApplyAction_delfundApply.action?fundApply.id=${pagelist.id}&cpage=${cpage}"
										onclick="confirm('确定要删除?')">删除</a>
								</s:if>
							<s:if test="pagestatus=='cw'">
								<s:if test="#pagelist.status=='财务确认'">
									<a href="javascript:void(0);" onclick="agree(${id})">/同意</a>/<a
										href="javascript:void(0);" onclick="back(${id})">打回</a>
								</s:if>
								<s:elseif test="#pagelist.status=='个人确认'">
									<a href="javascript:void(0);" onclick="tosureSelf(${id})">/确认</a>
								</s:elseif>
							</s:if>
							<s:if test="#pagelist.attachmentsFile!=null">
								/<a href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/fundApply/<s:property value="#pagelist.attachmentsFile"/>">文件</a>
							</s:if>
							<s:else>
							/无文件
							</s:else>
						</td>
					</s:iterator>
					<tr id="wtdiv">
						<td colspan="8" align="right" style="border-width: 0px;">
							委托:
<%--							<input name="bwtCompany" ><br/>--%>
<%--							<SELECT name="bwtCompany" id="bwtCompany" style="height: 25px;"--%>
<%--								onMouseOver="createDept('bwtCompany','EscrowAction_selectEscrowPayCom.action')">--%>
<%--								<option value="">--%>
<%--									请选择委托付款方--%>
<%--								</option>--%>
<%--							</SELECT>--%>
							<select id="danwei" style="width:155px;height:21px;" onchange="fuzhi()">
							</select>
							<input id="bwtCompany" name="bwtCompany" 
							style="width:132px;height:19px;margin-left:-157px;border-width: 0px;" />
						</td>
						<td colspan="1" style="border-width: 0px;"></td>
						<td colspan="7" align="left" style="border-width: 0px;">
							<input id="wtBtn" value="委托" type="submit" style="width: 60px;height: 40px;" disabled="disabled">
						</td>
					</tr>
					<tr>
						<td colspan="16" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />

						</td>
					</tr>
				</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript">
function agree(id) {
	if (confirm("确认同意此申请？")) {
		window.location.href = "FundApplyAction_agreeApply.action?cpage=${cpage}&id="
				+ id;
	}
}
function back(id) {
	if (confirm("确认打回此申请？")) {
		window.location.href = "FundApplyAction_backApply.action?cpage=${cpage}&id="
				+ id;
	}
}
function tosureSelf(id) {
	$("#fid").val(id);
	//$("#password").empty();
	chageDiv('block');
}
function sureSelf() {
	var password = $("#password").val();
	if (password == null) {
		alert("请输入密码");
		return false;
	}
	$
			.ajax( {
				type : "POST",
				url : "FundApplyActin_sureSelf.action",
				dataType : "json",
				data : {
					id : $("#fid").val(),
					password : password
				},
				success : function(data) {
					if (data == "true") {
						alert("确认成功!");
						window.location.href = "FundApplyAction_findfundList.action?pagestatus=cw&cpage=${cpage}";
					} else {
						alert(data);
					}
				}
			});
}

function chageNum(obj) {
	var inputs = document.getElementsByTagName("input");
	var num=0;
	for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "checkbox") {
				var checkBox = inputs[i];
				if (checkBox.checked == true) {
						num++;
				}
			}
	}
	if(num==0){
		$("#wtBtn").attr("disabled","disabled");
		$("#wtdiv").hide();
	}else{
		$("#wtBtn").removeAttr("disabled");
		$("#wtdiv").show();
	}
}
function fuzhi() {
	$("#bwtCompany").val($("#danwei option:selected").text());
}

function description1(){
	$.ajax( {
			url : "EscrowAction_selectEscrowPayCom.action",
			type : 'post',
			dataType : 'json',
			cache : false,//防止数据缓存
			success : function(useradsfa) {
				$("#danwei").empty();//清空
				if(useradsfa==null||useradsfa==''){
					$("<option value=''>无</option>").appendTo("#danwei");
				}else{
					var se = useradsfa.split('|');
					$(se).each(
						function() {
							$(
								"<option value='"
									+ this + "'>"
									+ this
									+ "</option>")
								.appendTo("#danwei")
						}
					);
				}
			},
			error : function() {
				alert("服务器异常!");
			}
		});
}

</script>
</html>
