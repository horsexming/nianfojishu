var source = [];
var list;
var MypageNum;

function show() {
	$.ajax({
		url : 'GanttAction!showGantt.action',
		type : 'post',
//		data : {
//		// parentId:${param.id}
//		},
		dataType : 'json',
		async : false,
		success : function(data) {
			list=data;
			$.each(data, function (i, item) {
				var barColor=['ganttRed','ganttBlue','ganttGreen','ganttOrange'];
				var num=Math.floor(Math.random()*4);
				source.push({
						name:"<input type='checkbox' value='"+item.belongLayer+"' name='belongLayer' style='display:none;' >"+item.projectName,
						desc:item.peopleName,
						id:item.id,
						isShowFlag:true,
						childLength:item.childLength,
						childSource:[],
						projectName:item.projectName,
						peopleName:item.peopleName,
						peopleIds:item.peopleIds,
						startTime:item.startTime,
						endTime:item.endTime,
						detail:item.detail,
						ganttId:item.id,
						values: [{
							from: "/Date("+item.startTime+")/",
							to: "/Date("+item.endTime+")/",
							label: item.detail+"<input type='hidden' value='"+item.id+"' />", 
							customClass: barColor[num],
							
						}]
						
				});
				
			});
			
		},
		error : function() {
			alert("服务器异常!");
		}
	});
	
	
	$(".gantt").gantt({
			source:source,
			navigate: "scroll",//button/scroll
			scale: "weeks",
			maxScale: "months",
			minScale: "days",
			itemsPerPage:6,
			scrollToToday:true,
			onItemClick: function(dataobj ) {
				
				var eve = window.event;
				var obj = eve.path[0];
			    var input_obj=$(obj).find("input")[0];
				var lineNum=input_obj.value;
				
				$('#dialog-overlay').show();
				$('#dialog-box').show();	
				
				for(var i=0;i<source.length;i++){
						if(source[i].id==lineNum){
							$.ajax( {
								type : "post",
								url : "GanttAction!findById.action",
								data : {
									parentId : source[i].id
								},
								dataType : "json",
								async : false,
								success : function(data) {
									if(data!=null){
										$("#projectName").val(data.projectName);	
										$("#peopleName").val(data.peopleName);
										$("#peopleIds").val(data.peopleIds);
										$("#startTime").val(data.startDate);
										$("#endTime").val(data.endDate);
										$("#detail").val(data.detail);
										$("#ganttId").val(data.id);
										$("#status").val(data.status);
										$("#textselectusername").val(data.peopleName);
									}
								}
							});
							break;
						}
				}
				$("#submitBtn").css("display","none");//添加按钮
				$("#deleteBtn").css("display","inline-block");//删除按钮
				$("#submitaddSon").css("display","inline-block");//删除按钮
				$("#submitUpdBtn").css("display","inline-block");//修改按钮
				$("#pageNum").val(MypageNum);
			},
			onAddClick: function(dt, rowId) {
				
			},
			onRender: function(pageNum) {
				if (window.console && typeof console.log === "function") 
				{
					MypageNum = pageNum;
				}
				return pageNum;
				
			}
			
		});
		$(".gantt").popover({
			selector: ".bar",
			title: "I'm a popover",
			content: "And I'm the content of said popover."
		});
		
		prettyPrint();
		return pageNum;	
	
}
function closeDia(){
	$('#dialog-overlay, #dialog-box').hide();  
}
function testTime() {
				var startStr = document.getElementById("startTime").value;
				var endStr = document.getElementById("endTime").value;
				if(startStr=="" ||startStr.length<=0){
					alert("请选择开始时间");
					$("#startTime").focus();
					return false;
				}
				if(endStr=="" ||endStr.length<=0){
					alert("请选择结束时间");
					$("#endTime").focus();
					return false;
				
				}
				if (startStr != "" && endStr != "") {
					var start = startStr.split("-");
					var end = endStr.split("-");
					var startTime = new Date(start[0], start[1], start[2]);
					
					var endTime = new Date(end[0], end[1], end[2]);
					var myDate = new Date(); //获得当前时间
					myDate.setMonth(myDate.getMonth() + 1);//为当前date的月份+1后重新赋值
					if (startTime <= endTime == false) {
						alert("开始时间不能大于结束时间!请重新选择!谢谢!");
						$("#endTime").val("");
						$("#endTime").focus();
						return false;
					} 
				}
				return true;
}

