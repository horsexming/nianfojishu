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
							<span id="title">历史记录</span>
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
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<input type="button" value="充值" onclick="chongzhi()" />
					&nbsp;&nbsp;&nbsp;
					
				</div>
			</div>

			<div align="center">
				
				<!-- 查询 -->
				<form action="carAllowAction!findAllSum.action"
					method="post">
					<table class="table">
						<tr>
							<th width="15%">
								工号：<input type="text" name="carAllowSum.code"
									value="${carAllowSum.code}" />
							</th>							
							<th width="15%">
								姓名：<input type="text" name="carAllowSum.name"
									value="${carAllowSum.name}" />
							</th>													
						
							<th>
								车牌号：<input type="text" name="carAllowSum.fileName"
									value="${carAllowSum.platenumber}" />
							</th>
							<th>
								<input type="submit" style="width: 120px; height: 80px;"
									value="查找">
								&nbsp;
							</th>							
						</tr>						
					</table>
				</form>
				<!-- 列表展示 -->
				<table class="table">
					<tr bgcolor="#c0dcf2" height="30px"
						style="border-collapse: separate;">
						<th align="center">
							序号
						</th>
						<th align="center">
							车牌号
						</th>
						<th align="center">
							车主
						</th>
						<th align="center">
							过路费汇总
						</th>
						<th align="center">
							停车费汇总
						</th>
						<th align="center">
							其他费用汇总
						</th>
						<th>
							保险费汇总
						</th>
						<th>
							总申请金额
						</th>
						<th align="center">
							油卡充值总金额
						</th>						
						<th>
							个人补贴额度
						</th>
						<th>
							单次充值限额
						</th>						
						<th>
							申请余额(待用)
						</th>
						<th>
							当前可申领补贴额
						</th>
						<th>
							最后申请日期
						</th>
						<th align="center">
							操作
						</th>
					</tr>

					<s:if test="{list.size()>0}">
						<s:iterator value="list" status="se" id="bxd">
							<s:if test="#se.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#se.index+1" />
							</td>
							<td>
								${bxd.platenumber}
							</td>
							<td>
								${bxd.name}
							</td>
							
							<td>
								${bxd.roadcost}
							</td>
							<td>
								${bxd.parkcost}
							</td>
							<td>
								${bxd.repaircost}
							</td>
							<td>
								${bxd.insurancecost}
							</td>
							<td>
								${bxd.sumcost}
							</td>
							<td>
								${bxd.sumchognzhi}
							</td>
							<td>
								${bxd.chognzhiedu}
							</td>	
							<td>
								${bxd.chongzhiMax}
							</td>	
							<td>
								${bxd.sumremainbaoxiao}
							</td>
												
							<td>
								${bxd.sumbaoxiao}
							</td>
							
							<td>
								${bxd.lastChongzhiMonth}
							</td>
							<td>
							<input type="button" id="delFile"
									onclick="appHistory('${bxd.id}')" value="申请记录">
								<input type="button" id="updateFile"
									onclick="appchognzhiHistory('${bxd.id}')" value="充值记录" />
									
									<input type="button"
									onclick="butieAppHistory('${bxd.id}')" value="申领记录" />
								
								<input type="button" 
									onclick="shezhiXiane('${bxd.id}')" value="设置限额">
								<input type="button" id="delFile"
									onclick="dele1('${bxd.name}','${bxd.id}')" value="del">
									
							</td>
						</s:iterator>
						<tr>
							<td colspan="15" align="right">
								共
								<s:property value="total" />
								页 第
								<s:property value="cpage" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td colspan="15" style="font-size: 15px; color: red;">
								对不起，没有查到相关的车补申请信息
							</td>
						</tr>
					</s:else>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//个人申请历史记录
function appHistory(id1) {
	//alert(id1);
	var url = "carAllowAction!appHistory.action?id="+id1;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
//个人充值历史记录
function appchognzhiHistory(id1) {
	//alert(id1);
	var url = "carAllowAction!appchognzhiHistory.action?tag=cz&id="+id1;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
//个人申領历史记录
function butieAppHistory(id1) {
	//alert(id1);
	var url = "carAllowAction!appchognzhiHistory.action?tag=bx&id="+id1;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
//设置限额
function shezhiXiane(id1) {
	//alert(id1);
	var url = "carAllowAction!preshezhiXiane.action?id="+id1;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}

//充值
function chongzhi(){
	var url = "carAllowAction!preChongzhi.action";
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
//报销
function baoxiao(){
	var url = "carAllowAction!baoxiao.action";
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
//刪除
function dele1(name,id1){
	if (window.confirm('确认要删除' + name + '的车补汇总吗?')) {
		window.location.href = "carAllowAction!delsumAllow.action?id="+ id1;
	}
}
</script>
	</body>
</html>
