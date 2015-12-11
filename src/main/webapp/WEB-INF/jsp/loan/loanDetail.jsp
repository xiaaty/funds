<%--
  Created by IntelliJ IDEA.
  User: 郭福
  Date: 2015/01/06
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>内审系统--借款管理--查看详细</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">


    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                <li>借款管理</li>
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

                            <div class="jarviswidget" id="wid-id-10" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
                                <header>
                                    <h2><i class="fa "></i>客户信息<font class="pl10 f12 color07"></font></h2>
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
                                                            <td>${loanDetailBean.customerInfoBean.customerName}</td>
                                                            <td class="tr">证件类型：</td>
                                                            <c:choose>
	                                                            <c:when test= "${loanDetailBean.customerInfoBean.certType =='1'}">
	                                                            	<td>身份证</td>
	                                                            </c:when>
	                                                            
	                                                             <c:when test= "${loanDetailBean.customerInfoBean.certType =='2'}">
	                                                            	<td>护照</td>
	                                                            </c:when>
	                                                            <c:otherwise>
	                                                            	<td>${loanDetailBean.customerInfoBean.certType}</td>
	                                                            </c:otherwise>
                                                            </c:choose>
                                                            <td class="tr">证件号码：</td>
                                                             <td>${loanDetailBean.customerInfoBean.certNo}</td>
                                                            <td colspan="2"></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="jarviswidget" id="wid-id-11" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
                                <header>
                                    <h2>合同信息</h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <div class="smart-form">
                                        <!-- widget content -->
                                        <div class="widget-body no-padding">
                                            <div class="mt10 mb10">
                                                <table class="table">
                                                   <col width="120" />
                                                   <col />
                                                   <col width="120" />
                                                   <col />
                                                   <col width="120" />
                                                   <col />
                                                   <col width="120" />
                                                   <col />
                                                    <tbody>
                                                        <tr>
                                                            <td class="tr">合同编号：</td>
                                                             <td>${loanDetailBean.contractInfoBean.contractNo}</td>
                                                            <td class="tr">借款类型：</td>
                                                            <c:choose>
	                                                            <c:when test= "${loanDetailBean.contractInfoBean.loanType =='1'}">
	                                                            	<td>信用贷款</td>
	                                                            </c:when>
	                                                            
	                                                             <c:when test= "${loanDetailBean.contractInfoBean.loanType =='2'}">
	                                                            	<td>抵押贷款</td>
	                                                            </c:when>
	                                                            <c:when test= "${loanDetailBean.contractInfoBean.loanType =='3'}">
	                                                            	<td>质押贷款</td>
	                                                            </c:when>
	                                                            <c:when test= "${loanDetailBean.contractInfoBean.loanType =='4'}">
	                                                            	<td>混合贷款</td>
	                                                            </c:when>
	                                                            <c:otherwise>
	                                                            	<td>${loanDetailBean.contractInfoBean.loanType}</td>
	                                                            </c:otherwise>
                                                            </c:choose>
                                                            
                                                            <td class="tr">借款方式：</td>
                                                            <c:choose>
	                                                            <c:when test= "${loanDetailBean.contractInfoBean.loanMode =='1'}">
	                                                            	<td>新客户</td>
	                                                            </c:when>
	                                                            
	                                                             <c:when test= "${loanDetailBean.contractInfoBean.loanMode =='2'}">
	                                                            	<td>循环贷</td>
	                                                            </c:when>
	                                                            <c:otherwise>
	                                                            	<td>${loanDetailBean.contractInfoBean.loanMode}</td>
	                                                            </c:otherwise>
                                                            </c:choose>
                                                            <td class="tr">合同金额：</td>
                                                            <td>${loanDetailBean.contractInfoBean.contractAmount}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="tr">贷款年利率：</td>
                                                            <td>${loanDetailBean.contractInfoBean.loanRate}</td>
                                                            <td class="tr">期&nbsp;&nbsp;&nbsp;&nbsp;限：</td>
                                                            <td>${loanDetailBean.contractInfoBean.limitTimes}</td>
                                                            <td class="tr">还款方式：</td>
                                                            <c:choose>
	                                                            <c:when test= "${loanDetailBean.contractInfoBean.backMoneyType =='1'}">
	                                                            	<td>等额等息</td>
	                                                            </c:when>
	                                                            
	                                                             <c:when test= "${loanDetailBean.contractInfoBean.backMoneyType =='2'}">
	                                                            	<td>等额本息</td>
	                                                            </c:when>
	                                                            <c:when test= "${loanDetailBean.contractInfoBean.backMoneyType =='3'}">
	                                                            	<td>先息后本</td>
	                                                            </c:when>
	                                                            <c:when test= "${loanDetailBean.contractInfoBean.backMoneyType =='4'}">
	                                                            	<td>自定义还款</td>
	                                                            </c:when>
	                                                            <c:otherwise>
	                                                            	<td>${loanDetailBean.contractInfoBean.backMoneyType}</td>
	                                                            </c:otherwise>
                                                            </c:choose>
                                                            <td class="tr">是否代扣：</td>
                                                             <c:if test= "${loanDetailBean.contractInfoBean.isWithholding =='1'}">
                                                             	<td>是</td>
                                                             </c:if>
                                                             <c:if test= "${loanDetailBean.contractInfoBean.isWithholding =='2'}">
                                                             	<td>否</td>
                                                             </c:if>
                                                        </tr>
                                                        <tr>
                                                            <td class="tr">合同签署日：</td>
                                                            <td>${loanDetailBean.contractInfoBean.contractSignDate}</td>
                                                            <td class="tr">应收总服务费：</td>
                                                            <td>${loanDetailBean.contractInfoBean.totalServiceFee}</td>
                                                            <td class="tr">应收总利息：</td>
                                                            <td>${loanDetailBean.contractInfoBean.totalAccrual}</td>
                                                            <td class="tr">信访费：</td>
                                                            <td>${loanDetailBean.contractInfoBean.petitionFee}</td>
                                                        </tr>

                                                        <tr>
                                                            <td class="tr">保证金：</td>
                                                            <td>${loanDetailBean.contractInfoBean.assureMoney}</td>
                                                            <td class="tr">已扣服务费：</td>
                                                            <td>${loanDetailBean.contractInfoBean.deductServiceFee}</td>
                                                            <td class="tr">已扣利息：</td>
                                                            <td>${loanDetailBean.contractInfoBean.deductAccrual}</td>
                                                            <td class="tr">实际放款金额：</td>
                                                            <td>${loanDetailBean.contractInfoBean.actualLoanAmount}</td>
                                                        </tr>
                                                        <tr>
                                                            <td class="tr">剩余服务费：</td>
                                                            <td>${loanDetailBean.contractInfoBean.surplusServiceFee}</td>
                                                            <td class="tr">剩余利息：</td>
                                                            <td>${loanDetailBean.contractInfoBean.surplusAccrual}</td>
                                                            <td class="tr">实际发款时间：</td>
                                                            <td>${loanDetailBean.contractInfoBean.actualLoanDate}</td>
                                                            <td colspan="2"></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="tr">借款原因：</td>
                                                            <td colspan="7">${loanDetailBean.contractInfoBean.loanReason}</td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <!-- end widget content -->
                                    </div>
                                </div>
                            </div>

                            <div class="jarviswidget" id="wid-id-13" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-collapsed="false">
                                <header>
                                    <h2>还款计划</h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <div class="smart-form">
                                        <!-- widget content -->
                                        <div class="widget-body no-padding">
                                            <div class="mt10 mb10" style="width: 98%;margin-left: 1%;">
                                                <table id="" class="table table-bordered table-striped tc " style="min-width:1600px">
                                                    <thead>
                                                    <tr class="b">
                                                        <th colspan="6"><h2 class="b">应还款计划</h2></th>
                                                        <th colspan="5"><h2 class="b">已还款</h2></th>
                                                    </tr>
                                                    <tr class="b">
                                                        <td>期数</td>
                                                        <td>应还款日期</td>
                                                        <td>本金</td>
                                                        <td>利息</td>
                                                        <td>服务费</td>
                                                        <td>小计</td>
                                                        <td>还款日期</td>
                                                        <td>本金</td>
                                                        <td>利息</td>
                                                        <td>其他费用</td>
                                                        <td>小计</td>
                                                    </tr>
                                                    </thead>
                                                    <tbody class="f12"><tr  class="input_data">
                                                        <c:forEach var="item" items="${repaymentPlanBeans}">
                                                        <c:if test="${item.actualListSize == 0}">
                                                    <tr>
                                                        <td>${item.repaymentTimes}</td>
                                                        <td>${item.repaymendate}</td>
                                                        <td><fmt:formatNumber value="${item.principalByBig}" pattern="###,###,##0.00"/></td>
                                                        <td><fmt:formatNumber value="${item.accrualByBig}" pattern="###,###,##0.00"/></td>
                                                        <td><fmt:formatNumber value="${item.serviceFeeByBig}" pattern="###,###,##0.00"/></td>
                                                        <td><fmt:formatNumber value="${item.subtotalByBig}" pattern="###,###,##0.00"/></td>
                                                        <td colspan="5">本期未还款</td>
                                                    </tr>
                                                    </c:if>
                                                    <c:if test="${item.actualListSize == 1}">
                                                        <c:forEach var="rep" items="${item.actualList}" varStatus="lineNum">
                                                            <tr>
                                                                <c:if test="${lineNum.index == 0}">
                                                                    <td rowspan="${item.actualListSize}">${item.repaymentTimes}</td>
                                                                    <td rowspan="${item.actualListSize}">${item.repaymendate}</td>
                                                                    <td rowspan="${item.actualListSize}"><fmt:formatNumber value="${item.principalByBig}" pattern="###,###,##0.00"/></td>
                                                                    <td rowspan="${item.actualListSize}"><fmt:formatNumber value="${item.accrualByBig}" pattern="###,###,##0.00"/></td>
                                                                    <td rowspan="${item.actualListSize}"><fmt:formatNumber value="${item.serviceFeeByBig}" pattern="###,###,##0.00"/></td>
                                                                    <td rowspan="${item.actualListSize}"><fmt:formatNumber value="${item.subtotalByBig}" pattern="###,###,##0.00"/></td>
                                                                </c:if>
                                                                <td>${rep.repaymentDate}</td>
                                                                <td><fmt:formatNumber value="${rep.capital/100}" pattern="###,###,##0.00"/></td>
                                                                <td><fmt:formatNumber value="${rep.interest/100}" pattern="###,###,##0.00"/></td>
                                                                <td><fmt:formatNumber value="${rep.otherFee/100}" pattern="###,###,##0.00"/></td>
                                                                <td><fmt:formatNumber value="${rep.total/100}" pattern="###,###,##0.00"/></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </c:if>
                                                    </c:forEach>
                                                    </tbody>

                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                            </div>
                            <div class="jarviswidget" id="wid-id-14" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-collapsed="false">
                                <header>
                                    <h2>资金流水</h2>
                                </header>
                                <!-- widget div-->
                                <div>
                                    <div class="widget-body m10">
                                        <table class="table table-bordered table-striped tc" id ="borrow-rep-table3">
                                            <thead>
                                                <tr class="b">
                                                    <td>合同编号</td>
                                                    <td>类型</td>
                                                    <td>登账日期</td>
                                                    <td>入账日期</td>
                                                    <td>金额</td>
                                                </tr>
                                            </thead>
                                            <tbody class="f12">
                                                <c:forEach var="amountWaterBean" items="${loanDetailBean.amountWaterBeanList}">
                                                <tr class="table_input">
                                                    <td>${amountWaterBean.contractNo}</td>
                                                    <td>${amountWaterBean.loanType}</td>
                                                    <td>${amountWaterBean.createDate}</td>
                                                    <td>${amountWaterBean.incomeDate}</td>
                                                    <td>${amountWaterBean.amount}</td>
                                                </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>   
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
		DT_page("borrow-rep-table3",false);
	});

</script>

</body>
</html>
