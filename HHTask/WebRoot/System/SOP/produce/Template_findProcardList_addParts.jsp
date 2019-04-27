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
.dhlabel{
border-top:1px solid #000;
border-bottom: 1px solid #000;
border-left: 1px solid #000;
border-right: 1px solid #000;
margin-left: 5px;
margin-right: 5px;
padding: 3px 5px;
white-space: nowrap;
}
#fullbg1 {
	background-color: gray;
	left: 0;
	opacity: 0.5;
	position: absolute;
	top: 0;
	z-index: 3;
	filter: alpha(opacity =     50);
	-moz-opacity: 0.5;
	-khtml-opacity: 0.5;
}

#dialog1 {
	background-color: #fff;
	border: 5px solid rgba(0, 0, 0, 0.4);
	left: 50%;
	margin: -200px 0 0 -200px;
	padding: 1px;
	position: fixed !important; /* 浮动对话框 */
	position: absolute;
	top: 45%;
	width: 400px;
	height: 100px;
	z-index: 5;
	border-radius: 5px;
	display: none;
}

#xiugaiIframe1 {
	background-color: #fff;
	height: 75px;
	line-height: 24px;
	width: 400px;
}
</STYLE>

	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">   
			<div id="msg_div" style="color: red"></div>
			<div align="center" style="border: 1px solid #00000;">
				<form id="form" action="ProcardTemplateAction!findAllProcardTemp.action"
					method="post">
					<input type="hidden" name="pageStatus" value="${pageStatus}" />
					<table class="table">
						<tr>
							<th colspan="5">
								流水单模板管理(Single template water management)
							</th>
						</tr>
						<tr>
							<th>
								件号(Part No.):
							</th>
							<td>
								<input name="procardTemplate.markId"
									value="${procardTemplate.markId}" />
							</td>
							<th>
								名称(Name):
							</th>
							<td>
								<input name="procardTemplate.proName"
									value="${procardTemplate.proName}" />
							</td>
						</tr>
						<tr>
							<th>
								卡片类型(Card Type):
							</th>
							<td>
								<select name="procardTemplate.procardStyle"
									style="width: 155px;">
									<option>
										${procardTemplate.procardStyle}
									</option>
									<option></option>
									<option>
										总成
									</option>
									<option>
										外购
									</option>
									<option>
										自制
									</option>
									<option>
										待定
									</option>
								</select>
							</td>
							<th>
								产品类型(Product Type):
							</th>
							<td>
								<select name="procardTemplate.productStyle"
									style="width: 155px;">
									<s:if test="pageStatus=='view'">
										<option></option>
										<option value="批产">
											批产
										</option>
										<option value="试制">
											试制
										</option>
									</s:if>
									<s:elseif test="pageStatus=='sop'">
										<option value="试制">
											试制
										</option>
									</s:elseif>
									<s:else>
										<option value="批产">
											批产
										</option>
									</s:else>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								图号(other Name):
							</th>
							<td>
								<input name="procardTemplate.tuhao"
									value="${procardTemplate.tuhao}" />
							</td>
							<th>
								原材料图号(other name):
							</th>
							<td>
								<input name="procardTemplate.ytuhao"
									value="${procardTemplate.ytuhao}" />
							</td>
						</tr>
						<tr>
							<th>
								原材料牌号:
							</th>
							<td>
								<input name="procardTemplate.trademark"
									value="${procardTemplate.trademark}" />
							</td>
							<th>
								原材料规格:
							</th>
							<td>
								<input name="procardTemplate.specification"
									value="${procardTemplate.specification}" />
							</td>
						</tr>
						<tr>
							<th>
								业务件号:
							</th>
							<td>
								<input name="procardTemplate.ywMarkId"
									value="${procardTemplate.ywMarkId}" />
							</td>
							<th>
							</th>
							<td>
							</td>
						</tr>
						<tr>
							<th colspan="6">
								<input type="hidden" value="${tag}" name="tag">
								<input type="hidden" value="${type}" name="type"/>
								<input type="submit" value="查询" class="input" />
								<input type="reset" value="清空" class="input" />
							</th>
						</tr>
					</table>
				</form>
				<div id="rootTemplateDiv">
					<div id="showMessage" style="color: red; font-size: 14px; font-weight: bolder;">
					</div>
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
								<br />
								No.
							</th>
							<th align="center">
								件号/图号
								<br />
								Part No.
							</th>
							<th align="center">
								名称
								<br />
								Name
							</th>
							<th align="center">
								卡片类型
								<br />
								Card Type
							</th>
							<th align="center">
								产品类型
								<br />
								Product Type
							</th>
							<th align="center">
								总成件号/业务件号
								<br />
								Assembly Member
							</th>
							<th align="center">
								编制状态
								<br />
								edit status
							</th>
							<s:if test="pageStatus!=null">
								<th align="center">
									单件价格
									<br />
									Single price
								</th>
							</s:if>
							<th align="center">
									导入时间
									<br />
									Time
								</th>
							<th align="center">
								操作
								<br />
								Operation
							</th>
						</tr>
						<s:iterator value="procardTemplateList" id="pageProcardTem"
							status="pageindex">
							<s:if test="#pageindex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageindex.index+1" />
							</td>
							<td>
								${pageProcardTem.markId}
							</td>
							<td style="width: 180px;" align="left">
								${pageProcardTem.proName}
							</td>
							<td>
								${pageProcardTem.procardStyle}
							</td>
							<td>
								${pageProcardTem.productStyle}
							</td>
							<td  align="left">
								${pageProcardTem.rootMarkId}
							</td>
							<td>
								${pageProcardTem.bzStatus}
							</td>
							<s:if test="pageStatus!=null">
								<td align="center">
									${pageProcardTem.onePrice}
								</td>
							</s:if>
							<td>
									${pageProcardTem.daoruDate}
							</td>
							<td align="center" id="showStatus_${pageProcardTem.markId}">
								
								<input type="button" value="添加配件" id="markId_${pageProcardTem.markId}" onclick="showAddParts('${pageProcardTem.markId}')"/>
							</td>
						</tr>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="11" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="11" align="center" style="color: red">
							</s:else>
							</td>
						</tr>
					</table>
					
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

//显示添加配件窗口
function showAddParts(markId){
	var xiugaiIframe = window.parent.document.getElementById("xiugaiIframe");
	if(markId==''){
		$(xiugaiIframe).attr("src","${pageContext.request.contextPath}/ProcardTemplateAction!findAllProcardTem.action?pageStatus=lp&type=lp");
		chageDiv('block');
	}else{
		var tab = window.parent.document.getElementById("table");
		var index = tab.rows.length;
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/ProcardTemplateAction!getProcardByMarkId.action",
			dataType:"JSON",
			data:{
				markId:markId,
				pageStatus:"getYuanclAndWaigj"
			},
			success:function(data){
				var markId = data.markId;
				var proName = data.proName;
				var xbType;
				var hsPrice;
				var bhsPrice;
				var taxprice;
				var priceId=0;
				var unit; //= data.unit;本外购件库的单位改价格表里的单位
				var procardId1 = data.id;
				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath}/pieceNum_getPriceByMarkId.action",
					data:{
						numId:markId
					},
					async:false,
					dataType:"JSON",
					success:function(priceData){
						if(null!=priceData){
							xbType= priceData.type;
							priceId = priceData.id;
							taxprice = priceData.taxprice;
							hsPrice = priceData.hsPrice;
							bhsPrice = priceData.bhsPrice;
							unit = priceData.danwei;
						}
					}
				});
				if(null==xbType ){
					xbType='';
				}
				var bool = true;
				var markIdaa = window.parent.document.getElementsByClassName("markIdaa");
				for ( var i = 0; i < markIdaa.length; i++) {
					var idaa = markIdaa[i].value;
					if(idaa==markId){
						$("#showStatus_"+markId).html("<img src='${pageContext.request.contextPath}/images/success1.png' style='width:25px;height:25px' />");
						return;
					}
				}
				var aboutBh="";
				$.ajax( {
					type : "POST",
					url : "orderManager_getbfCount.action",
					data : {
							markId:markId	
					},
					async:false,
					dataType : "json",
					success : function(data) {
						if (data != null&&data>0) {
							aboutBh = "<input type='button' value='关联备货' onclick='toRelateBh("
								+ (index - 2) + ",\""+markId+"\")' >"
						}else{
							aboutBh = "无剩余"
						}
					}
				});	
				//----
				var newTr = tab.insertRow(index - 1);
				newTr.id = 'tr_' + (index - 1);
				var tr = window.parent.document.getElementById("tr_" + (index - 1));
				newTr.align = "center";
				var newtd14 = "<td><input type='button' value='删除' onclick='del("
						+ (index - 1) + ")'><input type='hidden' value='${param.markId}' name='pmList["+(index -2)+"].fmarkid' ></td>";
				var newtd13 = '<td><input type="text" name=pmList['+(index -2)+'].remark ></td>';
				var newtd12 = "<td><lable id='relateShow"+(index - 2)+"'></lable>"+aboutBh+"</td>";
				var newtd11 = '<td><input class="Wdate" type="text"  name=pmList['+(index -2)+'].paymentDate  ' +
						' onClick=" WdatePicker( { dateFmt : &apos;yyyy-MM-dd&apos;,skin : &apos;whyGreen&apos;});"    /></td>'
				var newtd10 = '<td>' + unit + '<input type="hidden" value='
						+ unit + ' name=pmList[' + (index - 2) + '].danwei></td>'
				if(priceId!=null && priceId!=0){
					if(taxprice==0 || taxprice == ''){
						taxprice = 0;
					}
					var newtd9 = "<td><a href='javascript:;' onclick='selectYclAndWgj("
						+ priceId + ",null,\"mingxi\")'>明细</a> <input type='hidden' value=" + priceId
						+ " name='pmList[" + (index - 2) + "].priceId'></td>"
					var newtd6 = '<td>'+bhsPrice+'<input type="hidden" value='+bhsPrice+' name=pmList['+(index -2)+'].bhsPrice /></td>'
					var newtd7 = '<td>'+ taxprice +   '%<input type="hidden" value='
						+ taxprice + ' name=pmList[' + (index - 2) + '].taxprice></td>';
					var newtd8 = "<td><span id='unitPriceText_" + (index - 2) + "'></span><input type='hidden'  name='pmList[" + (index - 2)
									+ "].unitPrice' id=unitPrice_" + (index - 2) + "></td>";//总价
					var newtd5 = "<td>"+hsPrice+"<input type='hidden' id='hsprice_"+(index - 2)+"' value='"+hsPrice+"' name='pmList["+(index-2)+"].unit' ></td>";
				}else{
					var newtd9 = "<td><span style='color:red' class='nonePrice'>无价格</span></td>";
					var newtd8 = "<td></td>";
					var newtd7 = "<td></td>";
					var newtd6 = "<td></td>";
					var newtd5 = "<td></td>";
				}
				if(priceId==0){
					priceId=index;
				}
				
				
				var newtd4 = "<td><input type='text' name='pmList[" + (index - 2) + "].num' id='sp_" + (index - 2) + "'" +
								" style='width:100px;' class='addCount' onchange='computeSumPrice(" + (index - 2) + ")'></td>";//数量
				
				var newtd3 = '<td>' + xbType + '<input type="hidden" value=' + xbType
						+ ' name="pmList[' + (index - 2) + '].type"></td>';
				var newtd2 = '<td>' + proName + '<input type="hidden" value=' + proName
						+ ' name="pmList[' + (index - 2) + '].name"></td>';
				var newtd1 = '<td>' + markId + '<input type="hidden" value='+ markId + ' name="pmList[' + (index - 2)
						+ '].pieceNumber" id="procardId1_'+procardId1+'" class="markIdaa"><input type="hidden" value=' + priceId
						+ ' id=id_' + priceId + '><input type="hidden" value=' + priceId
						+ ' id=idflg_' + (index - 2) + '></td>';
				var newtd0 = '<td>'+(index - 1)+'</td>';
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
				index++;
				$("#showStatus_"+markId).html("<img src='${pageContext.request.contextPath}/images/success1.png' style='width:25px;height:25px' />");
				window.parent.document.getElementById("sub").removeAttribute("disabled");
				alert('添加成功!~')
			}
		});
	}
}
</script>
	</body>
</html>
