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
                                <h2>对账表数据</h2>
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
                                                    <td class="tr">交易流水号：</td>
                                                    <td  >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                ${ckEntity.orderNo}
                                                            </label>
                                                        </section>
                                                    </td>
                                                    <td class="tr">交易时间：</td>
                                                    <td  >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                ${ckEntity.tradeTime}
                                                            </label>
                                                        </section>
                                                    </td>
                                                    <td class="tr">交易金额(元)：</td>
                                                    <td >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                <fss:money money="${ckEntity.amount}"/>
                                                            </label>
                                                        </section>
                                                    </td>
                                                    <td class="tr">账户名：</td>
                                                    <td  >
                                                        <section class="fl">
                                                        <label class="input" >
                                                        ${ckEntity.accName}
                                                        </label>
                                                         </section>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="tr">客户姓名：</td>
                                                    <td  >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                ${ckEntity.userName}
                                                            </label>
                                                        </section>
                                                    </td>
                                                    <td class="tr">入账账户名：</td>
                                                    <td  >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                ${ckEntity.toAccName}
                                                            </label>
                                                        </section>
                                                    </td>

                                                    <td class="tr">入账客户名：</td>
                                                    <td  >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                ${ckEntity.toUserName}
                                                            </label>
                                                        </section>
                                                    </td>
                                                    <td class="tr">状态：</td>
                                                    <td  >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                ${ckEntity.status}
                                                            </label>
                                                        </section>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="tr">交易类型：</td>
                                                    <td  >
                                                        <section class="fl">
                                                            <label class="input" >
                                                                <fss:dictView key="${ckEntity.tradeType}" />
                                                            </label>
                                                        </section>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>

                                        </div>
                                        <footer>
                                            <c:if test="${type== '1104'}">
                                                <input type="button" id="exportExcelBtn" class="btn btn-primary" onclick="enterAccount(${ckEntity.orderNo});" value="入&nbsp;&nbsp;&nbsp;账"/>
                                            </c:if>
                                            <c:if test="${type== '1119' || type=='1108'}">
                                                <input type="button" id="exportExcelBtn" class="btn btn-primary" onclick="returnTrade(${ckEntity.orderNo});" value="反交易"/>
                                            </c:if>

                                            <input type="button" id="export" class="btn btn-default" onclick="handleState(${ckEntity.orderNo});" value="标记为已处理"/>
                                        </footer>
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
                            <h2>流水记录</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="">
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                    <div class="widget-body-nobg-toolbar" style="overflow:hidden;">
                                        <button type="button" class="btn btn-default fl table-nobg-btn" id="return" ><i class="fa fa-plus"></i>&nbsp;返回</button>&nbsp;
                                        <%-- <button type="button" class="btn btn-default fl table-nobg-btn" id="btn_detail"><i class="fa fa-list-ul"></i>&nbsp;详情</button>--%>
                                    </div>
                                <div class="widget-body">
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2050px;">
                                        <col width="50" />
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="100"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="300"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <thead>
                                        <tr>
                                            <td></td>
                                            <td>订单号</td>
                                            <td>交易流水号</td>
                                            <td>操作类型</td>
                                            <td>账户ID</td>
                                            <td>来源账户id</td>
                                            <td>交易类型  </td>
                                            <td>变动金额  </td>
                                            <td>摘要</td>
                                            <td>创建时间 </td>
                                            <td>修改日期 </td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t"  varStatus="l">
                                                <tr>
                                                    <td>${l.index+1}</td>
                                                    <td>${t.orderNo}</td>
                                                    <td>${t.sOrderNo}</td>
                                                    <td>${t.actionType}</td>
                                                    <td>${t.accountId}</td>
                                                    <td>${t.oAccountId}</td>
                                                    <td>${t.fundType}</td>
                                                    <td>
                                                        <fss:money money="${t.amount}"/>
                                                    </td>
                                                    <td>${t.sumary}</td>
                                                    <td><fss:fmtDate value="${t.createTime}" /></td>
                                                    <td><fss:fmtDate value="${t.modifyTime}" /></td>
                                                </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
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
    function enterAccount(orderNo){
        location.href="${contextPath}/checkAccounting/addAccounting/${type}/"+orderNo;
    }
    function handleState(orderNo){
        location.href="${contextPath}/checkAccounting/handleState/${type}/"+orderNo;
    }
    function returnTrade(orderNo) {
        if(confirm("您确认进行此操作码？")){
            $.ajax({
                url:"${contextPath}/checkAccounting/queryForFuiou",
                method:"post",
                data:{orderNo:orderNo},
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                dateType:"json",
                success : function (data){
                    if (data.code == '0000') {
                        alert(data.msg);
                    }else{
                        alert(data.msg);
                    }
                },
                error : function (XMLHttpRequest, textStatus, errorThrown) {
                }
            })
        }
    }


</script>


<%@include file= "../../../../view/include/foot.jsp"%>

</body>

</html>