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
 ul {
 position: absolute;
 list-style:inside; 
 left: 40%;

}
ul li{
			margin-top:5px;
			 border-bottom: 1px solid #666 dotted;
			 list-style-type:cjk-ideographic;
			text-align: left;
	 }
	 

</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div id="month" >
				<h2>客户满意度调查表</h2>
				<form action="KeHuManYiDiaoChaAction_addkhmydc.action" method="post" onsubmit="return check()" enctype="multipart/form-data">
				<table>
					<tr>
						<th>客户名称:</th>
						<td>
							<SELECT  onchange="changvalue(this,1)" >
							<option value="">--请选择--</option>
							<s:iterator value="cmList" id="cm" >
								<option value="${cm.id}_${cm.clientcompanyname}">${cm.clientcompanyname}</option>
							</s:iterator>
						</SELECT>
						<input type="hidden" name="khmydc.kehuId" id="kehuId1">
						<input type="hidden" name="khmydc.kehuName" id="kehuName1">
						</td>	
					</tr>
					<tr>
						<th>调查月份</th>
						<td>
							<input id="starttime1" class="Wdate" type="text" name="khmydc.dcmonth"
											onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})"
											onblur="date('1')" />
						</td>
					</tr>
					<tr>
						<th>上传月度满意度调查表:</th>

						<td>
							<input type="file" name="attachment">
						</td>	
					</tr>
					<tr>
						<td colspan="2" align="center">
						<input type="hidden" value="${status}" name="status"/>
							<input type="submit" value="添加" class="input">
						</td>
					</tr>
				</table>
				</form>
				<br/>
				<div id="xiazaidiv" align="center">
					<h2>客户满意度调查表下载</h2>
				<ul >
					<s:iterator value="khmydcList" id="khmydc">
						<s:if test="#khmydc.monthdcb!=null && #khmydc.monthdcb!=''">
							<li>
								<a href="javascript:;" onclick="xiazai1('${khmydc.monthdcb}')" >下载${khmydc.kehuName}月度满意度调查表</a>
							</li>
						</s:if>
						
					</s:iterator>
				</ul>
				</div>		
			</div>	
			<div id="jd" >
			<h2>客户满意度调查分析报告</h2>
				<form action="KeHuManYiDiaoChaAction_addkhmydc.action" method="post" onsubmit="return check()" enctype="multipart/form-data">
				<table>
					<tr>
						<th>客户名称:</th>
						<td>
							<SELECT  onchange="changvalue(this,2)">
							<option value="">--请选择--</option>
							<s:iterator value="cmList" id="cm" >
								<option value="${cm.id}_${cm.clientcompanyname}">${cm.clientcompanyname}</option>
							</s:iterator>
						</SELECT>
						<input type="hidden" name="khmydc.kehuId" id="kehuId2">
						<input type="hidden" name="khmydc.kehuName" id="kehuName2">
						</td>	
					</tr>
										<tr>
						<th>
					调查季度从:
						</th>
						<td>
							<input id="starttime1" class="Wdate" type="text" name="khmydc.jdfirsttime"
											onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})"
											onblur="date('1')" />
						</td>
					</tr>
					<tr>
						<th>
					调查季度至:
						</th>
						<td>
							<input id="starttime1" class="Wdate" type="text" name="khmydc.jdendtime"
											onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})"
											onblur="date('1')" />
						</td>
					</tr>
					<tr>
						<th>上传季度满意度分析报告:</th>
						<td>
							<input type="file" name="attachment">
						</td>	
					</tr>
					<tr>
						<td colspan="2" align="center">
						<input type="hidden" value="${status}" name="status"/>
							<input type="submit" value="添加" class="input">
						</td>
					</tr>
				</table>
				</form>
				<br/>
				<div id="xiazaidiv" align="center">
					<h2>客户满意度调查客户满意度调查分析报告下载</h2>
				<ul style="">
					<s:iterator value="khmydcList" id="khmydc">
							<s:if test="#khmydc.quarterbg!=null && #khmydc.quarterbg!=''">
								<li>
									 <a href="javascript:;" onclick="xiazai1('${khmydc.quarterbg}')">下载${khmydc.kehuName}季度满意度分析报告</a>
								 </li>
							</s:if>
						
					</s:iterator>
				</ul>
				</div>
			</div>	
			<div id="year">
			<h2>客户满意度调查分析报告</h2>
				<form action="KeHuManYiDiaoChaAction_addkhmydc.action" method="post" onsubmit="return check()" enctype="multipart/form-data">
				<table>
					<tr>
						<th>客户名称:</th>
						<td>
							<SELECT  onchange="changvalue(this,3)">
							<option value="">--请选择--</option>
							<s:iterator value="cmList" id="cm" >
								<option value="${cm.id}_${cm.clientcompanyname}">${cm.clientcompanyname}</option>
							</s:iterator>
						</SELECT>
						<input type="hidden" name="khmydc.kehuId" id="kehuId3">
						<input type="hidden" name="khmydc.kehuName" id="kehuName3">
						</td>	
					</tr>
					<tr>
						<th>
							调查年份
						</th>

						<td>
							<input id="dcyear" class="wdate" type="text" name="khmydc.dcyear"
											onClick="WdatePicker({dateFmt:'yyyy',skin:'whyGreen'})"
											onblur="date('1')" />
						</td>
					</tr>
					<tr>
						<th>上传年度满意度分析报告:</th>
						<td>
							<input type="file" name="attachment">
						</td>	
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="hidden" value="${status}" name="status"/>
							<input type="submit" value="添加" class="input">
						</td>
					</tr>
				</table>
				</form>
				<br/>
				<div id="xiazaidiv" align="center">
					<h2>客户满意度调查分析报告下载</h2>
				<ul style="">
					<s:iterator value="khmydcList" id="khmydc">
							<s:if test="#khmydc.yearbg !=null && #khmydc.yearbg!=''">
								<li>
									<a href="javascript:;" onclick="xiazai1('${khmydc.yearbg}')">下载${khmydc.kehuName}年度满意度分析报告</a>
								</li>
							</s:if>
						
					</s:iterator>
				</ul>
				</div>
			</div>	
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
var resume = "${user.password.resume}";
$(function(){
	var arraystr =  resume.split("#");
	if(arraystr!=null && arraystr.length>0){
		for(var i=0;i<arraystr.length;i++){
			if(arraystr[i]!=''){
			$("#xiazaidiv").append('<a href=javascript:; onclick=xiazai1("'+arraystr[i]+'")>下载学历证书'+(i+1)+'</a>' +
							'&nbsp;&nbsp;&nbsp;&nbsp;<a href=javascript:; onclick=del("'+arraystr[i]+'")>删除</a><br/>');
			}
		}
	}
})
function xiazai1(obj){
	var fileName1 = encodeURI(encodeURI(obj));
	
	location.href="<%=request.getContextPath()%>/DownAction.action?fileName="+fileName1;
}

function del(fileName1){
	var fileName1 = encodeURI(encodeURI(fileName1));
	window.location.href = "UsersAction!updateResume.action?id=${user.id}&pageStatus=1&pictureFileName="+fileName1;
}

function changvalue(obj,num){
	if(obj!=null && obj.value !=""){
		var value  = obj.value;
		var array = value.split("_");
		if(array.length == 2){
			$("#kehuId"+num).val(array[0]);
			$("#kehuName"+num).val(array[1]);
		}
	}
}
var status = '${status}';
$(function(){
	if(status == 'month'){
		$("#month").show();
		$("#jd").hide();
		$("#year").hide();
	}else if(status == 'jd'){
		$("#month").hide();
		$("#jd").show();
		$("#year").hide();
	}else if(status == 'year'){
		$("#month").hide();
		$("#jd").hide()
		$("#year").show();
	}
})

</SCRIPT>
	</body>
</html>
