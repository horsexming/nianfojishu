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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
				        项目材料清单管理<br/>(project material order Management)
				</h3>
				<form action="projectMaterialOrderAction_showList.action"
					method="post">
					<table class="table" align="center">
						<tr>
						   <td align="center">
								订单号（order Number）：
								<input type="text" name="projectMaterialOrder.orderNo" value="<s:property value="projectMaterialOrder.orderNo"/>" />
							</td>
							<td align="center">
								项目名称（project name）：
								<input type="text" name="projectMaterialOrder.proName" value="<s:property value="projectMaterialOrder.proName"/>" />
							</td>
						</tr>
						<tr>
						   <td align="center">
								申请人工号（applicant code）：
								<input type="text" name="projectMaterialOrder.usercode" value="<s:property value="projectMaterialOrder.usercode"/>" />
							</td>
							<td align="center">
								申请人名字（applicant name）：
								<input type="text" name="projectMaterialOrder.userName" value="<s:property value="projectMaterialOrder.userName"/>" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加清单(add)" onclick="add()"/>
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号<br/>(num)
						</td>
						<td align="center">
							清单编号<br/>(order number)
						</td>
						<td align="center">
							项目名称<br/>（project name）
						</td>
						<td align="center">
							申请人工号<br/>（applicant code）
						</td>
						<td align="center">
							申请人名字<br/>（applicant name）
						</td>
						<td align="center">
							申请时间<br/>（applicant time）
						</td>
						<td align="center">
							申请状态<br/>（applicant time）
						</td>
						<td align="center">
							领取状态<br/>（receive Status）
						</td>
						<td align="center">
							备注<br/>（remake）
						</td>
						<td align="center" colspan="4">操作<br/>(Operation)</td>
					</tr>
					<s:iterator value="pmoList" id="pmo" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td>
							${pmo.orderNo}
						</td>
						<td>
							${pmo.proName}
						</td>
						<td>
							${pmo.usercode}
						</td>
						<td>
							${pmo.userName}
						</td>
						<td>
							${pmo.addTime}
						</td>
						<td>
							${pmo.aduitStatus}
						</td>
						<td>
							${pmo.receiveStatus}
						</td>
						<td>
							${pmo.remark}
						</td>
						
						<td  colspan="4">
							<input type="button" value="明细(detail)"
								style="width: 60px; height: 30px;"
								onclick="showdetail(${pmo.id})" />
								<input type="button" value="审批流程(applicant status)"
								style="width: 60px; height: 30px;"
								onclick="applicantView(${pmo.epId})" />
								<s:if test="#pmo.aduitStatus=='未审批'||#pmo.aduitStatus=='打回'">
							<input type="button" value="修改(update)"
								style="width: 60px; height: 30px;"
								onclick="update(${pmo.id},${cpage})" />
							<input type="button" value="删除(delete)"
								style="width: 60px; height: 30px;"
								onclick="if(window.confirm('您将删除数据是否继续？')){todelete(${pmo.id },${cpage})};" />
								</s:if>
								<s:if test="#pmo.aduitStatus=='同意'">
							<input type="button" value="删除(delete)"
								style="width: 60px; height: 30px;"
								onclick="if(window.confirm('您将删除数据是否继续？')){todelete(${pmo.id },${cpage})};" />
								</s:if>
								<input type="button" value="导出"
								style="width: 60px; height: 30px;"
								onclick="exprotqd(${pmo.id});todisabledone(this)" data="downData"/>
						</td>

					</s:iterator>
					</tr>
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
						
						<s:if test="successMessage!=null">
						<tr>
							<td colspan="11" align="center" style="color: red">
								${successMessage}
								
						</td>
					</tr>
                          </s:if>
				</table>
			</div>
		</div>
		<input type="hidden" value="<s:property value='successMessage'/>" id="successMessage">
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		 function add(){
		  window.location.href="<%=path%>/System/pro/projectmaterial/promaterialorder_add.jsp";
		 }
		 	function approval(id) {
	     window.location.href = "projectMaterialOrderAction_toapproval.action?projectMaterialOrder.id="+ id;
       }
		 	function showdetail(id){
		 		 window.location.href = "projectMaterialOrderAction_pmdetail.action?projectMaterialOrder.id="+ id;
		 	}
		 	function todelete(id,cpage){
		 		 window.location.href = "projectMaterialOrderAction_todelete.action?projectMaterialOrder.id="+ id+"&cpage="+cpage;
		 	}
		 	function update(id,cpage){
		 		window.location.href = "projectMaterialOrderAction_toupdate.action?projectMaterialOrder.id="+ id+"&cpage="+cpage;
		 	}
		 	  $(document).ready(function(){
			  var successMessage=$("#successMessage").val();
			if(successMessage!=""){
　　                          alert(successMessage);
　　                          }
		  });
		 	 function applicantView(epId){
		 		 window.location.href = "CircuitRunAction_findAduitPage.action?id="+epId;
		 	 }
		 	 function exprotqd(id){
	window.location.href = "projectMaterialOrderAction_exprotqd.action?id="+id
}
		</SCRIPT>
	</body>
</html>
