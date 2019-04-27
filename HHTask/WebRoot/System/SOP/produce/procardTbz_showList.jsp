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
		<script type="text/javascript">
</script>

	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
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
		<div id="gongneng">
			<div align="center" style="border: 1px solid #00000;">
			<div align="center">
			<br/>
			<h3><font color="red">注：此处编制以bom结构为导向，所有显示出来的总成件号表示其下有需要您编制，校对，审批或者批准的物料</font></h3> 
			<br/>
			</div>
				<form action="procardTemplateGyAction_findProcardTemForBz.action"
					method="post">
					<table class="table">
						<tr>
							<th colspan="6">
								BOM管理
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
								</select>
							</td>
							<th>
								产品类型(Product Type):
							</th>
							<td>
								<select name="procardTemplate.productStyle"
									style="width: 155px;">
									<option>
										${procardTemplate.productStyle}
									</option>
									<option></option>
									<option value="试制">
										试制
									</option>
									<option value="批产">
										批产
									</option>
								</select>
							</td>
						</tr>
						<s:if test="pageStatus=='ok'">
						</s:if>
						<s:else>
							<tr>
								<th>
									编辑状态(edit Type):
								</th>
								<td>
									<select name="procardTemplate.bzStatus" style="width: 155px;">
										<option>
											${procardTemplate.bzStatus}
										</option>
										<option></option>
										<option>
											初始
										</option>
										<option>
											待编制
										</option>
										<option>
											已编制
										</option>
										<option>
											已校对
										</option>
										<option>
											已审核
										</option>
										<option>
											已批准
										</option>
									</select>
								</td>
								<th>
								业务件号(Part No.):
							</th>
							<td>
								<input name="procardTemplate.ywMarkId"
									value="${procardTemplate.ywMarkId}" />
							</td>
							</tr>
						</s:else>
						<tr>
							<th colspan="6">
								<input type="submit" value="查询(Query)" class="input" />
								<input type="reset" value="清空(Empty)" class="input" />
							</th>
						</tr>
					</table>
				</form>

				<div id="rootTemplateDiv">
					<div id="showMessage"
						style="color: red; font-size: 14px; font-weight: bolder;">
					</div>
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								件号
							</th>
							<th align="center">
								名称
							</th>
							<th align="center">
								卡片类型
							</th>
							<th align="center">
								产品类型
							</th>
							<th align="center">
								编制人
							</th>
							<s:if test="bzjdCount==2">
							<th align="center">
								批准人
							</th>
							</s:if>
							<s:elseif test="bzjdCount==3">
							<th align="center">
								校对人
							</th>
							<th align="center">
								批准人
							</th>
							</s:elseif>
							<s:else>
							<th align="center">
								校对人
							</th>
							<th align="center">
								审核人
							</th>
							<th align="center">
								批准人
							</th>
							</s:else>
							<th align="center">
								编制状态
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator value="procardTemplateList" id="pageProcardTem"
							status="pageindex">
							<s:if test="#pageindex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageindex.index+1" />
							</td>
							<td>
								${pageProcardTem.markId}
								<s:if test="#pageProcardTem.ywMarkId!=null">
								<br/>
									<font color="red">(${pageProcardTem.ywMarkId})</font>
									</s:if>
							</td>
							<td style="width: 180px;">
								${pageProcardTem.proName}
							</td>
							<td>
								${pageProcardTem.procardStyle}
							</td>
							<td>
								${pageProcardTem.productStyle}
							</td>
							<td>
								${pageProcardTem.bianzhiName}
							</td>
							<s:if test="bzjdCount==2">
							<td>
								${pageProcardTem.pizhunName}
							</td>
							</s:if>
							<s:elseif test="bzjdCount==3">
							<td>
								${pageProcardTem.jiaoduiName}
							</td>
							<td>
								${pageProcardTem.pizhunName}
							</td>
							</s:elseif>
							<s:else>
							<td>
								${pageProcardTem.jiaoduiName}
							</td>
							<td>
								${pageProcardTem.shenheName}
							</td>
							<td>
								${pageProcardTem.pizhunName}
							</td>
							</s:else>
							<td>
								${pageProcardTem.bzStatus}
							</td>
							<td align="center">
							 <a href="procardTemplateGyAction_findSonsForBz.action?id=${pageProcardTem.id}" >明细</a>
							</td>
							</tr>
						</s:iterator>
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
		<SCRIPT type="text/javascript">
		function showDaoRuDiv(){
	$("#showProcardDetail").attr("src",
			"procardTemplateGyAction_toDaoRuBom.action");
	chageDiv('block');
}
		function shenyue(id){
	$("#showProcardDetail").attr("src","procardTemplateGyAction_shenyue.action?id="+id)
	chageDiv('block');
}
function submitProcard(id,bzStatus) {
	var spms="";
	if(bzStatus=="已审核"){
		spms=",您审批同意后即将加盖印章";
	}
	if (confirm("当前模板编制状态为:"+bzStatus+spms+",是否提交?")) {
		
		$.ajax( {
			type : "POST",
			url : "procardTemplateGyAction_submitProcard.action",
			dataType : "json",
			data : {
				id : id
			},
			success : function(data) {
				alert(data.message);
				if (data.success) {
					window.location.href="procardTemplateGyAction_findProcardTem.action?cpage=${cpage}";
				}
			}
		});
	}
}
function backProcard(id,bzStatus) {
	if (confirm("当前模板编制状态为:"+bzStatus+",是否打回?")) {
		$
				.ajax( {
					type : "POST",
					url : "procardTemplateGyAction_backProcard.action",
					dataType : "json",
					data : {
						id : id
					},
					success : function(data) {
						alert(data.message);
						if (data.success) {
							window.location.href="procardTemplateGyAction_findProcardTem.action?cpage=${cpage}";
						}
					}
				});
	}
}
function tozpbz(id){
	$("#showProcardDetail").attr("src",
			"procardTemplateGyAction_tozpbz.action?id="+id);
	chageDiv('block');
}

function showHistory(id){
	$("#showProcardDetail").attr("src",
			"procardTemplateGyAction_showHistoryList.action?id="+id);
	chageDiv('block');
}
<%--function sbApply(id){--%>
<%--	$("#showProcardDetail").attr("src",--%>
<%--			"procardTemplateGyAction_tosbApply.action?id="+id);--%>
<%--	chageDiv('block');--%>
<%--}--%>
function sbApply(id){
	if(confirm("您将申请设变是否继续?")){
	$("#module14").attr("disabled","disabled");
	$
			.ajax( {
				type : "POST",
				url : "procardTemplateGyAction_applySb.action",
				data : {
					id:id
				},
				dataType : "json",
				success : function(data) {
					alert(data.message);
				}
			});
	}
}
		</SCRIPT>
	</body>
</html>
