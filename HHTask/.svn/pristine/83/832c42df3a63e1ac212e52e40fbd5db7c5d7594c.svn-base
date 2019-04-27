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
				<h1>
					${craftComplexity.name}
				</h1>
			</div>
			<s:if test="successMessage!=null">
			 <div align="center">
					<h5><font color="red">${successMessage}</font></h5>
			</div>
			</s:if>
			<s:if test="errorMessage!=null">
			 <div align="center">
					<h5><font color="red">${errorMessage}</font></h5>
			</div>
			</s:if>
			<div align="center">
				<form action="craftComplexityAction_linkSkillType.action" method="post"
					 style="margin: 0px">
					<input type="hidden" name="craftComplexity.id" value="${craftComplexity.id}">
					<table style="width: 980px; border-collapse: collapse;" border="0"
						align="center">
						<tr>
							<td align="right" colspan="7">
								<font color="red">共选择 <label id="peopleLabel">
										${count}
									</label> 个</font>
								<input type="submit" value="绑定"
									style="width: 60px; height: 40px;" align="top">
								<br>
								<br>
							</td>
						</tr>
						<tr align="center" bgcolor="#c0dcf2"
							style="height: 40px; font-weight: bold;">
							<td align="center">
								序号
								<br />
								(num)
							</td>
							<td align="center">
								技能 分类
								<br />
								(skill type)
							</td>
							<td align="center">
								最大分值
								<br />
								（max score）
							</td>
							<td align="center">
								最小分值
								<br />
								（min score）
							</td>
							<td>
								<input type="checkbox" id="checkAll"
									onchange="chageAllCheck()">
								全选
							</td>
						</tr>

						<s:iterator id="hadSTVo" value="hadSTVoList"
							status="ststusfunction">
							<s:if test="#ststusfunction.first">
								<tr bgcolor="green">
									<td colspan="7" style="font-family: 微软雅黑; font-weight: bold;"
										align="center">
										<font color="#ffffff"> 已绑定技能分类</font>
									</td>
								</tr>
							</s:if>
							<s:if test="#ststusfunction.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#ststusfunction.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#ststusfunction.index+1" />
								</font>
							</td>
							<td>
								${hadSTVo.name }
							</td>
							<td>
								${hadSTVo.maxscore}
							</td>
							<td>
								${hadSTVo.minscore}
							</td>
							<td>
								<input type="checkbox"
									name="checkboxs" value="${hadSTVo.id}" onchange="chageNum()"
									checked="checked">
							</td>
							</tr>
							<s:if test="#ststusfunction.last">
								<tr bgcolor="green">
									<td colspan="7" style="font-family: 微软雅黑; font-weight: bold;"
										align="center">
										<font color="#ffffff"> 未绑定技能分类</font>
									</td>
								</tr>
							</s:if>
						</s:iterator>

						<s:iterator id="unHadSTVo" value="unHadSTVoList" status="ststusfunction">
							<s:if test="#ststusfunction.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#ststusfunction.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#ststusfunction.index+1" />
								</font>
							</td>
							<td>
								${unHadSTVo.name }
							</td>
							<td>
								${unHadSTVo.maxscore}
							</td>
							<td>
								${unHadSTVo.minscore}
							</td>
							
							<td>
							    <s:if test="#unHadSTVo.skillScore.size()==0">
							              <font color="red">  不能选</font>
							    </s:if>
							    <s:else>
								<input type="checkbox"
									name="checkboxs" value="${unHadSTVo.id}" onchange="chageNum()"/>
								</s:else>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="7" align="center">
								<font color="red">${errorMessage}</font>
							</td>
						</tr>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="11" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="11" align="center" style="color: red">
							</s:else>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td align="right" colspan="7">
								<br>
								<font color="red">(不能选表示该技能分类没有绑定技能系数)共选择 <label id="peopleLabel2">
										${count}
									</label>人</font>
								<input type="submit" value="绑定"
									style="width: 60px; height: 40px;" align="top">
								<br>
								<br>
								<br>
								<br>
							</td>
						</tr>
					</table>
				</form>


			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		function chageAllCheck(){
			var checkAll=document.getElementById("checkAll");
			var checkboxs=document.getElementsByName("checkboxs");
			if(checkAll.checked==true){
				for(var i=0;i<checkboxs.length;i++){
					checkboxs[i].checked=true;
				}
			}else{
				for(var i=0;i<checkboxs.length;i++){
					checkboxs[i].checked=false;
				}
			}
			
		}
		function chageNum(){
			var checkAll=document.getElementById("checkAll");
			var checkboxs=document.getElementsByName("checkboxs");
			var count=0;
			for(var i=0;i<checkboxs.length;i++){
				if(checkboxs[i].checked==false){
					checkAll.checked=false;
					return;
				}else{
					count++;
				}
			}
			if(count==checkboxs.length){
				checkAll.checked=true;
			}
		}
		</script>
	</body>
</html>
