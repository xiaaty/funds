<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>交易记录--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <%@include file= "../../../../../view/include/common_css_js.jsp"%>
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
<%@include file= "../../../../../view/include/menu.jsp"%>


<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>交易管理</li>
            <li>交易审核</li>
            <li>代付审核</li>
            <li>借款人放款</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
    <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="jarviswidget" id="wid-id-71"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                           
                                <form class="smart-form" id="mortgageePayment" action="${contextPath}/fss/loan/trade/borrow/${t.id}" method="post" >
                              
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
                                                        <td class="tr" nowrap="nowrap">合同ID：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input" style="width:210px" >
                                                                <input type="text" name="contractId" value="${map.contractId}">
                                                            </label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                         <td class="tr">开户日期：</td>
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
    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-30"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>借款人放款表</h2>
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
                                    <table id="borrow-rep-table12" class="table table-bordered mt15" style="text-align:center;">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>抵押权人资金平台账号</th>
                                            <th>借款人资金平台账号</th>
                                            <th>交易流水号</th>
                                            <th>合同ID</th>
                                            <th>合同金额  </th>
                                            <th>放款金额   </th>
                                            <th>借款平台</th>
                                            <th>交易状态 </th>
                                            <th>交易结果</th>
                                            <th>交易类型</th>
                                            <th>大商户号</th>
                                            <th>子商户号 </th>
                                            <th>交易日期 </th>
                                            <th>修改日期 </th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t">
                                                <tr>
                                                    <td>${t.id}</td>
                                                    <td>${t.mortgageeAccNo}</td>
                                                    <td>${t.accNo}</td>
                                                    <td>${t.seqNo}</td>
                                                    <td>${t.contractId}</td>
                                                    <td>${t.contractAmt}</td>
                                                    <td>${t.payAmt}</td>
                                                    <td>
                                                    <c:if test="${t.loanPlatform == '10040001'}">北京</c:if>
										   			 <c:if test="${t.loanPlatform == '10040002'}">天津</c:if>
										   			 <c:if test="${t.loanPlatform == '10040003'}">上海</c:if>
                                                    
                                                    </td>
                                                    <td>${t.status}</td>
                                                    <td>
                                                    <c:if test="${t.isTrue == '0'}"><span style="color: green">成功</span></c:if>
										   			 <c:if test="${t.isTrue == '1'}"><span style="color: red">失败</span></c:if>
                                                    </td>
                                                    <td>
                                                    <c:if test="${t.tradeType == '11090001'}">抵押标放款</c:if>
                                                    <c:if test="${t.tradeType == '11090002'}">信用标放款</c:if>
                                                    </td>
                                                    <td>${t.mchnParent}</td>
                                                    <td>${t.mchnChild}</td>
                                                  <td> <fmt:formatDate value="${t.createTime}" pattern="yyyy-MM--dd HH:mm:ss"/></td>
                                                    <td> <fmt:formatDate value="${t.modifyTime}" pattern="yyyy-MM--dd HH:mm:ss"/></td>
                                                    <td>
                                                    <a href="${contextPath}/fss/loan/trade/feeList/${t.id}">查看</a>
                                                    &nbsp; &nbsp; &nbsp; 
                                                      <c:if test="${t.tradeType == '11090001' && t.status=='10050004'}">
														<a href="${contextPath}/fss/loan/trade/feeList">代扣</a>
														</c:if>
                                                    
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
<%@include file= "../../../../../view/include/common_footer_css_js.jsp"%>
</div>


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#mortgageePayment"));
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
    	var a=document.getElementsByName("startDate");
    	var b=document.getElementsByName("endDate");
    	if(b[0].value!=null&&b[0].value!=''){
    		
    		if(a[0].value>b[0].value){
    			alert("请检查您输入的日期");
    		}else{
    			$("#mortgageePayment").submit();
    		}
    	}else{
    		var d = new Date();
    		var str = d.getFullYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+(d.getDate()<10?"0":"")+d.getDate();
    		if(a[0].value>str){
    			alert("请检查您输入的日期");
    		}else{
    			$("#mortgageePayment").submit();
    		}
    	}
    }
</script>

<%@include file= "../../../../../view/include/foot.jsp"%>
</body>

</html>