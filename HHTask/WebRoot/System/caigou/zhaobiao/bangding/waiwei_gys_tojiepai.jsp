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
<center>
				<form action="markIdAction!waiweijiepai.action" method="post"
					theme="simple">
					
					<table class="table" style="width: 70%;">
					<tr><th colspan="2" align="center">添加工序节拍</th></tr>
					<tr>
							<th align="right">
								工序号：
							</th>
							
							<td>
							${pIdZijian.processNO}
									</td>
						</tr>
						<tr>
							<th align="right">
								工序名称：
							</th>
							
							<td>
							${pIdZijian.processName}
									</td>
						</tr>
						<tr  style="display: none">
							<th align="right" >
								类型：
							</th>
							<td>
								${pIdZijian.productStyle}
							</td>
						</tr>
						<tr>
							<th align="right">
								操作人工节拍：
							</th>
							<td  >
							<input type="text" name="pIdZijian.opcaozuojiepai"  value="${pIdZijian.opcaozuojiepai}"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								操作设备节拍：
							</th>
							<td>
								<input type="text"  name="pIdZijian.opshebeijiepai"  value="${pIdZijian.opshebeijiepai}"/>
							
							</td>
						</tr>
						<tr>
							<th align="right">
								准备人工节拍：
							</th>
							<td>
								<input type="text"  name="pIdZijian.gzzhunbeijiepai"  value="${pIdZijian.gzzhunbeijiepai}"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								准备次数：
							</th>
							<td>
								<input type="text" id="pIdZijian.gzzhunbeicishu" name="pIdZijian.gzzhunbeicishu"  value="${pIdZijian.gzzhunbeicishu}"/>
							</td>
						</tr>
						<tr>
							<tr>
							<th align="right">
								单班时长：
							</th>
							<td>
								<input type="text" id="pIdZijian.singleDuration" 
								name="pIdZijian.singleDuration"  value="${pIdZijian.singleDuration}"/>/h
								<font style="color: red;">*必填项单班时长是指您的单班工作时长即上班时长(8h)</font>
								
							</td>
						</tr>
						<tr>
							<tr>
							<th align="right">
								配送时长：
							</th>
							<td>
								<input type="text" id="pIdZijian.deliveryDuration"
								 name="pIdZijian.deliveryDuration"  value="${pIdZijian.deliveryDuration}"/>/h
								<font style="color: red;">*必填项(配送时长是指您 总共占用我们多少生产时间)</font>
							
							</td>
						</tr>
						<tr>
						
							<td colspan="2" align="center">
								<input type="hidden" name="pIdZijian.id"  value="${pIdZijian.id}"/>
								<input type="hidden" name="gysMarkIdjiepai.id"  value="${gysMarkIdjiepai.id}"/>
								
								
								<input type="submit" value="修改" class="input" />
								<input type="button" name="Submit2" value="取消" class="input"
									class="right-buttons" onclick="window.history.go(-1);" />
							</td>
						</tr>
					</table>


				</form>
			</div>
			<%@include file="/util/foot.jsp"%>
			
			
			</center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
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
