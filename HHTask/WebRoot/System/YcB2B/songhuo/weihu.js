$(function () {
	//批量维护DC点击事件
	$(".weihuDC").click(function () {
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
    });
	
	
	//导出
//	var sel=document.getElementById("selectDropDown");
//	　　var option=sel.options;
//	　　for(var i=0;i<option.length;i++){
//	　　　　option[i].onclick=function(){
//	　　　　　　alert(this.text);//获取下拉选项的文本值
//	　　　　　　alert(this.value);//获取下拉选项的value值
//	　　　　}
//	　　}

	
	
	
	//标题复选框
	$(".checkall").click(function () {
		if($('.checkall').is(':checked')){
			$("table input[type=checkbox]").attr("checked",true);			
		}   else {
    		$("table input[type=checkbox]").attr("checked",false) 
    	}		
    });
	
	
	
});