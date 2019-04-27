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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<table class="table">
					<tr>
						<th>
							序号
						</th>
						<th>
							订单号
						</th>
						<th>
							件号
						</th>
						<th>
							业务件号
						</th>
						<th>
							总数量
						</th>
						<th>
							已关联数量
						</th>
						<th>
							剩余数量
						</th>
					</tr>
					<s:iterator value="pmList" id="pagepm" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<input type="checkbox" name="checkboxs"
								id="box<s:property value="#pageStatus.index" />"
								value="${pagepm.id}">
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td>
							${pagepm.orderNumber}
							<input type="hidden"
								id="orderNum<s:property value="#pageStatus.index" />"
								value="${pagepm.orderNumber}">
						</td>
						<td>
							${pagepm.pieceNumber}
						</td>
						<td>
							${pagepm.ywMarkId}
						</td>
						<td>
							${pagepm.num}
						</td>
						<td>
							<s:property value="#pagepm.cxCount" />
						</td>
						<td>
							<s:property
								value="#pagepm.num-#pagepm.cxCount-#pagepm.cxApplyCount" />
							<input type="hidden"
								id="num<s:property value="#pageStatus.index" />"
								value="<s:property value="#pagepm.num-#pagepm.cxCount-#pagepm.cxApplyCount"/>" />
						</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="7" align="center">
							<input type="button" style="width: 80px; height: 40px" value="关联"
								onclick="relateOrder();">
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function relateOrder(){
	var checkboxs = document.getElementsByName("checkboxs");
	var ids = "";
	var orderNums="";
	for ( var i = 0; i < checkboxs.length; i++) {
		if (checkboxs[i].checked == false) {
		} else {
			if(orderNums==""){
				ids+=checkboxs[i].value;
				orderNums+=$("#orderNum"+i).val()
			}else{
				ids+=","+checkboxs[i].value;
				orderNums+=","+$("#orderNum"+i).val()
			}
		}
	}
	if (checkboxs != null && ids == "") {
		alert("请选择要关联的订单序号!");
		return false;
	}
	var tag = "${tag}";
	var id = "${id}";
	if(tag!=null&&tag=="update"){alert();
		$.ajax( {
		type : "post",
		url : "orderManager_relateBh.action",
		dataType : "json",
		data : {
			id : id,
			ids :ids,
			orderNums 
		},
		success : function(data) {
			if(data.success){
				alert("关联成功!");
				parent.$("#relateShow${index}").html(orderNums+"<input type='hidden' name='pmList[${index}].pmids' value ='"+ids+"'><br/>");
				parent.chageDiv("none");
				parent.location.reload(true);
			}else{
				alert(data.message);
			}
		}
	});
	}else{
		parent.$("#relateShow${index}").html(orderNums+"<input type='hidden' name='pmList[${index}].pmids' value ='"+ids+"'><br/>");
				parent.chageDiv("none");
	}
	
}		
</SCRIPT>
	</body>
</html>
