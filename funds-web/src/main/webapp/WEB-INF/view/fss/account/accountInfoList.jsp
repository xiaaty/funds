<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>账户管理--旧版账户--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">

    <%@include file="../../../view/include/common_css_js.jsp" %>
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

        .redFont {
            color: red;
        }

        /*.floatRight {
            !*float: right;*!
            !*margin-right: 30px;*!
            margin-bottom: 12px;
        }*/
    </style>

</head>

<body>
<%@include file="../../../view/include/menu.jsp" %>
<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>系统管理</li>
            <li>富有金账户对账文件获取</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <!-- NEW WIDGET START -->
                    <div class="jarviswidget" id="oldAccMsg" data-widget-deletebutton="false"
                         data-widget-editbutton="false">
                        <header>
                            <h2 class="redFont">快速查询</h2>
                        </header>
                        <div>
                            <form class="smart-form" action="${contextPath}/account/info/accountInfoList"  method="post" id="Form">
                                <div class="jarviswidget-editbox">
                                </div>
                                <div class="widget-body no-padding">
                                    <div class="mt10 mb10">
                                        <table class="table lh32">
                                            <col width="150" />
                                            <col width="220" />
                                            <col width="130" />
                                            <col width="220" />
                                            <col width="150" />
                                            <col />
                                            <tbody>
                                            <tr></tr>
                                            <tr>
                                                <td class="tr">信息类型：</td>
                                                <td>
                                                    <label class="input">
                                                        <input type="text" style="width:210px" name="tradeType" value="${map.tradeType}" />
                                                    </label>
                                                </td>
                                                <td class="tr">业务代码：</td>
                                                <td>
                                                    <label class="input" style="width:210px" >
                                                        <input type="text" name="businessCode" value="${map.businessCode}" />
                                                    </label>
                                                </td>
                                                <td class="tr">用户名/出账用户名:</td>
                                                <td>
                                                    <label class="input"  style="width:210px" >
                                                        <input type="text" name="userName" value="${map.userName}"/>
                                                    </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="tr">交易/充值/提现日期：</td>
                                                <td colspan="4">
                                                    <section class="fl">
                                                        <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                            <input type="text" maxlength="10" name="tradingTime" class="selectdate" placeholder="请选择时间" value="${map.tradingTime}">
                                                        </label>
                                                    </section>
                                                </td>
                                                <c:choose>
                                                    <c:when test="${failSize > 0 }">
                                                        <td colspan="2"><a href="${contextPath}/account/info/accountInfoFialList" class="redFont" title="点击手动抓取" >有抓取失败文件</a></td>
                                                    </c:when>
                                                </c:choose>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <footer>
                                        <button type="submit" class="btn btn-primary">查&nbsp;&nbsp;&nbsp;询</button>
                                    </footer>
                                </div>
                                <!-- end widget content -->
                            </form>
                        </div>
                    </div>

                    <!-- 手动抓取指定日期对账文件 -->
                    <div class="jarviswidget" id="manuallyCrawl" data-widget-deletebutton="false"
                         data-widget-editbutton="false">
                        <header>
                            <h2 class="redFont">手动抓取</h2>
                        </header>
                        <div>
                            <form class="smart-form" action="${contextPath}/account/info/accountInfoEdit/-1" onsubmit="return beforeManuallyCrawl()" method="post" >
                                <div class="jarviswidget-editbox">
                                </div>
                                <div class="widget-body no-padding">
                                    <div class="mt10 mb10">
                                        <table class="table lh32">
                                            <col width="20" />
                                            <col width="220" />
                                            <col width="150" />
                                            <col width="220" />
                                            <col width="150" />
                                            <col />
                                            <tbody>
                                            <tr></tr>
                                            <tr>
                                                <td></td>
                                                <td class="tr">信息类型：</td>
                                                <td>
                                                    <select class="select02" style="width:202px;" name="tradeType" id="tradeType">
                                                        <option value="" >请选择</option>
                                                        <option <c:if test="${FAIEntity.tradeType == 'DJJD'}"> selected="selected" </c:if> value="DJJD" >冻结/解冻</option>
                                                        <option <c:if test="${map.tradeTypeInfo == 'ZZ'}"> selected="selected" </c:if> value="ZZ" >转账</option>
                                                        <option <c:if test="${map.tradeTypeInfo == 'HB'}"> selected="selected" </c:if> value="HB" >划拨</option>
                                                        <option <c:if test="${map.tradeTypeInfo == 'WTCZ'}"> selected="selected" </c:if> value="WTCZ" >委托充值</option>
                                                        <option <c:if test="${map.tradeTypeInfo == 'WTTX'}"> selected="selected" </c:if> value="WTTX" >委托提现</option>
                                                        <option <c:if test="${map.tradeTypeInfo == 'YSQ'}"> selected="selected" </c:if> value="YSQ" >预授权交易</option>
                                                    </select>
                                                    <%--<label class="input">
                                                        <input type="text" style="width:210px" name="tradeType" value="${map.tradeType}" />
                                                    </label>--%>
                                                </td>
                                                <td class="tr">交易/充值/提现日期：</td>
                                                <td colspan="4">
                                                    <section class="fl">
                                                        <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                            <input type="text" maxlength="10" id="tradingTime" name="tradingTime" class="selectdate" placeholder="请选择时间" value="${map.tradingTime}" title="请选择手动抓取时间">
                                                        </label>
                                                    </section>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <footer>
                                        <button type="submit" class="btn btn-primary" >抓&nbsp;&nbsp;&nbsp;取</button>
                                    </footer>
                                </div>
                                <!-- end widget content -->
                            </form>
                        </div>
                    </div>
                    <!-- NEW WIDGET START -->
                    <!-- 	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-01" data-widget-deletebutton="false"
                         data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>账户信息列表</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15"
                                           style="min-width:3800px;">
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <thead>
                                        <tr>
                                            <td>序号</td>
                                            <td>信息类型</td>
                                            <td>业务代码</td>
                                            <td>交易来源</td>
                                            <td>合同号</td>
                                            <td>交易流水号/充值流水号</td>
                                            <td>富友账务流水号</td>
                                            <td>交易/充值/提现时间</td>
                                            <td>交易/充值/提现金额</td>
                                            <td>用户账号/出账账号/账号</td>
                                            <td>用户名/出账用户名</td>
                                            <td>入账账号</td>
                                            <td>入账账号名</td>
                                            <td>总金额</td>
                                            <td>余额</td>
                                            <td>备注</td>
                                            <td>返回码</td>
                                            <td>状态</td>
                                            <td>账户状态</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t" varStatus="Status">
                                            <tr class="success">
                                                <td>${Status.index}</td>
                                                <td>${t.tradeType}</td>
                                                <td>${t.businessCode}</td>
                                                <td>${t.tradeSources}</td>
                                                <td>${t.contractNum}</td>
                                                <td>${t.batch}</td>
                                                <td>${t.batchFoiuFinance}</td>
                                                <td><fmt:formatDate value="${t.tradingTime}" pattern="yyyy-MM-dd"/></td>
                                                <td>${t.transactionAmount}</td>
                                                <td>${t.userAccount}</td>
                                                <td>${t.userName}</td>
                                                <td>${t.inAccount}</td>
                                                <td>${t.inUserName}</td>
                                                <td>${t.totalMoney}</td>
                                                <td>${t.balance}</td>
                                                <td>${t.remark}</td>
                                                <td>${t.returnNum}</td>
                                                <td>${t.state}</td>
                                                <td>${t.accountState}</td>
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
<%@include file="../../../view/include/common_footer_css_js.jsp" %>
<script src="${contextPath}/js/jquery.form.js"></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#Form"));
    });

    $('.selectdate').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        autoclose: 1,
        format:'yyyy-mm-dd',
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    if(${grabState == "1"}){
        jAlert("抓取成功","提示消息");
    }else if(${grabState == "-1"}){
        jAlert("抓取失败，确认文件是否存在","提示消息");
    }

    function beforeManuallyCrawl(){
        $("#tradeType").val();
        $("#tradingTime").val();
        var tradeType =  $("#tradeType").val() != null ? $("#tradeType").val().replace(/(^\s*)|(\s*$)/g, "") : null;
        var tradingTime = $("#tradingTime").val() != null ? $("#tradingTime").val().replace(/(^\s*)|(\s*$)/g, "") : null;
        if(!(tradeType != null && tradeType != '' && tradingTime != null && tradingTime != '')){
            jAlert("信息类型/交易日期不能为空","提示消息");
            return false;
        }
    }

</script>

<%@include file="../../../view/include/foot.jsp" %>
</body>

</html>