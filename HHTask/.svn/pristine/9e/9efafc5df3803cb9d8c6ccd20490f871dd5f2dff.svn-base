<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
body {
	text-align: center;
}
</style>

		<%@include file="/util/sonHead.jsp"%>
	</head>
	<%
		Users user = (Users) session.getAttribute("Users");
	%>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
				</div>
			</div>

			<div align="center">
				<h5>
					产品持续改进项目质量风险评估单
				</h5>
				<!-- kvpAssessAction_addKVPAssess.action -->
				<form id=testFrom  action="" method="post"  enctype="multipart/form-data" >
					<table style="width: 98%;" class="table" id="complexselectedlist">
						<tbody>
						<tr>
							<th colspan="6"  >
									风险评估添加
							</th>
							</tr>
							<tr >
							<th rowspan="2" >
								产品信息
								</th>
								<th>
								零件名称/项目名
								</th>
								<th align="left">
									<input type="text" name="kvpAssess.part_name"> 
								</th>
								<th>
								零件号/项目号
								</th>
								<th align="left">
									<input type="text" name="kvpAssess.part_number"> 
								</th>
								<th>
								</th>
							</tr>
							<tr >
								<th>
								工序名/项目描述
								</th>
								<th colspan="3" align="left">
									<input type="text" name="kvpAssess.process_name" style="width: 500px;"> 
								</th>
								<th></th>
							</tr>
							<tr>
								<th id="change" rowspan="5">
									持续改进问题描述
								</th>
							</tr>
							<tr>
								<th colspan="2" >
									改进前问题	 
<%--									<input id="btn1" type="button"  value="添加" onclick="addImprove1(this)">--%>
								</th>
								<th colspan="2" >
									改进后问题	&nbsp;&nbsp;&nbsp;&nbsp;
<%--									<input id="btn2" type="button" value="添加" onclick="addImprove2(this)">--%>
								</th>
								<th>
									质量风险评估
								</th>
							</tr>
							<tr>
							<th colspan="2" >
<%--									<textarea rows="2" cols="50" name="improveKVPList[0].improved_beforeproblems"></textarea>--%>
								<div style="position: relative;left: 0px;top: 0px;">
												<textarea id="content"  name="kvpAssess.improved_beforeproblems" cols="10" rows="4"  style="width:350px;height:100px;visibility:hidden;"></textarea>	
										</div>
							</th>
							<th colspan="2" >
<%--									<textarea rows="2" cols="50" name="improveKVPList[0].improved_endproblems"></textarea>--%>
										<div style="position: relative;left: 0px;top: 0px;">
												<textarea id="content1" name="kvpAssess.improved_endproblems" cols="10" rows="4"  style="width:350px;height:100px;visibility:hidden;"></textarea>	
										</div>
							</th>
							<th>
							<input type="text" name="kvpAssess.quality_assessment">
							</th>
							</tr>
							<tr>
							<th colspan="5"> </th>
							</tr>
<%--							<tr id="uploadtr">--%>
<%--							<th colspan="2">--%>
<%--							<input id="signature_address1"  type="file"  name="improveK.beforeimage_Path" />--%>
<%--							</th>--%>
<%--							<th colspan="2">--%>
<%--								<input id="signature_address"  type="file"  name="improveK.afterimage_Path" />--%>
<%--							</th>--%>
<%--							<th></th>--%>
<%--						</tr>--%>
						<tr>
							<th>
							评估结论
							</th>
							<th colspan="3" >
							是否可以开展持续改进
							</th>
							<th>
								 <input type="radio"  id="aaa" name="kvpAssess.assessment_findings" value="是"  checked="checked"/>
									<label for="aaa">
										是
									</label>
									<input type="radio"  id="bbb"  name="kvpAssess.assessment_findings"  value="否" />
									<label for="bbb">
										否
									</label>
							</th>
						</tr>
						<tr id="button1">
								<td colspan="6" align="center">
									<input id="reviewButton" name="reviewButton" type="button" value="下一步"
										style="width: 60px; height: 40px;" align="top">
									&nbsp;&nbsp;
									<input type="reset" value="重置"
										style="width: 60px; height: 40px;" align="top">
								</td>
							</tr>
					
						<tbody>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
	</body>
	<script type="text/javascript">
$(function() {
	var successMessage = "${successMessage}";
	if (successMessage != "") {
		alert(successMessage);
		parent.location.reload(true);//刷新父页面
	}
})

var lineCount = 1;
var begAddLineNum = 1;//物品
var begAddLineNum1 = 8;//议价
var begAddLineNum2 = 10;//公司

function addImprove1(){
	var _tbody = document.getElementById("complexselectedlist").tBodies[0];//获得第一个tbody
	var uploadtr = document.getElementById("uploadtr");//将要在该Tr之前添加元素
	var _tr = document.createElement("tr");
	_tr.setAttribute('align', 'center');
	_tbody.insertBefore(_tr, uploadtr);
	begAddLineNum++;
	var x = _tr.insertCell(0);
	x.setAttribute('colspan',2)
	x.innerHTML = "<div id='image1_"+lineCount+"'><textarea rows='2' cols='50' name='improveKVPList["+lineCount+"].improved_beforeproblems'></textarea><a onclick='moveTr(this)'>删除</a></div>";
	var x1 = _tr.insertCell(1);
	x1.setAttribute('colspan',2)
	x1.innerHTML = "<div id='image2_"+lineCount+"'><textarea rows='2' cols='50' name='improveKVPList["+lineCount+"].improved_endproblems'></textarea><a onclick='moveTr(this)'>删除</a></div>";
	var x2 = _tr.insertCell(2);
	x2.innerHTML = "";
	lineCount++;
	var s=document.getElementById("change").attributes.getNamedItem("rowspan").nodeValue;
	 s++;
	$("#change").attr("rowspan",s);
}
 

//删除
function moveTr(obj) {
	var total = 0;
	var selectedTr = obj.parentNode.parentNode;
	var tbody = selectedTr.parentNode;
	tbody.removeChild(selectedTr);
	var s=document.getElementById("change").attributes.getNamedItem("rowspan").nodeValue;
	s--;
	$("#change").attr("rowspan",s);
}

$(function(){
	var self;
	var self2;
	function submit(){
		self.sync();
		self2.sync();
		var content=$('#content').val();
		var content1=$('#content1').val();
		var isNull=/^[\s]*$/;
		if(isNull.test(content)){
			alert('请填写内容！');
			return;
		}
		$.ajax({
							type: "POST",
							url: "kvpAssessAction_addKVPAssess.action",
							data: $('#testFrom').serialize(),
							dataType : "json",
							success: function(data){
								 if(data.success){
								 	//window.location.href="<%=path%>/System/tvp/addtvpAssess.jsp";
								 	//window.location.href="<%=path%>/kvpAssessAction_findExamList.action";
								 	//window.location.href="<%=path%>/kvpAssessAction_findKVPAssess.action?test=self";
								 	window.location.href="<%=path%>/kvpAssessAction_addExecuteKVP.action?id="+data.data+"&test=self";
								 }else{
									 alert("数据异常!");
								 }
							}
						});
				 
		
		
	}
	KindEditor.ready(function(K) {
		var editor1 = K.create('#content', {
			cssPath : '${pageContext.request.contextPath}/javascript/kindeditor-master/plugins/code/prettify.css',
			width : '550px',
			height: '200px',
			minWidth : 400,
			minHeight: 100,
			uploadJson : 'affixAction!upload1.action',
            allowFileManager : false,
			items : [ 'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', '|', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold','italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'emoticons', 'image'],
			afterCreate : function() {
				self = this;
			}
		});
	});
	KindEditor.ready(function(K) {
		var editor1 = K.create('#content1', {
			cssPath : '${pageContext.request.contextPath}/javascript/kindeditor-master/plugins/code/prettify.css',
			width : '550px',
			height: '200px',
			minWidth : 400,
			minHeight: 100,
			uploadJson : 'affixAction!upload1.action',
            allowFileManager : false,
			items : [ 'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', '|', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold','italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'emoticons', 'image'],
			afterCreate : function() {
				self2 = this;
			}
		});
	});
	$('#reviewButton').bind('click', function(){
		submit();
	});
	
});
</script>

</html>
