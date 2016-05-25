<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资金清结算系统--P2P商户交易--冠群驰骋投资管理(北京)有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            <li>P2P商户交易</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
 				<!-- NEW WIDGET START -->
                      <div class="jarviswidget" id="wid-id-024"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <div>
                                <form class="smart-form" action="${contextPath}/fstp/p2pBusinessTrade"  method="post" id="mchnForm">
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
                                                      <td class="tr" nowrap="nowrap">商户号:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:250px" >
                                                              <input type="text" name="mchn" value="${map.mchn}">
                                                            </label>
                                                        </td>
                                                        
                                                        <td class="tr" nowrap="nowrap">项目编号:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:250px" >
                                                              <input type="text" name="itemNo" value="${map.itemNo}">
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
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-711"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>P2P商户交易列表</h2>
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
                                     <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2900px;">
                                    	<col width="100" />
                                    	<col width="200" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="200" />
                                    	<col width="200" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="200" />
                                    	<col width="200" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="200" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<thead>
                                    	<tr>
                                    		<td>商户号</td>
	                                    	<td>第三方支付公司ID</td>
	                                    	<td>交易日期</td>
	                                    	<td>金账户交易类型</td>
	                                    	<td>项目编号</td>
	                                    	<td>合同编号</td>
	                                    	<td>出账人富友用户名</td>
	                                    	<td>出账人平台用户名</td>
	                                    	<td>金额</td>
	                                    	<td>手续费 </td>
	                                    	<td>该笔还款本金</td>
	                                    	<td>该笔还款利息</td>
	                                    	<td>入账人富友用户名 </td>
	                                    	<td>入账人平台用户名</td>
	                                    	<td>借款人姓名</td>
	                                    	<td>借款人证件类型</td>
	                                    	<td>借款人证件号码 </td>
	                                    	<td>投资人用户名</td>
	                                    	<td>投资人富友登陆用户名</td>
	                                    	<td>投资人姓名</td>
	                                    	<td>投资人证件类型</td>
	                                    	<td>投资人证件号码</td>
	                                    	<td>业务类型</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                         <c:forEach items="${page.list}" var="t">
                                         <tr>
                                        	<td>${t.mchn}</td>
	                                    	<td>${t.thirdPartyPaymentId}</td>
	                                    	<td>${t.tradeDate}</td>
	                                    	<td>${t.tradeType}</td>
	                                    	<td>${t.itemNo}</td>
	                                    	<td>${t.contractNo}</td>
	                                    	<td>${t.outFuiouUsername}</td>
	                                    	<td>${t.outPlatformUsername}</td>
	                                    	<td>${t.amt}</td>
	                                    	<td>${t.charge}</td>
	                                    	<td>${t.thisRepaymentPrincipal}</td>
	                                    	<td>${t.thisRepaymentInterest}</td>
	                                    	<td>${t.comeFuiouUsername}</td>
	                                    	<td>${t.comePlatformUsername}</td>
	                                    	<td>${t.loanUsername}</td>
	                                    	<td>
	                                    		<c:if test="${t.loanCertType==0}">居民身份证</c:if>
		                                    	<c:if test="${t.loanCertType==1}">护照</c:if>
		                                    	<c:if test="${t.loanCertType==2}">军官证</c:if>
		                                    	<c:if test="${t.loanCertType==7}">其他</c:if>
	                                    	</td>
	                                    	<td>${t.loanCertNo}</td>
	                                    	<td>${t.lendUsername}</td>
	                                    	<td>${t.lendFuiouUsername}</td>
	                                    	<td>${t.lendName}</td>
	                                    	<td>
	                                    		<c:if test="${t.lendCertType==0}">居民身份证</c:if>
		                                    	<c:if test="${t.lendCertType==1}">护照</c:if>
		                                    	<c:if test="${t.lendCertType==2}">军官证</c:if>
		                                    	<c:if test="${t.lendCertType==7}">其他</c:if>
	                                    	</td>
	                                    	<td>${t.lendCertNo}</td>
	                                    	<td>${t.busiType}</td>
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
	     DT_page("borrow-rep-table12", true, '${page.JSON}', $("#mchnForm"));
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
	    	$("#mchnForm").submit();
	    }
	     
</script>

<%@include file="../../../../view/include/foot.jsp"%>
</body>

</html>