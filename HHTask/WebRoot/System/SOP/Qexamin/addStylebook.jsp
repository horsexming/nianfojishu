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
		<div 
			style="width: 100%; ">
			<div align="center">
				<form action="QexamineAction!saveStylebook.action" method="post">
				 	<strong>客户名称:${qexamine.customername} &nbsp&nbsp&nbsp&nbsp&nbsp
				 			 产品名称${qexamine.productname}	&nbsp&nbsp&nbsp&nbsp&nbsp
				 			 抽样批次${qexamine.batchsampling} &nbsp&nbsp&nbsp&nbsp&nbsp
				 	</strong>
					<table class="table" style="width: 50%;">
						
				  	<tr>
							<td>
								抽取地点：成品库
							</td><td>
								<input type="radio" name="stylebook.place" value="是">是
								<input type="radio" name="stylebook.place" value="否">否
									 
							</td>
						</tr>
						<tr>
							<td>
								抽取方式：随机</td><td>
								<input type="radio" name="stylebook.method" value="是">是
								<input type="radio" name="stylebook.method" value="否">否
							</td>
						</tr>
						<tr>
							<td>
								工位器具根据技术要求合理放置，工位器具完好无损，零件在工位器具中牢固可靠不松动</td><td>
								<input type="radio" name="stylebook.packing1" value="是">是
								<input type="radio" name="stylebook.packing1" value="否">否
							</td>
						</tr>
						<tr>
							<td>
								文字标记符合技术要求，产品名称、图号、数量齐全、正确、清晰:</td><td>

								<input type="radio" name="stylebook.packing2" value="是">是
								<input type="radio" name="stylebook.packing2" value="否">否
							</td>
						</tr>
						<tr>
							<td>
								零件标识有零件号、厂商代号、生产日期等追溯性标识，零件上有合格证</td><td>
								<input type="radio" name="stylebook.packing3" value="是">是
								<input type="radio" name="stylebook.packing3" value="否">否
							</td>
						</tr>
						<tr>
							<td>
								隔热罩间隙6.9±0.5</td><td align="left" >
								<input  type="text" name="stylebook.sizeHeat1" size="8px"
									/>
							</td>
						</tr>
						<tr>
							<td>
								隔热罩间隙5±0.5</td><td align="left" >
								<input  type="text" name="stylebook.sizeHeat2" size="8px"
									 />
							</td>
						</tr>
						<tr>
							<td>
								检验销放置无间隙</td><td>
								<input type="radio" name="stylebook.checkx" value="是">是
								<input type="radio" name="stylebook.checkx" value="否">否
							</td>
						</tr>
						<tr>
							<td>
								方规随机放置无间隙</td><td>		
								<input type="radio" name="stylebook.decree" value="是">是
								<input type="radio" name="stylebook.decree" value="否">否
							</td>
						</tr>
						<tr>
							<td>
								内螺纹	</td><td>	
								<input type="radio" name="stylebook.tapped" value="是">是
								<input type="radio" name="stylebook.tapped" value="否">否
							</td>
						</tr>
						<tr>
							<td>
								总成长度符合检具	</td><td>
								<input type="radio" name="stylebook.totalgrowth" value="是">是
								<input type="radio" name="stylebook.totalgrowth" value="否">否
							</td>
						</tr>
						<tr>
							<td>
								管口尺寸	</td><td>
								<input type="radio" name="stylebook.orificesize" value="是">是
								<input type="radio" name="stylebook.orificesize" value="否">否
							</td>
						</tr>
						<tr>
							<td>
								走向符合总成检具		</td><td>
								<input type="radio" name="stylebook.trend" value="是">是
								<input type="radio" name="stylebook.trend" value="否">否
							</td>
						</tr>
						<tr>
							<td>
								零件外观清洁、不允许有裂纹、油污、锈蚀、不允许有明显划伤、
								压坑、标识真确正确完整清晰、且标有气密值，吊挂安装正确、无漏错装</td><td>		
								<input type="radio" name="stylebook.surface1" value="是" >是
								<input type="radio" name="stylebook.surface1" value="否">否
							</td>
						</tr>
						<tr>
							<td>
								焊缝平整，不允许有粗大焊瘤	</td><td>				
								<input type="radio" name="stylebook.surface2" value="是">是
								<input type="radio" name="stylebook.surface2" value="否">否
							</td>
						</tr>
						<tr>
							<td>
								不允许有裂纹、气孔		</td><td>
								<input type="radio" name="stylebook.surface3" value="是">是
								<input type="radio" name="stylebook.surface3" value="否">否
							</td>
						</tr>
						<tr>
							<td>
								不允许有焊漏、焊穿		</td><td>	
								<input type="radio" name="stylebook.surface4" value="是">是
								<input type="radio" name="stylebook.surface4" value="否">否
							</td>
						</tr> 
						<tr>
							<td>
								
								气密性压力=301KP泄漏量≦0.5L/min	</td><td>		
								<input type="radio" name="stylebook.sexfunction" value="是">是
								<input type="radio" name="stylebook.sexfunction" value="否">否
								
							 	<input type="hidden" name="stylebook.qid" value="${qexamine.id}">
								<input type="hidden" value="样本" name="stylebook.nature"> 
							</td>
						</tr>
						<tr>
							
						</tr>
						
						<tr>
							<td align="center" colspan="2">
								<input type="submit" value="添加"
									style="width: 80px; height: 50px;" />
								<input type="reset" value="重置"
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		
	<%@include file="/util/foot.jsp"%>
	</body>
</html>
	