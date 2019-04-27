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
		<script type="text/javascript"
			src="<%=basePath%>javascript/radialIndicator.js">
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title"></span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

				</div>
				<div id="logContentDiv" align="left"
					style="background-color: #ffffff; width: 100%; display: none">
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="WorkLogAction!showzplist.action?pageStatus=zhipai"
					id="testFrom" method="post" style="margin: 0px; padding: 0px">
					<table class="table">
						<tr>
							<th colspan="6">
								日志查询
							</th>
						</tr>
						<tr>
							<th align="right">
								标题:
							</th>
							<td align="left">
								<input type="text" name="workLog.title" />
							</td>
							<th align="right">
								状态:
							</th>
							<td align="left">
								<SELECT name="workLog.logStatus" style="width: 155px;">
									<OPTION value=""></OPTION>
									<OPTION value="待办">
										待办
									</OPTION>
									<OPTION value="办理中">
										办理中
									</OPTION>
									<OPTION value="已完成">
										已完成
									</OPTION>
								</SELECT>
							</td>
							<th align="right">
								类别:
							</th>
							<td align="left">
								<select id="logClassName"
									onmouseover="createDept('logClassName','WorkLogClassAction!findPersonWorkLogClass.action')"
									name="workLog.workLogClass.name" style="width: 100px">
									<option value=""></option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								月份:
							</th>
							<td align="left">
								<input class="Wdate" type="text" name="workLog.mouth"
									onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})" />
							</td>
							<th align="right">
								添加时间:
							</th>
							<td align="left">
								<input class="Wdate" type="text" name="workLog.addZpDataTime"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />

							</td>
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" value="查询" onclick="dept"
									style="width: 80px; height: 50px">
								<input type="reset" value="重置" style="width: 80px; height: 50px">
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							类别
						</th>
						<th align="center">
							标题
						</th>
						<th align="center">
							添加人
						</th>
						<th align="center">
							指派人
						</th>
						<th align="center">
							部门
						</th>
						<th align="center">
							月份
						</th>
						<th align="center">
							违规次数
						</th>
						<th align="center">
							添加时间
						</th>
						<th align="center">
							结束时间
						</th>
						<th align="center">
							提交时间
						</th>
						<th align="center">
							任务交期
						</th>
						<th align="center">
							状态
						</th>
						<th align="center">
							完成率
						</th>
						<th align="center">
							操作
						</th>
						<s:iterator value="dzpList" id="pageworkLog" status="pageStatus">
							<s:if test="#pageStatus.first">
								<tr bgcolor="red">
									<th colspan="16" align="center">
										<font color="#ffffff">工作记录待指派记录信息 <br /> </font>
									</th>
								</tr>
							</s:if>
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>

							<td>
								<s:if test="#pageStatus.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>
							<td>
								<s:if test="#pageworkLog.workLogClass!=null">
									<a
										href="WorkLogAction!findWorkLogByCondition.action?pageStatus=${pageStatus}&workLog.logStatus=&workLog.workLogClass.id=${pageworkLog.workLogClass.id}">${pageworkLog.workLogClass.name}</a>
								</s:if>
								<s:else>
									未分类
								</s:else>
							</td>
							<td>
								<a
									href="javascript:showContent('${pageworkLog.title}',
									'<b>内容:</b><br/>&nbsp;&nbsp;${pageworkLog.content}<br/><hr/><b>备注:</b><br/>&nbsp;&nbsp;${pageworkLog.remarks}');"
									onclick=""> ${pageworkLog.title}</a>
							</td>
							<td>
								${pageworkLog.userName}
							</td>
							<td>
								${pageworkLog.zpname}
							</td>
							<td>
								${pageworkLog.dept}
							</td>
							<td>
								${pageworkLog.mouth}
							</td>
							<td>
								<font color="#FF0000">${pageworkLog.wgcount}</font>
							</td>
							<td>
								${pageworkLog.addZpDataTime}
							</td>
							<td>
								${pageworkLog.endDateTime}
							</td>
							<td>
								${pageworkLog.submitDateTime}
							</td>
							<td>
								${pageworkLog.zptime}
							</td>
							<td>
								${pageworkLog.logStatus}
							</td>
							<td>
								<div class="jindu" style="cursor: pointer;"
									data="${pageworkLog.jindu}">
								</div>
							</td>
							<td>
								<s:if test="#pageworkLog.zpStatus=='待指派'">
									<a href="javascript:;"
										onclick="showContent('${pageworkLog.title}','operat','WorkLogAction!findWorkLogById.action?id=${pageworkLog.id}')">指派任务人</a>/
										</s:if>
								<s:else>
									<a href="javascript:;">指派任务人</a>/
								</s:else>
								<a
									href="WorkLogAction!delWorkLog.action?id=${pageworkLog.id}&pageStatus=zhipai"
									onclick="return confirm('确定要删除吗?')">删除</a>
							</td>
					</tr>
					</s:iterator>
					<s:iterator value="zpList" id="pageworkLog" status="pageStatus">
						<s:if test="#pageStatus.first">
							<tr bgcolor="red">
								<th colspan="16" align="center">
									<font color="#ffffff">工作记录已指派记录信息 <br /> </font>
								</th>
							</tr>
						</s:if>
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>

						<td>
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td>
							<s:if test="#pageworkLog.workLogClass!=null">
								<a
									href="WorkLogAction!findWorkLogByCondition.action?pageStatus=${pageStatus}&workLog.logStatus=&workLog.workLogClass.id=${pageworkLog.workLogClass.id}">${pageworkLog.workLogClass.name}</a>
							</s:if>
							<s:else>
									未分类
								</s:else>
						</td>
						<td>
							<a
								href="javascript:showContent('${pageworkLog.title}',
									'<b>内容:</b><br/>&nbsp;&nbsp;${pageworkLog.content}<br/><hr/><b>备注:</b><br/>&nbsp;&nbsp;${pageworkLog.remarks}');"
								onclick=""> ${pageworkLog.title}</a>
						</td>
						<td>
							${pageworkLog.userName}
						</td>
						<td>
							${pageworkLog.zpname}
						</td>
						<td>
							${pageworkLog.dept}
						</td>
						<td>
							${pageworkLog.mouth}
						</td>
						<td>
							<font color="#FF0000">${pageworkLog.wgcount}</font>
						</td>
						<td>
							${pageworkLog.addZpDataTime}
						</td>
						<td>
							${pageworkLog.endDateTime}
						</td>
						<td>
							${pageworkLog.submitDateTime}
						</td>
						<td>
							${pageworkLog.zptime}
						</td>
						<td>
							${pageworkLog.logStatus}
						</td>
						<td>
							<div class="jindu" style="cursor: pointer;"
								data="${pageworkLog.jindu}">
							</div>
						</td>
						<td>
							
								<a href="javascript:;"
									onclick="showContent('${pageworkLog.title}','operat','WorkLogAction!findWorkLogById.action?id=${pageworkLog.id}&pageStatus=genggai')">更改任务人</a>/
							
							<a
								href="WorkLogAction!delWorkLog.action?id=${pageworkLog.id}&pageStatus=zhipai"
								onclick="return confirm('删除之后，任务人那边也没有了哦！确定要删除吗?')">删除</a>
						</td>
						</tr>
					</s:iterator>
					<s:iterator value="workLogList" id="pageworkLog" status="pageStatus">
						<s:if test="#pageStatus.first">
							<tr bgcolor="green">
								<th colspan="16" align="center">
									<font color="#ffffff">工作记录已确认记录信息 <br /> </font>
								</th>
							</tr>
						</s:if>
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>

						<td>
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td>
							<s:if test="#pageworkLog.workLogClass!=null">
								<a
									href="WorkLogAction!findWorkLogByCondition.action?pageStatus=${pageStatus}&workLog.logStatus=&workLog.workLogClass.id=${pageworkLog.workLogClass.id}">${pageworkLog.workLogClass.name}</a>
							</s:if>
							<s:else>
									未分类
								</s:else>
						</td>
						<td>
							<a
								href="javascript:showContent('${pageworkLog.title}',
									'<b>内容:</b><br/>&nbsp;&nbsp;${pageworkLog.content}<br/><hr/><b>备注:</b><br/>&nbsp;&nbsp;${pageworkLog.remarks}');"
								onclick=""> ${pageworkLog.title}</a>
						</td>
						<td>
							${pageworkLog.userName}
						</td>
						<td>
							${pageworkLog.zpname}
						</td>
						<td>
							${pageworkLog.dept}
						</td>
						<td>
							${pageworkLog.mouth}
						</td>
						<td>
							<font color="#FF0000">${pageworkLog.wgcount}</font>
						</td>
						<td>
							${pageworkLog.addZpDataTime}
						</td>
						<td>
							${pageworkLog.endDateTime}
						</td>
						<td>
							${pageworkLog.submitDateTime}
						</td>
						<td>
							${pageworkLog.zptime}
						</td>
						<td>
							${pageworkLog.logStatus}
						</td>
						<td>
							<div class="jindu" style="cursor: pointer;"
								data="${pageworkLog.jindu}">
							</div>
						</td>
						<td>
							<s:if
								test="#pageworkLog.zptime!=#pageworkLog.yjtime && #pageworkLog.qrStatus!='同意'">
								<a href="javascript:;"
									onclick="querenLog('${pageworkLog.zptime}','${pageworkLog.yjtime}','${pageworkLog.id}')"
									id='queren'>确定任务</a>/
										</s:if>
							<s:elseif test="#pageworkLog.logStatus!='已完成'">
								<a href="javascript:;"
									onclick="showContent('${pageworkLog.title}','operat','WorkLogAction!findWorkLogById.action?id=${pageworkLog.id}&pageStatus=genggai')">更改任务人</a>/
							</s:elseif>
							<s:else>
								<a href="javascript:;">更改任务人</a>/
							</s:else>
							<a
								href="WorkLogAction!delWorkLog.action?id=${pageworkLog.id}&pageStatus=zhipai"
								onclick="return confirm('删除之后，任务人那边也没有了哦！确定要删除吗?')">删除</a>
						</td>
						</tr>
					</s:iterator>
					<tr>
					<s:if test="errorMessage==null">
						<td colspan="16" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />
					</s:if>
					<s:else>
						<td colspan="16" align="center" style="color: red">
							${errorMessage}
					</s:else>
					</td>
					</tr>
				</table>

			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
//显示内容
function showContent(title, content,src) {
	var logContentDiv = document.getElementById("logContentDiv");
	var operatingDiv = document.getElementById("operatingDiv");
	if (content == "operat") {
		operatingDiv.style.display = "block";
		logContentDiv.style.display = "none";
		document.getElementById("xiugaiIframe").src=src;
			
	} else {
		operatingDiv.style.display = "none";
		logContentDiv.style.display = "block";
		logContentDiv.innerHTML = content;
	}
	chageDiv("block", "您正在查看  " + title);
}

function querenLog(zptime,yjtime,id){
	 var queren=document.getElementById('queren');
	if(confirm('任务交期：'+zptime+'预计交期:'+yjtime+'是否将预交期设置为任务交期')){
		$.ajax({
			type:"POST",
			url:"WorkLogAction!updateWorkLog.action",
			data:{'workLog.id':id,'workLog.zptime':yjtime,'workLog.yjtime':yjtime,pageStatus:"queren",'workLog.qrStatus':"queren"},
			dataType:"json",
			success:function(data){
				if(data == "成功!"){
						alert(data);
					window.location.reload();
				}
			}
		})
	}else{
		showContent('${pageworkLog.title}','operat','WorkLogAction!findWorkLogById.action?id='+id+'&pageStatus=queren');
	}
}
$(function(){
	
	$(".jindu").each(function(i) {
		var hk_val = $(this).attr('data');
		
			$(this).radialIndicator( {
			barColor : ( {
				0 : '#FF0000',
				66 : '#FFFF00',
				100 : '#33CC33'
			}),
			barWidth : 3,
			radius : 22,
			initValue : hk_val,
			roundCorner : true,
			percentage : true
		});
	});
})

</SCRIPT>
	</body>
</html>
