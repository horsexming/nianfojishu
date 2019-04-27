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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong" >
			</div>
			
			<div align="center">
				<h3>
					保密项目及本件操作日志
				</h3>
				<form action="ProjectManage_baomiLogShow.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								操作类型:
								<SELECT name="baomiOperateLog.operateType">
								<s:if test="baomiOperateLog.operateType!=null">
								<option><s:property value="baomiOperateLog.operateType"/></option>
								</s:if>
								<option></option>
								<option>增加</option>
								<option>修改</option>
								<option>查看</option>
								<option>删除</option>
								</SELECT>
							</td>
							<td align="center">
								操作对象:
								<SELECT name="baomiOperateLog.operateObject">
								<s:if test="baomiOperateLog.operateObject!=null">
								<option><s:property value="baomiOperateLog.operateObject"/></option>
								</s:if>
								<option></option>
								<option>项目</option>
								<option>BOM</option>
								<option>文件</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<td align="center">姓名:<input name="baomiOperateLog.operateUsername" value="<s:property value="baomiOperateLog.operateUsername"/>"></td>
							<td align="center">工号:<input name="baomiOperateLog.operateCode" value="<s:property value="baomiOperateLog.operateCode"/>"></td>
						</tr>
						
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							操作类型
						</td>
						<td align="center">
							操作对象
						</td>
						<td align="center">
							操作人
						</td>
						<td align="center">
							操作部门
						</td>
						<td align="center">
							操作时间
						</td>						
						<td align="center">
							操作内容
						</td>						
					</tr>
					<s:iterator value="bmoLogList" id="bmlg" status="pageStatus">
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
						<td align="left">
							${bmlg.operateType}
						</td>
						<td align="left">
							${bmlg.operateObject}
						</td>
						<td align="left">
							${bmlg.operateUsername}
						</td>
						<td align="left">
							${bmlg.operateDept}
						</td>
						<td align="left">
							${bmlg.operateTime}
						</td>
						<td align="left">
							${bmlg.operateRemark}
						</td>
						
					</tr>
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
</script>
	</body>
</html>
