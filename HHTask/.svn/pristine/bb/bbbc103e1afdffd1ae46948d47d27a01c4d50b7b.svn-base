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
	<body>
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
				<h2>制作周转流水卡片</h2>
				<form action="RunningWaterCardAction!save.action" onsubmit="return checkFormm()" method="post">
					<table class="table">
					<tr>
							<th>
								制卡类型:
							</th>
							<td>
								<select name="runningWaterCard.rwStyle" id="rwStyle"
									onblur="selectrwStyle(this)">
									<s:if test="%{null!=runningWaterCard.rwStyle}">
										<option value="${runningWaterCard.rwStyle}">
											${runningWaterCard.rwStyle}
										</option>
									</s:if>
									<s:else>
										<option selected="selected" value="周转单">
											周转单
										</option>
										<option value="周转卡">
											周转卡
										</option>
									</s:else>
								</select>
							</td>
							<th>
								生产类型:
							</th>
							<td>
								<select name="runningWaterCard.productStyle">
									<s:if test="%{null!=runningWaterCard.rwStyle}">
										<option>
											${runningWaterCard.productStyle}
										</option>
									</s:if>
									<s:else>
										<option selected="selected">
											批产
										</option>
										<option>
											试制
										</option>
									</s:else>
								</select>
							</td>
						</tr>
					<tr>
					<th>客户:</th><td><input type="text" name="runningWaterCard.customer" value="${runningWaterCard.customer }" readonly="readonly" /> </td>
					<th>车型:</th><td><input type="text" name="runningWaterCard.carStyle" value="${runningWaterCard.carStyle }" readonly="readonly" /> </td>
					</tr>
					<tr>
					<th>件号:</th><td><input type="text"  name="runningWaterCard.markId" value="${runningWaterCard.markId }" readonly="readonly" /> </td>
					<th>卡号:</th>
					<td>
					<s:if test="%{'周转卡'==runningWaterCard.rwStyle}">
						<input type="text" id="cardNum"  name="runningWaterCard.cardNum" value="" /> 
					</s:if>
					
					
					<font style="color: red;size: 12;">${message }</font></td>
					</tr>
					<tr>
							<th>
								零件名:
							</th>
							<td>
								<input type="text" id="partName" name="runningWaterCard.partName" readonly="readonly"
									value="${runningWaterCard.partName}" />
							</td>
							</tr>
					<th colspan="4">
					<input type="submit" value="提交" /> &nbsp;					
					<input type="reset" value="放弃" />
					</th>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		//提交验证
			function checkFormm(){
				var cardNum=document.getElementById("cardNum");
				 if(cardNum.value==""){
					alert("卡号不能为空!");
					cardNum.focus();
					return false;
				}
			}
		function checkCard(obj){
			var iftag="f";
			var card=obj.value;
			$.ajax({
					type : "POST",
					url : "RunningWaterCardAction!findCardByCard.action",
					data : {
						tag : card
					},
					dataType : "json",
					success : function(msg) {
						//alert(msg);
						if(msg="false"){
							alert("该卡号已存在,请核实!!!");
							$("#cardNum").val();
							iftag="no";
						}else{
							iftag="ok"
						}
						
					}
				});	
			return iftag;
		}
		</script>
	</body>
</html>
