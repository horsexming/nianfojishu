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
					物料催配明细查看
				</h3>
				<br>
				<div align="center">
				 总成件号:${pmHead.markId}&nbsp;&nbsp;&nbsp;批次:${pmHead.selfCard}&nbsp;&nbsp;&nbsp;数量:${pmHead.thisCount}
				</div>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号<br/>(num)
						</td>
						<td align="center">
							件号
						</td>
						<td align="center">
							牌号
						</td>
						<td align="center">
							规格
						</td>
						<td align="center">
							名称
						</td>
						<td align="center">
							数量
						</td>
						<td align="center">
							单位
						</td>
						<td align="center">
							类型
						</td>
						<td align="center">
							领料
						</td>
					</tr>
					<s:iterator value="list" id="pagepm" status="pageStatus">
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
							${pagepm.markId}
						</td>
						<td>
							${pagepm.trademark}
						</td>
						<td>
							${pagepm.specification}
						</td>
						<td>
							${pagepm.name}
						</td>
						<td>
							${pagepm.thecount}
						</td>
						<td>
							${pagepm.unit}
						</td>
						<td>
							${pagepm.type}
						</td>
						<td>
							${pagepm.lingliaoStatus}
						</td>
					</tr>
					</s:iterator>
					<tr>
							<td colspan="8" align="center" >
								<input type="button" value="确认备齐" onclick="sureMatrial(${pagepm.id})">
						</td>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="8" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="8" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
						
						<s:if test="successMessage!=null">
						<tr>
							<td colspan="8" align="center" style="color: red">
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
function sureMatrial(id) {
	if(window.confirm("您是否确定已备齐?")){
		$
			.ajax( {
				type : "POST",
				url : "ProcardAction!sureMatrial.action",
				data : {
					id :id
				},
				dataType : "json",
				success : function(data) {
					if(data=="true"){
						alert("已发送备货确认消息");
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
