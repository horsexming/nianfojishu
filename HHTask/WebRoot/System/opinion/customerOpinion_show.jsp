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
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="customerOpinionAction_showList.action"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					客户投诉<br/>(Customer Opinion)
				</h3>
				<form action="customerOpinionAction_showList.action"
					method="post">
					<table class="table" align="center">
						<tr>
						 <th>对方单位（their Company）：
						 </th>
							<td align="center">
								<input type="hidden" name="tag" value="<s:property value="tag"/>">
								<input type="text" name="customerOpinion.otherCompany" value="<s:property value="customerOpinion.otherCompany"/>" />
							</td>
							<th>对方负责人（their Person in charge）：
						 </th>
							<td align="center">
								
								<input type="text" name="customerOpinion.otherPerson" value="<s:property value="customerOpinion.otherPerson"/>" />
							</td>
							<th>己方负责人（our Person in charge）：
						 </th>
							<td align="center">
								
								<input type="text" name="customerOpinion.ourPerson" value="<s:property value="customerOpinion.ourPerson"/>" />
							</td>
						</tr>
						<tr>
						<th>投诉单号（Number）：
						 </th>
						   <td align="center">
								
								<input type="text" name="customerOpinion.opinionNumber" value="<s:property value="customerOpinion.opinionNumber"/>" />
							</td>
							<th>投诉状态（Status）：
						 </th>
							<td align="center">
								
								<input type="text" name="customerOpinion.status" value="<s:property value="customerOpinion.status"/>" />
							</td>
							<th>投诉标题（Title）：
						 </th>
							<td align="center">
								
								<input type="text" name="customerOpinion.title" value="<s:property value="customerOpinion.title"/>" />
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
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号<br/>(NO)
						</td>
						<td align="center">
							投诉单号<br/>(Number)
						</td>
						<td align="center">
							投诉标题<br/>(title)
						</td>
						<td align="center">
							投诉内容<br/>（context）
						</td>
						<td align="center">
							对方单位<br/>（their Company）
						</td>						
						<td align="center">
							对方负责人<br/>（their Person in charge）
						</td>	
						<td align="center">
							对方联系电话<br/>（their Phone）
						</td>					
						<td align="center">
							己方负责人<br/>（our Person in charge）
						</td>						
						<td align="center">
							投诉状态<br/>（status）
						</td>						
						<td align="center">
							添加时间<br/>（add time）
						</td>						
						<td align="center" colspan="2">操作<br/>(Operation)</td>
					</tr>
					<s:iterator value="customerOpinionlist" id="pageOpinion" status="pageStatus">
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
							${pageOpinion.opinionNumber}
						</td>
						<td align="center">
							${pageOpinion.title}
						</td>
						<td align="center">
							${pageOpinion.context}
						</td>
						<td align="center">
							${pageOpinion.otherCompany}
						</td>						
						<td align="center">
							${pageOpinion.otherPerson}
						</td>	
						<td align="center">
							${pageOpinion.otherPhone}
						</td>					
						<td align="center">
							${pageOpinion.ourPerson}
						</td>						
						<td align="center">
							${pageOpinion.status}
						</td>		
						<td align="center">
							${pageOpinion.addTime}
						</td>		
						<td  colspan="2">
						   <s:if test="tag==null||tag=='all'">
							<input type="button" value="修改"
								style="width: 60px; height: 30px;"
								onclick="update(${pageOpinion.id},'${cpage}','${tag}')" />
							<input type="button" value="删除"
								style="width: 60px; height: 30px;"
								onclick="todelete(${pageOpinion.id},'${cpage}','${tag}')" />
						   </s:if>
						   <s:elseif test="tag=='analysis'">
						   <!-- 分析 -->
						   <input type="button" onclick="toanalysis(${pageOpinion.id},'${tag}');" value="分析" style="width: 60px; height: 30px;" />
						   </s:elseif>
						   <s:elseif test="tag=='improve'">
						   <!-- 改进 -->
						   <input type="button" onclick="toimprove(${pageOpinion.id},'${tag}');" value="改进" style="width: 60px; height: 30px;" />
						   </s:elseif>
						   <s:elseif test="tag=='approval1'||tag=='approval2'">
						   <!-- 分析审批 -->
						   <input type="button" onclick="toapproval(${pageOpinion.id},'${tag}');" value="审批" style="width: 60px; height: 30px;" />
						   </s:elseif>
						   <s:if test="#pageOpinion.analysisFile!=null">
										<a
<%--											href="<%=path%>/upload/file/analysis/<s:property value="#pageOpinion.analysisFile"/>">下载分析文件</a>--%>
											href="FileViewAction.action?FilePath=/upload/file/analysis/<s:property value="#pageOpinion.analysisFile"/>">查看分析文件</a>
									</s:if>
									<s:else>无文件</s:else>
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
	window.location.href = "customerOpinionAction_toUpdate.action?customerOpinion.id=" + id+"&cpage="+cpage+"&tag="+tag;
}
function todelete(id,cpage,tag) {
	window.location.href = "customerOpinionAction_delete.action?customerOpinion.id="+ id+"&cpage="+cpage+"&tag="+tag;
}
function toanalysis(id,tag){
	window.location.href = "customerOpinionAction_toAnalysis.action?customerOpinion.id=" + id+"&tag="+tag;
}
function toimprove(id,tag){
	window.location.href = "customerOpinionAction_toImprove.action?customerOpinion.id=" + id+"&tag="+tag;
}
function toapproval(id,tag){
	window.location.href = "customerOpinionAction_toApproval.action?customerOpinion.id=" + id+"&tag="+tag;
}

</script>
	</body>
</html>
