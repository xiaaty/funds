<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <title>系统管理--账户代扣--冠群驰骋投资管理(北京)有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
   <%@include file="../../../include/common_css_js.jsp"%>
  
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
    </style>
</head>
	
		
    <body>
    
<%@include file="../../../include/menu.jsp"%>

    <div id="main" role="main">

        <!-- RIBBON -->
        <div id="ribbon">
            <!-- breadcrumb -->
            <ol class="breadcrumb">
            <li>账户管理</li>
            <li>账户代扣</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                            <form  id="withHoldForm" action="#" method="post">
                   <%--     <input type="hidden" value="${dict.dictId}" name="dictId"  default="0"/> --%>
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">

                            <div class="jarviswidget" id="wid-id-711" data-widget-deletebutton="false" data-widget-editbutton="false">
                               <header>
                                <h2><i class="fa fa-edit pr10"></i>账户代扣<font class="pl10 f12 color07"></font></h2>
                                </header>
                                <div>
                       <div class="smart-form">

                                        <!-- widget content -->
                                        <div class="widget-body no-padding">
                                            <div class="mt10 mb10 ml30">
                                                <table class="table">
                                                    <col width="112" />
                                                    <col width="367" />
                                                    <col width="112" />
                                                    <col />
                                            <tbody>
                        <tr>
                           <td align="left"><span class="emphasis emphasis_txtx01 pr5">*</span>账户编号:</td>
                           <td>${acct.accountNo}</td>
                        </tr>
                        <tr>
                            <td align="left"><span class="emphasis emphasis_txtx01 pr5">*</span>客户编号:</td>
                            <td>${acct.custId}
                            <input name="cust_no" value="${acct.custId}" type="hidden">
                            </td>
                        </tr>
                        <tr>

                            <td align="left"><span class="emphasis emphasis_txtx01 pr5">*</span>账户类型:</td>
                            <td>
                                <c:if test="${acct.accountType == 1}">客户账户</c:if>
                                <c:if test="${acct.accountType == 2}">A0账户</c:if>
                                <c:if test="${acct.accountType == 3}">AX账户</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td align="left"><span class="emphasis emphasis_txtx01 pr5">*</span>账户余额:</td>
                            <td><fmt:formatNumber value="" pattern="#,#0.00"/>${acct.amount}</td>
                        </tr>
                        <tr>
                            <td align="left"><span class="emphasis emphasis_txtx01 pr5">*</span>冻结金额:</td>
                            <td><fmt:formatNumber value="" pattern="#,#0.00"/>${acct.freezeAmount}</td>
                        </tr>
                        <tr>
                            <td align="left"><span class="emphasis emphasis_txtx01 pr5">*</span>业务类型:</td>
                            <td>
                                <select class="select02" style="width:202px;" id="businessType">
                                    <option value="1">借款客户</option>
                                    <option value="2" selected> 线下出借客户</option>
                                    <option value="3"> 线上出借客户</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="left"><span class="emphasis emphasis_txtx01 pr5">*</span>代扣金额:</td>
                            <td><input class="input03" value="" style="width:190px;" name="amount" id="amount"></td>
                        </tr>
                        </tbody>
                	 </table> 
                	 <div class="mb20" id="wid-id-713">
                                        <button id="rechargeAcct" class="btn btn-primary table-nobg-btn"   type="button">确认</button>
                                        <button  onclick="location.href='${contextPath}/funds/accountBusinessList/${withHoldId}'" class="btn btn-default table-nobg-btn "  type="button">取消</button>
                                                </div>
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

<%@include file="../../../include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
  <script src="${contextPath}/js/gqi.js"></script>
<%--     <script src="${contextPath}/script/jquery/layer.min.js"></script> --%>
    <!-- main -->

    <script type="text/javascript">
                $(function(){
                    $("#rechargeAcct").click(function(){
                        var custId = "${acct.custId}";
                        var businessType = $("#businessType").val();
                        var ammount = $("#amount").val();
                        if(!gqi.checkNotNull(ammount)){
                            alert("充值额度不能为空!!");
                            return;
                        }
                        if(!gqi.checkAmount(ammount)){
                            alert("充值额度不是有效金额格式!");
                            return;
                        }
//                         var pageLoad =$.layer({
//                 		    type: 1,
//                 		    title: '',
//                 		    area: ['100', '100'],
//                 		    border: [0,0,'#44a9d5'], //认边框
//                 		    shade: [0.5, '#999'], //遮罩
//                 		    bgcolor:'',
//                 		    closeBtn: false,
//                 		    move: false,
//                 		    page: {
//                 		    	html:'<img src="${contextPath}/images/loading.gif">'
//                 		    }
//                 		});
                        gqi.post("${contextPath}/funds/acount/withhold",$("#withHoldForm").serialize(),null,function(data){
//                                 layer.close(pageLoad); //执行关闭
                                var msg = eval("(" + data + ")");
                                alert(msg.tips);
                                window.location.href="${contextPath}/funds/accountBusinessList/${withHoldId}";
                                return;
                        },"json");
                    });
                });
		</script>
    </body>
</html>
