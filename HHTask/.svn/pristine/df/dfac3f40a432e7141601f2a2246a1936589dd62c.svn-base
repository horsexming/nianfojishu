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
				<div id="yulan" style="display: none;">
				<div id="showprocard">
					<table class="table" id="rootTable">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							订单编号
							<br />
							(内部)
						</th>
						<th align="center">
							件号
							<br />
							Part No.
						</th>
						<th align="center">
							业务件号
							<br />
							Part No.
						</th>
						<th align="center">
							名称
							<br />
							Name
						</th>
						<th align="center">
							卡片类型
							<br />
							Card Type
						</th>
						<th align="center">
							产品类型
							<br />
							Product Type
						</th>
						<th align="center">
							批次
							<br />
							Batch
						</th>
						<th align="center">
							数量
							<br />
							Quantity
						</th>
						<th align="center">
							交付日期
							<br />
							Card time
						</th>
						<th align="center">
							状态
							<br />
							State
						</th>
					</tr>
				</table>
				<br/>
				<h2 style="font-size: x-large;">
					物料明细信息
				</h2>
				<br/>
				<table class="table" id="procardTable">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							物料类别
						</th>
						<th>
							件号
						</th>
						<th>
							版本
						</th>
						<th>
							供料属性
						</th>
						<th>
							零件名称
						</th>
						<th>
							规格
						</th>
						<th>
							单位
						</th>
						<th>
							需求数量
						</th>
						<th>
							采购数量
						</th>
					</tr>
				</table>
			</div>
				<span id="errormsg" style="color:red;font-size: x-large; " ></span>
			</div>
			<div id="show" style="color:red;font-size: x-large; ">
				<br/>
				正在进行MRP计算,请稍候<span id="showMess1_font"></span>
			</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
var str = '';
var time;
function test(){
	if(str.length>=8){
		str='';
	}else{
		str+='。';	
	}
	$("#showMess1_font").html(str);
}

time = setInterval("test()", 400);
$(function(){
		$.ajax( {
		type : "POST",
		url : "ProcardAction!yulan_nowwyx.action",
		data : {
				id:'${param.id}'
			},
		dataType : "json",
		success : function(data) {
				$("#yulan").show();
			if(data!=null){
				if(data.success){
				procardList = data.data1;
				procard =  	data.data2;
					$("#rootTable").append('<tr><td align="left">' +procard.planOrderNum+'</td><td align="left">'+procard.markId+'</td><td align="left">'
						+procard.ywMarkId+'</td><td align="left">'+procard.proName+'</td><td>'+procard.procardStyle+'</td>'
						+'<td>'+procard.productStyle+'</td><td>'+procard.selfCard+'</td><td>'+procard.filnalCount+'</td>'
						+'<td>'+procard.jioafuDate+'</td><td>'+procard.status+'</td>'
						+'</tr>');
					var addTr = '';
					$(procardList).each(function(index,pageprocard){
						if(index%2==1){
							addTr+='<tr align="center" class="core_tr" style="display: none;"  bgcolor="#e6f3fb" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,&apos;#e6f3fb&apos;)">'
						}else{
							addTr+='<tr align="center" class="core_tr"  style="display: none;" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,&apos;&apos;)">'
						}
						addTr+='<td>'+(index+1)+'</td><td>'+pageprocard.wgType+'</td>' 
								+'<td>'+pageprocard.markId+'</td><td>'+pageprocard.banBenNumber+'</td>'
								+'<td>'+pageprocard.kgliao+'</td><td>'+pageprocard.proName+'</td>'
								+'<td>'+pageprocard.specification+'</td><td>'+pageprocard.unit+'</td>'
								+'<td>'+pageprocard.filnalCount+'</td><td>'+pageprocard.cgNumber+'</td>';
						addTr+='</tr>'		
					})
					$("#procardTable").append(addTr);
					clearInterval(time);
					$("#show").hide(1500);
					$(".core_tr").show(1500);
				}else{
					$("#errormsg").html(data.message);
					clearInterval(time);
					$("#show").hide(1500);
				}
			}
		}
	})
})


</SCRIPT>
	</body>
</html>
