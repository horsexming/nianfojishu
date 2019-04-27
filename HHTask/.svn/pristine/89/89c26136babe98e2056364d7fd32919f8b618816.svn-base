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
	<body  >
		<center>
			<form action="DingdanAction!xuanzhejihua.action" method="post"
				theme="simple">
				<table class="table">
					<tr bgcolor="#c0dcf2">
					<td>
							选择:
					
		<input type="checkbox" name="test" value="" onclick="if(this.checked==true) { checkAll('selected'); } else { clearAll('selected'); }" />
				全选
						<td>
							编号:
						</td>
						<td>
							物料名称:
						</td>
						<td>
							件号:
						</td>
						
						<td>
							总数量:
						</td>
						<td>
							已购买数量:
						</td>
						<td>
							单位:
						</td>
						<td>
							规格要求:
						</td>
					</tr>

					<s:iterator value="list" id="pageList" status="pageStatus">
						<tr >
						<td>
						   <input type="checkbox" name="selected"
											value="${pageList.id}" />
						</td>
							<td>
							   ${pageStatus.index+1}
							</td>
							<td>
							   ${pageList.name}
							</td>
							
							<td>
							 <!--物料名称 -->
								<s:if test='#pageList.paihao==null'>
									<input type="text" id="zhaobiaoXis.t61" name="zhaobiaoXis.t6"
										value="外购件:${pageList.markId}" readonly="readonly"/>
								</s:if>
								<s:else>
									<input type="text" id="zhaobiaoXis.t62" name="zhaobiaoXis.t6"
										value="材料:${pageList.paihao}" />
								</s:else>
							</td>
							
							<td>
							 <!--总数量 -->
								<input type="text" id="shuliang${pageStatus.index}" name="shuliang"
									value="${pageList.shuliang}"  style="width: 50px;"/>&nbsp;&nbsp;
							</td>
							<td>
							 <!--已转化数量数量 -->
								<input type="text" id="shuliang${pageStatus.index}" name="yijin"
									value="${pageList.yijin}"  style="width: 50px;"/>&nbsp;&nbsp;
							</td>

							<td>
							 <!--单位-->
								<input type="text" id="zhaobiaoXis.t3" name="zhaobiaoXis.t3"
									value="${pageList.danwei}" />
							</td>
							<td>
							 <!--规格要求 -->
								<input type="text" id="zhaobiaoXis.t3" name="zhaobiaoXis.t3"
									value="${pageList.guige}" />
							
							</td>
						</tr>
					</s:iterator>
						<tr><td colspan="19">
					全选
		<input type="checkbox" name="test" value="" onclick="if(this.checked==true) { checkAll('selected'); } else { clearAll('selected'); }" />
					<tr>
					<tr>
						<td colspan="10" align="center">

							<input type="submit" value="保存" class="input" />
							<input type="button" name="Submit2" value="取消" class="input"
								class="right-buttons" onclick="window.history.go(-1);" />
						</td>
					</tr>
				</table>
				</form>
		</center>
	</body>
	<script type="text/javascript">
function vali() {
	var nums = document.getElementsByName("selected");
	for ( var i = 0; i < nums.length; i++) {
		if (nums[i].checked) {
			return true;
		}
	}
	alert("请选择需要采购的原材料！谢谢");
	return false;
}


function checkAll(name) 
{ 
    var el = document.getElementsByTagName('input');
    <!--取document中的所以的input，比如文本输入框，按钮啊什么的元件全都取出来存入数组，可以用el.length取数量，el[i]取内容
-->
    var len = el.length; 
    for(var i=0; i<len; i++) 
    { 
        if((el[i].type=="checkbox") && (el[i].name==name)) 
        { 
              el[i].checked = true; 
        } 
    } 
} 
function clearAll(name) 
{ 
    var el = document.getElementsByTagName('input'); 
    var len = el.length; 
    for(var i=0; i<len; i++) 
    { 
        if((el[i].type=="checkbox") && (el[i].name==name)) 
        { 
              el[i].checked = false; 
        } 
    } 
} 
//--> 

</script>
</html>
