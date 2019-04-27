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
 list-style:inside; 

}
ul li{
			margin-top:5px;
			 border-bottom: 1px solid #666 dotted;
			 list-style-type:cjk-ideographic;
	 }

</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>客户满意度调查</h2>
				<form action="KeHuManYiDiaoChaAction_addkhmydc.action" method="post" onsubmit="return check()" enctype="multipart/form-data">
				<table>
					<tr>
						<th>客户名称:</th>
						<td>
							<SELECT class="cxselect" onchange="changvalue(this)">
							<option value="">--请选择--</option>
							<s:iterator value="cmList" id="cm" >
								<option value="${cm.id}_${cm.clientcompanyname}">${cm.clientcompanyname}</option>
							</s:iterator>
						</SELECT>
						<input type="hidden" name="khmydc.kehuId" id="kehuId">
						<input type="hidden" name="khmydc.kehuName" id="kehuName">
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
						<th>
							调查年份
						</th>

						<td>
							<input id="starttime1" class="Wdate" type="text" name="khmydc.dcyear"
											onClick="WdatePicker({dateFmt:'yyyy',skin:'whyGreen'})"
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
						<th>上传季度满意度分析报告:</th>
						<td>
							<input type="file" name="attachment">
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
							<input type="submit" value="添加" class="input">
						</td>
					</tr>
				</table>
				<br/>
				<div id="xiazaidiv" align="center">
					<h2>客户满意度调查文件查看</h2>
				<ul>
					<s:iterator value="khmydcList" id="khmydc">
						<li>
							<a href="javascript:;" onclick="xiazai1('${khmydc.quarterbg}')" >下载${khmydc.kehuName}月度满意度调查表</a>
						 &nbsp;&nbsp;&nbsp;&nbsp;
						 <a href="javascript:;" onclick="xiazai1('${khmydc.quarterbg}')">下载${khmydc.kehuName}季度满意度分析报告</a>
						 &nbsp;&nbsp;&nbsp;&nbsp;
						 <a href="javascript:;" onclick="xiazai1('${khmydc.yearbg}')">下载${khmydc.kehuName}年度满意度分析报告</a>
						</li>
					</s:iterator>
				</ul>
				</div>		
					
							
					
				
			
				
				</form>
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

function changvalue(obj){
	if(obj!=null && obj.value !=""){
		var value  = obj.value;
		var array = value.split("_");
		if(array.length == 2){
			$("#kehuId").val(array[0]);
			$("#kehuName").val(array[1]);
		}
	}
}
</SCRIPT>
	</body>
</html>
