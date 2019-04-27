$(function(){
	
	 
   		var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        //当前日期 年月日
        var taday = year + seperator1 + month + seperator1 + strDate;
        
        $(".today").text(taday);
        
    	localStorage.setItem("taday", taday);
    	
        $("input[name='startTime']").val(taday);
        $("input[name='endTime']").val(taday);
        
        
        $("input[name='orgId']").val("华为技术有限公司");
        
        var currentdate;
   		var week; 
   		
		if(date.getDay()==0) week="星期日"
		if(date.getDay()==1) week="星期一"
		if(date.getDay()==2) week="星期二" 
		if(date.getDay()==3) week="星期三"
		if(date.getDay()==4) week="星期四"
		if(date.getDay()==5) week="星期五"
		if(date.getDay()==6) week="星期六"
		
		if(week=="星期一"){
		//如果是润年2月就是28天
		nexttoday=year + seperator1 + month + seperator1 + strDate;
		}
		if(week=="星期二"){
		strDate=strDate-1;
		if(strDate==0){
		
		//闰年
		if (year % 4 == 0 && year %100 != 0) {
		//夸年
		if(month==1 ){
		year=year-1;
		month=12;
		strDate=31;
		}
		if(month==2 ){
		month=month-1;
		strDate=31;
		}
		if(month==3 ){
		month=month-1;
		strDate=29;
		}
		if(month==4 ){
		month=month-1;
		strDate=31;
		}
		if(month==5 ){
		month=month-1;
		strDate=30;
		}
		if(month==6 ){
		month=month-1;
		strDate=31;
		}
		if(month==7 ){
		month=month-1;
		strDate=30;
		}
		if(month==8){
		month=month-1;
		strDate=31;
		}	
		if(month==9){
		month=month-1;
		strDate=31;
		}
		if(month==10){
		month=month-1;
		strDate=30;
		}
		if(month==11){
		month=month-1;
		strDate=31;
		}
		if(month==12){
		month=month-1;
		strDate=30;
		}
		
		}
		
		else{
		if(month==1 ){
		year=year-1;
		month=12;
		strDate=31;
		}
		if(month==2 ){
		month=month-1;
		strDate=31;
		}
		if(month==3 ){
		month=month-1;
		strDate=28;
		}
		if(month==4 ){
		month=month-1;
		strDate=31;
		}
		if(month==5 ){
		month=month-1;
		strDate=30;
		}
		if(month==6 ){
		month=month-1;
		strDate=31;
		}
		if(month==7 ){
		month=month-1;
		strDate=30;
		}
		if(month==8){
		month=month-1;
		strDate=31;
		}	
		if(month==9){
		month=month-1;
		strDate=31;
		}
		if(month==10){
		month=month-1;
		strDate=30;
		}
		if(month==11){
		month=month-1;
		strDate=31;
		}
		if(month==12){
		month=month-1;
		strDate=30;
		}
		
		}
			
			
		}

		currentdate=year + seperator1 + month + seperator1 + strDate;
		}
		if(week=="星期三"){
		strDate=strDate-2;
		
	if(strDate<=0){
		//闰年
		if (year % 4 == 0 && year %100 != 0) {
		//夸年
		if(month==1 ){
		year=year-1;
		month=12;
		strDate=31-strDate;
		}
		if(month==2 ){
		month=month-1;
		strDate=31 -strDate;
		}
		if(month==3 ){
		month=month-1;
		strDate=29 -strDate;
		}
		if(month==4 ){
		month=month-1;
		strDate=31 -strDate;
		}
		if(month==5 ){
		month=month-1;
		strDate=30 -strDate;
		}
		if(month==6 ){
		month=month-1;
		strDate=31 -strDate;
		}
		if(month==7 ){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==8){
		month=month-1;
		strDate=31-strDate;
		}	
		if(month==9){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==10){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==11){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==12){
		month=month-1;
		strDate=30-strDate;
		}
		
		}
		
		else{
		if(month==1 ){
		year=year-1;
		month=12;
		strDate=31-strDate;
		}
		if(month==2 ){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==3 ){
		month=month-1;
		strDate=28-strDate;
		}
		if(month==4 ){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==5 ){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==6 ){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==7 ){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==8){
		month=month-1;
		strDate=31-strDate;
		}	
		if(month==9){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==10){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==11){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==12){
		month=month-1;
		strDate=30-strDate;
		}
		
		}
			
			
		}
		
		
		
		
		
		
		
		currentdate=year + seperator1 + month + seperator1 + strDate;
		}
		if(week=="星期四"){
		strDate=strDate-3;
		
		if(strDate<=0){
		//闰年
		if (year % 4 == 0 && year %100 != 0) {
		//夸年
		if(month==1 ){
		year=year-1;
		month=12;
		strDate=31-strDate;
		}
		if(month==2 ){
		month=month-1;
		strDate=31 -strDate;
		}
		if(month==3 ){
		month=month-1;
		strDate=29 -strDate;
		}
		if(month==4 ){
		month=month-1;
		strDate=31 -strDate;
		}
		if(month==5 ){
		month=month-1;
		strDate=30 -strDate;
		}
		if(month==6 ){
		month=month-1;
		strDate=31 -strDate;
		}
		if(month==7 ){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==8){
		month=month-1;
		strDate=31-strDate;
		}	
		if(month==9){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==10){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==11){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==12){
		month=month-1;
		strDate=30-strDate;
		}
		
		}
		
		else{
		if(month==1 ){
		year=year-1;
		month=12;
		strDate=31-strDate;
		}
		if(month==2 ){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==3 ){
		month=month-1;
		strDate=28-strDate;
		}
		if(month==4 ){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==5 ){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==6 ){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==7 ){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==8){
		month=month-1;
		strDate=31-strDate;
		}	
		if(month==9){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==10){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==11){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==12){
		month=month-1;
		strDate=30-strDate;
		}
		
		}
			
			
		}
		currentdate=year + seperator1 + month + seperator1 + strDate;
		}
		if(week=="星期五"){
		strDate=strDate-4;
		
		if(strDate<=0){
		//闰年
		if (year % 4 == 0 && year %100 != 0) {
		//夸年
		if(month==1 ){
		year=year-1;
		month=12;
		strDate=31-strDate;
		}
		if(month==2 ){
		month=month-1;
		strDate=31 -strDate;
		}
		if(month==3 ){
		month=month-1;
		strDate=29 -strDate;
		}
		if(month==4 ){
		month=month-1;
		strDate=31 -strDate;
		}
		if(month==5 ){
		month=month-1;
		strDate=30 -strDate;
		}
		if(month==6 ){
		month=month-1;
		strDate=31 -strDate;
		}
		if(month==7 ){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==8){
		month=month-1;
		strDate=31-strDate;
		}	
		if(month==9){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==10){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==11){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==12){
		month=month-1;
		strDate=30-strDate;
		}
		
		}
		
		else{
		if(month==1 ){
		year=year-1;
		month=12;
		strDate=31-strDate;
		}
		if(month==2 ){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==3 ){
		month=month-1;
		strDate=28-strDate;
		}
		if(month==4 ){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==5 ){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==6 ){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==7 ){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==8){
		month=month-1;
		strDate=31-strDate;
		}	
		if(month==9){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==10){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==11){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==12){
		month=month-1;
		strDate=30-strDate;
		}
		
		}
			
			
		}
		currentdate=year + seperator1 + month + seperator1 + strDate;
		}
		if(week=="星期六"){
		strDate=strDate-5;
		if(strDate<=0){
		//闰年
		if (year % 4 == 0 && year %100 != 0) {
		//夸年
		if(month==1 ){
		year=year-1;
		month=12;
		strDate=31-strDate;
		}
		if(month==2 ){
		month=month-1;
		strDate=31 -strDate;
		}
		if(month==3 ){
		month=month-1;
		strDate=29 -strDate;
		}
		if(month==4 ){
		month=month-1;
		strDate=31 -strDate;
		}
		if(month==5 ){
		month=month-1;
		strDate=30 -strDate;
		}
		if(month==6 ){
		month=month-1;
		strDate=31 -strDate;
		}
		if(month==7 ){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==8){
		month=month-1;
		strDate=31-strDate;
		}	
		if(month==9){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==10){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==11){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==12){
		month=month-1;
		strDate=30-strDate;
		}
		
		}
		
		else{
		if(month==1 ){
		year=year-1;
		month=12;
		strDate=31-strDate;
		}
		if(month==2 ){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==3 ){
		month=month-1;
		strDate=28-strDate;
		}
		if(month==4 ){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==5 ){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==6 ){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==7 ){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==8){
		month=month-1;
		strDate=31-strDate;
		}	
		if(month==9){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==10){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==11){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==12){
		month=month-1;
		strDate=30-strDate;
		}
		
		}
			
			
		}
		currentdate=year + seperator1 + month + seperator1 + strDate;
		}
		if(week=="星期日"){
		strDate=strDate-6;
		if(strDate<=0){
		//闰年
		if (year % 4 == 0 && year %100 != 0) {
		//夸年
		if(month==1 ){
		year=year-1;
		month=12;
		strDate=31-strDate;
		}
		if(month==2 ){
		month=month-1;
		strDate=31 -strDate;
		}
		if(month==3 ){
		month=month-1;
		strDate=29 -strDate;
		}
		if(month==4 ){
		month=month-1;
		strDate=31 -strDate;
		}
		if(month==5 ){
		month=month-1;
		strDate=30 -strDate;
		}
		if(month==6 ){
		month=month-1;
		strDate=31 -strDate;
		}
		if(month==7 ){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==8){
		month=month-1;
		strDate=31-strDate;
		}	
		if(month==9){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==10){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==11){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==12){
		month=month-1;
		strDate=30-strDate;
		}
		}	
		else{
		if(month==1 ){
		year=year-1;
		month=12;
		strDate=31-strDate;
		}
		if(month==2 ){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==3 ){
		month=month-1;
		strDate=28-strDate;
		}
		if(month==4 ){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==5 ){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==6 ){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==7 ){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==8){
		month=month-1;
		strDate=31-strDate;
		}	
		if(month==9){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==10){
		month=month-1;
		strDate=30-strDate;
		}
		if(month==11){
		month=month-1;
		strDate=31-strDate;
		}
		if(month==12){
		month=month-1;
		strDate=30-strDate;
		}
		}	
		}
		currentdate=year + seperator1 + month + seperator1 + strDate;
		}
		 var  nexttoday;
		 var   twonextday;
		 var yue=0;
		 var yue2=0;
		 var yue3=0;
		 var yue4=0;
		 var yue5=0;
		 var yue6=0; 
		 var yue7=0;
		 var yue8=0;
		 var yue9=0;
		 var yue10=0;
		 var yue11=0;
		 
		 
		 
		 //下一个星期
		//当前星期为大月跳小月
		if((month==1||month==3||month==5||month==7||month==8||month==10) &&  strDate+7>31 ){
			if(month==1){
				yue=1
			}
		month=month+1;
		strDate=strDate+7-31;
		  nexttoday=year + seperator1 + month+ seperator1 + strDate;
		}
		//正常情况
		if((month==1||month==3||month==5||month==7||month==8||month==10) &&  strDate+7<31 ){
			strDate=strDate+7;
		  nexttoday=year + seperator1 + month+ seperator1 + strDate;
		}	
		if(month==12 && strDate+7>31){
		year=year+1;
			month=1;
		strDate=strDate+7-31;
		  nexttoday=year + seperator1 + month+ seperator1 + strDate;
		}	if(month==12){
			strDate=strDate+7;
			nexttoday=year + seperator1 + month+ seperator1 + strDate;
	}
		if(month==2 && yue==0){
		if (year % 4 == 0 && year %100 != 0) {
		//如果是闰年
		  if( strDate+7>29){
		  strDate=strDate+7-29;
		  month=month+1;
		  nexttoday=year + seperator1 + month+ seperator1 + strDate;
		  yue=0;
		  }else{
		  strDate=strDate+7;
		  nexttoday=year + seperator1 + month+ seperator1 + strDate;
		  yue=0;
		  }  
		}else{
		
		 if( strDate+7>28){
		  strDate=strDate+7-28;
		  month=month+1;
		  nexttoday=year + seperator1 + month+ seperator1 + strDate
		  yue=0;
		  }else{
		   strDate=strDate+7
		   nexttoday=year + seperator1 + month+ seperator1 + strDate
		   yue=0;
		  }
		
		}
		
		}
		if((month==4||month==6||month==9||month==11)&&  strDate+7>30 ){
		month=month+1;
		strDate=strDate+7-30;
		nexttoday=year + seperator1 + month+ seperator1 + strDate;
		}
		if((month==4||month==6||month==9||month==11)&&  strDate+7<30 ){
		strDate=strDate+7;
		nexttoday=year + seperator1 + month+ seperator1 + strDate;
		}
			
		
		var   threeday;
		//第二个星期一
				//当前星期为大月跳小月
		if((month==1||month==3||month==5||month==7||month==8||month==10 )&&  strDate+7>31 ){
			if(month==1){
				yue2=1
			}
			month=month+1;
			strDate=strDate+7-31;
		  twonextday=year + seperator1 + month+ seperator1 + strDate;
		}
		//正常情况
		if((month==1||month==3||month==5||month==7||month==8||month==10) &&  strDate+7<31 ){
			strDate=strDate+7;
		  twonextday=year + seperator1 + month+ seperator1 + strDate;
		}	
		if(month==12 && strDate+7>31){
		year=year+1;
			month=1;
		strDate=strDate+7-31;
		  twonextday=year + seperator1 + month+ seperator1 + strDate;
		}	if(month==12){
			strDate=strDate+7;
			twonextday=year + seperator1 + month+ seperator1 + strDate;
	}
		if(month==2 && yue2==0){
		if (year % 4 == 0 && year %100 != 0) {
		//如果是闰年
		  if( strDate+7>29){
		  strDate=strDate+7-29;
		  month=month+1;
		  twonextday=year + seperator1 + month+ seperator1 + strDate;
		  yue2==0;
		  }else{
		  strDate=strDate+7;
		  twonextday=year + seperator1 + month+ seperator1 + strDate;
		  yue2==0
		  }  
		}else{
		
		 if( strDate+7>28){
		 	month=month+1;
		  strDate=strDate+7-28;
		  twonextday=year + seperator1 + month+ seperator1 + strDate;
		  yue2==0
		  }else{
		   strDate=strDate+7;
		   twonextday=year + seperator1 + month+ seperator1 + strDate;
		   yue2==0
		  }
		
		}
		
		}
		if((month==4||month==6||month==9||month==11)&&  strDate+7>30 ){
		month=month+1;
		strDate=strDate+7-30;
		twonextday=year + seperator1 + month+ seperator1 + strDate;
		}
		if((month==4||month==6||month==9||month==11)&&  strDate+7<30 ){
		strDate=strDate+7;
		twonextday=year + seperator1 + month+ seperator1 + strDate;
		}
		
		
		//第三个星期
		if((month==1||month==3||month==5||month==7||month==8||month==10) &&  strDate+7>31 ){
			if(month==1){
				yue3=1
			}
			month=month+1;
			strDate=strDate+7-31;
		  threeday=year + seperator1 + month+ seperator1 + strDate;
		}
		//正常情况
		if((month==1||month==3||month==5||month==7||month==8||month==10 )&&  strDate+7<31 ){
			strDate=strDate+7;
		  threeday=year + seperator1 + month+ seperator1 + strDate;
		}	
		if(month==12 && strDate+7>31){
		year=year+1;
			month=1;
		strDate=strDate+7-31;
		  threeday=year + seperator1 + month+ seperator1 + strDate;
		}	if(month==12){
			strDate=strDate+7;
			threeday=year + seperator1 + month+ seperator1 + strDate;
	}
		
		
		
		if(month==2 && yue3==0){
		if (year % 4 == 0 && year %100 != 0) {
		//如果是闰年
		  if( strDate+7>29){
		  strDate=strDate+7-29;
		  month=month+1;
		  threeday=year + seperator1 + month+ seperator1 + strDate;
		  yue3=0;
		  }else{
		  strDate=strDate+7;
		  threeday=year + seperator1 + month+ seperator1 + strDate;
		  yue3=0;
		  }  
		}else{
		
		 if( strDate+7>28){
		 	month=month+1;
		  strDate=strDate+7-28;
		  threeday=year + seperator1 + month+ seperator1 + strDate;
		  yue3=0;
		  }else{
		   strDate=strDate+7;
		   threeday=year + seperator1 + month+ seperator1 + strDate;
		   yue3=0;
		  }
		
		}
		
		}
		if((month==4||month==6||month==9||month==11)&&  strDate+7>30 ){
		month=month+1;
		strDate=strDate+7-30;
		threeday=year + seperator1 + month+ seperator1 + strDate;
		}
		if((month==4||month==6||month==9||month==11)&&  strDate+7<30 ){
		strDate=strDate+7;
		threeday=year + seperator1 + month+ seperator1 + strDate;
		}
		
		var forday;
		//第四个星期
		if((month==1||month==3||month==5||month==7||month==8||month==10) &&  strDate+7>31 ){
			if(month==1){
				yue4=1
			}
			month=month+1;
			strDate=strDate+7-31;
		  forday=year + seperator1 + month+ seperator1 + strDate;
		}
		//正常情况
		if((month==1||month==3||month==5||month==7||month==8||month==10) &&  strDate+7<31 ){
			strDate=strDate+7;
		  forday=year + seperator1 + month+ seperator1 + strDate;
		}	
		if(month==12 && strDate+7>31){
		year=year+1;
			month=1;
		strDate=strDate+7-31;
		  forday=year + seperator1 + month+ seperator1 + strDate;
		}	if(month==12){
			strDate=strDate+7;
			forday=year + seperator1 + month+ seperator1 + strDate;
	}
		
		
		
		
		if(month==2 && yue4==0){
		if (year % 4 == 0 && year %100 != 0) {
		//如果是闰年
		  if( strDate+7>29){
		  strDate=strDate+7-29;
		  month=month+1;
		  forday=year + seperator1 + month+ seperator1 + strDate;
		  yue4=0;
		  }else{
		  strDate=strDate+7;
		  forday=year + seperator1 + month+ seperator1 + strDate;
		  yue4=0;
		  }  
		}else{
		
		 if( strDate+7>28){
		 	month=month+1;
		  strDate=strDate+7-28;
		  forday=year + seperator1 + month+ seperator1 + strDate;
		  yue4=0;
		  }else{
		   strDate=strDate+7;
		   forday=year + seperator1 + month+ seperator1 + strDate;
		   yue4=0;
		  }
		
		}
		
		}
		if((month==4||month==6||month==9||month==11)&&  strDate+7>30 ){
		month=month+1;
		strDate=strDate+7-30;
		forday=year + seperator1 + month+ seperator1 + strDate;
		}
		if(month==4||month==6||month==9||month==11&&  strDate+7<30 ){
		strDate=strDate+7;
		forday=year + seperator1 + month+ seperator1 + strDate;
		}
		
		
		
		
		var friveday;
		if((month==1||month==3||month==5||month==7||month==8||month==10) &&  strDate+7>31 ){
			if(month==1){
				yue5=1
			}
			month=month+1;
			strDate=strDate+7-31;
			friveday=year + seperator1 + month+ seperator1 + strDate;
		}
		//第五个星期
		if((month==1||month==3||month==5||month==7||month==8||month==10) &&  strDate+7<31 ){
			strDate=strDate+7;
		  friveday=year + seperator1 + month+ seperator1 + strDate;
		}	
		
		//正常情况

		if(month==12 && strDate+7>31){
			year=year+1;
			month=1;
		strDate=strDate+7-31;
		  friveday=year + seperator1 + month+ seperator1 + strDate;
		}	if(month==12){
			strDate=strDate+7;
			friveday=year + seperator1 + month+ seperator1 + strDate;
	}
		
		
		
		if(month==2 && yue5==0){
		if (year % 4 == 0 && year %100 != 0) {
		//如果是闰年
		  if( strDate+7>29){
		  strDate=strDate+7-29;
		  month=month+1;
		  friveday=year + seperator1 + month+ seperator1 + strDate;
		  yue5=0;
		  }else{
		  strDate=strDate+7;
		  friveday=year + seperator1 + month+ seperator1 + strDate;
		  yue5=0;
		  }  
		}else{
		
		 if( strDate+7>28){
		 	month=month+1;
		  strDate=strDate+7-28;
		  friveday=year + seperator1 + month+ seperator1 + strDate;
		  yue5=0;
		  }else{
		   strDate=strDate+7;
		   friveday=year + seperator1 + month+ seperator1 + strDate;
		   yue5=0;
		  }
		
		}
		
		}
		if((month==4||month==6||month==9||month==11)&&  strDate+7>30 ){
		month=month+1;
		strDate=strDate+7-30;
		friveday=year + seperator1 + month+ seperator1 + strDate;
		}
		if((month==4||month==6||month==9||month==11)&&  strDate+7<30 ){
		strDate=strDate+7;
		friveday=year + seperator1 + month+ seperator1 + strDate;
		}
		

					//第六个星期
   			var sixday;
   			if((month==1||month==3||month==5||month==7||month==8||month==10) &&  strDate+7>31 ){
   				if(month==1){
   					yue6=1
   				}
			month=month+1;
			strDate=strDate+7-31;
		  sixday=year + seperator1 + month+ seperator1 + strDate;
		}
		//正常情况
		if((month==1||month==3||month==5||month==7||month==8||month==10) &&  strDate+7<31 ){
			strDate=strDate+7;
		  sixday=year + seperator1 + month+ seperator1 + strDate;
		}	
		if(month==12 && strDate+7>31){
			year=year+1;
			month=1;
			strDate=strDate+7-31;
		  sixday=year + seperator1 + month+ seperator1 + strDate;
		}	
		
		if(month==12){
			strDate=strDate+7;
			sixday=year + seperator1 + month+ seperator1 + strDate;
	}
		
		if(month==2 && yue6==0){
		if (year % 4 == 0 && year %100 != 0) {
		//如果是闰年
		  if( strDate+7>29){
		  strDate=strDate+7-29;
		  month=month+1;
		  sixday=year + seperator1 + month+ seperator1 + strDate;
		  yue6=0;
		  }else{
		  strDate=strDate+7;
		  sixday=year + seperator1 + month+ seperator1 + strDate;
		  yue6=0;
		  }  
		}else{
		
		 if( strDate+7>28){
		 	month=month+1;
		  strDate=strDate+7-28;
		  sixday=year + seperator1 + month+ seperator1 + strDate;
		  yue6=0;
		  }else{
		   strDate=strDate+7;
		   sixday=year + seperator1 + month+ seperator1 + strDate;
		   yue6=0;
		  }
		
		}
		
		}
		if((month==4||month==6||month==9||month==11)&&  strDate+7>30 ){
		month=month+1;
		strDate=strDate+7-30;
		sixday=year + seperator1 + month+ seperator1 + strDate;
		}
		if((month==4||month==6||month==9||month==11)&&  strDate+7<30 ){
		strDate=strDate+7;
		sixday=year + seperator1 + month+ seperator1 + strDate;
		}
		
		//第七个星期
		var sevenday;

	//正常情况
		if((month==1||month==3||month==5||month==7||month==8||month==10) &&  strDate+7>31 ){
			if(month==1){
				yue7=1
			}
			month=month+1;
			strDate=strDate+7-31;
			sevenday=year + seperator1 + month+ seperator1 + strDate;	
		}
		
	if((month==1||month==3||month==5||month==7||month==8||month==10) &&  strDate+7<31 ){
		strDate=strDate+7;
		sevenday=year + seperator1 + month+ seperator1 + strDate;	
	}	
	

	
	if(month==12 && strDate+7>31){
		year=year+1;
		month=1;
		strDate=strDate+7-31;
		sevenday=year + seperator1 + month+ seperator1 + strDate;
	}
	if(month==12){
			strDate=strDate+7;
			sevenday=year + seperator1 + month+ seperator1 + strDate;
	}
	
	
	if(month==2 && yue==0){
	if (year % 4 == 0 && year %100 != 0) {
	//如果是闰年
	  if( strDate+7>29){
	  strDate=strDate+7-29;
	  month=month+1;
	  sevenday=year + seperator1 + month+ seperator1 + strDate;
	  yue7=0;
	  }else{
	  strDate=strDate+7;
	  sevenday=year + seperator1 + month+ seperator1 + strDate;
	  yue7=0;
	  }  
	}else{
	
	 if( strDate+7>28){
	 	month=month+1;
	  strDate=strDate+7-28;
	  sevenday=year + seperator1 + month+ seperator1 + strDate;
	  yue7=0;
	  }else{
	   strDate=strDate+7;
	   sevenday=year + seperator1 + month+ seperator1 + strDate;
	   yue7=0;
	  }
	
	}
	
	}
	if((month==4||month==6||month==9||month==11)&&  strDate+7>30 ){
	month=month+1;
	strDate=strDate+7-30;
	sevenday=year + seperator1 + month+ seperator1 + strDate;
	}
	if((month==4||month==6||month==9||month==11)&&  strDate+7<30 ){
	strDate=strDate+7;
	sevenday=year + seperator1 + month+ seperator1 + strDate;
	}
		
	
	
	//第8个星期
	var eitday;
	//正常情况
	if((month==1||month==3||month==5||month==7||month==8||month==10)  && strDate+7 > 31 ){
		if(month==1){
			yue8=1
		}
		month=month+1;
		strDate=strDate+7-31;
		eitday=year + seperator1 + month+ seperator1 + strDate;	
	}
	
	if((month==1||month==3||month==5||month==7||month==8||month==10) &&  strDate+7<31 ){
		strDate=strDate+7;
		eitday=year + seperator1 + month+ seperator1 + strDate;	
	}	

	
	
	
	if(month==12 && strDate+7>31){
		year=year+1;
		month=1;
		strDate=strDate+7-31;
		eitday=year + seperator1 + month+ seperator1 + strDate;
	}
	if(month==12){
			strDate=strDate+7;
			eitday=year + seperator1 + month+ seperator1 + strDate;
	}
	
	
	if(month==2 && yue8==0){
	if (year % 4 == 0 && year %100 != 0) {
	//如果是闰年
	  if( strDate+7>29){
	  strDate=strDate+7-29;
	  month=month+1;
	  eitday=year + seperator1 + month+ seperator1 + strDate;
	  yue8=0;
	  }else{
	  strDate=strDate+7;
	  eitday=year + seperator1 + month+ seperator1 + strDate;
	  yue8=0;
	  }  
	}else{
	
	 if( strDate+7>28){
	 	month=month+1;
	  strDate=strDate+7-28;
	  eitday=year + seperator1 + month+ seperator1 + strDate;
	  yue8=0;
	  }else{
	   strDate=strDate+7;
	   eitday=year + seperator1 + month+ seperator1 + strDate;
	   yue8=0;
	  }
	
	}
	
	}
	if((month==4||month==6||month==9||month==11) &&  strDate+7>30 ){
	month=month+1;
	strDate=strDate+7-30;
	eitday=year + seperator1 + month+ seperator1 + strDate;
	}
	if((month==4||month==6||month==9||month==11) &&  strDate+7<30 ){
	strDate=strDate+7;
	eitday=year + seperator1 + month+ seperator1 + strDate;
	}
	
	
	
	//第9个
	var nineday;
	//正常情况
	
	if((month==1||month==3||month==5||month==7||month==8||month==10)  && strDate+7 > 30 ){
		if(month==1){
			yue9=1
		}
		
		month=month+1;
		strDate=strDate+7-31;
		nineday=year + seperator1 + month+ seperator1 + strDate;
		
		
	}
	
	if((month==1||month==3||month==5||month==7||month==8||month==10) &&  strDate+7<30 ){
		strDate=strDate+7;
		nineday=year + seperator1 + month+ seperator1 + strDate;	
	}	
	
	if(month==12 && strDate+7>31){
		year=year+1;
		month=1;
		strDate=strDate+7-31;
		nineday=year + seperator1 + month+ seperator1 + strDate;
	}
	if(month==12){
			strDate=strDate+7;
			nineday=year + seperator1 + month+ seperator1 + strDate;
	}
	
	
	if(month==2 && yue9==0){
	if (year % 4 == 0 && year %100 != 0) {
	//如果是闰年
	  if( strDate+7>29){
	  strDate=strDate+7-29;
	  month=month+1;
	  nineday=year + seperator1 + month+ seperator1 + strDate;
	  yue9=0;
	  }else{
	  strDate=strDate+7;
	  nineday=year + seperator1 + month+ seperator1 + strDate;
	  yue9=0;
	  }  
	}else{
	
	 if( strDate+7>28){
	 	month=month+1;
	  strDate=strDate+7-28;
	  nineday=year + seperator1 + month+ seperator1 + strDate;
	  yue9=0;
	  }else{
	   strDate=strDate+7;
	   nineday=year + seperator1 + month+ seperator1 + strDate;
	   yue9=0;
	  }
	
	}
	
	}
	if((month==4||month==6||month==9||month==11) &&  strDate+7>30 ){
	month=month+1;
	strDate=strDate+7-30;
	nineday=year + seperator1 + month+ seperator1 + strDate;

	}
	if((month==4||month==6||month==9||month==11)&&  strDate+7<30 ){
	strDate=strDate+7;
	nineday=year + seperator1 + month+ seperator1 + strDate;
	}
	
	//第10个星期
	var tenday;
	//正常情况
	
	if((month==1||month==3||month==5||month==7||month==8||month==10) &&  strDate+7>31 ){
		if(month==1){
		yue10=1
		}
		month=month+1;
		strDate=strDate+7-31;
		tenday=year + seperator1 + month+ seperator1 + strDate;	
	}
	
	if((month==1||month==3||month==5||month==7||month==8||month==10) &&  strDate+7<31 ){
		strDate=strDate+7;
		tenday=year + seperator1 + month+ seperator1 + strDate;	
	}	

	
	if(month==12 && strDate+7>31){
		year=year+1;
		month=1;
		strDate=strDate+7-31;
		tenday=year + seperator1 + month+ seperator1 + strDate;
	}
	if(month==12){
			strDate=strDate+7;
			tenday=year + seperator1 + month+ seperator1 + strDate;
	}
	
	
	if(month==2 && yue10==0){
	if (year % 4 == 0 && year %100 != 0) {
	//如果是闰年
	  if( strDate+7>29){
	  strDate=strDate+7-29;
	  month=month+1;
	  tenday=year + seperator1 + month+ seperator1 + strDate;
	  yue10=0;
	  }else{
	  strDate=strDate+7;
	  tenday=year + seperator1 + month+ seperator1 + strDate;
	  yue10=0;
	  }  
	}else{
	
	 if( strDate+7>28){
	 	month=month+1;
	  strDate=strDate+7-28;
	  tenday=year + seperator1 + month+ seperator1 + strDate;
	  yue10=0;
	  }else{
	   strDate=strDate+7;
	   tenday=year + seperator1 + month+ seperator1 + strDate;
	   yue10=0;
	  }
	
	}
	
	}
	if((month==4||month==6||month==9||month==11) &&  strDate+7>30 ){
	month=month+1;
	strDate=strDate+7-30;
	tenday=year + seperator1 + month+ seperator1 + strDate;
	}
	if((month==4||month==6||month==9||month==11)&&  strDate+7<30 ){
	strDate=strDate+7;
	tenday=year + seperator1 + month+ seperator1 + strDate;
	}
	
	//第11个
	var elevenday;
	
	if((month==1||month==3||month==5||month==7||month==8||month==10) &&  strDate+7>31 ){
		if(month==1){
			 yue11=1	
		}
		month=month+1;
		strDate=strDate+7-31;
		elevenday=year + seperator1 + month+ seperator1 + strDate;	
	}
	
	//正常情况
	if((month==1||month==3||month==5||month==7||month==8||month==10) &&  strDate+7<31 ){
		strDate=strDate+7;
		elevenday=year + seperator1 + month+ seperator1 + strDate;	
	}	

	
	if(month==12 && strDate+7>31){
		year=year+1;
		month=1;
		strDate=strDate+7-31;
		elevenday=year + seperator1 + month+ seperator1 + strDate;
	}
	if(month==12){
			strDate=strDate+7;
			elevenday=year + seperator1 + month+ seperator1 + strDate;
	}
	
	
	if(month==2 && yue11==0){
	if (year % 4 == 0 && year %100 != 0) {
	//如果是闰年
	  if( strDate+7>29){
	  strDate=strDate+7-29;
	  month=month+1;
	  elevenday=year + seperator1 + month+ seperator1 + strDate;
	  yue11=0;
	  }else{
	  strDate=strDate+7;
	  elevenday=year + seperator1 + month+ seperator1 + strDate;
	  yue11=0;
	  }  
	}else{
	
	 if( strDate+7>28){
	 	month=month+1;
	  strDate=strDate+7-28;
	  elevenday=year + seperator1 + month+ seperator1 + strDate;
	  yue11=0;
	  }else{
	   strDate=strDate+7;
	   elevenday=year + seperator1 + month+ seperator1 + strDate;
	   yue11=0;
	  }
	
	}
	
	}
	if((month==4||month==6||month==9||month==11) &&  strDate+7>30 ){
	month=month+1;
	strDate=strDate+7-30;
	elevenday=year + seperator1 + month+ seperator1 + strDate;
	}
	if((month==4||month==6||month==9||month==11)&&  strDate+7<30 ){
	strDate=strDate+7;
	elevenday=year + seperator1 + month+ seperator1 + strDate;
	}
	

				
				
	
	

					$(".elevenday").text(elevenday);
	
					$(".tenday").text(tenday);

						$(".nineday").text(nineday);
	
	
						$(".eitday").text(eitday);
							
						
						$(".sevenday").text(sevenday);
					$(".sixday").text(sixday);
		
				 $(".friveday").text(friveday);

		
				 $(".forday").text(forday);
			 $(".threeday").text(threeday);
		 $(".twonextday").text(twonextday);
  	 $(".nexttoday").text(nexttoday);
  	 
  	localStorage.setItem("nexttoday",nexttoday);
	localStorage.setItem("twonextday", twonextday);
	localStorage.setItem("threeday", threeday);
	localStorage.setItem("forday",forday);
	localStorage.setItem("friveday", friveday);
	localStorage.setItem("sixday", sixday);
	localStorage.setItem("sevenday", sevenday);
	localStorage.setItem("eitday", eitday);
	localStorage.setItem("nineday", nineday);
	localStorage.setItem("tenday", tenday);
	localStorage.setItem("elevenday", elevenday);
  
  	 //修改之后的数据
  	 var b;
  	 //修改那一行的物料编号
  	 var Q1;
  	var Q2;
  	var Q3;	
  	var Q4;  	
  	var Q5;	
  	var Q6;	
  	var Q7;	
  	var Q8;	
  	var Q9;	
  	var Q10; 	
  	var Q11;	
  	var Q12; 	

  	
  	var Q1all=new Array();
  	var Q2all=new Array();
	var Q3all=new Array();
	var Q4all=new Array();
	var Q5all=new Array();
	var Q6all=new Array();
	var Q7all=new Array();
	var Q8all=new Array();
	var Q9all=new Array();
	var Q10all=new Array();
	var Q11all=new Array();
	var Q12all=new Array();
	
  	 $(".Q1hf").change(function(){
  		localStorage.removeItem("Q1all");
         var a =$(this).attr('value');       
         if($(this).val()!=a){
        	 //物料
        	 var Q1itemcode =$(this).parent().parent().prev().prev().find("td:eq(2)").text();
        	 //标题
        	 var  Q1thdate=$(".today").text();
        	//内容
     		Q1=$(this).val();
        	$(this).parent().append("<span style='color:red;display:none; ' id='Q1hide'>物料："+Q1itemcode+"标题："+Q1thdate+"内容:"+Q1+" </span>");
        	var alldata="{"+ Q1itemcode+","+Q1thdate+","+Q1+"}"
        	Q1all.push(alldata);

        	localStorage.setItem("Q1all",Q1all);
        	
        	
        	
         }
         else{
        	 removeItem("Q1all");
        	$(this).parent().append("<span style='color:red; display:none;' id='Q1hide'>未更改</span>");
        	 $(this).val($(this).val());
         }           
     });
  	 
  	 $(".Q2hf").change(function(){
  		localStorage.removeItem("Q2all");
         var a =$(this).attr('value');
         if($(this).val()!=a){
        	 var Q2itemcode =$(this).parent().parent().prev().prev().find("td:eq(2)").text();
           	 //标题
        	 var  Q2thdate=$(".nexttoday").text();
        	//内容
     		Q2=$(this).val();
        	$(this).parent().append("<span style='color:red; display:none;' id='Q2hide'>物料："+Q2itemcode+"标题："+Q2thdate+"内容:"+Q2+" </span>");
        	var alldata="{"+Q2itemcode+","+Q2thdate+","+Q2+"}"
        	Q2all.push(alldata);
        	localStorage.setItem("Q2all",Q2all);
         }
         else{
        	 removeItem("Q2all");
        	 $(this).parent().append("<span style='color:red; display:none;' id='Q2hide'>未更改</span>");
        	 $(this).val($(this).val());
         }           
     });
  	 
  	 $(".Q3hf").change(function(){
  		localStorage.removeItem("Q3all");
         var a =$(this).attr('value');
         if($(this).val()!=a){
        	 var Q3itemcode =$(this).parent().parent().prev().prev().find("td:eq(2)").text();
          	 //标题
        	 var  Q3thdate=$(".twonextday").text();
        	//内容
     		Q3=$(this).val();
        	$(this).parent().append("<span style='color:red; display:none;' id='Q3hide'>物料："+Q3itemcode+"标题："+Q3thdate+"内容:"+Q3+" </span>");
        	var alldata="{"+Q3itemcode+","+Q3thdate+","+Q3+"}";
        	Q3all.push(alldata);
        	localStorage.setItem("Q3all",Q3all);
        	
         }
         else{
        	 removeItem("Q3all");
        	 $(this).parent().append("<span style='color:red; display:none;' id='Q3hide'>未更改</span>");
        	 $(this).val($(this).val());
         }           
     });
  	 
  	 $(".Q4hf").change(function(){
  		localStorage.removeItem("Q4all");
  		 var a =$(this).attr('value');
             if($(this).val()!=a){
            	 var Q4itemcode =$(this).parent().parent().prev().prev().find("td:eq(2)").text();
              	 //标题
            	 var  Q4thdate=$(".threeday").text();
            	//内容
         		Q4=$(this).val();
            	$(this).parent().append("<span style='color:red; display:none;' id='Q4hide'>物料："+Q4itemcode+"标题："+Q4thdate+"内容:"+Q4+" </span>");
            	var alldata="{"+Q4itemcode+","+Q4thdate+","+Q4+"}";
            	Q4all.push(alldata);
            	localStorage.setItem("Q4all",Q4all);
         }
         else{
        	 removeItem("Q4all");
        	 $(this).parent().append("<span style='color:red; display:none;' id='Q4hide'>未更改</span>");
        	 $(this).val($(this).val());
         }           
  	 });
  	 
  	 $(".Q5hf").change(function(){

  		localStorage.removeItem("Q5all");
         var a =$(this).attr('value');
         if($(this).val()!=a){
        	 var Q4itemcode =$(this).parent().parent().prev().prev().find("td:eq(2)").text();
          	 //标题
        	 var  Q4thdate=$(".forday").text();
        	//内容
     		Q5=$(this).val();
        	$(this).parent().append("<span style='color:red; display:none;' id='Q5hide'>物料："+Q4itemcode+"标题："+Q4thdate+"内容:"+Q3+" </span>");
        	
        	var alldata="{"+Q4itemcode+","+Q4thdate+","+Q5+"}";
        	Q5all.push(alldata);
        	localStorage.setItem("Q5all",Q5all);
         }
         else{
        	 removeItem("Q5all");
        	 $(this).parent().append("<span style='color:red; display:none;' id='Q5hide'>未更改</span>");
        	 $(this).val($(this).val());
         }           
     });
  	 
  	 $(".Q6hf").change(function(){
  	
  		localStorage.removeItem("Q6all");
         var a =$(this).attr('value');
         if($(this).val()!=a){
        	 var Q4itemcode =$(this).parent().parent().prev().prev().find("td:eq(2)").text();
          	 //标题
        	 var  Q4thdate=$(".friveday").text();
        	//内容
     		Q6=$(this).val();
        	$(this).parent().append("<span style='color:red; display:none;' id='Q6hide'>物料："+Q4itemcode+"标题："+Q4thdate+"内容:"+Q3+" </span>"); 
           	var alldata="{"+Q4itemcode+","+Q4thdate+","+Q6+"}";
        	Q6all.push(alldata);
        	localStorage.setItem("Q6all",Q6all);
        	
        	}else{
        	removeItem("Q6all");
        	 $(this).parent().append("<span style='color:red; display:none;' id='Q6hide'>未更改</span>");
        	 $(this).val($(this).val());
         }           
     });
  	 
  	 
  	 $(".Q7hf").change(function(){
  		localStorage.removeItem("Q7all");
         var a =$(this).attr('value');
         if($(this).val()!=a){
        	 var Q4itemcode =$(this).parent().parent().prev().prev().find("td:eq(2)").text();
          	 //标题
        	 var  Q4thdate=$(".sixday").text();
        	//内容
     		Q7=$(this).val();
        	$(this).parent().append("<span style='color:red; display:none;' id='Q7hide'>物料："+Q4itemcode+"标题："+Q4thdate+"内容:"+Q3+" </span>");  
           	var alldata="{"+Q4itemcode+","+Q4thdate+","+Q7+"}";
        	Q7all.push(alldata);
        	localStorage.setItem("Q7all",Q7all);
         }
         else{
        	 removeItem("Q7all");
        	 $(this).parent().append("<span style='color:red; display:none;' id='Q7hide'>未更改</span>");
        	 $(this).val($(this).val());
         }           
     });
  	 
  	 $(".Q8hf").change(function(){
  		localStorage.removeItem("Q8all");
         var a =$(this).attr('value');
         if($(this).val()!=a){
        	 var Q4itemcode =$(this).parent().parent().prev().prev().find("td:eq(2)").text();
          	 //标题
        	 var  Q4thdate=$(".sevenday").text();
        	//内容
     		Q8=$(this).val();
        	$(this).parent().append("<span style='color:red; display:none;' id='Q8hide'>物料："+Q4itemcode+"标题："+Q4thdate+"内容:"+Q3+" </span>"); 
           	var alldata="{"+Q4itemcode+","+Q4thdate+","+Q8+"}";
        	Q8all.push(alldata);
        	localStorage.setItem("Q8all",Q8all);
        	
         }
         else{
        	 removeItem("Q8all");
        	 $(this).parent().append("<span style='color:red; display:none;' id='Q8hide'>未更改</span>");
        	 $(this).val($(this).val());
         }           
     });
  	
  	 $(".Q9hf").change(function(){
  		localStorage.removeItem("Q9all");
         var a =$(this).attr('value');
         if($(this).val()!=a){
        	 var Q4itemcode =$(this).parent().parent().prev().prev().find("td:eq(2)").text();
          	 //标题
        	 var  Q4thdate=$(".eitday").text();
        	//内容
     		Q9=$(this).val();
        	$(this).parent().append("<span style='color:red; display:none;' id='Q9hide'>物料："+Q4itemcode+"标题："+Q4thdate+"内容:"+Q3+" </span>"); 
           	var alldata="{"+Q4itemcode+","+Q4thdate+","+Q9+"}";
        	Q9all.push(alldata);
        	localStorage.setItem("Q9all",Q9all);
         }
         else{
        	 removeItem("Q9all");
          	 $(this).parent().append("<span style='color:red; display:none;' id='Q9hide'>未更改</span>");
          	 $(this).val($(this).val());
         }           
     });

  	 $(".Q10hf").change(function(){
  		localStorage.removeItem("Q10all");
         var a =$(this).attr('value');
         if($(this).val()!=a){
        	 var Q4itemcode =$(this).parent().parent().prev().prev().find("td:eq(2)").text();
          	 //标题
        	 var  Q4thdate=$(".nineday").text();
        	//内容
     		Q3=$(this).val();
        	$(this).parent().append("<span style='color:red; display:none;' id='Q10hide'>物料："+Q4itemcode+"标题："+Q4thdate+"内容:"+Q3+" </span>");
           	var alldata="{"+Q4itemcode+","+Q4thdate+","+Q10+"}";
        	Q10all.push(alldata);
        	localStorage.setItem("Q10all",Q10all);
         }
         else{
        	 removeItem("Q10all");
        	 $(this).parent().append("<span style='color:red; display:none;' id='Q10hide'>未更改</span>");
        	 $(this).val($(this).val());
         }           
     });
  	 
  	 $(".Q11hf").change(function(){	 
  		localStorage.removeItem("Q11all");
         var a =$(this).attr('value');
         if($(this).val()!=a){
        	 var Q4itemcode =$(this).parent().parent().prev().prev().find("td:eq(2)").text();
          	 //标题
        	 var  Q4thdate=$(".tenday").text();
        	//内容
     		Q11=$(this).val();
        	$(this).parent().append("<span style='color:red; display:none;' id='Q11hide'>物料："+Q4itemcode+"标题："+Q4thdate+"内容:"+Q3+" </span>");
           	var alldata="{"+Q4itemcode+","+Q4thdate+","+Q11+"}";
        	Q11all.push(alldata);
        	localStorage.setItem("Q11all",Q11all);
         }
         else{
        	 removeItem("Q11all");
        	 $(this).parent().append("<span style='color:red; display:none;' id='Q11hide'>未更改</span>");
        	 $(this).val($(this).val());
         }           
     });
  	 
  	 $(".Q12hf").change(function(){
  		localStorage.removeItem("Q12all");
         var a =$(this).attr('value');
         if($(this).val()!=a){
        	 var Q4itemcode =$(this).parent().parent().prev().prev().find("td:eq(2)").text();
          	 //标题
        	 var  Q4thdate=$(".elevenday").text();
        	//内容
     		Q3=$(this).val();
        	$(this).parent().append("<span style='color:red; display:none;' id='Q12hide'>物料："+Q4itemcode+"标题："+Q4thdate+"内容:"+Q3+" </span>");
           	var alldata="{"+Q12itemcode+","+Q12thdate+","+Q12+"}"
        	Q12all.push(alldata);
        	localStorage.setItem("Q12all",Q12all);
         }
         else{
        	 removeItem("Q12all");
        	 $(this).parent().append("<span style='color:red; display:none;' id='Q12hide'>未更改</span>");
        	 $(this).val($(this).val());
         }           
     });

  	 
  	 
  	 

  	 
  	 
  	 
  	 
  	 
  	 $(".huifu").click(function () {
  		 
  	
  		
  		 if(localStorage.getItem("Q1all")!=null  ){
  		var q1zu=[];
  		q1zu =localStorage.getItem("Q1all").split("}");
  		 }
  		 
  		 
  		 if(localStorage.getItem("Q2all")!=null){
 		 var q2zu=[];
 		q2zu =localStorage.getItem("Q2all").split("}");
  		 }
  		 
  		if(localStorage.getItem("Q3all") !=null){
		 var q3zu=[];
		 q3zu =localStorage.getItem("Q3all").split("}");
  		}
  		
  		if(localStorage.getItem("Q4all")!=null  ){
		 var q4zu=[];
		 q4zu =localStorage.getItem("Q4all").split("}");
  		}
		 
  		if(localStorage.getItem("Q5all")!=null  ){
		 var q5zu=[];
		 q5zu =localStorage.getItem("Q5all").split("}");
  		}
		 
  		if(localStorage.getItem("Q6all")!=null ){
		 var q6zu=[];
		 q6zu =localStorage.getItem("Q6all").split("}");
  		}
		 
  		if(localStorage.getItem("Q7all")!=null ){
		 var q7zu=[];
		 q7zu =localStorage.getItem("Q7all").split("}");
  		}
		 
  		if(localStorage.getItem("Q8all")!=null  ){
		 var q8zu=[];
		 q8zu =localStorage.getItem("Q8all").split("}");
  		}
		 
  		if(localStorage.getItem("Q9all")!=null  ){
		 var q9zu=[];
		 q9zu =localStorage.getItem("Q9all").split("}");
  		}
  		
  		if(localStorage.getItem("Q10all")!=null ){
		 var q10zu=[];
		 q10zu =localStorage.getItem("Q10all").split("}");
  		}
  		
  		if(localStorage.getItem("Q11all")!=null ){
  		var q11zu=[];
		 q11zu =localStorage.getItem("Q11all").split("}");
  		}
		 
  		if(localStorage.getItem("Q12all")!=null ){
		 var q12zu=[];
		 q12zu =localStorage.getItem("Q12all").split("}");
  		}


  		var array=new Array();
		Array.prototype.contains = function(obj) {
			 var i = this.length;
			 while (i--) {
			  if (this[i] === obj) {
			   return i; // 返回的这个 i 就是元素的索引下标，
			  }
			 }
			 return false;
			}
			
  		
  		if(q1zu !=null){
  		for(var i=0;i<q1zu.length-1;i++){
  			
  			var stra=q1zu[i];
  			
  			if (stra.substr(0,1)==','){
  				stra=stra.substr(1);
  				var stop= stra.split(",")[0];
  				
  			}else{
  				var stop= stra.split(",")[0];
  			}
  			
  			
  			//华为物料编号
  			var iteme= stra.substring(1, stop.length) ;
  			//标题时间
  			 var a= stra.split(",")[1];
  			//修改内容
  			var xiu=stra.split(",")[2];
  			var need="{"+"'data':{"+ "'"+a+"'"+":"+xiu;
  			  
  			
  			
  		//2组
  		if(q2zu !=null){
  		for(var j=0;j<q2zu.length-1;j++){
  			var strb=q2zu[j];
  			
  			if (strb.substr(0,1)==','){
  				strb=strb.substr(1);
  				var stop= strb.split(",")[0];
  				
  			}else{
  				var stop= strb.split(",")[0];
  			}
  			
  			//华为物料编号
  			var iteme1= strb.substring(1, stop.length) ;
  			//标题时间
  			 var a1= strb.split(",")[1];
  			var xiu1=strb.split(",")[2];
 			//修改后内容
  			var need2="'"+a1+"'"+":"+xiu1;

  			if(iteme==iteme1){	
			 need=need+","+need2;
			 var deletezu= stop+","+a1+","+xiu1;
			 q2zu.splice(q2zu.contains(''+deletezu),1)
		}

  		}	
  			}
  		
  		
  	//3组
  		if(q3zu !=null){
  		for(var q=0;q<q3zu.length-1;q++){
  			var strb=q3zu[q];
  			if (strb.substr(0,1)==','){
  				strb=strb.substr(1);
  				var stop= strb.split(",")[0];
  				
  			}else{
  				var stop= strb.split(",")[0];
  			}
  			//华为物料编号
  			var iteme2= strb.substring(1, stop.length) ;
  			//标题时间
  			 var a2= strb.split(",")[1];
  			var xiu2=strb.split(",")[2];
  			var need3="'"+a2+"'"+":"+xiu2;
 			//修改后内容
  			if(iteme==iteme2){
  				need=need+","+need3;
  				 var deletezu= stop+","+a2+","+xiu2;
  				q3zu.splice(q3zu.contains(''+deletezu),1)

  				
		}

  		}	
  			}
  		
  		//4组
  		if(q4zu !=null){
  		for(var w=0;w<q4zu.length-1;w++){
  			var strb=q4zu[w];
  			if (strb.substr(0,1)==','){
  				strb=strb.substr(1);
  				var stop= strb.split(",")[0];
  				
  			}else{
  				var stop= strb.split(",")[0];
  			}
  			//华为物料编号
  			var iteme4= strb.substring(1, stop.length) ;
  			//标题时间
  			 var a4= strb.split(",")[1];
  			var xiu4=strb.split(",")[2];
  			var need4="'"+a4+"'"+":"+xiu4;
 			//修改后内容
  			if(iteme==iteme4){
  				need=need+","+need4;
 				 var deletezu= stop+","+a3+","+xiu3;
 				q4zu.splice(q4zu.contains(''+deletezu),1)
  				
		}

  		}	
  			}
  		
  		
  		//5组
  		if(q5zu !=null){
  		for(var e=0;e<q5zu.length-1;e++){
  			var strb=q5zu[e];
  			if (strb.substr(0,1)==','){
  				strb=strb.substr(1);
  				var stop= strb.split(",")[0];
  				
  			}else{
  				var stop= strb.split(",")[0];
  			}
  			//华为物料编号
  			var iteme5= strb.substring(1, stop.length) ;
  			//标题时间
  			 var a5= strb.split(",")[1];
  			var xiu5=strb.split(",")[2];
 			//修改后内容
  			var need5="'"+a5+"'"+":"+xiu5;
  			if(iteme==iteme5){
  				need=need+","+need5;
 				 var deletezu= stop+","+a5+","+xiu5;
 				q5zu.splice(q5zu.contains(''+deletezu),1)
  				
		}

  		}	
  			}
  		
  		
  		//6组
  		if(q6zu !=null){
  		for(var r=0;r<q6zu.length-1;r++){
  			var strb=q6zu[r];
  			if (strb.substr(0,1)==','){
  				strb=strb.substr(1);
  				var stop= strb.split(",")[0];
  				
  			}else{
  				var stop= strb.split(",")[0];
  			}
  			//华为物料编号
  			var iteme6= strb.substring(1, stop.length) ;
  			//标题时间
  			 var a6= strb.split(",")[1];
  			var xiu6=strb.split(",")[2];
 			//修改后内容
  			var need6="'"+a6+"'"+":"+xiu6;
  			if(iteme==iteme6){
  				need=need+","+need6;
 				 var deletezu= stop+","+a6+","+xiu6;
 				q6zu.splice(q6zu.contains(''+deletezu),1)
  				
  			
		}

  		}	
  			}
  		
  		
  		//7组
  		if(q7zu !=null){
  		for(var t=0;t<q7zu.length-1;t++){
  			var strb=q7zu[t];
  			if (strb.substr(0,1)==','){
  				strb=strb.substr(1);
  				var stop= strb.split(",")[0];
  				
  			}else{
  				var stop= strb.split(",")[0];
  			}
  			//华为物料编号
  			var iteme7= strb.substring(1, stop.length) ;
  			//标题时间
  			 var a7= strb.split(",")[1];
  			var xiu7=strb.split(",")[2];
 			//修改后内容
  			var need7="'"+a7+"'"+":"+xiu7;
  			if(iteme==iteme7){
  				need=need+","+need7;
 				 var deletezu= stop+","+a7+","+xiu7;
 				q7zu.splice(q7zu.contains(''+deletezu),1)
  				
		}

  		}	
  			}
  		
  		
  		
  			
  		if(q8zu !=null){
  		for(var y=0;y<q8zu.length-1;y++){
  			var strb=q8zu[y];
  			if (strb.substr(0,1)==','){
  				strb=strb.substr(1);
  				var stop= strb.split(",")[0];
  				
  			}else{
  				var stop= strb.split(",")[0];
  			}
  			//华为物料编号
  			var iteme8= strb.substring(1, stop.length) ;
  			//标题时间
  			 var a8= strb.split(",")[1];
  			var xiu8=strb.split(",")[2];
 			//修改后内容
  			var need8="'"+a8+"'"+":"+xiu8;
  			if(iteme==iteme8){
  				need=need+","+need8;
 				 var deletezu= stop+","+a8+","+xiu8;
 				q8zu.splice(q8zu.contains(''+deletezu),1)
  				
		}

  		}	
  			}
  		
  			
  		if(q9zu !=null){
  		for(var u=0;u<q9zu.length-1;u++){
  			var strb=q9zu[u];
  			if (strb.substr(0,1)==','){
  				strb=strb.substr(1);
  				var stop= strb.split(",")[0];
  				
  			}else{
  				var stop= strb.split(",")[0];
  			}
  			//华为物料编号
  			var iteme9= strb.substring(1, stop.length) ;
  			//标题时间
  			 var a9= strb.split(",")[1];
  			var xiu9=strb.split(",")[2];
 			//修改后内容
  			var need9="'"+a9+"'"+":"+xiu9;
  			if(iteme==iteme9){
  				need=need+","+need9;
 				 var deletezu= stop+","+a9+","+xiu9;
 				q9zu.splice(q9zu.contains(''+deletezu),1)
  				
		}

  		}	
  		
  			}	
  		
  		
  		
  		if(q10zu !=null){
  		for(var o=0;o<q10zu.length-1;o++){
  			var strb=q10zu[o];
  			if (strb.substr(0,1)==','){
  				strb=strb.substr(1);
  				var stop= strb.split(",")[0];
  				
  			}else{
  				var stop= strb.split(",")[0];
  			}
  			//华为物料编号
  			var iteme10= strb.substring(1, stop.length) ;
  			//标题时间
  			 var a10= strb.split(",")[1];
  			var xiu10=strb.split(",")[2];
 			//修改后内容
  			var need10="'"+a10+"'"+":"+xiu10;
  			if(iteme==iteme10){
  				need=need+","+need10;
 				 var deletezu= stop+","+a10+","+xiu10;
 				q10zu.splice(q10zu.contains(''+deletezu),1)
  			
		}

  		}	
  			}	
  		
  		if(q11zu !=null){
  		for(var a=0;a<q11zu.length-1;a++){
  			var strb=q11zu[a];
  			if (strb.substr(0,1)==','){
  				strb=strb.substr(1);
  				var stop= strb.split(",")[0];
  				
  			}else{
  				var stop= strb.split(",")[0];
  			}
  			//华为物料编号
  			var iteme11= strb.substring(1, stop.length) ;
  			//标题时间
  			 var a11= strb.split(",")[1];
  			var xiu11=strb.split(",")[2];
 			//修改后内容
  			var need11="'"+a11+"'"+":"+xiu11;
  			if(iteme==iteme11){
  				need=need+","+need11;
 				 var deletezu= stop+","+a11+","+xiu11;
 				q11zu.splice(q11zu.contains(''+deletezu),1)
  			
		}

  		}	
  			}	
  		
  		
  		
  		
  		if(q12zu !=null){
  		for(var s=0;s<q12zu.length-1;s++){
  			var strb=q12zu[s];
  			if (strb.substr(0,1)==','){
  				strb=strb.substr(1);
  				var stop= strb.split(",")[0];
  				
  			}else{
  				var stop= strb.split(",")[0];
  			}
  			//华为物料编号
  			var iteme12= strb.substring(1, stop.length) ;
  			//标题时间
  			 var a12= strb.split(",")[1];
  			var xiu12=strb.split(",")[2];
 			//修改后内容
  			var need12="'"+a12+"'"+":"+xiu12;
  			if(iteme==iteme12){
  				need=need+","+need12;
 				 var deletezu= stop+","+a12+","+xiu12;
 				q12zu.splice(q12zu.contains(''+deletezu),1)
  			
		}

  		}	
  			}	
  		

  		need=need+"},"+"'itemCode':"+"'"+iteme+"'"+"}"
  		
  		array.push(need);
  		
  		
  		}	
  		}
  		

  		
  		
  		
  	/**
  	 * 2组
  	 */
  		if(q2zu !=null){
  	  		for(var i=0;i<q2zu.length-1;i++){
  	  			
  	  			
  	  			
  	  			
  	  			
  	  			var stra=q2zu[i];  	  			
  	  			if (stra.substr(0,1)==','){
  	  				stra=stra.substr(1);
  	  				var stop= stra.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= stra.split(",")[0];
  	  			}
  	  			
  	  			//华为物料编号
  	  			var iteme= stra.substring(1, stop.length) ;
  	  			//标题时间
  	  			 var a= stra.split(",")[1];
  	  			var xiu=stra.split(",")[2];
  	  			var need="{"+"'data':{"+ "'"+a+"'"+":"+xiu;
  	  			

  	  		
  	  	//3组
  	  		if(q3zu !=null){
  	  		for(var q=0;q<q3zu.length-1;q++){
  	  			var strb=q3zu[q];
  	  			
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme2= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a2= strb.split(",")[1];
  	  			var xiu2=strb.split(",")[2];
  	  			var need3="'"+a2+"'"+":"+xiu2;
  	 			//修改后内容
  	  			if(iteme==iteme2){
  	  				need=need+","+need3;
  	  			var deletezu= stop+","+a2+","+xiu2;
 				q3zu.splice(q3zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  		//4组
  	  		if(q4zu !=null){
  	  		for(var w=0;w<q4zu.length-1;w++){
  	  			var strb=q4zu[w];
  	  			
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme4= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a4= strb.split(",")[1];
  	  			var xiu4=strb.split(",")[2];
  	  			var need4="'"+a4+"'"+":"+xiu4;
  	 			//修改后内容
  	  			if(iteme==iteme4){
  	  				need=need+","+need4;
  	  			var deletezu= stop+","+a4+","+xiu4;
 				q4zu.splice(q4zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  		
  	  		//5组
  	  		if(q5zu !=null){
  	  		for(var e=0;e<q5zu.length-1;e++){
  	  			var strb=q5zu[e];
  	  			
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme5= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a5= strb.split(",")[1];
  	  			var xiu5=strb.split(",")[2];
  	 			//修改后内容
  	  			var need5="'"+a5+"'"+":"+xiu5;
  	  			if(iteme==iteme5){
  	  				need=need+","+need5;
  	  			var deletezu= stop+","+a5+","+xiu5;
 				q5zu.splice(q5zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  		
  	  		//6组
  	  		if(q6zu !=null){
  	  		for(var r=0;r<q6zu.length-1;r++){
  	  			var strb=q6zu[r];

  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme6= strb.substring(0, stop.length) ;	
  	  			
  	  			//标题时间
  	  			 var a6= strb.split(",")[1];
  	  			var xiu6=strb.split(",")[2];
  	 			//修改后内容
  	  			var need6="'"+a6+"'"+":"+xiu6;
  	  			if(iteme==iteme6){
  	  				need=need+","+need6;
  	  			var deletezu= stop+","+a6+","+xiu6;
 				q6zu.splice(q6zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  		
  	  		//7组
  	  		if(q7zu !=null){
  	  		for(var t=0;t<q7zu.length-1;t++){
  	  			var strb=q7zu[t];
  	  		
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme7= strb.substring(0, stop.length) ;
  	  			
  	  			//标题时间
  	  			 var a7= strb.split(",")[1];
  	  			var xiu7=strb.split(",")[2];
  	 			//修改后内容
  	  			var need7="'"+a7+"'"+":"+xiu7;
  	  			if(iteme==iteme7){
  	  				need=need+","+need7;
  	  			var deletezu= stop+","+a7+","+xiu7;
 				q7zu.splice(q7zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  		
  	  		
  	  			
  	  		if(q8zu !=null){
  	  		for(var y=0;y<q8zu.length-1;y++){
  	  			var strb=q8zu[y];
  	  		
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strab.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme8= strb.substring(0, stop.length) ;
  	  			
  	  			//标题时间
  	  			 var a8= strb.split(",")[1];
  	  			var xiu8=strb.split(",")[2];
  	 			//修改后内容
  	  			var need8="'"+a8+"'"+":"+xiu8;
  	  			if(iteme==iteme8){
  	  				need=need+","+need8;
  	  			var deletezu= stop+","+a8+","+xiu8;
 				q8zu.splice(q8zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  			
  	  		if(q9zu !=null){
  	  		for(var u=0;u<q9zu.length-1;u++){
  	  			var strb=q9zu[u];
  	  			
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme9= strb.substring(0, stop.length) ;
  	  			
  	  			//标题时间
  	  			 var a9= strb.split(",")[1];
  	  			var xiu9=strb.split(",")[2];
  	 			//修改后内容
  	  			var need9="'"+a9+"'"+":"+xiu9;
  	  			if(iteme==iteme9){
  	  				need=need+","+need9;
  	  			var deletezu= stop+","+a9+","+xiu9;
 				q9zu.splice(q9zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  		
  	  			}	
  	  		
  	  		
  	  		
  	  		if(q10zu !=null){
  	  		for(var o=0;o<q10zu.lengt-1;o++){
  	  			var strb=q10zu[o];
  	  	
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme10= strb.substring(0, stop.length) ;
  	  			
  	  			//标题时间
  	  			 var a10= strb.split(",")[1];
  	  			var xiu10=strb.split(",")[2];
  	 			//修改后内容
  	  			var need10="'"+a10+"'"+":"+xiu10;
  	  			if(iteme==iteme10){
  	  				need=need+","+need10;
  	  			var deletezu= stop+","+a10+","+xiu10;
 				q10zu.splice(q10zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		if(q11zu !=null){
  	  		for(var a=0;a<q11zu.length-1;a++){
  	  			var strb=q11zu[a];
  	  	
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme11= strb.substring(0, stop.length) ;
  	  			
  	  			//标题时间
  	  			 var a11= strb.split(",")[1];
  	  			var xiu11=strb.split(",")[2];
  	 			//修改后内容
  	  			var need11="'"+a11+"'"+":"+xiu11;
  	  			if(iteme==iteme11){
  	  				need=need+","+need11;
  	  	  			var deletezu= stop+","+a11+","+xiu11;
  	 				q11zu.splice(q11zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		
  	  		
  	  		
  	  		if(q12zu !=null){
  	  		for(var s=0;s<q12zu.length-1;s++){
  	  			var strb=q12zu[s];
  	  			
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme12= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a12= strb.split(",")[1];
  	  			var xiu12=strb.split(",")[2];
  	 			//修改后内容
  	  			var need12="'"+a12+"'"+":"+xiu12;
  	  			if(iteme==iteme12){
  	  				need=need+","+need12;
  	  	  			var deletezu= stop+","+a12+","+xiu12;
  	 				q12zu.splice(q12zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		need=need+"}"+"'itemCode':"+"'"+iteme+"'" +"}"
  	  		
  	  		array.push(need);
  	  		
  	  		}	
  	  		
  	  		}
  		
  		
  		/**
  		 * 三组
  		 */
  		if(q3zu !=null){
  	  		for(var i=0;i<q3zu.length-1;i++){
  	  			
  	  			var stra=q3zu[i];
  	  			if (stra.substr(0,1)==','){
  	  				stra=stra.substr(1);
  	  				var stop= stra.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= stra.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme= stra.substring(1, stop.length) ;
  	  			//标题时间
  	  			 var a= stra.split(",")[1];
  	  			var xiu=stra.split(",")[2];
  	  			var need="{"+"'data':{"+ "'"+a+"'"+":"+xiu;
  	  			

  	  		

  	  		
  	  		//4组
  	  		if(q4zu !=null){
  	  		for(var w=0;w<q4zu.length-1;w++){
  	  			var strb=q4zu[w];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme4= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a4= strb.split(",")[1];
  	  			var xiu4=strb.split(",")[2];
  	  			var need4="'"+a4+"'"+":"+xiu4;
  	 			//修改后内容
  	  			if(iteme==iteme4){
  	  				need=need+","+need4;
  	  	  			var deletezu= stop+","+a4+","+xiu4;
  	 				q4zu.splice(q4zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  		
  	  		//5组
  	  		if(q5zu !=null){
  	  		for(var e=0;e<q5zu.length-1;e++){
  	  			var strb=q5zu[e];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme5= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a5= strb.split(",")[1];
  	  			var xiu5=strb.split(",")[2];
  	 			//修改后内容
  	  			var need5="'"+a5+"'"+":"+xiu5;
  	  			if(iteme==iteme5){
  	  				need=need+","+need5;
  	  	  			var deletezu= stop+","+a5+","+xiu5;
  	 				q5zu.splice(q5zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  		
  	  		//6组
  	  		if(q6zu !=null){
  	  		for(var r=0;r<q6zu.length-1;r++){
  	  			var strb=q6zu[r];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme6= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a6= strb.split(",")[1];
  	  			var xiu6=strb.split(",")[2];
  	 			//修改后内容
  	  			var need6="'"+a6+"'"+":"+xiu6;
  	  			if(iteme==iteme6){
  	  				need=need+","+need6;
  	  	  			var deletezu= stop+","+a6+","+xiu6;
  	 				q6zu.splice(q6zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  		
  	  		//7组
  	  		if(q7zu !=null){
  	  		for(var t=0;t<q7zu.length-1;t++){
  	  			var strb=q7zu[t];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme7= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a7= strb.split(",")[1];
  	  			var xiu7=strb.split(",")[2];
  	 			//修改后内容
  	  			var need7="'"+a7+"'"+":"+xiu7;
  	  			if(iteme==iteme7){
  	  				need=need+","+need7;
  	  	  			var deletezu= stop+","+a7+","+xiu7;
  	 				q7zu.splice(q7zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  		
  	  		
  	  			
  	  		if(q8zu !=null){
  	  		for(var y=0;y<q8zu.length-1;y++){
  	  			var strb=q8zu[y];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme8= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a8= strb.split(",")[1];
  	  			var xiu8=strb.split(",")[2];
  	 			//修改后内容
  	  			var need8="'"+a8+"'"+":"+xiu8;
  	  			if(iteme==iteme8){
  	  				need=need+","+need8;
  	  	  			var deletezu= stop+","+a8+","+xiu8;
  	 				q8zu.splice(q8zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  			
  	  		if(q9zu !=null){
  	  		for(var u=0;u<q9zu.length-1;u++){
  	  			var strb=q9zu[u];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme9= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a9= strb.split(",")[1];
  	  			var xiu9=strb.split(",")[2];
  	 			//修改后内容
  	  			var need9="'"+a9+"'"+":"+xiu9;
  	  			if(iteme==iteme9){
  	  				need=need+","+need9;
  	  	  			var deletezu= stop+","+a9+","+xiu9;
  	 				q9zu.splice(q9zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  		
  	  			}	
  	  		
  	  		
  	  		
  	  		if(q10zu !=null){
  	  		for(var o=0;o<q10zu.length-1;o++){
  	  			var strb=q10zu[o];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme10= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a10= strb.split(",")[1];
  	  			var xiu10=strb.split(",")[2];
  	 			//修改后内容
  	  			var need10="'"+a10+"'"+":"+xiu10;
  	  			if(iteme==iteme10){
  	  				need=need+","+need10;
  	  	  			var deletezu= stop+","+a10+","+xiu10;
  	 				q10zu.splice(q10zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		if(q11zu !=null){
  	  		for(var a=0;a<q11zu.length-1;a++){
  	  			var strb=q11zu[a];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme11= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a11= strb.split(",")[1];
  	  			var xiu11=strb.split(",")[2];
  	 			//修改后内容
  	  			var need11="'"+a11+"'"+":"+xiu11;
  	  			if(iteme==iteme11){
  	  				need=need+","+need11;
  	  	  			var deletezu= stop+","+a11+","+xiu11;
  	 				q11zu.splice(q11zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		
  	  		
  	  		
  	  		if(q12zu !=null){
  	  		for(var s=0;s<q12zu.length-1;s++){
  	  			var strb=q12zu[s];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme12= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a12= strb.split(",")[1];
  	  			var xiu12=strb.split(",")[2];
  	 			//修改后内容
  	  			var need12="'"+a12+"'"+":"+xiu12;
  	  			if(iteme==iteme1){
  	  				need=need+","+need12;
  	  	  			var deletezu= stop+","+a12+","+xiu12;
  	 				q12zu.splice(q12zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		need=need+"}"+"'itemCode':"+"'"+iteme+"'" +"}"
  	  		
  	  		array.push(need);
  	  			
  	  		
  	  		}	
  	  		}
  		
  		
  		
  		
  		
  		/**
  		 * 四组
  		 */
  		if(q4zu !=null){
  	  		for(var i=0;i<q4zu.length-1;i++){
  	  			
  	  			var stra=q4zu[i];

  	  			if (stra.substr(0,1)==','){
  	  				stra=stra.substr(1);
  	  				var stop= stra.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= stra.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme= stra.substring(1, stop.length) ;
  	  			//标题时间
  	  			 var a= stra.split(",")[1];
  	  			var xiu=stra.split(",")[2];
  	  			var need="{"+"'data':{"+ "'"+a+"'"+":"+xiu;
  	  			

  	  		
  	  		//5组
  	  		if(q5zu !=null){
  	  		for(var e=0;e<q5zu.length-1;e++){
  	  			var strb=q5zu[e];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme5= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a5= strb.split(",")[1];
  	  			var xiu5=strb.split(",")[2];
  	 			//修改后内容
  	  			var need5="'"+a5+"'"+":"+xiu5;
  	  			if(iteme==iteme5){
  	  				need=need+","+need5;
  	  	  			var deletezu= stop+","+a5+","+xiu5;
  	 				q5zu.splice(q5zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  		
  	  		//6组
  	  		if(q6zu !=null){
  	  		for(var r=0;r<q6zu.length-1;r++){
  	  			var strb=q6zu[r];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme6= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a6= strb.split(",")[1];
  	  			var xiu6=strb.split(",")[2];
  	 			//修改后内容
  	  			var need6="'"+a6+"'"+":"+xiu6;
  	  			if(iteme==iteme6){
  	  				need=need+","+need6;
  	  	  			var deletezu= stop+","+a6+","+xiu6;
  	 				q6zu.splice(q6zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  		
  	  		//7组
  	  		if(q7zu !=null){
  	  		for(var t=0;t<q7zu.length-1;t++){
  	  			var strb=q7zu[t];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme7= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a7= strb.split(",")[1];
  	  			var xiu7=strb.split(",")[2];
  	 			//修改后内容
  	  			var need7="'"+a7+"'"+":"+xiu7;
  	  			if(iteme==iteme7){
  	  				need=need+","+need7;
  	  	  			var deletezu= stop+","+a7+","+xiu7;
  	 				q7zu.splice(q7zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  		
  	  		
  	  			
  	  		if(q8zu !=null){
  	  		for(var y=0;y<q8zu.length-1;y++){
  	  			var strb=q8zu[y];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme8= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a8= strb.split(",")[1];
  	  			var xiu8=strb.split(",")[2];
  	 			//修改后内容
  	  			var need8="'"+a8+"'"+":"+xiu8;
  	  			if(iteme==iteme8){
  	  				need=need+","+need8;
  	  	  			var deletezu= stop+","+a8+","+xiu8;
  	 				q8zu.splice(q8zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  			
  	  		if(q9zu !=null){
  	  		for(var u=0;u<q9zu.length-1;u++){
  	  			var strb=q9zu[u];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme9= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a9= strb.split(",")[1];
  	  			var xiu9=strb.split(",")[2];
  	 			//修改后内容
  	  			var need9="'"+a9+"'"+":"+xiu9;
  	  			if(iteme==iteme9){
  	  				need=need+","+need9;
  	  	  			var deletezu= stop+","+a9+","+xiu9;
  	 				q9zu.splice(q9zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  		
  	  			}	
  	  		
  	  		
  	  		
  	  		if(q10zu !=null){
  	  		for(var o=0;o<q10zu.length-1;o++){
  	  			var strb=q10zu[o];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme10= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a10= strb.split(",")[1];
  	  			var xiu10=strb.split(",")[2];
  	 			//修改后内容
  	  			var need10="'"+a10+"'"+":"+xiu10;
  	  			if(iteme==iteme10){
  	  				need=need+","+need10;
  	  	  			var deletezu= stop+","+a10+","+xiu10;
  	 				q10zu.splice(q10zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		if(q11zu !=null){
  	  		for(var a=0;a<q11zu.length-1;a++){
  	  			var strb=q11zu[a];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme11= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a11= strb.split(",")[1];
  	  			var xiu11=strb.split(",")[2];
  	 			//修改后内容
  	  			var need11="'"+a11+"'"+":"+xiu11;
  	  			if(iteme==iteme11){
  	  				need=need+","+need11;
  	  	  			var deletezu= stop+","+a11+","+xiu11;
  	 				q11zu.splice(q11zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		
  	  		
  	  		
  	  		if(q12zu !=null){
  	  		for(var s=0;s<q12zu.length-1;s++){
  	  			var strb=q12zu[s];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme12= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a12= strb.split(",")[1];
  	  			var xiu12=strb.split(",")[2];
  	 			//修改后内容
  	  			var need12="'"+a12+"'"+":"+xiu12;
  	  			if(iteme==iteme12){
  	  				need=need+","+need12;
  	  	  			var deletezu= stop+","+a12+","+xiu12;
  	 				q12zu.splice(q12zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		need=need+"}"+"'itemCode':"+"'"+iteme+"'" +"}"
  	  		
  	  		array.push(need);
  	  			
  	  		
  	  		}	
  	  		}

  		
  		/**
  		 * 五组
  		 */
  		if(q5zu !=null){
  	  		for(var i=0;i<q5zu.length-1;i++){
  	  			
  	  			var stra=q5zu[i];
  	  			if (stra.substr(0,1)==','){
  	  				stra=stra.substr(1);
  	  				var stop= stra.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= stra.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme= stra.substring(1, stop.length) ;
  	  			//标题时间
  	  			 var a= stra.split(",")[1];
  	  			var xiu=stra.split(",")[2];
  	  			var need="{"+"'data':{"+ "'"+a+"'"+":"+xiu;
  	  			

  	  		
  	  		
  	  		//6组
  	  		if(q6zu !=null){
  	  		for(var r=0;r<q6zu.length-1;r++){
  	  			var strb=q6zu[r];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme6= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a6= strb.split(",")[1];
  	  			var xiu6=strb.split(",")[2];
  	 			//修改后内容
  	  			var need6="'"+a6+"'"+":"+xiu6;
  	  			if(iteme==iteme6){
  	  				need=need+","+need6;
  	  	  			var deletezu= stop+","+a6+","+xiu6;
  	 				q6zu.splice(q6zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  		
  	  		//7组
  	  		if(q7zu !=null){
  	  		for(var t=0;t<q7zu.length-1;t++){
  	  			var strb=q7zu[t];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme7= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a7= strb.split(",")[1];
  	  			var xiu7=strb.split(",")[2];
  	 			//修改后内容
  	  			var need7="'"+a7+"'"+":"+xiu7;
  	  			if(iteme==iteme7){
  	  				need=need+","+need7;
  	  	  			var deletezu= stop+","+a7+","+xiu7;
  	 				q7zu.splice(q7zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  		
  	  		
  	  			
  	  		if(q8zu !=null){
  	  		for(var y=0;y<q8zu.length-1;y++){
  	  			var strb=q8zu[y];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme8= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a8= strb.split(",")[1];
  	  			var xiu8=strb.split(",")[2];
  	 			//修改后内容
  	  			var need8="'"+a8+"'"+":"+xiu8;
  	  			if(iteme==iteme8){
  	  				need=need+","+need8;
  	  	  			var deletezu= stop+","+a8+","+xiu8;
  	 				q8zu.splice(q8zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  			
  	  		if(q9zu !=null){
  	  		for(var u=0;u<q9zu.length-1;u++){
  	  			var strb=q9zu[u];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme9= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a9= strb.split(",")[1];
  	  			var xiu9=strb.split(",")[2];
  	 			//修改后内容
  	  			var need9="'"+a9+"'"+":"+xiu9;
  	  			if(iteme==iteme9){
  	  				need=need+","+need9;
  	  	  			var deletezu= stop+","+a9+","+xiu9;
  	 				q9zu.splice(q9zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  		
  	  			}	
  	  		
  	  		
  	  		
  	  		if(q10zu !=null){
  	  		for(var o=0;o<q10zu.length-1;o++){
  	  			var strb=q10zu[o];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme10= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a10= strb.split(",")[1];
  	  			var xiu10=strb.split(",")[2];
  	 			//修改后内容
  	  			var need10="'"+a10+"'"+":"+xiu10;
  	  			if(iteme==iteme10){
  	  				need=need+","+need10;
  	  	  			var deletezu= stop+","+a10+","+xiu10;
  	 				q10zu.splice(q10zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		if(q11zu !=null){
  	  		for(var a=0;a<q11zu.length-1;a++){
  	  			var strb=q11zu[a];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme11= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a11= strb.split(",")[1];
  	  			var xiu11=strb.split(",")[2];
  	 			//修改后内容
  	  			var need11="'"+a11+"'"+":"+xiu11;
  	  			if(iteme==iteme11){
  	  				need=need+","+need11;
  	  	  			var deletezu= stop+","+a11+","+xiu11;
  	 				q11zu.splice(q11zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		
  	  		
  	  		
  	  		if(q12zu !=null){
  	  		for(var s=0;s<q12zu.length-1;s++){
  	  			var strb=q12zu[s];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme12= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a12= strb.split(",")[1];
  	  			var xiu12=strb.split(",")[2];
  	 			//修改后内容
  	  			var need12="'"+a12+"'"+":"+xiu12;
  	  			if(iteme==iteme12){
  	  				need=need+","+need12;
  	  	  			var deletezu= stop+","+a12+","+xiu12;
  	 				q12zu.splice(q12zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		need=need+"}"+"'itemCode':"+"'"+iteme+"'" +"}"
  	  		
  	  		array.push(need);
  	  			
  	  		
  	  		}	
  	  		}

  		
  		
  		
  		
  		
  		
  		/**
  		 * 六组
  		 */
  		if(q6zu !=null){
  	  		for(var i=0;i<q6zu.length-1;i++){
  	  			
  	  			var stra=q5zu[i];
  	  			if (stra.substr(0,1)==','){
  	  				stra=stra.substr(1);
  	  				var stop= stra.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= stra.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme= stra.substring(1, stop.length) ;
  	  			//标题时间
  	  			 var a= stra.split(",")[1];
  	  			var xiu=stra.split(",")[2];
  	  			var need="{"+"'data'{"+"'"+ a+"'"+":"+xiu;
  	  			


  	  		
  	  		//7组
  	  		if(q7zu !=null){
  	  		for(var t=0;t<q7zu.length-1;t++){
  	  			var strb=q7zu[t];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme7= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a7= strb.split(",")[1];
  	  			var xiu7=strb.split(",")[2];
  	 			//修改后内容
  	  			var need7="'"+a7+"'"+":"+xiu7;
  	  			if(iteme==iteme7){
  	  				need=need+","+need7;
  	  	  			var deletezu= stop+","+a7+","+xiu7;
  	 				q7zu.splice(q7zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  		
  	  		
  	  			
  	  		if(q8zu !=null){
  	  		for(var y=0;y<q8zu.length-1;y++){
  	  			var strb=q8zu[y];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme8= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a8= strb.split(",")[1];
  	  			var xiu8=strb.split(",")[2];
  	 			//修改后内容
  	  			var need8="'"+a8+"'"+":"+xiu8;
  	  			if(iteme==iteme8){
  	  				need=need+","+need8;
  	  	  			var deletezu= stop+","+a8+","+xiu8;
  	 				q8zu.splice(q8zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  			
  	  		if(q9zu !=null){
  	  		for(var u=0;u<q9zu.length-1;u++){
  	  			var strb=q9zu[u];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme9= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a9= strb.split(",")[1];
  	  			var xiu9=strb.split(",")[2];
  	 			//修改后内容
  	  			var need9="'"+a9+"'"+":"+xiu9;
  	  			if(iteme==iteme9){
  	  				need=need+","+need9;
  	  	  			var deletezu= stop+","+a9+","+xiu9;
  	 				q9zu.splice(q9zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  		
  	  			}	
  	  		
  	  		
  	  		
  	  		if(q10zu !=null){
  	  		for(var o=0;o<q10zu.length-1;o++){
  	  			var strb=q10zu[o];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme10= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a10= strb.split(",")[1];
  	  			var xiu10=strb.split(",")[2];
  	 			//修改后内容
  	  			var need10="'"+a10+"'"+":"+xiu10;
  	  			if(iteme==iteme10){
  	  				need=need+","+need10;
  	  	  			var deletezu= stop+","+a10+","+xiu10;
  	 				q10zu.splice(q10zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		if(q11zu !=null){
  	  		for(var a=0;a<q11zu.length;a++){
  	  			var strb=q11zu[a];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme11= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a11= strb.split(",")[1];
  	  			var xiu11=strb.split(",")[2];
  	 			//修改后内容
  	  			var need11="'"+a11+"'"+":"+xiu11;
  	  			if(iteme==iteme11){
  	  				need=need+","+need11;
  	  	  			var deletezu= stop+","+a11+","+xiu11;
  	 				q11zu.splice(q11zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		
  	  		
  	  		
  	  		if(q12zu !=null){
  	  		for(var s=0;s<q12zu.length-1;s++){
  	  			var strb=q12zu[s];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme12= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a12= strb.split(",")[1];
  	  			var xiu12=strb.split(",")[2];
  	 			//修改后内容
  	  			var need12="'"+a12+"'"+":"+xiu12;
  	  			if(iteme==iteme1){
  	  				need=need+","+need12;
  	  	  			var deletezu= stop+","+a12+","+xiu12;
  	 				q12zu.splice(q12zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		need=need+"}"+"'itemCode':"+"'"+iteme+"'" +"}"
  	  		
  	  		array.push(need);
  	  			
  	  		
  	  		}	
  	  		}

  	
  		
  		/**
  		 * 七组
  		 */
  		if(q7zu !=null){
  	  		for(var i=0;i<q7zu.length-1;i++){
  	  			
  	  			var stra=q7zu[i];
  	  			if (stra.substr(0,1)==','){
  	  				stra=stra.substr(1);
  	  				var stop= stra.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= stra.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme= stra.substring(1, stop.length) ;
  	  			//标题时间
  	  			 var a= stra.split(",")[1];
  	  			var xiu=stra.split(",")[2];
  	  			var need="{"+"'data'{"+"'"+ a+"'"+":"+xiu;
  	  				
  	  		
  	  			
  	  		if(q8zu !=null){
  	  		for(var y=0;y<q8zu.length-1;y++){
  	  			var strb=q8zu[y];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme8= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a8= strb.split(",")[1];
  	  			var xiu8=strb.split(",")[2];
  	 			//修改后内容
  	  			var need8="'"+a18+"'"+":"+xiu8;
  	  			if(iteme==iteme8){
  	  				need=need+","+need8;
  	  	  			var deletezu= stop+","+a8+","+xiu8;
  	 				q8zu.splice(q8zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}
  	  		
  	  			
  	  		if(q9zu !=null){
  	  		for(var u=0;u<q9zu.length-1;u++){
  	  			var strb=q9zu[u];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme9= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a9= strb.split(",")[1];
  	  			var xiu9=strb.split(",")[2];
  	 			//修改后内容
  	  			var need9="'"+a9+"'"+":"+xiu9;
  	  			if(iteme==iteme9){
  	  				need=need+","+need9;
  	  	  			var deletezu= stop+","+a9+","+xiu9;
  	 				q9zu.splice(q9zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  		
  	  			}	
  	  		
  	  		
  	  		
  	  		if(q10zu !=null){
  	  		for(var o=0;o<q10zu.length-1;o++){
  	  			var strb=q10zu[o];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme10= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a10= strb.split(",")[1];
  	  			var xiu10=strb.split(",")[2];
  	 			//修改后内容
  	  			var need10="'"+a10+"'"+":"+xiu10;
  	  			if(iteme==iteme10){
  	  				need=need+","+need10;
  	  	  			var deletezu= stop+","+a10+","+xiu10;
  	 				q10zu.splice(q10zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		if(q11zu !=null){
  	  		for(var a=0;a<q11zu.length-1;a++){
  	  			var strb=q11zu[a];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme11= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a11= strb.split(",")[1];
  	  			var xiu11=strb.split(",")[2];
  	 			//修改后内容
  	  			var need11="'"+a11+"'"+":"+xiu11;
  	  			if(iteme==iteme11){
  	  				need=need+","+need11;
  	  	  			var deletezu= stop+","+a11+","+xiu11;
  	 				q11zu.splice(q11zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		
  	  		
  	  		
  	  		if(q12zu !=null){
  	  		for(var s=0;s<q12zu.length;s++){
  	  			var strb=q12zu[s];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme12= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a12= strb.split(",")[1];
  	  			var xiu12=strb.split(",")[2];
  	 			//修改后内容
  	  			var need12="'"+a12+"'"+":"+xiu12;
  	  			if(iteme==iteme1){
  	  				need=need+","+need12;
  	  	  			var deletezu= stop+","+a12+","+xiu12;
  	 				q12zu.splice(q12zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		need=need+"}"+"'itemCode':"+"'"+iteme+"'" +"}"
  	  		
  	  		array.push(need);
  	  			
  	  		
  	  		}	
  	  		}

  		
  		
  		
 		/**
  		 * 八组
  		 */
  		if(q8zu !=null){
  	  		for(var i=0;i<q8zu.length-1;i++){
  	  			
  	  			var stra=q8zu[i];
  	  			//华为物料编号
  	  			if (stra.substr(0,1)==','){
  	  				stra=stra.substr(1);
  	  				var stop= stra.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= stra.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme= strb.substring(1, stop.length) ;
  	  			//标题时间
  	  			 var a= stra.split(",")[1];
  	  			var xiu=stra.split(",")[2];
  	  			var need="{"+"'data'{"+ "'"+a+"'"+":"+xiu;
  	  				
  	  		

  	  			
  	  		if(q9zu !=null){
  	  		for(var u=0;u<q9zu.length-1;u++){
  	  			var strb=q9zu[u];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme9= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a9= strb.split(",")[1];
  	  			var xiu9=strb.split(",")[2];
  	 			//修改后内容
  	  			var need9="'"+a9+"'"+":"+xiu9;
  	  			if(iteme==iteme9){
  	  				need=need+","+need9;
  	  	  			var deletezu= stop+","+a9+","+xiu9;
  	 				q9zu.splice(q9zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  		
  	  			}	
  	  		
  	  		
  	  		
  	  		if(q10zu !=null){
  	  		for(var o=0;o<q10zu.length-1;o++){
  	  			var strb=q10zu[o];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme10= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a10= strb.split(",")[1];
  	  			var xiu10=strb.split(",")[2];
  	 			//修改后内容
  	  			var need10="'"+a10+"'"+":"+xiu10;
  	  			if(iteme==iteme10){
  	  				need=need+","+need10;
  	  	  			var deletezu= stop+","+a10+","+xiu10;
  	 				q10zu.splice(q10zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		if(q11zu !=null){
  	  		for(var a=0;a<q11zu.length-1;a++){
  	  			var strb=q11zu[a];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme11= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a11= strb.split(",")[1];
  	  			var xiu11=strb.split(",")[2];
  	 			//修改后内容
  	  			var need11="'"+a11+"'"+":"+xiu11;
  	  			if(iteme==iteme11){
  	  				need=need+","+need11;
  	  	  			var deletezu= stop+","+a11+","+xiu11;
  	 				q11zu.splice(q11zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		
  	  		
  	  		
  	  		if(q12zu !=null){
  	  		for(var s=0;s<q12zu.length-1;s++){
  	  			var strb=q12zu[s];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme12= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a12= strb.split(",")[1];
  	  			var xiu12=strb.split(",")[2];
  	 			//修改后内容
  	  			var need12="'"+a12+"'"+":"+xiu12;
  	  			if(iteme==iteme1){
  	  				need=need+","+need12;
  	  	  			var deletezu= stop+","+a12+","+xiu12;
  	 				q12zu.splice(q12zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		need=need+"}"+"'itemCode':"+"'"+iteme+"'" +"}"
  	  		
  	  		array.push(need);
  	  			
  	  		
  	  		}	
  	  		}
  		
  		/**
  		 * 九组
  		 */
  		if(q9zu !=null){
  	  		for(var i=0;i<q9zu.length-1;i++){
  	  			
  	  			var stra=q9zu[i];
  	  			//华为物料编号
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme= strb.substring(1, stop.length) ;
  	  			//标题时间
  	  			 var a= stra.split(",")[1];
  	  			var xiu=stra.split(",")[2];
  	  			var need="{"+"'data'{"+"'"+ a+"'"+":"+xiu;
  	  				
  	  		


  	  		
  	  		
  	  		
  	  		if(q10zu !=null){
  	  		for(var o=0;o<q10zu.length-1;o++){
  	  			var strb=q10zu[o];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];	
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme10= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a10= strb.split(",")[1];
  	  			var xiu10=strb.split(",")[2];
  	 			//修改后内容
  	  			var need10="'"+a10+"'"+":"+xiu10;
  	  			if(iteme==iteme10){
  	  				need=need+","+need10;
  	  	  			var deletezu= stop+","+a10+","+xiu10;
  	 				q10zu.splice(q10zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		if(q11zu !=null){
  	  		for(var a=0;a<q11zu.length-1;a++){
  	  			var strb=q11zu[a];

  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme11= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a11= strb.split(",")[1];
  	  			var xiu11=strb.split(",")[2];
  	 			//修改后内容
  	  			var need11="'"+a11+"'"+":"+xiu11;
  	  			if(iteme==iteme11){
  	  				need=need+","+need11;
  	  	  			var deletezu= stop+","+a11+","+xiu11;
  	 				q11zu.splice(q11zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		
  	  		
  	  		
  	  		if(q12zu !=null){
  	  		for(var s=0;s<q12zu.length-1;s++){
  	  			var strb=q12zu[s];
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme12= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a12= strb.split(",")[1];
  	  			var xiu12=strb.split(",")[2];
  	 			//修改后内容
  	  			var need12="'"+a12+"'"+":"+xiu12;
  	  			if(iteme==iteme12){
  	  				need=need+","+need12;
  	  	  			var deletezu= stop+","+a12+","+xiu12;
  	 				q12zu.splice(q12zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		need=need+"}"+"'itemCode':"+"'"+iteme+"'" +"}"
  	  		
  	  		array.push(need);
  	  			
  	  		
  	  		}	
  	  		}

  		
  		
  		/**
  		 * 十组
  		 */
  		if(q10zu !=null){
  	  		for(var i=0;i<q10zu.length-1;i++){
  	  			
  	  			var stra=q10zu[i];
  	  			//华为物料编号
  	  			if (stra.substr(0,1)==','){
  	  				stra=stra.substr(1);
  	  				var stop= stra.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= stra.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme4= strb.substring(1, stop.length) ;
  	  			//标题时间
  	  			 var a= stra.split(",")[1];
  	  			var xiu=stra.split(",")[2];
  	  			var need="{"+"'data'{"+ "'"+a+"'"+":"+xiu;
  	  				
  	  		


  	  		
  	  		
  	  		if(q11zu !=null){
  	  		for(var a=0;a<q11zu.length-1;a++){
  	  			var strb=q11zu[a];
  	  			//华为物料编号
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme11= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a11= strb.split(",")[1];
  	  			var xiu11=strb.split(",")[2];
  	 			//修改后内容
  	  			var need11="'"+a11+"'"+":"+xiu11;
  	  			if(iteme==iteme11){
  	  				need=need+","+need11;
  	  	  			var deletezu= stop+","+a11+","+xiu11;
  	 				q11zu.splice(q11zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		
  	  		
  	  		
  	  		if(q12zu !=null){
  	  		for(var s=0;s<q12zu.length-1;s++){
  	  			var strb=q12zu[s];
  	  			//华为物料编号
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme12= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a12= strb.split(",")[1];
  	  			var xiu12=strb.split(",")[2];
  	 			//修改后内容
  	  			var need12="'"+a12+"'"+":"+xiu12;
  	  			if(iteme==iteme12){
  	  				need=need+","+need12;
  	  	  			var deletezu= stop+","+a12+","+xiu12;
  	 				q12zu.splice(q12zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		need=need+"}"+"'itemCode':"+"'"+iteme+"'" +"}"
  	  		
  	  		array.push(need);
  	  			
  	  		
  	  		}	
  	  		}

  		
  		
  		
  		
  		/**
  		 * 11组
  		 */
  		if(q11zu !=null){
  	  		for(var i=0;i<q11zu.length-1;i++){
  	  			
  	  			var stra=q11zu[i];
  	  			if (stra.substr(0,1)==','){
  	  				stra=stra.substr(1);
  	  				var stop= stra.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= stra.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme= strb.substring(1, stop.length) ;
  	  			//标题时间
  	  			 var a= stra.split(",")[1];
  	  			var xiu=stra.split(",")[2];
  	  			var need="{"+"'data'{"+ "'"+a+"'"+":"+xiu;
  	  				

  	  		
  	  		if(q12zu !=null){
  	  		for(var s=0;s<q12zu.length-1;s++){
  	  			var strb=q12zu[s];
  	  			//华为物料编号
  	  			if (strb.substr(0,1)==','){
  	  				strb=strb.substr(1);
  	  				var stop= strb.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= strb.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme4= strb.substring(0, stop.length) ;
  	  			//标题时间
  	  			 var a12= strb.split(",")[1];
  	  			var xiu12=strb.split(",")[2];
  	 			//修改后内容
  	  			var need12="'"+a12+"'"+":"+xiu12;
  	  			if(iteme==iteme12){
  	  				need=need+","+need12;
  	  	  			var deletezu= stop+","+a12+","+xiu12;
  	 				q12zu.splice(q12zu.contains(''+deletezu),1)
  			}

  	  		}	
  	  			}	
  	  		
  	  		need=need+"}"+"'itemCode':"+"'"+iteme+"'" +"}"
  	  		
  	  		array.push(need);
  	  			
  	  		
  	  		}	
  	  		}
  		
  		
  		
  		/**
  		 * 12组
  		 */
  		if(q12zu !=null){
  	  		for(var i=0;i<q12zu.length-1;i++){
  	  			
  	  			var stra=q12zu[i];
  	  			//华为物料编号
  	  			if (strb.substr(0,1)==','){
  	  				stra=stra.substr(1);
  	  				var stop= stra.split(",")[0];
  	  				
  	  			}else{
  	  				var stop= stra.split(",")[0];
  	  			}
  	  			//华为物料编号
  	  			var iteme= strb.substring(1, stop.length) ;
  	  			//标题时间
  	  			 var a= stra.split(",")[1];
  	  			var xiu=stra.split(",")[2];
  	  			var need="{"+"data{"+ "'"+a+"'" +":"+xiu;

  	  			need=need+"}"+"'itemCode':"+"'"+iteme+"'" +"}"
  	  		
  	  			array.push(need);
  	  		}	
  	  		}
  		
  		if(array !=null){
  			var linjson=array.join();
  	 		$.ajax({
  	  		  url:"HttpRestClientForaction!huifu.action",
  	  		data:{ 
  	  			"array" :linjson
  	  			},
  	           type:'post',
  	           data:{"array":linjson},
  	           dataType:"json",	
  	           success:function(data){
  	        	   	alert(data.message);
  	            	window.location.href="System/YcB2B/yuce.jsp";
  	        		localStorage.removeItem("Q1all");
  	        		localStorage.removeItem("Q2all");
  	        		localStorage.removeItem("Q3all");
  	        		localStorage.removeItem("Q4all");
  	        		localStorage.removeItem("Q5all");
  	        		localStorage.removeItem("Q6all");
  	        		localStorage.removeItem("Q7all");
  	        		localStorage.removeItem("Q8all");
  	        		localStorage.removeItem("Q9all");
  	        		localStorage.removeItem("Q10all");
  	        		localStorage.removeItem("Q11all");
  	        		localStorage.removeItem("Q12all");
  	              },
  	              error: function (XMLHttpRequest, textStatus, errorThrown) { 
  	        	   alert("错误信息："+ XMLHttpRequest.responseText); //获取的信息即是异常中的Message 
  	        	   } 

  	  			
  	  		});
  		}else  {
  			alert("没有修改的数据");
  		}

    });
  	 
  	 
  	 
  	 
  	 
  	 
  	 
  	 
  	 
  	 
  	 
  	 
  	 
  	 
  	 
  	 
  	 
  	 

  	 
  	 $("form").submit(function () {
  		 
  	    
    	 if ($(".orgId").val()==null ||$(".orgId").val()=="" ) {
    		alert("库存组织不能为空！")	
    		return false;
    	}
    	if(($(".startTime").val()==null)  || ($(".endTime").val()==null) ||  $(".startTime").val()=="" || $(".endTime").val()==""){
    		alert("起止时间为空！")	
    		return false;
    	}

    	 
    	 if(($(".startTime").val() !=null)  && ($(".endTime").val() !=null ) && ($(".orgId").val() !=null) ){
    		var suppItemCode=$("input[name='suppItemCode']").val();
    		var itemCode=$("input[name='itemCode']").val();
    		var orgId=$("input[name='orgId']").val();
    		var startTime=$("input[name='startTime']").val();
    		var endTime=$("input[name='endTime']").val();
    		var purchaseMode=$("input[name='purchaseMode']").val();
    		var buyerName=$("input[name='buyerName']").val();
    		var version=$("input[name='version']").val();
    		$(".ifam").attr('src',"HttpRestClientForaction!selhuawei.action?suppItemCode="+suppItemCode+"&&itemCode="+itemCode+"&&orgId="+orgId+"&&startTime="+startTime+"&&endTime="+endTime+"&&purchaseMode="+purchaseMode+"&&buyerName="+buyerName+"&&version="+version);
    		
    		return true;
    	 } 
    });
  	 
  	 
     
     $("#evey  td").css({"background-color":"#EBF1FF"});
     if($(".Q1").text()>$(".gon").text()){
    	 alert($(".Q1").text());
         $(".Q1").css({"color":"red"});
     }else if ($(".Q1").text()<$(".gon").text()) {
    	 $("Q1").css({"color":"blue"});
    }else if ($(".Q1").text()==$(".gon").text()) {
    	$(".Q1").css({"color":"#EBF1FF"});
    } ;
     	

    
    
    
    
    
    
    
    
    
    
    //鼠标悬停事件
    $(".huifu").hover(function(){
    	$(".huifu").css({"cursor":"pointer"});      
    },function(){
          });
    
    //鼠标悬停事件
    $(".daochu").hover(function(){
    	$(".daochu").css({"cursor":"pointer"});      
    },function(){

    });
    
    //鼠标悬停事件
    $(".daoru").hover(function(){
    	$(".daoru").css({"cursor":"pointer"});      
    },function(){

    });
    
    //鼠标悬停事件
    $(".tian").hover(function(){
    	$(".tian").css({"cursor":"pointer"});      
    },function(){

    });
    
    
    //鼠标悬停事件
    $(".zhou").hover(function(){
    	$(".zhou").css({"cursor":"pointer"});      
    },function(){

    });
    

    
    
    
    
    
    
  	 
   
   //鼠标悬停事件
   $(".tutu").hover(function(){
   
   if($("#shou").is(':hidden')){
   $(this).attr('src',"System/YcB2B/showmo.jpg");
   }else{
    $(this).attr('src',"System/YcB2B/thide.jpg");
   }
   },function(){
    if($("#shou").is(':hidden')){
     $(this).attr('src',"System/YcB2B/show.jpg");
    }else{
      $(this).attr('src',"System/YcB2B/hide.jpg");
    }    
   });

   
   //鼠标点击事件
  $(".tutu").click(function(){
  if($("#shou").is(':hidden')){
        //如果隐藏时。。。
		 $("#shou").show();
		 $("#two").css({"width":"76%"});
		 
	}else{
	      //如果显示时。。。
	$("#shou").hide();
	$("#two").css({"width":"98%"});
	}
  });
  
	var size= $(".size").text()/3;
	if(size !=null){
		var leixing=-3;
		var xuhang=-2;
		var gonghang=-1;
		var quehang=0;
		var diyi=0;
		var dier=0;
		
		for (var int =0; int < size; int++) {  			
			leixing=leixing+4;
			//类型
			var lei= $("#qinagtao tr:eq("+leixing+") td:eq(4)").text();
		  	//需求
			xuhang=xuhang+4
		  	var xuval=$("#qinagtao tr:eq("+xuhang+") td:eq(6)").find("input").val();
			diyi=6+1;
			dier=diyi+1;
			var diyixuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+diyi+")").find("input").val()
			var dierxuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+dier+")").find("input").val()
			
			//供应
			gonghang=gonghang+4
		  	var gonval=$("#qinagtao tr:eq("+gonghang+") td:eq(6)").find("input").val();
			//缺口
			quehang=quehang+4;
			var queval=$("#qinagtao tr:eq("+quehang+") td:eq(6)").find("input").val();
			//库存
			var kucunval=$("#qinagtao tr:eq("+quehang+") td:eq(4)").text();

			if(lei !=null){
				if(xuval>0 && queval<0){
						
			  				if(lei=="DUN"){
			  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(6)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="JIT"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(6)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="Normal"){
			  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==0){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(6)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="PO-Consignmen"){
			  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(6)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="VMI-Consignment"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(6)").find("input").css('color','red');
			  					}
			  				}	
				}
				
				if(queval<diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(6)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(6)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(6)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(6)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(6)").find("input").css('color','#FF9743');
	  					}
	  				}	
				}
				if(queval>diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(6)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(6)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(6)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(6)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(6)").find("input").css('color','#1890FF');
	  					}
	  				}
				}
				
			}
	
		}
		
		
		
		var leixing=-3;
		var xuhang=-2;
		var gonghang=-1;
		var quehang=0;
		var diyi=0;
		var dier=0;
		
		for (var int =0; int < size; int++) {  			
			leixing=leixing+4;
			//类型
			var lei= $("#qinagtao tr:eq("+leixing+") td:eq(4)").text();
		  	//需求
			xuhang=xuhang+4
		  	var xuval=$("#qinagtao tr:eq("+xuhang+") td:eq(7)").find("input").val();
			diyi=7+1;
			dier=diyi+1;
			var diyixuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+diyi+")").find("input").val()
			var dierxuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+dier+")").find("input").val()
			
			//供应
			gonghang=gonghang+4
		  	var gonval=$("#qinagtao tr:eq("+gonghang+") td:eq(7)").find("input").val();
			//缺口
			quehang=quehang+4;
			var queval=$("#qinagtao tr:eq("+quehang+") td:eq(7)").find("input").val();
			//库存
			var kucunval=$("#qinagtao tr:eq("+quehang+") td:eq(4)").text();

			if(lei !=null){
				if(xuval>0 && queval<0){
						
			  				if(lei=="DUN"){
			  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(7)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="JIT"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(7)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="Normal"){
			  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==0){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(7)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="PO-Consignmen"){
			  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(7)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="VMI-Consignment"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(7)").find("input").css('color','red');
			  					}
			  				}	
				}
				
				if(queval<diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(7)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(7)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(7)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(7)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(7)").find("input").css('color','#FF9743');
	  					}
	  				}	
				}
				if(queval>diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(7)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(7)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(7)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(7)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(7)").find("input").css('color','#1890FF');
	  					}
	  				}
				}
				
			}
	
		}
		//==================================
		var leixing=-3;
		var xuhang=-2;
		var gonghang=-1;
		var quehang=0;
		var diyi=0;
		var dier=0;
		
		for (var int =0; int < size; int++) {  			
			leixing=leixing+4;
			//类型
			var lei= $("#qinagtao tr:eq("+leixing+") td:eq(4)").text();
		  	//需求
			xuhang=xuhang+4
		  	var xuval=$("#qinagtao tr:eq("+xuhang+") td:eq(8)").find("input").val();
			diyi=8+1;
			dier=diyi+1;
			var diyixuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+diyi+")").find("input").val()
			var dierxuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+dier+")").find("input").val()
			
			//供应
			gonghang=gonghang+4
		  	var gonval=$("#qinagtao tr:eq("+gonghang+") td:eq(8)").find("input").val();
			//缺口
			quehang=quehang+4;
			var queval=$("#qinagtao tr:eq("+quehang+") td:eq(8)").find("input").val();
			//库存
			var kucunval=$("#qinagtao tr:eq("+quehang+") td:eq(4)").text();

			if(lei !=null){
				if(xuval>0 && queval<0){
						
			  				if(lei=="DUN"){
			  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(8)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="JIT"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(8)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="Normal"){
			  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==0){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(8)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="PO-Consignmen"){
			  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(8)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="VMI-Consignment"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(8)").find("input").css('color','red');
			  					}
			  				}	
				}
				
				if(queval<diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(8)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(8)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(8)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(8)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(8)").find("input").css('color','#FF9743');
	  					}
	  				}	
				}
				if(queval>diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(8)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(8)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(8)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(8)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(8)").find("input").css('color','#1890FF');
	  					}
	  				}
				}
				
			}
	
		}
		//===============================
		var leixing=-3;
		var xuhang=-2;
		var gonghang=-1;
		var quehang=0;
		var diyi=0;
		var dier=0;
		
		for (var int =0; int < size; int++) {  			
			leixing=leixing+4;
			//类型
			var lei= $("#qinagtao tr:eq("+leixing+") td:eq(4)").text();
		  	//需求
			xuhang=xuhang+4
		  	var xuval=$("#qinagtao tr:eq("+xuhang+") td:eq(9)").find("input").val();
			diyi=9+1;
			dier=diyi+1;
			var diyixuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+diyi+")").find("input").val()
			var dierxuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+dier+")").find("input").val()
			
			//供应
			gonghang=gonghang+4
		  	var gonval=$("#qinagtao tr:eq("+gonghang+") td:eq(9)").find("input").val();
			//缺口
			quehang=quehang+4;
			var queval=$("#qinagtao tr:eq("+quehang+") td:eq(9)").find("input").val();
			//库存
			var kucunval=$("#qinagtao tr:eq("+quehang+") td:eq(4)").text();

			if(lei !=null){
				if(xuval>0 && queval<0){
						
			  				if(lei=="DUN"){
			  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(9)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="JIT"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(9)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="Normal"){
			  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==0){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(9)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="PO-Consignmen"){
			  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(9)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="VMI-Consignment"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(9)").find("input").css('color','red');
			  					}
			  				}	
				}
				
				if(queval<diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(9)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(9)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(9)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(9)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(9)").find("input").css('color','#FF9743');
	  					}
	  				}	
				}
				if(queval>diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(9)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(9)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(9)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(9)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(9)").find("input").css('color','#1890FF');
	  					}
	  				}
				}
				
			}
	
		}
		//===============================
		var leixing=-3;
		var xuhang=-2;
		var gonghang=-1;
		var quehang=0;
		var diyi=0;
		var dier=0;
		
		for (var int =0; int < size; int++) {  			
			leixing=leixing+4;
			//类型
			var lei= $("#qinagtao tr:eq("+leixing+") td:eq(4)").text();
		  	//需求
			xuhang=xuhang+4
		  	var xuval=$("#qinagtao tr:eq("+xuhang+") td:eq(10)").find("input").val();
			diyi=10+1;
			dier=diyi+1;
			var diyixuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+diyi+")").find("input").val()
			var dierxuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+dier+")").find("input").val()
			
			//供应
			gonghang=gonghang+4
		  	var gonval=$("#qinagtao tr:eq("+gonghang+") td:eq(10)").find("input").val();
			//缺口
			quehang=quehang+4;
			var queval=$("#qinagtao tr:eq("+quehang+") td:eq(10)").find("input").val();
			//库存
			var kucunval=$("#qinagtao tr:eq("+quehang+") td:eq(4)").text();

			if(lei !=null){
				if(xuval>0 && queval<0){
						
			  				if(lei=="DUN"){
			  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(10)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="JIT"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(10)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="Normal"){
			  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==0){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(10)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="PO-Consignmen"){
			  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(10)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="VMI-Consignment"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(10)").find("input").css('color','red');
			  					}
			  				}	
				}
				
				if(queval<diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(10)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(10)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(10)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(10)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(10)").find("input").css('color','#FF9743');
	  					}
	  				}	
				}
				if(queval>diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(10)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(10)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(10)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(10)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(10)").find("input").css('color','#1890FF');
	  					}
	  				}
				}
				
			}
	
		}
		//============================
		var leixing=-3;
		var xuhang=-2;
		var gonghang=-1;
		var quehang=0;
		var diyi=0;
		var dier=0;
		
		for (var int =0; int < size; int++) {  			
			leixing=leixing+4;
			//类型
			var lei= $("#qinagtao tr:eq("+leixing+") td:eq(4)").text();
		  	//需求
			xuhang=xuhang+4
		  	var xuval=$("#qinagtao tr:eq("+xuhang+") td:eq(11)").find("input").val();
			diyi=11+1;
			dier=diyi+1;
			var diyixuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+diyi+")").find("input").val()
			var dierxuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+dier+")").find("input").val()
			
			//供应
			gonghang=gonghang+4
		  	var gonval=$("#qinagtao tr:eq("+gonghang+") td:eq(11)").find("input").val();
			//缺口
			quehang=quehang+4;
			var queval=$("#qinagtao tr:eq("+quehang+") td:eq(11)").find("input").val();
			//库存
			var kucunval=$("#qinagtao tr:eq("+quehang+") td:eq(4)").text();

			if(lei !=null){
				if(xuval>0 && queval<0){
						
			  				if(lei=="DUN"){
			  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(11)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="JIT"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(11)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="Normal"){
			  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==0){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(11)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="PO-Consignmen"){
			  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(11)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="VMI-Consignment"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(11)").find("input").css('color','red');
			  					}
			  				}	
				}
				
				if(queval<diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(11)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(11)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(11)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(11)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(11)").find("input").css('color','#FF9743');
	  					}
	  				}	
				}
				if(queval>diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(11)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(11)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(11)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(11)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(11)").find("input").css('color','#1890FF');
	  					}
	  				}
				}
				
			}
	
		}
		//================================
		var leixing=-3;
		var xuhang=-2;
		var gonghang=-1;
		var quehang=0;
		var diyi=0;
		var dier=0;
		
		for (var int =0; int < size; int++) {  			
			leixing=leixing+4;
			//类型
			var lei= $("#qinagtao tr:eq("+leixing+") td:eq(4)").text();
		  	//需求
			xuhang=xuhang+4
		  	var xuval=$("#qinagtao tr:eq("+xuhang+") td:eq(12)").find("input").val();
			diyi=12+1;
			dier=diyi+1;
			var diyixuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+diyi+")").find("input").val()
			var dierxuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+dier+")").find("input").val()
			
			//供应
			gonghang=gonghang+4
		  	var gonval=$("#qinagtao tr:eq("+gonghang+") td:eq(12)").find("input").val();
			//缺口
			quehang=quehang+4;
			var queval=$("#qinagtao tr:eq("+quehang+") td:eq(12)").find("input").val();
			//库存
			var kucunval=$("#qinagtao tr:eq("+quehang+") td:eq(12)").text();

			if(lei !=null){
				if(xuval>0 && queval<0){
						
			  				if(lei=="DUN"){
			  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(12)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="JIT"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(12)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="Normal"){
			  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==0){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(12)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="PO-Consignmen"){
			  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(12)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="VMI-Consignment"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(12)").find("input").css('color','red');
			  					}
			  				}	
				}
				
				if(queval<diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(12)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(12)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(12)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(12)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(12)").find("input").css('color','#FF9743');
	  					}
	  				}	
				}
				if(queval>diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(12)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(12)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(12)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(12)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(12)").find("input").css('color','#1890FF');
	  					}
	  				}
				}
				
			}
	
		}
		//=======================================================
		var leixing=-3;
		var xuhang=-2;
		var gonghang=-1;
		var quehang=0;
		var diyi=0;
		var dier=0;
		
		for (var int =0; int < size; int++) {  			
			leixing=leixing+4;
			//类型
			var lei= $("#qinagtao tr:eq("+leixing+") td:eq(4)").text();
		  	//需求
			xuhang=xuhang+4
		  	var xuval=$("#qinagtao tr:eq("+xuhang+") td:eq(13)").find("input").val();
			diyi=13+1;
			dier=diyi+1;
			var diyixuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+diyi+")").find("input").val()
			var dierxuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+dier+")").find("input").val()
			
			//供应
			gonghang=gonghang+4
		  	var gonval=$("#qinagtao tr:eq("+gonghang+") td:eq(13)").find("input").val();
			//缺口
			quehang=quehang+4;
			var queval=$("#qinagtao tr:eq("+quehang+") td:eq(13)").find("input").val();
			//库存
			var kucunval=$("#qinagtao tr:eq("+quehang+") td:eq(4)").text();

			if(lei !=null){
				if(xuval>0 && queval<0){
						
			  				if(lei=="DUN"){
			  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(13)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="JIT"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(13)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="Normal"){
			  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==0){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(13)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="PO-Consignmen"){
			  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(13)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="VMI-Consignment"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(13)").find("input").css('color','red');
			  					}
			  				}	
				}
				
				if(queval<diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(13)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(13)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(13)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(13)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(13)").find("input").css('color','#FF9743');
	  					}
	  				}	
				}
				if(queval>diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(13)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(13)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(13)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(13)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(13)").find("input").css('color','#1890FF');
	  					}
	  				}
				}
				
			}
	
		}
		//=========================================================
		var leixing=-3;
		var xuhang=-2;
		var gonghang=-1;
		var quehang=0;
		var diyi=0;
		var dier=0;
		
		for (var int =0; int < size; int++) {  			
			leixing=leixing+4;
			//类型
			var lei= $("#qinagtao tr:eq("+leixing+") td:eq(4)").text();
		  	//需求
			xuhang=xuhang+4
		  	var xuval=$("#qinagtao tr:eq("+xuhang+") td:eq(14)").find("input").val();
			diyi=14+1;
			dier=diyi+1;
			var diyixuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+diyi+")").find("input").val()
			var dierxuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+dier+")").find("input").val()
			
			//供应
			gonghang=gonghang+4
		  	var gonval=$("#qinagtao tr:eq("+gonghang+") td:eq(14)").find("input").val();
			//缺口
			quehang=quehang+4;
			var queval=$("#qinagtao tr:eq("+quehang+") td:eq(14)").find("input").val();
			//库存
			var kucunval=$("#qinagtao tr:eq("+quehang+") td:eq(4)").text();

			if(lei !=null){
				if(xuval>0 && queval<0){
						
			  				if(lei=="DUN"){
			  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(14)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="JIT"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(14)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="Normal"){
			  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==0){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(14)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="PO-Consignmen"){
			  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(14)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="VMI-Consignment"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(14)").find("input").css('color','red');
			  					}
			  				}	
				}
				
				if(queval<diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(14)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(14)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(14)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(14)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(14)").find("input").css('color','#FF9743');
	  					}
	  				}	
				}
				if(queval>diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(14)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(14)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(14)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(14)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(14)").find("input").css('color','#1890FF');
	  					}
	  				}
				}
				
			}
	
		}
		
		
		
		
		var leixing=-3;
		var xuhang=-2;
		var gonghang=-1;
		var quehang=0;
		var diyi=0;
		var dier=0;
		
		for (var int =0; int < size; int++) {  			
			leixing=leixing+4;
			//类型
			var lei= $("#qinagtao tr:eq("+leixing+") td:eq(4)").text();
		  	//需求
			xuhang=xuhang+4
		  	var xuval=$("#qinagtao tr:eq("+xuhang+") td:eq(15)").find("input").val();
			diyi=15+1;
			dier=diyi+1;
			var diyixuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+diyi+")").find("input").val()
			var dierxuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+dier+")").find("input").val()
			
			//供应
			gonghang=gonghang+4
		  	var gonval=$("#qinagtao tr:eq("+gonghang+") td:eq(15)").find("input").val();
			//缺口
			quehang=quehang+4;
			var queval=$("#qinagtao tr:eq("+quehang+") td:eq(15)").find("input").val();
			//库存
			var kucunval=$("#qinagtao tr:eq("+quehang+") td:eq(4)").text();

			if(lei !=null){
				if(xuval>0 && queval<0){
						
			  				if(lei=="DUN"){
			  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(15)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="JIT"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(15)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="Normal"){
			  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==0){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(15)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="PO-Consignmen"){
			  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(15)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="VMI-Consignment"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(15)").find("input").css('color','red');
			  					}
			  				}	
				}
				
				if(queval<diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(15)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(15)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(15)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(15)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(15)").find("input").css('color','#FF9743');
	  					}
	  				}	
				}
				if(queval>diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(15)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(15)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(15)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(15)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(15)").find("input").css('color','#1890FF');
	  					}
	  				}
				}
				
			}
	
		}
	
		
		
		
		var leixing=-3;
		var xuhang=-2;
		var gonghang=-1;
		var quehang=0;
		var diyi=0;
		var dier=0;
		
		for (var int =0; int < size; int++) {  			
			leixing=leixing+4;
			//类型
			var lei= $("#qinagtao tr:eq("+leixing+") td:eq(4)").text();
		  	//需求
			xuhang=xuhang+4
		  	var xuval=$("#qinagtao tr:eq("+xuhang+") td:eq(16)").find("input").val();
			diyi=16+1;
			dier=diyi+1;
			var diyixuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+diyi+")").find("input").val()
			var dierxuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+dier+")").find("input").val()
			
			//供应
			gonghang=gonghang+4
		  	var gonval=$("#qinagtao tr:eq("+gonghang+") td:eq(16)").find("input").val();
			//缺口
			quehang=quehang+4;
			var queval=$("#qinagtao tr:eq("+quehang+") td:eq(16)").find("input").val();
			//库存
			var kucunval=$("#qinagtao tr:eq("+quehang+") td:eq(4)").text();

			if(lei !=null){
				if(xuval>0 && queval<0){
						
			  				if(lei=="DUN"){
			  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(16)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="JIT"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(16)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="Normal"){
			  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==0){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(16)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="PO-Consignmen"){
			  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(16)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="VMI-Consignment"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(16)").find("input").css('color','red');
			  					}
			  				}	
				}
				
				if(queval<diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(16)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(16)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(16)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(16)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(16)").find("input").css('color','#FF9743');
	  					}
	  				}	
				}
				if(queval>diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(16)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(16)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(16)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(16)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(16)").find("input").css('color','#1890FF');
	  					}
	  				}
				}
				
			}
	
		}
		
		
		
		
		
		
		
		var leixing=-3;
		var xuhang=-2;
		var gonghang=-1;
		var quehang=0;
		var diyi=0;
		var dier=0;
		
		for (var int =0; int < size; int++) {  			
			leixing=leixing+4;
			//类型
			var lei= $("#qinagtao tr:eq("+leixing+") td:eq(4)").text();
		  	//需求
			xuhang=xuhang+4
		  	var xuval=$("#qinagtao tr:eq("+xuhang+") td:eq(17)").find("input").val();
			diyi=17+1;
			dier=diyi+1;
			var diyixuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+diyi+")").find("input").val()
			var dierxuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+dier+")").find("input").val()
			
			//供应
			gonghang=gonghang+4
		  	var gonval=$("#qinagtao tr:eq("+gonghang+") td:eq(17)").find("input").val();
			//缺口
			quehang=quehang+4;
			var queval=$("#qinagtao tr:eq("+quehang+") td:eq(17)").find("input").val();
			//库存
			var kucunval=$("#qinagtao tr:eq("+quehang+") td:eq(4)").text();

			if(lei !=null){
				if(xuval>0 && queval<0){
						
			  				if(lei=="DUN"){
			  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(17)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="JIT"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(17)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="Normal"){
			  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==0){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(17)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="PO-Consignmen"){
			  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(17)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="VMI-Consignment"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(17)").find("input").css('color','red');
			  					}
			  				}	
				}
				
				if(queval<diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(17)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(17)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(17)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(17)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(17)").find("input").css('color','#FF9743');
	  					}
	  				}	
				}
				if(queval>diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(17)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(17)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(17)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(17)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(17)").find("input").css('color','#1890FF');
	  					}
	  				}
				}
				
			}
	
		}
		
		
		
		
		var leixing=-3;
		var xuhang=-2;
		var gonghang=-1;
		var quehang=0;
		var diyi=0;
		var dier=0;
		
		for (var int =0; int < size; int++) {  			
			leixing=leixing+4;
			//类型
			var lei= $("#qinagtao tr:eq("+leixing+") td:eq(4)").text();
		  	//需求
			xuhang=xuhang+4
		  	var xuval=$("#qinagtao tr:eq("+xuhang+") td:eq(18)").find("input").val();
			diyi=18+1;
			dier=diyi+1;
			var diyixuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+diyi+")").find("input").val()
			var dierxuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+dier+")").find("input").val()
			
			//供应
			gonghang=gonghang+4
		  	var gonval=$("#qinagtao tr:eq("+gonghang+") td:eq(18)").find("input").val();
			//缺口
			quehang=quehang+4;
			var queval=$("#qinagtao tr:eq("+quehang+") td:eq(18)").find("input").val();
			//库存
			var kucunval=$("#qinagtao tr:eq("+quehang+") td:eq(4)").text();

			if(lei !=null){
				if(xuval>0 && queval<0){
						
			  				if(lei=="DUN"){
			  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(18)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="JIT"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(18)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="Normal"){
			  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==0){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(18)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="PO-Consignmen"){
			  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(18)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="VMI-Consignment"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(18)").find("input").css('color','red');
			  					}
			  				}	
				}
				
				if(queval<diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(18)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(18)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(18)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(18)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(18)").find("input").css('color','#FF9743');
	  					}
	  				}	
				}
				if(queval>diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(18)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(18)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(18)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(18)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(18)").find("input").css('color','#1890FF');
	  					}
	  				}
				}
				
			}
	
		}
		
		
		
		var leixing=-3;
		var xuhang=-2;
		var gonghang=-1;
		var quehang=0;
		var diyi=0;
		var dier=0;
		
		for (var int =0; int < size; int++) {  			
			leixing=leixing+4;
			//类型
			var lei= $("#qinagtao tr:eq("+leixing+") td:eq(4)").text();
		  	//需求
			xuhang=xuhang+4
		  	var xuval=$("#qinagtao tr:eq("+xuhang+") td:eq(19)").find("input").val();
			diyi=19+1;
			dier=diyi+1;
			var diyixuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+diyi+")").find("input").val()
			var dierxuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+dier+")").find("input").val()
			
			//供应
			gonghang=gonghang+4
		  	var gonval=$("#qinagtao tr:eq("+gonghang+") td:eq(19)").find("input").val();
			//缺口
			quehang=quehang+4;
			var queval=$("#qinagtao tr:eq("+quehang+") td:eq(19)").find("input").val();
			//库存
			var kucunval=$("#qinagtao tr:eq("+quehang+") td:eq(4)").text();

			if(lei !=null){
				if(xuval>0 && queval<0){
						
			  				if(lei=="DUN"){
			  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(19)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="JIT"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(19)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="Normal"){
			  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==0){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(19)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="PO-Consignmen"){
			  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(19)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="VMI-Consignment"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(19)").find("input").css('color','red');
			  					}
			  				}	
				}
				
				if(queval<diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(19)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(19)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(19)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(19)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(19)").find("input").css('color','#FF9743');
	  					}
	  				}	
				}
				if(queval>diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(19)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(19)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(19)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(19)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(19)").find("input").css('color','#1890FF');
	  					}
	  				}
				}
				
			}
	
		}
		
		
		
		
		
		var leixing=-3;
		var xuhang=-2;
		var gonghang=-1;
		var quehang=0;
		var diyi=0;
		var dier=0;
		
		for (var int =0; int < size; int++) {  			
			leixing=leixing+4;
			//类型
			var lei= $("#qinagtao tr:eq("+leixing+") td:eq(4)").text();
		  	//需求
			xuhang=xuhang+4
		  	var xuval=$("#qinagtao tr:eq("+xuhang+") td:eq(20)").find("input").val();
			diyi=20+1;
			dier=diyi+1;
			var diyixuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+diyi+")").find("input").val()
			var dierxuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+dier+")").find("input").val()
			
			//供应
			gonghang=gonghang+4
		  	var gonval=$("#qinagtao tr:eq("+gonghang+") td:eq(20)").find("input").val();
			//缺口
			quehang=quehang+4;
			var queval=$("#qinagtao tr:eq("+quehang+") td:eq(20)").find("input").val();
			//库存
			var kucunval=$("#qinagtao tr:eq("+quehang+") td:eq(4)").text();

			if(lei !=null){
				if(xuval>0 && queval<0){
						
			  				if(lei=="DUN"){
			  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(20)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="JIT"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(20)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="Normal"){
			  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==0){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(20)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="PO-Consignmen"){
			  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(20)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="VMI-Consignment"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(20)").find("input").css('color','red');
			  					}
			  				}	
				}
				
				if(queval<diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(20)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(20)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(20)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(20)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(20)").find("input").css('color','#FF9743');
	  					}
	  				}	
				}
				if(queval>diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(20)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(20)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(20)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(20)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(20)").find("input").css('color','#1890FF');
	  					}
	  				}
				}
				
			}
	
		}
		
		
		
		
		var leixing=-3;
		var xuhang=-2;
		var gonghang=-1;
		var quehang=0;
		var diyi=0;
		var dier=0;
		
		for (var int =0; int < size; int++) {  			
			leixing=leixing+4;
			//类型
			var lei= $("#qinagtao tr:eq("+leixing+") td:eq(4)").text();
		  	//需求
			xuhang=xuhang+4
		  	var xuval=$("#qinagtao tr:eq("+xuhang+") td:eq(21)").find("input").val();
			diyi=21+1;
			dier=diyi+1;
			var diyixuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+diyi+")").find("input").val()
			var dierxuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+dier+")").find("input").val()
			
			//供应
			gonghang=gonghang+4
		  	var gonval=$("#qinagtao tr:eq("+gonghang+") td:eq(21)").find("input").val();
			//缺口
			quehang=quehang+4;
			var queval=$("#qinagtao tr:eq("+quehang+") td:eq(21)").find("input").val();
			//库存
			var kucunval=$("#qinagtao tr:eq("+quehang+") td:eq(4)").text();

			if(lei !=null){
				if(xuval>0 && queval<0){
						
			  				if(lei=="DUN"){
			  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(21)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="JIT"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(21)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="Normal"){
			  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==0){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(21)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="PO-Consignmen"){
			  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(21)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="VMI-Consignment"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(21)").find("input").css('color','red');
			  					}
			  				}	
				}
				
				if(queval<diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(21)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(21)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(21)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(21)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(21)").find("input").css('color','#FF9743');
	  					}
	  				}	
				}
				if(queval>diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(21)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(21)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(21)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(21)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(21)").find("input").css('color','#1890FF');
	  					}
	  				}
				}
				
			}
	
		}
		
		
		
		
		var leixing=-3;
		var xuhang=-2;
		var gonghang=-1;
		var quehang=0;
		var diyi=0;
		var dier=0;
		
		for (var int =0; int < size; int++) {  			
			leixing=leixing+4;
			//类型
			var lei= $("#qinagtao tr:eq("+leixing+") td:eq(4)").text();
		  	//需求
			xuhang=xuhang+4
		  	var xuval=$("#qinagtao tr:eq("+xuhang+") td:eq(22)").find("input").val();
			diyi=22+1;
			dier=diyi+1;
			var diyixuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+diyi+")").find("input").val()
			var dierxuval=$("#qinagtao tr:eq("+xuhang+") td:eq("+dier+")").find("input").val()
			
			//供应
			gonghang=gonghang+4
		  	var gonval=$("#qinagtao tr:eq("+gonghang+") td:eq(22)").find("input").val();
			//缺口
			quehang=quehang+4;
			var queval=$("#qinagtao tr:eq("+quehang+") td:eq(22)").find("input").val();
			//库存
			var kucunval=$("#qinagtao tr:eq("+quehang+") td:eq(4)").text();

			if(lei !=null){
				if(xuval>0 && queval<0){
						
			  				if(lei=="DUN"){
			  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(22)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="JIT"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap=kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(22)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="Normal"){
			  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==0){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(22)").find("input").css('color','red');
			  					}
			  				}
			  				
			  				if(lei=="PO-Consignmen"){
			  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(22)").find("input").css('color','red');
			  					}
			  				}
			  				if(lei=="VMI-Consignment"){
			  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
			  					var gap=(xuval+gonval)-queval;
			  					if(gap==kucunval){
			  						$("#qinagtao tr:eq("+quehang+") td:eq(22)").find("input").css('color','red');
			  					}
			  				}	
				}
				
				if(queval<diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(22)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(22)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(22)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(22)").find("input").css('color','#FF9743');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(22)").find("input").css('color','#FF9743');
	  					}
	  				}	
				}
				if(queval>diyixuval+dierxuval){
					
					if(lei=="DUN"){
	  					//  			Gap = 上周Gap + 供应 - 需求数量     计算第一周时：上周GAP = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(22)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="JIT"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量   计算第一周时，上周Gap = 供应商库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap=kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(22)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				
	  				if(lei=="Normal"){
	  					//Gap = 上周Gap + 周交货数量 - 需求数量    计算第一周时，上周Gap = 0
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==0){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(22)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="PO-Consignmen"){
	  				//Gap = 上周Gap + 周交货数量 - 需求数量   计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(22)").find("input").css('color','#1890FF');
	  					}
	  				}
	  				if(lei=="VMI-Consignment"){
	  					//Gap = 上周Gap + 供应回复数量 - 需求数量  计算第一周时，上周Gap = 预测发布时的供应商VMI库存
	  					var gap=(xuval+gonval)-queval;
	  					if(gap==kucunval){
	  						$("#qinagtao tr:eq("+quehang+") td:eq(22)").find("input").css('color','#1890FF');
	  					}
	  				}
				}
				
			}
	
		}
		
		
		
		
	
		
		
		
		
		
	}


  

	
	
	

		

  
   });



