<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>交易审核--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <%@include file= "../../../../view/include/common_css_js.jsp"%>
    <style>
        .button-icon i{
            line-height:32px;
        }
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
            <li>交易管理</li>
            <li>借款流程</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
    <div id="contents">
            <section id="widget-grid" class="">
                <div class="row">
                    <!-- NEW WIDGET START -->
                    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="jarviswidget" id="bloan"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <!-- widget div-->
                            <div>
                           
                                <form class="smart-form" id="mortgageePayment" action="${contextPath}/trade/${type}" method="post" >
                              
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
                                                        <td class="tr">手机号：</td>
                                                        <td>
                                                            <label class="input">
                                                                <input type="text" style="width:150px" name="mobile" value="${map.mobile}" />
                                                            </label>
                                                        </td>
                                                        <td class="tr">客户姓名：</td>
                                                        <td>
                                                            <label class="input">
                                                                <input type="text" style="width:150px" name="custName" value="${map.custName}" />
                                                            </label>
                                                        </td>
                                                        <td class="tr">交易类型：</td>
                                                        <td>
                                                            <label>
                                                                <select id = "tradeType" name = "tradeType" style="width:150px;height: 30px;">
                                                                    <option value="">所有</option>
                                                                    <option  <c:if test="${map.tradeType==11040002}"> selected="selected" </c:if> value="11040002">wap提现</option>
                                                                    <option  <c:if test="${map.tradeType==11040003}"> selected="selected" </c:if> value="11040003" >安卓提现</option>
                                                                    <option  <c:if test="${map.tradeType==11040004}"> selected="selected" </c:if> value="11040004" >ios提现</option>
                                                                    <option  <c:if test="${map.tradeType==11040005}"> selected="selected" </c:if> value="11040005" >微信提现</option>
                                                                    <option  <c:if test="${map.tradeType==11040015}"> selected="selected" </c:if> value="11040015" >新版wap提现</option>
                                                                </select>
                                                            </label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="tr">交易状态：</td>
                                                        <td>
                                                            <label>
                                                                <select id = "status" name = "status" style="width:150px;height: 30px;">
                                                                    <option value="">所有</option>
                                                                    <option  <c:if test="${map.status==10030001}"> selected="selected" </c:if> value="10030001">交易提交</option>
                                                                    <option  <c:if test="${map.status==10030002}"> selected="selected" </c:if> value="10030002" >交易成功</option>
                                                                    <option  <c:if test="${map.status==10030003}"> selected="selected" </c:if> value="10030003" >交易失败</option>
                                                                    <option  <c:if test="${map.status==10030004}"> selected="selected" </c:if> value="10030004" >交易关闭</option>
                                                                </select>
                                                            </label>
                                                        </td>
                                                        <td class="tr">流程状态：</td>
                                                        <td>
                                                            <select id = "processState" name = "processState" style="width:150px;height: 30px;">
                                                                <option value="">所有</option>
                                                                <fss:dictOrder var="order" dictOrder="">
                                                                    <option value="${order.key}"  <c:if test="${order.key==map.processState}">selected</c:if> >${order.value}</option>
                                                                </fss:dictOrder>
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
                    <div class="jarviswidget jarviswidget-color-darken" id="borrowerLoan"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>借款流程</h2>
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
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2450px;">
                                        <col width="50" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200" />
                                        <thead>
                                        <tr>
                                            <td></td>
                                            <td>交易流水号</td>
                                            <td>客户姓名</td>
                                            <td>客户电话</td>
                                            <td>客户账户</td>
                                            <td>交易金额</td>
                                            <td>交易类型</td>
                                            <td>交易状态</td>
                                            <td>流程状态</td>
                                            <td>创建时间</td>
                                            <td>修改时间</td>
                                            <td>备注</td>
                                            <td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t">
                                            <tr>
                                                <td>${l.index+1}</td>
                                                <td>${t.seqNo}</td>
                                                <td>${t.fromCustName}</td>
                                                <td>${t.fromCustMobile}</td>
                                                <td>${t.fromAccId}</td>
                                                <td><fss:money money="${t.amt}"/></td>
                                                <td><fss:dictView key="${t.tradeType}" /></td>
                                                <td><fss:dictView key="${t.status}" /></td>
                                                <td><fss:dictView key="${t.processState}" /></td>
                                                <td><fss:fmtDate value="${t.createTime}"/></td>
                                                <td><fss:fmtDate value="${t.modifyTime}"/></td>
                                                <td>${t.memo}</td>
                                                <td>
                                                    <a href="${contextPath}/trade/processChild/${t.id}">查看详细</a>
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
<%@include file= "../../../../view/include/common_footer_css_js.jsp"%>
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
    	var a=document.getElementsByName("startTime");
    	var b=document.getElementsByName("endTime");
    	if(b[0].value!=null&&b[0].value!=''){
    		
    		if(a[0].value>b[0].value){
    			JAlert("请检查您输入的日期","提示消息");
    		}else{
    			$("#mortgageePayment").submit();
    		}
    	}else{
    		var d = new Date();
    		var str = d.getFullYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+(d.getDate()<10?"0":"")+d.getDate();
    		if(a[0].value>str){
    			JAlert("请检查您输入的日期","提示消息");
    		}else{
    			$("#mortgageePayment").submit();
    		}
    	}
    }

</script>

<%@include file= "../../../../view/include/foot.jsp"%>
</body>

</html>