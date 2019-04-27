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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="OSWorkAction!findJIezhagnList.action" method="post">
					<table class="table" style="width: 85%;">
						<tr>
							<td>
								合同号：
								<select name="osWork.contractNO" id="contractNO"
									style="width: 140px;"
									onMouseOver="createDept('contractNO','OSWorkAction!selectItem.action?tag=contractNO')">
									<option value=""></option>
									<option value="${osWork.contractNO}" selected="selected">
										${osWork.contractNO}
									</option>
								</select>

							</td>
							<td>
								批次：
								<select name="osWork.lotId" id="lotId" style="width: 140px;"
									onMouseOver="createDept('lotId','OSWorkAction!selectItem.action?tag=lotId')">
									<option value=""></option>
									<option value="${osWork.lotId}" selected="selected">
										${osWork.lotId}
									</option>
								</select>
							</td>
							<td>
								件号：
								<select name="osWork.markID" id="markID" style="width: 140px;"
									onMouseOver="createDept('markID','OSWorkAction!selectItem.action?tag=markID')">
									<option value=""></option>
									<option value="${osWork.markID}" selected="selected">
										${osWork.markID}
									</option>
								</select>
							</td>
							<td rowspan="3" align="center">
								<input type="submit" style="width: 50px; height: 70px;"
									value="查询" />
								&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								名称：
								<select name="osWork.partName" id="partName"
									style="width: 140px;"
									onMouseOver="createDept('partName','OSWorkAction!selectItem.action?tag=partName')">
									<option value=""></option>
									<option value="${osWork.partName}" selected="selected">
										${osWork.partName}
									</option>
								</select>
							</td>

							<td>
								日期从：
								<input class="Wdate" type="text" name="startDate"
									value="${ startDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />

							</td>
							<td>
								<span style="width: 21px;"></span>到
								<span style="width: 21px;"></span>：
								<input class="Wdate" type="text" name="endDate"
									value="${ endDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<td>
								条码：
								<input type="text" name="osWork.number"
									value="${osWork.number }" />
							</td>
							<td>
								外委商：
								<select name="osWork.outScourceComp   style="
									width:140px;" id="outScourceComp"
									onMouseOver="createDept('outScourceComp','OSWorkAction!selectItem.action?tag=outScourceComp')">
									<option value=""></option>
									<option value="${osWork.outScourceComp}" selected="selected">
										${osWork.outScourceComp}
									</option>
								</select>
							</td>
							<td>
								班组：
								<select name="osWork.dept" id="dept" style="width: 140px;"
									onMouseOver="createDept('dept','OSWorkAction!selectItem.action?tag=dept')">
									<option value=""></option>
									<option value="${osWork.dept}" selected="selected">
										${osWork.dept}
									</option>
								</select>
							</td>
						</tr>
						</form>
						<form action="OSWorkAction!findJiezhangPrint.action" method="post">
							<table class="table" style="width: 95%;">
								<tr>
									<td align="right" colspan="10">
										<font color="red">共选择 <label id="peopleLabel">
												${count}
											</label> <input type="hidden" id="propleText" name="peopleNum"
												style="width: 20px;" readonly="readonly"> 条外委记录</font>
										<input type="submit" value="打印"
											style="width: 60px; height: 40px;" align="top">
										<br>
										<br>
									</td>
								</tr>
								<tr bgcolor="#c0dcf2" height="30px"
									style="border-collapse: separate;">
									<th align="center">
										序号
									</th>
									<th align="center">
										批次
									</th>
									<th align="center">
										零件号
									</th>
									<th align="center">
										品名
									</th>
									<th align="center">
										外委数量
									</th>
									<th align="center">
										接收数量
									</th>
									<th align="center">
										外委厂家
									</th>
									<th align="center">
										合同号
									</th>
									<th align="center">
										外委工序
									</th>
									<th align="center">
										<input type="checkbox" id="checkAll"
											onclick="chageAllCheck(this)">
										全选
										</td>
									</th>
								</tr>

								<s:if test="{list.size()>0}">
									<s:iterator value="list" status="se" id="osw">
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
											${osw.lotId}
										</td>
										<td>
											${osw.markID}
										</td>
										<td>
											${osw.partName}
										</td>
										<td>
											${osw.outSourceCount }
										</td>
										<td>
											${osw.receiveCount}
										</td>
										<td>
											${osw.outScourceComp}
										</td>
										<td>
											${osw.contractNO}
										</td>
										<td>
											<s:iterator value="#osw.processInforList" id="os">
												${os.processNO};
										</s:iterator>
										</td>
										<td>
											<input type="checkbox"
												id="sell<s:property value="#se.index"/>" name="osJiesuan"
												value="${osw.id}" onclick="chageNum(this)">
										</td>

										</tr>
									</s:iterator>
									
									<tr>
										<td colspan="10" align="right">
											共
											<s:property value="total" />
											页 第
											<s:property value="cpage" />
											页
											<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
												styleClass="page" theme="number" />
										<input type="checkbox" id="checkAll2"
												onclick="chageAllCheck(this)">
											全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
									<tr>
										<td align="right" colspan="10">
											<font color="red">共选择 <label id="peopleLabel2">
													${count}
												</label> <input type="hidden" id="propleText" name="peopleNum"
													style="width: 20px;" readonly="readonly"> 条外委记录</font>
													
											<input type="submit" value="打印"
												style="width: 60px; height: 40px;" align="top">
											<br>
											<br>
								</s:if>
								<s:else>
									<tr>
										<td style="font-size: 10px; color: red;">
											对不起，没有需要结算的外委工序信息
										</td>
									</tr>
								</s:else>
							</table>
						</form>
						</div>
						<br>
						</div>
						<%@include file="/util/foot.jsp"%>
						</center>
						<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
						<script type="text/javascript">
							var oldObj;
							var oldObj2;
							function chageModule(obj, obj2) {
								if (obj.id != "module1") {
									document.getElementById("module1").className = "tag_1";
									document.getElementById("module1_1").style.display = "none";
								}
								if (oldObj != null) {
									oldObj.className = "tag_1";
									document.getElementById("module1_" + oldObj2).style.display = "none";
								}
							
								obj.className = "tag_2";
								document.getElementById("module1_" + obj2).style.display = "block";
							
								oldObj = obj;
								oldObj2 = obj2;
							}
							function chageBgcolor(obj) {
								obj.style.background = "#c0dcf2";
							}
							function outBgcolor(obj, oldColor) {
								obj.style.background = oldColor;
							}
							
							function chageAllCheck(obj) {
								var inputs = document.getElementsByTagName("input");
								for ( var i = 0; i < inputs.length; i++) {
									if (inputs[i].type == "checkbox") {
										var checkBox = inputs[i];
										if (checkBox.checked != obj.checked) {
											checkBox.checked = obj.checked;
											if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
												chageNum(checkBox);
											}
										}
									}
								}
							}
							var num = "${count}";
							if (num == "") {
								num = 0;
							}
							function chageNum(obj) {
								var check = obj;
								var checkAll = document.getElementById("checkAll");
								var checkAll2 = document.getElementById("checkAll2");
								if (check.checked == true) {
									var inputs = document.getElementsByTagName("input");
									var status = true;
									for ( var i = 0; i < inputs.length; i++) {
										if (inputs[i].type == "checkbox") {
											var checkBox = inputs[i];
											if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
												if (checkBox.checked == false) {
													status = false;
													break;
												}
											}
										}
									}
									if (status == true) {
										checkAll.checked = true;
										checkAll2.checked = true;
									}
									num++;
								} else if (num == 0 && check.checked == false) {
									num = 0;
								} else {
									if (checkAll.checked == true || checkAll2.checked == true) {
										checkAll.checked = false;
										checkAll2.checked = false;
									}
									num--;
								}
								document.getElementById("peopleLabel").innerHTML = num;
								document.getElementById("peopleLabel2").innerHTML = num;
							}
							</script>
	</body>
</html>
