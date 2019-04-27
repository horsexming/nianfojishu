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
				<form action="goodsAction!ylApplyShow.action" method="post">
				<input type="hidden" name="role" value="${role}">
					<table class="table" style="width: 95%;">
						<tr>
						   <th>
								来源件号:
							</th>
							<td>
								<input type="text" name="yuLiaoApply.ylMarkId" />
							</td>
							<th>
								来源批次:
							</th>
							<td>
								<input type="text" name="yuLiaoApply.ylSelfCard"  />
							</td>
							</tr>
						<tr>
							<th>
								牌号:
							</th>
							<td>
								<input type="text" name="yuLiaoApply.goodsMarkId"  />
							</td>
							<th>
							规格：
						</th>
						<td>
							<input type="text" name="yuLiaoApply.goodsFormat"  />
						</td>
						</tr>
						<tr>
						</tr>
						<tr>
						<th>
								新牌号:
							</th>
							<td>
								<input type="text" name="yuLiaoApply.newGoodsMarkId"  />
							</td>
						<th>
								新规格:
							</th>
							<td>
								<input type="text" name="yuLiaoApply.newGoodsFormat"  />
							</td>
							</tr>
						<tr>
						<th>
								状态:
							</th>
							<td>
								<SELECT name="yuLiaoApply.status">
								 <option></option>
								 <option value="可用">可用</option>
								 <option value="作废">作废</option>
								</SELECT>
							</td>
						<th>
								审批状态:
							</th>
							<td>
								<SELECT name="yuLiaoApply.applyStatus">
								 <option></option>
								 <option value="审批中">审批中</option>
								 <option value="同意">同意</option>
								 <option value="打回">打回</option>
								</SELECT>
							</td>
						</tr>
						<tr>
						<th>
								申请类型:
							</th>
							<td>
								<SELECT name="yuLiaoApply.applyType">
								 <option></option>
								 <option value="报废">报废</option>
								 <option value="转件号">转件号</option>
								</SELECT>
							</td>
						<th>
							</th>
							<td>
							</td>
						</tr>
						<tr><td colspan="4" align="center"><input type="submit" value="查询" style="width: 80px;height: 60px"> <input type="reset" value="重置" style="width: 80px;height: 60px"> </td></tr>
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
							规格
						</th>
						<th align="center">
							数量
						</th>
						<th align="center">
							单位
						</th>
						<th align="center">
							新牌号
						</th>
						<th align="center">
							新规格
						</th>
						<th align="center">
							新数量
						</th>
						<th align="center">
							新单位
						</th>
						<th align="center">
							状态
						</th>
						<th align="center">
							申请类型
						</th>
						<th align="center">
							审批状态
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:if test="yuLiaoApplyList.size()>0">
						<s:iterator value="yuLiaoApplyList" status="see" id="ylAppyly">
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
						<td align="center">
						<s:property value="#ylAppyly.ylMarkId"/>
						</td>
						<td align="center">
						<s:property value="#ylAppyly.ylSelfCard"/>
						</td>
						<td align="center">
						<s:property value="#ylAppyly.goodsMarkId"/>
						</td>
						<td align="center">
						<s:property value="#ylAppyly.goodsFormat"/>
						</td>
						<td align="center">
						<s:property value="#ylAppyly.applyCount"/>
						</td>
						<td align="center">
						<s:property value="#ylAppyly.goodsUnit"/>
						</td>
						<td align="center">
						<s:property value="#ylAppyly.newGoodsMarkId"/>
						</td>
						<td align="center">
						<s:property value="#ylAppyly.newGoodsFormat"/>
						</td>
						<td align="center">
						<s:property value="#ylAppyly.changeCount"/>
						</td>
						<td align="center">
						<s:property value="#ylAppyly.newGoodsUnit"/>
						</td>
						<td align="center">
						<s:if test="#ylAppyly.status=='作废'">
						 <font color="red"><s:property value="#ylAppyly.status"/></font>
						</s:if>
						<s:else>
						<s:property value="#ylAppyly.status"/>
						</s:else>
						</td>
						<td align="center">
						<s:property value="#ylAppyly.applyType"/>
						</td>
						<td align="center">
						<s:property value="#ylAppyly.applyStatus"/>
						</td>
						<td align="center">
							<s:if test="role=='apply'&&#ylAppyly.applyStatus=='审批中'">
							<a onclick="apply('agree',${ylAppyly.id})">同意</a>
							<a onclick="apply('back',${ylAppyly.id})">打回</a>
							</s:if>
							<s:if test="role=='single'||role=='all'">
							<a onclick="todelete(${ylAppyly.id})">删除</a>
							</s:if>
						</td>
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
							<td colspan="14" style="font-size: 15px; color: red;">
								对不起，没有查到相关的申请信息
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
		function apply(type,id){
			if(type=="agree"){
				if(window.confirm("您将同意此申请!")){
					window.location.href="goodsAction!applyYl.action?id="+id+"&tag="+type;
				}
			}else{
				if(window.confirm("您将打回此申请!")){
						window.location.href="goodsAction!applyYl.action?id="+id+"&tag="+type;
				}
			}
		}
		function todelete(id){
			if(window.confirm("您将删除此申请!")){
						window.location.href="goodsAction!deleteYlApply.action?id="+id;
				}
		}
</script>
	</body>
</html>
