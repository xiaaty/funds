<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资金清结算系统--标的财务汇总文件--冠群驰骋投资管理(北京)有限公司</title>
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
            <li>标的财务汇总文件</li>
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
                                <form class="smart-form" action="${contextPath}/fstp/bidFinanceTotals"  method="post" id="financeForm">
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
                                                      <td class="tr" nowrap="nowrap">客户编号:</td>
                                                      <td nowrap="nowrap">
                                                          <label class="input"  style="width:250px" >
                                                            <input type="text" name="custNo" value="${map.custNo}">
                                                          </label>
                                                      </td>
                                                      <td class="tr" nowrap="nowrap">客户姓名:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:250px" >
                                                              <input type="text" name="custName" value="${map.custName}">
                                                            </label>
                                                        </td>
                                                      <td class="tr" nowrap="nowrap">客户身份证号:</td>
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
                            <h2>标的财务汇总文件</h2>
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
                                     <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:3450px;">
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
	                                    	<td>支付机构标的ID</td>
	                                    	<td>支付机构平台ID</td>
	                                    	<td>放款客户号</td>
	                                    	<td>客户姓名</td>
	                                    	<td>客户身份证号</td>
	                                    	<td>借款人证件类型</td>
	                                    	<td>标的状态</td>
	                                    	<td>发标日期</td>
	                                    	<td>实际满标日期</td>
	                                    	<td>应还款总本金</td>
	                                    	<td>应还款总利息</td>
	                                    	<td>最后还款日期</td>
	                                    	<td>实际结清日期</td>
	                                    	<td>至结清日累计已还款本金</td>
	                                    	<td>至结清日累计已还款利息</td>
	                                    	<td>本日还款本金</td>
	                                    	<td>本日还款利息</td>
	                                    	<td>截止当日累计还款本金</td>
	                                    	<td>截止当日累计还款利息</td>
	                                    	<td>已垫资总金额</td>
	                                    	<td>剩余未偿垫资</td>
	                                    	<td>放款金额</td>
	                                    	<td>截止当日累计放款</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                         <c:forEach items="${page.list}" var="t">
                                         <tr>
	                                    	<td>${t.orgTargetId}</td>
	                                    	<td>${t.orgTerraceId}</td>
	                                    	<td>${t.custNo}</td>
	                                    	<td>${t.custName}</td>
	                                    	<td>${t.certNo}</td>
	                                    	<td>
	                                    		<c:if test="${t.certType==00}">身份证</c:if>
		                                    	<c:if test="${t.certType==01}">护照</c:if>
		                                    	<c:if test="${t.certType==02}">军官证</c:if>
		                                    	<c:if test="${t.certType==07}">其他</c:if>
	                                    	</td>
	                                    	<td>
	                                    		<c:if test="${t.targetState==1}">筹款中</c:if>
		                                    	<c:if test="${t.targetState==2}">还款中</c:if>
		                                    	<c:if test="${t.targetState==3}">已到期未结清</c:if>
		                                    	<c:if test="${t.targetState==4}">已结清</c:if>
	                                    	</td>
	                                    	<td><fmt:formatDate value="${t.tenderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            <td><fmt:formatDate value="${t.fullScaleTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                                    	<td>${t.tReCaptical}</td>
	                                    	<td>${t.tReInterest}</td>
	                                    	<td><fmt:formatDate value="${t.lRepaymentTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            <td><fmt:formatDate value="${t.aSquareTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                                    	<td>${t.aReCaptical}</td>
	                                    	<td>${t.aReInterest}</td>
	                                    	<td>${t.todayReCaptical}</td>
	                                    	<td>${t.todayReInterest}</td>
	                                    	<td>${t.eReCaptical}</td>
	                                    	<td>${t.eReInterest}</td>
	                                    	<td>${t.paidSum}</td>
	                                    	<td>${t.creditSum}</td>
	                                    	<td>${t.debtSum}</td>
	                                    	<td>${t.tCreditSum}</td>
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
	     DT_page("borrow-rep-table12", true, '${page.JSON}', $("#financeForm"));
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
   			$("#financeForm").submit();
	    }
	     
</script>

<%@include file="../../../../view/include/foot.jsp"%>
</body>

</html>