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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<br />
				<h2 style="font-size: x-large">
					修改供应商
				</h2>
				<br />
				<h2 style="font-size: x-large; color: red">
					${errorMessage}
				</h2>
				<h1>
					本订单明细供应商:${waigouPlan.gysName}
				</h1>
				<form action="WaigouwaiweiPlanAction!xiugaimxgys.action"
					method="post">
					<div id="order_div">
						<input type="button" value="所有供应商" onclick="showAll_div()" />
						<br/>
						<input type="hidden" value="<s:property value="list.size()"/>"
							id="listsize" />
						<br />
						<table>
							<s:iterator value="list" id="wgplan">
								<tr>
									<s:if test="waigouPlan.gysId == #wgplan.gysId">
										<td>
											<input type="radio" name="waigouOrder.id"
												value="${wgplan.waigouOrder.id}" checked="checked" />
											<a
												href="zhaobiaoAction!listByIdZhUser.action?zhUser.id=${wgplan.gysId}">${wgplan.gysName}</a>
											&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>
											<b>含税价:</b>${wgplan.hsPrice}&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>
											<b>不含税价:</b>${wgplan.price}&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>
											<b>税率:</b>${wgplan.taxprice}&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</s:if>
									<s:else>
										<td>
											<input type="radio" name="waigouOrder.id"
												value="${wgplan.waigouOrder.id}" />
											<a
												href="zhaobiaoAction!listByIdZhUser.action?zhUser.id=${wgplan.gysId}">${wgplan.gysName}</a>
											&nbsp;&nbsp;&nbsp;&nbsp;
										</td>

										<td>
											<b>含税价:</b>${wgplan.hsPrice}&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>
											<b>不含税价:</b>${wgplan.price}&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>
											<b>税率:</b>${wgplan.taxprice}&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</s:else>
								</tr>
							</s:iterator>

						</table>
					</div>
					<div id="all_div" style="display: none;">
						<input type="button" value="已有订单供应商" onclick="showOrder_div()" />
						<br />
						<table>
							<s:iterator value="listAll" id="price" status="pagestatus">
								<tr>
									<s:if test="waigouPlan.gysId == #price.gysId">
										<td>
											<input type="radio" name="id2" value="${price.id}" />
											<a
												href="zhaobiaoAction!listByIdZhUser.action?zhUser.id=${price.gysId}">${price.gys}</a>
											&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>
											<b>含税价:</b>${price.hsPrice}&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>
											<b>不含税价:</b>${price.bhsPrice}&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>
											<b>税率:</b>${price.taxprice}&nbsp;&nbsp;&nbsp;&nbsp;
										</td>

									</s:if>
									<s:else>
										<td>
											<input type="radio" name="id2" value="${price.id}" />
											<a
												href="zhaobiaoAction!listByIdZhUser.action?zhUser.id=${price.gysId}">${price.gys}</a>
											&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>
											<b>含税价:</b>${price.hsPrice}&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>
											<b>不含税价:</b>${price.bhsPrice}&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>
											<b>税率:</b>${price.taxprice}&nbsp;&nbsp;&nbsp;&nbsp;
										</td>

									</s:else>
								</tr>
							</s:iterator>
						</table>
					</div>
					<input type="submit" value="修改" class="input">
					<input type="hidden" value="${waigouPlan.id}" name="id" />
				</form>
				<input type="hidden" value="${errorMessage}" id="rebeack" />
			</div>


		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
$(function(){
	var rebeack = $("#rebeack").val();
	if (~rebeack.index("修改成功")) {
		alert(rebeack);
		parent.chageDiv('none');
		parent.window.location.reload();
	}
	if($("#listsize")!=null){
		var	listsize =	$("#listsize").val();
	if(listsize == 0 || listsize == ""){
		$("#order_div").hide();
		$("#all_div").show();
	}
	}
	
})	
		
		
function showAll_div(){
	var arrays =	document.getElementsByName("waigouOrder.id");
	if(arrays!=null && arrays.length>0){
		for(var i=0; i< arrays.length; i++){
			if(arrays[i].checked == true){
				arrays[i].checked = false;
			}
		}
	}
	$("#order_div").hide();
	$("#all_div").show();
}
function showOrder_div(){
	var arrays =	document.getElementsByName("id2");
	if(arrays!=null && arrays.length>0){
		for(var i=0; i< arrays.length; i++){
			if(arrays[i].checked == true){
				arrays[i].checked = false;
			}
		}
	}
	$("#order_div").show();
	$("#all_div").hide();
}	
	
	
	
	</SCRIPT>
	</body>
</html>
