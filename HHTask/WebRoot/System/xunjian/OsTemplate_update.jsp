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
		<script type="text/javascript">
var index = 1;

function addLine() {
	var newLine = '<tr> <td> <select name="t.scope['
			+ index
			+ '].type" style="width:150px " > <option>手动填写</option> <option>OKorNo</option> </select> </td> <td> <input name="t.scope['
			+ index
			+ '].content"/> </td> <td> <input name="t.scope['
			+ index
			+ '].zltz"/> </td> <td> <input name="t.scope['
			+ index
			+ '].jcff"/> <input type="button" onclick="addLine();" value="追加"> <input type="button" onclick="delLine();" value="删除"> </td> </tr>';
	$($('#mytable tr')[$('#mytable tr').length - 2]).insertBefore(newLine);
	$('#lastTr').before(newLine);
	index++;
}

function delLine() {
	if (index == 1) {
		alert("只剩最后一项了,再删真没了");
		return;
	}
	$($('#mytable tr')[$('#mytable tr').length - 2]).remove();
	index--;
}
</script>
	</head>
	<body>
		<center>
			<form id="myForm" action="OsTemplate_updateOsTemplate.action"
				method="post" enctype="multipart/form-data"
				onsubmit="return check();">
				<input type="hidden" name="t.dept" value="${companyInfo.shortName}" />
				<s:if test="status != 'ty'">
				<table id="mytable" class="table" style="width: 90%">
					<tr>
					
						<th colspan="4">
							<font size="6">修改外购件模版</font>
						</th>
					</tr>
					<tr>
						<th>
							车型
							<input name="t.id" value="${t.id}" type="hidden" />
						</th>
						<td>
							<input name="t.cmodel" value="${t.cmodel}" />
						</td>
						<th>
							类型
						</th>
						<td>
							<select name="t.ctype" style="width: 150px">
								<option>
									${t.ctype}
								</option>
								<option>
									端盖
								</option>
								<option>
									隔盘
								</option>
								<option>
									内管
								</option>
								<option>
									外管
								</option>
								<option>
									吊钩
								</option>
								<option>
									法兰
								</option>
								<option>
									护板
								</option>
								<option>
									岩棉
								</option>
								<option>
									筒体
								</option>
								<option>
									螺帽
								</option>
								<option>
									其它
								</option>
								<option>
									螺纹嘴
								</option>
								<option>
									净化器
								</option>
								<option>
									波纹管
								</option>
								<option>
									玻璃纤维
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>
							名称
						</th>
						<td>
							<input name="t.name" value="${t.name}" />
						</td>
						<th>
							件号
						</th>
						<td>
							<input name="t.partNumber" value="${t.partNumber}" />
						</td>
					</tr>
					<tr>
						<th>
							材料
						</th>
						<td align="left">
							<input name="t.material" value="${t.material}" />
						</td>
						<th>
							检验规程编号
						</th>
						<td>
							<input name="t.serialNumber" value="${t.serialNumber}" />
						</td>
					</tr>
					<tr>
						<th>
							资料上传
						</th>
						<td>
							<a
								href="DownAction.action?fileName=${t.filename}&directory=upload/file/OsTemplate/">下载</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 重新上传
							<input id="abc" type="file" name="attachment" />
						</td>
						<th>
							类型
						</th>
						<td>
							<select name="t.ctype1">
								<option>
									${t.ctype1}
								</option>
								<option>
									外购件
								</option>
								<option>
									原材料
								</option>
								<option>
									组合
								</option>
								<option>
									总成
								</option>
								<option>
									自制
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>
							工序号
						</th>
						<td>
							<input name="t.gongxuNum" type="text" value="${t.gongxuNum}"/>
						</td>
					</tr>
					<tr id="lastTr">
						<td align="center" colspan="4">
							<input type="submit" value="提交" class="input">
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							常用符号: Φ ± ° ≤ ≥ ℃ < > № ⊥ ◎ ○
						</td>
					</tr>
				</table>
				</s:if>
				<s:else>
					<table id="mytable1" class="table" style="width: 90%">
						<tr>
							<th colspan="4">
								<font size="6">修改通用检验模版</font>
							</th>
						</tr>
						<tr>
							<th>
								型别
							</th>
							<td>
								<input id="cmodel" name="t.cmodel" value="${t.cmodel}" />
							</td>
							<th>
								产品类型
							</th>
							<td>
								<input id="ctype" name="t.ctype" value="${t.ctype}">
							</td>
						</tr>
						<tr>
							<th>
								模板类型
							</th>
							<td>
								<input type="text" value="${t.zhonglei}" id="zhonglei" name="t.zhonglei" />
							</td>
							<th>
								版本
							</th>
							<td>
								<input id="banbenNumber" name="t.banbenNumber" value="${t.banbenNumber}">
							</td>
						</tr>
						<tr>
							<th>
								检验类型
							</th>
							<td align="left">
								<select name="t.xjtype" id="xjtype" style="width: 209px;"
									onchange="changvalue(this)">
									<option value="${t.xjtype}">
										${t.xjtype}
									</option>
									<option value="按时间">
										按时间
									</option>
									<option value="按时间">
										按次数
									</option>
								</select>
							</td>
							<th>
								检验频次
							</th>
							<td id="xjcheckpc_id">
								<input name="t.xjcheckpc" id="xjcheckpc" value="${t.xjcheckpc}"/>
							</td>
						</tr>
						<tr>
							<th>
								上传图纸
							</th>
							<td>
								<a
								href="DownAction.action?fileName=${t.filename}&directory=upload/file/OsTemplate/">下载</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 重新上传
								<input id="abc" type="file" name="attachment" />
							</td>
							<th>
								生产类型
							</th>
							<td>
								<select id="ctype1" name="t.ctype1">
									<option value="${t.ctype1}">
										${t.ctype1}
									</option>
									<option>
										外购件
									</option>
									<option>
										原材料
									</option>
									<option>
										自制件
									</option>
									<option>
										总成件
									</option>
									<option>
										组合件
									</option>
								</select>
							</td>
						</tr>
						<tr id="lastTr">
						<td align="center" colspan="4">
							<input name="t.id" value="${t.id}" type="hidden" />
							<input type="hidden"	name="t.ispublic" value="${t.ispublic}"/>
							<input type="submit" value="提交" class="input">
						</td>
					</tr>
					</table>
				</s:else>
			</form>
			</div>
			<br>
			</div>

		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
					$(function(){
	var errorMessage = '${errorMessage}';
		if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
	})	
		
		</SCRIPT>
	</body>
</html>
