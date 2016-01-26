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
            <li>菜单管理</li>
            <li>账户列表</li>
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
                                <form class="smart-form" action="${contextPath}/fss/account/oldlist"  method="post" id="accountlistForm">
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
                                                        <td class="tr">账户编号：</td>
                                                        <td>
                                                            <label class="input">
                                                                <input type="text" style="width:210px" name="accountNo" value="${FundAccountEntity.accountNo}" />
                                                            </label>
                                                        </td>
                                                       <%--  <td class="tr">姓名：</td>
                                                        <td>
                                                            <label class="input" style="width:210px" >
                                                                <input type="text" name="userName" value="${FundAccountEntity.userName}" />
                                                            </label>
                                                        </td>
                                                        <td class="tr">所属部门:</td>
                                                        <td>
                                                            <label class="input"  style="width:210px" >
                                                                <input type="text" name="department" value="${FundAccountEntity.department}"/>
                                                            </label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="tr">直接上司：</td>
                                                        <td>
                                                            <label class="input" style="width:210px">
                                                                <input type="text" name="leader" value="${FundAccountEntity.leader}"/>
                                                            </label>
                                                        </td>
                                                        <td class="tr">账户状态：</td>
                                                        <td>
                                                            <label class="select" style="width:210px">
                                                                <select name="isDel" >
                                                                   <option value="0">不限</option>
                                                                    <option value="1"  ${User.isDel == 1?"selected":""}>启用</option>
                                                                    <option value="-1"  ${User.isDel == -1?"selected":""}>禁用</option>
                                                                </select>
                                                            </label>
                                                        </td>
                                                        <td class="tr">所属机构:</td>
                                                        <td>
                                                            <label class="input" style="width:210px">
                                                                <input type="text" value="${FundAccountEntity.company}" name="company"/>
                                                            </label>
                                                        </td> --%>
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
                            <h2>旧版账户列表</h2>
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
                                              <td>账户编号</td>
                                              <td>账户余额</td>
                                          <!--     <td>登录名</td>
                                              <td>性别</td>
                                              <td>所属机构</td>
                                              <td>部门</td>
                                              <td>直接上司</td>
                                              <td>岗位</td>
                                              <td>电话</td> -->
                                            <!--   <td>账户状态</td> -->
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${page.list}" var="t">
                                                <tr class="success">
                                                    <td>${t.accountNo}</td>
                                                    <td>${t.}</td>
                                                    <td>${t.loginName}</td>
                                                    <td>${t.sex}</td>
                                                    <td>${t.department}</td>
                                                    <td>${t.company}</td>
                                                    <td>${t.leader}</td>
                                                    <td>${t.job}</td>
                                                    <td>${t.isDel}</td>
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
	     DT_page("borrow-rep-table12", true, '${page.JSON}', $("#accountlistForm"));
	 }); 
 
</script>

<%@include file="../../../view/include/foot.jsp"%>
</body>

</html>