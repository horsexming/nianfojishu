<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/util/sonHead.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/ajaxfileupload/ajaxfileupload.js"></script>
<script type="text/javascript">

	function ajaxFileUpload(){
        
		$("#loading").ajaxStart(function(){
            $(this).show();//开始上传文件时显示一个图片
        }).ajaxComplete(function(){
            $(this).hide();//文件上传完成将图片隐藏起来
        });
        
        $.ajaxFileUpload({
                url:'tclaimsRecord_fileUpload.action',//用于文件上传的服务器端请求地址
                secureuri:false,//一般设置为false
                fileElementId:'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
                dataType: 'json',//返回值类型 一般设置为json
                success: function (data, status) {//服务器成功响应处理函数
                    //alert(data.message);从服务器返回的json中取出message中的数据,其中message为在struts2中action中定义的成员变量
                    $('#fileInput').val(data.data);
                    
                },
                error: function (data, status, e) { //服务器响应失败处理函数
                    alert(e);
                }
            }
        )
        return false;
    }
	$(function(){
		$('#allCheckBox').bind('click', function(){
			if($('#allCheckBox').attr('checked')) {
				$('#mytable input[name="ids"]').attr('checked',$('#allCheckBox').attr('checked'));	
			} else {
				$('#mytable input[name="ids"]').removeAttr('checked');
			}
		});
		
		$('#submitBtn').bind('click', function(){
			if( $('#mytable input[name="ids"]:checked').length <= 0){
				alert("至少要选中一项进行分析!");
				return;
			}
			ajaxFileUpload();
			$.ajax({
				type: "POST",
				url: "tclaimsRecord_update.action",
				data: $('#myForm').serialize(),
				dataType: "json",
				success: function(msg){
					if(msg.success){
						$('#mytable input[name="ids"]:checked').each(function(){  
							$(this).attr("disabled","disabled");  
							$(this).removeAttr('checked');
							var font = $($(this).parent().parent().find("font")[0]);
							font.html("已分析");
							font.attr("color","green");
							$('#mytextarea').val('');
						});
					}
					alert(msg.message);
				}
			});
		});
	})
</script>
</head>
<body>
	<%@include file="/util/sonTop.jsp"%>
	<div id="gongneng" style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
		<div id="xitong" style="width: 100%; font-weight: bold; height: 50px; " align="left">
			<div style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;" align="left">
				
			</div>
			<div style="float: left; width: 45%; padding-top: 5px;" align="right">
				<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}" style="color: #ffffff">刷新</a>
			</div>
		</div>
		
		<div align="center">
			<form id="myForm">
				<table id="mytable" width="100%" border="0" style="border-collapse: collapse;" class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center"> <input id="allCheckBox" type="checkbox" /> </th>
						<th align="center">生产日期</th>
						<th align="center">件号</th>
						<th align="center">名称</th>
						<th align="center">数量</th>
						<th align="center">故障描述</th>
						<th align="center">对方原因分析</th>
						<th align="center">状态</th>
					</tr>
					<s:iterator value="tclaimsRecords" id="r">
						<tr onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'#e6f3fb')">
							<td align="center">
								<s:if test="#r.status == null">
									<input name="ids" type="checkbox" value="${r.id}" />
								</s:if><s:else>
									<input disabled="disabled" type="checkbox" />
								</s:else>
							</td>
							<td align="center">${r.productionDate}</td>
							<td align="center">${r.partNumber}</td>
							<td align="center">${r.name}</td>
							<td align="center">${r.quantity}</td>
							<td align="center">${r.description}</td>
							<td align="center">${r.cause}</td>
							<td align="center">
								<s:if test="#r.status == null">
									<font color="red">未分析</font>
								</s:if><s:else>
									<font color="green">${r.status}</font>
								</s:else>
							</td>
						</tr>
					</s:iterator>
					<tr>
						<td align="center" colspan="2">原因分析</td>
						<td align="center" colspan="3">
							<textarea id="mytextarea" name="tclaimsRecord.reason" rows="5" cols="40"></textarea><br/>
							<img src="javascript/ajaxfileupload/loading.gif" id="loading" style="display: none;">
							<input type="hidden" id="fileInput" name="tclaimsRecord.reasonFilename" />
							<input type="file" id="file" name="attachment" />
							
						</td>
						<td align="center">责任归属</td>
						<td colspan="2" align="center">
							<select name="tclaimsRecord.responsibility">
								<option value="我方责任">我方责任</option>
								<option value="供应商责任">供应商责任</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="8">
							<input id="submitBtn" type="button" value="确定" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<br>
	</div>
	<%@include file="/util/foot.jsp"%>
	</center>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
</body>
</html>
