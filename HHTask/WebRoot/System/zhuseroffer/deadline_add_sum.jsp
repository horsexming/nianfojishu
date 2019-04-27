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
		<style type="text/css">
		</style>
	</head>
  
  <body>
  <form  method="post">
  <table  style="width: 100%"  >
  <tr>
  <td>
  	<span>当前日期：  </span>
  	<input style="border:none" id="nowDate" readonly="readonly"/>
  </td>
  <td>
 	<span>请输入报价期限：  </span> 
 	<input id="deadline" type="text" name="deadline" onkeyup="jisuanshijian()" onblur="jisuanshijian()"/><span>(天)</span>
  </td>
  <td>
	<span>截止日期：  </span>
  	<input style="border:none" id="endDate" readonly="readonly" placeholder="请填写期限..."/>
  </td>
  </tr>
  	</table>
  	<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						
						<td align="center">
						<input type=""  name="offerIds" value="${offerIds}" id="offerIds"/>
							件号：
						</td>
						<td align="center">
							名称：
						</td>
						<td align="center">
							规格：
						</td>
						<td align="center">
							物料类别：
						</td>
						<td align="center">
							版本号：
						</td>
					</tr>
					
					<s:iterator value="YuanclAndWaigjList" id="list1"
						status="pageStatus">
						<tr>
							<td align="center">
								${list1.markId }
							</td>
							<td align="center">
								${list1.name }
							</td>
							<td align="center">
								${list1.specification }
							</td>
							<td align="left" >
								${list1.wgType }
							</td>
							<td align="left" >
								${list1.banbenhao }
							</td>
						</tr>
					</s:iterator>
					
				</table>
   	<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
						<input type="checkbox" onclick="chageAllCheck(this)"/>
						</td>
						<td align="center">
							序号
						</td>
						<td align="center">
							公司编号
						</td>
						<td align="center">
							公司名称
						</td>
						<td align="center">
							联系人
						</td>
						<td align="center">
							联系方式
						</td>
					</tr>
					<s:iterator value="zhUserList" id="list"
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
							<input name="zhUserId" type="checkbox" value="${list.id}" onclick="chageNum(this)"/>
						</td>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${list.usercode}
						</td>
						<td align="center">
							${list.cmp}
						</td>
						<td align="center">
							${list.cperson}
						</td>
						<td align="left" >
							${list.ctel}
						</td>
					</s:iterator>
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
							<td colspan="12" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
   
   </table>
   <div align="center" style="margin:10px">
   	<input type="button" value="提交" style="height: 30px;width:70px" onclick="submit1()"/>
   </div>
   </form>
   <script type="text/javascript">
   	var myDate = new Date(); 
   	document.getElementById("nowDate").value = myDate.toLocaleDateString();
   function jisuanshijian(){
	   var deadline = document.getElementById("deadline").value;
		var szReg=/^\d+(\.\d+)?$/;
		if(deadline!=""){
			if(!szReg.test(deadline)){
				$("#deadline").val("");
				$("#deadline").focus();
				alert("请输入正确格式");
			}
			}
		$.ajax( {
		type : "POST",
		url : "yuanclAndWaigjAction_dayafter.action",
		data : {
			deadline:deadline
			},
		dataType : "json",
		success : function(data) {
			document.getElementById("endDate").value = data;
			}
			});
		}
   function submit1(){
	   var deadline = document.getElementById("deadline").value;
	   var offerIds = document.getElementById("offerIds").value;
	   obj = document.getElementsByName("zhUserId");
    	zhUserId = [];
   		 for(k in obj){
        if(obj[k].checked)
            zhUserId.push(obj[k].value);
    	}
	   $.ajax( {
		type : "POST",
		url : "yuanclAndWaigjAction_piliangbaojia.action",
		data : {
			deadline:deadline,
			offerIds:offerIds,
			zhUserId:zhUserId
			},
		traditional: true,
		dataType : "json",
		success : function(data) {
				if(data==true){
					parent.window.location.reload();
					alert("成功");
				}else{
					alert(data);
				}
					
			}
			});
	   
   }
   </script>
  </body>
  
</html>

