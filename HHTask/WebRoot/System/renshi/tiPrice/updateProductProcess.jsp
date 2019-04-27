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
		<script type="text/javascript" src="javascript/jquery-easyui-1.3.1/jquery-1.8.0.min.js"></script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="productPriceAction!updateProductProcess.action" method="post">
					<input type="text" id="sparePartsId" name="productProcess.id" value="${productProcess.id }">
					<table>
						<tr>
						<td>工序号：<input type="text" name="productProcess.processNo" value="${productProcess.processNo }" /></td>
						<td>工序名称：<input type="text" name="productProcess.processName" value="${productProcess.processName }" />(s)</td>
						<td>人工节拍：<input type="text" name="productProcess.realJIEPAI" value="${productProcess.realJIEPAI }" />(s)</td>
						</tr>
						<tr>
						<td>操作人工号：<input type="text" onblur="send(this)" id="code" name="productProcess.operatorCode" value="${productProcess.operatorCode }" /></td>
						<td>操作者：<input type="text" id="username" name="productProcess.operatorName" value="${productProcess.operatorName }" readonly="true" /></td>
						<td>单件节拍：<input type="text" name="productProcess.danjianJIEPAI" value="0" value="${productProcess.danjianJIEPAI }" />(s)</td>
						</tr>
						<tr>
						<td>工位号：<select name="productProcess.gongwei" 
						            id="gongwei"
										style="width: 155px;"
										onmouseover="findgongwei()">
										<option value="${productProcess.gongwei }">${productProcess.gongwei }</option>
						</select></td>
						<td>设备编号：<select id="shebeiCode" name="productProcess.shebeiNo" 
								style="width: 155px;"								
								onmouseover="getshebeiCode(this)"
								onmouseout="getGongweiAndOth()">					
																
								<option value="${productProcess.shebeiNo }">${productProcess.shebeiNo }</option>
						</select>
						</td>
						<td>设备名称：<input type="text" name="" id="shebeiName" readOnly="readonly" style="background-color:#cccccc; "/></td>
						</tr>						
						<tr><td colspan="3" style="text-align: center;background-color: gray;">操作过程</td></tr>
						<tr>
						
						<td>人工节拍：<input type="text" name="productProcess.OPcaozuojiepai" value="${productProcess.OPcaozuojiepai }" />(s)</td>
						<td>设备节拍：<input type="text" name="productProcess.OPshebeijiepai" value="${productProcess.OPshebeijiepai }"  /></td>
						<td>交付量：<input type="text" name="productProcess.OPjiaofu" id="Dayjiaofu" value="${productProcess.OPjiaofu }" readOnly="readonly" style="background-color:#cccccc; "/></td>
						
						</tr>
						<tr>						
						<td>负荷指数：<input type="text" name="productProcess.OPfuheRate" value="${productProcess.OPfuheRate }" /></td>
						<td>技能指数：<input type="text" name="productProcess.OPtechnologyRate" value="${productProcess.OPtechnologyRate }" id="OPtechnologyRate" readOnly="readonly" style="background-color:#cccccc; "/></td>
						<td>可替换人数：<input type="text" name="productProcess.OPCouldReplaceRate" value="${productProcess.OPCouldReplaceRate }" id="OPCouldReplaceRate" readOnly="readonly" style="background-color:#cccccc; "/></td>
						
						</tr>
						<tr><td colspan="3" style="text-align: center;background-color: gray;">准备过程</td></tr>
						<tr>
						<td>人工节拍：<input type="text" name="productProcess.GZzhunbeijiepai" value="${productProcess.GZzhunbeijiepai }" />(s)</td>
						<td>准备次数：<input type="text" name="productProcess.GZzhunbeicishu" value="${productProcess.GZzhunbeicishu }" /></td>
						<td>备注：<input type="text" name="productProcess.more" value="${productProcess.more }" /></td>
						</tr>
						<tr>
						<td>负荷指数：<input type="text" name="productProcess.GZfuheRate" id="GZfuheRate" value="${productProcess.GZfuheRate }" readOnly="readonly" style="background-color:#cccccc; "/></td>
						<td>技能指数：<input type="text" name="productProcess.GZtechnologyRate" id="GZtechnologyRate" value="${productProcess.GZtechnologyRate }" readOnly="readonly" style="background-color:#cccccc; "/></td>
						<td>可替换人数：<input type="text" name="productProcess.GZCouldReplaceRate" id="GZCouldReplaceRate" value="${productProcess.GZCouldReplaceRate }"  readOnly="readonly" style="background-color:#cccccc; "/></td>
						
						</tr>						
						
						<tr>
						<td >同步工序：<input type="text" name="productProcess.fuheProcessNo" value="${productProcess.fuheProcessNo }" /></td>
						<td>视频连接地址：<input type="text" name="productProcess.videoFile" value="${productProcess.videoFile }" /></td>
						<td >备注：<input type="text" name="productProcess.processMore" value="${productProcess.processMore }" /></td>
						
						</tr>
						<tr>
						<td colspan="2" align="center">
						<input type="submit" value="更改" />&nbsp;
						<input type="reset" value="取消">
						
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
	
   //查询工位信息
   function findgongwei(){
	  
	  
    createDept('gongwei','productPriceAction!findGongwei.action')
   }
	//根据工位查询设备编号
	function getshebeiCode(obj){
		var gongwei=document.getElementById("gongwei").value;
		var select =document.getElementById("shebeiCode");	
		select.options.length=0;	   
		if(null!=gongwei && ""!=gongwei){
			createDept("shebeiCode","productPriceAction!findShebeiCode.action?tag="+gongwei);
		}
		//getGongweiAndOth(obj);
	}
	//查询工位对象和交付量
	function getGongweiAndOth(){
		//工位和设备编号
		
		var gongwei=document.getElementById("gongwei").value;
		
		var shebeicode=document.getElementById("shebeiCode").value;
	  
		var id=document.getElementById("sparePartsId").value;
		//alert("====="+id+"=="+gongwei+"=="+shebeicode);
		$.ajax({
		   type: "POST",
		   url: "productPriceAction!getUpdateGongweiAndOth.action",
		   data: "gongweihao=" + gongwei+"&shebeiCode="+shebeicode+"&id="+id,
		   success: function(msg){
		     var gong = $.parseJSON(msg);
		     //赋值
		   //  alert(gong.gongwei.gongzhuangJineng+"id="+gong.gongwei.id+"====="+gong.dalyOut);
		     document.getElementById("Dayjiaofu").value=gong.dalyOut;
		     document.getElementById("shebeiName").value=gong.gongwei.shebeiName;
		     document.getElementById("OPtechnologyRate").value=gong.gongwei.caozJineng;
		     document.getElementById("OPCouldReplaceRate").value=gong.gongwei.caoztihuanrenshu;
		     document.getElementById("GZfuheRate").value=gong.gongwei.gongzhuangFuhe;
		     document.getElementById("GZtechnologyRate").value=gong.gongwei.gongzhuangJineng;
		     document.getElementById("GZCouldReplaceRate").value=gong.gongwei.gongzhuangtihuanrenshu;
		     
		   }
		});
	}
	//根据工号查询人员姓名	
	function send(obj) {
	var value = encodeURI(obj.value);//对strValue进行编码 
	sendRequest("productPriceAction!findUserName.action?code=" + value,
			messageResponse);
	
	}
	// 联系人查询
	function messageResponse() {
		if (XMLHttpReq.readyState == 4) { // 判断对象状态
			if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var message = XMLHttpReq.responseText;
			document.getElementById("username").value=message;

		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}
		
		</script>
	</body>
</html>
