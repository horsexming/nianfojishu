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
		<div id="gongneng">
			<div align="center">
				<form action="QuotedPrice_allProcardSelsct.action" method="post">
					<table class="table">
						<tr>
							<th colspan="3">
								<h2>
									外购件查询
								</h2>
							</th>
						</tr>
						<tr>
							<th align="right">
								件号:
							</th>
							<th align="center">
								<input name="allId" value="${allId}" />
							</th>
							<td align="center">
								<input type="submit" value="查询" class="input" />
								<input type="reset" value="重置" class="input" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div align="center">
				<%--1、项目报价--%>
				<table class="table">
					<tr>
						<th colspan="9" style="font-size: 18px;">
							项目报价
						</th>
					</tr>
					<tr bgcolor="#c0dcf2" height="30px">
						<th align="center">
							序号
						</th>
						<th align="center">
							询价单号
						</th>
						<th align="center">
							产品生命周期
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							名称
						</th>
						<th align="center">
							类型
						</th>
						<th align="center">
							数量
						</th>
						<th align="center">
							流程状态
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:if test="quotedPriceList.size()>0">
						<s:iterator value="quotedPriceList" id="pageNeedQpri"
							status="pageStatus">
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
								<s:property value="#pageStatus.index+1" />
							</td>
							<td align="center">
								${pageNeedQpri.quotedNumber}
							</td>
							<td align="center">
								${pageNeedQpri.procardLifeCycle}年
							</td>
							<td align="center">
								${pageNeedQpri.markId}
							</td>
							<td align="center">
								${pageNeedQpri.proName}
							</td>
							<td align="center">
								${pageNeedQpri.procardStyle}
							</td>
							<td align="center">
								${pageNeedQpri.filnalCount}
							</td>
							<td align="center">
								${pageNeedQpri.status}
							</td>
							<td align="center">
								<a
									href="QuotedPrice_findQuotedPrice.action?id=${pageNeedQpri.rootId}&pageStatus=bom">Bom</a>/
								<a
									href="ProjectManage_findDeptProTime.action?id=${pageNeedQpri.rootId}">时间指派</a>/
								<a href="QuotedPrice_findeOsa.action?id=${pageNeedQpri.rootId}">查看评审进度</a>/
								<s:if test='#pageQpri.status!="初始"'>
									<a
										href="ProjectManage_findDeptProTime.action?id=${pageNeedQpri.rootId}&pageStatus=show">完成进度</a>/
										</s:if>
								<a
									href="QuotedPrice_findAllPrice.action?id=${pageNeedQpri.rootId}">报价汇总</a>
								<s:if test='#pageQpri.status=="集合报价"'>
										/<a
										href="QuotedPrice_startproject.action?id=${pageNeedQpri.rootId}&cpage=${cpage}&total=${total}&pageStatus=all">启动项目</a>
								</s:if>
								<s:if test='#pageQpri.status=="项目跟踪"'>
										/<a
										href="QuotedPrice_trackQuotedPrice.action?id=${pageNeedQpri.rootId}">项目跟踪</a>
										/<a onclick="intoSop(${pageNeedQpri.rootId})">进入SOP</a>
								</s:if>
							</td>
						</s:iterator>
					</s:if>
					<s:else>
						<tr>
							<td colspan="9" style="font-size: 15px; color: red;"
								align="center">
								对不起，没有查到相关的项目信息
							</td>
						</tr>
					</s:else>
				</table>
				<hr>
				<%--2、外购外委评审--%>
				<table class="table">
					<tr>
						<th colspan="19" style="font-size: 18px;">
							外购外委评审
						</th>
					</tr>
					<tr bgcolor="#c0dcf2" height="30px"
						style="border-collapse: separate;">
						<th align="center">
							序号
						</th>
						<th align="center">
							外委申请编号
						</th>
						<th align="center">
							外委人
						</th>
						<th align="center">
							部门
						</th>
						<th align="center">
							客户名称
						</th>
						<th align="center">
							零件号
						</th>
						<th align="center">
							外委工序
						</th>
						<th align="center">
							工序名
						</th>
						<th align="center">
							交付数量
						</th>
						<th align="center">
							状态
						</th>
						<th align="center">
							交付时间
						</th>
						<th align="center">
							是否加急
						</th>
						<th align="center">
							时限
						</th>
						<th align="center">
							设备故障
						</th>
						<th align="center">
							外委单价
						</th>
						<th align="center">
							自制单价
						</th>
						<th align="center">
							外委限量
						</th>
						<th align="center">
							录入状态
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:if test="outSouList.size()>0">
						<s:iterator value="outSouList" status="se" id="osaVAll">
							<s:if test="#se.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#se.index+1" />
							</td>
							<td>
								${osaVAll.osaNO}
							</td>
							<td>
								${osaVAll.username}
							</td>
							<td>
								${osaVAll.dept}
							</td>
							<td>
								${osaVAll.customer}
							</td>

							<td>
								${osaVAll.markID }
							</td>
							<td>
								${osaVAll.processNO}
							</td>
							<td>
								${osaVAll.processName}
							</td>
							<td>
								${osaVAll.deliveryCount}
							</td>
							<td>
								${osaVAll.status}
							</td>

							<td>
								${osaVAll.deliveryDate}
							</td>
							<td>
								${osaVAll.isJiaji}
							</td>
							<td>
								${osaVAll.timeLimit}
							</td>
							<td>
								${osaVAll.machineFail}
							</td>
							<td>
								${osaVAll.osOneHeji}
							</td>
							<td>
								${osaVAll.selfOneHeji}
							</td>
							<td>
								${osaVAll.addChengMinBalanceCount}
							</td>
							<td>
								${osaVAll.executeStatus}
							</td>

							<td width="35px;">
								<s:if
									test="#osaVAll.executeStatus=='周期录入'||#osaVAll.executeStatus=='产品录入'">
									<a
										href="osaAction!getOSAById.action?id=${id}&cpage=${cpage}&crudTag=update2">产品周期</a>
								</s:if>
								<s:if test="#osaVAll.executeStatus=='原因录入'">
									<a
										href="osaAction!getOSAById.action?id=${id}&cpage=${cpage}&crudTag=update3">申报原因</a>
								</s:if>
								<s:if test="#osaVAll.executeStatus=='成本核算录入'">
									<a
										href="osaAction!getOSAById.action?id=${id}&cpage=${cpage}&crudTag=update4">成本核算</a>
								</s:if>
								<s:if test="#osaVAll.executeStatus=='自制新增成本录入'">
									<a
										href="osaAction!getOSAById.action?id=${id}&cpage=${cpage}&crudTag=update5">自制新增成本</a>
								</s:if>
								<s:if test="#osaVAll.executeStatus=='评审完成'">
									<a target="_showwaiwei"
										href="osaAction!getOSAById.action?id=${id}&crudTag=print">查看</a>
									<br />
									<a
										href="osaAction!getOSAById.action?id=${id}&cpage=${cpage}&crudTag=update">修改</a>
								</s:if>
							</td>
						</s:iterator>
					</s:if>
					<s:else>
						<tr>
							<td colspan="19" style="font-size: 15px; color: red;"
								align="center">
								对不起，没有查到相关的外购外委评审单信息
							</td>
						</tr>
					</s:else>
				</table>
				<hr>
				<%--3、询比议价--%>
				<table class="table">
					<tr>
						<th colspan="9" style="font-size: 18px;">
							询比议价
						</th>
					</tr>
					<tr bgcolor="#c0dcf2" height="30px">
						<td align="center">
							序号
						</td>
						<td align="center">
							编号
						</td>
						<td align="center">
							申请人所在部门
						</td>
						<td align="center">
							申请人
						</td>
						<td align="center">
							议价单号
						</td>
						<td align="center">
							议价缘由
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							日期
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:if test="bargainList.size()>0">
						<s:iterator value="bargainList" id="pageList" status="pageStatus">
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
								<s:property value="#pageStatus.index+1" />
							</td>
							<td>
								${pageList.bargain_number}
							</td>
							<td>
								${pageList.applicants_dept}
							</td>
							<td>
								${pageList.user_name}
							</td>
							<td>
								${pageList.bargain_num}
							</td>
							<td>
								<s:if test="#pageList.bargain_source=='OA'">
									OA
								</s:if>
								<s:if test="#pageList.bargain_source=='SB'">
									设备维修
								</s:if>
								<s:if test="#pageList.bargain_source=='KVP'">
									KVP
								</s:if>
								<s:if test="#pageList.bargain_source=='设备'">
									设备
								</s:if>
								<s:if test="#pageList.bargain_source=='QT'">
									其他
								</s:if>
							</td>
							<td>
								${pageList.status}
							</td>
							<td>
								${pageList.bargain_date}
							</td>
							<td>
								<a
									href="bargainAction_salBargain.action?bargain.id=${pageList.id}&test=1">预览/</a>
								<a
									href="CircuitRunAction_findAduitPage.action?id=${pageList.epId}">审批动态</a>
								<s:if test="#pageList.status=='未审核'||#pageList.status=='打回'">
									<a
										href="bargainAction_salBargain.action?bargain.id=${pageList.id}&tag=<s:property value="test"/>">/修改</a>
									<a onclick="return window.confirm('此操作关联明细,确定删除?')"
										href="bargainAction_delBargain.action?test=<s:property value="test"/>&bargain.id=${pageList.id}">/删除</a>
								</s:if>
								<%--								<s:if test="test!=1">--%>
								<%--									<a onclick="return window.confirm('此操作关联明细,确定删除?')"--%>
								<%--										href="bargainAction_delBargain.action?test=<s:property value="test"/>&bargain.id=${pageList.id}">删除</a>--%>
								<%--								</s:if>--%>
							</td>
						</s:iterator>
					</s:if>
					<s:else>
						<tr>
							<td colspan="9" style="font-size: 15px; color: red;"
								align="center">
								对不起，没有查到相关的询比议价单
							</td>
						</tr>
					</s:else>
				</table>
				<hr>
				<%--4、采购执行单--%>
				<table class="table">
					<tr>
						<th colspan="9" style="font-size: 18px;">
							采购执行单
						</th>
					</tr>
					<tr bgcolor="#c0dcf2" height="30px">
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
					</tr>
					<s:if test="performsingleList.size()>0">
						<s:iterator value="performsingleList" id="pageList"
							status="pageStatus">
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
								<s:property value="#pageStatus.index+1" />
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
								<a
									href="CircuitRunAction_findAduitPage.action?id=${pageList.epId}">审批动态</a>
								<s:if test="#pageList.status=='未审核'||#pageList.status=='打回'">
									<%--									<a onclick="return window.confirm('此操作关联明细,确定删除?')"--%>
									<%--										href="performsingleAction_delPerformsingle.action?tag=<s:property value='tag'/>&performsingle.id=${pageList.id}&bargain_num=${pageList.purchase_number}">删除/</a>--%>
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
					</s:if>
					<s:else>
						<tr>
							<td colspan="9" style="font-size: 15px; color: red;"
								align="center">
								对不起，没有查到相关的采购执行单
							</td>
						</tr>
					</s:else>
				</table>
				<hr>
				<%--5、合同管理--%>
				<table class="table">
					<tr>
						<th colspan="9" style="font-size: 18px;">
							采购合同
						</th>
					</tr>
					<tr bgcolor="#c0dcf2" height="30px">
						<td align="center">
							序号
						</td>
						<td align="center">
							合同编号
						</td>
						<td align="center">
							合同名称
						</td>
						<td align="center">
							供方
						</td>
						<td align="center">
							来源
						</td>
						<td align="center">
							单号
						</td>
						<td align="center">
							总金额
						</td>
						<td align="center">
							添加人
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:if test="barContractList.size()>0">
						<s:iterator value="barContractList" id="pageList"
							status="pageStatus">
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
								<s:property value="#pageStatus.index+1" />
							</td>
							<td>
								${pageList.contract_num}
							</td>
							<td>
								${pageList.contract_name}
							</td>
							<td>
								${pageList.supplier}
							</td>
							<td>
								${pageList.contract_source}
							</td>
							<td style="width: 450px">
								${pageList.contract_num1}
							</td>
							<td>
								${pageList.heji}
							</td>
							<td>
								${pageList.username}
							</td>
							<td>
								<a
									href="bargainAction_salBarContract.action?barContract.id=${pageList.id}&status=print">预览/</a>
								<a
									href="bargainAction_salBarContract.action?barContract.id=${pageList.id}&
								barContract.contract_source=${pageList.contract_source}&barContract.contract_num1=${pageList.contract_num1}&
								test=<s:property value="test"/>">修改</a>
								<%--								<a onclick="return window.confirm('此操作关联明细,确定删除?')"--%>
								<%--									href="bargainAction_delBarContract.action?barContract.id=${pageList.id}&test=<s:property value="test"/>">删除</a>--%>

							</td>
						</s:iterator>
					</s:if>
					<s:else>
						<tr>
							<td colspan="9" style="font-size: 15px; color: red;"
								align="center">
								对不起，没有查到相关的采购合同
							</td>
						</tr>
					</s:else>
				</table>
				<hr>
				<%--6、价格--%>
				<table class="table">
					<tr>
						<th colspan="19" style="font-size: 18px;">
							价格管理
						</th>
					</tr>
					<tr align="center" bgcolor="#c0dcf2" height="30px">
						<td>
							序号
						</td>
						<td>
							产品类别
						</td>
						<td>
							生产类型
						</td>
						<td width="80px">
							供料属性
						</td>
						<td>
							件号
						</td>
						<td>
							版本
						</td>
						<td>
							物料类别
						</td>
						<td width="80px">
							名称
						</td>
						<td>
							工序号
						</td>
						<td width="60px">
							工序名称
						</td>
						<td>
							外委类型
						</td>
						<td width="60px">
							签订方
						</td>
						<td>
							价格(含税)
						</td>
						<td>
							价 格 (不含税)
						</td>
						<td>
							合同编号
						</td>
						<td>
							档案编号
						</td>
						<td>
							档案柜号
						</td>
						<td>
							价格有效期
						</td>
						<td>
							操作
						</td>
					</tr>
					<s:if test="pricesList.size()>0">
						<s:iterator id="pricetest" value="pricesList" status="statussdf">
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
								${pricetest.productCategory}
							</td>
							<td>
								${pricetest.produceType}
							</td>
							<td>
								${pricetest.kgliao}
							</td>
							<td>
								${pricetest.partNumber}
							</td>
							<td>
								${pricetest.banbenhao}
							</td>
							<td>
								${pricetest.wlType}
							</td>
							<td align="left" width="60px">
								${pricetest.name}
							</td>
							<td>
								${pricetest.gongxunum}
							</td>
							<td width="60px">
								${pricetest.processNames}
							</td>
							<td>
								${pricetest.wwType} ${pricetestt.gys}
							</td>
							<td align="left" width="60px">
								${pricetest.qidingfang}
							</td>
							<td>
								<s:iterator value="strList1" id="str">
									<s:if test="#str == (#pricetest.productCategory+'价格')">
											${pricetest.hsPrice}
										</s:if>
									<s:elseif test="#str == (#pricetest.produceType+'价格')">
											${pricetest.hsPrice}
										</s:elseif>
								</s:iterator>
							</td>
							<td>
								<s:iterator value="strList1" id="str">
									<s:if test="#str == (#pricetest.productCategory+'价格')">
											${pricetest.bhsPrice}
										</s:if>
									<s:elseif test="#str == (#pricetest.produceType+'价格')">
											${pricetest.bhsPrice}
										</s:elseif>
								</s:iterator>
							</td>
							<td>
								${pricetest.contractNumber}
							</td>
							<td>
								${pricetest.fileNumber}
							</td>
							<td>
								${pricetest.danganWeizhi}
							</td>
							<td>
								${pricetest.pricePeriodEnd}
							</td>
							<td>
								<a
									href="PriceAction!findPriceById.action?id=${pricetest.id}&statue=${statue}"
									target="_blank">查看详细Details</a>
							</td>
						</s:iterator>
					</s:if>
					<s:else>
						<tr>
							<td colspan="19" style="font-size: 15px; color: red;"
								align="center">
								对不起，没有查到相关的价格信息
							</td>
						</tr>
					</s:else>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function showht(id) {
	$
			.ajax( {
				type : "POST",
				url : "bargainAction_getBargain1.action",
				data : {
					contract_id : id
				},
				dataType : "json",
				success : function(data) {
					if (data != null) {
						location.href = "bargainAction_salBarContract.action?barContract.id="
								+ data.id + "&status=print";
					}
				}
			})
}
function add(obj1, obj2, obj3) {
	//alert(obj1+"==="+obj2+"==="+obj3);
	var fileName1 = encodeURI(encodeURI(obj1));
	var fileName2 = encodeURI(encodeURI(obj2));
	location.href = "bargainAction_jumpaddBargain1.action?contract_id=" + obj3
			+ "&contract_num1=" + fileName1 + "&contract_source=" + fileName2;
}
$(function() {
	var main = $(window.parent.document).find("#showProcess");//找到iframe对象
	if (main != null) {
		var thisheight = document.body.scrollHeight;
		var thisheight2 = parseFloat(thisheight);
		main.height(thisheight2 + 100);//为iframe高度赋值如果高度小于500，则等于500，反之不限高，自适应
	}
})
</script>
	</body>
</html>
