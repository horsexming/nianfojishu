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
					style="float: left; width: 100%; padding-left: 30px; padding-top: 5px;"
					align="center">
					<input type="button" value="进入sop" class="input" onclick="intoBom('试制')">
					<input type="button" value="进入lp" class="input" onclick="intoBom('批产')">
					<input type="button" value="取消参与" class="input" onclick="outBuildBom()">
				</div>

				</div>
			</div>
			
			<div align="center">
				<form
					action="gongyiGuichengAction!findGongyiGuichengList.action"
					method="post" style="">
					<br>
					<table border="0" width="100%" class="table">
						<tr>
							<td align="right">
								件号:
							</td>
							<td>
								<input type="text" name="gongyiGuicheng.jianNumb"
									value="${gongyiGuicheng.jianNumb}" />
							</td>
							<td align="right">
								名称:
							</td>
							<td>
								<input id="jianName" type="text" name="gongyiGuicheng.jianName"
									value="${gongyiGuicheng.jianName}" />
							</td>
							
						</tr>
						<tr>
						<td align="right">
								生产类型:
							</td>
							<td>
								<select id="status" name="gongyiGuicheng.procardStyle">
								<s:if test="gongyiGuicheng.procardStyle!=null&&gongyiGuicheng.procardStyle!=''">
								  <option value='<s:property value="gongyiGuicheng.procardStyle"/>' selected="selected">
										<s:property value="gongyiGuicheng.procardStyle"/>
									</option>
								</s:if>
									<option>
										请选择
									</option>
									<option>
										组合
									</option>
									<option>
										自制
									</option>
									<option>
										外购
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input type="submit" value="查询"
									style="width: 100px; height: 50px;" />

								<input type="reset" value="重置"
									style="width: 100px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table" style="text-align: center;">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							工艺规程编号
						</td>
						<td align="center">
							件号
						</td>
						<td align="center">
							件名
						</td>
						<td align="center">
							卡片类型
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator id="g" value="gongyiGuichengListForAll" status="st">

						<s:if test="#st.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this);"
								onmouseout="outBgcolor(this,'');">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this);"
								onmouseout="outBgcolor(this,'');">
						</s:else>

						<td>
							${st.index + 1}
						</td>
						<td>
							${g.numb}
						</td>
						<td>
							${g.jianNumb}
						</td>
						<td>
							${g.jianName}
						</td>
						<td>
							${g.procardStyle}
						</td>
						<td>
							${g.status}
						</td>
						<td>
							<a target="_self" onclick="buildBOm(${g.id},'${g.jianNumb}')">参与组装</a>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</s:if>
						<s:else>
							<td colspan="12" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
			<br />
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<script type="text/javascript">
//参与组装
function buildBOm(id,markId){
 if(parent.selectedPocardStyle=="自制"||parent.selectedPocardStyle=="外购"){
  alert("自制件和外购件下不能加零件！");
  return ;
 }
 if(markId==parent.selectedMarkId){
  alert("同件号不能作为上下级零件！");
  return;
 }
 if(window.confirm("确认参与组装？")){
   //alert(parent.rootId);
   //alert(parent.selectedId);
   //alert(id);
   $.ajax({
     type : "POST",
     url : "gongyiGuichengAction!bulidBomTree.action",
     dataType : "json",
     data : {
      id : id,//将要参与的零件的Id
      pid : parent.selectedId,//被选中的Id
      rootId : parent.rootId//正在组装的Bom的rootId
     },
     success : function(data){
      if(data.success){
       alert("成功加入组装Bom");
      parent.loadTree();
      }else{
       alert(data.message);
      }
     }
   });
 }
}
//取消参与组装
function outBuildBom(){
if(parent.selectedId==1){
return;
}
if(window.confirm("确认取消参与组装？")){
   //alert(parent.rootId);
   //alert(parent.selectedId);
   //alert(id);
   $.ajax({
     type : "POST",
     url : "gongyiGuichengAction!outBulidBomTree.action",
     dataType : "json",
     data : {
      id : parent.selectedId,//被选中的Id
      rootId : parent.rootId//正在组装的Bom的rootId
     },
     success : function(data){
      if(data.success){
       alert("成功取消加入组装Bom");
      parent.loadTree();
      }else{
       alert(data.message);
      }
     }
   });
 }
}
function intoBom(type){
 if(window.confirm("确认进入"+type+"？")){
 $.ajax({
     type : "POST",
     url : "gongyiGuichengAction!buildBomtoProcard.action",
     dataType : "json",
     data : {
      productStyle : type,//被选中的Id
      rootId : parent.rootId//正在组装的Bom的rootId
     },
     success : function(data){
      if(data.success){
       alert("成功进入"+type);
        parent.loadTree();
       }else{
       alert(data.message);
      }
     }
   });
 }
}
</script>
