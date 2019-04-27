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
					
					<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					月度销售收入录入
				</h3>

				<form action="saleBudgetAction!.action" method="post" onsubmit="return checkForm()">
					<table width="85%" class="table" id="complexselectedlist">
					<tbody>
						<tr>
							<th>预算月份：<select name=""><option value="${planMonth}">${planMonth}</option></select></th>
						</tr>
						<tr><th colspan="4">预算销售明细</th></tr>
						
						<tr>
							<th>
							客户
							</th>
							<th>
							产品件号
							</th>
							<th>
							销售单价
							</th>
							<th>									
							预测销售量					
							</th>
						</tr>
						<tr align="left">
							<th>
								<select name="listDetail[0].baoxiaoStyle" style="width:80px;" onMouseOver="selectCourse(0)" id="course0"  >
									
								</select>
							</th>
							<th>
								<input type="text" name="listDetail[0].baoxiaoContent" />
							</th>
							<th>
								<input type="text" name="listDetail[0].markId" />
							</th>
							<th>
								<input type="text" onKeyUp="hejiJine()" id="h0" name="listDetail[0].money" />
							</th>
						</tr>
			            <tr id="uploadtr">
						<tr>
							<th align="left"  >								
									<input type="button" id="inforButton_1"
										onclick="saveHKInfor(this,1)" value="添加明细" />								
							</th>
							<th width="29%" align="left">
									<input id="deleteItem" style="display:none;" type="button" id="inforButton_2" onclick="delInfor()"
										value="删除明细" />							
							</th>
							<th colspan="2">
							
							合计金额
							<span id="hejiMoney"><font color="red"><label id="allMoney"></label></font></span><select
									name="baoxiaodan.currency">
									<option value="RMB">
										人民币
									</option>
									<option value="USD">
										美元
									</option>
									<option value="EUR">
										欧元
									</option>
									<option value="HKD">
										港币
									</option>
									<option value="GBR">
										英镑
									</option>
									<option value="JPY">
										日元
									</option>
									<option value="CHF">
										瑞士法郎
									</option>
									<option value="AUD">
										澳元
									</option>
									<option value="CAD">
										加元
									</option>
									<option value="SGD">
										新加坡元
									</option>
									<option value="SEK">
										瑞典克朗
									</option>
									<option value="DKK">
										丹麦克朗
									</option>
									<option value="NOK">
										挪威克朗
									</option>
									<option value="THB">
										泰国铢
									</option>
									<option value="NZD">
										新西兰元
									</option>
									<option value="KRW">
										韩国元
									</option>
								</select></th>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="提交"
									style="width: 60px; height: 40px;" align="top">
								&nbsp;&nbsp;
								<input type="reset" value="取消"
									style="width: 60px; height: 40px;" align="top">
							</td>
						</tr>
						<tr>
							<td colspan="4" style="font-size: 12px;">
								备注：
								<br>
								1、除相关签名栏目必须手写外，其余栏目可采用电脑录入，其中大小写合计数由电脑自动汇总。
								2、报销时，请明确付款方式，并在相应的空格内打勾。 3、“报销人”为实际经费使用者。
								4、报销人提供报销的发票，其所属日期为截止报销日3个月之内。
								5、此报销单仅适用于事务性费用报销（例如：差旅费、车辆费、业务招待费等） 6、领款日为每周二、四（对公转账及急事、特事除外）。
							</td>
						</tr>
						<tbody>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
	var inforDivHTML = "";
	var lineCount = 1;
	var begAddLineNum = 6;
	function selectCourse(few){
		var baoxiaoClass=document.getElementById("baoxiaoClass").value;
		var id="course"+few;		
		createDept(id,"BaoXiaoDanAction!findchildClass.action?tag="+baoxiaoClass);
	}
	function hejiJine(){
			var total=0;
			for(var t=0;t<lineCount;t++){
				var id="h"+t;
				var cur=document.getElementById(id).value;
				//alert(cur);
				total=total+parseFloat(cur);
			}
			document.getElementById("allMoney").innerHTML=total;
		}
	function saveHKInfor(obj, few) {
	var _tbody = document.getElementById("complexselectedlist").tBodies[0];//获得第一个tbody
	var uploadtr = document.getElementById("uploadtr");//将要在该Tr之前添加元素
	var _tr = document.createElement("tr");
	_tbody.insertBefore(_tr, uploadtr);
	begAddLineNum++;
	//if(local.complexselectedlist.value.length != 0) {			
	var x=_tr.insertCell(0);
  	x.innerHTML = "<select style=\"width:80px;\" onclick=\"selectCourse("+lineCount+")\" name=\"listDetail["+lineCount+"].baoxiaoStyle\" id=\"course"+lineCount+"\">" +	
	"</select";
	var x1=_tr.insertCell(1);
    x1.innerHTML = "<input type=\"text\" name=\"listDetail["+lineCount+"].baoxiaoContent\" >";
	var x2=_tr.insertCell(2);
    x2.innerHTML = "<input type=\"text\" name=\"listDetail["+lineCount+"].markId\" >";
	var x3=_tr.insertCell(3);
    x3.innerHTML = "<input type=\"text\" onKeyUp=\"hejiJine()\" id=\"h"+lineCount+"\" name=\"listDetail["+lineCount+"].money\" >";
	//执行下拉
    selectCourse(lineCount);
    lineCount++;
	document.getElementById("deleteItem").style.display="block";
	//} 

	//lineCount++;   
	
	}
	function delInfor() {
		//alert(begAddLineNum);
		complexselectedlist.deleteRow(begAddLineNum);
		begAddLineNum--;
		lineCount--;
		if(begAddLineNum<7){
			document.getElementById("deleteItem").style.display="none";
		}
		hejiJine();
	}
	//提交验证
	function checkForm(){
		var shoukuanRen=document.getElementById("shoukuanRen");
		var payStyle=document.getElementById("payStyle");
		var baoxiaoDate=document.getElementById("baoxiaoDate");
		var explain=document.getElementById("explain");
		
		if(shoukuanRen.value==""){
			alert("收款单位（人）不能为空!");
			shoukuanRen.focus();
			return false;
		}else if(payStyle.value==""){
			alert("付款方式不能为空!");
			payStyle.focus();
			return false;
		}else if(baoxiaoDate.value==""){
			alert("报销日期不能为空!");
			baoxiaoDate.focus();
			return false;
		}else if(explain.value==""){
			alert("说明不能为空!");
			explain.focus();
			return false;
		}
	}
	
	function chagebaoxiaoClass(){
		for(var i=0;i<lineCount;i++){
			document.getElementById("course"+i).options.length=0;
			selectCourse(i);
			
		}
		
	}
	
	</script>
	</body>	
</html>
