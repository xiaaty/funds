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
            <li>出借资产</li>
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
                           
                                <form class="smart-form" id="lendAssetForm" action="${contextPath}/accounting/lendAsset/list" method="post" >
                              
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
                                                        <td class="tr" nowrap="nowrap">客户姓名：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="custName" value="${map.custName}">
                                                            </label>
                                                        </td>
                                                        <td class="tr" nowrap="nowrap">资金平台账号：</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input" style="width:210px" >
                                                                <input type="text" name="accNo" value="${map.accNo}">
                                                            </label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                    <td class="tr" nowrap="nowrap">财务编号：</td>
                                                        <td nowrap="nowrap">
                                                             <label class="input" style="width:210px" >
                                                                <input type="text" name="accountingNo" value="${map.accountingNo}">
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
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-30"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>出借资产</h2>
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
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2300px;">
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
                                        <col width="200" />
                                        <col width="200" />
                                        <col width="200"/>
                                        <thead>
                                        <tr>
                                            <td></td>
                                            <td>客户编号</td>
                                            <td>资金平台账号</td>
                                            <td>财务编号</td>
                                            <td>客户姓名</td>
                                            <td>应收余额</td>
                                            <td>应收本金 </td>
                                            <td>应收利息 </td>
                                            <td>已收本金</td>
                                            <td>已收利息 </td>
                                            <td>冻结金额</td>
                                            <td>可用金额</td>
                                            <td>交易日期 </td>
                                            <td>修改日期 </td>
                                            <td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t" varStatus="l">
                                                <tr>
                                                    <td>${l.index+1}</td>
                                                    <td>${t.custNo}</td>
                                                    <td>${t.accNo}</td>
                                                    <td>${t.accountingNo}</td>
                                                    <td>${t.custName}</td>
                                                    <td>${t.receiveBalance}</td>
                                                    <td>${t.receivePrincipal}</td>
                                                    <td>${t.receiveInterest}</td>
                                                    <td>${t.returnPrincipal}</td>
                                                    <td>${t.returnInterest}</td>
                                                    <td>${t.freezeAmount}</td>
                                                    <td>${t.availableAmount}</td>
                                                    <td>${t.mchnParent}</td>
                                                  <td> <fss:fmtDate value="${t.createTime}"/></td>
                                                    <td> <fss:fmtDate value="${t.modifyTime}"/></td>
                                                <td >
                                                    <a href="${contextPath}/accounting/${t.id}/detail/">查看详情</a>
                                                    </td>
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
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#lendAssetForm"));
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
    			$("#lendAssetForm").submit();
    		}
    	}else{
    		var d = new Date();
    		var str = d.getFullYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+(d.getDate()<10?"0":"")+d.getDate();
    		if(a[0].value>str){
    			JAlert("请检查您输入的日期","提示消息");
    		}else{
    			$("#lendAssetForm").submit();
    		}
    	}
    }
   
</script>

<%@include file= "../../../../view/include/foot.jsp"%>
</body>

</html>