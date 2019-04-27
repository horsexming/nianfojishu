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
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">更新记录</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv" style="background-color: #fff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 600px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>

		<div align="center">
			<!-- 添加 -->
			<form action="FileManagerAction!updateFileM.action" method="post"
				target="main">
				<input type="hidden" name="fileManger.id" value="${fileManger.id}">
				<table class="table">
					<tr>
						<th width="15%">
							文件类型
						</th>
						<th width="20%">
							<select name="fileManger.fileType" style="width: 150px;"
								id="filetype"
								onMouseOver="createDept('filetype','FileManagerAction!findStyle.action?tag=filetype')">
								<option value="${fileManger.fileType}">
									${fileManger.fileType}
								</option>
							</select>
						</th>
						<th width="15%">
							文件名称
						</th>
						<th align="left">
							<input type="text" name="fileManger.fileName"
								value="${fileManger.fileName}" />
						</th>
						<th>
							文件编号
						</th>
						<th align="left">
							<input type="text" name="fileManger.fileNumber"
								value="${fileManger.fileNumber}" />
						</th>

					</tr>
					<tr>
						<th>
							文件性质
						</th>
						<th>
							<select name="fileManger.fileXingzhi" style="width: 150px;">
								<option value="${fileManger.fileXingzhi}">
									${fileManger.fileXingzhi}
								</option>
								<option value="原件">
									原件
								</option>
								<option value="复印件">
									复印件
								</option>
								<option value="其他">
									其他
								</option>
							</select>
						</th>
						<th>
							存放位置
						</th>
						<th align="left">
							<select name="fileManger.fileLocation" style="width: 150px;"
								onMouseOver="createDept('location','FileManagerAction!findStyle.action?tag=location')"
								id="location">
								<option value="${fileManger.fileLocation}">
									${fileManger.fileLocation}
								</option>
							</select>
						</th>
						<th>
							文件数量
						</th>
						<th align="left">
							<select style="width: 150px;" name="fileManger.fileCount">
								<option value="${fileManger.fileCount}">
									${fileManger.fileCount}
								</option>
								<%
									for (int i = 0; i < 301; i++) {
								%>
								<option value="<%=i%>"><%=i%></option>
								<%
									}
								%>

							</select>
						</th>
					</tr>
					<tr>
						<th>
							档案编号
						</th>
						<th>
							<input type="hidden" id="priceID1" name="fileManger.archivesId"
								value="${fileManger.archivesId}" />
							<input id="daNum1" type="text" name="fileManger.filearchivesNo"
								value="${fileManger.filearchivesNo}" readonly="readonly"
								onclick="selectPrice()" />
						</th>
						<th>
							文件签订日期
						</th>
						<th align="left">
							<input class="Wdate" type="text"
								value="${fileManger.fileSignDate}"
								name="fileManger.fileSignDate" size="20"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />

						</th>
						<th>
							文件页数
						</th>
						<th align="left">
							<select style="width: 150px;" name="fileManger.filePages">
								<option value="${fileManger.filePages}">
									${fileManger.filePages}
								</option>
								<%
									for (int i = 0; i < 301; i++) {
								%>
								<option value="<%=i%>"><%=i%></option>
								<%
									}
								%>

							</select>
						</th>

					</tr>
					<tr>
						<th>
							是否申请存档
						</th>
						<th>
							<s:if test="%{'yes'==oadetail.detailClass}">
								<input type="radio" id="budgetDept" onclick="showcundangClass()"
									name="fileManger.isCundang" value="yes" checked="checked" />
									是
									<input type="radio" id="budgetDept2"
									onclick="hidecundangClass()" name="fileManger.isCundang"
									value="no" />
									否
								</s:if>
							<s:else>
								<input type="radio" id="budgetDept" onclick="showcundangClass()"
									name="fileManger.isCundang" value="yes" />
									是
									<input type="radio" id="budgetDept2"
									onclick="hidecundangClass()" name="fileManger.isCundang"
									value="no" checked="checked" />
									否
								</s:else>

							<th>
								申请存档数量
							</th>
							<th align="left">
								<s:if test="%{'yes'==oadetail.detailClass}">
									<div id="cundangNO">
										<select style="width: 150px;" name="fileManger.cundangCount">
											<option value="${fileManger.cundangCount}">
												${fileManger.cundangCount}
											</option>
											<%
												for (int i = 0; i < 301; i++) {
											%>
											<option value="<%=i%>"><%=i%></option>
											<%
												}
											%>

										</select>
									</div>
								</s:if>
								<s:else>
									<div id="cundangNO" style="display: none;">
										<select style="width: 150px;" name="fileManger.cundangCount">
											<option value="${fileManger.cundangCount}">
												${fileManger.cundangCount}
											</option>
											<%
												for (int i = 0; i < 301; i++) {
											%>
											<option value="<%=i%>"><%=i%></option>
											<%
												}
											%>

										</select>
								</s:else>
							</th>
							<th>
								备注
							</th>
							<th align="left">
								<textarea cols="40" name="fileManger.fileRemarks">${fileManger.fileRemarks}</textarea>
							</th>
					</tr>

					<tr>
						<th>
							档案柜：
							<input id="danganId" type="hidden" name="fileManger.danganId"
								value="${fileManger.danganId}" />
							<input id="danganWeizhi" type="hidden"
								name="fileManger.danganWeizhi" value="${fileManger.danganWeizhi}" />
						</th>
						<td colspan="5">
							<SELECT id="danganselect" style="width: 153px;">
								<option
									value="${fileManger.danganWeizhi}|${fileManger.danganId}">
									${fileManger.danganWeizhi}
								</option>
							</SELECT>
						</td>
					</tr>
					<tr>
						<th colspan="6">
							<input type="submit" style="height: 50px; width: 120px;"
								value="更新">
							&nbsp;
							<input type="reset" style="height: 50px; width: 80px;" value="清空">
						</th>
					</tr>
				</table>
			</form>
			<br>
		</div>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//隐藏存档信息
$(function() {
	dangangui();
});

function danganguan() {
	var name = $("#banciselect").val();
	if ("${user.banci_name}" == name) {
		$("#banci_id").val(user.banci_id);
		$("#banci_name").val(user.banci_name);
	} else {
		var usersData = name.split("|");
		var banciid = usersData[1];
		var banciname = usersData[0];
		$("#banci_id").val(banciid);
		$("#banci_name").val(banciname);
	}
	//alert("${user.banci_name}");
}

function dangangui() {
	$.ajax( {
		url : "AccessEquipmentAction_finAllGuihao.action",
		type : 'post',
		dataType : 'json',
		success : function(data) {
			$(data).each(
					function() {
						$(
								"<option value='" + this.cabinetNum + "|"
										+ this.id + "'>" + this.cabinetNum
										+ "</option>")
								.appendTo("#danganselect")
					});
			$("#danganselect").tinyselect();
			$("#danganselect").bind("change", function() {
				var name = $("#danganselect").val();
				var usersData = name.split("|");
				var bfName = usersData[0];
				var ncode = usersData[1];
				$("#danganId").val(ncode);
				$("#danganWeizhi").val(bfName);
			});
		},
		error : function() {
			alert("服务器异常!");
		}
	})
}
function hidecundangClass() {
	$("#cundangNO").hide();
}
function showcundangClass() {
	$("#cundangNO").show();
}
//查找档案信息弹出层
function selectPrice() {
	//alert(type);"http://task.shhhes.com"
	var url = "PriceAction!findPriceByCondition.action?statue=dangan&num_1=" + 1;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
</script>
	</body>
</html>
