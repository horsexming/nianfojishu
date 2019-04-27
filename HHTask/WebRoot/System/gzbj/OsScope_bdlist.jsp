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
			var newtd1='<td> <select name="type" style="width:150px " > <option>手动填写</option> <option>OKorNo</option> </select> </td> '
			var newtd2='<td><input type="text" name="content"></td>'
			var newtd3='<td><input type="text" name="zltz"></td>'
			var newtd4='<td><input type="text" name="jcff"></td>'
			var newtd5='<td></td>'
			var newtd6='<td>' +
			'<input type=button onclick=dellin('+n+') value=删除></td>'
			$(newtd0).appendTo(newTr);	
			$(newtd1).appendTo(newTr);	
			$(newtd2).appendTo(newTr);	
			$(newtd3).appendTo(newTr);	
			$(newtd4).appendTo(newTr);	
			$(newtd5).appendTo(newTr);	
			$(newtd6).appendTo(newTr);	
	number++;
	n++;
}
function dellin(obj){
	var table = document.getElementById("mytable");
	obj = 'tr_' + obj;
	table.deleteRow($("#" + obj).index());
}
function jiaoyan(){
	var content=document.getElementsByName("content");
	var bool=false;
	fuzhi();
	if(content!=null && content.length>0){
		for(var i=0;i<content.length;i++){
			if(content[i].value==""){
				bool=true;
			}
		}
	}
	if(bool){
		alert('请至少填写一项质检内容')
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
			url : "processGzstoreAction_bdOsScope.action",
			dataType : "json",
			data : $("#submit").serialize(),
			async : false,
			success : function(data) {
				alert(data)
				if(data=="绑定成功"){
					//parent.chageDiv('none')
					
					window.location.reload();
				}
				}
			})
	}
}

function fuzhi(){
	var content=document.getElementsByName("content");
	var type=document.getElementsByName("type");
	var zltz=document.getElementsByName("zltz");
	var jcff=document.getElementsByName("jcff");
	if(content!=null && content.length>0){
		for(var i=0;i<content.length;i++){
			if(content[i].value!=""){
				$("#mydiv").append('<input type="hidden" value="'+content[i].value+'" name="osList['+ i +'].content">')
			}
		}
	}
	if(type!=null && type.length>0){
		for(var i=0;i<content.length;i++){
			if(type[i].value!=""){
				$("#mydiv").append('<input type="hidden" value="'+type[i].value+'" name="osList['+ i +'].type">')
			}
		}
	}
	if(jcff!=null && jcff.length>0){
		for(var i=0;i<jcff.length;i++){
			if(jcff[i].value!=""){
				$("#mydiv").append('<input type="hidden" value="'+jcff[i].value+'" name="osList['+ i +'].jcff">')
			}
		}
	}
	if(zltz!=null && zltz.length>0){
		for(var i=0;i<zltz.length;i++){
			if(zltz[i].value!=""){
				$("#mydiv").append('<input type="hidden" value="'+zltz[i].value+'" name="osList['+ i +'].zltz">')
			}
		}
	}
}
function delbd(processid,osId){
if(confirm('确定要删除吗？')){
	

	$.ajax({
		 type:"POST", 
  		url:"processGzstoreAction_delOsScope.action",
  		data:{'processGzstore.id':processid,id:osId},
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

function shuaxin(processName){
	document.getElementById("ziti").innerHTML="同步数据中,请勿关闭";
	document.getElementById("but").disabled="disabled";
		$.ajax( {
		type : "POST",
		url : "processGzstoreAction_shuaxin.action",
		data : {processName:processName},
		dataType : "json",
		success : function(data) {
				if(data == "更新成功"){
					alert(data);
					document.getElementById("ziti").innerHTML=""
					window.location.reload();
				}
		}
	})
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
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<center>
					<table style="width: 100%">
						<tr>
							<td>

							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none')">
							</td>
						</tr>
					</table>
				</center>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>

		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
					检查内容
				</h3>
				<font size="5" color="red"><span id="ziti"></span>
				</font>
				<form action="" id="submit" method="post" onsubmit="return tijiao()">
					<input type="button" value="更新质检模板"
						onclick="shuaxin('${processGzstore.processName }')" id="but" />
					<STRONG>常用符号: Φ ± ° ≤ ≥ ℃ < > № ⊥ ◎ ○
						&nbsp;&nbsp;&nbsp;&nbsp; </STRONG>
					<table class="table" id="mytable">
						<tr align="center">
							<td>
								序号
							</td>
							<td>
								检查类型
							</td>
							<td>
								检查内容
							</td>
							<td>
								质量特征
							</td>
							<td>
								检查方法
							</td>
							<td>
								操作
							</td>
							<td>
							</td>

						</tr>
						<s:iterator id="ostest" value="osList" status="statussdf">
							<s:if test="#statussdf.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#statussdf.index+1" />
							</td>
							<td align="left">
								${ostest.type}
							</td>
							<td align="left">
								${ostest.content}
							</td>
							<td align="left">
								${ostest.zltz}
							</td>
							<td align="left">
								${ostest.jcff}
							</td>
							<td align="left">
								<a href="javascript:;" onclick="toUpdateScope(${ostest.id})">修改</a>/
								<a href="javascript:;"
									onclick="delbd('${param.id}','${ostest.id}')">删除</a>
							</td>
							<td>
							</td>
						</s:iterator>
						</tr>
						<tr>
							<td align="center">
							</td>
							<td align="center">
							</td>
							<td align="center">
							</td>
							<td align="center">
							</td>
							<td align="center">
							</td>
							<td align="center">
							</td>
							<td align="center">
								<input type="hidden" value="${size}" id="hid" />
								<input type="button" value="添加" onclick="addlin()" />
							</td>
							<tr>
								<td colspan="7" align="right">
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
						<td colspan="7" align="center">
							<input type="hidden" value="${id}" name="id" id="hid">
							<input type="button" value="绑定" style="width: 80px; height: 35px"
								id="sub" onclick="tijiao()">

						</td>
					</tr>
					<div id="mydiv">
					</div>
				</form>
				<input type="hidden" value="${errorMessage}" id="rebeack" />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function toUpdateScope(id) {
<%--	var url = encodeURI(encodeURI("OsTemplate_toUpdateOsScope.action?tt.id="--%>
<%--			+ id));--%>
	$("#showProcess").attr("src", "OsTemplate_toUpdateOsScope.action?tt.id="+id);
	chageDiv('block');
}



		</script>
	</body>
</html>
