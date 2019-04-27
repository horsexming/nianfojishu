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
	<body style="">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<font id="zi_font" color="red"></font>
				<h2>
					产品月总产能分析系统(Capacity Per Month)
				</h2>
				<table id="mytable" class="table">
					<tr>
						<th style="width: 75px;" align="center" colspan="${size+1}">
							单班工作时长(Processing Time Per Shift):
							<input type="text" id="gzsc" style="width: 70px;" maxlength="2"
								value="7" onkeyup="numyanzheng(this,'gzsc');jisuan2(this)"
								onblur="numyanzheng(this)" />(H)
						</th>
					</tr>
					<tr id="markId_tr" align="center">
						<th style="width: 150px;">
							零件号(Part No.)
						</th>
						<s:iterator value="markIdS" id="pagemarkId" status="pagestatus">
							<td style="width: 75px;">
								${pagemarkId}
								<input type="hidden" value="${pagemarkId}"
									id="markId_${pagestatus.index}">
							</td>
						</s:iterator>
					</tr>
					<tr id="capacityS_tr" align="center">
						<th style="width: 150px;">
							单班最大产能
							<br />
							Max Capacity Per Shift
						</th>
						<s:iterator value="capacityS" id="pagecapacity"
							status="pagestatus1">
							<td style="width: 75px;">
								<s:if test="#pagecapacity == 'null' ||#pagecapacity == null ">0
								<input type="hidden" value="0"
										id="capacity_${pagestatus1.index}">
								</s:if>
								<s:else>
									<input type="text" value="${pagecapacity}"
										id="capacity_${pagestatus1.index}" style="width: 70px;"
										onkeyup="jisuan(${pagestatus1.index})"><br/>
								(默认${pagecapacity})
							</s:else>
							</td>
						</s:iterator>
					</tr>
					<tr id="xq_tr" align="center">
						<th style="width: 150px;">
							订单需求数量
							<br />
							Demand Quantity
						</th>
					</tr>
					<tr id="jfzq_tr" align="center">
						<th style="width: 150px;">
							交付周期(天)
							<br />
							Delivery Period (days)
						</th>
					</tr>
					<tr id="cnbl_tr" align="center">
						<th style="width: 150px;">
							产能比例
							<br />
							Capacity Ratio
						</th>
					</tr>
					<tr id="sc_tr" align="center">
						<th style="width: 150px;">
							时长
							<br />
							Processing Time Per Day
						</th>
					</tr>
					<tr id="scz_tr" align="center">
						<th style="width: 75px;" colspan="${size+1}">
							单班总时长(All Of The Orders Total Time Per Day):
							<input type="text" id="sc_z" style="width: 70px;" />(H)
						</th>
					</tr>
				</table>

			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
var size = ${size}
$(function(){
		for(var i=0; i<size; i++){
			$("#xq_tr").append('<td style="width: 75px;"><input type="text" id=xq_'+i+' style="width: 70px;"  onblur="numyanzheng(this)"   onkeyup="numyanzheng(this);jisuan('+i+')"  onchange="jisuan('+i+')"/></td>');
			$("#jfzq_tr").append('<td style="width: 75px;"><input type="text" id=jfzq_'+i+' value = "15" style="width: 70px;"  onblur="numyanzheng(this)"   onkeyup="numyanzheng(this);jisuan('+i+')" onchange="jisuan('+i+')"/></td>');
			$("#cnbl_tr").append('<td style="width: 75px;"><input type="text" id=cnbl_'+i+' style="width: 70px;" readonly="readonly"/></td>');
			$("#sc_tr").append('<td style="width: 75px;"><input type="text" id=sc_'+i+' style="width: 70px;" onchange="jssuan()" readonly="readonly"/></td>');
		}
})

function numyanzheng(obj,name){
	if(obj!=null && obj.value!=""){
		var szReg=/^\d+(\.\d+)?$/;
		var value = parseFloat(obj.value);
		var bChk=szReg.test(obj.value);
		if(!bChk){
			obj.value = "";
			obj.focus();
			alert("请输入正数");
		}else if(name == 'gzsc' && value > 24){
			obj.value = "";
			obj.focus();
			alert("请输入小于24的数字");
		}
	}
}
function jisuan(num){
	if(num!=null){
		var gzsc = $("#gzsc").val();
		var xq = $("#xq_"+num).val();
		var jfzq =$("#jfzq_"+num).val(); 
		var capacity =$("#capacity_"+num).val();
		if(xq!=null && xq!='' && jfzq!=null &&
			jfzq!='' && capacity!=null && capacity != ''
			&& gzsc != null && gzsc != ''){
		var	 b3 = parseFloat(xq);
		var b4 =parseFloat(jfzq); 
		var b2 = parseFloat(capacity);
		gzsc = parseFloat(gzsc);
		var sc = (b3/b4)*(gzsc/b2);
			sc = sc.toFixed(3);
			$("#sc_"+num).val(sc);
		var cnbl = b2/(b3/b4);
			cnbl = cnbl.toFixed(3);
		$("#cnbl_"+num).val(cnbl);
			var sc_z = 0;
			var cnbl_z = 0;
			for(var i=0;i<size; i++){
				var sc_num = $("#sc_"+i).val();
				var cnbl_num = $("#cnbl_"+i).val();
				if(sc_num!=null && sc_num!=''){
					var sc_ = parseFloat(sc_num);
					sc_z += sc_;
				}
				if(cnbl_num!=null && cnbl_num != ''){
					var cnbl_ = parseFloat(cnbl_num);
					cnbl_z = cnbl_;
				}
			}
			sc_z =sc_z.toFixed(3);
			cnbl_z =cnbl_z.toFixed(3);
			$("#sc_z").val(sc_z);
			$("#cnbl_z").val(cnbl_z);
			
		}
	}
}
function jisuan2(obj){
	if(obj!=null && obj.value!=''){
		var gzsc = obj.value;
		var sc_z = 0;
		var cnbl_z = 0;
		for(var i=0; i<size; i++){
			var xq = $("#xq_"+i).val();
			var jfzq =$("#jfzq_"+i).val(); 
			var capacity =$("#capacity_"+i).val();
			if(xq!=null && xq!='' && jfzq!=null &&
			jfzq!='' && capacity!=null && capacity != ''
			&& gzsc != null && gzsc != ''){
				var	 b3 = parseFloat(xq);
				var b4 =parseFloat(jfzq); 
				var b2 = parseFloat(capacity);
				gzsc = parseFloat(gzsc);
				var sc = (b3/b4)*(gzsc/b2);
				sc = sc.toFixed(3);
				$("#sc_"+i).val(sc);
				var cnbl = b2/(b3/b4);
				cnbl = cnbl.toFixed(3);
				$("#cnbl_"+i).val(cnbl);
				sc = parseFloat(sc);
				cnbl = parseFloat(cnbl);
			sc_z += sc;
			sc_z = parseFloat(sc_z)
			}
		}
		
			
			sc_z =sc_z.toFixed(3);
			$("#sc_z").val(sc_z);
	}
	
}

</SCRIPT>
	</body>
</html>
