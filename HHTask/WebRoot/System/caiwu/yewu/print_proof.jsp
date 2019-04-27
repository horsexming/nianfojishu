<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style type="text/css">
body,td,th {
	font-size: 12px;
}
font {
	font-size: 20px;
}
</style>
<script language="javascript" type="text/javascript"
	src="<%=basePath%>javascript/javascript.js">
</script>
</head>
<body onload="load()">
	<div id="printDiv">
	<div style="top: 30px;">
   	<div id="head">
	  <table  class="table">
	    <tr>
	      <td width="19%">&nbsp;</td>
	      <td colspan="3" align="center" style="font-size:16px">付款凭证(单)</td>
	      <td width="16%">编号：<span style="font-size:20px">${pp.numbers }</span></td>
        </tr>
	    <tr>
	      <td>收款单位名称：<span style="font-size:20px">${pp.collectionUnit }</span></td>
	      <td width="23%">关联客户名称：<span style="font-size:15px">${pp.correlationCustomer }</span></td>
	      <td width="20%">评审单号：<span style="font-size:15px">${pp.reviewNumber }</span></td>
	      <td width="22%">合同号：<span style="font-size:15px">${pp.contractNumber }</span></td>
	      <td>日期：${pp.date }</td>
        </tr>
      </table>
	</div>
    <div id="bo">
<table width="100%" border="1" style="border-collapse: collapse;">
            <tr>
              <td width="8%" align="center">付款依据</td>
              <td width="10%" align="center">1合同</td>
              <td width="9%" align="center">2发票</td>
              <td width="14%" align="center">3协议</td>
              <td width="10%" align="center">4通知</td>
              <td width="14%" align="center">5其他依据请说明</td>
              <td colspan="4" align="center">审核内容</td>
            </tr>
            <tr>
              <td colspan="2" align="center">业务内容</td>
              <td align="center">请购申请单编号</td>
              <td align="center">订购单编号</td>
              <td align="center">入库单编号</td>
              <td align="center">发票号码</td>
              <td align="center">付款金额</td>
              <td align="center">是否借款</td>
              <td align="center">借款单号</td>
              <td align="center">审核人</td>
            </tr>
            <s:iterator id="busi" value="pp.buss">
            	<s:iterator id="inv" value="#busi.invoices" status="i">
            	<tr>
            		<s:if test="#i.index == 0 ">
	            		<td colspan="2">${busi.content }</td>
            		</s:if>
            		<s:else>
            			<td colspan="2"></td>
            		</s:else>
            		<td></td>
            		<td></td>
            		<td></td>
            		<td align="center">${inv.number }</td>
            		<td align="center">${inv.money }${inv.currencyType }</td>
            		<td align="center">否</td>
            		<td></td>
            		<td></td>
            	</tr>
            	</s:iterator>
            </s:iterator>
            <tr>
              <td colspan="2">合计</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td align="center">${pp.lowMoney }</td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td height="30px" align="center">付款金额</td>
              <td colspan="7" height="30px">人民币(大写)&nbsp;&nbsp;<span style="font-size:20px">${pp.total}</span></td>
              <td colspan="2" rowspan="3" valign="top">审核说明：</td>
            </tr>
            <tr>
              <td align="center">付款性质</td>
              <td align="center">1预付<font id="n0"></font></td>
              <td align="center">2中间付款<font id="n1"></font></td>
              <td align="center">3余款<font id="n2"></font></td>
              <td align="center">4质保金<font id="n3"></font></td>
              <td align="center">5借款<font id="n4"></font></td>
              <td align="center">6冲账<font id="n5"></font></td>
              <td align="center">7其他<font id="n6"></font></td>
            </tr>
            <tr>
              <td align="center">付款方式</td>
              <td align="left">1银行<font id="w0"></font></td>
              <td align="center">2汇票<font id="w1"></font></td>
              <td align="center">3汇兑<font id="w2"></font></td>
              <td align="center">4支票<font id="w3"></font></td>
              <td align="center">5贷记<font id="w4"></font></td>
              <td align="center">6现金<font id="w5"></font></td>
              <td align="center">7其他<font id="w6"></font></td>
            </tr>
            <tr>
              <td>付款情况</td>
              <td colspan="9"><table width="100%" border="1" style="border-collapse: collapse;">
                  <tr>
                    <td width="22%">1总额<font id="s0"></font></td>
                    <td width="24%">2已支付<font id="s1"></font></td>
                    <td width="26%">3本次应付<font id="s2"></font></td>
                    <td width="14%">4累计支付<font id="s3"></font></td>
                    <td width="14%">5余额<font id="s4"></font></td>
                  </tr>
              </table></td>
            </tr>
            <tr>
              <td align="center">付款条件</td>
              <td align="center">1即付<font id="c0"></font></td>
              <td align="center">2TT30天<font id="c1"></font></td>
              <td align="center">3TT60天<font id="c2"></font></td>
              <td align="center">4TT90天<font id="c3"></font></td>
              <td align="center">5TT120天<font id="c4"></font></td>
              <td align="center">6TT120天以上<font id="c5"></font></td>
              <td colspan="3" align="center">7其他条件请说明<font id="c6"></font></td>
            </tr>
            <tr>
              <td>类别</td>
              <td>1总务性采购<font id="ca0"></font></td>
              <td>2原材料采购<font id="ca1"></font></td>
              <td>3工程设备类采购<font id="ca2"></font></td>
              <td colspan="6">5其他类采购请说明<font id="ca3"></font></td>
            </tr>
            <tr>
              <td colspan="10"><table width="100%" border="0">
                <tr>
                  <td width="18%">审核说明</td>
                  <td width="7%">&nbsp;</td>
                  <td width="20%">&nbsp;</td>
                  <td width="5%">&nbsp;</td>
                  <td width="19%">&nbsp;</td>
                  <td width="6%">&nbsp;</td>
                  <td width="21%">&nbsp;</td>
                  <td width="4%">&nbsp;</td>
                </tr>
                <tr>
                  <td>1、授权批准</td>
                  <td>&nbsp;</td>
                  <td>4、发票和入库单核对一致</td>
                  <td>&nbsp;</td>
                  <td>7、原始单据正确完整合规</td>
                  <td>&nbsp;</td>
                  <td>10、例外报告</td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>2、订购单和请购单核对一致</td>
                  <td>&nbsp;</td>
                  <td>5、折扣退货核对一致</td>
                  <td>&nbsp;</td>
                  <td>8、支持性凭据核销标记</td>
                  <td>&nbsp;</td>
                  <td>Y：表示核对相符正确合规</td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>3、入库单和请购单核对一致</td>
                  <td>&nbsp;</td>
                  <td>6、供应商对账一致</td>
                  <td>&nbsp;</td>
                  <td>9、其他检查一致</td>
                  <td>&nbsp;</td>
                  <td>N：表示不符或不合规(需写出说明)</td>
                  <td>&nbsp;</td>
                </tr>
              </table></td>
            </tr>
          </table>
          <table width="100%" border="0">
            <tr>
              <td width="18%">批准：</td>
              <td width="19%">主管副总：</td>
              <td width="15%">部门主管：</td>
              <td width="16%">经办人：<span style="font-size:15px">${pp.agent }</span></td>
              <td width="17%">财务部：</td>
              <td width="15%">付款日期：</td>
            </tr>
      </table>
</div>
</div>
</div>
<input type="button" value="打印" onclick="pagePrint('printDiv','yes')"/>
<script type="text/javascript">
	function load(){
		document.getElementById("${pp.nature}").innerHTML="√"
		document.getElementById("${pp.conditions}").innerHTML="√";
		document.getElementById("${pp.situation}").innerHTML="√";
		document.getElementById("${pp.way}").innerHTML="√";
		document.getElementById("${pp.category}").innerHTML="√";
	}
</script>
</body>
</html>
