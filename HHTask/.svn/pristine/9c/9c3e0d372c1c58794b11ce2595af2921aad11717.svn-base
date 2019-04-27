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
        <h1>
            供应商认证审核人员
        </h1>
        <form action="SupplierCertificationAction_updateSupplierCertificationLevel.action" method="post">
            <table class="table">
                <tr>
                    <td>
                        部门主管
                    </td>
                    <td>
                        <select id="deptname" style="width: 120px;"
                                onchange="userlist(0,1)">
                            <option value="0">
                                请选择部门
                            </option>

                        </select>
                        <select id="username" name="reviewerStr[0]" style="width: 150px;">
                            <option value="0">
                                请先选择部门
                            </option>
                        </select>
                    </td>
                    <td>
                        ${reviewerStr[0]}
                    </td>
                </tr>
                <tr>
                    <td>
                        采购总监
                    </td>
                    <td>
                        <select id="deptname1" style="width: 120px;"
                                onchange="userlist(0,2)">
                            <option value="0">
                                请选择部门
                            </option>

                        </select>
                        <select id="username1" name="reviewerStr[1]" style="width: 150px;">
                            <option value="0">
                                请先选择部门
                            </option>
                        </select>
                    </td>
                    <td>
                        ${reviewerStr[1]}
                    </td>
                </tr>
                <tr>
                    <td>
                        公司领导
                    </td>
                    <td>
                        <select id="deptname2" style="width: 120px;"
                                onchange="userlist(0,3)">
                            <option value="0">
                                请选择部门
                            </option>

                        </select>
                        <select id="username2" name="reviewerStr[2]" style="width: 150px;">
                            <option value="0">
                                请先选择部门
                            </option>
                        </select>
                    </td>
                    <td>
                        ${reviewerStr[2]}
                    </td>
                </tr>
            </table>
            <div class="form-group">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </form>
    </div>
</div>
<%@include file="/util/foot.jsp" %>
<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="application/javascript">
    $(function () {
        //加载所有分组
        $.ajax({
            type: "post",
            url: "GzstoreAction_getdept.action",
            dataType: "json",
            success: function (data) {
                //填充部门信息
                $(data).each(function () {
                    $("<option value='" + this.id + "'>" + this.dept + "</option>").appendTo("#deptname");
                    $("<option value='" + this.id + "'>" + this.dept + "</option>").appendTo("#deptname1");
                    $("<option value='" + this.id + "'>" + this.dept + "</option>").appendTo("#deptname2");
                });
            }
        });
    });

    function userlist(flag,selectNum) {//flag0表示是点击部门的时候flag1表示页面刷新的时候
        if(selectNum=="1"){
            var deptid = $("#deptname").val();
        }if(selectNum=="2"){
            var deptid = $("#deptname1").val();
        }if(selectNum=="3"){
            var deptid = $("#deptname2").val();
        }

        if (deptid == "0") {
            if(selectNum=="1"){
                $("#username").empty();
                $("<option value='0'>请先选择部门</option>").appendTo("#username");
            }if(selectNum=="2"){
                $("#username1").empty();
                $("<option value='0'>请先选择部门</option>").appendTo("#username1");
            }if(selectNum=="3"){
                $("#username2").empty();
                $("<option value='0'>请先选择部门</option>").appendTo("#username2");
            }


        } else {
            <%--		$("#username").empty();不合适--%>
            $.ajax({
                type: "post",
                url: "GzstoreAction_getusers.action",
                data: {
                    id: deptid
                },
                dataType: "json",
                async: false,
                success: function (data) {
                    if (flag == 0) {
                        if(selectNum=="1"){
                            $("#username").empty();
                            $("<option value='0'>请选择人员</option>").appendTo("#username");
                        }if(selectNum=="2"){
                            $("#username1").empty();
                            $("<option value='0'>请选择人员</option>").appendTo("#username1");
                        }if(selectNum=="3"){
                            $("#username2").empty();
                            $("<option value='0'>请选择人员</option>").appendTo("#username2");
                        }
                    }
                    $(data).each(function () {
                        if(selectNum=="1"){
                            $("<option value='" + this.id + "_" + this.name + "'>" + this.code + "_" + this.name + "</option>").appendTo("#username");
                        }if(selectNum=="2"){
                            $("<option value='" + this.id + "_" + this.name + "'>" + this.code + "_" + this.name + "</option>").appendTo("#username1");
                        }if(selectNum=="3"){
                            $("<option value='" + this.id + "_" + this.name + "'>" + this.code + "_" + this.name + "</option>").appendTo("#username2");
                        }
                    });


                }
            });
        }
        <%--上海洪湖：通过Ajax访问后台返回Json字符太长报错    else {$("#username").empty();  上海洪湖为空，
             if (flag == 0) {$("#username").empty();  会保留上个单位员工信息  --%>

        if(selectNum=="1"){
            duoxuaSelect('username');
        }if(selectNum=="2"){
            duoxuaSelect('username1');
        }if(selectNum=="3"){
            duoxuaSelect('username2');
        }
        $(".select_checkBox").off( "mouseenter mouseleave" );
    }
</script>
</body>
</html>
