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
	<title>快递员信息审批查看界面</title>
		<%@include file="/util/sonHead.jsp"%>
		 <style type="text/css">
        body{
            margin: 0;
            padding: 0;
        }
        .yctpdiv{
            position:absolute;
            top: 50%;
            left: 50%;
            -webkit-transform: translate(-50%, -50%);
            -moz-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            -o-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
        }
        img{
            transform: rotate(0deg);
        }
        .cp {
			border: 2px solid;
			border-color: #5599ff;
			font-size: 18px;
			color: #5555ff;
		}
    </style>
	</head>
	<body>
	    <div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div style="display: none; width:500px; height:500px;" class="yctpdiv">
			    <img style="transform:rotate(90deg);" width=100% height=100% id="fdhxstp" src="" >
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在查看其它信息操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
	     <div class="express">
			<table class="table" style="height:140px; font-size:10px;text-align:center;font-weight:bold;">
			    <tr height=40% style="font-size: 22px;">
			        <td colspan="3">快递员信息审批查看</td>
			    </tr>
			</table>
			<table class="table" style="text-align:center; font-size:10px;" >
			    <tr style="font-weight:bold;">
			        <td>姓名</td>
			        <td>手机号</td>
			        <td>身份证号</td>
			        <td>快递公司</td>
			        <td>证件照</td>
			    </tr>	    
			    <tr>
			        <td>${courier.couName}</td>
			        <td>${courier.phoneNumber}</td>
			        <td>${courier.idNumber}</td>
			        <td>${courier.kdCompany}</td>
			        <td>
			            <img style="transform: rotate(90deg);" class="imgfd" height="40" width="40" src="<%=basePath%>/upload/file/lpanclear/${courier.idFront}" />
			        </td>
			    </tr>
			</table>										
		 </div>
		 <!--此处显示分页的首页 上一页 下一页  末页 -->
		 <!--<div align="center">第
			 <font color="red" id="cpage">${cpage}</font> /
			 <font id="total">${total}</font> 页
			 <fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
				styleClass="page" theme="number" />
		     </div>-->
	 <%@include file="/util/foot.jsp"%>
	 <script type="text/javascript">

		$(".imgfd").bind("click",function(){
	    	if($('.yctpdiv').is(':hidden')){     //B是另一个div	    		
                var path = $(this).attr("src");	
				$("#fdhxstp").attr('src',path); 
				$(".yctpdiv").show();//显示div 
			}else{
				var path =$(this).attr("src");	
				$("#fdhxstp").attr('src',path); 
                $(".yctpdiv").hide();//隐藏div 	    
			}
		});
		$("#fdhxstp").bind("click",function(){
			$(".yctpdiv").hide();//隐藏div
		});

     </script>
  </body>
</html>