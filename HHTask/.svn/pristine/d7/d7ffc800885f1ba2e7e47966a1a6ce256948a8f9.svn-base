
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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<center>
					<table style="width: 100%">
						<tr>
							<td>

							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none')">
							</td>
						</tr>
					</table>
				</center>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>

		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center" id="d1">
				<form action="zhaobiaoAction!listzhaobiao.action" method="post"
					theme="simple">
					<table class="table">
						<tr>
							<th align="center">
								标题：
							</th>
							<td>
								<input type="text" id="zhaobiao.title" name="zhaobiao.title" />
							</td>
							<th align="center">
								负责人：
							</th>
							<td>
								<input type="text" id="zhaobiao.fuze" name="zhaobiao.fuze" />
							</td>
							<td rowspan="3">
								<input type="submit" value="查询" class="input" />
								<input type="button" value="添加" class="input" onclick="add()" />
						</tr>
						<tr>
							<th align="center">
								起：
							</th>
							<td>

								<input class="Wdate" type="text" id="zhaobiao.moban"
									name="zhaobiao.moban"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="center">
								止：
							</th>
							<td>
								<input class="Wdate" type="text" id="zhaobiao.kongxian"
									name="zhaobiao.kongxian"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>

						</tr>
						<tr>
							<th align="center">
								状态：
							</th>
							<td>
								<select name="zhaobiao.status" id="zhaobiao.status">
									<option value="">
										--请选择--
									</option>
									<option value="G">
										回款方式已经确定
									</option>
									<option value="W">
										拟定
									</option>
									<option value="N">
										未提交
									</option>
									<option value="C">
										正在审批
									</option>
									<option value="F">
										回款方式拟定中
									</option>

									<option value="E">
										拟定
									</option>
									<option value="D">
										招标中
									</option>
									<option value="Y">
										已审批
									</option>
									<option value="S">
										评标中
									</option>
									<option value="审核中">
										上级审核中
									</option>
									<option value="同意">
										上级审核通过
									</option>
									<option value="未审批"'>
										上级审核中
									</option>
									<option value="合同审批中">
										合同审批中
									</option>
								</select>
							</td>
							<td></td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2">
						<th>
							序号
						</th>
						<th>
							招标名称
						</th>
						<th>
							负责人
						</th>
						<th>
							负责人电话
						</th>

						<th>
							开始时间
						</th>
						<th>
							结束时间
						</th>
						<th>
							发布人
						</th>
						<th>
							状态
						</th>
						<th>
							审批状态
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="list" id="zhaobiao1" status="pageIndex">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<th>
							${pageIndex.index+1}
						</th>
						<th>
							${zhaobiao1.title}
						</th>
						<th>
							${zhaobiao1.fuze}
						</th>
						<th>
							${zhaobiao1.phone}
						</th>

						<th>
							${zhaobiao1.moban}
						</th>
						<th>
							${zhaobiao1.kongxian}
						</th>
						<th>
							${zhaobiao1.faburen}
						</th>
						<th>
							<s:if test='#zhaobiao1.status=="G"'>回款方式已经确定</s:if>
							<s:elseif test='#zhaobiao1.status=="W"'>拟定</s:elseif>
							<s:elseif test='#zhaobiao1.status=="N"'>未提交</s:elseif>
							<s:elseif test='#zhaobiao1.status=="C"'>正在审批</s:elseif>
							<s:elseif test='#zhaobiao1.status=="F"'>回款方式拟定中</s:elseif>
							<s:elseif test='#zhaobiao1.status=="E"'>拟定</s:elseif>
							<s:elseif test='#zhaobiao1.status=="D"'>招标中</s:elseif>
							<s:elseif test='#zhaobiao1.status=="Y"'>
								<s:if test='#zhaobiao1.shenpiStatus=="同意"'>已审批</s:if>
								<s:else>拟定审批中</s:else>
							</s:elseif>
							<s:elseif test='#zhaobiao1.status=="S"'>评标中</s:elseif>
							<s:elseif test='#zhaobiao1.status=="审核中"'>上级审核中</s:elseif>
							<s:elseif test='#zhaobiao1.status=="审批中"'>上级审核中</s:elseif>
							<s:elseif test='#zhaobiao1.status=="同意"'>上级审核通过</s:elseif>
							<s:elseif test='#zhaobiao1.status=="未审批"'>上级审核中</s:elseif>
							<s:elseif test='#zhaobiao1.status=="合同审批中"'>合同审批中</s:elseif>
							<s:elseif test='#zhaobiao1.status=="自动评标结束"'>自动评标结束</s:elseif>
							<s:elseif test='#zhaobiao1.status=="完成"'>完成</s:elseif>
						</th>
						<th>
							${zhaobiao1.shenpiStatus}
						</th>
						<th>
							<s:if test='#zhaobiao1.status=="E"'>
								<a onclick="toUpdatezhaobiao(${zhaobiao1.id})">修改</a>
								<a
									href="zhaobiaoAction!deletezhaobiao.action?zhaobiao.id=${zhaobiao1.id}">删除</a>
								<a
									href="zhaobiaoAction!listById.action?zhaobiao.id=${zhaobiao1.id}">添加采购料</a>
							</s:if>
							<!-- 
							<s:if test='#zhaobiao1.status=="N"'>
								<a href="zhaobiaoAction!deletezhaobiao.action?zhaobiao.id=${zhaobiao1.id}">删除</a>
								<a href="zhaobiaoAction!updatezhaobiaoXi.action?zhaobiao.id=${zhaobiao1.id}">修改板料</a>
								<a  
									href="zhaobiaoAction!chakanflowdetail.action?zhaobiao.id=${zhaobiao1.id}">查看审批记录</a>
							</s:if>
							 -->

							<s:if test='#zhaobiao1.status=="W"'>
								<!--  <a href="zhaobiaoAction!huikuangfangshi.action?zhaobiao.id=${zhaobiao1.id}">选择回款方式</a> -->
							  <a href="CircuitRunAction_findAduitPage.action?id=${zhaobiao1.epId}">审批动态</a>
							</s:if>
							<s:if test='#zhaobiao1.status=="Y"'>
								<!--<a href="zhaobiaoAction!chakanflowdetail.action?zhaobiao.id=${zhaobiao1.id}">查看审批记录</a>-->
								<s:if test='#zhaobiao1.shenpiStatus=="同意"'>
									<a href="zhaobiaoAction!fabu.action?zhaobiao.id=${zhaobiao1.id}">发布</a>
									<a href="zhaobiaoAction!xianshizhaoshangyonghu.action?zhaobiao.id=${zhaobiao1.id}">显示可以投标的招商用户</a>
								</s:if>
								<a href="CircuitRunAction_findAduitPage.action?id=${zhaobiao1.epId}">审批动态</a>
							</s:if>





							<s:if test='#zhaobiao1.status=="D"'>
								<!-- <a href="zhaobiaoAction!chakantoubiao.action?zhaobiao.id=${zhaobiao.id}">查看投标记录</a> -->
								<a
									href="zhaobiaoAction!chankantoubiaoXi.action?zhaobiao.id=${zhaobiao1.id}">查看投标记录</a>
								<a
									href="zhaobiaoAction!jieshu.action?zhaobiao.id=${zhaobiao1.id}">结束</a>
								<a
									href="zhaobiaoAction!xianshizhaoshangyonghu.action?zhaobiao.id=${zhaobiao1.id}">显示可以投标的招商用户</a>
							</s:if>
							<!--
							<s:if test='#zhaobiao1.status=="D"'>
								<a href="zhaobiaoAction!tohuikuang.action?zhaobiao.id=${zhaobiao1.id}">提交财务选择回款方式</a>  
							</s:if>
							-->
							<s:if test='#zhaobiao1.status=="S"'>
								<!-- 	<a
									href="zhaobiaoAction!qupingxuan.action?zhaobiao.id=${zhaobiao1.id}">手动评选</a> 
								<a href="zhaobiaoAction!listzidong.action">自动评选</a>-->
								<a
									href="zhaobiaoAction!pingbiao.action?zhaobiao.id=${zhaobiao1.id}">自动评标</a>
								<a
									href="zhaobiaoAction!chankantoubiaoXi.action?zhaobiao.id=${zhaobiao1.id}">查看投标记录</a>


								<!-- <a href="zhaobiaoAction!mingxi.action?zhaobiao.id=${zhaobiao1.id}">查看明细</a> 
								<a href="zhaobiaoAction!chankantoubiaoXi.action?zhaobiao.id=${zhaobiao1.id}">查看投标记录</a>-->
							</s:if>
							<s:if test='#zhaobiao1.status=="自动评标结束"'>
								<a
									href="zhaobiaoAction!shenhezhaobiao.action?zhaobiao.id=${zhaobiao1.id}">提交中标信息审核</a>

								<a
									href="zhaobiaoAction!dayinzhongbiaoxinxi.action?zhaobiao.id=${zhaobiao1.id}">打印中标信息</a>
								<a
									href="zhaobiaoAction!chankantoubiaoXi.action?zhaobiao.id=${zhaobiao1.id}">查看中标信息</a>
							</s:if>


							<s:if test='#zhaobiao1.status=="同意"'>
								<a
									href="zhaobiaoAction!tijiaohetong.action?zhaobiao.id=${zhaobiao1.id}">查看合同</a>
								<!-- 
							<a href="zhaobiaoAction!tochakanhetong.action?zhaobiao.id=${zhaobiao1.id}">打印中标信息</a>
							 -->
								<a
									href="zhaobiaoAction!dayinzhongbiaoxinxi.action?zhaobiao.id=${zhaobiao1.id}">打印中标信息</a>

								<a
									href="DingdanAction!rukuchaxun.action?zhaobiao.id=${zhaobiao1.id}">打印入库执行单</a>

							</s:if>



							<s:if test='#zhaobiao1.status=="完成"'>
								<a
									href="zhaobiaoAction!tochakanhetong.action?zhaobiao.id=${zhaobiao1.id}">查看合同</a>
								<a
									href="zhaobiaoAction!dayinzhongbiaoxinxi.action?zhaobiao.id=${zhaobiao1.id}">打印中标信息</a>
								<a
									href="DingdanAction!rukuchaxun.action?zhaobiao.id=${zhaobiao1.id}">打印入库执行单</a>
							</s:if>

							<s:if test='#zhaobiao1.status=="审核中"'>
								<a
									href="zhaobiaoAction!chankantoubiaoXi.action?zhaobiao.id=${zhaobiao1.id}">查看投标记录</a>
								<a href="CircuitRunAction_findAduitPage.action?id=${zhaobiao1.epId}">审核</a>
								<a href="CircuitRunAction_findAduitPage.action?id=${zhaobiao1.epId}">查看审批记录</a>
							</s:if>
							<!--   <a href="CircuitRunAction_findAduitPage.action?id=102">审核</a> -->

							<s:if test='#zhaobiao1.status=="同意"||#zhaobiao1.status=="合同审批中"'>
								<a href="CircuitRunAction_findAduitPage.action?id=${zhaobiao1.epId}">查看审批记录</a>
							</s:if>
						</th>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<th colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<th colspan="11" align="center" style="color: red">
						</s:else>
						</th>
					</tr>

				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

function add() {
	var url = encodeURI(encodeURI("${pageContext.request.contextPath}/System/caigou/zhaobiao/addzhaobiao.jsp"));
	$("#showProcess").attr("src", url);
	chageDiv('block');
}

function toUpdatezhaobiao(id) {
	var url = encodeURI(encodeURI("zhaobiaoAction!toUpdatezhaobiao.action?zhaobiao.id="
			+ id));
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
</script>
	</body>
</html>
