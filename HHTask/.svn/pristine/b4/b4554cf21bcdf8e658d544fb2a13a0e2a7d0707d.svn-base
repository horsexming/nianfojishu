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
	<body onload="loadValue()">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="LogoStickerAction!saveLogoSticker.action" onsubmit="return checkForm()" method="post">
					<h2>${companyInfo.name} 标识贴管理</h2>
					
					<table>
						<tr align="center">
						<td colspan="4" style="font-size: 20px;">标识类别：<select id="markSelect" style="font-size: 20px;width:150px;" onblur="selectStickStyle(this)" name="sticker.stickStyle">
						<option value="${sticker.stickStyle}">${sticker.stickStyle}</option>
						<option value="首检样品">首检样品</option>
						<option value="报废品">报废品</option>
						<option value="待处理品">待处理品</option>
						</select></td>						
						</tr>
						<tr>
						<td>零件号：</td><td><input id="markId" type="text" name="sticker.markId" value="${sticker.markId}"/></td>
						<td align="center">工<span style="width:7px;"></span>序<span style="width:7px;"></span>号：</td>
						<td><input id="processNO" type="text" name="sticker.processNO" value="${sticker.processNO}"/></td>
						</tr>
						<tr>
						<td>零件名：</td><td><input id="partsName" type="text" name="sticker.partsName" value="${sticker.partsName}"/></td>
						<td align="center">批<span style="width:7px;"></span>次<span style="width:7px;"></span>号：</td>
						<td><input id="lotId" type="text" name="sticker.lotId" value=""/></td>
						</tr>
						<tr>
						<td>员工号：</td><td><input onMouseOut="getUserBy('co',this)" id="code" type="text" name="sticker.code" value="${sticker.code}"/></td>
						<td align="center">操<span style="width:7px;"></span>作<span style="width:7px;"></span>者：</td>
						<td><input id="operator" onMouseOut="getUserBy('na',this)" type="text" name="sticker.operator" value="${sticker.operator}"/></td>
						</tr>
						<tr>
						<td>签<span style="width:14px;"></span>名：</td><td><input type="text" name="sticker.examinerName" /></td>
						<td align="center">
						<span style="display:none;" id="advise">处理意见：</span>
						<span style="display:none;" id="count">报废数量：</span>
						</td><td>
						<span style="display:none;" id="advise2"><input type="text" name="sticker.treatAdvice" value="${sticker.treatAdvice}"/></span>
						<span style="display:none;" id="advise3"><input type="text" name="sticker.count" value="${sticker.count}"/></span>
						</td>
						</tr>
						
						<tr><td colspan="4">
						<div id="fp" style="text-align:center;">
						
						<table>
							<tr><td colspan="2" align="left">要求检查内容：</td>
							<td colspan="2"><textarea id="demandExamContent" name="sticker.demandExamContent"  cols="35" rows="6">${sticker.demandExamContent}</textarea></td></tr>
							<tr><td colspan="2" align="left">实际检查记录：</td>
							<td colspan="2"><textarea id="realExamContent" name="sticker.realExamContent"  cols="35" rows="6">${sticker.realExamContent}</textarea></td></tr>
						</table>	
						</div>
						<div id="bf" style="display:none;text-align:center;">
						<table>
							<tr><td colspan="2" align="left">报废原因：</td>
							<td colspan="2"><textarea id="demandExamContent" name="sticker.more" cols="35" rows="6">${sticker.more}</textarea></td></tr>
						</table>	
						</div>
						</td></tr>
						
						<tr><td colspan="4" style="text-align: center">
						<input style="width:80px;font-size:18px;" type="submit" value="添加"><span style="width:7px;"></span>
						<input style="width:75px;font-size:15px;"  type="reset" value="取消">
						</td></tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			function loadValue(){
				var stickStyle="${sticker.stickStyle}";		
				if("待处理品"==stickStyle){
					document.getElementById("advise").style.display="block";
					document.getElementById("advise2").style.display="block";
					document.getElementById("fp").style.display="none";
					document.getElementById("demandExamContent").value="";
					document.getElementById("realExamContent").value="";
					document.getElementById("count").style.display="none";
					document.getElementById("advise3").style.display="none";
					document.getElementById("bf").style.display="none";
				}else if("报废品"==stickStyle){
					document.getElementById("advise").style.display="none";
					document.getElementById("advise2").style.display="none";
					document.getElementById("fp").style.display="none";
					document.getElementById("realExamContent").value="";
					document.getElementById("count").style.display="block";
					document.getElementById("advise3").style.display="block";
					document.getElementById("bf").style.display="block";
				}else if("首检样品"==stickStyle){
					document.getElementById("advise").style.display="none";
					document.getElementById("advise2").style.display="none";
					document.getElementById("fp").style.display="block";
					document.getElementById("count").style.display="none";
					document.getElementById("advise3").style.display="none";
					document.getElementById("bf").style.display="none";
				}
				//alert(stickStyle);
				//setTimeout(selectStyle,100);			
			}
			function selectStickStyle(obj){				
				var stickStyle=obj.value;
				if("待处理品"==stickStyle){
					document.getElementById("advise").style.display="block";
					document.getElementById("advise2").style.display="block";
					document.getElementById("fp").style.display="none";
					document.getElementById("demandExamContent").value="";
					document.getElementById("realExamContent").value="";
					document.getElementById("count").style.display="none";
					document.getElementById("advise3").style.display="none";
					document.getElementById("bf").style.display="none";
				}else if("报废品"==stickStyle){
					document.getElementById("advise").style.display="none";
					document.getElementById("advise2").style.display="none";
					document.getElementById("fp").style.display="none";
					document.getElementById("realExamContent").value="";
					document.getElementById("count").style.display="block";
					document.getElementById("advise3").style.display="block";
					document.getElementById("bf").style.display="block";
				}else if("首检样品"==stickStyle){
					document.getElementById("advise").style.display="none";
					document.getElementById("advise2").style.display="none";
					document.getElementById("fp").style.display="block";
					document.getElementById("count").style.display="none";
					document.getElementById("advise3").style.display="none";
					document.getElementById("bf").style.display="none";
				}
			}
			//提交验证
			function checkForm(){
				var markId=document.getElementById("markId");
				var processNO=document.getElementById("processNO");
				var partsName=document.getElementById("partsName");
				var lotId=document.getElementById("lotId");
				var code=document.getElementById("code");
				var operator=document.getElementById("operator");
				if(markId.value==""){
					alert("件号不能为空!");
					markId.focus();
					return false;
				}else if(processNO.value==""){
					alert("工序不能为空!");
					processNO.focus();
					return false;
				}else if(partsName.value==""){
					alert("零件号不能为空!");
					partsName.focus();
					return false;
				}else if(lotId.value==""){
					alert("批次不能为空!");
					lotId.focus();
					return false;
				}else if(code.value==""){
					alert("操作人工号不能为空!");
					code.focus();
					return false;
				}else if(operator.value==""){
					alert("操作人姓名不能为空!");
					operator.focus();
					return false;
				}
				
			}
			function getUserBy(obj,o){	
				if(obj=="co"){//根据工号查询
				var code =o.value;
				$.ajax({
					type : "POST",
					url : "LogoStickerAction!findUserInfor.action",
					data : {
						tag : obj,
						content : code
					},
					dataType : "json",
					success : function(msg) {
						if(msg.content==""){
							alert(msg.message);
						}else{
							document.getElementById("operator").value = msg.content;
						}
						
					}
				});				
				}else if(obj=="na"){
					var name =o.value;
				$.ajax({
					type : "POST",
					url : "LogoStickerAction!findUserInfor.action",
					data : {
						tag : obj,
						content : name
					},
					dataType : "json",
					success : function(msg) {
						if(msg.content==""){
							alert(msg.message);
						}else{
							document.getElementById("code").value = msg.content;
						}
						
					}
				});		
				}
			}
		</script>
	</body>
</html>
