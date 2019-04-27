$(function () {
    $(".daochu").click(function () {
    	$(".ing").css({"display":"block"});
    	$(".all").css({"display":"block"});
    });
  	
    

    
    
    //当前页点击事件
    $(".ing").click(function () {
    	$(".ing").css({"display":"none"});
    	$(".all").css({"display":"none"});
    	
        var taday= localStorage.getItem("taday");
        var nexttoday= localStorage.getItem("nexttoday");
        var twonextday= localStorage.getItem("twonextday");
        var threeday= localStorage.getItem("threeday");
        var forday=localStorage.getItem("forday");
        var friveday=localStorage.getItem("friveday");
        var sixday=localStorage.getItem("sixday");
        var sevenday=localStorage.getItem("sevenday");
        var eitday=localStorage.getItem("eitday");
        var nineday=localStorage.getItem("nineday");
        var tenday=localStorage.getItem("tenday");
        var elevenday=localStorage.getItem("elevenday");
    	
    	
    	
    	
    	

    	//全部事件
    	//给个判断当他为全部的时候就是空就好
    	var orgId=$("input[name='orgId']").val();
		var startTime=$("input[name='startTime']").val();
		var endTime=$("input[name='endTime']").val();
		var zhuangtai="当前页";
		window.location.href ="HttpRestClientForaction!excel.action?orgId="+orgId+"&&startTime="+startTime+"&&endTime="+endTime+"&&zhuangtai="+zhuangtai+"&&taday="+taday+"&&nexttoday="+nexttoday+"&&twonextday="
		+twonextday+"&&threeday="+threeday+"&&forday="+forday+"&&friveday="+friveday+"&&sixday="+sixday+"&&sevenday="+sevenday+"&&eitday="+eitday+"&&nineday="+nineday+"&&tenday="+tenday+"&&elevenday="+elevenday;

    });
    
    
    
    
    
  //所有页点击事件
    $(".all").click(function () {
    	$(".ing").css({"display":"none"});
    	$(".all").css({"display":"none"});
    	//全部事件
    	//给个判断当他为全部的时候就是空就好
    	var orgId=$("input[name='orgId']").val();
		var startTime=$("input[name='startTime']").val();
		var endTime=$("input[name='endTime']").val();
		var zhuangtai="全部";
		
        var taday= localStorage.getItem("taday");
        var nexttoday= localStorage.getItem("nexttoday");
        var twonextday= localStorage.getItem("twonextday");
        var threeday= localStorage.getItem("threeday");
        var forday=localStorage.getItem("forday");
        var friveday=localStorage.getItem("friveday");
        var sixday=localStorage.getItem("sixday");
        var sevenday=localStorage.getItem("sevenday");
        var eitday=localStorage.getItem("eitday");
        var nineday=localStorage.getItem("nineday");
        var tenday=localStorage.getItem("tenday");
        var elevenday=localStorage.getItem("elevenday");
		
		
    	window.location.href ="HttpRestClientForaction!excel.action?orgId="+orgId+"&&startTime="+startTime+"&&endTime="+endTime+"&&zhuangtai="+zhuangtai+"&&taday="+taday+"&&nexttoday="+nexttoday+"&&twonextday="
		+twonextday+"&&threeday="+threeday+"&&forday="+forday+"&&friveday="+friveday+"&&sixday="+sixday+"&&sevenday="+sevenday+"&&eitday="+eitday+"&&nineday="+nineday+"&&tenday="+tenday+"&&elevenday="+elevenday;
    });
    
    
    
    
    
    
    
    
    
    
    
}); 