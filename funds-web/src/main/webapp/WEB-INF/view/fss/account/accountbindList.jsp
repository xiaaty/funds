<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统配置--统一支付绑定账户信息--冠群驰骋投资管理(北京)有限公司</title>

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
            <li>系统配置</li>
            <li>统一支付绑定账户信息</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <!-- NEW WIDGET START -->
                    <div class="jarviswidget" id="accMappinglist"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <h2>快速搜索</h2>
                        </header>
                        <div>
                            <form class="smart-form" action=""  method="post" id="accBindForm">
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
                                                <td class="tr">客户编号：</td>
                                                <td>
                                                    <label class="input">
                                                        <input type="text" style="width:200px" name="busiId" value="${map.busiId}" />
                                                    </label>
                                                </td>
                                                <td class="tr">账户类型：</td>
                                                <td nowrap="nowrap">
                                                    <select id = "busiType" name = "busiType" style="width:200px;height: 30px;">
                                                            <option value="">所有</option>
                                                            <option  <c:if test="${map.busiType==1}"> selected="selected" </c:if> value="1">借款账户</option>
                                                            <option  <c:if test="${map.busiType==2}"> selected="selected" </c:if> value="2">线下出借账户</option>
                                                            <option  <c:if test="${map.busiType==3}"> selected="selected" </c:if> value="3">线上出借账户</option>
                                                            <option  <c:if test="${map.busiType==50}"> selected="selected" </c:if> value="50">其他账户</option>
                                                            <option  <c:if test="${map.busiType==60}"> selected="selected" </c:if> value="60">费用账户</option>
                                                            <option  <c:if test="${map.busiType==70}"> selected="selected" </c:if> value="70">运营账户</option>
                                                            <option  <c:if test="${map.busiType==90}"> selected="selected" </c:if> value="90">标的账户</option>
                                                            <option  <c:if test="${map.busiType==96}"> selected="selected" </c:if> value="96">应付款账户</option>
                                                        </select>
                                                </td>
                                                <td class="tr">借款合同号：</td>
                                                <td>
                                                    <label class="input">
                                                        <input type="text" style="width:200px" name="contractNo" value="${map.contractNo}" />
                                                    </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="tr">账户编号：</td>
                                                <td>
                                                    <label class="input">
                                                        <input type="text" style="width:200px" name="accNo" value="${map.accNo}" />
                                                    </label>
                                                </td>
                                                <td class="tr">是否绑定：</td>
                                                <td nowrap="nowrap">
                                                    <select id = "status" name = "status" style="width:200px;height: 30px;">
                                                        <option value="">所有</option>
                                                        <option  <c:if test="${map.status=='0'}"> selected="selected" </c:if> value="0">未绑定</option>
                                                        <option  <c:if test="${map.status=='1'}"> selected="selected" </c:if> value="1">已绑定</option>
                                                    </select>
                                                </td>
                                                <td class="tr">创建时间：</td>
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
                                        <button type="submit" class="btn btn-primary">查&nbsp;&nbsp;&nbsp;询</button>
                                    </footer>
                                </div>
                                <!-- end widget content -->
                            </form>
                        </div>
                    </div>



                    <!-- NEW WIDGET START -->
                    <!-- 	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-7171"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>统一支付绑定账户信息</h2>
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
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:1700px;">
                                        <col width="50" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="200" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="150" />
                                        <col width="100" />
                                        <col width="200" />
                                        <col width="100" />
                                        <col width="200" />
                                        <col width="200" />
                                        <thead>
                                        <tr>
                                            <td>序号</td>
                                            <td>客户编号</td>
                                            <td>客户姓名</td>
                                            <td>手机号码</td>
                                            <td>账户编号</td>
                                            <td>账户类型</td>
                                            <td>是否绑定</td>
                                            <td>交易流水</td>
                                            <td>交易类型</td>
                                            <td>借款合同号</td>
                                            <td>创建日期</td>
                                            <td>修改日期</td>
                                            <td>开户日期</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t">
                                            <tr class="">
                                                <td>${t.id}</td>
                                                <td>${t.busiId}</td>
                                                <td>${t.custName}</td>
                                                <td>${t.mobile}</td>
                                                <td>${t.accNo}</td>
                                                <td>${t.busiType}</td>
                                                <td>${t.status=="0"?"未绑定":"已绑定"}</td>
                                                <td>${t.seqNo}</td>
                                                <td>${t.tradeType}</td>
                                                <td>${t.contractNo}</td>
                                                <td><fmt:formatDate value="${t.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                <td><fmt:formatDate value="${t.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                <td><fmt:formatDate value="${t.openAccTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#accBindForm"));
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
                $("#accBindForm").submit();
            }
        }else{
            var d = new Date();
            var str = d.getFullYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+(d.getDate()<10?"0":"")+d.getDate();
            if(a[0].value>str){
                alert("请检查您输入的日期");
            }else{
                $("#accBindForm").submit();
            }
        }
    }

</script>

<%@include file="../../../view/include/foot.jsp"%>
</body>

</html>