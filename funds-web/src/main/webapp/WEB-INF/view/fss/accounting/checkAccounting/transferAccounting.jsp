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
            <li>转账对账</li>
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
                           
                                <form class="smart-form" id="mortgageePayment" action="${contextPath}/checkAccounting/fundsOrder/${type}" method="post" >
                              
                                    <!-- widget edit box -->
                                    <div class="jarviswidget-editbox">
                                        <!-- This area used as dropdown edit box -->
                                    </div>
                                    <!-- end widget edit box -->
                                    <!-- widget content -->
                                    <div class="widget-body no-padding">
                                        <div class="mt10 mb10">
                                            <table class="table lh32">
                                                <col width="100" />
                                                <col width="220" />
                                                <col width="100" />
                                                <col width="220" />
                                                <col width="100" />
                                                <col width="220" />
                                                <col />
                                                <tbody>
                                                    <tr></tr>
                                                    <tr>
                                                        <td class="tr" nowrap="nowrap">订单号：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="orderNo" value="${map.orderNo}">
                                                            </label>
                                                        </td>
                                                        <%--<td class="tr" nowrap="nowrap">手机号：</td>--%>
                                                        <%--<td nowrap="nowrap">--%>
                                                            <%--<label class="input" style="width:210px" >--%>
                                                                <%--<input type="text" name="mobile" id="mobile" value="${map.mobile}">--%>
                                                            <%--</label>--%>
                                                        <%--</td>--%>
                                                        <td class="tr" nowrap="nowrap">是否已处理：</td>
                                                        <td nowrap="nowrap">
                                                            <label>
                                                                <select id = "handleState" name = "handleState" style="width:150px;height: 30px;">
                                                                    <option value="">所有</option>
                                                                    <option  <c:if test="${map.handleState=='98010001'}"> selected="selected" </c:if> value="98010001" >已处理</option>
                                                                    <option  <c:if test="${map.handleState=='98010002'}"> selected="selected" </c:if> value="98010002" >未处理</option>
                                                                </select>
                                                            </label>
                                                        </td>
                                                        <%--</tr>--%>
                                                    <%--<tr>--%>
                                                        <td class="tr">交易日期：</td>
                                                        <td colspan="3">
                                                            <section class="fl">
                                                                <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                                    <input type="text" maxlength="10" readonly="readonly" id="startTime"  name="startTime" class="selectdate" placeholder="请选择时间" value="${map.startTime}">
                                                                </label>
                                                            </section>
                                                            <span class="fl">&nbsp;至&nbsp;</span>
                                                            <section class="fl">
                                                                <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                                    <input type="text" maxlength="10" readonly="readonly"  id="endTime" name="endTime" class="selectdate" placeholder="请选择时间" value="${map.endTime}">
                                                                </label>
                                                            </section>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer>
                                            <button class="btn btn-primary" onclick="javascript:void(0);">查&nbsp;&nbsp;&nbsp;询</button>
                                        </footer>
                                    </div>
                                </form>
                            </div>


                        </div>
    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-darken" id="borrowerLoan"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>转账异常列表</h2>
                        </header>
                        <div>
                            <form class="smart-form" id="">
                                <div class="jarviswidget-editbox">
                                </div>
                                <div class="widget-body">
                                    <div class="widget-body-nobg-toolbar" style="overflow:hidden;">
                                    </div>
                                <div class="widget-body">
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:4400px;">
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
                                        <col width="200"/>
                                        <col width="200"/>
                                        <col width="200"/>
                                        <thead>
                                        <tr>
                                            <td></td> 
                                            <td>出账账户编号</td>
                                            <td>入账账户编号</td>
                                            <td>订单编号</td>
                                            <td>订单类型 </td>
                                            <td>订单来源编号   </td>
                                            <td>来源类型</td>
                                            <td>订单金额(元) </td>
                                            <td>订单状态</td>
                                            <td>第三方返回代码</td>
                                            <td>第三方返回信息 </td>
                                            <td>订单创建时间 </td>
                                            <td>最后更新时间 </td>
                                            <td>第三方支付类型 </td>
                                            <td>手续费 </td>
                                            <td>出账客户id </td>
                                            <td>出账出借编号 </td>
                                            <td>合同编号 </td>
                                            <td>新订单类型 </td>
                                            <td>交易类型 </td>
                                            <td>异常状态 </td>
                                            <td>是否已处理 </td>
                                            <td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t"  varStatus="l">
                                                <tr>
                                                    <td>${l.index+1}</td>
                                                    <td>${t.accountId}</td>
                                                    <td>${t.toAccountId}</td>
                                                    <td>${t.orderNo}</td>
                                                    <td>${t.orderType}</td>
                                                    <td>${t.orderFrormId}</td>
                                                    <td>${t.orderSource}</td>
                                                    <td align="left">
                                                        <fss:money money="${t.orderAmount}"/>
                                                    </td>
                                                    <td>${t.orderState}</td>
                                                    <td>${t.retCode}</td>
                                                    <td>${t.retMessage}</td>
                                                    <td><fss:fmtDate value="${t.createTime}" /></td>
                                                    <td><fss:fmtDate value="${t.lastModifyTime}" /></td>
                                                    <td>${t.thirdPartyType}</td>
                                                    <td>
                                                        <fss:money money="${t.chargeAmount}"/>
                                                    </td>
                                                    <td>${t.custId}</td>
                                                    <td>${t.lendNo}</td>
                                                    <td>${t.loanNo}</td>
                                                    <td>${t.newOrderType}</td>
                                                    <td>${t.tradeType}</td>
                                                    <td><fss:dictView key="${t.abnormalState}" /></td>
                                                    <td><fss:dictView key="${t.handleState}" /></td>
                                                    <td>
                                                        <a href="${contextPath}/checkAccounting/fundsOrder/${type}/${t.orderNo}">查看</a>
                                                        <%--<a href="" onclick="returnTrade(${t.orderNo})">反交易</a>--%>
                                                    </td>
                                                </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
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
    //校验函数
    function validateCheck() {
        return true;
    }

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
    function returnTrade(orderNo) {
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

</script>


<%@include file= "../../../../view/include/foot.jsp"%>

</body>

</html>