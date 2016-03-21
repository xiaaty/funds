<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>定时器配置--资金清结算系统--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
    
    <%@include file= "../../../view/include/common_css_js.jsp"%>
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
            <li>系统管理</li>
            <li>系统配置</li>
            <li>定时器配置</li>
        </ol>
        <!-- end breadcrumb -->
    </div>
    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-30"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>定时器列表</h2>
                        </header>
                        <!-- widget div-->
                        <div>
                            <form class="smart-form" id="quartzForm">
                                <!-- widget edit box -->
                                <div class="jarviswidget-editbox">
                                    <!-- This area used as dropdown edit box -->
                                </div>
                                <!-- end widget edit box -->
                                <!-- widget content -->
                                <div class="widget-body">
                                 <div class="widget-body-nobg-toolbar" style="overflow:hidden;">
                                        <button type="button" class="btn btn-default fl table-nobg-btn" id="btn_add"><i class="fa fa-plus"></i>&nbsp;添加</button>
                                    </div>
                                    <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2300px;">
                                    	<col width="200" />
                                    	<col width="500" />
                                    	<col width="300" />
                                    	<col width="500" />
                                    	<col width="300" />
                                    	<col width="300" />
                                    	<col width="200" />
                                        <thead>
                                        <tr>
                                        	<td>编号</td>
                                            <td>job名称</td>
                                            <td>job类名  </td>
                                            <td>定时cron  </td>
                                            <td>状态 </td>
                                            <td>执行ip </td>
                                            <td>操作</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.list}" var="t">
                                           <tr>
                                               <td>${t.id}</td>
                                               <td>${t.name}</td>
                                               <td>${t.className}</td>
                                               <td>${t.cron}</td>
                                               <td>${t.state=='98040001'?"有效":"无效"}</td>
                                               <td>${t.ip}</td>
                                             <td>
                                               	<a href="${contextPath}/quartz/toQuartzUpdate/${t.id}">修改</a>
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
<%@include file= "../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
</div>


 <script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        pageSetUp();
        DT_page("borrow-rep-table12", true, '${page.JSON}', $("#quartzForm"));
    });
    //添加按钮按下
    $("#btn_add").button().click(function() {
    	window.open("${contextPath}/quartz/toQuartzAdd","_self");
    });
    
//    	删除
   function deleteQuartz(id){
	            $.ajax({
	            	url : "${contextPath}/fund/deleteBankCard?id="+id,
	                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	                dataType: "json",
	                success: function (data) {
	                    if (data.code == '0000') {
	                      jAlert("删除成功!", '确认信息');
	                      //自动跳转
	                      parent.location.href="${contextPath}/fund/bankCardsManage";
	                    } else {
	                        return;
	                    }
	                }
	            });
    }
    
  
    
</script>

<%@include file= "../../../view/include/foot.jsp"%>
</body>

</html>