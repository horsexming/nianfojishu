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
			<h2>板材尺寸密度添加</h2>
			<font color="red" id="font_ziti"></font>
				<form action="PanelSizeAction_addpanelSize.action"   method="post" onsubmit="return check()">
					<table class="table">
					<s:if test='status == "fenmo"'>
						<tr>
							<th colspan="4" >
								材质
								<input type="text" value="" id="caizhi" name="panelSize.caizhi" />
							</th>
						</tr>
						<tr>
							<th align="right">
								粉末1材质
							</th>
							<td>
								<input type="text" value="" name="panelSize.fenmo1"  
								id="fenmo1" />
							</td>
							<th align="right">
								粉末2材质
							</th>
							<td>
								<input type="text" value="" name="panelSize.fenmo2" id="fenmo2" />
							</td>
						</tr>
						<tr>
							<th align="right">
								粉末1需喷面数
							</th>
							<td>
								<input type="text" value="" name="panelSize.miancount1" onchange="numyanzheng(this,'zhengshu')" id="miancount1" />
							</td>
							<th align="right">
								粉末2需喷面数
							</th>
							<td>
								<input type="text" value="" name="panelSize.miancount2" id="miancount2" onchange="numyanzheng(this,'zhengshu')" />
							</td>
						</tr>
						<tr>
							<th align="right">
								粉末1每公斤喷粉面积
							</th>
							<td>
								<input type="text" value="" name="panelSize.areakg1" id="areakg1" onchange="numyanzheng(this)"/>
							</td>
							<th align="right">
								粉末2分公斤喷粉面积
							</th>
							<td>
								<input type="text" value="" name="panelSize.areakg2" id="areakg2" onchange="numyanzheng(this)"/>
							</td>
							<input type="hidden" value="复合粉末" name="panelSize.type"/>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<th align="right">
								材质
							</th>
							<td>
								<input type="text" value="" id="caizhi" name="panelSize.caizhi" />
							</td>
							<th align="right">
								密度
							</th>
							<td>
								<input type="text" value="" id="density" name="panelSize.density" onchange="numyanzheng(this)"/>(kg/m³)
							</td>
						</tr>
						<tr>
							<th align="right">
								板厚从
							</th>
							<td>
								<input type="text" value="" id="fristThickness" name="panelSize.fristThickness"
								  onchange="bijiao();numyanzheng(this)"/>(mm)
							</td>
							<th align="right">
								到
							</th>
							<td>
								<input type="text" value="" id="endThickness" name="panelSize.endThickness" 
								 onchange="bijiao();numyanzheng(this)"/>(mm)
							</td>
						</tr>
						
						<tr>
							<th align="right">
								长
							</th>
							<td>
								<input type="text" value="" id="thislength" name="panelSize.thislength" 
								onchange="numyanzheng(this,'zhengshu');fuzhi()"/>(mm)
							</td>
							<th align="right">
								宽
							</th>
							<td>
								<input type="text" value="" name="panelSize.thiswideth"  
								id="thiswideth" onchange="numyanzheng(this,'zhengshu');fuzhi()"/>(mm)
								<font color="red">(尺寸:<span id="size_span"></span>）</font>
								<input type="hidden" value="" name="panelSize.size" id="size" />
								<input type="hidden" value="板材" name="panelSize.type"/>
							</td>
						</tr>
					</s:else>
						<tr>
							<th align="right">
								备注
							</th>
							<td colspan="5">
								<textarea rows="2" cols="80" name="panelSize.remarks">
								
								</textarea>
							</td>
						</tr>
					</table>
					<input type="hidden" value="${status}" name="status"/>
					<input type="submit" value="提交"  class="input" onclick="todisabled(this)" id="sub">
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function check(){
	var caizhi =$("#caizhi").val();
	var fristThickness = $("#fristThickness").val();
	var endThickness = $("#endThickness").val();
	var thislength = $("#thislength").val();
	var thiswideth = $("#thiswideth").val();
	var density = $("#density").val();
	var type = $("#type").val();
	var miancount1 = $("#miancount1").val();
	var areakg1 = $("#areakg1").val();
	var miancount2 = $("#miancount1").val();
	var areakg2 = $("#areakg2").val();
	var fenmo1 = $("#fenmo1").val();
	var fenmo2 = $("#fenmo2").val();
	if(caizhi == ''){
		$("#font_ziti").html('请填写材质');
		$("#sub").removeAttr("disabled");
		return false;
	}
	if(type == '板材'){
		if(fristThickness == ''){
		$("#font_ziti").html('请填写厚度起始范围');
		$("#sub").removeAttr("disabled");
		return false;
	}else if(endThickness == ''){
		$("#font_ziti").html('请填写厚度截止范围');
		$("#sub").removeAttr("disabled");
		return false;
	}else if(thislength == ''){
		$("#font_ziti").html('请填写长度');
		$("#sub").removeAttr("disabled");
		return false;
	}else if(thiswideth == ''){
		$("#font_ziti").html('请填写宽度');
		$("#sub").removeAttr("disabled");
		return false;
	}else if(density == ''){
		$("#font_ziti").html('请填写密度');
		$("#sub").removeAttr("disabled");
		return false;
	}
	}else if(type == '复合粉末'){
		if(fenmo1 == ''){
			$("#font_ziti").html("请填写粉末1的材质");
			$("#sub").removeAttr("disabled");
			return false;
		}else if(fenmo2 == ''){
			$("#font_ziti").html("请填写粉末2的材质");
			$("#sub").removeAttr("disabled");
			return false;
		}else if(miancount1 == ""){
			$("#font_ziti").html("请填写粉末1的需喷面数");
			$("#sub").removeAttr("disabled");
			return false;
		}else if(miancount2 == ""){
			$("#font_ziti").html("请填写粉末2的需喷面数");
			$("#sub").removeAttr("disabled");
			return false;
		}else if(areakg1 == ""){
			$("#font_ziti").html("请填写粉末1的每公斤喷粉面积");
			$("#sub").removeAttr("disabled");
			return false;
		}else if(areakg2 == ""){
			$("#font_ziti").html("请填写粉末2的每公斤喷粉面积");
			$("#sub").removeAttr("disabled");
			return false;
		}
	}
	
	 $("#font_ziti").html('');
	
}		

function bijiao(){
	var fristThickness = $("#fristThickness").val();
	var endThickness = $("#endThickness").val();
	if(fristThickness!='' && endThickness!=''){
		fristThickness = parseFloat(fristThickness);
		endThickness = parseFloat(parseFloat);
		if(fristThickness>endThickness){
			alert('起始厚度不能大于截止厚度，请重新填写！');
			$("#fristThickness").val('');
			 $("#endThickness").val('');
		}
	}
}
function fuzhi(){
	var thislength = $("#thislength").val();
	var thiswideth = $("#thiswideth").val();
	if(thislength!='' && thiswideth!= ''){
		$("#size").val(thiswideth+"x"+thislength);
		$("#size_span").html(thiswideth+"x"+thislength);
	}
	
	
}
	</script>
	</body>
</html>
