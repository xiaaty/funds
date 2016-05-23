<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
    <title>系统管理--账户提现--冠群驰骋投资管理(北京)有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            <li>对公账户提现</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                            <form  id="withDrawForm" action="#" method="post">
                   <%--     <input type="hidden" value="${dict.dictId}" name="dictId"  default="0"/> --%>
                        <article class="col-sm-12 col-md-12 sortable-grid ui-sortable">

                            <div class="jarviswidget" id="accBusiwi" data-widget-deletebutton="false" data-widget-editbutton="false">
                               <header>
                                <h2><i class="fa fa-edit pr10"></i>账户提现<font class="pl10 f12 color07"></font></h2>
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
                           <td>${acct.accountNo}
                           <input type="hidden" value="${acct.custId}" name="cust_no" id="custId" />
                           </td>
                        </tr>
                        <tr>
                            <td align="left"><span class="emphasis emphasis_txtx01 pr5">*</span>客户编号:</td>
                            <td>${acct.custId}</td>
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
                                <select class="select02" style="width:202px;"  id="businessType">
                                    <option value="0">对公账户提现</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="left"><span class="emphasis emphasis_txtx01 pr5">*</span>提现金额:</td>
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
<script type="text/javascript">
                $(function(){
                    $("#rechargeAcct").click(function(){
                        var custId = $("#custId").val();
                        var businessType = $("#businessType").val();
                        var ammount = $("#amount").val();
                        if(!gqi.checkNotNull(ammount)){
                            alert("提现额度不能为空!!");
                            return;
                        }
                        if(!gqi.checkAmount(ammount)){
                            alert("提现额度不是有效金额格式!");
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
//                 		    	html:'<img src="${contextPath}/images/info.gif">'
//                 		    }
//                 		});
                        gqi.post("${contextPath}/funds/acount/AccountWithdraw",{"custId":custId,"businessType":businessType,"ammount":ammount},null,function(data){
//                           var msg = eval("(" + data + ")");
                          console.info(data.tips+"*********");
                            alert(data.tips);
//                                 window.location.href="${contextPath}/funds/accountBusinessList/${withHoldId}";
                                return;
                        });
                    });
                });
		</script>
    <!-- main -->

    </body>
</html>
