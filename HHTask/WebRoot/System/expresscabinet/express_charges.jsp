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
	    <div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在标准设置进行操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
	     <div class="express">
			<table class="table" style="height:140px; font-size:10px;text-align:center;font-weight:bold;">
			    <tr height=40% style="font-size: 22px;">
			        <td colspan="3">快递柜收费标准设置</td>
			    </tr>
			    <tr height=30%>
			        <td>柜子类型:<input style="height:18px;width:160px;" type="text" value="" id="gzlx" /><span style="color:#808080;">按设备调整</span></td>
			        <td>免费时长:<input style="height:18px;width:160px;" type="number" value="" id="mfsc" /><span style="color:#808080;">单位(时/s)</span></td>
			        <td>收费标准:<input style="height:18px;width:160px;" type="text" value="" id="sfbz" /><span style="color:#808080;">单位(￥/元)</span></td>
			    </tr>
			    <tr height=30%>
			        <td colspan="3" style="text-align: center;">
			            <input style="height:18px;width:160px;" type="hidden" value="" id="chargId" />
			            <input class="cxkdglxbz" type="button" value="查询" />
			            <input class="qkxgsfbz" type="button" value="清空" />
					    <input class="xzxgsfbz" type="button" onclick="add()" value="添加" />
			        </td>
			    </tr>
			</table>
			<table class="table" style="text-align:center; font-size:10px;" >
			    <tr style="font-weight:bold;">
			        <td>序号</td>
			        <td>快递柜类型</td>
			        <td>免收费时长</td>
			        <td>收费标准</td>
			        <td></td>
			    </tr>
			    <s:iterator id="clist" value="chargingList" status="status">
				    <s:if test="#clist==null">
				    </s:if>		
				    <s:else>	    
					    <tr>
					        <td>${clist.id}</td>
					        <td>${clist.type}</td>
					        <td>${clist.overTime}</td>
					        <td>${clist.cost}</td>
					        <td>
					            <input type="hidden" value="${clist.id}" />
					            <input class="xg" type="button" onclick="update(${clist.id})" value="修改" />
					            <input class="sc" type="button" value="删除" />
					        </td>
					    </tr>
				    </s:else>
				</s:iterator>
			</table>										
		 </div>
		 <!--此处显示分页的首页 上一页 下一页  末页 -->
		 <div align="center">第
			 <font color="red" id="cpage">${cpage}</font> /
			 <font id="total">${total}</font> 页
			 <fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
				styleClass="page" theme="number" />
		</div>
	 <%@include file="/util/foot.jsp"%>
	 <script type="text/javascript"
			src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js">
     </script>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">  
		//新增点击方法
		function add() {
			var url = "<%=request.getContextPath()%>/System/expresscabinet/add_charging.jsp";
			$("#showProcess").attr("src", url);
			chageDiv('block');
		}
		//修改点击方法
		function update(obj) {
			var url = "<%=request.getContextPath()%>/WePayAction_selectCg.action?charging.id="+obj;
			//var url = "<%=request.getContextPath()%>/banCiAction_salBanCiByid.action?tag=${tag}&banCi.id="+obj;
			$("#showProcess").attr("src", url);
			chageDiv('block');
		}
		
		
		  $(function(){	
			  $("#showProcess").attr("src", "");
			  $("#showProcess").attr("src", "");
			  var errorMessage = '${errorMessage}';
			  if (errorMessage != "") {			
				  location.href="WePayAction_selectCharg.action";
			  }
			  //查询快递柜标准类型
			  $(".cxkdglxbz").click(function(){
				  var overTime =  $("#mfsc").val();
			      var cost = $("#sfbz").val();
			      var type = $("#gzlx").val();
			      //if(overTime !=""){
			    	   location.href="WePayAction_selectCharg.action?charging.overTime="+overTime+"&charging.cost="+cost+"&charging.type="+type;
			      //}
			  })
			  //清空所有文本框内容
			  $(".qkxgsfbz").click(function(){
				  var overTime =  $("#mfsc").val("");
			      var cost = $("#sfbz").val("");
			      var type = $("#gzlx").val("");
			  })
			  //新增快递柜类型标准
			  //$(".xzxgsfbz").click(function(){
				 // var overTime =  $("#mfsc").val();
			     // var cost = $("#sfbz").val();
			     // var type = $("#gzlx").val();
			     // if(overTime==null||overTime==""||overTime==undefined||cost==null||cost==""||cost==undefined||type==null||type==""||type==undefined){
				//	  alert("请先添加数据!!!");
				 // }else{
				//	  location.href="WePayAction_insertCharg.action?charging.type="+type+"&charging.overTime="+overTime+"&charging.cost="+cost;
					  
				//  }
			//  });
			  //删除类型标准
			  $(".sc").click(function(){
				  var chargid = $(this).prev().prev().val();
				   location.href="WePayAction_deleteCharg.action?charging.id="+chargid;
			  });
		  });
	    </script>
  </body>
</html>
