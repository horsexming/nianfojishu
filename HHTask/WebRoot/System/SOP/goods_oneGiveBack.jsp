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
		
		<h1 align="center">库存归还清单</h1>
		<hr />
		<center>
<%--			LendNectAction!saveOneRepay.action--%>
			<form action="" method="post"
				 id="submit">
				<table align="center" border="1" style="border-collapse: collapse;">
					<tr>
						<td>
							卡号：
							<input type="text" name="repay.cardNum" value="${lend.cardNum}"
								readonly="readonly" />
							<span style="color: red;">${errorMessage}</span>
						</td>
						<td>
							姓名：
							<input type="text" name="repay.peopleName" value="${lend.peopleName }"
								readonly="readonly" />
							<input type="hidden" name="repay.admin" value="${lend.admin}"
								readonly="readonly" />	
							<input type="hidden" name="repay.adminId" value="${lend.adminId}"
								readonly="readonly" />	
						</td>
						<td>
							部门：
							<input type="text" name="repay.dept" value="${lend.dept }"
								readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td>
							件号：
							<input type="text" name="repay.goodsMarkId" value="${lend.goodsMarkId}"
								readonly="readonly" />
						</td>
						<td>
							批次：
							<input type="text" name="repay.goodsLotId" value="${lend.goodsLotId}"
								readonly="readonly" />
						</td>
						
						<td>
							名称：
							<input type="text" name="repay.goodsFullName" value="${lend.goodsFullName}"
								readonly="readonly" />
						</td>
						
					</tr>
					<tr>
						<td>
							
							归还数量：
							<input type="text" name="repay.num" id="num"   value="${lend.giveBackNum}"/>
						</td>
						<td>
							单位：
							<input type="text" name="repay.unit" value="${lend.unit}"
								readonly="readonly" />
						</td>
						<td>
							规格：
							<input type="text" name="repay.format" value="${lend.format}" readonly="readonly" />
						</td>
<%--						<td>--%>
<%--							归还日期：--%>
<%--							<input type="text" name="repay.rdate" class="Wdate" id="time"--%>
<%--								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />--%>
<%--							--%>
<%--						</td>--%>
						
					</tr>
					<tr>
						<td>
							借物日期：
							<input type="text" name="repay.ldate" value="${lend.date}"
								readonly="readonly" />
						</td>
<%--						<td>--%>
<%--							加工数量：--%>
<%--							<input type="text" name="repay.jiaGongNum" />--%>
<%--						</td>--%>
						
<%--						<td>--%>
<%--							加工件号：--%>
<%--							<input type="text" name="repay.processPieceNum"--%>
<%--								value="${lend.processPieceNum}" />--%>
<%--						</td>--%>
						<td>备注：
							<input type="text" name="repay.remark" />
						</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<input type="hidden" name="repay.lendId" value="${lend.id}" />
							<input type="hidden" name="repay.goodsId" value="${lend.goodsId}" />
							<input type="button" value="归还"  onclick="tijiao()"/>
							<font color="red">最多归还${lend.giveBackNum}</font>
							<input type="hidden" id="maxNum" value="${lend.giveBackNum}"/>
							<input type="hidden" name="repay.storehouse" value="${lend.storehouse}"/>
							<input type="hidden" name="repay.goodHouse" value="${ lend.goodHouse}"/>
							<input type="hidden" name="repay.wareHouse" value="${lend.wareHouse }"/>
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
			alert("归还数量应小于借用数量!");
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
	var repayId;
	if(flag){
		$.ajax({
			type : "POST",
			url : "LendNectAction!ajaxSaveOneRepay.action",
			dataType : "json",
			data : $("#submit").serialize(),
			async : false,
			success : function(data) {
				if(data!=null){
					var str=data.split(",");
					if(str[0]){
						alert("归还成功,生成归还清单");
						repayId=str[1];
						window.location.href="LendNectAction!printRepay.action?repayId="+repayId;
					}else{
						alert("归还失败,请检查");
					}
				}
			}
		});
	}
}
</script>
	</body>
</html>
