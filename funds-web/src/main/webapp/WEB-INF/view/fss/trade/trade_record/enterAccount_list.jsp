<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <title>交易记录--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

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
            <li>交易记录</li>
            <li>入账记录</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
    <div >
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="jarviswidget" id="enList"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                           
                                <form class="smart-form" id="mortgageePayment" action="${contextPath}/loan/enterAccount/list" method="post" >
                              
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
                                                        <td class="tr" nowrap="nowrap">商户号：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="mchnChild" value="${map.mchnChild}">
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">交易类型：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input" style="width:210px" >
                                                                <input type="text" name="tradeType" value="${map.tradeType}">
                                                            </label>
                                                        </td>


                                                        <td class="tr">执行结果：</td>
                                                        <td>
                                                            <select id = "resultState" name = "resultState" style="width:200px;height: 30px;" >
                                                                <option value="">所有</option>
                                                                <option  <c:if test="${map.resultState==10080001}"> selected="selected" </c:if> value="10080001">新增</option>
                                                                <option  <c:if test="${map.resultState==10080002}"> selected="selected" </c:if> value="10080002" >成功</option>
                                                                <option  <c:if test="${map.resultState==10080003}"> selected="selected" </c:if> value="10080003" >部分成功</option>
                                                                <option  <c:if test="${map.resultState==10080010}"> selected="selected" </c:if> value="10080010" >失败</option>
                                                            </select>
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
    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-30"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>入账记录表</h2>
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
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2300px;">
                                    	<col width="300" />
                                    	<col width="300" />
                                    	<col width="300" />
                                    	<col width="300" />
                                    	<col width="400" />
                                    	<col width="400" />
                                    	<col width="300" />
                                    	<col width="300" />
                                        <thead>
                                        <tr>
                                            <td>交易流水号</td>
<!--                                             <td>交易类型</td> -->
                                            <td>商户号</td>
<!--                                             <td>执行状态</td> -->
                                            <td>交易情况</td>
                                            <td>执行结果</td>
                                            <td>创建时间</td>
                                            <td>修改时间</td>
                                            <td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t">
                                                <tr>
                                                    <td>${t.seqNo}</td>
<!--                                                     <td> -->
<%--                                                    <fss:dictOrder var="order" dictOrder="tradeType"> --%>
<%--                                                     <c:if test="${t.tradeType == order.key}">${order.value}</c:if> --%>
<%--                                                    	 </fss:dictOrder></td> --%>
                                                    <td>${t.mchnChild}</td>
<%--                                                     <td>${t.state} --%>
<%--                                                     <fss:dictOrder var="order" dictOrder="resultStatus"> --%>
<%--                                                     <c:if test="${t.resultState == order.key}">${order.value}</c:if> --%>
<%--                                                    	 </fss:dictOrder></td> --%>
                                                    <td>该批交易共${t.tradeCount}批，${t.successCount}批成功，${t.filedCount}批失败</td>
                                                    <td>
                                                    <fss:dictOrder var="order" dictOrder="resultStatus">
                                                    <c:if test="${t.resultState == order.key}">${order.value}</c:if>
                                                   	 </fss:dictOrder></td>
                                                      <td> <fss:fmtDate value="${t.createTime}"/>
<%--                                                       <fmt:formatDate value="${t.createTime}" pattern="yyyy-MM--dd HH:mm:ss"/> --%>
                                                      </td>
                                                    <td> <fss:fmtDate value="${t.motifyTime}"/>
<%--                                                     <fmt:formatDate value="${t.modifyTime}" pattern="yyyy-MM--dd HH:mm:ss"/> --%>
                                                    </td>
                                                    <td>					
                                                    <a href="${contextPath}/loan/enterAccount/${t.tradeType}/${t.id}/detail">查看详情</a>
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
<%@include file= "../../../../view/include/common_footer_css_js.jsp"%>
</div>


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#mortgageePayment"));
    });
</script>

<%@include file= "../../../../view/include/foot.jsp"%>
</body>

</html>