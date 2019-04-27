<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'tuoti.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body{text-align:center;margin:0px;padding:0px; }
	</style>
  </head>
  
  <body>
    <center><h2>托体打印页面</h2></center>
    <center>  <input type="button"  onclick= "printcode()" value="打印" />&nbsp;&nbsp;
    <a href="javascript:history.back()"><font size="3" color="#333333">返回</font></a>
 
    <center>
     <div id="image" style="margin-left:10px;display:;text-align:center;">   
     <br> 	
    		<%
    			String DCI=request.getParameter("OrderNumber");
    			String Customer=request.getParameter("PartNumber");
    			String Xquantity=request.getParameter("TQuantity");
    			String Xdate=request.getParameter("Tdate");
    			String Xpaiban=request.getParameter("Tpaiban");
    			System.out.println("排版"+Xpaiban);
    			int Xcount=Integer.parseInt(request.getParameter("Tcount"));
    			int hangshu=2;    			
    			
    			if("heng".equals(Xpaiban)){//横向排版    排6个	
    			   if(Xcount%2==1){
    				hangshu=Xcount/2+1;
    			   }else{
    				hangshu=Xcount/2;
    			   }
    			   if(hangshu>8){
    			   	hangshu=4;
    			   }		
    			 %>
    			 <table style="border:solid #999;border-width:2px 2px 2px 2px;padding:0px;">
    			 <tr style="border:solid #999;border-width:1px 1px 1px 1px;padding:0px;">
    			  <%
    			 	for(int i=0;i<hangshu;i++){
    			 		%>
    			 		<td style="border:solid #999;border-width:1px 1px 1px 1px;padding:0px;">
    			    	<table>
    			    	<tr><td align="left">
    			    	
    			    	<hr>
    			    	<table width="362px" style="border:1;font-size:18px;text-align:left">
	    				<tr><td>
	    				&nbsp;&nbsp;&nbsp;<b>Donaldson P/N. . .<%=DCI %></b><br>
	    				
	    				<img src="<%=request.getContextPath()%>/barcode.action?msg=<%=DCI %>&type=code39" height="55px" width="320px"/>
	    	  			 
	    	  			</td></tr>
	    	  			<tr><td>
	    	  			&nbsp;&nbsp;<b>Part Number. . . . .<%=Customer %></b><br>
	    				<img src="<%=request.getContextPath()%>/barcode.action?msg=<%=Customer %>&type=code39" height="55px" width=230px/>
	    	  			
	    	  			</td></tr>
	    	  			<tr><td>
	    	  			&nbsp;&nbsp;&nbsp;<b>Quantity. . . . . . . . . . . . . . . .<%=Xquantity %></b><br>
	    				<img src="<%=request.getContextPath()%>/barcode.action?msg=<%=Xquantity %>&type=code39" height="55px" width=110px/>
	    	  			
	    	  			</td></tr>
	    	  			<tr><td>
	    	  			&nbsp;&nbsp;&nbsp;<b>Mfy.Date. . . . . .<%=Xdate %></b><br>
	    				
	    	  			</td></tr>
	    				</table> 
    			    	</td></tr>    			    	
    			    	<tr><td align="left">
    			    	
    			    	<br><br><hr><br>
    			    	<table width="362px" style="border:1;font-size:18px;text-align:left">
	    				<tr><td>
	    				&nbsp;&nbsp;&nbsp;<b>Donaldson P/N. . .<%=DCI %></b><br>
	    				<img src="<%=request.getContextPath()%>/barcode.action?msg=<%=DCI %>&type=code39" height="55px" width="320px"/>
	    	  			
	    	  			</td></tr>
	    	  			<tr><td>
	    	  			&nbsp;&nbsp;&nbsp;<b>Part Number. . . . .<%=Customer %></b><br>
	    				<img src="<%=request.getContextPath()%>/barcode.action?msg=<%=Customer %>&type=code39" height="55px" width=230px/>
	    	  			
	    	  			</td></tr>
	    	  			<tr><td>
	    	  			&nbsp;&nbsp;&nbsp;<b>Quantity. . . . . . . . . . . . . . . .<%=Xquantity %></b><br>
	    				<img src="<%=request.getContextPath()%>/barcode.action?msg=<%=Xquantity %>&type=code39" height="55px" width=110px/>
	    	  			
	    	  			</td></tr>
	    	  			<tr><td>
	    	  			&nbsp;&nbsp;&nbsp;<b>Mfy.Date. . . . . .<%=Xdate %></b><br>
	    				
	    	  			</td></tr>
	    				</table> 
	    				<br>
    			    	</td></tr>
    			    	</table>    			    	
	    	  			</td>
    			 		<%
    			 	}
    			  %>
    			 </tr></table>
    			 <%
    			}else if("zong".equals(Xpaiban)){//纵向排版    		排4个	
    			 if(Xcount%2==1){
    				 hangshu=Xcount/2+1;
    			   }else{
    			   	 hangshu=Xcount/2;
    			   }
    			   if(hangshu>2){
    			   	hangshu=2;
    			   }
    			  for(int i=0;i<hangshu;i++){
    			 %>
    			 <br><br><br><br>
    			<table style="border:solid #999;border-width:2px 2px 2px 2px;padding:10px;">
    			 <tr style="border:solid #999;border-width:2px 1px 2px 2px;padding:4px;">
    			   <td style="border:solid #999;text-align:left;border-width:1px 1px 1px 1px;padding:4px;">
    			    	 <br>
    			    	
    			    	<hr>
    			    	<table width="341px" style="font-size:13px;text-align:left">
	    				<tr><td>
	    				&nbsp;&nbsp;&nbsp;<b>Donaldson P/N.. .<%=DCI %></b><br>
	    				<img src="<%=request.getContextPath()%>/barcode.action?msg=<%=DCI %>&type=code39" height="55px" width="320px"/>
	    	  			
	    	  			</td></tr>
	    	  			<tr><td>
	    	  			&nbsp;&nbsp;&nbsp;<b>Part Number. . . . .<%=Customer %></b><br>
	    				<img src="<%=request.getContextPath()%>/barcode.action?msg=<%=Customer %>&type=code39" height="55px" width=230px/>
	    	  			
	    	  			</td></tr>
	    	  			<tr><td>
	    	  			&nbsp;&nbsp;&nbsp;<b>Quantity. . . . . . . . . . . . . . . .<%=Xquantity %></b><br>
	    				<img src="<%=request.getContextPath()%>/barcode.action?msg=<%=Xquantity %>&type=code39" height="55px" width=110px/>
	    	  			
	    	  			</td></tr>
	    	  			<tr><td>
	    	  			&nbsp;&nbsp;&nbsp;<b>Mfy.Date. . . . . .<%=Xdate %></b><br>
	    				
	    	  			</td></tr>
	    				</table> 
	    				<br>
    			   </td>
    			   <td style="border:solid #999;text-align:left;border-width:1px 1px 1px 1px;padding:4px;">
    			    	 <br>
    			    	
    			    	<hr>
    			    	 <table width="341px" style="font-size:13px;text-align:left">
	    				<tr><td>
	    				&nbsp;&nbsp;&nbsp;<b>Donaldson P/N. . .<%=DCI %></b><br>
	    				<img src="<%=request.getContextPath()%>/barcode.action?msg=<%=DCI %>&type=code39" height="55px" width="320px"/><br>
	    	  			
	    	  			</td></tr>
	    	  			<tr><td>
	    	  			&nbsp;&nbsp;&nbsp;<b>Part Number. . . . .<%=Customer %></b><br>1818M060S01-248-202M060S01-248-202
	    				<img src="<%=request.getContextPath()%>/barcode.action?msg=<%=Customer %>&type=code39" height="55px" width=230px/>
	    	  			
	    	  			</td></tr>
	    	  			<tr><td>
	    	  			&nbsp;&nbsp;&nbsp;<b>Quantity. . . . . . . . . . . . . . . .<%=Xquantity %></b><br>
	    				<img src="<%=request.getContextPath()%>/barcode.action?msg=<%=Xquantity %>&type=code39" height="55px" width=110px/>
	    	  			
	    	  			</td></tr>
	    	  			<tr><td>
	    	  			&nbsp;&nbsp;&nbsp;<b>Mfy.Date. . . . . .<%=Xdate %></b><br>
	    				<!-- 
	    				<img src="<%=request.getContextPath()%>/barcode.action?msg=<%=Xdate %>&type=code39" height="55px" width="320px"/>
	    	  			  -->
	    	  			 
	    	  			</td></tr>
	    				</table> 
	    				<br>
    			   </td>
    			</tr> </table>
    			 <%
    			 }
    			 
    			 }else{//条码打印
    			 %>	
    			    	<table width="320px" style="font-size:17px;text-align:left">
	    				<tr><td>
	    				&nbsp;&nbsp;&nbsp;<b>Donaldson P/N.. .<%=DCI %></b>
	    				<img src="<%=request.getContextPath()%>/barcode.action?msg=<%=DCI %>&type=code39" height="50px" width="360px"/>
	    	  			
	    	  			</td></tr>
	    	  			<tr><td>
	    	  			&nbsp;&nbsp;&nbsp;<b>Part Number. . . . .<%=Customer %></b>
	    				<img src="<%=request.getContextPath()%>/barcode.action?msg=<%=Customer %>&type=code39" height="50px" width=220px/>
	    	  			
	    	  			</td></tr>
	    	  			<tr><td>
	    	  			&nbsp;&nbsp;&nbsp;<b>Quantity. . . . . . . . . . . . . . . .<%=Xquantity %></b><br>
	    				<img src="<%=request.getContextPath()%>/barcode.action?msg=<%=Xquantity %>&type=code39" height="50px" width=100px/>
	    	  			
	    	  			</td></tr>
	    	  			<tr><td>
	    	  			&nbsp;&nbsp;&nbsp;<b>Mfy.Date. . . . . .<%=Xdate %></b>	    				
	    	  			</td></tr>
	    				</table> 
    			 <%
    			 
    			}
    		 %>
    		    		
    </div>
  </body>
   <script language="javascript">
  
	 function	printcode(){
	 
	 //alert("===========");
	 var printBody = document.getElementById("image");
	 var printBodyHTML="";
	 var bodyHTML="";
	 if(printBodyHTML==""){
	  printBodyHTML = printBody.innerHTML;
	 }
	 if(bodyHTML==""){
	  bodyHTML = document.body.innerHTML;
	 }
	 document.body.innerHTML = printBodyHTML;
	 window.print();
	  //window.location.href="queryAllGoods.action";
	 document.body.innerHTML = bodyHTML; 
	
	 }
	</script>
</html>