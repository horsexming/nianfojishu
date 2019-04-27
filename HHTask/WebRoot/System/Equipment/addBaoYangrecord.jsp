<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			
			<div align="center">
				<font color="red"> ${successMessage}</font>
				<form action="ProdEquipmentAction!addBaoYangRecord.action"
					method="post" >
					<input type="hidden" name="machine.id" value="${machine.id}" />
<%--					<input type="hidden" name="machine.status"--%>
<%--						value="${machine.status}" />--%>
<%----%>
<%--					<input type="hidden" name="machine.addTime"--%>
<%--						value="${machine.addTime}" />--%>
<%--					<input type="hidden" name="machine.barcode"--%>
<%--						value="${machine.barcode}" />--%>

					<table border="1" width="100%" class="table">
						<tr>

							<td colspan="20" align="center"
								style="font-family: 微软雅黑; font-weight: bold;">
							添加设备保养
							</td>
						</tr>
						<tr>
							<th align="right">
								是否手工
							</th>
							<td>
								${machine.isManual}
							</td>
							<th align="right">
								工区
							</th>
							<td>${machine.workArea}
							</td>

							<th align="right">
								工位
							</th>
							<td>
								${machine.workPosition}
							</td>
						</tr>
						<tr>
							<th align="right">
								设备编号
							</th>
							<td>
								${machine.no}
							</td>
							<th align="right">
								设备类型
							</th>
							<td>
								${machine.type}
							</td>

							<th align="right">
								设备名称
							</th>

							<td>
								${machine.name}
							</td>

						</tr>
						<tr>
							<th align="right">
								设备型号
							</th>
							<td>
								${machine.unitType}
							</td>
							<th align="right">
								设备品牌
							</th>
							<td>
								${machine.brand}
							</td>
							
							<th align="right">
								使用状态
							</th>
							<td colspan="10">
								${machine.useStatus}
							</td>
						</tr>
						
						<tr>
							<th align="right">
								所在班组
							</th>
							<td>
								${machine.classGroup}

							</td>
						<th align="right">折旧年限</th>
						<td>
								${machine.depreciationYear}
									

							</td>
							<th align="right">购买时间</th>
						<td>
							${machine.buytime}
							</td>
						</tr>
						<tr>
						<th align="right">购买金额</th>
							<td>
								${machine.buyamount}
							</td>	
							
								<th align="right">
									关键设备
								</th>
								<td>
									${machine.isKey}
								</td>
								<th align="right">
									是否点检
								</th>
								<td>
								   ${machine.isdj}
								</td>
							</tr>
							<tr>
								<th align="right">
									稼动率
								</th>
								<td>
									${machine.jiadonglv}
								</td>
								<th align="right">
									备注
								</th>
								<td>
									${machine.more}
								</td>
								<th align="right">
									是否为流水线工位:
								</th>
								<td align="left">
									${machine.islsxgw}
								</td>
							</tr>
							<tr>
							<th align="right">
									设备分类:
								</th>
								<td>
									
										    ${machine.baoyangType}
										
								</td>
								<th align="right">
									设备图片
								</th>
								<td>
								<s:if test="machine.pic!=null">
									<p style="color: red">已有设备图片！</p>
								</s:if>
<%--									<input type="file" id="pic" name="machine.picF" onchange="validateFile();"/>--%>
								</td>
								<th align="right">
								</th>
								<td align="left">
									
								</td>
							</tr>
					</table>
					<br/>
					<h2 style="font-size: 20px;">保养标准</h2>
					<table class="table" id="bybz_table">
						<tr>
							<th>序号</th>
							<th>保养条件</th>
							<th>保养方法</th>
							<th>保养周期（天）</th>
							<th>
								保养结果
							</th>
						</tr>
						<s:iterator value="list" id="bybz" status="pagestatus">
							<tr align="center">
								<td>
								${pagestatus.index+1}
									<input type="hidden" value="${bybz.id}" name="byRecordList[${pagestatus.index}].BaoYangBiaoZhunId">
								</td>
								<td>
									${bybz.baoyangCondition}
									<input type="hidden" value="${bybz.baoyangCondition}" name="byRecordList[${pagestatus.index}].baoyangCondition">
								</td>
								<td>
									${bybz.baoyangMeans}
									<input type="hidden" value="${bybz.baoyangMeans}" name="byRecordList[${pagestatus.index}].baoyangMeans">
								</td>
								<td>
									${bybz.baoyangCycle}
									<input type="hidden" value="${bybz.baoyangCycle}" name="byRecordList[${pagestatus.index}].baoyangCycle">
								</td>
								<td>
									<input type="radio" value="Yes" name="byRecordList[${pagestatus.index}].result">已保养
									<input type="radio" value="No" name="byRecordList[${pagestatus.index}].result">未保养
								</td>
							</tr>
						</s:iterator>
					</table>
					<br/>
					<input type="hidden" value="${cpage}" name="cpage">
					<input type="submit"  value="添加"
									style="width: 100px; height: 50px;">
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			function update(){
			var depreciationYear =document.getElementById("depreciationYear").value;
				var reg = /^[0-9]+$/; 
				//校验折旧年限
				if(depreciationYear!=""&&!reg.test(depreciationYear)){ 
					alert("折旧年限只能为整数！");
					return false; 
					}else{
						return true;
				} 
		//校验时间		
//			var   reg1   =   /^(\d{4})-(\d{2})-(\d{2})$/;  
//			var buytime =document.getElementById("buytime").value;
//			var   arr   =   reg1.exec(buytime);  
//			if   (buytime=="")   return   true;  
//			 if   (!reg1.test(buytime)&&RegExp.$2<=12&&RegExp.$3<=31){  
//      			  alert("请输入的日期格式为yyyy-mm-dd或正确的日期!");  
//      			  return   false;  
//   		     }  
//		 		 return   true;  	 
			}
		
function setMachine(){
	$("#no").val("manual");
	$("#name").val("手工");
}
var index = $("#list_size").val();
function addline(){
	index = parseInt(index);
	var newline = '<tr align="center"><td>'+(index+1)+'</td>' +
					'<td><input type="text" name="machine.bybzList['+index+'].baoyangCondition" ></td>' +
					'<td><input type="text" name="machine.bybzList['+index+'].baoyangMeans" ></td>' +
					'<td><input type="text"  name="machine.bybzList['+index+'].baoyangCycle" onkeyup="numyanzheng(this,&apos;zhengshu&apos;)" onblur="numyanzheng(this,&apos;zhengshu&apos;)" ></td><td></td></tr>';
	$("#bybz_table").append(newline);
	index++;
}
function delline(){
	var num = $("#list_size").val();
	var n = $('#bybz_table tr').length;
	if(index<1){
		alert("已经没有了保养项了，不能再删了。");
		return;
	}else  if(index<=num){
		if(confirm('已经删除到之前添加的保养项了，是否继续删除？')){
			$($('#bybz_table tr')[n]).remove();
		}else{
			return;
		}
	}
	$($('#bybz_table tr')[n-1]).remove();
	index--;
	
}
function todelete(id){
	if(confirm('是否删除该保养标准？')){
		 $.ajax( {
		type : "post",
		url : "ProdEquipmentAction!delbybz.action",
		data:{
			id:id
		},
		dataType : "json",
		success : function(data) {
			window.location.reload();
		}
	});
	}
}
/**
 * 限制文件格式
 */
function validateFile(){ 
	var fileObject=$("#pic"); 
	var filepath=fileObject.val(); 
	var fileArr=filepath.split("//"); 
	var fileTArr=fileArr[fileArr.length-1].toLowerCase().split("."); 
	var filetype=fileTArr[fileTArr.length-1]; 
	if(filetype!="jpeg"&&filetype!="jpg"&&filetype!="bmp"&&filetype!="png"&&filetype!="gif"){ 
		var file = document.getElementById('pic');
		file.outerHTML = file.outerHTML; //重新初始化了file的html
		fileObject.focus(); 
		alert("上传文件必须为图片(.jpeg/.jpg/.bmp/.png/.gif)文件！"); 
	}
} 
		</script>
		
	</body>
</html>
