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
		<STYLE type="text/css">
			  #output{
			  
    }
		</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<font id="font_zi" color="red"></font>
				<form action="IntelligentDiagnosisAction_adduser.action" method="post" onsubmit="return check()">
					<table>
						<tr>
							<th>姓&nbsp;&nbsp;&nbsp;&nbsp;名:</th>
							<td>
								<input type="text" value="${user.name}" 
								name="user.name" id="name" onblur=""onfocus="nameyanzheng(this)"
								onkeyup="nameyanzheng(this)"
									onblur="nameyanzheng(this)"
									onchange="nameyanzheng(this)"/>
								<font color="red" id="name_font">*</font>
							</td>
						</tr>
						<tr>
							<th>手机号:</th>
							<td>
								<input type="text" value="${user.password.phoneNumber}" name="user.password.phoneNumber" 
								id="phoneNumber" maxlength="11"onfocus="mobilephoneyanzhen(this)"
									onkeyup="mobilephoneyanzhen(this)"
									onblur="mobilephoneyanzhen(this)"
									onchange="mobilephoneyanzhen(this)"/>
									<font color="red" id="phoneNumber_font">*</font>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="hidden" value="${no}" id="fatherNo" name="no"/>
								<input type="hidden" value="信息" name="user.dept"/>
								<input type="hidden" value="123456" name="user.uid"/>
								<input type="submit" value="产生二维码" class="input" id="sub" />
							</td>
						</tr>
					</table>
				</form>
				<div id="output" >
				</div>

			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		
<SCRIPT type="text/javascript">
var id =${user.id}
$(function(){
	getQRCode();
})
  function getQRCode () {
	  var res = generateMixed(16);
	  if(id!=null && id>0 && res!=""){
		  $("#output").empty();
        var trs = $('#output').qrcode({        
            width: 200,
            height: 200,
            render: "canvas", //设置渲染方式 table canvas
            text: utf16to8("<%=basePath%>/IntelligentDiagnosisAction_initadd.action?No="+res),
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
			iamge = iamge.substring("22");
		if(iamge!=""){
			$("#font_zi").html("");
			var arrays = $("#fatherNo").val();
			var	fatherNo = arrays.split(",");
			$("#output").append("<br/><input type='button' onclick=isQRCodeKu('"+res+"') value='检验二维码' id='jybutton'></input>")
			$.ajax({
				 type : "POST",
				url : "IntelligentDiagnosisAction_addQRCodeKu.action",
				data : {
					'qrcodeku.no' : res,
					'qrcodeku.userId':id,
					'qrcodeku.userName':$("#name").val(),
					'qrcodeku.phoneNumber':$("#phoneNumber").val(),
					'qrcodeku.fatherNo':fatherNo[0],
					strImg:iamge
				},
		dataType : "json",
		success : function(data) {
			if(data.success){
				$(canvas[0]).show();
				$("#font_zi").html("生成二维码成功,请检验该二维码");
			}
		}
				
			})
		}else{
				$("#font_zi").html("生成二维码失败");
			}
	 }
    }
function xiazai(name){
			//对中文进行加密
			var fileName1 = encodeURI(encodeURI(name));
			location.href="<%=request.getContextPath()%>/DownAction.action?fileName="+fileName1;
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
    
   var chars = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
var res = "";
function generateMixed(n) {
     for(var i = 0; i < n ; i ++) {
         var id = Math.ceil(Math.random()*35);
         res += chars[id];
     }
     return res;
}
function isQRCodeKu(res){
	if(res!=null && res!=""){
    	 $.ajax({
    		 type : "POST",
			url : "IntelligentDiagnosisAction_getqrcodekuByNo.action",
			data : {
				No : res,
		},
		dataType : "json",
		success : function(data) {
			if(data.success){
				$("#font_zi").html("检验通过,恭喜该二维码可用");
				var QRCOde =data.data;
				$("#jybutton").remove();
				$("#output").append("<input type='button' onclick=xiazai('"+QRCOde.name+"') value='下载' class='input'>");
			}else{
				$("#font_zi").html("该二维码不可用,正在为你生成另一个");
				getQRCode();
			}
		}
    	 })
     }
}  
  

function check(){
	var name = $("#name").val();
	var phoneNumber = $("#phoneNumber").val();
	if(name!=null && name == ""){
		$("#name_font").html("请填写姓名");
		$("#name").focus();
		return false;
	}else if(phoneNumber!=null && phoneNumber == ""){
		$("#phoneNumber_font").html("请填写手机号");
		$("#phoneNumber").focus();
		return false;
	}
	document.getElementById("sub").disabled="disabled";
	return true;
}

function nameyanzheng(obj){
	if(obj!=null && obj.value.trim() ==""){
		 obj.focus(); 
		 $("#name_font").css({
    		color:"red"
    	});
		$("#name_font").html("✘");	 
	}else{
		$("#name_font").css({
    		color:"blue"
    	});
    		$("#name_font").html("✔");	
	}
	
}


function mobilephoneyanzhen(obj){
	 if(!(/^1[3|4|5|8|7|9][0-9]\d{8}$/.test(obj.value))){ 
        $("#phoneNumber_font").html("✘");
        $("#phoneNumber_font").css({
    		color:"red"
    	});
      	 obj.focus(); 
        return false; 
    } else{
    	$("#phoneNumber_font").css({
    		color:"blue"
    	});
    		$("#phoneNumber_font").html("✔");	
    }
}

</SCRIPT>
	</body>
</html>

