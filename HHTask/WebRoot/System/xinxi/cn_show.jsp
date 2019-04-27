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
		<STYLE type="text/css">
.input_a {
	width: 50px;
}

.input_b {
	width: 110px;
}
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<font id="zi_font" color="red" size="5">${errorMessage}</font>
				<s:if test="status == 'update'">
					<form action="IntelligentDiagnosisAction_updateis.action"
						method="post" onsubmit="return check()">
						<table class="table" id="mytable">
							<tr>
								<th>
									企业名称:
								</th>
								<td>
									<input type="text" name="cn.campanyname"
										value="${cn.campanyname}" id="campanyname"
										onchange="changvalue()" />
									<input type="hidden" name="ceshiNo" value="${cn.ceshiNo}" />
								</td>
								<td colspan="7" align="center">
									<SPAN id="cp_span"></SPAN>
								</td>
							</tr>
							<tr align="center">
								<th>
									流程内容
								</th>
								<th>
									是否人工完成
								</th>
								<th>
									部门
								</th>
								<th>
									岗位
								</th>
								<th>
									岗位人数
								</th>
								<th>
									年龄
								</th>
								<th>
									专职/兼职
								</th>
								<th>
									任职时间
								</th>
								<th>
									人均年收入
								</th>
							</tr>
							<s:iterator value="isList" id="is" status="pageStatus">
								<tr id="tr_${pageStatus.index}" align="center">
									<td>
										<input type="text"
											name="cn.islist[${pageStatus.index}].processName"
											id="processName_0" value="${is.processName}" class="input_b">
									</td>
									<td>
										<s:if test="#is.isman =='yes'">
											<input type="radio"
												name="cn.islist[${pageStatus.index}].isman"
												checked="checked" id="isman_0" value="yes">是
									<input type="radio" name="cn.islist[${pageStatus.index}].isman"
												id="noman_0" value="no">否
								</s:if>
										<s:else>
											<input type="radio"
												name="cn.islist[${pageStatus.index}].isman" id="isman_0"
												value="yes">是
									<input type="radio" name="cn.islist[${pageStatus.index}].isman"
												id="noman_0" value="no" checked="checked">否
								</s:else>
									</td>
									<td>
										<input type="text" name="cn.islist[${pageStatus.index}].dept"
											id="dept_0" class="input_a" value="${is.dept}"
											class="input_b" />
									</td>
									<td>
										<input type="text" name="cn.islist[${pageStatus.index}].post"
											id="post_0" class="input_a" value="${is.post}" />
									</td>
									<td>
										<input type="text"
											name="cn.islist[${pageStatus.index}].postnum" id="postnum_0"
											class="input_a" onblur="numyanzhen(this)"
											onkeyup="numyanzhen(this)" value="${is.postnum}" />
									</td>
									<td>
										<input type="text" name="cn.islist[${pageStatus.index}].age"
											id="age_0" value="${is.age}" onchange="formatage('0')"
											class="input_b" />
									</td>
									<td>
										<input type="text" name="cn.islist[${pageStatus.index}].zorj"
											id="zorj_0" value="${is.zorj}" class="input_b" />
									</td>
									<td>
										<input type="text"
											name="cn.islist[${pageStatus.index}].servingtime"
											id="servingtime_0" style="width: 130px;"
											onchange="formatervingtime('0')" value="${is.servingtime}"
											class="input_b">
										(年)
									</td>
									<td>
										<input type="text" name="cn.islist[${pageStatus.index}].pcai"
											id="pcai_0" value="${is.pcai}" class="input_a"
											onchange="checkpcai('0')" />
										(万元)
										<input type="button"
											onclick="delLine('tr_${pageStatus.index}')" value="删除">
									</td>
								</tr>
							</s:iterator>
							<tr align="center" id="hj_tr">
								<th>
									人工成本合计
								</th>
								<th></th>
								<td></td>
								<td></td>
								<td>
									<s:if test="cn.totalman!=null">${cn.totalman}人</s:if>
								</td>
								<th></th>
								<td></td>
								<td></td>
								<td>
									<s:if test="cn.totalpaci!=null">
										<fmt:formatNumber value="${cn.totalpaci}"
											maxFractionDigits="2"></fmt:formatNumber>万元
							</s:if>
								</td>
							</tr>
							<tr>
								<th>
									人工岗位年总收入
								</th>
								<td colspan="8" align="center">
									<s:if test="cn.total!=null">
										<font color="red" size="5">${cn.total}万元</font>
									</s:if>
								</td>
							</tr>
						</table>
						<input type="hidden" value="${status}" name="status" />
						<input type="hidden" value="${cn.id}" name="cn.id" />
						<input type="button" onclick="addLine()" value="追加"
							style="width: 75px; height: 35px;">
						<input type="submit" value="修改" style="width: 75px; height: 35px;"
							id="sub" />
						<input type="reset" value="重置" style="width: 75px; height: 35px;" />
					</form>
				</s:if>
				<s:elseif test="status =='mingxi'">
					<table class="table" id="mytable">
						<tr>
							<th colspan="7">
								企业名称: ${cn.campanyname}
							</th>
						</tr>
						<tr>
							<th colspan="7">
								企业规模: ${cn.guimo}人
							</th>
						</tr>
						<tr>
							<th colspan="7">
								企业性质: ${cn.type}
							</th>
						</tr>
						<tr>
							<th colspan="7">

								联 系 人 : ${cn.lianxiren}
							</th>
						</tr>
						<tr>
							<th colspan="7">
								联系方式: ${cn.phone}
							</th>
						</tr>

						<tr align="center">
							<th>
								序号
							</th>
							<th>
								是否需要
							</th>
							<th>
								岗位职责
							</th>
							<th>
								是否人工
							</th>
							<%--<th >部门</th>
							<th >岗位</th>
							--%>
							<th>
								岗位人数
							</th>
							<%--<th>
								年龄
							</th>
							--%>
							<%--<th>专职/兼职</th>
							<th>任职时间</th>
							--%>
							<th>
								人均年收入(万元)
							</th>
							<th>
								可节省
							</th>
						</tr>
						<s:iterator value="isList" id="is" status="pageIs">
							<s:if test="#is.iseconomize =='yes'">
								<tr id="tr_${pageStatus.index}" align="center"
									style="background-color: yellow;">
							</s:if>
							<s:else>
								<tr id="tr_${pageStatus.index}" align="center">
							</s:else>
							<td>
								${pageIs.index+1}
							</td>
							<td>
								${is.isxuyao}
							</td>
							<td>
								${is.processName}
							</td>
							<td>
								<s:if test="#is.isman =='yes'">
									是
								</s:if>
								<s:else>
									否
								</s:else>
							</td>
							<%--<td>
									${is.dept}
								</td>
								<td>
									${is.post}
								</td>
								--%>
							<td>
								${is.postnum}人
							</td>
							<%--<td>
									<s:if test="#is.age!=null">${is.age}岁</s:if>
								</td>
								--%>
							<%--<td>
									${is.zorj}
								</td>
								<td>
									${is.servingtime}年
								</td>
								--%>
							<td>
								<s:if test="#is.pcai != null">${is.pcai}</s:if>
							</td>
							<td>
								<s:if test="#is.iseconomize != null">${is.iseconomize}</s:if>
							</td>
							</tr>
						</s:iterator>
						<%--<tr align="center" id="hj_tr">
							<th>
								人工成本合计
							</th>
							<th></th>
							<td></td>
							<td></td>
							<td>
								<s:if test="cn.totalman!=null">${cn.totalman}人</s:if>
							</td>
							<th></th>
							<td></td>
							<td></td>
							<td>
								<s:if test="cn.totalpaci!=null">
									<fmt:formatNumber value="${cn.totalpaci}" maxFractionDigits="2"></fmt:formatNumber>万元
							</s:if>
							</td>
						</tr>
						<tr>
							<th>
								人工岗位年总收入
							</th>
							<td colspan="8" align="center">
								<s:if test="cn.total!=null">
									<font color="red" size="5">${cn.total}万元</font>
								</s:if>
							</td>
						</tr>
					--%>
					</table>
				</s:elseif>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<SCRIPT type="text/javascript">
var index=${size};
function addLine() {
	var newLine = '<tr id=tr_'+index+' align="center"><td><input type=text name=cn.islist['+index+'].processName id=processName_'+index+' class="input_b"></td>' +
	'<td><input type=radio name=cn.islist['+index+'].isman checked="checked" id=isman_'+index+' value="yes">是' +
	'<input type=radio name=cn.islist['+index+'].isman id=noman_'+index+' value="no">否</td>' +
	'<td><input type=text name=cn.islist['+index+'].dept id=dept_'+index+' class="input_a"/></td>' +
	'<td><input type=text name=cn.islist['+index+'].post id=post_'+index+' class="input_a"/></td>' +
	'<td><input type=text name=cn.islist['+index+'].postnum id=postnum_'+index+' class="input_a"' +
	'onkeyup=numyanzhen(this) onblur=numyanzhen(this)/></td>' +
	'<td><input type=text name=cn.islist['+index+'].age id=age_'+index+' onchange="formatage('+index+')" class="input_b"/></td>' +
	'<td><input type=text name=cn.islist['+index+'].zorj id=zorj_'+index+' class="input_b"/></td>' +
	'<td><input type=text name=cn.islist['+index+'].servingtime id=servingtime_'+index+' style="width: 130px;"' +
	'onchange="formatervingtime('+index+')" class="input_b">(年)</td>' +
	'<td><input type=text name=cn.islist['+index+'].pcai id=pcai_'+index+' class="input_a" onchange="checkpcai('+index+')"/>(万元)' +
	'' +
	'<input type=button onclick=delLine("tr_'+index+'") value=删除 /></td></tr>';
	$("#mytable").append(newLine);
	index++;
}

function delLine(obj) {
	if(index<=1){
		alert('再删就没有了哦。')
	}else{
	$("#"+obj).remove();
	index--;
	var n = $('#mytable tr').length;
	for(var i=0;i<index;i++){
		var id=	$('#mytable tr')[n-(i+1)].id 
		var num =id.split('_');
		if(num.length == 2){
			$("#processName_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].processName');
			$("#isman_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].isman');
			$("#isman_"+num[1]).attr('checked','checked');
			$("#noman_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].isman');
			$("#dept_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].dept');
			$("#post_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].post');
			$("#postnum_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].postnum');
			$("#age_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].age');
			$("#age_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].age');
			$("#zorj_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].zorj');
			$("#servingtime_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].servingtime');
			$("#pcai_"+num[1]).attr('name','cn.islist['+(index-i-1)+'].pcai');
		}
		
	}
	}
}
function changvalue(obj){
	if(obj!=null && obj.value!=""){
		var value = obj.value;
		$("#cp_span").html("<font color='red' size='5'>"+value+"</font>企业的在线智能诊断!");
	}
}

function numyanzhen(obj,tag) {
	var num = obj.value;
	var ty = /^[0-9]*[1-9][0-9]*$/;
	var bChk=ty.test(obj.value);;
	if (!bChk) {
		obj.value = "";
		obj.focus();
		obj.select();
		$("#zi_font").html("只能输入正整数");
	}
}
function formatage(num){
	var postnum = document.getElementById('postnum_'+num);
	var age = document.getElementById('age_'+num);
	var n=	parseInt(postnum.value)
	var szReg=/^[0-9]*[1-9][0-9]*$/;	
	var str = "";
	if(n<=1){
		str ="请输入正整数";
	}else{
		szReg=/^(\d{1,2},{1}\d{0,2}){1,}$/;
		str = "请安此种41,50,16这种格式输入";
	}
	var bChk=szReg.test(age.value);
	if(!bChk){
		age.value = "";
		age.focus();
		age.select();
		$("#zi_font").html(str);
	}
	
}
function formatervingtime(num){
	var postnum = document.getElementById('postnum_'+num);
	var servingtime = document.getElementById('servingtime_'+num);
	var n = parseInt(postnum.value);
	var szReg=/^\d+(\.\d+)?$/;	
	var str = "";
	if(n<=1){
		str ="请输入正数";
	}else{
		szReg=/^[\d+(\.\d+)?,{1}\d{1,}+(\.\d+)?]{1,}$/;
		str = "请安此种4.4,5,16这种格式输入";
	}
	var bChk=szReg.test(servingtime.value);
	if(!bChk){
		servingtime.value = "";
		servingtime.focus();
		servingtime.select();
		$("#zi_font").html(str);
	}
}
function checkpcai(num){
	var pcai =  document.getElementById('pcai_'+num);
	if(pcai!=null && pcai.value!=""){
		var szReg=/^\d+(\.\d+)?$/;
		var bChk=szReg.test(pcai.value);
		if(!bChk){
			pcai.value = "";
			pcai.focus();
			pcai.select();
			$("#zi_font").html('请输入正数');
		}
	}
}
function check(){
	var vipNo = document.getElementById('vipNo');
	if(vipNo!=null && vipNo.value==""){
		$("#zi_font").html('请先选择企业');
		return false;
	}
	for(var i=0;i<index; i++){
		var processName = document.getElementById('processName_'+i);
		var postnum = document.getElementById('postnum_'+i);
		var pcai = document.getElementById('pcai_'+i);
		if(processName!=null && processName.value == ''){
			$("#zi_font").html('请填写流程内容');
			processName.focus();
			return false;
		}else if(postnum!=null && postnum.value == ''){
			$("#zi_font").html('请填写员工人数');
			postnum.focus();
			return false;
		}else if(pcai!=null && pcai.value == ''){
			$("#zi_font").html('请填写人均年收入');
			pcai.focus();
			return false;
		}
	}
	document.getElementById('sub').disabled="disabled";
	return true;
	
}
</SCRIPT>
	</body>
</html>
