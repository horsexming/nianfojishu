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
		<div id="bodyDiv" align="center" class="transDiv" style="z-index: 2"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none; top: 20px;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<div id="submitProcessDiv" style="display: none;">
					<table style="width: 100%; margin-top: ">
						<tr>
							<td>
								查看明细:
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none');">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
						<table class="table" style="width: 40%" align="center">
							<tr>
								<td align="center">
									<b>查看明细</b>
								</td>
							</tr>
							<tr>
								<th align="center">
									<textarea id="infornei" rows="5" cols="100"></textarea>
								</th>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style ="width: 100%;">

			<div align="center">
				<h3>
					内部员工进出门记录查询
				</h3>
				<form action="AccessRecordsAction_showList_log.action"
					method="post">
					<table class="table">
						<tr style="width: 100%">
							<th align="center" style="width: 25%">
								员工姓名
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="accessLogInfor.username" />
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
								员工卡号
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="accessLogInfor.cardId" />
							</td>
						</tr>
						<tr>
							<th align="center" style="width: 25%">
								进出状态
							</th>
							<td align="center" style="width: 25%">
								<select name="accessLogInfor.inOutType" style="width: 156px;">
									<option value="">
										请选择进出状态
									</option>
									<option value="进门">
										进门
									</option>
									<option value="出门">
										出门
									</option>
								</select>
							</td>
							<td align="center" style="width: 50%" colspan="2">
								<input type="submit" value="查询"
									style="width: 100px; height: 25px;" />
								<input type="reset" value="清空"
									style="width: 100px; height: 25px;" />
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
							姓名
						</td>
						<td align="center">
							卡号
						</td>
						<td align="center">
							进出状态
						</td>
						<td align="center">
							设备名称
						</td>
						<td align="center">
							出入时间
						</td>
						<td align="center">
							明细
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
							${samples.addTime}
						</td>
						<td align="center">
							<a onclick="openMx('${samples.infor}')">查看详细</a>
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
	url:'AccessEquipmentAction_findAllAce.action',
 	type:'POST',
	dataType:'json',
	cache : false,//防止数据缓存
	data:{},
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
function openMx(infor){
	alert(infor);
	//$("#infornei").val(infor);
	//$("#submitProcessDiv").show();
	//chageDiv("block");
	//单独设置弹出层的高度
	//var thisTopHeight = $(obj).offset().top;
	//$('#contentDiv').css( {
	//	'top' : thisTopHeight + 'px'
	//});
}
		</script>
	</body>
</html>
