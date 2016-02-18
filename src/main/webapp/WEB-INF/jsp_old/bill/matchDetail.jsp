<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2014/12/18
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>内审系统--查账--拆账</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <%@include file="/WEB-INF/jsp/inc/common_css_js.inc" %>
    <style>
        .table-nobg-btn {
            font: 15/29px;
            height: 31px;
            margin: 7px 7px 7px 0;
            padding: 0 22px;
        }
        .dt-wrapper {
            overflow: auto;
        }
        .table > tbody > tr.table_input > td {
            padding: 4px;
            vertical-align: middle;
        }
        .table > tbody > tr.table_input > td .input input {
            height: 26px;
            line-height: 20px;
            padding: 3px;
            vertical-align: middle;
        }
        .table > tbody > tr.table_input > td .select select {
            height: 26px;
            line-height: 20px;
            padding: 3px;
            vertical-align: middle;
        }
    </style>

</head>

<body>
<%@include file="/WEB-INF/jsp/inc/menu.inc" %>
<div id="main" role="main">

<div id="ribbon">
    <!-- breadcrumb -->
    <ol class="breadcrumb">
        <li>账户管理</li><li>拆账</li>
    </ol>
    <!-- end breadcrumb -->
</div>

<%--<div class="row ml15">
    <button class="btn btn-default fl table-nobg-btn matchingOk"><i class="fa fa-check-circle"></i>&nbsp;确认</button>
    <button class="btn btn-default fl table-nobg-btn matchingCancle"><i class="fa fa-minus-circle "></i>&nbsp;取消</button>
    <button class="btn btn-default fl table-nobg-btn matchingAdd matchingAdd"><i class="fa fa-plus-circle "></i>&nbsp;添加</button>
    <button class="btn btn-default fl table-nobg-btn matchingDelete"><i class="fa fa-times-circle "></i>&nbsp;删除</button>
</div>--%>
<div class="title01 clearfix ml20 mr20 mt10 mb20" id="addErrorMsg" style="color:red;"></div>
<div id="content">
<section id="widget-grid" class="">
<div class="row">

<!-- NEW WIDGET START -->
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

<div class="jarviswidget" id="wid-id-5118" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
    <header>
        <h2>合同信息</h2>
    </header>
    <!-- widget div-->
    <div>
        <div class="smart-form">
            <!-- widget content -->
            <div class="widget-body no-padding">
                <div class="mt10 mb10">
                    <table class="table">
                        <col width="120" />
                        <col />
                        <col width="120" />
                        <col />
                        <col width="120" />
                        <col />
                        <col width="120" />
                        <col />
                        <tbody>
                        <tr>
                            <td class="tr">合同编号：</td>
                            <td>${contractInfo.contractNo}</td>
                            <td class="tr">客户姓名：</td>
                            <td>${customerInfoBean.customerName}</td>
                            <td class="tr">借款类型：</td>
                            <c:choose>
                                <c:when test= "${contractInfo.loanType =='1'}">
                                    <td>信用贷款</td>
                                </c:when>

                                <c:when test= "${contractInfo.loanType =='2'}">
                                    <td>抵押贷款</td>
                                </c:when>
                                <c:when test= "${contractInfo.loanType =='3'}">
                                    <td>质押贷款</td>
                                </c:when>
                                <c:when test= "${contractInfo.loanType =='4'}">
                                    <td>混合贷款</td>
                                </c:when>
                                <c:otherwise>
                                    <td>${contractInfo.loanType}</td>
                                </c:otherwise>
                            </c:choose>


                            <td class="tr">贷款金额：</td>
                            <td>${contractInfo.contractAmount}</td>
                        </tr>
                        <tr>
                            <td class="tr">贷款年利率：</td>
                            <td>${contractInfo.loanRate}</td>
                            <td class="tr">期&nbsp;&nbsp;&nbsp;&nbsp;限：</td>
                            <td>${contractInfo.limitTimes}</td>
                            <td class="tr">还款方式：</td>
                            <c:choose>
                                <c:when test= "${contractInfo.backMoneyType =='1'}">
                                    <td>等额等息</td>
                                </c:when>

                                <c:when test= "${contractInfo.backMoneyType =='2'}">
                                    <td>等额本息</td>
                                </c:when>
                                <c:when test= "${contractInfo.backMoneyType =='3'}">
                                    <td>先息后本</td>
                                </c:when>
                                <c:when test= "${contractInfo.backMoneyType =='4'}">
                                    <td>自定义还款</td>
                                </c:when>
                                <c:otherwise>
                                    <td>${contractInfo.backMoneyType}</td>
                                </c:otherwise>
                            </c:choose>
                            <td class="tr">实际发款时间：</td>
                            <td>${contractInfo.actualLoanDate}</td>
                        </tr>

                        </tbody>
                    </table>
                </div>
            </div>
            <!-- end widget content -->
        </div>
    </div>
</div>


</div>
<div class="jarviswidget" id="wid-id-5119" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
    <header>
        <h2 class="b">查账基本信息</h2>
    </header>

    <div>
        <div class="smart-form">
            <!-- widget content -->
            <div class="widget-body">
                <div class="mt10 mb10 ofx">
                    <table class="table table-bordered table-striped tc"  style="min-width:1600px">
                        <thead>
                        <tr class="b">
                            <td>导入日期</td>
                            <td>查询部门</td>
                            <td>查询人</td>
                            <td>贷款类型</td>
                            <td>合同编号</td>
                            <td>大区经理</td>
                            <td>地区</td>
                            <td>门店</td>
                            <td>客户姓名</td>
                            <td>银行</td>
                            <td>查询金额</td>
                            <td>到账金额</td>
                            <td>到账日期</td>
                            <td>具体时间</td>
                            <td>还款方式</td>
                        </tr>
                        </thead>
                        <tbody class="f12">
                        <tr>
                            <td><fmt:formatDate value="${billRecord.inputDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>${billRecord.department}</td>
                            <td>${billRecord.requester}</td>
                            <td>${billRecord.loanType} </td>
                            <td>${billRecord.contractNo }</td>
                            <td>${billRecord.regionManager} </td>
                            <td>${billRecord.district}</td>
                            <td>${billRecord.workshop}</td>
                            <td>${billRecord.customerName}</td>
                            <td>${billRecord.bank}</td>
                            <td><fmt:formatNumber value="${billRecord.queryAmount/100}" pattern="###,##0.00"/></td>
                            <td><fmt:formatNumber value="${billRecord.matchMoney/100}" pattern="###,##0.00"/></td>
                            <td>${billRecord.payDate}</td>
                            <td>${billRecord.specificTime}</td>
                            <td>${billRecord.payWay}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- end widget content -->
        </div>
    </div>
</div>

<c:if test="${isBefore == true}">
    <div class="jarviswidget" id="wid-id-5120" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
        <header>
            <h2 class="b">${billRecord.payDate}日前查账未拆账信息</h2>
        </header>

        <div>
            <div class="smart-form">
                <!-- widget content -->
                <div class="widget-body">
                    <div class="mt10 mb10 ofx">
                        <table class="table table-bordered table-striped tc"  style="min-width:1600px">
                            <thead>
                            <tr class="b">
                                <td>导入日期</td>
                                <td>查询部门</td>
                                <td>查询人</td>
                                <td>贷款类型</td>
                                <td>合同编号</td>
                                <td>大区经理</td>
                                <td>地区</td>
                                <td>门店</td>
                                <td>客户姓名</td>
                                <td>银行</td>
                                <td>查询金额</td>
                                <td>到账金额</td>
                                <td>到账日期</td>
                                <td>具体时间</td>
                                <td>还款方式</td>
                            </tr>
                            </thead>
                            <tbody class="f12">
                            <c:forEach var="bill" items="${beforeBill}">
                                <tr>
                                    <td><fmt:formatDate value="${bill.inputDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td>${bill.department}</td>
                                    <td>${bill.requester}</td>
                                    <td>${bill.loanType} </td>
                                    <td>${bill.contractNo }</td>
                                    <td>${bill.regionManager} </td>
                                    <td>${bill.district}</td>
                                    <td>${bill.workshop}</td>
                                    <td>${bill.customerName}</td>
                                    <td>${bill.bank}</td>
                                    <td><fmt:formatNumber value="${bill.queryAmount/100}" pattern="###,###,##0.00"/></td>
                                    <td><fmt:formatNumber value="${bill.matchMoney/100}" pattern="###,###,##0.00"/></td>
                                    <td>${bill.payDate}</td>
                                    <td>${bill.specificTime}</td>
                                    <td>${bill.payWay}</td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- end widget content -->
            </div>
        </div>
    </div>
</c:if>
<c:if test="${billRecord.matchToken == 0 || billRecord.matchToken == 2}">
<div class="jarviswidget" id="wid-id-5122" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
<header>
    <h2 class="b">拆账</h2>
</header>

<div>
    <form class="smart-form" onsubmit="return false" id="spliteeForm">
        <div class="smart-form">
        <!-- widget content -->
        <div class="widget-body">
        <div class="mt10 mb10 ofx">
            <div class="widget-body-nobg-toolbar" style="overflow:hidden;">
            <c:if test="${isBefore == true}">
                <p style="color: red">
                    系统检测到存在比本次查账时间更早的，查账还没有拆账，请按时间顺序拆账
                </p>
            </c:if>
            <c:if test="${isBefore == false}">
                <button class="btn btn-default fl table-nobg-btn" id="autoSplitAccount" type="button"><i class="fa fa-sign-in"></i>&nbsp;自动拆账</button>
                <button class="btn btn-default fl table-nobg-btn"  id="manualSplitAccount" type="button"><i class="fa fa-plus"></i>&nbsp;手动拆账</button>

            </c:if>

            </div>

            <table id="matching" class="table table-bordered table-striped tc matching" style="min-width:1600px">
                <thead>
                  <tr class="b">
                  <tr class="b">
                      <td>期数</td>
                      <td>应还日期</td>
                      <td>应收</td>
                      <td>本期剩余应收</td>
                      <td>上期挂账</td>
                      <td>本金</td>
                      <td>利息</td>
                      <td>滞纳金</td>
                      <td>罚息</td>
                      <td>违约金</td>
                      <td>营业外收入</td>
                      <td>减免</td>
                      <td>是否结清</td>
                      <td>查账类型</td>
                      <td>上门费</td>
                      <td>服务费</td>
                      <td>保证金</td>
                      <td>展期费</td>
                      <td>退回</td>
                      <td>挂账</td>
                  </tr>
                </tr>
                </thead>
                <tbody class="f12">

                </tbody>

        </table>
        </div>
            <footer>
                <button class="btn btn-primary" type="button" id="createButton">确定拆账</button>
            </footer>
        </div>
        <!-- end widget content -->
        </div>
        </form>
</div>
</div>
</c:if>
<div class="jarviswidget" id="wid-id-5123" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
    <header>
        <h2 class="b">还款记录</h2>
    </header>

    <div>
        <div class="smart-form">
            <!-- widget content -->
            <div class="widget-body">
                <div class="mt10 mb10 ofx">
                    <div class="widget-body-nobg-toolbar" style="overflow:hidden;">

                    </div>

                    <table id="" class="table table-bordered table-striped tc " style="min-width:1600px">
                        <thead>
                        <tr class="b">
                            <th colspan="6"><h2 class="b">应还款计划</h2></th>
                            <th colspan="5"><h2 class="b">已还款</h2></th>
                        </tr>
                        <tr class="b">
                            <td>期数</td>
                            <td>应还款日期</td>
                            <td>本金</td>
                            <td>利息</td>
                            <td>服务费</td>
                            <td>小计</td>
                            <td>还款日期</td>
                            <td>本金</td>
                            <td>利息</td>
                            <td>其他费用</td>
                            <td>小计</td>
                        </tr>
                        </thead>
                        <tbody class="f12"><tr  class="input_data">
                            <c:forEach var="item" items="${repaymentPlanBeans}">
                            <c:if test="${item.actualListSize == 0}">
                        <tr>
                            <td>${item.repaymentTimes}</td>
                            <td>${item.repaymendate}</td>
                            <td><fmt:formatNumber value="${item.principalByBig}" pattern="###,###,##0.00"/></td>
                            <td><fmt:formatNumber value="${item.accrualByBig}" pattern="###,###,##0.00"/></td>
                            <td><fmt:formatNumber value="${item.serviceFeeByBig}" pattern="###,###,##0.00"/></td>
                            <td><fmt:formatNumber value="${item.subtotalByBig}" pattern="###,###,##0.00"/></td>
                            <td colspan="5">本期未还款</td>
                        </tr>
                        </c:if>
                        <c:if test="${item.actualListSize == 1}">
                            <c:forEach var="rep" items="${item.actualList}" varStatus="lineNum">
                                <tr>
                                    <c:if test="${lineNum.index == 0}">
                                        <td rowspan="${item.actualListSize}">${item.repaymentTimes}</td>
                                        <td rowspan="${item.actualListSize}">${item.repaymendate}</td>
                                        <td rowspan="${item.actualListSize}"><fmt:formatNumber value="${item.principalByBig}" pattern="###,###,##0.00"/></td>
                                        <td rowspan="${item.actualListSize}"><fmt:formatNumber value="${item.accrualByBig}" pattern="###,###,##0.00"/></td>
                                        <td rowspan="${item.actualListSize}"><fmt:formatNumber value="${item.serviceFeeByBig}" pattern="###,###,##0.00"/></td>
                                        <td rowspan="${item.actualListSize}"><fmt:formatNumber value="${item.subtotalByBig}" pattern="###,###,##0.00"/></td>
                                    </c:if>
                                    <td>${rep.repaymentDate}</td>
                                    <td><fmt:formatNumber value="${rep.capital/100}" pattern="###,###,##0.00"/></td>
                                    <td><fmt:formatNumber value="${rep.interest/100}" pattern="###,###,##0.00"/></td>
                                    <td><fmt:formatNumber value="${rep.otherFee/100}" pattern="###,###,##0.00"/></td>
                                    <td><fmt:formatNumber value="${rep.total/100}" pattern="###,###,##0.00"/></td>
                                </tr>
                            </c:forEach>




                        </c:if>
                        </c:forEach>
                        </tbody>

                    </table>
                </div>
            </div>
            <!-- end widget content -->
        </div>
    </div>
</div>

</article>
</div>
</section>
</div>
</div>

<form id="formform"  action="${contextPath}/bill/matchDetail.do" method="post" style="display: none">
    <input type="hidden" name="id" value="${billRecord.id}">
    <input type="hidden" name="billPayDate" value="${billRecord.payDate}">
    <input type="hidden" name="billQueryAmount" value="${billRecord.matchMoney}">
    <input type="hidden" name="businessType" value="${billRecord.businessType}">
    <input type="hidden" name="forID" value="${billRecord.forId}">
    <div id="input_div"></div>

</form>

<div id="autoSplitAccountDialog" title="<div class='widget-header'><h4 class='b'>自动拆账</h4></div>">
    <div class="jarviswidget" id="matchDetail-split-id-01" data-widget-deletebutton="false" data-widget-editbutton="false">
        <header>
            <h2>拆账方式</h2>
        </header>
        <!-- widget div-->
        <div>
            <form class="smart-form" onsubmit="return false" id="splitForm">
                <!-- widget edit box -->
                <div class="jarviswidget-editbox">
                    <!-- This area used as dropdown edit box -->
                </div>
                <!-- end widget edit box -->
                <!-- widget content -->
                <div class="widget-body no-padding">
                    <div class="mt10 mb10">
                        <table class="table">
                            <col width="120" />
                            <col />
                            <col width="120" />
                            <col />
                            <tbody>
                            <tr>
                                <td align="right">到账时间:</td>
                                <td>${billRecord.transDate}</td>
                                <td>到账金额:</td>
                                <td><fmt:formatNumber value="${billRecord.matchMoney/100}" pattern="###,###,##0.00"/></td>
                            </tr>
                            <tr>
                                <td align="right">余额处理:</td>
                                <td colspan="3"><input type="radio" name="balance" value="1" id="yecl_1" checked><label for="yecl_1">记为挂账</label>&nbsp;&nbsp;<input type="radio" name="balance" value="2" id="yecl_2"><label for="yecl_2">继续还款</label>&nbsp;&nbsp;<input type="radio" name="balance" value="3" id="yecl_3"><label for="yecl_3">手动调整</label></td>
                            </tr>
                            <tr>
                                <td align="right">到账金额不足:</td>
                                <td colspan="3"><input type="radio" name="isNotMoney" value="1" id="nomoney_1" checked><label for="nomoney_1">本金优先</label>&nbsp;&nbsp;<input type="radio" name="isNotMoney" value="2" id="nomoney_2"><label for="nomoney_2">利息优先</label></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- end widget content -->
            </form>
        </div>


    </div>
</div>

<%@include file="/WEB-INF/jsp/inc/common_footer_css_js.inc" %>
<script type="text/javascript" src="${contextPath}/js/bill/matchAutoSplit.js" charset="UTF-8"></script>
<script type="text/javascript" src="${contextPath}/js/plugin/jquery-json-2.5.1/src/jquery.json.js" charset="UTF-8"></script>

<script >
    $(document).ready(function () {
        var autoSplit ={};
        var add_dialog = $("#autoSplitAccountDialog").dialog({
            autoOpen: false,
            width: 600,
            resizable: false,
            modal: true,
            buttons: [
                {
                    html: "生&nbsp;&nbsp;&nbsp;&nbsp;成",
                    "class": "btn btn-default",
                    click: function () {
                        AutoSplitUtil.autoSplit();
                        $(this).dialog("close");
                    }
                }, {

                    html: "取&nbsp;&nbsp;&nbsp;&nbsp;消",
                    "class": "btn btn-default",
                    click: function () {
                        $(this).dialog("close");
                    }
                }]
        });

        $("#autoSplitAccount").click(function(){
            add_dialog.dialog("open");
        });

            AutoSplitUtil.init({
            divID:"split",
            id:'${billRecord.id}',
            accountID:'${billRecord.billID}',
            form:"splitForm",
            aotuButton:"autoSplitAccount",
            manualSplitAccount:"manualSplitAccount",
            debug: true
        });

        $("#createButton").click(function(){
            AutoSplitUtil.submit();
        });
    });

</script>

</body>
</html>
