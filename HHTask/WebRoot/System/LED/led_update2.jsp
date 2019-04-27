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
					修改LED
					<br />
					（update LED）
				</h3>
				<form action="lEDAction_update.action" method="post"
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
								<input type="hidden" name="lED.id" id="id"
									value="<s:property value='lED.id'/>" />
								<input type="text" name="lED.name" id="name"
									value="<s:property value='lED.name'/>" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								序号
								<br />
								（number）
							</th>
							<td>
								<input type="text" name="lED.number" id="number"
									value="<s:property value='lED.number'/>" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								ip
								<br />
								（ip）
							</th>
							<td>
								<input type="text" name="lED.ip" id="ip"
									value="<s:property value='lED.ip'/>" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								端口
								<br />
								（port）
							</th>
							<td>
								<input type="text" name="lED.port" id="port"
									value="<s:property value='lED.port'/>" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								域名
								<br />
								（domainName）
							</th>
							<td>
								<input type="text" name="lED.domainName" id="domainName"
									value="<s:property value='lED.domainName'/>"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								工位
								<br />
								（stations）
							</th>
							<td>
								<input type="text" name="lED.stations" id="stations"
									value="<s:property value='lED.stations'/>" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								宽
								<br />
								（width）
							</th>
							<td>
								<input type="text" name="lED.width" id="width"
									value="<s:property value='lED.width'/>" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								高
								<br />
								（higth）
							</th>
							<td>
								<input type="text" name="lED.higth" id="higth"
									value="<s:property value='lED.higth'/>" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								字体大小
								<br />
								（fontSize）
							</th>
							<td>
								<input type="text" name="lED.fontSize" id="fontSize"
									value="<s:property value='lED.fontSize'/>" readonly="readonly" />
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
								<input type="text" value="${lED.color}" readonly="readonly" />
								<select name="lED.color">
									<option value="0x0000FF">
										红色
									</option>
									<option value="0x00FF00">
										绿色
									</option>
									<option value="0x00FFFF">
										黄色
									</option>
									<option value="0xffff">
										单色屏
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
									<option value="${lED.style}">
										${lED.style}
									</option>
									<option value="1">
										64*32 居中左移
									</option>
									<option value="2">
										64*32 两条左移
									</option>
									<option value="3">
										64*32 连续上移
									</option>
									<option value="4">
										192*64 倒计时&连续上移
									</option>
									<option value="5">
										192*64 倒计时&单条左移
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								穿戴标准
								<br />
								（dress）
							</th>
							<td>
								<input type="hidden" name="lED.dress" id="dress"
									value="<s:property value='lED.dress'/>" />
								<s:if test="lED.dress.indexOf('帽子')>-1">
									<input type="checkbox" name="dress1" value="帽子"
										onchange="changeDress()" checked="checked">帽子
								</s:if>
								<s:else>
									<input type="checkbox" name="dress1" value="帽子"
										onchange="changeDress()">帽子
								</s:else>
								<s:if test="lED.dress.indexOf('防护眼镜')>-1">
									<input type="checkbox" name="dress1" value="防护眼镜"
										onchange="changeDress()" checked="checked">防护眼镜
								</s:if>
								<s:else>
									<input type="checkbox" name="dress1" value="防护眼镜"
										onchange="changeDress()">防护眼镜
								</s:else>
								<s:if test="lED.dress.indexOf('护耳器')>-1">
									<input type="checkbox" name="dress1" value="护耳器"
										onchange="changeDress()" checked="checked">护耳器
								</s:if>
								<s:else>
									<input type="checkbox" name="dress1" value="护耳器"
										onchange="changeDress()">护耳器
								</s:else>
								<s:if test="lED.dress.indexOf('口罩')>-1">
									<input type="checkbox" name="dress1" value="口罩"
										onchange="changeDress()" checked="checked">口罩
								</s:if>
								<s:else>
									<input type="checkbox" name="dress1" value="口罩"
										onchange="changeDress()">口罩
								</s:else>
								<s:if test="lED.dress.indexOf('工作服')>-1">
									<input type="checkbox" name="dress1" value="工作服"
										onchange="changeDress()" checked="checked">工作服
								</s:if>
								<s:else>
									<input type="checkbox" name="dress1" value="工作服"
										onchange="changeDress()">工作服
								</s:else>
								<s:if test="lED.dress.indexOf('防护手套')>-1">
									<input type="checkbox" name="dress1" value="防护手套"
										onchange="changeDress()" checked="checked">防护手套
								</s:if>
								<s:else>
									<input type="checkbox" name="dress1" value="防护手套"
										onchange="changeDress()">防护手套
								</s:else>
								<s:if test="lED.dress.indexOf('工作鞋')>-1">
									<input type="checkbox" name="dress1" value="工作鞋"
										onchange="changeDress()" checked="checked">工作鞋
								</s:if>
								<s:else>
									<input type="checkbox" name="dress1" value="工作鞋"
										onchange="changeDress()">工作鞋
								</s:else>
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
	var dress = document.getElementById("dress").value;
	if (dress == "") {
		alert("请选择穿戴标准");
		return false;
	}
}
function changeDress() {
	var dresses = document.getElementsByName("dress1");
	var dressAll = "";
	var n = 0;
	for ( var i = 0; i < dresses.length; i++) {
		if (dresses[i].checked) {
			if (n == 0) {
				dressAll += dresses[i].value;
			} else {
				dressAll += "," + dresses[i].value;
			}
			n++;
		}
	}
	document.getElementById("dress").value = dressAll;
}
</script>
	</body>
</html>
