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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>设变生产外购数据</h3>
			</div>
			<div>
			<form action="procardTemplateGyAction_procardSbWgshowList.action"
					method="post">
					<table class="table" align="center">
						<tr>
							<th>设变单号:</th>
							<td align="center">
								<input type="text" name="procardsbwg.sbNumber" value="<s:property value="procardsbwg.sbNumber"/>" />
							</td>
							<th>件号:</th>
							<td align="center">
								<input type="text" name="procardsbwg.markId" value="<s:property value="procardsbwg.markId"/>" />
							</td>
							
						</tr>
						<tr>
						<th>版本号:</th>
							<td align="center">
								<input type="text" name="procardsbwg.banbenNumber" value="<s:property value="procardsbwg.banbenNumber"/>" />
							</td>
							<th>名称:</th>
							<td align="center">
								<input type="text" name="procardsbwg.proName" value="<s:property value="procardsbwg.proName"/>" />
							</td>
							
						</tr>
						<tr>
						<th>供料属性:</th>
							<td align="center">
								<select name="procardsbwg.kgliao" style="width: 172px;">
									<s:if test="procardsbwg.kgliao!=null">
										<s:if test="procardsbwg.kgliao=='TK'">
											<option value="TK">
											自购(TK)
											</option>
										</s:if>
										<s:if test="procardsbwg.kgliao=='TK AVL'">
											<option value="TK">
											指定供应商(TK AVL)
											</option>
										</s:if>
										<s:if test="procardsbwg.kgliao=='CS'">
											<option value="TK">
											客供(CS)
											</option>
										</s:if>
										<s:if test="procardsbwg.kgliao=='TK Price'">
											<option value="TK">
											完全指定(TK Price)
											</option>
										</s:if>
									</s:if>
									<option></option>
									<option value="TK">
										自购(TK)
									</option>
									<option value="TK AVL">
										指定供应商(TK AVL)
									</option>
									<option value="CS">
										客供(CS)
									</option>
									<option value="TK Price">
										完全指定(TK Price)
									</option>
								</select>
							</td>
						<th>状态:</th>
							<td align="center">
								<SELECT name="procardsbwg.status" style="width: 172px;" >
									<s:if test="procardsbwg.status!=null">
									<option><s:property value="procardsbwg.status"/></option>
									</s:if>
									<s:else><option></option> </s:else>
									<option>待处理</option>
									<option>完成</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th>业务件号:</th>
							<td align="center">
								<input type="text" name="procardsbwg.ywMarkId" value="<s:property value="procardsbwg.ywMarkId"/>" />
							</td>
							<th></th>
							<td align="center">
							</td>
							
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr>
						<th>序号</th>
						<th>设变单号</th>
						<th>件号</th>
						<th>业务件号</th>
						<th>名称</th>
						<th>版本号</th>
						<th>供料属性</th>
						<th>数量</th>
						<th>处理数量</th>
						<th>处理方案</th>
						<th>处理人</th>
						<th>添加时间</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<s:iterator value="procardsbwgList" id="pageSbWg" status="pageStatus">
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
							<a href="procardTemplateGyAction_showSbApplyJd2.action?bbAply.id=${pageSbWg.ptbbApplyId}">${pageSbWg.sbNumber}</a>
						</td>
						<td>
							${pageSbWg.markId}
						</td>
						<td>
							${pageSbWg.ywMarkId}
						</td>
						
						<td>
							${pageSbWg.proName}
						</td>
						<td>
							${pageSbWg.banbenNumber}
						</td>
						<td>
							${pageSbWg.kgliao}
						</td>
						<td align="right">
							${pageSbWg.addCount}
						</td>
						<td align="right">
							${pageSbWg.clCount}
						</td>
						<td>
							${pageSbWg.cltype}
						</td>
						<td>
							${pageSbWg.clUser}(${pageSbWg.clUserCode})
						</td>
						<td>
							${pageSbWg.addTime}
						</td>
						<td>
							${pageSbWg.status}
						</td>
						<td>
						<s:if test="#pageSbWg.status!='关闭'">
						<s:if test="#pageSbWg.addCount>0"><input type="button" onclick="addcg(${pageSbWg.id})" value="增加采购"/>
						</s:if>
						<s:else><input type="button" onclick="deletecg(${pageSbWg.id})" value="减少采购"/>
						</s:else>
						</s:if>
						<input type="button" onclick="closesbcg(${pageSbWg.id})" value="关闭"/>
						</td>
					</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="15" align="right">
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
							<td colspan="15" align="center" style="color: red">
								${successMessage}
								
						</td>
					</tr>
                          </s:if>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function addcg(id){
	window.location.href="procardTemplateGyAction_toaddsbcg.action?id="+id+"&cpage=${cpage}";
}
function closesbcg(id){
	window.location.href="procardTemplateGyAction_closesbcg.action?id="+id+"&cpage=${cpage}";
}
function deletecg(id){
	window.location.href="procardTemplateGyAction_todeletesbcg.action?id="+id+"&cpage=${cpage}";
}
</SCRIPT>
	</body>
</html>
