<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
	<script type="text/javascript">
function chagePrice(obj, few) {
	var price = obj.value;
	if (price != null) {
		if (obj.id == "hsPrice_" + few) {
			var otherPrice = price / 1.17;
			document.getElementById("bhsPrice_" + few).value = parseFloat(otherPrice);
		} else if (obj.id == "bhsPrice_" + few) {
			document.getElementById("hsPrice_" + few).value = parseFloat((price * 1.17));
		}
	}
}
var fileDivHTML = "";
var count = 0;
function uploadFile(obj, few) {
	var fileDiv = document.getElementById("fileDiv_" + few);
	if (obj.value == "上传附件") {
		fileDiv.style.display = "block";
		obj.value = "添加文件";
	}
	fileDivHTML = "<div id='file"
			+ count
			+ "'><input type='file' name='attachment'><a href='javascript:delFile("
			+ count + "," + few + ")'>删除</a></div>";
	fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);
	count++;
}

function delFile(obj, few) {
	document.getElementById("file" + obj).parentNode.removeChild(document
			.getElementById("file" + obj));
	count--;
	if (count <= 0) {
		count = 0;
		document.getElementById("fileButton_" + few).value = "上传附件";
		document.getElementById("fileDiv_" + few).style.display = "none";
	}
}



var oldObj;
var oldObj2;
function chageModule(obj, obj2) {
	if (obj.id != "module1") {
		document.getElementById("module1").className = "tag_1";
		document.getElementById("module1_1").style.display = "none";
	}
	if (oldObj != null) {
		oldObj.className = "tag_1";
		document.getElementById("module1_" + oldObj2).style.display = "none";
	}

	obj.className = "tag_2";
	document.getElementById("module1_" + obj2).style.display = "block";

	oldObj = obj;
	oldObj2 = obj2;
}
function checkForm(num) {
	var partNumber = document.getElementById("partNumber" + num);
	var client = document.getElementById("client" + num);
	var contractNumber = document.getElementById("contractNumber" + num);
	var hair = document.getElementById("hair" + num);
	var price = document.getElementById("price" + num);
	var name = document.getElementById("name" + num);
	var fileNumber = document.getElementById("fileNumber" + num);
	if (partNumber != null && partNumber.value == "") {
		alert("件号不能为空!");
		partNumber.focus();
		return false;
	} else if (name != null && name.value == "") {
		alert("名称不能为空!");
		name.focus();
		return false;
	} else if (client != null && client.value == "") {
		alert("客户不能为空!");
		client.focus();
		return false;
	} else if (contractNumber != null && contractNumber.value == "") {
		alert("合同编号不能为空!");
		contractNumber.focus();
		return false;
	} else if (hair != null && hair.value == "") {
		alert("发住地不能为空!");
		hair.focus();
		return false;
	} else if (price != null && price.value == "") {
		alert("价格不能为空!");
		price.focus();
		return false;
	} else if (fileNumber != null && fileNumber.value == "") {
		alert("档案号不能为空!");
		fileNumber.focus();
		return false;
	} else {
		return true;
	}

}
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="left">

			</div>

			<div align="center">
				<form action="SellPriceAction!tofind.action?" method="post">
					<table width="99%">
						<tr>
							<td align="right">
								件号:
								<br />
							</td>
							<td>
								<input type="text" style="width: 150px;"
									name="sellPrice.partNumber">
							</td>
							<td align="right">
								名称:
								<br />
							</td>
							<td>
								<input type="text" style="width: 150px;" name="sellPrice.name">
							</td>
							<td align="right">
										客&nbsp;&nbsp;&nbsp;户:<br/>
									</td>
									<td>
									  <select  id="type1" name="sellPrice.clientManagement">
									  <option value="">请选择客户</option>
									  <s:iterator value="clientNameList" id="clientName">
									   <option>${clientName}</option>
									  </s:iterator>
									  </select>
									</td>
						</tr>

						<tr>

							<td align="right">
								合同编号:
								<br>

							</td>
							<td>
								<input type="text" style="width: 150px;"
									name="sellPrice.contractNumber">
							</td>
					<td align="right">
								销售价格有效期时间从
								<br>
								
							</td>
							
							
							<td>
								<div>
									<input class="Wdate" type="text" name="sellPrice.starttime"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
										
								</div>
							</td>
								<td align="right">
								销售价格有效期结束时间到
								<br>
							</td>
							<td>
								<div>
									<input class="Wdate" type="text" name="sellPrice.endtime"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</div>
							</td>
						</tr>
						<tr>
							<td align="right">
								档案号
								<br>
							</td>
							<td>
								<input type="text" style="width: 150px;"
									name="sellPrice.fileNumber">
							</td>
							
									<td align="right">
										产品类别:<br/>
									</td>
									<td>
										<select name="sellPrice.productCategory">
										    
											<option>总成</option>
											<option>组合</option>
											<option>外购</option>
											<option>自制</option>
										</select>
									</td>
									<td align="right">
										生产类型:<br/>
									</td>
									<td>
										<select name="sellPrice.produceType">
										    
											<option value="销售">
												销售
											</option>
											<option value="外委">
												外委
											</option>
											<option value="外购">
												外购
											</option>
											<option value="行政">
												行政
											</option>
										</select>
									</td>
									</tr>
						<tr>
						
							
							<td colspan="9" rowspan="2" align="center">
								<input type="submit" value="查询"
									style="width: 100px; height: 40px">
								<input type="reset" value="添加价格 " onclick="toadd()"
									style="width: 100px; height: 40px">

							</td>
							</tr>
						
					</table>
				</form>
				<br>
				<center>
					<table width="99%">
						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<td>
								序号
								<br>
							</td>
							<td>
								件号
								<br>
							</td>
							<td>
								名称
								<br>
							</td>
							<td>
								客户
								<br>
							</td>
							<td>
								合同编号
								<br>
							</td>
							<td>
								含税价
								<br>
							</td>
							<td>
								不含税价
								<br>
							</td>
							<td>
								发住地
								<br>
							</td>
							<td>
								档案号
								<br>
							</td>
							<td>
								负责人
								<br>
							</td>
							<td>
								型别
								<br>
							</td>
							
							<td>
								销售价格开始有效时间从
								<br>
							</td>
							<td>
								销售价格有效时间到
								<br>
							</td>
							<td>
								产品类别
								<br>
							</td>
							<td>
								生产类型
								<br>
							</td>
							 <td>
								添加时间
								<br>
							</td>
							 <td>
								查看附件
								<br>
							</td>
							<td>
								操作
								<br>
							</td>
						</tr>
						<s:iterator id="sellPrice1" value="sellPriceList"
							status="statussdf">
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

							<td>
								${sellPrice1.partNumber}
							</td>
							<td>
								${sellPrice1.name}
							</td>
							<td>
								${sellPrice1.clientManagement}
							</td>
							<td>
								${sellPrice1.contractNumber}
							</td>
							<td>
								${sellPrice1.hsPrice}
							</td>
							<td>
								${sellPrice1.bhsPrice}
							</td>
							<td>
								${sellPrice1.hair}
							</td>
							<td>
								${sellPrice1.fileNumber}
							</td>
                             <td>
								${sellPrice1.chargePerson}
							</td>
							  <td>
								${sellPrice1.type}
							</td>
							<td>
								${sellPrice1.starttime}
							</td>
							<td>
								${sellPrice1.endtime}
							</td>
                             <td>
								${sellPrice1.productCategory}
							</td>
							<td>
								${sellPrice1.produceType}
							</td>
							<td>
							   ${sellPrice1.addtime}
							</td>
							 <td>
<%--							 <a href="<%=path%>/upload/file/sellPrice/${sellPrice1.attachmentName}">下载</a>--%>
							 <a href="FileViewAction.action?FilePath=/upload/file/sellPrice/${sellPrice1.attachmentName}">下载</a>
							</td>
							<td align="center" colspan="2">
								<a href="SellPriceAction!toupdate.action?id=${sellPrice1.id}">修改/</a>
								<a
									href="SellPriceAction!deleteSellPrice.action?id=${sellPrice1.id}">删除</a>
							</td>
						</s:iterator>
						<tr>
							<td colspan="18" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</tr>
					</table>
				</center>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript">

function update(obj) {
	var url = "SellPriceAction!toupdate.action?sellPrice.id=" + obj;
	window.location.href = url;
}
function toadd(){
	var url = "SellPriceAction!toadd.action";
	window.location.href = url;
	
}

</script>
	</body>
</html>
