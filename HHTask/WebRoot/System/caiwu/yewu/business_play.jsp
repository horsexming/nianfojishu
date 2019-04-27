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
</style>
<script language="javascript" type="text/javascript"
	src="<%=basePath%>javascript/DatePicker/WdatePicker.js">
</script>
</head>

<body>
	<form action="business_saveProof.action" method="post" onsubmit="return valitime()">
	<div>
   	<div id="head">
	  <table  class="table">
	    <tr>
	      <td width="19%">&nbsp;</td>
	      <td colspan="3" align="center" style="font-size:16px">付款凭证(单)</td>
	      <td width="16%">编号：<span style="font-size:20px">${mark }</span></td>
        </tr>
	    <tr>
	      <td>收款单位名称：<span style="font-size:20px">${bu.collectionUnit }</span></td>
	      <td width="23%">关联客户名称：<input type="text" name="pp.correlationCustomer"/></td>
	      <td width="20%">评审单号：<input type="text" name="pp.reviewNumber"/></td>
	      <td width="22%">合同号：<input type="text" name="pp.contractNumber"/></td>
	      <td>日期：<input style="width: 155px" class="Wdate"
									type="text" name="pp.date" id="time"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
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
            <s:iterator id="pageList" value="lis">
	            <s:iterator id="inv" value="#pageList.invoices" status="i">
	            	<tr>
	            		<s:if test="#i.index == 0 ">
		            		<td colspan="2" align="center">${pageList.content }</td>
	            		</s:if>
	            		<s:else>
	            			<td colspan="2" align="center"></td>
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
              <td align="center">${mony}${bu.currencyType }</td>
              <td></td>
              <td></td>
              <td></td>
            </tr>
            <tr>
              <td height="30px" align="center">付款金额</td>
              <td colspan="7" height="30px">人民币(大写)&nbsp;&nbsp;<span style="font-size:20px">${role }${bu.currencyType }</span></td>
              <td colspan="2" rowspan="3" valign="top">审核说明：</td>
            </tr>
            <tr>
              <td align="center">付款性质</td>
              <td colspan="7">
              	<select name="pp.nature">
              	<s:iterator id="str" value="{'1预付','2中间付款','3余款','4质保金','5借款','6冲账','7其他'}" status="i">
              			<s:if test="#i.index == 0">
							<option value="n${i.index }" selected="selected">${str }</option>
						</s:if>
						<s:else>
							<option value="n${i.index }">${str }</option>
						</s:else>
              	  </s:iterator>
              </select>
              </td>
            </tr>
            <tr>
              <td align="center">付款方式</td>
              <td colspan="7">
              <select name="pp.way">
              	<s:iterator id="str" value="{'1银行','2汇票','3汇兑','4支票','5贷记','6现金','7其他'}" status="i">
              			<s:if test="#i.index == 0">
							<option value="w${i.index }" selected="selected">${str }</option>
						</s:if>
						<s:else>
							<option value="w${i.index }">${str }</option>
						</s:else>
              	  </s:iterator>
              </select>
              </td>
            </tr>
            <tr>
              <td>付款情况</td>
              <td colspan="9"><table width="100%" border="1" style="border-collapse: collapse;">
                  <tr>
                  	<td colspan="5">
                  	<select name="pp.situation">
                  		<s:iterator value="{'1总额','2已支付','3本次应付','4累计支付','5余额'}" id="str" status="i">
									<s:if test="#i.index == 0">
										<option value="s${i.index }" selected="selected">${str }</option>
									</s:if>
									<s:else>
										<option value="s${i.index }">${str }</option>
									</s:else>
						</s:iterator>
					</select>
                  	</td>
                  </tr>
              </table></td>
            </tr>
            <tr>
              <td align="center">付款条件</td>
              <td colspan="9">
              <select name="pp.conditions">
              	<s:iterator value="{'1即付','2TT30天','3TT60天','4TT90天','5TT120天','6TT120天以上','7其他条件说明'}" id="str" status="i">
						<s:if test="#i.index == 0">
							<option value="c${i.index }" selected="selected">${str }</option>
						</s:if>
						<s:else>
							<option value="c${i.index }">${str }</option>
						</s:else>
					</s:iterator>
				</select>
              </td>
            </tr>
            <tr>
              <td>类别</td>
              <td colspan="9">
              	<select name="pp.category">
              	<s:iterator value="{'1总务性采购','2原材料采购','3工程设备类采购','5其他类采购请说明'}" id="str" status="i">
						<s:if test="#i.index == 0">
							<option value="ca${i.index }" selected="selected">${str }</option>
						</s:if>
						<s:else>
							<option value="ca${i.index }">${str }</option>
						</s:else>
					</s:iterator>
				</select>
              </td>
              <!-- 
              <td>1总务性采购</td>
              <td>2原材料采购</td>
              <td>3工程设备类采购</td>
              <td colspan="6">5其他类采购请说明</td>
               -->
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
              <td width="16%">经办人：${session.Users.name}</td>
              <td width="17%">财务部：</td>
              <td width="15%">付款日期：</td>
            </tr>
      </table>
      </div>
      <hr/>
      <input type="hidden" name="pp.agent" value="${session.Users.name }"/>
      <input type="hidden" value="${bu.id }" name="buId"/>
      <input type="hidden" name="pp.numbers" value="${mark }"/>
      <input type="hidden" name="pp.lowMoney" value="${mony}${bu.currencyType }"/>
      <input type="hidden" name="pp.total" value="${role }${bu.currencyType }"/>
      <input type="hidden" name="pp.collectionUnit" value="${bu.collectionUnit }"/>
     <div align="center"><input type="submit" value="提交付款凭证"/></div>
</form>
</div>
	<script type="text/javascript">
		function valitime(){
			var time = document.getElementById("time").value;
			if(time == ""){
				alert("请选择办理日期！谢谢");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>
