<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资金清结算系统--项目信息回盘--冠群驰骋投资管理(北京)有限公司</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" media="screen" href="${contextPath}/css/jquery.alerts.css">
    
   <%@include file="../../../../view/include/common_css_js.jsp"%>
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
<%@include file="../../../../view/include/menu.jsp"%>
<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">

        <!-- breadcrumb -->
        <ol class="breadcrumb">
           <li>入账管理</li>
            <li>项目信息回盘</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
 				<!-- NEW WIDGET START -->
                      <div class="jarviswidget" id="projectInFoback"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <div>
                                <form class="smart-form" action="${contextPath}/fstp/projectcallback"  method="post" id="projectForm">
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
                                                      <td class="tr" nowrap="nowrap">项目编号:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:250px" >
                                                              <input type="text" name="itemNo" value="${map.itemNo}">
                                                            </label>
                                                        </td>
                                                        
                                                        <td class="tr" nowrap="nowrap">项目名称:</td>
                                                        <td nowrap="nowrap">
                                                            <label class="input"  style="width:250px" >
                                                              <input type="text" name="itemName" value="${map.itemName}">
                                                            </label>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <footer>
                                           <button class="btn btn-primary" onclick="javascript:void(0);">查&nbsp;&nbsp;&nbsp;询</button>
                                        </footer>
                                    </div>
                                    <!-- end widget content -->
    							</form>
                    		</div>
                		</div>
                
                    <!-- NEW WIDGET START -->
                    <!-- 	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-478"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>项目信息回盘</h2>
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
                                     <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:900px;">
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="100" />
                                    	<col width="150" />
                                    	<col width="150" />
                                    	<col width="100" />
                                    	<thead>
                                    	<tr>
	                                    	<td>项目编号</td>
	                                    	<td>项目名称</td>
	                                    	<td>支付机构平台ID</td>
	                                    	<td>审核状态</td>
	                                    	<td>应答码</td>
	                                    	<td>应答描述</td>
	                                    	<td>银行标的ID</td>
	                                    	<td>拒绝原因</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                         <c:forEach items="${page.list}" var="t">
                                         <tr>
	                                    	<td>${t.itemNo}</td>
	                                    	<td>${t.itemName}</td>
	                                    	<td>${t.payChannel}</td>
	                                    	<td>
	                                    		<c:if test="${t.status=='S'}">待审批</c:if>
		                                    	<c:if test="${t.status=='P'}">通过</c:if>
	                                    	</td>
	                                    	<td>
		                                    	<c:if test="${t.respCode==0}">成功</c:if>
		                                    	<c:if test="${t.respCode==1}">失败</c:if>
		                                    	<c:if test="${t.respCode==112115}">借款人证件号码不能为空</c:if>
		                                    	<c:if test="${t.respCode==112110}">项目信息已存在</c:if>
		                                    	<c:if test="${t.respCode==112111}">平台信息不存在</c:if>
		                                    	<c:if test="${t.respCode==112112}">系统信息不存在</c:if>
	                                    	</td>
	                                    	<td>${t.respMsg}</td>
	                                    	<td>${t.bidId}</td>
	                                    	<td>${t.failedMsg}</td>
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
<%@include file="../../../../view/include/common_footer_css_js.jsp"%>
<script src="${contextPath}/js/jquery.form.js" ></script>
<script src="${contextPath}/js/jquery.alerts.js" ></script>
 <script type="text/javascript" charset="utf-8">
	 $(document).ready(function () {
	     pageSetUp();
	     DT_page("borrow-rep-table12", true, '${page.JSON}', $("#projectForm"));
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
   			$("#projectForm").submit();
	    }
	     
</script>

<%@include file="../../../../view/include/foot.jsp"%>
</body>

</html>