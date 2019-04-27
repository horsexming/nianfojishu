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
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<div align="center">
			<%@include file="/util/sonTop.jsp"%>
			<s:if test='procard.productStyle=="批产"'>
				<div
					style="background-color: green; color: #ffffff; font-size: 14px;">
					批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产
					批产 批产 批产 批产 批产 批产手机手机手机手机手机手机手机手机 ${cardNumber}
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
			<form action="sellAction!saveSellByCardnew.action" method="post"
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
					
					<tr>
						<th colspan="16">
							领料信息
						</th>
					</tr>
				</table>
				<table class="table" width="90%" id="processTable">
					<tr>
						<th colspan="17">
							<table class="table">
								<tr>
									<th colspan="4" align="center" style="border-right: hidden;">
											发料信息
									</th>
									<th colspan="1" align="center" style="border-right: hidden;">
										可领数量:
										<FONT color="red"><s:property value='procard.klNumber' />
										</FONT>
									</th>
									<th colspan="1" align="center" style="border-right: hidden;">
										剩余未领数量:
										<FONT color="red"><label id='weiling'>
													<s:property value='procard.hascount' />
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
											<input type="text" id="getCount" name="getCount"
												value="<s:property value='procard.getCount'/>"
												onkeyup="checkCount()"><font color="red">(已配套数量:<s:property value='procard.klNumber-procard.hascount' />)</font>
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
							件号
						</th>
						<th align="center">
							批次
						</th>
						<th align="center">
							名称
						</th>
						<th align="center">
							规格
						</th>
						<th align="center">
							供料属性
						</th>
						<th align="center">
							库别
						</th>
						<th align="center">
							仓区
						</th>
						<th align="center">
							库位
						</th>
						<th align="center">
							应需数量 
							</br>(比例)
						</th>
						<th align="center">
							外委数量
						</th>
						<th align="center">
							已领数量
						</th>
						<th align="center">
							可发数量 
						</th>
						<th align="center">
							库存量 
						</th>
						<th align="center">
							请领数量
						</th>
						<th align="center">
							实发数量
						</th>
						<th align="center">
							单位
						</th>
<%--						<th align="center">--%>
<%--							可用在制品数--%>
<%--						</th>--%>
						<th align="center">
							操作
						</th>
					</tr>
					<s:if test="list.size()>0">
						<tr><th align="center" colspan="18" bgcolor="gray">在制品充领</th></tr>
						<s:set value="0"   name="zzclIndex"></s:set>
						<s:iterator value="list" status="zzclse" id="zzclgoods">
						  <s:if test="#zzclgoods.showType=='在制品充领'">
							<s:if test="#zzclIndex%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#zzclIndex+1" />
							</td>
							<td>
								<input type="hidden" value="${zzclgoods.goodsId}" name="goodsList[<s:property value="#zzclse.index" />].goodsId">
								${zzclgoods.goodsMarkId}
							</td>
							<td>
								${zzclgoods.goodsLotId}
							</td>
							<td>
								${zzclgoods.goodsFullName}
							</td>
							<td>
								${zzclgoods.goodsFormat}
							</td>
							<td>
								/
							</td>
							<td>
								${zzclgoods.goodsClass}
							</td>
							<td>
								${zzclgoods.goodHouseName}
							</td>
							<td>
								${zzclgoods.goodsPosition}
							</td>
							<td>
								${zzclgoods.xqCount} 
								<br>
								<font color="red">(${zzclgoods.quanzi1}:${zzclgoods.quanzi2})</font>
							</td>
							<td>
								${zzclgoods.qlCount}
							</td>
							<td>
								${zzclgoods.hqlCount}
							</td>
							<td>
								<label id="zaizhi">
<%--									${zzclgoods.kfCount}/${zzclgoods.goodsUnit}--%>
									${zzclgoods.goodsBeginQuantity}/${zzclgoods.goodsUnit}
								</label>
							</td>
							<td>
								<label id="zaizhi">
									${zzclgoods.kfCount}/${zzclgoods.goodsUnit}
<%--									${zzclgoods.goodsBeginQuantity}/${zzclgoods.goodsUnit}--%>
								</label>
							</td>
							<td>
									<s:if test="%{#zzclgoods.goodsZhishu>=0}">
										<label>
											${zzclgoods.goodsZhishu}
										</label>
										<input type="hidden" name="goodsList[<s:property value="#zzclse.index" />].goodsZhishu"
											value="${zzclgoods.goodsZhishu}" size="5px;" />/${zzclgoods.qlUnit}
										<input id="ylshifa<s:property value="#zzclse.index" />" name="goodsList[<s:property value="#zzclse.index" />].ylshifa" type="hidden" value="${zzclgoods.ylshifa}" />
									</s:if>
									<s:else>
										<label>
											0
										</label>
										<input type="hidden" name="goodsList[<s:property value="#zzclse.index" />].goodsZhishu" value="0" size="5px;" />/${zzclgoods.qlUnit}</s:else>
							</td>
							<td>
							<s:if test="#zzclgoods.isChangeSf">
							<input type="text" name="goodsList[<s:property value="#zzclse.index" />].goodsCurQuantity" id="shifa<s:property value="#zzclse.index" />" 
										onkeyup="checkshifacount(<s:property value="#zzclse.index" />,'<s:property value="#zzclgoods.goodsMarkId" />')"	value="${zzclgoods.goodsCurQuantity}" size="5px;" />
							</s:if>
							<s:else>
								<input type="text" name="goodsList[<s:property value="#zzclse.index" />].goodsCurQuantity" id="shifa" value="${zzclgoods.goodsCurQuantity}" readonly="readonly" size="5px;" />
							</s:else>
							</td>
							<td>
							${zzclgoods.goodsUnit}
							</td>
							<td>
								<s:if test="#zzclgoods.goodsId==null">
									<input type="hidden" disabled="disabled" class="gid<s:property value="#zzclgoods.flag"/>"
										id="goodsId<s:property value='#zzclse.index' />" name="selected"
										value="0">
								</s:if>
								<s:else>
									<input type="hidden" disabled="disabled" class="gid<s:property value="#zzclgoods.flag"/>"
										id="goodsId<s:property value='#zzclse.index' />" name="selected"
										value="${zzclgoods.goodsId}">
								</s:else>
								<s:if test="#zzclgoods.isEnough">
									<s:if test="%{#zzclgoods.goodsZhishu>0}">
									<div id="B<s:property value='#zzclse.index' />"  class="btn<s:property value="#zzclgoods.flag"/>"
											style="display: block">
											<input type="button" value="确认" 
												onClick="confirmGoods(<s:property value='#zzclse.index' />,'<s:property value='#zzclgoods.goodsMarkId' />','<s:property value="#zzclgoods.flag"/>')">
										</div>
										<div id="H<s:property value='#zzclse.index' />"  class="fbtn<s:property value="#zzclgoods.flag"/>"
											style="display: none">
											确认
										</div>
									</s:if>
									<s:else>
									<div id="H<s:property value='#zzclse.index' />"  class="fbtn<s:property value="#zzclgoods.flag"/>" >
											确认
										</div>
									</s:else>
								</s:if>
								<s:else>
										<div>
											<font color="red">库存不够</font>
										</div>
								</s:else>

							</td>
							</tr>
							</s:if>
								<s:set name="zzclIndex"  value="#zzclIndex+1"></s:set>
						</s:iterator>
						<tr><th align="center" colspan="18" bgcolor="yellow">发余料</th></tr>
						<s:set value="0"   name="ylIndex"></s:set>
						<s:iterator value="list" status="ylse" id="ylgoods">
						  <s:if test="#ylgoods.showType=='余料'">
							<s:if test="#ylIndex%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#ylIndex+1" />
							</td>
							<td>
								<input type="hidden" value="${ylgoods.goodsId}" name="goodsList[<s:property value="#ylse.index" />].goodsId">
								${ylgoods.goodsMarkId}
							</td>
							<td>
								${ylgoods.goodsLotId}
							</td>
							<td>
								${ylgoods.goodsFullName}
							</td>
							<td>
								${ylgoods.goodsFormat}
							</td>
							<td>
								${ylgoods.kgliao}
							</td>
							<td>
								${ylgoods.goodsClass}
							</td>
							<td>
								${ylgoods.goodHouseName}
							</td>
							<td>
								${ylgoods.goodsPosition}
							</td>
							<td>
								${ylgoods.xqCount}
								<br>
								<font color="red">(${ylgoods.quanzi1}:${ylgoods.quanzi2})</font>
							</td>
							<td>
								${wggoods.qlCount}
							</td>
							<td>
								${ylgoods.hqlCount}
							</td>
							<td>
								<label id="zaizhi">
<%--								${ylgoods.kfCount}/${ylgoods.goodsUnit}--%>
									${ylgoods.goodsBeginQuantity}/${ylgoods.goodsUnit}
								</label>
							</td>
							<td>
								<label id="zaizhi">
								${ylgoods.kfCount}/${ylgoods.goodsUnit}
<%--									${ylgoods.goodsBeginQuantity}/${ylgoods.goodsUnit}--%>
								</label>
							</td>
							<td>
									<s:if test="!#ylgoods.isEnough">
									<label>
											缺：${ylgoods.tqlCount}
										</label>
									</s:if>
									<s:else>
										<s:if test="%{#ylgoods.goodsZhishu>=0}">
										<label>
											${ylgoods.goodsZhishu}
										</label>
										<input type="hidden" name="goodsList[<s:property value="#ylse.index" />].goodsZhishu"
											value="${ylgoods.goodsZhishu}" size="5px;" />/${ylgoods.qlUnit}
<%--											<font--%>
<%--											color="red">(需用:${ylgoods.goodsBeginQuantity}/${ylgoods.goodsUnit})</font>--%>
										<input id="ylshifa<s:property value="#ylse.index" />" name="goodsList[<s:property value="#ylse.index" />].ylshifa" type="hidden" value="${ylgoods.ylshifa}" />
										</s:if>
										<s:else>
										<label>
											0
										</label>
										<input type="hidden" name="goodsList[<s:property value="#ylse.index" />].goodsZhishu" value="0" size="5px;" />/${ylgoods.qlUnit}</s:else>
									</s:else>
							</td>
							<td>
							<s:if test="#ylgoods.isChangeSf">
							<input type="text" name="goodsList[<s:property value="#ylse.index" />].goodsCurQuantity" id="shifa<s:property value="#ylse.index" />" 
										onkeyup="checkshifacount(<s:property value="#ylse.index" />,'<s:property value="#ylgoods.goodsMarkId" />')"	value="${ylgoods.goodsCurQuantity}" size="5px;" />
							</s:if>
							<s:else>
								<input type="text" name="goodsList[<s:property value="#ylse.index" />].goodsCurQuantity" id="shifa" value="${ylgoods.goodsCurQuantity}" readonly="readonly" size="5px;" />
							</s:else>
							</td>
							<td>
							${ylgoods.goodsUnit}
							</td>
							<td>
								<s:if test="#ylgoods.goodsId==null">
									<input type="hidden" disabled="disabled" class="gid<s:property value="#ylgoods.flag"/>"
										id="goodsId<s:property value='#ylse.index' />" name="selected"
										value="0">
								</s:if>
								<s:else>
									<input type="hidden" disabled="disabled" class="gid<s:property value="#ylgoods.flag"/>"
										id="goodsId<s:property value='#ylse.index' />" name="selected"
										value="${ylgoods.goodsId}">
								</s:else>
								<s:if test="#ylgoods.isEnough">
									<s:if test="%{#ylgoods.goodsZhishu>0}">
									<div id="B<s:property value='#ylse.index' />"  class="btn<s:property value="#ylgoods.flag"/>"
											style="display: block">
											<input type="button" value="确认" 
												onClick="confirmGoods(<s:property value='#ylse.index' />,'<s:property value='#ylgoods.goodsMarkId' />','<s:property value="#ylgoods.flag"/>')">
										</div>
										<div id="H<s:property value='#ylse.index' />"  class="fbtn<s:property value="#ylgoods.flag"/>"
											style="display: none">
											确认
										</div>
									</s:if>
									<s:else>
									<div id="H<s:property value='#ylse.index' />"  class="fbtn<s:property value="#ylgoods.flag"/>" >
											确认
										</div>
									</s:else>
								</s:if>
								<s:else>
										<div>
											<font color="red">库存不够</font>
										</div>
								</s:else>

							</td>
							</tr>
							</s:if>
								<s:set name="ylIndex"  value="#ylIndex+1"></s:set>
						</s:iterator>
						<tr><th align="center" colspan="18" bgcolor="green">发外购件</th></tr>
						<s:set value="0" name="wgIndex"></s:set>
						<s:iterator value="list" status="wgse" id="wggoods">
						  <s:if test="#wggoods.showType=='外购件'">
							<s:if test="#wgIndex%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#wgIndex+1" />
							</td>
							<td>
								<input type="hidden" value="${wggoods.goodsId}" name="goodsList[<s:property value="#wgse.index" />].goodsId">
								${wggoods.goodsMarkId}
							</td>
							<td>
								${wggoods.goodsLotId}
							</td>
							<td>
								${wggoods.goodsFullName}
							</td>
							<td>
								${wggoods.goodsFormat}
							</td>
							<td>
								${wggoods.kgliao}
							</td>
							<td>
								${wggoods.goodsClass}
							</td>
							<td>
								${wggoods.goodHouseName}
							</td>
							<td>
								${wggoods.goodsPosition}
							</td>
							<td>
								${wggoods.xqCount}
								<br>
								<font color="red">(${wggoods.quanzi1}:${wggoods.quanzi2})</font>
							</td>
							<td>
								${wggoods.qlCount}
							</td>
							<td>
								${wggoods.hqlCount}
							</td>
							<td>
								<label id="zaizhi">
<%--									${wggoods.kfCount}/${wggoods.goodsUnit}--%>
									${wggoods.goodsBeginQuantity}/${wggoods.goodsUnit}
								</label>
							</td>
							<td>
								<label id="zaizhi">
									${wggoods.kfCount}/${wggoods.goodsUnit}
<%--									${wggoods.goodsBeginQuantity}/${wggoods.goodsUnit}--%>
								</label>
							</td>
							<td>
									<s:if test="!#wggoods.isEnough">
									<label>
											缺：${wggoods.tqlCount}
										</label>
									</s:if>
									<s:else>
									<s:if test="%{#wggoods.goodsZhishu>=0}">
										<label>
											${wggoods.goodsZhishu}
										</label>
										<input type="hidden" name="goodsList[<s:property value="#wgse.index" />].goodsZhishu"
											value="${wggoods.goodsZhishu}" size="5px;" />/${wggoods.qlUnit}
										<input id="ylshifa<s:property value="#wgse.index" />" name="goodsList[<s:property value="#wgse.index" />].ylshifa" type="hidden" value="${wggoods.ylshifa}" />
									</s:if>
									<s:else>
										<label>
											0
										</label>
										<input type="hidden" name="goodsList[<s:property value="#wgse.index" />].goodsZhishu" value="0" size="5px;" />/${wggoods.qlUnit}</s:else>
									</s:else>
							</td>
							<td>
							<s:if test="#wggoods.isChangeSf">
							<input type="text" name="goodsList[<s:property value="#wgse.index" />].goodsCurQuantity" id="shifa<s:property value="#wgse.index" />" 
										onkeyup="checkshifacount(<s:property value="#wgse.index" />,'<s:property value="#wggoods.goodsMarkId" />')"	value="${wggoods.goodsCurQuantity}" size="5px;" />
							</s:if>
							<s:else>
								<input type="text" name="goodsList[<s:property value="#wgse.index" />].goodsCurQuantity" id="shifa" value="${wggoods.goodsCurQuantity}" readonly="readonly" size="5px;" />
							</s:else>
							</td>
							<td>
							${wggoods.goodsUnit}
							</td>
							<td>
								<s:if test="#wggoods.goodsId==null">
									<input type="hidden" disabled="disabled" class="gid<s:property value="#wggoods.flag"/>"
										id="goodsId<s:property value='#wgse.index' />" name="selected"
										value="0">
								</s:if>
								<s:else>
									<input  type="hidden" disabled="disabled" class="gid<s:property value="#wggoods.flag"/>"
										id="goodsId<s:property value='#wgse.index' />" name="selected"
										value="${wggoods.goodsId}">
								</s:else>
								<s:if test="#wggoods.isEnough">
									<s:if test="%{#wggoods.goodsZhishu>0}">
									<div id="B<s:property value='#wgse.index' />"  class="btn<s:property value="#wggoods.flag"/>"
											style="display: block">
										<s:if test="#wggoods.kuweiId!=null">
											<input type="button"
												id="open<s:property value="#wggoods.flag+1" />" value="确认"
												style="width: 50px; height: 30px; background-color: green;"
<%--												onclick="openDoor('${wggoods.kuweiId}','<s:property value="#wggoods.flag+1" />')" --%>
												onclick="getsendTow('${wggoods.goodsId}','<s:property value="#wggoods.flag+1" />')" 
												/>
											<input type="button"
												id="quxiao<s:property value="#wggoods.flag+1" />" value="取消"
												style="display: none; width: 50px; height: 30px; background-color: red;"
												onclick="closeDoor1('${wggoods.kuweiId}','<s:property value="#wggoods.flag+1" />')" />
											<input type="button"
												id="close<s:property value="#wggoods.flag+1" />" value="领料成功"
												style="display: none; width: 65px; height: 30px; background-color: red;"
												onclick="closeDoor('${wggoods.kuweiId}','<s:property value="#wggoods.flag+1" />','${wggoods.goodsId}')" />
										</s:if>
										<s:else>
											<input type="button" value="确认2" 
												onClick="confirmGoods(<s:property value='#wgse.index' />,'<s:property value='#wggoods.goodsMarkId' />','<s:property value="#wggoods.flag"/>')">
										</s:else>
										</div>
										<div id="H<s:property value='#wgse.index' />"  class="fbtn<s:property value="#wggoods.flag"/>"
											style="display: none">
											确认
										</div>
									</s:if>
									<s:else>
									<div id="H<s:property value='#wgse.index' />"  class="fbtn<s:property value="#wggoods.flag"/>" >
											确认
										</div>
									</s:else>
								</s:if>
								<s:else>
										<div>
											<font color="red">库存不够</font>
										</div>
								</s:else>
							</td>
							</s:if>
								<s:set name="wgIndex"   value="#wgIndex+1"></s:set>
						</s:iterator>
						<tr><th align="center" colspan="18" bgcolor="red">中转在制品</th></tr>
						<s:set value="0" name="zzzzIndex"></s:set>
						<s:iterator value="list" status="zzzzse" id="zzzzgoods">
						  <s:if test="#zzzzgoods.showType=='中转在制品'">
							<s:if test="#zzzzIndex%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#zzzzIndex+1" />
							</td>
							<td>
								<input type="hidden" value="${zzzzgoods.goodsId}" name="goodsList[<s:property value="#zzzzse.index" />].goodsId">
								${zzzzgoods.goodsMarkId}
							</td>
							<td>
								${zzzzgoods.goodsLotId}
							</td>
							<td>
								${zzzzgoods.goodsFullName}
							</td>
							<td>
								${zzzzgoods.goodsFormat}
							</td>
							<td>
								/
							</td>
							<td>
								${zzzzgoods.goodsClass}
							</td>
							<td>
								${zzzzgoods.goodHouseName}
							</td>
							<td>
								${zzzzgoods.goodsPosition}
							</td>
							<td>
								${zzzzgoods.xqCount}
								<br>
								<font color="red">(${zzzzgoods.quanzi1}:${zzzzgoods.quanzi2})</font>
							</td>
							<td>
								${zzzzgoods.qlCount}
							</td>
							<td>
								${zzzzgoods.hqlCount}
							</td>
								<td>
								<label id="zaizhi">
<%--								${zzzzgoods.kfCount}/${zzzzgoods.goodsUnit}--%>
									${zzzzgoods.goodsBeginQuantity}/${zzzzgoods.goodsUnit}	
								</label>
							</td>
								<td>
								<label id="zaizhi">
								${zzzzgoods.kfCount}/${zzzzgoods.goodsUnit}
<%--									${zzzzgoods.goodsBeginQuantity}/${zzzzgoods.goodsUnit}	--%>
								</label>
							</td>
							<td>	
								<s:if test="!#zzzzgoods.isEnough">
									<label>
											缺：${zzzzgoods.tqlCount}
										</label>
									</s:if>
								<s:else>
									<s:if test="%{#zzzzgoods.goodsZhishu>0}">
										<label>
											${zzzzgoods.goodsZhishu}
										</label>
										<input type="hidden" name="goodsList[<s:property value="#zzzzse.index" />].goodsZhishu"
											value="${zzzzgoods.goodsZhishu}" size="5px;" />/${zzzzgoods.qlUnit}
										<input id="ylshifa<s:property value="#zzzzse.index" />" name="goodsList[<s:property value="#zzzzse.index" />].ylshifa" type="hidden" value="${zzzzgoods.ylshifa}" />
									</s:if>
									<s:else>
										<label>
											0
										</label>
										<input type="hidden" name="goodsList[<s:property value="#zzzzse.index" />].goodsZhishu" value="0" size="5px;" />/${zzzzgoods.qlUnit}</s:else>
								</s:else>
							</td>
							<td>
							<s:if test="#zzzzgoods.isChangeSf">
							<input type="text" name="goodsList[<s:property value="#zzzzse.index" />].goodsCurQuantity" id="shifa<s:property value="#zzzzse.index" />" 
										onkeyup="checkshifacount(<s:property value="#zzzzse.index" />,'<s:property value="#zzzzgoods.goodsMarkId" />')"	value="${zzzzgoods.goodsCurQuantity}" size="5px;" />
							</s:if>
							<s:else>
								<input type="text" name="goodsList[<s:property value="#zzzzse.index" />].goodsCurQuantity" id="shifa" value="${zzzzgoods.goodsCurQuantity}" readonly="readonly" size="5px;" />
							</s:else>
							</td>
							<td>
							${zzzzgoods.goodsUnit}
							</td>
							<td>
								<s:if test="#zzzzgoods.goodsId==null">
									<input type="hidden" disabled="disabled" class="gid<s:property value="#zzzzgoods.flag"/>"
										id="goodsId<s:property value='#zzzzse.index' />" name="selected"
										value="0">
								</s:if>
								<s:else>
									<input type="hidden" disabled="disabled" class="gid<s:property value="#zzzzgoods.flag"/>"
										id="goodsId<s:property value='#zzzzse.index' />" name="selected"
										value="${zzzzgoods.goodsId}">
								</s:else>
								<s:if test="#zzzzgoods.isEnough">
									<s:if test="%{#zzzzgoods.goodsZhishu>0}">
									<div id="B<s:property value='#zzzzse.index' />"  class="btn<s:property value="#zzzzgoods.flag"/>"
											style="display: block">
											<input type="button" value="确认"
												onClick="confirmGoods(<s:property value='#zzzzse.index' />,'<s:property value='#zzzzgoods.goodsMarkId' />','<s:property value="#zzzzgoods.flag"/>')">
										</div>
										<div id="H<s:property value='#zzzzse.index' />"  class="fbtn<s:property value="#zzzzgoods.flag"/>"
											style="display: none">
											确认
										</div>
									</s:if>
									<s:else>
									<div id="H<s:property value='#zzzzse.index' />"  class="fbtn<s:property value="#zzzzgoods.flag"/>" >
											确认
										</div>
									</s:else>
								</s:if>
								<s:else>
										<div>
											<font color="red">库存不够</font>
										</div>
								</s:else>
							</td>
							</s:if>
								<s:set name="zzzzIndex"   value="#zzzzIndex+1"></s:set>
						</s:iterator>
						<tr>
							<th colspan="18" style="color: red;">
								<s:if test="message!='true'">
									${message}
								</s:if>
							</th>
						</tr>
						<tr>
							<td colspan="18" align="center">
								<input type="hidden" name="procard.lingliaoren" id="lingliaoren" value="${cardNumber}"/> <br /> 
								<input class="input" type="submit" value="提交" id="sbtn" /> 
							</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<th colspan="18" style="color: red;">
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
		var sureCount = 0;
function openDoor(id, it) {
	$.ajax( {
		url : "WarehouseAreaAction_OpenWNById_1.action",
		type : "POST",
		data : {
			id : id
		},
		dataType : "json",
		async : false,
		success : function(data) {
			if (data != null) {
				if (data.success) {
					var ui = document.getElementById("quxiao" + it);
					ui.style.display = "block";
					var ui = document.getElementById("close" + it);
					ui.style.display = "block";
					var ui = document.getElementById("open" + it);
					ui.style.display = "none";
				} else {
					alert(data.message);
				}
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}
function closeDoor(id, it, goodid) {
	if (window.confirm('确定已经成功取料？')) {
		$.ajax( {
			url : "WarehouseAreaAction_ColseWNById_1.action",
			type : "POST",
			data : {
				id : id
			},
			dataType : "json",
			async : false,
			success : function(data) {
				if (data != null) {
					if (data.success) {
						var ui = document.getElementById("close" + it);
						ui.style.display = "none";
						var ui = document.getElementById("quxiao" + it);
						ui.style.display = "none";
						var getCount1 = $("#getCount").val();
						var hasCount = $("#hasCount").val();
						//访问领料接口
						$.ajax( {
							url : "sellAction!saveSellByCode.action?tags=code&procard.hascount="+hasCount+"&procard.id=${procard.id}&procard.lingliaoren=${cardNumber}",
							type : "POST",
							data : {
								ids : goodid,
								getCount:getCount1
							},
							dataType : "json",
							async : false,
							success : function(data) {
								if (data != null) {
									if (data.success) {
										alert(data.message);
									}
								}
							},
							error : function() {
								alert("服务器异常!");
							}
						});
					} else {
						alert(data.message);
					}
				}
			},
			error : function() {
				alert("服务器异常!");
			}
		});
		
	}
}
function closeDoor1(id, it) {
	$.ajax( {
		url : "WarehouseAreaAction_ColseWNById_1.action",
		type : "POST",
		data : {
			id : id
		},
		dataType : "json",
		async : false,
		success : function(data) {
			if (data != null) {
				if (data.success) {
					var ui = document.getElementById("close" + it);
					ui.style.display = "none";
					var ui = document.getElementById("quxiao" + it);
					ui.style.display = "none";
				} else {
					alert(data.message);
				}
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}
var num = 1;
function getsendTow(wwd, index) {
	//$("#open" + index).removeAttr("onclick");
	$("#open" + index).hide();
	$.ajax( {
				url : "sellAction!querensendTow.action",
				type : "POST",
				data : {
					id : wwd
				},
				dataType : "json",
				async : false,
				success : function(data) {
					if (data != null) {
						if (data.success) {
							getcheckList2();
							if (index >= 1) {
								num = index;
							}
						} else {
							if (data.message == 'trues') {
								window.location.href = "<%=basePath%>/System/SOP/produce/Procard_rkxz.jsp?id="
										+ wwd;
							} else {
								alert(data.message)
							}
						}
					}
				},
				error : function() {
					alert("服务器异常!");
				}
			});
}

function getcheckList2() {
	if (typeof (myObj) != "undefined") {
		//打开扫描服务
		myObj.scanGongWei(1);
	} else {
		alert("无法打开扫描服务,请检查后重试!");
	}
}

function funFromjs(tm) {
	$.ajax( {
		type : "POST",
		url : "WaigouwaiweiPlanAction!redTowWeb.action",
		data : {
			bacode : tm,
			pageStatus : 'lingliao'
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				if (data.success) {
					var ui = document.getElementById("close" + num);
					ui.style.display = "block";
					var ui = document.getElementById("quxiao" + num);
					ui.style.display = "block";
				} else {
					num = 1;
					alert(data.message);
				}
			}
		}
	})
}

function confirmGoods(obj, goodsMarkId,btnclass) {
	var hasCount = $("#hasCount").val();//lingliaoren
	var getCount = $("#getCount").val();
	var zaizhi = $("#zaizhi").text();
	if((hasCount-getCount)<0){
		 alert("领取数量超过剩下数量");
	}
	var procardStyle="${procard.procardStyle}";
	var danjiaojian="${procard.danjiaojian}";
<%--		if(checkshifacount(obj,goodsMarkId)==false){--%>
<%--			return ;--%>
<%--		}--%>
		$(".gid"+btnclass).removeAttr("disabled");
		$("#goodsId").removeAttr("disabled");
		$(".btn"+btnclass).hide();
		$(".fbtn"+btnclass).show();
		sureCount++;
	$("#shifa"+obj).attr("readonly","readonly");
	
	//$("#lingliaoren").focus();
}
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

</script>
	</body>
</html>
