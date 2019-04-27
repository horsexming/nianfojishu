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
	<body >
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
	<table class="table" style="width: 50%;">
					<tr><th colspan="2">招标信息</th></tr>
					<tr><th align="right">招标题目:</th>
						<td>${zhaobiao.title}</td>
					</tr>
					<tr>
						<th align="right">招标负责人:</th>
						<td>${zhaobiao.fuze}</td>
					</tr>
					<tr>
						<th align="right">负责人电话:</th>
						<td>${zhaobiao.phone}</td>
					</tr>
					<tr>
						<th align="right">开始时间:</th>
						<td>${zhaobiao.moban}</td>
					</tr>
					<tr>
						<th align="right">结束时间:</th>
						<td>${zhaobiao.kongxian}</td>
					</tr>
					<tr>
						<th align="right">招商简介:</th>
						<td colspan="2" height="100px">${zhaobiao.loc}</td>
					</tr>
				</table>
				<!--               -->
				<br />
				<br />
				<table class="table" style="width: 100%">
					<tr bgcolor="#c0dcf2">
						<th>
							ID
						</th>
						<th>
							物料名称
						</th>
						<th>
							使用模版
						</th>
						<th>
							数量/单位
						</th>
						<th>
							规格要求
						</th>
						
						<th>
							操作
						</th>
					</tr >
					<s:iterator value="list" id="zhaobiaoXi" status="pageIndex">
						<tr  align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
							<td>
								${pageIndex.index+1}
							</td>
							<td>
								${zhaobiaoXi.t6}
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
								<a href="zhaobiaoAction!qutoubiao.action?zhaobiaoXi.id=${zhaobiaoXi.id}">去投标</a>|
								<a href="javascript:fun1('${zhaobiaoXi.id}');">查看投标记录</a>
							</td>
						</tr>
					</s:iterator>
				</table>
					<div id="t1"></div>
				
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
			 function fun1(id){
			 //alert(id);
	     	 	$.getJSON("zhaobiaoAction!listXitoubiao.action?zhaobiaoXi.id="+encodeURI(id),{},function(msg){
				var resHtml = "<table class='table' id='t2'>";
				resHtml = resHtml + "<tr align='center'>"
							+"&nbsp;&nbsp;&nbsp;"
							+"<td>投标公司</td>"
							+"<td>到货时间</td>"
							+"<td>负责人</td>"
							+"<td>联系方式</td>"
							+"<td>含税价</td>"
							+"<td>备注</td>"
							+"<td>操作</td></tr>";
				$.each(msg, function(index, data){
						resHtml = resHtml + "<tr align='center'>"
								         +"<td>"+ data.tname
						            
						            +"</td><td>"+ data.ttime
						            +"</td><td>"+ data.tkong4
						            +"</td><td>"+ data.tkong5
						             +"</td><td>"+ data.tkong1
						            +"</td><td>"+ data.tshuliang
						            +"</td><td><a href='zhaobiaoAction!toupdatetoubiao.action?zhtoubiao.tid="+data.tid +"'>修改</a></td></tr>";
				});
				
				resHtml = resHtml + "<table>";
				
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
