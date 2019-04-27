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
							<span id="title">选择档案</span>
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
					<input type="button" value="文件类型及存放置管理" onclick="managerFile()" />
					&nbsp;&nbsp;&nbsp;
					<input type="button" value="创建文件类型及存放置" onclick="createFile()" />
					&nbsp;&nbsp;&nbsp;
				</div>
			</div>

			<div align="center">
				<!-- 添加 -->
				<form action="FileManagerAction!saveFile.action" method="post">
					<table class="table">
						<tr>
							<th width="15%">
								文件类型
							</th>
							<th width="20%">
								<select name="fileManger.fileType" style="width: 150px;"
									id="filetype"
									onMouseOver="createDept('filetype','FileManagerAction!findStyle.action?tag=filetype')"></select>
							</th>
							<th width="15%">
								文件名称
							</th>
							<th align="left">
								<input type="text" name="fileManger.fileName" />
							</th>
							<th>
								文件编号
							</th>
							<th align="left">
								<input type="text" name="fileManger.fileNumber" />
							</th>
						</tr>
						<tr>
							<th>
								文件性质
							</th>
							<th>
								<select name="fileManger.fileXingzhi" style="width: 150px;">
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
									id="location"></select>
							</th>

							<th>
								文件数量
							</th>
							<th align="left">
								<select style="width: 150px;" name="fileManger.fileCount">
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
								<input type="hidden" id="priceID1" name="fileManger.archivesId" />
								<input id="daNum1" type="text" name="fileManger.filearchivesNo"
									readonly="readonly" onclick="selectPrice()" />
							</th>
							<th>
								文件签订日期
							</th>
							<th align="left">
								<input class="Wdate" type="text" name="fileManger.fileSignDate"
									size="20"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />

							</th>
							<th>
								文件页数
							</th>
							<th align="left">
								<select style="width: 150px;" name="fileManger.filePages">
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
								<input type="radio" id="cundang" onclick="showcundangClass()"
									name="fileManger.isCundang" value="yes" checked="checked" />
								是
								<input type="radio" id="cundang2" onclick="hidecundangClass()"
									name="fileManger.isCundang" value="no" />
								否
							</th>
							<th>
								申请存档数量
							</th>
							<th align="left">
								<div id="cundangNO">
									<select style="width: 150px;" name="fileManger.cundangCount">
										<%
											for (int i = 0; i < 301; i++) {
										%>
										<option value="<%=i%>"><%=i%></option>
										<%
											}
										%>

									</select>
								</div>
							</th>
							<th>
								备注
							</th>
							<th align="left">
								<textarea cols="30" name="fileManger.fileRemarks"></textarea>
							</th>

						</tr>
						<tr>
							<th>
								档案柜：
								<input id="danganId" type="hidden" name="fileManger.danganId"
									value="" />
								<input id="danganWeizhi" type="hidden"
									name="fileManger.danganWeizhi" value="" />
							</th>
							<td colspan="5">
								<SELECT id="danganselect" style="width: 153px;">
									<option value=""></option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th colspan="6">
								<input type="submit" style="height: 50px; width: 120px;"
									value="保存">
								&nbsp;
								<input type="reset" style="height: 50px; width: 80px;"
									value="清空">
							</th>
						</tr>
					</table>
				</form>
				<!-- 查询 -->
				<form action="FileManagerAction!findFileManager.action"
					method="post">
					<table class="table">
						<tr>
							<th width="15%">
								文件类型
							</th>
							<th width="20%">
								<select name="fidefileManager.fileType" style="width: 150px;"
									id="filetype2"
									onMouseOver="createDept('filetype2','FileManagerAction!findStyle.action?tag=filetype')">
									<option value="${fidefileManager.fileType}">
										${fidefileManager.fileType}
									</option>
									<option value="">
										选择类型
									</option>
								</select>
							</th>
							<th width="15%">
								文件名称
							</th>
							<th align="left">
								<input type="text" name="fidefileManager.fileName"
									value="${fidefileManager.fileName}" />
							</th>
							<th rowspan="4">
								<input type="submit" style="width: 120px; height: 80px;"
									value="查找">
								&nbsp;

							</th>
						</tr>
						<tr>
							<th>
								文件性质
							</th>
							<th>
								<select name="fidefileManager.fileXingzhi" style="width: 150px;">
									<option value="${fidefileManager.fileXingzhi}">
										${fidefileManager.fileXingzhi}
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
								<select name="fidefileManager.fileLocation"
									style="width: 150px;" id="fileloaction2"
									onMouseOver="createDept('fileloaction2','FileManagerAction!findStyle.action?tag=location')">
									<option value="${fidefileManager.fileLocation}">
										${fidefileManager.fileLocation}
									</option>
									<option value="">
										选择存放位置
									</option>
								</select>
							</th>
						</tr>
						<tr>
							<th>
								档案编号
							</th>
							<th>
								<input type="text" name="fidefileManager.filearchivesNo"
									value="${fidefileManager.filearchivesNo}" />
							</th>
							<th>
								用途
							</th>
							<th align="left">
								<input type="text" name="fidefileManager.fileRemarks"
									value="${fidefileManager.fileRemarks}" />
							</th>

						</tr>
						<tr>
							<th>
								日期从：
							</th>
							<th>
								<input class="Wdate" type="text" name="startDate"
									value="${startDate}" size="20"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />

							</th>
							<th>
								<span style="width: 21px;"></span>到
								<span style="width: 21px;"></span>：
							</th>
							<th align="left">
								<input class="Wdate" type="text" name="endDate"
									value="${endDate}" size="20"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</th>
						</tr>
					</table>
				</form>
				<!-- 列表展示 -->
				<table class="table">
					<tr bgcolor="#c0dcf2" height="30px"
						style="border-collapse: separate;">
						<th align="center">
							序号
						</th>
						<th align="center">
							文件类型
						</th>
						<th align="center">
							文件名称
						</th>
						<th align="center">
							文件性质
						</th>
						<th align="center">
							文件存放位置
						</th>
						<th align="center">
							存档编号
						</th>
						<th align="center">
							备注
						</th>
						<th>
							文件编号
						</th>
						<th>
							文件数量
						</th>
						<th align="center">
							文件签订日期
						</th>
						<th>
							是否申请存档
						</th>
						<th>
							申请存档数量
						</th>
						<th>
							档案柜号
						</th>
						<th align="center">
							操作
						</th>
					</tr>

					<s:if test="{list.size()>0}">
						<s:iterator value="list" status="se" id="bxd">
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
								${bxd.fileType}
							</td>
							<td>
								${bxd.fileName}
							</td>
							<td>
								${bxd.fileXingzhi}
							</td>
							<td>
								${bxd.fileLocation}
							</td>
							<td>
								${bxd.filearchivesNo}
							</td>
							<td>
								${bxd.fileRemarks}
							</td>
							<td>
								${bxd.fileNumber}
							</td>
							<td>
								${bxd.fileCount}
							</td>
							<td>
								${bxd.fileSignDate}
							</td>
							<td>
								${bxd.isCundang}
							</td>
							<td>
								${bxd.cundangCount}
							</td>
							<td>
								${bxd.danganWeizhi}
							</td>
							<td>
								<input type="button" id="updateFile"
									onclick="updateFileManager('${bxd.id}')" value="修改" />
								<input type="button" id="delFile"
									onclick="dele1('${bxd.fileName}','${bxd.id}')" value="删除">
									
								<s:if test="#bxd.danganWeizhi!=null && #bxd.danganWeizhi!=''">
								
<%--									<s:if test="bxd.linshi!=null">--%>
<%--										验证码为：${bxd.linshi}--%>
<%--									</s:if>--%>
										<input type="button" id="shengqingid"
											onclick="takeout('${bxd.id}')" value="获取验证码" />
								</s:if>
								
								<s:if test="%{#bxd.archivesId!=null&#bxd.archivesId!=''}">
									<input type="button" id="danganDetail"
										onclick="danganDetailFind('${bxd.archivesId}')" value="档案明细" />
								</s:if>
								<!-- <a href="CircuitRunAction_findAduitPage.action?id=${bxd.epId}">审批动态</a>-->
							</td>
						</s:iterator>
						<tr>
							<td colspan="14" align="right">
								共
								<s:property value="total" />
								页 第
								<s:property value="cpage" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td colspan="14" style="font-size: 15px; color: red;">
								对不起，没有查到相关的预算信息
							</td>
						</tr>
					</s:else>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function() {
	dangangui();
});
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
								.appendTo("#danganselect");
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

//隐藏存档信息
function hidecundangClass() {
	$("#cundangNO").hide();
}
function showcundangClass() {
	$("#cundangNO").show();
}

//查看档案明细
function danganDetailFind(id1) {
	var url = "PriceAction!findPriceById.action?statue=find&id=" + id1;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
//查找档案信息弹出层
function selectPrice() {
	//alert(type);"http://task.shhhes.com"
	var url = "PriceAction!findPriceByCondition.action?statue=dangan&num_1=" + 1;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
	
//删除记录
function dele1(name, id1) {
	if (window.confirm('确认要删除' + name + '的财务文件?')) {
		window.location.href = "FileManagerAction!takeoutFileManager.action?id="+ id1;
	}
}
// 申请
function takeout(id) {
	window.location.href = "FileManagerAction!addYanZheng.action?fileManger.id="+id;
}
//修改记录
function updateFileManager(id1) {
	var url = "FileManagerAction!getOneFileManager.action?id=" + id1;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
//管理文件类型及存放位置、
function managerFile() {
	var url = "FileManagerAction!findAllTypeLocation.action";
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
//创建文件类型及存放位置、
function createFile() {
	var url = "FileManagerAction!createFileType.action";
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
</script>
	</body>
</html>
