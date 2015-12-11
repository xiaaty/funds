<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>内审系统--出借管理-查看详细</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <%@include file="/WEB-INF/jsp/inc/common_css_js.inc" %>

</head>

<body>
<%@include file="/WEB-INF/jsp/inc/menu.inc" %>
    <div id="main" role="main">
        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
                <li>出借管理</li>
                <li>查看详细</li>
            </ol>
            <!-- end breadcrumb -->
        </div>
        <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <form>
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">

                            <div class="jarviswidget" id="wid-id-230" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
                                <header>
                                    <h2><i class="fa"></i>客户信息<font class="pl10 f12 color07"></font></h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <div class="smart-form">
                                        <!-- widget content -->
                                        <div class="widget-body no-padding">
                                            <div class="mt10 mb10">
                                                <table class="table">
                                                   <col width="90" />
                                                   <col width="200" />
                                                   <col width="90" />
                                                   <col width="200" />
                                                   <col width="90" />
                                                   <col width="200" />
                                                   <col />
                                                   <col />
                                                    <tbody>
                                                        <tr>
                                                            <td class="tr">客户姓名：</td>
                                                            <td>${lend.customerInfoBean.customerName }</td>
                                                            <td class="tr">证件类型：</td>
                                                            <c:choose>
	                                                            <c:when test= "${lend.customerInfoBean.certType =='1'}">
	                                                            	<td>身份证</td>
	                                                            </c:when>
	                                                            
	                                                             <c:when test= "${lend.customerInfoBean.certType =='2'}">
	                                                            	<td>护照</td>
	                                                            </c:when>
	                                                            <c:otherwise>
	                                                            	<td>${lend.customerInfoBean.certType}</td>
	                                                            </c:otherwise>
                                                            </c:choose>
                                                            <td class="tr">证件号码：</td>
                                                            <td>${lend.customerInfoBean.certNo }</td>
                                                            <td colspan="2"></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="jarviswidget" id="wid-id-232" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
                                <header>
                                    <h2><i class="fa "></i>合同信息</h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <div class="smart-form">
                                        <!-- widget content -->
                                        <div class="widget-body no-padding">
                                           <div class="mt10 mb10">
                                                <table class="table">
                                                    <col width="90" />
                                                    <col width="200" />
                                                    <col width="110" />
                                                    <col width="200" />
                                                    <col width="90" />
                                                    <col width="200" />
                                                    <col width="90" />
                                                    <col />
                                                    <tbody>
                                                        <tr>
                                                            <td class="tr">出借编号：</td>
                                                            <td>${lend.lendAgreement.agreeNo }</td>
                                                            <td class="tr">预计出借日期：</td>
                                                            <td>${lend.lendAgreement.estimateTime }</td>
                                                            <td class="tr">出借金额：</td>
                                                            <td>${lend.lendAgreement.lendAmount}</td>
                                                            <td colspan="2"></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <!-- end widget content -->
                                    </div>
                                </div>
                            </div>
                            <div class="jarviswidget" id="wid-id-233" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-collapsed="false">
                                <header>
                                    <h2>银行流水</h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <div class="smart-form">
                                        <!-- widget content -->
                                        <div class="widget-body no-padding clearfix">
                                            <div class="fl ml20 mr100 mt20 mb20" >
                                                <h2 class="color01 mb10 b">扣款记录：</h2> 
                                                <table class="table table-bordered table-striped" style="width:400px" id ="borrow-rep-table1">
	                                                <thead>
	                                                    <tr>
	                                                        <th>出借编号</th>
	                                                        <th>代扣日期</th>
	                                                        <th>金额</th>
	                                                    </tr>
	                                                 <thead>
	                                                 <tbody class="f12">
	                                                	<c:forEach var="amountWaterBean" items="${lend.backMoneyRecordList}">
	                                                    <tr>
	                                                        <td>${amountWaterBean.contractNo}</td>
	                                                        <td>${amountWaterBean.incomeDate}</td>
                                                            <td>${amountWaterBean.amount}</td>
	                                                    </tr>
	                                                    </c:forEach>
                                                     </tbody>
                                                </table>
                                            </div>
                                            <div class="fl ml50 mt20 mb10" >
                                                <h2 class="color01 mb10 b ">赎回记录：</h2> 
                                                <table class="table table-bordered table-striped"  style="width:400px" id ="borrow-rep-table2">
	                                                <thead>
	                                                    <tr>
	                                                        <th>出借编号</th>
	                                                        <th>赎回日期</th>
	                                                        <th>金额</th>
	                                                    </tr>
	                                                 <thead>
	                                                 <tbody class="f12">
	                                                	<c:forEach var="amountWaterBean" items="${lend.backMoneyRecordList}">
	                                                    <tr>
	                                                        <td>${amountWaterBean.contractNo}</td>
	                                                        <td>${amountWaterBean.incomeDate}</td>
                                                            <td>${amountWaterBean.amount}</td>
	                                                    </tr>
	                                                    </c:forEach>
                                                     </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </article>
                    </form>
                </div>
            </section>
        </div>
    </div>

<%@include file="/WEB-INF/jsp/inc/common_footer_css_js.inc" %>
<script type="text/javascript"> 

	$(document).ready(function () {
		pageSetUp();
		DT_page("borrow-rep-table1",false)
		DT_page("borrow-rep-table2",false)
	});

</script>
	
</body>

</html>
