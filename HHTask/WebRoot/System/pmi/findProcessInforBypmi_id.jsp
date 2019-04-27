<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    response.setHeader("Expires", "0");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragrma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <%@include file="/util/sonHead.jsp" %>
</head>
<body>
<div id="gongneng"
     style="border: solid 1px #0170b8; margin-top: 10px;">
    <div align="center">
        <div align="center">
            <h3>
                PMI(名称:${pmiManagement.pmi_name};ip:${pmiManagement.pmi_ip})对应生产工序查询
                <input type="hidden" name="pmi_id" value="${pmi_id}"/>
                <br/>
            </h3>
            <form
                    action="pmiManagementAction_findProcessInforBypmi_id.action?pmi_id=${pmi_id}"
                    method="post">
                <table class="table">
                    <tr>
                        <td align="center">
                            姓名：
                            <input type="text" name="processInfor.usernames"
                                   value="${processInfor.usernames}"/>
                        </td>
                        <td align="center">
                            件号：
                            <input type="text" name="processInfor.markId"
                                   value="${processInfor.markId}"/>
                        </td>
                        <td align="center">
                            批次：
                            <input type="text" name="processInfor.selfCard"
                                   value="${processInfor.selfCard}"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">
                            工序名：
                            <input type="text" name="processInfor.processName"
                                   value="${processInfor.processName}"/>
                        </td>
                        <td align="center">
                            工位：
                            <input type="text" name="processInfor.gongwei"
                                   value="${processInfor.gongwei}"/>
                        </td>
                        <%--								colspan="3" --%>
                        <td align="center">
                            <input type="submit" class="input" value="查询"/>
                        </td>
                    </tr>
                </table>
            </form>
            <br/>
            <!-- 内容 -->
            <table class="table">
                <tr bgcolor="#c0dcf2" height="50px">
                    <th align="center">
                        序号
                    </th>
                    <th>
                        工序号
                    </th>
                    <th>
                        名称
                    </th>
                    <th>
                        可领数量
                    </th>
                    <th>
                        已领数量
                    </th>
                    <th>
                        提交数量
                    </th>
                    <th>
                        不合格量
                    </th>
                    <th>
                        领取时间
                    </th>
                    <th>
                        提交时间
                    </th>
                    <th>
                        件号
                    </th>
                    <th>
                        批次
                    </th>
                    <th>
                        并行
                    </th>
                    <th>
                        工位
                    </th>
                    <th>
                        设备编号
                    </th>
                    <th>
                        生产类型
                    </th>
                    <th>
                        状态
                    </th>
                    <th>
                        控制类型
                    </th>
                    <th>
                        领取人
                    </th>
                    <th>
                        操作
                    </th>
                </tr>
                <s:iterator value="proceList" id="pageProcess" status="proStatus">
                    <tr align="center">
                        <th>
                                ${proStatus.index+1}
                        </th>
                        <th height="30px">
                                ${pageProcess.processNO}
                        </th>
                        <th>
                                ${pageProcess.processName}
                        </th>
                        <th>
                                ${pageProcess.totalCount}
                        </th>
                        <th>
                                ${pageProcess.applyCount}
                        </th>
                        <th>
                                ${pageProcess.submmitCount}
                        </th>
                        <th>
                                ${pageProcess.breakCount}
                        </th>
                        <th>
                                ${pageProcess.firstApplyDate}
                        </th>
                        <th style="width: 174px;">
                                ${pageProcess.submitDate}
                        </th>
                        <th>
                                ${pageProcess.markId}
                        </th>
                        <th>
                                ${pageProcess.selfCard}
                        </th>
                        <th>
                                ${pageProcess.processStatus}
                        </th>
                        <th>
                                ${pageProcess.gongwei}
                        </th>
                        <th>
                                ${pageProcess.shebeiNo}
                        </th>
                        <th>
                            <s:if test='#pageProcess.productStyle=="外委"'>
                                <a
                                        href="OSWorkAction!findAllOSW.action?osWork.markID=${procard.markId}&osWork.lotId=${procard.selfCard}">${pageProcess.productStyle}</a>
                            </s:if>
                            <s:else>
                                ${pageProcess.productStyle}
                            </s:else>
                        </th>
                        <th>
                                ${pageProcess.status}
                        </th>
                        <th>
                            <s:if
                                    test='#pageProcess.pmiType==null||#pageProcess.pmiType==""'>
                                无
                            </s:if>
                            <s:else>
                                ${pageProcess.pmiType}
                            </s:else>
                        </th>
                        <th>
                            <s:if test="viewStatus=='zjl'">
                                <a
                                        href="UsersAction!findUserByIdForDetails.action?id=${pageProcess.userId}">
                                        ${pageProcess.usernames}</a>
                            </s:if>
                            <s:else>${pageProcess.usernames}</s:else>
                        </th>
                        <th>
                            <div style="width: 100px;">
                                <s:if
                                        test="#pageProcess.shebeiNo!=''&&#pageProcess.shebeiNo!=null">
                                    <a
                                            href="ProdEquipmentAction!findMachineByNum.action?machine.no=${pageProcess.shebeiNo}&id=${pageProcess.id}">
                                        <s:if
                                                test="#pageProcess.pmiType!=null&&#pageProcess.pmiType!=''">
                                            <img src="images/PMIShebei.png" width="20px" height="20px"/>
                                        </s:if> <s:else>机</s:else> </a>
                                </s:if>
                                <s:else>
                                    <font color="gray">机</font>
                                </s:else>
                                <a
                                        href="pmiManagementAction!findRoodId.action?pmi_id=${pageProcess.id}">生产进度</a>
                            </div>
                        </th>
                    </tr>
                    <tr>
                        <th align="left" colspan="20"
                            style="background-color: red; border: 0px; margin: 0px; padding: 0px;">
                            <div align="left"
                                 style="background-color: #00ff01; width:${pageProcess.submmitCount/pageProcess.totalCount*100}%; text-overflow: ellipsis; white-space: nowrap; height: 100%;">
                                &nbsp;&nbsp;&nbsp;&nbsp;${pageProcess.processNO}工序完成进度:
                                    ${pageProcess.submmitCount/pageProcess.totalCount*100}%
                            </div>
                        </th>
                    </tr>
                </s:iterator>
                <tr>
                    <s:if test="errorMessage==null">
                    <td colspan="18" align="right">
                        第
                        <font color="red"><s:property value="cpage"/> </font> /
                            <s:property value="total"/>
                        页
                            <fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
                                         styleClass="page" theme="number"/>
                        </s:if>
                        <s:else>
                    <td colspan="18" align="center" style="color: red">
                            ${errorMessage}
                    </td>
                    </s:else>
                </tr>

                <tr>
                    <th align="left" colspan="20"
                        style="background-color: red; border: 0px; margin: 0px; padding: 0px;">
                        <%--<div align="left"
                        style="background-color: #00ff01; width:${proceL.submmitCount/proceL.filnalCount*100}%; text-overflow: ellipsis; white-space: nowrap; height: 100%;">
                        &nbsp;&nbsp;&nbsp;&nbsp;${proceL.processName}工序完成进度:
                        ${proceL.submmitCount/proceL.filnalCount*100}%
                    </div>
                --%>
                    </th>
                </tr>

            </table>
        </div>
    </div>
</div>
<%@include file="/util/foot.jsp" %>
<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
</body>
<script type="text/javascript">
    function add() {
        var id = "${pmi_id}";
        $("#showProcess").attr("src",
            "pmiManagementAction_salMachine.action?pmi_id=" + id);
        chageDiv('block');
    }

    function del(obj) {
        var id1 = "${pmi_id}";
        window.location = "pmiManagementAction_delMachine.action?id1=" + id1
            + "&machine.id=" + obj;
    }

    //透明层调用其方法（子页面）
    var newUrl = function () {
        window.location.reload(true);
    }
</script>
</html>
