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

			<div align="center">
			<div>文件类型管理
			<!-- 列表展示 -->
						<table class="table">
							<tr bgcolor="#c0dcf2" height="30px"
								style="border-collapse: separate;">
								<th align="center" width="20%">
									序号
								</th>
								<th align="center">
									文件类型
								</th>
														
								<th align="center" width="20%">
									操作
								</th>
							</tr>

							<s:if test="{listType.size()>0}">
								<s:iterator value="listType" status="se11" id="type">
									<s:if test="#se1.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
									<td>
										<s:property value="#se11.index+1" />
									</td>
									<td>
										${type.fileType}
									</td>
									
									<td>
									<input type="button" id="delFile" onclick="deletype('${type.fileType}','${type.id}')" value="删除">
									</td>
									</tr>
								</s:iterator>
								
							</s:if>
							
						</table>
			</div>
			
			<div>文件存放位置
			<!-- 列表展示 -->
						<table class="table">
							<tr bgcolor="#c0dcf2" height="30px"
								style="border-collapse: separate;">
								<th align="center" width="20%">
									序号
								</th>
								<th align="center">
									文件存放位置
								</th>
																
								<th align="center" width="20%">
									操作
								</th>
							</tr>

							<s:if test="{list.size()>0}">
								<s:iterator value="list" status="se" id="bxd">
									<s:if test="#se.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
									<td>
										<s:property value="#se.index+1" />
									</td>
									<td>
										${bxd.fileLocation}
									</td>
									
									<td>
									<input type="button" id="delFile" onclick="delelocation('${bxd.fileLocation}','${bxd.id}')" value="删除">
									
									</td>
									</tr>
								</s:iterator>
								<tr>
									<td colspan="3" align="right">
										共
										<s:property value="total" />
										页 第
										<s:property value="cpage" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />

									</td>
								</tr>
							</s:if>
							<s:else>
								<tr>
									<td colspan="3" style="font-size: 15px; color: red;">
										对不起，没有查到相关的存放位置信息
									</td>
								</tr>
							</s:else>
						</table>
			</div>
			
						<br>
						</div>
						</center>
						<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
						<script type="text/javascript">
						//删除文件类型
						function deletype(name,id1){
							if(window.confirm('确认要删除'+name+'的财务文件类型?')){
						window.location.href = "FileManagerAction!deleteFileType.action?id="+id1;
					}
						}
						//删除存放位置
						function delelocation(name,id1){
							if(window.confirm('确认要删除'+name+'的财务文件存放位置?')){
						window.location.href = "FileManagerAction!deleteFileLocation.action?id="+id1;
					}
						}
						</script>
	</body>
</html>
