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

				</div>
			</div>
			
			<div align="center">
				<form
					action="gongyiGuichengAction!findAllGongyiGuicheng.action?role=all"
					method="post" style="">
					<br>
					<table border="0" width="100%" class="table">
						<tr>
							<td align="right">
								件号:
							</td>
							<td>
								<input type="text" name="gongyiGuicheng.jianNumb"
									value="${gongyiGuicheng.jianNumb}" />
							</td>
							<td align="right">
								名称:
							</td>
							<td>
								<input id="jianName" type="text" name="gongyiGuicheng.jianName"
									value="${gongyiGuicheng.jianName}" />
							</td>
							
						</tr>
						<tr>
						<td align="right">
								状态:
							</td>
							<td>
								<select id="status" name="gongyiGuicheng.status">
									<option value="">
										请选择
									</option>
									<option value="待编制">
										待编制
									</option>
									<option value="打回">
										打回
									</option>
									<option value="已编制">
										已编制
									</option>
									<option value="已校对">
										已校对
									</option>
									<option value="已审核">
										已审核
									</option>
									<option value="已批准">
										已批准
									</option>
								</select>
							</td>
						<td align="right">
								生产类型:
							</td>
							<td>
								<select id="procardStyle" name="gongyiGuicheng.procardStyle">
								<s:if test="gongyiGuicheng.procardStyle!=null&&gongyiGuicheng.procardStyle!=''">
								  <option value='<s:property value="gongyiGuicheng.procardStyle"/>' selected="selected">
										<s:property value="gongyiGuicheng.procardStyle"/>
									</option>
								</s:if>
									<option value="">
										请选择
									</option>
									<option>
										总成
									</option>
									<option>
										组合
									</option>
									<option>
										自制
									</option>
									<option>
										外购
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input type="submit" value="查询"
									style="width: 100px; height: 50px;" />

								<input type="reset" value="重置"
									style="width: 100px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table" style="text-align: center;">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							工艺规程编号
						</td>
						<td align="center">
							件号
						</td>
						<td align="center">
							件名
						</td>
						<td align="center">
							卡片类型
						</td>
						<td align="center">
							编制
						</td>
						<td align="center">
							较对
						</td>
						<td align="center">
							审核
						</td>
						<td align="center">
							批准
						</td>
						<td align="center">
							发出日
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator id="g" value="gongyiGuichengListForAll" status="st">

						<s:if test="#st.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this);"
								onmouseout="outBgcolor(this,'');">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this);"
								onmouseout="outBgcolor(this,'');">
						</s:else>

						<td>
							${st.index + 1}
						</td>
						<td>
							${numb}
						</td>
						<td>
							${jianNumb}
						</td>
						<td>
							${jianName}
						</td>
						<td>
							${procardStyle}
						</td>
						<td>
							${bianzhiName}
						</td>
						<td>
							${jiaoduiName}
						</td>
						<td>
							${shenheName}
						</td>
						<td>
							${pizhunName}
						</td>
						<td>
							<s:date name="fachuDate" format="yyyy-MM-dd" />
						</td>
						<td>
							${status}
						</td>
						<td>
							<a target="_self" onclick="return window.confirm('确认要删除该条记录?')"
								href="gongyiGuichengAction!deleteGongyiGuicheng.action?gongyiGuicheng.id=${g.id}&role=all">删除</a>
							<a target="_detail"
								href="gongyiGuichengAction!getGongyiGuichengDetail.action?gongyiGuicheng.id=${g.id}">查看详细</a>
							<a target="_self"
								href="gongyiGuichengAction!getGongyiGuichengListForHistoryByParentId.action?gongyiGuicheng.id=${g.id}">查看历史版本</a>
						    <s:if test="#g.procardStyle=='总成'">
						    <a target="_blank"
								href="gongyiGuichengAction!getBomReviewById.action?gongyiGuicheng.id=${g.id}">BOM预览</a>
							<a target="_blank"
								href="gongyiGuichengAction!checkBuildBom.action?gongyiGuicheng.id=${g.id}">组装BOM</a>
								</s:if>
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
							</td>
						</s:if>
						<s:else>
							<td colspan="12" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
			<br />
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<script type="text/javascript">
$(function() {
	var status = '${gongyiGuicheng.status}';
	$("#status").find("option[value='" + status + "']").attr("selected", true);
});
</script>
