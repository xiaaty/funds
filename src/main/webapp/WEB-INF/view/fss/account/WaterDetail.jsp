<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
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

<body>
<%@include file= "../../../view/include/menu.jsp"%>


<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <li>客户列表</li>
            <li>查看账户</li>
            <li>查看流水</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
    <div id="content">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="jarviswidget" id="WaterDetail"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                           
                                <form class="smart-form" id="waterDetailForm" action="${contextPath}/fss/account/waterDetail/${id}" method="post" >
                              
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
                                                         <td class="tr">开户日期：</td>
                                            <td colspan="3">
                                                <section class="fl">
                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                        <input type="text" maxlength="10" readonly="readonly" name="startDate" class="selectdate" placeholder="请选择时间" value="${startDate}">
                                                    </label>
                                                </section>
                                                <span class="fl">&nbsp;至&nbsp;</span>
                                                <section class="fl">
                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                        <input type="text" maxlength="10" readonly="readonly"  name="endDate" class="selectdate" placeholder="请选择时间" value="${endDate}">
                                                    </label>
                                                </section>
                                            </td>
                                                    </tr>
                                                   
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer>
                                            <!-- <button class="btn btn-default" onclick="window.history.back();" type="button">重&nbsp;&nbsp;&nbsp;置</button> -->
                                            <button class="btn btn-primary" type="button" onclick="verify();">查&nbsp;&nbsp;&nbsp;询</button>
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
                            <h2>流水详情</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="water">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                   <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2300px;">
                                    	<col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                    	<col width="150" />
                                        <col width="150" />
                                        <col width="150" />
                                    	<col width="150" />
                                        <col width="150" />
                                        <col width="150" />
                                    	<col width="200" />
                                        <col width="100" />
                                        <col width="150" />
                                    	<col width="150" />
                                        <col width="100" />
                                        <col width="100" />
                                        <thead>
                                        <tr>
                                            <td>账号</td>
                                            <td>交易日期</td>
                                            <td>交易时间 </td>
                                            <td>支出 </td>
                                            <td>收入 </td>
                                            <td>余额  </td>
                                            <td>创建日期 </td>
                                            <td>修改日期 </td>
                                            <td>交易描述</td>
                                            <td>交易渠道</td>
                                            <td>三方交易订单号</td>
                                            <td>API交易订单号</td>
                                            <td>交所属商户号（大）</td>
                                            <td>所属商户号（小）</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t">
                                                <tr>
<!--                                                  账号  -->
                                                    <td>${t.accNo}</td>                  
<!--                                             	 交易日期  -->
                                                    <td><fmt:formatDate value="${t.createTime}" pattern="yyyy-MM--dd"/></td>
<!--                                             	 交易时间   -->
                                                    <td><fmt:formatDate value="${t.createTime}" pattern="HH:mm:ss"/></td>
<!--                                              	支出   -->
                                                    <td>${t.debitAmount}</td>
<!--                                             	 收入   -->
                                                    <td>${t.creditAmount}</td>
<!--                                              	余额    -->
                                                    <td>${t.banlance}</td>
<!--                                            	 创建日期 -->
                                                    <td> <fmt:formatDate value="${t.createTime}" pattern="yyyy-MM--dd HH:mm:ss"/></td>
<!--                                              	修改日期   -->
                                                    <td> <fmt:formatDate value="${t.modifyTime}" pattern="yyyy-MM--dd HH:mm:ss"/></td>
<!--                                             	 交易描述  -->
                                                    <td>${t.sumary}</td>
<!--                                              	交易渠道  -->
                                                    <td>${t.payChannel}</td>
<!--                                            	  三方交易订单号  -->
                                                    <td>${t.orderNo}</td>
<!--                                              	API交易订单号  -->
                                                    <td>${t.seqo}</td>
<!--                                              	交所属商户号（大）  -->
                                                    <td>${t.mchnParent}</td>
<!--                                             	 所属商户号（小）  -->
                                                    <td>${t.mchnChild}</td>
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
<%@include file= "../../../view/include/common_footer_css_js.jsp"%>
</div>


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#waterDetailForm"));
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
    			$("#waterDetailForm").submit();
    		}
    	}else{
    		var d = new Date();
    		var str = d.getFullYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+(d.getDate()<10?"0":"")+d.getDate();
    		if(a[0].value>str){
    			alert("请检查您输入的日期");
    		}else{
    			$("#waterDetailForm").submit();
    		}
    	}
    }
</script>

<%@include file= "../../../view/include/foot.jsp"%>
</body>

</html>