<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript">
</script>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div id="printDiv" class="my_show" align="center">

				<table class="table" style="width: 50%;">
					<tr>
						<th colspan="2">
							招标信息
						</th>
					</tr>
					<tr>
						<th align="right">
							招标题目:
						</th>
						<td>
							${zhaobiao.title}
						</td>
					</tr>
					<tr>
						<th align="right">
							招标负责人:
						</th>
						<td>
							${zhaobiao.fuze}
						</td>
					</tr>
					<tr>
						<th align="right">
							负责人电话:
						</th>
						<td>
							${zhaobiao.phone}
						</td>
					</tr>
					<tr>
						<th align="right">
							开始时间:
						</th>
						<td>
							${zhaobiao.moban}
						</td>
					</tr>
					<tr>
						<th align="right">
							结束时间:
						</th>
						<td>
							${zhaobiao.kongxian}
						</td>
					</tr>
					<tr>
						<th align="right">
							招商简介:
						</th>
						<td colspan="2" height="100px">
							${zhaobiao.loc}
						</td>
					</tr>
				</table>
				<!--               -->
				<br />
				<br />
				<input type="hidden" id="listsize" value="${fn:length(list)} ">


				<s:iterator value="list" id="zhaobiaoXi" status="pageIndex">
					<table class="table" style="width: 100%"
						id="Xi${pageIndex.index+1}">
						<tr bgcolor="#c0dcf2">
							<td>
								序号:${pageIndex.index+1}
							</td>
							<td>
								物料名称: ${zhaobiaoXi.t6}
								<input type="hidden" value="${zhaobiaoXi.id}" id="zhaobiaoXiid" />
							</td>
							<td>
								使用模版:${zhaobiaoXi.zhmoban.name}
							</td>
							<td>
								数量/单位:${zhaobiaoXi.t2}/${zhaobiaoXi.t3}
							</td>
							<td>
								规格要求:${zhaobiaoXi.t5}
							</td>

						</tr>
						<tr>
							<td colspan="5">
								<!--  -->
								<table class="table">
									<tr bgcolor="#c0dcf2">
										<td rowspan="10" width="30px">
											投 标 信 息
										</td>
										<td>
											序号
										</td>
										<td>
											投标公司
										</td>
										<td>
											核算价格
										</td>
										<td>
											负责人
										</td>
										<td>
											联系方式
										</td>
										<td>
											备注
										</td>
										<th>
											款到发货
										</th>
										<th>
											货到付款
										</th>
										<td>
											状态
										</td>
										<td>
											操作
										</td>
									</tr>
									<s:iterator value="#zhaobiaoXi.zhtoubiaos" id="zhtoubiao"
										status="pageIndex2">
										<s:if test='#zhtoubiao.tkong7!="N"'>
											<tr id="r1">

												<td>
													<input type="hidden" id="toubiao${pageIndex2.index+1}"
														value="${zhtoubiao.tid}">
													${pageIndex2.index+1}
													<input type="hidden" value="${zhtoubiao.tkong10}"
														id="zhtoubiaotid" />
												</td>
												<td>
													${zhtoubiao.tname}
												</td>
												<td>
													${zhtoubiao.tkong2}
												</td>
												<td>
													${zhtoubiao.tkong4}
												</td>
												<td>
													${zhtoubiao.tkong5}
												</td>
												<td>
													${zhtoubiao.tshuliang}
												</td>


												<td align="left">
													比例:${pagezhtoubiao.shoubikuan}
													</br>
													首笔周期:${pagezhtoubiao.zhouqikuan}
												</td>
												<td align="left">
													比例:${pagezhtoubiao.shoubikuan}首笔周期:${pagezhtoubiao.zhouqikuan}
													</br>
													第二笔比例:${pagezhtoubiao.erbihuo}第二笔周期:${pagezhtoubiao.zhouqier}
													</br>
													末笔比例:${pagezhtoubiao.mobihuo}末笔周期:${pagezhtoubiao.mobiuo}
												</td>
												<td>
													<s:if
														test='#zhtoubiao.tkong7=="审核中"||#zhtoubiao.tkong7=="同意"||#zhtoubiao.tkong7=="Y"'>
											中标
										</s:if>
													<s:if test='#zhtoubiao.tkong7=="N"'>
											未中标
										</s:if>
												</td>
												<td>
													<a
														href="zhaobiaoAction!shengchenhetong.action?zhtoubiao.tid=${zhtoubiao.tid}">查看合同详细</a>
												</td>

											</tr>
										</s:if>
									</s:iterator>
								</table>
							</td>
						</tr>
					</table>
					<br />
					<br />
				</s:iterator>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function f1() {
	listsize = document.getElementById("listsize").value;
	for ( var i = 1; i <= listsize; i++) {
		var Xid = document.getElementById("Xi" + i);
		var tid = document.getElementById("toubiao" + i).value;
	}

}
</script>
	</body>
</html>
