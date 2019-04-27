<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
			href="<%=basePath%>/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script src="<%=basePath%>/javascript/jquery/jquery-3.2.1.js"> </script> 
		<script type="text/javascript"
 			src="<%=basePath%>/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"> </script> 
 		<script type="text/javascript" src="<%=basePath%>/javascript/jquery.qrcode.min.js"></script>
 		<style type="text/css">
 			.left{
 				text-align: right;
 				width: 45%;
 			}
 			.right{
 				text-align: left;
 				width: 55%;
 			}
 			*{
 				margin: 0;
 				padding: 0;
 			}
 			table,tr,td{
 				margin: 0;
 				padding: 0;
 			}
 			.table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th{
			    padding: 4px;
			    line-height: 1.22857143;
			    vertical-align: top;
			    border-top: 1px solid #ddd;
 			}
 		</style>
	</head>
	<body>
		<div class="container">
           	<s:iterator value="visitorList" id="vis" status="ps">
           		<div class="row col-lg-12" style="page-break-after:always" id="print_${ps.index }">
	           		<table class="table">
	            		<thead>
	            			<tr>
	            				<th colspan="2">
									<h3 align="center">${companyInfo.shortName}</h3>
									<h5 align="center">(${companyInfo.englishName})</h5>
									<h4 align="center">访客单</h4>
	            				</th>
	            			</tr>
	            		</thead>
	            		<tbody>
	            			<tr>
	            				<td class="left">NO：</td>
	            				<td class="right">${vis.voucher }
	           						<input type="hidden" value="${vis.voucher}" id="voucher_${ps.index }">
	           					</td>
	            			</tr>
	            			<tr>
	            				<td class="left">访客人次：</td>
	            				<td class="right">${vis.id }</td>
	            			</tr>
	            			<tr>
	            				<td class="left">姓名：</td>
	            				<td class="right">${vis.visitorName }</td>
	            			</tr>
	            			<tr>
	            				<td class="left">来访单位：</td>
	            				<td class="right">${vis.visitorComp}</td>
	            			</tr>
	            			<tr>
	            				<td class="left">申请时间：</td>
	            				<td class="right">${vis.addTime}</td>
	            			</tr>
	            			<tr>
	            				<td class="left">被访人：</td>
	            				<td class="right">${vis.interviewee}</td>
	            			</tr>
	            			<tr>
	            				<td class="left">开始时间：</td>
	            				<td class="right">${vis.dateTime}</td>
	            			</tr>
	            			<tr>
	            				<td class="left">结束时间：</td>
	            				<td class="right">${vis.endTime}</td>
	            			</tr>
	            			<tr>
	            				<td class="left">来访缘由：</td>
	            				<td class="right">${vis.visitorCause}</td>
	            			</tr>
	            		</tbody>
	            		<tfoot>
	            			<tr style="height: 100px;">
	            				<td colspan="2"  style="height: 100px;">
	            					<div id="printdiv_${ps.index}" style="width: 100%;height: 100px;padding: auto;margin: auto;text-align: center;">
									</div>
									<div style="clear: both;"></div>
	            				</td>
	            			</tr>
	            			<tr>
	            				<td colspan="2">*注：单次进出15秒后自动失效。请妥善保管此凭条，不能重复打印！</td>
	            			</tr>
	            		</tfoot>
	            	</table>
	           	</div>
           	</s:iterator>
           	<input type="hidden" value="${visitorList.size()}" id="visListSize" >
		</div>
	</body>	
	<script type="text/javascript">
	$(function(){
		var visListSize = $("#visListSize").val();
		for(var i=0;i<visListSize;i++){
			
			//根据div标签ID拿到div中的局部内容
		    bdhtml=window.document.body.innerHTML; 
		    var jubuData = document.getElementById("print_"+i).innerHTML;
		    //把获取的 局部div内容赋给body标签, 相当于重置了 body里的内容
		    window.document.body.innerHTML= jubuData; 
			var voucher = $("#voucher_"+i).val();
			$("#printdiv_"+i).qrcode({
	            width: 80,
	            height: 80,
	            render: "canvas", //设置渲染方式 table canvas
	            text: utf16to8(voucher),
	            background: "#ffffff", //背景颜色 
	            foreground: "#000000" //前景颜色 
	        });
		    //调用打印功能
		    window.print();
		    window.document.body.innerHTML=bdhtml;//重新给页面内容赋值；
// 			window.print();
		}
		location.href="<%=basePath%>/visitorAction!toVisitorCheck.action";
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
        var canvas =$("canvas");
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