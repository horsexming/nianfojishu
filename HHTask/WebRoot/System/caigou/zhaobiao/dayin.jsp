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

				<table class="table" style="width: 50%;" align="center">
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
											价格
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
									</tr>
									<s:iterator value="#zhaobiaoXi.zhtoubiaos" id="zhtoubiao"
										status="pageIndex2">

										<tr id="r1">

											<td>
												<input id="toubiao${pageIndex2.index+1}"
													value="${zhtoubiao.tid}">
												${pageIndex2.index+1}
												<input type="hidden" value="${zhtoubiao.tkong10}"
													id="zhtoubiaotid" />
											</td>
											<td>
												${zhtoubiao.tname}
											</td>
											<td>
												${zhtoubiao.tkong1}/${zhaobiaoXi.t3}(含税)
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
												比例:${zhtoubiao.shoubikuan}
												</br>
												首笔周期:${zhtoubiao.zhouqikuan}
											</td>
											<td align="left">
												比例:${zhtoubiao.shoubikuan}首笔周期:${zhtoubiao.zhouqikuan}
												</br>
												第二笔比例:${zhtoubiao.erbihuo}第二笔周期:${zhtoubiao.zhouqier}
												</br>
												末笔比例:${zhtoubiao.mobihuo}末笔周期:${zhtoubiao.mobiuo}
											</td>
											<td>
												<s:if
													test='#zhtoubiao.tkong7=="审核中"||#zhtoubiao.tkong7=="同意"||#zhtoubiao.tkong7=="Y"'>
											中标
										</s:if>
												<s:if test='#zhtoubiao.tkong7=="N"'>
											未中标
										</s:if>
										</tr>

									</s:iterator>
								</table>
							</td>
						</tr>
					</table>
					<br />
					<br />
				</s:iterator>
			</div>
			<table align="center">
				<tr>
					<td colspan="10">

						<input type="button" id="print" value="打印" class="input"
							onclick="pagePrint('printDiv')" />


					</td>
				</tr>
			</table>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function f1() {
	listsize = document.getElementById("listsize").value;

	alert(listsize);
	for ( var i = 1; i <= listsize; i++) {
		alert("+++++++++++i" + i);
		var Xid = document.getElementById("Xi" + i);
		var tid = document.getElementById("toubiao" + i).value;
		alert(Xid);
		alert(tid);
	}

}
</script>
	</body>
</html>
