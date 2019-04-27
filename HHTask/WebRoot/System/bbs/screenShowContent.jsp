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
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/jquery-1.8.3.js">
</script>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<%--					<a--%>
					<%--						href="customerOpinionAction_showList.action"--%>
					<%--						style="color: #ffffff">刷新<br/>(reflesh)</a>--%>
				</div>
			</div>
			<%--			<div align="center">--%>
			<%----%>
			<%--				<form action="ScreenContentAction_add.action" method="post" style="">--%>
			<%--					<br>--%>
			<%--					<table border="0" width="100%" class="table">--%>
			<%--						<tr>--%>
			<%--							<td align="right">--%>
			<%--								屏幕内容名称:--%>
			<%--							</td>--%>
			<%--							<td>--%>
			<%--								<input type="text" id="name" name="screencontent.name" />--%>
			<%--							</td>--%>
			<%--							<td align="right">--%>
			<%--								添加屏幕内容:--%>
			<%--							</td>--%>
			<%--							<td>--%>
			<%--								<input type="text" id="Url" name="screencontent.screenUrl" />--%>
			<%--							</td>--%>
			<%--						</tr>--%>
			<%--						<tr>--%>
			<%--							<td align="center" colspan="4">--%>
			<%--								<input type="submit" value="添加submit" onclick="reload()"--%>
			<%--									style="width: 100px; height: 50px;" />--%>
			<%--								<input type="reset" value="重置reset"--%>
			<%--									style="width: 100px; height: 50px;" />--%>
			<%--							</td>--%>
			<%--						</tr>--%>
			<%--					</table>--%>
			<%--				</form>--%>
			<%--			</div>--%>

			<div align="center">
				<h3>
					屏幕内容
					<br />
					(Screen Content)
				</h3>
				<form id="myform">
					<input type="hidden" name="screen.id" value="${screen.id}" />
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">

							<th align="center">
								序号
							</th>
							<th align="center">
								名称
							</th>
							<th align="center">
								绑定|全选
								<input type="checkbox" id="all">

							</th>
						</tr>
						<s:iterator value="screencontentList" id="pageList"
							status="pageIndex">
							<s:if test="#pageIndex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td align="center">
								${pageIndex.index+1}
							</td>
							<td>
								${pageList.name}
							</td>
							<td id="list">
								<input type="checkbox" value="${pageList.id}" name="checkbox"
									id="checkbox${pageList.id}" />

							</td>
						</s:iterator>

						<s:if test="successMessage!=null">
							<tr>
								<td colspan="11" align="center" style="color: red">
									${successMessage}

								</td>
							</tr>
						</s:if>
						<tr>

							<td align="center" colspan="3">
								<input id="submitBtn" type="button" value="绑定"
									style="width: 100px; height: 50px;" />

							</td>
							<td id="test"></td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

		<script type="text/javascript">
$(function(){
	
					$.ajax({
						type: "POST",
							url: "screen_showbindscreencontentbyid.action?screen.id=${screen.id}",
							data: {
								id : ${screen.id}
							},
							dataType : "json",
							success: function(obj){
								for(var i=0;i<obj.length;i++)
									{
										var checkbox=document.getElementById("checkbox"+obj[i]);
										  checkbox.checked = true;
									}
							},
							 error: function (XMLHttpRequest, textStatus, errorThrown) {
								alert(2);
               				}   
						});
				});
$('#submitBtn').bind('click', function() {
	submit();
});
function chexkbox1() {
	var chexboxs = document.getElementsByName("checkbox");
	var index = 0;
	for ( var i = 0; i < chexboxs.length; i++) {
		if (chexboxs[i].checked == true) {
			$("#test").append(
			 '<input type="hidden" value="' + chexboxs[i].value
			 	 	+ '" name="screen.screencontentList[' + index++
				 	+ '].id">');
		}
	}
}
function submit() {
	chexkbox1();
	$.ajax( {
		type : "POST",
		url : "screen_addscreencontent.action",
		data : $('#myform').serialize(),
		dataType : "json",
		success : function(json) {
			alert(json.message);
		}
	});
}

	
$("#all").click(function(){   
    if(this.checked){   
        $("#list :checkbox,#all").prop("checked", true);  
    }else{   
		$("#list :checkbox,#all").prop("checked", false);
    }   
});

</script>

		<script type="text/javascript">
</script>
	</body>
</html>
