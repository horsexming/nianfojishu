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
	<SCRIPT type="text/javascript">
var n=0;
var number=${size+1};
function addlin(){
	//添加一行
	var tab = document.getElementById("mytable");
	var index = tab.rows.length;
	var newTr = tab.insertRow(index-2);
	newTr.id = 'tr_' +n;
	newTr.name="test_tr"
	//添加列 
			newTr.align = "center";
			
			var newtd0='<td>'+number+'</td>'
			var newtd1='<td ><input type="text" name="djnrList_nr"/></td>'
			var newtd2='<td>' +
			'<input type=button onclick=dellin('+n+') value=删除></td>'
			$(newtd0).appendTo(newTr);
			$(newtd1).appendTo(newTr);
			$(newtd2).appendTo(newTr);
	number++;
	n++;
}
function dellin(obj){
	var table = document.getElementById("mytable");
	obj = 'tr_' + obj;
	table.deleteRow($("#" + obj).index());
}
function jiaoyan(){
	var djnrList=document.getElementsByName("djnrList_nr");
	var bool=false;
	fuzhi();
	if(djnrList!=null && djnrList.length>0){
		for(var i=0;i<djnrList.length;i++){
			if(djnrList[i].value==""){
				bool=true;
			}
		}
	}
	if(bool){
		alert('请至少填写一项点检内容')
		return false;
	}
	
	document.getElementById("sub").disabled="disabled";
	return true;
}
function tijiao(){
	var bool = jiaoyan();
	if(bool){
		$.ajax( {
			type : "POST",
			url : "ProdEquipmentAction!bddjnr.action",
			dataType : "json",
			data : $("#submit").serialize(),
			async : false,
			success : function(data) {
				alert(data)
				if(data=="绑定成功"){
					window.location.reload();
					return true;
				}
				}
			})
	}
}

function fuzhi(){
	var djnrList=document.getElementsByName("djnrList_nr");
	if(djnrList!=null && djnrList.length>0){
		for(var i=0;i<djnrList.length;i++){
			if(djnrList[i].value!=""){
				$("#mydiv").append('<input type="hidden" value="'+djnrList[i].value+'" name="djnrList['+ i +'].nr">')
			}
		}
	}
}
function delbd(id){
	if(confirm("确定要删除吗？")){
	$.ajax({
		 type:"POST", 
  		url:"DJNRAction_delbd.action",
  		data:{'djnr.id':id,pageStatus:'bd'},
  		dataType:"json",
		success:function(data){
  			alert(data)
  			if(data=="删除成功!"){
  				window.location.reload();
  			}
		}
		
	})
	}
}
	
	</SCRIPT>
	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在设备点检内容进行操作</span>
						</td>
						<td align="right" >
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
						
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			
		
			<div align="center">
				<h3>设备点检内容</h3>
			
			<form action="" id="submit" method="post" onsubmit="return tijiao()">
				
				<table class="table" id="mytable">
					<tr align="center" >
<%--						<td>--%>
<%--							<input type="checkbox"  onclick="checkAll()" id="allchecks" > 全选--%>
<%--						</td>--%>
						<td>
							序号
						</td>
						<td>
							点检内容
						</td>
						<td>
							操作
						</td>
					</tr>
					<s:iterator id="djnrtest" value="djnrList" status="statussdf">
   					<s:if test="#statussdf.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
					</s:else>
<%--					<td>--%>
<%--						<input type="checkbox" id="${djnrtest.id}" value="${djnrtest.nr}" name="checkboxs"/>选择--%>
<%--					</td>--%>
					<td>
						<s:property value="#statussdf.index+1" />
					</td>
					<td>
						${djnrtest.nr}
					</td>
					<td>
						<a href="javascript:;" onclick="delbd(${djnrtest.id})">删除</a>
					</td>
					</s:iterator>
					</tr>
					<tr>
						<td align="center">
						</td>
						<td align="center">
						</td>
						<td align="center">
							<input type="hidden" value="${size}" id="hid"/>
							<input type="button"  value="添加" onclick="addlin()"/>
						</td>
					</tr>
					
					 <tr>
				<td colspan="3" align="right">
								第
					<font color="red"><s:property value="cpage" /> </font> /
						<s:property value="total" />
							页
						<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
									
				</td>
			</tr>
				</table>
				<tr>
				<td colspan="3" align="center" >
					<input type="hidden" value="${param.id}" name="machine.id">
					<input type="submit" value="绑定" style="width: 80px;height: 35px" id="sub" >
					
				</td>
			</tr>
			<div id="mydiv"> </div>
			</form>
			<input type="hidden" value="${errorMessage}" id="rebeack"/>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">




		</script>
	</body>
</html>
