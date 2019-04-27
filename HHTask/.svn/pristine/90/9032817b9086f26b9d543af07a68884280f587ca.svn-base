
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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
<!--				内部计划管理详情-->
				<h3>
					${title}
				</h3>
				<div align="left">
					计划编号: ${orderNum}
					计划编号: ${orderNum}
				</div>
				<form action="ProcardAction!addProCard.action" method="post"
					onsubmit="return hidesub()">
					<input name="pageStatus" value="${pageStatus}" type="hidden" />
					<input name="id" value="${id}" type="hidden" />
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								序号
							</td>
							<td align="center">
								件号
							</td>
							<td align="center">
								业务件号
							</td>
							<td align="center">
								产品名称
							</td>
							<td align="center">
								总数量
							</td>
							<td align="center">
								投产日期
							</td>
							<td align="center">
								交付日期
							</td>
							<td align="center">
								可分配数量
							</td>
							<td align="center">
								转换数量
							</td>
							<td align="center">
								装配日期
							</td>
							<td align="center">
								注意
							</td>
						</tr>
						
						<s:iterator value="list" id="pageList" status="pageindex">
							<s:if test="#pageindex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#pageindex.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#pageindex.index+1" />
								</font>
								<input name="processIds" value="${pageList.id}" type="hidden">
							</td>
							<td>
								${pageList.pieceNumber }
							</td>
							<td>
								${pageList.ywMarkId}
							</td>
							<td>
								${pageList.name}
							</td>
							<td>
								${pageList.num}
							</td>
							<td>
								${pageList.touchanDate}
							</td>
							<td>
								${pageList.paymentDate}
							</td>
							<td>
								${pageList.num-pageList.turnCount}
							</td>
							
							<td>
								<s:if test="pageStatus=='noCard'">
									<input name="processNumbers"
										value="${pageList.num-pageList.turnCount}" />
								</s:if>
								<s:else>
									<input name="processNumbers" value="0" />
								</s:else>
							</td>
							<td>
								<s:if test="(#pageList.num-#pageList.turnCount)==0">
									${pageList.jihuoDate}<input name="processCards"
										value="${pageList.paymentDate}" type="hidden" />

								</s:if>
								<s:else>
									<input name="processCards" class="Wdate" type="text" value="${pageList.paymentDate}"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen',startDate:'%y-%M-%d 08:00:00'})" />
								</s:else>
							</td>
							<th>
							</th>
						</s:iterator>
						</tr>
						<tr>
							<td align="center" colspan="15">
								<input type="hidden" value="${tag}" name="tag" />
								<input id="hideSub" type="submit" value="转换" class="input" />
								<div id="showMess" style="display: none;"></div>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
			function update(id){
				window.location="internalOrder_initProduct.action?id="+id+"&customeId="+${id};
			}
			function hidesub(){
				var subFlag=true;
				var processNumbersObj=document.getElementsByName("processNumbers");
				$.each(processNumbersObj,function(key,value){
					var val=$(this).val();
					if(val!="0"){
						//验证时间
						var timeObj=$(this).parent("td").next().find("input").get(0);
						var timeVal=timeObj.value;
						if(timeVal=="" ||timeVal.length<=0){
							alert("请输入装配日期");
							timeObj.focus();
							subFlag=false;
							return false; 
						}
					}
				});
				if(subFlag){
					$("#hideSub").hide();
					$("#showMess").html("<font color='red'>生产BOM正在生成中...请耐心等待!谢谢~</font>");
					$("#showMess").show();
					
				}
				return subFlag;
			}             
		</SCRIPT>
	</body>
</html>
