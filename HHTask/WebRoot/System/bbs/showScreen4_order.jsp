<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%--	<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/myslideup.css" />--%>

		<title>生产进度汇总</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">


		<%@include file="/util/sonHead.jsp"%>
		<link
			href="<%=basePath%>/javascript/BarIndicator/bi-style.css"
			rel="stylesheet" />
<!--		<script-->
<!--			src="${pageContext.request.contextPath}/javascript/jquery-1.7.2.min.js">-->
<!--</script>-->
		<script
			src="<%=basePath%>/javascript/BarIndicator/jquery.easing.1.3.js">
</script>


		<script
			src="<%=basePath%>/javascript/jquery.percentageloader-0.1.js">
</script>
		<script
			src="<%=basePath%>/javascript/radialIndicator.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>/javascript/popwin.js">
</script>

		<script
			src="<%=basePath%>/javascript/BarIndicator/jquery-barIndicator.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>/javascript/myslideup.js">
</script>


		<style type="text/css">
.showtbw {
	list-style: none;
	width: 100%;
}

.showtbw li {
	float: left;
	font-size: 35px;
	font-weight: bolder;
	color: #ffffff;
	width: 19%;
	border-right: solid 1px #407eb8;
	height: 60px;
}
.itemcontainer li{
	clear:both;
	width: 100%;
	height: 40px;
	border-right: solid 1px #407eb8;
	color: #000000;
}
.title {
	border: 1px solid black;
	width: 19.5%;
	float: left;
	background-color: #407eb8;
	height: 65px;
	font-weight: bolder;
	font-size: 45px;	
	padding-top: 40px;
	color: #000000;
}

.detail {
	border: 1px solid black;
	width: 19.5%;
	float: left;
	background-color: #32228c;
	height: 65px;
	font-weight: bolder;
	font-size: 45px;
	padding-top: 40px;
	color: #ffffff;
}
</style>
	</head>
	<body style="background-color: #ccc; color: #ffffff; font-family: 黑体;">
		<center>
			<div style="clear: both; width: 100%;">
				<ul class="showtbw" style="height: 65px; padding-top: 20px;background-color: #000">
					<li style="font-size: 30px; color: rgb(255, 255, 0);">
						<br>
						<span id="showMarkId"></span>
					</li>
					<li id="li2">
					</li>
					<li id="li3">
					</li>
					<li id="li4">
					</li>
					<li>
						<img src="images/pebs.png"
							style="position: absolute; right: 130px; top: 10px;">
						<form action="ProcardAction!showOrderAndProcard.action?id=${id}"
							method="post">
							<br />
							<br />
							<select name="markId" id="markIdlist" class="markId"
								style="font-size: 28px; width: 80%;"
								onchange="javascript:this.form.submit();" >
							</select>
						</form>

					</li>
				</ul>
			</div>
			<div style="clear: both; height: 20px;background-color: #000""></div>
			<div id="order">
			</div>
		</center>

		<script type="text/javascript">
function login() {
	$("#tcIframe").attr("src", "<%=basePath%>login.jsp");
	chageDiv("block");
}
function tozuye() {
	window.location.href = "ModuleFunctionAction!findMfByUser.action?pageStatus=qx";
}
function jindu(id) {
	var a = document.createElement('a');
	a.href = "ProcardAction!findProcardView.action?id=" + id
			+ "&pageStatus=history&viewStatus=zjl";
	a.target = 'showProcardJindu';
	document.body.appendChild(a);
	a.click();
}
</script>

		<script type="text/javascript">
function findDimensions() //函数：获取尺寸 
{
	//获取窗口宽度 
	if (window.innerWidth)
		winWidth = window.innerWidth;
	else if ((document.body) && (document.body.clientWidth))
		winWidth = document.body.clientWidth;

	//获取窗口高度 
	if (window.innerHeight) {
		winHeight = window.innerHeight;
	} else if ((document.body) && (document.body.clientHeight))
		winHeight = document.body.clientHeight;

	//通过深入Document内部对body进行检测，获取窗口大小 
	//	if (document.documentElement && document.documentElement.clientHeight
	//			&& document.documentElement.clientWidth) {
	//		winHeight = document.documentElement.clientHeight;
	//		winWidth = document.documentElement.clientWidth;
	//	}
}
function nextPage() {
	window.parent.clearsetInterval();
	window.parent.next();
}

var pagePage = "${cpage}";
var idlist = "${flag}";
var pageMarkId = "${markId}";
$(function() {
	if (pagePage == "") {
		pagePage = 1;
	} else {
		pagePage = parseInt(pagePage);
	}
	if (idlist == "") {
		idlist = 1;
	}

	function load() {
		getOnline();//在线检测
		findDimensions();
		//计算每页显示数量
		var pageRows = parseInt((winHeight - 100) / 85);
		var linRows = (winHeight - 100) / 85;
		if (linRows - pageRows >= 0.7) {
			pageRows++;
		}
		$
				.ajax( {
					type : "POST",
					url : "ProcardAction!showScreen.action",
					data : {
						"markId" : pageMarkId,
						"page" : pagePage,
						"rows" : pageRows
					},
					async : false,
					dataType : "json",
					success : function(object) {
						//markId, list, list_procard, list_allMarkid, orderlist
					var markId = object[0];// 订单信息
					var list = object[1];// 订单信息
					var data = object[2];//数据列表
					var allMarkId = object[3];
					var count = object[4];

					$('#order').empty();//清空表格
					$('#showMarkId').html(markId);
					$('#markIdlist')
							.prepend("<option >" + markId + "</option>");
					
					//添加表头
					var head = "";
					var name = "${Users.name}";
					if (name != null && name.length > 0) {
					} else {
					}
					var li2 = "拖欠" + "<br /><br />" + list[1];
					$('#li2').append(li2);

					var li3 = "本月(" + list[2] + "<br /><br />" + list[3];
					$('#li3').append(li3);
					var li4 = "未来(至" + list[4] + "<br /><br />" + list[5];
					$('#li4').append(li4);

					for ( var i = 0; i < allMarkId.length; i++) {
						if (allMarkId[i] != "" && allMarkId[i] !== null) {
							var tr = "<option>" + allMarkId[i] + "</option>";
								$('#markIdlist').append(tr);
						}
					}
					$("#markIdlist").tinyselect();
					var tr1 = head
							+ "<div class='title' style='width: 13%;'>批次</div>"
							+ "<div class='title' style='width: 10%;'>数量</div>"
                        	+ "<div class='title' style='width: 16%;'>内部订单号</div>"
							+ "<div class='title' style='width: 12%;'>产品状态</div>"
							+ "<div class='title'>工序进度</div>"
							+ "<div class='title' style='width:28%;font-size: 38px;'>外购件缺货状态</div>"
					$('#order').append(tr1);

					//填充内容 
					for ( var i = 0; i < data.length; i++) {
						//产品状态
						var procardStatus = data[i].rootSelfCard;
						if (procardStatus == "完成" || procardStatus == "待入库"
								|| procardStatus == "已发料"
								|| procardStatus == "领工序"
								|| procardStatus == "取消"
								|| procardStatus == "已发卡") {
							procardStatus = "<div class='detail' style='font-size:45px;width:12%;'>"
									+ procardStatus;
						} else {
							procardStatus = "<div class='detail' style='font-size:20px;width:12%;padding-top:0px;height:107px;'>"
									+ procardStatus;
						}

						//缺件状态
						var iFquejian = data[i].rootMarkId;
						if (iFquejian != '否') {
							iFquejian = "<div class='detail' style='width:28%;overflow-y:scroll;font-size:22px;padding-top:0px;height:107px;line-height:25px' align='left'>"
									+ "<span >";
							for ( var j = 0; j < data[i].procardList.length; j++) {
								iFquejian += (j + 1) + '、'
										+ data[i].procardList[j].markId + ' '
										+ data[i].procardList[j].tjNumber.toFixed(4) + '/'
										+ data[i].procardList[j].filnalCount.toFixed(4)
										+ '<br/>';
							}
						} else {
							iFquejian = "<div class='detail' style='width:28%;overflow-y:scroll;font-size:45px;padding-top:30px;height:77px;line-height:25px' align='center'>"
									+ "<span >已齐套" ;
						}

						var tr = "<div class='detail' style='word-wrap:break-word;width:13%;font-size:30px;line-height:30px;' align='right'>"
								+ data[i].selfCard
								+ "</div>"
								+ "<div class='detail'  style='width: 10%;'>"
								+ data[i].filnalCount
								+ "</div>"
								+ "<div class='detail'  style='width: 16%;font-size:30px;line-height:30px;' align='right'>"
                            	+ data[i].orderNumber//内部订单号
								+ "</div>"
								+ procardStatus
								+ "</div>"
								+ "<div style='border:1px solid black;width: 19.5%;float:left;background-color: #000000;height: 45px;font-weight: bolder;font-size: 26px;color: #ffffff;'>"
								//								+data[i].lingliaoren+"</div>"
								+ "<div id='test"
								+ i
								+ "' class='rkjindu' style='cursor: pointer;padding-top:36px;font-size:45px;' data='"
								+ data[i].lingliaoren
								+ "'  onclick='jindu("
								+ data[i].id
								+ ")'>"
								//+data[i].lingliaoren
								+ "</div></div>"
								+ iFquejian
								+ "</span><br/></div>";

						$('#order').append(tr);
						var bar = parseInt(data[i].lingliaoren);
						$("#test" + i).barIndicator( {
							milestones : false,
							data : bar,
							horBarHeight : 105,
							backColor : '#000000',
							horLabelPos : 'right',
							//labelVisibility:'hidden',
							colorRange : true,
							colorRangeLimits : {
								newRangeOne : '0-40-#f80043',
								newRangeTwo : '41-60-#FF800A',
								newRangeThree : '61-100-#7fc41c'
							}
						});
					}
					var allPage = (Math.ceil(count / pageRows)) == 0 ? 1
							: (Math.ceil(count / pageRows));

					var tr3 = "<tr><td colspan='8' align='center' style='font-size:20px;'>页码:"
							+ pagePage
							+ "/"
							+ allPage
							+ " 产品:"
							+ idlist
							+ "/"
							+ allMarkId.length + "</td></tr>";
					$('#order').append(tr3);

					if (pagePage < allPage) {
						pagePage++;
						setInterval(nextPage3, 1000 * 60*5);
					} else if (pagePage == allPage) {
						pageMarkId = allMarkId[idlist];
						idlist++;
						if (idlist > allMarkId.length) {
							setInterval(nextPage, 1000 * 60*5);
							
						} else {
							pagePage = 1;
							setInterval(nextPage3, 1000 * 60*5);
						}
					} else {
						setInterval(nextPage, 1000 * 60*5);
					}

				}

				});

	}

	load();//第一次加载页面

	//  

	//下一个页面 pagePage/allPage跳转
<%--	function nextPage2() {--%>
<%--		--%>
<%--		if ("${id}" != "") {--%>
<%--			getOnline();--%>
<%--			window.location.href = "${pageContext.request.contextPath}/ProcardAction!showOrderAndProcard.action?markId=${markId}&id=${id}&cpage="--%>
<%--					+ pagePage;--%>
<%--		}--%>
<%--	}--%>
	function nextPage3() {
		window.parent.clearsetInterval();
		window.parent.auto2nextpage();
		
		getOnline();	
		window.location.href = "ProcardAction!showOrderAndProcard.action?markId="
				+ pageMarkId
				+ "&id=${id}&cpage="
				+ pagePage
				+ "&flag="
				+ idlist;
	}

});
</script>
	</body>
</html>
