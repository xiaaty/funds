<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>内审系统--查账管理-入账</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <%@include file="/WEB-INF/jsp/inc/common_css_js.inc" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
                <li>查账管理</li>
                <li>入账</li>
            </ol>
            <!-- end breadcrumb -->
        </div>
        
        <div class="row ml15" >
            <%--<button class="btn btn-default fl table-nobg-btn"><i class="fa fa-reply"></i>&nbsp;撤销入账</button>--%>
                <%--<button class="btn btn-default fl table-nobg-btn"><i class="fa fa-reply"></i>&nbsp;撤销匹配</button>--%>
        </div>

        <div id="content">
            <section id="widget-grid">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <!-- NEW WIDGET START -->

                        <div class="jarviswidget" id="wid-id-522" data-widget-sortable="false" data-widget-editbutton="false" data-widget-deletebutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>

                            <div>
                                <form class="smart-form" action="${contextPath}/bill/account" method="post" id="searchForm">
                                    <!-- widget content -->
                                    <div class="widget-body no-padding">
                                        <div class="mt10 mb10">
                                            <table class="table lh32">
                                                <col width="100" />
                                                <col width="320" />
                                                <col width="100" />
                                                <col width="320" />
                                                <col width="100" />
                                                <col />
                                                <tbody>
                                                    <tr>
                                                        <td class="lh32 tr">交易日期：</td>
                                                        <td>
                                                            <section class="fl">
                                                                <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                                    <input type="text" name="map[startDateInt]" class="selectdate" placeholder="请选择时间" value="${query.map['startDateInt']}">
                                                                </label>
                                                            </section>
                                                            <span class="fl">&nbsp;至&nbsp;</span>
                                                            <section class="fl">
                                                                <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                                    <input type="text" name="map[endDateInt]" class="selectdate" placeholder="请选择时间" value="${query.map['startDateInt']}">
                                                                </label>
                                                            </section>
                                                        </td>
                                                        <td class="lh32 tr">交易时间：</td>
                                                        <td>
                                                            <section class="fl">
                                                                <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                                    <input type="text" name="map[startTimeInt]" class="selectdate_time" placeholder="请选择时间" value="${query.map['startTimeInt']}">
                                                                </label>
                                                            </section>
                                                            <span class="fl">&nbsp;至&nbsp;</span>
                                                            <section class="fl">
                                                                <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                                    <input type="text" name="map[endTimeInt]" class="selectdate_time" placeholder="请选择时间" value="${query.map['endTimeInt']}">
                                                                </label>
                                                            </section>
                                                        </td>
                                                        <td class="lh32 tr">交易行名：</td>
                                                        <td>
                                                            <label class="input" style="width:200px;">
                                                                <input type="text" name="map[bankName]" class="s"  value="${query.map['bankName']}">
                                                            </label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="lh32 tr">收入金额：</td>
                                                        <td>
                                                            <section class="fl">
                                                                <label class="input" style="width:140px;">
                                                                    <input type="text" name="map[startIncome]" value="${query.map['startIncome']}">
                                                                </label>
                                                            </section>
                                                            <span class="fl">&nbsp;至&nbsp;</span>
                                                            <section class="fl">
                                                                <label class="input" style="width:140px;">
                                                                    <input type="text" name="map[endIncome]"  value="${query.map['endIncome']}">
                                                                </label>
                                                            </section>

                                                        </td>
                                                        <td class="lh32 tr">客户姓名：</td>
                                                        <td>
                                                            <label class="input"  style="width:302px">
                                                                <input type="text" name="map[customerName]" value="${query.map['customerName']}"/>
                                                            </label>
                                                        </td>
                                                        <td class="lh32 tr">交易摘要：</td>
                                                        <td>
                                                            <label class="input" style="width:200px">
                                                                <input type="text" name="" name="map[summary]" value="${query.map['summary']}"/>
                                                            </label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="lh32 tr">入账时间：</td>
                                                        <td>
                                                            <section class="fl">
                                                                <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                                    <input type="text" name="map[accountStartTime]" class="selectdate" placeholder="请选择时间" value="${query.map['accountStartTime']}">
                                                                </label>
                                                            </section>
                                                            <span class="fl">&nbsp;至&nbsp;</span>
                                                            <section class="fl">
                                                                <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                                    <input type="text" name="map[accountEndTime]" class="selectdate" placeholder="请选择时间" value="${query.map['accountEndTime']}">
                                                                </label>
                                                            </section>
                                                        </td>
                                                        <td class="lh32 tr">大区经理：</td>
                                                        <td>
                                                            <label class="input" style="width:302px">
                                                                <input type="text" name="" value="${query.map['startDateInt']}" />
                                                            </label>
                                                        </td>
                                                        <td class="lh32 tr">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区：</td>
                                                        <td>
                                                            <label class="input" style="width:200px">
                                                                <input type="text" name="map[district]" value="${query.map['district']}"/>
                                                            </label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="lh32 tr">大&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区：</td>
                                                        <td>
                                                            <label class="input" style="width:302px">
                                                                <input type="text" name="map[region]" value="${query.map['region']}"/>
                                                            </label>
                                                        </td>
                                                        <td class="lh32 tr">门&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;店：</td>
                                                        <td>
                                                            <label class="input" style="width:302px">
                                                                <input type="text" name="map[workshop]" value="${query.map['workshop']}"/>
                                                            </label>
                                                        </td>
                                                        <td class="lh32 tr">合同编号：</td>
                                                        <td>
                                                            <label class="input" style="width:200px">
                                                                <input type="text" name="map[contractNo]"value="${query.map['contractNo']}"/>
                                                            </label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="lh32 tr">查&nbsp;&nbsp;询&nbsp;&nbsp;人：</td>
                                                        <td>
                                                            <label class="input" style="width:302px">
                                                                <input type="text" name="map[requester]" value="${query.map['requester']}" >
                                                            </label>
                                                        </td>
                                                        <td class="lh32 tr">贷款类型：</td>
                                                        <td>
                                                            <label class="input" style="width:302px">
                                                               <input type="text" name="map[loanType]" value="${query.map['district']}">
                                                            </label>
                                                        </td>
                                                       <td colspan="2"></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer>
                                            <button class="btn btn-primary" type="submit">查&nbsp;&nbsp;&nbsp;询</button>
                                        </footer>
                                    </div>
                                    <!-- end widget content -->
                                </form>
                            </div>
                        </div>

                        <%--<div id="wid-id-523">
                            <div class="alert alert-info fade in">
                                <button class="close" data-dismiss="alert">×</button>
                                <i class="fa-fw fa fa-info"></i>
                                <strong class="f14 b">提醒</strong>
                                <br>
                                <p class="f12 lh200">收入金额累计：<span>16,544.66</span> 元</p>
                            </div>
                        </div>--%>

                        <div class="jarviswidget" id="wid-id-524" data-widget-sortable="false" data-widget-editbutton="false" data-widget-deletebutton="false">
                            <header>
                                <h2 class="b">入账</h2>
                            </header>

                            <div>
                                <div class="smart-form">
                                    <!-- widget content -->
                                    <div class="widget-body">
                                        <div class="widget-body-nobg-toolbar" style="overflow:hidden; height:10px">
                                        </div>
                                        <table id="borrow-rep-table1" class="table table-bordered table-striped tc" style="min-width:1400px">
                                            <thead>
                                                <tr class="b">
                                                    <td>交易日期</td>
                                                    <td>交易时间</td>
                                                    <td>收入金额</td>
                                                    <td>匹配金额</td>
                                                    <td>交易行名</td>
                                                    <td>对方账号</td>
                                                    <td>对方户名</td>
                                                    <td>交易摘要</td>
                                                    <td>交易渠道</td>
                                                    <td>合同编号</td>
                                                    <td>客户姓名</td>
                                                    <td>大区经理</td>
                                                    <td>地区</td>
                                                    <td>大区</td>
                                                    <td>门店</td>
                                                    <td>查询余额</td>
                                                    <td>银行</td>
                                                    <td>到账日期</td>
                                                    <td>拆账状态</td>
                                                  <%--  <td>详细</td>--%>
                                                </tr>
                                            </thead>
                                            <tbody class="f12">
                                            <c:forEach var="record" items="${page.list}">
                                                <tr>
                                                    <td>${record.transDate}</td>
                                                    <td>${record.transTime >0 ?record.transTime:""}</td>
                                                    <td><fmt:formatNumber value="${record.income/100}" pattern="#,###,###,###,###,##0.00"/></td>
                                                    <td><fmt:formatNumber value="${record.matchMoney/100}" pattern="#,###,###,###,###,##0.00"/></td>
                                                    <td>${record.bank}</td>
                                                    <td>${record.fromAccount}</td>
                                                    <td>${record.fromName}</td>
                                                    <td>${record.summary}</td>
                                                    <td>${record.channel}</td>
                                                    <td>${record.contractNo}</td>
                                                    <td>${record.customerName}</td>

                                                    <td>${record.regionManager}</td>
                                                    <td>${record.district}</td>
                                                    <td>${record.region}</td>
                                                    <td>${record.workshop}</td>

                                                    <td><fmt:formatNumber value="${record.queryAmount/100}" pattern="#,###,###,###,###,##0.00"/></td>
                                                    <td>${record.bank}</td>
                                                    <td>${record.payDate}</td>
                                                    <td>${matchTypeNameMap[record.matchToken]}</td>
                                                   <%-- <td><i class="fa fa-exchange"></i>--%>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- end widget content -->
                                </div>
                            </div>
                        </div>
                    </article>
                </div>
            </section>
        </div>

        <!-- add role dialog  -->
        <div id="upload" title="<div class='widget-header'><h4 class='b'>导入</h4></div>">
            <form>
                <fieldset>
                    <!-- <input name="authenticity_token" type="hidden"> -->
                    <div class="mt20 mb20 ml50 ofh">
                        <label class="lh30 color01">只能导入EXCEL：</label>
                        <input class="form-control fl" style="width:80%" id="tab_title" value="" type="file">
                        <i class="fa fa-folder-open fl fa-lg ml10 mt10"></i>
                    </div>
                </fieldset>
            </form>
        </div>

         <%@include file="/WEB-INF/jsp/inc/common_footer_css_js.inc" %>


        <script>
            $(document).ready(function () {
                pageSetUp();


                DT_page("borrow-rep-table1", true, '${page.JSON}', $("#searchForm"));


                /*
                 * CONVERT DIALOG TITLE TO HTML
                 * REF: http://stackoverflow.com/questions/14488774/using-html-in-a-dialogs-title-in-jquery-ui-1-10
                 */

                var add_dialog = $("#upload").dialog({
                    autoOpen: false,
                    width: 480,
                    resizable: false,
                    modal: true,
                    buttons: [{
                        html: "确&nbsp;&nbsp;&nbsp;&nbsp;认",
                        "class": "btn btn-default",
                        click: function () {
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


                $("#btn_upload").button().click(function () {
                    add_dialog.dialog("open");
                    return false;
                });

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

</body>

</html>
