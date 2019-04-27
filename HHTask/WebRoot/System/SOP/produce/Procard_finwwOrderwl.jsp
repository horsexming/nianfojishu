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
		<STYLE type="text/css">
/**/ /**
*    分页打印打印相关
*/
@media print {
	.notprint {
		display: none;
	}
	#sellTable {
		display: none;
	}
	.PageNext {
		page-break-after: always;
	}
	.notprintTd {
		display: none;
	}
	.notprintTr {
		display: none;
	}
}

@media screen {
	.notprint {
		display: inline;
		cursor: pointer;
	}
	.notprintTd {
		background-color: #d0d0d0;
	}
	.notprintTr {
		background-color: #d0d0d0;
	}
}
</STYLE>
	</head>
	<body style="font-family: '黑体';">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;" align="right">
			<table width="5%" style="float: right;" class="notprint">
				<tr>
					<td align="right">
						<input type=button value='打印' onClick="window.print()"
							style="border: 1px solid #000000">
					</td>
				</tr>
			</table>
			<div align="center">
				<div style="width: 100%" align="center">
					<table width="100%" border=0 align=center id="tabHeader"
						style="font-size: 12px; line-height: 10px;">
						<tr>
							<th colspan="1">
								<div style="width: 100%; font-size: 20px; font-weight: bolder;"
									align="center">
								</div>
								<div style="float: left;" align="right">
									<div style="float: left;">
										<img id="showcode" alt=""
											src="barcode.action?msg=${waigouOrder.planNumber}"
											width="200px" height="60px" />
									</div>
									<div id="ercode" style="float: left;">
									</div>
								</div>
							</th>
							<th colspan="14" align="left"
								style="font-size: 22px; height: 25px; margin: 0px; line-height: 20px;">
								${companyInfo.name}
								<br />
								<span style="font-size: 13px;">${companyInfo.englishName}</span>
							</th>
						</tr>
						<tr>
							<td align="right" colspan="15"></td>
						</tr>
						<tr>
							<th colspan="14" style="border: hidden; font-size: x-large;"
								align="center">
								委外加工领料单
							</th>
						</tr>
						<tr>
							<th style="border: hidden; width: 100px;" align="left"
								colspan="9">
								REY: 01
							</th>
							<th style="border: hidden;" align="center" colspan="4">
								QP750500-N
							</th>
						</tr>
						<tr>
							<th style="border: hidden; width: 100px;" align="left"
								colspan="14">
								加工单位: ${waigouOrder.gysName}
							</th>
						</tr>
						<tr>
							<th style="border: hidden;" align="left" colspan="2">
								加工要求:
							</th>
							<th style="border: hidden;" colspan="1" align="left">
								日期: ${waigouOrder.addTime}
							</th>
							<th style="border: hidden;" colspan="5" align="left">
								编号: ${waigouOrder.planNumber}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</th>
						</tr>
					</table>
					<s:if test="goodsList!=null&&goodsList.size()>0">
						<table class="table" border=1 
							style="border-collapse: collapse; font-size: 12px; border: 1px solid #000000;width: 95%"
							align=center cellpadding=3 id="tabDetail">
							<tr style="font-size: 2.5mm; height: 40px;">
								<th align="center">
									序号
								</th>
								<th align="center">
									件号
								</th>
								<th align="center" style="max-width: 50px;">
									版本
								</th>
								<th align="center" style="max-width: 100px;">
									名称
								</th>
								<th align="center" >
									规格
								</th>
								<th align="center">
									图号
								</th>
								<th align="center" style="max-width: 80px;">
									内部订单号
								</th>
								<th align="center">
									仓库
								</th>
								<th align="center">
									数量
								</th>
								<th align="center">
									实发数量
								</th>
								<th align="center">
									单位
								</th>
							</tr>
							<s:iterator value="goodsList" id="pagegoods" status="pageStatus">
								<tr align="center" ondblclick="noprintTr(this,${pageStatus.index})" >
									<th style="width: 10px;">
										${pageStatus.index+1}
									</th>
									<td align="left" style="width: 40px;">
										${pagegoods.markId}
									</td>
									<td align="left" style="max-width: 40px;">
										${pagegoods.banBenNumber}
									</td>
									<td align="left" style="max-width: 80px;">
										${pagegoods.proName }
									</td>
									<td align="left" style="max-width: 120px;">
										${pagegoods.specification}
									</td>
									<td align="left" style="max-width: 80px;">
										${pagegoods.tuhao}
									</td>
									<td align="left" style="max-width: 80px;">
										${pagegoods.orderNumber}
									</td>
									<td align="right" style="width: 10px;">
										<s:if test="#pagegoods.procardStyle=='外购'&&#pagegoods.needProcess!='yes'">
										外购件库
										</s:if>
										<s:else>委外库
										</s:else>
										</td>
									<td align="right" style="width: 15px;">
										${pagegoods.realReceive}
									</td>
									<td align="center">
									
									</td>
									<td align="left" style="width: 15px;">
										${pagegoods.unit}
									</td>
								</tr>
							</s:iterator>
						</table>
					</s:if>
					
					<table width="100%" border=0 align="center" id="tabFooter" style="font-size: 8px;">
						<tr>
							<th style="border: hidden;" align="left" colspan="3">
								制单:${Users.name}
							</th>
							<th style="border: hidden;" align="left" colspan="3">
								仓管:
							</th>
							<th style="border: hidden;" colspan="3" align="left">
								采购:
							</th>
							<th style="border: hidden;" colspan="3" align="left">
								仓库主管:
							</th>
							<th style="border: hidden;" colspan="2" align="left">
								供应商:
							</th>
						</tr>
						<tr>
							<th style="border: hidden;" align="left" colspan="2">
								白联:仓管
							</th>
							<th style="border: hidden;" align="left" colspan="2">
								红联:财务
							</th>
							<th style="border: hidden;" colspan="2" align="left">
								蓝联:供应商
							</th>
							<th style="border: hidden;" colspan="2" align="left">
								黄联:采购
							</th>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div id="divPrint"></div>
		<div align="center">
			<%--		<s:if test="tag=='true'">--%>
			<%--			<input type="button" value="打印" class="input"--%>
			<%--				onclick="pagePrint('printdiv')" />--%>
			<%--		</s:if>--%>
			<%--		<s:else>--%>
			<%--			<font color="red">有物料库存不足不能打印</font>--%>
			<%--		</s:else>--%>
		</div>
		<br/>
		<div>
		<table class="table" id="sellTable" >
				<tr bgcolor="red" >
				<td align="center" colspan="15">历史出库
				</td>
				</tr>
				<tr>
				<th align="center">序号
					</th>
					<th align="center">件号
					</th>
					<th align="center">名称
					</th>
					<th align="center">版本
					</th>
					<th align="center">工序号
					</th>
					<th align="center">供料属性
					</th>
					<th align="center">批次
					</th>
					<th align="center">规格
					</th>
					<th align="center">仓库
					</th>
					<th align="center">仓区
					</th>
					<th align="center">库位
					</th>
					<th align="center">数量
					</th>
					<th align="center">单位
					</th>
					<th align="center">数量转换
					</th>
					<th align="center">转换单位
					</th>
				</tr>
<%--				<s:iterator value="listSell" id="pageSell" status="pstatus">--%>
<%--				<s:if test="#pstatus.index%2==1">--%>
<%--							<tr align="center" bgcolor="#e6f3fb" --%>
<%--								onmouseover="chageBgcolor(this)"--%>
<%--								onmouseout="outBgcolor(this,'#e6f3fb')">--%>
<%--						</s:if>--%>
<%--						<s:else>--%>
<%--							<tr align="center"  onmouseover="chageBgcolor(this)"--%>
<%--								onmouseout="outBgcolor(this,'')">--%>
<%--						</s:else>--%>
<%--						<td>--%>
<%--							<s:if test="#pstatus.index%2==1">--%>
<%--								<font>--%>
<%--							</s:if>--%>
<%--							<s:else>--%>
<%--								<font color="#c0dcf2">--%>
<%--							</s:else>--%>
<%--							<s:property value="#pstatus.index+1" />--%>
<%--							</font>--%>
<%--						</td>--%>
<%--					<td align="left">${pageSell.sellMarkId}--%>
<%--					</td>--%>
<%--					<td align="left">${pageSell.sellGoods}--%>
<%--					</td>--%>
<%--					<td align="left">${pageSell.processNo}--%>
<%--					</td>--%>
<%--					<td align="left">${pageSell.kgliao}--%>
<%--					</td>--%>
<%--					<td align="left">${pageSell.sellLot}--%>
<%--					</td>--%>
<%--					<td align="left">${pageSell.sellFormat}--%>
<%--					</td>--%>
<%--					<td align="center">图号${pageSell.}--%>
<%--					</td>--%>
<%--					<td align="left">${pageSell.sellWarehouse}--%>
<%--					</td>--%>
<%--					<td align="left">${pageSell.goodHouseName}--%>
<%--					</td>--%>
<%--					<td align="left">${pageSell.kuwei}--%>
<%--					</td>--%>
<%--					<td align="right" >--%>
<%--						${pageSell.sellCount}--%>
<%--					</td>--%>
<%--					<td align="left">${pageSell.sellUnit}--%>
<%--					</td>--%>
<%--					<td align="right">${pageSell.sellZhishu}--%>
<%--					</td>--%>
<%--					<td align="left">${pageSell.goodsStoreZHUnit}--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--				</s:iterator>--%>
				</table>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function() {
	ajax_findSellList();
})

/**//** 
 **    ================================================================================================== 
 **    类名：CLASS_PRINT 
 **    功能：打印分页 
 **    示例： 
    --------------------------------------------------------------------------------------------------- 

        var pp = new CLASS_PRINT();

        window.onload = function(){
            pp.header = document.getElementById("tabHeader");
            pp.content= document.getElementById("tabDetail");
            pp.footer = document.getElementById("tabFooter");

            pp.hideCols("5,7");    
            pp.hideRows("3,15");
            pp.pageSize = 10;    
        }

        <BODY onbeforeprint="pp.beforePrint()" onafterprint="pp.afterPrint()">


 */
function CLASS_PRINT() {
	this.header = null;
	this.content = null;
	this.footer = null;
	this.board = null;
	this.pageSize = 10;

	var me = this;

	//哈希表类
	function Hashtable() {
		this._hash = new Object();
		this.add = function(key, value) {
			if (typeof (key) != "undefined") {
				if (this.contains(key) == false) {
					this._hash[key] = typeof (value) == "undefined" ? null
							: value;
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		this.remove = function(key) {
			delete this._hash[key];
		}
		this.count = function() {
			var i = 0;
			for ( var k in this._hash) {
				i++;
			}
			return i;
		}
		this.items = function(key) {
			return this._hash[key];
		}
		this.contains = function(key) {
			return typeof (this._hash[key]) != "undefined";
		}
		this.clear = function() {
			for ( var k in this._hash) {
				delete this._hash[k];
			}
		}

	}

	//字符串转换为哈希表
	this.str2hashtable = function(key, cs) {

		var _key = key.split(/,/g);
		var _hash = new Hashtable();
		var _cs = true;
		if (typeof (cs) == "undefined" || cs == null) {
			_cs = true;
		} else {
			_cs = cs;
		}

		for ( var i in _key) {
			if (_cs) {
				_hash.add(_key[i]);
			} else {
				_hash.add((_key[i] + "").toLowerCase());
			}

		}
		return _hash;
	}

	this._hideCols = this.str2hashtable("");
	this._hideRows = this.str2hashtable("");

	this.hideCols = function(cols) {
		me._hideCols = me.str2hashtable(cols)
	}

	this.isHideCols = function(val) {
		return me._hideCols.contains(val);
	}

	this.hideRows = function(rows) {
		me._hideRows = me.str2hashtable(rows)
	}

	this.isHideRows = function(val) {
		return me._hideRows.contains(val);
	}

	this.afterPrint = function() {
		var table = me.content;

		if (typeof (me.board) == "undefined" || me.board == null) {
			me.board = document.getElementById("divPrint");
			if (typeof (me.board) == "undefined" || me.board == null) {
				me.board = document.createElement("div");
				document.body.appendChild(me.board);
			}
		}

		if (typeof (table) != "undefined") {
			for ( var i = 0; i < table.rows.length; i++) {
				var tr = table.rows[i];
				for ( var j = 0; j < tr.cells.length; j++) {
					if (me.isHideCols(j)) {
						tr.cells[j].style.display = "";
					}
				}
			}
		}

		me.content.style.display = '';
		me.header.style.display = '';
		me.footer.style.display = '';
		me.board.innerHTML = '';

	}

	this.beforePrint = function() {

		var table = me.content;

		if (typeof (me.board) == "undefined" || me.board == null) {
			me.board = document.getElementById("divPrint");
			if (typeof (me.board) == "undefined" || me.board == null) {
				me.board = document.createElement("div");
				document.body.appendChild(me.board);
			}
		}

		if (typeof (table) != "undefined" && this.hideCols.length > 0) {

			for ( var i = 0; i < table.rows.length; i++) {
				var tr = table.rows[i];
				for ( var j = 0; j < tr.cells.length; j++) {
					if (me.isHideCols(j)) {
						tr.cells[j].style.display = "none";
					}
				}
			}
		}

		///开始分页    
		var pageSize = this.pageSize;

		var head = me.header;
		var foot = me.footer;

		var page = new Array();
		var rows = "";
		var rowIndex = 1;

		var cp = 0;
		var tp = 0;

		for (i = 1; i < table.rows.length; i++) {
			if (this.isHideRows(i) == false) {
				if ((((rowIndex - 1) % pageSize) == 0 && rowIndex > 1)
						|| i == table.rows.length) {
					page[page.length] = getTable(head, table, rows, foot);

					rows = getOuterHTML(table.rows[i]) + "\n";
					rowIndex = 2;

				} else {
					rows += getOuterHTML(table.rows[i]) + "\n";
					rowIndex++;
				}
			}
		}

		if (rows.length > 0) {
			page[page.length] = getTable(head, table, rows, foot);
		}

		tp = page.length;

		for ( var i = 0; i < page.length; i++) {
			page[i] = page[i].replace(/\<\!--ct-->/g,
					'第' + (i + 1) + '页 共' + tp + '页').replace(/\<\!--cp--\>/g,
					i + 1).replace(/\<\!--tp--\>/g, tp);
		}

		head.style.display = 'none';
		foot.style.display = 'none';
		table.style.display = 'none';
		if (page.length > 1) {
			me.board.innerHTML = page.join("\n<div class='pageNext'></div>");
		} else {
			me.board.innerHTML = page.join("");
		}
	}

	function getOuterHTML(node) {
		if (typeof (node) != "undefined"
				&& typeof (node.outerHTML) != "undefined") {
			return node.outerHTML;
		}

		var emptyElements = {
			HR : true,
			BR : true,
			IMG : true,
			INPUT : true
		};
		var specialElements = {
			TEXTAREA : true
		};

		var html = '';
		switch (node.nodeType) {
		case Node.ELEMENT_NODE:
			html += '<';
			html += node.nodeName;
			if (!specialElements[node.nodeName]) {
				for ( var a = 0; a < node.attributes.length; a++)
					html += ' ' + node.attributes[a].nodeName.toUpperCase()
							+ '="' + node.attributes[a].nodeValue + '"';
				html += '>';
				if (!emptyElements[node.nodeName]) {
					html += node.innerHTML;
					html += '<\/' + node.nodeName + '>';
				}
			} else
				switch (node.nodeName) {
				case 'TEXTAREA':
					var content = '';
					for ( var a = 0; a < node.attributes.length; a++)
						if (node.attributes[a].nodeName.toLowerCase() != 'value')
							html += ' '
									+ node.attributes[a].nodeName.toUpperCase()
									+ '="' + node.attributes[a].nodeValue + '"';
						else
							content = node.attributes[a].nodeValue;
					html += '>';
					html += content;
					html += '<\/' + node.nodeName + '>';
					break;
				}
			break;
		case Node.TEXT_NODE:
			html += node.nodeValue;
			break;
		case Node.COMMENT_NODE:
			html += '<!' + '--' + node.nodeValue + '--' + '>';
			break;
		}
		return html;
	}

	function getTable(header, table, content, footer) {
		var htm = "";

		if (typeof (header) != "undefined") {
			htm += getOuterHTML(header);
		}

		if (typeof (table) != "undefined") {
			htm += "\n<" + table.tagName;

			for ( var i = 0; i < table.attributes.length; i++) {
				if (table.attributes[i].specified) {
					if (table.attributes[i].name == "style")
						htm += " style='" + table.style.cssText + "'";
					else
						htm += " " + table.attributes[i].nodeName + "='"
								+ table.attributes[i].nodeValue + "'";
				}
			}

			if (table.rows.length > 0) {
				htm += ">\n" + getOuterHTML(table.rows[0]) + content + "</"
						+ table.tagName + ">";
			} else {
				htm += ">\n" + content + "</" + table.tagName + ">\n";
			}
		}

		if (typeof (footer) != "undefined") {
			htm += getOuterHTML(footer);
		}

		return htm;
	}

	if (!window.attachEvent) {
		window.attachEvent = function() {
			window.addEventListener(arguments[0].substr(2), arguments[1],
					arguments[2]);
		}
	}
}

var pp = new CLASS_PRINT();

!function() {
	getQRCode(60, 60, '${waigouOrder.planNumber}', 'ercode');
	pp.pageSize = 6; //控制打印行数
	pp.header = document.getElementById("tabHeader");
	var tabDetail = document.getElementById("tabDetail");
	var m = tabDetail.rows.length - 1;
	var q = Math.ceil(m / pp.pageSize);
	q = pp.pageSize * q - m;
	for ( var j = 0; j < q; j++) {
		var newtr = "<tr style='' ondblclick='noprintTr(this,"+i+")'>";
		var cells = tabDetail.rows[j].cells.length;
		for ( var o = 0; o < cells; o++) {
			newtr += "<td>&nbsp;</td>";
		}
		newtr += "</tr>";
		$(tabDetail).append(newtr);
	}

	pp.content = tabDetail;
	pp.footer = document.getElementById("tabFooter");

	pp.beforePrint();
}();

function noprintTr(obj1,num){
	var tdclass =	$(obj1).attr("class")
	if(tdclass!='notprintTd'){
		$(obj1).addClass("notprintTd");
	}else{
		$(obj1).removeClass();
	}
}
function ajax_findSellList(){
		$.ajax( {
		type : "POST",
		url : "WaigouwaiweiPlanAction!ajax_findSellList.action",
		data : {
				id:${id}
			},
		dataType : "json",
		success : function(data) {
			if(data!=null){
				var html = "";
				var lastmarkId = "";
				var sum =0;
				$(data).each(function(i,se){
					var sumtr = "";
					if(lastmarkId== se.sellMarkId){
						sum+=se.sellCount;
					}else{
						if(sum>0){
							sumtr='<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>' +
								'<td></td><th align="right">合计</th><th align="right">'+sum+'</th><td></td><td></td><td></td></tr>'
								html+=sumtr;
						}
						sum = se.sellCount;
					}
					if(i%2==1){
							html+='<tr align="center" bgcolor="#e6f3fb" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,&apos;#e6f3fb&apos;)">'
						}else{
							html+='<tr align="center" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,&apos;&apos;)">'
						}
					
					var kgliao = se.kgliao==null?'':se.kgliao;
					var goodHouseName = se.goodHouseName==null?'':se.goodHouseName;
					var kuwei = se.kuwei ==null?'':se.kuwei;
					var sellZhishu = se.sellZhishu == null?'':se.sellZhishu;
					var goodsStoreZHUnit = se.goodsStoreZHUnit == null?'':se.goodsStoreZHUnit;
					var banBenNumber = se.banBenNumber == null?'':se.banBenNumber;
					html+='<td>'+(i+1)+'</td><td>'+se.sellMarkId+'</td><td>'+se.sellGoods+'</td>' +
					'<td>'+banBenNumber+'</td><td>'+se.processNo+'</td><td>'+kgliao+'</td><td>'+se.sellLot+'</td>' +
					'<td>'+se.sellFormat+'</td><td>'+se.sellWarehouse+'</td><td>'+goodHouseName+'</td>'+
					'<td>'+kuwei+'</td><td align="right">'+se.sellCount+'</td><td>'+se.sellUnit+'</td>' +
					'<td>'+sellZhishu+'</td><td>'+goodsStoreZHUnit+'</td></tr>';
					lastmarkId = se.sellMarkId;
				})
				if(sum>0){
					html+='<td></td><td><td></td></td><td></td><td></td><td></td><td></td><td></td><td></td>' +
								'<td></td><th align="right">合计</th><th align="right">'+sum+'</th><td></td><td></td><td></td>'	
				}
				$("#sellTable").append(html);
			}
		}
	})
	
}



</script>
	</body>
</html>
