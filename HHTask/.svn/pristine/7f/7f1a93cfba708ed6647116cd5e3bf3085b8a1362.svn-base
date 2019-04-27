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
		<div id="gongneng">

			<div align="center">
				<h3>
					${cm.clientcompanyname}
				</h3>
				<form action="pieceNum_queryPieceNumByCondition.action"
					method="post">
					<table class="table">
						<tr>
							<td>
								件号(Part No.)：
								<input type="text" name="numId" value="${numId}" />
							</td>
							<td>
								产品名称(Product Name)：
								<input type="text" name="name" value="${name}" />
							</td>
							<td>
								车型(Models)：
								<input type="text" name="carType" value="${carType}" />
							</td>
							<td>
								型别(Type)：
								<input type="text" name="type" value="${type}" />
								<input type="hidden" name="errorMessage" value="all" />
								<input type="hidden" name="tag" value="KH"/>
								<input type="hidden" name="ddType" value="${ddType}"/>
								<input type="hidden" name="id" value="${cm.id}" />
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="查询(Query)" style="height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
				<div>
					<font color="red"><b>注:</b> </font>(此处产品来源于产品合同档案信息,此处仅显示产品类型是
					<b>总成</b>和
					<b>销售类型</b>以及在当前
					<b>有效期范围内</b>的产品信息)
				</div>
				<s:set name="abc" value="id" />
				<form action="pieceNum_initSelectedNum.action" method="post"
					onsubmit="return vali()">
					<table id="test" class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								序号
							</td>
							<td align="center">
								件号
							</td>
							<td align="center">
								产品名称
							</td>
							<td align="center">
								型别
							</td>
							<td align="center">
								税率
							</td>
							<td align="center">
								含税价格
							</td>
							
							<td align="center">
								不含税价格
							</td>
							<td align="center" colspan="2">
								操作
							</td>
						</tr>
						<s:iterator value="list" id="pageList" status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#pageStatus.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>
							<td>
								<input type="hidden" id="hid_${pageList.id}"
									value="${pageList.id}" />
								${pageList.partNumber }
							</td>
							<td align="left">
								${pageList.name }
							</td>
							<td>
								${pageList.type}
							</td>
							<td>
								<fmt:formatNumber value="${pageList.taxprice}" pattern="#.0000"></fmt:formatNumber>
							</td>
							<td>
								<fmt:formatNumber value="${pageList.hsPrice}" pattern="#.0000"></fmt:formatNumber>
							</td>
							<td>
								<fmt:formatNumber value="${pageList.bhsPrice}" pattern="#.0000"></fmt:formatNumber>
							</td>
							<td colspan="2">
								<div id="num_${pageList.id}" style="display: none;">
									数量:
									<input type="text" id="in_${pageList.id}" style="width: 80px;"
										onkeyup="numyanzhen('${pageList.id}')"
										onblur="numyanzhen('${pageList.id}')" />
								</div>
								<input type="button" value="选择" id="btu_${pageList.id}"
									style="width: 60px; height: 30px;"
									onclick="selectWgj('${pageList.id}',this)" />
							</td>
							</tr>
						</s:iterator>
						<tr>
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
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function changevalue(id) {
	var btu = document.getElementById("btu_" + id);
	btu.value = "确定";
}
function numyanzhen(id) {
	var num = document.getElementById("in_" + id);
	var ty = '^[0-9]*[1-9][0-9]*$';
	var re = new RegExp(ty);
	if (num != null && num.value.match(re) == null) {
		num.value = "";
		num.focus();
		num.select();
	} else {
		changevalue(id);
	}
}
var a = 0;
function selectWgj( id, obj) {
	var ddType="${param.ddType}";//订单类型，如果为售后，则没有价格，数量，交付日期，备货等等相关信息
	var in_ = document.getElementById("in_" + id);
	document.getElementById("num_" + id).removeAttribute('style');
	in_.focus();
	in_.select();
	var num = document.getElementById("in_" + id);
	var btu = document.getElementById("btu_" + id);
	if (btu != null && btu.value == '确定') {
		//添加一行
		var tab = window.parent.document.getElementById("table");
		var index = tab.rows.length;
		var partNumber,name,type,hsPrice,bhsPrice,danwei="",taxprice=0;
		if(id!=null && id!=""){
		$.ajax( {
			type : "POST",
			url : "PriceAction!findPriceById1.action",
			data : {
					id:id		
			},
			async:false,
			dataType : "json",
			success : function(data) {
				if (data != null) {
					partNumber =data.partNumber ;
					name = data.name;
					type = data.type;
					hsPrice = data.hsPrice;
					bhsPrice = data.bhsPrice;
					if(data.danwei!=null){
						danwei =data.danwei;
					}
					 if(data.taxprice!=null){
						taxprice = data.taxprice; 
					 }
					
				}
			},
			error : function() {
				alert("价格数据异常!可能含有tab空格");
			}
		})
		hsPrice = Number(hsPrice)
		hsPrice = hsPrice.toFixed(4);
		var unitPrice = num.value * hsPrice;//总价;
		unitPrice = unitPrice.toFixed(2);
		}
		var bool = true;
		for ( var i = 1; i < index - 1; i++) {
			var old_unitPrice = window.parent.document
					.getElementById("unitPrice_" + id);
			var sp_num = window.parent.document.getElementById("sp_" + id);
			var old_id = window.parent.document.getElementById("id_" + id);
			var old_num = window.parent.document.getElementById("num_" + id);
			var sp_unitPrice = window.parent.document.getElementById("sp1_"
					+ id);
			if (old_id != null && old_id.value == id) {
				bool = false;
				var new_num = Number(old_num.value) + Number(num.value);
				var new_unitPrice = new_num * hsPrice;
				new_unitPrice = new_unitPrice.toFixed(3);
				old_num.value = new_num
				old_unitPrice = new_unitPrice
				sp_num.innerHTML = new_num;
				sp_unitPrice.innerHTML = new_unitPrice;
			}
		}
		if (bool) {
			/// 目前不知道为什么先注掉的东西
			//var tab=window.opener.document.getElementById("table!");
			var index = tab.rows.length;

			var newTr = tab.insertRow(index - 1);

			newTr.id = 'tr_' + (index - 1);
			var tr = window.parent.document.getElementById("tr_" + (index - 1));
			//添加列 
			var aboutBh="";
			$.ajax( {
				type : "POST",
				url : "orderManager_getbfCount.action",
				data : {
						markId:partNumber		
				},
				async:false,
				dataType : "json",
				success : function(data) {
					if (data != null&&data>0) {
						aboutBh = "<input type='button' value='关联备货' onclick='toRelateBh("
							+ (index - 2) + ",\""+partNumber+"\")' >"
					}else{
						aboutBh = "无剩余"
					}
				}
			});
			newTr.align = "center";
			var newtd14 = "<td><input type='button' value='删除' onclick='del("
					+ (index - 1) + ")'><input type='button' value='添加配件' onclick='showAddParts(\""+ partNumber + "\")'>" +
					"<input type='hidden' name='pmList["+(index -2)+"].fmarkid' value='"+partNumber+"'</td>";
			var newtd13 = '<td><input type="text" name=pmList['+(index -2)+'].remark ></td>';
			var newtd12 = "<td><lable id='relateShow"+(index - 2)+"'></lable>"+aboutBh+"</td>";
			var newtd11 = '<td><input class="Wdate" type="text"  name=pmList['+(index -2)+'].paymentDate   onClick=" WdatePicker( { dateFmt : &apos;yyyy-MM-dd&apos;,skin : &apos;whyGreen&apos;,minDate:&apos;%y-%M-%d&apos;});"    /></td>'
			var newtd10 = '<td>' + danwei + '<input type="hidden" value='
					+ danwei + ' name=pmList[' + (index - 2) + '].danwei></td>'
			var newtd9 = "<td><a href='javascript:;' onclick=selectYclAndWgj("
					+ id + ",null,'mingxi')>明细</a> <input type='hidden' value=" + id
					+ " name='pmList[" + (index - 2) + "].priceId'></td>"
			var newtd6 = '<td>'+bhsPrice+'<input type="hidden" value='+bhsPrice+' name=pmList['+(index -2)+'].bhsPrice /></td>'
			if(taxprice==0 || taxprice == ''){
				taxprice = 0;
			}
			var newtd7 = '<td>'+ taxprice +   '%<input type="hidden" value='
					+ taxprice + ' name=pmList[' + (index - 2) + '].taxprice></td>'
			var newtd5 = '<td>'+ hsPrice + '<input type="hidden" value='
					+ hsPrice + ' name=pmList[' + (index - 2) + '].unit></td>'
			var newtd4 = "<td><span id='sp_" + id + "'>" + num.value
					+ "</span>" + "<input type='hidden'  value=" + num.value
					+ " name='pmList[" + (index - 2) + "].num' id=num_" + id
					+ "></td>";
			var newtd8 = "<td id=price_" + (index - 1) + "><span id='sp1_" + id
					+ "'>" + unitPrice+ "</span>"
					+ '<input type="hidden" value=' + unitPrice
					+ ' name="pmList[' + (index - 2)
					+ '].unitPrice" id=unitPrice_' + id + '></td>';
			var newtd3 = '<td>' + type + '<input type="hidden" value=' + type
					+ ' name="pmList[' + (index - 2) + '].type"></td>';
			var newtd2 = '<td>' + name + '<input type="hidden" value=' + name
					+ ' name="pmList[' + (index - 2) + '].name"></td>';
			var newtd1 = '<td>' + partNumber + '<input type="hidden" value='
					+ partNumber + ' name="pmList[' + (index - 2)
					+ '].pieceNumber">' + '<input type="hidden" value=' + id
					+ ' id=id_' + id + '><input type="hidden" value=' + id
					+ ' id=idflg_' + (index - 2) + '></td>';
			var newtd0 = '<td>'+(index - 1)+'</td>';
			
<%--			if(ddType=='shouhou'){--%>
<%--				newtd13 = "<td></td>";--%>
<%--				newtd12 = "<td></td>";--%>
<%--				newtd11 = "<td></td>";--%>
<%--				newtd10 = "<td></td>";--%>
<%--				newtd9 = "<td></td>";--%>
<%--				newtd8 = "<td></td>";--%>
<%--				newtd7 = "<td></td>";--%>
<%--				newtd6 = "<td></td>";--%>
<%--				newtd5 = "<td></td>";--%>
<%--				newtd4 = "<td></td>";--%>
<%--			}--%>
			$(newtd0).appendTo(newTr);
			$(newtd1).appendTo(newTr);
			$(newtd2).appendTo(newTr);
			$(newtd3).appendTo(newTr);
			$(newtd4).appendTo(newTr);
			$(newtd5).appendTo(newTr);
			$(newtd6).appendTo(newTr);
			$(newtd7).appendTo(newTr);
			$(newtd8).appendTo(newTr);
			$(newtd9).appendTo(newTr);
			$(newtd10).appendTo(newTr);
			$(newtd11).appendTo(newTr);
			$(newtd12).appendTo(newTr);
			$(newtd13).appendTo(newTr);
			$(newtd14).appendTo(newTr);
		}
		obj.disabled = "disabled";
		window.parent.chageDiv('none');
		window.parent.document.getElementById("sub").removeAttribute("disabled");
	}
	if (a > 0 && btu.value != '确定') {
		alert("请输入数量")
	}
	a++;
}
<%--var danwei;--%>
<%--var banben;--%>
<%--function getprocardTemplate(markId,name){--%>
<%--	$.ajax( {--%>
<%--		type : "POST",--%>
<%--		url : "ProcardTemplateAction!findprocardTemplateByMarkId.action",--%>
<%--		data : {--%>
<%--			markId:markId,--%>
<%--			name:name,--%>
<%--			pageStatus:'${param.status}'--%>
<%--		},--%>
<%--		dataType : "json",--%>
<%--		async : false,--%>
<%--		success : function(data) {--%>
<%--			if(data!=null){--%>
<%--				danwei = data.unit;--%>
<%--				banben = data.banBenNumber;--%>
<%--			}else if('${param.status}' == 'sz'){--%>
<%--				danwei = '';--%>
<%--				banben = '';--%>
<%--				alert('该总成件号未录入试制BOM')--%>
<%--			}else{--%>
<%--				danwei = '';--%>
<%--				banben = '';--%>
<%--				alert('该总成件号未录入批产BOM')--%>
<%--			}--%>
<%--		}--%>
<%--	})--%>
<%--}--%>
function vali() {
	var selectList = document.getElementsByName("selected");
	for ( var i = 0; i < selectList.length; i++) {
		if (selectList[i].checked) {
			return true;
		}
	}
	alert("请选择需要的产品！谢谢");
	return false;
}<%--function chageAllCheck(obj) {--%>
<%--	var inputs = document.getElementsByTagName("input");--%>
<%--	for ( var i = 0; i < inputs.length; i++) {--%>
<%--		inputs[i].checked = obj.checked;--%>
<%--	}--%>
<%--}--%>
</script>
	</body>
</html>
