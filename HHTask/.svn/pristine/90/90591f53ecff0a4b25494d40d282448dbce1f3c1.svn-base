<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML >
<html>
	<head>

		<title>${companyInfo.shortName}电子看板</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
	
		<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.8.3.js"></script>
		
		<script type="text/javascript">
		
		function sortArray(x, y){
			if(x.gongwei > y.gongwei){
				return 1;
			} else if(x.gongwei < y.gongwei){
				return -1;
			} else if(x.markid > y.markid){
				return 1;
			} else if(x.markid < y.markid){
				return -1;
			} else if(x.processName > y.processName){
				return 1;
			} else if(x.processName < y.processName){
				return -1;
			} else if(x.count > y.count){
				return 1;
			} else if(x.count < y.count){
				return -1;
			} else if(x.shebeiNo > y.shebeiNo){
				return 1;
			} else if(x.shebeiNo < y.shebeiNo){
				return -1;
			}
			return 0;
		}
function AutoResizeImage(objImg) {

	var maxWidth = document.body.scrollWidth;
	var maxHeight = document.body.scrollHeight - 90;
	var img = new Image();
	img.src = objImg.src;
	var hRatio;
	var wRatio;
	var Ratio = 1;
	var w = img.width;
	var h = img.height;
	wRatio = (maxWidth - 40) / 4 / w;
	hRatio = (maxHeight - 90) / 2 / h;
	if (maxWidth == 0 && maxHeight == 0) {
		Ratio = 1;
	} else if (maxWidth == 0) {//
		if (hRatio < 1)
			Ratio = hRatio;
	} else if (maxHeight == 0) {
		if (wRatio < 1)
			Ratio = wRatio;
	} else if (wRatio < 1 || hRatio < 1) {
		Ratio = (wRatio <= hRatio ? wRatio : hRatio);
	}
	if (Ratio < 1) {
		w = w * Ratio;
		h = h * Ratio;
	}
	objImg.height = h;
	objImg.width = w;
}

$(function(){
	
	function autoScreen(){
		var maxWidth = document.body.scrollWidth;
		var maxHeight = document.body.scrollHeight;
		document.getElementById("titleBar").style.width = maxWidth + "px";
		document.getElementById("titleBar").style.height = maxHeight / 13 + "px";
	
		document.getElementById("td0").style.width = maxWidth / 5 * 2-13 + "px";
		document.getElementById("td0").style.height = maxHeight / 15 * 7 + "px";
	
		document.getElementById("td1").style.width = maxWidth / 5 * 3 + "px";
		document.getElementById("td1").style.height = maxHeight / 15 * 7 + "px";
	
		document.getElementById("machineDiv").style.height = maxHeight / 15 * 7 -20 + "px";
		document.getElementById("machineDiv").style.width = maxWidth / 5 * 3 + "px";
		
		document.getElementById("descDiv").style.height = maxHeight / 15 * 7 -20 + "px";
		document.getElementById("descDiv").style.width = maxWidth / 5 * 2-13 + "px";
	}
	
	function haha(){
		$.ajax({
		   type: "POST",
		   url: "${pageContext.request.contextPath}/screen_showScreen",
		   data : "screen.id=${screen.id}" ,
		   dataType: "json",
		   success: function(data){
		     var usercodes = new Array();
		     var usernames = new Array();
		     var usergw = new Array();
		     for(var i = 0; i< data.length; i++){
		    	 if(!data[i].usercodes){
		    		 continue;
		    	 }
		    	 var b = data[i].usercodes.split(",");
		    	 var a = data[i].usernames.split(",");
		    	 
		    	 for(var j = 0; j < b.length; j++){
		    		 if($.inArray(b[j], usercodes) < 0){
	    		 	 	usercodes.push(b[j]);
	    				usernames.push(a[j]);
	    				usergw.push(data[i].gongwei)
		    		 }
		    	 }
		     }
		     
		     var userTableStr = new Array();
		     for(var i = 0; i < usercodes.length; i++){
		    	 if(i == 0){
		    		 userTableStr[0] = "<tr><td  style='border-left:0px;'>照片</td>";
		    		 userTableStr[1] = "<tr><td  style='border-left:0px;'>工位</td>";
		    		 userTableStr[2] = "<tr><td  style='border-left:0px;border-bottom:0px;'>姓名</td>";
		    	 }
		    	 
		    	 userTableStr[0] = userTableStr[0] + '<td align="center"><img src="${pageContext.request.contextPath}/upload/user/'+usercodes[i]+'.jpg" /></td>'
		    	 userTableStr[1] = userTableStr[1] + '<td align="center"  >' + usergw[i] + '</td>'
		    	 userTableStr[2] = userTableStr[2] + '<td align="center" style="border-bottom:0px;" >' + usernames[i] + '</td>'
		    	 
		    	 if(i == (usercodes.length-1)){
		    		 userTableStr[0] = userTableStr[0] + "</tr>";
		    		 userTableStr[1] = userTableStr[1] + "</tr>";
		    		 userTableStr[2] = userTableStr[2] + "</tr>";
		    	 }
		     }
		     $('#userTable').append($(userTableStr[0]));
		     $('#userTable').append($(userTableStr[1]));
		     $('#userTable').append($(userTableStr[2]));
		     $('#userTableTile').attr("colspan", "" + (usercodes.length+1));
		     
		     data.sort(sortArray);
		     var tempArray = new Array();
		     for(var i=0; i<data.length; i++){
		    	 if($.inArray(data[i].gongwei + data[i].shebeiNo, tempArray) >= 0){
		    		 continue;
	    		 }
	    		 tempArray.push(data[i].gongwei + data[i].shebeiNo);
		    	 var icon;
		    	 if(data[i].machineStatus == "正常"){
		    		 icon = "<image src='${pageContext.request.contextPath}/images/mk/green.png' width='20' height='20'>";
		    	 } else if(data[i].machineStatus == "故障"){
		    		 icon = "<image src='${pageContext.request.contextPath}/images/mk/red.png' width='20' height='20'>";
		    	 } else {
		    		 icon = "<image src='${pageContext.request.contextPath}/images/mk/yellow.png' width='20' height='20'>";
		    	 }
		    	 var tr = "<tr><td align='center' style='border-left-width: 0px;'>" +data[i].gongwei+ "</td><td align='center'>" +data[i].markid+ "</td><td align='center'>" +data[i].processName+ "</td><td align='center'>" +data[i].count+ "</td><td align='center'>" +data[i].shebeiNo+ "</td><td align='center' style='border-right-width: 0px;'>" + icon + "</td></tr>"
		    	 $('#machineTable').append(tr);
		     }
		     
		   }
		   
		});
	}
	
	
	function hehe(){
		$('#scheduleTable')
		$.ajax({
		   type: "POST",
		   url: "${pageContext.request.contextPath}/screen_getProductionSchedule",
		   data : "screen.id=${screen.id}" ,
		   dataType: "json",
		   success: function(data){
			   for(var i=0; i<data.length; i++){
					var tr = "<tr><td align='center'  style='border-left: 0px;'>" +data[i].f_name+ "</td><td align='center' >" +data[i].f_pieceNum+ "</td><td align='center' >" +data[i].f_num+ "</td><td align='center'  style='border-right: 0px;'></td></tr>"
					$('#scheduleTable').append(tr);
			   }
				setInterval(scrollLop1, 1000);
		   }
	   });
		
	}
	function scrollLop1(){
		var x = document.getElementById("machineDiv");
		var k = x.scrollTop;
		x.scrollTop = x.scrollTop + 10;
		if((x.scrollTop + $(x).height())>= $('#machineDiv').children().height()){
			x.scrollTop =0;
		}
	}
	
	function scrollLop2(){
		var x = document.getElementById("descDiv");
		var k = x.scrollTop;
		x.scrollTop = x.scrollTop + 10;
		if((x.scrollTop + $(x).height())>= $('#descDiv').children().height()){
			x.scrollTop =0;
		}
	}

	autoScreen();
	haha();
	hehe();
	
	setInterval(scrollLop2, 1000);
});

function openShebeiUrl(url){
	window.open(url, "newwindow");
}
</script>
	<style type="text/css">
	table{
	border-collapse: collapse;
	}
			.table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;	
	border-collapse: collapse;
	border: solid #999 0px; 
	border-width:0px;
}

.table th,.table td {
	border: solid #999;
	border-width:1px;
	padding: 0px;
}

.bbtt th,.bbtt td {
	border: solid #999;
	border-width:0px;
	padding: 0px;
}

.button{
    background: #fff; /* Old browsers */
    background: -moz-linear-gradient(top, #ffffff 0%, #ededed 100%); /* FF3.6+ */
    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffffff), color-stop(100%,#ededed)); /* Chrome,Safari4+ */
    background: -webkit-linear-gradient(top, #ffffff 0%,#ededed 100%); /* Chrome10+,Safari5.1+ */
    background: -o-linear-gradient(top, #ffffff 0%,#ededed 100%); /* Opera11.10+ */
    background: -ms-linear-gradient(top, #ffffff 0%,#ededed 100%); /* IE10+ */
    filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffffff', endColorstr='#ededed',GradientType=0 ); /* IE6-9 */
    background: linear-gradient(top, #ffffff 0%,#ededed 100%); /* W3C */
    border: 1px solid #CCCCCC;
    color: #777777;
    font: 12px/50px Arial,Tahoma,Verdana,sans-serif;
    padding: 4px 10px;
    cursor: pointer;
    overflow: visible;  border-radius: 4px 4px 4px 4px;
}
		</style>
	</head>

	<body style="overflow: hidden">
		<table  class="table" >
			<tr>
				<th id="titleBar" colspan="2" valign="middle">
					<table style="width: 100%;" class="bbtt">
						<tr>
							<td>
								<img src="${pageContext.request.contextPath }/${companyInfo.logoOKjpg}" style="width: 45px; height: 45px"  />
							</td>
							<td valign="middle" align="center" style="text-align: center;">
								<div style="font-size: 25px;" >${companyInfo.shortName}电子看板</div>
							</td>
							<td align="right">
								<input type="button" class="button" onclick="openShebeiUrl('http://task.shhhes.com/System/Equipment/Barcode.jsp');" value="设备报修" />
								<input type="button" class="button" onclick="openShebeiUrl('http://task.shhhes.com/System/SOP/produce/Process_Receive.jsp');" value="生产工序实施" />
							</td>
						</tr>
					</table>
					
				</th>
			</tr>
			<tr>

				<td id="td0">
					  <video autoplay="autoplay" loop="true" id="example_video_1" class="video-js vjs-default-skin" controls preload="none" width="100%" height="100%" poster="${pageContext.request.contextPath }/System/bbs/honghu.png" data-setup="{}">
					    <source src="${pageContext.request.contextPath }/${screen.screenUrl}" type='video/mp4' />
					    <track kind="captions" src="demo.captions.vtt" srclang="en" label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
					    <track kind="subtitles" src="demo.captions.vtt" srclang="en" label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
					  </video>
				</td>

				<td id="td1" valign="top">
					<table style="width: 100%" >
						<tr>
							<td  style="border-width: 0 0 0 0px;">
								<table id="userTable"  style="border-width:0px;">
									<tr>
										<th id="userTableTile" style="border-left:0px;border-top: 0px;">
											当日生产人员
										</th>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td  style="border-width: 0 0 0 0px;">
								<table id="scheduleTable"  style="width: 100%; border-width: 0 0 0 0px;">
									<tr>
										<th  style='border-left: 0px;'>产品</th>
										<th>件号</th>
										<th>数量</th>
										<th style="border-right: 0px;">备注</th>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
				<td id="td0">
					  <video autoplay="autoplay" loop="true" id="example_video_1" class="video-js vjs-default-skin" controls preload="none" width="100%" height="100%" poster="${pageContext.request.contextPath }/images/mk/oceans-clip.png" data-setup="{}">
					    <source src="${pageContext.request.contextPath }/${screen.screenUrl}" type='video/mp4' />
					    <track kind="captions" src="demo.captions.vtt" srclang="en" label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
					    <track kind="subtitles" src="demo.captions.vtt" srclang="en" label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
					  </video>
				</td>
				
			</tr>
			<tr>
				<td valign=top id="td2" align="center" >
					<div id="machineDiv" style="overflow:hidden; ">
						<table id="machineTable"   style="width: 100%;border-top-width: 0px;">
							<tr>
								<th style="border-top-width: 0px;border-left-width: 0px;">工位</th>
								<th style="border-top-width: 0px;">件号</th>
								<th style="border-top-width: 0px;">工序</th>
								<th style="border-top-width: 0px;">数量</th>
								<th style="border-top-width: 0px;">设备编号</th>
								<th style="border-top-width: 0px;border-right-width: 0px;">设备状态</th>
							</tr>
						</table>
					</div>
				</td>
				<td id="td3" valign="top"><div id="descDiv" style="overflow: hidden">
				<table ><tr><td align="left" style="border-width: 0px;">${screen.desc}</td></tr></table>
				</div></td>
			</tr>
		</table>
	</body>
</html>
