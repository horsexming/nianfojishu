<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
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
        <div class="row clearfix">
            <h1 align="center">
                新供应商认证列表
            </h1>

            <div class="col-md-12 column">
                <table class="table">
                    <thead>
                    <tr>
                        <th>
                            序号
                        </th>
                        <th>
                            供应商名称
                        </th>
                        <th>
                            申请人
                        </th>
                        <th>
                            申请日期
                        </th>
                        <th>
                            状态
                        </th>
                        <th>
                            操作
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="supplierCertifications" id="sC" status="Status0">
                        <tr>
                            <td>
                                    ${Status0.index+1}
                            </td>
                            <td>
                                    ${sC.supplierName}
                            </td>
                            <td>
                                    ${sC.applyUserName}
                            </td>
                            <td>
                                    ${sC.applyDate}
                            </td>
                            <td>
                                    ${sC.state}
                            </td>
                            <td>
                                <a href="SupplierCertificationAction_findSupplierCertification.action?id=${sC.id}">查看</a>
                                <a href="SupplierCertificationAction_deleteSupplierCertification.action?id=${sC.id}">删除</a>
                            </td>
                        </tr>
                    </s:iterator>
                    <tr>
                        <s:if test="errorMessage==null">
                            <td colspan="22" align="right">
                                第
                                <font color="red"><s:property value="cpage"/> </font> /
                                <s:property value="total"/>
                                页
                                <fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
                                             styleClass="page" theme="number"/>
                            </td>
                        </s:if>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<%@include file="/util/foot.jsp" %>
<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">

</script>
</body>
</html>
