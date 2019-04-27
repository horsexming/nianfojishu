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
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="gongxuAction!updateGongwei.action" method="post">
				<input type="hidden" name="gongwei.id" value="${gongwei.id }" />
				<input type="hidden" name="cpage" value="${cpage }" />
					<table width="95%" class="table">
						<tr><td>班组：<input type="text"  name="gongwei.banzu" value="${gongwei.banzu }"/>
						</td>
						<td>设备编号：<input type="text" name="gongwei.shebeiCode" size="7" value="${gongwei.shebeiCode }"/></td>
						<td>设备名称：<input type="text" name="gongwei.shebeiName" size="7" value="${gongwei.shebeiName }"/></td>
						<td>设备型号：<input type="text" name="gongwei.shebeiXinghao" size="7" value="${gongwei.shebeiXinghao }"/></td>
						</tr>
						
						<tr>	
						<td>可操作工序内容：<input type="text" name="gongwei.caozuoContent" size="7" value="${gongwei.caozuoContent }" /></td>
						<td>可操作规格：<input type="text" name="gongwei.caozuoFormat" size="7" value="${gongwei.caozuoFormat }" /></td>					
						<td>操作技能指数：<input type="text"  name="gongwei.caozJineng" value="${gongwei.caozJineng }"/>
											
						</td>
						<td>可替换人数：<input type="text" name="gongwei.caoztihuanrenshu" size="7" value="${gongwei.caoztihuanrenshu }" /></td>
								
						
						</tr>
						<tr>
						<td>工位号：<input type="text" name="gongwei.gongweihao" size="7" value="${gongwei.gongweihao }" /></td>
												
						<td>最低操作人数：<input type="text" name="gongwei.caozMinimumMan" size="7" value="${gongwei.caozMinimumMan }" /></td>
						<td>备注1：<input type="text" name="gongwei.more1" size="7" value="${gongwei.more1 }" /></td>
						<td>备注2：<input type="text" name="gongwei.more2" size="7" value="${gongwei.more2 }" /></td>
						</tr>
						<tr>
						<td>B*工装调试技能指数</td>
						<td>工装技能指数：<input type="text"  name="gongwei.gongzhuangJineng" value="${gongwei.gongzhuangJineng }" />
						
						
						</td>
						<td>可替换人数：<input type="text" name="gongwei.gongzhuangtihuanrenshu" size="7" value="${gongwei.gongzhuangtihuanrenshu }" /></td>
						<td>负荷指数：<input type="text"  name="gongwei.gongzhuangFuhe" value="${gongwei.gongzhuangFuhe }" />
											
						</td>
						</tr>
						<tr>
						<td colspan="6" align="center">
						<input type="submit" value="修改" />
						<input type="reset" value="放弃">
						</td></tr>
					</table>
				</form>
				
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
