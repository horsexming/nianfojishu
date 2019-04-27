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
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
		<style type="text/css">
.ztree li a {
	color: #fff;
}

.dhlabel {
	border-top: 1px solid #000;
	border-bottom: 1px solid #000;
	border-left: 1px solid #000;
	border-right: 1px solid #000;
	margin-left: 5px;
	margin-right: 5px;
	padding: 3px 5px;
	white-space: nowrap;
}

#fullbg1 {
	background-color: gray;
	left: 0;
	opacity: 0.5;
	position: absolute;
	top: 0;
	z-index: 3;
	filter: alpha(opacity =       50);
	-moz-opacity: 0.5;
	-khtml-opacity: 0.5;
}

#dialog1 {
	background-color: #fff;
	border: 5px solid rgba(0, 0, 0, 0.4);
	left: 50%;
	margin: -200px 0 0 -200px;
	padding: 1px;
	position: fixed !important; /* 浮动对话框 */
	position: absolute;
	top: 45%;
	width: 400px;
	height: 100px;
	z-index: 5;
	border-radius: 5px;
	display: none;
}

#xiugaiIframe1 {
	background-color: #fff;
	height: 75px;
	line-height: 24px;
	width: 400px;
}
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="left">
				<div id="printDiv">
					<br />
					<div
						style="font-weight: bolder; font-size: 30px; margin-bottom: 10px;"
						align="center">
						待入库批次
					</div>
					<form
						action="WaigouwaiweiPlanAction!findAllDrk.action?pageStatus=${pageStatus}&tag=${tag}"
						method="post">
						<table>
							<tr>
								<td>
									物料类别:
								</td>
								<td>
									<div class="zTreeDemoBackground left">
										<ul class="list">
											<li class="title">
												<input id="wgType" type="text" value="${waigoudd.wgType}"
													style="width: 120px;" name="waigoudd.wgType" />
												<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>
											</li>
										</ul>
									</div>
									<div id="menuContent" class="menuContent"
										style="display: none; position: absolute;">
										<ul id="treeDemo" class="ztree"
											style="margin-top: 0; width: 160px;"></ul>
									</div>
								</td>
								<td>
									件号：
								</td>
								<td>
									<input type="text" style="width: 120px;" name="waigoudd.markId"
										value="${waigoudd.markId}" />
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<input type="submit" value="查询" class="input" />
									&nbsp;
									<input type="button" value="扫码入库" id="saoma"
										onclick="javascript:this.disabled='disabled';getsendFour()"
										class="input" />
								</td>
							</tr>
						</table>
					</form>
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								供应商
							</th>
							<th align="center">
								件号
							</th>
							<th align="center">
								零件名称
							</th>
							<s:if test="pageStatus=='ww'">
								<th align="center">
									外委工序
								</th>
								<th align="center">
									下工序
								</th>
							</s:if>
							<th align="center">
								版本
							</th>
							<th align="center">
								类型
							</th>
							<th align="center">
								供料属性
							</th>
							<th align="center">
								物料类别
							</th>
							<th align="center">
								规格
							</th>
							<th align="center">
								图号
							</th>
							<th align="center">
								数量
							</th>
							<th align="center">
								箱数
							</th>
							<th align="center">
								物料位置
							</th>
							<th align="center">
								检验批次
							</th>
							<th align="center">
								检验时间
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator value="list" id="pageWgww2" status="pageStatus2">
							<s:if test="#pageStatus2.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 50px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageStatus2.index+1" />
								<input type="checkbox" name="wddshuzu" value="${pageWgww2.id}">
							</td>
							<td align="left">
								${pageWgww2.gysName}
							</td>
							<td align="left">
								${pageWgww2.markId}
							</td>
							<td align="left">
								${pageWgww2.proName}
							</td>
							<s:if test="pageStatus=='ww'">
								<td align="left">
									${pageWgww2.processName}
								</td>
								<td align="left">
									${pageWgww2.nextProcessName}
								</td>
							</s:if>
							<td>
								<s:if test="#pageWgww2.banben!=null&&#pageWgww2.banben!='null'">
								${pageWgww2.banben}
								</s:if>
							</td>
							<td>
								${pageWgww2.type}
							</td>
							<td>
								${pageWgww2.kgliao}
							</td>
							<td>
								${pageWgww2.wgType}
							</td>
							<td>
								${pageWgww2.specification}
							</td>
							<td>
								${pageWgww2.tuhao}
							</td>
							<td align="center">
								${pageWgww2.hgNumber} ${pageWgww2.unit}
							</td>
							<td>
								${pageWgww2.ctn}
							</td>
							<td>
								${pageWgww2.qrWeizhi}
							</td>
							<td>
								${pageWgww2.examineLot}
							</td>
							<td>
								<input type="text" value="${pageWgww2.checkTime}" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td>
								<s:if test="#pageWgww2.wbdId==null">
									<div name="kuwei" onclick="getsendThere('${pageWgww2.id}')"
										style="width: 55px; height: 55px; border-radius: 50%; background-color: gray; color: #ffffff; font-size: 10px;">
										<br />
										库位码
										<br />
										扫描
									</div>
								</s:if>
								<s:else>
									<div onclick="getsendTow('${pageWgww2.id}')"
										style="width: 55px; height: 55px; border-radius: 50%; background-color: green; color: #ffffff; font-size: 10px;">
										<br />
										二维码
										<br />
										扫描
									</div>
								</s:else>
							</td>
							<s:if test="#pageWgww2.classNames!=null">
							</s:if>
							<tr>
								<th>
									推荐
								</th>
								<td colspan="15">
									${pageWgww2.classNames}
								</td>
							</tr>
						</s:iterator>
						<s:if test="list.size()<=0">
							<tr>
								<s:if test="pageStatus=='ww'">
									<th colspan="17">
								</s:if>
								<s:else>
									<th colspan="15">
								</s:else>
								没有待入库任务,可以休息一会儿了~
								</th>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<s:if test="pageStatus=='ww'">
									<th colspan="17" align="right">
								</s:if>
								<s:else>
									<th colspan="15" align="right">
								</s:else>
								第
								<font size="14" color="red"><s:property value="cpage" />
								</font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</tr>
						</s:else>
					</table>
				</div>
				<br />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
var b = true;
var id = 0;
function getsendTow(wwd) {
	b = true;
	id = wwd;
	$
			.ajax( {
				url : "WaigouwaiweiPlanAction!querensendTow.action",
				type : "POST",
				data : {
					id_wdd : wwd
				},
				dataType : "json",
				async : false,
				success : function(data) {
					if (data != null) {
						if (data.success) {
							getcheckList2();
						} else {
							if (data.message == 'trues') {
								window.location.href = "<%=basePath%>/System/SOP/produce/Procard_rkxz.jsp?id="
										+ wwd;
							} else {
								alert(data.message)
							}
						}
					}
				},
				error : function() {
					alert("服务器异常!");
				}
			});
}
//无IP直接入库
function getsendThere(wwd) {
	zhengmu();
	b = false;
	id = wwd;
	$.ajax( {
		url : "WaigouwaiweiPlanAction!querenKu.action",
		type : "POST",
		data : {
			id_wdd : wwd,
			//tag : '${tag}',
			pageStatus : '${pageStatus}'
		},
		dataType : "json",
		async : false,
		success : function(data) {
			if (data != null) {
				if (data.success) {
					getcheckList2();
				} else {
					alert(data.message)
				}
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}

//无IP批量入库
function getsendFour() {
	b = false;
	id = 0;
	var invalue = document.getElementsByName("wddshuzu");
	var num = 0;
	var mxId = "";
	for (i = 0; i < invalue.length; i++) {
		if (invalue[i].checked == true) {
			mxId += invalue[i].value + ",";
			num++;
		}
	}
	if (mxId == "") {
		alert("请选择待入库物品");
		//.attr("disabled", "disabled");
		$("#saoma").removeAttr("disabled");
		return false;
	} else {
		if (num > 10) {
			alert("由于入库操作比较耗时，单次入库数量需小于10条。已选中" + num + "条");
			$("#saoma").removeAttr("disabled");
			return false;
		}
		zhengmu();
		getcheckList2();
		//location.reload(true);
		//funFromjs('93261b4f-1c74-4933-a328-c25e420fa266');
	}
}

function getcheckList2() {
	if (typeof (myObj) != "undefined") {
		//打开扫描服务
		myObj.scanGongWei(1);
	} else {
		alert("无法打开扫描服务,请检查后重试!");
		$("#dialog1").hide();
		$("#fullbg1").hide();
// 		funFromjs("7eaa0da3-210d-4fbd-8e28-b870223ba192");
	}
}
function funFromjs(tm) {
	zhengmu();
	//$("#kuwei").hide();
	var invalue1 = document.getElementsByName("kuwei");
	for (i = 0; i < invalue1.length; i++) {
		invalue1[i].style.display = "none";
	}
	var invalue2 = document.getElementsByName("tuijian");
	for (i = 0; i < invalue2.length; i++) {
		invalue2[i].style.display = "none";
	}
if (b) {//二维码
		window.location.href = "WaigouwaiweiPlanAction!upRuKuBacode.action?bacode="
				+ tm;
	} else {//库位码
		var invalue = document.getElementsByName("wddshuzu");
		var num= invalue.length;
		var mxId= "";
		var checkTimes ="";
		for (i=0;i<num;i++){
			if(invalue[i].checked == true) {
			   	mxId+=invalue[i].value+",";
				 var tr = 	$(invalue[i]).parent().parent("tr");
				checkTimes+=$(tr).find("input[type='text']").val()+",";
			}
		}
		if(mxId == "" && id == 0){
			alert("请选择待入库物品");
			return false;
		}
		querenKu(tm,id,mxId,checkTimes);
		
<%--		window.location.href = "WaigouwaiweiPlanAction!querenKuBacode.action?bacode="--%>
<%--				+ tm + "&id=" + id+"&pageStatus=${pageStatus}" + "&mxId=" + mxId + "&tag=${tag}&=${cpage}";--%>
	}
}
function querenKu(tm,id,mxId,checkTimes){
	$.ajax( {
		url : "WaigouwaiweiPlanAction!querenKuBacode_ajax.action",
		type : "POST",
		data : {
			bacode : tm,
			id :id,
			pageStatus:'${pageStatus}',
			mxId: mxId,
			tag:'${tag}',
			cpage:'${cpage}',
			checkTimes:checkTimes
		},
		dataType :"json",
		success : function(data) {
			if(data=="true"){
				alert("入库成功!~")
				window.location.reload(true);
			}else{
				$("#dialog1").hide();
				$("#fullbg1").hide();
				alert("入库失败:"+data);
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}

$(document).ready(function() {
	getCaizhi("wgType");
})

var mfzTree;
var addzTree;
var delzTree;
var updatezTree;

var id;
var pId;
var name;
var setting = {
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		beforeClick : beforeClick,
		onClick : onClick
	}
};
//读取树形数据
$(document).ready(function() {
	parent.mfzTree();
});
var zNodes = [];
parent.mfzTree = function() {
	$.ajax( {
		url : 'CategoryAction_findcyListByrootId.action',
		type : 'post',
		data : {
			status : '物料类别'
		},
		dataType : 'json',
		cache : true,
		success : function(doc) {
			$(doc).each(function() {
				zNodes.push( {
					id : $(this).attr('id'),
					pId : $(this).attr('fatherId'),
					name : $(this).attr('name'),
					target : "main",
					click : false
				});

			});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.expandAll(true);
		},
		error : function() {
			alert("服务器异常!");
		}
	});
};
function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);

	return true;
}

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
			.getSelectedNodes(), v = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v = nodes[i].name;
	}
	//if (v.length > 0 ) v = v.substring(0, v.length-1); 
	cityObj = $("#wgType").val(v);

}

function showMenu() {
	var cityObj = $("#wgType");
	var cityOffset = $("#wgType").offset();
	$("#menuContent").css( {
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
			event.target).parents("#menuContent").length > 0)) {
		hideMenu();
	}
}
function showPrice(num){
	document.getElementById("xiugaiIframe").src = "WaigouwaiweiPlanAction!showPrice.action?id="+ num;
	chageDiv('block');
}
function sdxdshowPrice(obj){
	obj.action = "WaigouwaiweiPlanAction!sdxdshowPrice.action";
	$(obj).submit();
	obj.action = "WaigouwaiweiPlanAction!addWgOrder.action";
}

function zhengmu(){
	//弹出遮罩层开始
		$("body").append("<div id='fullbg1'></div>");
		$("body")
				.append(
						"<div id='dialog1' class='loginbox'>"
								+ "<iframe id='xiugaiIframe1' src='<%=basePath%>/System/SOP/produce/procard_zhenmu.jsp' "
								+ "marginwidth='0' marginheight='0' hspace='0' vspace='0' "
								+ "frameborder='0' scrolling='yes'"
								+ " style='width: 100%;margin: 0px; padding: 0px;'>"
								+ "</iframe></div>")

		var sWidth, sHeight;
		//sWidth=document.body.offsetWidth;//得出当前屏幕的宽
		sWidth = document.body.clientWidth;//BODY对象宽度

		//sHeight=screen.height; //得到当前屏幕的高
		//sHeight=document.body.clientHeight;//BODY对象高度
		if (window.innerHeight && window.scrollMaxY) {
			sHeight = window.innerHeight + window.scrollMaxY;
		} else if (document.body.scrollHeight > document.body.offsetHeight) {
			sHeight = document.body.scrollHeight;
		} else {
			sHeight = document.body.offsetHeight;
		} //以上得到整个屏幕的高
		//		var bw = $("body").width();
		//		var bh = window.screen.availHeight;
		$("#fullbg1").css( {
			height : sHeight,
			width : sWidth,
			display : "block"
		});
		$("#dialog1").show();
		//弹出遮罩层结束
		
}

</script>
	</body>
</html>