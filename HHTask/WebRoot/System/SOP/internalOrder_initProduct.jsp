\<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong">

				<div align="center">
					<form action="internalOrder_updateProduct.action" method="post"
						onsubmit="return validate()">
						<table>
							<tr>
								<th align="center" colspan="2">
									修改计划明细
								</th>
							</tr>
							<tr>
								<td>
									件号：
								</td>
								<td>
									<input type="text" id="pieceNumber" name="iod.pieceNumber"
										style="width: 200px;" value="${iod.pieceNumber }"
										readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td>
									产品名字：
								</td>
								<td>
									<input type="text" id="name" name="iod.name"
										value="${iod.name }" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td>
									分配数量：
								</td>
								<td>
									<input type="text" id="num" name="iod.num" value="${iod.num}"
										onblur="send()" />
								</td>
							</tr>
							<tr>
								<td>
									入库数量：
								</td>
								<td>
									<input type="text" id="quantityCompletion"
										name="iod.quantityCompletion"
										value="${iod.quantityCompletion}" />
								</td>
							</tr>
							<tr>
								<td align="center">
									备注：
								</td>
								<td>
									<textarea name="iod.remark" cols="20" rows="1">${iod.remark}</textarea>
								</td>
							</tr>
							<tr>
								<input type="hidden" name="id" value="${customeId}" id="ioId" />
								<input type="hidden" name="iod.id" value="${iod.id }" id="iod" />
								<td align="center" colspan="2">
									<input type="submit" value="提交"
										style="width: 80px; height: 50px;" />
								</td>
							</tr>
						</table>
					</form>
				</div>
				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
			</center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
			<script type="text/javascript">
var undistributed = undefined;
var exp = /^\d+$/;
function validate() {
	var pieceNumber = document.getElementById("num").value;
	var name = document.getElementById("quantityCompletion").value;
	if (pieceNumber == "") {
		alert("请输入分配数量!谢谢");
		return false;
	}
	if (name == "") {
		alert("请输入完成数量！谢谢");
		return false;
	}
<%--if(undistributed != undefined && undistributed < pieceNumber){
					alert("修改的分配数量必须小于当前可分配数量!");
					return false;
				}
				if(undistributed != undefined && name > undistributed ){
					alert("入库数量不能大于当前分配数量!");
					return false;
				}--%>
				if(exp.test(pieceNumber) && exp.test(name)){	
					
				}else{
					alert("请输入数字!");
					return false;
				}
				if(undistributed != undefined && undistributed < pieceNumber){
					alert("修改的分配数量必须小于当前可分配数量!\r\n"+"可分配的数量为:" + undistributed);
					return false;
				}
				if(name > pieceNumber){
					alert("入库数量不能大于当前分配数量!");
					return false;
				}
				return true;
			}
			function send(){
				var pieceNum = $('#pieceNumber').val();
				var num = $('#num').val();
				var hid = $('#iod').val();
				var ioId = $('#ioId').val();
				$.ajax({
					url:"internalOrder_validateNum.action",
					type:"post",
					dataType:"json",
					data:{
						  title:pieceNum,
						  message:num,
						  orderNum:hid,
						  beginTime:ioId},
					success:function(data){
						if(data){
							if(data.msg){
								alert(data.msg);
							}
							if(data.num){
								undistributed = data.num;
							}
						}
					}
				});
			}
					
		</script>
	</body>
</html>
