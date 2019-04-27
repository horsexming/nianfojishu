<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
body {
	text-align: center;
}
</style>

		<%@include file="/util/sonHead.jsp"%>
	</head>
	<%
		Users user = (Users) session.getAttribute("Users");
	%>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">完善个人基本信息</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv" style="background-color: #fff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 600px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="gongneng"
			style="text-align:center;width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
				</div>
			</div>
			
            <form action="carAllowAction!saveCarAllowSum.action" method="post">
            	<table align="center">
            	<tr><th colspan="2">个人信息维护</th></tr>
            	<tr><th>工号：</th><th><input type="text" name="carAllowSum.code" style="width:150px;" value="${users.code}" readonly="readonly"/></th></tr>
            	<tr><th>姓名:</th><th><input type="text" name="carAllowSum.name" style="width:150px;" value="${users.name}" readonly="readonly"/></th></tr>
            	<tr>
            	<th>车牌号：</th>
            	<th>
            	<input type="text" name="carAllowSum.platenumber" style="width:150px;" value="${credentials.platenumber}" readonly="readonly"/>
            	<!-- 
            	<select name="tag">
            	<option value="">选择省份
            	<option value="京">京</option><option value="津">津</option><option value="渝">渝</option>
            	<option value="沪">沪</option><option value="冀">冀</option><option value="晋">晋</option>
            	<option value="辽">辽</option><option value="吉">吉</option><option value="黑">黑</option>
            	<option value="苏">苏</option><option value="浙">浙</option><option value="皖">皖</option>
            	<option value="闽">闽</option><option value="赣">赣</option><option value="鲁">鲁</option>
            	<option value="豫">豫</option><option value="鄂">鄂</option><option value="湘">湘</option>
            	<option value="粤">粤</option><option value="琼">琼</option><option value="川">川</option>
            	<option value="贵">贵</option><option value="云">云</option><option value="陕">陕</option>
            	<option value="甘">甘</option><option value="青">青</option><option value="蒙">蒙</option>
            	<option value="桂">桂</option><option value="宁">宁</option><option value="新">新</option>
            	<option value="藏">藏</option><option value="使">使</option><option value="领">领</option>
            	<option value="警">警</option><option value="学">学</option><option value="港">港</option>
            	<option value="澳">澳</option>
            	</select>
            	<input type="text" name="message" style="width:150px;"/>
            	 -->
            	</th>
            	</tr>
            	<tr><th>油卡号:</th><th><input type="text" name="carAllowSum.icNumber" style="width:150px;" /></th></tr>
            	</table>          	
            	
				

          	<br/>
          	<table align="center">
          	<tr><th>条款序号</th><th>车补使用条款</th></tr>
          				<s:if test="{list.size()>0}">
						<s:iterator value="list" status="se" id="bxd">
							<tr>
							<td>
								<s:property value="#se.index+1" />
							</td>
							<td>
								${bxd.content}
							</td>
							</tr>
							</s:iterator>
							</s:if>
					<tr><th colspan="2"><input type="checkbox" id="check1"
									onclick="agreeTiaokuan(this)"></th></tr>		
          	</table>
          	<div style="width:99%;border: 1">
            	<input type="submit" id="tijiao" disabled="disabled" style="width:120px;height:60px;" value="添加">
            	</div>
          	  
            </form>
			<br/>
			
			</div>
			<br>
		<%@include file="/util/foot.jsp"%>
	</body>
	<script type="text/javascript">
	function agreeTiaokuan(obj){
		var check = obj;
		var stamp = document.getElementById("tijiao");
		if(check.checked == true){
			stamp.disabled=false;
		}else{
			stamp.disabled=true;
		}
	}
	</script>

</html>
