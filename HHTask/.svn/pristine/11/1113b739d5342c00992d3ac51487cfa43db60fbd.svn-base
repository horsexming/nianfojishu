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
		<div id="gongneng" >
			
			<div align="center">
				<h3>
					物料催配查看
				</h3>
				<form action="ProcardAction!findpmList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<th align="center">
								件号：
							</th>
							<td align="center">
								<input type="text" name="pmHead.markId" value="<s:property value="pmHead.markId"/>" />
							</td>
							<th align="center">
								批次：
							</th>
							<td align="center">
								<input type="text" name="pmHead.selfCard" value="<s:property value="pmHead.selfCard"/>" />
							</td>
						</tr>
						
						<tr>
							<td align="center" colspan="4">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号<br/>(num)
						</td>
						<td align="center">
							件号
						</td>
						<td align="center">
							批次
						</td>
						<td align="center">
							数量
						</td>
						<td align="center">
							是否确认
						</td>
						<td align="center">
							领料
						</td>
						<td align="center" colspan="2">操作<br/>(Operation)</td>
					</tr>
					<s:iterator value="list" id="pagepmHead" status="pageStatus">
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
							${pagepmHead.markId}
						</td>
						<td>
							${pagepmHead.selfCard}
						</td>
						<td>
							${pagepmHead.thisCount}
						</td>
						<td>
						<label id="hastrue<s:property value="#pageStatus.index" />">${pagepmHead.hastrue}</label>
						</td>
						<td>
						${pagepmHead.lingliaoStatus}
						</td>
						
						<td  colspan="2">
							<input type="button" value="明细"
								style="width: 60px; height: 30px;"
								onclick="showdetail(${pagepmHead.id})" />
							<s:if test="#pagepmHead.hastrue=='否'.toString()">
							<input type="button" value="确认备齐"
								style="width: 60px; height: 30px;"
								onclick="sureMatrial(${pagepmHead.id},<s:property value="#pageStatus.index" />)" />
							</s:if>

						</td>
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
						</s:if>
						<s:else>
							<td colspan="6" align="center" style="color: red">
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
		<%@include file="/util/foot.jsp"%>
		
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function showdetail(id) {
	window.location.href = "ProcardAction!getProcardMatrialDetail.action?id=" + id;
}
function sureMatrial(id,index) {
	if(window.confirm("您是否确定已备齐?")){
		$
			.ajax( {
				type : "POST",
				url : "ProcardAction!sureMatrialajx.action",
				data : {
					id :id
				},
				dataType : "json",
				success : function(data) {
					if(data=="true"){
						alert("已发送备货确认消息");
						$("#hastrue"+index).html("是");
					}else{
						alert(data);
					}
				}
			})
	}
}
</script>
	</body>
</html>
