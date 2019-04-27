<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		table td {
		text-align:center;
		
}
	</style>
	</head>
	<body>
		
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			
			
			<div align="center">
				<form	action="QualityccAction!updateQualitytoo.action?qualityto.Id=${qualityto.id}"
					method="post">
					<table class="table">
						<tr>
							<td  >产品名称</td>
							<td  colspan="2">${qualityto.productname}</td>
							<td  colspan="2">产品图号</td>
							<td  colspan="2">${qualityto.bianhao}</td>
							<td  >客户名称</td>
							<td colspan="4">${qualityto.kehu}</td>						
						</tr>
						<tr>
							<td  >审核日期	</td>
							<td  colspan="2">${qualityto.addtime}</td>
							<td  colspan="2">抽样批次</td>
							<td  colspan="2">${qualityto.pici}</td>
							<td>引用标准</td>
							<td  colspan="4">${qualityto.yybz}</td>
						</tr>
						<tr>
							<td  rowspan="2">项目</td>
							<td  rowspan="2">标准值/要求</td>
							<td  rowspan="2">量检具/编号</td>
							<td  colspan="5" >样本数</td>
							<td  colspan="3">缺陷系数</td>
							<td>缺陷数</td>
						</tr>
						<tr>
							<td>样本1</td>
							<td>样本2</td>
							<td>样本3</td>
							<td>样本4</td>
							<td>样本5</td>
							<td>A</td>
							<td>B</td>
							<td>C</td>
							<td></td>
						</tr>
						<s:iterator value="list" id="qualityta" status="pageStatus">
						<tr>
							<td>${qualityta.project}</td>
							<td  width="20%">${qualityta.biaozhun}</td>
							<td>${qualityta.fangshi}</td>
							<td>${qualityta.shuju1}</td>
							<td>${qualityta.shuju2}</td>
							<td>${qualityta.shuju3}</td>
							<td>${qualityta.shuju4}</td>
							<td>${qualityta.shuju5}</td>
							<td><s:if test='%{"A".equals(#qualityta.xishul)}'>${qualityta.xishuz}</s:if></td>
							<td><s:if test='%{"B".equals(#qualityta.xishul)}'>${qualityta.xishuz}</s:if></td>
							<td><s:if test='%{"C".equals(#qualityta.xishul)}'>${qualityta.xishuz}</s:if></td>
							<td>${qualityta.shuzi}</td>
						</tr>
						</s:iterator>
						
						<tr>
							<td colspan="12">QBZ=${qualityto.kbz}</td>
						</tr>
						
						<tr>
							<td>结论</td>
							<td  colspan="2">${qualityto.jlun}
							 </td>
							<td  colspan="3">处置方案</td>
							<td colspan="6">${qualityto.faan}
							</td>
						</tr>
						<tr>
							<td rowspan="2">说明	</td>
							<td colspan="2">缺陷类别</td>
							<td colspan="9">${qualityto.shuoming1}
								</td>
						</tr>
						<tr>
							<td colspan="2">以上审核项目所涉及到的量检具均为合格量检具			
							</td>
							<td colspan="9">${qualityto.shuoming2}
							</td>
						</tr>
						<tr>
							<td>审核人员:</td>
							<td colspan="2">${qualityto.shy}</td>
							<td colspan="3">审批状态:</td>
							<td colspan="6">${qualityto.statu}</td>
						</tr>
						
					</table>
					</form>

			</div>
			<br>
		</div>
		
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

		</script>
	</body>
</html>
