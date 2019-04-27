<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <%@include file="/util/sonHead.jsp" %>
</head>
<body>
<%@include file="/util/sonTop.jsp" %>
<div id="gongneng" style="width: 100%;">
    <div align="center">
        <table class="table">
            <form action="SupplierEvaluateAction_generateMonthReport.action"
                  method="post" id="generate">
                <tr>
                    <td colspan="6" align="center">
                        <label for="">
                            手动按月生成评分表：
                        </label>
                        <INPUT id="start"
                               class="validate[required,funcCall[validate2time]]"
                               name="supplierEvaluateMonth"
                               onclick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})"
                               style="width: 80px;"/>
                        <%--<input type="text" value=""--%>
                        <%--style="width: 80px; height: 50px;"/>--%>
                        <input id="generateSubmit" type="submit" value="生成" onclick="PreventRepeat()"/>
                    </td>
                </tr>
            </form>
            <form action="SupplierEvaluateAction_generateQuarterReport.action" method="post">
                <td colspan="6" align="center">
                    <label for="">
                        手动按季度生成评分表：
                    </label>
                    <INPUT id="start"
                    <%--class="validate[required,funcCall[validate2time]]"--%>
                           name="supplierEvaluateQuarter"
                    <%--onclick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})"--%>
                           style="width: 80px;"/>
                    <%--<input type="text" value=""--%>
                    <%--style="width: 80px; height: 50px;"/>--%>
                    <input id="generateSubmit" type="submit" value="生成" onclick="PreventRepeat()"/>
                    格式：2018年1季
                </td>
            </form>
        </table>
    </div>

    </br>
    </br>
    <div align="center">

        <form action="SupplierEvaluateAction_updatelevelsocre.action" method="post" id="level">
            <table class="table">
                <tr align="center">
                    <td colspan="6">
                        评级设置
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        评级名称
                    </td>
                    <td colspan="3">
                        评级分数
                    </td>
                </tr>

                <s:iterator value="supplierEvaluateLevelList" id="lev" status="Status0">
                    <tr class="levelListTr" id="levelListTr${Status0.index}">

                        <td colspan="3">
                                <%--<input id="name${Status0.index}" type="text" name="supplierEvaluateLevelList[${Status0.index}].levelName" value="${lev.levelName}">--%>
                            <input id="name${Status0.index}" type="text"
                                   name="supplierEvaluateLevelList[${Status0.index}].levelName"
                                   value="${lev.levelName}">
                        </td>
                        <td colspan="3">
                                <%--<input id="scl${Status0.index}" type="text" name="supplierEvaluateLevelList[${Status0.index}].levelScore" value="${lev.levelScore}">--%>
                            <input id="scl${Status0.index}" type="text"
                                   name="supplierEvaluateLevelList[${Status0.index}].levelScore"
                                   value="${lev.levelScore}">
                        </td>
                    </tr>
                </s:iterator>
                <td colspan="6">
                    <input type="button" value="添加一行" style="width: 80px; float: left;" onclick="addOneLine()"/>
                    <input type="button" value="删除一行" style="width: 80px; float: left;" onclick="delOneLine()"/>
                </td>

                </tr>
                <%--<tr>--%>
                <%--<s:iterator>--%>
                <%--<td>评级A--%>
                <%--</td>--%>
                <%--<td>--%>
                <%--<s:if test="#levelScore[0]==''">--%>
                <%--</s:if>--%>
                <%--<s:else>--%>
                <%--<input type="text" name="levelScore[0]" value="${levelScore[0]}">--%>
                <%--</s:else>--%>
                <%--</td>--%>
                <%--<td>评级B--%>
                <%--</td>--%>
                <%--<td>--%>
                <%--<s:if test="#levelScore[1]==''">--%>
                <%--</s:if>--%>
                <%--<s:else>--%>
                <%--<input type="text" name="levelScore[1]" value="${levelScore[1]}">--%>
                <%--</s:else>--%>
                <%--</td>--%>
                <%--<td>评级C--%>
                <%--</td>--%>
                <%--<td>--%>
                <%--<s:if test="#levelScore[2]==''">--%>
                <%--</s:if>--%>
                <%--<s:else>--%>
                <%--<input type="text" name="levelScore[2]" value="${levelScore[2]}">--%>
                <%--</s:else>--%>
                <%--</td>--%>
                <%--</s:iterator>--%>

                <%--</tr>--%>
                <tr>
                    <td colspan="6">
                    <span>
                    注：设定分数表示该级别最低分数
                        </span>
                    </td>

                </tr>
                <td align="center" colspan="6">
                    <input type="submit" value="提交"/>
                </td>
            </table>
        </form>
    </div>
</div>
</div>
<%@include file="/util/foot.jsp" %>

<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="application/javascript">
    function addOneLine() {
        //第几行
        var num;
        num = $('.levelListTr')[$('.levelListTr').length - 1].id;
        num = num.substring(11, num.length);
        var numplusone = parseInt(num) + 1;
        var linediv = "<tr class='levelListTr'id='levelListTr" + numplusone + "'><td colspan='3'> " +
            "<input id='name" + numplusone + "' type='text' name='supplierEvaluateLevelList[" + numplusone + "].levelName' value='' >" +
            "</td> " +
            "<td colspan='3'> " +
            "<input id='name" + numplusone + "' type='text' name='supplierEvaluateLevelList[" + numplusone + "].levelName' value='' >" +
            "</td></tr>"
        $("tr[class='levelListTr']:last").after(linediv);

    }

    function delOneLine() {
        // var num;
        // num = $('.levelListTr')[$('.levelListTr').length - 1].id;
        // num = num.substring(11, num.length);
        $("tr[class='levelListTr']:last").remove();
    }
</script>
</body>
</html>
