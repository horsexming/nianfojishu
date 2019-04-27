
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

			<div align="center" id="d1">
				<div id="del_div">

				</div>
				<%--			<a href="zhaobiaoAction!XFWgType.action">只是用一次的修复物料类别功能</a>--%>
				<form action="zhaobiaoAction!listAll.action" method="post"
					theme="simple">
					<table class="table">
						<tr>
							<th align="center">
								所属公司：
							</th>
							<td>
								<input type="text" id="zhUser.cmp" name="zhUser.cmp"
									value="${zhUser.cmp}" />
							</td>
							<th align="center">
								联系人：
							</th>
							<td>
								<input type="text" id="zhUser.cperson" name="zhUser.cperson"
									value="${zhUser.cperson}" />
							</td>
							<th align="center">
								供应商编号：
							</th>
							<td>
								<input type="text" id="zhUser.usercode" name="zhUser.usercode"
									value="${zhUser.usercode}" />
							</td>
							<td rowspan="2">
								<input type="submit" value="查询" class="input" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" value="添加供应商" onclick="add()" class="input" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" value="导出" onclick="exportExcel(this.form);todisabledone(this)" data="downData"
									class="input" />
							</td>
						</tr>
						<tr>
							<th align="center">
								注册时间：
							</th>
							<td>
								<input class="Wdate" type="text" id="zhUser.time"
									name="zhUser.time"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
									value="${zhUser.time}" />
							</td>
							<th align="center">
								公司地址：
							</th>
							<td>
								<input type="text" id="zhUser.companydz" name="zhUser.companydz"
									value="${zhUser.companydz}" />
							</td>
							<th align="center">
								物料类别：
							</th>
							<td>
								<input type="text" id="zhUser.cclass" name="zhUser.cclass"
									value="${zhUser.cclass}" />
							</td>
						</tr>
					</table>
				</form>
				<form action="zhaobiaoAction!addzhUser.action" method="post"
					enctype="multipart/form-data">
					<table>
						<tr>
							<th>
								客户批量导入:
							</th>
							<th>
								<input type="file" name="addzhUser" id="file">
							</th>
							<td>
								<input type="submit" value="批量导入">
								<a href="">导入模版下载</a>
								<a href="<%=basePath%>/upload/file/download/zhUserTemplate.xls">导入模版下载</a>
								<a
									href="FileViewAction.action?FilePath=/upload/file/download/zhUserTemplate.xls&Refresh=true">/预览</a>
							</td>
						</tr>
					</table>

				</form>


				<%--								  <a href="zhaobiaoAction!toaddUser.action">添加</a>--%>
				<table class="table">
					<tr bgcolor="#c0dcf2">
						<th>
							序号
						</th>
						<th>
							编号
						</th>
						<th>
							所属公司
						</th>
						<th>
							生产商品
						</th>
						<th>
							产品类别
						</th>
						<th>
							联系人
						</th>

						<th>
							固定电话
						</th>
						<th>
							目前情况
						</th>
						<th>
							ASL
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="list" id="zhUser1" status="pageIndex">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
							<th>
								${pageIndex.index+1}
							</th>
							<th>
								${zhUser1.usercode}
							</th>
							<th>
								${zhUser1.cmp}
							</th>
							<th>
								${zhUser1.note}
							</th>
							<th>
								${zhUser1.cclass}
							</th>
							<th>
								${zhUser1.cperson}
							</th>

							<th>
								${zhUser1.ctel}
							</th>
							<th>
								${zhUser1.blackliststauts}
							</th>
							<th>
								${zhUser1.asl}
							</th>
							<th>
								<a
									href="javascript:delete1('${zhUser1.id}','${cpage}','${total}');">删除</a>

								<a
									href="zhaobiaoAction!laheizhUser.action?zhUser.id=${zhUser1.id}"
									onclick="return confirm('确认拉黑操作?');">拉黑</a>

								<a href="javascript:showdetail('${zhUser1.id}');">详细信息</a>
								<a
									href="zhaobiaoAction!toUpdatezhUser.action?zhUser.id=${zhUser1.id}">修改</a>
								<%--								<a--%>
								<%--									href="zhaobiaoAction!qubangding.action?zhUser.id=${zhUser1.id}">绑定外购件件号</a>--%>
								<%----%>
								<%--								<a href="markIdAction!listBom.action?zhUser.id=${zhUser1.id}">绑定外委工序</a>--%>
								<a
									href="zhaobiaoAction!screenSupplierManagement.action?zhUser.id=${zhUser1.id}"
									target="_blank">综合分析</a>
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
	   	function add(){
	   		window.open("zhaobiaoAction!toaddUser.action");
	   	}
	 function check(){
		 var file= document.getElementById("file");
		 alert(file.value)
		 
		 return true;
	 }
	 function exportExcel(obj){//zhaobiaoAction!listAll.action
		obj.action = "zhaobiaoAction!exportExcel.action";
	 	obj.submit();
	  	obj.action = "zhaobiaoAction!listAll.action";
	 	}
	 
	 
function delete1(id,cpage,total){
	if(confirm('确定要删除吗？')){
	$.ajax( {
		type : "POST",
		url : "zhaobiaoAction!deletezhUser.action?",
		data : {
			'zhUser.id':id
		},
		dataType : "json",
		success : function(data) {
			if (data == "删除成功！") {
				alert(data);
				window.location.href = "zhaobiaoAction!listAll.action?cpage=${cpage}&total=${total}";
			}else{
				alert(data);
				//$("#del_div").html(data);
			}
		}
	})
	}
}
	</SCRIPT>

</html>
