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
			<br/>
			<div align="left">
			<s:if test=	"pageStatus != 'gys' && pageStatus!= 'noprint'">
				<s:if test='rs.status == "待领"'>
					<table>
				<tr>
					<th>
						仓管:
					</th>
					<td>
						<input type="text" id="cangguan" onkeyup="fuzhi(this)" onblur="fuzhi(this)"/>
					</td>
					<th>
						仓库主管:
					</th>
					<td>
						<input type="text" id="caikuzg" onkeyup="fuzhi(this)" onblur="fuzhi(this)"/>
					</td>
				</tr>
				<tr>
					<th>
						采购:
					</th>
					<td>
						<input type="text" id="caigou" onkeyup="fuzhi(this)" onblur="fuzhi(this)"/>
					</td>
					<th>
						采购主管:
					</th>
					<td>
						<input type="text" id="caigouzg" onkeyup="fuzhi(this)" onblur="fuzhi(this)"/>
					</td>
				</tr>
				<tr>
					<th>
						制单:
					</th>
					<td>
						<input type="text" id="zhidan" onkeyup="fuzhi(this)" onblur="fuzhi(this)" value="${session.Users.name}"/>
					</td>
					<th>
						IQC:
					</th>
					<td>
						<input type="text" id="IQC" onkeyup="fuzhi(this)" onblur="fuzhi(this)"/>
					</td>
				</tr>
			</table>
			</s:if>
			</s:if>
			</div>
			<div id="printDiv">
		<form action="" id="submit" method="post">
				<table  class="table">
					<tr>
						<th colspan="9" style="border:hidden ;">
							<font size="5">
							${application.companyInfo.name}<br/>
							${application.companyInfo.englishName}
							</font>
						</th>
					</tr>
					<tr>
						<th colspan="9" style="border:hidden ;">
							<font size="5"><u>${rs.type}</u></font>
						</th>
					</tr>
					<tr >
						<th style="border:hidden;" colspan="10" align="left">供应商:${rs.gysName}
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						退货单号:${rs.planNum}</th>
					</tr>
					<tr>
						<th colspan="9" style="border:hidden ; border-bottom:solid 1px;">
							&nbsp;
						</th>
					</tr>
					<tr >
						<th align="left" style="border:solid 1px;"><font size="2">件号</font></th>
						<th align="left" style="border:solid 1px;"> <font size="2">产品名称</font></th>
						<th align="left" style="border:solid 1px;width: 180px;"><font size="2">规格型号</font></th>
						<th align="left" style="border:solid 1px;"><font size="2">图号</font></th>
						<th align="left" style="border:solid 1px;"><font size="2">单位</font></th>
						<th align="left" style="border:solid 1px;"><font size="2">数量</font></th>
						<th align="left" style="border:solid 1px; width: 120px;"><font size="2">采购订单号</font></th>
						<th align="left" style="border:solid 1px; width: 120px;"><font size="2">送货单号</font></th>
						<th align="left" style="border: solid 1px;width: 220px;"><font size="2">不良原因</font></th>
					</tr>
					<s:iterator value="list" id="pageList" status="pagestatus1">
						<tr style="height: 35px;line-height: 12px;">
							<td align="left" style="border: solid 1px;"> <font size="1">${pageList.markId}</font></td>
							<td align="left" style="border: solid 1px;width: 180px;height: 35px;" class="proName_td">
								<p class="proName_p">
									<font size="1">${pageList.proName}</font>
								</p>
								
							</td>
							<td align="left" style="border: solid 1px; width: 180px;"><font size="1">${pageList.specification}</font></td>
							<td align="left" style="border: solid 1px;"><font size="1">${pageList.tuhao}</font></td>
							<td align="left" style="border: solid 1px;"><font size="1">${pageList.unit}</font></td>
							<td align="left" style="border: solid 1px;">${pageList.thNumber}</td>
							<td align="left" style="border: solid 1px;width: 100px;">${pageList.cgOrderNum}</td>
							<td align="left" style="border: solid 1px; width: 100px;">
								<font size="1">${pageList.shOrderNum} </font>
							</th>
							<th align="left" style="border: solid 1px;width: 220px;">${pageList.bldescribe}</th>
						</tr>
					</s:iterator>
					<tr>
						<th colspan="9" align="right" style="border: solid 1px;">
							合计:${rs.thNumber}
						</th>
					</tr>
				<s:if test='pageStatus!= "noprint"'>
					<tr >
						<th colspan="2" align="left" style="border: hidden;border-top:solid 1px; ">
							制单:<span id="zhidan1">${rs.zhidan}</span>
							<input type="hidden" value="" name="rs.zhidan" id="zhidan2">
						</th>
						<th colspan="2" align="left" style="border: hidden;border-top:solid 1px; ">
							仓管:<span id="cangguan1">${rs.cangguan}</span>
							<input type="hidden" value="" name="rs.cangguan" id="cangguan2">
						</th>
						<th colspan="3" align="left" style="border: hidden;border-top:solid 1px; ">
							IQC:<span id="IQC1">${rs.iqc}</span>
							<input type="hidden" value="" name="rs.iqc" id="IQC2">
						</th>
						<th colspan="2" align="left" style="border: hidden;border-top:solid 1px; ">
							仓库主管:<span id="caikuzg1">${rs.caikuzg}</span>
							<input type="hidden" value="" name="rs.caikuzg" id="caikuzg2">
						</th>
					</tr>
					<tr >
						<th colspan="2" align="left" style="border: hidden;border-top:solid 1px; ">
							采购:<span id="caigou1">${rs.caigou}</span>
							<input type="hidden" value="" name="rs.caigou" id="caigou2">
						</th>
						<th colspan="3" align="left" style="border: hidden;border-top:solid 1px; ">
							采购主管:<span id="caigouzg1">${rs.caigouzg}</span>
							<input type="hidden" value="" name="rs.caigouzg" id="caigouzg2">
						</th>
						<th colspan="2" align="left" style="border: hidden;border-top:solid 1px; ">
							财务:
						</th>
						<th colspan="4" align="left" style="border: hidden;border-top:solid 1px; ">
							供应商:${rs.gysjc}
						</th>
					</tr>
					<tr >
						<th colspan="2" align="left" style="border: hidden;border-top:solid 1px; ">
							白联:仓管
						</th>
						<th colspan="3" align="left" style="border: hidden;border-top:solid 1px; ">
							红联:财务
						</th>
						<th colspan="4" align="left" style="border: hidden;border-top:solid 1px; ">
							黄联:供应商
						</th>
					</tr>
				</s:if>
					<input type="hidden" value="${rs.id}" name="rs.id"/>
				</table>
			</form>
			</div>
		<s:if test='pageStatus!= "noprint"'>	
			<s:if test="pageStatus != 'gys'">
				<s:if test='rs.status == "待领"' >
					<input type="button" value="打印"  onclick="printRs();todisabled(this)" class="input" />
				</s:if>
				<s:elseif test='tag == "bd"'>
					<input type="button" value="补打"  onclick="printRsbd()" class="input" />
				</s:elseif>
			</s:if>	
		</s:if>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		
<SCRIPT type="text/javascript">
function printRsbd(){
	textoverflow('proName_td','proName_p');
	pagePrint('printDiv');
}
function printRs() {
	pagePrint('printDiv');
	$.ajax( {
		type : "POST",
		url : "WaigouwaiweiPlanAction!updateRsToPrint.action",
		data :$("#submit").serialize(),
		dataType : "json",
		cache : false,//防止数据缓存
		success : function(msg) {
			alert(msg);
		}
	});
}

$(function(){
	//var daxie =	daxiezhuanhuan('${rs.thNumber}');
	//$("#daxie").html(daxie);
	getQRCode(60,60,'${rs.planNum}','ercode');
})
function daxiezhuanhuan(n){
	 var fraction = ['角', '分'];
    var digit = [
        '零', '壹', '贰', '叁', '肆',
        '伍', '陆', '柒', '捌', '玖'
    ];
    var unit = [
        ['个', '万', '亿'],
        ['', '拾', '佰', '仟']
    ];
    var head = n < 0 ? '欠' : '';
    n = Math.abs(n);
    var s = '';
    for (var i = 0; i < fraction.length; i++) {
        s += (digit[Math.floor(n * 10 * Math.pow(10, i)) % 10] + fraction[i]).replace(/零./, '');
    }
    s = s || '整';
    n = Math.floor(n);
    for (var i = 0; i < unit[0].length && n > 0; i++) {
        var p = '';
        for (var j = 0; j < unit[1].length && n > 0; j++) {
            p = digit[n % 10] + unit[1][j] + p;
            n = Math.floor(n / 10);
        }
        s = p.replace(/(零.)*零$/, '').replace(/^$/, '零') + unit[0][i] + s;
    }
    return head + s.replace(/(零.)*零元/, '元')
        .replace(/(零.)+/g, '零')
        .replace(/^整$/, '零元整');
}
function fuzhi(obj){
	var id = obj.id;
	if(obj.value!=""){
		if(id!=""){
			$("#"+id+"1").html(obj.value);
			$("#"+id+"2").html(obj.value);
		}
	}else{
		$("#"+id+"1").html('');
	}
}
</SCRIPT>
	</body>
</html>
