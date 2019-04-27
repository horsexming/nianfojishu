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
    
    <title>My JSP 'yuce.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="/util/sonHead.jsp"%>
	<script type="text/javascript" src="System/YcB2B/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="System/YcB2B/yuce.js"></script>
	<script type="text/javascript" src="System/YcB2B/exportExcel.js"></script>
	<style type="text/css">
	#two{
	background-color: #F5F5F5;
	float: left;
	width: 76%;
	height: 100%;
	}
	
	.tup{
		margin-top: 320px;	
		float: left;
	}
	
	
	
	</style>
  </head>
  
  <body style="width: 100%;font-family: "微软雅黑";">
  <%@include file="/util/sonTop.jsp"%>
  <div>
  
  
  <div   id="shou" style="background: #FAFAFA; width:22%;height:100%;float: left;">
	<p style="width: 80%;height:40px; line-height:50px; background-color: #EBEBEB;font-size: 20px;font-weight: bold; margin-top:50px; margin-left: 26px;" align="center" >查询</p>
  <form action="javaScript:void(0)" method="post">
		 <fieldset style="width:260px; height: 140px;">
		 <legend>物料编码</legend>
		 <div >
		 <font  size="1">  供方物料编码</font>
		<input type="text" style="width: 250px; height:25px;" name="suppItemCode">
		<td>
		<font size="1" style="margin-left: 100px;"> 或者 </font><br/>
		
		<font size="1" >华为物料编码</font>
		<input type="text" style="width: 250px; height:25px; " name="itemCode">
		</div>
		</fieldset>


	<table>
		<tr><td colspan="2"><font size="2"><span style="color: red;" >*</span>库存组织</font></td></tr>
		<tr><td  colspan="2">
		<input type="text" style="width: 260px; height:25px;" name="orgId" class="orgId"></td></tr>
		<tr><td colspan="2"><font size="2"><span style="color: red;">*</span>起止日期</font></td></tr>
		<tr>
		<td><input type="date"  name="startTime"  class="startTime"></td>

		<td ><input type="date"   name="endTime" class="endTime"></td>
		</tr>
		<tr><td colspan="2"><font size="2">采购模式	</font></td></tr>
		<tr><td colspan="2"><input type="text" style="width: 260px; height:25px;" name="purchaseMode"></td></tr>
		
		<tr><td colspan="2"><font size="2">采购员</font></td></tr>
		<tr><td colspan="2"><input type="text" style="width: 260px; height:25px;" name="buyerName"></td></tr>
		
		
		<tr><td colspan="2"><font size="2">欠料标识</font></td></tr>
		<tr><td colspan="2"><input type="text" style="width: 260px; height:25px;" name="version"></td></tr>
		<br/>
		<tr><td align="center"><input type="submit"  value="提交" style="background: #87CEFA; color: #FFFFFF;border-color:#87CEFA;"></td><td align="left"><input type="reset" value="重置" style="color:#87CEFA; background: #FFFFFF;border-color:#87CEFA; "></td></tr>
	
	 </table >
 </form>
 
 
 </div>
 
 
   <div class="tup"  >
   <img  src="System/YcB2B/hide.jpg" class="tutu" >
   </div>
   
   
   <div id="two">
   
   <div>
   	<span>天预测协同</span>
   </div>
   
   
      <div style="background-color: #FFFFFF;">
     <img  src="System/YcB2B/huifu.jpg"  class="huifu"  />  
      <img  src="System/YcB2B/daochu.jpg"  class="daochu" />
       <img  src="System/YcB2B/daoru.jpg"  class="daoru" />  
      <font size="2px;"> 只允许导入天的数据 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;     &nbsp; &nbsp; &nbsp;	前三周</font>
       <img  src="System/YcB2B/tian.jpg"  class="tian" />
        <img  src="System/YcB2B/zhou.jpg"  class="zhou" />

         <img  src="System/YcB2B/gant.jpg"  title="前三周为周维度显示时，不能回复，前三周为天维度显示时，才能回复！" />
          <font size="2px;"> 回复预测规则 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;     &nbsp; &nbsp; &nbsp;</font>
          <img title="缺货(Shortage)： 需求数量 >= 0 并且 Gap < 0 
 以周为单位计算  天与所在周保持一致
 --------------------------------------------------------------------------
JIT：Gap = 上周Gap + 供应回复数量 - 需求数量
         计算第一周时，上周Gap = 供应商库存
DUN：Gap = 上周Gap + 供应 - 需求数量
         计算第一周时：上周GAP = 供应商库存
Normal：Gap = 上周Gap + 周交货数量 - 需求数量
         计算第一周时，上周Gap = 0
PO-Consignmen：Gap = 上周Gap + 周交货数量 - 需求数量
         计算第一周时，上周Gap = 预测发布时的供应商VMI库存
VMI-Consignment：Gap = 上周Gap + 供应回复数量 - 需求数量
         计算第一周时，上周Gap = 预测发布时的供应商VMI库存
以上供应商回复数量为空时，按0处理" src="System/YcB2B/que.jpg" />
           <img title="不足(Non-Enough)：Gap < 后面两周的需求数量之和(不包含当周) 
 以周为单位计算   天与所在周保持一致
--------------------------------------------------------------------------
JIT：Gap = 上周Gap + 供应回复数量 - 需求数量
         计算第一周时，上周Gap = 供应商库存
DUN：Gap = 上周Gap + 供应 - 需求数量
         计算第一周时：上周GAP = 供应商库存
Normal：Gap = 上周Gap + 周交货数量 - 需求数量
         计算第一周时，上周Gap = 0
PO-Consignmen：Gap = 上周Gap + 周交货数量 - 需求数量
         计算第一周时，上周Gap = 预测发布时的供应商VMI库存
VMI-Consignment：Gap = 上周Gap + 供应回复数量 - 需求数量
         计算第一周时，上周Gap = 预测发布时的供应商VMI库存
以上供应商回复数量为空时，按0处理" src="System/YcB2B/buzu.jpg" />
            <img title="过高(High-Inv)： Gap > 后面四周的需求数量之和(不包含当周)  
 以周为单位计算  天与所在周保持一致
--------------------------------------------------------------------------
JIT：Gap = 上周Gap + 供应回复数量 - 需求数量
         计算第一周时，上周Gap = 供应商库存
DUN：Gap = 上周Gap + 供应 - 需求数量
         计算第一周时：上周GAP = 供应商库存
Normal：Gap = 上周Gap + 周交货数量 - 需求数量
         计算第一周时，上周Gap = 0
PO-Consignmen：Gap = 上周Gap + 周交货数量 - 需求数量
         计算第一周时，上周Gap = 预测发布时的供应商VMI库存
VMI-Consignment：Gap = 上周Gap + 供应回复数量 - 需求数量
         计算第一周时，上周Gap = 预测发布时的供应商VMI库存
以上供应商回复数量为空时，按0处理" src="System/YcB2B/guogao.jpg" />
          
          <img alt="" src="System/YcB2B/she.jpg"  />
          <img alt="" src="System/YcB2B/zuida.jpg" id="big" style="height: 25px"  align="right"/>
          <script type="text/javascript">
          $("#big").click(function(){
          if($("#shou").is(':hidden')){
        //如果隐藏时。。。
		 $("#shou").show(); 
		 $(".tup").show();
		 	$("#two").css({"width":"77%"});
			$("#two").css({"height":"100%"});
			}else{
			 //如果显示时。。。
			$("#shou").hide();
			$(".tup").hide();
			$("#two").css({"width":"100%"});
			$("#two").css({"height":"100%"});
			}
          });
          </script>
          
      </div>
      <div   style="margin-left: 80px;"><input type="button" value="当前页" class="ing" style="width: 70px;  background-color: #FFFFFF;  border-color:#FFFFFF;display: none;"   /></div>
		<div style="margin-left: 80px;"> <input type="button" value="全部 "   class="all"  style="width: 70px; background-color: #FFFFFF;  border-color:#FFFFFF;display: none;" /></div>
   	<div style="overflow-x:scroll;">
   	<!-- System/YcB2B/qinataoshuju.jsp -->
    <iframe src=""  class="ifam"  id="iframename" scrolling="auto"   frameBorder=0   height="91%" style="overflow-x:scroll;overflow-y:scroll;{FILTER: Chroma(Color = #cc3300)}" width="100%">
	 </iframe>
	 
   </div>
   
   </div>
    </div> 
    
     

   
  
  
 
  
  </body>
</html>
