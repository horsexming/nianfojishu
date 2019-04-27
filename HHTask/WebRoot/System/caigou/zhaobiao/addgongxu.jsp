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
					<a href="zhaobiaoAction!listAll.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="d1">
			   <form action="zhaobiaoAction!addZhgongxu.action" method="post"  theme="simple">
			   <table class="table" width="60%">
			       <tr>
							<th colspan="2" align="center">
								<font size="5px;">添加原材料</font>
							</th>
						</tr>
			       <tr><th align="right" width="50%">月份: </th>
			            <td><input class="Wdate" type="text" id="zhgongxu.yuefen"
									name="zhgongxu.yuefen"
									onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" /></td></tr>   
			   
				    <tr><th align="right">牌号: </th><td><input type="text" id="zhgongxu.paihao" name="zhgongxu.paihao" /></td> </tr>   
				    <tr><th align="right">规格: </th><td><input type="text" id="zhgongxu.guige" name="zhgongxu.guige" /></td> </tr>   
				    
				    <tr><th align="right">单位: </th><td>
				    					<SELECT name="zhgongxu.danwei" id="danwei">
				    					
								</SELECT>
				  </td></tr>
				    
				       <tr><th></th><td>
				       				<input type="radio" value="不含税"  name="t2"  checked="checked"  onclick="fun1('不含税');"/>不含税2
				       				<input type="radio" value="含税"  name="t2"  onclick="fun1('含税');"/>含税1
				       				</td></tr>
				    <tr>
				    <th align="right">价格: </th><td>  <input type="text" id="hanshui" name="zhgongxu.jiage"  style="display: none;" />
													<input type="text" id="buhanshui" name="zhgongxu.buhanshui" />			    
					    								</td> </tr> 
				     <tr><th align="right">税率: </th><td>
				    								<SELECT   name="zhgongxu.kongxian">
				    										 <option value="">请选择税率</option>
				    								        <option value="0.15">税率     15%</option>
				    										<option value="0.17">税率     17%</option>
				    										<option value="0.16">税率      16%</option>
				    										<option value="0.15">税率      15%</option>
				    										<option value="0.14">税率      14%</option>
				    										<option value="0.13">税率       13%</option>
				    										<option value="0.12">税率       12%</option>
				    										<option value="0.11">税率       11%</option>
				    										<option value="0.10">税率           10%</option>
				    								</SELECT>
				    		
				    		</td></tr>
					 <tr><td align="center" colspan="2"><s:submit value="保存" cssClass="input"/>
					 
					 
					      <input type="button" name="Submit2" value="取消"  class="input" onclick="window.history.go(-1);"/></td></tr>
			   </table>
		</form>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

	</body>
	<SCRIPT type="text/javascript">
	$(function(){
		getUnit("danwei");
	})
	 function  fun1(shuilv){
			var tr=document.getElementById("hanshui");
			var buhanshui=document.getElementById("buhanshui");
			if(shuilv=="含税") {
				tr.style.display="block";
				buhanshui.style.display="none";
			} else {
				tr.style.display="none";
				buhanshui.style.display="block";
			}
	 
	 }
	</SCRIPT>

</html>
