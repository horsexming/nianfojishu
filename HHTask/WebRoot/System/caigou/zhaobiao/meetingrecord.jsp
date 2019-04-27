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
    <%@include file="/util/sonHead.jsp" %>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/javascript/jquery/jquery-1.12.4.js">
    </script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/javascript/typeahead.js/examples.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js">
    </script>
    <script language="javascript"
            src="${pageContext.request.contextPath}/javascript/typeahead.js/typeahead.bundle.js">
    </script>

</head>
<body>
<%@include file="/util/sonTop.jsp" %>
<div id="gongneng"
     style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
    <div id="xitong"
         style="width: 100%; font-weight: bold; height: 50px; "
         align="left">
        <div
                style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
                align="left">

        </div>
        <div style="float: left; width: 45%; padding-top: 5px;"
             align="right">
            <a href="zhaobiaoAction!listAll.action" style="color: #ffffff">刷新</a>
        </div>
    </div>

    <div align="center" id="d1">
        <table class="table" style="width: 70%;">

            <tr>
                <th colspan="4" align="center">供应商会谈纪录</th>
            </tr>

            <tr>
                <th align="right">
                    供应商
                </th>
                <td>
                    <form class="searchFun navbar-form navbar-left" id="remote"
                          <%--action="ModuleFunctionAction!searchModuleFunction.action"--%>
                          method="post" target="workMain">
                        <input type="text" id="searchinput"
                               class="form-control search_text form-control typeahead"
                               accesskey="s" tabindex="9" autocomplete="off" x-webkit-speech=""
                               placeholder="供应商搜索" x-webkit-grammar="builtin:search"
                               style="width: 310px">
                    </form>
                </td>
                <th align="right">
                    会谈纪录
                <td>
                    <form method="post" action="zhaobiaoAction!uploadMeetingRecord.action"
                          enctype="multipart/form-data">
                        <input type="file" name="attachment"/>
                        <br/><br/>
                        <s:if test='zhUser.meetingRecord == null'>
                            <input type="submit" value="上传"/>
                        </s:if>
                        <s:else>
                            <input type="submit" value="重新上传"/>
                        </s:else>
                        <input type="hidden" id="zhuerid" name="id"/>
                    </form>

                    <s:if test='zhUser.meetingRecord != null'>
                        <a target="_showPri"
                           href="<%=basePath%>/FileViewAction.action?FilePath=/upload/file/MeetingRecord/${zhUser.meetingRecord}">
                            <img src="<%=path%>/upload/file/MeetingRecord/${zhUser.meetingRecord}"
                                 style="width: 80px; height: 80px;"/></a>
                    </s:if>
                </td>
                </th>
            </tr>
            <tr>
                <th colspan="4" align="center"><input type="button" name="Submit2" value="返回" class="input"
                                                      onclick="window.history.go(-1);"/></th>
            </tr>
        </table>
    </div>
    <br>
</div>
<%@include file="/util/foot.jsp" %>
</center>
<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

</body>
<SCRIPT type="text/javascript">

    $(function () {
        var zhName = new Bloodhound({
            queryTokenizer: Bloodhound.tokenizers.whitespace,
            datumTokenizer: Bloodhound.tokenizers.whitespace,
            remote: {
                url: 'zhaobiaoAction!serachZhUserNameAndId.action',
                prepare: function (query, settings) {
                    settings.url += '?zhUser.cmp=' + encodeURI(encodeURI(query));
                    return settings;
                }
            }
        });
        $('#remote .typeahead').typeahead(null, {
            // name: '',
            display: 'suppliername',
            source: zhName,
            limit: '5000',
        });
        <%--https://github.com/twitter/typeahead.js/pull/1233--%>
        $('#remote .typeahead').bind('typeahead:selected', function (obj, datum, name) {
            $("#zhuerid").val(datum.supplierid);
        });
    });

</SCRIPT>

</html>
