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
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script
			src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js">
	</script>
			<script type="text/javascript"
				src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js">
	</script>
	</head>
	<style type="text/css">
		.upload{position:relative; display:inline-block; height:33px;line-height:33px; overflow:hidden;vertical-align:middle; cursor:pointer;}
	.upload .upload-input-file{position:absolute; right:0; top:0; font-size:100px; opacity:0; filter:alpha(opacity=0);cursor:pointer;}
	.upload .upload-btn{outline:none;border:0; padding:7px 10px;cursor:pointer; margin:3px; border-radius:3px;}
	.upload .upload-url { -moz-box-sizing: border-box; -webkit-box-sizing: border-box; box-sizing: border-box; border-radius:2px;
	    border: solid 1px #ddd; width: 200px; height:30px;-webkit-line-height: 1;line-height: 30px\9;-ms-line-height: 30px; text-indent:3px;}
	.upload .upload-tip {display:none; background-color:#1c84c6; padding:2px 10px; color:#fefefe; font-size:12px !important;border-radius:3px;}
	.horizontalLine{
		text-align:center;
	}
  </style>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<form action="ReliabilityTestAction!updateRTS.action" onsubmit="validate()" method="post" enctype="multipart/form-data">
			<input type="hidden" value="${sheet.id}" name="sheet.id" />
			<table class="table">
				<tr align="center">
					<td colspan="8" style="font-size: 20px;">
						修改可靠性测试
					</td>
				</tr>
				
				<tr>
					<th align="right" class="col-xs-1">申请单流水号:</th>
					<td class="col-xs-2"><input type="text" name="sheet.number" value="${sheet.number }" readonly="readonly"></td>
					<th align="right" class="col-xs-1">申请单位:</th>
					<td class="col-xs-2"><input type="text" name="sheet.company" value="${sheet.company }"></td>
					<th align="right" class="col-xs-1">申请日期:</th>
					<td class="col-xs-2"><input type="text" name="sheet.addTime" value="${sheet.addTime }" ></td>
					<th align="right" class="col-xs-1">申请人:</th>
					<td class="col-xs-2"><input type="text" name="sheet.addUserName"  value="${sheet.addUserName }"></td>
				</tr>
				<tr>
					<th align="right">物料类别:</th>
					<td><input type="text" name="sheet.materialCategory" value="${sheet.materialCategory }"></td>
					<th align="right">物料来源:</th>
					<td><input type="text" name="sheet.materialResource" value="${sheet.materialResource }"></td>
					<th align="right">样品编号:</th>
					<td colspan="3"><input type="text" name="sheet.specimenNum" value="${sheet.specimenNum }"></td>
				</tr>
				<tr>
					<th align="right">机柜名称:</th>
					<td><input type="text" name="sheet.jGname" value="${sheet.jGname }"></td>
					<th align="right">件号:</th>
					<td><input type="text" name="sheet.markId" value="${sheet.markId }"></td>
					<th align="right">检验批次号:</th>
					<td colspan="3"><input type="text" name="sheet.lotId" value="${sheet.lotId }"></td>
				</tr>
				<tr>
					<th align="right">测试项目:</th>
					<td colspan="7">
						<s:iterator value="proList" id="pro" >
							<s:if test="sheet.recordSet!=null&&sheet.recordSet.size()>0">
								<s:if test="listString.contains(#pro.proName)">
									&nbsp;&nbsp;&nbsp;<input type="checkbox" value="${pro.proName }" 
										name="recordList.proName" onchange="changePro(this)" checked="checked">${pro.proName }
								</s:if>		
								<s:else>
									&nbsp;&nbsp;&nbsp;<input type="checkbox" value="${pro.proName }" 
										name="recordList.proName" onchange="changePro(this)">${pro.proName}
								</s:else>
							</s:if>
							<s:else>
								&nbsp;&nbsp;&nbsp;<input type="checkbox" value="${pro.proName }" 
									name="recordList.proName"onchange="changePro(this)">${pro.proName }
							</s:else>
							
						</s:iterator>
					</td>
				</tr>
				<tr>
					<th align="right">
						推送人员
						<br />
						<br />
						<input id="test2" type="button" value="选择推送人员">
					</th>
					<td colspan="8">
						<input id="fid" name="sheet.personToLookId"
							value="${sheet.personToLookId}" readonly="readonly"
							type="hidden" />
						<textarea id="tishiPerson" name="sheet.personToLook"
							rows="5" cols="80" readonly="readonly">${sheet.personToLook}</textarea>
					</td>
				</tr>
				<tr>
					<th align="right">
						审批人员
					</th>
					<td colspan="3">
						<div id="freeDeptDiv">
							<font color="red">可靠性测试审批人员:</font>
							<input type="hidden" value="" name="uidsAndLevels" id="uidsAndLevels">
							<div>
								<div >
									<div>
										<div style="float: left; " align="center">
											<div style="float: left; ">
												<input type="text" id="searchDeptInput"style="width: 120px;"
													  placeholder="搜索部门" onkeyup="searchDept()"><br>
												<select id="userGroup" name="" style="width: 120px;" size="15" onchange="changeDept()">
													<option value="">
														选择部门
													</option>
													
												</select>
												<br>
											</div>
											<div style="float: left; ">
												<input type="text" id="searchperson"style="width: 120px;"
													 onkeyup="changeDept()" placeholder="搜索审批人"><br>
												<select id="person" name="" style="width: 120px;" size="15">
													<option>
														选择审批人
													</option>
												</select>
											</div>
										</div>
										<div id="allLevel" style="float: left; width: 50%;" align="left">
										<div>
											<input type="button" value="添加审核等级" class="input"
												style="width: 100px;" onclick="addLevel()" />
											<input type="button" value="删除审核等级" class="input"
												style="width: 100px;" onclick="delLevel()" />
										</div>
										<div id="levelDiv1" >
											<div style="float: left; padding-top: 10px;">
												<input type="button" value="------->"
													onclick="getPerson(1,this)" />
												1级
												<input type="hidden" name="" value="1" />
												<br />
												<input type="button" value="<-------"
													onclick="backPerson(1,this)" />
											</div>
											<div style="float: left;">
												<select id="level1" style="width: 120px;" size="3"></select>
												<span id="addStatus1" style="color: red;"> </span>
											</div>
											<div style="clear: both;"></div>
											<br>
										</div>
									</div>
									<div style="clear: both;"></div>
								</div>
							</div>
						</div>
					</td>
				</tr>
			</table>
			<%-- <br/>
			<br/>
			<br/>
			<table class="table">
				<thead>
					<tr>
						<th colspan="3" style="font-size: 20px;text-align: center;">
							测试记录
							<input type="hidden" value="${sheet.recordSet.size()}" id="opt_index"/>
						</th>
					</tr>
					<tr>
						<th class="col-xs-2">测试项目</th>
						<th class="col-xs-7">测试记录</th>
						<th class="col-xs-5">附件</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<s:iterator value="sheet.recordSet" id="record" status="pageStatus" >
						<tr>
							<td>${record.proName }<input type="hidden" value="${record.id}" name="recordList[${pageStatus.index}].id" /></td>
							<td><textarea rows='3' cols='100' name='recordList[${pageStatus.index}].testRecord'>${record.testRecord }</textarea></td>
							<td><a href="${pageContext.request.contextPath}/FileViewAction.action?FilePath=/upload/file/rts/${record.testFile }">${record.otherFileName }</a>
								<br/>
								<input type='file' name='attachments[${pageStatus.index}]'onchange='javascript:changeFile(${pageStatus.index},this)' >
								<input type='hidden' name='attachmentsFileName[${pageStatus.index}]' id='fileName_${pageStatus.index}' >
							</td>
						</tr>
					</s:iterator>
				</tbody>
				<tfoot>
					<tr>
						<td class="col-xs-12" colspan="3" style="padding: 0;margin: 0">
							<table class="table" style="padding: 0;margin: 0">
								<tr>
									<th class="col-xs-2">记录人</th>
									<td class="col-xs-3" style="margin: 0;padding: 0">
										<textarea style="width: 100%;margin: 0;padding: 0;resize:none;border:none" name="sheet.jlPerson" >${sheet.jlPerson}</textarea>
									</td>
									<th class="col-xs-3">记录填写日期</th>
									<td class="col-xs-4" style="margin: 0;padding: 0">
										<textarea style="width: 100%;margin: 0;padding: 0;resize:none;border:none" name="sheet.jlAddTime">${sheet.jlPerson}</textarea>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						
					</tr>
				</tfoot> 
			</table>
			<br/>--%>
<%--			<br/>--%>
<%--			<br/>--%>
<%--			<table class="table" align="center">--%>
<%--				<tr>--%>
<%--					<th colspan="4" style="font-size: 20px;text-align: center">结果判定</th>--%>
<%--				</tr>--%>
<%--				<tr>--%>
<%--					<th class="col-xs-2">判定依据</th>--%>
<%--					<td class="col-xs-4" style="margin: 0;padding: 0">--%>
<%--						<textarea style="width: 100%;margin: 0;padding: 0;resize:none;border:none" name="sheet.decideBasis">${sheet.jlPerson}</textarea>--%>
<%--					</td>--%>
<%--					<th class="col-xs-2">判定结果</th>--%>
<%--					<td class="col-xs-4" style="margin: 0;padding: 0">--%>
<%--						<textarea style="width: 100%;margin: 0;padding: 0;resize:none;border:none" name="sheet.decideResult">${sheet.jlPerson}</textarea>--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--				<tr>--%>
<%--					<th colspan="1" class="col-xs-2">记录/异常描述</th>--%>
<%--					<td colspan="3" class="col-xs-10" style="margin: 0;padding: 0">--%>
<%--						<textarea style="width: 100%;height: 70px;padding: 0;resize:none;border:none" name="sheet.errRemark">${sheet.jlPerson}</textarea>--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--			</table>--%>
			<div align="center">
				<input class="input" type="submit" />
			</div>
		</form>
		</div>
		<%@include file="/util/foot.jsp"%>
		
	</body>
<script type="text/javascript">
	var opt_index = 0;
	$(function(){
		var index = $("#opt_index").val();
		if(index!=null && index>0){
			opt_index = index;
		}
	})
	
	$(function() {
		$.ajax( {
			type : "post",
			url : "GzstoreAction_getdept.action",
			dataType : "json",
			success : function(data) {
				//填充部门信息
				$(data).each(function() {
					var html = "";
					if (this.dept == "${Users.dept}") {
						html = "<option selected='selected' value='"
								+ this.id + "'>" + this.dept + "</option>";
					} else {
						html = "<option value='" + this.id + "'>"
								+ this.dept + "</option>";
					}
					$(html).appendTo("#userGroup");
				});
				changeDept();
			}
		});
	});
	function changePro(object){
		
		var proName = $(object).val();
		if($(object).is(':checked')){//选中
			// multiple='multiple' 
			$("#tbody").append("<tr></tr><tr><td><input type='hidden' value='"+proName+"' name='recordList["+
			opt_index+"].proName'>"+proName+"</td>"+
				"<td><textarea rows='3' cols='100' name='recordList["+opt_index+"].testRecord'></textarea></td>"+
				"<td><input type='file' name='attachments["+opt_index+"]'onchange='javascript:changeFile("+opt_index+",this)' >" +
				"	<input type='hidden' name='attachmentsFileName["+opt_index+"]' id='fileName_"+opt_index+"' ></td></tr>");
		}else{
			
			var html = $("#tbody").html();
			var trHtml = html.split("<tr></tr>");
			for(var i =0;i<trHtml.length;i++){
				var tr = trHtml[i];
				if(tr.indexOf(proName)>=0){
					$("#tbody tr").filter(":contains('"+proName+"')").remove();
				}
			}
		}
		opt_index++;
	}

	 function changeFile(className,obj){
		 $("#showFile"+className).val("请稍后...");
<%--		 var values = [];--%>
<%--		 $("").each(function(i,file){--%>
<%--			 values.push($(this).val());--%>
<%--		 });--%>
<%--		 alert(values);--%>
		 var fileUrl = $(obj).val();
		 alert(fileUrl);
		 var urlArr = fileUrl.split("\\");
		 var getName = urlArr[urlArr.length-1];
		 if(getName!=null && getName !=""){
			 $("#showFile"+className).val(getName);
		 }else{
			 $("#showFile"+className).val("浏览文件");
		 }
	 }
	 
	 function changeFile(index,obj){
		 var fileUrl = $(obj).val();
		 var urlArr = fileUrl.split("\\");
		 var getName = urlArr[urlArr.length-1];
		 $("#fileName_"+index).val(getName);
	 }
	 function deleteFreeDept(index) {
			$("#freeDeptUl" + index).remove();
		}
		$('#test2').on('click', function(){
			layer.open({
			  type: 2,
			  title: '选择推送人员',
			  area: ['450px', '800px'],
			  fixed: false, //不固定
			  maxmin: true,
			  content: '<%=basePath%>/System/systemfile/checkPerson.jsp'
			});
		});
		
		function changeDept(){
			var deptId = $("#userGroup").val();
			var searchperson = $("#searchperson").val();
			if (deptId > 0) {
				$.ajax( {
					type : "post",
					url : "GzstoreAction_getusers.action",
					dataType : "json",
					data : {
						id : deptId,
						"variable":searchperson
					},
					async : false, 
					success : function(data) {
						//填充部门信息
						$("#person").empty();
						$("#person").append("<option>选择审批人</option>");
						$(data).each(function() {
							if(this!=null&& this.name!=null && this.name!=""){
								var html = "<option value='" + this.id + "'>"
									+ this.name + "</option>";
								$(html).appendTo("#person");
							}
						});
			
					}
				});
			}
		}

		//右移
		function getPerson(id, obj) {
			var personVal = $("#person option:selected");
			if (personVal.val() > 0) {
				var userId = personVal.val();
				var userName = personVal.text();
				var a = true;
				var levels = $("#level"+id+" option").each(function(){
					var uid = $(this).val();
					if(parseInt(uid)==parseInt(userId)){
						alert("选择审批人重复");
						a = false;
						return false;
					}
				});
				if(a){
					$("#level"+id).append("<option value='"+userId+"'>"+userName+"</option>");
				}
			} else if (personVal.val() == null) {
				alert("请选择人员!");
			} else {
				alert(personVal.val());
			}
		}

		//左移
		function backPerson(id, obj) {
			var levelVal = $("#level" + id);
			if (levelVal.val() > 0) {
				var checkIndex = $("#level" + id).get(0).selectedIndex;//获取Select选择的索引值 
				var so = $("#level" + id + " option:selected");
				$("#person").append(so);
				//选中第一个option
				$("#level" + id).get(0).selectedIndex = 0;
			} else if (levelVal.val() == null) {
				alert("请选择人员!");
			}
		}


		//添加审核等级
		var count = 1;
		function addLevel() {
			count++;
			$("<div id='levelDiv"
					+ count
					+ "'>"
					+ "<div style='float: left; padding-top: 10px;'>"
					+ "<input type='button' value='------->' onclick='getPerson("
					+ count
					+ ",this)' /> "
					+ count
					+ "级"
					+ " <input type='hidden' name='' value='"
					+ count
					+ "' /><br />"
					+ "<input type='button' value='<-------' onclick='backPerson("
					+ count
					+ ",this)' />"
					+ "</div>"
					+ "<div style='float: left;'><select id='level"
					+ count
					+ "' style='width: 120px;' size='3'></select><span id='addStatus"
					+ count
					+ "' style='color: red;'></span></div><div style='clear: both;'></div><br /></div>")
			.appendTo("#allLevel");
		}

		//删除审核等级
		function delLevel() {
			if (count == 1) {
				alert("就剩一个了,不能再删了!");
				return false;
			} else {
				//先删除该审核等级里面的人员
				var selectSize = $("#level" + count).get(0).options.length;
				if (selectSize == 0) {
					$("#levelDiv" + count).remove();
					count--;
				} else {
					alert("请先删除审核等级为" + count + "级的人员!");
				}
			}

		}
		function deleteFile(delId){
			$("#pfile"+delId).remove();
		}
		function changefreeDept(i) {
			var deptId = $("#zrdept" + i).val();
			if (deptId > 0) {
				$.ajax( {
					type : "post",
					url : "AskForLeaveAction!getDeptUsers.action",
					dataType : "json",
					data : {
						id : deptId
					},
					success : function(data) {
						//填充部门信息
					var selectbox = $("#freeDeptUl" + i + " .tinyselect");
					if (selectbox.length > 1) {
						var len = selectbox.length - 1;
						for ( var n = len; n >= 1; n--) {
							$(selectbox[n]).remove();
						}
					}
					$("#zrpeople" + i).empty();
					$(data).each(
							function() {
								var html = "<option value='" + this.userId + "'>"
										+ this.userName + "</option>";
								$(html).appendTo("#zrpeople" + i);
							});
					$("#zrpeople" + i).tinyselect();

				}
				});
			}
		}
		var deptIndex = 0;
		function setDept(i) {
			$.ajax( {
				type : "post",
				url : "GzstoreAction_getdept.action",
				dataType : "json",
				success : function(data) {
					//填充部门信息
					$(data).each(
							function() {
								var html = "";
								if (this.dept == "${Users.dept}") {
									html = "<option selected='selected' value='"
											+ this.id + "'>" + this.dept + "</option>";
								} else {
									html = "<option value='" + this.id + "'>"
											+ this.dept + "</option>";
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
				$.ajax( {
					type : "post",
					url : "GzstoreAction_getusers.action",
					dataType : "json",
					data : {
						id : deptId
					},
					success : function(data) {
						//填充部门信息
					var selectbox = $("#freeDeptUl" + i + " .tinyselect");
					if (selectbox.length > 1) {
						var len = selectbox.length - 1;
						for ( var n = len; n >= 1; n--) {
							$(selectbox[n]).remove();
						}
					}
					$("#zrpeople" + i).empty();
					$(data).each(
							function() {
								var html = "<option value='" + this.id + "'>"
										+ this.name + "</option>";
								$(html).appendTo("#zrpeople" + i);
							});
					$("#zrpeople" + i).tinyselect();

				}
				});
			}
		}
		function addFreeDept() {
			deptIndex++;
			var html = "<ul id='freeDeptUl" + deptIndex + "'>" + "<li id='freeDeptli"
					+ deptIndex + "'>" + "<SELECT id='zrdept" + deptIndex
					+ "' name='approvalId' onchange='changefreeDept(" + deptIndex
					+ ")'></SELECT>" + "<SELECT id='zrpeople" + deptIndex
					+ "' name='ids'></SELECT>"
					+ "<input type='button' value='删除' onclick='deleteFreeDept("
					+ deptIndex + ")' style='width: 60px;height: 30px'>" + "</li></ul>"
			$(html).appendTo("#freeDeptDiv");
			setDept(deptIndex);
		}
		
		function validate(){
			var arr = [];
			var szIndex = 0;
			for(var i=1;i<=count;i++){
				var levels = document.getElementById("level"+i).options;
				if(levels==null|| levels.length==0){
					alert("审核等级："+i+"级为空！");
					return false;
				}else{
					 for (var j = 0; j < levels.length; j++){
					 		arr.push(i+":"+ levels[j].value);
		        	 }
				}
				document.getElementById("uidsAndLevels").value = arr;
			}
			return true;
		}
</script>
</html>
