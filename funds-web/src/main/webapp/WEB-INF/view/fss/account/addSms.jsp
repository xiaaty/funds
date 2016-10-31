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
            <li>新增映射配置信息</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <form id="addSmsForm" action="${contextPath}/account/saveSms" method="post">
                    <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">
                        <div class="jarviswidget" id="customerAdd" data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2><i class="fa fa-edit pr10"></i>新增映射配置信息<font class="pl10 f12 color07"></font></h2>
                                <span class="tip02 color03">”*“为必填项</span>
                            </header>
                            <div>
                                <div class="smart-form">

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
                                                    <td align="right"><span class="emphasis emphasis_txtx01 pr5">*</span>手机号码：</td>
                                                    <td>
                                                        <label class="input">
                                                            <input type="text" maxlength="50" id="custId" name="custId" value="${map.custId}" style="width:202px;"/>
                                                        </label>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="tr" nowrap="nowrap">备注：</td>
                                                    <td>
                                                        <label class="input">
                                                            <input type="text" maxlength="50" id="remark" name="remark" value="${map.remark}" style="width:202px;"/>
                                                        </label>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                            <div class="mb20" id="wid-id-713">
                                                <button class="btn btn-default table-nobg-btn" type="button" id="addSms">保存</button>
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
        $("#addSms").click(function () {
            var mobile=$("#custId").val();
            if (validateCheck(mobile)) {
                $("#addSmsForm").ajaxSubmit({
                    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                    dataType: "json",
                    success: function (data) {
                        if(data.code == '0000') {
                            jAlert("添加成功!", '信息提示');
                            parent.location.href="${contextPath}/account/smsNotification";
                        }else if(data.code == '1002'){
                            jAlert("添加失败!该客户手机号已存在", '消息提示');
                            return;
                        }else {
                            jAlert("添加失败!", '消息提示');
                            return;
                        }
                    }
                });
            }
        });
    });
    //校验函数
    function validateCheck(mobile) {
        var phone = /^1([38]\d|4[57]|5[0-35-9]|7[06-8]|8[89])\d{8}$/;
        if(mobile.length==0){
            jAlert("手机号不能为空!", '消息提示');
            return false;
        }else if(!phone.test(mobile)){
            jAlert("手机号格式不正确!", '消息提示');
            return false;
        }else {
            return true;
        }
    }
</script>
</body>
</html>