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
	<body onload="createDept('repairdepartment');">
		<div id="bodyDiv" align="center"
			style="filter: Alpha(Opacity =75); display: none; position: absolute; width: 100%; background: #000; opacity: 1;">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title"></span>
						</td>
						<td align="right">
							<img alt="" src="images/closeImage.png" width="30" height="32"
								onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
				</div>
				<div id="addProductPrice"
					style="background-color: #ffffff; width: 100%;">
					<form action="RepairAction!addrepairsingle.action" method="post"
						onsubmit="return check()">
						<table border="1" width="100%" class="table">
							<tr>
								<td colspan="20" align="center"
									style="font-family: 微软雅黑; font-weight: bold;">
									添加报修信息

								</td>
							</tr>
							<tr>
								<th>
									工号
								</th>
								<td>
									<input name="repair.jobnumber" value="${Users.code}"
										readonly="readonly" />
								</td>

								<th>
									报修人
								</th>
								<td>
									<input name="repair.name" value="${Users.name}"
										readonly="readonly" />

								</td>
								<th>
									报修部门
								</th>
								<td>

									<input name="repair.department" value="${Users.dept}"
										readonly="readonly" />
								</td>
							</tr>
							<tr>
							<th>
									维修部门
								</th>
								<td>
						<select id="repairdepartment"  name="repair.repairdept" >
							<option selected="selected" value="">
								选择部门
							</option>
							<s:iterator id="cu" value="list">
								<option value="${cu.ta_dept}" >
									${cu.ta_dept}
								</option>
							</s:iterator>
						</select>
								</td>
								<th>
									报修类别
								</th>

								<td>
								<select id="category" name="repair.category" style="width: 160px;" >
								<option></option>
<!--									<select id="category" name="repair.category" style="width: 160px;"-->
<!--										onchange="javascript:document.getElementById('personalnominee').options.length=1;createDept('personalnominee','RepairAction!finAllPeople.action?pageStatus='+this.value)">-->
<!--										<option value="信息系统类">-->
<!--											信息系统类-->
<!--										</option>-->
<!--										<option value="PC电脑打印机类">-->
<!--											PC电脑打印机类-->
<!--										</option>-->
<!--										<option value="综合公共信息类">-->
<!--											综合公共信息类-->
<!--										</option>-->
<!--										<option value="网络通讯类">-->
<!--											网络通讯类-->
<!--										</option>-->
<!--										<option value="设备维修">-->
<!--											设备维修-->
<!--										</option>-->

									</select>

								</td>

								<th>
									修理人
								</th>
								<td>
									<select id="personalnominee" name="repair.personalnominee">
										<option></option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									报修物品
								</th>
								<td>
									<input type="text" name="repair.devicename"
										onkeyup="this.value=this.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3')" />
								</td>
								<th>
									报修故障:
								</th>
								<td>

									<input type="text" style="width: 250px; height: 80px;"
										name="repair.repairfailed" />
								</td>

							</tr>

							<tr>
								<td colspan="6" align="center">
									<input type="submit"  id="isok"  value="添加"
										style="width: 100px; height: 50px;">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" value="重置"
										style="width: 100px; height: 50px;">

								</td>


							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>

		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			
			<div align="center">

				<div align="center">
					<s:if test="pageStatus==null">
						<form action="RepairAction!findAll.action" method="post"
							style="margin: 0px;">
							<table width="100%" border="1">
								<tr>
									<th colspan="6">
										报修查询管理
									</th>
								</tr>
								<tr>
									<td align="center">
										报修人:
										<input  type="text" name="repair.name"  />

									</td>
									<td align="center">
										修理人:
									 <input type="text" name="repair.repairers" >
									</td>
									
								</tr>
								
								<tr>
									<td align="center">
										报修时间:
										<input class="Wdate" type="text" name="repair.repairtime"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd ',skin:'whyGreen'})" />

									</td>
									<td align="center">
										状态:
									<select name="repair.status">
										<option value=""></option>
											<option value="待确定">待确定</option>
											<option value="待指派">待指派</option>
											<option value="维修中">维修中</option>
											<option value="修复完成">修复完成</option>
									</select>
									</td>
									
								</tr>
								<tr>
									<td colspan="6" align="center">
										<input value="查询" type="submit"
											style="width: 100px; height: 50px">
										<s:if test="pageStatus==null">
											<input type="button" onclick="chageDiv('block');"
												value="报修" style="width: 100px; height: 50px" />

										</s:if>
									</td>
									
									
								</tr>
							</table>
						</form>
					</s:if>
					
					<table width="100%" border="0" style="border-collapse: collapse;"
						class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								工号
							</th>
							<th align="center">
								报修人
							</th>
							<th align="center">
								报修部门
							</th>
<!--							<th align="center">-->
<!--								报修设备-->
<!--							</th>-->
							<th align="center">
								报修时间
							</th>
							<th align="center">
								类别
							</th>
							<th align="center">
								状态
							</th>
							<th align="center">
								修理反馈
							</th>
							<th align="center">
								修理人
							</th>
							<th>
								维修部门
							</th>
							<th align="center">
								修复时间
							</th>
							<th align="center">
								确认倒计时
							</th>

							<th align="center">
								操作
							</th>
						</tr>

						<s:if test="pageStatus==null">
							<tr bgcolor="green">
								<th colspan="13" align="center">
									<font color="#ffffff">待确定信息</font>
								</th>
							</tr>
							<s:iterator value="repairList1" id="pageRepair"
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
									<s:if test="#pageStatus.index%2==1">
										<font>
									</s:if>
									<s:else>
										<font color="red">
									</s:else>
									<s:property value="#pageStatus.index+1" />
									</font>
								</td>

								<td>
									${pageRepair.jobnumber}
								</td>
								<td>
									${pageRepair.name}
								</td>
								<td>
									${pageRepair.department}

								</td>

<!--								<td>-->
<!--									${pageRepair.devicename}-->
<!--								</td>-->
								<td>
									${pageRepair.repairtime}
								</td>
								<td>
									${pageRepair.category}
								</td>
								<td>
									${pageRepair.status}
								</td>
								<td>
									${pageRepair.repairfeedback}
								</td>
								<td>
									${pageRepair.personalnominee}
								</td>
								<td>
									${pageRepair.repairdept}
								</td>
								<td>
									${pageRepair.timetorepair}
								</td>
								<td>
									${pageRepair.countdowntime}
								</td>
								<td height="50px">

									<a href="RepairAction!initupRepair.action?id=${pageRepair.id}">修改</a>/
									<a
										href="RepairAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>/
									<a href="RepairAction!delSubmit.action?id=${pageRepair.id}"
										onClick="return window.confirm('确认要删除吗？')">删除</a>
								</td>
								<s:if test="#pageStatus.last">

								</s:if>
							</s:iterator>
							<tr bgcolor="green">
								<th colspan="13" align="center">
									<font color="#ffffff">待指派信息</font>
								</th>
							</tr>
							<s:iterator value="repairList3" id="pageRepair"
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
									<s:if test="#pageStatus.index%2==1">
										<font>
									</s:if>
									<s:else>
										<font color="red">
									</s:else>
									<s:property value="#pageStatus.index+1" />
									</font>
								</td>

								<td>
									${pageRepair.jobnumber}
								</td>
								<td>
									${pageRepair.name}
								</td>
								<td>
									${pageRepair.department}

								</td>

<!--								<td>-->
<!--									${pageRepair.devicename}-->
<!--								</td>-->
								<td>
									${pageRepair.repairtime}
								</td>
								<td>
									${pageRepair.category}
								</td>
								<td>
									${pageRepair.status}
								</td>
								<td>
									${pageRepair.repairfeedback}
								</td>
								<td>
									${pageRepair.personalnominee}
								</td>
								<td>
									${pageRepair.repairdept}
								</td>
								<td>
									${pageRepair.timetorepair}
								</td>
								<td>
									${pageRepair.countdowntime}
								</td>
								<td>

									<a href="RepairAction!initupRepair.action?id=${pageRepair.id}">修改</a>/
									<a
										href="RepairAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>/
									<a href="RepairAction!delSubmit.action?id=${pageRepair.id}"
										onClick="return window.confirm('确认要删除吗？')">删除</a>
								</td>
								<s:if test="#pageStatus.last">
									</tr>
								</s:if>
							</s:iterator>
							<tr bgcolor="green">
								<th colspan="13" align="center">
									<font color="#ffffff">维修中信息</font>

								</th>
							</tr>
							<s:iterator value="repairList2" id="pageRepair"
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
									<s:if test="#pageStatus.index%2==1">
										<font>
									</s:if>
									<s:else>
										<font color="red">
									</s:else>
									<s:property value="#pageStatus.index+1" />
									</font>
								</td>
								<td>
									${pageRepair.jobnumber}
								</td>
								<td>
									${pageRepair.name}
								</td>
								<td>
									${pageRepair.department}

								</td>

<!--								<td>-->
<!--									${pageRepair.devicename}-->
<!--								</td>-->
								<td>
									${pageRepair.repairtime}
								</td>
								<td>
									${pageRepair.category}
								</td>
								<td>
									${pageRepair.status}
								</td>
								<td>
									${pageRepair.repairfeedback}
								</td>
								<td>
									${pageRepair.personalnominee}
								</td>
								<td>
									${pageRepair.repairdept}
								</td>
								<td>
									${pageRepair.timetorepair}
								</td>
								<td>
									${pageRepair.countdowntime}
								</td>
								<td>


									<a
										href="RepairAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>

								</td>
								<s:if test="#pageStatus.last">
									</tr>
								</s:if>


							</s:iterator>
							<tr bgcolor="green">
								<th colspan="13" align="center">
									<font color="#ffffff">修复确认信息</font>
								</th>
							</tr>
							<s:iterator value="repairList4" id="pageRepair"
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
									<s:if test="#pageStatus.index%2==1">
										<font>
									</s:if>
									<s:else>
										<font color="red">
									</s:else>
									<s:property value="#pageStatus.index+1" />
									</font>
								</td>
								<td>
									${pageRepair.jobnumber}
								</td>
								<td>
									${pageRepair.name}
								</td>
								<td>
									${pageRepair.department}

								</td>

<!--								<td>-->
<!--									${pageRepair.devicename}-->
<!--								</td>-->
								<td>
									${pageRepair.repairtime}
								</td>
								<td>
									${pageRepair.category}
								</td>
								<td>
									${pageRepair.status}
								</td>
								<td>
									${pageRepair.repairfeedback}
								</td>
								<td>
									${pageRepair.personalnominee}
								</td>
								<td>
									${pageRepair.repairdept}
								</td>
								<td>
									${pageRepair.timetorepair}
								</td>
								<td style="color: red; font-size: 18px; font-weight: bolder;">
									${pageRepair.countdowntime}分钟
								</td>
								<td>
									<a
										href="RepairAction!upremarkRepair.action?id=${pageRepair.id}">确认修复</a>

								</td>
								<s:if test="#pageStatus.last">
									</tr>
								</s:if>

							</s:iterator>
							<tr bgcolor="green">
								<th colspan="13" align="center">
									<font color="#ffffff">修复完成信息</font>
								</th>
							</tr>
							<s:iterator value="repairList" id="pageRepair"
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
									<s:if test="#pageStatus.index%2==1">
										<font>
									</s:if>
									<s:else>
										<font color="red">
									</s:else>
									<s:property value="#pageStatus.index+1" />
									</font>
								</td>

								<td>
									${pageRepair.jobnumber}
								</td>
								<td>
									${pageRepair.name}
								</td>
								<td>
									${pageRepair.department}

								</td>

<!--								<td>-->
<!--									${pageRepair.devicename}-->
<!--								</td>-->
								<td>
									${pageRepair.repairtime}
								</td>
								<td>
									${pageRepair.category}
								</td>
								<td>
									${pageRepair.status}
								</td>
								<td>
									${pageRepair.repairfeedback}
								</td>
								<td>
									${pageRepair.personalnominee}
								</td>
								<td>
									${pageRepair.repairdept}
								</td>
								<td>
									${pageRepair.repairpersontime}
								</td>
								<td>
									${pageRepair.countdowntime}
								</td>
								<td>


									<a
										href="RepairAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>

								</td>
								<s:if test="#pageStatus.last">
								</s:if>
							</s:iterator>
						</s:if>
						<s:else>
							<s:iterator value="repairList" id="pageRepair"
								status="repairPage">
								<s:if test="#repairPage.first">
									<s:if test='pageStatus=="daiqueding"'>
										<tr bgcolor="green">
											<th colspan="14" align="center">
												<font color="#ffffff">开始维修信息</font>
											</th>
										</tr>
									</s:if>
									<s:else>
										<tr bgcolor="green">
											<th colspan="14" align="center">
												<font color="#ffffff">指派维修人信息</font>
											</th>
										</tr>
									</s:else>
								</s:if>
								<s:if test="#repairPage.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>

								<td>
									<s:if test="#repairPage.index%2==1">
										<font>
									</s:if>
									<s:else>
										<font color="red">
									</s:else>
									<s:property value="#repairPage.index+1" />
									</font>
								</td>
								<td>
									${pageRepair.jobnumber}
								</td>
								<td>
									${pageRepair.name}
								</td>
								<td>
									${pageRepair.department}

								</td>

<!--								<td>-->
<!--									${pageRepair.devicename}-->
<!--								</td>-->
								<td>
									${pageRepair.repairtime}
								</td>
								<td>
									${pageRepair.category}
								</td>
								<td>
									${pageRepair.status}
								</td>
								<td>
									${pageRepair.repairfeedback}
								</td>
								<td>
									${pageRepair.personalnominee}
								</td>
								<td>
									${pageRepair.repairdept}
								</td>
								<td>
									${pageRepair.timetorepair}
								</td>
								<td>
									${pageRepair.countdowntime}
								</td>
								<td>
									<s:if test='status=="待确定"'>
										<a
											href="RepairAction!condition.action?id=${pageRepair.id}&pageStatus=${pageStatus}" >开始维修</a>/
						
									<a
											href="RepairAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>
									</s:if>
									<s:else>
										<a
											href="RepairAction!initupRepair.action?id=${pageRepair.id}&pageStatus=${pageStatus}" >指派维修人</a>/
									<a href="RepairAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>
									</s:else>
								</td>
							</s:iterator>
							<s:iterator value="repairList1" id="pageRepair"
								status="repairStatus">
								<s:if test="#repairStatus.first">
									<s:if test="errorMessage=='zhipai'">
										<tr bgcolor="green">
											<th colspan="13" align="center">
												<font color="#ffffff">修改维修人信息</font>
											</th>
										</tr>
									</s:if>
									<s:else>
										<tr bgcolor="green">
											<th colspan="13" align="center">
												<font color="#ffffff">维修中信息</font>
											</th>
										</tr>
									</s:else>
								</s:if>
								<s:if test="#repairStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:if test="#repairStatus.index%2==1">
										<font>
									</s:if>
									<s:else>
										<font color="red">
									</s:else>
									<s:property value="#repairStatus.index+1" />
									</font>
								</td>
								<td>
									${pageRepair.jobnumber}
								</td>
								<td>
									${pageRepair.name}
								</td>
								<td>
									${pageRepair.department}

								</td>

<!--								<td>-->
<!--									${pageRepair.devicename}-->
<!--								</td>-->
								<td>
									${pageRepair.repairtime}
								</td>
								<td>
									${pageRepair.category}
								</td>
								<td>
									${pageRepair.status}
								</td>
								<td>
									${pageRepair.repairfeedback}
								</td>
								<td>
									${pageRepair.personalnominee}
								</td>
								<td>
									${pageRepair.repairdept}
								</td>
								<td>
									${pageRepair.timetorepair}
								</td>
								<td>
									${pageRepair.countdowntime}
								</td>
								<td>
									<s:if test="errorMessage=='zhipai'">
										<a
											href="RepairAction!initupRepair.action?id=${pageRepair.id}&pageStatus=${pageStatus}">修改维修人</a>
									</s:if>
									<s:else>
										<s:if test='status=="维修中"'>
											<a
												href="RepairAction!remarkRepair.action?id=${pageRepair.id}&pageStatus=${pageStatus}">维修确认</a>/
										</s:if>
										<s:else>
											<a
												href="RequisitionAction!updateSubmit.action?id=${pageRequisition.id}&pageStatus=${pageStatus}"
												onClick="return window.confirm('确认通过吗？')">通过</a>/
									<a
												href="RequisitionAction!updateSubmit.action?id=${pageRequisition.id}&pageStatus=back${pageStatus}"
												onClick="return window.confirm('你确定要打回？')">打回</a>
										</s:else>
										<a
											href="RepairAction!findByclientManagement.action?id=<s:property value="id" />">明细</a>
									</s:else>
								</td>
							</s:iterator>
						</s:else>
						<tr>
							<td colspan="13" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</tr>
					</table>
					<br>
				</div>
			</div>
			<%@include file="/util/foot.jsp"%>
			</center>
		</div>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js"></script>
		<script type="text/javascript">
		$(function(){
		getClass();
		//getUser();
		//校验部门不能为空
		$("#isok").click(function(){
		var dept = $("#repairdepartment").val();
		if(dept==""){
			alert("请选择维修部门！");
				return false;
		}
		
		});
		 
		//点击报修部门时触发的事件
		$("#repairdepartment").change(function(){
				getClass();
			});
				//点击报修类别时触发的事件
			$("#category").change(function(){
				getUser();//调用点击报修类别时触发的事件
			});
		
		})
		function getClass(){
			var repairdepartment = $("#repairdepartment").val();
			var category = $("#category").val();
			$.ajax( {
				url : "RepairAction!isChange.action",
				type : 'post',
				dataType : 'json',
				cache : false,//防止数据缓存
				data : {
					"repairdepartment" : repairdepartment
				},
				success : function(data) {
				$("#category").empty();//清空
				$("#personalnominee").empty();//清空
					$.each(data.data,function(i){
						$("#category").append("<option value='" + data.data[i].category+ "'>"+ data.data[i].category +"</option>");
					});
					getUser();
				},
				error : function() {
					alert("服务器异常!");
				}
			});
		}
		
		//点击报修类别时触发的事件
		function getUser(){
			var repairdepartment = $("#repairdepartment").val();
			var category = $("#category").val();
			if(repairdepartment==""){
				repairdepartment="";
				category="";
			} 
			
				$.ajax( {
				url : "RepairAction!isChange1.action",
				type : 'post',
				dataType : 'json',
				cache : false,//防止数据缓存
				data : {
					"category" : category,
					"repairdepartment":repairdepartment
				},
				success : function(data) {
				$("#personalnominee").empty();//清空
				$("#personalnominee").append("<option></option>");
				$.each(data.data,function(i){
					if(data.data[i].repairname!=""){
					$("#personalnominee").append("<option value='" + data.data[i].repairname+ "'  >"+ data.data[i].repairname +"</option>");
					}else{
						$("#personalnominee").append("<option value=''>请选修理人</option>");
					}
				});
				},
				error : function() {
					alert("服务器异常!");
				}
			});
		}
			
		</script>
		
	</body>
	 
</html>
