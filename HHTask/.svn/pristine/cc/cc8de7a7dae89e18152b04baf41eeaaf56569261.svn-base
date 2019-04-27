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
	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			
			<div align="center">
			
				<table width="100%" border="0" style="border-collapse: collapse;" class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							类别
						</td>
						<td align="center">
							名称
						</td>
						<s:if test="tag=='category'">
							<td>
								类别代码
							</td>
						</s:if>
												
						<td align="center" colspan="2">操作<br/>(Operation)</td>
					</tr>
					<s:iterator value="fileTypeList" id="sFile" status="pageStatus">
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
						<td align="center">
							${sFile.typeforit}
						</td>
						<td align="left">
							${sFile.typeName}  
						</td>
						<s:if test="tag=='category'">
							<td>
								${sFile.code }
							</td>
						</s:if>
						<td  colspan="2">
							<input type="button" value="修改"
								style="width: 60px; height: 30px;"
								onclick="update(${sFile.id},'${cpage}','${tag}')" />
							<input type="button" value="删除"
								style="width: 60px; height: 30px;"
								onclick="todelete(${sFile.id},'${cpage}','${tag}')" />
						 
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
		<%@include file="/util/foot.jsp"%>
		
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function update(id,cpage,tag) {
	window.location.href = "systemFileAction_toUpdateFileType.action?id=" + id+"&cpage="+cpage+"&tag="+tag;
}
function todelete(id,cpage,tag) {
	window.location.href = "systemFileAction_deleteType.action?fileType.id="+ id+"&cpage="+cpage+"&tag="+tag;
}
<%--function toDetail(id, cpage) {--%>
<%--	window.location.href = "systemFileAction_toDetail.action?systemFile.id="--%>
<%--			+ id + "&cpage=" + cpage;--%>
<%--}--%>

</script>
	</body>
</html>
