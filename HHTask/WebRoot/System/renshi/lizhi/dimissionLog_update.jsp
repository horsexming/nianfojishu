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
		<div align="center" id="gongneng">
			<div align="center"
				style="width: 70%; height: 100%; border: 0px solid #000000;">
				<form action="dimissionLogAction_update.action" method="post"
					onsubmit="return dimissAdd()">
					<table class="table" align="center">
						<tr>
							<th align="center" colspan="6" style="size: 20pt;">
								<img alt="loge" src="<%=path%>/images/zhaobiao.jpg"
									style="width: 30px; height: 30px;">
								修改离职申请单
								<input type="hidden" name="dimissionLog.id"
									value="${dimissionLog.id}" />
								<input type="hidden" name="tag" value="${tag}" id="ta1" />
							</th>
						</tr>
						<tr>
							<td colspan="2" align="left" style="border-right-width: 0px;">
								<h3>
									&nbsp;&nbsp;&nbsp;&nbsp;员工合同编号：${dimissionLog.contract_number}
								</h3>
							</td>
							<td colspan="4" align="right" style="border-left-width: 0px;">
								<h3>
									申请单编号：${dimissionLog.shenqing_number} &nbsp;&nbsp;&nbsp;&nbsp;
								</h3>
							</td>
						</tr>
						<tr>
							<th>
								申请人姓名
							</th>
							<th>
								部门
							</th>
							<th>
								岗位
							</th>
							<th colspan="2">
								本厂工作年限
							</th>
							<th>
								工号
							</th>
						</tr>
						<tr>
							<td align="center">
								<input type="text" name="dimissionLog.name" id="name"
									value="${dimissionLog.name}" readonly="readonly" />
							</td>
							<td align="center">
								<input type="text" name="dimissionLog.dept" id="dept"
									value="${dimissionLog.dept}" readonly="readonly" />
							</td>
							<td align="center">
								<input type="text" name="dimissionLog.job" id="job"
									value="${dimissionLog.job}" readonly="readonly" />
							</td>
								<td align="center" colspan="2">
									<input type="text" name="dimissionLog.year_term" id="year_term"
										readonly="readonly" value="${dimissionLog.year_term}" />
								</td>
							<td align="center">
								<input type="text" name="dimissionLog.code" id="code"
									value="${dimissionLog.code}" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th>
								离职原因
							</th>
							<s:if test="tag=='all'&&tag!=null">
								<td colspan="5">
									<textarea rows="5" cols="100%"
										name="dimissionLog.dimission_Reason" id="dimission_Reason"
										readonly="readonly">${dimissionLog.dimission_Reason}</textarea>
								</td>
							</s:if>
							<s:else>
								<td colspan="5">
									<textarea rows="5" cols="100%"
										name="dimissionLog.dimission_Reason" id="dimission_Reason">${dimissionLog.dimission_Reason}</textarea>
								</td>
							</s:else>
						</tr>

						<tr>
							<th>
								离职后去向
							</th>
							<s:if test="tag=='all'&&tag!=null">
								<td align="center" colspan="3" style="border-right-width: 0px;">
									<input type="text" name="dimissionLog.dimission_laterGo"
										id="dimission_laterGo" style="width: 100%;"
										readonly="readonly" value="${dimissionLog.dimission_laterGo}" />
								</td>
							</s:if>
							<s:else>
								<td align="center" colspan="3" style="border-right-width: 0px;">
									<input type="text" name="dimissionLog.dimission_laterGo"
										id="dimission_laterGo" style="width: 100%;"
										value="${dimissionLog.dimission_laterGo}" />
								</td>
							</s:else>
							<s:if test="dimissionLog.app_time!=null">
								<th>
									部门主管确认离职时间
								</th>
								<th>
									${dimissionLog.app_time}
								</th>
							</s:if>
							<s:else>
								<td colspan="2" style="border-left-width: 0px;"></td>
							</s:else>
						</tr>
						<%--<tr>
						
							<th>
								是否有遗留问题或劳动争议
							</th>
							<td colspan="5">
							<s:if test="dimissionLog.zhengyi_Status!=null&&dimissionLog.zhengyi_Status=='已填写'">
							${dimissionLog.naowuzhengyi} <input type="hidden" name="dimissionLog.naowuzhengyi" id="naowuzhengyi" value="${dimissionLog.naowuzhengyi}"/>
							</s:if>
							<s:else>
								<select name="dimissionLog.naowuzhengyi" id="naowuzhengyi"
									class="zhengyi" onclick="zhengyi('${naowuzhengyi}')">
								<option value="${dimissionLog.naowuzhengyi}">
										${dimissionLog.naowuzhengyi}
									</option>
									<option value="否">
										否
									</option>
									<option value="是">
										是
									</option>
								</select>
							</s:else>
								<textarea rows="3" cols="90%" title="请填写争议内容"
									name="dimissionLog.zhengyi_content" id="zhengyi_content" style="display: none">${dimissionLog.zhengyi_content}</textarea>
							</td>
						</tr>
						--%>
						<tr>
							<th colspan="6">
								请确认以下条款
							</th>
						</tr>
						<tr>
							<s:iterator value="provisionlist" id="provil" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="left">
								</s:if>
								<s:else>
									<tr align="left">
								</s:else>
								<td align="left" colspan="6">
									&nbsp;&nbsp;&nbsp;&nbsp;${provil.content}
								</td>
							</s:iterator>
						</tr>
						<s:if test="tag!='all'">
							<tr>
								<td align="center" colspan="6">
									本人确定:
									<s:if
										test="dimissionLog.confirm=='同意'&&dimissionLog.confirm!=null">
									同意
									<input type="hidden" name="dimissionLog.confirm" id="confirm"
											value="同意">
									</s:if>
									<s:else>
										<input type="checkbox" name="dimissionLog.confirm"
											id="confirm" value="同意">
									</s:else>
								</td>
							</tr>
						</s:if>
						<s:if test="tag=='all'&&tag!=null">
							<tr>
								<th style="height: 40px;">
									本人签字
								</th>
								<td colspan="5" style="height: 30px;"></td>
							</tr>
							<tr>
								<th colspan="2" style="height: 40px;">
									是否需要离职体检
									<br />
									(如不需要做体检请本人确认签字)
								</th>
								<td colspan="4" style="height: 40px;">
									<textarea rows="2" cols="85" name="dimissionLog.tijian"
										id="tijian">${dimissionLog.tijian}</textarea>
								</td>
							</tr>
							<tr>
								<th colspan="2" style="height: 30px;">
									是否接受过工厂外派培训
									<br />
									并签有培训协议
								</th>
								<td colspan="4" style="height: 30px;">
									<textarea rows="2" cols="85" name="dimissionLog.peixunxieyi"
										id="peixunxieyi">${dimissionLog.peixunxieyi}</textarea>
								</td>
							</tr>
							<tr>
								<th colspan="2" style="height: 30px;">
									是否与工厂签有保密
									<br />
									及竟业禁止协议
								</th>
								<td colspan="4" style="height: 30px;">
									<textarea rows="2" cols="85" name="dimissionLog.baomi"
										id="baomi">${dimissionLog.baomi}</textarea>
								</td>
							</tr>
							<tr>
								<th colspan="2" style="height: 30px;">
									其它需要补充或说明的事项
								</th>
								<td colspan="4" style="height: 30px;">
									<textarea rows="2" cols="85" name="dimissionLog.buchong"
										id="buchong">${dimissionLog.buchong}</textarea>
								</td>
							</tr>
							<tr>
								<th>
									是否添加协议
								</th>
								<td colspan="5">
									<select name="dimissionLog.naowuzhengyi" id="naowuzhengyi"
										class="zhengyi">
										<option value="${dimissionLog.naowuzhengyi}">
											${dimissionLog.naowuzhengyi}
										</option>
										<option value="否">
											否
										</option>
										<option value="是">
											是
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<th style="height: 60px;">
									备注
								</th>
								<td colspan="5" style="height: 30px;">
									<textarea rows="3" cols="100"
										name="dimissionLog.zhengyi_content">${dimissionLog.zhengyi_content}</textarea>
								</td>
							</tr>
							<tr>
								<th style="height: 40px;">
									人力资源签字
								</th>
								<td colspan="5" style="height: 30px;">
									<select name="dimissionLog." id="">
										<option value="${dimissionLog.naowuzhengyi}">
											${dimissionLog.naowuzhengyi}
										</option>
										<option value="">
										</option>
										<option value="同意 ">
											同意
										</option>
									</select>
								</td>
							</tr>
						</s:if>


						<tr>
							<td align="center" colspan="6">
								<s:if test="tag=='all'&&tag!=null">
									<input type="submit" value="确定" 
									onclick="return window.confirm('人事项填写后将无法修改，是否继续?')"
										style="width: 80px; height: 40px;">
								</s:if>
								<s:else>
									<input type="submit" value="修改"
										style="width: 80px; height: 40px;">
								</s:else>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="重置"
									style="width: 80px; height: 40px;">
							</td>
						</tr>

					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

function disable() {
	document.getElementById("btnlizhi").disabled = true
}
function enable() {
	document.getElementById("btnlizhi").disabled = false
}

function dimissAdd() {
	if ($("#ta1").val() == "all") {
		if (!validateText("tijian", "是否需要离职体检")) {
			return false;
		}
		if (!validateText("peixunxieyi", "是否接受过工厂外派培训")) {
			return false;
		}
		if (!validateText("baomi", "是否与工厂签有保密")) {
			return false;
		}
		if (!validateText("buchong", "其它需要补充或说明的事项")) {
			return false;
		}
		if (!validateText("naowuzhengyi", "是否有协议")) {
			return false;
		} else {
			alert("确认后，人事填项将无法修改，是否继续?");
		}
	} else {
		if (!validateText("job", "岗位")) {
			return false;
		}
		if (!validateText("year_term", "本厂工作年限")) {
			return false;
		}
		if (!validateText("dimission_Reason", "离职原因")) {
			return false;
		}
		if (!validateText("dimission_laterGo", "离职后去向")) {
			return false;
		}
	}
}

function validateText(id, textname) {
	var textValue = $("#" + id).val();
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
function zhengyi(obj){
	if($("#naowuzhengyi").val()=='是'){
		$("#zhengyi_content").show();
	}else{
		$("#zhengyi_content").hide();
	};
}
		</script>
	</body>
</html>
