<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/util/newHead.jsp"%>
<div class="main">
	<div class="m_top">
		<ul>
			<s:iterator id="mf" value="allModuleList" status="pageId">
				<li
					style="background: ${mf.bgColor}; position: absolute; z-index: 1; margin-left: ${pageId.index*1008/8}px;width:${1008/8-7}px; display: inline;">
					<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${mf.id}"
						target="topModule${mf.id}"><img
							src="<%=basePath%>upload/file/sysImages/${mf.qximageName}"
							style="width: ${1008/8-7}px" /> </a>
				</li>
			</s:iterator>
		</ul>
	</div>
	<div class="spana"></div>
	<div class="m_c">
		<h1>
			<a href="ModuleFunctionAction!findMfByUser.action">首页</a> >
			${mfNames}
			<a href="javascript:void(0)" onclick="addtofavorite()" class="sop fr">收藏</a>
			<%--			<a--%>
			<%--				href="javascript:location.href='FavoriteAction_addFavorite.action?id=${moduleFunction.id}&favorite.path=${moduleFunction.functionLink}&favorite.name='+encodeURI(encodeURI('${moduleFunction.functionName}'));"--%>
			<%--				target="" class="sop fr">收藏</a>--%>
			<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${id}"
				class="sop fr">刷新</a><a href="javascript:;"
				onclick="document.getElementById('workMainIframe').contentWindow.history.back();"
				class="sop fr">返回</a><a
				href="<%=basePath%>${moduleFunction.functionLink}" target="showQuan"
				class="sop fr">全屏</a>
		</h1>
	</div>
	<%--	<p class="gl">--%>
	<%--		<img src="images/tb_05.jpg" />--%>
	<%--		<s:if test="moduleFunction.functionIntro==''">--%>
	<%--			${moduleFunction.functionName}--%>
	<%--		</s:if>--%>
	<%--		<s:else>--%>
	<%--			${moduleFunction.functionIntro}--%>
	<%--		</s:else>--%>
	<%--	</p>--%>
	<%--	<div class="mkgl">--%>
	<%--		<h2>--%>
	<%--			<img src="images/tb_03.jpg" />--%>
	<%--			${moduleFunction.functionName}(${moduleFunction.englishName})--%>
	<%--			<img src="images/tb_05.jpg" />--%>
	<%--		</h2>--%>
	<%--	</div>--%>
	<!-- 导航条 -->
	<s:iterator value="moduleFunction" status="sta" id="MF">
		<s:if test="moduleFunction.functionLink!=''">
			<div style="padding: 15px 15px 15px;">
				<nav class="navbar navbar-default" style="margin-bottom:0">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<%--									<a class="navbar-brand " href="#">${moduleFunction.functionName}</a>--%>
						<%--					<a class="navbar-brand" href="#">主要</a>--%>
					</div>
					<div class="collapse navbar-collapse">
						<ul class="nav navbar-nav">
							<li class="active">
								<a href="javascript:void(0);"
									onclick="changeSubMF('${moduleFunction.functionLink}',this);">${moduleFunction.functionName}</a>
							</li>
							<s:iterator value="listSubModules" status="st" id="listSM">
								<li>
									<a href="javascript:void(0);" 
										<%--										onclick="changeSubMF('${listSM.subfunctionLink}',this);">${listSM.subfunctionName}</a>--%>
										onclick="changeSubMF('${listSM.functionLink}',this);">${listSM.functionName}</a>
								</li>
							</s:iterator>
						</ul>
					</div>
				</div>
				</nav>
			</div>
			<%--loading--%>
			<div align="center" id="loadingimg">
				<img alt="loading" class="img-circle" src="images/loading4.gif"
					width="50px;" style="height: 50px;" 
					onerror="this.src='images/man.jpg' ">
			</div>
			<%--loading end--%>
		</s:if>
		<%--		<s:else>--%>
		<%--			${MF.functionLink}--%>
		<%--		</s:else>--%>
	</s:iterator>
	<script type="text/javascript">
function changeSubMF(src, obj) {
	$(".active").removeClass("active");
	var node = obj.parentNode;
	node.className = "active";
	$("#workMainIframe").attr("src", src);
}
function addtofavorite() {

<%--	<a href="javascript:location.href='FavoriteAction_addFavorite.action?id=${moduleFunction.id}" +--%>
<%--	"&favorite.path=${moduleFunction.functionLink}&favorite.name='+encodeURI(encodeURI('${moduleFunction.functionName}'));"--%>
<%--				target="" class="sop fr">收藏</a>--%>
				
<%--	var favorite = {}--%>
<%--          favorite["id"]="${moduleFunction.id}";--%>
<%--          favorite["name"]="${moduleFunction.functionName}";--%>
<%--          favorite["path"]="${moduleFunction.functionLink}";--%>
<%--          --%>
          
    var favorite={"favorite.Mfid":"${moduleFunction.id}","favorite.name":"${moduleFunction.functionName}","favorite.path":"${moduleFunction.functionLink}"};
          
        
	$.ajax( {
			url : 'FavoriteAction_addFavorite.action',
			type : 'post',
			dataType : 'json',
			data : favorite,
			cache : false,
			success : function(obj) {
				if(obj==true){
					alert("收藏成功");	
				}else{
					alert("收藏失败");
				}
			},
			error : function() {
				alert("收藏失败");	
			}
		});
	
	
	
}

$(document).ready(function() {
	var wfrm = document.getElementById('workMainIframe');
	$(wfrm).load(function() { //  等iframe加载完毕  
		$("#loadingimg").hide(); 
			});
});
</script>
	<div class="spana"></div>