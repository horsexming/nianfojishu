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
		<script type="text/javascript" src="javascript/jquery-1.7.2.min.js"></script>
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
				</div>
			</div>
			
			<div align="center">
				<div><font color="red">${successMessage}${errorMessage}</font></div>
				<div>
					<form method="post" action="MsgGroup_sendInput.action" >
						<table class="table" align="center">
							<tr>
								<td>姓名</td>
								<td><input type="text" name="msgSendInput.username" maxlength="10" /></td>
								<td>手机号</td>
								<td><input type="text" name="msgSendInput.phone" maxlength="10" /></td>
								<td>所在组</td>
								<td>
									<select name="msgSendInput.userGroup">
										<option></option>
										<s:iterator value="groups" id="kkk">
											<option value="${kkk}">${kkk}</option>
										</s:iterator>
									</select>
								</td>
								<td><input type="submit" value="提交" /></td>
							</tr>
						</table>
					</form>
				</div>
				<br/>
				<br/>
				<div>
					<form id="form2" action="" onsubmit="return checkSubmit();" >
					
						<table class="table" width="80%" border="0" style="border-collapse: collapse;">
							<tr bgcolor="#c0dcf2" height="50px">
								<td align="center"><input id="checkall" type="checkbox"> </td>
								<td align="center">序号</td>
								<td align="center">姓名</td>
								<td align="center">电话</td>
								<td align="center">所在组</td>
								<td align="center">操作</td>
							</tr>
							<s:iterator value="msgGroups" id="mg" status="st">
								<tr bgcolor="#e6f3fb" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'#e6f3fb')">
									<td  ><input type="checkbox" name="msgGroupSend" value="${mg.id}"> </td>
									<td>${st.index+1}</td>
									<td>${mg.username}</td>
									<td >${mg.phone}</td>
									<td>${mg.userGroup}</td>
									<td>修改删除</td>
								</tr>
							</s:iterator>
							<tr>
								<s:if test="errorMessage==null">
									<td colspan="6" align="right">
										第
										<font color="red"><s:property value="cpage" /> </font> /
										<s:property value="total" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />
									</td>
								</s:if>
								<s:else>
									<td colspan="5" align="center" style="color: red">
										${errorMessage}
									</td>
								</s:else>
							</tr>
							<tr>
								<td>消息正文</td>
								<td align="center" colspan="4"><textarea name="msg" rows="5" cols="15"></textarea> </td>
								<td><input type="submit" /></td>
							</tr>
						</table>
					
					</form>
				</div>
				
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			//全选
			$("#checkall").click( 
				function(){ 
					if(this.checked){ 
						$("input[name='msgGroupSend']").each(function(){this.checked=true;}); 
					}else{ 
						$("input[name='msgGroupSend']").each(function(){this.checked=false;}); 
					} 
				} 
			);
			//submit之前判断是否选择收信人
			function checkSubmit(){
				var k = false;
				$("input[name='msgGroupSend']").each(function(){
					if(this.checked==true){
						k = true;
						return ;
					}
				}); 
				if(!k){
					alert("请至少选中一个收信人!");
					return false;
				}
				
				if($("textarea[name='msg']").val().length > 70){
					alert("长度请控制在70个字以内");
					return false;
				}
				return k;
			}
		</script>
	</body>
</html>
