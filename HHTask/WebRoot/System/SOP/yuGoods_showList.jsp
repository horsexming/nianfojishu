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
		<div id="gongneng">

			<div align="center">
				<h2>
					库存管理
				</h2>
				<form action="goodsAction!ylShowList.action" method="post">
					<table class="table" style="width: 95%;">
						<tr>
						   <th>
								来源件号:
							</th>
							<td>
								<input type="text" name="goods.ylMarkId"
									value="${goods.ylMarkId}" />
							</td>
							<th>
								来源批次:
							</th>
							<td>
								<input type="text" name="goods.ylSelfCard"
									value="${goods.ylSelfCard}" />
							</td>
							<th>
								牌号:
							</th>
							<td>
								<input type="text" name="goods.goodsMarkId"
									value="${goods.goodsMarkId }" />
							</td>
							<th rowspan="4">
								<input type="submit" value="查找"
									style="width: 50px; height: 30px；; margin-top: 5px;" />
								&nbsp;

							</th>
						</tr>
						<tr>
						<th>
								品名:
							</th>
							<td>
								<input type="text" name="goods.goodsFullName"
									value="${goods.goodsFullName }" />
							</td>
							<th>
							锁定件号
						</th>
						<td>
							<select name="goods.lock">
								<option></option>
								<option value="no">
									否
								</option>
								<option value="yes">
									是
								</option>
							</select>
						</td>
							<th>
								库位:
							</th>
							<td>
								<input type="text" name="goods.goodsPosition"
									value="${goods.goodsPosition}" />
							</td>
						</tr>
						<tr>
							<th>
								工艺卡号:
							</th>
							<td>
								<input type="text" name="goods.goodsArtsCard"
									value="${goods.goodsArtsCard }" />
							</td>
							<th>
								日期从
							</th>
							<td>
								<input class="Wdate" type="text" name="startDate"
									value="${startDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th>
								到
							</th>
							<td>
								<input class="Wdate" type="text" name="endDate"
									value="${ endDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
						<th>
								库别：
							</th>
							<td>
								<select name="goods.goodsClass" id="goodsClass"
									onMouseOver="createDept('goodsClass','goodsAction!findSelectList.action?tag=goodsClass')">
									<option value="">
										选择库别
									</option>
									<option value="${goods.goodsClass }" selected="selected">
										${goods.goodsClass }
									</option>
								</select>
							</td>
						<th>
							规格：
						</th>
						<td>
							<input type="text" name="goods.goodsFormat"
								value="${goods.goodsFormat }" />
						</td>
						<th>
							入库类型
						</th>
						<td>
							<select name="goods.goodsStyle">
								<option></option>
								<option>
									${goods.goodsStyle}
								</option>
								<option>
									批量入库
								</option>
								<option>
									试制入库
								</option>
								<option>
									返修入库
								</option>
								<option>
									退货入库
								</option>
							</select>
						</td>
						</tr>
					</table>
				</form>
				<table class="table" style="width: 95%;">
					<tr bgcolor="#c0dcf2" height="30px"
						style="border-collapse: separate;">
						<th align="center">
							序号
						</th>
						<th align="center">
							来源件号
						</th>
						<th align="center">
							来源批次
						</th>
						<th align="center">
							牌号
						</th>
						<th align="center">
							品名
						</th>
						<th align="center">
							规格
						</th>
						<th align="center">
							数量
						</th>
						<th align="center">
							申请中数量
						</th>
						<th align="center">
							单位
						</th>
						<th align="center">
							库别
						</th>
						<th align="center">
							库位
						</th>
						<th align="center">
							计划单号
						</th>
						<th align="center">
							入库时间
						</th>
						<th align="center">
							入库类型
						</th>
						<th align="center">
							锁定件号
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:if test="{list.size()>0}">
						<s:iterator value="list" status="see" id="gs">
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
								${gs.ylMarkId}
							</td>
							<td>
								${gs.ylSelfCard}
							</td>
							<td>
								${gs.goodsMarkId}
							</td>
							<td>
								${gs.goodsFullName}
							</td>
							<td>
								${gs.goodsFormat}
							</td>
							<td>
								${gs.goodsCurQuantity}
							</td>
							<th align="center">
								<s:if test="gs.ylApplyCount==null">
									0
								</s:if>
								<s:else>
									${gs.ylApplyCount}
								</s:else>
						</th>
							<td>
								${gs.goodsUnit}
							</td>
							<td>
								${gs.goodsClass}
							</td>
							<td>
								${gs.goodsPosition}
							</td>
							<td>
								${gs.goodsArtsCard}
							</td>
							<td>
								${gs.goodsChangeTime}
							</td>
							<td>
								${gs.goodsStyle}
							</td>
							<td>
							 <s:if test="'yes'==#gs.lock">
							     是
							 </s:if>
							 <s:else>否
							 </s:else>
							</td>
							<td>
							<s:if test="#gs.goodsCurQuantity>#gs.ylApplyCount">
								<s:if test="true==#gs.bedit||true==#gs.bout">
									<a href="goodsAction!toBaoFei.action?id=${gs.goodsId}">报废</a>
									<a
										href="goodsAction!toChangeYcl.action?id=${goodsId}&tag=update">转原材料</a>
								</s:if>
							</s:if>
								
							 <a href="goodsAction!deletegs.action?goods.goodsId=${gs.goodsId}" onclick="return confirm('确认删除操作?次啊操作将会删除相关所有记录');">删除</a>
							</td>

							</tr>
						</s:iterator>
						<tr>
							<td colspan="15" align="right">
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
							<td colspan="15" style="font-size: 15px; color: red;">
								对不起，没有查到相关的库存信息
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
		
</script>
	</body>
</html>
