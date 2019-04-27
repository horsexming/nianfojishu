<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				style="width: 100%; font-weight: bold; height: 50px; background: url('<%=basePath%>images/title.jpg') no-repeat;"
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

				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							客户名称
						</th>
						<th align="center">
							产品名称
						</th>
						<th align="center">
							添加时间
						</th>
						<th align="center">
							检测模板编号
						</th>
						<th align="center">
							审批状态
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="list" id="qualityto" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout=outBgcolor(this,'');>
						</s:else>
						<td>
							<s:if test="#pageStatus.index%2==1">
							</s:if>
							<s:else>
								<font color="#c0dcf2"></font>
							</s:else>
							<s:property value="#pageStatus.index+1" />

						</td>
						<td>
							${qualityto.kehu}
						</td>
						<td>
							${qualityto.productname}
						</td>
						<td>
							${qualityto.addtime}
						</td>
						<td>
							${qualityto.jcbh}
						</td>
						<td>
							${qualityto.statu}
						</td>


						<td>
							<s:if test='%{"已添样本".equals(#qualityto.statu)}'>
								<a
									href="QualityccAction!findQualityta.action?qualityto.Id=${qualityto.id}">完成</a>
							</s:if>
							<s:else>
								<a
									href="QualityccAction!lookQualityta.action?qualityto.Id=${qualityto.id}">查看</a>
							</s:else>
							<s:if test='%{"已审批".equals(#qualityto.statu)}'></s:if>
							<s:else>
								<a href="#"	
									onclick="if(window.confirm('确实要删除该记录吗?')){window.location.href=
									'QualityccAction!deleteQualityto.action?qualityto.Id=${qualityto.id}&cpage=${cpage}'}">删除 </a>
							</s:else>

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
