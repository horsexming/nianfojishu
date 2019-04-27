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
					  <form action="queXianAction!listqueXianZong.action" method="post"  theme="simple">
			     <table class="table">
			   		<tr><th align="center">填报人：</th><td><input type="text" id="queXian.tianbaoren" name="queXian.tianbaoren" /></td>
			   			<th align="center">类型：</th><td><select name="queXian.leixing" id="queXian.leixing" >
			   									<option value="">--请选择--</option>
				    							<option value="人">人</option>
				                       			<option value="机">机</option>
				                       			<option value="法">法</option>
				                       			<option value="环">环</option>
				                       			<option value="料">料</option>
				                        </select>
			   			</td>
			   			<td rowspan="2"><input type="submit" value="查询"   class="input"/></td>
			   		</tr>
			   		<tr>
			   			<th align="center">发布时间：</th><td>
			   			
			   				<input class="Wdate" type="text" id="queXian.tianbaoshijian"
									name="queXian.tianbaoshijian"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
			   			</td>
			   			<th align="center">部门</th><td>
			   				<select   id='dept'  name="queXian.tianbaodept"  style="width:90px;">
			   			  </select>
			   			</td>
			   		</tr>
			  
			  </table>
			  </form>
			
			
			
				<table class="table">
					<tr  bgcolor="#c0dcf2">
						<th>序号</th>
						<th>类型</th>
						<th>描述</th>
						<th>备注</th>
						<th>填报人</th>
						<th>填报时间</th>
						<th>填报部门</th>
						<th>操作</th>
					</tr>
					<s:iterator value="list" id="zhUser1" status="pageIndex">
						<tr onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
							<th>${pageIndex.index+1}</th>
							<th>${zhUser1.leixing}</th>
							<th>${zhUser1.loc}</th>
							<th>${zhUser1.beizhu}</th>
							<th>${zhUser1.tianbaoren}</th>
							
							<th>${zhUser1.tianbaoshijian}</th>
							<th>${zhUser1.tianbaodept}</th>
							<th>
								<a href="queXianAction!toUpdatequeXianguanliyuan.action?queXian.id=${zhUser1.id}">修改</a>
		    			        <a href="queXianAction!deletequeXianguanliyuan.action?queXian.id=${zhUser1.id}">删除</a>
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
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

	</body>
	<SCRIPT type="text/javascript">
	    // $(function (){
	    	// if(list.length==0){
		    //	 $.post("zhaobiaoAction!listAll.action",{},function(msg){
		    		 //alert(msg);
		    		 
		    //		$("#d1").html(msg);
		    		
		   //   	})
	      //	}
	      //	window.location.href="<%=basePath%>zhaobiaoAction!listAll.action";
	     //})
	     function showdetail(id){
	    	//alert("1111");
			//var style="dialogWidth:45;dialogHeight:35;status:no;help:no"; 
			//window.showModalDialog("<%=basePath%>add.jsp","",style);
			//window.open ("zhaobiaoAction!listByIdZhUser.action?zhUser.id="+id, "newwindow", "height=600, width=600, top=400,left=300,toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");  
	        window.location.href="zhaobiaoAction!listByIdZhUser.action?zhUser.id="+id;
	    }
	</SCRIPT>

</html>
