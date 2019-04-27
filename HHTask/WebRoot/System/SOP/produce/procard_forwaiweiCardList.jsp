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
<STYLE type="text/css">
#fullbg1 {
	background-color: gray;
	left: 0;
	opacity: 0.5;
	position: absolute;
	top: 0;
	z-index: 3;
	filter: alpha(opacity =             50);
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
	height: 200px;
	z-index: 5;
	border-radius: 5px;
	display: none;
}

#xiugaiIframe1 {
	background-color: #fff;
	height: 165px;
	line-height: 24px;
	width: 400px;
}
</STYLE>
</head>
<body>
	<%@include file="/util/sonTop.jsp"%>
	<div id="gongneng" style="width: 100%;">
		<div align="center" style="border: 1px solid #00000;">
			<form action="ProcardAction!forwaiweiParocardList.action"
				method="post">
				<input type="hidden" name="id" value="${id}"> <input
					type="hidden" name="pageStatus" value="${pageStatus}">
				<table class="table">
					<tr>
						<th colspan="6">工序外委计划管理</th>
					</tr>
					<tr>
						<th>件号:</th>
						<td><input name="procard.markId" value="${procard.markId}" />
						</td>
						<th>批次</th>
						<td><input name="procard.selfCard"
							value="${procard.selfCard}" /></td>
					</tr>
					<tr>
						<th>业务件号</th>
						<td><input name="procard.ywMarkId"
							value="${procard.ywMarkId}" /></td>
						<th>名称:</th>
						<td><input name="procard.proName" value="${procard.proName}" />
						</td>
					</tr>
					<tr>
						<th>内部订单号：</th>
						<td><input name="procard.orderNumber"
							value="${procard.orderNumber}" /></td>
						<th></th>
						<td>
						</td>
					</tr>
					<tr>
						<th colspan="6"><input type="submit" value="查询(Query)"
							class="input" /> <input type="reset" value="清空(Empty)"
							class="input" /></th>
					</tr>
					
				</table>
			</form>
			<div id="rootTemplateDiv">
				<div id="showMessage"
					style="color: red; font-size: 14px; font-weight: bolder;"></div>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th><input type="checkbox" id="checkAll"
							onchange="checkAllBoxs()">全选 <br> <input
							type="button" value="一键激活" onclick="onKeyActive()"></th>
						<th align="center">序号</th>
						<th align="center">内部订单号</th>
						<th align="center">件号</th>
						<th align="center">批次</th>
						<th align="center">名称</th>
						<th align="center">卡片类型</th>
						<th align="center">产品类型</th>
						<th align="center">数量</th>
						<th align="center">说明</th>
						<th align="center">操作</th>
					</tr>
					<tr align="center" bgcolor="red" height="25px">
						<td colspan="22">待处理</td>
					</tr>
					<s:iterator value="procardList" id="pageProcard0"
						status="statusdef">
						<s:if test="#pageProcard0.remark == 'red'">
							<tr align="center" bgcolor="#ff0000">
						</s:if>
						<s:elseif test="#pageProcard0.remark == 'shebian'">
							<tr align="center" bgcolor="#FFD700">
						</s:elseif>
						<s:else>
							<s:if test="#statusdef.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
						</s:else>
						<td><input type="checkbox" name="selected"
							value="${pageProcard0.id}" class="toCheckSingle"
							onchange="checkSingle()" /></td>
						<td><s:property value="#statusdef.index+1" /></td>
						<td>${pageProcard0.orderNumber}</td>
						<td>${pageProcard0.markId} <br /> <s:if
								test="#pageProcard0.remark == 'red'">
								<font color="#fff">(${pageProcard0.ywMarkId})</font>
							</s:if> <s:else>
								<font color="red">(${pageProcard0.ywMarkId})</font>
							</s:else></td>
						<td>${pageProcard0.selfCard}</td>
						<td style="width: 180px;">${pageProcard0.proName}</td>
						<td>${pageProcard0.procardStyle}</td>
						<td>${pageProcard0.productStyle}</td>
						<td><label
							id="filnalCount<s:property value="#pageindex.index" />">
								${pageProcard0.filnalCount} </label></td>
						<td>
								<s:if test="#pageProcard0.remark == 'red'">
									外委同意
								</s:if> 
								<s:elseif test="#pageProcard0.remark == 'shebian'">
									设变同步
								</s:elseif>
								<s:elseif test="#pageProcard0.remark == 'shouhou'">
									<font color="red">补料申请</font>
								</s:elseif>
							</td>
						<td align="center"><s:if test="pageStatus == 'MRP'">
								<input type="button" onclick="scjd(${pageProcard0.rootId})"
									value="生产进度">
							</s:if> <s:elseif test="pageStatus == 'WWSQ'">
								<input type="button" onclick="wwsqyx(${pageProcard0.id})"
									value="外委申请">
							</s:elseif> <s:if test='#pageProcard0.wlstatus=="待定"'>
								<input type="button" id="active${pageProcard0.id}"
									onclick="nowwsqyx(${pageProcard0.id},this)" value="激活(MRP运算)"
									title="表示本批次无包工包料的外委产品,即不会影响物料采购计划">
								<input type="button"
									onclick="yulan_nowwyx(${pageProcard0.id},this)" value="预览">
							</s:if> <s:else>
								<input type="button" disabled="disabled" value="激活(MRP运算)"
									title="表示本批次无包工包料的外委产品,即不会影响物料采购计划">
								<input type="button" disabled="disabled" value="预览">
							</s:else></td>
						</tr>
					</s:iterator>
					<tr align="center" bgcolor="green" height="25px">
						<td colspan="22">已处理</td>
					</tr>
					<s:iterator value="list" id="pageProcard" status="pageindex">
						<s:if test="#pageindex.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td></td>
						<td><s:property value="#pageindex.index+1" /></td>
						<td>${pageProcard.orderNumber}</td>
						<td>${pageProcard.markId} <br /> <font color="red">(${pageProcard.ywMarkId})</font>
						</td>
						<td>${pageProcard.selfCard}</td>
						<td style="width: 180px;">${pageProcard.proName}</td>
						<td>${pageProcard.procardStyle}</td>
						<td>${pageProcard.productStyle}</td>
						<td><label
							id="filnalCount<s:property value="#pageindex.index" />">
								${pageProcard.filnalCount} </label></td>
						<td></td>
						<td align="center"><s:if test="pageStatus == 'MRP'">
								<input type="button" onclick="scjd(${pageProcard.rootId})"
									value="生产进度">
							</s:if> <s:elseif test="pageStatus == 'WWSQ'">
								<input type="button" onclick="wwsqyx(${pageProcard.id})"
									value="外委申请">
							</s:elseif> <s:if test='#pageProcard.wlstatus=="待定"'>
								<input type="button" onclick="nowwsqyx(${pageProcard.id},this)"
									id="active${pageProcard0.id}" value="激活(MRP运算)"
									title="表示本批次无包工包料的外委产品,即不会影响物料采购计划">
								<input type="button"
									onclick="yulan_nowwyx(${pageProcard.id},this)" value="预览">
							</s:if> <s:else>
								<input type="button" disabled="disabled" value="无外委计划"
									title="表示本批次无包工包料的外委产品,即不会影响物料采购计划">
								<input type="button" disabled="disabled" value="预览">
							</s:else></td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">第 <font color="red"><s:property
										value="cpage" /> </font> / <s:property value="total" /> 页 <fenye:pages
									cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
						</s:else>
						</td>
					</tr>
					<tr>
						<th colspan="11">
							<div id="showMess" style="color: red; display: none;">
								正在确认委外订单中,请稍候......</div>
							<div id="showMess2" style="color: red; display: none;">
								正在生成物料计划中,请稍候......</div>
							<div id="showMess3" style="color: red; display: none;">
								正在激活生产任务中,请稍候......</div></th>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<%@include file="/util/foot.jsp"%>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
function scjd(id) {
	window.open("ProcardAction!findProcardView.action?id=" + id
			+ "&pageStatus=history&viewStatus=update");
}
function wwsqyx(id) {
	var a = document.createElement('a');
	a.href = "ProcardAction!towwyx.action?id=" + id;
	a.target = 'chageWaiwei';
	document.body.appendChild(a);
	a.click();
}
function nowwsqyx(id, obj) {
if (confirm("确定设置无外委计划?")) {
		//弹出遮罩层开始
		$("body").append("<div id='fullbg1'></div>");
		$("body")
		.append(
				"<div id='dialog1' class='loginbox'><iframe id='xiugaiIframe1' "+
				"src='${pageContext.request.contextPath}/System/SOP/produce/zhemu.jsp' "
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
		$.ajax( {
		type : "POST",
		url : "ProcardAction!notowwyx.action",
		data : {
			cpage :'${cpage}',
			id : id
		},
		//async : false, 
		dataType : "json",
		success : function(data) {
				alert(data);
				$(obj).attr("disabled", "disabled");
				$("#dialog1").hide();
				$("#fullbg1").hide();
				$("#showMess").hide();
				$("#showMess2").hide();
				$("#showMess3").hide();
		}
	});
<%--		window.location.href = "ProcardAction!notowwyx.action?cpage=${cpage}&id=" + id;--%>
		
<%--		$("#showMess").show();--%>
<%--		setTimeout(function() {--%>
<%--			$("#showMess2").show();--%>
<%--		}, 1000 * 60);--%>
<%--		setTimeout(function() {--%>
<%--			$("#showMess3").show();--%>
<%--		}, 1000 * 90);--%>
	}
}
function yulan_nowwyx(id, obj){
	window.open("<%=basePath%>System/SOP/produce/yulan_nowwyx.jsp?id="+id)
}

//选中全部或取消全部
function checkAllBoxs() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = $(".toCheckSingle");
	if (checkAll.checked == true) {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = true;
		}
	} else {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = false;
		}
	}

}

function checkSingle() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = $(".toCheckSingle");
	var count = 0;
	for ( var i = 0; i < checkboxs.length; i++) {
		if (checkboxs[i].checked == false) {
			checkAll.checked = false;
			return;
		} else {
			count++;
		}
	}
	if (count == checkboxs.length) {
		checkAll.checked = true;
	}
}


function onKeyActive(){
	var totalCount = 0; //处理总数
	var toCheckSingle = $(".toCheckSingle");
	for(var i=0;i<toCheckSingle.length;i++){
		var box = toCheckSingle[i];
		if (box.checked == true) {
			//alert(box.value);
			totalCount++;
			//$("#active"+box.value).trigger("click"); 
		}
	}
	
	if (confirm("确定批量设置无外委计划?")) {
		var currentCount = 0;
		for(var i=0;i<toCheckSingle.length;i++){
			var box = toCheckSingle[i];
			if (box.checked == true) {
				currentCount++;
				$("#currentCount").val(currentCount);
				//弹出遮罩层开始
				if(i==0){
					$("body").append("<div id='fullbg1'></div>");
					$("body").append( "<div id='dialog1' class='loginbox'>"+
							"<div id='showTop' style='color: red;' align='center'>"+
							"当前正在处理第<span id='currentNum'></span>条，总数为<span id='showCount'></span>"+
							"</div>"+
							"<div id='showMessmult' style='color: red;'>"+
							"	正在确认委外订单中,请稍候...<font id='showMess1_font'></font>"+
							"</div>"+
							"<div id='showMessmult2' style='color: red; '>"+
							"	正在生成物料计划中,请稍候...<font id='showMess2_font'></font>"+
							"</div>"+
							"<div id='showMessmult3' style='color: red;'>"+
							"	正在激活生产任务中,请稍候...<font id='showMess3_font'></font>"+
							"</div></div>");
					var sWidth, sHeight;
					sWidth = document.body.clientWidth;//BODY对象宽度

					sHeight=document.body.clientHeight;//BODY对象高度
					if (window.innerHeight && window.scrollMaxY) {
						sHeight = window.innerHeight + window.scrollMaxY;
					} else if (document.body.scrollHeight > document.body.offsetHeight) {
						sHeight = document.body.scrollHeight;
					} else {
						sHeight = document.body.offsetHeight;
					} 
					$("#fullbg1").css( {
						height : sHeight,
						width : sWidth,
						display : "block"
					});
				}else{
					$("#dialog1").show();
					$("#fullbg1").show();
					$("#showMessmult").show();
					$("#showMessmult2").show();
					$("#showMessmult3").show();
					
				}
				$("#dialog1").show();
				$("#showCount").text(totalCount);
				$("#currentNum").text(currentCount);
				//setInterval("test()", 400);
				//test();
				var flag = false;
				$.ajax( {
					type : "POST",
					url : "ProcardAction!notowwyx.action",
					data : {
						cpage :'${cpage}',
						id : box.value
					},
					async : false, 
					dataType : "json",
					success : function(data) {
						flag = true;
						if(data!=null && data=="设定成功!"){
							$("#active"+box.value).attr("disabled", "disabled");
							$("#showMessmult").hide();
							$("#showMessmult2").hide();
							$("#showMessmult3").hide();
							$("#fullbg1").hide();
							$("#dialog1").hide();
						}else{
							alert("第"+currentCount+"行："+data);
							$("#showMessmult").hide();
							$("#showMessmult2").hide();
							$("#showMessmult3").hide();
							$("#fullbg1").hide();
							$("#dialog1").hide();
							return;
						}
					}
				});
				
				
				
			}
		}
		
		
	}
}


var str = '';
function test(){
	
	for(var i=0;i<8;i++){
		if(i==0){
			str='';
		}else{
			str+='。';
		}
		setTimeout("dynamicLoading(str)", 20000);
		
	}
	/*if(str.length>=8){
		str='';
	}else{
		str+='。';	
	}*/
	
}

function dynamicLoading(str){
	document.getElementById("showMess1_font").innerHTML=str;
	document.getElementById("showMess2_font").innerHTML=str;
	document.getElementById("showMess3_font").innerHTML=str;
}
</script>
</body>
</html>
