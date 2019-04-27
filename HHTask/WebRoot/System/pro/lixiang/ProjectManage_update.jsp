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
	$(function() {
		//表单提交验证
		$("#proForm").bind("submit", function() {
			if ($("#projectName").val() == "") {
				alert("请填写项目名称!");
				$("#projectName").focus();
				return false;
			} else if ($("#projectNum").val() == "") {
				alert("请填写项目编号!");
				$("#projectNum").focus();
				return false;
			} else {
				return true;
			}
		});
	})
	var fileDivHTML = "";
	var count = 0;
	function uploadFile(obj, few) {
		var fileDiv = document.getElementById("fileDiv_" + few);
		if (obj.value == "上传附件") {
			fileDiv.style.display = "block";
			obj.value = "添加文件";
		}
		fileDivHTML = "<div id='file"
			+ count
			+ "'><input type='file' name='attachment'><input type='text' name='otherName'>（文件名） <a href='javascript:delFile("
				+ count + "," + few + ")'>删除</a></div>";
		fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);
		count++;
	}

	function delFile(obj, few) {
		document.getElementById("file" + obj).parentNode.removeChild(document
				.getElementById("file" + obj));
		count--;
		if (count <= 0) {
			count = 0;
			document.getElementById("fileButton_" + few).value = "上传附件";
			document.getElementById("fileDiv_" + few).style.display = "none";
		}
	}
	function delwenjian(id) {//ProjectManage_delwenjian.action?id=${pagewenjian.id}
		if (confirm('确定要删除吗?')) {
			$.ajax({
				type : "POST",
				url : "ProjectManage_delwenjian.action",
				data : {
					id : id
				},
				dataType : "json",
				success : function(data) {
					if (data) {
						alert("删除成功!")
						$("#wenjian_p_" + id).remove();
					}
				}
			})
		}

	}
</script>
</head>
<body>
	<div align="center">
		<form action="ProjectManage_updateProjectManage.action" method="post"
			enctype="multipart/form-data">
			<input name="projectManage.id" value="${projectManage.id}"
				type="hidden" /> <input name="pageStatus" value="${pageStatus}"
				type="hidden" />
			<table class="table">
				<tr>
					<th colspan="4">
						<h2>项目立项明细</h2>
					</th>
				</tr>
				<tr>
					<th align="right">项目类别:</th>
					<th align="left">
						<div>
							<select id="proType" name="projectManage.proType"
								onchange="changeProType()">
								<option value="${projectManage.proType}">${projectManage.proType
									}</option>
								<option value="预研类">预研类</option>
								<option value="基础类">基础类</option>
								<option value="客户定制类">客户定制类</option>
								<option value="改善类">改善类</option>
							</select>
						</div></th>
					<th align="right">是否保密:</th>
					<th align="left">
						<s:if  test='"否"==projectManage.isbaomi'>
							<input type="radio" value="是" name="projectManage.isbaomi" />是
							<input type="radio" value="否" name="projectManage.isbaomi"
								checked="checked" />否
						</s:if>
						<s:else>
						
							<input type="radio" value="是" name="projectManage.isbaomi"
								checked="checked" />是
							<input type="radio" value="否" name="projectManage.isbaomi" />否
						</s:else>
					</th>
				</tr>
				<tr>
					<th align="right">项目名称:</th>
					<th align="left"><input id="projectName"
						name="projectManage.projectName"
						value="${projectManage.projectName}" />
					</th>
					<th align="right">项目编号:</th>
					<th align="left"><input id="projectNum"
						name="projectManage.projectNum" readonly="readonly"
						value="${projectManage.projectNum}" />
					</th>
				</tr>
				<tr>
					<th align="right">客户:</th>
					<th align="left"><input name="projectManage.client"
						value="${projectManage.client}" />
					</th>
					<th align="right">预估金额:</th>
					<th align="left"><input name="projectManage.yuMoney"
						value="${projectManage.yuMoney}" />
					</th>
				</tr>
				<tr>
					<th align="right">附件上传:</th>
					<th align="left" colspan="1"><s:if
							test="projectManage.projectWenJianSet!=null">
							<s:iterator value="projectManage.projectWenJianSet"
								id="pagewenjian">
								<P id="wenjian_p_${pagewenjian.id}">
									<a
										href="FileViewAction.action?FilePath=/upload/file/project/${pagewenjian.fileName}">${pagewenjian.otherName}</a>
									<a href="javascript:;"
										onclick="delwenjian('${pagewenjian.id}')">删除</a>
								</P>
							</s:iterator>
						</s:if> <%--					<input type="file" name="attachment">--%> <input
						type="button" id="fileButton_1" onclick="uploadFile(this,1)"
						value="上传附件">

						<div id="fileDiv_1" style="display: none;"></div>
					</th>
					
				</tr>
				<tr>
					<th align="right">项目内容/可行性分析:</th>
					<th align="left" colspan="3"><textarea rows="10" cols="80"
							name="projectManage.content">${projectManage.content}</textarea>
					</th>
				</tr>

				<th align="right">选择审批人:</th>
				<th align="left" id="freeDeptDiv" colspan="3"><font color="red">开发立项审批人:</font>
					<input type="button" value="增加" onclick="addFreeDept()"
					style="width: 60px; height: 30px">
					<ul id="freeDeptUl0">
						<li id="freeDeptli0"><SELECT id="zrdept0" name="deptIds"
							onchange="changefreeDept(0)"></SELECT> <SELECT id="zrpeople0"
							name="userIds"></SELECT> <input type="button" value="删除"
							onclick="deleteFreeDept(0)" style="width: 60px; height: 30px">
						</li>
					</ul>
				</th>
				</tr>
				<tr>
					<td colspan="4" align="center"><input type="submit" value="修改"
						class="input" /> <input type="reset" value="取消" class="input" />
					</td>
				</tr>
				<tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		$(function() {
			setDept(0);
			$("#proType").tinyselect();

			$
					.ajax({
						type : "post",
						url : "${pageContext.request.contextPath}/orderManager_getCustomer.action",
						dataType : "json",
						async : false,
						success : function(data) {
							$("#costomer").append("<option></option>");
							if (data != null) {
								for ( var i = 0; i < data.length; i++) {
									$("#costomer").append(
											"<option value='" + data[i] + "'>"
													+ data[i] + "</option>");
								}
							}
							$("#costomer").tinyselect();
						}

					});
			//设置客户项隐藏
			$("#costomerStr").hide();
			$("#costomerTd").children().filter("div").hide();

		});

		var deptIndex = 0;
		function setDept(i) {
			$
					.ajax({
						type : "post",
						url : "GzstoreAction_getdept.action",
						dataType : "json",
						success : function(data) {
							//填充部门信息
							$(data)
									.each(
											function() {
												var html = "";
												if (this.dept == "${Users.dept}") {
													html = "<option selected='selected' value='"
										+ this.id + "'>"
															+ this.dept
															+ "</option>";
												} else {
													html = "<option value='" + this.id + "'>"
															+ this.dept
															+ "</option>";
												}
												$(html).appendTo("#zrdept" + i);
											});
							changefreeDept(i);
							$("#zrdept" + i).tinyselect();
						}
					});
		}
		function changefreeDept(i) {
			var deptId = $("#zrdept" + i).val();
			if (deptId > 0) {
				$
						.ajax({
							type : "post",
							url : "GzstoreAction_getusers.action",
							dataType : "json",
							data : {
								id : deptId
							},
							success : function(data) {
								//填充部门信息
								var selectbox = $("#freeDeptUl" + i
										+ " .tinyselect");
								if (selectbox.length > 1) {
									var len = selectbox.length - 1;
									for ( var n = len; n >= 1; n--) {
										$(selectbox[n]).remove();
									}
								}
								$("#zrpeople" + i).empty();
								$(data)
										.each(
												function() {
													var html = "<option value='" + this.id + "'>"
															+ this.name
															+ "</option>";
													$(html).appendTo(
															"#zrpeople" + i);
												});
								$("#zrpeople" + i).tinyselect();

							}
						});
			}
		}
		function addFreeDept() {
			deptIndex++;
			var html = "<ul id='freeDeptUl" + deptIndex + "'>"
					+ "<li id='freeDeptli"
				+ deptIndex + "'>"
					+ "<SELECT id='zrdept"
					+ deptIndex
					+ "' name='deptIds' onchange='changefreeDept("
					+ deptIndex
					+ ")'></SELECT>"
					+ "<SELECT id='zrpeople" + deptIndex
				+ "' name='userIds'></SELECT>"
					+ "<input type='button' value='删除' onclick='deleteFreeDept("
					+ deptIndex + ")' style='width: 60px;height: 30px'>"
					+ "</li></ul>"
			$(html).appendTo("#freeDeptDiv");
			setDept(deptIndex);
		}
		function deleteFreeDept(index) {
			$("#freeDeptUl" + index).remove();
		}

		function changeProType() {
			var proType = $("#proType").val();
			if (proType == "客户定制类") {
				$("#costomerStr").show();
				$("#costomerTd").children().filter("div").show();
			} else {
				$("#costomerStr").hide();
				$("#costomerTd").children().filter("div").hide();
			}
		}
	</script>
</body>
</html>
