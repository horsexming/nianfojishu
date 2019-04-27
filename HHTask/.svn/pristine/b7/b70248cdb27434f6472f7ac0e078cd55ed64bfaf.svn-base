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
		<script type="text/javascript" src="javascript/jquery-easyui-1.3.1/jquery-1.8.0.min.js"></script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="productPriceAction!findbaojia.action" method="post">
				<table>
				<tr>
				<td>总成件号：<input type="text" name="productPrice.markId" /></td>
				<td>产品名称：<input type="text" name="productPrice.goodsName" /></td>
				<td rowspan="2"><input type="submit" value="查找" /> <input type="button" onclick="chageDiv('block');" value="添加总成信息" /></td>
				</tr>
				<tr>
				<td>产品型别：<input type="text" name="productPrice.style" /></td>
				<td>产品车型：<input type="text" name="productPrice.carStyle" /></td>				
				</tr>
				</table>
				</form>
				<br>
				<form action="productPriceAction!findbaojia.action" method="post" >
				<table width="95%" class="table">  
			  				
			   	<tr align="center" bgcolor="#c0dcf2" style="height: 40px; font-weight: bold;">
			   	<td>序号</td>
			   	<td>总成件号</td>
			   	<td>产品名称</td>   	
			   	<td>入库量</td>
			   	<td>型别</td>
			   	<td>车型</td>
			   	<td>直接人工费</td>
			   	<td>操作</td>
			   	
			   	</tr>
			   	 <s:iterator value="list" status="se" id="sell">
			    <input type="hidden" id="id<s:property value='#se.index+1' />" value=${id} />
				  <tr align="center" onmouseover="chageBgcolor(this)"
					onmouseout="outBgcolor(this,'')">			     
			    	<td><s:property value="#se.index+1" /></td> 
			    	<td><s:property value="markId" /></td>
			    	<td><s:property value="goodsName" /></td>
			    	<td><s:property value="dailyoutput" /></td>
			    	<td><s:property value="style" /></td>
			    	<td><s:property value="carStyle" /></td>  
			    	<td align="left" width="100px;">
			    	<SPAN id="labo<s:property value='#se.index+1' />"><s:property value="laborcost" /></SPAN>
			    	<SPAN id="updatelabo<s:property value='#se.index+1' />"></SPAN>
			    	<span id="inputLabor<s:property value='#se.index+1' />" style="display:none" >
			    	<input type="text"  name="labor" id="inputLaborvalue<s:property value='#se.index+1'/>"  size="10" value="${sell.laborcost}" /></span>
			    	</td>
			    	<td>
			    	<span id="edit<s:property value='#se.index+1' />"><input type="button"  onclick="changeUpdate(<s:property value='#se.index+1' />)" value="编辑"> </span>
			    	<span id="update<s:property value='#se.index+1'/>" style="display:none"><input type="button"  onclick="updateLabor(<s:property value="#se.index+1" />)"  value="修改">		</span>	    	
			    	</td>
			    	</tr>
			    </s:iterator>
			    <tr>
			    <td colspan="8" align="right">			    
			    	 共 <s:property value="total"/> 页 第 <s:property value="cpage"/> 页
			        <fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}" styleClass="page" theme="number"/> 				
			    	
			    	</td></tr>
			    	 
			   </table>
			   </form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
		function changeUpdate(obj){
			document.getElementById("edit"+obj).style.display="none";
			document.getElementById("labo"+obj).style.display="none";
			document.getElementById("updatelabo"+obj).style.display="none";
			document.getElementById("update"+obj).style.display="block";
			document.getElementById("inputLabor"+obj).style.display="block";	
		}
		function updateLabor(obj){
			var price=document.getElementById("inputLaborvalue"+obj).value;
			var id=document.getElementById("id"+obj).value;
			$.ajax({	
			   type: "POST",
			   url: "productPriceAction!updatePrice.action",
			   data: {
					price:price,
					id:id
			   },
			   success: function(msg){			
			     var gong = $.parseJSON(msg);
			     //赋值		      
			   }
			});
			document.getElementById("updatelabo"+obj).innerHTML=price;
			document.getElementById("updatelabo"+obj).style.display="block";			
			document.getElementById("edit"+obj).style.display="block";
			document.getElementById("update"+obj).style.display="none";
			document.getElementById("inputLabor"+obj).style.display="none";	
		}
		
		
	</script>
	</body>
</html>
