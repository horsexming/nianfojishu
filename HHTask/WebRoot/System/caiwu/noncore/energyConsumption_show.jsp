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
	<div id="bodyDiv" align="center" class="transDiv" style="z-index: 2"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none; top: 20px;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
					<table style="width: 100%; margin-top: ">
						<tr>
							<td>
								您正在修改能耗信息:
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none');reload();">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 900px; margin: 0px; padding: 0px;"></iframe>
					</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<table class="table">
					<tr>
						<th>
							能耗类型:
						</th>
						<td>
							<SELECT name="energyConsumption.type" id="type" style="width: 152px;">
								<option value="">能耗类型</option>
								<option value="水费">水费</option>
								<option value="电费">电费</option>
							</SELECT>
						</td>
						<th>
							能耗单价:
						</th>
						<td>
							<input type="text" name="energyConsumption.unitPrice" id="unitPrice" onblur="mustBeNumber('unitPrice')"/>
						</td>
					</tr>
					<tr>
						<th>
							能耗名称:
						</th>
						<td>
							<input type="text" name="energyConsumption.name" id="name"/>
						</td>
						<th>
							是否限时:
						</th>
						<td>
							<SELECT name="energyConsumption.isTimeLimit" id="isTimeLimit" onclick="xianshi()" style="width: 152px;">
								<option value="否">否</option>
								<option value="是">是</option>
							</SELECT>
						</td>
					</tr>
					<tr id="time" style="display: none;">
						<th>
							限时起始时间:
						</th>
						<td>
							<input type="text" class="Wdate" name="energyConsumption.startTime" id="startTime"
							onclick="WdatePicker({dateFmt:'HH:mm:ss',skin:'whyGreen'})"
							/>
						</td>
						<th>
							限时结束时间:
						</th>
						<td>
							<input type="text" class="Wdate" name="energyConsumption.endTime" id="endTime"
							onclick="WdatePicker({dateFmt:'HH:mm:ss',skin:'whyGreen'})"
							/>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="button" value="添加" onclick="tijiao()" style="width: 80px; height: 30px;"/>
						</td>
					</tr>
				</table>
			</div>
			<div align="center">
				<h3>
					能耗信息表
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								序号
							</td>
							<td align="center">
								类型
							</td>
							<td align="center">
								单价
							</td>
							<td align="center">
								名称
							</td>
							<td align="center">
								是否限时
							</td>
							<td align="center">
								限时起始时间
							</td>
							<td align="center">
								限时结束时间
							</td>
							<td align="center">
								添加时间
							</td>
							<td align="center">
								添加人
							</td>
							<td align="center" colspan="2">
								操作类型
							</td>
						</tr>
					<tr id="add1">
					</tr>
					<s:iterator value="energyConsumptionList" id="samples" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${samples.type}
						</td>
						<td align="center">
							${samples.unitPrice}
						</td>
						<td align="center">
							${samples.name}
						</td>
						<td align="center">
							${samples.isTimeLimit}
						</td>
						<td align="center">
							${samples.startTime}
						</td>
						<td align="center">
							${samples.endTime}
						</td>
						<td align="center">
							${samples.saveTime}
						</td>
						<td align="center">
							${samples.saveUser}
						</td>
						<td align="center" colspan="2">
							<a 
								onclick="toupdate('${samples.id}')">修改</a>
							<a onclick="return window.confirm('您将删除数据，是否继续?')"
								href="NoncorePayableAction!deleteEner.action?id=${samples.id}">删除</a>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
var num = <s:property value="energyConsumptionList.size()"/>;

function xianshi(){
	if($("#isTimeLimit").val()=='是'){
		$("#time").show();
	}else{
		$("#time").hide();
		$("#startTime").val("");
		$("#endTime").val("");
	}
}
		
function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}

function tijiao(){
	if (!validateText("type", "类型")) {
		return false;
	}
	if (!validateText("unitPrice", "单价")) {
		return false;
	}
	if($("#unitPrice").val()<=0){
		alert("单价不能小于等于0");
		return false;
	}
	if (!validateText("name", "名称")) {
		return false;
	}
	if($("#isTimeLimit").val()=='是'){
		if (!validateText("startTime", "限时起始时间")) {
		return false;
		}
		if (!validateText("endTime", "限时结束时间")) {
			return false;
		}
	}
	$.ajax( {
		url : 'NoncorePayableAction!addEner.action',//上传
		type : 'post',
		secureuri : false,//一般设置为false
		dataType : 'json',//返回值类型 一般设置为json
		cache : false,//防止数据缓存
		data : {
			'energyConsumption.type' : $("#type").val(),
			'energyConsumption.unitPrice' : $("#unitPrice").val(),
			'energyConsumption.name' : $("#name").val(),
			'energyConsumption.isTimeLimit' : $("#isTimeLimit").val(),
			'energyConsumption.startTime' : $("#startTime").val(),
			'energyConsumption.endTime' : $("#endTime").val()
		},
		success : function(datas) //服务器成功响应处理函数
		{
			if(datas!=null){
				if(datas!=null){
					var newline = '<tr>' +
					'<td align="center">'+(num+1)+'</td>' +
					'<td align="center">'+datas.type+'</td> ' +
					'<td align="center">'+datas.unitPrice+'</td> ' +
					'<td align="center">'+datas.name+'</td> ' +
					'<td align="center">'+datas.isTimeLimit+'</td> ' +
					'<td align="center">'+datas.startTime+'</td> ' +
					'<td align="center">'+datas.endTime+'</td> ' +
					'<td align="center">'+datas.saveTime+'</td> ' +
					'<td align="center">'+datas.saveUser+'</td> ' +
					'<td align="center" colspan="2">' +
					'<a href="NoncorePayableAction!deleteDetail.action?id='+datas.id+'" onclick=\'return window.confirm("您将删除数据，是否继续?")\'>删除</a>' +
					'</td>' +
					'</tr>';
					num++;
					$("#add1").before(newline);
					$("#name").val("");
					$("#unitPrice").val("");
				}else{
					alert("信息重复，添加失败！");
				}
			}
		},
		error : function()//服务器响应失败处理函数
		{
			alert("服务器异常!");
		}
	});
}
function toupdate(id) {
	var url = "NoncorePayableAction!toupdateEner.action?energyConsumption.id=" + id;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}

</script>
	</body>
</html>
