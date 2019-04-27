/*jshint shadow:true, unused:false, laxbreak:true, evil:true*/
/*globals jQuery, alert*/

//得到孩子节点
function  showChild(ganttId,index){
	//显示
	if(source[index].isShowFlag){
		source[index].isShowFlag=false;
		var before=[];
		var after=[];
		var checkedIndex;
		var  current=[];
		//当前节点下标==index
		for(var i=0;i<source.length;i++){
			if(source[i].id==ganttId){
				checkedIndex=i;
				break;
			}
		}
		
		
		//前
		var b=0;
		for(var i=0;i<=checkedIndex;i++){
			before[b++]=source[i];
		}
		//后
		var a=0;
		for(var i=checkedIndex+1;i<source.length;i++){
			after[a++]=source[i];
		}
		
		source=[];
//		var childLength=0;
		$.ajax({
			url : 'GanttAction!gainChild.action',
			type : 'post',
			data : {
			 parentId:ganttId
			},
			dataType : 'json',
			async : false,
			success : function(data) {
				list=data;
				//childLength=data.length;
				$.each(data, function (i, item) {
					var barColor=['ganttRed','ganttBlue','ganttGreen','ganttOrange'];
					var num=Math.floor(Math.random()*4);
					current.push({
							name:"<input type='checkbox' value='"+item.belongLayer+"' name='belongLayer' style='display:none;' >" +
							     "<input type='checkbox' value='"+item.fatherId+"' name='fatherId' style='display:none;' >"+item.projectName,
							desc:item.peopleName,
							id:item.id,
							isShowFlag:true,//是否显示子节点标志   true：显示  false：隐藏 
							childSource:[],
							childLength:item.childLength,
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
								
							}],
							
					});
				});
			},
			error : function() {
				alert("服务器异常!");
			}
		});
//		before[before.length-1].childLength=childLength;
		source=before.concat(current);
		source=source.concat(after);
		
		
		
		
//	//隐藏
	}else{
		source[index].isShowFlag=true;
		var checkedIndex;
		for(var i=0;i<source.length;i++){
			if(source[i].id==ganttId){
				checkedIndex=i;
				break;
			}
		}

		
		if(source[index].childLength!=null && source[index].childLength>0){
			child=source.splice(checkedIndex+1,source[index].childLength);//移除孩子节点	
		}else{
			alert("无子节点");
			source[index].isShowFlag=false;
			
		}
		
		
	}
	
	
	//生gantt
	$(".gantt").gantt({
				source:source,
				navigate: "scroll",//button/scroll
				scale: "weeks",
				maxScale: "months",
				minScale: "days",
				itemsPerPage:6,
				scrollToToday:true,
				onItemClick: function(dataobj) {
					
					var eve = window.event;
					var obj = eve.path[0];
					var ind=obj;
				    var input_obj=$(obj).find("input")[0];
					var lineNum=input_obj.value;
					$('#dialog-overlay').show();
					$('#dialog-box').show();
					for(var i=0;i<source.length;i++){
						if(source[i].id==lineNum){
							$("#projectName").val(source[i].projectName);	
							$("#peopleName").val(source[i].peopleName);
							$("#peopleIds").val(source[i].peopleIds);
							$("#startTime").val(source[i].startDate);
							$("#endTime").val(source[i].endDate);
							$("#detail").val(source[i].detail);
							$("#ganttId").val(source[i].id);
							$("#textselectusername").val(source[i].peopleStr);
							break;
						}
					}
//					$("#projectName").val(list[ganttId].projectName);	
//					$("#peopleName").val(list[ganttId].peopleName);
//					$("#peopleIds").val(list[ganttId].peopleIds);
//					$("#startTime").val(list[ganttId].startDate);
//					$("#endTime").val(list[ganttId].endDate);
//					$("#detail").val(list[ganttId].detail);
//					$("#ganttId").val(list[ganttId].id);
//					$("#textselectusername").val(list[ganttId].peopleStr);
					$("#submitBtn").css("display","none");//添加按钮
					$("#deleteBtn").css("display","inline-block");//删除按钮
					$("#submitUpdBtn").css("display","inline-block");//修改按钮
					$("#pageNum").val(MypageNum);
				},
				onAddClick: function(dt, rowId) {
					//addTask();
				},
				onRender: function(pageNum) {
					//打开，关闭按钮
					if(source[index].isShowFlag){
						document.getElementById("openIcon"+ganttId).style.display="inline-block";
						document.getElementById("closeIcon"+ganttId).style.display="none";
					}else{
						document.getElementById("openIcon"+ganttId).style.display="none";
						document.getElementById("closeIcon"+ganttId).style.display="inline-block";
					}
					for(var i=0;i<source.length;i++){
						if(source[i].childLength<=0){
							document.getElementById("openIcon"+source[i].id).style.display="none";
							document.getElementById("closeIcon"+source[i].id).style.display="none";
						}
					}
					if (window.console && typeof console.log === "function") 
					{
						MypageNum = pageNum;
						console.log("chart rendered");
						
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

//移除数组指定值元素
function removeByValue(arr, val) {
  for(var i=0; i<arr.length; i++) {
    if(arr[i] == val) {
      arr.splice(i, 1);
      break;
    }
  }
}


(function($) {//(function(arg){...})(param) 这就相当于定义了一个参数为arg的匿名函数，并且将param作为参数来调用这个匿名函数 
	"use strict";
	$.fn.gantt = function(options) {
		var cookieKey = "jquery.fn.gantt";
		var scales = [ "hours", "days", "weeks", "months" ];
		var settings = {//部件设置集
			source : null,
			itemsPerPage :6,
			months : [ "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月",
					"十月", "十一月", "十二月" ],
			dow : [ "日", "一", "二", "三", "四", "五", "六" ],
			startPos : new Date(),
			navigate : "buttons",
			scale : "days",
			useCookie : false,
			maxScale : "months",
			minScale : "hours",
			waitText : "Please wait...",
			onItemClick : function(data) {
				return;
			},
			onAddClick : function(data) {
				return;
			},
			//onRender: function() {},
			onRender : function(pageNum) {
				return element.pageNum;
			},
			scrollToToday : true
		};

		$
				.extend($.expr[":"], {//自定义选择器方法（a：元素，i：索引，m：值）
							findday : function(a, i, m) {//以毫秒为时间单位匹配一个指定的日期
							var cd = new Date(parseInt(m[3], 10));
							var id = $(a).attr("id");
							id = id ? id : "";
							var si = id.indexOf("-") + 1;
							var ed = new Date(parseInt(id.substring(si,
									id.length), 10));
							cd = new Date(cd.getFullYear(), cd.getMonth(), cd
									.getDate());
							ed = new Date(ed.getFullYear(), ed.getMonth(), ed
									.getDate());
							return cd.getTime() === ed.getTime();
						}
					});

		$.extend($.expr[":"], {
			findweek : function(a, i, m) {//以毫秒为时间单位匹配一个指定的周
					var cd = new Date(parseInt(m[3], 10));
					var id = $(a).attr("id");
					id = id ? id : "";
					var si = id.indexOf("-") + 1;
					cd = cd.getFullYear() + "-"
							+ cd.getDayForWeek().getWeekOfYear();
					var ed = id.substring(si, id.length);
					return cd === ed;
				}
			});

		$.extend($.expr[":"], {
			findmonth : function(a, i, m) {//：以毫秒为时间单位匹配一个指定的月
					var cd = new Date(parseInt(m[3], 10));
					cd = cd.getFullYear() + "-" + cd.getMonth();
					var id = $(a).attr("id");
					id = id ? id : "";
					var si = id.indexOf("-") + 1;
					var ed = id.substring(si, id.length);
					return cd === ed;
				}
			});

		Date.prototype.getWeekId = function() {//获取weekid用于标识week的div的id特性，返回字符串，格式为dh-YYYY-WW，其中的ww是一年中的第几周
			var y = this.getFullYear();
			var w = this.getWeekOfYear();
			var m = this.getMonth();
			if (m === 11 && w === 1) {
				y++;
			}

			return 'dh-' + y + "-" + w;
		};

		Date.prototype.genRepDate = function() {//按部件设置属性中scale的值获取时间范围，以秒为时间单位

			switch (settings.scale) {
			case "hours":

				return this.getTime();
			case "weeks":

				return this.getDayForWeek().getTime();
			case "months":

				return new Date(this.getFullYear(), this.getMonth(), 1)
						.getTime();
			default:

				return this.getTime();
			}
		};
		Date.prototype.getDayOfYear = function() {
			var fd = new Date(this.getFullYear(), 0, 0);
			var sd = new Date(this.getFullYear(), this.getMonth(), this
					.getDate());
			return Math.ceil((sd - fd) / 86400000);
		};
		Date.prototype.getWeekOfYear = function() {
			var ys = new Date(this.getFullYear(), 0, 1);
			var sd = new Date(this.getFullYear(), this.getMonth(), this
					.getDate());
			if (ys.getDay() > 3) {
				ys = new Date(sd.getFullYear(), 0, (7 - ys.getDay()));
			}
			var daysCount = sd.getDayOfYear() - ys.getDayOfYear();
			return Math.ceil(daysCount / 7);

		};
		Date.prototype.getDaysInMonth = function() {
			return 32 - new Date(this.getFullYear(), this.getMonth(), 32)
					.getDate();
		};
		Date.prototype.hasWeek = function() {
			var df = new Date(this.valueOf());
			df.setDate(df.getDate() - df.getDay());
			var dt = new Date(this.valueOf());
			dt.setDate(dt.getDate() + (6 - dt.getDay()));

			if (df.getMonth() === dt.getMonth()) {
				return true;
			} else {
				return (df.getMonth() === this.getMonth() && dt.getDate() < 4)
						|| (df.getMonth() !== this.getMonth() && dt.getDate() >= 4);
			}
		};
		Date.prototype.getDayForWeek = function() {
			var df = new Date(this.valueOf());
			df.setDate(df.getDate() - df.getDay());
			var dt = new Date(this.valueOf());
			dt.setDate(dt.getDate() + (6 - dt.getDay()));
			if ((df.getMonth() === dt.getMonth())
					|| (df.getMonth() !== dt.getMonth() && dt.getDate() >= 4)) {
				return new Date(dt.setDate(dt.getDate() - 3));
			} else {
				return new Date(df.setDate(df.getDate() + 3));
			}
		};

		/**
		 * Core functions for creating grid.
		 */
		var core = {// Grid管理器（负责导航和渲染）：core

			elementFromPoint : function(x, y) {//获取位于指定点的最高处的元素

				if ($.browser.msie) {
					x -= $(document).scrollLeft();
					y -= $(document).scrollTop();
				} else {
					x -= window.pageXOffset;
					y -= window.pageYOffset;
				}
				return document.elementFromPoint(x, y);
			},
			/**
			 * Create header
			 */
			create : function(element) {
				/**
				 * Retrieve data
				 */
				if (typeof settings.source !== "string") {
					element.data = settings.source;
					core.init(element);
				} else {
					$.getJSON(settings.source, function(jsData) {
						element.data = jsData;
						core.init(element);
					});
				}
				
				
				//添加跳转
				if(gantt_add){
					//最后 一项删除跳转if()
					element.pageNum=element.pageCount-1;
					
				}
				
			},
			init : function(element) {//初始化视图，计算行数、页数、可见的开始时间与结束时间
				element.rowsNum = element.data.length;//行数
				element.pageCount = Math.ceil(element.rowsNum
						/ settings.itemsPerPage);//页数
				
				
				
				element.rowsOnLastPage = element.rowsNum
						- (Math.floor(element.rowsNum / settings.itemsPerPage) * settings.itemsPerPage);//最后一页条数
				element.dateStart = tools.getMinDate(element);
				element.dateEnd = tools.getMaxDate(element);
				core.waitToggle(element, true, function() {
					core.render(element);
				});
			},
			render : function(element) {//渲染grida
				var content = $('<div class="fn-content"/>');
				var $leftPanel = core.leftPanel(element);//创建左侧Panel
				content.append($leftPanel);
				var $rightPanel = core.rightPanel(element, $leftPanel);//创建右侧头部Panel
				content.append($rightPanel);
				var mLeft, hPos;
				content.append(core.navigation(element));//导航条
				var $dataPanel = $rightPanel.find(".dataPanel");

				element.gantt = $('<div class="fn-gantt" />').append(content);

				$(element).html(element.gantt);
				$(element).find("#pageNumInput").val((element.pageNum + 1));

				element.scrollNavigation.panelMargin = parseInt($dataPanel.css(
						"margin-left").replace("px", ""), 10);

				element.scrollNavigation.panelMaxPos = ($dataPanel.width() - $rightPanel
						.width());

				element.scrollNavigation.canScroll = ($dataPanel.width() > $rightPanel
						.width());//是否可滚动

				core.markNow(element);//移除”wd“class添加”today“class到当前的scale模式
				core.fillData(element, $dataPanel, $leftPanel);//填充图表，解析数据并填充到panel

				
				
				
				//距离左边据
				var checkbox=document.getElementsByName("belongLayer");
				if(checkbox!=null  && checkbox.length>0){
					for(var i=0;i<checkbox.length;i++){
						$(checkbox[i]).parent().css("margin-left",((checkbox[i].value-1)*30)+"px");
						$(checkbox[i]).parent().css("width",(110+(checkbox[i].value-1)*30)+"px");
//						$(".leftPanel .fn-label").css("width",(110+(checkbox[i].value-1)*30)+"px");
						
						$(".leftPanel").css("width",(225+(checkbox[i].value-1)*30)+"px");
						
					}
				}
				
				
				
				
				
				if (settings.useCookie) {
					var sc = $.cookie(this.cookieKey + "ScrollPos");
					if (sc) {
						element.hPosition = sc;
					}
				}

				if (settings.scrollToToday) {//设置是否滚动到今天
					var startPos = Math
							.round((settings.startPos / 1000 - element.dateStart / 1000) / 86400) - 2;
					if ((startPos > 0 && element.hPosition !== 0)) {
						if (element.scaleOldWidth) {
							mLeft = ($dataPanel.width() - $rightPanel.width());
							hPos = mLeft * element.hPosition
									/ element.scaleOldWidth;
							hPos = hPos > 0 ? 0 : hPos;
							$dataPanel.css( {
								"margin-left" : hPos + "px"
							});
							element.scrollNavigation.panelMargin = hPos;
							element.hPosition = hPos;
							element.scaleOldWidth = null;
						} else {
							$dataPanel.css( {
								"margin-left" : element.hPosition + "px"
							});
							element.scrollNavigation.panelMargin = element.hPosition;
						}
						core.repositionLabel(element);
					} else {
						core.repositionLabel(element);
					}
				} else {
					if ((element.hPosition !== 0)) {
						if (element.scaleOldWidth) {
							mLeft = ($dataPanel.width() - $rightPanel.width());
							hPos = mLeft * element.hPosition
									/ element.scaleOldWidth;
							hPos = hPos > 0 ? 0 : hPos;
							$dataPanel.css( {
								"margin-left" : hPos + "px"
							});
							element.scrollNavigation.panelMargin = hPos;
							element.hPosition = hPos;
							element.scaleOldWidth = null;
						} else {
							$dataPanel.css( {
								"margin-left" : element.hPosition + "px"
							});
							element.scrollNavigation.panelMargin = element.hPosition;
						}
						core.repositionLabel(element);
					} else {
						core.repositionLabel(element);
					}
				}

				$dataPanel.css( {
					height : $leftPanel.height()
				});
				core.waitToggle(element, false);

				settings.onRender(element.pageNum);//
			},
			leftPanel : function(element) {
				
				/* Left panel */
				var ganttLeftPanel = $('<div class="leftPanel"/>').append(
						$('<div class="row spacer"/>')
								.css(
										"height",
										tools.getCellSize()
												* element.headerRows + "px")
								.css("width", "100%"));

				var entries = [];

				$.each(element.data, function(i, entry) {
					
							//i>=0*6  && i<0*6+6
								//i>=1*6  && i<1*6+6
								if (i >= element.pageNum
										* settings.itemsPerPage
										&& i < (element.pageNum
												* settings.itemsPerPage + settings.itemsPerPage)) {//i>=0 && i<5

									entries.push('<div class="row name row' + i
											+ (entry.desc ? '' : ' fn-wide')
											+ '" id="rowheader' + i
											+ '" offset="' + i
											% settings.itemsPerPage
											* tools.getCellSize() + '">');
									//console.log("entry.cssClass:",entry.cssClass);
									
//									entries
//											.push('<span  onclick ="showChild('+entry.id+','+i+')"   class="fn-label'
//													+ (entry.cssClass ? ' ' + entry.cssClass
//															: '') + '" style=\"text-align:left; \">'
//													+ entry.name + '</span>');
									
									entries
											.push('<span  onclick ="showChild('+entry.id+','+i+')"   class="fn-label'
													+ (entry.cssClass ? ' ' + entry.cssClass
															: '') + '" style=\"text-align:left; \"><span class="openIcon" id="openIcon'+entry.id+'"></span><span class="closeIcon" id="closeIcon'+entry.id+'" style="display:none;"></span>'
													+ entry.name + '</span>');
									
									
									
									entries.push('</div>');

									if (entry.desc) {
										entries.push('<div class="row desc row'
												+ i + ' " id="RowdId_' + i
												+ '" data-id="' + entry.id
												+ '">');
										entries
												.push('<span class="fn-label'
														+ (entry.cssClass ? ' ' + entry.cssClass
																: '') + ' ">'
														+ entry.desc
														+ '</span>');
										entries.push('</div>');
									}
									
									
									
								}
							});
				ganttLeftPanel.append(entries.join(""));//数组名.join("");join() 方法用于把数组中的所有元素放入一个字符串。元素是通过指定的分隔符进行分隔的。   
				
				return ganttLeftPanel;
			},
			
			//创建右侧数据Panel
			dataPanel : function(element, width) {

				var dataPanel = $('<div class="dataPanel" style="width: ' + width + 'px;"/>');

				/*
				 * Mouse wheel events滚轮事件
				 */
				var mousewheelevt = (/Firefox/i.test(navigator.userAgent)) ? "DOMMouseScroll"
						: "mousewheel";////如果是火狐浏览器就mousewheelevt=DOMMouseScroll;否则mousewheelevt=mousewheel,因为火狐绑定滚轮事件是DOMMouseScroll

				if (document.attachEvent) {//IE中我们可以使用attachEvent
					element.attachEvent("on" + mousewheelevt, function(e) {
						core.wheelScroll(element, e);
					});
				} else if (document.addEventListener) {//可惜这个微软的私人方法,火狐和其他浏览器都不支持,幸运的是他们都支持W3C标准的addEventListener方法
					element.addEventListener(mousewheelevt, function(e) {
						core.wheelScroll(element, e);
					}, false);
				}

				// addNEwClick
				dataPanel.click(function(e) {
					e.stopPropagation();//不再派发事件。终止事件在传播过程的捕获、目标处理或起泡阶段进一步传播。调用该方法后，该节点上处理该事件的处理程序将被调用，事件不再被分派到其他节点。
						var corrX, corrY;
						var leftpanel = $(element).find(".fn-gantt .leftPanel");
						var datapanel = $(element).find(".fn-gantt .dataPanel");
						switch (settings.scale) {
						case "weeks":
							corrY = tools.getCellSize() * 2;
							break;
						case "months":
							corrY = tools.getCellSize();
							break;
						case "hours":
							corrY = tools.getCellSize() * 4;
							break;
						case "days":
							corrY = tools.getCellSize() * 3;
							break;
						default:
							corrY = tools.getCellSize() * 2;
							break;
						}

						// Adjust, so get middle of elm
						// corrY -= Math.floor(tools.getCellSize() / 2);

						//find column
						var col = core.elementFromPoint(e.pageX, datapanel
								.offset().top
								+ corrY);
						// hit the label?
						if (col.className === "fn-label") {
							col = $(col.parentNode);
						} else {
							col = $(col);
						}

						var dt = col.attr("repdate");
						//find row
						var row = core.elementFromPoint(leftpanel.offset().left
								+ leftpanel.width() - 10, e.pageY);
						// hit the label?
						if (row.className.indexOf("fn-label") === 0) {
							row = $(row.parentNode);
						} else {
							row = $(row);
						}
						var rowId = row.data().id;

						settings.onAddClick(dt, rowId);
					});
				return dataPanel;
			},
			// Creates Data container with header
			rightPanel : function(element, leftPanel) {//创建右侧头部Panel

				var range = null;
				var dowClass = [ " sn", " wd", " wd", " wd", " wd", " wd",
						" sa" ];
				var gridDowClass = [ " sn", "", "", "", "", "", " sa" ];

				var yearArr = [ '<div class="row"/>' ];
				var daysInYear = 0;

				var monthArr = [ '<div class="row"/>' ];
				var daysInMonth = 0;

				var dayArr = [];

				var hoursInDay = 0;

				var dowArr = [];

				var horArr = [];

				var today = new Date();
				today = new Date(today.getFullYear(), today.getMonth(), today
						.getDate());

				var holidays = settings.holidays ? settings.holidays.join()
						: '';
				

				var dataPanel;

				switch (settings.scale) {//甘特图每一列的时间范围
				// hours ///////////////////////////////////////////////////////////////////////////////////////// 
				case "hours":
					range = tools.parseTimeRange(element.dateStart,
							element.dateEnd, element.scaleStep);
					var year = range[0].getFullYear();
					var month = range[0].getMonth();
					var day = range[0];

					for ( var i = 0; i < range.length; i++) {
						var rday = range[i];
						/*
						 * Fill years
						 */
						var rfy = rday.getFullYear();
						if (rfy !== year) {
							yearArr
									.push(('<div class="row header year" style="width: '
											+ tools.getCellSize()
											* daysInYear
											+ 'px;"><div class="fn-label">'
											+ year + '</div></div>'));

							year = rfy;
							daysInYear = 0;
						}
						daysInYear++;

						/*
						 * Fill months
						 */
						var rm = rday.getMonth();
						if (rm !== month) {
							monthArr
									.push(('<div class="row header month" style="width: '
											+ tools.getCellSize()
											* daysInMonth
											+ 'px"><div class="fn-label">'
											+ settings.months[month] + '</div></div>'));

							month = rm;
							daysInMonth = 0;
						}
						daysInMonth++;

						/*
						 * Fill days & hours
						 */
						var rgetDay = rday.getDay();
						var getDay = day.getDay();
						var day_class = dowClass[rgetDay];
						var getTime = day.getTime();
						if (holidays.indexOf((new Date(rday.getFullYear(), rday
								.getMonth(), rday.getDate())).getTime()) > -1) {
							day_class = "holiday";
						}
						if (rgetDay !== getDay) {

							var day_class2 = (today - day === 0) ? ' today'
									: (holidays.indexOf(getTime) > -1) ? "holiday"
											: dowClass[getDay];

							dayArr.push('<div class="row date ' + day_class2
									+ '" ' + ' style="width: '
									+ tools.getCellSize() * hoursInDay
									+ 'px;"> ' + ' <div class="fn-label">'
									+ day.getDate() + '</div></div>');
							dowArr.push('<div class="row day ' + day_class2
									+ '" ' + ' style="width: '
									+ tools.getCellSize() * hoursInDay
									+ 'px;"> ' + ' <div class="fn-label">'
									+ settings.dow[getDay] + '</div></div>');

							day = rday;
							hoursInDay = 0;
						}
						hoursInDay++;

						horArr.push('<div class="row day ' + day_class
								+ '" id="dh-' + rday.getTime() + '"  offset="'
								+ i * tools.getCellSize() + '"  repdate="'
								+ rday.genRepDate() + '22222 "> '
								+ rday.getHours() + '</div>');
					}

					/*
					 * Last year
					 */

					yearArr.push('<div class="row header year" style="width: '
							+ tools.getCellSize() * daysInYear
							+ 'px;"><div class="fn-label">' + year
							+ '-2222</div></div>');
					/*
					 * Last month
					 */
					monthArr
							.push('<div class="row header month" style="width: '
									+ tools.getCellSize()
									* daysInMonth
									+ 'px"><div class="fn-label">'
									+ settings.months[month]
									+ '22222222</div></div>');

					var day_class = dowClass[day.getDay()];

					if (holidays.indexOf((new Date(day.getFullYear(), day
							.getMonth(), day.getDate())).getTime()) > -1) {
						day_class = "holiday";
					}

					dayArr.push('<div class="row date ' + day_class + '" '
							+ ' style="width: ' + tools.getCellSize()
							* hoursInDay + 'px;"> ' + ' <div class="fn-label">'
							+ day.getDate() + '</div></div>');

					dowArr.push('<div class="row day ' + day_class + '" '
							+ ' style="width: ' + tools.getCellSize()
							* hoursInDay + 'px;"> ' + ' <div class="fn-label">'
							+ settings.dow[day.getDay()] + '</div></div>');

					//                        var dataPanel = core.dataPanel(element, range.length * tools.getCellSize());
					dataPanel = core.dataPanel(element, range.length
							* tools.getCellSize());

					/*
					 * Append panel elements
					 */

					dataPanel.append(yearArr.join(""));
					dataPanel.append(monthArr.join(""));
					dataPanel.append($('<div class="row"/>').html(
							dayArr.join("")));
					dataPanel.append($('<div class="row"/>').html(
							dowArr.join("")));
					dataPanel.append($('<div class="row"/>').html(
							horArr.join("")));

					break;

				// weeks ///////////////////////////////////////////////////////////////////////////////////////// 
				case "weeks":
					range = tools.parseWeeksRange(element.dateStart,
							element.dateEnd);
					// console.log("range",range);
					yearArr = [ '<div class="row"/>' ];
					monthArr = [ '<div class="row"/>' ];
					var year = range[0].getFullYear();//2017
					var month = range[0].getMonth();//6--5
					var day = range[0];//
					for ( var i = 0; i < range.length; i++) {
						var rday = range[i];
						/*
						 * Fill years//2017
						 */
						if (rday.getFullYear() !== year) {//year-2017 30

							yearArr
									.push(('<div class="row header year" style="width: '
											+ tools.getCellSize()
											* daysInYear
											+ 'px;"><div class="fn-label">'
											+ year + '</div></div>'));

							year = rday.getFullYear();//year-2018  8

							daysInYear = 0;
						}
						daysInYear++;
						/*
						 * Fill months
						 */
						if (rday.getMonth() !== month) {//month-5
							monthArr
									.push(('<div class="row header month" style="width:'
											+ tools.getCellSize()
											* daysInMonth
											+ 'px;"><div class="fn-label">'
											+ settings.months[month] + '</div></div>'));
							month = rday.getMonth();
							daysInMonth = 0;
						}
						daysInMonth++;

						/*
						 * Fill weeks
						 */
						dayArr.push('<div class="row day wd" ' + ' id="'
								+ rday.getWeekId() + '" offset="' + i
								* tools.getCellSize() + '" repdate="'
								+ rday.genRepDate() + '"> '
								+ ' <div class="fn-label">'
								+ rday.getWeekOfYear() + '</div></div>');
						//genRepDate():按部件设置属性中scale的值获取时间范围，以秒为时间单位
						//getWeekId():获取weekid用于标识week的div的id特性，返回字符串，格式为dh-YYYY-WW，其中的ww是一年中的第几周
						//getWeekOfYear():获取日期在一年中的第几周
					}

					/*
					 * Last year//最后一年
					 */
					yearArr.push('<div class="row header year" style="width: '
							+ tools.getCellSize() * daysInYear
							+ 'px;"><div class="fn-label">' + year
							+ '</div></div>');
					/*
					 * Last month//最后一月
					 */
					monthArr
							.push('<div class="row header month" style="width: '
									+ tools.getCellSize()
									* daysInMonth
									+ 'px"><div class="fn-label">'
									+ settings.months[month] + '</div></div>');
					dataPanel = core.dataPanel(element, range.length
							* tools.getCellSize());
					///dataPanel.append(yearArr.join("") + monthArr.join("") + dayArr.join("") + (dowArr.join("")));
					dataPanel.append(yearArr.join("") + monthArr.join("")
							+ dayArr.join(""));
					break;
				// months //////////////////////////////////////////////////////////////////////////////////////// 
				case 'months':
					range = tools.parseMonthsRange(element.dateStart,
							element.dateEnd);
					var year = range[0].getFullYear();
					var month = range[0].getMonth();
					var day = range[0];
					for ( var i = 0; i < range.length; i++) {
						var rday = range[i];
						/*
						 * Fill years
						 */

						if (rday.getFullYear() !== year) {
							yearArr
									.push(('<div class="row header year" style="width: '
											+ tools.getCellSize()
											* daysInYear
											+ 'px;"><div class="fn-label">'
											+ year + '</div></div>'));
							year = rday.getFullYear();
							daysInYear = 0;
						}
						daysInYear++;
						monthArr.push('<div class="row day wd" id="dh-'
								+ tools.genId(rday.getTime()) + '" offset="'
								+ i * tools.getCellSize() + '" repdate="'
								+ rday.genRepDate() + '">'
								+ (1 + rday.getMonth()) + '</div>');

					}

					/*
					 * Last year
					 */
					yearArr.push('<div class="row header year" style="width: '
							+ tools.getCellSize() * daysInYear
							+ 'px;"><div class="fn-label">' + year
							+ '</div></div>');

					/*
					 * Last month
					 */
					monthArr
							.push('<div class="row header month" style="width: '
									+ tools.getCellSize()
									* daysInMonth
									+ 'px">"<div class="fn-label">'
									+ settings.months[month] + '</div></div>');

					//var dataPanel = core.dataPanel1(element, range.length * tools.getCellSize(),"months");
					dataPanel = core.dataPanel(element, range.length
							* tools.getCellSize());
					/*
					 * Append panel elements
					 */
					dataPanel.append(yearArr.join(""));
					dataPanel.append(monthArr.join(""));
					dataPanel.append($('<div class="row"/>').html(
							dayArr.join("")));
					dataPanel.append($('<div class="row"/>').html(
							dowArr.join("")));

					break;
				// days ////////////////////////////////////////////////////////////////////////////////////////// 
				default:
					//console.log("settings.scale:days");
					range = tools.parseDateRange(element.dateStart,
							element.dateEnd);

					var year = range[0].getFullYear();
					var month = range[0].getMonth();
					var day = range[0];

					for ( var i = 0; i < range.length; i++) {
						var rday = range[i];
						/*
						 * Fill years
						 */
						if (rday.getFullYear() !== year) {
							yearArr
									.push(('<div class="row header year" style="width:'
											+ tools.getCellSize()
											* daysInYear
											+ 'px;"><div class="fn-label">'
											+ year + '</div></div>'));
							year = rday.getFullYear();
							daysInYear = 0;
						}
						daysInYear++;

						/*
						 * Fill months
						 */
						if (rday.getMonth() !== month) {
							monthArr
									.push(('<div class="row header month" style="width:'
											+ tools.getCellSize()
											* daysInMonth
											+ 'px;"><div class="fn-label">'
											+ settings.months[month] + '</div></div>'));
							month = rday.getMonth();
							daysInMonth = 0;
						}
						daysInMonth++;

						var getDay = rday.getDay();
						var day_class = dowClass[getDay];
						if (holidays.indexOf((new Date(rday.getFullYear(), rday
								.getMonth(), rday.getDate())).getTime()) > -1) {
							day_class = "holiday";
						}

						dayArr.push('<div class="row date ' + day_class + '" '
								+ ' id="dh-' + tools.genId(rday.getTime())
								+ '" offset="' + i * tools.getCellSize()
								+ '" repdate="' + rday.genRepDate() + '> '
								+ ' <div class="fn-label">' + rday.getDate()
								+ '</div></div>');
						dowArr.push('<div class="row day ' + day_class + '" '
								+ ' id="dw-' + tools.genId(rday.getTime())
								+ '"  repdate="' + rday.genRepDate() + '"> '
								+ ' <div class="fn-label">'
								+ settings.dow[getDay] + '</div></div>');
					} //for

					/*
					 * Last year
					 */
					yearArr.push('<div class="row header year" style="width: '
							+ tools.getCellSize() * daysInYear
							+ 'px;"><div class="fn-label">' + year
							+ '</div></div>');
					/*
					 * Last month
					 */
					monthArr
							.push('<div class="row header month" style="width: '
									+ tools.getCellSize()
									* daysInMonth
									+ 'px"><div class="fn-label">'
									+ settings.months[month] + '</div></div>');

					var dataPanel = core.dataPanel(element, range.length
							* tools.getCellSize());

					/*
					 * Append panel elements
					 */
					dataPanel.append(yearArr.join(""));
					dataPanel.append(monthArr.join(""));
					dataPanel.append($('<div class="row"/>').html(
							dayArr.join("")));
					dataPanel.append($('<div class="row"/>').html(
							dowArr.join("")));

					break;
				}
				//				console.log("dataPanel:",dataPanel);
				return $('<div class="rightPanel"></div>').append(dataPanel);
			},

			navigation : function(element) {//导航
				var ganttNavigate = null;
				if (settings.navigate === "scroll") {
					ganttNavigate = $('<div class="navigate" />')
							.append(
									$('<div class="nav-slider" />')
											.append(
													$(
															'<div class="nav-slider-left" />')
															.append(
																	$(
																			'<span role="button" class="nav-link nav-page-back"/>')
																			.html(
																					'&lt;')
																			.click(
																					function() {
																						core
																								.navigatePage(
																										element,
																										-1);
																					}))
															.append(
																	$(
																			'<div class="page-number"/>')
																			.append(
																					$(
																							'<span />')
																							.html(
																									element.pageNum
																											+ 1
																											+ ' of '
																											+ element.pageCount)))
															.append(
																	$(
																			'<span role="button" class="nav-link nav-page-next"/>')
																			.html(
																					'&gt;')
																			.click(
																					function() {
																						core
																								.navigatePage(
																										element,
																										1);
																					}))

															.append(
																	$(
																			'<span id="test"><input type="text"  id="pageNumInput" style="width:50px;"/>')

																			.append(
																					$(
																							'<input  type="button" class="searchBtn" value="跳转"  style="width:50px;height:25px;margin-top:-15px;margin-left:5px;margin-right:5px;' + '"/>')

																							.click(
																									function() {
																										var myPageNum = $
																												.trim($(
																														'#pageNumInput')
																														.val());
																										var obj = document
																												.getElementById("pageNumInput");
																										//alert(($('#pageNumInput').val()+1));
																										if (myPageNum != null
																												&& myPageNum.length > 0) {
																											var flag = numyanzheng(
																													obj,
																													'zhengshu');
																											if (flag) {
																												var num = parseInt(myPageNum);
																												//alert(num);
																												var maxNum = element.pageCount;
																												//alert(num+"---"+maxNum);

																												if (num >= maxNum) {
																													$(
																															"#pageNumInput")
																															.val(
																																	maxNum);
																													core
																															.navigatePage1(
																																	element,
																																	maxNum - 1);
																												} else if (num <= 0) {
																													$(
																															"#pageNumInput")
																															.val(
																																	1);
																													core
																															.navigatePage1(
																																	element,
																																	0);
																												} else {
																													$(
																															"#pageNumInput")
																															.val(
																																	num);
																													core
																															.navigatePage1(
																																	element,
																																	num - 1);
																												}

																											}
																										}

																									}))

															)
															.append(
																	$(
																			'<span role="button" class="nav-link nav-now"/>')
																			.html(
																					'&#9679;')
																			.click(
																					function() {
																						core
																								.navigateTo(
																										element,
																										'now');
																					}))
															.append(
																	$(
																			'<span role="button" class="nav-link nav-prev-week"/>')
																			.html(
																					'&lt;&lt;')
																			.click(
																					function() {
																						if (settings.scale === 'hours') {
																							core
																									.navigateTo(
																											element,
																											tools
																													.getCellSize() * 8);
																						} else if (settings.scale === 'days') {
																							core
																									.navigateTo(
																											element,
																											tools
																													.getCellSize() * 30);
																						} else if (settings.scale === 'weeks') {
																							core
																									.navigateTo(
																											element,
																											tools
																													.getCellSize() * 12);
																						} else if (settings.scale === 'months') {
																							core
																									.navigateTo(
																											element,
																											tools
																													.getCellSize() * 6);
																						}
																					}))
															.append(
																	$(
																			'<span role="button" class="nav-link nav-prev-day"/>')
																			.html(
																					'&lt;')
																			.click(
																					function() {
																						if (settings.scale === 'hours') {
																							core
																									.navigateTo(
																											element,
																											tools
																													.getCellSize() * 4);
																						} else if (settings.scale === 'days') {
																							core
																									.navigateTo(
																											element,
																											tools
																													.getCellSize() * 7);
																						} else if (settings.scale === 'weeks') {
																							core
																									.navigateTo(
																											element,
																											tools
																													.getCellSize() * 4);
																						} else if (settings.scale === 'months') {
																							core
																									.navigateTo(
																											element,
																											tools
																													.getCellSize() * 3);
																						}
																					})))
											.append(
													$(
															'<div class="nav-slider-content" />')
															.append(
																	$(
																			'<div class="nav-slider-bar" />')
																			.append(
																					$('<a class="nav-slider-button" />'))
																			.mousedown(
																					function(
																							e) {
																						if (e.preventDefault) {
																							e
																									.preventDefault();
																						}
																						element.scrollNavigation.scrollerMouseDown = true;
																						core
																								.sliderScroll(
																										element,
																										e);//通过滑块控制图表
																					})
																			.mousemove(
																					function(
																							e) {
																						if (element.scrollNavigation.scrollerMouseDown) {
																							core
																									.sliderScroll(
																											element,
																											e);
																						}
																					})))
											.append(
													$(
															'<div class="nav-slider-right" />')
															.append(
																	$(
																			'<span role="button" class="nav-link nav-next-day"/>')
																			.html(
																					'&gt;')
																			.click(
																					function() {
																						if (settings.scale === 'hours') {
																							core
																									.navigateTo(
																											element,
																											tools
																													.getCellSize()
																													* -4);
																						} else if (settings.scale === 'days') {
																							core
																									.navigateTo(
																											element,
																											tools
																													.getCellSize()
																													* -7);
																						} else if (settings.scale === 'weeks') {
																							core
																									.navigateTo(
																											element,
																											tools
																													.getCellSize()
																													* -4);
																						} else if (settings.scale === 'months') {
																							core
																									.navigateTo(
																											element,
																											tools
																													.getCellSize()
																													* -3);
																						}
																					}))
															.append(
																	$(
																			'<span role="button" class="nav-link nav-next-week"/>')
																			.html(
																					'&gt;&gt;')
																			.click(
																					function() {
																						if (settings.scale === 'hours') {
																							core
																									.navigateTo(
																											element,
																											tools
																													.getCellSize()
																													* -8);
																						} else if (settings.scale === 'days') {
																							core
																									.navigateTo(
																											element,
																											tools
																													.getCellSize()
																													* -30);
																						} else if (settings.scale === 'weeks') {
																							core
																									.navigateTo(
																											element,
																											tools
																													.getCellSize()
																													* -12);
																						} else if (settings.scale === 'months') {
																							core
																									.navigateTo(
																											element,
																											tools
																													.getCellSize()
																													* -6);
																						}
																					}))
															.append(
																	$(
																			'<span role="button" class="nav-link nav-zoomIn"/>')
																			.html(
																					'&#43;')
																			//+
																			.click(
																					function() {
																						core
																								.zoomInOut(
																										element,
																										-1);
																					}))
															.append(
																	$(
																			'<span role="button" class="nav-link nav-zoomOut"/>')
																			.html(
																					'&#45;')
																			.click(
																					function() {
																						core
																								.zoomInOut(
																										element,
																										1);
																					}))));
					$(document).mouseup(function() {
						element.scrollNavigation.scrollerMouseDown = false;
					});
				} else {
					/* Navigation panel */
					ganttNavigate = $('<div class="navigate" />')
							.append(
									$(
											'<span role="button" class="nav-link nav-page-back"/>')
											.html('&lt;').click(function() {
												core.navigatePage(element, -1);
											}))
							.append(
									$('<div class="page-number"/>')
											.append(
													$('<span/>')
															.html(
																	element.pageNum
																			+ 1
																			+ ' of '
																			+ element.pageCount)))
							.append('<input type="text" value="1" ')
							.append(
									$(
											'<span role="button" class="nav-link nav-page-next"/>')
											.html('&gt;').click(function() {
												core.navigatePage(element, 1);
											}))
							.append(
									$(
											'<span role="button" class="nav-link nav-begin"/>')
											.html('&#124;&lt;').click(
													function() {
														core.navigateTo(
																element,
																'begin');
													}))
							.append(
									$(
											'<span role="button" class="nav-link nav-prev-week"/>')
											.html('&lt;&lt;')
											.click(
													function() {
														core
																.navigateTo(
																		element,
																		tools
																				.getCellSize() * 7);
													}))
							.append(
									$(
											'<span role="button" class="nav-link nav-prev-day"/>')
											.html('&lt;')
											.click(
													function() {
														core
																.navigateTo(
																		element,
																		tools
																				.getCellSize());
													}))
							.append(
									$(
											'<span role="button" class="nav-link nav-now"/>')
											.html('&#9679;')
											.click(
													function() {
														core.navigateTo(
																element, 'now');
													}))
							.append(
									$(
											'<span role="button" class="nav-link nav-next-day"/>')
											.html('&gt;')
											.click(
													function() {
														core
																.navigateTo(
																		element,
																		tools
																				.getCellSize()
																				* -1);
													}))
							.append(
									$(
											'<span role="button" class="nav-link nav-next-week"/>')
											.html('&gt;&gt;')
											.click(
													function() {
														core
																.navigateTo(
																		element,
																		tools
																				.getCellSize()
																				* -7);
													}))
							.append(
									$(
											'<span role="button" class="nav-link nav-end"/>')
											.html('&gt;&#124;')
											.click(
													function() {
														core.navigateTo(
																element, 'end');
													}))
							.append(
									$(
											'<span role="button" class="nav-link nav-zoomIn"/>')
											.html('&#43;')//+
											.click(function() {
												core.zoomInOut(element, -1);
											}))
							.append(
									$(
											'<span role="button" class="nav-link nav-zoomOut"/>')//-
											.html('&#45;').click(function() {
												core.zoomInOut(element, 1);
											}));
				}
				return $('<div class="bottom"/>').append(ganttNavigate);
			},
			createProgressBar : function(days, cls, desc, label, dataObj) {//创建进度条    
				//   days:宽度格数    ,              
				var cellWidth = tools.getCellSize();
				var barMarg = tools.getProgressBarMargin() || 0;
				var bar = $(
						'<div class="bar"><div class="fn-label">' + label + '</div></div>')
						.addClass(cls).css( {
							width : ((cellWidth * days) - barMarg) + 5
						}).data("dataObj", dataObj);
				if (desc) {
					bar.mouseover(
							function(e) {
								var hint = $('<div class="fn-gantt-hint" />')
										.html(desc);
								$("body").append(hint);
								hint.css("left", e.pageX);
								hint.css("top", e.pageY);
								hint.show();
							}).mouseout(function() {
						$(".fn-gantt-hint").remove();
					}).mousemove(function(e) {
						$(".fn-gantt-hint").css("left", e.pageX);
						$(".fn-gantt-hint").css("top", e.pageY + 15);
					});
				}
				bar.click(function(e) {
					e.stopPropagation();
					settings.onItemClick($(this).data("dataObj"));
				});
				return bar;
			},
			markNow : function(element) {//移除”wd“class添加”today“class到当前的scale模式
				switch (settings.scale) {
				case "weeks":
					var cd = Date.parse(new Date());
					cd = (Math.floor(cd / 36400000) * 36400000);
					$(element).find(':findweek("' + cd + '")')
							.removeClass('wd').addClass('today');
					break;
				case "months":
					$(element).find(
							':findmonth("' + new Date().getTime() + '")')
							.removeClass('wd').addClass('today');
					break;
				default:
					var cd = Date.parse(new Date());
					cd = (Math.floor(cd / 36400000) * 36400000);
					$(element).find(':findday("' + cd + '")').removeClass('wd')
							.addClass('today');
					break;
				}
			},

			fillData : function(element, datapanel, leftpanel) {//填充图表，解析数据并填充到panel
				var invertColor = function(colStr) {
					try {
						colStr = colStr.replace("rgb(", "").replace(")", "");
						var rgbArr = colStr.split(",");
						var R = parseInt(rgbArr[0], 10);
						var G = parseInt(rgbArr[1], 10);
						var B = parseInt(rgbArr[2], 10);
						var gray = Math
								.round(
										(255 - (0.299 * R + 0.587 * G + 0.114 * B)) * 0.9,
										1);
						return "rgb(" + gray + ", " + gray + ", " + gray + ")";
					} catch (err) {
						return "";
					}
				};
				$
						.each(
								element.data,
								function(i, entry) {//遍历处理element.data，可以是数组、DOM、json等，取决于直接给定或者ajax返回的类型,i是当前元素的位置，entry是值。
									if (i >= element.pageNum
											* settings.itemsPerPage
											&& i < (element.pageNum
													* settings.itemsPerPage + settings.itemsPerPage)) {
										//itemsPerPage分页的每页数据行数 i>=0 && i<6  i>=6 && i<12

										$
												.each(
														entry.values,
														function(j, day) {
															var _bar = null;
															switch (settings.scale) {
															case "hours":
																var dFrom = tools
																		.genId(
																				tools
																						.dateDeserialize(
																								day.from)
																						.getTime(),
																				element.scaleStep);
																var from = $(
																		element)
																		.find(
																				'#dh-' + dFrom);

																var dTo = tools
																		.genId(
																				tools
																						.dateDeserialize(
																								day.to)
																						.getTime(),
																				element.scaleStep);
																var to = $(
																		element)
																		.find(
																				'#dh-' + dTo);

																var cFrom = from
																		.attr("offset");
																var cTo = to
																		.attr("offset");
																var dl = Math
																		.floor((cTo - cFrom)
																				/ tools
																						.getCellSize()) + 1;

																_bar = core
																		.createProgressBar(
																				dl,
																				day.customClass ? day.customClass
																						: "",
																				day.desc ? day.desc
																						: "",
																				day.label ? day.label
																						: "",
																				day.dataObj ? day.dataObj
																						: null);

																// find row
																var topEl = $(
																		element)
																		.find(
																				"#rowheader"
																						+ i);

																var top = tools
																		.getCellSize()
																		* 5
																		+ 2
																		+ parseInt(
																				topEl
																						.attr("offset"),
																				10);
																_bar
																		.css( {
																			'margin-top' : top,
																			'margin-left' : Math
																					.floor(cFrom)
																		});

																//datapanel.append(_bar);
																break;
															case "weeks":
																var dtFrom = tools
																		.dateDeserialize(day.from);//当前页进度条起始时间集合
																var dtTo = tools
																		.dateDeserialize(day.to);
																//getDate():从 Date 对象返回一个月中的某一天 (1 ~ 31)。

																if (dtFrom
																		.getDate() <= 3
																		&& dtFrom
																				.getMonth() === 0) {
																	dtFrom
																			.setDate(dtFrom
																					.getDate() + 4);
																}

																if (dtFrom
																		.getDate() <= 3
																		&& dtFrom
																				.getMonth() === 0) {
																	dtFrom
																			.setDate(dtFrom
																					.getDate() + 4);
																}

																if (dtTo
																		.getDate() <= 3
																		&& dtTo
																				.getMonth() === 0) {
																	dtTo
																			.setDate(dtTo
																					.getDate() + 4);
																}

																var from = $(
																		element)
																		.find(
																				"#"
																						+ dtFrom
																								.getWeekId());

																var cFrom = from
																		.attr("offset");//控制margin-left

																var to = $(
																		element)
																		.find(
																				"#"
																						+ dtTo
																								.getWeekId());
																var cTo = to
																		.attr("offset");

																//Math.round():可把一个数字舍入为最接近的整数。
																var dl = Math
																		.round((cTo - cFrom)
																				/ tools
																						.getCellSize()) + 1;//进度条所占单元格数
																_bar = core
																		.createProgressBar(
																				dl,
																				day.customClass ? day.customClass
																						: "",
																				day.desc ? day.desc
																						: "",
																				day.label ? day.label
																						: "",
																				day.dataObj ? day.dataObj
																						: null);

																// find row
																var topEl = $(
																		element)
																		.find(
																				"#rowheader"
																						+ i);

																var top = tools
																		.getCellSize()
																		* 3
																		+ 2
																		+ parseInt(
																				topEl
																						.attr("offset"),
																				10);

																_bar
																		.css( {
																			'margin-top' : top,
																			'margin-left' : Math
																					.floor(cFrom)
																		});
																//console.log("cFrom",cFrom);
																datapanel
																		.append(_bar);
																break;
															case "months":

																var dtFrom = tools
																		.dateDeserialize(day.from);
																var dtTo = tools
																		.dateDeserialize(day.to);

																if (dtFrom
																		.getDate() <= 3
																		&& dtFrom
																				.getMonth() === 0) {
																	dtFrom
																			.setDate(dtFrom
																					.getDate() + 4);
																}

																if (dtFrom
																		.getDate() <= 3
																		&& dtFrom
																				.getMonth() === 0) {
																	dtFrom
																			.setDate(dtFrom
																					.getDate() + 4);
																}

																if (dtTo
																		.getDate() <= 3
																		&& dtTo
																				.getMonth() === 0) {
																	dtTo
																			.setDate(dtTo
																					.getDate() + 4);
																}

																var from = $(
																		element)
																		.find(
																				"#dh-"
																						+ tools
																								.genId(dtFrom
																										.getTime()));

																var cFrom = from
																		.attr("offset");
																var to = $(
																		element)
																		.find(
																				"#dh-"
																						+ tools
																								.genId(dtTo
																										.getTime()));
																var cTo = to
																		.attr("offset");
																var dl = Math
																		.round((cTo - cFrom)
																				/ tools
																						.getCellSize()) + 1;

																_bar = core
																		.createProgressBar(
																				dl,
																				day.customClass ? day.customClass
																						: "",
																				day.desc ? day.desc
																						: "",
																				day.label ? day.label
																						: "",
																				day.dataObj ? day.dataObj
																						: null);

																// find row
																var topEl = $(
																		element)
																		.find(
																				"#rowheader"
																						+ i);

																var top = tools
																		.getCellSize()
																		* 2
																		+ 2
																		+ parseInt(
																				topEl
																						.attr("offset"),
																				10);
																_bar
																		.css( {
																			'margin-top' : top,
																			'margin-left' : Math
																					.floor(cFrom)
																		});

																datapanel
																		.append(_bar);
																break;

															// Days 
															default:
																var dFrom = tools
																		.genId(tools
																				.dateDeserialize(
																						day.from)
																				.getTime());
																var dTo = tools
																		.genId(tools
																				.dateDeserialize(
																						day.to)
																				.getTime());

																var from = $(
																		element)
																		.find(
																				"#dh-"
																						+ dFrom);
																var cFrom = from
																		.attr("offset");

																var dl = Math
																		.floor(((dTo / 1000) - (dFrom / 1000)) / 86400) + 1;
																_bar = core
																		.createProgressBar(
																				dl,
																				day.customClass ? day.customClass
																						: "",
																				day.desc ? day.desc
																						: "",
																				day.label ? day.label
																						: "",
																				day.dataObj ? day.dataObj
																						: null);

																// find row
																var topEl = $(
																		element)
																		.find(
																				"#rowheader"
																						+ i);

																var top = tools
																		.getCellSize()
																		* 4
																		+ 2
																		+ parseInt(
																				topEl
																						.attr("offset"),
																				10);
																_bar
																		.css( {
																			'margin-top' : top,
																			'margin-left' : Math
																					.floor(cFrom)
																		});

																datapanel
																		.append(_bar);

																break;
															}
															var $l = _bar
																	.find(".fn-label");
															if ($l
																	&& _bar.length) {
																var gray = invertColor(_bar[0].style.backgroundColor);
																$l.css("color",
																		gray);
															} else if ($l) {
																$l.css("color",
																		"");
															}
														});

									}
								});
			},

			navigateTo : function(element, val) {
				var $rightPanel = $(element).find(".fn-gantt .rightPanel");
				var $dataPanel = $rightPanel.find(".dataPanel");
				$dataPanel.click = function() {
					alert(arguments.join(""));
				};
				var rightPanelWidth = $rightPanel.width();
				var dataPanelWidth = $dataPanel.width();

				switch (val) {
				case "begin":
					$dataPanel.animate( {
						"margin-left" : "0px"
					}, "fast", function() {
						core.repositionLabel(element);
					});
					element.scrollNavigation.panelMargin = 0;
					break;
				case "end":
					var mLeft = dataPanelWidth - rightPanelWidth;
					element.scrollNavigation.panelMargin = mLeft * -1;
					$dataPanel.animate( {
						"margin-left" : "-" + mLeft + "px"
					}, "fast", function() {
						core.repositionLabel(element);
					});
					break;
				case "now":
					if (!element.scrollNavigation.canScroll
							|| !$dataPanel.find(".today").length) {
						return false;
					}
					var max_left = (dataPanelWidth - rightPanelWidth) * -1;
					var cur_marg = $dataPanel.css("margin-left").replace("px",
							"");
					var val = $dataPanel.find(".today").offset().left
							- $dataPanel.offset().left;
					val *= -1;
					if (val > 0) {
						val = 0;
					} else if (val < max_left) {
						val = max_left;
					}
					$dataPanel.animate( {
						"margin-left" : val + "px"
					}, "fast", core.repositionLabel(element));
					element.scrollNavigation.panelMargin = val;
					break;
				default:
					var max_left = (dataPanelWidth - rightPanelWidth) * -1;
					var cur_marg = $dataPanel.css("margin-left").replace("px",
							"");
					var val = parseInt(cur_marg, 10) + val;
					if (val <= 0 && val >= max_left) {
						$dataPanel.animate( {
							"margin-left" : val + "px"
						}, "fast", core.repositionLabel(element));
					}
					element.scrollNavigation.panelMargin = val;
					break;
				}

			},
			navigatePage : function(element, val) {//导航到指定的页面

				//alert("element.rowsNum / settings.itemsPerPage "+element.rowsNum / settings.itemsPerPage +"<br>"+"(element.pageNum + val)"+(element.pageNum + val));

				if ((element.pageNum + val) >= 0
						&& (element.pageNum + val) < Math.ceil(element.rowsNum
								/ settings.itemsPerPage)) {
					core.waitToggle(element, true, function() {
						element.pageNum += val;
						element.hPosition = $(".fn-gantt .dataPanel").css(
								"margin-left").replace("px", "");
						element.scaleOldWidth = false;
						//element.find("#pageNumInput").val((element.pageNum));
							core.init(element);
						});
				}
			},
			navigatePage1 : function(element, val) {//导航到指定的页面
				core.waitToggle(element, true, function() {
					element.pageNum = val;
					element.hPosition = $(".fn-gantt .dataPanel").css(
							"margin-left").replace("px", "");
					element.scaleOldWidth = false;
					core.init(element);

				});
				//}
			},
			zoomInOut : function(element, val) {//变更空间轴级次（zoom）
				core.waitToggle(element, true, function() {
					var zoomIn = (val < 0);
					var scaleSt = element.scaleStep + val * 3;
					scaleSt = scaleSt <= 1 ? 1 : scaleSt === 4 ? 3 : scaleSt;
					var scale = settings.scale;
					var headerRows = element.headerRows;
					if (settings.scale === "hours" && scaleSt >= 13) {
						scale = "days";
						headerRows = 4;
						scaleSt = 13;
					} else if (settings.scale === "days" && zoomIn) {
						scale = "hours";
						headerRows = 5;
						scaleSt = 12;
					} else if (settings.scale === "days" && !zoomIn) {
						//show();
						scale = "weeks";
						headerRows = 3;
						scaleSt = 13;
					} else if (settings.scale === "weeks" && !zoomIn) {
						scale = "months";
						headerRows = 2;
						scaleSt = 14;
					} else if (settings.scale === "weeks" && zoomIn) {
						scale = "days";
						headerRows = 4;
						scaleSt = 13;
					} else if (settings.scale === "months" && zoomIn) {
						scale = "weeks";
						headerRows = 3;
						scaleSt = 13;
					}

					if ((zoomIn && $.inArray(scale, scales) < $.inArray(
							settings.minScale, scales))
							|| (!zoomIn && $.inArray(scale, scales) > $
									.inArray(settings.maxScale, scales))) {
						core.init(element);
						return;
					}
					element.scaleStep = scaleSt;
					settings.scale = scale;
					element.headerRows = headerRows;
					var $rightPanel = $(element).find(".fn-gantt .rightPanel");
					var $dataPanel = $rightPanel.find(".dataPanel");
					element.hPosition = $dataPanel.css("margin-left").replace(
							"px", "");
					element.scaleOldWidth = ($dataPanel.width() - $rightPanel
							.width());

					if (settings.useCookie) {
						$.cookie(this.cookieKey + "CurrentScale",
								settings.scale);
						// reset scrollPos
						$.cookie(this.cookieKey + "ScrollPos", null);
					}
					core.init(element);
				});
			},
			mouseScroll : function(element, e) {//通过鼠标移动图表

				var $dataPanel = $(element).find(".fn-gantt .dataPanel");
				$dataPanel.css("cursor", "move");
				var bPos = $dataPanel.offset();

				var mPos = element.scrollNavigation.mouseX === null ? e.pageX
						: element.scrollNavigation.mouseX;
				var delta = e.pageX - mPos;
				element.scrollNavigation.mouseX = e.pageX;

				core.scrollPanel(element, delta);//更新滚动panel的margin

				clearTimeout(element.scrollNavigation.repositionDelay);
				element.scrollNavigation.repositionDelay = setTimeout(
						core.repositionLabel, 50, element);
			},
			//通过鼠标滚轮移动图:一次移动-50
			wheelScroll : function(element, e) {

				var delta = e.detail ? e.detail * (-50)
						: e.wheelDelta / 120 * 50;

				core.scrollPanel(element, delta);

				clearTimeout(element.scrollNavigation.repositionDelay);
				element.scrollNavigation.repositionDelay = setTimeout(
						core.repositionLabel, 50, element);

				if (e.preventDefault) {
					e.preventDefault();
				} else {
					return false;
				}
			},
			//通过滑块控制图表
			sliderScroll : function(element, e) {
				//alert("通过滑块控制图表");
				var $sliderBar = $(element).find(".nav-slider-bar");
				var $sliderBarBtn = $sliderBar.find(".nav-slider-button");
				var $rightPanel = $(element).find(".fn-gantt .rightPanel");
				var $dataPanel = $rightPanel.find(".dataPanel");

				var bPos = $sliderBar.offset();

				var bWidth = $sliderBar.width();
				var wButton = $sliderBarBtn.width();

				var pos, mLeft;

				if ((e.pageX >= bPos.left) && (e.pageX <= bPos.left + bWidth)) {
					pos = e.pageX - bPos.left;
					pos = pos - wButton / 2;
					$sliderBarBtn.css("left", pos);

					mLeft = $dataPanel.width() - $rightPanel.width();

					var pPos = pos * mLeft / bWidth * -1;
					if (pPos >= 0) {
						$dataPanel.css("margin-left", "0px");
						element.scrollNavigation.panelMargin = 0;
					} else if (pos >= bWidth - (wButton * 1)) {
						$dataPanel.css("margin-left", mLeft * -1 + "px");
						element.scrollNavigation.panelMargin = mLeft * -1;
					} else {
						$dataPanel.css("margin-left", pPos + "px");
						element.scrollNavigation.panelMargin = pPos;
					}
					clearTimeout(element.scrollNavigation.repositionDelay);
					element.scrollNavigation.repositionDelay = setTimeout(
							core.repositionLabel, 5, element);
				}
			},
			scrollPanel : function(element, delta) {//更新滚动panel的margin
				if (!element.scrollNavigation.canScroll) {
					return false;
				}

				var _panelMargin = parseInt(
						element.scrollNavigation.panelMargin, 10)
						+ delta;

				if (_panelMargin > 0) {
					element.scrollNavigation.panelMargin = 0;
					$(element).find(".fn-gantt .dataPanel").css("margin-left",
							element.scrollNavigation.panelMargin + "px");
				} else if (_panelMargin < element.scrollNavigation.panelMaxPos
						* -1) {
					element.scrollNavigation.panelMargin = element.scrollNavigation.panelMaxPos
							* -1;
					$(element).find(".fn-gantt .dataPanel").css("margin-left",
							element.scrollNavigation.panelMargin + "px");
				} else {
					element.scrollNavigation.panelMargin = _panelMargin;
					$(element).find(".fn-gantt .dataPanel").css("margin-left",
							element.scrollNavigation.panelMargin + "px");
				}
				core.synchronizeScroller(element);
			},
			synchronizeScroller : function(element) {//同步滚动

				if (settings.navigate === "scroll") {
					var $rightPanel = $(element).find(".fn-gantt .rightPanel");
					var $dataPanel = $rightPanel.find(".dataPanel");
					var $sliderBar = $(element).find(".nav-slider-bar");
					var $sliderBtn = $sliderBar.find(".nav-slider-button");

					var bWidth = $sliderBar.width();
					var wButton = $sliderBtn.width();

					var mLeft = $dataPanel.width() - $rightPanel.width();
					var hPos = 0;
					if ($dataPanel.css("margin-left")) {
						hPos = $dataPanel.css("margin-left").replace("px", "");
					}
					var pos = hPos * bWidth / mLeft - $sliderBtn.width() * 0.25;
					pos = pos > 0 ? 0
							: (pos * -1 >= bWidth - (wButton * 0.75)) ? (bWidth - (wButton * 1.25))
									* -1
									: pos;
					$sliderBtn.css("left", pos * -1);
				}
			},
			repositionLabel : function(element) {//重新定位数据标签

				setTimeout(function() {
					var $dataPanel;
					if (!element) {
						$dataPanel = $(".fn-gantt .rightPanel .dataPanel");
					} else {
						var $rightPanel = $(element).find(
								".fn-gantt .rightPanel");
						$dataPanel = $rightPanel.find(".dataPanel");
					}

					if (settings.useCookie) {
						$.cookie(this.cookieKey + "ScrollPos", $dataPanel.css(
								"margin-left").replace("px", ""));
					}
				}, 500);
			},
			waitToggle : function(element, show, fn) {//切换等待
				if (show) {
					var eo = $(element).offset();
					var ew = $(element).outerWidth();
					var eh = $(element).outerHeight();

					if (!element.loader) {
						element.loader = $('<div class="fn-gantt-loader" style="position: absolute; top: '
								+ eo.top
								+ 'px; left: '
								+ eo.left
								+ 'px; width: '
								+ ew
								+ 'px; height: '
								+ eh
								+ 'px;">'
								+ '<div class="fn-gantt-loader-spinner"><span>'
								+ settings.waitText + '</span></div></div>');
					}
					$("body").append(element.loader);
					setTimeout(fn, 100);

				} else {
					if (element.loader) {
						element.loader.remove();
					}
					element.loader = null;
				}
			}
		};

		/**
		 * Additional functions
		 */
		var tools = {//实用工具类功能

			getMaxDate : function(element) {//返回最大可能的日期在scale值的标准下
				var maxDate = null;
				//console.log("element.data",element.data);Array[8];
				$.each(element.data, function(i, entry) {//Array[8];
							$.each(entry.values, function(i, date) {
								maxDate = maxDate < tools
										.dateDeserialize(date.to) ? tools
										.dateDeserialize(date.to) : maxDate;

							});
						});
				// console.log("maxDate000:",maxDate);//                       
				switch (settings.scale) {
				case "hours":
					maxDate.setHours(Math.ceil((maxDate.getHours())
							/ element.scaleStep)
							* element.scaleStep);
					maxDate
							.setHours(maxDate.getHours() + element.scaleStep
									* 3);
					break;
				case "weeks":
					var bd = new Date(maxDate.getTime());
					var bd = new Date(bd.setDate(bd.getDate() + 3 * 7));
					var md = Math.floor(bd.getDate() / 7) * 7;
					maxDate = new Date(bd.getFullYear(), bd.getMonth(),
							md === 0 ? 4 : md - 3);
					break;
				case "months"://月+2
					var bd = new Date(maxDate.getFullYear(),
							maxDate.getMonth(), 1);
					bd.setMonth(bd.getMonth() + 2);
					maxDate = new Date(bd.getFullYear(), bd.getMonth(), 1);
					break;
				default://天+3
					maxDate.setHours(0);
					maxDate.setDate(maxDate.getDate() + 3);
					break;
				}
				//console.log("maxDate1111:",maxDate);
				return maxDate;
			},
			getMinDate : function(element) {//返回最小可能的日期在scale值的标准下
				var minDate = null;
				$.each(element.data, function(i, entry) {
					$.each(entry.values, function(i, date) {
						minDate = minDate > tools.dateDeserialize(date.from)
								|| minDate === null ? tools
								.dateDeserialize(date.from) : minDate;
					});
				});
				switch (settings.scale) {
				case "hours":
					minDate.setHours(Math.floor((minDate.getHours())
							/ element.scaleStep)
							* element.scaleStep);
					minDate
							.setHours(minDate.getHours() - element.scaleStep
									* 3);
					break;
				case "weeks":
					var bd = new Date(minDate.getTime());
					var bd = new Date(bd.setDate(bd.getDate() - 3 * 7));
					var md = Math.floor(bd.getDate() / 7) * 7;
					minDate = new Date(bd.getFullYear(), bd.getMonth(),
							md === 0 ? 4 : md - 3);

					break;
				case "months":
					var bd = new Date(minDate.getFullYear(),
							minDate.getMonth(), 1);
					bd.setMonth(bd.getMonth() - 3);
					minDate = new Date(bd.getFullYear(), bd.getMonth(), 1);
					break;
				default:
					minDate.setHours(0);
					minDate.setDate(minDate.getDate() - 3);
					break;
				}
				return minDate;
			},
			parseDateRange : function(from, to) {
				var current = new Date(from.getTime());
				var end = new Date(to.getTime());
				var ret = [];
				var i = 0;
				do {
					ret[i++] = new Date(current.getTime());
					current.setDate(current.getDate() + 1);
				} while (current.getTime() <= to.getTime());
				return ret;

			},
			parseTimeRange : function(from, to, scaleStep) {
				var current = new Date(from);
				var end = new Date(to);
				var ret = [];
				var i = 0;
				do {
					ret[i] = new Date(current.getTime());
					current.setHours(current.getHours() + scaleStep);
					current.setHours(Math.floor((current.getHours())
							/ scaleStep)
							* scaleStep);

					if (current.getDay() !== ret[i].getDay()) {
						current.setHours(0);
					}

					i++;
				} while (current.getTime() <= to.getTime());
				return ret;
			},
			parseWeeksRange : function(from, to) {//返回一个日期对象数组介于from和to，时间单位为周
				var current = new Date(from);
				var end = new Date(to);

				var ret = [];
				var i = 0;

				do {

					if (current.getDay() === 0) {
						ret[i++] = current.getDayForWeek();
					}
					current.setDate(current.getDate() + 1);
				} while (current.getTime() <= to.getTime());

				return ret;

			},
			parseMonthsRange : function(from, to) {//返回一个日期对象数组介于from和to，时间单位为月
				//				console.log("parseMonthsRange:from"+from);
				//				console.log("parseMonthsRange:to"+to);
				var current = new Date(from);
				var end = new Date(to);

				var ret = [];
				var i = 0;
				do {
					ret[i++] = new Date(current.getFullYear(), current
							.getMonth(), 1);
					current.setMonth(current.getMonth() + 1);
				} while (current.getTime() <= to.getTime());

				return ret;
			},
			dateDeserialize : function(dateStr) {//从字符串反序列化成日期
				//         /Date()/   dateStr.replace(/\//g, " ")):把所有\变成""  
				var date = eval("new" + dateStr.replace(/\//g, " "));
				//eval()计算某个字符串，并执行其中的的 JavaScript 代码。 new Date();
				return new Date(date.getUTCFullYear(), date.getUTCMonth(), date
						.getUTCDate(), date.getUTCHours(), date.getUTCMinutes());
			},

			genId : function(ticks) {
				var t = new Date(ticks);
				switch (settings.scale) {
				case "hours":
					var hour = t.getHours();
					if (arguments.length >= 2) {
						hour = (Math.floor((t.getHours()) / arguments[1]) * arguments[1]);
					}
					return (new Date(t.getFullYear(), t.getMonth(),
							t.getDate(), hour)).getTime();
				case "weeks":
					var y = t.getFullYear();
					var w = t.getDayForWeek().getWeekOfYear();
					var m = t.getMonth();
					if (m === 11 && w === 1) {
						y++;
					}
					return y + "-" + w;
				case "months":
					return t.getFullYear() + "-" + t.getMonth();
				default:
					return (new Date(t.getFullYear(), t.getMonth(), t.getDate()))
							.getTime();
				}
			},
			_getCellSize : null,
			getCellSize : function() {
				if (!tools._getCellSize) {
					$("body")
							.append(
									$('<div style="display: none; position: absolute;" class="fn-gantt" id="measureCellWidth"><div class="row"></div></div>'));
					tools._getCellSize = $("#measureCellWidth .row").height();
					$("#measureCellWidth").empty().remove();
				}
				return tools._getCellSize;
			},
			getRightPanelSize : function() {
				$("body")
						.append(
								$('<div style="display: none; position: absolute;" class="fn-gantt" id="measureCellWidth"><div class="rightPanel"></div></div>'));
				var ret = $("#measureCellWidth .rightPanel").height();
				$("#measureCellWidth").empty().remove();
				return ret;
			},
			getPageHeight : function(element) {
				return element.pageNum + 1 === element.pageCount ? element.rowsOnLastPage
						* tools.getCellSize()
						: settings.itemsPerPage * tools.getCellSize();
			},
			_getProgressBarMargin : null,
			getProgressBarMargin : function() {//获取当前进度条的margin大小
				if (!tools._getProgressBarMargin) {
					$("body")
							.append(
									$('<div style="display: none; position: absolute;" id="measureBarWidth" ><div class="fn-gantt"><div class="rightPanel"><div class="dataPanel"><div class="row day"><div class="bar" /></div></div></div></div></div>'));
					tools._getProgressBarMargin = parseInt($(
							"#measureBarWidth .fn-gantt .rightPanel .day .bar")
							.css("margin-left").replace("px", ""), 10);
					tools._getProgressBarMargin += parseInt($(
							"#measureBarWidth .fn-gantt .rightPanel .day .bar")
							.css("margin-right").replace("px", ""), 10);
					$("#measureBarWidth").empty().remove();
				}
				return tools._getProgressBarMargin;
			}
		};

		this.each(function() {//甘特图组件对象遍历器（负责执行初始化操作）
					//gantt div
					/**
					 * Extend options with default values
					 */
					if (options) {
						$.extend(settings, options);
					}

					this.data = null; // Recived data
					this.pageNum = 0; // Current page number
					
					
					//修改，删除跳转
					if (gantt_pageNum != null && gantt_pageNum != ""
							&& gantt_pageNum != undefined) {
						this.pageNum = parseInt(gantt_pageNum);
					}
					
					
					
					this.pageCount = 0; // Aviable pages count
					this.rowsOnLastPage = 0; // How many rows on last page
					this.rowsNum = 0; //
					this.hPosition = 0; // Current position on diagram (Horizontal)
					this.dateStart = null;
					this.dateEnd = null;
					this.scrollClicked = false;
					this.scaleOldWidth = null;
					this.headerRows = null;

					if (settings.useCookie) {
						var sc = $.cookie(this.cookieKey + "CurrentScale");
						if (sc) {
							settings.scale = $.cookie(this.cookieKey
									+ "CurrentScale");
						} else {
							$.cookie(this.cookieKey + "CurrentScale",
									settings.scale);
						}
					}
					
					switch (settings.scale) {
					case "hours":
						this.headerRows = 5;
						this.scaleStep = 1;
						break;
					case "weeks":
						this.headerRows = 3;
						this.scaleStep = 13;
						break;
					case "months":
						this.headerRows = 2;
						this.scaleStep = 14;
						break;
					default:
						this.headerRows = 4;
						this.scaleStep = 13;
						break;
					}

					this.scrollNavigation = {
						panelMouseDown : false,
						scrollerMouseDown : false,
						mouseX : null,
						panelMargin : 0,
						repositionDelay : 0,
						panelMaxPos : 0,
						canScroll : true
					};

					this.gantt = null;
					this.loader = null;

					core.create(this);

				});

	};
})(jQuery);
