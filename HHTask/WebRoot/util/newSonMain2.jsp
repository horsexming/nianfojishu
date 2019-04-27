<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/util/newHead2.jsp"%>
<div class="main">
	<div class="m_top">
		<ul>
			<s:iterator id="mf" value="allModuleList" status="pageId">
				<li
					style="float:left;width:${1008/8-7}px;height:99px;position: absolute; z-index: 1; margin-left: ${pageId.index*1008/8}px; display: inline;">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${mf.id}&pageStatus=2"><img
							src="<%=basePath%>upload/file/sysImages/${mf.smallImageName}" style="width: ${1008/8-7}px"/>
					</a>
				</li>
			</s:iterator>
		</ul>
	</div>
	<div class="spana"></div>
	<div class="m_c">
		<h1>
			<a href="ModuleFunctionAction!findMfByUser2.action">首页</a> >
			${mfNames}
			<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${id}"
				class="sop fr">刷新</a><a href="javascript:;"
				onclick="document.getElementById('workMainIframe').contentWindow.history.back();"
				class="sop fr">返回</a><a
				href="<%=basePath%>${moduleFunction.functionLink}" target="showQuan"
				class="sop fr">全屏</a>
		</h1>
	</div>
	<p class="gl">
		<img src="images/tb_05.jpg" />
		${moduleFunction.functionIntro}
	</p>
	<div class="mkgl">
		<h2>
			<img src="images/tb_03.jpg" />
			${moduleFunction.functionName}(${moduleFunction.englishName})
			<img src="images/tb_05.jpg" />
		</h2>
	</div>
	<div class="spana"></div>