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
		<SCRIPT type="text/javascript">
			$(function (){
				var procardStyle="${quotedPrice.procardStyle}"
				if(procardStyle=="总成"||procardStyle=="组合"){
					$("#showZong").show();
				}else if(procardStyle=="自制"){
					$("#showZi").show();
				}else if(procardStyle=="外购"){
					$("#showWai").show();
				}
			});
function isneedprocess() {
	var needProcess = $("#needProcess").val();
	if (needProcess == 'yes') {
		parent.module4.attr("disabled", false);
	} else if (needProcess == 'no') {
		parent.module4.attr("disabled", true);
	}
}
$(document).ready(function() {
	isneedprocess();
})
		</SCRIPT>
	</head>
	<body>
		<center>
			<input type="button" value="修改"
				onclick="show('updateProcardT','slow')" />
			<input type="button" value="关闭"
				onclick="hide('updateProcardT','hide')" />
			<div id="updateProcardT" style="display: none">
				<form action="QuotedPrice_updateQuotedPrice.action" method="post"
					style="margin: 0px; padding: 0px;">
					<input type="hidden" name="id" value="${quotedPrice.id}" />
					<table class="table" style="width: 100%;">
						<tr>
							<th align="center" colspan="2">
								修改${quotedPrice.proName}(${quotedPrice.markId})的模版信息
							</th>
						</tr>
						<tr>
							<th align="right" style="width: 25%;">
								件号:
							</th>
							<td>
								<input name="quotedPrice.markId" value="${quotedPrice.markId}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								名称:
							</th>
							<td>
								<input name="quotedPrice.proName" value="${quotedPrice.proName}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								型别:
							</th>
							<td>
								<input name="quotedPrice.xingbie" value="${quotedPrice.xingbie}">
							</td>
						</tr>
						<s:if test='quotedPrice.procardStyle=="外购"'>
							<tr>
								<th align="right">
									权值:
								</th>
								<td>
									<input name="quotedPrice.quanzi1" style="width: 71px;"
										value="${quotedPrice.quanzi1}" />
									:
									<input name="quotedPrice.quanzi2" style="width: 71px;"
										value="${quotedPrice.quanzi2}" />
									(组合件:外购件,格式如1:1)
								</td>
							</tr>
							<tr>
								<th align="right">
									是否半成品:
								</th>
								<td>
									<select name="quotedPrice.needProcess" id="needProcess"
										style="width: 155px;" >
										<s:if test="quotedPrice.needProcess==null">
											<option value="no">
												否
											</option>
											<option value="yes">
												是
											</option>
										</s:if>
										<s:elseif test="quotedPrice.needProcess=='yes'">
											<option value="${quotedPrice.needProcess}">
												是
											</option>
											<option value="no">
												否
											</option>
										</s:elseif>
										<s:else>
											<option value="${quotedPrice.needProcess}">
												否
											</option>
											<option value="yes">
												是
											</option>
										</s:else>
									</select>
								</td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<th align="right">
									数量:
								</th>
								<td>
									<input name="quotedPrice.corrCount"
										value="${quotedPrice.corrCount}">
									(权值,对应上层所需数量)
								</td>
							</tr>
						</s:else>
						<tr>
							<th align="right">
								卡片类型:
							</th>
							<td>
								<input name="quotedPrice.procardStyle"
									value="${quotedPrice.procardStyle}" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								单位:
							</th>
							<td>
								<select name="quotedPrice.unit" style="width: 155px;">
									<option value="${quotedPrice.unit}">
										${quotedPrice.unit}
									</option>
									<option value="件">
										件
									</option>
									<option value="支">
										支
									</option>
									<option value="吨">
										吨
									</option>
									<option value="公斤">
										公斤
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								产品类型:
							</th>
							<td>
								<select name="quotedPrice.productStyle" style="width: 155px;">
									<option value="${quotedPrice.productStyle}">
										${quotedPrice.productStyle}
									</option>
									<option value="批产">
										批产
									</option>
									<option value="试制">
										试制
									</option>
								</select>
							</td>
						</tr>
						<s:if test="quotedPrice.procardStyle=='自制'">
							<tr>
								<th colspan="2">
									原材料信息
								</th>
							</tr>
							<tr>
								<th align="right">
									牌号:
								</th>
								<td>
									<input name="quotedPrice.trademark"
										value="${quotedPrice.trademark}">
								</td>
							</tr>
							<tr>
								<th align="right">
									规格:
								</th>
								<td>
									<input name="quotedPrice.specification"
										value="${quotedPrice.specification}">
								</td>
							</tr>
							<tr>
								<th align="right">
									单位:
								</th>
								<td>
									<select name="quotedPrice.yuanUnit" style="width: 155px;">
										<option value="${quotedPrice.yuanUnit}">
											${quotedPrice.yuanUnit}
										</option>
										<option value="件">
											件
										</option>
										<option value="支">
											支
										</option>
										<option value="吨">
											吨
										</option>
										<option value="公斤">
											公斤
										</option>
										<option value="块">
											块
										</option>
										<option value="个">
											个
										</option>
										<option value="根">
											根
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<th align="right">
									材料消耗:
								</th>
								<td>
									<input name="quotedPrice.materialXh"
										onkeyup="value=value.replace(/[^\d\.]/g,'')"
										value="${quotedPrice.materialXh}">
										<font color="red">（批产）</font>
									(指每件零件的单个消耗量)
								</td>
							</tr>
							<tr>
								<th align="right">
								</th>
								<td>
									<input name="quotedPrice.materialXhsz"
										onkeyup="value=value.replace(/[^\d\.]/g,'')"
										value="${quotedPrice.materialXhsz}">
										<font color="red">（小批量）</font>
								</td>
							</tr>
							<tr>
								<th align="right">
								</th>
								<td>
									<input name="quotedPrice.materialXhsj"
										onkeyup="value=value.replace(/[^\d\.]/g,'')"
										value="${quotedPrice.materialXhsj}">
										<font color="red">（首件）</font>
								</td>
							</tr>
							<tr>
								<th align="right">
									权值:
								</th>
								<td>
									<input name="quotedPrice.quanzi1" style="width: 71px;"
										value="${quotedPrice.quanzi1}" />
									:
									<input name="quotedPrice.quanzi2" style="width: 71px;"
										value="${quotedPrice.quanzi2}" />
									(自制件:原材料,格式如1:1)
								</td>
							</tr>
						</s:if>
						
						<tr>
								<th align="right">
									是否外购:
								</th>
								<td>
									<select name="quotedPrice.yucailiaostatus" style="width: 155px;">
										<option value="${quotedPrice.yucailiaostatus}">
											${quotedPrice.yucailiaostatus}
										</option>
										<option value="是">
											是
										</option>
										<option value="否">
											否
										</option>
									</select>
								</td>
							</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" value="修改"
									style="width: 80px; height: 50px;" />
								<input type="reset" value="重置"
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div id="showZong" style="display: none;">
				<div align="center" style="border-bottom: solid #000 1px;">
					工 艺 规范
				</div>
				<div style="font-weight: bolder;">
					名称:${quotedPrice.proName} &nbsp;&nbsp;件号:${quotedPrice.markId}
					&nbsp;&nbsp; 卡片类型:${quotedPrice.procardStyle} &nbsp;&nbsp;
					产品类型:${quotedPrice.productStyle} &nbsp;&nbsp;
					型别:${quotedPrice.xingbie}&nbsp;&nbsp;
				</div>
				<table class="table" style="width: 100%;">
					<tr>
						<th>
							数量(权值)
						</th>
						<th>
							1 : ${quotedPrice.corrCount==null?0:quotedPrice.corrCount}
						</th>
						<th align="right">
							人工费:
						</th>
						<td>
							${quotedPrice.rengongFei}
						</td>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr align="center">
						<td>
							零组件
						</td>
						<td>
							名称
						</td>
						<td>
							数量
						</td>
						<td>
							卡片类型
						</td>
						<th>
							&nbsp;
						</th>

					</tr>
					<s:iterator value="quotedPriceList" id="pageQuotedPrice">
						<tr align="center">
							<th>
								${pageQuotedPrice.markId}
							</th>
							<th>
								${pageQuotedPrice.proName}
							</th>
							<th>
								${pageQuotedPrice.filnalCount}
							</th>
							<th>
								${pageQuotedPrice.procardStyle}
							</th>
							<th>
								&nbsp;
							</th>
						</tr>
					</s:iterator>
					<tr>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
					<tr>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
						<th>
							&nbsp;
						</th>
					</tr>
				</table>
			</div>
			<div id="showZi" style="display: none;">
				<div align="center" style="border-bottom: solid #000 1px;">
					工 艺规范
				</div>
				<div style="font-weight: bolder;">
					名称:${quotedPrice.proName} &nbsp;&nbsp;件号:${quotedPrice.markId}
					&nbsp;&nbsp; 卡片类型:${quotedPrice.procardStyle} &nbsp;&nbsp;
					产品类型:${quotedPrice.productStyle} &nbsp;&nbsp;
					型别:${quotedPrice.xingbie}&nbsp;&nbsp;
				</div>
				<table class="table" style="width: 100%;">
					<tr>
						<th colspan="3" width="40%">
						</th>
						<th colspan="2" width="50%">
							数量(权值) 1 : ${quotedPrice.corrCount==null?0:quotedPrice.corrCount}
						</th>
					</tr>
					<tr>
						<td rowspan="5" style="width: 5px;" align="center">
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
						<td width="15%">
							${quotedPrice.trademark}
						</td>
						<td rowspan="5" align="center" width="10%">
							备
							<br />
							<br />
							<br />
							注
						</td>
						<td rowspan="5">
							${quotedPrice.remark}
						</td>
					</tr>
					<tr>
						<td>
							规格
						</td>
						<td>
							${quotedPrice.specification}
						</td>
					</tr>
					<tr>
						<td>
							数量(权值)
						</td>
						<td>
							${quotedPrice.quanzi1} : ${quotedPrice.quanzi2}
						</td>
					</tr>
					<tr>
						<td>
							是否外购
						</td>
						<td>
							${quotedPrice.yucailiaostatus}
						</td>
					</tr>
					<tr>
						<th align="right">
							人工费:
						</th>
						<td>
							${quotedPrice.rengongFei}
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
			</div>
			<div id="showWai" style="border: solid #000 1px; display: none;">
				<div align="center" style="border-bottom: solid #000 1px;">
					工 艺 规范
				</div>
				<table class="table" style="width: 100%;">
					<tr>
						<th align="right" style="width: 25%;">
							件号:
						</th>
						<td>
							${quotedPrice.markId}
						</td>
					</tr>
					<tr>
						<th align="right">
							名称:
						</th>
						<td>
							${quotedPrice.proName}
						</td>
					</tr>
					<tr>
						<th align="right">
							型别:
						</th>
						<td>
							${quotedPrice.xingbie}
						</td>
					</tr>
					<tr>
						<th align="right">
							卡片类型:
						</th>
						<td>
							${quotedPrice.procardStyle}
						</td>
					</tr>
					<tr>
						<th align="right">
							单位:
						</th>
						<td>
							${quotedPrice.unit}
						</td>
					</tr>
					<tr>
						<th align="right">
							权值:
						</th>
						<td>
							${quotedPrice.quanzi1} : ${quotedPrice.quanzi2}
						</td>
					</tr>
					<tr>
						<th align="right">
							产品类型:
						</th>
						<td>
							${quotedPrice.productStyle}
						</td>
					</tr>
					<tr>
						<th align="right">
							外购单价:
						</th>
						<td>
							${quotedPrice.waigouPrice}
						</td>
					</tr>
				</table>
			</div>
			<div>
				<table class="table" style="width: 100%">
					<tr align="center">
						<th>
							工序号
						</th>
						<th>
							名称
						</th>
						<th>
							生产类型
						</th>
						<th>
							设备
						</th>
						<th>
							工装
						</th>
					</tr>
					<s:iterator value="list" id="pageProcessTem">
						<tr align="center">
							<th>
								${pageProcessTem.processNO}
							</th>
							<th>
								${pageProcessTem.processName}
							</th>
							<th>
								${pageProcessTem.productStyle}
							</th>
							<th>
								${pageProcessTem.shebeiName} ${pageProcessTem.shebeiNo}
							</th>
							<th>
								${pageProcessTem.gongzhuang} ${pageProcessTem.gongzhuangNumber}
							</th>
						</tr>
					</s:iterator>
				</table>
			</div>
		</center>
	</body>
</html>
