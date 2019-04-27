<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
	<body >
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
							<span id="title">您正在采购执行单进行操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
			</div>

			<div align="center">
				<h3>
					采购执行单管理
				</h3>
				<form
					action="performsingleAction_findPerformsingle.action?tag=<s:property value='tag'/>"
					method="post">
					<table class="table">
						<tr>
							<td align="center">
								采购名称：
								<input type="text" name="performsingle.purchase_name" />
							</td>
							<td align="center">
								采购单号:
								<input type="text" name="performsingle.purchase_number" />
							</td>
							<td align="center">
								采购部门：
								<input type="text" name="performsingle.purchase_dept" />
							</td>
						</tr>
						<tr>
							<td colspan="3" align="center">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" class="input" />
						</tr>
					</table>

					<table width="100%" border="0" style="border-collapse: collapse;" class="table" >
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								序号
							</td>
							<td align="center">
								采购名称
							</td>
							<td align="center">
								类别
							</td>
							<td align="center">
								采购部门
							</td>
							<td align="center">
								采购单号
							</td>
							<td align="center">
								采购人
							</td>
							<td align="center">
								状态
							</td>
							<td align="center">
								采购时间
							</td>
							<td align="center">
								操作
							</td>
							<td></td>
						</tr>
						<s:iterator value="list" id="pageList" status="pageStatus">
							<s:if test="#pageStatus.first">
								<tr bgcolor="red">
									<th colspan="16" align="center">
										<font color="#ffffff">未生成合同纪录<br /> </font>
									</th>
								</tr>
							</s:if>
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#pageStatus.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>
							<td>
								${pageList.purchase_name}
							</td>
							<td>
								${pageList.purchase_category}
							</td>
							<td>
								${pageList.purchase_dept}
							</td>
							<td style="width: 450px;">
								${pageList.purchase_number}
							</td>
							<td>
								${pageList.purchaser}
							</td>
							<td>
								${pageList.status}
							</td>
							<td>
								${pageList.purchase_date}
							</td>
							<td>
								<a
									href="performsingleAction_salPerformsingle.action?performsingle.id=${pageList.id}&performsingle.purchase_category=${pageList.purchase_category}">查看/</a>
								<a href="CircuitRunAction_findAduitPage.action?id=${pageList.epId}">审批动态/</a>
								<s:if test="#pageList.status=='未审核'||#pageList.status=='打回'">
									<a onclick="return window.confirm('此操作关联明细,确定删除?')"
										href="performsingleAction_delPerformsingle.action?tag=<s:property value='tag'/>&performsingle.id=${pageList.id}&bargain_num=${pageList.purchase_number}">删除/</a>
								</s:if>
								<s:else>
									<s:if test="#pageList.ht_status=='已生成'">
										<a href="javascript:;" onclick="showht('${pageList.purchase_number}','${pageList.purchase_category}','${pageList.purchase_name}')">查看合同</a>
									</s:if>
									<s:else>
									<a
										onclick="add('${pageList.purchase_number}','${pageList.purchase_category}','${pageList.id}')">生成合同</a>
									</s:else>
								</s:else>
							</td>
						</s:iterator>
						<s:iterator value="maps" id="pageList" status="pageStatus">
							<s:if test="#pageStatus.first">
								<tr bgcolor="green">
									<th colspan="16" align="center">
										<font color="#ffffff">已生成合同纪录<br /> </font>
									</th>
								</tr>
							</s:if>
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#pageStatus.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>
							<td>
								${pageList.purchase_name}
							</td>
							<td>
								${pageList.purchase_category}
							</td>
							<td>
								${pageList.purchase_dept}
							</td>
							<td style="width: 450px;">
								${pageList.purchase_number}
							</td>
							<td>
								${pageList.purchaser}
							</td>
							<td>
								${pageList.status}
							</td>
							<td>
								${pageList.purchase_date}
							</td>
							<td>
								<a
									href="performsingleAction_salPerformsingle.action?performsingle.id=${pageList.id}&performsingle.purchase_category=${pageList.purchase_category}">查看/</a>
								<a href="CircuitRunAction_findAduitPage.action?id=${pageList.epId}">审批动态/</a>
								<s:if test="#pageList.status=='未审核'||#pageList.status=='打回'">
									<a onclick="return window.confirm('此操作关联明细,确定删除?')"
										href="performsingleAction_delPerformsingle.action?tag=<s:property value='tag'/>&performsingle.id=${pageList.id}&bargain_num=${pageList.purchase_number}">删除/</a>
								</s:if>
								<s:else>
									<s:if test="#pageList.ht_status=='已生成'">
										<a href="javascript:;" onclick="showht('${pageList.id}')">查看合同</a>
									</s:if>
									<s:else>
									<a
										onclick="add('${pageList.purchase_number}','${pageList.purchase_category}','${pageList.id}')">生成合同</a>
									</s:else>
								</s:else>
							</td>
						</s:iterator>
						</tr>
						<tr >
							<s:if test="errorMessage==null">
								<td colspan="12" align="right">
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
							</s:else>
							</td>
						</tr>
					</table >
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function add(obj1, obj2, obj3) {
	//alert(obj1+"==="+obj2+"==="+obj3);
	var fileName1 = encodeURI(encodeURI(obj1));
	var fileName2 = encodeURI(encodeURI(obj2));
	location.href = "bargainAction_jumpaddBargain1.action?contract_id=" + obj3
			+ "&contract_num1=" + fileName1 + "&contract_source=" + fileName2;
}
function showht(id) {
		$.ajax({
 			type:"POST",
 			url:"bargainAction_getBargain1.action",
 			data:{contract_id:id},
 			dataType:"json",
 			success:function(data){
 				if(data!=null){
 					location.href = "bargainAction_salBarContract.action?barContract.id="+data.id+"&status=print";
 				}
 			}
	})
}

</script>
	</body>
</html>
