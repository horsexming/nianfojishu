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
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div style="display: none; width:500px; height:500px;" class="yctpdiv">
			    <img width=100% height=100% id="fdhxstp" src="" >
			</div>
			<div align="center" style="margin-top:200px;">
				<p style="font-size:24px; color:#5599ff;">清洁表上传图片查看</p>
				<p style="margin-top:30px;color:#5599ff;">
					查找日期: <input id="dt" type="date" name="bday">
					<input id="sjcz" style="margin-left:30px;" type="submit" value="查找" />				
				</p>
				<table border="1" class="cp" style=" width:800px;">
				    <tr style="color:#5599ff;">
				        <th>上传人员</th>
				        <th>打扫日期</th>
				        <th>上传时间</th>
				        <th>上传图像</th>
				        <th>内容描述</th>
				        <th>所属值日人</th>			       
				    </tr>
				    <s:iterator id="cphone" value="clearPhoneList" status="stauts">
					    <tr style="height:40px; ">
					        <th>${cphone.manload}</th>
					        <th>${cphone.pictureDay}</th>
					        <th style="width:220px;">${cphone.dayload}</th>				        
					        <th style="width:100px; height:40px; ">
					            <img class="imgfd" height="40" width="40" src="<%=basePath%>/upload/file/lpanclear/${cphone.route}" />
					            <input type="hidden" value="${cphone.primaryName}" />
					        </th>
					        <th></th>
					        <th><a href="javascript:void(0)">查看人员</a></th>
					    </tr>
				    </s:iterator>
				</table>
				<table style="width:100px;">
					<s:iterator id="clearDay" value="clearInfoList" status="stauts">
					    <tr>
					        <th>${clearDay.employee.employeeName}</th>
					    </tr>
					</s:iterator>   
				</table>
				<!--此处显示分页的首页 上一页 下一页  末页 -->
				<div align="center">第
					<font color="red" id="cpage">${cpage}</font>
					<font id="total">${total}</font>页
					<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
						styleClass="page" theme="number"/>
						<a href="javascript:history.go(-1);">返回上一页</a>
				</div>
				<div align="center">
				    <table>
				        <tr>
				           <th></th>
				           <th></th>
				           <th></th>      
				        </tr>
				        <s:iterator id="clearDay" value="clearInfoList" status="stauts">				        
					        <tr>
					           <th>   </th>
					        </tr>
				         </s:iterator>
				    </table>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
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
        $("#sjcz").click(function(){
        	var dt = $("#dt").val();
        	location.href="ClearInfoAction_selectPhrecordList.action?clearPhone.pictureDay="+dt;
        })
	</script>
</html>
