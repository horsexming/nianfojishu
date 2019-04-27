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
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 90%; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">产品明细与维护</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcardDetail" src="" marginwidth="0"
						marginheight="0" hspace="0" vspace="0" frameborder="0"
						scrolling="yes"
						style="width: 98%; height: 900px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>申请版本升级<h3/>
			</div>
			<div align="center">
			<form action="procardTemplateGyAction_applyUpdateBan.action" method="post" onsubmit="return validate();">
			 <table class="table">
			  <tr>
			   <th align="center">序号</th>
			   <th align="center">件号</th>
			   <th align="center">名称</th>
			   <th align="center">模板类型</th>
			   <th align="center">生产类型</th>
			   <th align="center">版本号</th>
			   <th align="center">新版本号</th>
			   <th align="center">操作</th>
			  </tr>
			  <s:iterator value="procardTemplateList" id="pageProcardTem"
							status="pageindex">
							<s:if test="#pageindex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td align="center">
								<s:property value="#pageindex.index+1" />
							</td>
							<td align="center">
								<a href="javascript:;"
									onclick="toshowPro('${pageProcardTem.id}')">
									${pageProcardTem.markId} </a>
							</td>
							<td align="center" >
								${pageProcardTem.proName}
							</td>
							<td align="center">
								${pageProcardTem.procardStyle}
							</td>
							<td align="center">
								${pageProcardTem.productStyle}
							</td>
							<td align="center">
								${pageProcardTem.banBenNumber}
							</td>
							<td align="center">
								<input id="newBanBan<s:property value="#pageindex.index" />" name="procardTemplateList[<s:property value="#pageindex.index" />].newBanBenNumber"/>
								<input type="hidden" name="procardTemplateList[<s:property value="#pageindex.index" />].id" value="${pageProcardTem.id}"/>
							</td>
							<td align="center">
							<input type="button" value="取消" style="width: 60px;height: 20px" onclick="quxiao(${pageProcardTem.id})"/>
<%--								<a target="_showbb"--%>
<%--									href="<%=basePath%>System/SOP/produce/Template_banben.jsp?id=${pageProcardTem.rootId}">版本管理</a>--%>
							</td>
							</tr>
						</s:iterator>
						<s:if test="procardTemplateList.size()>0">
						<tr>
						<th>备注</th>
						 <td align="center" colspan="7"><textarea rows="4" cols="70" name="remark"></textarea> </td>
						</tr>
						<tr>
						 <td align="center" colspan="8"><input type="submit" value="申请" class="input"> </td>
						</tr>
						</s:if>
			 </table>
			 </form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function toshowPro(id) {
	$("#showProcardDetail").attr("src",
			"ProcardTemplateAction!findCardTemForShow.action?id=" + id);
	chageDiv('block');
}
function quxiao(id){
	var treeObj = parent.$.fn.zTree.getZTreeObj("treeDemo");
    var node = treeObj.getNodeByParam("id", id, null);
    node.checked=false;
    treeObj.updateNode(node);
    var ids=parent.ids;
    if(ids.length==0){
			ids=""+id;
		}else{
		    var idlist = ids.split(",");
		    var add=true;
		    for(var i=idlist.length-1;i>=0;i--){
		    	if((idlist[i]-id)==0){
		    		add=false;
		    		idlist[i]=-1;
		    		break;
		    	}
		    }
		    ids=0;
		    for(var j=0;j<idlist.length;j++){
		    	if(idlist[j]>0){
		    		ids=ids+","+idlist[j];
		    	}
		    }
		    if(add){
		    	ids=ids+","+id;
		    }
	}
    parent.ids=ids+"";
    window.location.href="procardTemplateGyAction_showToUpdatebanben.action?ids="+ids;
}
function validate(){
	if(window.confirm("是否确认提交申请!")){
		var size = "<s:property value='procardTemplateList.size()' />";
		for(var i=0;i<size;i++){
			var newbanben = $("#newBanBan"+i).val();
			if(newbanben==null||newbanben==""){
				alert("第"+(i+1)+"行没有填写新版本号,请填写!");
				return false;
			}
		}
	}
}
</script>
	</body>
</html>
