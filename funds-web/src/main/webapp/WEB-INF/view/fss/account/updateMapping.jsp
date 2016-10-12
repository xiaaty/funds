<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统配置--映射配置信息--冠群驰骋投资管理(北京)有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">

    <%@include file="../../include/common_css_js.jsp"%>
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
        .button-icon i{
            line-height:32px;
        }
    </style>
</head>
<body>

<%@include file="../../include/menu.jsp"%>

<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">
        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>系统配置</li>
            <li>修改映射配置信息</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <form id="updateAccountForm" action="${contextPath}/account/updateAndSaveRedAccount" method="post">
                    <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">
                        <div class="jarviswidget" id="customerupdate" data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2><i class="fa fa-edit pr10"></i>修改映射配置信息<font class="pl10 f12 color07"></font></h2>
                                <span class="tip02 color03">”*“为必填项</span>
                            </header>
                            <div>
                                <div class="smart-form">
                                    <input type="hidden" name="id" value="${mappingEntity.id}"/>
                                    <!-- widget content -->
                                    <div class="widget-body no-padding">
                                        <div class="mt10 mb10 ml30">
                                            <table class="table">
                                                <col width="112" />
                                                <col width="367" />
                                                <col width="112" />
                                                <col />
                                                <tbody>
                                                <tr>
                                                    <td align="right"><span class="emphasis emphasis_txtx01 pr5">*</span>客户编号：</td>
                                                    <td>
                                                        <label class="input">
                                                            ${mappingEntity.custId}
                                                        </label>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="tr" nowrap="nowrap"><span class="emphasis emphasis_txtx01 pr5">*</span>映射类型：</td>
                                                    <td nowrap="nowrap">
                                                        <label class="select">
                                                            <select class="select02" style="width:202px;" name="mappingType" id="mappingType">
                                                                <fss:dictOrder var="order" dictOrder="mappingType">
                                                                    <option value="${order.key}"  <c:if test="${order.key==mappingEntity.mappingType}">selected</c:if> >${order.value}</option>
                                                                </fss:dictOrder>
                                                            </select>
                                                        </label>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right"><span class="emphasis emphasis_txtx01 pr5">*</span>交易类型：</td>
                                                    <td>
                                                        <label class="select">
                                                            <select class="select02" style="width:202px;" name="tradeType" id="tradeType">
                                                                <c:forEach items="${list}" var="t">
                                                                    <option value="${t.dictId}">${t.dictName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </label>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right">是否有效：</td>
                                                    <td>
                                                        <label>
                                                            <select id ="isValid" name ="isValid" value="${mappingEntity.isValid}" style="width:202px;height: 30px;">
                                                                <option  <c:if test="${mappingEntity.isValid=='0'}"></c:if> value="有效">有效</option>
                                                                <option  <c:if test="${mappingEntity.isValid=='1'}"></c:if> value="无效" >无效</option>
                                                            </select>
                                                            <label>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="tr" nowrap="nowrap"><span class="emphasis emphasis_txtx01 pr5">*</span>排序号：</td>
                                                    <td>
                                                        <label class="input">
                                                            <input type="text" maxlength="50" id="sort" name="sort" value="${mappingEntity.sort}" style="width:202px;">
                                                        </label>
                                                        <span class="emphasis emphasis_txtx01 pr5">排序号从1开始依次递增，并且不能和列表中已存在的排序号重复</span>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                            <div class="mb20" id="wid-id-713">
                                                <button class="btn btn-default table-nobg-btn" type="button" id="addAccount">保存</button>
                                                <button class="btn btn-default table-nobg-btn" type="button" onclick="location.href='${contextPath}/account/redaccountlist'">取消</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </article>
                </form>
            </div>
        </section>
    </div>
</div>

<%@include file="../../include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        $("#addAccount").click(function () {
            if (validateCheck()) {
                $("#updateAccountForm").ajaxSubmit({
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    dataType: "json",
                    success: function (data) {
                        debugger;
                        if (data.code == '0000') {
                            jAlert("修改成功!", '信息提示');
                            parent.location.href="${contextPath}/account/redaccountlist";
                        } else {
                            jAlert("修改失败!", '消息提示');
                            return;
                        }
                    }
                });
            }
        });
    });
    //校验函数
    function validateCheck() {
        return true;
    }
</script>
</body>
</html>