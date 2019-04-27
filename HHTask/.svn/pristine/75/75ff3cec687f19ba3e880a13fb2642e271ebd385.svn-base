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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
					<h3>
					rtx消息管理<br/>(Skill Coefficient Management)
				</h3>
				<form action="rtxMsgAction_showList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								发送人名称（sender name）：
								<input type="text" name="rtxMsg.userName" value="<s:property value="rtxMsg.userName"/>" />
							</td>
							<td align="center">
								发送人工号（sender code）：
								<input type="text" name="rtxMsg.userCode" value="<s:property value="rtxMsg.userCode"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								接收人名称（receivers name）：
								<input type="text" name="rtxMsg.receiverNames" value="<s:property value="rtxMsg.receiverNames"/>" />
							</td>
							<td align="center">
								接收人工号（receivers code）：
								<input type="text" name="rtxMsg.receivers" value="<s:property value="rtxMsg.receivers"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								标题（title）：
								<input type="text" name="rtxMsg.title" value="<s:property value="rtxMsg.title"/>" />
							</td>
							<td align="center">
								消息内容（message conetxt）：
								<input type="text" name="rtxMsg.msg" value="<s:property value="rtxMsg.msg"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								消息类型（message context）：
								<SELECT name="rtxMsg.msgType">
								<s:if test="rtxMsg.msgType!=null">
								<option value="<s:property value="rtxMsg.msgType"/>">
								<s:property value="rtxMsg.msgType"/>
								</option>
								</s:if>
								<option value="全部">
								全部
								</option>
								<option value="平台消息">
								平台消息
								</option>
								<option value="流程消息">
								流程消息
								</option>
								</SELECT>
							</td>
							<td align="center">
								是否发送成功
								<SELECT name="rtxMsg.sendOk">
								<s:if test="rtxMsg.sendOk=='OK'">
								<option value="OK">
								成功
								</option>
								</s:if>
								<s:elseif test="rtxMsg.sendOk=='NO'">
								<option value="NO">
								失败
								</option>
								</s:elseif>
								<option>
								</option>
								<option value="OK">
								成功
								</option>
								<option value="NO">
								失败
								</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
								<input type="reset" style="width: 100px; height: 40px;"
									value="重置(reset)" />
							</td>
						</tr>
					</table>
				</form>
				<table width="100%" border="0" style="border-collapse: collapse;" class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							发送人名字
						</td>
						<td align="center">
							发送人工号
						</td>
						<td align="center" width="300px">
							接收人名字
						</td>
						<td align="center" width="200px">
							接收人工号
						</td>
						<td align="center">
							标题
						</td>	
						<td align="center" width="300px">
							消息内容
						</td>	
						<td align="center">
							发送时间
						</td>
						<td align="center">
							发送消息类型
						</td>							
						<td align="center">
							发送成功
						</td>							
						<td align="center">
							操作
						</td>							
					</tr>
					<s:iterator value="rtxMsgList" id="pageRtxMsg" status="pageStatus">
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
							${pageRtxMsg.userName}
						</td>
						<td>
							${pageRtxMsg.userCode}
						</td>
						<td width="300px">
							${pageRtxMsg.receiverNames}
						</td>
						<td width="200px">
							${pageRtxMsg.receivers}
						</td>
						<td>
							${pageRtxMsg.title}
						</td>
						<td width="300px">
							${pageRtxMsg.msg}
						</td>
						<td>
							${pageRtxMsg.sendTime}
						</td>
						<td>
							${pageRtxMsg.msgType}
						</td>
						<td>
							<s:if test="#pageRtxMsg.sendOk=='NO'">
							失败
							</s:if>
							<s:else>成功</s:else>
						</td>
						<td>
							<input type="button" value="重发" style="width: 40px;height: 20px;" onclick="sendAgin(${pageRtxMsg.id})">
						</td>
					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
						
						<s:if test="successMessage!=null">
						<tr>
							<td colspan="11" align="center" style="color: red">
								${successMessage}
								
						</td>
					</tr>
                          </s:if>
				</table>
			</div>
			<br>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function sendAgin(id){
	$.ajax( {
			type : 'post',
			url : 'rtxMsgAction_sendAgin.action',
			dataType : 'json',
			data : {
				id : id
			},
			cache : false,//防止数据缓存
			success : function(data) {
				alert(data);
				window.location.reload();
			}

		});
}
</SCRIPT>
	</body>
</html>
