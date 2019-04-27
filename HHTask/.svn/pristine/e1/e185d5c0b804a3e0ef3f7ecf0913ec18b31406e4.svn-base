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
<head>
	<%@include file="/util/sonHead.jsp"%>
	<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

#www_ablanuxe_com {
	width: 100%;
	height: 100%;
	background: #f00;
	filter: alpha(opacity :                          
		                                                         
		                                                         
		                                                                     
		                0);
	opacity: 0;
	z-index: 9999;
	position: absolute;
	top: 0;
	left: 0;
	display: none;
}

.ablanuxe_com_2 {
	width: 100px;
	height: 100px;
	position: absolute;
	border: 1px solid #f00;
	overflow: hidden;
	border: 1px solid #f00;
}

.tuodong {
	width: 15px;
	height: 15px;
	background: #99CC00;
	position: absolute;
	right: 0px;
	bottom: 0px;
	cursor: nw-resize;
	overflow: hidden;
	font-size: 12px;
	text-align: center;
	line-height: 15px;
	color: #FFFFFF;
	float: right;
	z-index: 3;
}

.rightTuo {
	width: 15px;
	height: 100%;
	background: #f00;
	float: right;
	position: absolute;
	right: 0;
	top: 0;
	cursor: e-resize;
	overflow: hidden;
	filter: alpha(opacity :                          
		                                                         
		                                                         
		                                                                     
		                0);
	opacity: 0;
	z-index: 1;
}

.bottomTuo {
	width: 100%;
	height: 15px;
	background: #f00;
	position: absolute;
	left: 0;
	bottom: 0;
	cursor: n-resize;
	overflow: hidden;
	filter: alpha(opacity :                            
		                                                         
		                                                         
		                                                                     
		                 0);
	opacity: 0;
	z-index: 1;
}

.ablanuxe_com_2 p {
	padding: 10px;
	line-height: 24px;
	font-size: 13px;
	text-indent: 24px;
}

.ablanuxe_com_2 h2 {
	cursor: move;
	border-bottom: 1px #f00 solid;
	font-size: 10px;
}
</style>

	<script type="text/javascript">
//现场布置图尺寸
var divWidth;
var divHeight;
//初始整体尺寸
$(function() {
	divWidth = document.documentElement.clientWidth;
	divHeight = divWidth * 1203 / 2316;
	$("#allBlock").css("height", divHeight + "px");
})

function getDiv(obj, id) {
	var oDiv = document.getElementById("www_ablanuxe_com_" + id);
	var oDiv2 = obj;
	var zhezhao = document.getElementById("www_ablanuxe_com");
	var h2 = oDiv2.getElementsByTagName("h2")[0];
	var right = document.getElementById("right_" + id);
	var bottom = document.getElementById("bottom_" + id);
	var mouseStart = {};
	var divStart = {};
	var rightStart = {};
	var bottomStart = {};

	//往右拽
	right.onmousedown = function(ev) {
		var oEvent = ev || event;
		mouseStart.x = oEvent.clientX;
		mouseStart.y = oEvent.clientY;
		rightStart.x = right.offsetLeft;
		if (right.setCapture) {
			right.onmousemove = doDrag1;
			right.onmouseup = stopDrag1;
			right.setCapture();
		} else {
			document.addEventListener("mousemove", doDrag1, true);
			document.addEventListener("mouseup", stopDrag1, true);
		}
	};
	function doDrag1(ev) {
		var oEvent = ev || event;
		var l = oEvent.clientX - mouseStart.x + rightStart.x;
		var w = l + oDiv.offsetWidth;
		if (w < oDiv.offsetWidth) {
			w = oDiv.offsetWidth;
		} else if (w > document.documentElement.clientWidth - oDiv2.offsetLeft) {
			w = document.documentElement.clientWidth - oDiv2.offsetLeft - 2;
		}
		oDiv2.style.width = w + "px";
	}
	;
	function stopDrag1() {
		if (right.releaseCapture) {
			right.onmousemove = null;
			right.onmouseup = null;
			right.releaseCapture();
		} else {
			document.removeEventListener("mousemove", doDrag1, true);
			document.removeEventListener("mouseup", stopDrag1, true);
		}
		//移动区块停止是 更新数据坐标
		moveBlock(oDiv2);
	}
	;
	//往下拽
	bottom.onmousedown = function(ev) {
		var oEvent = ev || event;
		mouseStart.x = oEvent.clientX;
		mouseStart.y = oEvent.clientY;
		bottomStart.y = bottom.offsetTop;
		if (bottom.setCapture) {
			bottom.onmousemove = doDrag2;
			bottom.onmouseup = stopDrag2;
			bottom.setCapture();
		} else {
			document.addEventListener("mousemove", doDrag2, true);
			document.addEventListener("mouseup", stopDrag2, true);
		}
	};
	function doDrag2(ev) {
		var oEvent = ev || event;
		var t = oEvent.clientY - mouseStart.y + bottomStart.y;
		var h = t + oDiv.offsetHeight;
		if (h < oDiv.offsetHeight) {
			h = oDiv.offsetHeight;
		} else if (h > document.documentElement.clientHeight - oDiv2.offsetTop) {
			h = document.documentElement.clientHeight - oDiv2.offsetTop - 2;
		}
		oDiv2.style.height = h + "px";
	}
	;
	function stopDrag2() {
		if (bottom.releaseCapture) {
			bottom.onmousemove = null;
			bottom.onmouseup = null;
			bottom.releaseCapture();
		} else {
			document.removeEventListener("mousemove", doDrag2, true);
			document.removeEventListener("mouseup", stopDrag2, true);
		}
		//移动区块停止是 更新数据坐标
		moveBlock(oDiv2);
	}
	;
	//往左右同时拽
	oDiv.onmousedown = function(ev) {
		var oEvent = ev || event;
		mouseStart.x = oEvent.clientX;
		mouseStart.y = oEvent.clientY;
		divStart.x = oDiv.offsetLeft;
		divStart.y = oDiv.offsetTop;
		if (oDiv.setCapture) {
			oDiv.onmousemove = doDrag;
			oDiv.onmouseup = stopDrag;
			oDiv.setCapture();
		} else {
			document.addEventListener("mousemove", doDrag, true);
			document.addEventListener("mouseup", stopDrag, true);
		}
		zhezhao.style.display = 'block';
	};
	function doDrag(ev) {
		var oEvent = ev || event;
		var l = oEvent.clientX - mouseStart.x + divStart.x;
		var t = oEvent.clientY - mouseStart.y + divStart.y;
		var w = l + oDiv.offsetWidth;
		var h = t + oDiv.offsetHeight;
		if (w < oDiv.offsetWidth) {
			w = oDiv.offsetWidth;
		} else if (w > document.documentElement.clientWidth - oDiv2.offsetLeft) {
			w = document.documentElement.clientWidth - oDiv2.offsetLeft - 2;
		}
		if (h < oDiv.offsetHeight) {
			h = oDiv.offsetHeight;
		} else if (h > document.documentElement.clientHeight - oDiv2.offsetTop) {
			h = document.documentElement.clientHeight - oDiv2.offsetTop - 2;
		}
		oDiv2.style.width = w + "px";
		oDiv2.style.height = h + "px";
	}
	;
	function stopDrag() {
		if (oDiv.releaseCapture) {
			oDiv.onmousemove = null;
			oDiv.onmouseup = null;
			oDiv.releaseCapture();
		} else {
			document.removeEventListener("mousemove", doDrag, true);
			document.removeEventListener("mouseup", stopDrag, true);
		}
		zhezhao.style.display = 'none';
		//移动区块停止是 更新数据坐标
		moveBlock(oDiv2);
	}
	;
	//h2完美拖拽
	h2.onmousedown = function(ev) {
		var oEvent = ev || event;
		mouseStart.x = oEvent.clientX;
		mouseStart.y = oEvent.clientY;
		divStart.x = oDiv2.offsetLeft;
		divStart.y = oDiv2.offsetTop;
		if (h2.setCapture) {
			h2.onmousemove = doDrag3;
			h2.onmouseup = stopDrag3;
			h2.setCapture();
		} else {
			document.addEventListener("mousemove", doDrag3, true);
			document.addEventListener("mouseup", stopDrag3, true);
		}
		zhezhao.style.display = 'block';

	};
	function doDrag3(ev) {
		var oEvent = ev || event;
		var l = oEvent.clientX - mouseStart.x + divStart.x;
		var t = oEvent.clientY - mouseStart.y + divStart.y;
		if (l < 0) {
			l = 0;
		} else if (l > document.documentElement.clientWidth - oDiv2.offsetWidth) {
			l = document.documentElement.clientWidth - oDiv2.offsetWidth;
		}
		if (t < 0) {
			t = 0;
		} else if (t > document.documentElement.clientHeight
				- oDiv2.offsetHeight) {
			t = document.documentElement.clientHeight - oDiv2.offsetHeight;
		}
		oDiv2.style.left = l + "px";
		oDiv2.style.top = t + "px";
	}

	function stopDrag3() {
		if (h2.releaseCapture) {
			h2.onmousemove = null;
			h2.onmouseup = null;
			h2.releaseCapture();
		} else {
			document.removeEventListener("mousemove", doDrag3, true);
			document.removeEventListener("mouseup", stopDrag3, true);
		}
		zhezhao.style.display = 'none';
		//移动区块停止是 更新数据坐标
		moveBlock(oDiv2);
	}
};

//移动、拖动的数据更新
function moveBlock(obj) {

	//区块坐标以及尺寸
	var left = obj.style.left;
	var top = obj.style.top;
	var width = obj.style.width;
	var height = obj.style.height;
	var id = obj.id;

	//	left = Math.ceil((left.substring(0, left.length - 2)) / divWidth * 100);
	left = (left.substring(0, left.length - 2)) / divWidth * 100;
	top = parseInt((top.substring(0, top.length - 2)) / divHeight * 100);
	width = (width.substring(0, width.length - 2)) / divWidth * 100;
	height = (height.substring(0, height.length - 2)) / divHeight * 100;
	id = id.substring(6);

	$.ajax( {
		async : false,
		type : "POST",
		url : "BlockAction_moveBlock.action?id=" + id + "&block.topDistance="
				+ top + "&block.leftDistance=" + left + "&block.width=" + width
				+ "&block.hight=" + height,
		dataType : "json",
		success : function(msg) {
			if (msg.success == false) {
				alert(msg.message);
			}
		},
		error : function() {
			alert("数据异常!移动失败!");
		}
	});
}

//添加区块
function addBlock() {
	if ($("#blockName").val() == "") {
		alert("请填写区块名称!");
		return false;
	} else {
		$
				.ajax( {
					type : "POST",
					url : "BlockAction_addBlok.action",
					dataType : "json",
					data : $("#addBlockForm").serialize(),
					success : function(msg) {
						if (msg.success) {
							var block = msg.data;
							var count = block.id;
							var newBlock = "<div id='block_"
									+ count
									+ "' class='ablanuxe_com_2' onmouseover='getDiv(this,"
									+ count
									+ ")' style='left: 0px; top: 0px; height: 99px;'>"
									+ "<div style='width: 100%; height: 100%;'><h2 align='center'>"
									+ block.blockName
									+ "</h2><div id='right_"
									+ count
									+ "' class='rightTuo'></div>"
									+ "<div id='www_ablanuxe_com_"
									+ count
									+ "' class='tuodong'>拖	</div><div id='bottom_"
									+ count
									+ "' class='bottomTuo'></div></div></div>";
							$('#allBlock').append(newBlock);

						}
					}
				});
	}
}
</script>
</head>
<body>
	<div id="allBlock"
		style="background: url('<%=basePath%>images/newxqqy.png') no-repeat; background-size: 100% auto; height:507px;">
		<!-- 遮罩层 -->
		<div id="www_ablanuxe_com"></div>
		<!-- 区块管理区域 -->
		<s:iterator id="pageBlock" value="blockList">
			<div id="block_${pageBlock.id}" class="ablanuxe_com_2"
				onmouseover="getDiv(this,'${pageBlock.id}')"
				style="left: ${pageBlock.leftDistance}%; top: ${pageBlock.topDistance}%; height:${pageBlock.hight}%;width:${pageBlock.width}%;">
				<div style="width: 100%; height: 100%;">
					<h2 align="center">
						${pageBlock.blockName}
					</h2>
					<p>
					</p>
					<div id="right_${pageBlock.id}" class="rightTuo"></div>
					<div id="www_ablanuxe_com_${pageBlock.id}" class="tuodong">
						拖
					</div>
					<div id="bottom_${pageBlock.id}" class="bottomTuo"></div>
				</div>
			</div>
		</s:iterator>
	</div>
	<div align="center">
		<form id="addBlockForm" method="post">
			<input id="blockIndex" name="block.blockIndex" value="0"
				type="hidden" />
			<input id="topDistance" name="block.topDistance" value="0"
				type="hidden" />
			<input id="leftDistance" name="block.leftDistance" value="0"
				type="hidden" />
			<input id="width" name="block.width" value="5" type="hidden" />
			<input id="hight" name="block.hight" value="5" type="hidden" />
			<input id="blockStatus" name="block.blockStatus"
				value="${blockStatus}" type="hidden" />
			<table class="table">
				<tr>
					<th colspan="5">
						添加区块
					</th>
				</tr>
				<tr>
					<th align="right">
						区块名称:
					</th>
					<td>
						<input id="blockName" name="block.blockName" />
					</td>
					<th align="right">
						区块描述:
					</th>
					<td>
						<input name="block.blockMore" />
					</td>
					<td>
						<input type="button" value="添加" class="input" onclick="addBlock()" />
					</td>
				</tr>
			</table>
		</form>

	</div>
</body>
</html>