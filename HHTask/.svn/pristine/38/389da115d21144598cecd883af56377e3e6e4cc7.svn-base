<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<center>
			<form action="YusuantianbaobiaoAction!updateYusuantianbaobiao.action" method="post">
				<table class="table">
					<tr>
						<th colspan="4" align="center">修改预算报表</th>
					</tr>
					<tr>
						<th align="right">
							项目名称或者代码：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.xiangmumingda"
							 value="${yusuantianbaobiao.xiangmumingda}"/>
						</td>
						<th align="right">
							固定资产：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.gudingzichan"
							 value="${yusuantianbaobiao.gudingzichan}"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							人力成本增加：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.renlichengbenzengjia"
							 value="${yusuantianbaobiao.renlichengbenzengjia}"/>
						</td>
						<th align="right">
							试验费用：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.shiyanfeiyong"
								value="${yusuantianbaobiao.shiyanfeiyong}"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							其他费用：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.qitafeiyong"
							value="${yusuantianbaobiao.qitafeiyong}"/>
						</td>
						<th align="right">
							收益金额：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.shouyijine"
							value="${yusuantianbaobiao.shouyijine}"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							收益年限：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.shouyinianxian"
							value="${yusuantianbaobiao.shouyinianxian}"/>
						</td>
						<th align="right">
							预算类型：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.yusuanleixing"
							value="${yusuantianbaobiao.yusuanleixing}"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							年度：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.niandu"
							value="${yusuantianbaobiao.niandu}" readonly="readonly"/>
						</td>
						<th align="right">
							部门：
						</th>
						<td>
							<input type="text" name="yusuantianbaobiao.bumen"
							value="${yusuantianbaobiao.bumen}"  readonly/>
						</td>
					</tr>
					<tr>
						<th align="right">
							
						</th>
						<td>
							
						</td>
						<th>
						</th>
						<td>
							<input type="hidden" name="idw" value="${yusuantianbaototal.id}"/>
							<input type="hidden" name="yusuantianbaobiao.addtime"
							value="${yusuantianbaobiao.addtime}"/>
							<input type="hidden" name="yusuantianbaobiao.id"
							value="${yusuantianbaobiao.id}"/>
							<input type="hidden" name="yusuantianbaobiao.epid"
							value="${yusuantianbaobiao.epid}"/>
						</td>
					</tr>
					<tr>	
						<td colspan="4" align="center">	
							<input type="submit" value="修改"/>
							<input type="reset" value="重置 "/>
							
						</td>					
					</tr>
				</table>
			</form>			
		</center>
		<script type="text/javascript">
</script>
	</body>
</html>