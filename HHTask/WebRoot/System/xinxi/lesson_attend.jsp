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
		<meta name="viewport" content="width=device-width, initial-scale=1" />
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<div style="font-size: large;">
			嘉定共修班2106届 版权:上海零参科技发展有限公司
			<br/>
			学员${user.name}的课时圆满情况;
			</div>
			
				<ul style=" list-style:inside; ">
					<s:iterator value="lessonList" id="pagelist" status="pagestauts">
						<li style="margin-top:5px;
			 border-bottom: 1px solid #666 dotted;
			 list-style-type:disc;">
							第${pagestauts.index+1}课时 
						<s:if test="#pagelist.activation == '未激活' || #pagelist.activation == null">
							<input type="button" value="-------" disabled="disabled"/>
						</s:if>
						<s:elseif test="#pagelist.attendtime !=null">
							<input type="button" value="已圆满" disabled="disabled"/>
						</s:elseif>
						<s:else>
							<input type="button" value="圆满" onclick="attend('${code}','${pagelist.id}')"/>
						</s:else>
						</li>
					</s:iterator>
				</ul>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function attend(code,id){
	$.ajax({
		 type : "POST",
			url : "attendClassAction_attend.action",
			data : {
				code:code,
				id:id,
			},
		dataType : "json",
		success : function(data) {
			if(data.success){
				window.location.reload();
			}
		}
		
	})
}

</SCRIPT>
	</body>
</html>
