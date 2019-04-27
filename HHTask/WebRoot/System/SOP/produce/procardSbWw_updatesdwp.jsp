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
	<title></title>
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
							<span id="title">工序外委</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;">
					</iframe>
				</div>
				<div id="show2" style="display: none;background-color: white;" >
						<table id="sonCardTable" class="table">
						</table>
					</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>重选外委</h3>
				<div align="left">原外委： 件号:${procard.markId},批次:{procard.selfCard},工序号:{waigouplan.processNOs},工序名称:{waigouplan.processNames},
				数量:${procard.wwCount}/${waigouplan.unit},来源:{waigouplan.wwSource},类型:{waigouplan.wwType}</div>
			</div>
			<form action="procardTemplateGyAction_updateSbsdwp.action" method="post">
			<input type="hidden" name="id" value="${id}" >
			<input type="hidden" name="id2" value="${id2}" >
			<input type="hidden" name="procard.id" value="${procard.id}" >
			
			<table class="table">
			<tr>
				<td align="center">序号<input type="checkbox" id="cbAll"
						onclick="cbAll()"/>全选
				</td>
				<td align="center">工序号
				</td>
				<td align="center">工序名称
				</td>
				<td align="center">总数量
				</td>
				<td align="center">外委数量
				</td>
				<td align="center">类型
				</td>
				<td align="center">关联外购
				</td>
			</tr>
			<s:iterator value="processInforList" id="pageProcess"  status="pstatus">
			<tr>
			<td align="left">
				<input type="checkbox" name="checkboxs" onclick="cbsingle(<s:property value="#pstatus.index" />)"
						value="${pageProcess.id}"/>${pstatus.index+1}
			</td>
			<td align="left">
			${pageProcess.processNO}
			</td>
			<td align="left">
			${pageProcess.processName}
			</td>
			<td align="right">
			${procard.filnalCount}
			</td>
			<s:if test="#pstatus.index==0">
				<td rowspan="processInforList.size()" align="left">
				<input type="text" name="wwApplyDetail.applyCount">
				</td>
				<td rowspan="processInforList.size()" align="left">
				<input name="wwApplyDetail.wwType" value="工序外委" type="radio" onclick="selectwwtype('工序外委')"/>工序外委
				<input name="wwApplyDetail.wwType" value="包工包料" type="radio" onclick="selectwwtype('包工包料')"/>包工包料
				<label id="wwxclab" style="display: none;">(<input name="wwApplyDetail.relatDown" value="是" type="radio"/>是<input name="wwApplyDetail.relatDown" value="否" type="radio" checked="checked"/>否 )
				</label>
				</td>
				<td rowspan="processInforList.size()" align="left">
				<label id="wwMarkId"></label><input type="hidden" id="wwMarkIdInput" name="wwApplyDetail.wwMarkId"> <input type="button" value="选择" onclick="getxcwgj(${procard.id})"> 
				</td>
			</s:if>
			</tr>
			</s:iterator>
			<tr>
			<td align="center"><input type="submit" value="提交" style="width: 40px;height: 60px;" > </td>
			</tr>
			</table>
			</form>
		</div>
		<%@include file="/util/foot.jsp"%>
<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function cbAll(index){
	var checkAll=document.getElementById("cbAll");
			var checkboxs=document.getElementsByName("checkboxs");
			if(checkAll.checked==true){
				for(var i=0;i<checkboxs.length;i++){
					checkboxs[i].checked=true;
				}
			}else{
				for(var i=0;i<checkboxs.length;i++){
					checkboxs[i].checked=false;
				}
			}
}
function cbsingle(index){
	var checkAll=document.getElementById("cbAll");
			var checkboxs=document.getElementsByName("checkboxs");
			var count=0;
			for(var i=0;i<checkboxs.length;i++){
				if(checkboxs[i].checked==false){
					checkAll.checked=false;
				}else{
					count++;
				}
			}
			if(count==checkboxs.length){
				checkAll.checked=true;
			}
}
function selectwwtype(type){
	if(type=="工序外委"){
		$("#wwxclab").show();
	}else{
		$("#wwxclab").hide();
	}
}

function getxcwgj(procardId){
	$("#show2").show();
	$("#operatingDiv").hide();
	$("#sonCardTable").empty();
	chageDiv("block");
	$
			.ajax( {
				type : "post",
				url : "ProcardAction!findSonMarkId.action",
				dataType : "json",
				data : {
					id : procardId
				},
				success : function(data) {
					var i = 0;
					var htmlbefor = "";
					var html = "";
					var htmlend = "";
					$(data)
							.each(
									function() {
										if (i % 2 == 0) {
											html += "<tr><td><input type='checkbox' name='checkboxs2' value='"
													+ i
													+ "' onchange='chageNum2()'></td>"
													+ "<td align='center'><input type='hidden' id='markId"
													+ i
													+ "' value='"
													+ data[i][0]
													+ "'>"
													+ data[i][0]
													+ "</td>"
													+ "<td align='center'>"
													+ data[i][1]
													+ "</td>"
													+ "<td align='center'>"
													+ data[i][2]
													+ "</td>"
													+ "<td align='center'>"
													+ data[i][3] + "</td>";
										} else {
											html += "<td><input type='checkbox' name='checkboxs2' value='"
													+ i
													+ "' onchange='chageNum2()'></td>"
													+ "<td align='center'><input type='hidden' id='markId"
													+ i
													+ "' value='"
													+ data[i][0]
													+ "'>"
													+ data[i][0]
													+ "</td>"
													+ "<td align='center'>"
													+ data[i][1]
													+ "</td>"
													+ "<td align='center'>"
													+ data[i][2]
													+ "</td>"
													+ "<td align='center'>"
													+ data[i][3] + "</td></tr>";
										}
										i++;
									});
					if (i == 1) {
						htmlbefor = "<tr><th>全选 <input type='checkbox' id='checkAll2' onchange='chageAllCheck2()'>选择</th>" + "<th>件号</th>"
								+ "<th>名称</th>" + "<th>规格</th>"
								+ "<th>库存</th></tr>"
						html += "</tr>";
						htmlend = "<tr><td align='center' colspan='5'><input type='button' value='确定' style='width: 60px;height: 20px' onclick='setMarkId();'></td></tr>"
					} else {
						htmlbefor = "<tr><th>全选 <input type='checkbox' id='checkAll2' onchange='chageAllCheck2()'>选择</th>" + "<th>件号</th>"
								+ "<th>名称</th>" + "<th>规格</th>" + "<th>库存</th>"
								+ "<th>选择</th>" + "<th>件号</th>" + "<th>名称</th>"
								+ "<th>规格</th>" + "<th>库存</th></tr>"
						if (i % 2 == 1) {
							html += "<td></td>" + "<td></td>" + "<td></td>"
									+ "<td></td>" + "<td></td></tr>";
						}
						htmlend = "<tr><td align='center' colspan='10'><input type='button' value='确定' style='width: 60px;height: 20px' onclick='setMarkId();'></td></tr>"
					}
					$("#sonCardTable").append(htmlbefor + html + htmlend);
				}
			});
	
}


function setMarkId() {
	var checkboxs = document.getElementsByName("checkboxs2");
	var markIds = "";
	var index2 = -1;
	for ( var i = 0; i < checkboxs.length; i++) {
		if (checkboxs[i].checked == false) {
			//checkAll.checked = false;
		} else {
			//count++;
			index2 = checkboxs[i].value;
			var markId = $("#markId" + index2).val();
			if (markIds == "") {
				markIds = markId;
			} else {
				markIds += ";" + markId;
			}
		}
	}
	if(markIds==""){
		alert("请先选择零件!");
		return false;
	}
	$("#wwMarkId" ).html(markIds + "</br>");
	$("#wwMarkIdInput" ).val(markIds);
	chageDiv('none');
}
function chageAllCheck2() {//全选框
	var checkAll = document.getElementById("checkAll2");
	var checkboxs = document.getElementsByName("checkboxs2");
	if (checkAll.checked == true) {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = true;
		}
	} else {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = false;
		}
	}

}
function chageNum2() {
	var checkAll = document.getElementById("checkAll2");
	var checkboxs = document.getElementsByName("checkboxs2");
	var count = 0;
	for ( var i = 0; i < checkboxs.length; i++) {
		if (checkboxs[i].checked == false) {
			checkAll.checked = false;
		} else {
			count++;
		}
	}
	if (count == checkboxs.length) {
		checkAll.checked = true;
	}

}
</SCRIPT>
	</body>
</html>
