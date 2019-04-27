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
	<body onload="createDept('dept')">
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
				</div>
			</div>
			
			<div align="center">
				<h3>
					<s:if test="pageStatus == 'gjb'">
						国军标文件管理
					</s:if>
					<s:else>
						体系文件管理
					</s:else>
					
				</h3>
				<form action="systemFileAction_findAllBylevel.action"
					method="post">
					<table class="table" align="center">
						<tr>
						 <th>文件类型：
						 </th>
							<td align="center">
							<input type="hidden" name="tag" value="${tag}"/>
							<input type="hidden" name="pageStatus" value="${pageStatus}"/>
							<input type="hidden" name="level" value="${level}"/>
								<select name="systemFile.fileType" id="fileType">
									<s:if test="pageStatus=='gjb'">
										<s:if test="level=='gb'">
											<option value="国标(gb)">国标</option>
										</s:if>
										<s:elseif test="level=='jb'"><option value="军标">军标</option></s:elseif>
										<s:elseif test="level=='gjb'"><option value="国军标">国军标</option></s:elseif>
										<s:else>
											<option value="项目文件">项目文件</option>
										</s:else>
										
									</s:if>
									<s:else>
										<option value="${systemFile.fileType}">${systemFile.fileType}</option>
    									<option value="体系文件">体系文件</option>
										<option value="外来文件">外来文件</option>
										<option value="法律法规">法律法规</option>
										<option value="认证证书">认证证书</option>
										<option value="国标">国标</option>
									</s:else>
    							</select>
							</td>
							<th>文件编号：
						 </th>
							<td align="center">
								<input type="text" name="systemFile.fileNo" value="${systemFile.fileNo}" />
							</td>
							<th>文件名称：
						 </th>
							<td align="center">
								<input type="text" name="systemFile.fileName" value="${systemFile.fileName}" />
							</td>
						</tr>
						<tr>
						<th>文件等级：
						 </th>
						   <td align="center">
						   <s:if test='level=="1"'>一级文件<input type="hidden" name="systemFile.fileLevel" value="一级文件" /></s:if>
							<s:elseif test='level=="2"'>二级文件<input type="hidden" name="systemFile.fileLevel" value="二级文件"/></s:elseif>
							<s:elseif test='level=="3"'>三级文件<input type="hidden" name="systemFile.fileLevel" value="三级文件" /></s:elseif>
							<s:elseif test='level=="4"'>四级表单<input type="hidden" name="systemFile.fileLevel" value="四级表单" /></s:elseif>
							<s:elseif test='level=="gb"'>国标<input type="hidden" name="systemFile.fileLevel" value="国标"/></s:elseif>
							<s:elseif test='level=="jb"'>军标<input type="hidden" name="systemFile.fileLevel" value="军标"/></s:elseif>
							<s:elseif test='level=="gjb"'>国军标<input type="hidden" name="systemFile.fileLevel" value="国军标"/></s:elseif>
							<s:elseif test='level=="skill"'>技术规范<input type="hidden" name="systemFile.fileLevel" value="技术规范"/></s:elseif>
							<s:elseif test='level=="law"'>法律法规<input type="hidden" name="systemFile.fileLevel" value="法律法规" /></s:elseif>
							<s:elseif test='level=="sop"'>作业SOP<input type="hidden" name="systemFile.fileLevel" value="作业SOP" /></s:elseif>
							<s:elseif test='level=="sip"'>检验SIP<input type="hidden" name="systemFile.fileLevel" value="检验SIP"/></s:elseif>
							<s:elseif test='level=="out"'>外来文件<input type="hidden" name="systemFile.fileLevel" value="外来文件" /></s:elseif>
							<s:elseif test='level=="peixun"'>培训资料<input type="hidden" name="systemFile.fileLevel" value="培训资料" /></s:elseif>
							<s:elseif test='level=="other"'>其他文件<input type="hidden" name="systemFile.fileLevel" value="其他文件" /></s:elseif>
							<s:elseif test="level==null">
							<select name="systemFile.fileLevel" id="fileLevel">
    								<option value="${systemFile.fileLevel}">${systemFile.fileLevel}</option>
    								<option value="一级文件">一级文件</option>
									<option value="二级文件">二级文件</option>
									<option value="三级文件">三级文件</option>
									<option value="四级表单">四级表单</option>
									<option value="技术规范">技术规范</option>
									<option value="法律法规">法律法规</option>
									<option value="作业SOP">作业SOP</option>
									<option value="检验SIP">检验SIP</option>
									<option value="外来文件">外来文件</option>
									<option value="培训资料">培训资料</option>
									<option value="其他文件">其他文件</option>
    						</select>
    						</s:elseif>
							</td>
							<th>部门归属：
						 </th>
							<td align="center">
					  	<SELECT id="dept" name="systemFile.department" value="${systemFile.department}">
					  		<option value=""></option>
					  	</SELECT>
					 
							</td>
							<th>上传人员：
						 </th>
							<td align="center">
								<input type="text" name="systemFile.person" value="${systemFile.person}" />
							</td>
							
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" />
								<input type="reset" style="width: 100px; height: 40px;"
									value="重置"/>
							</td>
						</tr>
					</table>
				</form>
				<table width="100%" border="0" style="border-collapse: collapse;"class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							文件类型
						</td>
						<td align="center">
							文件编号
						</td>
						<td align="center">
							文件名称
						</td>
						<td align="center">
							产品编码
						</td>
						<td align="center">
							文件等级
						</td>	
						<td align="center">
							部门归属
						</td>					
						<td align="center">
							文件描述
						</td>						
						<td align="center">
							上传人员
						</td>						
						<td align="center">
							上传时间
						</td>						
						<td align="center" colspan="2">操作<br/>(Operation)</td>
					</tr>
					<s:iterator value="systemFileList" id="sFile" status="pageStatus">
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
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td align="center">
							${sFile.fileType}
						</td>
						<td align="left">
							${sFile.fileNo}
						</td>
						<td align="left">
							${sFile.fileName}
						</td>
						<td align="center">
							${sFile.cpCode}
						</td>
						<td align="center">
							${sFile.fileLevel}
						</td>						
						<td align="center">
							${sFile.department}
						</td>	
						<td align="center">
							${sFile.description}
						</td>					
						<td align="center">
							${sFile.person}
						</td>						
						<td align="center">
							${sFile.uploadDate}
						</td>			
						<td  colspan="2">
							<a onclick="toDetail(${sFile.id},'${cpage}','${level}')">文件详情/</a>
						   <s:if test="#sFile.fileUrl!=null">
						   <s:if test ="level==4">
										<a
											href="<%=path%>/upload/file/sysFile/${sFile.fileUrl}">下载/</a>
							</s:if>
										<a	href="FileViewAction.action?FilePath=/upload/file/sysFile/${sFile.fileUrl}">预览</a>
									</s:if>
									<s:else>无文件</s:else>
						</td>

					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
						
						<s:if test="successMessage!=null">
						<tr>
							 <td colspan="11" align="center" style="color: red">
								${successMessage}
								
						</td>
					</tr>
                          </s:if>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function toDetail(id, cpage,level) {
	window.location.href = "systemFileAction_toDetail.action?systemFile.id="
			+ id + "&cpage=" + cpage+"&tag=nolook"+"&level="+level;
}

</script>
	</body>
</html>
