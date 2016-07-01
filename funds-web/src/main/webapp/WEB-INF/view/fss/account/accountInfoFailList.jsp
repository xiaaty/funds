<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>账户管理--旧版账户--冠群驰骋投资管理(北京)有限公司</title>

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
        .redFont {
            color: red;
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
            <li>旧版账户信息</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
 				<!-- NEW WIDGET START -->
                      <div class="jarviswidget" id="oldAccMsg"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <div>
                                <form class="smart-form" action=""  method="post" id="Form">
                                    <div class="jarviswidget-editbox">
                                    </div>
                                    <div class="widget-body no-padding">
                                        <div class="mt10 mb10">
                                            <table class="table lh32">
                                                <col width="150" />
                                                <col width="220" />
                                                <col width="130" />
                                                <col width="220" />
                                                <col width="150" />
                                                <col />
                                                <tbody>
                                                <tr></tr>
                                                <tr>
                                                    <td class="tr">信息类型：</td>
                                                    <td>
                                                        <label class="input">
                                                            <input type="text" style="width:210px" name="tradeType" value="${map.tradeType}" />
                                                        </label>
                                                    </td>
                                                    <td class="tr">交易/充值/提现日期：</td>
                                                    <td>
                                                        <section class="fl">
                                                            <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
                                                                <input type="text" maxlength="10" name="tradingTime" class="selectdate" placeholder="请选择时间" value="${map.tradingTime}">
                                                            </label>
                                                        </section>
                                                    </td>
                                                </tr>
                                                <tr>
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
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-01"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>对账文件抓取失败文件列表</h2>
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
                                     <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:1100px;">
                                         <col width="220"/>
                                         <col width="220"/>
                                         <col width="220"/>
                                         <col width="220"/>
                                         <col width="220"/>
                                         <thead>
                                         <tr>
                                             <td>序号</td>
                                             <td>信息类型</td>
                                             <td>业务代码</td>
                                             <td>失败日期</td>
                                             <td>操作</td>
                                         </tr>
                                         </thead>
                                         <tbody>
                                         <c:forEach items="${page.list}" var="t" varStatus="Status">
                                             <tr>
                                                 <td>${Status.index}</td>
                                                 <td>${t.tradeType}</td>
                                                 <td>${t.businessCode}</td>
                                                 <td><fmt:formatDate value="${t.tradingTime}" pattern="yyyy-MM-dd"/></td>
                                                 <td><a href="${contextPath}/account/info/accountInfoEdit/${t.id}" class="redFont" title="点击手动抓取" >手动抓取</a></td>
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

<%@include file= "../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>

<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#Form"));
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

     if(${grabState == "1"}){
         JAlert("抓取成功","提示消息");
     }else if(${grabState == "-1"}){
         JAlert("抓取失败","提示消息");
     }

</script>

<%@include file="../../../view/include/foot.jsp"%>
</body>

</html>