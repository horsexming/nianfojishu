<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.task.util.Util"%>
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
.table2 {
	font-size: 16px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	border-width: 0px 0 0 0px;
	width: 100%;
}

.table2 th,.table2 td {
	border-width: 0 0px 0px 0;
}
</style>
	</head>
	<body style="background: url('');">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div>
				<form action="zhaobiaoAction!updateZhtoubiaoY.action" method="post"
					theme="simple">
					<input style="display: none;" value="${zhtoubiao.tid}"
						name="zhtoubiao.tid">
					<div align="center" id="d1">
						<div id="printDiv" class="my_show" align="center">
							<table width="100%" border="0" style="border-collapse: collapse;"
								align="center">
								<tr>
									<td align="center" colspan="10">
										<img width="45px" height="45px;"
											src="<%=basePath%>${companyInfo.logoOKjpg}" align="bottom"></img>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<font size="6px"><B>${companyInfo.name}</B> </font>
									</td>
								</tr>
								<tr>
									<td align="center" colspan="9">
										<font size="5px"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;原材料采购合同</font>
									</td>
								</tr>
								<tr>
									<td colspan="9" align="right">
										<font>合同编号NO: ${zhtoubiao.hetongbiaohao}</font>
										<br />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="6">
										甲方：${companyInfo.name}
									</td>
									<td colspan="5" align="right">
										签定地点：${companyInfo.name}
									</td>
								</tr>
								<tr>
									<td colspan="6">
										乙方：${zhtoubiao.tname}
									</td>
									<td colspan="5" align="right">
										签定时间：
										<s:if test='#session.Users.dept!="供应商"'>
											<input class="horizontalLine" style=""
												name="zhtoubiao.qiandingTime"
												value="${zhtoubiao.qiandingTime}"
												onfocus="chageClass(this,'')"
												onblur="chageClass(this,'horizontalLine')"
												onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
										</s:if>
										<s:else>
										${zhtoubiao.qiandingTime}
										</s:else>
									</td>
								</tr>
								<tr>
									<td align="center" colspan="7">
										&nbsp;&nbsp;
									</td>
								</tr>
								<tr>
									<td colspan="8">
										<table style="size: 18px; line-height: 22px;" border="1px"
											class="table">
											<tr>
												<td colspan="7">
													1、乙方按订货合同及采购定单向甲方提供以下材料：
												</td>
											</tr>
											<tr>
												<td>
													物料名称
												</td>
												<td>
													采购料
												</td>
												<td>
													规格要求
												</td>
												<td>
													数量(单位)
												</td>
												<td>
													实际称重
												</td>
												<td>
													含税单价(元/${zhaobiaoXi.t3})
												</td>
												<td>
													金额(RMB/元)
												</td>
												<s:iterator value="list" id="zhUser1" status="pageIndex">
													<tr>
														<td>
															${zhUser1.name}
														</td>
														<td>
															${zhUser1.style}
														</td>
														<td>
															${zhUser1.guigestyule}
														</td>

														<td>
															${zhUser1.num}

														</td>
														<td>
															<s:if test='#session.Users.dept!="供应商"'>
																<input class="horizontalLine" style=""
																	id="chengzhong${pageIndex.index}"
																	value="${zhUser1.chengzhong}"
																	name="zhtoubiao.zhtoubiaossList[${pageIndex.index}].chengzhong"
																	onfocus="chageClass(this,'')"
																	onkeyup="f1(this.value,${pageIndex.index})"
																	onblur="chageClass(this,'horizontalLine')">

																<input style="display: none;" value="${zhUser1.tid}"
																	name="zhtoubiao.zhtoubiaossList[${pageIndex.index}].tid">



															</s:if>
															<s:else>
														${zhUser1.chengzhong}
														</s:else>

														</td>
														<td>
															<input value="${zhUser1.tkong1}" readonly="readonly"
																id="danjia${pageIndex.index}">

														</td>
														<td>
															<input type="text" value="${zhUser1.zongjine}"
																id="zongjine${pageIndex.index}"
																name="zhtoubiao.zhtoubiaossList[${pageIndex.index}].zongjine"
																readonly="readonly">
														</td>
													</tr>
												</s:iterator>
												<tr>
													<th colspan="3">
														合计
													</th>
													<th colspan="4">

														<input class="horizontalLine" style="width: 150px"
															onfocus="chageClass(this,'')" name="zhtoubiao.heji"
															id="y1" value="${zhtoubiao.heji}"
															onblur="chageClass(this,'horizontalLine')"
															readonly="readonly">
														元
													</th>
												</tr>

												<tr>
													<td rowspan="2">
														合同期限
													</td>
													<td align="center">
														起
													</td>
													<td align="center">
														止
													</td>
													<td rowspan="2">
														合同依据:
													</td>
													<td rowspan="2" colspan="3">
														开标单号:
														<br />
														${zhtoubiao.number}
													</td>
												</tr>
												<tr>
													<td>
														<s:if test='#session.Users.dept!="供应商"'>
															<input class="horizontalLine" style=""
																onfocus="chageClass(this,'')" name="zhtoubiao.qiTime"
																value="${zhtoubiao.qiTime}"
																onblur="chageClass(this,'horizontalLine')"
																onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
														</s:if>
														<s:else>${zhtoubiao.qiTime}</s:else>
													</td>
													<td>
														<s:if test='#session.Users.dept!="供应商"'>
															<input class="horizontalLine" style=""
																onfocus="chageClass(this,'')" name="zhtoubiao.zhongTime"
																value="${zhtoubiao.zhongTime}"
																onblur="chageClass(this,'horizontalLine')"
																onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
														</s:if>
														<s:else>${zhtoubiao.zhongTime}</s:else>
													</td>
												</tr>
												<tr>
													<td colspan="7">
														2、质量要求、技术标准：按技术协议、质量协议、118WISH8.04-02原材料检验规程及GB/T700-88;
														Q/BQB303-2003标准执行。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														3、交货地点：
														<s:if test='#session.Users.dept!="供应商"'>
															<input class="horizontalLine" style=""
																onfocus="chageClass(this,'')"
																name="zhtoubiao.jiaohuoTime"
																value="${zhtoubiao.jiaohuoTime}"
																onblur="chageClass(this,'horizontalLine')"
																onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
														</s:if>
														<s:else>${zhtoubiao.jiaohuoTime}</s:else>
														送到上海市嘉定区安亭镇和静路1200号（${companyInfo.name}）
													</td>
												</tr>
												<tr>
													<td colspan="7">
														4、运输方式及费用负担：由乙方负责运输，费用乙方承担。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														5、包装要求：由乙方负责包装，应保证牢固，符合产品特性，以确保产品质量。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														6、合理损耗标准及计算方法：以实际来料净重计算确定（磅重）。
													</td>
												</tr>
												<!-- 
										<tr>
											<td colspan="7">
												7、验收标准、方法和期限：按第二条验收，每批货物需附产品质量证明书。
											</td>
										</tr>
										 -->
												<tr>
													<td colspan="7">
														7、结算方式及期限：
														<s:if test='#session.Users.dept!="供应商"'>
															<input class="horizontalLine" style="width: 500px"
																onfocus="chageClass(this,'')" name="zhtoubiao.shuoming"
																value="${zhtoubiao.shuoming}"
																onblur="chageClass(this,'horizontalLine')">
														</s:if>
														<s:else>${zhtoubiao.shuoming}</s:else>
													</td>
												</tr>
												<tr>
													<td colspan="7">
														8、甲方在生产过程中，发现因乙方原因（如材质或焊接问题等）造成零件的报废或返修，由此发生的各项费用由乙方承担。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														9、违约责任：按《经济合同法》。
													</td>
												</tr>
												<tr>
													<td colspan="7">
														10、本合同一式两份，甲乙双方各执一份。
													</td>
												</tr>
												<tr>
													<td align="center" colspan="7">
														&nbsp;&nbsp;
													</td>
												</tr>
												<tr>
													<td colspan="4">
														<table class="table2">
															<tr height="30px">
																<td colspan="3" align="center">
																	甲方
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位名称：
																</td>
																<td>
																	${companyInfo.name}
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位地址：
																</td>
																<td>
																	${companyInfo.address}
																</td>
															</tr>
															<tr height="30px">
																<td>
																	法定代表人：
																</td>
																<td>
																	______________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	委托代理人：
																</td>
																<td>
																	______________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	电 话：
																</td>
																<td>
																	______________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	开户银行：
																</td>
																<td>
																	______________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	帐 号：
																</td>
																<td>
																	______________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	邮政编码：
																</td>
																<td>
																	${companyInfo.zip}
																</td>
														</table>
													</td>
													<td colspan="3">
														<table class="table2">
															<tr height="30px">
																<td colspan="4" align="center">
																	乙 方
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位名称：
																</td>
																<td>
																	${zhtoubiao.tname}
																</td>
															</tr>
															<tr height="30px">
																<td>
																	单位地址：
																</td>
																<td>
																	______________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	法定代表人：
																</td>
																<td>
																	______________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	委托代理人：
																</td>
																<td>
																	______________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	电 话：
																</td>
																<td>
																	______________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	开户银行：
																</td>
																<td>
																	______________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	帐 号：
																</td>
																<td>
																	______________________
																</td>
															</tr>
															<tr height="30px">
																<td>
																	邮政编码：
																</td>
																<td>
																	______________________
																</td>
															</tr>
														</table>
													</td>
												</tr>
										</table>
									</td>
								</tr>
							</table>

						</div>
						<s:if test='#session.Users.dept!="供应商"'>
							<input type="submit" value="保存" class="input" />
						</s:if>
				</form>
				<s:if test='#session.Users.dept!="供应商"'>
					<s:if test='zhtoubiao.tkong7=="审核中"'>
						<a href="CircuitRunAction_findAduitPage.action?id=${zhtoubiao.epId}">审核</a>
					</s:if>
				</s:if>
				<s:if test='zhtoubiao.tkong7=="Y"'>
					<input type="button" id="print" value="打印" class="input"
						onclick="pagePrint('printDiv')" />

				</s:if>

				<%@include file="/util/foot.jsp"%>
		</center>
		<SCRIPT type="text/javascript">
		getHeji();
		function getHeji(){
			var list ="<s:property value='list.size'/>"  ;
			var nums=0;
			for(var i=0;i<list;i++){
				var val=document.getElementById("zongjine"+i).value;
				if(val!=""){
					nums+=parseFloat(val);
				}
			}
			document.getElementById("y1").value=nums; 
		}
		function f1(jine,index){
			//alert(jine+"  "+index);
			var  zhongliang=jine;
			var  jine=document.getElementById("danjia"+index).value;
			document.getElementById("zongjine"+index).value=zhongliang*jine;
			getHeji();
		}
		
		</SCRIPT>
	</body>
</html>
