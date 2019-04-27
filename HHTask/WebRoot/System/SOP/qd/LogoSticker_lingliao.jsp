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
				</div>
			</div>
			
			<div align="center">
				<form action="LogoStickerAction!findLingliaoSticker.action"
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
							<td rowspan="2" align="center">
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
									数量
								</th>
								<th align="center">
									工序号
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
									状态
								</th>
								<th align="center">
									打印状态
								</th>
								<th align="center">
									操作
								</th>
							</tr>

							<s:if test="{listPrint.size()>0}">
								<tr>
									<th colspan="13" bgcolor="#FFB6C1"
										style="color: red；font-weight :     bold;">
										<s:if test="%{'exam'==tag}">待处理的报废品任务</s:if>
										<s:else>待打印的领料单任务</s:else>
									</th>
								</tr>
								<s:iterator value="listPrint" status="se" id="printL">
									<tr align="center" bgcolor="#FFB6C1"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#FFB6C1')">


										<td>
											<s:property value="#se.index+1" />
										</td>
										<td>
											${printL.number}
										</td>
										<td>
											${printL.markId}
										</td>
										<td>
											${printL.partsName}
										</td>
										<td>
											${printL.lotId}
										</td>
										<td>
											${printL.count}
										</td>
										<td>
											${printL.processNO}
										</td>
										<td>
											${printL.billDate}
										</td>
										<td>
											${printL.operator}
										</td>
										<td>
											${printL.examinerName}
										</td>
										<td>
											${printL.status}
										</td>

										<td>
											${printL.isPrint}
										</td>
										<td>
											<s:if test="%{'exam'==tag}">
												<a
													href="ProcardAction!findProcardForzj.action?id=${printL.id}">处理</a>
											</s:if>
											<s:else>
												/<a
													href="LogoStickerAction!findLogoStickerById.action?id=${printL.id}&tag=print">打印</a>&nbsp;&nbsp;&nbsp;
											</s:else>
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td align="center" colspan="13"
										style="font-size: 15px; color: red;">
										<s:if test="%{'exam'==tag}">没有需要处理的报废领料单信息</s:if>
										<s:else>没有需要打印的领料单信息</s:else>
									</td>
								</tr>
							</s:else>
							<s:if test="{list.size()>0}">
								<tr bgcolor="#9BCD9B">
									<td colspan="13" align="center">
										<s:if test="%{'exam'==tag}">已处理的领料单历史记录</s:if>
										<s:else>已打印的领料单历史记录信息</s:else>
									</td>
								</tr>
								<s:iterator value="list" status="see" id="pageProcard">
									<s:if test="#see.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
									<td>
										<s:property value="#see.index+1" />
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
										${pageProcard.count}
									</td>
									<td>
										${pageProcard.processNO}
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
										${pageProcard.status}
									</td>

									<td>
										${pageProcard.isPrint}
									</td>
									<td>
										&nbsp;

									</td>
									</tr>
								</s:iterator>
								<tr>
									<td colspan="13" align="right">
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
									<td align="center" colspan="13"
										style="font-size: 15px; color: gray;">
										<s:if test="%{'exam'==tag}">没有相关的已处理的领料单历史记录</s:if>
										<s:else>对不起，没有相关的领料单打印历史记录信息</s:else>
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
