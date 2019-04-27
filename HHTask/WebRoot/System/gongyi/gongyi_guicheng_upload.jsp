<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
		<style type="text/css">
.table th,.table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
	height: 24px;
}

#gongyiNav {
	width: 100%;
	background: #666666;
	color: #FFFFFF;
	font-family: 黑体;
	text-align: center;
	font-size: 13px;
	font-weight: bold;
}

#gongyiNav a,span {
	border-left: #CCCCCC 1px solid;
	display: inline-block;
	height: 40px;
	line-height: 40px;
	padding: 0px 10px;
	margin-left: -8px;
}

#gongyiNav a:link,a:visited,a:active {
	text-decoration: none;
	color: #FFFFFF;
}

#gongyiNav a:hover {
	background: #CCCCCC;
}

form span {
	border: 0px;
}
</style>
<script type="text/javascript">
function uploadProcessDataAffixResend(data){
	//var gongyiGuichengAffix=JSON.parse(data);
	//var id=gongyiGuichengAffix.id;
	//var url=gongyiGuichengAffix.url;
	//var affixType=gongyiGuichengAffix.affixType;
	//var processDataId=$("input[name='gongyiGuichengAffix.processDataId']").val();
	//var _src='gongyiGuichengAction!getGongyiGuiChengUploadPage.action?processData.id='+processDataId;
	//window.location=_src;
}
</script>
	</head>
	<body style="text-align: center;">
		<div align="center" style="width: 900px;">
			<table class="table">
				<tr >
					
					<td style="width: 20%;font-size: 20px;font-weight: bold">工艺规程文件 </td>
					<td style="width: 60%;">
						<form action="gongyiGuichengAction!uploadProcessDataAffix2.action?affixType=processImg" enctype="multipart/form-data" method="post">  
					      <div style="position:relative;display:none;">  
					       	<input type="hidden" name="gongyiGuichengAffix.gongyiGuichengId" value="${gongyiGuichengAffix.gongyiGuichengId}" />  
					        <input type="hidden" name="gongyiGuichengAffix.processDataId" value="${gongyiGuichengAffix.processDataId}" /> 
					        <input type="hidden" name="gongyiGuichengAffix.weizhi" value="gxsmlq" />   
					        <!-- 
					        <iframe name="hidden_iframe" id="hidden_iframe"></iframe>   -->
					      </div>  
				    	  <input type="file" name="processDataImgFile" /> 
				    	  <select id="affixType" name="gongyiGuichengAffix.affixType">
				    	  	<!-- 
				    	  	<option value="fujian">附件</option>
				    	  	<option value="shipin">视频</option>
				    	  	<option value="tupian">图片</option>
				    	  	 -->
				    	  </select> 
						  <input type="button" id="upload" value=" 上 传 " onclick="if(window.confirm('确认要上传吗?')){this.form.submit();}"  />
					    </form> 
					</td>
					<td style="width: 20%;">&nbsp;</td>
				</tr>
				<tr id="fujian">
					<td colspan="3" style="font-size: 20px;font-weight: bold">附件</td>
				</tr>
				<tr id="shipin">
					<td colspan="3" style="font-size: 20px;font-weight: bold">视频</td>
				</tr>
				<tr id="tupian">
					<td colspan="3" style="font-size: 20px;font-weight: bold">工艺规范</td>
				</tr>
			</table>
			<table style="width: 100%;text-align: center;border: 0px;">
					<tr>
						<input type="button" id="finishButton" value="完成工序编辑" class="input" style="width: 120px;"/></td>
					</tr>
				</table>
		</div>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<script type="text/javascript">
$(function() {
	var gongyiGuichengId=$("input[name='gongyiGuichengAffix.gongyiGuichengId']").val();
	var processDataId=$("input[name='gongyiGuichengAffix.processDataId']").val();
	$.ajax( {
		type : "post",
		dataType : "json",
		url : "gongyiGuichengAction!getGongyiGuichengAffixListByGuiyiGuichengIdAndProcessDataId.action",
		data : {
			'gongyiGuichengAffix.gongyiGuichengId': gongyiGuichengId,
			'gongyiGuichengAffix.processDataId': processDataId,
			'gongyiGuichengAffix.affixType': 'fujian',
			'gongyiGuichengAffix.weizhi': 'gxsmlq'
		},
		async : false,
		success : function(data) {
			var success = data.success;
			if (success) {
				var gongyiGuichengAffixList = data.data;
				$(gongyiGuichengAffixList).each(function(i,n){
					var processDataId=this.processDataId==null?'': this.processDataId;
					var gongyiGuichengId=this.gongyiGuichengId==null?'':this.gongyiGuichengId;
					$("<tr><td></td><td>"+this.fileName+"</td><td><a href='gongyiGuichengAction!downloadProcessDataAffix2.action?gongyiGuichengAffix.id="+this.id+"'>下载</a>&nbsp;&nbsp;<a href='gongyiGuichengAction!deleteGongyiGuichengAffix.action?gongyiGuichengAffix.id="+this.id+"&gongyiGuichengAffix.gongyiGuichengId="+gongyiGuichengId+"&gongyiGuichengAffix.processDataId="+processDataId+"' onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td></tr>").insertAfter("#fujian");
				});
			}
		}
	});
	$.ajax( {
		type : "post",
		dataType : "json",
		url : "gongyiGuichengAction!getGongyiGuichengAffixListByGuiyiGuichengIdAndProcessDataId.action",
		data : {
			'gongyiGuichengAffix.gongyiGuichengId': gongyiGuichengId,
			'gongyiGuichengAffix.processDataId': processDataId,
			'gongyiGuichengAffix.affixType': 'shipin',
			'gongyiGuichengAffix.weizhi': 'gxsmlq'
		},
		async : false,
		success : function(data) {
			var success = data.success;
			if (success) {
				var gongyiGuichengAffixList = data.data;
				$(gongyiGuichengAffixList).each(function(i,n){
					var processDataId=this.processDataId==null?'': this.processDataId;
					var gongyiGuichengId=this.gongyiGuichengId==null?'':this.gongyiGuichengId;
					$("<tr><td></td><td>"+this.fileName+"</td><td><a href='gongyiGuichengAction!downloadProcessDataAffix2.action?gongyiGuichengAffix.id="+this.id+"'>下载</a>&nbsp;&nbsp;<a href='gongyiGuichengAction!deleteGongyiGuichengAffix.action?gongyiGuichengAffix.id="+this.id+"&gongyiGuichengAffix.gongyiGuichengId="+gongyiGuichengId+"&gongyiGuichengAffix.processDataId="+processDataId+"' onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td></tr>").insertAfter("#shipin");
				});
			}
		}
	});
	$.ajax( {
		type : "post",
		dataType : "json",
		url : "gongyiGuichengAction!getGongyiGuichengAffixListByGuiyiGuichengIdAndProcessDataId.action",
		data : {
			'gongyiGuichengAffix.gongyiGuichengId': gongyiGuichengId,
			'gongyiGuichengAffix.processDataId': processDataId,
			'gongyiGuichengAffix.affixType': 'tupian',
			'gongyiGuichengAffix.weizhi': 'gxsmlq'
		},
		async : false,
		success : function(data) {
			var success = data.success;
			if (success) {
				var gongyiGuichengAffixList = data.data;
				$(gongyiGuichengAffixList).each(function(i,n){
					var processDataId=this.processDataId==null?'': this.processDataId;
					var gongyiGuichengId=this.gongyiGuichengId==null?'':this.gongyiGuichengId;
					$("<tr><td><img src='<%=request.getContextPath()%>"+this.url+"' style='width:80px;height:80px;'/></td><td>"+this.fileName+"</td><td><a href='gongyiGuichengAction!downloadProcessDataAffix2.action?gongyiGuichengAffix.id="+this.id+"'>下载</a>&nbsp;&nbsp;<a href='gongyiGuichengAction!deleteGongyiGuichengAffix.action?gongyiGuichengAffix.id="+this.id+"&gongyiGuichengAffix.gongyiGuichengId="+gongyiGuichengId+"&gongyiGuichengAffix.processDataId="+processDataId+"' onclick='return window.confirm(\"确认要删除该条记录?\");'>删除</a></td></tr>").insertAfter("#tupian");
				});
			}
		}
	});
	
	//是否显示视频附件
	if(processDataId){
		$("#fujian").css("display","none");
		$('<option value="tupian">工艺规范</option><option value="shipin">视频</option>').appendTo("#affixType");
	}else{
		$("#shipin").css("display","none");
		$('<option value="tupian">工艺规范</option><option value="fujian">附件</option>').appendTo("#affixType");
		$("#finishButton").css("display","none");
	}
	
	//*************************************************完成工序编辑****************************************************
	$('#finishButton').bind('click',function(){
		$.ajax({
			type: "post",
			dataType: "json",
	        url: "gongyiGuichengAction!finishProcessData.action",
	        data:{
				'processData.id': processDataId
			},
			async: false,
			success: function(data){
				var success=data.success;
				if(success){
					alert("完成工序编辑");
					//window.location.reload();
				}
			}
		});
	});
});
</script>
