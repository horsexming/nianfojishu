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
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
						<a
						href="System/SOP/qd/saveLogoSticker.jsp"
						style="color: #ffffff">添加</a>
				</div>
			</div>
			
			<div align="center" style="width:95%;">
				<form action="LogoStickerAction!findSum.action" method="post">
					<table class="table" style="width:85%;">
						<tr>
							<td>
								班组编号：
								<select name="sticker.workingGroup" id="working" style="width: 140px;" onclick="createDept('working','LogoStickerAction!selectItem.action?tag=workingGroup')">
								<option value=""></option>
								<option value="${sticker.workingGroup}" selected="selected">${sticker.workingGroup}</option>
								</select>
								
							</td>
							<td>
								批<span style="width:14px;"></span>次：
								<select name="sticker.lotId" id="lotId" style="width: 140px;" onclick="createDept('lotId','LogoStickerAction!selectItem.action?tag=lotId')">
								<option value=""></option>
								<option value="${sticker.lotId}" selected="selected">${sticker.lotId}</option>
								</select>
							</td>
							<td>
								件<span style="width:28px;"></span>号：
								<select name="sticker.markId" id="markId" style="width: 140px;" onclick="createDept('markId','LogoStickerAction!selectItem.action?tag=markId')">
								<option value=""></option>
								<option value="${sticker.markId}" selected="selected">${sticker.markId}</option>
								</select>
							</td>
							<td rowspan="3" align="center">
								<input type="submit" style="width:50px;height:70px;" value="查询" />		&nbsp;
								<input type="button" value="导出" style="width: 50px; height: 70px;" onclick="exportExcel(this.form);todisabledone(this)" data="downData" />						
							</td>
						</tr>
						<tr>
							<td>
								零件名称：
								<select name="sticker.partsName" id="partsName" style="width: 140px;" onclick="createDept('partsName','LogoStickerAction!selectItem.action?tag=partsName')">
								<option value=""></option>
								<option value="${sticker.partsName}" selected="selected">${sticker.partsName}</option>
								</select>
							</td>
							<td>
								工<span style="width:14px;"></span>序：
								<select name="sticker.processNO" id="processNO" style="width: 140px;" onclick="createDept('processNO','LogoStickerAction!selectItem.action?tag=processNO')">
								<option value=""></option>
								<option value="${sticker.processNO}" selected="selected">${sticker.processNO}</option>
								</select>
							</td>
							<td>
								操<span style="width:7px;"></span>作<span style="width:7px;"></span>者：
								<select name="sticker.operator" id="operator" style="width: 140px;" onclick="createDept('operator','LogoStickerAction!selectItem.action?tag=operator')">
								<option value=""></option>
								<option value="${sticker.operator}" selected="selected">${sticker.operator}</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								标识类别：
								<select name="sticker.stickStyle" >
								    <option value="" >标识类别</option>
								    <option value="${sticker.stickStyle}" selected="selected">${sticker.stickStyle}</option>
								    <option value="首检样品">首检样品</option>
									<option value="报废品">报废品</option>
									<option value="待处理品">待处理品</option>									
								</select>
							</td>
							<td>
								日期从：
								<input class="Wdate" type="text" name="startDate" value="${ startDate}" size="15" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"/>
								
							</td>
							<td>
								<span style="width:21px;"></span>到<span style="width:21px;"></span>：
								<input class="Wdate" type="text" name="endDate" value="${ endDate}" size="15" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"/>
							</td>
							

						</tr>
				</form>
				<table class="table" style="width:85%;">
					<tr bgcolor="#c0dcf2" height="30px"
							style="border-collapse: separate;">
						<th align="center">
							序号
						</th>
						<th align="center">
							班组编号
						</th>
						<th align="center">
							类型
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							汇总数量
						</th>
						
					</tr>
					
					<s:if test="{list.size()>0}">
						<s:iterator value="list" status="se" id="pageProcard">
							<s:if test="#se.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#se.index+1" />
							</td>
							<td>
								${pageProcard[1]}
							</td>
							<td>
								${pageProcard[2]}
							</td>
							<td>
								${pageProcard[3]}
							</td>
							<td>
								${pageProcard[0]}
							</td>
							
							</tr>
						</s:iterator>
						<tr>
							<td colspan="5" align="right">			
								共
								<s:property value="total" />
								页 第
								<s:property value="cpage" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />

							</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td style="font-size: 15px; color: red;">
								对不起，没有查到相关的标识贴信息
							</td>
						</tr>
					</s:else>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		function exportExcel(objForm) {	
			objForm.action="LogoStickerAction!export.action";
			objForm.submit();	
		}
		</script>
		
	</body>
</html>
