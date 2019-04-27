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
	<body onload="createDept('interviewDept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				<div id="xitong"
					style="width: 100%; font-weight: bold; height: 50px;" align="left">
					<div
						style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
						align="left">

					</div>
					<div style="float: left; width: 45%; padding-top: 5px;"
						align="right">
						<a href="interviewLogAction_showList.action"
							style="color: #ffffff">刷新<br />(reflesh)</a>
					</div>
				</div>

				<div align="center">
					<h3>
						面试登记单管理
						<br />
						(Interview registration form management)
					</h3>
					<form action="interviewLogAction_showList.action" method="post">
						<table class="table" align="center">
							<tr>
								<td align="center">
									姓名（project name）：
									<input type="hidden" name="ccTag" value="${ccTag}" />
									<input type="text" name="interviewLog.name" />
								</td>
								<td align="center">
									录结取果（enroll result）：
									<input type="text" name="interviewLog.enroll_result" />
								</td>
							</tr>
							<s:if test="ccTag=='all'">
								<tr>
									<td align="center">
										应聘部门（candidates dept）：
										<select name="interviewLog.interviewDept" id="interviewDept">
											<option></option>
										</select>
									</td>
									<td align="center">
										性别（sex）：
										<input type="text" name="interviewLog.sex" />
									</td>
								</tr>
							</s:if>

							<tr>
								<td align="center" colspan="2">
									<input type="submit" style="width: 100px; height: 40px;"
										value="查询(select)" />
									<input type="button" style="width: 110px; height: 40px;"
										value="添加面试单(add)" onclick="add()" />
								</td>
							</tr>
						</table>
					</form>
					<!-- 内容 -->
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								序号
								<br />
								(num)
							</td>
							<td align="center">
								姓名
								<br />
								(name)
							</td>
							<td align="center">
								性别
								<br />
								(sex)
							</td>
							<td align="center" colspan="2">
								毕业学校
								<br />
								（finish school）
							</td>
							<td align="center">
								应聘部门
								<br />
								（candidates Dept）
							</td>
							<td align="center" colspan="2">
								通讯地址
								<br />
								（contact address）
							</td>
							<td align="center">
								联系方式
								<br />
								（tel）
							</td>
							<td align="center">
								工作经验
								<br />
								（experience Score）
							</td>
							<td align="center">
								录取结果
								<br />
								（enroll result）
							</td>
							<td align="center">
								添加时间
								<br />
								（Add Time）
							</td>
							<td align="center">
								审批状态
								<br />
								（Status）
							</td>
							<td align="center" colspan="2">
								操作
								<br />
								(Operation)
							</td>
						</tr>

						<s:iterator value="interviewLogList" id="interl"
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
									<font color="#000000"></font>
								</s:if>
								<s:else>
									<font color="#ff0000"></font>
								</s:else>
								<s:property value="#pageStatus.index+1" />

							</td>
							<td>
								${interl.name}
							</td>
							<td>
								${interl.sex}
							</td>
							<td align="center" colspan="2">
								${interl.finishSchool}
							</td>
							<td>
								${interl.interviewDept}
							</td>
							<td align="center" colspan="2">
								${interl.contactAddress}
							</td>
							<td>
								${interl.tel}
							</td>
							<td>
								${interl.experienceScore}
							</td>
							<td>
								${interl.enroll_result}
							</td>
							<td>
								${interl.interviewAddTime}
							</td>
							<td>
							<s:if
								test="#interl.inter_status=='已入职'&&#interl.inter_status!=null">${interl.inter_status}</s:if>
								<s:else>
									${interl.interStatus}
								</s:else>
							</td>
							<td colspan="2">
								<s:if test="#interl.epId!=null">
									<a
										href="CircuitRunAction_findAduitPage.action?id=${interl.epId}">审批动态</a>
								</s:if>
								<s:if
									test="#interl.epId==null">
								<a 
									href="interviewLogAction_toupdate.action?interviewLog.id=${interl.id}">评价</a>
								</s:if>
								<s:if
									test="#interl.inter_status=='待测试'&&#interl.inter_status!=null">
									<a
										href="ProvisionAction!findAllProvisionMsd.action?provision.provisionStatus=mst&interviewLog.id=${interl.id}">/<font
										color="red">填写测试单</font> </a>
								</s:if>
								<s:if
									test="#interl.inter_status=='待评价'||#interl.inter_status=='yes'||#interl.inter_status=='no'||#interl.inter_status=='已入职'&&#interl.inter_status!=null">
									<a
										href="interviewQuizzesAction_selectId.action?interviewLog.id=${interl.id}">/查看测试单</a>
								</s:if>
								<s:if
									test="#interl.epId==null">
									<a onclick="return window.confirm('您将删除数据，是否继续?')"
									href="interviewLogAction_delete.action?interviewLog.id=${interl.id}&cpage=${cpage}">/删除</a>
								</s:if>
							</td>

						</s:iterator>

						<tr>
							<s:if test="errorMessage==null">
								<td colspan="14" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="14" align="center" style="color: red">
									${errorMessage}

								</td>
							</s:else>
						</tr>
						<s:if test="successMessage=='addsuccess'">
							<tr>
								<td colspan="14" align="center" style="color: red">
									添加成功
								</td>
							</tr>
						</s:if>
						<s:if test="successMessage=='updatesuccess'">
							<tr>
								<td colspan="14" align="center" style="color: red">
									修改成功
								</td>
							</tr>
						</s:if>
						<s:if test="successMessage=='deletesuccess'">
							<tr>
								<td colspan="14" align="center" style="color: red">
									删除成功
								</td>
							</tr>
						</s:if>
					</table>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function add(){
		window.location.href = "interviewLogAction_toadd.action";
		}
		function update(id){
		window.location.href = "interviewLogAction_toupdate.action?interviewLog.id=" + id;
		}
		function todelete(id,cpage){
		window.location.href = "interviewLogAction_delete.action?interviewLog.id=" + id +"&cpage="+cpage;
		}
		</SCRIPT>
	</body>
</html>
