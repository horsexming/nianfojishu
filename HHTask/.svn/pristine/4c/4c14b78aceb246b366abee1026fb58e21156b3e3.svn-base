<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
			src="<%=basePath%>javascript/DatePicker/WdatePicker.js">
</script>
		<%@include file="/util/sonHead.jsp"%>

		<base href="<%=basePath%>">
		<title>My JSP 'store_return.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<body>
		<p>&nbsp;</p>
		<center><font  style="font-weight: bold;font-size:24px;">库存报废清单</font></center>
		<hr />
		<center>
			<form action="" method="post"
				 id="submit">
				<table align="center" border="1" style="border-collapse: collapse;">
					<tr>
						<td >
							卡号：
						
							<input type="text" name="scrap.cardNum" value="${lend.cardNum}"
								readonly="readonly" />
							<input type="hidden" name="scrap.goodsId"  value="${lend.goodsId}"> 
							<input type="hidden" name="scrap.lendId"  value="${lend.id}">
							<span style="color: red;">${errorMessage}</span>
						</td>
						<td>
							姓名：
							<input type="text" name="scrap.username" value="${lend.peopleName }"
								readonly="readonly" />
							
						</td>
						<td>
							部门：
							<input type="text" name="scrap.dept" value="${lend.dept }"
								readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td>
							编号：
							<input type="text" name="scrap.goodsMarkId" value="${lend.goodsMarkId}"
								readonly="readonly" />
						</td>
						<td>
							批次：
							<input type="text" name="scrap.goodsLotId" value="${lend.goodsLotId}"
								readonly="readonly" />
<%--							<input type="text" name="scrap.badDate" class="Wdate" id="time"--%>
<%--								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />--%>
							
						</td>
						<td>
							名称：
							<input type="text" name="scrap.goodsFullName" value="${lend.goodsFullName}"
								readonly="readonly" />
						</td>
						
					</tr>
					<tr>
						<td>
							
							数量：
							<input type="text" name="scrap.badNum" id="num"   value="${lend.giveBackNum}"/>
						</td>
						<td>
							单位：
							<input type="text" name="scrap.unit" value="${lend.unit}"
								readonly="readonly" />
						</td>
						
						<td>
							规格：
							<input type="text" name="scrap.format" value="${lend.format}"  readonly="readonly" />
						</td>

					</tr>
					<tr>
						<td>
							原因：
							<input type="text" name="scrap.reason" 
								/>
						</td>
						<td>
							意见：
							<input type="text" name="scrap.badIdea" />
						</td>
						<td>
							备注：
							<input type="text" name="scrap.remark" 
								/>
						</td>
					</tr>
					<tr>
					
						<td>
							受理人：
							<input type="text" name="scrap.admin" value="${loginUser.name}"
								/>
							<input type="hidden" name="scrap.adminId" value="${lend.adminId}"
								readonly="readonly" />	
							
						</td>
						<td>
							出借日期：
							<input type="text" name="scrap.ldate" value="${lend.date}" readonly="readonly"/>
						</td>
						
						
						
					</tr>
					<tr>
						<td colspan="3" align="center">
							<input type="hidden" name="repay.lendId" value="${lend.id}" />
							<input type="hidden" name="repay.goodsId" value="${lend.goodsId}" />
							<input type="button" value="申请报废"  onclick="tijiao()"/>
							<font color="red">最多报废${lend.giveBackNum}</font>
							<input type="hidden" name="maxNum" value="${lend.giveBackNum}" id="maxNum">
							
							
						</td>
					</tr>
				</table>
			</form>
		</center>
		<script type="text/javascript">
var exp = /^\d+$/;
function  validate(){
	var numObj=document.getElementById("num");
	var num = document.getElementById("num").value;
	var maxNum= document.getElementById("maxNum").value;
	//var time = document.getElementById("time").value;
	if (num == "") {
		alert("请输入数量!");
		return false;
	}
	
	var flag1=numyanzheng(numObj,null);
	if(!flag1){
		return false;
	}else{
		if(num-maxNum>0){
			alert("报废数量应小于借用数量!");
			$("#num").focus();
			$("#num").val(maxNum);
			return false;
		}
	}
<%--	if (time == "") {--%>
<%--		alert("请选择日期！谢谢");--%>
<%--		return false;--%>
<%--	}--%>
	return true;
}

function tijiao(){
	var flag=validate();
	var scrapId="";
	if(flag){
		$.ajax({
			type : "POST",
			url : "LendNectAction!ajaxSaveOneScrap.action",
			dataType : "json",
			data : $("#submit").serialize(),
			async : false,
			success : function(data) {
				if(data!=null){
					var str=data.split(",");
					if(str[0]){
						alert("申请报废成功，已成功进入废品库,打印报废清单中");
						scrapId=str[1];
						window.location.href="LendNectAction!printScrap.action?scrapId="+scrapId;
					}else{
						alert("申请报废失败，请检查");
					}
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert("系统异常\n状态："+XMLHttpRequest.status+",状态值："+XMLHttpRequest.readyState+",异常信息:"+textStatus);
			}
		});
	}
}
</script>
	</body>
</html>
