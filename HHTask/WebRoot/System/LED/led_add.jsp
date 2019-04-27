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
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<h3>
					添加LED
					<br />
					（add LED）
				</h3>
				<form action="lEDAction_add.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr>
							<th align="right">
								名称
								<br />
								（name）
							</th>
							<td>
								<input type="hidden" name="pageStatus"
									value="<s:property value='pageStatus'/>" />
								<input type="text" name="lED.name" id="name" />
							</td>
						</tr>
						<tr>
							<th align="right">
								编号
								<br />
								（number）
							</th>
							<td>
								<input type="text" name="lED.number" id="number" />
							</td>
						</tr>
						<tr>
							<th align="right">
								ip
								<br />
								（ip）
							</th>
							<td>
								<input type="text" name="lED.ip" id="ip" />
							</td>
						</tr>
						<tr>
							<th align="right">
								端口
								<br />
								（port）
							</th>
							<td>
								<input type="text" name="lED.port" id="port" />
							</td>
						</tr>
						<tr>
							<th align="right">
								域名
								<br />
								（domainName）
							</th>
							<td>
								<input type="text" name="lED.domainName" id="domainName" />
							</td>
						</tr>
						<tr>
							<th align="right">
								工位
								<br />
								（stations）
							</th>
							<td>
								<input type="text" name="lED.stations" id="stations" />
							</td>
						</tr>
						<tr>
							<th align="right">
								宽
								<br />
								（width）
							</th>
							<td>
								<input type="text" name="lED.width" id="width" />
							</td>
						</tr>
						<tr>
							<th align="right">
								高
								<br />
								（higth）
							</th>
							<td>
								<input type="text" name="lED.higth" id="higth" />
							</td>
						</tr>
						<tr>
							<th align="right">
								滚动字体大小
								<br />
								（fontSize）
							</th>
							<td>
								<input type="text" name="lED.fontSize" id="fontSize"  value="10"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								完成字体大小
								<br />
								（fontSize）
							</th>
							<td>
								<input type="text" name="lED.endfontSize" id="fontSize" value="20"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								颜色
								<br />
							</th>
							<td>
								<select name="lED.color">
									<option value="255">
										255、红色
									</option>
									<option value="65280">
										65280、绿色
									</option>
									<option value="65535">
										65535、黄色
									</option>
									<option value="65535">
										65535、单色屏
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								推送样式
								<br />
							</th>
							<td>
								<select name="lED.style">
									<option value="2">
										2、192*64(倒计时)
									</option>
									<option value="1">
										1、64*32
									</option>
									<option value="3">
										3、128*64(倒计时)
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								移动方式
								<br />
							</th>
							<td>
								<select name="lED.iactionType">
									<option value="5">
										5、连续上移
									</option>
									<option value="2">
										2、左移
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								对齐方式
								<br />
							</th>
							<td>
								<select name="lED.ialignStyle">
									<option value="0">
										0、左对齐
									</option>
									<option value="1">
										1、居中
									</option>
									<option value="2">
										2、右对齐
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								暂停时间
								<br />
							</th>
							<td>
								<input name="lED.iholdTime" value="20">
							</td>
						</tr>
						<tr>
							<th align="right">
								穿戴标准
								<br />
								（dress）
							</th>
							<td>
								<input type="hidden" name="lED.dress" id="dress" />
								<input type="checkbox" name="dress1" value="帽子"
									onchange="changeDress()">
								帽子
								<input type="checkbox" name="dress1" value="防护眼镜"
									onchange="changeDress()">
								防护眼镜
								<input type="checkbox" name="dress1" value="护耳器"
									onchange="changeDress()">
								护耳器
								<input type="checkbox" name="dress1" value="口罩"
									onchange="changeDress()">
								口罩
								<input type="checkbox" name="dress1" value="工作服"
									onchange="changeDress()">
								工作服
								<input type="checkbox" name="dress1" value="防护手套"
									onchange="changeDress()">
								防护手套
								<input type="checkbox" name="dress1" value="工作鞋"
									onchange="changeDress()">
								工作鞋
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="提交(submit) "
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	var name = document.getElementById("name").value;
	var number = document.getElementById("number").value;
	var ip = document.getElementById("ip").value;
	var port = document.getElementById("port").value;
	var domainName = document.getElementById("domainName").value;
	var stations = document.getElementById("stations").value;
	var width = document.getElementById("width").value;
	var higth = document.getElementById("higth").value;
	var fontSize = document.getElementById("fontSize").value;
	var dress = document.getElementById("dress").value;
	if (name == "") {
		alert("请输入名称!");
		return false;
	}
	if (ip == "") {
		alert("请输入ip!");
		return false;
	}
	if (port == "") {
		alert("请输入端口!");
		return false;
	}
<%--	if (domainName == "") {--%>
<%--		alert("请输入域名!");--%>
<%--		return false;--%>
<%--	}--%>
	if (stations == "") {
		alert("请输入工位!");
		return false;
	}
	if (width == ""||isNaN(width)) {
		alert("请输入宽且为数字!");
		return false;
	}
	if (higth == ""||isNaN(higth)) {
		alert("请输入高且为数字!");
		return false;
	}
	if (fontSize == "") {
		alert("请输入字体大小!");
		return false;
	}
	if(dress==""){
		alert("请选择穿戴标准");
		return false;
	}
}
function changeDress(){
	var dresses=document.getElementsByName("dress1");
		  var dressAll="";
		  var n=0;
	for(var i=0;i<dresses.length;i++){
		if(dresses[i].checked){
			if(n==0){
		      dressAll+=dresses[i].value;
			}else{
				dressAll+=","+dresses[i].value;
			}
			n++;
		}
	}
	document.getElementById("dress").value=dressAll;
}
</script>
	</body>
</html>
