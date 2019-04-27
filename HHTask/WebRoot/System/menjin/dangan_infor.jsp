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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<br />
				<br />
				<br />
				<div id="code" style="display: none;">
				</div>
				<div style="width: 100%; height: auto;" id="printdiv">
					<div style="float:left; width: 35%">
						<img alt="" src="" id="myimg">
					</div>
					<div style="float: left; width: 65%; margin-top: 5px;">
						<div>
							<span style="font-size: 40px">${param.ku}</span>
						</div>
						<div style="margin-top: 35px;">
							<span style="font-size: 18px">${companyInfo.name}</span>
						</div>
					</div>
				</div>
				<br />
				<input type="button" value="打印" onclick="pagePrint('printdiv')"
					class="input" />
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
	
	$(function(){
	getQRCode () ;
})
  function getQRCode () {
        var trs = $('#code').qrcode({        
            width: 100,
            height: 100,
            render: "canvas", //设置渲染方式 table canvas
            text: utf16to8("${param.number}"),
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
		var iamge =	canvas[0].toDataURL('image/png');
			$(canvas[0]).hide();
		$("#myimg").attr('src',iamge);
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
