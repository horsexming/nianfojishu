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
	<body onload="getDept('dept');">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<table class="table" width="40%">
					<tr>
						<th colspan="2">
							招商信息
						</th>
					</tr>
					<tr>
						<td width="25%">
							招标题目
						</td>
						<td width="70%">
							${zhaobiao.title}
						</td>
					</tr>

					<tr>
						<td>
							招标负责人:
						</td>
						<td>
							${zhaobiao.fuze}
						</td>
					</tr>
					<tr>
						<td>
							负责人电话:
						</td>
						<td>
							${zhaobiao.phone}
						</td>
					</tr>
					<tr>
						<td>
							开始时间:
						</td>
						<td>
							${zhaobiao.moban}
						</td>
					</tr>
					<tr>
						<td>
							结束时间:
						</td>
						<td>
							${zhaobiao.kongxian}
						</td>
					</tr>

					<tr>
						<td>
							招商简介:
						</td>
						<td>
							<tr>
								<td colspan="2" width="100%" height="100px">
									${zhaobiao.loc}
									</textarea>
				</table>
				<!--               -->
				<br />
				<br />
				<table class="table" style="width: 100%">
					<tr>
						<td>
							ID
						</td>
						<td>
							使用模版
						</td>
						<td>
							数量/单位
						</td>
						<td>
							规格要求
						</td>
						<td>
							物料介绍
						</td>
					</tr>
					<s:iterator value="list" id="zhaobiaoXi" status="pageIndex" >
						<tr >
							<td>
								${pageIndex.index+1}
							</td>
							<td>
								${zhaobiaoXi.zhmoban.name}
							</td>
							<td>
								${zhaobiaoXi.t2}/${zhaobiaoXi.t3}
							</td>
							<td>
								${zhaobiaoXi.t5}
							</td>
							<td>
								${zhaobiaoXi.t6}
								<input type="hidden" value="${zhaobiaoXi.id}" id="zhaobiaoXiid"/>
							</td>
						</tr>
						<tr><td colspan="6">
						
						
						<!--  -->
						<table class="table">
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						    <td>ID</td>
							<td>投标公司</td>
							<td>到货时间</td>
							<td>联系方式</td>
							<td>负责人</td>
							<td>状态</td>
							<td>备注</td>
						</tr>
					<s:iterator value="#zhaobiaoXi.zhtoubiaos" id="zhtoubiao" status="pageIndex">
				
						<tr id="r1">
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>${pageIndex.index+1} <input type="hidden" value="${zhtoubiao.tkong10}" id="zhtoubiaotid"/></td>
							<td>${zhtoubiao.tname}</td>
							<td>${zhtoubiao.ttime}</td>
							<td>${zhtoubiao.tkong4}</td>
							<td>${zhtoubiao.tkong5}</td>
							
							<td><s:if test='#zhtoubiao.tkong7=="Y"'>中标</s:if>
								<s:if test='#zhtoubiao.tkong7=="N"'>未中标</s:if>
								<s:if test='#zhtoubiao.tkong7=="S"'>投标超出预算</s:if>
							</td>
							<td>${zhtoubiao.tshuliang}</td>
						</tr>
							<s:if test='#zhtoubiao.tkong4=="Y"'></s:if>
					</s:iterator>
					</table>
					<tr><td char="7">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
					</td></tr>
					</s:iterator>
				</table>
				<!-- 	<a class="input" href="CircuitRunAction_findAduitPage.action?id=${zhaobiao.epId}">审批</a>
				<input type="submit" value="审核" class="input" onclick="CircuitRunAction_findAduitPage.action?id=${zhaobiao.epId}" /> -->
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function f1(){
		     var xid=document.getElementById("zhaobiaoXiid").value;
		     var tid=document.getElementById("zhtoubiaotid").value;
		     alert(xid);
		     alert(tid);
				if(xid=tid){
					t1.style.display="block";
				}else{	
					t1.style.display="none";
				}		     
			 function fun1(){
			   var id =document.getElementById("id").value;
			   alert(id)
	     	 	$.getJSON("zhaobiaoAction!listXitoubiao.action?zhaobiaoXi.id="+encodeURI(id),{},function(msg){
				var resHtml = "<table class='table' id='t2'>";
				resHtml = resHtml + "<tr align='center'>"
							+"&nbsp;&nbsp;&nbsp;<td>ID</td>"
							+"<td>投标公司</td>"
							+"<td>物质名称</td>"
							+"<td>到货时间</td>"
							+"<td>联系方式</td>"
							+"<td>负责人</td>"
							+"<td>30天回款</td>"
							+"<td>备注</td>"
							+"<td>操作</td></tr>";
				
				$.each(msg, function(index, data){
						resHtml = resHtml + "<tr align='center'>"
								         +"<td>"+ data.tid 
						            +"</td><td>"+ data.tname 
						            +"</td><td>"+ data.tkong6
						            
						            +"</td><td>"+ data.ttime
						            +"</td><td>"+ data.tkong4
						            +"</td><td>"+ data.tkong5
						            +"</td><td>"+ data.tkong1
						            +"</td><td>"+ data.tshuliang
						            +"</td><td><a href='zhaobiaoAction!toupdatetoubiao.action?zhtoubiao.tid="+data.tid +"'>修改</a></td></tr>";
				});
				
				resHtml = resHtml + "<tr></td colspan='7'><td></tr><table>";
				
				$("#t1").html(resHtml);
			});
	     }
	     function fun2(){
	        var t2=document.getElementById("t2");
			if(t2.style.display=="none") {
				t2.style.display="block";
			} else {
				t2.style.display="none";
			}
	     }
	</SCRIPT>
	</body>
</html>
