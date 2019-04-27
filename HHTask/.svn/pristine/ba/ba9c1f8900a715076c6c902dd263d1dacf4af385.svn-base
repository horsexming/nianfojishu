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
			<s:if test='procard.productStyle=="批产"'>
				<div style="background-color: green; color: #ffffff;font-size: 14px;">
					批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产 批产
					批产 批产 批产 批产 批产 批产 
				</div>
			</s:if>
			<s:if test='procard.productStyle=="试制"'>
				<div style="background-color: yellow; color: #ffffff;font-size: 14px;">
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
						<th colspan="25">
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
							工序号
						</th>
						<th align="center">
							工序名
						</th>
						<th align="center">
							库位
						</th>
						<th align="center">
							单张产量
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
							本次实发
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
						<tr><th align="center" colspan="25" bgcolor="gray">在制品充领</th></tr>
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
								/
							</td>
							<td>
								/
							</td>
							<td>
								/
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
						<tr><th align="center" colspan="25" bgcolor="yellow">发余料</th></tr>
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
								${ylgoods.gongxuNum}
							</td>
							<td>
								${ylgoods.gongxuName}
							</td>
							<td>
								${ylgoods.goodsPosition}
							</td>
							<td>
								/
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
									<s:if test="%{#ylgoods.goodsZhishu>0&&#ylgoods.goodsBeginQuantity>0}">
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
						<tr><th align="center" colspan="25" bgcolor="green">发外购件</th></tr>
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
								${wggoods.gongxuNum}
							</td>
							<td>
								${wggoods.gongxuName}
							</td>
							<td>
								${wggoods.goodsPosition}
							</td>
							<td>
								${wggoods.singleCount}
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
								<fmt:formatNumber value="${wggoods.hqlCount}" pattern="#.###"></fmt:formatNumber> 
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
											<input type="button" value="确认" 
												onClick="confirmGoods(<s:property value='#wgse.index' />,'<s:property value='#wggoods.goodsMarkId' />','<s:property value="#wggoods.flag"/>')">
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
							</tr>
							</s:if>
								<s:set name="wgIndex"   value="#wgIndex+1"></s:set>
						</s:iterator>
						<tr><th align="center" colspan="25" bgcolor="red">中转在制品</th></tr>
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
								/
							</td>
							<td>
								/
							</td>
							<td>
								/
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
							</tr>
							</s:if>
								<s:set name="zzzzIndex"   value="#zzzzIndex+1"></s:set>
						</s:iterator>
						<tr>
							<th colspan="25" style="color: red;">
								<s:if test="message!='true'">
									${message}
								</s:if>
							</th>
						</tr>
						<tr>
							<td colspan="25" align="center">
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
							<th colspan="23" style="color: red;">
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
//核实实发数量
function checkshifacount(index,goodsMarkId){
	var shifa=$("#shifa"+index).val();
	var getCount=$("#getCount").val();
	 var st=false;
	$.ajax({
		type : "POST",
		url : "sellAction!checkshifacount.action",dataType : "json",
		async : false,
		data : {
		  goodsMarkId : goodsMarkId,
		  shifa : shifa
		},
	   success : function(data){
		 if(!data.success){
			 var bid = "B" + index;
	         var hid = "H" + index;
	         var bbtn = document.getElementById(bid);
	         var hbtn = document.getElementById(hid);
	         bbtn.style.display = "none";
	         hbtn.style.display = "block";
	        // alert("库存不足！");
		 }else{
			 var bid = "B" + index;
	         var hid = "H" + index;
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
	var getCount=$("#getCount").val();
	if(getCount==0){
		$("#getCount").val($("#hasCount").val());
		getCount=$("#hasCount").val();
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
			if(${procard.lingliaoType=='part'}){
			}else{
			return;
				
			}
		}else if((getCount-hasCount)>0){
			if(${procard.lingliaoType=='part'}){
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
				  getCount : getCount
				},
				success : function(data){
					if(data.indexOf("对不起")== 0){
						alert(data);
						return;
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
			       var html1="<tr><th align='center' colspan='21' bgcolor='gray'>在制品充领</th></tr>";
			       var i1=0;
			       var html2="<tr><th align='center' colspan='21' bgcolor='yellow'>发余料</th></tr>";
			       var i2=0;
			       var html3="<tr><th align='center' colspan='21' bgcolor='green'>发外购件</th></tr>";
			       var i3=0;
			       var html4="<tr><th align='center' colspan='21' bgcolor='red'>中转在制品</th></tr>";
			       var i4=0;
			       var hascount="${procard.hascount}";
			       $.each(data,function(i,good){
			    	    var htmlTem="";
			    	    var iTem=0;
			    	    var singleCount="/";
			    	//    var ylqingling="";
			    	    if(good.showType=="在制品充领"){
			    	    	iTem =i1;
			    	    }else if(good.showType=="余料"){
			    	 //   	ylqingling = "<font color='red'>(需用:"+good.goodsBeginQuantity+"/"+good.goodsUnit+")</font>";
			    	    	iTem =i2;
			    	    }else if(good.showType=="外购件"){
			    	    	iTem =i3;
			    	    	singleCount = good.singleCount;
			    	    }else if(good.showType=="中转在制品"){
			    	    	iTem =i4;
			    	    }
			    	   var zs=good.goodsZhishu;
			    	   if(zs==null){
			    		   zs=0;
			    	   }
			    	   var goodid=good.goodsId;
			    	   if(goodid==null){
			    		   goodid=0;
			    	   }
			    	    if(iTem%2==1){
			    		   htmlTem=htmlTem+"<tr align='center' onmouseover='chageBgcolor(this)' onmouseout='outBgcolor(this,\"\")'>" ;
			    	   }else{
			    		   htmlTem=htmlTem+"<tr align='center' bgcolor='#e6f3fb' onmouseover='chageBgcolor(this)' onmouseout='outBgcolor(this,\"#e6f3fb\")'>";
			    	   }
			    	    var fName=good.goodsFullName;
			    	    var goodsFormat=good.goodsFormat;
			    	    if(fName==null||fName=="null"||fName.length==0){
			    	    	fName="";
			    	    }
			    	    if(goodsFormat==null||goodsFormat=="null"||goodsFormat.length==0){
			    	    	goodsFormat="";
			    	    }
			    	    var readonly = "";
			    	    if(!good.isChangeSf){
			    	    	readonly="readonly='readonly'";
			    	    }
			    	    var qlCounthtml="";
			    	    if(good.isEnough){
			    	    	qlCounthtml="</td><td> <label>"+zs+"</label> <input type='hidden' name='goodsList["+i+"].goodsZhishu' value='"+zs+"' size='5px;' />" +
			    	      "/"+good.qlUnit
			    	    }else{
			    	    	qlCounthtml="</td><td>缺："+good.tqlCount +"/"+good.goodsUnit
			    	    }
			    	    var btnHtml = "<div id='B"+i+"' style='display: block' class='btn"+good.flag+"'>" +
			    	           "<input type='button' value='确认' onClick='confirmGoods("+i+",\""+good.goodsMarkId+"\","+good.flag+")'> </div>"+
			    	           "<div id=\"H"+i+"\" class='fbtn"+good.flag+"' style='display: none'>确认</div> " ;
			    	  	if(good.isEnough&&good.goodsZhishu==0){
			    	  		btnHtml = "<div id=\"H"+i+"\" class='fbtn"+good.flag+"' >确认</div> " ;
			    	  	}
			    	    htmlTem=htmlTem+
			    	    "<td> "+(iTem+1)+"<input type='hidden' value='"+good.goodsId+"' name='goodsList["+i+"].goodsId'>" +
			    	    "</td><td>"+good.goodsMarkId+
			    	    "</td><td>"+good.goodsLotId+
			    	    "</td><td>"+fName+
			    	    "</td><td>"+goodsFormat+
			    	    "</td><td>"+good.kgliao+
			    	    "</td><td>"+good.goodsClass+
			    	    "</td><td>"+good.goodHouseName+
			    	    "</td><td>"+good.gongxuNum+
			    	    "</td><td>"+good.gongxuName+
			    	    "</td><td>"+good.goodsPosition+
			    	    "</td><td>"+good.singleCount+
			    	    "</td><td>"+good.xqCount+"<br/><font color='red'>("+good.quanzi1+":"+good.quanzi2+")</font>"+
			    	    "</td><td>"+good.qlCount+
			    	    "</td><td>"+good.hqlCount+
			    	    "</td><td>"+good.goodsBeginQuantity+good.goodsUnit+
			    	    "</td><td>"+good.kfCount+good.goodsUnit+
			    	    qlCounthtml+
			    	    "</td><td><input "+readonly+" type='text' name='goodsList["+i+"].goodsCurQuantity' id='shifa"+i+"' onkeyup='checkshifacount("+i+",\""+good.goodsMarkId+"\")' value='"+good.goodsCurQuantity+"' size='5px;' />"+
			    	    "</td><td> "+good.goodsUnit+
			    	  //  "</td><td><label id='zaizhi'><font color='red' size='6'>"+good.goodsBeginQuantity+"</font></label></td>" +
			    	    "<td><input type='hidden' disabled='disabled' class='gid"+good.flag+"' id='goodsId"+i+"' name='selected' value='"+goodid+"' >" +
			    	    btnHtml+
			    	     "</td></tr>";
			           count1++;
			            if(good.showType=="在制品充领"){
			    	    	html1 +=htmlTem;
			    	    }else if(good.showType=="余料"){
			    	    	html2 +=htmlTem;
			    	    }else if(good.showType=="外购件"){
			    	    	html3 +=htmlTem;
			    	    }else if(good.showType=="中转在制品"){
			    	    	html4 +=htmlTem;
			    	    }
			       });
			       if(count1<0){
			    	   return ;
			       }
			       if(count1>0){
			    	   html=html+html1+html2+html3+html4+"<tr> <th colspan='21' style='font-size: 15px; color: red;'> " +
			    	   "<span id='butt'  align='top;'>领料人刷卡: <input type='text' name='procard.lingliaoren' id='lingliaoren'/>" +
			    	   " <br /> <input class='input' type='submit' value='提交' /> </span>" +
			    	   "</th> </tr>";
			       }else{
			    	 html=html+html1+html2+html3+html4+"<tr> <th colspan='21' style='font-size: 15px; color: red;'> " +
			    	   "<span id='butt' align='top;'>领料人刷卡: <input type='text' name='procard.lingliaoren' id='lingliaoren' onchange='getCraName(this.value)'/>" +
			    	   " <br /> <input class='input' type='submit' value='提交' /> </span>" +
			    	   "</th> </tr>";
			       }
			       $("#processTable>tbody>tr").eq(afterIndex).after(html);
				}
			});
	}
	
}
<%--$(document).ready(function(){--%>
<%--	var ckCount="<s:property value='procard.ckCount'/>";--%>
<%--	var ckType ="${ckType}";--%>
<%--	var msg = "${message}";--%>
<%--	if(msg.indexOf("对不起")>0){--%>
<%--		alert(msg);--%>
<%--		return false;--%>
<%--	}--%>
<%--	if(ckCount!=null&&ckCount.length>0){--%>
<%--		if(ckCount>0){--%>
<%--			ckCount=parseInt(ckCount);--%>
<%--			if((ckCount-$("#getCount").val())<0){--%>
<%--				$("#getCount").val(ckCount);--%>
<%--   				checkCount();--%>
<%--			}--%>
<%--		}--%>
<%--	}--%>
<%--})--%>
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
