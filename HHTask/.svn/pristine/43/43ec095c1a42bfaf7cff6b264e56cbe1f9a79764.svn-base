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
		<script type="text/javascript">
</script>
	</head>
	<body bgcolor="#ffffff" ><%-- onload="createDept('dept')"--%>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
					<h3>
						个人信息维护 
					</h3>
					<form action="UsersAction!updateUsersW.action" method="post" id="form"  enctype="multipart/form-data" >
<%--				onsubmit="return chackForm()"	--%>
						<table width="100%" border="0" class="table" bgcolor="#F0F0F0">
							<s:if test="errorMessage!=null">
								<tr>
									<th colspan="6" align="center">
										<font color="red" size="10px">${errorMessage}</font>
									</th>
								</tr>
							</s:if>
							<tr>
								<th colspan="6" align="left">
									<br />
									个人资料
									<input type="hidden" name="user.id" value="${user.id}"/>
									<br />
									<br />
								</th>
							</tr>
							<tr>
								<th align="right">
									姓名:
								</th>
								<td>
									${user.name}
								</td>
								<th align="right">
									性别:
								</th>
								<td>
									${user.sex}
								</td>
								<th rowspan="5" align="center">
									照片
								</th>
								<td rowspan="5">
									<s:if test='user.sex =="男"'>
										<img alt="${user.name}"
											src="${pageContext.request.contextPath}/upload/user/${user.password.picture}" width="120px;"
											style="border: solid 1px #000000; height: 130px;" onerror="this.src='images/man.jpg'" />
<%--											--%>
									</s:if>
									<s:else>
										<img alt="${user.name}"
											src="${pageContext.request.contextPath}/upload/user/${user.password.picture}" width="120px;"
											style="border: solid 1px #000000; height: 130px;" onerror="this.src='images/woman.jpg'" />
									</s:else><br/>
									<input type="file" name="picture" />
								</td>
							</tr>
							<tr>
								<th align="right">
									民族:
								</th>
								<td>${user.nation}</td>
								<th align="right">
									籍贯:
								</th>
								<td>${user.birthplace}</td>
							</tr>
							<tr>
								<th align="right">
									户籍:
								</th>
								<td>
									${user.residence}
								</td>
								<th align="right">
									出生年月:
								</th>
								<td>
									${user.bothday}
								</td>
							</tr>
							<tr>
								<th align="right">
									学历:
								</th>
								<td>
									${user.education}
								</td>
								<th align="right">
									身份证号:
								</th>
								<td>
									${user.uid}
								</td>
							</tr>
							<tr>
								<th align="right">
									员工性质:
								</th>
								<td>
									${user.password.staffNature}
								</td>
								<th align="right">
									户籍性质:
								</th>
								<td>
									${user.password.censusNature}
								</td>
							</tr>
							<tr>
								<th colspan="6" align="left">
									<br />
									工作岗位信息
									<br />
									<br />
								</th>
							</tr>
							<tr>
								<th align="right">
									卡号:
								</th>
								<td>
									${user.cardId}
								</td>
								<th align="right">
									工号:
								</th>
								<td>
									${user.code}
								</td>
								<th align="right">
									部门:
								</th>
								<td>
									${user.dept}(${user.password.deptNumber})
								</td>
							</tr>
							<tr>
								<th align="right">
									职务:
								</th>
								<td>
									${user.duty}
								</td>
								<th align="right">
									职级:
								</th>
								<td>
									${user.post}
								</td>

								<th align="right">
									入职时间:
								</th>
								<td>
									${user.joined}
								</td>
							</tr>
							<tr>
								<th align="right">
									状态:
								</th>
								<td>
									${user.onWork}
								</td>
								<th align="right">
									试用期:
								</th>
								<td>
									${user.tryDays}
								</td>
							</tr>
							<tr>
								<th align="right">
									班次:
								</th>
								<td>
									${user.banci_name}
								</td>
								<th align="right">
									电话:
								</th>
								<td>
									${user.password.telephone}
								</td>
								<th align="right">
									是否属于内部人员:
									<br />
								</th>
								<td>
									${user.internal}
								</td>
							</tr>
							<tr>
								<th align="right">
									备注:
								</th>
								<td colspan="1">
									${user.more}
								</td>
								<th align="right">
									现住址:
								</th>
								<td colspan="3">
									${user.password.presentAddress}
								</td>
							</tr>
							<tr>
								<th colspan="6" align="left">
									<br />
									联系方式<span style="margin-left:150px;color:red" id="hint"></span>
									<br />
									<br />
								</th>
							</tr>
							<tr>
								<th align="right">
									邮箱:
								</th>
								<td>
									<input id="mailBox" name="user.password.mailBox"
										class="horizontalLine" onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')" 
										value="${user.password.mailBox}">
									授权码或密码：<input type="password" onblur="checkEmailInfo()" id="password" onkeydown="checkUserPassword(event,this)"
										name="user.password.AuthorizationCode"/>
								</td>
								<th align="right">
									手机号码:
								</th>
								<td>
									<input id="phoneNumber"
										onkeyup="this.value=this.value.replace(/\D/g,'')"
										name="user.password.phoneNumber" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.password.phoneNumber}">
									<font color="red"> *</font>
								</td>
								<th align="right">
									微信号码:
								</th>
								<td>
									<input id="wxNumber"
<%--										onkeyup="this.value=this.value.replace(/\D/g,'')"--%>
										name="user.password.wxNumber" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.password.wxNumber}">
									<font color="red"> *</font>
								</td>
							</tr>
							<tr>
								<th align="right">
									日期类别:${user.password.rili}
								</th>
								<td colspan="1">
								<s:if test='user.password.rili=="阴历"'>
									<input type="radio" name="user.password.rili" value="阳历">
									阳历
									<input type="radio" name="user.password.rili" value="阴历"
										checked="checked">
									阴历
								</s:if>
								<s:else>
									<input type="radio" name="user.password.rili" value="阳历"
										checked="checked">
									阳历
									<input type="radio" name="user.password.rili" value="阴历">
									阴历
								</s:else>
								<font color="red"> *</font>
							</td>
								<th align="right">
									实际生日日期:
								</th>
								<td colspan="1">
									<input id="shijiBirthDay" name="user.password.shijiBirthDay"
										class="horizontalLine"
										value="${user.password.shijiBirthDay}" type="text"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
										>
									<font color="red"> *</font>
								</td>
								<th align="right">
									QQ号码:
								</th>
								<td>
									<input id="qqNumber"
										name="user.password.qqNumber" class="horizontalLine"
										onfocus="chageClass(this,'')"
										onblur="chageClass(this,'horizontalLine')"
										value="${user.password.qqNumber}">
									<font color="red"> *</font>
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<input type="button" value="修改" onclick="updateBtn()"
										style="width: 80px; height: 50px;" />
									<input type="reset" value="重置"
										style="width: 80px; height: 50px;" />
									<br />
									<br />
								</td>
							</tr>
							<tr>
								<td colspan="6">
									备注：<br/>
									1.一些邮箱默认没有开启IMAP协议需要到邮箱的（设置→POP3/SMTP/IMAP→勾选imap协议） 根据邮箱提示进行下一步操作
									<br/>
									2. 绑定某些邮箱，如（163、qq）等邮箱需要使用授权码，设置协议之后会有提示绑定授权码的操作，具体查看邮箱帮助
									<br/>
									3. 如果邮箱服务器需要使用授权码，在本页面必须使用授权码登陆。
									<br/>
									4. 某些邮箱自动获取的为最近30天的邮件，可到邮箱界面设置（POP3/SMTP/IMAP）下面设置接收邮件的天数
									<br/>
									5. 如果提示：用户名或密码错误、未知错误，请确邮箱和密码是否正确，如果正确请到邮箱主页检查并设置imap协议，并绑定授权码。
									<br/>
									6. 常用邮箱帮助<a href="http://service.mail.qq.com/cgi-bin/help?subtype=1&id=28&no=166"  target="_blank">QQ邮箱设置帮助</a>
									 <a href="http://help.163.com/10/0312/13/61J0LI3200752CLQ.html"  target="_blank">163邮箱设置帮助</a>
									 <a href="http://help.163.com/10/0312/13/61J0LI3200752CLQ.html"  target="_blank">126邮箱设置帮助</a>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		<%@include file="/util/foot.jsp"%>
	<script type="text/javascript">
	
		function checkUserPassword(event,obj){
			 var ev= window.event||e;
			//13是键盘上面固定的回车键
		    if (ev.keyCode == 13) {
		    	if(null!=obj.value && ""!=obj.value){
					//你要执行的方法
				  	checkEmailInfo();
		    	}
		    	
		  	}
		}
		function checkEmailInfo(){
			var mailname = $("#mailBox").val();
			var password = $("#password").val();
			if(password==''){
				$("input[type=submit]").attr("disabled",false);
				return ;
			}
			if(mailname!='' && password!=''){
				var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
				$("#hint").text("邮箱功能正在校验中，请稍后...");
				$("input[type=submit]").attr("disabled",true);
       			if(reg.test(mailname)){
					$.ajax({
						type:"POST",
							url:"JavaMailAction!checkEmailInfo.action",
							data:{
								mailname:mailname,
								password:password
							},
							//async:false, 
							dataType:"json",
							success:function(data){
								if(data!=null){
									$("#hint").text(data);
									if(data=="邮箱验证成功！"){
										$("input[type=submit]").attr("disabled",false);
									}
								}else{
									$("#hint").text("");
									$("input[type=submit]").attr("disabled",false);
								}
							}
					});
				}else{
					$("#hint").text("邮箱格式不合法");
				}
			}
			
		}
		
		function updateBtn(){
			$("#form").submit();
		}
	</script>
	</body>
</html>
