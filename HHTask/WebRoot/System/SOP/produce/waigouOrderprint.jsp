<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<title>${waigouOrder.planNumber}</title>
		<style type="text/css">
/**/ /**
*    分页打印打印相关
*/
@media print {
	.notprint {
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
	.proName_td {
		overflow: hidden;
		width: 240px;
		font-size:1mm;
		line-height:12px;
		border: 1px solid black;
	}
	.specification_td {
		overflow: hidden;
		width: 280px;
		font-size:1mm;
		line-height:12px;
		border: 1px solid black;
	}
	
	.shenhe {  
		position: absolute; 
		top: 40px; 
		right: 10px; 
		z-index: 10; 
		transform: rotate(12deg); 
		opacity: 1.0;
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
	.proName_td {
		line-height: 25px;
		overflow: hidden;
		width: 120px;
		border: 1px solid black;
	}
}

.shenhe { 
	position: absolute; 
	top: 50px;
	right: 10px; 
	z-index: 10; 
	transform: rotate(12deg);
	opacity: 1.0;
	
}
.table th,.table td {
	border: solid #000;
	border-width: 1 1px 1px 1;
	padding: 0px;
}
</style>
	</head>
	<body style="font-family: sans-serif;">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<br />
				<div id="printdiv" align="right">
					<table width="100%" class="notprint">
						<tr>
							<td align=right>
								<input type="text" value="${pageSize1}" id="pageSize1"
									style="width: 75px;" onchange="numyanzheng(this,'zhengshu')" />
								<input type="button" value="调整分页"
									style="border: 1px solid #000000" onclick="print2('${strids}')" />
								<input type=button value='打印' onClick="isprint()"
									style="border: 1px solid #000000; cursor: pointer;">
							</td>
						</tr>
					</table>
				</div>
				<form action="" id="submit" method="post">
					<s:iterator value="waigouOrderList" id="waigouOrder"
						status="statusdf">
						<input type="hidden" value="${waigouOrder.id}"
							id="wgorderId_${statusdf.index}" name="processIds" />
						<div>
							<table width="100%" border=0 align=center
								id="tabHeader_${statusdf.index}"
								style="font-size: 15px; line-height: 15px;">
								<tr>
									<td colspan="15" align="right">
										<input type="hidden" value="${waigouOrder.status}"
											id="shehe_status_${statusdf.index}" />
										<s:if
											test="#waigouOrder.status!='待核对'&&#waigouOrder.status!='待审批'&&#waigouOrder.status!='待通知'">
											<div id="shenhe_${statusdf.index}" class="shenhe">
												<img alt="" src="<%=basePath%>/img/yishenhe.png"
													style="transform: rotate(8deg);" width="80px;"
													height="50px;" />
											</div>
										</s:if>
									</td>
								</tr>
								<tr>
									<th colspan="10" align="right"
										style="font-size: 22px; height: 25px; margin: 0px; line-height: 20px;">
										${companyInfo.name}
										<br />
										<span style="font-size: 13px;">${companyInfo.englishName}</span>
									</th>
									<td align="right">
										<img id="showcode" alt=""
											src="barcode.action?msg=${waigouOrder.planNumber}&type=code39"
											width="200px" height="40px" align="top" />
									</td>
								</tr>
								<tr>
									<td align="right" colspan="15"></td>
								</tr>
								<tr>
									<th colspan="15" align="right"
										style="font-size: 12px;   margin: 0px;">
										电话:
										${companyInfo.tel}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										传真: ${companyInfo.fax}
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<!--ct-->
									</th>
								</tr>
								<tr>
									<th colspan="11" style="border: hidden; font-size: x-large;"
										align="center">
										${waigouOrder.type}采购订单

									</th>
								</tr>
								<tr>
									<th style="border: hidden; width: 100px;" align="left"
										colspan="8">
										REY:01
									</th>
									<th style="border: hidden;" align="center" colspan="4">
										QP140900-C
									</th>
								</tr>
								<tr>
									<th style="border: hidden; width: 100px;" align="left"
										colspan="8">
										供应商编号: ${waigouOrder.userCode}
									</th>
									<th style="border: hidden;" align="left" colspan="4">
										订单编号: ${waigouOrder.planNumber}
									</th>
								</tr>
								<tr>
									<th style="border: hidden;" align="left" colspan="8">

										供应商名称: ${waigouOrder.gysName}
									</th>
									<th style="border: hidden;" colspan="4" align="left">
										制单日期: ${waigouOrder.addTime}
									</th>
								</tr>
								<tr>
									<th style="border: hidden;" colspan="8" align="left">
										地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址:
										${waigouOrder.ghAddress}
									</th>
									<th style="border: hidden;" colspan="4" align="left">
										采&nbsp;&nbsp;购&nbsp;&nbsp;员: ${waigouOrder.addUserName}
									</th>
								</tr>
								<tr>
									<th style="border: hidden;" colspan="8" align="left">
										联&nbsp;&nbsp;&nbsp;系&nbsp;&nbsp;&nbsp;人:
										${waigouOrder.lxPeople}
									</th>
									<th style="border: hidden;" colspan="4" align="left">
										付款方式: ${waigouOrder.payType}
									</th>
								</tr>
								<tr>
									<th style="border: hidden; border-bottom: inherit;" colspan="8"
										align="left">
										电话&nbsp;/&nbsp;手机: ${waigouOrder.gysPhone}
									</th>
									<th style="border: hidden; border-bottom: inherit;" colspan="4"
										align="left">
										票据类型: 增值税发票 ${waigouOrder.piaojuType}%
									</th>
								</tr>
							</table>
							<table class="table" border=1
								style="border-collapse: collapse; border: 1px solid #000000; line-height: 12px;"
								align=center cellpadding=3 id="tabDetail_${statusdf.index}">
								<tr style ="font-size: 1.5mm; height: 25px;">
									<th>
										件号
									</th>
									<th>
										物料名称
									</th>
									<th ondblclick="noprintTd(this,${statusdf.index})"
										style="cursor: pointer;">
										规格型号
									</th>
									<th ondblclick="noprintTd(this,${statusdf.index})"
										style="cursor: pointer;">
										版本
									</th>
									<th>
										物料类别
									</th>
									<th ondblclick="noprintTd(this,${statusdf.index})"
										style="cursor: pointer;">
										图号
									</th>
									<th>
										订单数量
									</th>
									<th>
										单位
									</th>
									<th>
										含税单价
									</th>
									<th>
										税率
									</th>
									<th>
										含税金额
									</th>
									<th ondblclick="noprintTd(this,${statusdf.index})"
										style="cursor: pointer;">
										交货日期
									</th>
									<s:if test="#waigouOrder.type=='辅料'">
										<th style="width: 100px;">
											需求部门
										</th>
										
									</s:if>
									<th ondblclick="noprintTd(this,${statusdf.index})"
										style="cursor: pointer;">
										备注
									</th>
								</tr>
								<s:iterator value="#waigouOrder.wwpList" id="waigouPlan"
									status="pagestatus1">
									<tr
										style=" letter-spacing: 1px; height: 35px; cursor: pointer;"
										ondblclick="noprintTr(this,${statusdf.index})">
										<th align="left" style="width: 120px; font-size: 6px">
											${waigouPlan.markId}
										</th>
										<td align="left" class="proName_td" style="width: 240px;height:35px;font-size:1px;line-height: 12px;">
												<p class="proName_p">
													${waigouPlan.proName}
												</p>
										</td>
										<td align="left"
											style="width: 240px; height: 35px;line-height: 12px;font-size:1px; "
											class="specification_td">
											<p class="specification_p" >
												${waigouPlan.specification}
											</p>
										</td>
										<th  align="left" style="width: 50px; font-size: 1px">
											${waigouPlan.banben}
										</th>
										<th  align="left" class="wgType_td" style="width: 120px;height35px;overflow:hidden;  font-size: 1px">
											${waigouPlan.wgType}
										</th>
										<th  align="left" style="width: 120px; font-size: 1px">
											${waigouPlan.tuhao}
										</th>
										<th align="right" style="width: 80px; font-size: 1px">
											${waigouPlan.number}
										</th>
										<th  align="left" style="width: 45px; ">
											${waigouPlan.unit}
										</th>
										<th align="right" id="td_hsPrice_${waigouPlan.id}"
											style="width: 80px; font-size: 6px">
											<SCRIPT type="text/javascript">
													var num =	formtNumber(${waigouPlan.hsPrice},4)
													$("#td_hsPrice_${waigouPlan.id}").html(num);
											</SCRIPT>
										</th>
										<th align="right" style="width: 75px; font-size: 6px">
											${waigouPlan.taxprice}
										</th>
										<th align="right" id="td_money_${waigouPlan.id}"
											style="width: 80px; font-size: 6px">
											<input type="hidden" value="${waigouPlan.money}"
												id="hid_money_${waigouPlan.id}">
											<!-- hj -->
											${waigouPlan.money}
											<!-- hj -->
											<SCRIPT type="text/javascript">
												var number = $("#hid_money_${waigouPlan.id}").val();
												var num =	formtNumber(number,4);
												$("#td_money_${waigouPlan.id}").html('<!-- hj -->'+num+'<!-- hj -->');
											</SCRIPT>
										</th>
										<th align="left" style="width: 135px; font-size: 6px">
											${waigouPlan.jiaofuTime}
										</th>
										<s:if test="#waigouOrder.type=='辅料'">
											<th>
												${waigouPlan.demanddept}
											</th>
										</s:if>
										<th align="left" style="width: 120px; font-size: 6px">
											${waigouPlan.remark}
										</th>
									</tr>
								</s:iterator>
							</table>
							<table width="100%" border=0 id="tabFooter_${statusdf.index}"
								class="tabFooter_" align="center" cellpadding=4
								style="font-size: 12px; line-height: 11px;">
								<tr>
									<td colspan="11"></td>
								</tr>
								<tr>
									<!-- byhj -->
								</tr>
								<tr>
									<th align="right">
										订单合计:
									</th>
									<th colspan="8" align="left" id="td_sumMoney">
										<input type="hidden"  id="sumMoney_${statusdf.index}" />
										<span id="span_sumMoney_${waigouOrder.id}"></span>
										<SCRIPT type="text/javascript">
												var num =	formtNumber(${waigouOrder.allMoneys},2)
												$("#span_sumMoney_${waigouOrder.id}").html(num);
												$("#sumMoney_${statusdf.index}").val(num);
											</SCRIPT>
									</th>
									<th align="right" colspan="3">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										订单合计金额大写:
									</th>
									<th colspan="4" id="daxie_${statusdf.index}" align="left"></th>
								</tr>
								<tr style="">
									<th align="right">
										送货地址:
									</th>
									<th colspan="12" align="left" style="border-top: inherit;">
										${waigouOrder.shAddress}
									</th>
								</tr>
								<tr>
									<th align="right" style="vertical-align: top;">
										条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;款:
									</th>
									<th style="" colspan="12" align="left">
										<ul style="line-height: 15px;">
											<li>
												1.供方必须遵循本订购单之交货期或需方采购部电话及书面通知调整之交期，若有延误，每延误一日扣除该批款3%。
												<bR />
											</li>
											<li>
												2.按工程图纸要求、品质保证期限为一年，供方交货之料件必须符合需方之品质要求，否则需方在一年内有权退货或要求赔偿供方不得拒绝。
												<bR />
											</li>
											<li>
												3.检验后如发现品质不良供方在接到通知后3日内应将退货取回，并尽快补料，延期需方自行处理，若急用料需挑选所需人工费由供方负责。
												<bR />
											</li>
										</ul>
									</th>
								</tr>
								<tr>
									<th align="right">
										备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:
									</th>
									<th colspan="12" align="left" style="border: hidden;"></th>
								</tr>
								<tr>
									<th colspan="20" align="left">
										制单:${waigouOrder.addUserName}
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										采购:
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 采购经理:
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 审批:
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										供应商确认:
									</th>
								</tr>
								<tr>
									<th style="border: hidden;" align="right">
										注:
									</th>
									<th style="border: hidden;" colspan="12" align="left">
										第一联: PMC &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第二联: 财务
									</th>
								</tr>
							</table>
						</div>
						<div id="divPrint_${statusdf.index}"></div>
						<div class='pageNext'></div>
					</s:iterator>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

		<SCRIPT type="text/javascript">
$(function(){
	var sumMoney =${waigouOrder.allMoneys};
		sumMoney =formtNumber(sumMoney,2);
	var daxie =	daxiezhuanhuan(sumMoney);
	$("#daxie").html(daxie);
	textoverflow('proName_td','proName_p');
	textoverflow('specification_td','specification_p');
})
function daxiezhuanhuan(n){
	 var fraction = ['角', '分'];
    var digit = [
        '零', '壹', '贰', '叁', '肆',
        '伍', '陆', '柒', '捌', '玖'
    ];
    var unit = [
        ['元', '万', '亿'],
        ['', '拾', '佰', '仟']
    ];
    var head = n < 0 ? '欠' : '';
    n = Math.abs(n);
    var s = '';
    for (var i = 0; i < fraction.length; i++) {
        s += (digit[Math.floor((n * 10 * Math.pow(10, i)).toFixed(5)) % 10] + fraction[i]).replace(/零./, '');
    }
    s = s || '整';
    n = Math.floor(n);
    for (var i = 0; i < unit[0].length && n > 0; i++) {
        var p = '';
        for (var j = 0; j < unit[1].length && n > 0; j++) {
            p = digit[n % 10] + unit[1][j] + p;
            n = Math.floor(n / 10);
        }
        s = p.replace(/(零.)*零$/, '').replace(/^$/, '零') + unit[0][i] + s;
    }
    return head + s.replace(/(零.)*零元/, '元')
        .replace(/(零.)+/g, '零')
        .replace(/^整$/, '零元整');
}


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
function CLASS_PRINT()
{
    this.header        = null;
    this.content    = null;
    this.footer        = null;
    this.board        = null;
    this.pageSize    = 6;
    this.count = 0;
    var me            = this;

    //哈希表类
    function Hashtable()
    {
        this._hash        = new Object();
        this.add        = function(key,value){
                            if(typeof(key)!="undefined"){
                                if(this.contains(key)==false){
                                    this._hash[key]=typeof(value)=="undefined"?null:value;
                                    return true;
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        }
        this.remove        = function(key){delete this._hash[key];}
        this.count        = function(){var i=0;for(var k in this._hash){i++;} return i;}
        this.items        = function(key){return this._hash[key];}
        this.contains    = function(key){return typeof(this._hash[key])!="undefined";}
        this.clear        = function(){for(var k in this._hash){delete this._hash[k];}}

    }

    //字符串转换为哈希表
    this.str2hashtable = function(key,cs){
        
            var _key    = key.split(/,/g);
            var _hash    = new Hashtable();
            var _cs        = true;
            if(typeof(cs)=="undefined"||cs==null){
                _cs = true;
            } else {
                _cs = cs;
            }

            for(var i in _key){
                if(_cs){
                    _hash.add(_key[i]);
                } else {
                    _hash.add((_key[i]+"").toLowerCase());
                }

            }
            return _hash;
        }

    this._hideCols    = this.str2hashtable("");
    this._hideRows    = this.str2hashtable("");

    this.hideCols = function(cols){
        me._hideCols = me.str2hashtable(cols)
    }

    this.isHideCols = function(val){    
        return    me._hideCols.contains(val);
    }

    this.hideRows = function(rows){
        me._hideRows = me.str2hashtable(rows)
    }

    this.isHideRows = function(val){    
        return    me._hideRows.contains(val);
    }

    this.afterPrint = function()
    {
        var table = me.content;        
        
        if(typeof(me.board)=="undefined"||me.board==null){        
            me.board = document.getElementById("divPrint_"+this.count);
            if(typeof(me.board)=="undefined"||me.board==null){
                me.board = document.createElement("div");
                document.body.appendChild(me.board);
            }
        }

        if(typeof(table)!="undefined"){
            for(var i =0;i<table.rows.length;i++){
                var tr = table.rows[i];
                for(var j=0;j<tr.cells.length;j++){
                    if(me.isHideCols(j)){
                        tr.cells[j].style.display = "";
                    }
                }
            }
        }

        me.content.style.display    = '';
        me.header.style.display        = '';
        me.footer.style.display        = '';
        me.board.innerHTML            = '';

    }

    this.beforePrint = function(count){
        var table = me.content;   
        if(typeof(me.board)=="undefined"||me.board==null){        
            me.board = document.getElementById("divPrint_"+this.count);
            if(typeof(me.board)=="undefined"||me.board==null){
                me.board = document.createElement("div");
                document.body.appendChild(me.board);
            }
        }


        if(typeof(table)!="undefined"&&this.hideCols.length>0){        
            
            for(var i =0;i<table.rows.length;i++){
                var tr = table.rows[i];
                for(var j=0;j<tr.cells.length;j++){
                    if(me.isHideCols(j)){                    
                        tr.cells[j].style.display = "none";
                    }
                }
            }
        }
    
        
        ///开始分页    
        var pageSize = this.pageSize;
        
        var head    = me.header;
        var foot    = me.footer;
        
        var page    = new Array();
        var rows    = "";    
        var rowIndex= 1;

        var cp        = 0;
        var tp        = 0;
        
        
        for(i=1;i<table.rows.length;i++){                
            if(this.isHideRows(i)==false){
                if((((rowIndex-1)%pageSize)==0&&rowIndex>1)||i==table.rows.length){                                
                    page[page.length] = getTable(head,table,rows,foot);
                                                 
                    rows    = getOuterHTML(table.rows[i]) + "\n" ; 
                    rowIndex= 2;
                                                                            
                } else {
                    rows    += getOuterHTML(table.rows[i]) + "\n"; 
                    rowIndex++;
                }
            }
        }
        
        if(rows.length>0){
            page[page.length] = getTable(head,table,rows,foot);
        }
	
        tp = page.length;
        for(var i=0;i<page.length;i++){
       		var arrays =		 page[i].split('<!-- hj -->');
       		var pagemoney = 0;
       		for(var j=1;j<arrays.length;j+=2){
       			pagemoney+=parseFloat(arrays[j]);
       		}
       		pagemoney = formtNumber(pagemoney,2)
       		 var daxie = daxiezhuanhuan(pagemoney);
            page[i] = page[i].replace(/\<\!--ct-->/g,'第'+(i+1)+'页 共' + tp+'页').replace(/\<\!--cp--\>/g,i+1).replace(/\<\!--tp--\>/g,tp);
            page[i] = page[i].replace(/\<!-- byhj -->/g,'<th align="right">本页合计:</th> <th colspan="8" align="left" >'+pagemoney
            	+'</th><th align="right"  colspan="3">本页合计金额大写:</th><th colspan="4" id="daxie" align="left" >'+daxie+'</th>').replace(/\<!-- byhj -->/g,i+1).replace(/\<!-- byhj -->/g,tp);
        }
        
                    
        head.style.display        = 'none';
        foot.style.display        = 'none';
        table.style.display        = 'none';
        if(page.length>1){
            me.board.innerHTML = page.join("\n<div class='pageNext'></div>");
        }else{
            me.board.innerHTML = page.join("");
        }
    }

function getOuterHTML (node) {

    if(typeof(node)!="undefined"&&typeof(node.outerHTML)!="undefined"){
        return node.outerHTML;
    }

    var emptyElements = {
      HR: true, BR: true, IMG: true, INPUT: true
    };
    var specialElements = {
      TEXTAREA: true
    };

    var html = '';
    switch (node.nodeType){
        case Node.ELEMENT_NODE:
            html += '<';
            html += node.nodeName;
            if (!specialElements[node.nodeName]) {
                for (var a = 0; a < node.attributes.length; a++)
                    html += ' ' + node.attributes[a].nodeName.toUpperCase() + '="' + node.attributes[a].nodeValue + '"';
                html += '>'; 
                if (!emptyElements[node.nodeName]){
                    html += node.innerHTML;
                    html += '<\/' + node.nodeName + '>';
                }
            }
            else 
                switch (node.nodeName){
                    case 'TEXTAREA':
                        var content = '';
                        for (var a = 0; a < node.attributes.length; a++)
                            if (node.attributes[a].nodeName.toLowerCase() != 'value')
                                html += ' ' + node.attributes[a].nodeName.toUpperCase() + '="' + node.attributes[a].nodeValue + '"';
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

    function getTable(header,table,content,footer){
        var htm = "";

        if(typeof(header)!="undefined"){
            htm += getOuterHTML(header);
        }

        if(typeof(table)!="undefined"){        
            htm += "\n<" + table.tagName;
            
            for(var i =0;i<table.attributes.length;i++){
                if(table.attributes[i].specified){
                    if(table.attributes[i].name=="style")
                        htm += " style='" + table.style.cssText + "'";
                    else
                        htm += " " + table.attributes[i].nodeName + "='" + table.attributes[i].nodeValue + "'";
                }        
            }    
            
            if(table.rows.length>0){
                htm += ">\n" + getOuterHTML(table.rows[0]) + content + "</" + table.tagName + ">";
            } else {
                htm += ">\n" + content + "</" + table.tagName + ">\n";
            }        
        }

        if(typeof(footer)!="undefined"){
            htm += getOuterHTML(footer);
        }
        
        return htm;
    }

    if(!window.attachEvent){
        window.attachEvent = function(){window.addEventListener(arguments[0].substr(2),arguments[1],arguments[2]);}
    }
}



var size = <s:property value="waigouOrderList.size()"/>
function print1(){
	var	pageSize = ${pageSize1}
	if(pageSize == '' || pageSize == 0){
		pageSize = 6
	}
	if(size!=null && size>0){
		for(var i=0;i<size;i++){
			var pp = new CLASS_PRINT();
			 pp.pageSize = pageSize; 
			pp.header = document.getElementById("tabHeader_"+i);
   			var tabDetail = document.getElementById("tabDetail_"+i);
			var m = tabDetail.rows.length-1;
			var q =m%pp.pageSize;
				if(q>0){
					q = pp.pageSize-q;
				}
   			 for(var j=0;j<q;j++){
   				 var newtr = "<tr style='font-size: 2.5mm; height: 35px;cursor: pointer;' ondblclick='noprintTr(this,"+i+")'>";
   				 var cells = tabDetail.rows[0].cells.length;
   				 for(var o=0;o<cells;o++){
   					 newtr+="<td>&nbsp;</td>";
   				 }
   				 newtr+="</tr>";
   				 $(tabDetail).append(newtr);
   			 }
			
   			pp.content = tabDetail;
   			 pp.footer = document.getElementById("tabFooter_"+i);
   			 
   			  var status = $("#shehe_status_"+i).val();
   			  if('待核对'==status){
				$("#shenhe_"+i).hide();
			}
   			  pp.count =i;
   			  var sumMoney = $("#sumMoney_"+i).val();
   			  var daxie =	daxiezhuanhuan(sumMoney);
			$("#daxie_"+i).html(daxie);
			pp.beforePrint();
		}
	}
}
$(function(){
	var	pageSize = $("#pageSize1").val();
	if(pageSize == 0){
		 $("#pageSize1").val(6);
	}
	print1();
})


function isprint(){
	if('${tag}'.indexOf('gys')<0 && '${Users.dept}'!='供应商'){
			$.ajax( {
		type : "POST",
		url : "WaigouwaiweiPlanAction!wgOrderIsPrint.action?pageStatus=waiwei",
		data : $("#submit").serialize(),
		dataType : "json",
		success : function(data) {
			if(data=="true"){
				window.print();
			}
		}
	})
	}else{
		window.print();
	}

}
function noprintTd(obj1,num){
	var tdclass =	$(obj1).attr("class")
	var cellIndex =	obj1.cellIndex;
	if(tdclass!='notprintTd'){
	$("#tabDetail_"+num+" tr th").each(function(){
		if(this.cellIndex === cellIndex ){
			$(this).addClass("notprintTd");
		}
	})
	$("#tabDetail_"+num+" tr td").each(function(){
		if(this.cellIndex === cellIndex ){
			$(this).addClass("notprintTd");
		}
	})
	}else{
		$("#tabDetail_"+num+" tr th").each(function(){
		if(this.cellIndex === cellIndex ){
			$(this).removeClass();
		}
	})
	$("#tabDetail_"+num+" tr td").each(function(){
		if(this.cellIndex === cellIndex ){
			$(this).removeClass();
		}
	})
		
	}
}
function noprintTr(obj1,num){
	var tdclass =	$(obj1).attr("class")
	if(tdclass!='notprintTd'){
		$(obj1).addClass("notprintTd");
	}else{
		$(obj1).removeClass();
	}
}
function print2(id){
	var	pageSize = $("#pageSize1").val();
	if(pageSize == '' || pageSize == 0){
		pageSize = 6
	}
	window.location.href = 'WaigouwaiweiPlanAction!gotoprint.action?strids='+id+'&pageStatus=waigou&pageSize1='+pageSize;
}

</SCRIPT>
	</body>
</html>
