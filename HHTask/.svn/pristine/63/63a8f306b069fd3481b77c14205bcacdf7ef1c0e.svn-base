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
	<body onload="selectAce()">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style ="width: 100%;">

			<div align="center">
				<h3>
					水阀明细记录查询
				</h3>
				<form action="AccessRecordsAction_showList_log.action?tag=${tag}"
					method="post">
					<table class="table">
						<tr style="width: 100%">
							<th align="center" style="width: 15%">
								设备名称
							</th>
							<td align="center" style="width: 25%">
								<select name="accessLogInfor.aceId" style="width: 156px;" id = "aceName"
									>
									<option value="" >
										请选择设备
									</option>
								</select>
							</td>
							<th align="center" style="width: 25%">
								查询的日期
							</th>
							<td align="center" style="width: 25%">
								<input class="Wdate" type="text" name="accessLogInfor.addTime"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<td align="center" style="width: 50%" colspan="4">
								<input type="submit" value="查询"
									style="width: 100px; height: 45px;" /> 
								<input type="button" value="返回" onclick="bank()"
									style="width: 100px; height: 45px;" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							设备名称
						</td>
						<td align="center">
							操作状态
						</td>
						<td align="center">
							设备类型
						</td>
						<td align="center">
							安装位置
						</td>
						<td align="center">
							用水量
						</td>
						<td align="center">
							采集时间
						</td>
					</tr>
					<s:iterator value="accessLogInforList" id="samples"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${samples.username}
						</td>
						<td align="center">
							${samples.cardId}
						</td>
						<td align="center">
							${samples.inOutType}
						</td>
						<td align="center">
							${samples.aceName}
						</td>
						<td align="center">
							${samples.yanzheng}升
						</td>
						<td align="center">
							${samples.addTime}
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="7" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="7" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function selectAce(){
$.ajax({
	url:'AccessEquipmentAction_findAllAceShui.action',
 	type:'POST',
	dataType:'json',
	cache : false,//防止数据缓存
	data:{
		tag:'水阀'
	},
	success:function(data){
			$(data).each(function(){
				$("<option value='"+this.id+"'>"+this.equipmentName+'/'+this.equipmentNum+"</option>").appendTo("#aceName");
					});
	},
	error : function() {
					alert("服务器异常!");
			}
	});
}
function bank(){
	window.location.href = "AccessEquipmentAction_showList.action?tag=${tag}";
}
		</script>
	</body>
</html>
