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
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="saleBudgetAction!prepareSave.action"
						style="color: #ffffff">采购申请</a> &nbsp;&nbsp;&nbsp;
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
			<input type="hidden" value="${id}" id="preId" />
			<input type="hidden" value="${tag}" id="tag" />
			<input type="button" value="打印" class="input" onclick="printPre()">	
			<br/>
			<div id="printDiv">
   				<table class="table">
   					<tr><th colspan="2" rowspan="2" align="center" ><span><img src="${pageContext.request.contextPath}/${companyInfo.logoOKjpg}" height="80px" width="80px"></span></th>
   					<th colspan="10" ><span style="font-size:28px;text-align:center;">${companyInfo.name}</span></th>
   					<th rowspan="2" align="left" width="20%">
   					<img src="<%=request.getContextPath()%>/barcode.action?msg=${opApply.appBarcode}&type=code128" height="55px" width=200px/>
   					 </th>
   					</tr>
   					<tr style="font-size:14px;"><td colspan="10" align="center"><span style="font-size:30px;">采购执行单</span></td></tr>
   					<tr style="font-size:14px;height: 35px;">
   					<th colspan="2" align="right">申报部门</th>
   					<th colspan="4">${opApply.appDept}</th>
   					<th >创建人</th>
   					<th colspan="3">${opApply.appApplier}</th>
   					<th colspan="2">计划月份</th>
   					<th colspan="2">${opApply.appPlanMon}</th>
   					</tr>
   					<tr style="font-size:14px;height: 35px;">
   					<th>序号</th><th>编号</th>
   					<th>物料编码</th>
   					<th>类别</th><th>项目编号</th>
   					<th>物品名称</th><th>规格</th>
   					<th width=80>编号</th><th>单位</th>
   					<th>数量</th><th>参考价</th><th>到货期限</th><th>计划依据</th>
   					
   					</tr>

								<s:iterator value="list" status="se" id="detail">
									<s:if test="#se.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
									<td style="height: 35px;">
										<s:property value="#se.index+1" />
									</td>
									<td>
										${detail.detailSeqNum}
									</td>
									<td>
										${detail.wlcode}
									</td>
									<td>
										${detail.detailChildClass}
									</td>
									<td>
										${detail.detailItemId}
									</td>
									<td>
										${detail.detailAppName}
									</td>
									<td>
										${detail.detailFormat}
									</td>
									<td>
										${detail.detailOrdNumber}
									</td>
									<td>
										${detail.detailUnit}
									</td>
									<td>
										${detail.detailCount}
									</td>
									<td>
										${detail.detailBudgetMoney}
									</td>
									<td>
										${detail.detailArrDate}
									</td>
									<td style="text-align: left;font-size:11px;">
										${detail.detailPlanAcco}
									</td>
									
									</tr>
									</s:iterator>
						</table>

						</div>
						<br>
						</div>
						<%@include file="/util/foot.jsp"%>
						</center>
						<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript">
	function printPre(){
		//打印
		pagePrint('printDiv');
			var id=$("#preId").val();
			var tag=$("#tag").val();
			$.ajax({
					type : "POST",
					url : "oaAppDetailAction!updatePrintStatus.action",
					data : {
						id : id,
						tag:tag
					},
					dataType : "json",
					success : function(msg) {
					}
				});		
		}
		//更新form
	
</script>
</html>
