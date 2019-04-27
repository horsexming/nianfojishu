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
	<STYLE type="text/css">
.button {
	display: inline-block;
	zoom: 1; /* zoom and *display = ie7 hack for display:inline-block */ *
	display: inline;
	vertical-align: baseline;
	margin: 0 2px;
	outline: none;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	font: 14px/ 100% Arial, Helvetica, sans-serif;
	padding: .5em 2em .55em;
	text-shadow: 0 1px 1px rgba(0, 0, 0, .3);
	-webkit-border-radius: .5em;
	-moz-border-radius: .5em;
	border-radius: .5em;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
}

.button:hover {
	text-decoration: none;
}

.button:active {
	position: relative;
	top: 1px;
}
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<font id="zi_font" color="red" size="5">${errorMessage}</font>
				<form action="IntelligentDiagnosisAction_addResearchReport.action" method="POST" onsubmit="return check()">
					<table class="table" id="rr_table">
						<tr>
							<th colspan="6">
								&nbsp;&nbsp;企业名称:
								<input type="text" name="cn.campanyname" id="campanyname"
									onchange="changvalue(this)" class="horizontalLine"
									onfocus="chageClass(this,'')"
									onblur="chageClass(this,'horizontalLine')" />
								<font color="red">*</font>
							</th>
						</tr>
						<tr>
							<th colspan="6">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;企业规模:
								<input type="text" name="cn.guimo" id="guimo"
									onchange="changvalue(this)" class="horizontalLine"
									onfocus="chageClass(this,'')"
									onblur="chageClass(this,'horizontalLine')"
									style="width: 160px;" />
								(人数)
							</th>
						</tr>
						<tr>
							<th colspan="6">
								企业性质:&nbsp;
								<input type="text" name="cn.type" id="type"
									onchange="changvalue(this)" class="horizontalLine"
									onfocus="chageClass(this,'')"
									onblur="chageClass(this,'horizontalLine')" />
							</th>
						</tr>
						<tr>
							<th colspan="6">

								负&nbsp;&nbsp;责&nbsp;人:
								<input type="text" name="cn.lianxiren" id="lianxiren"
									onchange="changvalue(this)" class="horizontalLine"
									onfocus="chageClass(this,'')"
									onblur="chageClass(this,'horizontalLine')" /><font color="red">*</font>
							</th>
						</tr>
						<tr>
							<th colspan="6">
							联系电话:
								<input type="text" name="cn.phone" id="phone" maxlength="11"
									onchange="mobilephoneyanzhen(this)" class="horizontalLine"
									onfocus="chageClass(this,'')"
									onblur="chageClass(this,'horizontalLine')"
									onkeyup="mobilephoneyanzhen(this)" /><font color="red">*</font>
								<input type="hidden" value="调研" name="cn.groups"/>
							</th>
						</tr>
									<tr>
										<th>序号</th>
										<th>部门</th>
										<th>问题点</th>
										<th>希望达到的效果</th>
										<th>PEBS解决方案</th>
										<th></th>
									</tr>
									<tr align="center">
										<td>1</td>
										<td>
											<input type="text" value="" name="cn.rrList[0].dept" id="dept_0"/>
										</td>
										<td>
											<textarea rows="2" cols="25" name="cn.rrList[0].cases" id="cases_0"></textarea>
										</td>
										<td>
											<textarea rows="2" cols="25" name="cn.rrList[0].expectedEffec" id="expectedEffec_0"></textarea>
										</td>
										<td>
											<textarea rows="2" cols="25" name="cn.rrList[0].solution" id="solution_0"></textarea>
										</td>
										<td>
											<input type="button" value="追加" onclick="addLine()"/>
											<input type="button" value="删除" onclick="delLine()"/>
										</td>
									</tr>
					</table>
					<font id="zi_font1" color="red" size="5">${errorMessage}</font>
					<input type="hidden" value="diaoyan" name="status"/>
					<input type="submit" value="提交" class="button"/>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function mobilephoneyanzhen(obj){
	 if(!(/^1[3|4|5|8|7|9][0-9]\d{8}$/.test(obj.value))){ 
        $("#zi_font").html("请输入正确的手机号");
        $("#phoneNumber_font").css({
    		color:"red"
    	});
      	 obj.focus(); 
    } else{
    	$("#zi_font").html("");
    }
}
var index = 1;
function addLine(){
	var newtr= '<tr align="center"><td>'+(index+1)+'</td>' +
	'<td><input type="text" value="" name="cn.rrList['+index+'].dept" id="dept_'+index+'" /></td>' +
	'<td><textarea rows="2" cols="25" name="cn.rrList['+index+'].cases" id="cases_'+index+'" ></textarea></td>' +
	'<td><textarea rows="2" cols="25" name="cn.rrList['+index+'].expectedEffec" id="expectedEffec_'+index+'" ></textarea></td>' +
	'<td><textarea rows="2" cols="25" name="cn.rrList['+index+'].solution" id="solution_'+index+'" ></textarea>' +
	'</td><td></td></tr>'
	$('#rr_table').append(newtr);
	index++;
}
function delLine(){
	var n = $('#rr_table tr').length;
	if (index == 1) {
			alert("只剩最后一项了,再删真没了");
		return;
	}
	$($('#rr_table tr')[n-1]).remove();
	index--;
}

function check(){
	var campanyname = $('#campanyname').val();
	var lianxiren = $('#lianxiren').val();
	var phone = $('#phone').val();
	if(campanyname == ''){
		$("#zi_font").html('请填写企业名称');
		$("#zi_font1").html('请填写企业名称');
		$('#campanyname').focus();
		return false;
	}else if(lianxiren == ''){
		$("#zi_font").html('请填写负责人');
		$("#zi_font1").html('请填写负责人');
		$('#lianxiren').focus();
		return false;
	}else if(phone == ''){
		$("#zi_font").html('请填写联系电话');
		$("#zi_font1").html('请填写联系电话');
		$('#phone').focus();
		return false;
	}
	
	for(var i=0;i<index; i++){
		var dept = document.getElementById('dept_'+i);
		var cases = document.getElementById('cases_'+i);
		var expectedEffec = document.getElementById('expectedEffec_'+i);
		var solution = document.getElementById('solution_'+i);
		if(dept!=null && dept.value == ''){
			$("#zi_font").html('第'+(i+1)+'行未填写部门');
			$("#zi_font1").html('第'+(i+1)+'行未填写部门');
			dept.focus();
			return false;
		}else if(cases!=null && cases.value == ''){
			$("#zi_font").html('第'+(i+1)+'行未填写问题点');
			$("#zi_font1").html('第'+(i+1)+'行未填写问题点');
			cases.focus();
			return false;
		}else if(expectedEffec!=null && expectedEffec.value == ''){
			if(!confirm('第'+(i+1)+'行未填写希望达到的效果，是否继续提交?')){
				expectedEffec.focus();
				return false;
			}
		}else if(solution!=null && solution.value == ''){
			if(!confirm('第'+(i+1)+'行未填写PEBS解决方案，是否继续提交?')){
				solution.focus();
				return false;
			}
		}
	}
	document.getElementById('sub').disabled="disabled";
	return true;
	
}
</SCRIPT>
	</body>
</html>
