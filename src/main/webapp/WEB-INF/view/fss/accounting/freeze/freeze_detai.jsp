<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>出借资产--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
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
        #footer{position: absolute;bottom: 10px;z-index: 100px;}
        .footer-bottom{font-size:13px}
        .footer-bottom ul>li{padding:0}
        .footer-bottom ul>li+li:before{padding:0 10px;color:#ccc;content:"|"}
    </style>

</head>

<body>
<%@include file="../../../../view/include/menu.jsp"%>


<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
          <li>记账信息</li>
            <li>资金冻结</li>
            <li>冻结详情</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-30"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>冻结详情</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="freezeForm">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                <div class="mb20" id="wid-id-713">
                                            <button class="btn btn-default table-nobg-btn" type="button" onclick="location.href='${contextPath}/accounting/freeze/list'" ><i class="fa fa-minus"></i>返回</button>
                                      </div>
                                     <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2300px;">
                                        <col width="50" />
                                        <col width="150" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="250" />
                                        <col width="250" />
                                        <col width="250" />
                                        <col width="250" />
                                        <col width="250" />
                                        <col width="250" />
                                        <thead>
                                        <tr>
                                            <td></td>
                                            <td>客户编号</td>
                                            <td>资金平台账号</td>
                                            <td>客户姓名</td>
                                            <td>资金类型</td>
                                            <td>交易类型</td>
                                            <td>冻结金额</td>
                                            <td>解冻金额 </td>
                                            <td>冻结余额 </td>
                                            <td>交易时间</td>
                                            <td>交易日期 </td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t" varStatus="l">
                                                <tr>
                                                    <td>${l.index+1}</td>
                                                    <td>${t.custNo}</td>
                                                    <td>${t.accNo}</td>
                                                    <td>${t.custName}</td>
                                                    <td>${t.fundType}</td>
                                                    <td>${t.tradeType}</td>
                                                    <td>${t.freezeAmount}</td>
                                                    <td>${t.unfreezeAmount}</td>
                                                    <td>${t.freezeBalance}</td>
                                                    <td>${t.tradeTime}</td>
                                                    <td>${t.tradeDate}</td>
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
<%@include file="../../../../view/include/common_footer_css_js.jsp"%>
</div>


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#freezeForm"));
    });

</script>

<%@include file= "../../../../view/include/foot.jsp"%>
</body>

</html>