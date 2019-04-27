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
	<body  >
		<center>
			<form action="DingdanAction!addRukudanzhaobiao.action" method="post"
				theme="simple">
				<table class="table" style="width: 50%">
					<tr>
					<th align="center" colspan="2">
							入库申请单</th></tr>
							
							<tr><th align="right">订单号:</th>
							    <td><input type="text" name="rukudan.dindanhao" /></td>
							</tr>
							<tr><th align="right">送货单号:</th>
							    <td><input type="text"  name="rukudan.songhuodanhao" /></td>
							</tr>
							<tr><th align="right">供货厂家:</th>
							    <td><input type="text"  name="rukudan.gys"  value="${zhtoubiao.tname}"/></td>
							</tr>
							<tr><th align="right">送货时间:</th>
							    <td>
									<input class="Wdate" type="text" id="rukudan.songhuoshijian"
									name="rukudan.songhuoshijian"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
									
									</td>
							</tr>
							<tr><th align="right">牌号:</th>
							    <td><input type="text"  name="rukudan.name"  value="${zhtoubiao.paihao}"/></td>
							</tr>
							<tr><th align="right">物料名称:</th>
							    <td><input type="text"  name="rukudan.mingcheng"  value=""/></td>
							</tr>
							
							
							<tr><th align="right">材料规格:</th>
							    <td><input type="text"  name="rukudan.guige"  value="${zhtoubiao.guige}"/></td>
							</tr>
							<tr><th align="right">送检数量:</th>
							    <td><input type="text"  name="rukudan.songjainshuliang" value="${zhtoubiao.xuqiushuliang}" /></td>
							</tr>
							<tr><th align="right">材质、检验报告编号:</th>
							    <td><input type="text"  name="rukudan.bianhao"/></td>
							</tr>
							<tr><th align="right">采购经办人:</th>
							    <td><input type="text"  name="rukudan.caigouren"  value="${sessionScope.Users.name}"/>
							    
							    
							    <input type="hidden"  name="rukudan.caigiuId"  value="${zhtoubiao.tid}"/>
							    </td>
							</tr>
							
							<tr>
							<td align="center" colspan="2">
							<input type="submit" value="保存" class="input" />
							<input type="button" name="Submit2" value="取消" class="input"
								class="right-buttons" onclick="window.history.go(-1);" /></td>
							</tr>
							</table>
					
		
				</form>
		</center>
	</body>
	<script type="text/javascript">
$(function(){
	var errorMessage = '${errorMessage}';
		if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
	
	})
</script>
</html>
