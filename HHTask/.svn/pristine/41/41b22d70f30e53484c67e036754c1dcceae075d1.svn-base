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
		<form id="xform" action="GzstoreAction_doupdateProcess.action"
			method="post" onsubmit="return validate()">
			<table class="table">
				<tr>
					<th colspan="4" align="center">
						<h3>
							修改工序信息
						</h3>
					</th>
				</tr>
				<tr>
					<th align="right">
						工序名称:
					</th>
					<td>
						<input type="text" id="matetag" name="processGzstore.processName"
							value="${processGzstore.processName}" />
					</td>
					<th align="right">
						生产类型:
					</th>
					<td>
						<select name="processGzstore.productStyle">
							<option value="${processGzstore.productStyle}">
								${processGzstore.productStyle}
							</option>
							<option>
								自制
							</option>
							<option>
								外委
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<th align="right">
						是否并行:
					</th>
					<td>
						<select id="processStatus" name="processGzstore.processStatus">
							<option value="${processGzstore.processStatus}">
								${processGzstore.processStatus}
							</option>
							<option value="no">
								不并行
							</option>
							<option value="yes">
								并行
							</option>
						</select>
					</td>
					<th align="right">
						奖金分配:
					</th>
					<td>
						<select name="processGzstore.isPrice">
							
							<option value="${processGzstore.isPrice}">
								${processGzstore.isPrice}
							</option>
							<option value="yes">
								参与
							</option>
							<option value="no">
								不参与
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<th align="right">
						考勤验证:
					</th>
					<td>
						<select name="processGzstore.kaoqingstatus">
							
							<option value="${processGzstore.kaoqingstatus}">
								${processGzstore.kaoqingstatus}
							</option>
							<option value="是">
								是
							</option>
							<option value="否">
								否
							</option>
						</select>
					</td>
					<th align="right" rowspan="7">
						描述:
					</th>
					<td rowspan="7">
						<textarea rows="8" cols="30" name="processGzstore.more">${processGzstore.more} </textarea>
					</td>

				</tr>
				<tr>
					<th align="right">
						规范验证:
					</th>
					<td>
						<select name="processGzstore.guifanstatus">
							<option value="${processGzstore.guifanstatus}">
								${processGzstore.guifanstatus}
							</option>
							<option value="是">
								是
							</option>
							<option value="否">
								否
							</option>
						</select>
					</td>
				</tr>
				<tr>

					<th align="right">
						工装验证:
					</th>
					<td>
						<select name="processGzstore.gongzhuangstatus">
							
							<option value="${processGzstore.gongzhuangstatus}">
								${processGzstore.gongzhuangstatus}
							</option>
							<option value="是">
								是
							</option>
							<option value="否">
								否
							</option>
						</select>
					</td>

				</tr>
				<tr>
					<th align="right">
						量具验证:
					</th>
					<td>
						<select name="processGzstore.liangjustatus">
							<option value="${processGzstore.liangjustatus}">
								${processGzstore.liangjustatus}
							</option>
							<option value="是">
								是
							</option>
							<option value="否">
								否
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<th align="right">
						是否首检:
					</th>
					<td>
						<select id="zjStatus" name="processGzstore.zjStatus">
							<option value="${processGzstore.zjStatus}">
									${processGzstore.zjStatus}
							</option>
							<option value="no">
								no
							</option>
							<option value="yes">
								yes
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<th align="right">
						设备验证:
					</th>
					<td>
						<select name="processGzstore.shebeistatus">
							<option value="${processGzstore.shebeistatus}">
								${processGzstore.shebeistatus}
							</option>
							<option value="是">
								是
							</option>
							<option value="否">
								否
							</option>
						</select>
					</td>
				</tr>
				<tr>
				<th align="right">
						是否转存:
					</th>
					<td>
						<select name="processGzstore.needSave">
							<option value="${processGzstore.needSave}">
								${processGzstore.needSave}
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
					<th align="right">
						精益计算:
					</th>
					<td>
						<select name="processGzstore.isJisuan">
								<option value="${processGzstore.isJisuan}">
									${processGzstore.isJisuan}
								</option>
								<option value="yes">
									是
								</option>
								<option value="no">
									否
								</option>
						</select>
					</td>
					<td colspan="2" align="center">
						是否需要辅料
						<s:if test="processGzstore.isNeedFuliao=='yes'">
							<input type="radio" name='processGzstore.isNeedFuliao'
								value="yes" checked="checked" onchange="changeFuliao('是')">是
					  <input type="radio" name='processGzstore.isNeedFuliao' value="no"
								onchange="changeFuliao('否')"> 否
					 </s:if>
						<s:else>
							<input type="radio" name='processGzstore.isNeedFuliao'
								value="yes" onchange="changeFuliao('是')">是
					  <input type="radio" name='processGzstore.isNeedFuliao' value="no"
								checked="checked" onchange="changeFuliao('否')"> 否
					 </s:else>
					</td>


				</tr>
				<tr id="fuliaoTr">
					<td colspan="6" align="center">
						<s:if test="processGzstore.isNeedFuliao=='yes'">
							<table id="fuliaoTb">
								</s:if>
								<s:else>
									<table id="fuliaoTb" style="display: none;">
										</s:else>
										<tr>
											<td style="border-top: 0px; border-left: 0px" align="center">
												类别
											</td>
											<td style="border-top: 0px; border-left: 0px" align="center">
												名称
											</td>
											<td style="border-top: 0px; border-left: 0px" align="center">
												规格
											</td>
											<td style="border-top: 0px; border-left: 0px" align="center">
												单位
											</td>
											<td style="border-top: 0px; border-left: 0px" align="center">
												权值比(工序:辅料)
											</td>
											<td
												style="border-top: 0px; border-left: 0px; border-right: 0px"
												align="center">
												<input type="button" value="增加" onclick="addFuliaoLine()">
											</td>
										</tr>
										<tr id='fuliaoTr0'>
											<td style="border-top: 0px; border-left: 0px" align="center">
												<SELECT id="type0" name="processGzstore.fuliaoList[0].type">
													<option>
														请选择
													</option>
													<option>
														备件
													</option>
													<option>
														金属五交材料
													</option>
													<option>
														工具
													</option>
													<option>
														办公用品
													</option>
													<option>
														杂品
													</option>
													<option>
														金属五交
													</option>
													<option>
														工装
													</option>
													<option>
														五金
													</option>
													<option>
														包装物
													</option>
												</SELECT>
												<td style="border-top: 0px; border-left: 0px" align="center">
													<div id="showAll0"
														style="background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;">
													</div>
													<input type="text" id="shortname0" onkeyup="getAllNames(0)"
														style="height: 20px;"
														onFocus="init('shortname0','showAll0',0)"
														onBlur="hidediv('showAll0')"
														name="processGzstore.fuliaoList[0].name" />
												</td>
												<td style="border-top: 0px; border-left: 0px" align="center">
													<input id="specification0"
														name="processGzstore.fuliaoList[0].specification">
												</td>

												<td style="border-top: 0px; border-left: 0px" align="center">
													<input id="unit0" name="processGzstore.fuliaoList[0].unit">
												</td>

												<td style="border-top: 0px; border-left: 0px" align="center">
													<input id="quanzhi10"
														name="processGzstore.fuliaoList[0].quanzhi1"
														style="width: 80">
													:
													<input id="quanzhi20"
														name="processGzstore.fuliaoList[0].quanzhi2"
														style="width: 80">
												</td>

												<td
													style="border-top: 0px; border-left: 0px; border-right: 0px"
													align="center">
													<input type="button" value="删除"
														onclick="deleteFuliaoLine(0)">
												</td>
										</tr>
									</table>
									</td>
									</tr>
									<tr>
										<td colspan="4" align="center">
											<input type="hidden" name="processGzstore.id"
												value="${processGzstore.id}" />
											<input type="hidden" name="processGzstore.processNO"
												value="${processGzstore.processNO}" />
											<input type="submit" value="修改" class="input" />
										</td>
									</tr>
							</table>

							</form>
							<script type="text/javascript">
$(function() {
	var errorMessage = '${errorMessage}';
	if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
		//				parent.newUrl();//调用其父页面的方法
	}
});
//--------------------------------------------辅料--------------------------------------
function changeFuliao(obj) {
	if (obj == '是') {
		if (fuliaoSize == null || fuliaoSize == 0) {
			fuliaoSize = 0;
			fuliaoSize = -1;
			addFuliaoLine();
		}
		$("#fuliaoTb").show();
	} else {
		$("#fuliaoTb").hide();
	}
}

var fuliaoSize = '<s:property value="processGzstore.processGzstoreFuLiaos.size()"/>';
var maxFuLiaoIndex = fuliaoSize - 1;//辅料最大下标
function addFuliaoLine() {
	maxFuLiaoIndex++;
	var html = "<tr id='fuliaoTr"
			+ maxFuLiaoIndex
			+ "'><td style='border-top: 0px;border-left: 0px' align='center'>"
			+ "<SELECT id='type"
			+ maxFuLiaoIndex
			+ "' name='processGzstore.fuliaoList["
			+ maxFuLiaoIndex
			+ "].type'><option>请选择</option><option >备件</option><option >金属五交材料</option><option>工具</option><option>办公用品</option><option>杂品</option><option>金属五交</option><option>工装</option><option>五金</option><option>包装物</option></SELECT> </td>"
			+ "<td style='border-top: 0px;border-left: 0px' align='center'> <div id='showAll"
			+ maxFuLiaoIndex
			+ "' style='background-color: #ffffff; position: absolute; visibility: hidden; z-index: 1;'> </div>"
			+ "<input type='text' id='shortname"
			+ maxFuLiaoIndex
			+ "' onkeyup='getAllNames("
			+ maxFuLiaoIndex
			+ ")' style='height: 20px;' onFocus='init(\"shortname"
			+ maxFuLiaoIndex
			+ "\",\"showAll"
			+ maxFuLiaoIndex
			+ "\","
			+ maxFuLiaoIndex
			+ ")' onBlur='hidediv(\"showAll"
			+ maxFuLiaoIndex
			+ "\")' name='processGzstore.fuliaoList["
			+ maxFuLiaoIndex
			+ "].name' /> </td>"
			+ "<td style='border-top: 0px;border-left: 0px' align='center'><input id='specification"
			+ maxFuLiaoIndex
			+ "' name='processGzstore.fuliaoList["
			+ maxFuLiaoIndex
			+ "].specification'> </td>"
			+ "<td style='border-top: 0px;border-left: 0px' align='center'><input id='unit"
			+ maxFuLiaoIndex
			+ "' name='processGzstore.fuliaoList["
			+ maxFuLiaoIndex
			+ "].unit'> </td>"
			+ "<td style='border-top: 0px;border-left: 0px' align='center'><input id='quanzhi1"
			+ maxFuLiaoIndex
			+ "' name='processGzstore.fuliaoList["
			+ maxFuLiaoIndex
			+ "].quanzhi1' style='width: 80'>"
			+ ":<input id='quanzhi2"
			+ maxFuLiaoIndex
			+ "' name='processGzstore.fuliaoList["
			+ maxFuLiaoIndex
			+ "].quanzhi2' style='width: 80'></td>"
			+ "<td style='border-top: 0px;border-left: 0px;border-right: 0px' align='center'><input type='button' value='删除' onclick='deleteFuliaoLine("
			+ maxFuLiaoIndex + ")'> </td>" + "</tr>";
	$("#fuliaoTb>tbody>tr").eq(fuliaoSize).after(html);
	fuliaoSize++;
}

function deleteFuliaoLine(index) {
	if (fuliaoSize == 1) {
		alert("至少一条辅料");
		return;
	}
	if ((maxFuLiaoIndex - index) == 0) {
		maxFuLiaoIndex--;
	}
	fuliaoSize--;
	$("#fuliaoTr" + index).remove();
}
//--------模糊查询辅料
function getAllNames(index) {
	count_seach = 1;
	var shortname = $("#shortname" + index).val();
	if (shortname == null || shortname == "") {
		return;
	}
	$.ajax( {
		type : "POST",
		url : "yuanclAndWaigjAction_getAllNames.action",
		dataType : "json",
		data : {
			'yuanclAndWaigj.name' : shortname,
			'yuanclAndWaigj.clClass' : '辅料'
		},
		success : function(data) {
			$("#showAll" + index).empty();
			$(data).each(
					function() {
						var name = $(this).attr('name').replace(
								$("#shortname" + index).val(),
								"<font color='red'>"
										+ $("#shortname" + index).val()
										+ "</font>");
						$("#showAll" + index).append(
								"<div onmouseover='ondiv(this)' onmouseout='outdiv(this,shortname"
										+ index + ")' onclick='selectdiv(this,"
										+ index + ")' align='left'>" + name
										+ "," + $(this).attr('specification')
										+ "," + $(this).attr('unit')
										+ "<span style='display: none;'>"
										+ $(this).attr('name') + ","
										+ $(this).attr('specification') + ","
										+ $(this).attr('unit')
										+ "</span></div>");
					});
		}
	});
}
//选中工装
function selectdiv(obj, index) {
	var shortname = document.getElementById("shortname" + index);
	var spaniner = $(obj).find("span").html();
	var spaniners = spaniner.split(",");
	shortname.value = spaniners[0];
	var specification = document.getElementById("specification" + index);
	specification.value = spaniners[1];
	var unit = document.getElementById("unit" + index);
	unit.value = spaniners[2];
	var showAll = document.getElementById("showAll" + index);
	showAll.style.visibility = "hidden";
}
</script>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

</html>
