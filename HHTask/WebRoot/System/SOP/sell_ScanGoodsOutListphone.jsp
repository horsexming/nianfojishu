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
								比重
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
												onchange="checkCount()">
										</s:if>
										<s:else>
											<input type="text" id="getCount" name="getCount"
												value="<s:property value='procard.ckCount'/>"
												onchange="checkCount()">
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
								${goods.goodsPosition}
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
								${goods.goodsMarkId}
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
										<div id="B<s:property value='#se.index+1'  />"
											style="display: none;">
											<input type="button" value="确认"
												onClick="confirmGoods(<s:property value='#se.index+1' />,'<s:property value='#goods.goodstype' />','<s:property value='#goods.goodsMarkId' />','<s:property value='#goods.goodsFormat' />','${goods.goodsId}')">
										</div>
										<div onclick="getsendTow('${goods.goodsId}','${se.index+1}')"
											id="kuwei${se.index+1}"
											style="width: 55px; height: 55px; border-radius: 50%; background-color: green; color: #ffffff; font-size: 10px;">
											<br />
											入库码1
											<br />
											扫描
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
											style="display: none">
											<input type="button" value="确认"
												onClick="confirmGoods(<s:property value='#se.index+1' />,'<s:property value='#goods.goodstype' />','<s:property value='#goods.goodsMarkId' />','<s:property value='#goods.goodsFormat' />','${goods.goodsId}')">
										</div>
										<div onclick="getsendTow('${goods.goodsId}','${se.index+1}')"
											id="kuwei${se.index+1}"
											style="width: 55px; height: 55px; border-radius: 50%; background-color: green; color: #ffffff; font-size: 10px;">
											<br />
											入库码2
											<br />
											扫描
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
											style="display: none">
											<input type="button" value="确认"
												onClick="confirmGoods(<s:property value='#se.index+1' />,'<s:property value='#goods.goodstype' />','<s:property value='#goods.goodsMarkId' />','<s:property value='#goods.goodsFormat' />','<s:property value="#goods.goodsMarkId"/>','${goods.goodsId}')">
										</div>
										<div onclick="getsendTow('${goods.goodsId}','${se.index+1}')"
											id="kuwei${se.index+1}"
											style="width: 55px; height: 55px; border-radius: 50%; background-color: green; color: #ffffff; font-size: 10px;">
											<br />
											入库码3
											<br />
											扫描
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
								<span id="butt"> <input type="hidden"
										name="procard.lingliaoren" id="lingliaoren"
										value="${cardNumber}" /> <br /> <input class="input"
										type="submit" value="提交" id="sbtn" /> </span>
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

</script>
	</body>
</html>
