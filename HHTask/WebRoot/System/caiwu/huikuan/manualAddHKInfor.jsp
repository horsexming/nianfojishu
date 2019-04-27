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
	<style type="text/css">
		
		body{text-align:center;}		
		
		</style>
		<script type="text/javascript">
		var inforDivHTML="";
        var lineCount = 0;
        function saveHKInfor(obj,few){	    	
	    	var inforDiv=document.getElementById("inforDiv_"+few);    	
	    	if(obj.value=="添加明细"){	    		
	    		inforDiv.style.display="block";
	    		obj.value="继续添加";
	    	}
	    	inforDivHTML="<div id='file"
	    	           + lineCount
	    	           +"'><span style='width:150px;' ><input type='text' name='hkset["
	    	           +lineCount
    	           +"].hkSellCumpanyName' /></span>"
    	           +"<span style='width:150px;' ><input type='text' name='hkset["
    	           +lineCount
    	           +"].hkSellMarkId' /></span>"
    	           +"<span style='width:150px;' ><input type='text' name='hkset["
    	           +lineCount
    	           +"].hkSellCount' /></span>"
    	           +"<span style='width:100px;' ><input type='text' name='hkset["
    	           +lineCount
    	           +"].hkSellSendId' /></span>"
    	           +"<span style='width:100px;' ><input type='text' name='hkset["
    	           +lineCount
    	           +"].hkSellOrderId' /></span>"
    	           +"<span style='width:150px;' ><input type='text' name='hkset["
    	           +lineCount
    	           +"].hkSellMore' /></span>"
    	           +"<a href='javascript:delInfor("
    	           +lineCount+","+few+")'>删除</a></div>";
    	           inforDiv.insertAdjacentHTML("beforeEnd", inforDivHTML);
    	    lineCount++;    	   
	         }
	    function delInfor(obj,few){	    	
	    	document.getElementById("file" + obj).parentNode.removeChild(document
			.getElementById("file" + obj));			
			lineCount--;			
			if (lineCount <= 0) {
				lineCount = 0;
				document.getElementById("inforButton_" + few).value = "添加明细";
				document.getElementById("inforDiv_" + few).style.display = "none";
			}
	    }
	    
		
		</script>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
			</div>
			
			<div align="center">
				<h3>手动添加开票申请信息</h3>
				
				<form action="huikuanAction!saveHK.action?tag=manual" method="post"
					target="main" enctype="multipart/form-data">
					
					<div id="headI" >
					<table width="900px">	
					<tr><td colspan="6">&nbsp;</td></tr>	
					<tr>
					<td>
					<span style="width:150px;">客户</span>
					</td>
					<td>
					<span style="width:150px;">零件号</span>
					</td>
					<td>
					<span style="width:100px;">开票数量</span>
					</td>
					<td>
					<span style="width:100px;">送货单号</span>
					</td>
					<td>
					<span style="width:150px;">订单号</span>
					</td>
					<td>
					<span style="width:150px;">备注</span>
					</td>
					</tr>
					<tr><td></td><td colspan="5">
						</td></tr>		
					</table>
					</div>
					<div style="width:90%;text-align:left; "><input type="button" id="inforButton_1"
											onclick="saveHKInfor(this,1)" value="添加明细">
					</div>
					<div id="inforDiv_1" style="display: none;">
					</div>	
							
						<!-- 添加明细 -->
						
				<table width="900px">				
						
						<!-- 添加基本信息 -->
						<tr><td colspan="6">&nbsp;</td></tr>
						<tr>
						<td>客户公司名称：</td><td><input type="text" name="taHk.hkClientComp" size="15" /></td>
						<td>客户负责人：</td><td><input type="text" name="taHk.hkClientName" size="15" /></td>
						<td>申请开票时间：</td><td><input class="Wdate" type="text" name="taHk.hkAppPayDate" size="15" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/></td>
						</tr>		
						<tr><td>上传附件</td><td colspan="5">
						<input type="button" id="fileButton_1"
											onclick="uploadFile(this,1)" value="上传附件">

						<div id="fileDiv_1" style="display: none;">

						</div>
						
						</td></tr>		
						<tr>						
							<td colspan="6" align="center">
								<input type="submit" value="提交"
									style="width: 60px; height: 40px;" align="top">
							</td>
						</tr>
						</table>
						</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>	
	</body>
    <script type="text/javascript">  
	var fileDivHTML = "";
	var count = 0;	
	function uploadFile(obj, few) {
		var fileDiv = document.getElementById("fileDiv_" + few);
		if (obj.value == "上传附件") {
			fileDiv.style.display = "block";
			obj.value = "添加文件";
		}
		fileDivHTML = "<div id='infooo"
				+ count
				+ "'><input type='file' name='attachment'><a href='javascript:delFile("
				+ count + "," + few + ")'>删除</a></div>";
		fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);
		count++;
	}
	
	function delFile(obj, few) {
		document.getElementById("infooo" + obj).parentNode.removeChild(document.getElementById("infooo" + obj));
		count--;
		if (count <= 0) {
			count = 0;
			document.getElementById("fileButton_" + few).value = "上传附件";
			document.getElementById("fileDiv_" + few).style.display = "none";
		}
	}
	
	
		</script>

</html>
