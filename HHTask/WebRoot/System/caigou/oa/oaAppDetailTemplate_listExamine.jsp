<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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
		<div align="center">
			<form method="post"
				action="OAAppDetailTemplateAction!findTempList.action" id="form">
				<table class="table" align="center">
					<tr>
						<th>件号</th>
						<th><input type="text" name="template.wlcode"
							value="${template.wlcode}" /></th>
						<th>名称：</th>
						<th><input type="text" name="template.detailAppName"
							value="${template.detailAppName}" /></th>
						<th>日期：</th>
						<th>从：<input class="Wdate" type="text" name="startDate"
							size="15" value="${startDate}"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							到： <input class="Wdate" type="text" name="endDate" size="15"
							value="${endDate}"
							onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
						</th>
					</tr>
					<tr>
						<th>规格：</th>
						<th><input type="text" name="template.detailFormat"
							value="${template.detailFormat}" /></th>
						<th>单位：</th>
						<th><input type="text" name="template.detailUnit"
							value="${template.detailUnit}" /></th>
						<th>版本：</th>
						<th><input type="text" name="template.banben"
							value="${template.banben}" /></th>
					</tr>
					<tr>
						<th>类别：</th>
						<th><select name="template.detailChildClass"
							style="width: 130px;" id="detailChildClass"
							onMouseOver="createDept('detailChildClass','OAAppDetailTemplateAction!findOASelect.action?tag=detailChildClass&powerTag=${powerTag}')"
							<option value=""></option>
							<s:if test="template.detailChildClass!=null">
                            <option selected="selected" value="${template.detailChildClass}">
                                    ${template.detailChildClass}
                            </option>
                        </s:if>
							<s:else>
                            <option value="${template.detailChildClass}">
                                    ${template.detailChildClass}
                            </option>
                        </s:else></select>
						</th>
						<th>供料属性：</th>
						<th><select name="template.kgliao" style="width: 150px;">
								<option></option>
								<s:iterator value="{'TK','TK Price','TK AVL','CS'}"
									id="kgliaoTemp">
									<s:if test="#kgliaoTemp==template.kgliao">
										<option value="${kgliaoTemp}" selected="selected">${kgliaoTemp}</option>
									</s:if>
									<s:else>
										<option value="${kgliaoTemp}">${kgliaoTemp}</option>
									</s:else>
								</s:iterator>
						</select></th>
						<th>审批状态：</th>
						<th><select name="template.epStatus" style="width: 173px">
								<option></option>
								<s:iterator id="status" value="{'同意','未审批','打回','预申请'}">
									<s:if test="#status==template.epStatus">
										<option value="${status}" selected="selected">${status}</option>
									</s:if>
									<s:else>
										<option value="${status}">${status}</option>
									</s:else>
								</s:iterator>
						</select></th>
					</tr>
					<tr>
						<th>借领属性</th>
						<td><select name="template.lendNeckStatus">
								<option value=""></option>
								<s:iterator value="{'借','领'}" id="asdf">
									<s:if test="#asdf==template.lendNeckStatus">
										<option value="${asdf }" selected="selected">${asdf }</option>
									</s:if>
									<s:else>
										<option value="${asdf }">${asdf }</option>
									</s:else>
								</s:iterator>
						</select></td>
						<th>图号</th>
						<th><input type="text" name="template.tuhao"
							value="${template.tuhao }"></th>
						<th>每页显示条数</th>
						<th><input type="text" name="pageSize" value="${pageSize }"></th>
					</tr>
					<tr>
						<td colspan="7" align="center"><input type="submit"
							value="查询" class="input" /> <input type="button" value="导出"
							class="input" onclick="getExcel();todisabledone(this)"
							id="exportb"></td>
					</tr>
				</table>
			</form>
			<br>
			<form  method="post">
				<table class="table">
					<tr bgcolor="#c0dcf2" height="30px"
						style="border-collapse: separate;">
						<td>
							<input type="checkbox" onclick="chageAllCheck(this)"
								id="checkAll">
							全选
						</td>
						<th align="center">序号
						</th>
						<th align="center">类别
						</th>
						<th align="center">
							物料编码
						</th>
						<th align="center">名称
						</th>
						<th align="center">规格
						</th>
						<th align="center">单位
						</th>
						<th>供料属性</th>
						<th>版本</th>
						<th>图号</th>
						<th align="center">申购日期
						</th>
						<th>借领属性</th>
						<th align="center">状态
						</th>
						<th align="center" style="width: 40px;">操作
						</th>
					</tr>

					<s:if test="templateList.size()>0">
						<s:iterator value="templateList" status="se" id="detail">
							<s:if test="#se.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr id="trId${detail.id}" align="center"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<input type="checkbox" value="${detail.id}" name="ids"/>
							</td>
							<td><s:property value="#se.index+1" /></td>
							<td>${detail.detailChildClass}</td>
							<td>
								${detail.wlcode}
							</td>
							<td>${detail.detailAppName}</td>
							<td>${detail.detailFormat}</td>
							<td>${detail.detailUnit}</td>
							<td>${detail.kgliao}</td>
							<td>${detail.banben}</td>
							<td>${detail.tuhao}</td>
							<td>${detail.detailAppDate}</td>
							<td>${detail.lendNeckStatus}</td>
							<td><a
								href="CircuitRunAction!findAduitPage.action?id=${detail.epId}">${detail.epStatus}</a>
							</td>
							<td><a
								href="OAAppDetailTemplateAction!getTemplateById.action?id=${detail.id}&tag=update&cpage=${cpage}">修改</a>
								<br /> <a onClick="deleteOADetailTemp(${detail.id},${cpage})"
								href="javascript:;">删除</a> <br /></td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="16" align="right">共 <s:property value="total" />
								页 第 <s:property value="cpage" /> 页 <fenye:pages cpage="%{cpage}"
									total="%{total}" url="%{url}" styleClass="page" theme="number" />
							</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" onclick="chageAllCheck(this)"
									id="checkAll">
								全选
							</td>
							<td colspan="20">
								<input id="ok" class="input" style="width: 120px;" align="top"
									type="button" value="审批通过"
									onclick="toSubmitTl1(this.form,'ok')" />
								<input id="ng" class="input" align="top" type="button"
									value="审批驳回" onclick="toSubmitTl1(this.form,'no')" />
							</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td colspan="16" style="font-size: 15px; color: red;">
								当前没有您要审批的申报物品	
							</td>
						</tr>
					</s:else>
				</table>
			</form>
		</div>
		<br>
	</div>
	<%@include file="/util/foot.jsp"%>
	</center>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
</body>
<script type="text/javascript">

    function getExcel(id){
        var form=document.getElementById("form");
        form.action="OAAppDetailTemplateAction!exportExcel.action";
        form.submit();
        form.action="OAAppDetailTemplateAction!findTempList.action";
    }
    /*function alsoAdd() {
        window.location = "oaAppDetailAction!preSaveOADetail.action";
    }*/
    function deleteOADetailTemp(id, cpage) {
        if (confirm("确定要删除该条记录吗？")) {

            $.ajax({
                type: "POST",
                url: "OAAppDetailTemplateAction!deleteOADetailTempById.action",
                data: {
                    id: id,
                    cpage: cpage
                },
                dataType: 'json',
                success: function (data) {
                    alert(data);
                    $("#trId" + id).remove();
                }
            });
        }
    }
    function toSubmitTl1(form,tag){
    	form.action = "OAAppDetailTemplateAction!auditAllShenPi.action?tag="+tag;
    	form.submit();
    }
</script>
</html>
