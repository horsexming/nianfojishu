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
$(function() {
	var pageStatus = "${pageStatus}";
	var size = parseFloat("${size}");
	var manage = $("#manage");
	var laborcostLabel = $("#laborcostLabel");
	var fenpeiRateLabel = $("#fenpeiRateLabel");
	if (pageStatus != "" && size > 0) {
		for ( var i = 0; i < size; i++) {
			$("#manage" + i).show();
			//市场,隐藏可调系数
			if (pageStatus == "sc") {
				$("#fenpeiRateLabel" + i).hide();
			}
		}
	}
});

function update(obj, index, id, name, oldValue) {
	var laborcost = $("#laborcost" + index).val();
	var fenpeiRate = $("#fenpeiRate" + index).val();
	if (laborcost == "") {
		laborcost = 0;
	}
	if (fenpeiRate == "") {
		fenpeiRate = 0;
	}
	//判断值是否相同
	if (parseFloat(obj.value) == parseFloat(oldValue)) {
		$("#showMessage").html("");
		return false;
	}

	if (fenpeiRate > 1) {
		$("#showMessage").html("可调系数不能大于1,请重新填写!");
		$("#fenpeiRate" + index).select();
		return false;
	}

	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!updatelf.action",
		data : {
			id : id,
			laborcost : laborcost,
			fenpeiRate : fenpeiRate
		},
		dataType : "json",
		success : function(msg) {
			if (msg.success == true) {
				$("#showMessage").html(name + "更改成功");
			} else {
				$("#showMessage").html(name + "更改失败,请重新更改!");

			}
		}
	});
}
</script>

	</head>
	<body>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<center>
					<table style="width: 100%">
						<tr>
							<td>

							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									height="32" onclick="chageDiv('none')">
							</td>
						</tr>
					</table>
				</center>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>

		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center" style="border: 1px solid #00000;">
				<form action="markIdAction!listGysMarkIdjiepaichakan.action"
					method="post">
					<table class="table">
						<tr>
							<th colspan="6">
								供应商产品管理
							</th>
						</tr>
						<tr>
							<th>
								件号:
							</th>
							<td>
								<input name="gysMarkIdjiepai.markId" value="${gysMarkIdjiepai.markId}"/>
							</td>
							<th>
								名称:
							</th>
							<td>
								<input name="gysMarkIdjiepai.proName" value="${gysMarkIdjiepai.proName}"/>
							</td>
							<th>
								供应商:
							</th>
							<td align="left">
								<select name="gysMarkIdjiepai.zhuserId" id="gys_3" class="cxselect">
									<option selected="selected" value="">
										选择供应商
									</option>
									<s:iterator id="zh" value="zhuserList">
										<option value="${zh.id}">
											${zh.cmp}
										</option>
									</s:iterator>
								</select>
							</td>
						</tr>

						<tr>
							<th colspan="6">
								<input type="submit" value="查询(Query)" class="input" />
								<input type="button" value="添加(add)" class="input" onclick="toadd()"/>
								<input type="button" value="导出(exprot)" class="input" onclick="exprot(this.form)"/>
							</th>
						</tr>
					</table>
				</form>

				<div id="rootTemplateDiv">
					<div id="showMessage"
						style="color: red; font-size: 14px; font-weight: bolder;">
					</div>

					<form action="markIdAction!pladdGysMarkIdjiepai.action"
						method="post" enctype="multipart/form-data"
						onsubmit="return checktype()">
						<b>供应商采购比例导入</b>:
						<input type="file" name="addfile">
						<a href="<%=basePath%>/upload/file/download/GysMarkIdjiepai.xls">导入模版下载</a>
						<a href="FileViewAction.action?FilePath=/upload/file/download/GysMarkIdjiepai.xls&Refresh=true">/预览</a>
						<input type="submit" value="批量导入" id="sub">
					</form>
					<form action="zhaobiaoAction!banding.action" method="post">
						<input type="hidden" value="${zhUser.id}" name="zhUser.id"
							id="zhUser.id" />
						<table class="table">
							<tr bgcolor="#c0dcf2" height="50px">
								<th align="center">
									序号
									<br />
									No.
								</th>
								<th align="center">
									件号
									<br />
									Part No.
								</th>
								<th align="center">
									名称
									<br />
									Name
								</th>
								<th align="center">
									物料类别
								</th>
								<th align="center">
									版本
								</th>
								<th align="center">
									供料属性
								</th>
<%--								<th align="center">--%>
<%--									卡片类型--%>
<%--									<br />--%>
<%--									Card Type--%>
<%--								</th>--%>
								<th align="center">
									产品类型
									<br />
									Product Type
								</th>
								<th align="center">
									型号
									<br />
									Operation
								</th>
								<th align="center">
									单件节拍
								</th>
								<th align="center">
									配送时长
								</th>
								<th align="center">
									供应商
								</th>
								<th align="center">
									是否节拍完成
								</th>
								<th align="center">
									配额
								</th>

								<th align="center">
									操作
									<br />
									Operation
								</th>
							</tr>
							<!-- ---------------------------------------------------------------------- -->
							<s:iterator value="list" id="pageProcardTem" status="pageindex">
								<s:if test="#pageindex.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#pageindex.index+1" />
								</td>
								<td align="left">
									${pageProcardTem.markId}
								</td>
								<td style="width: 180px;"  align="left">
									${pageProcardTem.proName}
								</td>
								<td style="">
									${pageProcardTem.wgType}
								</td>
								<td>
									${pageProcardTem.banBenNumber}
								</td>
								<td>
									${pageProcardTem.kgliao}
								</td>
<%--								<td>--%>
<%--									${pageProcardTem.procardStyle}--%>
<%--								</td>--%>
								<td>
									${pageProcardTem.productStyle}
								</td>
								<td>
									${pageProcardTem.carStyle}
								</td>
								<td>
									${pageProcardTem.allJiepai}
								</td>
								<td>
									${pageProcardTem.deliveryDuration}
								</td>
								<td>
									${pageProcardTem.gys}
								</td>
								<td>
									<s:if test='#pageProcardTem.capacity==null'>
										<font color="red">未完成</font>
									</s:if>
									<s:else>
									已完成
									</s:else>
								</td>
								<td id="td_${pageProcardTem.id}">
									${pageProcardTem.cgbl}
								</td>
								<td>
								
									<!-- CircuitRunAction_findAduitPage.action?id=${pageList.ep_Id} -->
									<a
										href="<%=basePath%>System/caigou/zhaobiao/bangding/Template_findProcard.jsp?id=${pageProcardTem.rootId}">明细(Details)</a>/
									<a href="javascript:;"
										onclick="changcgbl('${pageProcardTem.markId}','${pageProcardTem.id}','${pageProcardTem.cgbl}','${pageProcardTem.kgliao}','${pageProcardTem.banBenNumber}')">修改配额</a>
									/
									<a href="javascript:;" onclick="spdt('${pageProcardTem.id}')">审批动态</a>
									/<a href="javascript:;" onclick="del('${pageProcardTem.id}')">删除</a>


								</td>

								</tr>
							</s:iterator>
							<tr>
								<s:if test="errorMessage==null">
									<td colspan="14" align="right">
										第
										<font color="red"><s:property value="cpage" /> </font> /
										<s:property value="total" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />
								</s:if>
								<s:else>
									<td colspan="14" align="center" style="color: red">
								</s:else>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function toUpdatezhaobiao(id) {
	var zhUserId = document.getElementById("zhUserId").value();
	var url = encodeURI(encodeURI("markIdAction!toUpdatezhaobiao.action?zhaobiao.id="
			+ id + "&zhUser.id=" + zhUserId));
	$("#showProcess").attr("src", url);
	chageDiv('block');
}

function vali() {
	var nums = document.getElementsByName("selected");
	for ( var i = 0; i < nums.length; i++) {
		if (nums[i].checked) {
			return true;
		}
	}
	alert("请选择需要绑定的件号！谢谢");
	return false;
}
function exportExcel(objForm) {
	objForm.action = "sellAction!exportEXCEL.action?tag=sellDetail";
	objForm.submit();
}
function detail(id) {
	window.location = "orderManager_queryDetail.action?id=" + id;
}
var cgbl = 0;
function changcgbl(markId, id, cgbl1, kgliao,banbenNumber) {
	if (markId != null && markId != "") {
		$
				.ajax( {
					type : "POST",
					url : "markIdAction!getSumCgbl.action",
					data : {
						markId : markId,
						kgliao : kgliao,
						banBenNumber:banbenNumber
					},
					dataType : "json",
					success : function(data) {
						if (data == "error") {
							alert("啊哦，出错了啊");
						} else if (data != null) {
							var ycgbl = parseFloat(data);
							cgbl = 100 - ycgbl;
							alert('件号' + markId + '已分配配额' + ycgbl + '%，还剩余'
									+ (100 - ycgbl) + '%')
							if ((100 - ycgbl) >= 0) {
								$("#td_" + id)
										.html(
												'<input type="text" value="'
														+ cgbl1
														+ '" onchange="numyanzheng(this);yanzheng(this,&apos;'
														+ cgbl1
														+ '&apos;)"  style="width: 50px;" name="cgbl" id=input_'
														+ id
														+ '    /> <input type="button" value="申请修改"'
														+ '  onclick="sqxgcgbl(&apos;'
														+ id + '&apos;,&apos;'
														+ markId
														+ '&apos;,&apos;'
														+ kgliao + '&apos;)"/>');
							}
						}
					}
				})
	}
}
function sqxgcgbl(id, markId, kgliao) {
	if (id != null && id != "") {
		var xgcgbl = $("#input_" + id).val();
		xgcgbl = parseFloat(xgcgbl);
		$.ajax( {
			type : "POST",
			url : "markIdAction!updatecgbl.action",
			data : {
				id : id,
				cgbl : xgcgbl,
				kgliao : kgliao,
			},
			dataType : "json",
			success : function(data) {
				if (data == "error") {
					alert("啊哦，出错了啊");
				} else if (data == "true") {
					alert("申请成功，请等待审批!");
					window.location.reload();
				} else {
					alert(data)
				}
			}
		})

	}
}
function yanzheng(obj, xgcnbl) {
	if (obj != null) {
		var value = obj.value;
		value = parseFloat(value);
		var sxcgbl = parseFloat(xgcnbl) + parseFloat(cgbl);
		if (value > sxcgbl) {
			obj.value = '';
			obj.focus();
			alert("所分配配额超出剩余分配配额，请重新填写")
		}
	}
}
function spdt(id) {
	if (id != null && id != '') {
		$
				.ajax( {
					type : "POST",
					url : "markIdAction!getepId.action",
					data : {
						id : id
					},
					dataType : "json",
					success : function(data) {
						if (data == "error") {
							alert("啊哦，出错了啊");
						} else if (data != "" && data != null) {
							window.location.href = "CircuitRunAction_findAduitPage.action?id="
									+ data;
						}
					}
				})
	}
}
function toadd(){
	$("#showProcess").attr("src", "markIdAction!toadd.action");
	chageDiv('block');
}
function del(id){
	if(confirm('确定要删除该供应商产品吗？')){
			$.ajax( {
		type : "POST",
		url : "markIdAction!delProcard.action",
		data : {
				id:id
			},
		dataType : "json",
		success : function(data) {
			if(data){
				alert('删除成功!')
				window.location.reload(true);
			}else{
				alert('删除失败!')
			}
		}
	})
	}
	
}
function exprot(obj){
	$(obj).attr("action","markIdAction!exprot.action");
	$(obj).submit();
	$(obj).attr("action","markIdAction!listGysMarkIdjiepaichakan.action");
}
</script>
	</body>
</html>
