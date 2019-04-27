<%--
  Created by IntelliJ IDEA.
  User: fy
  Date: 2018/6/22
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <script
            src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js">
    </script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js">
    </script>
    <style type="text/css">
    </style>
</head>
<body>
<title>新供应商开发认证</title>
<div class="container">
    <div class="row clearfix">
        <h1>
            新供应商开发认证
        </h1>
        <div class="col-md-12 column">
            <div class="col-md-12 column">
                创建于：${supplierCertification.applyDate}
            </div>
            <br>
            <div class="col-md-12 column">
                当前处理人：
                <s:iterator value="supplierCertificationUsers" id="scUsers" status="Status0">
                    ${scUsers.name}
                    <input type="hidden" class="usrsid" value="${scUsers.id}">
                </s:iterator>
                <%--${supplierCertification.state}--%>

            </div>
            <br>
            <div class="col-md-12 column">
                当前状态：${supplierCertification.state}
            </div>
            <br>
            <div class="tabbable" id="tabs-241818">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#panel-000001" data-toggle="tab">供应商开发申请</a>
                    </li>
                    <li>
                        <a href="#panel-000002" data-toggle="tab">部门主管审核</a>
                    </li>
                    <li>
                        <a href="#panel-000003" data-toggle="tab">采购总监审核</a>
                    </li>
                    <li>
                        <a href="#panel-000004" data-toggle="tab">公司领导核准</a>
                    </li>
                    <li class="">
                        <a href="#panel-000005" data-toggle="tab">供应商信息调查</a>
                    </li>
                    <%--<li class="">--%>
                    <%--<a href="#panel-000006" data-toggle="tab">审核小组现场审核</a>--%>
                    <%--</li>--%>
                    <%--<li class="">--%>
                    <%--<a href="#panel-000007" data-toggle="tab">合同签订</a>--%>
                    <%--</li>--%>
                    <%--<li class="">--%>
                    <%--<a href="#panel-000008" data-toggle="tab">采购总监审核</a>--%>
                    <%--</li>--%>
                    <li class="">
                        <a href="#panel-000009" data-toggle="tab">公司领导批示</a>
                    </li>
                    <li class="">
                        <a href="#panel-000010" data-toggle="tab">合格供应商录入</a>
                    </li>
                    <li class="">
                        <a href="#panel-000011" data-toggle="tab">流程纪录</a>
                    </li>

                </ul>
                <%--<div class="row clearfix">--%>
                <%--<div class="col-md-12 column">--%>
                <div class="tab-content">
                    <div class="tab-pane active" id="panel-000001">
                        <form role="form" action="SupplierCertificationAction_AddSupplierCertification.action"
                              method="post">
                            <div class="form-group">
                                <div class="form-group">
                                    <label for="">供应商名称</label><input type="text" class="form-control" id=""
                                                                      name="supplierCertification.supplierName"
                                                                      value="${supplierCertification.supplierName}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="form-group">
                                    <label for="">地址</label><input type="text" class="form-control" id=""
                                                                   name="supplierCertification.supplierAddress"
                                                                   value="${supplierCertification.supplierAddress}"/>
                                </div>
                            </div>
                            <div class="form-inline form-group">
                                <div class="form-group">
                                    <label for="">供应类型</label><input type="text" class="form-control" id=""
                                                                     name="supplierCertification.supplierType"
                                                                     value="${supplierCertification.supplierType}"/>
                                </div>
                                <div class="form-group">
                                    <label for="">联系人员</label><input type="text" class="form-control" id=""
                                                                     name="supplierCertification.supplierTel"
                                                                     value="${supplierCertification.supplierTel}"/>
                                </div>
                                <div class="form-group">
                                    <label for="">联系电话</label><input type="text" class="form-control" id=""
                                                                     name="supplierCertification.supplierContact"
                                                                     value="${supplierCertification.supplierContact}"/>
                                </div>
                            </div>
                            <div class="form-inline form-group">
                                <%--<div class="form-group">--%>
                                <%--<label for="">申请人</label><input type="text" class="form-control" id=""--%>
                                <%--name="supplierCertification.applyUser"--%>
                                <%--value="${supplierCertification.applyUser}"/>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label for="">申请部门</label><input type="text" class="form-control" id=""
                                                                     name="supplierCertification.applyDept"
                                                                     value="${supplierCertification.applyDept}"/>
                                </div>
                                <div class="form-group">
                                    <label for="">申请日期</label><input type="date" class="form-control" id=""
                                                                     name="supplierCertification.applyDate"
                                                                     value="${supplierCertification.applyDate}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="">申请原因描述</label><input type="text" class="form-control" id=""
                                                                   name="supplierCertification.applyReason"
                                                                   value="${supplierCertification.applyReason}"/>
                            </div>

                            <div class="form-group">
                                <button type="submit" class="btn btn-default submit">提交</button>

                            </div>

                        </form>
                    </div>
                    <div class="tab-pane" id="panel-000002">
                        <form action="SupplierCertificationAction_updateSupplierCertification.action"
                              method="post">
                            <div class="form-group">
                                <label for="">审核意见</label>
                                <input type="hidden" name="SupplierCertification_id"
                                       value="${supplierCertification.id}">
                                <input type="hidden" name="supplierCertificationReviewContent.reviewLevel"
                                       value="部门主管审核">
                                <div class="radio">
                                    <label>
                                        <input type="radio"
                                               name="supplierCertificationReviewContent.reviewOpinion"
                                               value="true" checked="${supplierCertificationReviewContents[0].reviewOpinion}">
                                        同意
                                    </label>
                                </div>
                                <div class="radio">
                                    <label>
                                        <input type="radio"
                                               name="supplierCertificationReviewContent.reviewOpinion"
                                               value="false">
                                        不同意
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="">意见说明</label><input type="text" class="form-control" id=""
                                                                 name="supplierCertificationReviewContent.reviewDetail"
                                                                 value="${supplierCertificationReviewContents[0].reviewDetail}">
                            </div>
                            <%--<div class="form-group">--%>
                            <%--<label for="">抄送</label><input type="text" class="form-control" id=""/>--%>
                            <%--</div>--%>
                            <div class=" form-group">
                                <button type="submit" class="btn btn-default submit">提交</button>
                            </div>
                        </form>
                        <s:iterator value="supplierCertificationReviewContents" id="scCon" status="Status0">
                            <s:if test="#scCon.reviewLevel=='部门主管审核'">
                                ${scCon.reviewLevel}  :  ${reviewUsers[0].name} &nbsp&nbsp&nbsp&nbsp于 &nbsp&nbsp&nbsp&nbsp&nbsp     ${scCon.reviewDate}&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp审批结果:
                                <s:if test="#scCon.reviewOpinion=='true'">
                                    同意
                                </s:if>
                                <s:else>
                                    打回
                                </s:else>
                            </s:if>
                        </s:iterator>
                    </div>
                    <div class="tab-pane" id="panel-000003">
                        <form action="SupplierCertificationAction_updateSupplierCertification.action"
                              method="post">
                            <div class="form-group">
                                <label for="">审核意见</label>
                                <input type="hidden" name="SupplierCertification_id"
                                       value="${supplierCertification.id}">
                                <input type="hidden" name="supplierCertificationReviewContent.reviewLevel"
                                       value="采购总监审核">
                                <div class="radio">
                                    <label>
                                        <input type="radio"
                                               name="supplierCertificationReviewContent.reviewOpinion"
                                               value="true" checked="${supplierCertificationReviewContents[1].reviewOpinion}">
                                        同意
                                    </label>
                                </div>
                                <div class="radio">
                                    <label>
                                        <input type="radio"
                                               name="supplierCertificationReviewContent.reviewOpinion"
                                               value="false">
                                        不同意
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="">意见说明</label><input type="text" class="form-control" id=""
                                                                 name="supplierCertificationReviewContent.reviewDetail"
                                                                 value="${supplierCertificationReviewContents[1].reviewDetail}"/>
                            </div>
                            <%--<div class="form-group">--%>
                            <%--<label for="">抄送</label><input type="text" class="form-control" id=""/>--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <button type="submit" class="btn btn-default submit">提交</button>
                            </div>
                        </form>
                        <s:iterator value="supplierCertificationReviewContents" id="scCon" status="Status0">
                            <s:if test="#scCon.reviewLevel=='采购总监审核'">
                                ${scCon.reviewLevel}  :${reviewUsers[1].name}  &nbsp&nbsp&nbsp&nbsp于 &nbsp&nbsp&nbsp&nbsp&nbsp     ${scCon.reviewDate}&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp审批结果:
                                <s:if test="#scCon.reviewOpinion=='true'">
                                    同意
                                </s:if>
                                <s:else>
                                    打回
                                </s:else>
                            </s:if>
                        </s:iterator>
                    </div>
                    <div class="tab-pane" id="panel-000004">
                        <form action="SupplierCertificationAction_updateSupplierCertification.action"
                              method="post">
                            <div class="form-group">
                                <label for="">审核意见</label>
                                <input type="hidden" name="SupplierCertification_id"
                                       value="${supplierCertification.id}">
                                <input type="hidden" name="supplierCertificationReviewContent.reviewLevel"
                                       value="公司领导核准">
                                <div class="radio">
                                    <label>
                                        <input type="radio"
                                               name="supplierCertificationReviewContent.reviewOpinion"
                                               value="true" checked="${supplierCertificationReviewContents[2].reviewOpinion}">
                                        同意
                                    </label>
                                </div>
                                <div class="radio">
                                    <label>
                                        <input type="radio"
                                               name="supplierCertificationReviewContent.reviewOpinion"
                                               value="false">
                                        不同意
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="">意见说明</label><input type="text" class="form-control" id=""
                                                                 name="supplierCertificationReviewContent.reviewDetail"
                                                                 value="${supplierCertificationReviewContents[2].reviewDetail}"/>
                            </div>
                            <%--<div class="form-group">--%>
                            <%--<label for="">抄送</label><input type="text" class="form-control" id=""/>--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <button type="submit" class="btn btn-default submit">提交</button>
                            </div>
                        </form>
                        <s:iterator value="supplierCertificationReviewContents" id="scCon" status="Status0">
                            <s:if test="#scCon.reviewLevel=='公司领导核准'">
                                ${scCon.reviewLevel}  : ${reviewUsers[2].name} &nbsp&nbsp&nbsp&nbsp于 &nbsp&nbsp&nbsp&nbsp&nbsp     ${scCon.reviewDate}&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp审批结果:
                                <s:if test="#scCon.reviewOpinion=='true'">
                                    同意
                                </s:if>
                                <s:else>
                                    打回
                                </s:else>
                            </s:if>
                        </s:iterator>
                    </div>
                    <div class="tab-pane" id="panel-000005">
                        <form action="SupplierCertificationAction_updateSupplierCertificationAttachment.action"
                              method="post" enctype="multipart/form-data">
                            <input type="hidden" name="SupplierCertification_id"
                                   value="${supplierCertification.id}">
                            <div class="form-group">
                                <label for="">处理说明</label><input type="text" class="form-control" id=""
                                                                 name="supplierCertification.info"
                                                                 value="${supplierCertification.info}"/>
                            </div>
                            <div class="form-group">
                                <label for="">附件</label>
                                <input type="file" id="" name="infoFile"/>
                            </div>
                            <%--<div class="form-group">--%>
                            <%--<label for="">供应商开发评估小组ID</label><input type="text" class="form-control" id=""/>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                            <%--<label for="">采购总监ID</label><input type="text" class="form-control" id=""/>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                            <%--<label for="">公司领导ID</label><input type="text" class="form-control" id=""/>--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <button type="submit" class="btn btn-default submit">提交</button>
                            </div>
                        </form>
                    </div>
                    <%--<div class="tab-pane" id="panel-000006">--%>
                    <%--审核小组现场审核--%>
                    <%--</div>--%>
                    <div class="tab-pane" id="panel-000007">
                        <div class="form-group">
                            <form action="SupplierCertificationAction_updateSupplierCertification.action">
                                <label for="">审核意见</label>
                                <input type="hidden" name="SupplierCertification_id"
                                       value="${supplierCertification.id}">
                                <input type="hidden" name="supplierCertificationReviewContent.reviewLevel"
                                       value="公司领导批示">
                                <div class="radio">
                                    <label>
                                        <input type="radio"
                                               name="supplierCertificationReviewContent.reviewOpinion"
                                               value="true" checked="${supplierCertificationReviewContents[0].reviewOpinion}">
                                        同意
                                    </label>
                                </div>
                                <div class="radio">
                                    <label>
                                        <input type="radio"
                                               name="supplierCertificationReviewContent.reviewOpinion"
                                               value="false">
                                        不同意
                                    </label>
                                </div>
                            </form>
                            <s:iterator value="supplierCertificationReviewContents" id="scCon" status="Status0">
                                <s:if test="#scCon.reviewLevel=='公司领导批示'">
                                    ${scCon.reviewLevel}  : ${reviewUsers[3].name} &nbsp&nbsp&nbsp&nbsp于 &nbsp&nbsp&nbsp&nbsp&nbsp     ${scCon.reviewDate}&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp审批结果:
                                    <s:if test="#scCon.reviewOpinion=='true'">
                                        同意
                                    </s:if>
                                    <s:else>
                                        打回
                                    </s:else>
                                </s:if>
                            </s:iterator>
                        </div>
                        <div class="form-group">
                            <label for="">意见说明</label><input type="text" class="form-control" id=""/>
                        </div>
                        <%--<div class="form-group">--%>
                        <%--<label for="">抄送</label><input type="text" class="form-control" id=""/>--%>
                        <%--</div>--%>
                        <div class="form-group">
                            <button type="submit" class="btn btn-default submit">提交</button>
                        </div>
                    </div>
                    <div class="tab-pane" id="panel-000008">
                        <form action="SupplierCertificationAction_updateSupplierCertification.action"
                              method="post">
                            <div class="form-group">
                                <label for="">审核意见</label>
                                <div class="radio">
                                    <label>
                                        <input type="radio"
                                               name="supplierCertificationReviewContent.reviewOpinion"
                                               value="true" checked="${supplierCertificationReviewContents[0].reviewOpinion}">
                                        同意
                                    </label>
                                </div>
                                <div class="radio">
                                    <label>
                                        <input type="radio"
                                               name="supplierCertificationReviewContent.reviewOpinion"
                                               value="false">
                                        不同意
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="">意见说明</label><input type="text" class="form-control" id=""/>
                            </div>
                            <%--<div class="form-group">--%>
                            <%--<label for="">抄送</label><input type="text" class="form-control" id=""/>--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <button type="submit" class="btn btn-default submit">提交</button>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane" id="panel-000009">
                        <form action="SupplierCertificationAction_updateSupplierCertification.action"
                              method="post">
                            <input type="hidden" name="SupplierCertification_id"
                                   value="${supplierCertification.id}">
                            <input type="hidden" name="supplierCertificationReviewContent.reviewLevel"
                                   value="公司领导批示">
                            <div class="form-group">
                                <label for="">审核意见</label>
                                <div class="radio">
                                    <label>
                                        <input type="radio"
                                               name="supplierCertificationReviewContent.reviewOpinion"
                                               value="true" checked="${supplierCertificationReviewContents[3].reviewOpinion}">
                                        同意
                                    </label>
                                </div>
                                <div class="radio">
                                    <label>
                                        <input type="radio"
                                               name="supplierCertificationReviewContent.reviewOpinion"
                                               value="false">
                                        不同意
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="">意见说明</label><input type="text" class="form-control" id=""
                                                                 name="supplierCertificationReviewContent.reviewDetail"
                                                                 value="${supplierCertificationReviewContents[3].reviewDetail}"/>
                            </div>
                            <div class="form-group">
                                <a href="FileViewAction.action?FilePath=/upload/file/gysCertification/${supplierCertification.info_attachment}" target="_blank">查看附件</a>
                            </div>
                            <%--<div class="form-group">--%>
                            <%--<label for="">抄送</label><input type="text" class="form-control" id=""/>--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <button type="submit" class="btn btn-default submit">提交</button>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane" id="panel-000010">
                            <div class="form-group">
                                <button class="btn btn-default "
                                        onclick="window.location.href='zhaobiaoAction!toaddUser.action?id=${supplierCertification.id}'">点击录入
                                </button>
                                <button class="btn btn-default "
                                        onclick="window.location.href='SupplierCertificationAction_overSupplierCertification.action?id=${supplierCertification.id}'">录入完成
                                </button>
                            </div>
                    </div>
                    <div class="tab-pane" id="panel-000011">

                        <s:iterator value="supplierCertificationReviewContents" id="scCon" status="Status0">
                            ${scCon.reviewLevel}  : ${reviewUsers[Status0.index].name} &nbsp&nbsp&nbsp&nbsp    于 &nbsp&nbsp&nbsp&nbsp&nbsp     ${scCon.reviewDate}&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp审批结果:
                                 <s:if test="#scCon.reviewOpinion=='true'">
                                       同意
                                </s:if>
                                <s:else>
                                    打回
                                </s:else>
                            <br>
                        </s:iterator>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="application/javascript">
    $(document).ready(function () {
        $(":enabled").attr("disabled", "disabled")

        if ("${supplierCertification.state}" == "") {
            $("#panel-000001 form input,#panel-000001 form div input,#panel-000001 form div button").removeAttr("disabled");
        }
        if ("${supplierCertification.state}" == "部门主管审核") {
            $("#panel-000002 form input,#panel-000002 form div input,#panel-000002 form div button").removeAttr("disabled");
        }
        if ("${supplierCertification.state}" == "采购总监审核") {
            $("#panel-000003 form input,#panel-000003 form div input,#panel-000003 form div button").removeAttr("disabled");
        }
        if ("${supplierCertification.state}" == "公司领导核准") {
            $("#panel-000004 form input,#panel-000004 form div input,#panel-000004 form div button").removeAttr("disabled");
        }
        if ("${supplierCertification.state}" == "供应商信息调查") {
            $("#panel-000005 form input,#panel-000005 form div input,#panel-000005 form div button").removeAttr("disabled");
        }
        if ("${supplierCertification.state}" == "公司领导批示") {
            $("#panel-000009 form input,#panel-000009 form div input,#panel-000009 form div button").removeAttr("disabled");
        }
        if ("${supplierCertification.state}" == "合格供应商录入") {
            $("#panel-000010 form input,#panel-000010 form div input,#panel-000010 div button").removeAttr("disabled");
        }


        //是否为处理人
        var numArr = [];
        var txt = $(".usrsid");
        for (var i = 0; i < txt.length; i++) {
            numArr.push(txt.eq(i).val()); // 将文本框的值添加到数组中
        }

        //判断是否存在于
        Array.prototype.in_array = function (e) {
            var r = new RegExp(',' + e + ',');
            return (r.test(',' + this.join(this.S) + ','));
        };
        if (!numArr.in_array("${sessionScope.Users.id}") && "${supplierCertification.state}" != "") {
            $(":enabled").attr("disabled", "disabled");
        }


        //是否已经设置审批人员

        if ("${pageStatus}"=="false") {
            $(":enabled").attr("disabled", "disabled");
            alert("未设置审批人员");
        }

    });


</script>
</body>
</html>