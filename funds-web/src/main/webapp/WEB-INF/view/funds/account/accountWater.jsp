<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>账户管理--交易流水-冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">

    <%@include file="../../../view/include/common_css_js.jsp"%>
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
<%@include file="../../../view/include/menu.jsp"%>
<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>账户管理</li>
            <li>交易流水</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <!-- NEW WIDGET START -->
                    <div class="jarviswidget" id="sequencelist"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <h2>快速搜索</h2>
                        </header>
                        <!-- widget div-->
                        <div>

                            <form class="smart-form" id="sequneceForm" action="${contextPath}/funds/account/accSequence/${custId}" method="post" >

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
                                            <tr>
                                                <td class="tr">操作类型：</td>
                                                <td>
                                                    <select id = "actionType" name = "actionType" style="width:150px;height: 30px;">
                                                        <option value="">所有</option>
                                                        <option  <c:if test="${map.actionType==1}"> selected="selected" </c:if> value="1">充值</option>
                                                        <option  <c:if test="${map.actionType==2}"> selected="selected" </c:if> value="2">提现</option>
                                                        <option  <c:if test="${map.actionType==3}"> selected="selected" </c:if> value="3">转账</option>
                                                        <option  <c:if test="${map.actionType==4}"> selected="selected" </c:if> value="4">冻结</option>
                                                        <option  <c:if test="${map.actionType==5}"> selected="selected" </c:if> value="5">解冻</option>
                                                        <option  <c:if test="${map.actionType==6}"> selected="selected" </c:if> value="6">投标</option>
                                                        <option  <c:if test="${map.actionType==7}"> selected="selected" </c:if> value="7">还款</option>
                                                        <option  <c:if test="${map.actionType==8}"> selected="selected" </c:if> value="8">债权转让</option>
                                                    </select>
                                                </td>
                                                <td class="tr">创建日期：</td>
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
                                        <button class="btn btn-primary" type="button" onclick="verify();">查&nbsp;&nbsp;&nbsp;询</button>
                                    </footer>
                                </div>
                                <!-- end widget content -->
                            </form>
                        </div>
                    </div>



                    <!-- NEW WIDGET START -->
                    <!-- 	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-1201"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>交易流水</h2>
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
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:1600px;">
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="150" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="300" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="150" />
                                        <col width="200" />
                                        <thead>
                                            <tr>
                                                <td>出账客户编号</td>
                                                <td>入账客户编号</td>
                                                <td>交易类型</td>
                                                <td>操作类型</td>
                                                <td>账户ID</td>
                                                <td>业务类型</td>
                                                <td>交易金额</td>
                                                <td>交易摘要</td>
                                                <td>订单号</td>
                                                <td>来源账户ID </td>
                                                <td>第三方交易流水号</td>
                                                <td>创建时间 </td>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t">
                                            <tr>
                                                <td>${t.custId}</td>
                                                <td>${t.toCustId}</td>
                                                <td><fss:dictView key="${t.tradeType}" /></td>
                                                <td>
                                                        <c:if test="${t.actionType==1}">充值</c:if>
                                                        <c:if test="${t.actionType==2}">提现</c:if>
                                                        <c:if test="${t.actionType==3}">转账</c:if>
                                                        <c:if test="${t.actionType==4}">冻结</c:if>
                                                        <c:if test="${t.actionType==5}">解冻</c:if>
                                                        <c:if test="${t.actionType==6}">投标</c:if>
                                                        <c:if test="${t.actionType==7}">还款</c:if>
                                                        <c:if test="${t.actionType==8}">债权转让</c:if>
                                                </td>
                                                <td>${t.accountId}</td>
                                                <td>${t.fundType}</td>
                                                <td align="right"><fss:money money="${t.amount}"/></td>
                                                <td>${t.sumary}</td>
                                                <td>${t.orderNo}</td>
                                                <td>${t.oAccountId}</td>
                                                <td>${t.sOrderNo}</td>
                                                <td><fss:fmtDate value="${t.createTime}"/></td>
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

<%@include file="../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#sequneceForm"));
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
                alert("请检查您输入的日期");
            }else{
                $("#sequneceForm").submit();
            }
        }else{
            var d = new Date();
            var str = d.getFullYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+(d.getDate()<10?"0":"")+d.getDate();
            if(a[0].value>str){
                alert("请检查您输入的日期");
            }else{
                $("#sequneceForm").submit();
            }
        }
    }
</script>

<%@include file="../../../view/include/foot.jsp"%>
</body>

</html>