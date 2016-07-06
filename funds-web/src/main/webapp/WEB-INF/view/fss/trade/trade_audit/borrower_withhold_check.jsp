<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>代扣审核--冠群驰骋投资管理(北京)有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">

    <%@include file="../../../../view/include/common_css_js.jsp"%>
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

<%@include file="../../../../view/include/menu.jsp"%>

<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">
        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>交易管理</li>
            <li>交易审核</li>
            <li>代扣审核</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <form id="withdrawForm" action="${contextPath}/trade/tradeApply/${tradeapply.applyType}/${tradeapply.busiType}/${tradeapply.applyNo}/moneySplit" method="post">
                    <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">
                        <div class="jarviswidget" id="borrowWithholdCheck" data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2><i class="fa fa-edit pr10"></i>审核信息<font class="pl10 f12 color07"></font></h2>
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
                                                    <td align="left">客户姓名：</td>
                                                    <td>
                                                        <label class="input">
                                                            <input type="text" maxlength="50" readonly="readonly" name="custName" value="${custName}" style="width:256px;" />
                                                        </label>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="left">手机号码：</td>
                                                    <td>
                                                        <label class="input">
                                                            <input type="text" maxlength="50" readonly="readonly" name="custMobile" value="${custMobile}" style="width:256px;" />
                                                        </label>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="left">业务编号：</td>
                                                    <td>
                                                        <label class="input">
                                                            <%--                                                                 <input type="hidden" name="token" value="${sessionScope.token}"/>  --%>
                                                            <input type="text" maxlength="50" readonly="readonly" name="businessNo" value="${tradeapply.businessNo}" style="width:256px;" />
                                                        </label>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="left">交易金额：</td>
                                                    <td>
                                                        <label class="input">
                                                            <input type="text" maxlength="50" readonly="readonly" name="tradeAmount" value="${tradeapply.tradeAmount}" style="width:256px;" />
                                                        </label>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="left">审核金额：</td>
                                                    <td>
                                                        <label class="input">
                                                            <input type="text" maxlength="50"  name="auditAmount" style="width:256px;" />
                                                        </label>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="left">审批状态：</td>
                                                    <td colspan="5">
                                                        <span class="pl10 pr50"><input checked="checked" name="applyStatus" type="radio" value="4"><label class="ml5" for="typeofPayYes">通过</label></span>
                                                        <span class="pl10 pr50"><input name="applyStatus" type="radio" value="3"><label class="ml5" for="checkRefuse">不通过</label></span>
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

<%@include file="../../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
<script src="${contextPath}/js/jquery.blockUI.js"></script>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        /************************************************************************/
        // pageSetUp();
        // DT_page("borrow-rep-table12", true, '${page.JSON}', $("#Form"));
        $('.selectdate').datetimepicker({
            language: 'zh-CN',
            weekStart: 1,
            autoclose: 1,
            format: 'yyyy-mm-dd',
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0
        });

        dateCheck();


        $('.selectdate_time').datetimepicker({
            language: 'zh-CN',
            weekStart: 1,
            autoclose: 1,
            format: 'hh:m:00',
            todayHighlight: 1,
            startView: 1,
            minView: 1,
            forceParse: 0
        });
    });


    //日期的合法性check
    function dateCheck() {
        var $selectdate = $(".selectdate");
        $selectdate.each(function() {
            //$(this).off();
            $(this).focus(function() {
                //
                this.select();
            })
                    .blur(function() {
                        if($(this).val() != "") {
                            var val = $(this).val();
                            if (val.indexOf("\-") > 0 ) {
                            } else {
                                if (val.length == 8) {
                                    val = val.substr(0,4) + "-" + val.substr(4,2) + "-" + val.substr(6,2);
                                    $(this).val(val);
                                }
                            }
                            var msg= isDate($(this).val());
                            if (msg != "") {
                                alert(msg);
                                this.focus();
                            }

                        }
                    });
        });
    }

    $("#btn_cancel").button().click(function() {
        window.history.back();
    });

    /***************************审核通过*********************************************/
    $("#passbtn").click(function () {
        wait("正在审核中，请耐心等待...");
        if (validateCheck()) {
            $("#withdrawForm").ajaxSubmit({
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                dataType: "json",
                success: function (data) {
                    if (data.code == '0000') {
                        $.unblockUI();
                        jAlert("审核完成!", '信息提示');
                        //自动跳转
                        //  window.history.back();
                        <%--parent.location.href="${contextPath}/trade/tradeApply/${tradeapply.applyNo}/records";--%>
                        parent.location.href="${contextPath}/trade/tradeApply/${tradeapply.applyType}/${tradeapply.busiType}";
                    }else if(data.code == '0001') {
                        $.unblockUI();
                        jAlert("审核失败!", '消息提示');
                        return;
                    } else {
                        $.unblockUI();
                        jAlert("添加失败,该编号已经存在,请勿重复添加!", '消息提示');
                        return;
                    }
                }
            });
        }
        $.unblockUI();
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


</script>
</body>

</html>