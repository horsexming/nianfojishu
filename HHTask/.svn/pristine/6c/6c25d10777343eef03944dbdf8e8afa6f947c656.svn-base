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
	<style type="text/css">
	</style>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div width=100%; id="gongneng" style="">
			<div width=100%; style="">
				 <!-- 隐藏div 作用于修改 -->
			     <div width=100%; id="ycdivkdgxg" style="text-align:center;height:230px;">
			         <table height=100% class="table" style="font-size:10px;text-align:center;font-weight:bold;">
					    <tr height=30% style="font-size: 22px;">
					        <td colspan="3">修改快递柜收费标准</td>
					    </tr>
					    <tr height=40%>
					        <td>柜子类型:<input style="height:18px;width:160px;" type="text" value="${charging.type}" id="gzlx" /><span style="color:#808080;">按设备调整</span></td>
					        <td>免费时长:<input style="height:18px;width:160px;" type="number" value="${charging.overTime}" id="mfsc" /><span style="color:#808080;">单位(时/s)</span></td>
					        <td>收费标准:<input style="height:18px;width:160px;" type="text" value="${charging.cost}" id="sfbz" /><span style="color:#808080;">单位(￥/元)</span></td>
					    </tr>
					    <tr height=30%>
					        <td colspan="3" style="text-align: center;">
					            <input style="height:18px;width:160px;" type="hidden" value="${charging.id}" id="chargId" />
					            <input class="tjxgsfbz" type="button" onclick="add()" value="提交" />
					            <input class="qkxgsfbz" type="button" value="重置" />
					        </td>
					    </tr>
					</table>
			     </div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">  	
		$(function() {			
			//获取并刷新父界面
			var errorMessage = '${errorMessage}';
			if (errorMessage != "") {
				parent.location.reload(true);//刷新父页面
			}	
			
			//对修改信息进行提交
			$(".tjxgsfbz").click(function(){
				var chargid = $(this).prev().val();
	            var type = $(this).prev().prev().val();
				var overTime =  $("#mfsc").val();
				var cost = $("#sfbz").val();
				if(overTime==null||overTime==""||overTime==undefined||cost==null||cost==""||cost==undefined||chargid==null||chargid==""||chargid==undefined){
					 alert("请先点击修改!!!");
				}else{
					// var zzyz =  /^\d{0,10}$/;
					// var zzyz = /^[1-9]/d*$/;
				    // if(!checkNum.test(overTime)||!checkNum.test(cost)){
					//     alert("输入的值只能为正整数");
					// }else{
				    //	  alert("1111");
					  location.href="WePayAction_updateCharg.action?charging.id="+chargid+"&charging.overTime="+overTime+"&charging.cost="+cost;   				    	  
					// }
			     }    	
			});
				  
			//清空所有文本框内容
			$(".qkxgsfbz").click(function(){
			     var overTime =  $("#mfsc").val("");
				 var cost = $("#sfbz").val("");
				 var type = $("#gzlx").val("");
			});
			//新增快递柜类型标准
			 // $(".tjxgsfbz").click(function(){
				//  var overTime =  $("#mfsc").val();
			    //  var cost = $("#sfbz").val();
			    //  var type = $("#gzlx").val();
			    //  if(overTime==null||overTime==""||overTime==undefined||cost==null||cost==""||cost==undefined||type==null||type==""||type==undefined){
				//	  alert("请先添加数据!!!");
				//  }else{
				//	  location.href="WePayAction_insertCharg.action?charging.type="+type+"&charging.overTime="+overTime+"&charging.cost="+cost;		  
				//  }
			//});			
		});
	    </script>
	</body>
</html>
