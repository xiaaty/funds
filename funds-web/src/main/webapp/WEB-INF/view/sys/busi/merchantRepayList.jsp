<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <title>主页--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">

    <%@include file="../../../view/include/common_css_js.jsp" %>
    <style>
        .table-nobg-btn {
            font: 15/29px;
            height: 31px;
            line-height: 31px;
            margin: 7px 7px 7px 0;
            padding: 0 22px;
        }

        .dt-wrapper {
            overflow: auto;
        }

        .button-icon i {
            line-height: 32px;
        }

        #footer {
            position: absolute;
            bottom: 10px;
            z-index: 100px;
        }

        .footer-bottom {
            font-size: 13px
        }

        .footer-bottom ul > li {
            padding: 0
        }

        .footer-bottom ul > li + li:before {
            padding: 0 10px;
            color: #ccc;
            content: "|"
        }
        
        .float-left {
            float: left;
            margin-top: 0px;
        }
    </style>

</head>

<body>
<%@include file="../../../view/include/menu.jsp" %>
<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>商户管理</li>
            <li>商户列表</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <%--<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">--%>
                <div class="jarviswidget jarviswidget-color-darken" id="dictList-id-02" data-widget-deletebutton="false"
                     data-widget-editbutton="false">
                    <header>
                        <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                        <h2>商户列表</h2>
                    </header>
                    <!-- widget div-->
                    <div>
                        <form class="smart-form" action="" method="post" id="busiListForm">
                            <!-- widget edit box -->
                            <div class="jarviswidget-editbox">
                                <!-- This area used as dropdown edit box -->
                            </div>
                            <!-- end widget edit box -->
                            <!-- widget content -->
                            <div class="widget-body">
                                <div class="mb20" id="wid-id-713">
                                </div>
                                <table id="borrow-rep-table12" class="table table-bordered tc mt15"
                                       style="min-width:2300px;">
                                    <col width="100"/>
                                    <col width="300"/>
                                    <col width="620"/>
                                    <col width="620"/>
                                    <col width="300"/>
                                    <col width="300"/>
                                    <col width="300"/>
                                    <col width="300"/>
                                    <col width="150"/>
                                    <thead>
                                    <tr>
                                        <td>序号</td>
                                        <td>商户号</td>
                                        <td>交易类型</td>
                                        <td>商户地址</td>
                                        <td>回盘地址</td>
                                        <td>创建时间</td>
                                        <td>修改时间</td>
                                        <td>操作</td>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <c:forEach items="${page.list}" var="t" varStatus="status">
                                        <tr>
                                            <td>
                                                    ${status.index+1 }
                                                <c:choose>
                                                    <c:when test="${edit && t.id == id}">
                                                        <input type="hidden" id="id" value="${t.id}"/>
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td>${t.mchnNo}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${!edit || t.id != id}">
                                                        <fss:dictView key="${t.tradeType}"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <select class="select02" style="width:202px;"
                                                                id="tradeType">
                                                            <fss:dictOrder var="order" dictOrder="tradeType">
                                                                <option value="${order.key}"
                                                                        <c:if test="${order.key== t.tradeType}">selected</c:if> >
                                                                        ${order.value}
                                                                </option>
                                                            </fss:dictOrder>
                                                        </select>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td align="left">
                                                <c:choose>
                                                    <c:when test="${!edit || t.id != id}">
                                                        ${t.mchnUrl}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input id="mchnUrl" value=" ${t.mchnUrl}"
                                                               style="width: 600px;"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td style="text-align:left;">
                                                <c:choose>
                                                    <c:when test="${!edit || t.id != id}">
                                                        ${t.repayClassName }
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input id="repayClassName"
                                                               value="${t.repayClassName }" style="width: 600px;"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td><fmt:formatDate value="${t.createTime}"
                                                                pattern="yyyy-MM--dd HH:mm:ss"/></td>
                                            <td><fmt:formatDate value="${t.modifyTime}"
                                                                pattern="yyyy-MM--dd HH:mm:ss"/></td>
                                            <td style="text-align:left;">
                                                    <%-- <a href="${contextPath}/sys/busi/update/${t.mchnNo}?parentId=${t.parentId}">修改</a>
                                                     <a href="${contextPath}/sys/busi/list/${t.id}">查看</a>--%>
                                                <a href="/sys/busi/merchantRepayList/${mchnNo}?edit=true&&id=${t.id}">配置</a>
                                                    <%--<a href="#" Class="aConf">配置</a>--%>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${edit}">
                                            <input class="btn btn-default table-nobg-btn" type="button" onclick="sumb('update')"
                                                   value="提交"/>
                                            <button class="btn btn-default table-nobg-btn" type="button"
                                                    onclick="location.href='${contextPath}/sys/busi/merchantRepayList/${mchnNo}'"><%--<i class="fa fa-minus"></i>--%>返回
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="btn btn-default table-nobg-btn " type="button" id="buttonAdd" onclick="addMerchantRepay()"><i class="fa fa-plus"></i>添加</button>
                                            <button class="btn btn-default table-nobg-btn float-left" type="button" id="buttonSave" style="display: none;" onclick="sumb('save')"><i class="fa fa-minus"></i>提交</button>
                                            <button class="btn btn-default table-nobg-btn" type="button" id="buttonBack" style="display: none;" onclick="location.href='${contextPath}/sys/busi/merchantRepayList/${mchnNo}'"><i class="fa fa-minus"></i>返回</button>
                                        </c:otherwise>
                                    </c:choose>

                                    </tbody>
                                </table>

                            </div>
                        </form>
                        <form action="${contextPath}/sys/busi/merchantRepay" id="submitFrom">
                            <input type="hidden" name="id"/>
                            <input type="hidden" name="mchnNo" />
                            <input type="hidden" name="tradeType"/>
                            <input type="hidden" name="mchnUrl"/>
                            <input type="hidden" name="repayClassName"/>
                            <input type="hidden" name="mchnNoVal" value="${mchnNo}"/>

                        </form>
                    </div>
                </div>
                <%--</article>--%>
            </div>

        </section>
    </div>
    <%@include file="../../../view/include/common_footer_css_js.jsp" %>
    <script src="${contextPath}/js/jquery.form.js" ></script>
    <script src="${contextPath}/js/jquery.alerts.js" ></script>
    <script src="${contextPath}/js/jquery.blockUI.js"></script>
    <script src="${contextPath}/js/util/lock.js"></script>
</div>

<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#busiListForm"));
    });

    //修改和添加的提交按钮
    function sumb(obj) {
        bilocUtil("保存...");
        var tradeType = ( $("#tradeType").val() != null && $("#tradeType").val() != '' && $("#tradeType").val() != undefined ) ? $("#tradeType").val().replace(/(^\s*)|(\s*$)/g, "") : '';
        var mchnUrl = ( $("#mchnUrl").val() != null && $("#mchnUrl").val() != '' && $("#mchnUrl").val() != undefined ) ? $("#mchnUrl").val().replace(/(^\s*)|(\s*$)/g, "") : '';
        var repayClassName = ( $("#repayClassName").val() != null && $("#repayClassName").val() != '' && $("#repayClassName").val() != undefined ) ? $("#repayClassName").val().replace(/(^\s*)|(\s*$)/g, "") : '';
        var mchnNo = ( $("#mchnNo").val() !=null && $("#mchnNo").val() != '' && $("#mchnNo").val() != undefined ) ? $("#mchnNo").val().replace(/(^\s*)|(\s*$)/g, "") : '';

        if(obj=="update"){
            $("#submitFrom").attr('action','${contextPath}/sys/busi/merchantRepay');
        }else if(obj=="save"){
            $("#submitFrom").attr('action','${contextPath}/sys/busi/addMerchantRepay');
        }else{
            return;
        }

        if(obj=="update"){
            $("input[name='id']").val($("#id").val());
        }else if(obj=="save"){
            $("input[name='id']").val(null);
        }else{
            $.unblockUI();
            jAlert("保存失败", '信息提示');
        }

        if(obj=="save"){
            $("input[name='mchnNo']").val(mchnNo);
            if(mchnNo == '') {
                $.unblockUI();
                jAlert("商户号不能为空", '信息提示');
                return;
            }
        }

        $("input[name='tradeType']").val(tradeType);
        if(tradeType == '') {
            $.unblockUI();
            jAlert("交易类型不能为空", '信息提示');
            return;
        }

        $("input[name='mchnUrl']").val(mchnUrl);
        if(mchnUrl == '') {
            $.unblockUI();
            jAlert("商户地址不能为空", '信息提示');
            return;
        }

        $("input[name='repayClassName']").val(repayClassName);

        if(repayClassName == '') {
            $.unblockUI();
            jAlert("回盘地址不能为空", '信息提示');
            return;
        }

        $("#submitFrom").submit();
        $.unblockUI();
    }

    //添加按钮
    function addMerchantRepay() {
       $("#buttonBack").css('display','block');
       $("#buttonSave").css('display','block');
       $("#buttonAdd").css('display','none');
       addRow();
    }


    //添加行
    function addRow() {
        var mchnNo = "${mchnNo}";
        var oTab = $("#borrow-rep-table12");

            var mchnNoRow = null;
            if (mchnNo == -1) {
                mchnNoRow = '<input type="text" name="mchnNo" id="mchnNo" >';
            } else {
                mchnNoRow = '<input type="text" name="mchnNo" id="mchnNo" value = "${mchnNo}" disabled >';
            }

            oTab.append('<tr>' +
                    '<td>' +
                    '</td>' +
                    '<td>' +
                        mchnNoRow +
                    '</td>' +
                    '<td>' +
                        '<select class="select02" style="width:202px;" id="tradeType" >' +
                            '<fss:dictOrder var="order" dictOrder="tradeType">' +
                                '<option value="${order.key}">${order.value}</option>' +
                            '</fss:dictOrder>' +
                        '</select>' +
                    '</td>' +
                    '<td>' +
                        '<input type="text" id="mchnUrl" style="width: 600px;" />' +
                    '</td>' +
                    '<td>' +
                        '<input type="text" id="repayClassName" style="width: 600px;" />' +
                    '</td>' +
                    '<td>' +
                    '</td>' +
                    '<td>' +
                    '</td>' +
                    '<td>' +
                    '</td>' +
                    '</tr>');

    }


</script>

<%@include file="../../../view/include/foot.jsp" %>
</body>

</html>