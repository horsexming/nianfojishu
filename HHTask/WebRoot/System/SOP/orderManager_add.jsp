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
	<body style="height: 1200px;">
		<SCRIPT type="text/javascript">
$(function(){
	if (/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
		var str = '${strList1}';
		if(str==null || str.length == 0){
			window.location.href = "<%=basePath%>System/SOP/orderManager_noAdd.jsp";
		}
	}
})

</SCRIPT>
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
							<span id="title"></span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 800px; margin: 0px; padding: 0px;"></iframe>

				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<h3>
					添加
					<s:if test='status == "yc"'>预测备货</s:if>
					订单(Add an order)
					<s:if test="successMessage!=null">
						<br />
						<font color="red"><s:property value="successMessage" /> </font>
					</s:if>
				</h3>
				<form action="orderManager_add.action" method="post" id="submitBtn"
					onsubmit="return validate()" enctype="multipart/form-data">
					<input type="hidden" value="${flag}" name="flag">
					<input type="hidden" name="om.orderStatus" value="NO" />
					<input type="hidden" name="om.conversionStatus" value="NO" />
					<table class="table" style="width: 40%;">
						<tr>
							<th align="right">
								客户名字：
								<br />
								(Customer name):
							</th>
							<td align="left">
								<s:if test="tag=='KH'">
									<input type="hidden" value="${customeId}" name="id" />
									<input type="text" value="${users.more}" readonly="readonly" />
								</s:if>
								<s:else>
									<select name="id" id="custome">
										<s:iterator id="cu" value="clients">
											<option value="${cu.id}">
												${cu.clientcompanyname}
											</option>
										</s:iterator>
									</select>
									<font color="red">*</font>
								</s:else>
							</td>
						</tr>
						<tr>
							<th align="right">
								订单文件：
								<br />
								(Orders file):
							</th>
							<td align="left">
								<input type="file" name="orderFile" id="orderFile0" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								合同文件：
								<br />
								(Contract Documents):
							</th>
							<td align="left">
								<input type="file" name="orderFile" id="orderFile1" />
							</td>
						</tr>
						<tr>
							<th align="right">
								订单编号(内部)：
								<br />
								(Internal Order Number):
							</th>
							<th align="left">
								<input type="text" name="om.orderNum" id="orderNum"
									value="${om.orderNum}" />
								<br />
								(预生成编号:
								<span id="showNum">${om.orderNum}</span>)
							</th>
						</tr>
						<tr>
							<th align="right">
								订单编号(外部)：
								<br />
								(External Order Number):
							</th>
							<td align="left">
								<input type="text" name="om.outOrderNumber" id="outOrderNumber"
									value="${om.orderNum}" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								跟单人：
								<br />
								(With a single person):
							</th>
							<td align="left">
								<s:if test="tag=='KH'">
									<input type="text" value="${users.dept}" name="om.dept"
										style="width: 35px" />
									<input type="text" value="${users.name}"
										name="om.documentaryPeople" style="width: 118px" />
								</s:if>
								<s:else>
									<select id="dept" style="width: 80px;" name="om.dept">
										<option value="${users.dept}">
											${users.dept}
										</option>
									</select>
									<select style="width: 80px;" id="documentaryPeople"
										name="om.documentaryPeopleId">
										<option value="${users.id}">
											${users.name}
										</option>
									</select>
									<font color="red">*</font>
								</s:else>
							</td>
						</tr>
						<tr>
							<th align="right">
								开单人：
								<br />
								(Billing person):
							</th>
							<td align="left">
								<input type="text" name="om.billingPeople" id="billingPeople"
									value="${billingPeople}" readonly="readonly" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								客责任人：
							</th>
							<td align="left">
								<input type="text" name="om.clientFzr" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								订单类型：
								<br />
								(Order Type):
							</th>
							<td align="left">
								<select name="om.orderType" style="width: 155px;" id="orderType"
									onchange="changshowNum(this);showycorder1(this)">
									<s:if test='status == "yc"'>
										<option value="预测">
											预测
										</option>
									</s:if>
									<s:if test='status == "sz"'>
										<option value="试制">
											试制
										</option>
									</s:if>
									<s:if test='status == "sh"'>
										<option value="售后">
											售后
										</option>
									</s:if>
									<s:else>
										<option value="正式">
											正式
										</option>
										<option value="备货">
											备货
										</option>
									</s:else>
								</select>
								<font color="red">*</font>
								<span id="span_a"><a href="javascript:;"
									onclick="showycorder()">关联预测订单</a> </span>
							</td>
							<tr>
								<th align="right">
									关联订单编号(内部)：
									<br />
									(External Order Number):
								</th>
								<td align="left">
									<input type="text" name="" id="ycorderNum" />
									<input type="hidden" value="" name="om.orderId" id="orderId" />
								</td>
							</tr>
						</tr>
						<%--						<tr>--%>
						<%--							<th align="right">--%>
						<%--								交付日期：--%>
						<%--								<br />--%>
						<%--								(Delivery Date):--%>
						<%--							</th>--%>
						<%--							<td align="left">--%>
						<%--								<input class="Wdate" type="text" name=om.paymentDate--%>
						<%--									id="paymentDate"--%>
						<%--									onClick="WdatePicker( { dateFmt : 'yyyy-MM-dd',skin : 'whyGreen'});" />--%>
						<%--								<font color="red">*</font>--%>
						<%--							</td>--%>
						<%--						</tr>--%>
						<%--						<tr align="center">--%>
						<%--							<th colspan="2">--%>
						<%--								<input type="submit" value="提交 "--%>
						<%--									style="width: 80px; height: 50px;" />--%>
						<%--								&nbsp;&nbsp;&nbsp;--%>
						<%--								<input type="reset" value="清空 "--%>
						<%--									style="width: 75px; height: 50px;" />--%>
						<%--							</th>--%>
						<%--						</tr>--%>
						<tr>
							<th align="right">
								备注
							</th>
							<td>
								<textarea rows="2" cols="20" name="om.remark"></textarea>
							</td>
						</tr>
					</table>
					<div align="center">
							<s:if test="customeId==null">
								<input type="button" value="添加总成"
									onclick="selectYclAndWgj('','','${status}')" class="input">
							</s:if>
							<s:else>
								<input type="button" value="添加总成"
									onclick="selectYclAndWgj(${customeId},'${tag}')" class="input">
							</s:else>
						<input type="button" value="添加配件" class="input"
							onclick="showAddParts('')" />
						<%--						<STRONG>(此处产品仅显示总成的销售类型)</STRONG>--%>
						<hr>
						<h3>
							产品明细(Part Numbers)
						</h3>
						<table class="table" id="table">
							<tr bgcolor="#c0dcf2" height="50px" id="btn" align="center">
								<td>
									序号
								</td>
								<td id="partNumber">
									件号
								</td>
								<td id="name">
									产品名称
								</td>
								<td id="type">
									型别
								</td>
								<td>
									数量
								</td>
								<td>
									含税价
								</td>
								<td>
									不含税价
								</td>
								<td>
									税率
								</td>
								<td id="hsPrice">
									总价(含税)
								</td>
								<td>
									价格明细
								</td>
								<td>
									单位
								</td>
								<td>
									交付日期
								</td>
								<td>
									相关备货
								</td>
								<td>
									备注
								</td>
								<td>
									操作
								</td>
							</tr>
							<tr id="un">
								<s:if test="errorMessage==null">
									<td colspan="20" align="right">
										第
										<font color="red"><s:property value="cpage" /> </font> /
										<s:property value="total" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />
								</s:if>
								<s:else>
									<td colspan="20" align="center" style="color: red">
										${errorMessage}
								</s:else>
								</td>
							</tr>
						</table>
					</div>
					<tr align="center">
						<th colspan="2">
							<input type="hidden" value="${tag}" name="tag">
							<s:if test="status == 'sz'">
								<input type="hidden" value="试制" name="om.producttype" />
							</s:if>
							<s:else>
								<input type="hidden" value="批产" name="om.producttype" />
							</s:else>
							<input type="hidden" value="${status}" name="status" />
							<input type="button" value="提交" onclick="submitProduct()"
								style="width: 80px; height: 50px;" id="sub" disabled="disabled" />
						</th>
					</tr>
				</form>
				<form action="${pageContext.request.contextPath}/ProcardTemplateAction!findAllProcardTemp.action?pageStatus=lp&type=lp" method="post" id="addShowParts">

				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

$(function() {
	//显示所有部门信息
	$.ajax( {
		url : 'DeptNumberAction!findAllDept.action',
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(allDdept) {
			$("#dept").empty();
			$(allDdept).each(
					function() {
						if ("${users.dept}" == this.dept) {
							$(
									"<option value='" + this.dept
											+ "'selected='selected'>"
											+ this.dept + "</option>")
									.appendTo("#dept");
						} else {
							$(
									"<option value='" + this.dept + "'>"
											+ this.dept + "</option>")
									.appendTo("#dept");
						}

					});
		}

	});
	//显示部门对应的员工信息
	$("#dept").bind("change", function() {
		getUser();
	});

	getUser();
});
function selectYclAndWgj(id, tag, statue) {
	var custome = $("#custome").val();
	var ddType = $("#orderType").val();//订单类型
	if (ddType == '售后') {
		ddType = 'shouhou';
	}
	if (statue != null && id!="") {
		$("#xiugaiIframe").attr(
				"src",
				"PriceAction!findPriceById.action?id=" + id + "&statue="
						+ statue);
	} else if (tag == "KH") {
		$("#xiugaiIframe").attr(
				"src",
				"pieceNum_queryPieceNumByCondition.action?pagestatus=Addorder&id="
						+ id + "&tag=" + tag);
	} else if (custome > 0) {
		$("#xiugaiIframe").attr(
				"src",
				"pieceNum_queryPieceNumByCondition.action?pagestatus=Addorder&id="
						+ custome + "&tag=KH&status=" + statue + "&ddType="
						+ ddType);
	} else {
		$("#xiugaiIframe").attr(
				"src",
				"pieceNum_queryPieceNumByCondition.action?pagestatus=Addorder&status="
						+ statue);
	}
	chageDiv('block');
}
function del(obj) {
	var table = document.getElementById("table");
	obj = 'tr_' + obj;
	table.deleteRow($("#" + obj).index());
	var index = table.rows.length;
	/*for ( var i = 1; i < index - 1; i++) {
		table.rows[i].cells[0].innerHTML = i;
		var list1 = table.rows[i].cells[7].getElementsByTagName("input");
		list1[0].name = "pmList[" + (i - 1) + "].priceId"
		var list2 = table.rows[i].cells[6].getElementsByTagName("input");
		list2[0].name = "pmList[" + (i - 1) + "].unit"
		var list3 = table.rows[i].cells[5].getElementsByTagName("input");
		list3[0].name = "pmList[" + (i - 1) + "].num"
		var list4 = table.rows[i].cells[4].getElementsByTagName("input");
		list4[0].name = "pmList[" + (i - 1) + "].unitPrice"
		var list5 = table.rows[i].cells[3].getElementsByTagName("input");
		list5[0].name = "pmList[" + (i - 1) + "].type"
		var list6 = table.rows[i].cells[2].getElementsByTagName("input");
		list6[0].name = "pmList[" + (i - 1) + "].name"
		var list7 = table.rows[i].cells[1].getElementsByTagName("input");
		list7[0].name = "pmList[" + (i - 1) + "].pieceNumber"
		var list8 = table.rows[i].cells[1].getElementsByTagName("input");
		list8[0].name = "pmList[" + (i - 1) + "].bhsPrice"
		var list9 = table.rows[i].cells[1].getElementsByTagName("input");
		list9[0].name = "pmList[" + (i - 1) + "].taxprice"
	

	}*/
	releaseBtn();
}

function getUser() {
	if ($("#dept").val() != "") {
		$.ajax( {
			url : "UsersAction!findUsersByDept.action",
			type : 'post',
			dataType : 'json',
			cache : false,//防止数据缓存
			data : {
				deptName : $("#dept").val()
			},
			success : function(useradsfa) {
				$("#documentaryPeople").empty();//清空
				if ($("#dept").val() == "${users.dept}") {
					$("<option value='${users.id}'>${users.name}</option>")
							.appendTo("#documentaryPeople");
				}
				$(useradsfa).each(
						function() {
							$(
									"<option value='" + this.id + "'>"
											+ this.name + "</option>")
									.appendTo("#documentaryPeople");
						});
			},
			error : function() {
				//alert("服务器异常!");
		}
		});

	}

}

function validate() {
	var custome = document.getElementById("custome").value;
	var documentaryPeople = document.getElementById("documentaryPeople").value;
	var orderNum = document.getElementById("orderNum").value;
	var orderFile0 = document.getElementById("orderFile0").value;
	var Wdates = $(".Wdate");
	if (custome == 0) {
		alert("请选择客户名称!");
		return false;
	}
	if (orderFile0 == "") {
		alert("请选择订单文件!");
		return false;
	}
	if (documentaryPeople == "") {
		alert("请输入跟单人!");
		return false;
	}
if (Wdates != null && Wdates.length > 0 && '${status}' != 'yc') {
		for ( var i = 0; i < Wdates.length; i++) {
<%--			var num = $("#sp_"+i).val();--%>
<%--			if(num==null||num==""){--%>
<%--				alert("请填写第" + (i + 1) + "行的数量")--%>
<%--				$("#sp_"+i).focus();--%>
<%--				return false;--%>
<%--			}else if(num<=0){--%>
<%--				alert("请填写第" + (i + 1) + "行的数量不能小于0")--%>
<%--				$("#sp_"+i).focus();--%>
<%--				return false;--%>
<%--			}--%>
			if (Wdates[i].value == "") {
				alert("请填写第" + (i + 1) + "行的交付日期")
				$(Wdates[i]).focus();
				return false;
			}
		}
	}
	document.getElementById("sub").disabled = "disabled";
	return true;
}

function changshowNum(obj) {
	if (obj != null && obj.value != "") {
		$.ajax( {
			type : "POST",
			url : "orderManager_changshowNum.action",
			data : {
				status : obj.value
			},
			dataType : "json",
			success : function(data) {
				if (data != null) {
					$("#showNum").html(data);
					$("#orderNum").val(data);
					$("#outOrderNumber").val(data);
				}
			}
		})
	}
}
function showycorder() {
	$("#xiugaiIframe").attr("src",
			"orderManager_queryOrderManagerByCondition.action?flag=dj&tag=yc");
	chageDiv('block');
}
var showi = 0;
function showycorder1(obj) {
	if (obj != null && obj.value != "正式") {
		$("#span_a").hide();
	} else {
		$("#span_a").show();
	}
	showi++;
	if(showi>1){
		obj.disabled = true;
		obj.setAttribute('style', 'background-color: white;width: 155px; !important');
	}
}

function toRelateBh(index, markId) {
	$("#xiugaiIframe").attr(
			"src",
			"orderManager_toRelateBh.action?markId=" + markId + "&index="
					+ index);
	chageDiv('block');
}
$(function() {
	var obj = document.getElementById("orderType")
	changshowNum(obj);
	showycorder1(obj);
})

//显示添加配件窗口
function showAddParts(markId){
	if(markId==''){
		$("#xiugaiIframe").attr("src","${pageContext.request.contextPath}/ProcardTemplateAction!findAllProcardTemp.action?pageStatus=lp&type=lp");
		chageDiv('block');
	}else{
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/ProcardTemplateAction!getProcardByMarkId.action",
			dataType:"JSON",
			data:{
				markId:markId
			},
			success:function(data){
				$("#xiugaiIframe").attr("src","${pageContext.request.contextPath}/System/SOP/produce/Template_addParts.jsp?pageStatus=lp&id="+data.id+"&markId="+markId);
				chageDiv('block');
			}
		});
	}
}
//计算总价格
function computeSumPrice(index){
	var count = $("#sp_"+index).val();
	var price= $("#hsprice_"+index).val();
	var sumPrice = (parseFloat(count) * parseFloat(price)).toFixed(2);
	$("#unitPriceText_"+index).text(sumPrice);
	$("#unitPrice_"+index).val(sumPrice);
	releaseBtn();
}
//提交产品
function submitProduct(){
	$("#orderType").removeAttr("disabled");
	$("#submitBtn").submit();
}

function releaseBtn(){
	var addCount = $(".addCount");
	
	for(var i = 0;i<addCount.length;i++){
		if(addCount[i].value=='' || addCount[i].value==null ){
			$("#sub").attr("disabled","disabled");
		}else{
			$("#sub").removeAttr("disabled");
		}
	}
	
	if($(".nonePrice").length>0 &&'${status}' !='sh'){
		$("#sub").attr("disabled","disabled");
	}
	
}
</script>
	</body>
</html>
