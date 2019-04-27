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
	<body onload="getDept('dept');">
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
				<table class="table" style="width: 100%">
					<tr  bgcolor="#c0dcf2">
						<th>
							序号
						</th>
						<th>
							投标公司
						</th>
						<th>
							核算价格
						</th>
						<th>
							联系方式
						</th>
						<th>
							负责人
						</th>
						<th>
							含税单价
						</th>
						
						<th>
							款到发货
						</th>
						<th>
							货到付款		
						</th>
						
						
						
						<th>
							备注
						</th>
						<th>
							操作
						</th>
						<th>
							状态
						</th>
					</tr>
					<s:iterator value="list" id="pagezhtoubiao" status="pageIndex">
						<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
							<td>
								${pageIndex.index+1}
							</td>
							<td>
								${pagezhtoubiao.tname}
							</td>
							<td>
								${pagezhtoubiao.tkong2}
							</td>
							<td>
								${pagezhtoubiao.tkong4}
							</td>
							<td>
								${pagezhtoubiao.tkong5}
							</td>
							<td>
								${pagezhtoubiao.tkong1}
							</td>
							<td align="left">
								比例:${pagezhtoubiao.shoubikuan}</br>
								首笔周期:${pagezhtoubiao.zhouqikuan}
							</td>
							<td align="left">
								比例:${pagezhtoubiao.shoubikuan}首笔周期:${pagezhtoubiao.zhouqikuan}</br>
								第二笔比例:${pagezhtoubiao.erbihuo}第二笔周期:${pagezhtoubiao.zhouqier}</br>
								末笔比例:${pagezhtoubiao.mobihuo}末笔周期:${pagezhtoubiao.mobiuo}
							</td>
							<td>
								${pagezhtoubiao.tshuliang}   
								<input type="hidden" id="tkong10" value="${pagezhtoubiao.tkong10}" />
							</td>
							<td>
							<!-- 
								<a href="zhaobiaoAction!chakanjine.action?zhtoubiao.tid=${pagezhtoubiao.tid}">查看投标金额</a>
							 -->
							<s:if test="#pagezhtoubiao.tmoney!=''">
<%--							 	<a href='DownAction.action?fileName=${pagezhtoubiao.tmoney}&directory=/upload/zhaobiao/file/'>查看附件</a>--%>
							 	<a href='FileViewAction.action?FilePath=/upload/zhaobiao/file/${pagezhtoubiao.tmoney}'>查看附件</a>
							</s:if>
							
							</td>
							<td><s:if test='#pagezhtoubiao.tkong7=="Y"'>中标</s:if>
								<s:if test='#pagezhtoubiao.tkong7=="N"'>未中标</s:if>
							</td>
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
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		//价格
		function paixujiage(){
		   var tkong10=document.getElementById("tkong10").value;
		   window.location.href="zhaobiaoAction!listpaixujiage.action?zhtoubiao.tkong10="+tkong10;
		}
		
		function paixugonghuolv(){
		   var tkong10=document.getElementById("tkong10").value;
		   window.location.href="zhaobiaoAction!listpaixugonghuolv.action?zhtoubiao.tkong10="+tkong10;
		}
		function paixuzhiliang(){
			var tkong10=document.getElementById("tkong10").value;
		    window.location.href="zhaobiaoAction!listpaixuzhiliang.action?zhtoubiao.tkong10="+tkong10;
	
		}  
			function paixuzonghe(){
		   var tkong10=document.getElementById("tkong10").value;
		   window.location.href="zhaobiaoAction!listpaixuzonghe.action?zhtoubiao.tkong10="+tkong10;
		}
	</SCRIPT>
	</body>
</html>
