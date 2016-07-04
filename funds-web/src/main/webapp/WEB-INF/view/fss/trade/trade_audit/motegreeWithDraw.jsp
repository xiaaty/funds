<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>借款流程--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

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
            <li>交易管理</li>
            <li>借款流程</li>
            <li>抵押标满标</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
    <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="jarviswidget" id="mo_WithDraw"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <!-- widget div-->
                            <div>

                                <form class="smart-form" id="borrowWithDraw" action="${contextPath}/loan/trade/${type}" method="post" >

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
                                                <col />
                                                <tbody>
                                                    <tr></tr>
                                                    <tr>
                                                        <td class="tr" nowrap="nowrap">交易流水号:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:210px" >
                                                                <input type="text" name="seqNo" value="${map.seqNo}">
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">商户号：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="mchnChild" value="${map.mchnChild}">
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">合同编号：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input" style="width:210px" >
                                                                <input type="text" name="contractNo" value="${map.contractNo}">
                                                            </label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                         <td class="tr">交易日期：</td>
                                            <td colspan="3">
                                                <section class="fl">
                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                        <input type="text" maxlength="10" readonly="readonly" name="creatTime" class="selectdate" placeholder="请选择时间" value="${map.creatTime}">
                                                    </label>
                                                </section>
                                                <span class="fl">&nbsp;至&nbsp;</span>
                                                <section class="fl">
                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                        <input type="text" maxlength="10" readonly="readonly"  name="modifyTime" class="selectdate" placeholder="请选择时间" value="${map.modifyTime}">
                                                    </label>
                                                </section>
                                            </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer>
                                            <!-- <button class="btn btn-default" onclick="window.history.back();" type="button">重&nbsp;&nbsp;&nbsp;置</button> -->
                                            <button class="btn btn-primary" onclick="javascript:void(0);">查&nbsp;&nbsp;&nbsp;询</button>
                                        </footer>
                                    </div>
                                    <!-- end widget content -->
                                </form>
                            </div>


                        </div>

                        </div>
    <div id="contents">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-darken" id="mwithDraw"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>抵押权人提现列表</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:1800px;">
                                    	<col width="50" />
                                    	<col width="200" />
                                    	<col width="200" />
                                    	<col width="200" />
                                    	<col width="150" />
                                    	<col width="200" />
                                    	<col width="200" />
                                    	<col width="200" />
                                    	<col width="200" />
                                    	<col width="200" />
                                        <thead>
                                        <tr>
                                            <td>序号</td>
                                            <td>抵押权人人资金平台账号</td>
                                            <td>交易流水号</td>
                                            <td>合同编号</td>
                                            <td>提现金额 </td>
                                            <td>交易状态 </td>
                                            <td>商户号</td>
                                            <td>交易日期 </td>
                                            <td>修改日期 </td>
											<td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t">
                                                <tr>
                                                    <td>${l.index+1}</td>
                                                    <td>${t.mortgageeAccNo}</td>
                                                    <td>${t.seqNo}</td>
                                                    <td>${t.contractNo}</td>
                                                    <td>${t.contractAmt}</td>
                                                    <td> <fss:dictView key="${t.status}" /></td>
                                                    <td>${t.mchnChild}</td>
                                                    <td><fss:fmtDate value="${t.createTime}"/></td>
                                                    <td><fss:fmtDate value="${t.modifyTime}"/></td>
                                                    <%-- <td><a href="${contextPath}/fss/loan/trade/borrowerwithdraw/${t.id}">借款人提现</a></td> --%>
                                                	<td>
                                                        <c:if test="${t.status=='10050009'&&t.withDrawStatus!='1'}">
                                                            <a href="${contextPath}/fss/loan/trade/${type}/${t.id}">抵押权人提现</a></td>
                                                         </c:if>
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
<%@include file= "../../../../view/include/common_footer_css_js.jsp"%>
</div>


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#borrowWithDraw"));
    });


</script>

<%@include file= "../../../../view/include/foot.jsp"%>
</body>

</html>