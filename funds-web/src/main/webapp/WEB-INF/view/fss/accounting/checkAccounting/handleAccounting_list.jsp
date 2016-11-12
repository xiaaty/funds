<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>对账管理--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <%@include file= "../../../../view/include/common_css_js.jsp"%>
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
        #footer{position: absolute;bottom: 10px;z-index: 100px;}
        .footer-bottom{font-size:13px}
        .footer-bottom ul>li{padding:0}
        .footer-bottom ul>li+li:before{padding:0 10px;color:#ccc;content:"|"}
    </style>

</head>

<body>
<%@include file= "../../../../view/include/menu.jsp"%>


<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>对账管理</li>
            <li>交易对账</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
    <div id="contents">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="jarviswidget" id="bloan"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                           
                                <form class="smart-form" id="mortgageePayment" action="${contextPath}/checkAccounting/checkAccountList" method="post" >
                              
                                    <!-- widget edit box -->
                                    <div class="jarviswidget-editbox">
                                        <!-- This area used as dropdown edit box -->
                                    </div>
                                    <!-- end widget edit box -->
                                    <!-- widget content -->
                                    <div class="widget-body no-padding">
                                        <div class="mt10 mb10">
                                            <table class="table lh32">
                                                <col width="130" />
                                                <col width="150" />
                                                <col width="130" />
                                                <col width="150" />
                                                <col width="130" />
                                                <col width="100" />
                                                <col width="150" />
                                                <col width="150" />
                                                <col />
                                                <tbody>
                                                <tr></tr>
                                                <tr>
                                                    <td class="tr">订单编号：</td>
                                                    <td  >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                ${orderEntity.orderNo}
                                                            </label>
                                                        </section>
                                                    </td>
                                                    <td class="tr">出账账户编号：</td>
                                                    <td  >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                ${orderEntity.accountId}
                                                            </label>
                                                        </section>
                                                    </td>
                                                    <td class="tr">入账账户编号：</td>
                                                    <td  >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                ${orderEntity.toAccountId}
                                                            </label>
                                                        </section>
                                                    </td>
                                                    <td class="tr">订单金额：</td>
                                                    <td  >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                <fss:money money="${orderEntity.orderAmount}"/>
                                                            </label>
                                                        </section>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="tr">订单状态：</td>
                                                    <td  >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                ${orderEntity.orderState}
                                                            </label>
                                                        </section>
                                                    </td>
                                                    <td class="tr">第三方返回代码：</td>
                                                    <td  >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                ${orderEntity.retCode}
                                                            </label>
                                                        </section>
                                                    </td>

                                                    <td class="tr">第三方返回信息：</td>
                                                    <td  >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                ${orderEntity.retMessage}
                                                            </label>
                                                        </section>
                                                    </td>
                                                    <td class="tr">订单创建时间：</td>
                                                    <td  >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                <fss:fmtDate value="${orderEntity.createTime}" />
                                                            </label>
                                                        </section>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="tr">最后更新时间：</td>
                                                    <td  >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                <fss:fmtDate value="${orderEntity.lastModifyTime}" />
                                                            </label>
                                                        </section>
                                                    </td>
                                                    <td class="tr">交易类型：</td>
                                                    <td  >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                <fss:dictView key="${orderEntity.tradeType}" />
                                                            </label>
                                                        </section>
                                                    </td>

                                                    <td class="tr">异常状态：</td>
                                                    <td  >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                <fss:dictView key="${orderEntity.abnormalState}" />
                                                            </label>
                                                        </section>
                                                    </td>
                                                    <td class="tr">是否已处理：</td>
                                                    <td  >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                <fss:dictView key="${orderEntity.handleState}" />
                                                            </label>
                                                        </section>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                    <!-- end widget content -->
                                </form>
                            </div>


                        </div>
                        <%--<div class="jarviswidget-editbox">--%>


                        <%--</div>--%>
    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-darken" id="borrowerLoan"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>交易对账</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="">
                                <div class="jarviswidget-editbox">
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                    <div class="widget-body-nobg-toolbar" style="overflow:hidden;">
                                        <button type="button" class="btn btn-default fl table-nobg-btn" id="return" ><i class="fa fa-plus"></i>&nbsp;返回</button>&nbsp;
                                        <%-- <button type="button" class="btn btn-default fl table-nobg-btn" id="btn_detail"><i class="fa fa-list-ul"></i>&nbsp;详情</button>--%>
                                    </div>
                                <div class="widget-body">
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:4550px;">
                                        <col width="50" />
                                        <col width="200" />
                                        <col width="150" />
                                        <col width="150" />
                                        <col width="150" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="150" />
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="150"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="150"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <thead>
                                        <tr>
                                            <td></td> 
                                            <td>交易流水号</td>
                                            <td>交易时间</td>
                                            <td>记账流水  </td>
                                            <td>记账日期   </td>
                                            <td>充值方式</td>
                                            <td>交易金额(元) </td>
                                            <td>客户ID</td>
                                            <td>账户</td>
                                            <td>用户名 </td>
                                            <td>用户名称 </td>
                                            <td>入账客户ID </td>
                                            <td>入账账户 </td>
                                            <td>入账用户名 </td>
                                            <td>入账用户名称 </td>
                                            <td>业务合同号 </td>
                                            <td>项目号 </td>
                                            <td>备注 </td>
                                            <td>状态 </td>
                                            <td>交易类型 </td>
                                            <td>是否对账 </td>
                                            <td>对账结果 </td>
                                            <td>异常状态 </td>
                                            <td>导入日期 </td>
                                            <td>修改日期 </td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t"  varStatus="l">
                                                <tr>
                                                    <td>${l.index+1}</td>
                                                    <%--<td>${t.mortgageeAccNo}</td>
                                                    <td>${t.accNo}</td>--%>
                                                    <td>${t.orderNo}</td>
                                                    <td>${t.tradeTime}</td>
                                                    <td>${t.accountingNo}</td>
                                                    <td>${t.accountingTime}</td>
                                                    <td>${t.rechargeWay}</td>
                                                    <td>
                                                        <fss:money money="${t.amount}"/>
                                                    </td>
                                                    <td>${t.custId}</td>
                                                    <td>${t.accNo}</td>
                                                    <td>${t.accName}</td>
                                                    <td>${t.userName}</td>
                                                    <td>${t.toCustId}</td>
                                                    <td>${t.toAccNo}</td>
                                                    <td>${t.toAccName}</td>
                                                    <td>${t.toUserName}</td>
                                                    <td>${t.contractNo}</td>
                                                    <td>${t.itemNo}</td>
                                                    <td>${t.remark}</td>
                                                    <td>${t.status}</td>
                                                    <td>
                                                        <fss:dictView key="${t.tradeType}" />
                                                    </td>
                                                    <td>
	                                                    <fss:dictView key="${t.accountingStatus}" />
                                                    </td>
                                                    <td>
	                                                    <fss:dictView key="${t.accountingResult}" />
                                                    </td>
                                                    <td>
	                                                    <fss:dictView key="${t.abnormalState}" />
                                                    </td>

                                                    <td><fss:fmtDate value="${t.createTime}" /></td>
                                                    <td><fss:fmtDate value="${t.modifyTime}" /></td>
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



<%@include file= "../../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
<script src="${contextPath}/js/jquery.blockUI.js"></script>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#mortgageePayment"));
    });
    $(function () {
    });
    function selectedInit() {

    }
    $('#return').click(function () {
        location.href="${contextPath}/checkAccounting/fundsOrder/${type}";
    })



</script>


<%@include file= "../../../../view/include/foot.jsp"%>

</body>

</html>