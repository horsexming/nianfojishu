<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
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
		<style type="text/css">
body {
	text-align: center;
}
</style>

		<%@include file="/util/sonHead.jsp"%>
	</head>
	<%
		Users user = (Users) session.getAttribute("Users");
	%>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<center>
					<div id="printDiv">
						<table align="center" class="table" id="complexselectedlist">
							<tr>
								<td colspan="10" align="center">
									<img align="left" ; src="${companyInfo.logoOKjpg}"
										style="width: 60px; height: 50px;">

									<font size="5">${companyInfo.name}询比议价评审单</font>
									<h5 align="right">
										编号：${bargain.bargain_number}&nbsp;&nbsp;&nbsp;&nbsp;
									</h5>
								</td>
							</tr>
							<tr>
								<th>
									品名
								</th>
								<th>
									规格
								</th>
								<th>
									采购数量
								</th>
								<th>
									单位
								</th>
								<th>
									采购交期
								</th>
								<th>
									质量要求
								</th>
								<th>
									备注
								</th>
							</tr>
							<s:iterator value="listgoods" id="pageList">
								<tr>
									<th align="center">
										${pageList.goods_name}
									</th>
									<th align="center">
										${pageList.goods_format}
									</th>
									<th align="center">
										${pageList.goods_amount}
									</th>
									<th align="center">
										${pageList.goods_unit}
									</th>
									<th align="center">
										${pageList.purchase_delivery}
									</th>
									<th align="center">
										${pageList.quality_requirements}
									</th>
									<th align="center">
										${pageList.remark}
										<s:if test='bargain.bargain_source=="零部件及工序外委采购"'>
											<s:if test="#pageList.dataId!=null">
												<a
													href="osaAction!getOSAById.action?id=${pageList.dataId}&crudTag=print">外购外委评审</a>
											</s:if>
											<s:else>
												<a
													href="osaAction!findOSAppList.action?tag=manager&osa.markID=${pageList.goods_format}">外购外委评审</a>
											</s:else>
										</s:if>
									</th>
								</tr>
							</s:iterator>
							<!-- 添加询比议价厂商 -->
							<s:iterator value="listCompanyVO" id="pageList1"
								status="pageStatus">
								<tr id="1_aaa_1">
									<th>
										询比议价厂商
									</th>
									<th colspan="2">
										<s:if test='#pageList1.selected_status=="是"'>
										是否选中
										<input type="radio" name="selected_status"
												value="${pageStatus.index+1}" checked="checked" />
											是
										</s:if>
										<s:else>
										是否选中
										<input type="radio" name="selected_status"
												value="${pageStatus.index+1}" />
											是
										</s:else>
										&nbsp;&nbsp;&nbsp;&nbsp; 公司名称 ${pageList1.company_name}
									</th>
									<th>
										联系人 ${pageList1.contacts}
									</th>
									<th>
										电话/邮箱 ${pageList1.telephone}
									</th>
									<th>
										结算方式 ${pageList1.clearing_way}
									</th>
									<th>
										结算日期 ${pageList1.clearing_date}
									</th>
								</tr>
								<s:iterator value="#pageList1.bargainingDetails" id="pageList2"
									status="index">
									<tr id="1_aaa_2">
										<th>
											<span>第${index.index+1}次议价</span>
										</th>
										<th>
											数量
										</th>
										<th colspan="2">
											${pageList2.amount}
										</th>
										<th colspan="2">
											单价
										</th>
										<th>
											${pageList2.unitprice}
										</th>
									</tr>
								</s:iterator>
							</s:iterator>
							<tr height="45px">
								<th align="center">
									已审批人员
								</th>
								<th>
									&nbsp;
									<span id="aaa"></span>
								</th>
								<th>
									&nbsp;
									<span id="bbb"></span>
								</th>
								<th>
									&nbsp;
									<span id="ccc"></span>
								</th>
								<th>
									&nbsp;
									<span id="ddd"></span>
								</th>
								<th>
									议价日期
								</th>
								<th>
									&nbsp; ${bargain.bargain_date}
								</th>
							</tr>
							<tr id="yijian">
								<th align="center" colspan="7">
									审批意见
								</th>
							</tr>
							<tr>
								<td colspan="8" style="font-size: 12px;">
									附件：
									<br>
									1、该表适用于单一材料、单项设备、单项工程；
									<br />
									2、评审部门由申请人所在部门业务需要填写；
									<br />
									3、该表一式两联。申请人所在部门第一联，财务第二联；
									<br />
									4、审批流程为申请人依次请各部门审批后，由总经理批示确定供应商。
									<br />
								</td>
							</tr>
						</table>
					</div>

					<table class="table" style="width: 100%">
						<tr>
							<td align="center">
								<s:if test="'同意'==bargain.status">
									<input style="width: 80px; font-size: 18px;"
										onclick="pagePrint('printDiv','sy')" type="button" value="打印">
								</s:if>
								<s:else>
									<span style="font-size: 20; color: red; font-weight: bold">等待审核人通过后，方可打印借款！</span>
								</s:else>
							</td>
						</tr>
					</table>
				</center>
			</div>
			<br>
			<%@include file="/util/foot.jsp"%>
	</body>
	<script type="text/javascript">
$(function() {
	var successMessage = "${successMessage}";
		var pay_id =${bargain.id};
		//查询所有审批节点审批意见
		$.ajax( {
				type : "POST",
				url : "bargainAction_findExecutionOpinion.action",
				data : {
					pay_id : pay_id
				},
				dataType : "json",
				success : function(data) {
					$.each(data.data, function(i) {
					if(data.data[i].auditOpinion!=null){
						$("#yijian").after( "<tr><th><span>"+data.data[i].auditUserDept+"</span>部门</th><th colspan='6' align='left'>" +
					"<span>"+data.data[i].auditOpinion+"</span></th></tr>");
					}else{
						$("#yijian").after( "<tr><th><span>"+data.data[i].auditUserDept+"</span>部门</th><th colspan='6' align='left'>" +
					"<span></span></th></tr>");
					}
					//document.getElementById("a1").innerHTML = data.data[i].auditUserDept;
					//document.getElementById("b1").innerHTML = data.data[i].auditOpinion;
				})
					
				}
			})
			//查询已审批人员
			$.ajax( {
				type : "POST",
				url : "bargainAction_findBargain_ExecutionNode.action",
				data : {
					pay_id : pay_id
				},
				dataType : "json",
				success : function(data) {
					document.getElementById("aaa").innerHTML = data.data[0].auditUserName;
					document.getElementById("bbb").innerHTML = data.data[1].auditUserName;
					document.getElementById("ccc").innerHTML = data.data[2].auditUserName;
					document.getElementById("ddd").innerHTML = data.data[3].auditUserName;
				}
			})
			
			
			
	if (successMessage != "") {
		alert(successMessage);
		parent.location.reload(true);//刷新父页面
	}
})
 

 
</script>

</html>
