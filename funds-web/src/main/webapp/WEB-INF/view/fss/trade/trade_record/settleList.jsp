<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>交易记录--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

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
          <li>交易管理</li>
            <li>交易记录</li>
            <li>入账记录</li>
            <li>费用列表</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-darken" id="settleList"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>收费列表</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="feeList">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                <div class="mb20" id="wid-id-713">
                                            <button class="btn btn-default table-nobg-btn" type="button" onclick="location.href='${contextPath}/loan/enterAccount/${type}/${parentId}/detail'" ><i class="fa fa-minus"></i>返回</button>
                                      </div>
                                   <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2300px;">
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="800" />
                                    	<col width="800" />
                                    	<col width="300" />
                                        <thead>
                                        <tr>
                                            <td>账务科目 </td>
                                            <td>清算金额  </td>
                                            <td>交易状态</td>
                                            <td>最终结果</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${settleList}" var="t">
                                                <tr>
                                                    <td><fss:dictView key="${t.accountType}"/></td>
                                                    <td>${t.settleAmt}</td>
                                                    <td><fss:dictView key="${t.tradeStatus}" /></td>
                                                    <td>${t.result=='98060001'?'成功':'失败'}</td>
<%--                                                     <td>${t.repCode}</td> --%>
<%--                                                     <td>${t.repMsg}</td> --%>
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
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#feeList"));
    });

</script>

<%@include file= "../../../../view/include/foot.jsp"%>
</body>

</html>