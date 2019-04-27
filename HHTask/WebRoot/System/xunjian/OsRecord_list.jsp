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
td:hover .qs_ul {
	display: block;
}

.qs_ul {
	display: none;
	border: 1px solid #999;
	list-style: none;
	margin: 0;
	padding: 0;
	position: absolute;
	width: auto;
	background: #CCC;
	color: green;
}
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<s:if test="tag!='pingmu'">
					<form method="post" action="OsRecord_list.action" id="formElement">
						<input type="hidden" value="${pageStatus}" name="pageStatus">
						<table class="table" style="width: 50%">
							<tr>
								<th colspan="7">
									条件查询
								</th>
							</tr>
							<tr>
								<td>
									型别
								</td>
								<td>
									<input type="text" name="record.template.cmodel"
										title="只需输出一个字就可以" />
								</td>
								<td>
									件号
								</td>
								<td>
									<input type="text" name="record.markId"
										value="${record.markId}" />
								</td>
								<td>
									名称
								</td>
								<td>
									<input type="text" name="record.proName"
										value="${record.proName}" />
								</td>
							</tr>
							<tr>
								<td>
									批次
								</td>
								<td>
									<input type="text" name="record.jcpc" value="${record.jcpc}" />
								</td>
								<td>
									时间从
								</td>
								<td>
									<input type="text" name="firsttime" class="Wdate"
										value="${firsttime}"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</td>
								<td>
									止
								</td>
								<td>
									<input type="text" name="endtime"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
										class="Wdate" value="${endtime}" />
								</td>
							</tr>
						</table>
						<input type="submit" value="查询" class="input" />
						<input type="button" value="导出" class="input" onclick="exportOsRecord()"/>
					</form>
				</s:if>
				<s:if test='pageStatus == "wg"'>
						<font style="font-size: 22px;font-weight: bolder;">外购检验记录</font>
					</s:if>
					<s:elseif test='pageStatus == "ww"'>
						<font style="font-size: 22px;font-weight: bolder;">外委检验记录</font>
					</s:elseif>
				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
												<th>
													类型
												</th>
<%--						<th>--%>
<%--							车型--%>
<%--						</th>--%>
						<th>
							名称
						</th>
						<th>
							件号
						</th>
						<th>
							时间
						</th>
						<th>
							检查批次
						</th>
						<th>
							本批数量
						</th>
						<th>
							是否合格
						</th>
						<th>
							缺陷描述
						</th>
						<th>
							缺陷代码
						</th>
						<th>
							缺陷分类
						</th>
						<th>
							检验人
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="list" status="st" id="tt">
						<s:if test="#st.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<th>
							${st.index +1}
						</th>
						<%--						<td>--%>
						<%--							<s:if test="#tt.template.ctype1==null">--%>
						<%--								${tt.wgType}--%>
						<%--							</s:if>--%>
						<%--							<s:else>--%>
						<%--								${tt.template.ctype1}--%>
						<%--							</s:else>--%>
						<%--						</td>--%>
						<td>
							${tt.template.cmodel}
						</td>
						<td align="left">
							<s:if test="#tt.proName==null">
								${tt.template.ctype}
							</s:if>
							<s:else>
								${tt.proName}
							</s:else>
						</td>
						<td align="left">
							<s:if test="#tt.markId==null">
								${tt.template.partNumber}
							</s:if>
							<s:else>
								${tt.markId}
							</s:else>
						</td>
						<td>
							${tt.nowDate}
						</td>
						<td>
							${tt.jcpc}
						</td>
						<td>
							${tt.quantity}
						</td>
						<td>
							${tt.verification}
						</td>
						<td
							style="max-width: 50px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							<font size="1">${tt.type}</font>
							<ul class="qs_ul">
								<li>
									${tt.type}
								</li>
							</ul>
						</td>
						<td>
							${tt.code}
						</td>
						<td>
							${tt.bhgclass}
						</td>
						<td>
							${tt.username}
						</td>
						<td>
							<a href="OsRecord_showScope.action?record.id=${tt.id}">抽检数据明细</a>
							<s:if test="pageStatus=='del'">
								<a onclick="return window.confirm('确定删除?')"
									href="OsRecord_deleteOsRecord.action?record.id=${tt.id}">删除</a>
							</s:if>
						</td>

						</tr>

					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="20" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</s:if>
						<s:else>
							<td colspan="20" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			function exportOsRecord(){
				var oldAction =$("#formElement").attr("action"); 
				$("#formElement").attr("action","OsRecord_exportOsRecord.action");
				$("#formElement").submit();
				$("#formElement").attr("action",oldAction);
// 				var form = new FormData(document.getElementById("formElement"));
// 		    	$.ajax({
// 		              url:"${pageContext.request.contextPath}/OsRecord_exportOsRecord.action",
// 		              type:"post",
// 		              data:form,
// 		              processData:false,
// 		              contentType:false,
// 		              async : false,
// 		              dataType:"json",
// 		              cache:false,
// 		              success:function(data){
		            	  
// 		              },
// 		              error:function(e){
// 		                  alert("出错了，重试一下吧");
// 		              }
// 		          });
			}
		
		</script>
	</body>
</html>
