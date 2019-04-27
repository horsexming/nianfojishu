<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"> </script> 
		<script type="text/javascript"
 			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"> </script> 
 		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.qrcode.min.js"></script>
	</head>
	<body>
		<div class="container">
			<br><br><br><br><br>
     		<div id="printdiv" align="center">
			</div>
			<div align="center">
				<p>${visitor.voucher}</p>
			</div>
		</div>
	</body>	
	<script type="text/javascript">
	$(function(){
		$("#printdiv").qrcode({        
            width: 400,
            height: 400,
            render: "canvas", //设置渲染方式 table canvas
            text: utf16to8("${visitor.voucher}"),
            background: "#ffffff", //背景颜色 
            foreground: "#000000" //前景颜色 
        });
	});
	  function getQRCode () {
        var trs = $('#code').qrcode({        
            width: 200,
            height: 200,
            render: "canvas", //设置渲染方式 table canvas
            text: utf16to8("${visitor.voucher}"),
            background: "#ffffff", //背景颜色 
            foreground: "#000000" //前景颜色 
        }).find('tr');
        var trLen = Math.floor(trs.size() / 2);
        var tdLen = Math.floor(trs.eq(0).find('td').size() / 2);
        var tds;
        var bgColor;
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
	</script>
</html>
