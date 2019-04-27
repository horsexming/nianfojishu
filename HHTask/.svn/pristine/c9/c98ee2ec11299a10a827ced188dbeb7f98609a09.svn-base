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
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left"> 
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
					<a href="Detail_addInput.action" style="color: #ffffff">添加计划</a>

				</div>
			</div>
			
			<script type="text/javascript">
function addDetail(id, element, index) {
	addDetailItemAjax(id, element);
	return false;
}

function addDetailItemAjax(id, element, index) {
	sendRequest('Detail_addDetailItem.action?detail.id=' + id, function() {
		if (XMLHttpReq.readyState == 4) { // 判断对象状态
				if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
				var message = XMLHttpReq.responseText;
				if(message == "1"){
					element.style.display = "none";
					var k = element.parentNode;
					var span = document.getElementById("addItemSpan"
							+ String.valueOf(index));
					var font_file = document.createElement("font");
					font_file.setAttribute("color", "red")
					font_file.innerHTML = "已加入";
					k.appendChild(font_file);
				} else {
					window.alert("页面异常,请重试!");
				}
			} else { //页面不正常
				window.alert("页面异常,请重试!");
			}
		}
	});
}
</script>
			<div align="center">
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							采购产品
						</th>
						<th align="center">
							月份
						</th>
						<th align="center">
							数量
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							规格
						</th>
						<th align="center">
							计划单编号
						</th>
						<th align="center">
							说明
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="details" id="d" status="detailsStatus">
						<s:if test="#detailsStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>

							<s:if test="#detailsStatus.index%2==0">
								<font color="red"> <s:property
										value="#detailsStatus.index+1" /> </font>
							</s:if>
							<s:else>
								<s:property value="#detailsStatus.index+1" />
							</s:else>
						</td>

						<td>
							${d.templet.name}
						</td>
						<td>
							${d.month}
						</td>
						<td>
							${d.quantity}
						</td>
						<td>
							${d.templet.partsNumber}
						</td>
						<td>
							${d.templet.specification}
						</td>
						<td>
							${d.detailNumber}
						</td>
						<td>
							${d.explanation}
						</td>
						<td>
							<a href="Detail_delete.action?detail.id=${d.id}"
								onclick="if(confirm('确实要删除该记录吗?')) return true; return false;">删除</a>/
							<a href="Detail_updateInput.action?detail.id=${d.id}">修改</a>/
							
							<a target="_blank" href="Detail_getSingleDetail.action?detail.id=${d.id}">查看</a>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="9" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</s:if>
						<s:else>
							<td colspan="8" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>

</html>
