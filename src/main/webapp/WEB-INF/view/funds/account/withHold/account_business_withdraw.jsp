<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c0" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页--账户提现--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <%@include file="../../../../view/include/common_css_js.jsp"%>
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
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
<script type="text/javascript">
                    function onSub(){
                        var custId = "+++";
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
                        var pageLoad =$.layer({
                		    type: 1,
                		    title: '',
                		    area: ['100', '100'],
                		    border: [0,0,'#44a9d5'], //认边框
                		    shade: [0.5, '#999'], //遮罩
                		    bgcolor:'',
                		    closeBtn: false,
                		    move: false,
                		    page: {
                		    	html:'<img src="${contextPath}/images/loading.gif">'
                		    }
                		});
                        gqi.post("${contextPath}/web/updateWithdrawAccount",{"custId":custId,"businessType":businessType,"amount":ammount},null,function(data){
                        	layer.close(pageLoad); //执行关闭
                        	var msg = eval("(" + data + ")");
                            alert(msg.tips);
                            window.location.href="${contextPath}/WEB-INF/view/funds/accountBusinessList";
                            return;
                        });
                    };
		</script>

<body>
<%@include file= "../../../../view/include/menu.jsp"%>

<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>对公账户</li>
            <li>账户提现</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div class="jarviswidget" id="wid-id-641"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <h2>账户提现</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="busiUpdateForm" action="#" method="post">
                                <input type="hidden" value="${busi.id}" name="id"/>
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body no-padding">
                                    <div class="mt10 mb10">
                                        <table class="table">
                                            <col width="100" />
                                            <col width="220" />
                                            <col width="100" />
                                            <col />
                                            <tbody>
                        <tr>
                           <td align="right"><span class="emphasis emphasis_txtx01 pr5">*</span>账户编号:</td>
                           <td>${acct.accountNo}</td>
                        </tr>
                        <tr>
                            <td align="right"><span class="emphasis emphasis_txtx01 pr5">*</span>客户编号:</td>
                            <td>${acct.custId}</td>
                        </tr>
                        <tr>

                            <td align="right"><span class="emphasis emphasis_txtx01 pr5">*</span>账户类型:</td>
                            <td>
                                <c:if test="${acct.accountType == 1}">客户账户</c:if>
                                <c:if test="${acct.accountType == 2}">A0账户</c:if>
                                <c:if test="${acct.accountType == 3}">AX账户</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td align="right"><span class="emphasis emphasis_txtx01 pr5">*</span>账户余额:</td>
                            <td><fmt:formatNumber value="${acct.amount}" pattern="#,#00.00"/></td>
                        </tr>
                        <tr>
                            <td align="right"><span class="emphasis emphasis_txtx01 pr5">*</span>冻结金额:</td>
                            <td><fmt:formatNumber value="${acct.freezeAmount}" pattern="#,#00.00"/></td>
                        </tr>
                        <tr>
                            <td align="right">业务类型:</td>
                            <td>
                                <select class="select02" style="width:202px;" name="busiType" id="businessType">
                                    <option value="0">对公账户提现</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right"><span class="emphasis emphasis_txtx01 pr5">*</span>提现金额:</td>
                            <td><input class="input03" value="" style="width:190px;" name="amount" id="amount"></td>
                        </tr>
                        </tbody>
                                        </table>
                                    </div>
                                    <footer>
                                    <tr>
                                    	<td>
                                        <button id="btn-success" class="btn btn-primary"   onclick="onSub();" type="button">确认</button>
                                        </td><td>
                                        <button id="btn-success" onclick="location.href='${contextPath}/funds/accountBusinessList/${withHoldId}'" class="btn btn-primary"  id="rechargeAcct" type="button">取消</button>
                                    	</td>
                                    	</tr>
                                    </footer>
                                </div>
                                <!-- end widget content -->
                            </form>
                        </div>


                    </div>
            <!-- end widget content -->
<%@include file="../../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
</div>


  <script type="text/javascript">
            $(function() {
                $('ul li a').each(function(){
                    var st = $(this).attr("href");
                    if(st.indexOf("queryBusinessAccountList") >= 0){
                        $(this).parent().parent().parent().trigger("click").addClass("t1 nav_line on");
                        $(this).parent().addClass("chd_on");
                    }
                });
            });
        </script>

<%@include file= "../../../../view/include/foot.jsp"%>
</body>

</html>