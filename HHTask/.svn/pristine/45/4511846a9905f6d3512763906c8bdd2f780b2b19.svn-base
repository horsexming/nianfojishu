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
					<a href="System/SOP/qd/saveLogoSticker.jsp" style="color: #ffffff">添加</a>
				</div>
			</div>

			<div align="center">
				<form action="LogoStickerAction!findLogoSticker2.action?tag=${tag}"
					method="post">
					<table class="table">
						<tr>
							<td>
								编
								<span style="width: 28px;"></span>号：
								<input type="text" name="sticker.number" />
							</td>
							<td>
								批
								<span style="width: 14px;"></span>次：
								<input type="text" name="sticker.lotId" />
							</td>
							<td>
								件
								<span style="width: 28px;"></span>号：
								<input type="text" name="sticker.markId" />
							</td>
							<td rowspan="3" align="center">
								<input type="submit" style="width: 90px; height: 70px;"
									value="查询" />
							</td>
						</tr>
						<tr>
							<td>
								零件名称：
								<input type="text" name="sticker.partsName" />
							</td>
							<td>
								工
								<span style="width: 14px;"></span>序：
								<input type="text" name="sticker.processNO" />
							</td>
							<td>
								操
								<span style="width: 7px;"></span>作
								<span style="width: 7px;"></span>者：
								<input type="text" name="sticker.operator" />
							</td>
						</tr>
						<tr>
							<td>
								标识类别：
								<select name="sticker.stickStyle">
									<option value="">
										标识类别
									</option>
									<option value="首检样品">
										首检样品
									</option>
									<option value="报废品">
										报废品
									</option>
									<option value="待处理品">
										待处理品
									</option>
								</select>
							</td>
							<td>
								日期从：
								<input class="Wdate" type="text" name="startDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />

							</td>
							<td>
								<span style="width: 21px;"></span>到
								<span style="width: 21px;"></span>：
								<input class="Wdate" type="text" name="endDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<td>
								打印：
								<select name="sticker.isPrint">
									<option>
									</option>
									<option >
										YES
									</option>
									<option >
										NO
									</option>
								</select>
							</td>
							<td>
							</td>
							<td>
							</td>
						</tr>
						</form>
						<table class="table">
							<tr bgcolor="#c0dcf2" height="30px"
								style="border-collapse: separate;">
								<th align="center">
									序号
								</th>
								<th align="center">
									编号
								</th>
								<th align="center">
									件号
								</th>
								<th align="center">
									零件名
								</th>
								<th align="center">
									批次号
								</th>
								<th align="center">
									工序号
								</th>
								<th align="center">
									数量
								</th>
								<th align="center">
									材料件号
								</th>
								<th align="center">
									材料批次
								</th>
								<th align="center">
									日期
								</th>
								<th align="center">
									操作者
								</th>
								<th align="center">
									签名
								</th>
								<th align="center">
									标识类型
								</th>
								<th align="center">
									处理意见
								</th>
								<th align="center">
									打印状态
								</th>
								<th align="center">
									处理状态
								</th>
								<th align="center">
									操作
								</th>
							</tr>

							<s:if test="list.size()>0">
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
										${pageProcard.number}
									</td>
									<td>
										${pageProcard.markId}
									</td>
									<td>
										${pageProcard.partsName}
									</td>
									<td>
										${pageProcard.lotId}
									</td>
									<td>
										${pageProcard.processNO}
									</td>
									<td>
										${pageProcard.count}
									</td>
									<td>
										${pageProcard.clMarkId}
									</td>
									<td>
										${pageProcard.clSelfCard}
									</td>
									<td>
										${pageProcard.billDate}
									</td>
									<td>
										${pageProcard.operator}
									</td>
									<td>
										${pageProcard.examinerName}
									</td>
									<td>
										${pageProcard.stickStyle}
									</td>
									<td>
										${pageProcard.treatAdvice}
									</td>
									<td>
										${pageProcard.isPrint}
									</td>
									<td>
										${pageProcard.status}
									</td>
									<td>
										<a href="LogoStickerAction!findLogoStickerById.action?id=${id}&tag=">检验</a>/
										<a
											href="LogoStickerAction!findLogoStickerById.action?id=${id}&tag=print">打印</a>
										<a onClick="return confirm('确定要删除该条记录吗？')"
											href="LogoStickerAction!deleteLogoSticker.action?id=${id}">删除</a>
									</td>
									</tr>
								</s:iterator>
								<tr>
									<td colspan="20" align="right">
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
									<td style="font-size: 15px; color: red;" colspan="20">
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
	</body>
</html>
