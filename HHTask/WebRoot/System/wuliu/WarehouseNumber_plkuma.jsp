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
		<style type="text/css">
.table1 {
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	border: solid #000000;
	border-width: 1px 0 0 1px;
	width: 100%;
}

.table1 th,.table1 td {
	border: solid #000000;
	border-width: 1 1px 1px 1;
}
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<s:iterator value="wnList" id="pagelist" status="pagestatus">
					<div id="code${pagestatus.index}" style="display: none;">
					</div>
					<input type="hidden" id="barCode${pagestatus.index}"
						value="${pagelist.barCode}" />
				</s:iterator>

				<br />
				<s:iterator value="wnList" id="pagelist" status="pagestatus">
					<s:if test="#pagestatus.index % 32 == 0">
						<input type="button" value="打印第${pagestatus.index/32+1}张"
							onclick="pagePrint('printdiv${pagestatus.index}')" class="input" />
						<div id="printdiv${pagestatus.index}">
							<table
								style="border: 1px solid #000000; border-collapse: collapse; width: 100%;">
					</s:if>
								<s:if test="#pagestatus.index % 4 == 0">
									<tr>
								</s:if>
								<th style="border: 3px solid #000000;">
									<div id="printdiv${pagestatus.index}"
										style="padding: 0px; margin-top: 10px;">
										<img alt="" src="" id="myimg${pagestatus.index}">
									</div>
									<font style="font-size: 30px; padding: 0px; margin: 0px;">${pagelist.number}</font>
								</th>
								<s:if test="(#pagestatus.index+1) % 4 == 0">
									</tr>
								</s:if>
					<s:if test="(#pagestatus.index+1) % 32 == 0">
							</table>
						</div>
						<div align="center" style="width: 100%" id="dayin">

						</div>
					</s:if>
				</s:iterator>
			</div>

			<%--											<script type="text/javascript">--%>
			<%--$('#code').qrcode( {--%>
			<%--	render : "table", //table方式 --%>
			<%--	width : 200, //宽度 --%>
			<%--	height : 200, //高度 --%>
			<%--	text : "${param.number}" //任意内容 --%>
			<%--}); //任意字符串 --%>
			<%----%>
			<%--</script>--%>
		</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
	function dayin(){
		var printBody = document.getElementById("code");
	var printBodyHTML = "";
	var bodyHTML = "";
	if (printBodyHTML == "") {
		printBodyHTML = printBody.innerHTML;
		alert(printBodyHTML);
	}
	if (bodyHTML == "") {
		bodyHTML = document.body.innerHTML;
	}
	document.body.innerHTML = printBodyHTML;
	window.print();
	document.body.innerHTML = bodyHTML;
	}
	var size = ${size}
$(function(){
<%--	var n = size%3;--%>
<%--	for(var i=0;i<3-n;i++){--%>
<%--		$("#li"+(size-1)).append('<div class="div_a" style="width: 300px;height: 280px; ">&nbsp;</div>');--%>
<%--	}--%>
	for(var i=0;i<size;i++){
		getQRCode (i);
	}
	
})
  function getQRCode (num) {
		var barCode = $("#barCode"+num).val();
        var trs = $('#code'+num).qrcode({        
            width: 80,
            height: 80,
            render: "canvas", //设置渲染方式 table canvas
            text: utf16to8(barCode),
            background: "#ffffff", //背景颜色 
            foreground: "#000000" //前景颜色 
        }).find('tr'), trLen = Math.floor(trs.size() / 2), tdLen = Math.floor(trs.eq(0).find('td').size() / 2), tds, bgColor;
        var colors = [['#ff0000', '#0100e2'], ['#00ed01', '#9f4d95']];
        trs.each(function (j) {
            tds = $(this).find('td');
            tds.each(function (i) {
                bgColor = this.style.backgroundColor;
                if (bgColor == 'red') {
                    this.style.backgroundColor = colors[j < trLen ? 0 : 1][i < tdLen ? 0 : 1];
                }
            });
        });
            var canvas =	$("canvas");
		var iamge =	canvas[num].toDataURL('image/png');
			$(canvas[num]).hide();
		$("#myimg"+num).attr('src',iamge);
    }
function utf16to8(str) {
        var out, i, len, c;
        out = "";
        len = str.length;
        for (i = 0; i < len; i++) {
            c = str.charCodeAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                out += str.charAt(i);
            } else if (c > 0x07FF) {
                out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
            } else {
                out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
                out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
            }
        }
        return out;
    } 
</SCRIPT>
	</body>
</html>
