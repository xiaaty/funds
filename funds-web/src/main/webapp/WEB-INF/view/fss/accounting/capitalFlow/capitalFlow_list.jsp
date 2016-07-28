<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>记账信息--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

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
            <li>记账信息</li>
            <li>客户资金流水总表</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
    <div id="contents">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="jarviswidget" id="fundFlowList"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                           
                                <form class="smart-form" id="fundFlowForm" action="${contextPath}/accounting/fundFlow/list" method="post" >
                              
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
                                                <col width="100" />
                                                <col />
                                                <tbody>
                                                    <tr></tr>
                                                     <tr>
                                                        <td class="tr" nowrap="nowrap">客户编号：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="custNo" value="${map.custNo}">
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">客户资金平台账号：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input" style="width:210px" >
                                                                <input type="text" name="accNo" value="${map.accNo}">
                                                            </label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                    <td class="tr" nowrap="nowrap">记账编号：</td>
                                                        <td nowrap="nowrap">
                                                             <label class="input" style="width:210px" >
                                                                <input type="text" name="accountNo" value="${map.accountNo}">
                                                            </label>
                                                        </td>
                                                         <td class="tr">交易日期：</td>
                                            <td colspan="3">
                                                <section class="fl">
                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                        <input type="text" maxlength="10" readonly="readonly" name="startTime" class="selectdate" placeholder="请选择时间" value="${map.startTime}">
                                                    </label>
                                                </section>
                                                <span class="fl">&nbsp;至&nbsp;</span>
                                                <section class="fl">
                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                        <input type="text" maxlength="10" readonly="readonly"  name="endTime" class="selectdate" placeholder="请选择时间" value="${map.endTime}">
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

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-darken" id="capitalFlow"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>资金流水</h2>
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
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:3350px;">
                                        <col width="50" />
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
                                        <col width="150"/>
                                        <col width="150"/>
                                        <col width="150"/>
                                        <col width="150"/>
                                        <col width="150"/>
                                        <col width="150"/>
                                        <col width="150"/>
                                        <col width="150"/>
                                        <col width="150"/>
                                        <col width="150"/>
                                        <col width="150"/>
                                        <thead>
                                        <tr>
                                            <td></td>
                                            <td>记账编号</td>
                                            <td>其他记账记账编号</td>
                                            <td>客户编号</td>
                                            <td>客户资金平台账号</td>
                                            <td>对方客户编号</td>
                                            <td>对方资金账号</td>
                                            <td>账务科目</td>
                                            <td>二级账务科目</td>
                                            <td>交易金额</td>
                                            <td>出借合同号</td>
                                            <td>借款编号</td>
                                            <td>债权转让—原出借编号</td>
                                            <td>债权转让—原客户编号 </td>
                                            <td>债权转让—原账户账号</td>
                                            <td>与第三方交易订单号</td>
                                            <td>交易日期 </td>
                                            <td>交易时间</td>
                                            <td>清算日期 </td>
                                            <td>交易渠道 </td>
                                            <td>商户号 </td>
                                            <td>创建日期 </td>
                                            <td>修改日期</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t" varStatus="l">
                                                <tr>
                                                    <td>${l.index+1}</td>
                                                    <td>${t.accountNo}</td>
                                                    <td>${t.oriAccountNo}</td>
                                                    <td>${t.custNo}</td>
                                                    <td>${t.accNo}</td>
                                                    <td>${t.toCustNo}</td>
                                                    <td>${t.toAccNo}</td>
                                                    <td>${t.fundsType}</td>
                                                    <td>${t.secendFundsType}</td>
                                                    <td><fss:money money="${t.amt}"/></td>
                                                    <td>${t.lendContractNo}</td>
                                                    <td>${t.loadContactNo}</td>
                                                    <td>${t.oriLendContractNo}</td>
                                                    <td>${t.oriCustNo}</td>
                                                    <td>${t.oriAccNo}</td>
                                                    <td>${t.orderNo}</td>
                                                    <td>${t.tradeDate}</td>
                                                    <td>${t.tradeTime}</td>
                                                    <td>${t.settleDate}</td>
                                                    <td>${t.payChannel}</td>
                                                    <td>${t.mchnChild}</td>
                                                    <td> <fss:fmtDate value="${t.createTime}"/></td>
                                                    <td> <fss:fmtDate value="${t.modifyTime}"/></td>
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
<%@include file= "../../../../view/include/common_footer_css_js.jsp"%>
</div>


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#fundFlowForm"));
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
    	var a=document.getElementsByName("startTime");
    	var b=document.getElementsByName("endTime");
    	if(b[0].value!=null&&b[0].value!=''){
    		
    		if(a[0].value>b[0].value){
    			JAlert("请检查您输入的日期","提示消息");
    		}else{
    			$("#fundFlowForm").submit();
    		}
    	}else{
    		var d = new Date();
    		var str = d.getFullYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+(d.getDate()<10?"0":"")+d.getDate();
    		if(a[0].value>str){
    			JAlert("请检查您输入的日期","提示消息");
    		}else{
    			$("#fundFlowForm").submit();
    		}
    	}
    }
   
</script>

<%@include file= "../../../../view/include/foot.jsp"%>
</body>

</html>