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
		.button {
	display: inline-block;
	zoom: 1; /* zoom and *display = ie7 hack for display:inline-block */ *
	display: inline;
	vertical-align: baseline;
	margin: 0 2px;
	outline: none;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	font: 14px/ 100% Arial, Helvetica, sans-serif;
	padding: .5em 2em .55em;
	text-shadow: 0 1px 1px rgba(0, 0, 0, .3);
	-webkit-border-radius: .5em;
	-moz-border-radius: .5em;
	border-radius: .5em;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
}

.button:hover {
	text-decoration: none;
}

.button:active {
	position: relative;
	top: 1px;
}
		
		</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<div align="left">
				<font>
				<STRONG>
					计算公式:<br/>
				</STRONG>
				
				
					最后50个人加入时间之和（不足五十人时则全部时间之和）+时时彩5位开奖数字()%奖品总人次+10000001 = 幸运号码<br/>
					<s:if test="sum!=null && sum>0 &&
					str!=null  && str>0&& 
					peoplenum!=null && peoplenum >0" >
						(${sum}+${str})%${peoplenum}+10000001= ${(sum+str)%peoplenum+10000001}
					</s:if>
				</font>
			<div align="center">
				<a href="IntegralGiftAction_findAlligSetList.action?status=baoming">报名夺宝</a>
			</div>	
			</div>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>序号</th>
						<th>工号</th>
						<th>姓名</th>
						<th>部门</th>
						<th>报名时间</th>
						<th>结束时间（数字）</th>
						<th>编号</th>
					</tr>
				<s:iterator id="pagelist" value="idgiftList" status="pagestatus">
					<s:if test="sum!=null && sum>0 &&
					str!=null  && str>0&& 
					peoplenum!=null && peoplenum >0 && (sum+str)%peoplenum+10000001 == #pagelist.num">
					<tr align="center" bgcolor="#fd6653">
				</s:if>
   					<s:elseif test="#statussdf.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:elseif>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
					</s:else>
					<td>
						<s:property value="#pagestatus.index+1" />
					</td>
					<td>
						${pagelist.code}
					</td>
					<td >
						${pagelist.name}
					</td>
					<td>${pagelist.dept}</td>
					<td>${pagelist.jointime}</td>
					<td>${pagelist.joinnum}</td>
					<td>${pagelist.num}</td>
					</tr>
   				 </s:iterator>
   				 <tr>
				<td colspan="11" align="right">
								第
					<font color="red"><s:property value="cpage" /> </font> /
						<s:property value="total" />
							页 
						<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
									
				</td>
			</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">


</script>
	</body>
</html>
