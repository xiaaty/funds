<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<%@include file= "../../../view/include/common_css_js.jsp"%>
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
<%@include file= "../../../view/include/menu.jsp"%>


<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>账户管理</li>
            <li>旧版账户</li>
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
                            <h2>旧版账户</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="accountOldListForm">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                    <table id="borrow-rep-table12" class="table table-bordered mt15" style="text-align:center;">
                                        <col width="200" />
                                        <col />
                                        <thead>
                                        <tr>
                                            <th>客户名称</th>
                                            <th>账户编号</th>
                                            <th>账户类型</th>
                                            <th>业务类型</th>
                                            <th>账户余额</th>
                                            <th>冻结金额</th>
                                            <th>是否创建第三方账户</th>
                                            <th>创建时间</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <#list page.list as t>
                                                <tr>
                                                    <td>${t.custName!}</td>
                                                    <td>${t.accountNo!}</td>
                                                    <td>
                                                        <#if t.accountType?c == '1'>
                                                                                                                                                                           客户账户
                                                        <#elseif t.accountType?c == '2'>
                                                         A0
                                                        <#elseif t.accountType?c == '3'>
                                                         AX
                                                        </#if>
                                                    </td>
                                                    <td>
                                                        <#if t.busiType?c == '1'>
                                                                                                                                                                            借款用户
                                                        <#elseif t.busiType?c == '2'>
                                                                                                                                                                          借款共借人
                                                        <#elseif t.busiType?c == '3'>
                                                                                                                                                                        线下出借用户
                                                        <#elseif t.busiType?c == '4'>
                                                                                                                                                                          线上注册用户
                                                        <#elseif t.busiType?c == '6'>
                                                                                                                                                                              抵押人
                                                        <#elseif t.busiType?c == '7'>
                                                                                                                                                                               抵押权人
                                                        <#elseif t.busiType?c == '8'>
                                                                                                                                                                                赎回内用用户
                                                        </#if>
                                                    </td>
                                                    <td>${t.amount}</td>
                                                    <td>${t.freezeAmount}</td>
                                                    <td>
                                                        <#if t.hasThirdAccount?c == '1'>
                                                                                                                                                                         未创建
                                                        <#elseif t.hasThirdAccount?c == '2'>
                                                                                                                                                                         已创建
                                                        </#if>
                                                    </td>
                                                    <td>${(t.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                                                    <td></td>
                                                </tr>
                                            </#list>
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
<%@include file= "../../../view/include/common_footer_css_js.jsp"%>
</div>


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#accountOldListForm"));
    });

</script>

<%@include file="../../../view/include/foot.jsp"%>
</body>

</html>