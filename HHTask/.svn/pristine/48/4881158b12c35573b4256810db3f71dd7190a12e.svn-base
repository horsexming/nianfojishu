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
	<body >
	<%@include file="/util/sonTop.jsp"%>
		<center>
			<form action="DingdanAction!quruku.action" method="post"
				theme="simple">
				<table class="table" style="width: 70%">
					<tr>
					<th align="center" colspan="5">
							入库申请单</th></tr>
							<tr><td colspan="4" align="left"><font style="color: red;">企划部（物流)</font></td></tr>
							<tr><th align="right">订单号:</th>
							    <td><input type="text" name="rukudan.dindanhao"  value="${rukudan.dindanhao}"    readonly="readonly"/></td>
						<th align="right">送货单号:</th>
							    <td><input type="text"  name="rukudan.songhuodanhao"  value="${rukudan.songhuodanhao}"    readonly="readonly"/></td>
							</tr>
							
							<tr><th align="right">供货厂家:</th>
							    <td><input type="text"  name="rukudan.gys"  value="${rukudan.gys}"     readonly="readonly"/></td>
							
							<th align="right">送货时间:</th>
							    <td>
									<input class="Wdate" type="text" id="rukudan.songhuoshijian"
									name="rukudan.songhuoshijian" value="${rukudan.songhuoshijian}"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"     readonly="readonly"/>
									
									</td>
							</tr>
							
							<tr><th align="right">牌号:</th>
							    <td><input type="text"  name="rukudan.name"  value="${rukudan.name}"    readonly="readonly"/></td>
							
							<th align="right">材料规格:</th>
							    <td><input type="text"  name="rukudan.guige"  value="${rukudan.guige}"    readonly="readonly"/></td>
							</tr>
							
							<tr><th align="right">送检数量:</th>
							    <td><input type="text"  name="rukudan.songjainshuliang" value="${rukudan.songjainshuliang}"     readonly="readonly"/></td>
							
							<th align="right">材质、检验报告编号:</th>
							    <td><input type="text"  name="rukudan.bianhao" value="${rukudan.bianhao}"    readonly="readonly"/></td>
							</tr>
							
							<tr><th align="right">采购经办人:</th>
							    <td><input type="text"  name="rukudan.caigouren"  value="${rukudan.caigouren}"    readonly="readonly"/>
							    </td>
							 <th align="right">申请单编号:</th>
							    <td><input type="text"  name="rukudan.rukubianhao" value="${rukudan.rukubianhao}"/></td>
							</tr>
							<tr><th align="right">物料名称:</th>
							    <td><input type="text"  name="rukudan.mingcheng"  value="${rukudan.mingcheng}"    readonly="readonly"/></td>
							    <td></td><td></td>
							</tr>
							
						<tr></tr>	
						<tr><td colspan="4" align="left"><font style="color: red;">技术质保部（品质)</font></td></tr>	
							<tr><th align="right" >批次号:</th>
							    <td ><input type="text"  name="rukudan.picihao" value="${rukudan.picihao}"    readonly="readonly"/>
							    <th align="right" >验收结论:</th>
							    <td >
							    	<input type="text"  name="rukudan.yanshou" value="${rukudan.yanshou}"    readonly="readonly"/>
							   </td>
							    
							</tr>
							<tr>
							<th align="right">炉批号:</th>
							    <td><input type="text"  name="rukudan.lupihao" value="${rukudan.lupihao}"    readonly="readonly"/></td>
							    <th align="right">质量反馈单编号:</th>
							    <td><input type="text"  name="rukudan.fankuihao"  value="${rukudan.fankuihao}"    readonly="readonly"/>
							    </td>
							</tr>
							<tr>
							<th align="right">品质经办人:</th>
							    <td><input type="text"  name="rukudan.pinzhiren" value="${sessionScope.Users.name}"    readonly="readonly"/></td>
							    <th align="right"></th>
							    <td>
							    </td>
							</tr>


	       <tr><td colspan="4" align="left"><font style="color: red;">生产部（物流)</font></td></tr>	
	       <tr>
							
							    <th align="right">入库数量:</th>
							    <td><input type="text"  name="rukudan.rukushuliang"/>
							    <th align="right">单位:</th>
							    <td>
							    
							    	<select name="rukudan.danwei" id="unit">
								</select>
							    
							    </td>
							    </td>
							</tr>
								<tr>
							<th align="right">物流经办人:</th>
							    <td><input type="text"  name="rukudan.wuliuren" value="${sessionScope.Users.name}"/></td>
							    <th align="right"></th>
							    <td>
							    </td>
							</tr>
							
							
							
							<tr>
							<td align="center" colspan="4">
							<input type="hidden"  name="rukudan.id" value="${rukudan.id}"/>
							<input type="hidden"  name="rukudan.caigiuId" value="${rukudan.caigiuId}"/>
							
							<input type="submit" value="确认并保存" class="input" />
							<input type="button" name="Submit2" value="取消" class="input"
								class="right-buttons" onclick="window.history.go(-1);" /></td>
							</tr>
							</table>
					
		
				</form>
				<%@include file="/util/foot.jsp"%>
		</center>
	</body>
	
	<script type="text/javascript">
$(function(){
	getUnit("unit");
	var errorMessage = '${errorMessage}';
		if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
	
	})
</script>
</html>
