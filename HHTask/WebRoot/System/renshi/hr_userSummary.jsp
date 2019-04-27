<<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<%@taglib prefix="s" uri="/struts-tags"%>
	<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
	<%String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">














<html>
	<head>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script
			src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js">
</script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="row-fluid">
					<div class="col-md-3">
						<div class="row-fluid">
							<div class="col-md-12">
								<h3 >
									个人资料
								</h3>
								<s:if test='user.sex =="男"'>
									<img alt="${user.name}" class="img-circle"
										src="upload/user/${user.password.picture}" width="140px;"
										style="border: solid 1px #000000; height: 140px;"
										onerror="this.src='images/man.jpg'">
								</s:if>
								<s:else>
									<img alt="${user.name}" class="img-circle"
										src="upload/user/${user.password.picture}" width="140px;"
										style="border: solid 1px #000000; height: 140px;"
										onerror="this.src='images/woman.jpg'">
								</s:else>
								<p>
									<em>姓名:</em>
									<input id="userName" name="user.name" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.name}">
									<font color="red"> *</font>
								</p>
								<p>
									<em>性别:</em>
									<s:if test='user.sex=="男"'>
										<input type="radio" name="user.sex" value="男"
											checked="checked">
												男
											<input type="radio" name="user.sex" value="女">
												女
										</s:if>
									<s:else>
										<input type="radio" name="user.sex" value="男">
												男
											<input type="radio" name="user.sex" value="女"
											checked="checked">
												女
									</s:else>




								</p>
								<p>
									<em>出生年月:</em>
									<input class="Wdate" type="text" name="user.bothday"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
										value="${user.bothday}" />
								</p>
								<p>
									<em>学历:</em>
									<select id="education" name="user.education"
										style="width: 150px;">
										<option value="${user.education}">
											${user.education}
										</option>
										<option value="小学">
											小学
										</option>
										<option value="初中">
											初中
										</option>
										<option value="高中">
											高中
										</option>
										<option value="中专">
											中专
										</option>
										<option value="大专">
											大专
										</option>
										<option value="本科">
											本科
										</option>
										<option value="硕士">
											硕士
										</option>
										<option value="博士">
											博士
										</option>
									</select>
									<font color="red"> *</font>
								</p>
								<p>
									<em>身份证号:</em>
									<input id="uid"
										onkeyup="document.getElementById('check').href='http://qq.ip138.com/idsearch/index.asp?action=idcard&userid='+this.value;"
										name="user.uid" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine');document.getElementById('check').href='http://qq.ip138.com/idsearch/index.asp?action=idcard&userid='+this.value;"
										maxlength="18" value="${user.uid}">
									<font color="red"> *<a id="check" target="_blank"
										href="http://qq.ip138.com/idsearch/index.asp?action=idcard&userid=${user.uid}">(验)</a>
									</font>
								</p>
								<p>
									<em>员工性质:</em>
									<s:if test='user.password.staffNature=="正式"'>
										<input type="radio" name="user.password.staffNature"
											value="正式" checked="checked">
												正式
											<input title="劳务派遣人员,无需签订合同" type="radio"
											name="user.password.staffNature" value="劳务">
												劳务
										</s:if>
									<s:else>
										<input type="radio" name="user.password.staffNature"
											value="正式">
												正式
											<input title="劳务派遣人员,无需签订合同" type="radio"
											name="user.password.staffNature" value="劳务" checked="checked">
												劳务
										</s:else>
								</p>
								<p>
									<em>卡号:</em>
									<input id="cardId" name="user.cardId" class="horizontalLine"
										value="${user.cardId}">
									<font color="red"> *</font>
								</p>
								<p>
									<em>工号:</em>
									<input id="code" name="user.code" class="horizontalLine"
										readonly="readonly" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.code}" />
									<font color="red"> *</font>
								</p>
								<p>
									<em>部门:</em>
									<input type="hidden" value="${user.dept}" name="user.dept" />
									<input type="text"
										value="${user.dept}(${user.password.deptNumber})"
										class="horizontalLine" readonly="readonly" />
								</p>
								<p>
									<em>职务:</em>
									<input name="user.duty" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.duty}">
								</p>
								<p>
									<em>职别:</em>
									<input name="user.post" value="${user.post}"
										readonly="readonly" class="horizontalLine" />
								</p>
								<%--									<p>--%>
								<%--										<em>个人信息</em>--%>
								<%--									</p>--%>
							</div>
						</div>
						<div class="row-fluid">
							<div class="col-md-12">
								<h3>
									培训记录
								</h3>
							</div>
						</div>
					</div>
					<div class="col-md-9">
						<div class="row-fluid">
							<div class="col-md-12">
								<h3>
									财务明细
								</h3>

								<div class="tabbable" id="tabs-992118">
									<ul class="nav nav-tabs">
										<li class="active">
											<a href="#panel-255555" data-toggle="tab">报销明细</a>
										</li>
										<li>
											<a href="#panel-982191" data-toggle="tab">借款明细</a>
										</li>
									</ul>
									<div class="tab-content">
										<div class="tab-pane active" id="panel-255555">
											<table class="table">
												<thead>
													<tr>
														<th>
															报销类别
														</th>
														<th>
															内容摘要
														</th>
														<th>
															金额
														</th>
														<th>
															币种
														</th>
														<th>
															收款单位（个人）
														</th>
														<th>
															付款方式
														</th>
														<th>
															报销日期
														</th>
														<th>
															报销编号
														</th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="baoxiaolist" id="baoxiaoList"
														status="baoxiaopageStatus">
														<s:if test="#baoxiaopageStatus.index<3">
															<tr>
																<td>
																	${baoxiaoList.baoxiaoStyle}
																</td>
																<td>
																	${baoxiaoList.baoxiaoDan.explain}
																</td>
																<td>
																	${baoxiaoList.baoxiaoDan.totalMoney}
																</td>
																<td>
																	${baoxiaoList.baoxiaoDan.currency}
																</td>
																<td>
																	${baoxiaoList.baoxiaoDan.shoukuanRen}
																</td>
																<td>
																	${baoxiaoList.baoxiaoDan.payStyle}
																</td>
																<td>
																	${baoxiaoList.baoxiaoDan.baoxiaoDate}
																</td>
																<td>
																	${baoxiaoList.baoxiaoDan.baoxiaoBarcode}
																</td>
															</tr>
														</s:if>
													</s:iterator>
												</tbody>
											</table>
											<a href="BaoXiaoDanAction!findBaoXiaoDanDetail.action">查看更多</a>
										</div>
										<div class="tab-pane" id="panel-982191">
											<table class="table">
												<thead>
													<tr>
														<th>
															借款单位
														</th>
														<th>
															合同编号
														</th>
														<th>
															科目
														</th>
														<th>
															用途
														</th>
														<th>
															借款金额
														</th>
														<th>
															是否借款
														</th>
														<th>
															借款状态
														</th>
														<th>
															备注
														</th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="paymentmaps" id="pageList"
														status="pageStatus">
														<s:if test="#pageStatus.index<3">
															<tr>
																<td>
																	${pageList.unitname1}
																</td>
																<td>
																	${pageList.number1}
																</td>
																<td>
																	${pageList.dept}
																</td>
																<td>
																	${pageList.pay_use}
																</td>
																<td>
																	${pageList.voucherMoney}
																</td>
																<td>
																	${pageList.isOk}
																</td>
																<td>
																	${pageList.detailStatus}
																</td>
																<td>
																	${pageList.remark}
																</td>
															</tr>
														</s:if>
													</s:iterator>
												</tbody>
											</table>
											<a href="">查看更多</a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="col-md-12">
								<h3>
									物品
								</h3>
								<div class="col-md-12">
									<div class="tabbable" id="tabs-386023">
										<ul class="nav nav-tabs">
											<li class="active">
												<a href="#panel-730639" data-toggle="tab">借用物品</a>
											</li>
											<li>
												<a href="#panel-699651" data-toggle="tab">归还物品</a>
											</li>
										</ul>
										<div class="tab-content">
											<div class="tab-pane active" id="panel-730639">
												<table class="table">
													<thead>
														<tr>
															<th>
																编号
															</th>
															<th>
																名称
															</th>
															<th>
																借出时间
															</th>
															<th>
																数量
															</th>
														</tr>
													</thead>
													<tbody>
														<s:iterator value="borrowlist" id="borrowList"
															status="pageStatus3">
															<s:if test="#pageStatus3.index<3">
																<tr>
																	<td>
																		${borrowList.number}
																	</td>
																	<td>
																		${borrowList.matetag}
																	</td>
																	<td>
																		${borrowList.date}
																	</td>
																	<td>
																		${borrowList.num}
																	</td>
																</tr>
															</s:if>
														</s:iterator>
													</tbody>
												</table>
												<a href="borrow_queryBorrowByCondition.action?vobo.cardId=${user.cardId}">查看更多</a>
											</div>
											<div class="tab-pane" id="panel-699651">
												<table class="table">
													<thead>
														<tr>
															<th>
																编号
															</th>
															<th>
																名称
															</th>
															<th>
																归还时间
															</th>
															<th>
																数量
															</th>
														</tr>
													</thead>
													<tbody>
														<s:iterator value="alsolist" id="alsoList"
															status="pageStatus2">
															<s:if test="#pageStatus2.index<3">
																<tr>
																	<td>
																		${alsoList.number}
																	</td>
																	<td>
																		${alsoList.name}
																	</td>
																	<td>
																		${alsoList.date}
																	</td>
																	<td>
																		${alsoList.num}
																	</td>
																</tr>
															</s:if>
														</s:iterator>
													</tbody>
												</table>
												<a href="borrow_queryBorrowByCondition.action?vobo.cardId=${user.cardId}">查看更多</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="col-md-12">
								<h3>
									工作记录
								</h3>
								<table class="table">
									<thead>
										<tr>
											<th>
												工号
											</th>
											<th>
												姓名
											</th>
											<th>
												总提交数量
											</th>
											<th>
												不合格数量
											</th>
											<th>
												工序数
											</th>
											<th>
												总时长(h)
											</th>
											<th>
												明细
											</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<s:iterator var="test" value="workrecords" status="status">
												<td>
													${test}
												</td>
											</s:iterator>
											<td>
												<a
													href="ProcessCollectAction_showpcList.action?pc.usercodes=<s:property value="workrecords[0]" />&tag=1">明细查看</a>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%--		<%@include file="/util/foot.jsp"%>--%>
	</body>
	<script type="text/javascript">
</script>
</html>
