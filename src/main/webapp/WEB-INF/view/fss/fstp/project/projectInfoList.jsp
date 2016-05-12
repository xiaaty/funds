<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资金清结算系统--项目信息--冠群驰骋投资管理(北京)有限公司</title>
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
            <li>项目信息</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
 				<!-- NEW WIDGET START -->
                      <div class="jarviswidget" id="wid-id-269"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <div>
                                <form class="smart-form" action="${contextPath}/fstp/projectInfo"  method="post" id="projectForm">
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
	                                                      <td class="tr" nowrap="nowrap">平台流水号:</td>
	                                                        <td nowrap="nowrap">
	                                                            <label class="input"  style="width:250px" >
	                                                              <input type="text" name="seqNo" value="${map.seqNo}">
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
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-478"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>项目信息</h2>
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
                                     <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:3100px;">
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
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
                                    	<col width="100" />
                                    	<col width="200" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<thead>
                                    	<tr>
                                    		<td>商户号</td>
	                                    	<td>平台流水号</td>
	                                    	<td>项目编号</td>
	                                    	<td>借款类型</td>
	                                    	<td>借款标题</td>
	                                    	<td>推荐机构</td>
	                                    	<td>借款用途</td>
	                                    	<td>借款金额</td>
	                                    	<td>预期收益</td>
	                                    	<td>产品名称</td>
	                                    	<td>还款方式</td>
	                                    	<td>借款期限</td>
	                                    	<td>筹表起始日</td>
	                                    	<td>每份投标金额</td>
	                                    	<td>最低投标份数</td>
	                                    	<td>最多投标金额</td>
	                                    	<td>借款人平台用户名</td>
	                                    	<td>借款人金账户用户名</td>
	                                    	<td>借款人项目概述</td>
	                                    	<td>费用项</td>
	                                    	<td>筹集情况</td>
	                                    	<td>还款期数</td>
	                                    	<td>备用金额</td>
	                                    	<td>第三方支付公司ID</td>
	                                    	<td>发标年化利率</td>
	                                    	<td>借款人名称</td>
	                                    	<td>借款人证件类型</td>
	                                    	<td>借款人证件号码</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                         <c:forEach items="${page.list}" var="t">
                                         <tr>
                                        	<td>${t.mchn}</td>
	                                    	<td>${t.seqNo}</td>
	                                    	<td>${t.itemNo}</td>
	                                    	<td>${t.loanType}</td>
	                                    	<td>${t.loanTittle}</td>
	                                    	<td>${t.organization}</td>
	                                    	<td>${t.description}</td>
	                                    	<td>${t.loanAmt}</td>
	                                    	<td>${t.expectedReturn}</td>
	                                    	<td>${t.productName}</td>
	                                    	<td>${t.repaymentType}</td>
	                                    	<td>${t.loanTime}</td>
	                                    	<td>${t.startDate}</td>
	                                    	<td>${t.eachBidAmount}</td>
	                                    	<td>${t.minNum}</td>
	                                    	<td>${t.maxAmount}</td>
	                                    	<td>${t.accNo}</td>
	                                    	<td>${t.accGoldNo}</td>
	                                    	<td>${t.loanItemDescription}</td>
	                                    	<td>${t.feeType}</td>
	                                    	<td>${t.status}</td>
	                                    	<td>${t.period}</td>
	                                    	<td>${t.prepareAmount}</td>
	                                    	<td>${t.payChannel}</td>
	                                    	<td>${t.bidYearIrr}</td>
	                                    	<td>${t.custName}</td>
	                                    	<td>${t.certType}</td>
	                                    	<td>${t.certNo}</td>
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
	     DT_page("borrow-rep-table12", true, '${page.JSON}', $("#projectForm"));
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
	    	/* var a=document.getElementsByName("startTime");
	    	var b=document.getElementsByName("endTime");
	    	if(b[0].value!=null&&b[0].value!=''){
	    		
	    		if(a[0].value>b[0].value){
	    			JAlert("请检查您输入的日期","提示消息");
	    		}else{
	    			$("#projectForm").submit();
	    		}
	    	}else{
	    		var d = new Date();
	    		var str = d.getFullYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+(d.getDate()<10?"0":"")+d.getDate();
	    		if(a[0].value>str){
	    			JAlert("请检查您输入的日期","提示消息");
	    		}else{
	    			$("#projectForm").submit();
	    		}
	    	} */
	    	$("#projectForm").submit();
	    }
	     
</script>

<%@include file="../../../../view/include/foot.jsp"%>
</body>

</html>