<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <STYLE type="text/css">
        .ztree li a {
            color: #fff;
        }
    </STYLE>
</head>
<body>
<div id="bodyDiv" align="center" class="transDiv" style="z-index: 2"
     onclick="chageDiv('none')">
</div>
<div id="contentDiv"
     style="position: absolute; z-index: 255; width: 900px; display: none; top: 20px;"
     align="center">
    <div id="closeDiv"
         style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
        <div id="submitProcessDiv" style="display: none;">
            <table style="width: 100%; margin-top: ">
                <tr>
                    <td>
                        您正在上传发票:
                    </td>
                    <td align="right">
                        <img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
                             height="32" onclick="chageDiv('none');reload();">
                    </td>
                </tr>
            </table>
            <div id="operatingDiv"
                 style="background-color: #ffffff; width: 100%;">
                <form action="CorePayableAction!uploadFapiao.action"
                      enctype="multipart/form-data" method="post"
                      onsubmit="return validate()">
                    <input type="hidden" id="coreId" name="corePayable.id" />
                    <table class="table" style="width: 40%" align="center">
                        <tr>
                            <th align="right">
                                发票号:
                            </th>
                            <th align="left">
                                <input name="corePayable.fapiaoNum" />
                            </th>
                        </tr>
                        <tr>
                            <th align="right">
                                发票附件:
                            </th>
                            <th align="left">
                                <input name="attachment" type="file" />
                            </th>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <input type="submit" value="提交"
                                       style="width: 65px; height: 40px;" />
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="/util/sonTop.jsp"%>
<div id="gongneng" style="width: 100%; margin-top: 10px;">
    <div align="center">
        <h3>
            待付款账单管理
            <br />
            <s:if test="successMessage!=null">
                <font color="red">${successMessage}</font>
            </s:if>
        </h3>
        <form action="AccountCheckAction_findReceiptList.action" method="post">
            <input name="pageStatus" value="${pageStatus}" type="hidden">
            <table class="table">
                <tr>
                    <td>
                        收款单位
                    </td>
                    <td>
                        <input value="${receipt.payee}" name="receipt.payee">
                    </td>
                    <td>
                        摘要
                    </td>
                    <td>
                        <input value="${receipt.summary}" name="receipt.summary">
                    </td>
                    <td>
                        状态
                    </td>
                    <td>
                        <select name="receipt.status">
                            <option value="${receipt.status}">
                                ${receipt.status}
                            </option>
                            <option>
                            </option>
                            <option>
                                待付款
                            </option>
                            <option>
                                审批中
                            </option>
                            <option>
                                付款中
                            </option>
                            <option>
                                完成
                            </option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th colspan="6">
                        <input type="submit" value="查询" class="input" />
                    </th>
                </tr>
            </table>
        </form>
        <s:if test="pageStatus=='auditPay'">
        <form action="ReceiptAction!auditPay.action" method="post"
              onsubmit="return validate()">
            </s:if>
            <s:elseif test="pageStatus=='dfk'">
            <form action="ReceiptAction!applyForPay.action" method="post"
                  onsubmit="return window.confirm('您确定要申请付款吗？')">
                </s:elseif>
                <table class="table">
                    <s:if test="pageStatus=='dfk'">
                        <tr>
                            <th colspan="25">
                                <s:if test="receipt.fukuanMonth!=null">
                                    <s:if test='qrTag=="true"'>
                                        <input type="submit" value="申请付款" class="input">
                                    </s:if>
                                    <s:else>
                                        <font color="red">未到付款日期，无法申请付款!</font>
                                    </s:else>
                                </s:if>
                                <s:else>
                                    <input type="submit" value="申请付款" class="input">
                                </s:else>
                            </th>
                        </tr>
                    </s:if>
                    <tr bgcolor="#c0dcf2" height="50px">
                        <%--<s:if
                                test="pageStatus=='dfk'||pageStatus=='dkp'||pageStatus=='gysdkp'">
                                --%>
                        <%--<td align="center">--%>
                            <%--<input type="hidden" name="pageStatus" value="${pageStatus}">--%>
                            <%--<input type="hidden" name="qrTag" id="qrTag" value="${qrTag}">--%>
                            <%--<input type="checkbox" id="checkId"--%>
                                   <%--onclick="chageAllCheck(this,'showCheckDetail')">--%>
                        <%--</td>--%>
                        <%--</s:if>
                            --%>
                        <td align="center">
                            序号
                        </td>
                        <td align="center">
                            付款单号
                        </td>
                        <td align="center">
                            类型
                        </td>
                        <td align="center">
                            收款单位
                        </td>
                        <td align="center">
                            摘要
                        </td>
                        <td align="center">
                            应付金额
                        </td>
                        <td align="center">
                            已付金额
                        </td>
                        <td align="center">
                            未付金额
                        </td>
                        <td align="center">
                            付款中金额
                        </td>
                        <td align="center">
                            申请中金额
                        </td>
                        <td align="center">
                            <s:if test="pageStatus=='auditPay'">
                                调整金额
                            </s:if>
                            <s:else>
                                待付款金额
                            </s:else>
                        </td>
                        <td align="center">
                            关联单据
                        </td>
                        <td align="center">
                            添加时间
                        </td>
                        <td align="center">
                            付款周期(天)
                        </td>
                        <td align="center">
                            应付款日期
                        </td>
                        <td align="center">
                            状态
                        </td>
                        <td align="center">
                            操作
                        </td>
                    </tr>
                    <s:iterator value="receiptList" id="pagecoreReceipt"
                                status="pageIndex">
                    <s:if test="#pageIndex.index%2==1">
                    <tr align="center" bgcolor="#e6f3fb"
                        onmouseover="chageBgcolor(this)"
                        onmouseout="outBgcolor(this,'#e6f3fb')">
                        </s:if>
                        <s:else>
                    <tr align="center" onmouseover="chageBgcolor(this)"
                        style="height: 25px;" onmouseout="outBgcolor(this,'')">
                        </s:else>
                        <%--<td>--%>
                            <%--<s:if test='pageStatus=="dfk"'>--%>
                                <%--<s:if test='#pagecoreReceipt.status=="待付款"'>--%>
                                    <%--<input type="checkbox"--%>
                                           <%--onclick="chageNum(this,'showCheckDetail');" name=""--%>
                                           <%--value="${pagecoreReceipt.id}"--%>
                                           <%--data="${pagecoreReceipt.payLast-pagecoreReceipt.payIng}" data2="${pageIndex.index}">--%>
                                <%--</s:if>--%>
                            <%--</s:if>--%>
                            <%--<s:elseif test="pageStatus=='auditPay'">--%>
                                <%--<input type="checkbox"--%>
                                       <%--onclick="chageNum(this,'showCheckDetail')" name=""--%>
                                       <%--value="${pagecoreReceipt.id}" data="${pagecoreReceipt.payOn}"--%>
                                       <%--data2="${pageIndex.index}">--%>
                            <%--</s:elseif>--%>
                            <%--<s:else>--%>
                                <%--<input type="checkbox"--%>
                                       <%--onclick="chageNum(this,'showCheckDetail')" name=""--%>
                                       <%--value="${pagecoreReceipt.id}"--%>
                                       <%--data="${pagecoreReceipt.allMoney}" data2="${pageIndex.index}">--%>
                            <%--</s:else>--%>
                        <%--</td>--%>
                        <td>
                            <s:property value="#pageIndex.index+1" />
                        </td>
                        <td>
                                ${pagecoreReceipt.pkNumber}
                        </td>
                        <td>
                                ${pagecoreReceipt.payType}
                        </td>
                        <td align="left">
                            <a
                                    href="CorePayableAction!findSupplierCorePayableList.action?supplierCorePayable.supplierId=${pagecoreReceipt.payeeId}">
                                    ${pagecoreReceipt.payee} </a>
                        </td>
                        <td align="left">
                                ${pagecoreReceipt.summary}
                        </td>
                        <td align="right">
                            <fmt:formatNumber type="number"
                                              value="${pagecoreReceipt.allMoney}" pattern="0.00"
                                              maxFractionDigits="2" />
                        </td>
                        <td align="right">
                            <fmt:formatNumber type="number"
                                              value="${pagecoreReceipt.accountPaid}" pattern="0.00"
                                              maxFractionDigits="2" />
                        </td>
                        <td>
                            <fmt:formatNumber type="number" value="${pagecoreReceipt.unPay}"
                                              pattern="0.00" maxFractionDigits="2" />
                        </td>
                        <td align="right">
                            <fmt:formatNumber type="number" value="${pagecoreReceipt.payIng}"
                                              pattern="0.00" maxFractionDigits="2" />
                        </td>
                        <td align="right">
                            <fmt:formatNumber type="number" value="${pagecoreReceipt.payOn}"
                                              pattern="0.00" maxFractionDigits="2" />
                        </td>
                        <td style="color: red;">
                            <s:if test="pageStatus=='dfk'">
                                <input name="" id="payOn_${pageIndex.index}"
                                       value="<fmt:formatNumber type="number"
								value="${pagecoreReceipt.payLast-pagecoreReceipt.payIng}" pattern="0.00"
								maxFractionDigits="2" />" />
                            </s:if>
                            <s:elseif test="pageStatus=='auditPay'">
                                <input name="" id="payIng_${pageIndex.index}"
                                       value="<fmt:formatNumber type="number"
								value="${pagecoreReceipt.payOn}" pattern="0.00"
								maxFractionDigits="2" />" />
                            </s:elseif>
                            <s:else>
                                <fmt:formatNumber type="number"
                                                  value="${pagecoreReceipt.payLast}" pattern="0.00"
                                                  maxFractionDigits="2" />
                            </s:else>
                        </td>
                        <td>
                            <s:if test="#pagecoreReceipt.fk_fundApplyId>0">
                                <a
                                        href="FundApplyAction_findfundDetailedList.action?id=${pagecoreReceipt.fk_fundApplyId}">${pagecoreReceipt.aboutNum}</a>
                            </s:if>
                            <s:else>
                                <a
                                        href="CorePayableAction!findCorePaybleList.action?corePayable.fk_CPMId=${pagecoreReceipt.fk_monthlyBillsId}&pageStatus=all">${pagecoreReceipt.aboutNum}</a>
                            </s:else>
                        </td>
                        <td>
                                ${pagecoreReceipt.addTime}
                        </td>
                        <td>
                                ${pagecoreReceipt.paymentCycle}
                        </td>
                        <td>
                                ${pagecoreReceipt.fukuanDate}
                        </td>
                        <td>
                                ${pagecoreReceipt.status}
                        </td>
                        <td>
                            <%--<a href="ReceiptAction!findReceiptLogList.action?receiptLog.receipt.id=${pagecoreReceipt.id}">付款明细</a>--%>
                                <input type="button" value="选择"
                                       style="width: 50px; height: 31px;"
                                       onclick="selectAcheck('${pagecoreReceipt.id}','${pagecoreReceipt.payee}','${pagecoreReceipt.summary}'
                                               ,'${pagecoreReceipt.allMoney}'
                                               ,'${pagecoreReceipt.accountPaid}'
                                               ,'${pagecoreReceipt.unPay}'
                                               ,'${pagecoreReceipt.payIng}'
                                               ,'${pagecoreReceipt.payOn}'
                                               ,'${pagecoreReceipt.payLast}'
                                               ,'${pagecoreReceipt.addUserName}'
                                               ,'${pagecoreReceipt.addUserCode}'

                                               )" />
                        </td>
                        </s:iterator>
                    <tr>
                        <td colspan="25" align="left" style="color: red; font-size: 20px;">
                            <span id="showCheckDetail"></span>
                            <div id="div_bank" style="display: none;">
                                <span>银行账户:</span>
                                <div class="zTreeDemoBackground left">
                                    <ul class="list">
                                        <li class="title">
                                            <input id="wgType" type="text" readonly="readonly" value=""
                                                   style="width: 120px;" name="" />
                                            <input type="hidden" value="" id="subId" name="subId" />
                                            <input type="hidden" value="" id="fatherSubName"  />
                                            <a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>(按住Ctrl建不松点击,可清空)
                                        </li>
                                    </ul>
                                </div>
                                <div id="menuContent" class="menuContent"
                                     style="display: none; position: absolute;">
                                    <ul id="treeDemo" class="ztree"
                                        style="margin-top: 0; width: 160px;"></ul>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <s:if test="pageStatus=='dfk'">
                        <tr>
                            <th colspan="25">
                                <s:if test="corePayable.jzMonth!=null">
                                    <s:if test='qrTag=="true"'>
                                        <input type="submit" value="申请付款" class="input">
                                    </s:if>
                                    <s:else>
                                        <font color="red">未到付款日期，无法申请付款!</font>
                                    </s:else>
                                </s:if>
                                <s:else>
                                    <input type="submit" value="申请付款" class="input">
                                </s:else>
                            </th>
                        </tr>
                    </s:if>
                    <s:elseif test="pageStatus=='auditPay'">
                        <tr>
                            <th colspan="25">
                                <input type="submit" value="确认付款" class="input">
                            </th>
                        </tr>
                    </s:elseif>
                    <tr>
                        <s:if test="errorMessage==null">
                        <td colspan="25" align="right">
                            第
                            <font color="red"><s:property value="cpage" /> </font> /
                                <s:property value="total" />
                            页
                                <fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
                                             styleClass="page" theme="number" />
                            </s:if>
                            <s:else>
                        <td colspan="20" align="center" style="color: red">
                                ${errorMessage}
                        </td>
                        </s:else>
                    </tr>
                </table>
            </form>
    </div>
</div>
<%@include file="/util/foot.jsp"%>
<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
    //财务确认
    function addFapiao(coreId) {
        $("#coreId").val(coreId);
        $("#submitProcessDiv").show();
        chageDiv("block");
        //单独设置弹出层的高度
        var thisTopHeight = $(obj).offset().top;
        $('#contentDiv').css( {
            'top' : thisTopHeight + 'px'
        });
    }

    function tosubmitNo(obj) {
        $("#qrTag").val("no");
        obj.submit();
    }

    var pageStatus = "${pageStatus}";
    function chageName(obj, index) {
        if (obj.checked) {
            obj.name = "receiptList[" + index + "].id";
            if (pageStatus == "dfk") {
                document.getElementById("payOn_" + index).name = "receiptList["
                    + index + "].payOn";
            } else {
                document.getElementById("payIng_" + index).name = "receiptList["
                    + index + "].payIng";
            }
        } else {
            obj.name = "";
            if (pageStatus == "dfk") {
                document.getElementById("payOn_" + index).name = "receiptList["
                    + index + "].payOn";
            } else {
                document.getElementById("payIng_" + index).name = "receiptList["
                    + index + "].payIng";
            }
        }
    }

    function chageAllCheck(obj, numId) {
        var inputs = document.getElementsByTagName("input");
        for ( var i = 0; i < inputs.length; i++) {
            if (inputs[i].type == "checkbox") {
                var checkBox = inputs[i];
                if (checkBox.checked != obj.checked) {
                    checkBox.checked = obj.checked;
                    if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
                        chageNum(checkBox, numId);
                    }
                }
            }
        }
    }
    var num = 0;
    var other = 0;
    function chageNum(obj, numId) {
        var check = obj;
        var checkAll = document.getElementById("checkAll");
        var checkAll2 = document.getElementById("checkAll2");
        if (checkAll == null) {
            checkAll = $(obj).parents("table").find("input[type='checkbox']")[0];
        }
        if (checkAll2 == null) {
            checkAll2 = $(obj).parents("table").find("input[type='checkbox']")[0];
        }
        if (check.checked == true) {
            var inputs = document.getElementsByTagName("input");
            var status = true;
            for ( var i = 0; i < inputs.length; i++) {
                if (inputs[i].type == "checkbox") {
                    var checkBox = inputs[i];
                    if (checkBox != checkAll2 && checkBox != checkAll) {
                        if (checkBox.checked == false) {
                            status = false;
                            break;
                        }
                    }
                }
            }
            if (status == true) {
                checkAll.checked = true;
                checkAll2.checked = true;
            }
            num++;
            if ($(obj).attr("data") != '') {
                other += parseFloat($(obj).attr("data"));
            }
        } else if (num == 0 && check.checked == false) {
            num = 0;
        } else {
            if (checkAll.checked == true || checkAll2.checked == true) {
                checkAll.checked = false;
                checkAll2.checked = false;
            }
            num--;
            if ($(obj).attr("data") != '') {
                other -= parseFloat($(obj).attr("data"));
            }
        }
        if (numId != "") {
            $("#" + numId).html("已选中:" + num + "条 总计：" + other.toFixed(2));
        }

        //处理name
        var index = $(obj).attr("data2");
        if (obj.checked) {
            obj.name = "receiptList[" + index + "].id";
            if (pageStatus == "dfk") {
                document.getElementById("payOn_" + index).name = "receiptList["
                    + index + "].payOn";
            } else {
                document.getElementById("payIng_" + index).name = "receiptList["
                    + index + "].payIng";
            }
        } else {
            obj.name = "";
            if (pageStatus == "dfk") {
                document.getElementById("payOn_" + index).name = "receiptList["
                    + index + "].payOn";
            } else {
                document.getElementById("payIng_" + index).name = "receiptList["
                    + index + "].payIng";
            }
        }
        $("#div_bank").show();
    }



    //下拉树形

    var mfzTree;
    var addzTree;
    var delzTree;
    var updatezTree;

    var id;
    var pId;
    var name;
    var setting = {
        view : {
            dblClickExpand : false
        },
        data : {
            simpleData : {
                enable : true
            }
        },
        callback : {
            beforeClick : beforeClick,
            onClick : onClick
        }
    };
    //读取树形数据
    $(document).ready(function() {
        parent.mfzTree();
    });
    var zNodes = [];
    parent.mfzTree = function() {
        $.ajax( {
            url : 'huikuanAction!findBankSub.action',
            type : 'post',
            dataType : 'json',
            cache : true,
            success : function(doc) {
                $(doc).each(function() {
                    zNodes.push( {
                        id : $(this).attr('id'),
                        pId : $(this).attr('fatherId'),
                        name : $(this).attr('name')+" 余额:"+$(this).attr('borrowJieyuMoney'),
                        fatherName:$(this).attr('fatherName'),
                        target : "main",
                        click : false
                    });

                });
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                zTree.expandAll(true);
            },
            error : function() {
                alert("服务器异常!");
            }
        });
    };
    function beforeClick(treeId, treeNode) {
        var check = (treeNode && !treeNode.isParent);
        if (!check) alert("只能选择最底层");
        return check;
    }

    function onClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
            .getSelectedNodes(), v = "";
        nodes.sort(function compare(a, b) {
            return a.id - b.id;
        });
        var id=0,fatherName='';
        for ( var i = 0, l = nodes.length; i < l; i++) {
            v = nodes[i].name;
            id = nodes[i].id;
            fatherName = nodes[i].fatherName;
        }
        //if (v.length > 0 ) v = v.substring(0, v.length-1);
        cityObj = $("#wgType").val(v);
        $("#subId").val(id);
        $("#fatherSubName").val(fatherName);
    }

    function showMenu() {
        var cityObj = $("#wgType");
        var cityOffset = $("#wgType").offset();
        $("#menuContent").css( {
            left : cityOffset.left + "px",
            top : cityOffset.top + cityObj.outerHeight() + "px"
        }).slideDown("fast");

        $("body").bind("mousedown", onBodyDown);
    }
    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuContent").length > 0)) {
            hideMenu();
        }
    }
    //下拉树形结束

    function validate(){
        var subId =	$("#subId").val();
        if(subId == ''){
            alert("请选择一个银行账户付款")
            return false;
        }
    }



    //选择 传回上级
    function selectAcheck(id, payee, summary, allMoney,accountPaid,unPay,payIng,payOn,payLast,addUserName,addUserCode) {
        parent.$("#rid").val(id);
        parent.$("#accountUnit").val(payee);
        parent.$("#summary").val(summary);
        parent.$("#allMoney").val(allMoney);
        parent.$("#accountPaid").val(accountPaid);
        parent.$("#unPay").val(unPay);
        parent.$("#payIng").val(payIng);
        parent.$("#payOn").val(payOn);
        parent.$("#addUserName").val(addUserName);
        parent.$("#addUserCode").val(addUserCode);
        parent.chageDiv('none');
    }


</script>
</body>
</html>
