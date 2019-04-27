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
		<div align="center">
			<s:if test='procard.productStyle=="批产"'>
				<div
					style="background-color: green; color: #ffffff; font-size: 14px;">
					批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产
					批产 批产 批产 批产 批产 批产
				</div>
			</s:if>
			<s:if test='procard.productStyle=="试制"'>
				<div
					style="background-color: yellow; color: #ffffff; font-size: 14px;">
					试制 试制 试制 试制 试制 试制 试制 试制 试制 试制 试制 试制 试制 试制 试制 试制 试制 试制 试制 试制 试制 试制
					试制 试制 试制 试制 试制 试制
				</div>
			</s:if>
			<h2>
				产品 ${procard.markId}
				<s:if test="procard.ywMarkId!=null">
									(<font color="green" style="font-weight: bolder;">${procard.ywMarkId}</font>)
									</s:if>
				领料申请明细
			</h2>
			<form action="sellAction!saveSellByCard.action" method="post"
				onkeydown="if(event.keycode==13)return false;"
				onsubmit="return validata();">
				<input type="hidden" name="procard.id" value="${procard.id}">
				<input type="hidden" id="listSize"
					value="<s:property value='list.size()'/>" />
				<s:if test="%{'barcode'==tag}">
					<input type="hidden" name="id" value="${sticker.id}">
				</s:if>
				<s:else>
					<input type="hidden" name="id" value="${runningWaterCard.id}">
				</s:else>
				<input type="hidden" name="tag" value="${tag}">
				<table width="90%" class="table">
					<tr>
						<th colspan="6">
							产品信息
						</th>
					</tr>
					<tr>
						<th>
							件号
						</th>
						<th>
							${procard.markId}
							<s:if test="procard.ywMarkId!=null">(<font color="green"
									style="font-weight: bolder;">${procard.ywMarkId}</font>)</s:if>
						</th>
						<th>
							批次
						</th>
						<th>
							${procard.selfCard}
						</th>
						<th>
							总数量
						</th>
						<th>
							${procard.filnalCount}
						</th>
					</tr>
					<tr>
						<th>
							制单人
						</th>
						<th>
							${procard.zhikaren}
						</th>
						<th>
							产品类型
						</th>
						<th>
							${procard.procardStyle}
						</th>
						<th>
							生产类型
						</th>
						<th>
							${procard.productStyle}
						</th>
					</tr>
					<s:if test="procard.trademark!=null&&procard.trademark!=''">
						<tr>
							<td rowspan="8" style="width: 5px;" align="center">
								原
								<br />
								<br />
								材
								<br />
								<br />
								料
							</td>
							<td width="15%">
								牌号
							</td>
							<td colspan="4">
								${procard.trademark}
							</td>
						</tr>
						<tr>
							<td>
								规格
							</td>
							<td colspan="4">
								${procard.specification}
							</td>
						</tr>
						<tr>
							<td>
								原材料名称
							</td>
							<td colspan="4">
								${procard.yuanName}
							</td>
						</tr>
						<tr>
							<td>
								数量(权值)
							</td>
							<td colspan="4">
								${procard.quanzi1} : ${procard.quanzi2}
								<font color="red">(消耗量:${procard.xiaohaoCount})</font>
							</td>
						</tr>
						<tr>
							<td>
								单张出件
							</td>
							<td colspan="4">
								<fmt:formatNumber type="number"
									value="${procard.bili/procard.quanzi2}" maxFractionDigits="0" />
								${procard.unit}
							</td>
						</tr>
						<tr>
							<td>
								单位
							</td>
							<td colspan="4">
								${procard.yuanUnit}
							</td>
						</tr>
						<tr>
							<td>
								单张重量
							</td>
							<td colspan="4">
								${procard.bili}
							</td>
						</tr>
						<tr>
							<td>
								长/宽/高
							</td>
							<td colspan="4">
								${procard.thisLength}/${procard.thisWidth}/${procard.thisHight}
							</td>
						</tr>
					</s:if>
					<tr>
						<th colspan="10">
							领料信息
						</th>
					</tr>
					<s:if test="procard.isZhHasYcl()">

						<tr style="font-size: 14px; font-weight: bolder;">
							<td colspan="6" align="center">
								<s:if test="ckType=='原材料库'">
									<input type="hidden" id="ckTag" value="yclk" />
									<input type="radio" id="yclk" name="ckType"
										onclick="checkCount()" value="原材料库" checked="checked" />
									<label for="yclk">
										原材料库
									</label>&nbsp;&nbsp;&nbsp;
					<input type="radio" id="wgjK" name="ckType" onclick="checkCount()"
										value="外购件库" />
									<label for="wgjK">
										外购件库
									</label>
								</s:if>
								<s:else>
									<input type="hidden" id="ckTag" value="wgjK" />
									<input type="radio" id="yclk" name="ckType"
										onclick="checkCount()" value="原材料库" />
									<label for="yclk">
										原材料库
									</label>&nbsp;&nbsp;&nbsp;
					<input type="radio" id="wgjK" name="ckType" onclick="checkCount()"
										value="外购件库" checked="checked" />
									<label for="wgjK">
										外购件库
									</label>
								</s:else>
							</td>
						</tr>
					</s:if>
				</table>
				<table class="table" width="90%" id="processTable">
					<tr>
						<th colspan="10">
							<table class="table">
								<tr>
									<s:if
										test="%{'自制'==procard.procardStyle||'单交件'==procard.danjiaojian}">
										<th colspan="3" align="center" style="border-right: hidden;">
											发料信息
										</th>
										<th colspan="2" align="center" style="border-right: hidden;">
											自制：原材料
											<font color="red">${procard.quanzi1} :
												${procard.quanzi2}</font>
										</th>
									</s:if>
									<s:else>
										<th colspan="4" align="center" style="border-right: hidden;">
											发料信息
										</th>
									</s:else>
									<th colspan="1" align="center" style="border-right: hidden;">
										可领数量:
										<FONT color="red"><s:property value='procard.klNumber' />
										</FONT>
									</th>
									<th colspan="1" align="center" style="border-right: hidden;">
										剩余未领数量:
										<FONT color="red"><label id='weiling'>
												<s:if test="procard.isZhHasYcl()&&ckType=='原材料库'">
													<s:property value='procard.yhascount' />
												</s:if>
												<s:else>
													<s:property value='procard.hascount' />
												</s:else>
											</label> </FONT>
									</th>
									<th colspan="1" align="center" style="border-right: hidden;">
										仓库可领数量:
										<FONT color="red"><s:property value='procard.ckCount' />
										</FONT>
									</th>
									<th style="border-right: hidden;"></th>
									<th colspan="2" align="right">
										本次预领数量
										<s:if
											test="%{'自制'==procard.procardStyle||'单交件'==procard.danjiaojian}">
											<input type="text" id="getCount" name="getCount"
												value="<s:property value='procard.getCount'/>"
												onkeyup="checkCount()">
										</s:if>
										<s:else>
											<input type="text" id="getCount" name="getCount"
												value="<s:property value='procard.ckCount'/>"
												onkeyup="checkCount()">
										</s:else>
										<input type="hidden" id="hasCount" name="procard.hascount"
											value="<s:property value='procard.hascount'/>">
									</th>
								</tr>
							</table>
						</th>
					</tr>
					<tr bgcolor="#c0dcf2" height="30px"
						style="border-collapse: separate;">
						<th align="center">
							序号
						</th>
						<th align="center">
							<s:if
								test="%{'自制'==procard.procardStyle||'单交件'==procard.danjiaojian}">
							库别
						</s:if>
							<s:else>
							批次
						</s:else>
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							名称
						</th>
						<th align="center">
							规格
						</th>
						<th align="center">
							<s:if
								test="%{'自制'==procard.procardStyle||'单交件'==procard.procardStyle}">
							请领数量
						</s:if>
							<s:else>
							库位
						</s:else>
						</th>
						<s:if
							test="('组合'==procard.procardStyle||'总成'==procard.procardStyle)&&procard.lingliaoType=='part'">
							<th align="center">
								缺领数量
							</th>
						</s:if>
						<th align="center">
							实发数量
						</th>
						<s:if
							test="%{'自制'!=procard.procardStyle&&'单交件'!=procard.danjiaojian}">
							<th align="center">
								单位
							</th>
						</s:if>
						<th align="center">
							可用在制品数
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:if test="list.size()>0">
						<s:iterator value="list" status="se" id="goods">
							<s:if test="#se.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#se.index+1" />
							</td>

							<td>
								<s:if
									test="%{'自制'==procard.procardStyle||'单交件'==procard.danjiaojian}">
								${goods.goodsClass}
							</s:if>
								<s:else>
								${goods.goodsLotId}
							</s:else>
							</td>
							<td>
								${goods.goodsMarkId}
							</td>
							<td>
								${goods.goodsFullName}
							</td>
							<td>
								${goods.goodsFormat}
							</td>
							<td>
								<s:if
									test="%{'自制'==procard.procardStyle||'单交件'==procard.danjiaojian}">
									<s:if test="%{#goods.goodsZhishu>=0}">
										<label>
											${goods.goodsZhishu}
										</label>
										<input type="hidden" name="qingling"
											value="${goods.goodsZhishu}" size="5px;" />/${goods.qlUnit}<font
											color="red">(${goods.goodsZhishu+good.ylshifa}-${goods.ylshifa})</font>
										<input id="ylshifa" type="hidden" value="${goods.ylshifa}" />
									</s:if>
									<s:else>
										<label>
											0
										</label>
										<input type="hidden" name="qingling" value="0" size="5px;" />/${procard.yuanUnit}</s:else>
								</s:if>
								<s:else>
								${goods.goodsPosition}
							</s:else>
							</td>
							<s:if
								test="('组合'==procard.procardStyle||'总成'==procard.procardStyle)&&procard.lingliaoType=='part'">
								<td align="center">
									${goods.qlCount}
								</td>
							</s:if>
							<td>
								<s:if
									test="%{'自制'==procard.procardStyle||'单交件'==procard.danjiaojian}">
									<s:if test="%{#goods.goodsBeginQuantity>procard.hascount}">
										<input type="text" name="shifa" id="shifa" readonly="readonly"
											value="${goods.goodsCurQuantity}" size="5px;" />/${goods.goodsUnit}
								  </s:if>
									<s:elseif test="%{'管料'==#goods.goodstype}">
										<s:if test="%{-1==#goods.goodsZhishu}">
											<input type="text" name="shifa" id="shifa"
												readonly="readonly" value="${goods.goodsCurQuantity}"
												size="5px;" />/${goods.goodsUnit}
								   </s:if>
										<s:else>
											<input type="text" name="shifa" id="shifa"
												onkeyup="checkshifacount('${goods.goodsMarkId}','${goods.goodsFormat}',<s:property value='#se.index+1' />)"
												value="${goods.goodsCurQuantity}" size="5px;" />/${goods.goodsUnit}
								   </s:else>
									</s:elseif>
									<s:else>
										<s:if test="%{-1==#goods.goodsZhishu}">
											<input type="text" name="shifa" id="shifa"
												value="${goods.goodsCurQuantity}" size="5px;" />/${goods.goodsUnit}
								  </s:if>
										<s:else>
											<input type="text" name="shifa" id="shifa"
												value="${goods.goodsCurQuantity}" size="5px;" />/${goods.goodsUnit}
								  </s:else>
									</s:else>


								</s:if>
								<s:else>
									<s:if test="%{-1!=#goods.goodsCurQuantity}">
								${goods.goodsCurQuantity}
								</s:if>
									<s:else>
								${goods.ckCount}
								</s:else>
								</s:else>

							</td>
							<s:if
								test="%{'自制'!=procard.procardStyle&&'单交件'!=procard.danjiaojian}">
								<td>
									${goods.goodsUnit}
								</td>
							</s:if>
							<td>
								<label id="zaizhi">
									<font color="red" size="6">${goods.goodsBeginQuantity}</font>
								</label>
							</td>
							<td>
								<s:if test="#goods.goodsId==null">
									<input type="hidden" disabled="disabled"
										class="gid<s:property value="#goods.goodsMarkId"/>"
										id="goodsId<s:property value='#se.index+1' />" name="selected"
										value="0">
								</s:if>
								<s:else>
									<input type="hidden" disabled="disabled"
										class="gid<s:property value="#goods.goodsMarkId"/>"
										id="goodsId<s:property value='#se.index+1' />" name="selected"
										value="${goods.goodsId}">
								</s:else>
								<s:if
									test="%{'自制'==procard.procardStyle||'单交件'==procard.danjiaojian">
									<s:if
										test="%{#goods.goodsZhishu>=0||#goods.goodsBeginQuantity>procard.hascount}">
										<div id="B<s:property value='#se.index+1' />"
											style="display: block">
											<input type="button" value="确认"
												onClick="confirmGoods(<s:property value='#se.index+1' />,'<s:property value='#goods.goodstype' />','<s:property value='#goods.goodsMarkId' />','<s:property value='#goods.goodsFormat' />')">
										</div>
										<div id="H<s:property value='#se.index+1' />"
											style="display: none">
											确认
										</div>
									</s:if>
									<s:else>
										<div>
											<font color="red">库存不够</font>
										</div>
									</s:else>
								</s:if>
								<s:else>
									<s:if test="%{#goods.goodsBeginQuantity>0}">
										<div id="B<s:property value='#se.index+1' />"
											style="display: block">
											<input type="button" value="确认"
												onClick="confirmGoods(<s:property value='#se.index+1' />,'<s:property value='#goods.goodstype' />','<s:property value='#goods.goodsMarkId' />','<s:property value='#goods.goodsFormat' />')">
										</div>
										<div id="H<s:property value='#se.index+1' />"
											style="display: none">
											确认
										</div>
									</s:if>
									<s:elseif
										test="%{#goods.goodsCurQuantity>0||procard.lingliaoType=='part'}">
										<div id="B<s:property value='#se.index+1' />"
											class="btn<s:property value="#goods.goodsMarkId"/>"
											style="display: block">
											<input type="button" value="确认"
												onClick="confirmGoods(<s:property value='#se.index+1' />,'<s:property value='#goods.goodstype' />','<s:property value='#goods.goodsMarkId' />','<s:property value='#goods.goodsFormat' />','<s:property value="#goods.goodsMarkId"/>')">
										</div>
										<div id="H<s:property value='#se.index+1' />"
											class="fbtn<s:property value="#goods.goodsMarkId"/>"
											style="display: none">
											确认
										</div>
									</s:elseif>
									<s:else>
										<div>
											<font color="red">库存不够</font>
										</div>
									</s:else>
								</s:else>

							</td>
							</tr>
						</s:iterator>
						<tr>
							<th colspan="12" style="color: red;">
								<s:if test="message!='true'">
									${message}
								</s:if>
							</th>
						</tr>
						<tr>
							<td colspan="15" align="center">
								<s:if test="list.size()==0">
									<span id="butt" align="top;">领料人刷卡: <input type="text"
											name="procard.lingliaoren" id="lingliaoren" /> <br /> <input
											class="input" type="submit" value="提交" id="sbtn" /> </span>
								</s:if>
								<s:else>
									<span id="butt" align="top;">领料人刷卡: <input type="text"
											name="procard.lingliaoren" id="lingliaoren" /> <br /> <input
											class="input" type="submit" value="提交" id="sbtn" /> </span>
								</s:else>
							</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<th colspan="12" style="color: red;">
								<s:if test="message!='true'">
									${message}
								</s:if>
							</th>
						</tr>

					</s:else>

				</table>

			</form>
		</div>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
var count = $("#listSize").val();
var sureCount = 0;
function confirmGoods(obj, type, goodsMarkId, goodsFormat, btnclass) {
	var hasCount = $("#hasCount").val();//lingliaoren
	var getCount = $("#getCount").val();
	var zaizhi = $("#zaizhi").text();
if((hasCount-getCount)<0){
		if(${procard.procardStyle=='总成'}||${procard.procardStyle=='组合'}&&${procard.lingliaoType=='part'}){
			}else{
		     alert("领取数量超过剩下数量");
			return;
			}
	}
	var bid = "B" + obj;
	var hid = "H" + obj;
	var bbtn = document.getElementById(bid);
	var hbtn = document.getElementById(hid);
	var procardStyle="${procard.procardStyle}";
	var danjiaojian="${procard.danjiaojian}";
	if(procardStyle=='自制'||danjiaojian=='单交件'){
		var shifa=$("#shifa").val();
		if((zaizhi-getCount)<0&&(shifa==null||shifa==0)){
			var ylshifa = $("#ylshifa").val();
			if(ylshifa!=null&&ylshifa>0){
			}else{
			 alert("实发不能为0");
			 $("#shifa").focus();
			 return;
			}
		}
		if(checkshifacount(goodsMarkId,goodsFormat,obj)==false){
			sureCount++;
			return ;
		}
	}
	if(procardStyle=='自制'){
		$("#goodsId"+obj).removeAttr("disabled");
		bbtn.style.display = "none";
		hbtn.style.display = "block";
		sureCount++;
		count--;
	}else{
		if(btnclass!=null&&btnclass.length>1){
			$(".gid"+btnclass).removeAttr("disabled");
			bbtn.style.display = "none";
			hbtn.style.display = "block";
			var len=$(".btn"+btnclass).length;
			sureCount=sureCount+len;
			count=count-len;
		}else{
			$("#goodsId"+obj).removeAttr("disabled");
			bbtn.style.display = "none";
			hbtn.style.display = "block";
			sureCount++;
			count--;
		}
	}
	
	$("#shifa").attr("readonly","readonly");
	
//	if (0 == count) {
//		document.getElementById("butt").style.display = "block";
//	}
	$("#lingliaoren").focus();
}
//核实实发数量
function checkshifacount(goodsMarkId,goodsFormat,obj){
	var shifa=$("#shifa").val();
	var getCount=$("#getCount").val();
	 var zaizhi=$("#zaizhi").text();
	 var st=false;
	 if(zaizhi-getCount>=0){
		document.getElementById("shifa").value=0;
	 }
	$.ajax({
		type : "POST",
		url : "sellAction!checkshifacount.action",
		dataType : "json",
		async : false,
		data : {
		   goodsMarkId : goodsMarkId,
		   goodsFormat : goodsFormat,
		   shifa : shifa
		},
	   success : function(data){
		 if(!data.success){
			 var bid = "B" + obj;
	         var hid = "H" + obj;
	         var bbtn = document.getElementById(bid);
	         var hbtn = document.getElementById(hid);
	         bbtn.style.display = "none";
	         hbtn.style.display = "block";
	        // alert("库存不足！");
		 }else{
			 var bid = "B" + obj;
	         var hid = "H" + obj;
//	         var bbbtn = document.getElementById(bid);
//	         var hhbtn = document.getElementById(hid);
//	         bbbtn.style.display = "block";
//	         hhbtn.style.display = "none";
			$("#"+bid).show();
			$("#"+hid).hide();
			 st= true;
		 }
	   }
	});
	return st;
}
function gosearch() {
	if (window.event.keyCode == 13) {
		return false;
	}

}
function checkCount(){
	var procardStyle="${procard.procardStyle}";
	var danjiaojian="${procard.danjiaojian}";
	var getCount=$("#getCount").val();
	var isZhYcl = "<s:property value='procard.isZhHasYcl()'/>";
	var ckType="";
	var ckTag="";
	if(procardStyle=='自制'||danjiaojian=='单交件'){
		ckType="原材料库";
	}else if(isZhYcl=="true"){
		ckType=$('input:radio[name="ckType"]:checked').val();
		ckTag = $("#ckTag").val();
		if(ckType=="外购件库"){
			if(getCount==0){
			$("#getCount").val($("#hasCount").val());
			getCount=$("#hasCount").val();
		}
		}
	}else{
		ckType="外购件库";
		if(getCount==0){
			$("#getCount").val($("#hasCount").val());
			getCount=$("#hasCount").val();
		}
	}
	if(isNaN(getCount)){
		alert("请输入数字");
		return ;
	}else{
		var hasCount=$("#hasCount").val();
		if(hasCount==null||hasCount==""){
			hasCount=${procard.filnalCount};
		}
		if(getCount==0){
			var len=$("#processTable>tbody>tr").length;
					for(var i=0;i<len;i++){
						if(i>=2){
						 $("#processTable>tbody>tr").eq(2).remove();	
						}
					}
			if(${procard.procardStyle=='总成'}||${procard.procardStyle=='组合'}&&${procard.lingliaoType=='part'}){
			}else{
			return;
				
			}
		}else if((getCount-hasCount)>0){
			if(${procard.procardStyle=='总成'}||${procard.procardStyle=='组合'}&&${procard.lingliaoType=='part'}){
			}else{
		     alert("领取数量超过剩下数量");
			return;
			}
			
		}
			$.ajax({
				type : "POST",
				url : "sellAction!procardLingliaonew.action",
				dataType : "json",
				async: false,
				data : {
				  id : "${procard.id}",
				  getCount : getCount,
				  ckType : ckType
				},
				success : function(data){
					if(data.indexOf("对不起")== 0){
						alert(data);
						if(isZhYcl=="true"){
							$("#"+ckTag).attr("checked","checked");
						}
						return;
					}else{
						if(isZhYcl=="true"){
							if(ckType=="原材料库"){
								$("#ckTag").val("yclk");
								$("#weiling").html(${procard.yhascount});
								var ckCount="${procard.ckCount}";
								if(($("#getCount").val()-ckCount)>0){
									$("#getCount").val(ckCount);
								}
							}else{
								$("#ckTag").val("wgjK");
								$("#weiling").html(${procard.hascount-procard.yhascount}+" (${procard.hascount}-${procard.yhascount})");
								$("#getCount").val(${procard.hascount-procard.yhascount});
							}
						}
					}
					count=data.length;
					var len=$("#processTable>tbody>tr").length;
					var afterIndex=1;
					for(var i=0;i<len;i++){
						if(i>=2){
						 $("#processTable>tbody>tr").eq(2).remove();	
						}
					}
			      
			       var count1 =0;
			       var html;
			       var hascount="${procard.hascount}";
			       var ph="${procard.trademark}";
			       $.each(data,function(i,good){
			    	   if(good.goodsPosition!="在制品"){
			    	   }
			    	   var zs=good.goodsZhishu;
			    	   if(zs==null){
			    		   zs=0;
			    	   }
			    	   var goodid=good.goodsId;
			    	   if(goodid==null){
			    		   goodid=0;
			    	   }
			    	   if(good.goodsZhishu==-1){
			    		   alert(good.goodsMarkId+"材料不足("+good.goodsFormat+")");
			    		   return;
			    	   }
			    	   if(procardStyle=="组合"&&ph!=null&&ph.length>0&&i==0){
			    		   $("#processTable>tbody>tr").eq(1).remove();	
			    		    afterIndex=0;
			    		   html=html+"<tr bgcolor='#c0dcf2' height='30px' style='border-collapse: separate;'>" +
			    		 "<th align='center'>序号</th>" +
			    		 "<th align='center'>库别</th>" +
			    		 "<th align='center'>件号</th>" +
			    		 "<th align='center'>名称</th>" +
			    		 "<th align='center'>规格</th>" +
			    		 "<th align='center'>请领数量</th>" +
			    		 "<th align='center'>实发数量</th>" +
			    		 "<th align='center'>单位</th>" +
			    		 "<th align='center'>可用在制品数</th>" +
			    		 "<th align='center'>操作</th>"  +
			    		 "</tr>";
			    	   }
			    	   if(procardStyle=="组合"&&ph!=null&&ph.length>0&&good.goodsClass=="原材料库"){
			    		   var html2="";
			    			   if(good.goodsBeginQuantity>=getCount){
			    			     html2= "<input type='text' name='shifa' id='shifa' readonly='readonly' value='0' size='5px;' />/"+good.goodsUnit;
			    			   }else if("nogl"==good.goodstype){
			    			      html2="<input type='text' name='shifa' id='shifa'  value='0' size='5px;' />/"+good.goodsUnit;
			    		        }else{
			    			        html2="<input type='text' name='shifa' id='shifa' onkeyup='checkshifacount(\""+good.goodsMarkId+"\",\""+good.goodsFormat+"\","+(i+1)+")' value='"+good.goodsCurQuantity+"' size='5px;' />/"+good.goodsUnit;
			    		        }
			    	    if(i%2==1){
			    		   html=html+"<tr align='center' onmouseover='chageBgcolor(this)' onmouseout='outBgcolor(this,\"\")'>" ;
			    	   }else{
			    		   html=html+"<tr align='center' bgcolor='#e6f3fb' onmouseover='chageBgcolor(this)' onmouseout='outBgcolor(this,\"#e6f3fb\")'>";
			    	   }
			    	    var fName=good.goodsFullName;
			    	    if(good.goodsFullName==null||good.goodsFullName=="null"||good.goodsFullName.length==0){
			    	    	fName="";
			    	    }
			    	   html=html+
			    	    "<td> "+(i+1)+" </td> " +
			    	    "<td> "+good.goodsClass+" </td> " +
			    	    "<td>"+good.goodsMarkId+"</td> " +
			    	    "<td>"+fName+"</td> " +
			    	    "<td>"+good.goodsFormat+" </td>" +
			    	    "<td> <label>"+zs+"</label> <input type='hidden' name='qingling' value='"+zs+"' size='5px;' />" +
			    	      "/<s:if test='procard.yuanUnit!=null'>"+good.qlUnit+"</s:if> </td>" +
			    	      "<td>" +html2+
			    	    "</td><td> "+good.goodsUnit+"</td>" +
			    	    "<td><label id='zaizhi'><font color='red' size='6'>"+good.goodsBeginQuantity+"</font></label></td>" +
			    	    "<td><input type='hidden' disabled='disabled' class='gid"+good.goodsMarkId+"' id='goodsId"+(i+1)+"' name='selected' value='"+goodid+"' ><div id='B"+(i+1)+"' style='display: block'>" +
			    	           "<input type='button' value='确认' onClick='confirmGoods("+(i+1)+",\""+good.goodstype+"\",\""+good.goodsMarkId+"\",\""+good.goodsFormat+"\")'> </div>"+
			    	           "<div id=\"H"+(i+1)+"\" style='display: none'>确认</div> </td>" +
			    	           "</tr>";
			           count1++;
			    	   }else{
			    	   var b=good.goodsBeginQuantity>=getCount;
			    		   var html2="";
			    		   if("自制"=='${procard.procardStyle}'||'${procard.danjiaojian}'=="单交件"){
			    			   if(good.goodsBeginQuantity>=getCount){
			    			     html2= "<input type='text' name='shifa' id='shifa' readonly='readonly' value='0' size='5px;' />/"+good.goodsUnit;
			    			   }else if("nogl"==good.goodstype){
			    			      html2="<input type='text' name='shifa' id='shifa'  value='0' size='5px;' />/"+good.goodsUnit;
			    		        }else{
			    			        html2="<input type='text' name='shifa' id='shifa' onkeyup='checkshifacount(\""+good.goodsMarkId+"\",\""+good.goodsFormat+"\","+(i+1)+")' value='"+good.goodsCurQuantity+"' size='5px;' />/"+good.goodsUnit;
			    		        }
			    		   }else{
			    			   html2=good.goodsCurQuantity;
			    		   }
			    	    if(i%2==1){
			    		   html=html+"<tr align='center' onmouseover='chageBgcolor(this)' onmouseout='outBgcolor(this,\"\")'>" ;
			    	   }else{
			    		   html=html+"<tr align='center' bgcolor='#e6f3fb' onmouseover='chageBgcolor(this)' onmouseout='outBgcolor(this,\"#e6f3fb\")'>";
			    	   }
			    	   html=html+
			    	    "<td> "+(i+1)+" </td> " +
			    	    "<td> <s:if test='%{\"自制\"==procard.procardStyle||procard.danjiaojian==\"单交件\"}'>"+good.goodsClass+"</s:if> " +
			    	    "<s:else>"+good.goodsLotId+"</s:else> </td> " +
			    	    "<td>"+good.goodsMarkId+"</td> " +
			    	    "<td>"+good.goodsFullName+"</td> " +
			    	    "<td>"+good.goodsFormat+" </td>" +
			    	    "<td> <s:if test='%{\"自制\"==procard.procardStyle||procard.danjiaojian==\"单交件\"}'><label>"+zs+"</label> <input type='hidden' name='qingling' value='"+zs+"' size='5px;' />" +
			    	      "/"+good.qlUnit+" </s:if>" +
			    	      "<s:else>"+good.goodsPosition+"</s:else><font color='red'>("+(zs+good.ylshifa)+"-"+good.ylshifa+")</font><input id='ylshifa' type='hidden' value='"+good.ylshifa+"'/> </td>" +
			    	      " <s:if test='(\"组合\"==procard.procardStyle||\"总成\"==procard.procardStyle)&&procard.lingliaoType==\"part\"'>" +
			    	      "<td align='center'>"+good.qlCount+"</td></s:if>" +
			    	      "<td>" +html2+
			    	    "</td><s:if test='%{\"自制\"!=procard.procardStyle&&procard.danjiaojian!=\"单交件\"}'><td> "+good.goodsUnit+"</td></s:if>" +
			    	    "<td><label id='zaizhi'><font color='red' size='6'>"+good.goodsBeginQuantity+"</font></label></td>" +
			    	    "<td><input type='hidden' id='goodsId"+(i+1)+"' disabled='disabled' name='selected' class='gid"+good.goodsMarkId+"' value='"+goodid+"' ><div id='B"+(i+1)+"' class='btn"+good.goodsMarkId+"' style='display: block'>" +
			    	           "<input type='button' value='确认'  onClick='confirmGoods("+(i+1)+",\""+good.goodstype+"\",\""+good.goodsMarkId+"\",\""+good.goodsFormat+"\",\""+good.goodsMarkId+"\")'> </div>"+
			    	           "<div class='fbtn"+good.goodsMarkId+"' id=\"H"+(i+1)+"\" style='display: none'>确认</div> </td>" +
			    	           "</tr>";
			           count1++;
			    	   }
			       });
			       if(count1<0){
			    	   return ;
			       }
			       if(count1>0){
			    	   html=html+"<tr> <th colspan='15' style='font-size: 15px; color: red;'> " +
			    	   "<span id='butt'  align='top;'>领料人刷卡: <input type='text' name='procard.lingliaoren' id='lingliaoren'/>" +
			    	   " <br /> <input class='input' type='submit' value='提交' /> </span>" +
			    	   "</th> </tr>";
			       }else{
			    	 html=html+"<tr> <th colspan='15' style='font-size: 15px; color: red;'> " +
			    	   "<span id='butt' align='top;'>领料人刷卡: <input type='text' name='procard.lingliaoren' id='lingliaoren' onchange='getCraName(this.value)'/>" +
			    	   " <br /> <input class='input' type='submit' value='提交' /> </span>" +
			    	   "</th> </tr>";
			       }
			       $("#processTable>tbody>tr").eq(afterIndex).after(html);
				}
			});
	}
	
}
$(document).ready(function(){
	var ckCount="<s:property value='procard.ckCount'/>";
	var ckType ="${ckType}";
	var isZhYcl = "<s:property value='procard.isZhHasYcl()'/>";
	var msg = "${message}";
	if(msg.indexOf("对不起")>0){
		alert(msg);
		return false;
	}
	if(ckCount!=null&&ckCount.length>0){
		if(ckCount>0){
			ckCount=parseInt(ckCount);
			if((ckCount-$("#getCount").val())<0){
				$("#getCount").val(ckCount);
   				checkCount();
			}else if(ckType=="原材料库"&&isZhYcl=="true"){
				checkCount();
			}
		}
	}
})
	function validata(){
		$("#sbtn").attr("disabled","disabled");
		var lingliaoren=$("#lingliaoren").val();
		if(sureCount==0){
			alert("您至少要选择领一种料！");
			return false;
		}
		if(lingliaoren==null||lingliaoren==""){
			alert("您还没有输入领料人！");
			return false;
		}
	}
$(function(){
				//获取窗口高度 
	var thisheight = document.body.scrollHeight;
	var thisheight2 = parseFloat(thisheight);
	var main = $(window.parent.document).find("#showLingLiaoIf");//找到iframe对象
	main.height(thisheight2+100);//为iframe高度赋值如果高度小于500，则等于500，反之不限高，自适应
})
</script>
	</body>
</html>
