<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资金清结算系统--标的放款明细文件--冠群驰骋投资管理(北京)有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
    
   <%@include file="../../../../view/include/common_css_js.jsp"%>
    <style>
        .table-nobg-btn{
            font:15/29px;
            height: 31px;
            margin: 7px 7px 7px 0;
            padding: 0 22px;
        }
        .dt-wrapper {
            overflow: auto;
        }
    </style>
</head>
<body>
<%@include file="../../../../view/include/menu.jsp"%>
<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
           <li>入账管理</li>
            <li>标的放款明细文件</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
 				<!-- NEW WIDGET START -->
                      <div class="jarviswidget" id="wid-id-299"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <div>
                                <form class="smart-form" action="${contextPath}/fstp/bidLoanDetails"  method="post" id="bidLoanForm">
                                    <div class="jarviswidget-editbox">
                                    </div>
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
                                                   <tr>
                                                      <td class="tr" nowrap="nowrap">投资人姓名:</td>
                                                      <td nowrap="nowrap">
                                                          <label class="input"  style="width:250px" >
                                                            <input type="text" name="custName" value="${map.custName}">
                                                          </label>
                                                      </td>
                                                      <td class="tr" nowrap="nowrap">投资人证件号码:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:250px" >
                                                              <input type="text" name="certNo" value="${map.certNo}">
                                                            </label>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer>
                                           <button class="btn btn-primary" onclick="javascript:void(0);">查&nbsp;&nbsp;&nbsp;询</button>
                                        </footer>
                                    </div>
                                    <!-- end widget content -->
    							</form>
                    		</div>
                		</div>
                
                    <!-- NEW WIDGET START -->
                    <!-- 	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-058"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>标的放款明细文件</h2>
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
                                     <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:1950px;">
                                    	<col width="150" />
                                    	<col width="150" />
                                    	<col width="150" />
                                    	<col width="150" />
                                    	<col width="150" />
                                    	<col width="150" />
                                    	<col width="150" />
                                    	<col width="150" />
                                    	<col width="150" />
                                    	<col width="150" />
                                    	<col width="150" />
                                    	<col width="150" />
                                    	<col width="150" />
                                    	<thead>
                                    	<tr>
	                                    	<td>总笔数</td>
	                                    	<td>标的号（项目ID）</td>
	                                    	<td>交易日期</td>
	                                    	<td>交易类型</td>
	                                    	<td>投资人姓名</td>
	                                    	<td>投资人证件类型</td>
	                                    	<td>投资人证件号码</td>
	                                    	<td>支付机构借款人ID</td>
	                                    	<td>借款人姓名</td>
	                                    	<td>借款人身份证号</td>
	                                    	<td>该笔放款金额</td>
	                                    	<td>该笔还款本金</td>
	                                    	<td>该笔还款利息</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                         <c:forEach items="${page.list}" var="t">
                                         <tr>
	                                    	<td>${t.totalNumber}</td>
	                                    	<td>${t.targetId}</td>
	                                    	<td>${t.tradeTime}</td>
	                                    	<td>${t.tradeType}</td>
	                                    	<td>${t.custName}</td>
	                                    	<td>${t.certType}</td>
	                                    	<td>${t.certNo}</td>
	                                    	<td>${t.loanId}</td>
	                                    	<td>${t.loanName}</td>
	                                    	<td>${t.loanCertNo}</td>
	                                    	<td>${t.payAmount}</td>
	                                    	<td>${t.repaymentCapital}</td>
	                                    	<td>${t.repaymentInterest}</td>
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
<%@include file="../../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
 <script type="text/javascript" charset="utf-8">
	 $(document).ready(function () {
	     pageSetUp();
	     DT_page("borrow-rep-table12", true, '${page.JSON}', $("#bidLoanForm"));
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
	    function verify(){
   			$("#bidLoanForm").submit();
	    }
	     
</script>

<%@include file="../../../../view/include/foot.jsp"%>
</body>

</html>