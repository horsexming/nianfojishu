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
    
    <title>My JSP 'label.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="/util/sonHead.jsp"%>
	
	<script type="text/javascript" src="System/YcB2B/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="System/YcB2B/songhuo/weihu.js"></script>
	<style type="text/css">
	
	.box{
    width:80%; margin-top:10%; margin:auto; padding:28px;
    background-color:#FFFFFF;
    height:400px; border:1px #111 solid;
    display:none;            /* 默认对话框隐藏 */
}
 
.box .x{ font-size:18px; text-align:right; display:block;}
/* .box input{width:80%; font-size:18px; margin-top:18px;} */
	</style>
  </head>
  
  <body>
      <%@include file="/util/sonTop.jsp"%>
           
  <div>查询条件∧</div>
  <div>
  
  <form action="javaScrippt:void(0)" method="post">
  <div style="float: left; margin-left: 30px;">
   <p>物料编码</p>
  <input type="text" placeholder="多个物料编码以','分隔"/>
  </div>
  
   <div style="float: left; margin-left: 30px;">
  <p>箱号</p>
  <input type="text"  />
  </div>
  
  <div style="float: left; margin-left: 30px;">
   <p>ASN单号</p>
  <input type="text" placeholder="多个单号以','分隔"/>
  </div>
  
  <div style="float: left; margin-left: 30px;">
    <p>PO号</p>
  <input type="text"  placeholder="多个PO号以','分隔"/>
  </div>
  
  
    <div style="float: left; margin-left: 30px;">
    <p>信息是否完整</p>
   <select style="width:170px">
  <option value="ALL">ALL</option>
  <option value="N">N</option>
  <option value="N">N</option>>
  </div>
  
    <div  margin-left: 30px;">
    <p>制造批次</p>
  <input type="text" />
  </div>
  
  
  <div style="float: left; margin-left: 30px;">
      <p>创建时间</p>
  <input type="text"   class="Wdate form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
 </div>
 
 
 <div style="float: left; margin-left: 30px;"> 
   <p>创建人</p>     
<input type="text" /> 
  </div>
    </div>
    <input type="submit"  class="submit"  value="查询" style="border-color:#40A9FF;background-color:#40A9FF;color:#FFFFFF;"/> 
    <input type="reset" value="重置"   class="reset" style="margin-top: 10px;margin-left: 94%;border-color:#40A9FF;background-color:#FFFFFF;color: #40A9FF;  " />
  </form>
   

    <script>  
        function msgbox(n){
            document.getElementById('inputbox').style.display=n?'block':'none';     /* 点击按钮打开/关闭 对话框 */
        }
     </script>  

   
   
   
   
   
   
   <div style="position:absolute;">
   <input type="button" value="批量维护DC"   onClick="msgbox(1)" style="background-color: #FDFDFD;border-color:#F1F1F1;color: #1890FF;float: left;" class="weihuDC" />
   <input type="button" value="批量维护明码SN" style="background-color: #FDFDFD;border-color:#F1F1F1;color: #1890FF;float: left;" />
   <input type="button" value="打印" style="background-color: #FDFDFD;border-color:#F1F1F1;color: #1890FF;float: left;" /> 
   <input type="button" value="批量复制" style="background-color: #FDFDFD;border-color:#F1F1F1;color: #1890FF;float: left;" />
   <input type="button" value="批量维护DC" style="background-color: #FDFDFD;border-color:#F1F1F1;color: #1890FF;float: left;" />
	<select style="border-color:#F1F1F1;float: left;" id="selectDropDown" >
	<option value="0" style="color: #1890FF;background-color: #FDFDFD;">导入导出(EXCEL维护标签)∨</option>
	<option  value="1" style="color: #1A1A1A;background-color:#F2F2F2">模块下载</option>
	<option  value="2" style="color: #1A1A1A;background-color:#F2F2F2">导入创建</option>
	<option  value="3" style="color: #1A1A1A;background-color:#F2F2F2" >导入更新</option>
	<option  value="4" style="color: #1A1A1A;background-color:#F2F2F2">导出</option>
	</select  >
   <input type="button" value="删除" style="background-color: #FDFDFD;border-color:#F1F1F1;color: #1890FF;" />
   
   <table border="1" cellspacing="0"  rules="all" bordercolor="#DEDEDE" id="bq">
   <!-- 头 -->
   <tr style="background-color: #E8E8E8;height:30px; ">
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 40px;" value="序号"   readonly="readonly"/></td>
      <td align="left"  style="background-color: #E8E8E8;"><input type="checkbox" style="border: none;background-color: #E8E8E8;width: 40px;"  class="checkall" /></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="操作"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="箱号"  readonly="readonly" /></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="物料编码"  readonly="readonly" /></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="物料版本"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="物料单位"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="追溯类型"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="信息是否完整"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="ASN单号"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="PO号"  readonly="readonly" /></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="件套数SN/TN"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="内装数量"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="是否尾箱"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="制造日期"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="制造批次"   readonly="readonly"/></td>
   <td align="left"  style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="绑定内标签"  readonly="readonly" /></td>
   <td align="left"  style="background-color: #E8E8E8;"> <input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="打印次数"   readonly="readonly"/></td>
    <td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="创建人"   readonly="readonly"/></td>
    <td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="创建时间"   readonly="readonly"/></td>
    <td align="left" style="background-color: #E8E8E8;"><input type="text" style="border: none;background-color: #E8E8E8;width: 120px;" value="最后修改时间"   readonly="readonly"/></td>
   </tr>
   
   <s:iterator id="list" value="labelist" status="stauts">
   <tr>
   <td style="background-color:#F7F7F7;"><s:property value="#stauts.index+1" /></td>
   <td style="background-color:#F7F7F7;"><input type="checkbox" style="border: none;background-color: #F7F7F7;width: 40px;"  value="张三" /></td>
    <td style="background-color:#F7F7F7;"><input type="text" value="写 复制标签" style="border: none;background-color: #F7F7F7;width: 40px;"  /></td>
     <td style="background-color:#F7F7F7;"><input type="text" value="${list.vendorCode}" style="border: none;color:#5990FF;  background-color: #F7F7F7; width: 150px;" readonly="readonly"  /></td>
      <td style="background-color:#F7F7F7;"><input type="text" value="${list.vendorName}" style="border: none;  background-color: #F7F7F7; width: 150px;"   readonly="readonly"/></td>
      <td style="background-color:#F7F7F7;"><input type="text" value="${list.colTaskOrPoStatus}" style="border: none;  background-color: #F7F7F7; width: 150px;" readonly="readonly" /></td>
      <td style="background-color:#F7F7F7;"><input type="text" value="" style="border: none;  background-color: #F7F7F7; width: 150px;"  readonly="readonly" /></td>     
      <td style="background-color:#F7F7F7;"><input type="text" value="${list.shipmentStatus}" style="border: none;  background-color: #F7F7F7; width: 150px;" readonly="readonly"  /></td>     
      <td style="background-color:#F7F7F7;"><input type="text" value="Y" style="border: none;  background-color: #F7F7F7; width: 150px;"  readonly="readonly" /></select></td>
      <td style="background-color:#F7F7F7;"><input type="text" value="${list.poSubType}" style="border: none;  background-color: #F7F7F7; width: 150px;" readonly="readonly"  /></td>
      <td style="background-color:#F7F7F7;"><input type="text" value="${list.poNumber}" style="border: none;  background-color: #F7F7F7; width: 150px;"  readonly="readonly" /></td>      
      <td style="background-color:#F7F7F7;"><input type="text" value="" style="border: none;  background-color: #F7F7F7; width: 150px;"  readonly="readonly" /></td>
      <td style="background-color:#F7F7F7;"><input type="text" value="${list.poHeaderId}" style="border: none;  background-color: #F7F7F7; width: 150px;" readonly="readonly"  /></td>
      <td style="background-color:#F7F7F7;"><input type="text" value="N" style="border: none;  background-color: #F7F7F7; width: 150px;" readonly="readonly"  /></td>    
      <td style="background-color:#F7F7F7;"><input type="text" value="${list.poReleaseId}" style="border: none;  background-color: #F7F7F7; width: 150px;"  readonly="readonly" /></td>
      <td style="background-color:#F7F7F7;"><input type="text" value="${list.publishDate}" style="border: none;  background-color: #F7F7F7; width: 150px;" readonly="readonly"  /></td>
      <td style="background-color:#F7F7F7;"><input type="text" value="N" style="border: none;  background-color: #F7F7F7; width: 150px;"  readonly="readonly" /></td>     
      <td style="background-color:#F7F7F7;"><input type="text" value="${list.businessMode}" style="border: none;  background-color: #F7F7F7; width: 150px;"  readonly="readonly" /></td>
      <td style="background-color:#F7F7F7;"><input type="text" value="YTDZ" style="border: none;  background-color: #F7F7F7; width: 150px;" readonly="readonly"  /></td>
      <td style="background-color:#F7F7F7;"><input type="text" value="${list.currencyCode}" style="border: none;  background-color: #F7F7F7; width: 150px;"  readonly="readonly" /></td>
      <td style="background-color:#F7F7F7;"><input type="text" value="${list.shipmentNum}" style="border: none;  background-color: #F7F7F7; width: 150px;"  readonly="readonly" /></td>     
   </tr>
   
   </s:iterator>
   
      	<tr>
						<td colspan="12" align="right">
							第
							<font color="red"><s:property value="cpage"   /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />
						</td>
					</tr>
   
   </table>
   </div>
      <div id='inputbox' class="box" style="position:relative;">
         <p>基本信息</p>
        <a class='x' href=''; onclick="msgbox(0); return false;">关闭</a>
        <p style="float: left;>物料编码</p>
        <input type="text" readonly="readonly"/>
        <p>物料版本</p>
        <input type="text" readonly="readonly">
        <p>追溯类型</p>
        <input type="text" readonly="readonly">
        <p>内装数量</p>
         <input type="text" readonly="readonly">
         
         <p>DC信息</p>
         <input type="button" value="批量删除" style="color: #1890FF;">
        <p style="color: red;">*</p><p>制造日期</p>
        <input type="text"   class="Wdate form-control" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
       	<p>制造批次</p>
         <input type="text" />
         <p>数量</p>
         <input type="text" />
         <input type="button"  value="新增一行" style="color: #1890FF"/>
         
         <table>
         <tr>
         <td><input type="" /> </td>
         <td><p style="color: red;">*</p>制造日期</td>
         <td><p style="color: red;">*</p>制造批次</td>
         <td><p style="color: red;">*</p>数量</td>
         </tr>
         <tr>
         <td><input type="checkbox"  /> </td>
         <td><input type="text"  value=""/> </td>
           <td><input type="text"  value=""/> </td>
             <td><input type="text"  value=""/> </td>
         </tr>
         
         <tr><td colspan="4">备注<input type="text"/> </td></tr>
         
         
         </table>
         
        <input type="submit" value="确定">
        <input type="reset" value="确定">
     </div>
   
   
    <%@include file="/util/foot.jsp"%>
    <script type="text/javascript">
     window.onload = function () {
     
	        document.getElementById('selectDropDown').addEventListener('change',function(){
	           //下拉列表的点击事件
	           var comname=this.value;
	           if(comname==4){
	                var shuzu=new Array();
	        	   for (var int = 1; int <= 60; int++) {
	        			  var check=  $("#bq tr:eq("+int+") td:eq(1)").find("input");
	   
	        			  if(check.is(":checked")){
	        				  //行数id
	        				  var number= $(check).parent().prev().text();
	        				 //箱号
	        				  var xh=	$(check).parent().next().next().find("input").val();
	        				  //物料编码
	        				  var itemcode=$(check).parent().next().next().next().find("input").val();
	        				  //物料版本
	        				  var banebn=$(check).parent().next().next().next().next().find("input").val();
	        				  if(banebn==null ||banebn=="" ){
	        				  banebn="";
	        				  }
	        				   //物料单位
	        				  var dw=$(check).parent().next().next().next().next().next().find("input").val();
	        				  if(dw==null ||dw==""){
	        				  dw="";
	        				  }
	        				  //追溯类型
	        				   var zslx=$(check).parent().next().next().next().next().next().next().find("input").val();
	        				   	//信息是否完整
	        				   var xxwz=$(check).parent().next().next().next().next().next().next().next().find("input").val();
	        				  	//ASN单号
	        				   var asndh=$(check).parent().next().next().next().next().next().next().next().next().find("input").val();
	        				   //po号
	        				   var poh=$(check).parent().next().next().next().next().next().next().next().next().next().find("input").val();
	        				   //件套数SN/TN
	        				   var taoshu=$(check).parent().next().next().next().next().next().next().next().next().next().next().find("input").val();
	        				   if(taoshu==null ||taoshu=="" ){
	        				   taoshu="";
	        				   }
	        				   	//内装数量
	        				   var nzsl=$(check).parent().next().next().next().next().next().next().next().next().next().next().next().find("input").val();
	        				   	//是否尾箱
	        				   var sfwx=$(check).parent().next().next().next().next().next().next().next().next().next().next().next().next().find("input").val();
	        				   //制造日期
	        				    var zzrq=$(check).parent().next().next().next().next().next().next().next().next().next().next().next().next().next().find("input").val();
	        				    //制造批次
	        				     var zzpc=$(check).parent().next().next().next().next().next().next().next().next().next().next().next().next().next().next().find("input").val();
	        				     //绑定内标签
	        				     var bdnbq=$(check).parent().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().find("input").val();
	        				     //打印次数
	        				     var dycs=$(check).parent().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().find("input").val();
	        				     //创建人
	        				     var cjr=$(check).parent().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().find("input").val();
	        				    //创建时间
	        				     var cjsj=$(check).parent().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().find("input").val();
	        				    //最后修改时间
	        				     var zhxgsj=$(check).parent().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().next().find("input").val();
	        				   
	        				   var jia="["+number+","+xh+","+itemcode+","+banebn+","+dw+","+zslx+","+xxwz+","+asndh+","+poh+","+taoshu+","+nzsl+","+sfwx+","+zzrq+","+zzpc+","+bdnbq+","+dycs+","+cjr+","+cjsj+","+zhxgsj+"]";
	        				   shuzu.push(jia);
	        				   
	        			  	}

	        			 } 
	        			 
	        			 window.location.href="HttpRestClientForaction!bqExcel.action?excellist="+shuzu;
	           }

	        });
	        
	    }
    </script>
    
  </body>
</html>
