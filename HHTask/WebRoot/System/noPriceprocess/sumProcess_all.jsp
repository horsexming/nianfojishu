<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/javascript/layer/theme/default/layer.css">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/layer/layer.js">
			</script>
	</head>
	<body>
	<%@include file="/util/sonTop.jsp"%>
	<div align="center">
		<h2>工序报价（总成件号）</h2>
	</div>
	<table width="540PX" align="center" border="1" bordercolor="black" style="border-collapse:collapse;" class="table">
	<form action="NoPriceprocessAction_findAllForMarkId.action" method="post">
		<tr  height="20px">
			<td colspan="6" align="center"><h3>查询</h3></td>
		</tr>
		<tr  height="20px">
			<th>总成件号</th>
			<td align="center" >
				<input name="noPriceprocess.rootMarkId" type="text"/>
			</td>
			<th>业务件号</th>
			<td align="center" >
				<input name="noPriceprocess.ywmarkId" type="text"/>
			</td>
			<th>工序名称</th>
			<td align="center" >
				<input name="noPriceprocess.processName" type="text"/>
			</td>
		</tr>
		<tr>
			<td colspan="6" align="center">
				<input type="submit" value="查询"/>
				<br>
				<hr>
				<input type="button" id ="showold" value="显示之前报价单" onclick="showoldnpp();"/>
				<input type="button" id ="disold" value="隐藏之前报价单" onclick="disoldnpp();" style="display : none"/>
			</td>
		</tr>
	</form>
	</table>
	<br/>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<div id="oldnpp" style="display : none">
				<table class="table"  >
				<tr >
					<td colspan="15" align="center" style="color: blue">
						需要选择供应商的报价单
					</td>
				</tr>
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							表头
						</td>
						<td align="center">
							总成件号
						</td>
						<td align="center">
							业务件号
						</td>
						<td align="center">
							需要报价的数量
						</td>
						<td align="center">
							添加时间
						</td>
						<td align="center">
							开始时间
						</td>
						<td align="center">
							结束时间
						</td>
						<td align="center">
							周期
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator value="sumProcessList" id="list1"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${list1.title}
						</td>
						<td align="center">
							${list1.rootMark}
						</td>
						<td align="center" style="WORD-WRAP: break-word" width="60PX">
							${list1.ywmarkId}
						</td>
						<td align="center">
							${list1.count}
						</td>
						<td align="center">
							${list1.addTime}
						</td>
						<td align="center">
							${list1.bjStartDate}
						</td>
						<td align="center">
							${list1.bjEndDate}
						</td>
						<td align="center">
						<s:if test="#list1.cycle!=null">
							${list1.cycle}天
						</s:if>
						</td>
						<td align="center">
							${list1.stutas}
						</td>
						<td align="center">
							<a onclick="checkZhuser(${list1.id});">选择供应商</a>
							<a onclick="deleteSum(${list1.id});">删除</a>
						</td>
					</s:iterator>
					<s:if test="tishi!=null">
						<td colspan="12" align="center" style="color: red">
								${tishi}
						</td>
					</s:if>
				</table>
				</div>
				<br/>
				<table class="table">
				<form action="NoPriceprocessAction_toZhouqi.action" method="post">
				<tr >
					<td colspan="15" align="center" style="color: red">
						请选择相同的总成件号进行报价
					</td>
				
				</tr>
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							<input type="checkbox" onclick="chageAllCheck(this)"/>
						</td>
						<td align="center">
							序号
						</td>
						<td align="center">
							总成件号
						</td>
						<td align="center">
							业务件号
						</td>
						<td align="center">
							件号
						</td>
						<td align="center">
							件号名称
						</td>
						<td align="center">
							工序号
						</td>
						<td align="center">
							工序名称
						</td>
						<td align="center">
							委外申请数量
						</td>
						<td align="center">
							批次数量
						</td>
						<td align="center">
							开始时间
						</td>
						<td align="center">
							结束时间
						</td>
						<td align="center">
							周期
						</td>
						<td align="center">
							状态
						</td>
<!--						<td align="center">-->
<!--							操作-->
<!--						</td>-->
					</tr>
					<s:iterator value="noPriceprocessList" id="list"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td align="center">
							<input id="offerId" name="offerIds" type="checkbox" value="${list.id}" onclick="chageNum(this)"/>
						</td>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${list.rootMarkId}
						</td>
						<td align="center">
							${list.ywmarkId}
						</td>
						<td align="center">
							${list.markId}
						</td>
						<td align="center">
							${list.name}
						</td>
						<td align="center">
							${list.processNO}
						</td>
						<td align="center">
							${list.processName}
						</td>
						<td align="center">
							${list.waiweiShenqiCount}
						</td>
						<td align="center">
							${list.piciCount}
						</td>
						<td align="center">
							${list.bjStartDate}
						</td>
						<td align="center">
							${list.bjEndDate}
						</td>
						<td align="center">
						<s:if test="#list.cycle!=null">
							${list.cycle}天
						</s:if>
						</td>
						<td align="center">
							${list.stutas}
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="15" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="12" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
					<tr>
						<td colspan="15" align="center">
<!--							<s:if test="#list.cycle==null||#list.cycle==''">-->
<!--							<a onclick="showProcess(${list.id});">选择报价周期</a>/-->
							<input type="button" value="选择报价周期" onclick="showProcess()"/>
<!--						</s:if>-->
<!--						<s:if test="#list.stutas==null||#list.stutas=='报价中'||#list.stutas==''">-->
<!--							<a onclick="checkZhuser(${list.id});">选择供应商</a>-->
<!--						</s:if>-->
						</td>
					</tr>
				</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
			<script type="text/javascript">
		function showProcess() {
		 var ids = "";
		$("input:checkbox[name='offerIds']:checked").each(function(){
			if(ids==""){
				ids += $(this).val();
			}else{
				ids += ","+$(this).val() ;
			}
		});
		layer.open({
 			type: 2,
 			 title: '填写报价周期',
 			 area: ['700px', '400px'],
 			 offset: '100px',
  				fixed: false, //不固定
				//  maxmin: true,
  				content: '<%=basePath%>/NoPriceprocessAction_toZhouqi.action?offerIds='+ids
});
		}
//		function addTime(id) {
//		window.location.href = "NoPriceprocessAction_.action?noPriceprocess.id="
//			+ id + "&cpage=${cpage}";
//		}
			function checkZhuser(id) {
			window.location.href = "NoPriceprocessAction_allZhuserForMarkId.action?sumProcess.id="
				+ id;
		}
			function deleteSum(id) {
				window.location.href = "NoPriceprocessAction_delete.action?sumProcess.id="
					+ id;
			}
		function showoldnpp(){
			document.getElementById("oldnpp").style.display='block';
			document.getElementById("disold").style.display='block';
			document.getElementById("showold").style.display='none';
		}
		
		function disoldnpp(){
			document.getElementById("oldnpp").style.display='none';
			document.getElementById("disold").style.display='none';
			document.getElementById("showold").style.display='block';
		}
		
</script>
	</body>
</html>
