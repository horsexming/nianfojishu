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
                <th colspan="4" align="center">供应商详细信息</th>
            </tr>
            <tr>
                <th align="right">公司名称:</th>
                <td>${zhUser.name}</td>
                <th align="right">公司全称:</th>
                <td>${zhUser.cmp}</td>
            </tr>
            <tr>
                <th align="right">公司联系电话:</th>
                <td>${zhUser.cfax}</td>
                <th align="right">公司备用电话:</th>
                <td>${zhUser.ctel}</td>
            </tr>
            <tr>
                <th align="right">公司邮箱:</th>
                <td>${zhUser.yx}</td>
                <th align="right">供应商编号:</th>
                <td>${zhUser.usercode}</td>
            </tr>
            <tr>
                <th align="right">类别:</th>
                <td>${zhUser.cclass}</td>
                <th align="right">商品代表:</th>
                <td>${zhUser.note}</td>
            </tr>

            <tr>
                <th align="right">联系人:</th>
                <td>${zhUser.cperson}</td>
                <th align="right">联系人电话:</th>
                <td>${zhUser.cmobile}</td>
            </tr>
            <tr>
                <th align="right">公司地址:</th>
                <td>${zhUser.companydz}</td>
                <th align="right">注册时间:</th>
                <td>${zhUser.time}</td>
            </tr>
            <tr>
                <th align="right">产品合格率:</th>
                <td>${zhUser.zhiliang}</td>
                <th align="right">供货率:</th>
                <td>${zhUser.gonghuolv}</td>
            </tr>
            <tr>
                <th align="right">付款方式:</th>
                <td>${zhUser.fkfs}</td>
                <th align="right">增值税率:</th>
                <td>${zhUser.zzsl}</td>
            </tr>
            <tr>
                <th align="right">税务登记号:</th>
                <td>${zhUser.djh}</td>
                <th align="right">营业执照:</th>
                <td>${zhUser.yyzz}</td>
            </tr>
            <tr>
                <th align="right">ASL:</th>
                <td>${zhUser.asl}</td>
                <th align="right">添加时间:</th>
                <td>${zhUser.addTime}</td>
            </tr>
            <tr>
                <th align="right">
                    最低起订量
                </th>
                <td>
                    ${zhUser.zdqdl}
                </td>
                <th align="right">
                    最低装箱量
                </th>
                <td>
                    ${zhUser.zdzxl}
                </td>
            </tr>
            <tr>
                <th align="right">
                    最低起送量
                </th>
                <td>
                    ${zhUser.zdqsl}
                </td>
                <th align="right">
                    预付款比例
                </th>
                <td>
                    ${zhUser.yufuBiLi}
                </td>
            </tr>
            <tr>
                <th align="right">
                    开票依据
                </th>
                <td>
                    ${zhUser.kpYiJu}
                </td>
                <th align="right">
                    目前状况
                </th>
                <td>
                    ${zhUser.blackliststauts}
                </td>
            </tr>
            <tr>
                <th align="right">
                    付款周期(票到)
                </th>
                <td>
                    ${zhUser.fkZhouQi}天
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
                        <input type="hidden" value="${zhUser.id}" name="id"/>
                    </form>

                    <s:if test='zhUser.meetingRecord != null'>
                        <a target="_showPri"
                           href="${pageContext.request.contextPath}/upload/file/MeetingRecord/${zhUser.meetingRecord}">
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
</SCRIPT>

</html>
