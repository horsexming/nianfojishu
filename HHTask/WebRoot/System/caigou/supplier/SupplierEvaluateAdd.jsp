<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <script
            src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js">
    </script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js">
    </script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <h2 align="center">
            添加品质异常履历
        </h2>
        <div class="col-md-12 column">
            <form action="QualityAbnormalresumeAction_add.action" method="post"
                  enctype="multipart/form-data" id="myform">
                <div class="form-group row">
                    <div class="col-xs-4">
                        <label for="">
                            考评期
                        </label>
                        <select class="form-control" id="typesel"
                                name="qualityAbnormalresume.type">
                            <option value="市场投诉">
                                市场投诉
                            </option>
                            <option value="总装厂">
                                总装厂
                            </option>
                            <option value="华为外检">
                                华为外检
                            </option>
                            <option value="成品检验">
                                成品检验
                            </option>
                            <option value="装配制程">
                                装配制程
                            </option>
                            <option value="钣金制程">
                                钣金制程
                            </option>
                            <option value="外协来料">
                                外协来料
                            </option>
                            <option value="外购来料">
                                外购来料
                            </option>
                            <option value="其他">
                                其他
                            </option>
                        </select>
                        <br/>
                        <input type="text" class="form-control" placeholder="请填写异常来源"
                               name="qualityAbnormalresume.type" id="typeinput"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="">
                        品质类
                    </label>
                </div>
                <div class="form-group row">
                    <div class="col-xs-4">
                        <label for="">
                            品质交货批次
                        </label>
                        <input type="text" class="form-control"
                               name="qualityAbnormalresume.feedbackCo"/>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-xs-4">
                        <label for="">
                            品质合格批次
                        </label>
                        <input type="text" class="form-control"
                               name="qualityAbnormalresume.responsibleCo"/>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-xs-4">
                        <label for="">
                            品质合格率
                        </label>
                        <input type="text" class="form-control"
                               name="qualityAbnormalresume.responsibleCo"/>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-xs-4">
                        <label for="">
                            品质得分
                        </label>
                        <input type="text" class="form-control"
                               name="qualityAbnormalresume.responsibleCo"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="">
                        交期类
                    </label>
                </div>


                <div class="form-group row">
                    <div class="col-xs-4">
                        <label for="">
                            交期交货批次
                        </label>
                        <input type="text" class="form-control"
                               name="qualityAbnormalresume.trackPeople"/>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-xs-4">
                        <label for="">
                            准时交货率
                        </label>
                        <input type="text" class="form-control"
                               name="qualityAbnormalresume.trackPeople"/>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-xs-4">
                        <label for="">
                            交期得分
                        </label>
                        <input type="text" class="form-control"
                               name="qualityAbnormalresume.responsibleCo"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="">
                        成本类
                    </label>
                </div>
                <div class="form-group row">
                    <div class="col-xs-4">
                        <label for="">
                            成本得分
                        </label>
                        <font color="red"> *</font>
                        <input type="text" class="form-control"
                               name="qualityAbnormalresume.responsibleCo"/>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-xs-12">
                        <label for="">
                            原因
                        </label>
                        <textarea class="form-control" rows="2"
                                  name="qualityAbnormalresume.scheme" id=""></textarea>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="">
                        服务类
                    </label>
                </div>
                <div class="form-group row">
                    <div class="col-xs-4">
                        <label for="">
                            服务得分
                        </label>
                        <input type="text" class="form-control"
                               name="qualityAbnormalresume.responsibleCo"/></div>
                </div>
                <div class="form-group row">
                    <div class="col-xs-12">
                        <label for="">
                            原因
                        </label>
                        <textarea class="form-control" rows="2"
                                  name="qualityAbnormalresume.scheme" id=""></textarea></div>
                </div>
                <div class="form-group col-xs-12" align="center">
                    <button type="submit" class="btn btn-default" id="taskadd">
                        添加
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript"
        src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js">
</script>
<script type="text/javascript">
</script>
</body>
<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">

    $("#typeinput").hide();//隐藏
    $('#typesel').change(function () {
        if ("其他" == $(this).children('option:selected').val()) {
            $('#typeinput').show();
            $('#typeinput').attr("disabled", false);
        } else {
            $('#typeinput').hide();
            $('#typeinput').attr("disabled", "disabled");
        }
    });
    $(function () {
        //var tag = "leixing";
        $.ajax({
            type: "post",
            url: "BuHeGePinAction_ajaxfindAllDefTypeList.action",
            dataType: "json",
            success: function (data) {
                $(data).each(
                    function () {
                        $(
                            "<option value='" + this.defName + "'>"
                            + this.defName + "</option>")
                            .appendTo("#typeOfProblem");
                    });

            }
        });
    });

    function change() {
        var statue = document.getElementById("typeOfProblem").value
        $.ajax({
            type: "post",
            url: "BuHeGePinAction_findBhgByDefName.action",
            dataType: "json",
            data: {
                "statue": statue
            },
            success: function (data) {
                $(data).each(
                    function () {
                        $(
                            "<option value='" + this.type + "'>"
                            + this.type + "</option>")
                            .appendTo("#inTypeOfProblem");
                    });

            }
        });
    }
</script>

</html>
