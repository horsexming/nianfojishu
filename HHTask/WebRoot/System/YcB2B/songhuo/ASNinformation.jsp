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
    
    <title>My JSP 'ASNinformation.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="/util/sonHead.jsp"%>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <%@include file="/util/sonTop.jsp"%>
   <div>
    <div>ASN主要信息</div>
   <div style="float: left;">
   <div style="margin-left: 80px;float: left;" >可预约时间 2018-12-17 12:00~201-12-21 00:00</div>
    <div style="float: left;margin-left: 100px;"><input type="text"   class="Wdate form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',skin:'whyGreen'})" /> </div> 
</div>

<div style="fmargin-left:100px; ">
    <p style="color: red;float: left;">*</p>交货地点
   		<input class="browsers" list="browsers">
            <datalist id="browsers">
                <option value="清醒" />
                <option value="嗜睡" />
                <option value="烦躁" />
                <option value="昏迷" />
         </datalist>
</div>

<div style="fmargin-left:100px	; ">
备注<textarea rows="" cols="" style="width: 60%;" ></textarea>
</div>
    </div>
    

    
    <div>
     <p>物料信息</p>
     <s:if test="#p==0 ||  #p==null">

  </s:if>
    <s:else>
        <div style="float: left;" class="baogui">
    <p>当前有${p } 个物料未维护包规信息，</p><p style="color: red;float: left;">请先维护包规</p>
    </div>
    </s:else>
    
    <table border="1" cellspacing="0"  rules="all" bordercolor="#DEDEDE">
    <!-- 头 -->
   <tr style="background-color: #E8E8E8;height:30px; ">
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 40px;" value="序号"   readonly="readonly"/></td>
     <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="操作"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="客户"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="物料编码"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="物料版本"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="追溯类型"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="物料描述"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="发货数量"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="箱件数"  readonly="readonly" /></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="是否外检"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="外检信息"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="备注"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="字库"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="货位"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="源产地"  readonly="readonly" /></td>
   <td align="left"  style="background-color: #E8E8E8;"> <input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="环保标识"   readonly="readonly"/></td>
   <td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="日韩标识"   readonly="readonly"/></td>
   <td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="是否法检"   readonly="readonly"/></td>
	<td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="是否多件套"   readonly="readonly"/></td>
	<td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="套件数"   readonly="readonly"/></td>
	<td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="FCC认证"   readonly="readonly"/></td>
   <td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="CE认证"   readonly="readonly"/></td>
   </tr>
   
   
   <s:if test="itemASN==null">
    <tr style="background-color:#F7F7F7;">
    <td colspan="20" style="background-color:#F7F7F7;" align="center">
    <input type="text" value="主上 没有数据可查询!" readonly="readonly" style="border: none;background-color:#F7F7F7;font-size: 20px;">		
   </td>
   </tr>
   </s:if>
   <s:else>
 
    <s:iterator id="ASNlist" value="itemASN" status="stauts">
    <tr>
    <td style="background-color:#F7F7F7;"><s:property value="#stauts.index+1" /></td>
    <td style="background-color:#F7F7F7;"><input type="text" value="修改包规"  readonly="readonly"  style="border: none;color:#1890FF;   background-color:#F7F7F7;"/></td>
    <td style="background-color:#F7F7F7;"><input type="text" value="${ASNlist.receivedFinishFlag}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/</td>
    <td style="background-color:#F7F7F7;"><input type="text" value="${ASNlist.shipmentType}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>
     <td style="background-color:#F7F7F7;"><input type="text" value="${ASNlist.partNumber}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>    
      <td style="background-color:#F7F7F7;"><input type="text" value="${ASNlist.repOfficeCode}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>
       <td style="background-color:#F7F7F7;"><input type="text" value="${ASNlist.quantityAccepted}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>      
        <td style="background-color:#F7F7F7;"><input type="text" value="${ASNlist.sendConnecter}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>     
         <td style="background-color:#F7F7F7;"><input type="text" value="${ASNlist.repOfficeName}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>      
          <td style="background-color:#F7F7F7;"><select  style="border: none;background-color:#F7F7F7;width: 150px;"> <option value="N">N</option><option value="Y">Y</option>  </select> </td>        
           <td style="background-color:#F7F7F7;"><input type="text" value="${ASNlist.comments}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>         
            <td style="background-color:#FFFFFF;"><input type="text" value="${ASNlist.authorizationStatus}"   style="border: none;background-color:#FFFFFF;"/></td>         
             <td style="background-color:#F7F7F7;"><input type="text" value="${ASNlist.billToLocationId}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>          
              <td style="background-color:#F7F7F7;"><input type="text" value="${ASNlist.createdBy}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>            
               <td style="background-color:#F7F7F7;"><input type="text" value="${ASNlist.prhaInterfaceSourceCode}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>             
                <td style="background-color:#F7F7F7;"><input type="text" value="${ASNlist.recvVendorTelNum}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>
                 <td style="background-color:#F7F7F7;"><input type="text" value="${ASNlist.category}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>               
                  <td style="background-color:#F7F7F7;"><input type="text" value="${ASNlist.primaryKey}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>               
                   <td style="background-color:#F7F7F7;"><input type="text" value="${ASNlist.paymentTerms}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>               
                    <td style="background-color:#F7F7F7;"><input type="text" value="${ASNlist.businessMode}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>                  
                     <td style="background-color:#F7F7F7;"><input type="text" value="${ASNlist.colTaskOrPoStatus}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>
                      <td style="background-color:#F7F7F7;"><input type="text" value="${ASNlist.shipmentNum}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>
    </tr>
    </s:iterator>
   </s:else>
    </table>
    </div>
    
        <div>
        <p>包装信息</p>
    <table border="1" cellspacing="0"  rules="all" bordercolor="#DEDEDE">
    <!-- 头 -->
   <tr style="background-color: #E8E8E8;height:30px; ">
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 40px;" value="序号"   readonly="readonly"/></td>
     <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="物料编码"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="物料版本"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="发货数量"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="包规名称"   readonly="readonly"/></td>
      <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="包装行操作"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="*内装数量"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="箱件数"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="制造日期(DATE)"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="制造批次(M.loat)"  readonly="readonly" /></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="制造工厂"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="工厂代码"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="件套数SN/TN"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="*是否尾箱"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="净重"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="毛重"  readonly="readonly" /></td>
   <td align="left"  style="background-color: #E8E8E8;"> <input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="重量单位"   readonly="readonly"/></td>
   <td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="备注"   readonly="readonly"/></td>
   <td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="外箱长"   readonly="readonly"/></td>
	<td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="外箱宽"   readonly="readonly"/></td>
	<td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="外箱高"   readonly="readonly"/></td>
	<td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="尺寸单位"   readonly="readonly"/></td>
   <td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="是否带板送货"   readonly="readonly"/></td>
    <td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="栈板规格"   readonly="readonly"/></td> 
     <td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="每层装箱数"   readonly="readonly"/></td>  
     <td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="堆码层数"   readonly="readonly"/></td>
   </tr>
   
   
   
      <s:if test="ASN==null">
    <tr style="background-color:#F7F7F7;">
    <td colspan="20" style="background-color:#F7F7F7;" align="center">
    <input type="text" value="主上 没有数据可查询!" readonly="readonly" style="border: none;background-color:#F7F7F7;font-size: 20px;">		
   </td>
   </tr>
   </s:if>
   <s:else>
   
   <s:iterator id="asn" value="ASN" status="stauts">
   <tr>
    <td  align="left" style="background-color:#F7F7F7;"><s:property value="#stauts.index+1" /></td>
   <td align="left"  style="background-color: #F7F7F7;"><input type="text" value="${asn.shipmentType}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>
     <td align="left"  style="background-color: #F7F7F7;"><input type="text" value="${asn.partNumber}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>
       <td align="left"  style="background-color: #F7F7F7;"><input type="text" value="${asn.sendConnecter}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>
         <td align="left"  style="background-color: #F7F7F7;"><input type="text" value="${asn.termsMode}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>         
           <td align="left"  style="background-color: #F7F7F7;"><input type="text" value="+ ==-"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>          
             <td align="left"  style="background-color: #FFFFFF;"><input type="text" value="${asn.unitOfMeasure}" style="border: none;background-color:#FFFFFF;"/></td>          
               <td align="left"  style="background-color: #FFFFFF;"><input type="text" value="${asn.repOfficeName}"  style="border: none;background-color:#FFFFFF;"/></td>
                 <td align="left"  style="background-color: #FFFFFF;"><input type="text" value="${asn.promiseDate}"  style="border: none;background-color:#FFFFFF;"/></td>             
                   <td align="left"  style="background-color: #FFFFFF;"><input type="text" value="${asn.expireDate}"   style="border: none;background-color:#FFFFFF;"/></td>                
                     <td align="left"  style="background-color: #FFFFFF;"><input type="text" value=""   style="border: none;background-color:#FFFFFF;"/></td>
                       <td align="left"  style="background-color: #F7F7F7;"><input type="text" value=""   readonly="readonly"  style="border: none;background-color:#F7F7F7;"/></td>                   
                         <td align="left"  style="background-color: #F7F7F7;"><input type="text" value="${asn.shipToLocation}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>                        
                           <td align="left"  style="background-color: #FFFFFF;"><select  style="border: none;background-color:#FFFFFF;width: 150px;"> <option value="N">N</option><option value="Y">Y</option>  </select> </td>
                             <td align="left"  style="background-color: #FFFFFF;"><input type="text" value="${asn.issuOffice}"   style="border: none;background-color:#FFFFFF;"/></td>                         
                               <td align="left"  style="background-color: #FFFFFF;"><input type="text" value="${asn.remark}"  style="border: none;background-color:#FFFFFF;"/></td>                             
                                 <td align="left"  style="background-color: #F7F7F7;"><input type="text" value="${asn.prNumber}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>
                                   <td align="left"  style="background-color: #FFFFFF;"><input type="text" value="${asn.authorizationStatus}"   style="border: none;background-color:#FFFFFF;"/></td>                                   
                                     <td align="left"  style="background-color: #FFFFFF;"><input type="text" value="${asn.taskNum}" style="border: none;background-color:#FFFFFF;"/></td>
                                      <td align="left"  style="background-color: #FFFFFF;"><input type="text" value="${asn.agentName}"   style="border: none;background-color:#FFFFFF;"/></td>                                      
                                       <td align="left"  style="background-color: #FFFFFF;"><input type="text" value="${asn.carrierName}"   style="border: none;background-color:#FFFFFF;"/></td>
                                        <td align="left"  style="background-color: #F7F7F7;"><input type="text" value="${asn.typeLookupCode}"  readonly="readonly" style="border: none;background-color:#F7F7F7;"/></td>                                       
                                         <td align="left"  style="background-color: #FFFFFF;"><input type="text" value="${asn.objectChangeContext}"   style="border: none;background-color:#FFFFFF;"/></td>                                         
                                          <td align="left"  style="background-color: #FFFFFF;"><input type="text" value="${asn.projectNo}"   style="border: none;background-color:#FFFFFF;"/></td>                                         
                                           <td align="left"  style="background-color: #FFFFFF;"><input type="text" value=""  style="border: none;background-color:#FFFFFF;"/></td>                                         
                                            <td align="left"  style="background-color: #FFFFFF;"><input type="text" value=""   style="border: none;background-color:#FFFFFF;"/></td>
   
   </tr>
   
   </s:iterator>
   </s:else>
    </table>
    </div>
    
    <div>
    <p><h4>物流信息</h4>说明：港料必填，其他情况下非	必填</p>
    
    <div>
    
    <div style="float: left;margin-left: 120px;"  >运输方式 
      <input class="browsers" list="browsers" >
            <datalist id="browsers">
                <option value="清醒" />
                <option value="嗜睡" />
                <option value="烦躁" />
                <option value="昏迷" />
         </datalist></div>   
         <div style="float: left;margin-left: 20%;" >承运商<input type="text" /></div>
          <div style="margin-left:70%;">出口国<input type="text" /></div>         
         </div>
    </div>
    
    
   		 <div>
        <div style="float: left;margin-left: 133px;">发货港 <input type="text" /> </div>   
         <div style="float: left;margin-left: 19.3%;">提货单号<input type="text" /></div>
          <div style="margin-left:69.3%;">主运单号<input type="text" /></div>         
         </div>
         
        
          <div>
        <div style="float: left;margin-left: 90px;">航班/船/车牌号<input type="text" /> </div>   
         <div style="float: left;;margin-left: 20%;">联系人<input type="text" /></div>
          <div  style="margin-left:69.4%;" > 联系电话<input type="text" /></div>         
         </div>

    </div>
    
  <div align="center">
  <font> <input  type="button" value="上一步"  class="prev" style="color: #FFFFFF;background-color: #40A9FF;border-color: #40A9FF;"/> <input  type="button" value="下一步"  class="nextone" style="color: #FFFFFF;background-color: #40A9FF;border-color: #40A9FF;"/>  </font>
  </div>
  <script type="text/javascript">
  //上一步点击事件
  $(".prev").click(function(){
  parent.prevchosedn();
  });
  
  </script>
  
  
    </body>
</html>
