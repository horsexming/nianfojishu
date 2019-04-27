
  function selasn(rucan){
  $('#ifname').attr("src","HttpRestClientForaction!selASN.action?add="+rucan);
  $(".choseone").css({"background-color":"#F1F1F1"});
  $(".chosetwo").css({"background-color":"#1890FF"});
  $(".chosethree").css({"background-color":"#F1F1F1"});
  }
  
$(function () {
	 $(".submit").hover(function(){
	    	$(".submit").css({"cursor":"pointer"});      
	    },function(){

	    });
	 
	 $(".reset").hover(function(){
	    	$(".reset").css({"cursor":"pointer"});      
	    },function(){

	    });
	 
	 
	 //下一个点击事件
	 $(".nextone").click(function () {
    	
    });
	
	 
	 $("#ifname").attr('src','System/YcB2B/songhuo/tableDelive.jsp');

	 
	 
	 
	 
	 if($("#ifname").attr('src','System/YcB2B/songhuo/tableDelive.jsp')){
		 $(".choseone").css({"background-color":"#1890FF"});
	 }else if ($("#ifname").attr('src','System/YcB2B/songhuo/ASNinformation.jsp')) {
		 $(".chosetwo").css({"background-color":"#1890FF"});
    }else if ($("#ifname").attr('src','System/YcB2B/songhuo/sureDelive.jsp')) {
    	$(".chosethree").css({"background-color":"#1890FF"});
    }
	 
	 
	 //标题的点击事件
	 $(".checkall").click(function () {
    	if($('.checkall').is(':checked')){
    		
    		$("table input[type=checkbox]").attr("checked",true)  		

    		
    	}else {
    		$("table input[type=checkbox]").attr("checked",false) 
    	}	 
    });
	 

	 //下一步
	 $(".nextone").click(function () {
		 var rucan=new Array();
		 for (var int = 1; int < 21; int++) {
		  var check=  $("#tab tr:eq("+int+") td:eq(1)").find("input");
		  if(check.is(":checked")){
			  //客户
			  var kehu= $(check).parent().next().find("input").val();
			  if(kehu=="华为技术有限公司"){
				  var invOrgId=218;
			  }
			  //物料编号
			  var itemcode=$(check).parent().next().next().next().next().find("input").val();
			  //物料版本
			  var itemrevison=$(check).parent().next().next().next().next().next().find("input").val();
			  if(itemrevison==""){
				  itemrevison="null";
			  }
			  //本次发货数量
    		var numberall=$(check).parent().next().next().next().next().next().next().next().next().next().find("input").val();
    		var add="["+itemcode+"," +itemrevison+"," + numberall+"," + invOrgId+"]";
    		rucan.push(add);  	
    	 }

		 } 
		 if(rucan==null ||rucan.length==0){
			 alert("请选贼要提交的条目");
		 }else{
				parent.selasn(rucan);
		 }
		 


    });
	 
	 
	 
	 
	 
	
});