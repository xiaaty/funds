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
                                                <col width="100" />
                                                <col width="220" />
                                                <col width="100" />
                                                <col width="220" />
                                                <col width="100" />
                                                <col />
                                                <tbody>
                                                    <tr></tr>
                                                    <tr>
                                                        <td class="tr">手机号码：</td>
                                                        <td>
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="mobile" value="${Customer.mobile}" />
                                                            </label>
                                                        </td>
                                                         <td class="tr">身份证号：</td>
                                                        <td>
                                                            <label class="input" style="width:210px" >
                                                                <input type="text" name="cert_no" value="${Customer.cert_no}" />
                                                            </label>
                                                        </td>
                                                        <td class="tr">姓名:</td>
                                                        <td>
                                                            <label class="input"  style="width:210px" >
                                                                <input type="text" name="name" value="${Customer.name}"/>
                                                            </label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="tr">开户日期：</td>
			                                            <td colspan="5">
			                                                <section class="fl">
			                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
			                                                        <input type="text" maxlength="10" name="startDate" class="selectdate" placeholder="请选择时间" value="${Customer.create_time}">
			                                                    </label>
			                                                </section>
			                                                <span class="fl">&nbsp;至&nbsp;</span>
			                                                <section class="fl">
			                                                    <label class="input" style="width:140px;"> <i class="icon-append fa fa-calendar"></i>
			                                                        <input type="text" maxlength="10" name="endDate" class="selectdate" placeholder="请选择时间" value="${Customer.modify_time}">
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
                    <div class="jarviswidget jarviswidget-color-darken" id="menu-id-01"  data-widget-deletebutton="false" data-widget-editbutton="false">
                        <header>
                            <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                            <h2>账户信息列表</h2>
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
                                     <table id="borrow-rep-table12" class="table table-bordered tc mt15" style="min-width:2300px;">
                                        <col width="200" />
                                        <col width="250" />
                                        <col width="250" />
                                        <col width="250" />
                                        <col width="250" />
                                        <col width="250" />
                                        <col width="300" />
                                        <col width="300" />
                                        <col width="250" />
                                        <thead>
                                        <tr>
                                              <td>客户编号</td>
                                              <td>客户姓名</td>
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
                                             <c:forEach items="${page.list}" var="t">
                                                <tr class="success">
                                                    <td>${t.cust_no}</td>
                                                    <td>${t.name}</td>
                                                    <td><fss:fmtData value="${t.mobile}"/></td>
                                                    <td>${t.cert_type==1?"身份证":"护照"}</td>
                                                    <td><fss:fmtData value="${t.cert_no}"/></td>
                                                    <td>否</td>
                                                    <td><fmt:formatDate value="${t.create_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    <td><fmt:formatDate value="${t.modify_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    <td><a href="#">查看账户资产</a></td>
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
	     DT_page("borrow-rep-table12", true, '${page.JSON}', $("#Form"));
	 }); 
 
</script>

<%@include file="../../../view/include/foot.jsp"%>
</body>

</html>