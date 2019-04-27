function son_li_click(i, mfId) {
	var files_ul = $("#" + mfId + " ul").length;
	if (files_ul <= 0) {
		var files_li_id = mfId;
		$
				.ajax( {
					url : 'ModuleFunctionAction!findMfByUserAndFId.action',
					type : 'post',
					dataType : 'json',
					data : {
						id : files_li_id
					},
					cache : true,
					success : function(mfList) {
						if (mfList.length > 0) {
							var ulhtml = "<ul>";
							$(mfList)
									.each(
											function(j, n) {
												/**填充数据****/
												ulhtml += "<li onclick='son_li_click("
														+ i
														+ ","
														+ n.id
														+ ")' id='"
														+ n.id
														+ "' class='firstnode'><a href='#'>"
														+ n.functionName
														+ "</a></li>";
											})
							ulhtml += "</ul>";
							$("#" + mfId).append(ulhtml);

						} else {
							$
									.ajax( {
										url : 'ModuleFunctionAction!findMfByIdForJson.action',
										type : 'post',
										dataType : 'json',
										data : {
											id : files_li_id,
											pageStatus : '3'
										},
										cache : true,
										success : function(mf) {
											$(".cenjinav p").html(mf[0]);
											$(".headercon strong").html(
													mf[1].functionName);
											$("#showAllPm").attr("href",
													mf[1].functionLink);//全屏
											$("#shuaxin").attr("href",
													mf[1].functionLink);//刷新
											$('#workMainIframe')
													.attr(
															"src",
															$('#workMainIframe')
																	.attr(
																			"data")
																	+ mf[1].functionLink);//功能地址
											$(".menucontainer:eq(" + i + ")")
													.hide();//将展开的功能明细隐藏
											$("#showMf").show();//主体展开
											$("#person").hide();//首页关闭
											/**重新加载导航条高度****/
											//						var height = document.body.scrollHeight;
											//						//获取窗口高度 
											//						var winHeight = 0;
											//						if (window.innerHeight) {
											//							winHeight = window.innerHeight;
											//						} else if ((document.body)
											//								&& (document.body.clientHeight))
											//							winHeight = document.body.clientHeight;
											//						alert(winHeight);
											//						$("#menu").css('height', winHeight);
										},
										error : function() {
											location.href = 'ModuleFunctionAction!findMfByUser.action';
										}
									});
						}
					},
					error : function() {
						location.href = 'ModuleFunctionAction!findMfByUser.action';
					}
				});
	}

}

function chagePageSize() {
	var height = document.body.scrollHeight;
	var width = document.body.clientWidth;
	var homepageWidth = $(".homepage").width();
	var centernavWidth = $(".centernav").width();

	//$(".centercontainer").css('width', 100 - (126 / width) * 100 + '%');
	//	$(".menucontainer").css('height', height);
	//	$(".inforight").css('width', 100 - (180 / homepageWidth) * 100 + '%');
	//	$(".cenjinav").css('width', 100 - (180 / centernavWidth) * 100 + '%');
}

/**当页面变动时,计算尺寸***/
window.onresize = function() {
	chagePageSize();
};

var menu_index;
var menu_index_all = 0;
var menu_size;
var activeIndex = "";
var clickcount = 1;
var oldvalue = "";
/**左侧导航logo**/
var arr1 = [ 'project', 'caigou', 'product', 'wl', 'finance', 'resource',
		'message', 'ybp' ];
var arr2 = [ 'projectactive', 'caigouactive', 'productactive', 'wlactive',
		'financeactive', 'resourceactive', 'messageactive', 'ybpactive' ];
function checkClick(i) {
	/**记录是否是再次点击****/
	var doubleClick = true;
	/**生成橙色logo****/
	activeIndex = "background:#33363f url(img/" + arr2[i]
			+ ".png) no-repeat 10px 15px;";
	/**将其他的li变为灰色logo****/
	$("#menu #list li").each(
			function(j) {
				oldvalue = "background:url(img/" + arr1[j]
						+ ".png) no-repeat 10px 15px;";
				$("#menu #list li:eq(" + j + ")").attr('style', oldvalue);
			});
	/**切换橙色logo****/
	$("#menu #list li:eq(" + i + ")").attr('style', activeIndex);

	$(".menucontainer:eq(" + i + ")").show();

	if (menu_index == i) {
		menu_size = 1;
	} else {
		menu_size = 0;
	}
	if (menu_index != i) {
		$(".menucontainer:eq(" + menu_index + ")").hide();
		menu_size = 0;
		menu_index = i;
	} else if (menu_index == i && menu_size == 1) {
		$(".menucontainer:eq(" + menu_index + ")").hide();
		menu_size = 0;
		menu_index = null;
		doubleClick = false;
		menu_index_all = i;
	}
	return doubleClick;
}

var loadZtree;
$(function() {
	chagePageSize();

	$("#menu #list li")
			.each(
					function(i) {
						/**初始化样式  灰色logo****/
						$("#menu #list li:eq(" + i + ")")
								.css(
										'background',
										'url(img/' + arr1[i] + '.png) no-repeat 10px 15px');

						$(this)
								.click(
										function() {

											var doubleClick = checkClick(i);
											/**1、判断是否存在ui即是否已经点击过
											 **2、不存在则开始查询对应
											 **/
											if (doubleClick) {
												$("#showOrCloseMenuDiv").css("left","431px");
												$("#marginDiv").css("left","426px");
												$(".centercontainer")
														.click(
																function() {
																	$(
																			".menucontainer:eq("
																					+ menu_index
																					+ ")")
																			.hide();
																	menu_size = 0;
																	$("#showOrCloseMenuDiv").css("left","5px");
																	$("#marginDiv").css("left","0px");
																	$("#menu").hide();;
																	clickcount=1;
																})
												if(menu_index_all!=i){
												/**
												 **2、不存在则开始查询对应数据，并加载ztree
												 **/
												var setting = {
													data : {
														simpleData : {
															enable : true
														}
													},
													callback : {
														beforeExpand: beforeExpand,
														onExpand: onExpand,
														onClick: onClick
													},
													view : {
														showIcon : false,
														showLine : true,
														dblClickExpand: false,
													}
												};
												/**获得li data 属性==id ,并根据id查询****/
												var menu_id = $(this).attr(
														'data');
												$
														.ajax( {
															url : 'ModuleFunctionAction!findUserMFByRootId.action',
															type : 'post',
															dataType : 'json',
															data : {
																"id" : menu_id
															},
															cache : true,
															success : function(
																	doc) {
																var zNodes = [];
																$(doc)
																		.each(
																				function() {
																					zNodes
																							.push( {
																								id : $(
																										this)
																										.attr(
																												'id'),
																								pId : $(
																										this)
																										.attr(
																												'fatherId'),
																								name : $(
																										this)
																										.attr(
																												'functionName'),
																								link : $(
																										this)
																										.attr(
																												'functionLink'),
																								liIndex : i,
																								open : false,
																							});

																				});
																loadZtree=$.fn.zTree
																		.init(
																				$(".files:eq("
																						+ i
																						+ ")"),
																				setting,
																				zNodes);
																/**去除ztree的个性设置****/
																$(
																		".files:eq("
																				+ i
																				+ ") ul")
																		.removeAttr(
																				"style");
																$(
																		".files:eq("
																				+ i
																				+ ") a")
																		.removeAttr(
																				"class");

																/**装载树形结构****/
																$(
																		".files:eq("
																				+ i
																				+ ")")
																		.tree();
															},
															error : function() {
																location.href = 'ModuleFunctionAction!findMfByUser.action';
															}
														});
													}
											}else{
												$("#showOrCloseMenuDiv").css("left","131px");
												$("#marginDiv").css("left","126px");
											}
										});

					});

	/**隐藏导航栏***/
	$(".togglebtn").each(function(i) {
		$(this).click(function() {
			$(".menucontainer:eq(" + menu_index + ")").hide();
			menu_size = 0;
			menu_index = null;
			$("#showOrCloseMenuDiv").css("left","131px");
			$("#marginDiv").css("left","126px");
		});

	});

	//点击回调函数
	function onClick(event, treeId, treeNode, clickFlag) {
		if (treeNode.link != "") {
			var files_li_id = treeNode.id;
			var liIndex = treeNode.liIndex;
			$.ajax( {
				url : 'ModuleFunctionAction!findMfByIdForJson.action',
				type : 'post',
				dataType : 'json',
				data : {
					id : files_li_id,
					pageStatus : '3'
				},
				cache : true,
				success : function(mf) {
					$(".cenjinav p").html(mf[0]);
					$(".headercon strong").html(mf[1].functionName);
					$("#showAllPm").attr("href",  mf[1].functionLink);//全屏
					$("#shuaxin").attr("href", mf[1].functionLink);//刷新
					$('#workMainIframe').attr(
							"src",
							$('#workMainIframe').attr("data")
									+ mf[1].functionLink);//功能地址
					$(".menucontainer:eq(" + menu_index + ")").hide();
					menu_index_all = menu_index;
					menu_size = 0;
					//menu_index = null;
					$("#menu").hide();
					$("#showOrCloseMenuDiv").css("left","5px");
					$("#marginDiv").css("left","0px");
					clickcount=1;
					$("#showMf").show();//主体展开
					$("#person").hide();//首页关闭
				},
				error : function() {
					location.href = 'ModuleFunctionAction!findMfByUser.action';
				}
			});
		}else{
			loadZtree.expandNode(treeNode);
		}
	}
	
	
	var curExpandNode = null;
		function beforeExpand(treeId, treeNode) {
			var pNode = curExpandNode ? curExpandNode.getParentNode():null;
			var treeNodeP = treeNode.parentTId ? treeNode.getParentNode():null;
			var zTree = loadZtree;
			for(var i=0, l=!treeNodeP ? 0:treeNodeP.children.length; i<l; i++ ) {
				if (treeNode !== treeNodeP.children[i]) {
					zTree.expandNode(treeNodeP.children[i], false);
				}
			}
			while (pNode) {
				if (pNode === treeNode) {
					break;
				}
				pNode = pNode.getParentNode();
			}
			if (!pNode) {
				singlePath(treeNode);
			}

		}
		function singlePath(newNode) {
			if (newNode === curExpandNode) return;
			if (curExpandNode && curExpandNode.open==true) {
				var zTree = loadZtree;
				if (newNode.parentTId === curExpandNode.parentTId) {
					zTree.expandNode(curExpandNode, false);
				} else {
					var newParents = [];
					while (newNode) {
						newNode = newNode.getParentNode();
						if (newNode === curExpandNode) {
							newParents = null;
							break;
						} else if (newNode) {
							newParents.push(newNode);
						}
					}
					if (newParents!=null) {
						var oldNode = curExpandNode;
						var oldParents = [];
						while (oldNode) {
							oldNode = oldNode.getParentNode();
							if (oldNode) {
								oldParents.push(oldNode);
							}
						}
						if (newParents.length>0) {
							zTree.expandNode(oldParents[Math.abs(oldParents.length-newParents.length)-1], false);
						} else {
							zTree.expandNode(oldParents[oldParents.length-1], false);
						}
					}
				}
			}
			curExpandNode = newNode;
		}

		function onExpand(event, treeId, treeNode) {
			curExpandNode = treeNode;
		}
		
		$("#showOrCloseMenu").click(function(){
			if(clickcount==0){
				$("#menu").hide();
				$(".menucontainer:eq(" + menu_index + ")").hide();
				clickcount=1;
				$("#showOrCloseMenuDiv").css("left","5px");
				$("#marginDiv").css("left","0px");
				$("#jiantou").html("》");
			}else{
				$("#menu").show();
				$(".menucontainer:eq(" + menu_index + ")").show();
				if(menu_index>0){
					$("#showOrCloseMenuDiv").css("left","431px");
					$("#marginDiv").css("left","426px");
				}else{
					$("#showOrCloseMenuDiv").css("left","131px");
					$("#marginDiv").css("left","126px");
				}
				clickcount=0;
				$("#jiantou").html("《");
			}
		})
	
});