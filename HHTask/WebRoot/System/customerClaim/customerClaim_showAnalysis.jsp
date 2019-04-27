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
		<div id="gongneng">
			
			<div align="center">
				<h3>
					客户索赔<br/>(Customer Claim)
				</h3>
				<form action="customerClaimAction_showList.action"
					method="post">
					<table class="table" align="center">
						<tr>
						 <th>对方单位（their Company）：
						 </th>
							<td align="center">
								<input type="hidden" name="tag" value="<s:property value="tag"/>">
								<input type="text" name="customerClaim.otherCompany" value="<s:property value="customerClaim.otherCompany"/>" />
							</td>
							<th>对方负责人（their Person in charge）：
						 </th>
							<td align="center">
								
								<input type="text" name="customerClaim.otherPerson" value="<s:property value="customerClaim.otherPerson"/>" />
							</td>
							<th>己方负责人（our Person in charge）：
						 </th>
							<td align="center">
								
								<input type="text" name="customerClaim.ourPerson" value="<s:property value="customerClaim.ourPerson"/>" />
							</td>
						</tr>
						<tr>
						<th>投诉单号（Number）：
						 </th>
						   <td align="center">
								
								<input type="text" name="customerClaim.claimNumber" value="<s:property value="customerClaim.claimNumber"/>" />
							</td>
							<th>索赔状态（Status）：
						 </th>
							<td align="center">
								
								<input type="text" name="customerClaim.status" value="<s:property value="customerClaim.status"/>" />
							</td>
							<th>索赔标题（Title）：
						 </th>
							<td align="center">
								
								<input type="text" name="customerClaim.title" value="<s:property value="customerClaim.title"/>" />
							</td>
							
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
								<input type="reset" style="width: 100px; height: 40px;"
									value="重置"/>
							</td>
						</tr>
					</table>
				</form>
				<table class="table" align="center">
				<s:if test="analysisList!=null&&analysisList.size!=0">
					<tr style="color: #ffffff;height:50px;background-color: red;">
						<td align="center">
							序号
						</td>
						<td align="center">
							索赔单号
						</td>
						<td align="center">
							索赔标题
						</td>
						<td align="center">
							索赔内容
						</td>
						<td align="center">
							对方单位
						</td>						
						<td align="center">
							对方负责人
						</td>	
						<td align="center">
							对方联系电话
						</td>					
						<td align="center">
							己方负责人
						</td>						
						<td align="center">
							索赔状态
						</td>						
						<td align="center">
							添加时间
						</td>						
						<td align="center" colspan="2">操作</td>
					</tr>
					<s:iterator value="analysisList" id="analysis" status="pageStatus">
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
							${analysis.claimNumber}
						</td>
						<td align="center">
							${analysis.title}
						</td>
						<td align="center">
							${analysis.context}
						</td>
						<td align="center">
							${analysis.otherCompany}
						</td>						
						<td align="center">
							${analysis.otherPerson}
						</td>	
						<td align="center">
							${analysis.otherPhone}
						</td>					
						<td align="center">
							${analysis.ourPerson}
						</td>						
						<td align="center">
							${analysis.status}
						</td>		
						<td align="center">
							${analysis.addTime}
						</td>		
						<td  colspan="2">
						   <input type="button" onclick="toanalysis(${analysis.id},'analysis');" value="分析" style="width: 60px; height: 30px;" />
						</td>
					</s:iterator>
					</s:if>
					<tr style="color: #ffffff;height:50px;background-color: green;">
						<td align="center">
							序号
						</td>
						<td align="center">
							索赔单号
						</td>
						<td align="center">
							索赔标题
						</td>
						<td align="center">
							索赔内容
						</td>
						<td align="center">
							对方单位
						</td>						
						<td align="center">
							对方负责人
						</td>	
						<td align="center">
							对方联系电话
						</td>					
						<td align="center">
							己方负责人
						</td>						
						<td align="center">
							索赔状态
						</td>						
						<td align="center">
							添加时间
						</td>						
						<td align="center" colspan="2">操作</td>
					</tr>
					<s:iterator value="customerClaimlist" id="pageClaim" status="pageStatus">
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
							${pageClaim.claimNumber}
						</td>
						<td align="center">
							${pageClaim.title}
						</td>
						<td align="center">
							${pageClaim.context}
						</td>
						<td align="center">
							${pageClaim.otherCompany}
						</td>						
						<td align="center">
							${pageClaim.otherPerson}
						</td>	
						<td align="center">
							${pageClaim.otherPhone}
						</td>					
						<td align="center">
							${pageClaim.ourPerson}
						</td>						
						<td align="center">
							${pageClaim.status}
						</td>		
						<td align="center">
							${pageClaim.addTime}
						</td>		
						<td  colspan="2">
						<s:if test="#pageClaim.status=='打回'">
						<!-- <s:if test="tag==null||tag=='all'"> -->  
							<input type="button" value="修改分析"
								style="width: 60px; height: 30px;"
								onclick="updateAnalysis(${pageClaim.id},'${cpage}','analysis')" />
						<!-- </s:if> -->  
						 
						   </s:if>
						   <br/>
						   <s:if test="#pageClaim.analysisFile!=null">
										<a
<%--											href="<%=path%>/upload/file/analysis/<s:property value="#pageClaim.analysisFile"/>">下载分析文件</a>--%>
											href="FileViewAction.action?FilePath=/upload/file/analysis/<s:property value="#pageClaim.analysisFile"/>">下载分析文件</a>
									</s:if>
									<s:else>无文件</s:else>
									<s:if test="#pageClaim.status=='同意'||#pageClaim.status=='打回'">
									/<a href="CircuitRunAction_findAduitPage.action?id=${pageClaim.epId}">审批动态</a>
							</s:if>
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
function updateAnalysis(id,cpage,tag) {
	
	window.location.href = "customerClaimAction_toAnalysis.action?customerClaim.id=" + id+"&cpage="+cpage+"&tag="+tag;
}
function todelete(id,cpage,tag) {
	window.location.href = "customerClaimAction_delete.action?customerClaim.id="+ id+"&cpage="+cpage+"&tag="+tag;
}
function toanalysis(id,tag){
	window.location.href = "customerClaimAction_toAnalysis.action?customerClaim.id=" + id+"&tag="+tag;
}
function toimprove(id,tag){
	window.location.href = "customerClaimAction_toImprove.action?customerClaim.id=" + id+"&tag="+tag;
}
function toapproval(id,tag){
	window.location.href = "customerClaimAction_toApproval.action?customerClaim.id=" + id+"&tag="+tag;
}

</script>
	</body>
</html>
