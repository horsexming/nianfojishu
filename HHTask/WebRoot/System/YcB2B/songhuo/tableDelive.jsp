<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'tableDelive.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="/util/sonHead.jsp"%>
		<script type="text/javascript" src="System/YcB2B/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="System/YcB2B/songhuo/Deliveery.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <%@include file="/util/sonTop.jsp"%>
        
  <div>查询条件∧</div>
  <div>
  
  <form action="HttpRestClientForaction!sellDN.action" method="post">
  <div style="float: left; margin-left: 30px;">
  <p>客户</p>
  <input type="text"  />
  </div>
  
   <div style="float: left; margin-left: 30px;">
  <p>需求ID2</p>
  <input type="text"   placeholder="多个需求ID2以','分隔"/>
  </div>
  
  <div style="float: left; margin-left: 30px;">
   <p>物料编码</p>
  <input type="text" placeholder="多个物料编码以','分隔"/>
  </div>
  
  <div style="float: left; margin-left: 30px;">
    <p>物料版本</p>
  <input type="text"/>
  </div>
  
  <div style="float: left; margin-left: 30px;">
      <p>交货地点</p>
  <input type="text" placeholder="请输入后选择"/>
 </div>
 
 <div style="float: left; margin-left: 30px;"> 
   <p>只查询有效期内DN</p>     
  <select style="width:170px" name="validityPeriod">
  <option value="N">N</option>
  <option value="Y">Y</option>
  </select>
  
  
  </div>
    </div>
    <input type="submit"  class="submit"  value="查询" style="border-color:#40A9FF;background-color:#40A9FF;color:#FFFFFF;"/> 
    <input type="reset" value="重置"   class="reset" style="margin-top: 10px;margin-left: 94%;border-color:#40A9FF;background-color:#FFFFFF;color: #40A9FF;  " />
  </form>
   
   
   <table border="1" cellspacing="0"  rules="all" bordercolor="#DEDEDE" id="tab">
   <!-- 头 -->
   <tr style="background-color: #E8E8E8;height:30px; ">
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 40px;" value="序号"   readonly="readonly"/></td>
      <td align="left"  style="background-color: #E8E8E8;"><input type="checkbox" style="border: none;background-color: #E8E8E8;width: 40px;" class="checkall" /></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="客户"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="需求ID2"  readonly="readonly" /></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="需求/计划交货日期"  readonly="readonly" /></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="物料编码"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="物料版本"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="需求数量"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="可发货数量"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="PO可预约总数量"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="本次发货数量"  readonly="readonly" /></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="交货地点"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="子库"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="货位"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="最早到货时间"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="要求到货数量"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"> <input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="最晚到货时间"   readonly="readonly"/></td>
    <td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="来源"   readonly="readonly"/></td>
   </tr>
   <s:if test="DNlist==null">
    <tr style="background-color:#F7F7F7;">
    <td colspan="20" style="background-color:#F7F7F7;" align="center">
    <input type="text" value="主上 没有数据可查询!" readonly="readonly" style="border: none;background-color:#F7F7F7;font-size: 20px;">		
   </td>
   </tr>
   </s:if>
   <s:else>
   <s:iterator id="dnlist" value="DNlist" status="stauts">
   <tr>
   <td style="background-color:#F7F7F7;"><s:property value="#stauts.index+1" /></td>
    <td style="background-color:#F7F7F7;"><input type="checkbox"  class="check" /></td>
    <td style="background-color:#F7F7F7;"><input type="text" value="${dnlist.orgName}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>
      <td style="background-color:#F7F7F7;"><input type="text" value="${dnlist.poHeaderId}" readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>
        <td style="background-color:#F7F7F7;"><input type="text" value="${dnlist.needByDate }" readonly="readonly" style="border: none;background-color:#F7F7F7;" /></td>
          <td style="background-color:#F7F7F7;"><input type="text" value="${dnlist.itemCode }"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>
            <td style="background-color:#F7F7F7;"><input type="text" value="${dnlist.itemRevision }"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>
              <td style="background-color:#F7F7F7;"><input type="text" value="${dnlist.shipmentType }"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>
                <td style="background-color:#F7F7F7;"><input type="text" value="${dnlist.receivedFinishFlag }"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>
                  <td style="background-color:#F7F7F7;"><input type="text" value="${dnlist.termsDescription }"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>
                    <td><input type="text" value="${dnlist.agentEmployeeNumber }"  style="border: none;background-color:#FFFFFF;"/></td>
                      <td style="background-color:#F7F7F7;"><input type="text" value="${dnlist.itemDescription }"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>
                        <td style="background-color:#F7F7F7;"><input type="text" value="${dnlist.typeLookupCode }"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>
                          <td style="background-color:#F7F7F7;"><input type="text" value="${dnlist.recvVendorAddr }" readonly="readonly"  style="border: none;background-color:#F7F7F7;"/></td>
                            <td style="background-color:#F7F7F7;"><input type="text" value="${dnlist.phrCreationDate }"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>
                              <td style="background-color:#F7F7F7;"><input type="text" value="${dnlist.rateDate }" readonly="readonly"  style="border: none;background-color:#F7F7F7;"/></td>
                              	<td style="background-color:#F7F7F7;"><input type="text" value="${dnlist.lastUpdateDate }"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td> 
                             	 <td style="background-color:#F7F7F7;"><input type="text" value="${dnlist.precision }"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>

   </tr>  
   </s:iterator>
   </s:else>
   
   	<tr>
						<td colspan="18" align="right">
							第
							<font color="red"><s:property value="cpage"   /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />
						</td>
					</tr>
   
   
   </table>
  
  
    <div align="center">
  <font><input  type="button" value="下一步"  class="nextone" style="color: #FFFFFF;background-color: #40A9FF;border-color: #40A9FF;"/>  </font>
  </div>
  
   </body>
</html>
