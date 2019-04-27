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
		<base href="<%=basePath%>">

		<title>登陆选择界面</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	    <link rel="stylesheet" type="text/css" href="styles.css">
	    -->
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="Cache-Control" content="no-cache"/>
        <meta name="viewport" content="width=device-width, minimum-scale=0.5, maximum-scale=1.0"/>
        <script type="text/javascript" src="<%=basePath%>/javascript/jquery-1.8.3.js"></script>
        <script type="text/javascript" src="<%=basePath%>/javascript/jquery-1.11.3.min.js"></script>
</script>
	</head>
  <style type="text/css">
  body{
  zoom:78%;
  }
  body{margin:0;padding:0;list-style-type:none;font-family: 楷体;}
  .ycxztb{ 
   text-align:center;
   border-color: #9370DB;
   }
   img{
    border-radius: 50%;
  	z-index:1000;
	opacity: 1;
	border: 1px solid rgba(255, 255, 255, 0.9);
   }
   #lq,#tj,#pf,#ckpf,#ckrwjd,#pfrk{
    border: 2px solid #dedede;
    -moz-border-radius: 15px;  
    -webkit-border-radius: 15px;  
    border-radius:15px; 
    background-color:#71a1ff;  
    border-color:#7aa7ff #7aa7ff #7aa7ff;   
   }
   #lq:hover,#tj:hover,#pf:hover,#ckpf:hover,#ckrwjd:hover,#pfrk:hover{
    border: 2px solid #dedede;
    -moz-border-radius: 15px;  
    -webkit-border-radius: 15px;  
    border-radius:15px; 
    background-color:#669aff;
    border-color:#6297ff #5c93ff #5c93ff;
   }
   
   #lq:active,#tj:active,#pf:active,#ckpf:active,#ckrwjd:active,#pfrk:active{
    border: 2px solid #dedede;
    -moz-border-radius: 15px;  
    -webkit-border-radius: 15px;  
    border-radius:15px; 
    background-color:#6096ff;
    border-color:#5b93ff #5690ff #5690ff; 
   }
  #czsc{
  text-align: right;
  }
</style>
  <body style="background-color:#97BCE4;">
       <!-- 隐藏登陆人员 -->
       <input type="hidden" value="${operation}" id="opera"/>
       <input type="hidden" value="${Users.name}" id="empName3"/>
       <div style="width:80px;text-align: center;float: left;">
            <s:if test="Users.sex==null ||Users.sex=='男'.toString()">
				<img style="width:40px;height:40px;" src="<%=basePath%>/upload/user/${Users.password.picture}" alt="${Users.name }" class="userImg"
						onerror="this.src='images/man.jpg'">
			</s:if>
			<s:else>
				<img style="width:40px;height:40px;" src="<%=basePath%>/upload/user/${Users.password.picture}" alt="${Users.name }" class="userImg"
						onerror="this.src='images/woman.jpg'">
			</s:else>
			<br/><span>${Users.name}</span>
       </div>
       <div style="height:80px;text-align: center; float:right;">
           <table height=100% style="text-align: center;">
               <tr>
                   <th><a id="czsc" href="javascript:void(0)">操作手册</a></th>
               </tr>
           </table>  
       </div>
       <div id="ycczsc" style="margin-left:100px;text-align:center; width:300px;height:500px;position: absolute;background-color:#f8f8ff;border:3px solid;border-color:#7aa7ff;display:none;">
           <p style="color: #6096ff;font-size: 19px;">操作说明</p>
           <p style="height:80px; margin:0px; color: #97BCE4;">一&nbsp;登陆后如未进行评分,请先评分<br/>再进行领取</p>
           <p style="height:80px;margin:0px; color: #97BCE4;">二&nbsp;领取成功后,请认真打扫各地卫<br/>生,检查完后进行提交</p>
           <p style="height:100px;margin:0px;color: #97BCE4;">三&nbsp;在进行提交前,请检查桌位上下<br/>是否存在垃圾,垃圾桶是否倒掉<br/>门,灯,空调,是否关闭</p>
           <p style="height:80px;margin:0px; color: #97BCE4;">四&nbsp;当天打扫完各地后,请不要忘记<br/>提交,如未提交则零分</p>
           <p height=47% ></p>
       </div>
      <p id="qdl" style="color:#FF4500;"></p>   
      <!-- 隐藏查看任务领取提交评分进度的div -->  
      <div id="ycckrwjd" style="width:500px;height:310px;position: absolute;top:200px;background-color:#88b0ff;border:3px solid;border-color:#7aa7ff;display:none;">
          <table border="1" width=99% height=99% class="ycxztb">
              <tr>
                  <th colspan="3">任务进度</th>
              </tr>
              <tr>
                  <th colspan="3">
                                                         查找日期: <input id="rwjddt" type="date" name="bday" style="height:25px;width:100px;">
					 <input id="rwjdcz" style="margin-left:30px;width:70px;height:25px;background-color:#DDA0DD;" type="submit" value="查找" />
                  </th>
              </tr>
              <tr style="color:#BA55D3;">              
                  <th width=33%>领取人</th>                  
                  <th width=33%>提交人</th>
                  <th width=33%>评分人</th>
              </tr>   
              <s:if test="cleanrecord==null">
                   <tr style="color:#BA55D3;">
	                  <th>无</th>
	                  <th>无</th>
	                  <th>无</th>
	              </tr>
              </s:if> 
              <s:else>        
                  <tr style="color:#BA55D3;">
                      <s:if test="cleanrecord.receive==null||cleanrecord.receive==''">
	                      <th>暂无领取</th>
	                  </s:if> 
	                  <s:else>
		                  <th>${cleanrecord.receive}</th>
	                  </s:else>
	                  <s:if test="cleanrecord.submitter==null||cleanrecord.submitter==''">
	                      <th>暂无提交</th>
	                  </s:if> 
	                  <s:else>
		                  <th>${cleanrecord.submitter}</th>
	                  </s:else>
	                  
	                  <s:if test="cleanrecord.evaluator==null||cleanrecord.evaluator==''">
	                      <th>暂无评分</th>
	                  </s:if> 
	                  <s:else>
		                  <th>${cleanrecord.evaluator}</th>
	                  </s:else>
	                  
	              </tr>
              </s:else> 
              <tr>                 
                  <th colspan="3">                 
                      <input type="button" value="关闭" id="ckrwjdgb" style="width:105px; height:40px; background-color:#DDA0DD; "/>                       
                  </th>                
              </tr>
          </table> 
      </div>
      
      <!-- 隐藏查看评分记录的div -->    
      <div id="ycckpfdiv" style="width:500px;height:310px;position: absolute;top:200px;background-color:#88b0ff;border:3px solid;border-color:#7aa7ff;display:none;">     
          <table border="3" width=99% height=99% class="ycxztb">
              <tr>
                  <th colspan="3">查看评分</th>
              </tr>
              <tr>
                  <th colspan="3">
                                                         查找日期: <input id="pfdt" type="date" name="bday" style="height:25px;width:100px;">
					 <input id="pfrqcz" style="margin-left:30px;width:70px;height:25px;background-color:#DDA0DD;" type="submit" value="查找" />
                  </th>
              </tr>
              <tr style="color:#BA55D3;">              
                  <th width=33%>标题</th>                  
                  <th width=33%>时间</th>
                  <th width=33%>分数</th>
              </tr>
              <s:if test="scoreAllList==null||scoreAllList.size()==0">
                   <tr style="color:#BA55D3;">
	                  <th>无</th>
	                  <th>无</th>
	                  <th>无</th>
	              </tr>
              </s:if>
              <s:iterator id="scolist" value="scoreAllList" status="stauts">                
                  <tr style="color:#BA55D3;">
	                  <th>${scolist.titleName}</th>
	                  <th>${scolist.clearTheDay}</th>
	                  <th>${scolist.fraction}</th>
	              </tr>
              </s:iterator>
              
              <tr>                 
                  <th colspan="3">                 
                      <input type="button" value="关闭" id="ckpfgb" style="width:105px; height:40px; background-color:#DDA0DD; "/>                       
                  </th>                
              </tr>
          </table>             
      </div>
      <!-- 整体DIV 内容包括 领取   提交   评分 -->
      <input type="hidden" value="${titleId}" id="tId" />
         <div id="ycczdiv" style="display:block;font-family:楷体;">
           <table border="0" style="height:750px;width:500px;">
               <tr height=16%>
                   <th><input type="hidden" value="${operation}"/><input id="lq" style=" font-size:21px; width:240px;height:50px;" type="button" value="领取值日" /></th>
               </tr>
               <tr height=16%>
                   <th><input type="hidden" value="${operation}"/><input id="tj" style="font-size:21px; width:240px;height:50px;" type="button" value="提交值日" /></th>
               </tr>
               <tr height=16%>
                   <th><input type="hidden" value="${operation}"/><input id="pf" style="font-size:20px; width:240px;height:50px;" type="button" value="值日评分" /></th>
               </tr>

               <tr height=16%>
                   <th><input type="hidden" value="${operation}"/><input id="ckpf" style="font-size:20px; width:240px;height:48px;" type="button" value="查看评分记录" /></th>
               </tr>
                <tr height=16%>
                   <th><input type="hidden" value="${operation}"/><input id="ckrwjd" style="font-size:20px; width:240px;height:48px;" type="button" value="查看任务进度" /></th>
               </tr>
                <tr height=16%>
                   <th><input type="hidden" value="${operation}"/><input id="pfrk" style="font-size:20px; width:240px;height:48px;" type="button" value="评分直达入口" /></th>
               </tr>
           </table>
         <!-- <table border="0" style="height:250px;width:500px;">
              
          </table> --> 
      </div>
      
  <script type="text/javascript">
         if($('#yctpdiv').is(':hidden')){
        	 
         }else{
        	 $("#yctpdiv").show();
         }
  
         var operation2;
         var operation3 = $("#opera").val();
         if(operation3=="系统操作"){
        	 location.href="ClearInfoAction_userOperation.action";
         }
                   
         //定义一个可操作状态,根据状态判断应该选择领取还是提交还是评分	  	  	     
	     var empName =  document.getElementById("qdl").innerHTML;
	     var empName3 = $("#empName3").val();
	     var ycdldiv = document.getElementById('ycdldiv');        
	     var ycczdiv = document.getElementById('ycczdiv');       
     
         //function hello(){ 
         //location.href="userOperation?employeeName="+empName2+"&titleName="+titleName;
         //} 
         //重复执行某个方法 
         //var t1 = window.setInterval(hello,1000); 
         //window.setInterval("hello()",10000);
                         
     
     
        //隐藏查看关闭
		$("#ckpfgb").click(function(){
			var div = document.getElementById('ycckpfdiv');
			div.style.display = 'none';  // div隐藏
		})
		$("#ckrwjdgb").click(function(){
	        var div = document.getElementById('ycckrwjd');
			div.style.display = 'none';
            
	    })
        //登陆按钮点击事件
		/*$("#zrdlan").click(function(){
			//获取数据
			var employeeName = $("[name='employeeName']").val();
			var tId = $("#ycczdiv").prev().val();						
			if(employeeName == null || employeeName == ""){
				alert("角色不能为空!");
			}else{			
			     $.ajax({
					url     : "ClearInfoAction_loginEmployee.action", //访问路径
					type    : "post",                      //提交方式
					data    : "employee.employeeName="+employeeName,//传递到后台的参数
					dataType: "json",                      //后台返回结果的类型
					success : function(data){//成功时执行的代码						
						alert("登陆成功!!!");							 
					    location.href="ClearInfoAction_userOperation.action?employee.employeeName="+data.employeeName+"&cleanrecord.homeTitle.id="+tId;							
					}						
		        });			   											
			}    
		});*/
           
		
	    $("#lq").click(function(){
	    var tId = $("#ycczdiv").prev().val();
	        operation2 = $(this).prev().val();
	        if(operation2=="领取"){	                
	         alert("符合领取要求");
	           $.ajax({
					"url"     : "ClearInfoAction_receiveOperation.action", //访问路径
					"type"    : "post",                      //提交方式
					"data"    : "",//传递到后台的参数
					"dataType": "json",                      //后台返回结果的类型
					"success" : function(data){//成功时执行的代码
						if(data==1){
							alert("领取成功!!!");
							var empName4 = encodeURI(empName3); 
							window.location.href="ClearInfoAction_userOperation.action?employee.employeeName="+empName4+"&titleId=${titleId}";
						}else{
							alert("您晚了一步!!!");
							window.location.href="ClearInfoAction_userOperation.action?employee.employeeName="+empName4+"&titleId=${titleId}";
			            }
					}						
		        });  
	        }else if(operation2=="待评分"){
	            alert("待评分!!!")
	        }else{
	            alert("暂时无领取状态!!!");
	        }
	    })  
	    $("#tj").click(function(){
	        var titleName = $("#ycczdiv").prev().val();
	        operation2 = $(this).prev().val();
	        if(operation2=="提交"){
	            alert("符合提交要求");
	             $.ajax({
					"url"     : "ClearInfoAction_submitterOperation.action", //访问路径
					"type"    : "post",                      //提交方式
					"data"    : "",//传递到后台的参数
					"dataType": "json",                      //后台返回结果的类型
					"success" : function(data){//成功时执行的代码
						if(data==1){
							alert("提交成功!!!");
							location.href="ClearInfoAction_userOperation.action?titleId=${titleId}";
						}else{
							alert("您晚了一步!!!");
			            }
					}						
		        });	
	        }else if(operation2=="待评分"){
	            alert("待评分!!!")
	        }else{
	            alert("暂时无提交状态!!!");
	        }
	    }) 
	     $("#pf").click(function(){
	     var titleName = $("#ycczdiv").prev().val();
	        operation2 = $(this).prev().val();
	        if(operation2=="评分"){
	            alert("符合评分要求");
	            location.href="ClearInfoAction_pingfen.action";
	        }else if(operation2=="待评分"){
	            alert("待评分!!!")
	        }else{
	            alert("暂时无评分状态!!!");
	        }
	    }) 
	    //查看评分记录
	    $("#ckpf").click(function(){
	    	$("#ycckpfdiv").show();

	    })
	    //显示任务进度
	    $("#ckrwjd").click(function(){
	    	$("#ycckrwjd").show();
	    })
	    //显示操作手册
	    $("#czsc").click(function(){	
	    	if($('#ycczsc').is(':hidden')){
	    		$("#ycczsc").show();
	    	}else{
	    		$("#ycczsc").hide();
	    	}
	    	
	    })

	    $("#pfrqcz").click(function(){
	    	var pfdt = $("#pfdt").val();
	    	alert(pfdt);
	    	location.href="ClearInfoAction_userOperation.action?scoreDate="+pfdt;
	    })
	    
	    $("#rwjdcz").click(function(){
	    	var rwjddt = $("#rwjddt").val();
	    	alert(rwjddt);
	    	location.href="ClearInfoAction_userOperation.action?theDay="+rwjddt;
	    })
	    
	    $("#pfrk").click(function(){
	    	location.href="ClearInfoAction_pingfen.action";
	    })
	  

  </script>
  </body>
</html>
