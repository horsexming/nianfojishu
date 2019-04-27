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
						 <a href="CircuitRunAction_findAduitPage.action?id=${zhaobiao.epId}" style="color: #ffffff">审核</a>
				</div>
			</div>
			
			<div align="center">
			
					<table class="table" style="width: 100%">
						<tr  bgcolor="#c0dcf2">
							<th>序号</th>
							<th>物料名称</th>
							<th>使用模版</th>
							<th>数量/单位</th>
							<th>规格要求</th>
							
							<th>操作</th>
						</tr>
				<s:iterator value="list" id="zhaobiaoXi"  status="pageIndex">
						<tr   align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb');fun2('${zhaobiaoXi.id}')">
							<td>${pageIndex.index+1}</td>
							<td>${zhaobiaoXi.t6}</td>
							<td>${zhaobiaoXi.zhmoban.name}</td>
							<td>${zhaobiaoXi.t2}/${zhaobiaoXi.t3}</td>
							<td>${zhaobiaoXi.t5}</td>
							
							<td>
							
						
							
						
									<a	href="zhaobiaoAction!chakantoubiaojilu.action?zhaobiaoXi.id=${zhaobiaoXi.id}">查看投标记录</a>
							
							</td>
						</tr>
				</s:iterator>	
				<div id="t1"></div>
				
				<tr>
						<s:if test="errorMessage==null">
							<th colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							
						</s:else>
						</th>
					</tr>	
					</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function fun1(xid,id){
		   // alert(xid);    &zhaobiaoXi.id="+xid
			var huikuang=encodeURI(encodeURI($("#"+id+" option:selected").val()));
			//alert(huikuang);
			url="zhaobiaoAction!quedinghuikuang.action?zhaobiaoXi.t11="+huikuang+"&zhaobiaoXi.id="+xid;
	       	window.location.href=url; 
		}
		function fun2(id){
	      
		}
	  /*  $(function(){
	    $.post("zhaobiaoAction!listhuikuang.action",{},function(msg){
  			$("#info").html(msg);
  			
  			
  				$("#shijian"+idIndex).empty();//清空
						//$("").appendTo(
						//						"#shijian"+idIndex);
						$(object).each(function() {
									$("<option value='"+ [object,deptValue,idIndex]
													   + "'>"
														+ object+
														"</option>")
																	.appendTo(
																			"#shijian"+idIndex)
									});
									ff12($("#shijian"+idIndex).val());
		       	     }
  		});
	    })*/
	</SCRIPT>
	</body>
</html>
