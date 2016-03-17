<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>账户管理--客户账户--冠群驰骋投资管理(北京)有限公司</title>

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
            <li>账户管理</li>
            <li>客户账户</li>
        </ol>
        <!-- end breadcrumb -->
    </div>

    <div id="content">
        <section id="widget-grid" class="">
            <div class="row">
                <!-- NEW WIDGET START -->
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
 				<!-- NEW WIDGET START -->
                      <div class="jarviswidget" id="wid-id-11"  data-widget-deletebutton="false" data-widget-editbutton="false">
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
                                                        <td class="tr">客户账号：</td>
                                                        <td>
                                                            <label class="input">
                                                                <input type="text" style="width:150px" name="accNo" value="${bussaccount.accNo}" />
                                                            </label>
                                                        </td>
                                                        <td class="tr">客户编号：</td>
                                                        <td>
                                                            <label class="input" style="width:150px" >
                                                                <input type="text" name="custNo" value="${bussaccount.custNo}" />
                                                            </label>
                                                        </td>
                                                        <td class="tr">客户姓名:</td>
                                                        <td>
                                                            <label class="input"  style="width:150px" >
                                                                <input type="text" name="custName" value="${bussaccount.custName}"/>
                                                            </label>
                                                        </td>
                                                        <td class="tr">证件号码:</td>
                                                        <td>
                                                            <label class="input"  style="width:200px" >
                                                                <input type="text" name="certNo" value="${bussaccount.certNo}"/>
                                                            </label>
                                                        </td>
                                                        <td class="tr">账户类型:</td>
                                                        <td>
                                                            <select id = "accType" name = "accType" style="width:150px;height: 30px;">
										                    	<option value="">请选择</option>
										                    	<option  <c:if test="${bussaccount.accType==10010001}"> selected="selected" </c:if> value="10010001">互联网账户</option>
										                    	<option  <c:if test="${bussaccount.accType==10010002}"> selected="selected" </c:if> value="10010002" >委托出借账户</option>
										                    	<option  <c:if test="${bussaccount.accType==10010003}"> selected="selected" </c:if> value="10010003" >借款账户</option>
										                    	<option  <c:if test="${bussaccount.accType==10010004}"> selected="selected" </c:if> value="10010004" >保理业务账户</option>
										                    	<option  <c:if test="${bussaccount.accType==10010005}"> selected="selected" </c:if> value="10010005" >代偿人账户</option>
										                    	<option  <c:if test="${bussaccount.accType==10010006}"> selected="selected" </c:if> value="10010006" >抵押权人账户</option>
										                    	<option  <c:if test="${bussaccount.accType==10011000}"> selected="selected" </c:if> value="10011000" >公司账户</option>
										                    </select>
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
                            <h2>客户账户信息列表</h2>
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
                                    <table id="borrow-rep-table12" class="table table-bordered mt15" style="text-align:center;">
                                       <%--  <col width="200" />
                                        <col /> --%>
                                        <thead>
                                        <tr>
                                              <td>客户账号</td>
                                              <td>客户编号</td>
                                              <td>客户姓名</td>
                                              <td>账户类型</td>
                                              <td>证件号码</td>
                                              <td>移动电话</td> 
                                              <td>账户状态</td>
                                              <td>账户余额</td>
                                              <td>可用余额</td>
                                              <td>冻结金额</td>
                                              <td>业务编号</td>
                                              <td>子商户号</td>
                                              <td>父商户号</td>
                                              <td>是否有效</td> 
                                              <td>银行卡号</td> 
                                              <td>创建日期</td>
                                              <td>修改日期</td> 
                                              <td>操作</td> 
                                        </tr>
                                        </thead>
                                        <tbody>
                                             <c:forEach items="${page.list}" var="bussaccount">
                                                <tr>
                                                    <td>${bussaccount.accNo}</td>
                                                    <td>${bussaccount.custNo}</td>
                                                    <td>${bussaccount.custName}</td>
                                                    <td> <fss:dictView key="${bussaccount.accType}" /></td>
                                                    <td>${bussaccount.certNo}</td>
                                                    <td>${bussaccount.mobile}</td>
                                                    <td> <fss:dictView key="${bussaccount.state}" /></td>
                                                    <td>${bussaccount.accBalance}</td>
                                                    <td>${bussaccount.accFreeze}</td>
                                                    <td>${bussaccount.accAvai}</td>
                                                    <td>${bussaccount.busiNo}</td>
                                                    <td>${bussaccount.mchnChild}</td>
                                                    <td>${bussaccount.mchnParent}</td>
                                                    <td> <fss:dictView key="${bussaccount.isValid}" /></td>
                                                    <td>${bussaccount.bankId}</td>
                                                    <td><fmt:formatDate value="${bussaccount.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    <td><fmt:formatDate value="${bussaccount.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    <td><a href="${contextPath}/fss/account/waterDetail/${bussaccount.accNo}">查看流水</a></td>
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