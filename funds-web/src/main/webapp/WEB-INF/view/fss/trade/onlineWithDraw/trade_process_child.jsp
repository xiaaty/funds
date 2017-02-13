<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>代扣审核--资金详细--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <%@include file="../../../../view/include/common_css_js.jsp" %>
    <style>
        .table-nobg-btn {
            font: 15/29px;
            height: 31px;
            line-height: 31px;
            margin: 7px 7px 7px 0;
            padding: 0 22px;
        }



        .button-icon i {
            line-height: 32px;
        }





        .footer-bottom ul > li {
            padding: 0
        }

        .footer-bottom ul > li + li:before {
            padding: 0 10px;
            color: #ccc;
            content: "|"
        }

    </style>

</head>

<body>
<%@include file="../../../../view/include/menu.jsp" %>


<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">
        <ol class="breadcrumb">
            <li>交易审核</li>
            <li>资金详细</li>
        </ol>
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <!-- NEW WIDGET START -->
                    <div class="jarviswidget jarviswidget-color-darken" id="dictList-id-100"
                         data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>提现详细</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="mortForm">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                    <div id="wid-id-713">
                                        <button class="btn btn-default table-nobg-btn" type="button"
                                                onclick="javascript:history.back(-1);"><i class="fa fa-minus"></i>返回
                                        </button>
                                    </div>
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15"
                                           style="min-width:2500px;">
                                        <col width="200" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="200" />
                                        <col width="150" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="200" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="250" />
                                        <col width="250" />
                                        <col width="150" />
                                        <thead>
                                        <tr>
                                            <td>交易流水号</td>
                                            <td>客户姓名</td>
                                            <td>客户电话</td>
                                            <td>客户账户</td>
                                            <td>交易金额</td>
                                            <td>交易类型</td>
                                            <td>入账客户姓名</td>
                                            <td>入账客户电话</td>
                                            <td>入账客户账户</td>
                                            <td>交易状态</td>
                                            <td>流程状态</td>
                                            <td>创建时间</td>
                                            <td>修改时间</td>
                                            <td>备注</td>
                                            <td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t">
                                            <tr>
                                                <td>${t.seqNo}</td>
                                                <td>${t.fromCustName}</td>
                                                <td>${t.fromCustMobile}</td>
                                                <td>${t.fromAccId}</td>
                                                <td><fss:money money="${t.amt}"/></td>
                                                <td><fss:dictView key="${t.tradeType}" /></td>
                                                <td>${t.toCustName}</td>
                                                <td>${t.toCustMobile}</td>
                                                <td>${t.toAccId}</td>
                                                <td><fss:dictView key="${t.status}" /></td>
                                                <td><fss:dictView key="${t.processState}" /></td>
                                                <td><fss:fmtDate value="${t.createTime}"/></td>
                                                <td><fss:fmtDate value="${t.modifyTime}"/></td>
                                                <td>${t.memo}</td>
                                                <td>
                                                    <c:if test="${t.processState == 10050032 && t.status == 10030003}"><a href="${contextPath}/trade/processChild/${t.id}/charge">重新收费</a></c:if>

                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- end widget content -->
                            </form>
                        </div>
                    </div>
                </article>
            </div>
        </section>
    </div>
</div>
<%@include file="../../../../view/include/common_footer_css_js.jsp" %>
</div>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#mortForm"));

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





</script>

<%@include file="../../../../view/include/foot.jsp" %>
</body>

</html>