<%@ page language="java" pageEncoding="UTF-8" %>
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


    <%@include file="/util/sonHead.jsp" %>

</head>
<body>
<%@include file="/util/sonTop.jsp" %>
<div class="container">
    <div class="row clearfix">
        <h1 align="center">
            添加对账单
        </h1>
        <div class="">
            <div id="bodyDiv" align="center" class="transDiv">
            </div>
            <div id="contentDiv"
                 style="position: absolute; z-index: 255; width: 900px; display: none;"
                 align="center">
                <div id="closeDiv"
                     style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
                    <table style="width: 100%">
                        <tr>
                            <td>
                                <span id="title">选择财务档案</span>
                            </td>
                            <td align="right">
                                <img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
                                     height="32" onclick="chageDiv('none')">
                            </td>
                        </tr>
                    </table>
                    <div id="operatingDiv" style="background-color: #fff; width: 100%;">
                        <iframe id="showProcess" src="" marginwidth="0" marginheight="0"
                                hspace="0" vspace="0" frameborder="0" scrolling="yes"
                                style="width: 98%; height: 600px; margin: 0px; padding: 0px;"></iframe>
                    </div>
                </div>
            </div>

            <table class="table" id="tablebod">
                <form action="AccountCheckAction_accountCheckAdd.action" method="post"
                      enctype="multipart/form-data" id="myform" onsubmit="return check()">
                    <tr>
                        <td>
                            <label for="">
                                银行回单会计流水号
                            </label>
                        </td>
                        <td>
                            <input type="text" class="form-control" id="duizhangdanhao"
                                   name="accountCheck.accountNumber"/>
                            <font style="color: red; size: 4px;" size="4px">*</font>
                        </td>
                        <td>

                        </td>
                        <td>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="">
                            类型
                        </label></td>
                        <td><select class="form-control" id="acctype"
                                    name="accountCheck.accountCategory">
                            <option value="付款">
                                付款
                            </option>
                            <option value="收款">
                                收款
                            </option>
                        </select></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="button" value="点击选择" onclick="selectRT()"></td>
                    </tr>
                    <%--<tr>--%>
                        <%--<td><label for="">--%>
                            <%--金额--%>
                        <%--</label></td>--%>
                        <%--<td><input type="text"   name=""/></td>--%>
                    <%--</tr>--%>
                    <tr>
                        <td><label for="">
                            金额
                        </label></td>
                        <td><input type="text" id="allMoney"  name="accountCheck.allMoney" readonly="readonly"/></td>
                    </tr>
                    <%--<tr>--%>
                        <%--<td><label for="">--%>
                            <%--已付金额--%>
                        <%--</label></td>--%>
                        <%--<td>--%>
                            <%--<input type="hidden" id="accountPaid"  name="accountCheck.accountPaid" readonly="readonly"/></td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td><label for="">--%>
                            <%--未付金额--%>
                        <%--</label></td>--%>
                        <%--<td>--%>
                            <%--<input type="hidden" id="unPay"  name="accountCheck.unPay" readonly="readonly"/></td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td><label for="">--%>
                            <%--付款中金额--%>
                        <%--</label></td>--%>
                        <%--<td>--%>
                            <%--<input type="hidden"  id="payIng" name="accountCheck.payIng" readonly="readonly"/></td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td><label for="">--%>
                            <%--申请中金额--%>
                        <%--</label></td>--%>
                        <%--<td>--%>
                            <%--<input type="hidden" id="payOn"  name="accountCheck.payOn" readonly="readonly"/></td>--%>
                    <%--</tr>--%>
                    <tr>
                        <td>
                            <label for="">
                                收款单位
                            </label>
                        </td>
                        <td>
                            <input type="text" class="form-control" id="accountUnit"  readonly="readonly"
                                   name="accountCheck.accountUnit"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="">
                                经办人
                            </label>
                        </td>
                        <td>

                            <input type="text" class="form-control" id="addUserName"  readonly="readonly"
                                   name="accountCheck.accountUsersName"/>
                            <input type="hidden" class="form-control" id="addUserCode"  readonly="readonly"
                                   name="accountCheck.accountUsers"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="">
                                业务内容
                            </label>
                        </td>
                        <td>
                             <textarea class="form-control" rows="3"  readonly="readonly"
                                       name="accountCheck.accountDescription" id="summary"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="">
                                状态
                            </label>
                        </td>
                        <td>

                            <input type="text" class="form-control" id="state"  readonly="readonly"
                                   name="accountCheck.state"/>
                        </td>
                    </tr>
                    <tr>
                        <input type="hidden" class="form-control" id="rid"  readonly="readonly"
                               name="accountCheck.receiptLogid"/>
                        <td colspan="2" align="center">
                            <button type="submit" class="btn btn-default" id="taskadd">
                                添加
                            </button>
                            <%--<input type="button" id="item" value="提交"--%>
                                   <%--onclick="checkAndSubmit()" />--%>
                        </td>
                    </tr>


                </form>
            </table>
        </div>
    </div>
</div>


</body>
<script type="text/javascript">

    //查找档案信息弹出层
    function selectRT() {
        if($("#acctype").val()=='付款'){
            var url = "AccountCheckAction_findReceiptLogListforAccountCheck.action";
        }
        if($("#acctype").val()=='收款'){
            var url = "AccountCheckAction_findNoncoreReceiveList.action?tag=all";
        }

        $("#showProcess").attr("src",
            url);
        chageDiv('block');
    }

    function check() {
        var dzd = document.getElementById("duizhangdanhao");
        if (dzd.value == "") {
            alert("对账单号不能为空");
            dzd.focus();
            return false;
        }else {
            return true;
        }
    }

    // function checkAndSubmit() {
    //     document.getElementById("item").disabled = true;
    //     var myForm = document.getElementById("myForm");
    //     myForm.submit();
    // }


</script>


</html>
