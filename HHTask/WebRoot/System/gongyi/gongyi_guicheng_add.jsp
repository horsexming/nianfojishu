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
		<style type="text/css">
			body{
				font-family:宋体;
			}
			.table{
				border:0px solid #999;
				width: 756px;	
				border-collapse:collapse;
			}
			.table th,.table td {
				height: 24px;
			}
		</style>
	</head>
	<body>
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
					
				</div>
			</div>
			
			<form action="gongyiGuichengAction!addGongyiGuicheng.action" method="post"
							style="">
			<div align="center">
			<!-- A4页面开始 -->
				<div id="printDiv" style="width:794px;height:1123px;border:1px solid #000000;">
					<!-- 页边距内开始 -->
					<!-- 打印页边距设定为 5mm 时，网页内最大元素的分辨率：756×1086   5mm/18.89-->
					<div style="width:756px;height:1086px;border:1px solid #000000;position: relative;top:19px;"> 
						
							<table border="0" width="100%" class="table">
								<!-- 1 -->
								<tr align="center">
									<td align="right">工艺规程编号：<input type="text" id="numb" name="gongyiGuicheng.numb"/></td>
								</tr>
								<!-- 2 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 3 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 4 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 5 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 6 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 7 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 8 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 9 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 10 -->
								<tr align="center">
									<td style="font-size: 120px;word-spacing: 10px;">工&nbsp;艺&nbsp;规&nbsp;程</td>
								</tr>
								<!-- 11 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 12 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 13 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 14 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 15 -->
								<tr align="center">
									<td style="font-size: 20px;">型别：<input type="text" id="xingbie" name="gongyiGuicheng.xingbie"/></td>
								</tr>
								<!-- 16 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 17 -->
								<tr align="center">
									<td style="font-size: 20px;">件号：<input type="text" id="jianNumb" name="gongyiGuicheng.jianNumb"/><%--<select type="text" id="jianId" name="gongyiGuicheng.jianId"></select>--%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件名：<input type="text" id="jianName" name="gongyiGuicheng.jianName"/></td>
									
								</tr>
								<!-- 18 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 19 -->
								<tr align="center">
									<td style="font-size: 20px;">共&nbsp;&nbsp;<input type="text" id="pageTotal" name="gongyiGuicheng.pageTotal"/>&nbsp;&nbsp;页</td>
								</tr>
								<!-- 20 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 21 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 22 -->
								<tr align="center">
									<td style="font-size: 28px;">编制：<%--<input type="text" id="bianzhiName" name="gongyiGuicheng.bianzhiName"/>
									--%>
									<select id="bianzhiDept" style="color:#000000;visibility:hidden;" >
									</select>
									<select id="bianzhiId" name="gongyiGuicheng.bianzhiId" >
									</select>
									<input type="hidden" id="bianzhiName" name="gongyiGuicheng.bianzhiName"/>
									<input class="Wdate" type="text" id="bianzhiDate" name="gongyiGuicheng.bianzhiDate"
												onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
									</td>
								</tr>
								<!-- 23 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 24 -->
								<tr align="center">
									<td style="font-size: 28px;">校对：<%--<input type="text" id="jiaoduiName" name="gongyiGuicheng.jiaoduiName"/>--%></td>
								</tr>
								<!-- 25 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 26 -->
								<tr align="center">
									<td style="font-size: 28px;">审核：<%--<input type="text" id="shenheName" name="gongyiGuicheng.shenheName"/>--%></td>
								</tr>
								<!-- 27 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 28 -->
								<tr align="center">
									<td style="font-size: 28px;">批准：<%--<input type="text" id="pizhunName" name="gongyiGuicheng.pizhunName"/>--%></td>
								</tr>
								<!-- 29 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 30 -->
								<tr align="center">
									<td style="font-size: 28px;">${companyInfo.name}</td>
								</tr>
								<!-- 31 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 32 -->
								<tr align="center">
									<td style="font-size: 18px;"><input id="joined" class="Wdate" type="text" name="gongyiGuicheng.fachuDate"
												onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />&nbsp;日发出</td>
								</tr>
								<!-- 33 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 34 -->
								<tr align="center">
									<td></td>
								</tr>
								<!-- 35 -->
								<tr align="center">
									<td align="right">版次<input type="text" id="banci" name="gongyiGuicheng.banci"/>存图号<input type="text" id="banci" name="gongyiGuicheng.cuntuNumb"/></td>
								</tr>
							</table>
							
						
						</div>
					</div>
					<table>
						<tr>
							<td align="center" colspan="1">
								<input type="submit" value="提交" style="width: 100px; height: 50px;" />
								<input type="reset" value="重置" style="width: 100px; height: 50px;" />
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<script type="text/javascript">
$(function(){
	var bianzhiId='${sessionScope.Users.id}';
	var bianzhiName='${sessionScope.Users.name}';
	//获得所以的件号 件名
	/*
	$.ajax({
		type: "get",
		dataType: "json",
        url: "gongyiGuichengAction!findJianNumbForSelect.action",
		async: true,
		success: function(data){
			if(data.success){
				var data=data.data;
				$('#jianId').empty();
				$("<option value='' data-jianNumb='' data-jianName=''>请选择</option>").appendTo("#jianId");
				$(data).each(function(){
					$("<option value='"+this.id+"' data-jianNumb='"+this.jianNumb+"' data-jianName='"+this.jianName+"'>"+this.jianNumb+this.jianName+"</option>").appendTo("#jianId");
				});
			}
		}
	});
	//填充件名
	$("#jianId").bind("change",function() {
		var jianId=$(this).val();
		var selectedOption=$('#jianId').find("option[value='"+jianId+"']");
		var jianNumb=selectedOption.attr('data-jianNumb');
		var jianName=selectedOption.attr('data-jianName');
		$('#jianNumb').val(jianNumb);
		$('#jianName').val(jianName);
	});
	*/
	//********************************部门人员管理***************************************************
$.ajax( {
		type : "get",
		dataType : "json",
		url : "AssessPersonnelAction!findAuditGroup.action?pageStatus=gy",
		async : false,
		success : function(data) {
			$("<option value='' >请选择成员组</option>").appendTo("#bianzhiDept");
			$("<option value='' >请选择人员</option>").appendTo("#bianzhiId");
			$(data).each(function(i,n){
				$("<option value='" + this.id + "'>" + this.groupName + "</option>").appendTo("#bianzhiDept");
			});
		}
	});

	//级联查询出部门所对应的所有人员
	
	$("#bianzhiDept").bind("change",function() {
		if ($("#bianzhiDept").val() != "") {
			$.ajax( {
				url : "AssessPersonnelAction!findAuditPerson.action?pageStatus=gy",
				type : 'post',
				dataType : 'json',
				async : false,
				cache : false,//防止数据缓存
				data : {
					id : $("#bianzhiDept").val()
				},
				success : function(useradsfa) {
					$("#bianzhiId").empty();//清空
					$("<option value='' data-name=''>请选择人员</option>")
							.appendTo("#bianzhiId");
					$(useradsfa).each(function() {
						$("<option value='" + this.userId+ "' data-name='"+ this.userName + "'>"+ this.userName +"</option>").appendTo("#bianzhiId");
					});
				},
				error : function() {
					alert("服务器异常!");
				}
			});
		}
	});
$("#bianzhiId").bind("change",function() {
		var bianzhiId=$(this).val();
		var selectedOption=$('#bianzhiId').find("option[value='"+bianzhiId+"']");
		var bianzhiName=selectedOption.attr('data-name');
		$('#bianzhiName').val(bianzhiName);
});
//$('#bianzhiId').append("<option value='"+bianzhiId+"' data-name='"+bianzhiName+"' selected='selected'>"+bianzhiName+"</option>");
//$('#bianzhiName').val(bianzhiName);
$('#bianzhiDept').find("option:contains('编制')").attr("selected",true);
$.ajax( {
	url : "AssessPersonnelAction!findAuditPerson.action?pageStatus=gy",
	type : 'post',
	dataType : 'json',
	async : false,
	cache : false,//防止数据缓存
	data : {
		id : $("#bianzhiDept").val()
	},
	success : function(useradsfa) {
		$("#bianzhiId").empty();//清空
		$("<option value='' data-name=''>请选择人员</option>")
				.appendTo("#bianzhiId");
		$(useradsfa).each(function() {
			$("<option value='" + this.userId+ "' data-name='"+ this.userName + "'>"+ this.userName +"</option>").appendTo("#bianzhiId");
		});
		var selectedOption=$('#bianzhiId').find("option[value='"+bianzhiId+"']");
		selectedOption.attr("selected",true);
		$('#bianzhiName').val(selectedOption.attr('data-name'));
	},
	error : function() {
		alert("服务器异常!");
	}
});
//$("#bianzhiId").find("option[value='"+bianzhiId+"']").trigger("click");
//*********************结尾*****************************	
});


</script>
