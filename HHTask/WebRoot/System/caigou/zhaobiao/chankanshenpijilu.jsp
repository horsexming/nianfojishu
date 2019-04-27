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
				<table class="table">
					<tr>
						<th>序号</th>
						<th>审批顺序</th>
						<th>审批人姓名</th>
						<th>审核状态</th>
						
						<th>审批时间</th>
						<th>审批意见</th>
					</tr>
					<s:iterator value="list" id="zhUser" status="pageIndex">
						<tr>
							<th>${pageIndex.index+1}</th>
							<th>${zhUser.cmp}</th>
							<th>${zhUser.note}</th>
							<th>${zhUser.cperson}</th>
							
							<th>${zhUser.ctel}</th>
							<th>${zhUser.blackliststauts}</th>
							<th><a href="javascript:showdetail('${zhUser.id}');">详细信息</a>
								<a href="zhaobiaoAction!toUpdatezhUser.action?zhUser.id=${zhUser.id}">修改</a>
		    			        <a href="zhaobiaoAction!deletezhUser.action?zhUser.id=${zhUser.id}">删除</a>
		    			         <a href="zhaobiaoAction!laheizhUser.action?zhUser.id=${zhUser.id}">拉黑</a>
		    			          <a href="zhaobiaoAction!laheizhUser.action?zhUser.id=${zhUser.id}">后期评定</a>
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
