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
		<center>
			<form action="DingdanAction!addcaigouxiangxiangxin.action" method="post" theme="simple">
				<table class="table">
					<tr bgcolor="#c0dcf2">
						<td>
							编号:
						</td>
						
						<td>
							件号:
						</td>
						
						<td>
							总数量:
						</td>
						<td>
							已购买数量:
						</td>
						<td>
							单位:
						</td>
						<td>
							规格要求:
						</td>
						<td>
							采购数量:
						</td>
						<td>
							到货期限:
						</td>
					</tr>

					<s:iterator value="list" id="pageList" status="pageStatus">
						<tr >
							<td>
							   ${pageStatus.index+1} 
							  
							   <input type="hidden" name="jihuanid"
										value="${pageList.id}"/>
										
								  <input type="hidden" name="oderids"
										value="${pageList.internalOrderzhaobiaoid}"/>	
										
									 <input type="hidden"  name="dateilids"
										value="${pageList.internalOrderDetailzhaobiaoid}"/>	
										
											
											 <input type="hidden"  name="lexings"
										value="${pageList.leixing}"/>	
										
										 <input type="hidden"  name="dingdanIds"
										value="${pageList.dingdanId}"/>
							</td>
							
							<td>
							 <!--物料名称  -->
							
									<input type="text" name="nams"
										value="<s:if test='#pageList.paihao==null'>${pageList.markId}</s:if><s:else>${pageList.paihao}</s:else>" />
							</td>
							
							<td>
							 <!--总数量 -->
								<input type="text"  name="zongshuliangs"
									value="${pageList.shuliang}"  style="width: 50px;"/>&nbsp;&nbsp;
							</td>
							<td>
							 <!--已转化的数量 -->
								<input type="text"  name="yijin"
									value="${pageList.yijin}"  style="width: 50px;"/>&nbsp;&nbsp;
							</td>

							<td>
							 <!--单位-->
								<input type="text"  name="danweis"
									value="${pageList.danwei}" />
							</td>
							<td>
							 <!--规格要求 -->
								<input type="text"  name="guiges"
									value="${pageList.guige}" />
							
							</td>
							<td>
							 <!--数量-->
								<input type="text"  name="caigous"
									value="" />
							</td>
							<td>
							 <!--到货期限-->
								<input type="text"  name="shijians"  id="shijians${pageStatus.index}" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:00',skin:'whyGreen'})" onblur="getshijian(this.value)"  />
							</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="10" align="center">

							<input type="submit" value="保存" class="input" />
							<input type="button" name="Submit2" value="取消" class="input"
								class="right-buttons" onclick="window.history.go(-1);" />
						</td>
					</tr>
				</table>
				</form>
		</center>
	</body>
	<script type="text/javascript">
	function getshijian(shijian){
		 var len=<s:property value='list.size()'/>;
		 
		 for(var i=0;i<len;i++){
			 document.getElementById("shijians"+i).value=shijian
		 }
	}


</script>
</html>
