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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div>
						<span id="msg_span" style="color:red;font-size:5px;"></span>
				</div>
				<form action="JiaoXiaoKaoHeAction_wanShaInCome.action" method="POST" onsubmit="return check()">
					<table class="table">
						<tr>
							<th align="right">
								部门:
							</th>
							<td>
								<span>${inCome.dept}</span>
							</td>
							<th align="right">
								姓名:
							</th>
							<td>
								<span>${inCome.name}</span>
							</td>
							<th align="right">
								职务:
							</th>
							<td>
								<span>${inCome.post}</span>
							</td>
						</tr>
						<tr>
							<th align="right">
								现薪资:
							</th>
							<td>
								<input type="text" onchange="numyanzheng(this)" 
								name="inCome.nowsalary" id="nowsalary" oninput="calculate()"/>
							</td>
							<th align="right">
								部门人均:
							</th>
							<td>
								<input type="hidden" value="${inCome.deptaverage}" id="deptaverage" />
								<span>${inCome.deptaverage}</span>
							</td>
							<th align="right">
								岗位系数:
							</th>
							<td>
								<input type="text" value="" id="postfactor" 
								name="inCome.postfactor" onchange="numyanzheng(this)" oninput="calculate()"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								绩效考核:
							</th>
							<td>
								<s:if test="inCome.jixiao!=null && inCome.jixiao>0">
									<input type="hidden" value="${inCome.jixiao}" id="jixiao" name="inCome.jixiao" />
									<span>${inCome.jixiao}</span>
								</s:if>
								<s:else>
									<input type="text" value="" id="jixiao" 
									name="inCome.jixiao" onchange="numyanzheng(this)" oninput="calculate()"/>
								</s:else>
							</td>
							<th align="right">
								合计:
							</th>
							<td>
								<input type="text" value="" name="inCome.heji" 
								readonly="readonly" id="heji"/>
							</td>
							<th align="right">
								差值:
							</th>
							<td>
								<input type="text" name="inCome.d_value" 
								readonly="readonly" value="" id="d_value"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								结构比:
							</th>
							<td>
								<span>${inCome.jieGouBi}</span>
							</td>
							<th align="right">
								委外比:
							</th>
							<td>
								<span>${inCome.weiwaibi}</span>
							</td>
							<th align="right">
								工资系数:
							</th>
							<td>
								<s:if test="inCome.wagesCoefficient !=null && inCome.wagesCoefficient>0">
										<input type="hidden" value="${inCome.wagesCoefficient}" id="wagesCoefficient" name="inCome.wagesCoefficient" />
									<span>${inCome.wagesCoefficient}</span>
								</s:if>
								<s:else>
									<input type="text" value="" id="wagesCoefficient" 
									name="inCome.wagesCoefficient" onchange="numyanzheng(this)" />
								</s:else>
								
							</td>
						</tr>
					</table>
					<input type="hidden" value="${inCome.id}" name="inCome.id"/>
					<input type="submit" value="计算收入" class="input" id="sub" >
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function(){
	if('${errorMessage}' == '计算完成'){
		alert('计算完成');
		parent.chageDiv('none');
		parent.window.location.reload();
	}
})
calculate =()=>{
	var e =  $("#nowsalary").val();
	var f = $("#deptaverage").val(); 
	var g =  $("#postfactor").val();
	var h = $("#jixiao").val();
	if(e && f && g && h){
		e = e*1;
		f = f*1;
		g = g*1;
		h = h*1;
		let heji =	e + (f*g*h);
		let d_value = heji-e;
		$("#heji").val(heji.toFixed(2));
		$("#d_value").val(d_value.toFixed(2));
	}
}
check = ()=>{
	var heji = $("#heji").val();
	var wagesCoefficient = $("#wagesCoefficient").val();
	if(!heji){
		$("#msg_span").text("请先计算出合计");
		return false;
	}else if(!wagesCoefficient){
		$("#msg_span").text("请填写工资系数");
		return false;
	}
	$("#msg_span").text("");
	$("#sub").attr("disabled","disabled")
}


</SCRIPT>
	</body>
</html>
