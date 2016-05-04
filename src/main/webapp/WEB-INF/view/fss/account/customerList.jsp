<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>账户管理--旧版账户--冠群驰骋投资管理(北京)有限公司</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="page" uri="/WEB-INF/pagetag.tld"%>
    <%@ taglib prefix="func" uri="/WEB-INF/func.tld"%>
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
            <li>客户信息管理</li>
            <li>客户核心信息</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
 				<!-- NEW WIDGET START -->
                      <div class="jarviswidget" id="wid-id-32"  data-widget-deletebutton="false" data-widget-editbutton="false">
                            <header>
                                <h2>快速搜索</h2>
                            </header>
                            <div>
                                <form class="smart-form" action="${contextPath}/fss/account/hxyhlist"  method="post" id="custForm">
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
	                                                     <td class="tr">客户姓名:</td>
	                                                        <td>
	                                                            <label class="input"  style="width:210px" >
	                                                                <input type="text" name="name" value="${map.name}"/>
	                                                            </label>
	                                                     </td>
	                                                     <td class="tr">证件号码：</td>
	                                                     <td>
	                                                         <label class="input" style="width:210px" >
	                                                             <input type="text" name="certNo" value="${map.certNo}" />
	                                                         </label>
	                                                     </td>
                                                    </tr>
                                                    <tr>
                                                       <td class="tr">手机号码：</td>
                                                        <td>
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="mobile" value="${map.mobile}" />
                                                            </label>
                                                        </td>
                                                        <td class="tr">开户日期：</td>
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
                                           <button class="btn btn-primary" onclick="javascript:void(0);">查&nbsp;&nbsp;&nbsp;询</button>
                                        </footer>
                                    </div>
                                    <!-- end widget content -->
    							</form>
                    		</div>
                		</div>
                
                
                
                    <!-- NEW WIDGET START -->
                    <!-- 	<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12"> -->
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-33"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>客户核心信息列表</h2>
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
                                        <col width="150" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="100" />
                                        <col width="150" />
                                        <col width="100" />
                                        <col width="150" />
                                        <col width="150" />
                                        <col width="100" />
                                        <thead>
                                        <tr>
                                              <td>客户编号</td>
                                              <td align="left">客户姓名</td>
                                              <td>客户手机号</td>
                                              <td>证件类型</td>
                                              <td>证件号码</td>
                                              <td>是否第三方开户</td>
                                              <td>创建日期</td>
                                              <td>修改日期</td> 
                                              <td>操作</td> 
                                        </tr>
                                        </thead>
                                        <tbody>
                                             <c:forEach items="${page.list}" var="customer">
                                                <tr>
                                                    <td>${customer.custNo}</td>
                                                    <td  align="left">${customer.name}</td>
                                                    <td><fss:fmtData value="${customer.mobile}"/></td>
                                                    <td>${customer.certType==1?"身份证":"护照"}</td>
                                                    <td><fss:fmtData value="${customer.certNo}"/></td>
                                                    <td>否</td>
                                                    <td><fmt:formatDate value="${customer.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    <td><fmt:formatDate value="${customer.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    <%-- <td><a href="${contextPath}/fss/account/customerAccountDetail">查看账户资产</a></td>--%>
                                                    <td><a href="${contextPath}/fss/account/customerAccountDetail/${customer.custNo}">查看账户资产</a></td> 
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
	     DT_page("borrow-rep-table12", true, '${page.JSON}', $("#custForm"));
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
	    			$("#custForm").submit();
	    		}
	    	}else{
	    		var d = new Date();
	    		var str = d.getFullYear()+"-"+((d.getMonth()+1)<10?"0":"")+(d.getMonth()+1)+"-"+(d.getDate()<10?"0":"")+d.getDate();
	    		if(a[0].value>str){
	    			JAlert("请检查您输入的日期","提示消息");
	    		}else{
	    			$("#custForm").submit();
	    		}
	    	}
	    }
	     
</script>

<%@include file="../../../view/include/foot.jsp"%>
</body>

</html>