<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>转账申请--冠群驰骋投资管理(北京)有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">

    <%@include file="../../../view/include/common_css_js.jsp"%>
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

<%@include file="../../../view/include/menu.jsp"%>

<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">
        <!-- breadcrumb -->
        <%--<ol class="breadcrumb">
            <li>转账</li>
        </ol>--%>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <form id="transferForm" action="${contextPath}/trade/tradeApply/createTransfer/${type}/${flag}" method="post">
                    <input type="hidden" id="id" name="id" value="${customerInfoEntity.id}"/>
                    <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">
                        <div class="jarviswidget" id="borrowWithholdCheck" data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2><i class="fa fa-edit pr10"></i>账户转账<font class="pl10 f12 color07"></font></h2>
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
                                                    <td align="left">转出客户姓名：</td>
                                                    <td>
                                                        <label class="input">
                                                            <input type="text" maxlength="50" id="customerName" name="customerName" value="${customerName}" style="width:256px;" />
                                                        </label>
                                                    </td>
                                                </tr>
                                                <%--<tr>
                                                    <td align="left">客户手机号码：</td>
                                                    <td>
                                                        <label class="input">
                                                            <input type="text" maxlength="50" id="mobilePhone" name="mobilePhone" value="${mobilePhone}" style="width:256px;" />
                                                        </label>
                                                    </td>
                                                </tr>--%>
                                                <tr>
                                                    <td align="left">身份证号：</td>
                                                    <td>
                                                        <label class="input">
                                                            <input type="text" maxlength="50" id="cert_no" name="cert_no" value="${cert_no}" style="width:256px;" />
                                                        </label>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="left"><span class="emphasis emphasis_txtx01 pr5">*</span>账户类型：</td>
                                                    <td>
                                                        <label>
                                                                <select id = "accType" name = "accType" style="width:150px;height: 30px;">
                                                                    <option value="0" selected="selected">主账户</option>
                                                                    <option value="1">借款账户</option>
                                                                    <option value="2">线下出借账户</option>
                                                                    <option value="3">线上出借账户</option>
                                                                    <option value="96">应付账户</option>
                                                                    <option value="99">冻结账户</option>
                                                                </select>
                                                        </label>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="left"><span class="emphasis emphasis_txtx01 pr5">*</span>转账类型：</td>
                                                    <td colspan="5">
                                                        <span class="pl10 pr50"><input id="tradeType" checked="checked"  name="tradeType" type="radio" value="11080001"><label class="ml5">个人对个人转账</label></span>
                                                        <span class="pl10 pr50"><input id="tradeType" name="tradeType" type="radio" value="11080001"><label class="ml5">个人对公司转账</label></span>
                                                        <span class="pl10 pr50"><input id="tradeType" name="tradeType" type="radio" value="11080004"><label class="ml5">个人账户间转账</label></span>
                                                        <span class="pl10 pr50"><input id="tradeType" name="tradeType" type="radio" value="11080001"><label class="ml5">其他账户间转账</label></span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="left"><span class="emphasis emphasis_txtx01 pr5">*</span>转账金额</td>
                                                    <td>
                                                        <label class="input">
                                                            <%--<input type="text" maxlength="50" id="amt" name="amt" value="${amt}" style="width:256px;" onblur="checkAmt()"/>--%>
                                                            <input type="text" maxlength="50" id="amt" name="amt" value="${amt}" style="width:256px;"/>
                                                        </label>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                            <div class="mb20" id="wid-id-713">
                                                <button class="btn btn-default table-nobg-btn" type="button" id="passbtn">提交</button>
                                                <button class="btn btn-default table-nobg-btn" type="button" id="btn_cancel">取消</button>
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

<%@include file="../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
<script src="${contextPath}/js/jquery.blockUI.js"></script>
<script type="text/javascript" charset="utf-8">

    $("#passbtn").click(function () {
        var cert_no=$("#cert_no").val();
        var accType=$("#accType").val();
        var tradeType=$("#tradeType").val();
        var amt=$("#amt").val();
        wait("正在提交中，请耐心等待...");
        if (validateCheck()) {
            $("#transferForm").ajaxSubmit({
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                dataType: "json",
                success: function (data) {
                    $.unblockUI();
                    if (data.code == '0000') {
                        jAlert("提交成功!", '信息提示',function (r) {
                            window.location.href="${contextPath}/account/${type}/list";
                        });
                    } else {
                        jAlert("提交失败，失败原因："+data.message, '消息提示',function (r) {
                            $.unblockUI();
                            window.location.href="${contextPath}/account/${type}/list";
                        });
                        return;
                    }
                }
            });
        }
    });
    $("#btn_cancel").button().click(function() {
        window.history.back();
    });
    //校验函数
    function validateCheck() {
        return true;
    }

    function wait(msg){
        $.blockUI({
            css: {
                border: 'none',
                padding: '15px',
                // backgroundColor: '#000',
                '-webkit-border-radius': '10px',
                '-moz-border-radius': '10px',
                //   opacity: .7,
                bindEvents: true,
                constrainTabKey: false,
                color: '#000'

            },baseZ:999999,
            message: '<img src="${contextPath}/img/loading.gif" />&nbsp;' + msg
        });
    }

   /* function checkAmt() {
        var amt=$("#amt").val();
        if(amt<=0){
            alert("转账金额不能为0");
        }
        return true;
    }*/
</script>
</body>

</html>